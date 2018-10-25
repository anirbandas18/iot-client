package com.teenthofabud.iot;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class BandwidthTester {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String host = "test-204.cadencecloud.net"; 
		InetSocketAddress socketAddress = new InetSocketAddress(host, 80); 
		SocketChannel channel = SocketChannel.open(); 
		channel.configureBlocking(false); 
		System.out.println(channel.connect(socketAddress));
	}

}
