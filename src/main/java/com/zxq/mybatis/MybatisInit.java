package com.zxq.mybatis;

import com.zxq.logo.Zxq;
import lombok.Data;

@Data
public class MybatisInit {

    public static String mapperLocations;

    public static String mapperPackage;


    public static boolean init(Class runApplication) {

        if(!runApplication.isAnnotationPresent(EnableMybatis.class))
            return false;

        EnableMybatis enableMybatis = (EnableMybatis) runApplication.getAnnotation(EnableMybatis.class);

        MybatisInit.mapperLocations = enableMybatis.mapperLocations();
        MybatisInit.mapperPackage = enableMybatis.mapperPackage();

        return true;
    }


    public static void main(String[] args) {
        init(Zxq.class);
    }

}
