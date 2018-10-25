package com.teenthofabud.iot;

import java.io.IOException;
import java.net.InetAddress;

public class Ping
{
    public static void main(final String[] args) throws IOException
    {
    	String url = "teenthofabud.me.uk";
    	//String url = "172.16.4.1";
        final InetAddress host = InetAddress.getByName(url);
        System.out.println("host.isReachable(1000) = " + host.isReachable(1000));
    }
}