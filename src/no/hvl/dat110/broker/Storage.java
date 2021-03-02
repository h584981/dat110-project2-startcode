package no.hvl.dat110.broker;

import no.hvl.dat110.messagetransport.Connection;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Storage {

    // data structure for managing subscriptions
    // maps from a topic to set of subscribed users
    protected ConcurrentHashMap<String, Set<String>> subscriptions;

    // data structure for managing currently connected clients
    // maps from user to corresponding client session object

    protected ConcurrentHashMap<String, ClientSession> clients;

    public Storage() {
        subscriptions = new ConcurrentHashMap<>();
        clients = new ConcurrentHashMap<>();
    }

    public Collection<ClientSession> getSessions() {
        return clients.values();
    }

    public Set<String> getTopics() {
        return subscriptions.keySet();
    }

    // get the session object for a given user
    // session object can be used to send a message to the user

    public ClientSession getSession(String user) {
        return clients.get(user);
    }

    public Set<String> getSubscribers(String topic) {
        return subscriptions.get(topic);
    }

    public void addClientSession(String user, Connection connection) {
        clients.put(user, new ClientSession(user, connection));
    }

    public void removeClientSession(String user) {
        clients.remove(user);
    }

    public void createTopic(String topic) {
        subscriptions.put(topic, new HashSet<>());
    }

    public void deleteTopic(String topic) {
        subscriptions.remove(topic);
    }

    public void addSubscriber(String user, String topic) {
        subscriptions.get(topic).add(user);

    }

    public void removeSubscriber(String user, String topic) {
        subscriptions.get(topic).remove(user);
    }
}
