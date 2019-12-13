package com.zxq.event;

public interface EventInterface<T> {

    void invoke(T t);
}
