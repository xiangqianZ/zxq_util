package com.zxq.exception;

/**
 * @Author aihui075
 * @Date 2018/4/12.
 * @Description
 */
public class ResultJsonException extends RuntimeException {

    private String result;
    private String msg;
    private Object data;

    public String getResult() {
        return result;
    }

    public Object getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public ResultJsonException() {
        super();
    }

    public ResultJsonException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public ResultJsonException(String result, String msg, Object data) {
        super();
        this.result = result;
        this.msg = msg;
        this.data = data;
    }
}
