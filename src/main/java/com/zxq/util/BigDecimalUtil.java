package com.zxq.util;

import com.zxq.exception.ResultJsonException;

import java.math.BigDecimal;

/**
 * @Author soda
 * @Date 2018/7/10.
 * @Description
 */
public class BigDecimalUtil {

    private static BigDecimal bigDecimal1;
    private static BigDecimal bigDecimal2;

    private static void init(String s1,String s2) {
         bigDecimal1 = new BigDecimal(s1);
         bigDecimal2 = new BigDecimal(s2);
    }

    public static Double calculation(Object s1,Object s2,String type) {
        return calculation(s1.toString(),s2.toString(),type);
    }
    public static Double calculation(Object s1,Object s2,String type,int scal,int round) {
        return calculation(s1.toString(),s2.toString(),type,scal,round);
    }

    public synchronized static Double calculation(String s1,String s2,String type) {
        init(s1,s2);
        return reckon(type,2,BigDecimal.ROUND_HALF_UP);
    }

    public synchronized static Double calculation(String s1,String s2,String type,int scal,int round) {
        init(s1,s2);
        return reckon(type,scal,round);
    }

    private static Double reckon(String type,int scal,int round) {
        switch (type) {
            case "+": return bigDecimal1.add(bigDecimal2).doubleValue();
            case "-": return bigDecimal1.subtract(bigDecimal2).doubleValue();
            case "*": return bigDecimal1.multiply(bigDecimal2).doubleValue();
            case "/": return bigDecimal1.divide(bigDecimal2,scal,round).doubleValue();
            default: throw new ResultJsonException(type + "异常");
        }
    }
}
