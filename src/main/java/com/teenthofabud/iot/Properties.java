package com.teenthofabud.iot;

public interface Properties {
	
	public static final String BROKER = "tcp://localhost:1883";
	public static final String TOPIC = "demo/chat";
	public static final String CLIENT_ID = "anirban";
	public static final Integer QOS = 2;
	public static final Integer KEEP_ALIVE_INTERVAL = 480;
	
	public static final String MQTTEXCEPTION_FORMAT = "%s: %s [%n]";
	public static final String EXCEPTION_FORMAT = "%s: %s [%d]";
	
	public static final String WILL_TOPIC = "SOS/help";
	public static final String WILL_PAYLOAD = "Unaccounted disconnection";
	public static final Integer WILL_QOS = 1;
	public static final Boolean WILL_RETAINED = true;

}
