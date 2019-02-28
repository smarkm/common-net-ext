package org.smark.net.ssdp;

public class SsdpMsgPrintProcessor implements SsdpMsgProcessor {

	@Override
	public void onMsg(SsdpMsg msg) {
		System.err.println(msg.getContent());
	}

}
