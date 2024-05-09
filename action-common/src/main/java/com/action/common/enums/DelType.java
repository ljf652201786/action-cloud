package com.action.common.enums;

public enum DelType {
    EXIST("1"),
    lOSE("0");
    private String status;

    DelType(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
