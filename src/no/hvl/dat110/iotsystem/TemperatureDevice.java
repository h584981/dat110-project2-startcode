package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;
import no.hvl.dat110.common.Logger;

public class TemperatureDevice {

    private static final int COUNT = 10;

    public static void main(String[] args) {

        // simulated / virtual temperature sensor
        TemperatureSensor sn = new TemperatureSensor();

        // TODO - start

        // create a client object and use it to

        // - connect to the broker
        // - publish the temperature(s)
        // - disconnect from the broker

        // TODO - end

        Client client = new Client("TempSensor", Common.BROKERHOST, Common.BROKERPORT);

        if (!client.connect())
            Logger.log("Failed to connect with " + client + " on " + Common.BROKERHOST + ":" + Common.BROKERPORT);

        for (int i = 0; i < COUNT; i++) {
            client.publish(Common.TEMPTOPIC, String.valueOf(sn.read()));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        client.disconnect();

        System.out.println("Temperature device stopping ... ");
    }
}
