package com.itstyle.common;

public enum ResultType {
    empty,

    fail,

    error,

    success,

    timeout,

    formatError,

    alreadyExists,

    login,

    maxLimit;

    private String needLogin = "needLogin";

    private String tokenInvalid = "tokenInvalid";

    public String getNeedLogin() {
        return this.needLogin;
    }

    public void setNeedLogin(String needLogin) {
        this.needLogin = needLogin;
    }

    public String getTokenInvalid() {
        return this.tokenInvalid;
    }

    public void setTokenInvalid(String tokenInvalid) {
        this.tokenInvalid = tokenInvalid;
    }
}