package com.well_talent.cjdzblistening.common.model.http.exception;

/**
 * Created by codeest on 2016/8/4.
 */
public class ListeningException extends Exception {

    private String code;

    public ListeningException(String msg) {
        super(msg);
    }

    public ListeningException(String msg, String code) {
        super(msg);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
