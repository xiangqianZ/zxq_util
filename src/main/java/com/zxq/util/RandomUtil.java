package com.zxq.util;

import java.util.Random;

public class RandomUtil {

    public static String getRandomNum(int num) {

        String str = "1";
        for(int i=1; i<num; i++) {
            str += "0";
        }
        Double random_temp =
                BigDecimalUtil.calculation(Math.random()*9+1,Double.valueOf(str),"*");
        return String.valueOf(random_temp.longValue());
    }

    public static int getRandomNumBySize(int size) {

        Random random = new Random();
        return random.nextInt(size);
    }

}
