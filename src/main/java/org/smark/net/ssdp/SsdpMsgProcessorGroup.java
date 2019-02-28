package org.smark.net.ssdp;

public class SsdpMsgProcessorGroup<P extends SsdpMsgProcessor> {
	void addFirst(P p) {};

	void addGroup(SsdpMsgProcessorGroup<P> p) {};

	void addTail(P p) {};
}
