package com.zxq.exception;

import com.alibaba.fastjson.JSONObject;
import com.zxq.util.ResultJson;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class ExceptionCatch {

    @ExceptionHandler(ResultJsonException.class)
    public JSONObject resultJsonException(Exception e) {

        e.printStackTrace();

        return ResultJson.resultError(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public JSONObject runtimeException(Exception e) {

        e.printStackTrace();

        return ResultJson.resultError("服务器异常，请联系管理员!");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public JSONObject missingBody(Exception e) {

        e.printStackTrace();

        return ResultJson.resultError("request body is missing");
    }
}
