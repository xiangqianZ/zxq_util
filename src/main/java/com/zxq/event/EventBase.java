package com.zxq.event;

import org.springframework.context.ApplicationEvent;


public class EventBase<T> extends ApplicationEvent {


    public EventBase(T t) {
        super(t);
    }
}