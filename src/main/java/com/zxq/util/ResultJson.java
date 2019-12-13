package com.zxq.util;

import com.alibaba.fastjson.JSONObject;

public class ResultJson {
    
    private static final String SUCCESS = "1";
    private static final String ERROR = "0";

    public static JSONObject resultSuccess() {
        return result(SUCCESS,null,null);
    }

    public static JSONObject resultSuccess(String msg) {
        return result(SUCCESS,msg,null);
    }
    public static JSONObject resultSuccess(Object data) {
        return result(SUCCESS,null,data);
    }
    public static JSONObject resultSuccess(String msg,Object data) {
        return result(SUCCESS,msg,data);
    }
    public static JSONObject resultError() {
        return result(ERROR,null,null);
    }

    public static JSONObject resultError(String msg) {
        return result(ERROR,msg,null);
    }
    public static JSONObject resultError(Object data) {
        return result(ERROR,null,data);
    }
    public static JSONObject resultError(String msg,Object data) {
        return result(ERROR,msg,data);
    }

    public static JSONObject result(Integer flag,String msg,Object data) {
        return result(flag.toString(),msg,data);
    }

    private static JSONObject result(String type, String msg, Object data) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result",type);
        jsonObject.put("msg",msg);
        jsonObject.put("data",data);
        return jsonObject;
    }
}
