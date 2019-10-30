package com.itstyle.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultData {
    private Object status;
    private String message;
    private Object data;

    public static ResultData format(String message) {
        ResultData result = new ResultData();
        result.setStatus(ResultType.formatError);
        result.setMessage(message);
        return result;
    }

    public static ResultData login() {
        ResultData result = new ResultData();
        result.setStatus(ResultType.login.getNeedLogin());
        result.setMessage("");
        return result;
    }

    public static ResultData timeout() {
        ResultData result = new ResultData();
        result.setStatus(ResultType.timeout);
        result.setMessage("");
        return result;
    }

    public static ResultData success(Object resultData) {
        ResultData result = new ResultData();
        result.setStatus(ResultType.success);
        result.setMessage("");
        result.setData(resultData);
        return result;
    }

    public static ResultData success() {
        ResultData result = new ResultData();
        result.setStatus(ResultType.success);
        result.setMessage("");
        return result;
    }

    public static ResultData error(String message) {
        ResultData result = new ResultData();
        result.setStatus(ResultType.error);
        result.setMessage(message);
        return result;
    }

    public static ResultData fail(String message) {
        ResultData result = new ResultData();
        result.setStatus(ResultType.fail);
        result.setMessage(message);
        return result;
    }

    public static ResultData maxLimit(String message) {
        ResultData result = new ResultData();
        result.setStatus(ResultType.maxLimit);
        result.setMessage(message);
        return result;
    }

    public static ResultData empty() {
        ResultData result = new ResultData();
        result.setStatus(ResultType.empty);
        result.setMessage("");
        return result;
    }

    public Object getStatus() {
        return this.status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}