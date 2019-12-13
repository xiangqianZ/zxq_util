package com.zxq.empty;

import com.zxq.exception.ResultJsonException;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @Author aihui075
 * @Date 2018/4/16.
 * @Description
 */
public class Param {

   public static <T> void paramIsNull(T t,String... param) {
       if(t == null)
           throw new ResultJsonException("参数不能为空");
//        Assert.notNull(t,"参数不能为空");
        if(t instanceof Map) {
            instanceofMap(t,param);
            return;
        }
        instanceofClass(t,param);
    }

    private static <T> void instanceofMap(T t,String... param) {
        Map map = (Map)t;
        if(map == null || map.isEmpty())
            throw new ResultJsonException("参数不能为空");
//        Assert.notEmpty(map,"参数不能为空");
        for(String str : param) {
            if(!map.containsKey(str))
                throw new ResultJsonException(str+" can't is null");
            Object obj = map.get(str);
            if(IsEmpty.isEmpty(obj))
                throw new ResultJsonException(str+" can't is null");
        }
    }

    private static <T> void instanceofClass(T t,String... param) {
        try {
            Class c = t.getClass();
            for (String s : param) {
                Field f = getFieldByClass(c,s);
                f.setAccessible(true);
                Object obj = f.get(t);
                if(obj == null || String.valueOf(obj).length()<1) {
                    throw new ResultJsonException(s+" can't is null");
                }
            }
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
            throw new ResultJsonException("系统异常");
        }
    }

    private static Field getFieldByClass(Class c,String name) {
       try {
           Field f = c.getDeclaredField(name);

           return f;
       }catch (NoSuchFieldException e) {

           Class superClass = c.getSuperclass();

           if(superClass == Object.class)
               throw new ResultJsonException(name+" can't is null");

           return getFieldByClass(c.getSuperclass(),name);
       }

    }
}
