package com.teenthofabud.iot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MQTTApplication implements Properties {
	
	private static MqttClient client;
	
	private static MqttCallbackImpl callback;
	
	static {
		try {
			MqttConnectOptions opts = new MqttConnectOptions();
			opts.setKeepAliveInterval(KEEP_ALIVE_INTERVAL);
			opts.setWill(WILL_TOPIC, WILL_PAYLOAD.getBytes(), WILL_QOS, WILL_RETAINED);
			client = new MqttClient(BROKER, CLIENT_ID, new MemoryPersistence());
			callback = new MqttCallbackImpl(client, opts);
			client.setCallback(callback);
			client.connect(opts);
			System.out.println(CLIENT_ID + " connected to " + BROKER);
			Runtime.getRuntime().addShutdownHook(callback);
		} catch (MqttException e) {
			MqttApplicationUtility.formatException("Connection error", e);
			System.exit(6);
		}
	}
	
	public static Integer publish(String topic) {
		Integer rc = 0;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Publish something: ");
			String input = br.readLine();
			System.out.println();
			MqttMessage message = new MqttMessage(input.getBytes());
			message.setRetained(true);
			message.setQos(2);
			client.publish(topic, message);
		} catch (IOException e) {
			MqttApplicationUtility.formatException("User input error: ", e);
			rc = 1;
		} catch (MqttException e) {
			MqttApplicationUtility.formatException("Publishing error: ", e);
			rc = 2;
		} 
		return rc;
	}
	
	public static Integer subscribe (String topic) {
		Integer rc = 0;
		try {
			client.subscribe(topic);
		} catch (MqttException e) {
			MqttApplicationUtility.formatException("Subscribtion error", e);
			rc = 3;
		} 
		return rc;
	}

	public static void main(String[] args) {
		if(args.length == 2) {
			int option = Integer.parseInt(args[0]);
			String topic = args[1];
			switch(option) {
			case 1:
				System.exit(publish(topic));
				break;
			case 2:
				subscribe(topic);
				break;
			default: 
				System.err.println("Invalid option: " + option);
				System.exit(4);
			}
		} else {
			System.err.println("Invalid number of options: " + args.length);
			System.exit(5);
		}
	}

}
