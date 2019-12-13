package com.zxq.event;

import com.zxq.util.LoadBeanUtil;
import com.zxq.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class EventFactory {

    public static Map<Class,EventInterface> eventServiceMap = new HashMap();

    public static void setEvent(Class beanClass, EventInterface eventService) {
        eventServiceMap.put(beanClass,eventService);
    }

    public static void invoke(Object obj) {
        EventInterface eventInterface = eventServiceMap.get(obj.getClass());
        if (eventInterface == null) {
            throw new RuntimeException(String.format("this %s class is not exist",obj));
        }

        eventInterface.invoke(obj);
    }

    public static void addBean() {
        String[] beanNameArr = SpringUtil.getApplicationContext().getBeanNamesForAnnotation(EventService.class);

        if(beanNameArr == null)
            return;

        for(String beanName : beanNameArr) {

            Object serviceBean = SpringUtil.getBean(beanName);
            EventService annotation = serviceBean.getClass().getAnnotation(EventService.class);

            log.info("model:{},impl:{}",annotation.value(),serviceBean);
            setEvent(annotation.value(),(EventInterface)serviceBean);
        }
    }


}
