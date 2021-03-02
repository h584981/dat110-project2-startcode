package no.hvl.dat110.broker;

import no.hvl.dat110.common.Logger;
import no.hvl.dat110.common.Stopable;
import no.hvl.dat110.common.TODO;
import no.hvl.dat110.messages.*;
import no.hvl.dat110.messagetransport.Connection;

import java.util.Collection;

public class Dispatcher extends Stopable {

    private Storage storage;

    public Dispatcher(Storage storage) {
        super("Dispatcher");
        this.storage = storage;

    }

    @Override
    public void doProcess() {

        Collection<ClientSession> clients = storage.getSessions();

        Logger.lg(".");
        for (ClientSession client : clients) {

            Message msg = null;

            if (client.hasData()) {
                msg = client.receive();
            }

            // a message was received
            if (msg != null) {
                dispatch(client, msg);
            }
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void dispatch(ClientSession client, Message msg) {

        MessageType type = msg.getType();

        // invoke the appropriate handler method
        // enhanced switch looks better
        switch (type) {
            case DISCONNECT -> onDisconnect((DisconnectMsg) msg);
            case CREATETOPIC -> onCreateTopic((CreateTopicMsg) msg);
            case DELETETOPIC -> onDeleteTopic((DeleteTopicMsg) msg);
            case SUBSCRIBE -> onSubscribe((SubscribeMsg) msg);
            case UNSUBSCRIBE -> onUnsubscribe((UnsubscribeMsg) msg);
            case PUBLISH -> onPublish((PublishMsg) msg);
            default -> Logger.log("broker dispatch - unhandled message type");
        }
    }

    // called from Broker after having established the underlying connection
    public void onConnect(ConnectMsg msg, Connection connection) {

        String user = msg.getUser();

        Logger.log("onConnect:" + msg.toString());

        storage.addClientSession(user, connection);

    }

    // called by dispatch upon receiving a disconnect message
    public void onDisconnect(DisconnectMsg msg) {

        String user = msg.getUser();

        Logger.log("onDisconnect:" + msg.toString());

        storage.removeClientSession(user);

    }

    public void onCreateTopic(CreateTopicMsg msg) {

        Logger.log("onCreateTopic:" + msg.toString());

        storage.createTopic(msg.getTopic());

    }

    public void onDeleteTopic(DeleteTopicMsg msg) {

        Logger.log("onDeleteTopic:" + msg.toString());

        storage.deleteTopic(msg.getTopic());
    }

    public void onSubscribe(SubscribeMsg msg) {

        Logger.log("onSubscribe:" + msg.toString());

        storage.addSubscriber(msg.getUser(), msg.getTopic());
    }

    public void onUnsubscribe(UnsubscribeMsg msg) {

        Logger.log("onUnsubscribe:" + msg.toString());

        storage.removeSubscriber(msg.getUser(), msg.getTopic());
    }

    public void onPublish(PublishMsg msg) {

        Logger.log("onPublish:" + msg.toString());

        storage.getSubscribers(msg.getTopic()).forEach(s -> storage.getSession(s).send(msg));
    }
}
