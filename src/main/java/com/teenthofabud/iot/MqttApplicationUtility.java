package com.teenthofabud.iot;

import java.io.IOException;

import org.eclipse.paho.client.mqttv3.MqttException;

public class MqttApplicationUtility implements Properties{

	protected static void formatException(String message, Exception e) {
		if(e instanceof MqttException) {
			MqttException me = (MqttException) e;
			System.err.println(String.format(MQTTEXCEPTION_FORMAT, new Object[] {message, e.getMessage(),  me.getReasonCode()}));
		} else if(e instanceof IOException) {
			IOException ioe = (IOException) e;
			System.err.println(String.format(EXCEPTION_FORMAT, new Object[] {message, ioe.getMessage()}));
		}
		e.printStackTrace();
	}
	
}
