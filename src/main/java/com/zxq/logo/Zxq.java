package com.zxq.logo;

//import com.zxq.empty.ParamCheckAop;

import com.zxq.empty.ParamCheckAop;
import com.zxq.event.EventFactory;
import com.zxq.event.EventListenerBase;
import com.zxq.exception.EnableExceptionCatch;
import com.zxq.exception.ExceptionCatch;
import com.zxq.redis.RedisUtils;
import com.zxq.util.LoadBeanUtil;
import com.zxq.util.SpringUtil;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author aihui075
 * @Date 2018/4/27.
 * @Description
 */

public class Zxq extends SpringApplication{

    public static Class runObject;

    private static List<Class> loadBeans = new ArrayList();

    private static void initZxq() {
        loadBeans.add(runObject);
        loadBeans.add(EventListenerBase.class);
        loadBeans.add(ParamCheckAop.class);

//        if(MybatisInit.init(runObject))
//            loadBeans.add(MyBatisConfig.class);

        if(runObject.isAnnotationPresent(EnableExceptionCatch.class))
            loadBeans.add(ExceptionCatch.class);
    }

    public static ConfigurableApplicationContext run2(Class source, String... args) {
        Zxq.runObject = source;

        initZxq();

        SpringApplication springApplication
                = new SpringApplication(loadBeans.toArray(new Class[]{}));
        try {
            Field field = springApplication.getClass().getDeclaredField("bannerMode");
            field.setAccessible(true);
            field.set(springApplication, Banner.Mode.OFF);

        }catch (Exception e) {
            e.printStackTrace();
        }

        ConfigurableApplicationContext configurableApplicationContext = springApplication.run(args);

        Logo.printLogo();

        new SpringUtil().setApplicationContext(configurableApplicationContext);

        LoadBeanUtil.loadBean(RedisUtils.class);

        new Thread(new Runnable() {
            @Override
            public void run() {
                EventFactory.addBean();
            }
        }).start();

        return configurableApplicationContext;
    }

    private static void loadConfig(SpringApplication springApplication) {
        Class c = springApplication.getMainApplicationClass();

//        if(c.isAnnotationPresent(EnableMybatis.class))

    }

}
