package org.smark.net.ssdp;

public class SSDPMsgPrintProcessor implements SSDPMsgProcessor {

	@Override
	public void onMsg(SSDPMsg msg) {
		System.err.println(msg.getContent());
	}

}
