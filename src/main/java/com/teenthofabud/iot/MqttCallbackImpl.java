package com.teenthofabud.iot;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttCallbackImpl extends Thread implements MqttCallback  {
	
	private MqttClient client;
	private MqttConnectOptions opts;
	
	public MqttCallbackImpl(MqttClient client, MqttConnectOptions opts) {
		this.client = client;
		this.opts = opts;
	}

	@Override
	public void connectionLost(Throwable cause) {
		// TODO Auto-generated method stub
		try {
			System.err.println(client.getClientId() + " disconnected from " + client.getServerURI());
			IMqttToken token = this.client.connectWithResult(opts);
			System.out.println(client.getClientId() + " reconnectd to " + client.getServerURI());
			System.out.println("Topics available: " + token.getTopics());
		} catch (MqttException e) {
			MqttApplicationUtility.formatException("Unable to reconnect", e);
			System.exit(7);
		}
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		// TODO Auto-generated method stub
		String payload = new String(message.getPayload());
		System.out.println("Subscription " + topic + " returned: " + payload + " [QoS: " + message.getQos() + "]");
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub
		if(token.isComplete()) {
			System.out.println(token.getMessageId() + " delivered");
		} else {
			System.out.println(token.getMessageId() + " un-delivered");
		}
	}
	
	@Override
	public void run() {
		try {
			this.client.disconnect();
			this.client.close();
		} catch (MqttException e) {
			MqttApplicationUtility.formatException("Unable to disconnect/close", e);
			System.exit(8);
		}
	}

}
