package com.zxq.date;

import com.zxq.empty.IsEmpty;
import com.zxq.exception.ResultJsonException;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateConvert {

    public static String YMDHMS0 = "yyyy-MM-dd HH:mm:ss.0";
    public static String YMDHMS = "yyyy-MM-dd HH:mm:ss";

    public static <T> List<T> dateConvert(List<T> lists, String fieldName, String oldType, String newType) {
        if (IsEmpty.isEmpty(lists, fieldName, oldType, newType))
            throw new ResultJsonException("dateConvert-->list method args is null");
        for (T list : lists) {
            dateConvert(list, fieldName, oldType, newType);
        }
        return lists;
    }

    public static <T> T dateConvert(T t, String fieldName, String oldType, String newType) {
        if (IsEmpty.isEmpty(t, fieldName, oldType, newType))
            throw new ResultJsonException("dateConvert-->ejb method args is null");

        try {
            Field f = t.getClass().getDeclaredField(fieldName);
            f.setAccessible(true);
            SimpleDateFormat sdf = new SimpleDateFormat(oldType);
            if (!IsEmpty.isEmpty(f.get(t))) {
                Date date = sdf.parse(f.get(t).toString());
                f.set(t, new SimpleDateFormat(newType).format(date));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResultJsonException("时间转换异常");
        }
        return t;
    }

    public static String dateConvert(String time, String oldType, String newType) throws Exception {
        if (IsEmpty.isEmpty(time, oldType, newType))
            throw new ResultJsonException("dateConvert-->string method args is null");
        SimpleDateFormat sdf = new SimpleDateFormat(oldType);
        Date date = sdf.parse(time);
        return new SimpleDateFormat(newType).format(date);
    }

    public static String createDate(String type) {
        if (IsEmpty.isEmpty(type)) throw new ResultJsonException("createDate method arg is null");
        return new SimpleDateFormat(type).format(new Date());
    }

    public static <T> void recentlyDate(List<T> lists, String fieldName, String oldFormat, String newFormat) {
        if (IsEmpty.isEmpty(lists, fieldName, oldFormat, newFormat))
            throw new ResultJsonException("recentlyDate--> method args is null");

        SimpleDateFormat sdf = new SimpleDateFormat(oldFormat);

        Calendar calendar = Calendar.getInstance();

        int nowDay = calendar.get(Calendar.DAY_OF_MONTH);

        lists.forEach(li -> {
            try {
                Field f = li.getClass().getDeclaredField(fieldName);
                f.setAccessible(true);
                Object createTimeObj = f.get(li);
                if (!IsEmpty.isEmpty(createTimeObj)) {
                    calendar.setTime(sdf.parse(createTimeObj.toString()));
                    int oldDay = calendar.get(Calendar.DAY_OF_MONTH);
                    int temp = nowDay - oldDay;
                    switch (temp) {
                        case 0:
                            f.set(li, "今天");
                            break;
                        case 1:
                            f.set(li, "昨天");
                            break;
                        default:
                            f.set(li, dateConvert(createTimeObj.toString(), oldFormat, newFormat));
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                throw new ResultJsonException("转换异常-->" + li);
            }
        });
    }
}