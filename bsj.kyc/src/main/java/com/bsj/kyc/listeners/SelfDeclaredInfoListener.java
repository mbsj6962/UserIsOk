package com.bsj.kyc.listeners;

import com.bsj.kyc.events.SelfDeclaredInfoEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
public class SelfDeclaredInfoListener {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @EventListener
    public void onApplicationEvent(SelfDeclaredInfoEvent selfDeclaredInfoEvent){
        logger.info("Received SelfDeclaredInfo Event : " + selfDeclaredInfoEvent.getEventType());
        logger.info("Received SelfDeclaredInfo From SelfDeclaredInfo Event : " + selfDeclaredInfoEvent.getSelfDeclaredInfo().toString());
    }

}
