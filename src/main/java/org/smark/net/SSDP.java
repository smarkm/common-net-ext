package org.smark.net;

import org.smark.net.ssdp.SsdpMsgPrintProcessor;
import org.smark.net.ssdp.SsdpMsgProcessor;
import org.smark.net.ssdp.SsdpMsgReceiver;

public class SSDP {
	SsdpMsgProcessor processor;
	
	public SSDP register(SsdpMsgProcessor processor) {
		this.processor = processor;
		return this;
	}
	
	public void start() throws NullPointerException{
		if (null==processor) {
			throw new NullPointerException();
		}
		new SsdpMsgReceiver(processor).start();
	}
	
	
	
	public static void main(String[] args) {
		new SSDP()
		.register(new SsdpMsgPrintProcessor())
		.start();
	}
}
