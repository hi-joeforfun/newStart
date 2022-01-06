package com.event.context;


public class AbstractContextEvent implements Event {
    private static final long serialVersionUID = -6159391039546783871L;

    private final long timestamp = System.currentTimeMillis();

    public final long getTimestamp() {
        return this.timestamp;
    }
}
