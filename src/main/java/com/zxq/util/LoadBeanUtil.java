package com.zxq.util;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

public class LoadBeanUtil {

    public static void loadBean(Class className) {

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(className);

        char[] chars = className.getSimpleName().toCharArray();
        chars[0] += 32;

        SpringUtil.defaultListableBeanFactory
                .registerBeanDefinition(String.valueOf(chars), beanDefinitionBuilder.getBeanDefinition());
    }
}
