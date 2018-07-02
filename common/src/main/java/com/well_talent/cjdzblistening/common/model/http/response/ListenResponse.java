package com.well_talent.cjdzblistening.common.model.http.response;

import java.io.Serializable;

/**
 * Created by zhanf on 2018/3/9.
 */

public class ListenResponse<T> implements Serializable {
    private String code;
    private String message;
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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

