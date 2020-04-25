package com.bsj.kyc.events;

import com.bsj.kyc.model.db.SelfDeclaredInfo;
import org.springframework.context.ApplicationEvent;

public class SelfDeclaredInfoEvent extends ApplicationEvent{
    private String eventType;
    private SelfDeclaredInfo selfDeclaredInfo;

    public SelfDeclaredInfoEvent(String eventType, SelfDeclaredInfo selfDeclaredInfo) {
        super(selfDeclaredInfo);
        this.eventType = eventType;
        this.selfDeclaredInfo = selfDeclaredInfo;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public SelfDeclaredInfo getSelfDeclaredInfo() {
        return selfDeclaredInfo;
    }

    public void setSelfDeclaredInfo(SelfDeclaredInfo selfDeclaredInfo) {
        this.selfDeclaredInfo = selfDeclaredInfo;
    }

    @Override
    public String toString() {
        return "SelfDeclaredInfoEvents{" +
                "eventType='" + eventType + '\'' +
                ", selfDeclaredInfo=" + selfDeclaredInfo +
                '}';
    }
}
