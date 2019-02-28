package org.smark.net.ssdp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public  class SsdpMsgReceiver extends Thread {
	SsdpMsgProcessor processor;
    protected MulticastSocket socket = null;
    protected byte[] buf = new byte[256];
    
    public SsdpMsgReceiver(SsdpMsgProcessor processor) {
		super();
		this.processor = processor;
	}

	public void run() {
        try {
			socket = new MulticastSocket(1900);
			 InetAddress group = InetAddress.getByName("239.255.255.250");
		        socket.joinGroup(group);
		        while (true) {
		            DatagramPacket packet = new DatagramPacket(buf, buf.length);
		            socket.receive(packet);
		            String received = new String(
		              packet.getData(), 0, packet.getLength());
		            processor.onMsg(new SsdpMsg(received));
		        }
		} catch (IOException e) {
			e.printStackTrace();
		}
       
    }
}