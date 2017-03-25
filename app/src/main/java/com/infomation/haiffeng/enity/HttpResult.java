package com.infomation.haiffeng.enity;

/**
 * Created by helin on 2016/10/10 11:44.
 * 实体的基类
 */
public class HttpResult<T> {

    private String message;
    private T data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
