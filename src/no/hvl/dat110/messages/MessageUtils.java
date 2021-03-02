package no.hvl.dat110.messages;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import no.hvl.dat110.common.Logger;
import no.hvl.dat110.messagetransport.Connection;
import no.hvl.dat110.messagetransport.TransportMessage;

public class MessageUtils {

    public static Message fromJson(String msg) {

        JsonParser jsonParser = new JsonParser();
        JsonObject json = jsonParser.parse(msg).getAsJsonObject();

        String typestr = json.get("type").getAsString();

        MessageType type = MessageType.valueOf(typestr);

        Gson gson = new Gson();
        Message message = null;

        // Enhanced switch looks way neater
        switch (type) {
            case CONNECT -> message = gson.fromJson(json, ConnectMsg.class);
            case DISCONNECT -> message = gson.fromJson(json, DisconnectMsg.class);
            case CREATETOPIC -> message = gson.fromJson(json, CreateTopicMsg.class);
            case DELETETOPIC -> message = gson.fromJson(json, DeleteTopicMsg.class);
            case SUBSCRIBE -> message = gson.fromJson(json, SubscribeMsg.class);
            case UNSUBSCRIBE -> message = gson.fromJson(json, UnsubscribeMsg.class);
            case PUBLISH -> message = gson.fromJson(json, PublishMsg.class);
            default -> Logger.log("fromJson - unknown message type");
        }

        return message;
    }

    public static Message fromBytes(byte[] payload) {

        return (fromJson(new String(payload)));
    }

    public static String toJson(Message msg) {
		// Why is inline variables never used?
        return new Gson().toJson(msg);
    }

    public static byte[] getBytes(Message msg) {

        return toJson(msg).getBytes();

    }

    public static TransportMessage toTransportMessage(Message msg) {

        return new TransportMessage(getBytes(msg));
    }

    public static Message fromTransportMessage(TransportMessage msg) {

        return fromBytes(msg.getData());
    }

    public static void send(Connection connection, Message message) {
        connection.send(toTransportMessage(message));
    }

    public static Message receive(Connection connection) {

        Logger.log("?");

        Message msg = fromTransportMessage(connection.receive());

        Logger.log(msg.toString());

        return msg;
    }
}
