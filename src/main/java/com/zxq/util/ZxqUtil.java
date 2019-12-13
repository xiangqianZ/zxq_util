package com.zxq.util;

import com.zxq.empty.IsEmpty;

/**
 * @Author aihui075
 * @Date 2018/5/4.
 * @Description
 */
public class ZxqUtil {

    public static boolean checkPhone(String phone) {

        if (IsEmpty.isEmpty(phone)) return false;

        String reg = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";

        return phone.matches(reg);
    }

}
