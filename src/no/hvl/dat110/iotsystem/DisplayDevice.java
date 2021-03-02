package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;
import no.hvl.dat110.common.Logger;

public class DisplayDevice {

    private static final int COUNT = 10;

    public static void main(String[] args) {

        System.out.println("Display starting ...");

        // TODO - START

        // create a client object and use it to

        // - connect to the broker
        // - create the temperature topic on the broker
        // - subscribe to the topic
        // - receive messages on the topic
        // - unsubscribe from the topic
        // - disconnect from the broker

        // TODO - END

        Client client = new Client("Display", Common.BROKERHOST, Common.BROKERPORT);

        if(!client.connect()) Logger.log("Failed to connect with " + client);

        client.createTopic(Common.TEMPTOPIC);

        client.subscribe(Common.TEMPTOPIC);

        for (int i = 0; i < COUNT; i++) {
            client.receive();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        client.unsubscribe(Common.TEMPTOPIC);

        client.disconnect();

        System.out.println("Display stopping ... ");
    }
}
