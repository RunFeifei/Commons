package com.fei.root.common.Rxbus;

/**
 * Created by PengFeifei on 17-5-12.
 */

public final class BusEvent {

    private int eventId;
    private String content;

    public BusEvent(int eventId, String content) {
        this.eventId = eventId;
        this.content = content;
    }

    public int getEventId() {
        return eventId;
    }

    public String getContent() {
        return content;
    }
}
