package org.smark.net.ssdp;

public abstract class SsdpMsgProcessorGroup<P extends SsdpMsgProcessor> {
	abstract void addFirst(P p);
	abstract void addGroup(SsdpMsgProcessorGroup<P> p);
	abstract void addTail(P p);
}
