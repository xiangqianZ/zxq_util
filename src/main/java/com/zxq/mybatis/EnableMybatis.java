package com.zxq.mybatis;

import javax.annotation.Generated;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableMybatis {

    String mapperPackage();

    String mapperLocations();
}
