package org.smark.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import org.smark.net.ssdp.SSDPMsg;
import org.smark.net.ssdp.SSDPMsgPrintProcessor;
import org.smark.net.ssdp.SSDPMsgProcessor;

public class SSDP {
	SSDPMsgProcessor processor;
	
	public SSDP register(SSDPMsgProcessor processor) {
		this.processor = processor;
		return this;
	}
	
	public void start() {
		new MulticastReceiver(processor).start();
	}
	
	static class MulticastReceiver extends Thread {
		SSDPMsgProcessor processor;
	    protected MulticastSocket socket = null;
	    protected byte[] buf = new byte[256];
	    
	    public MulticastReceiver(SSDPMsgProcessor processor) {
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
			            processor.onMsg(new SSDPMsg(received));
			        }
			} catch (IOException e) {
				e.printStackTrace();
			}
	       
	    }
	}
	
	public static void main(String[] args) {
		new SSDP()
		.register(new SSDPMsgPrintProcessor())
		.start();
	}
}
