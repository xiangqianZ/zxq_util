package com.zxq.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EventListenerBase {

    @EventListener(classes = EventBase.class)
    @Async
    public void listener(EventBase eventBase) {

        Object obj = eventBase.getSource();

        log.info("------------invoke  {}--------------",obj);

        EventFactory.invoke(obj);

    }

}
