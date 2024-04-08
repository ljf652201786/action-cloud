package com.action.common.enums;

public enum UseType {
    ENABLE("1"),
    DISABLED("0");
    private String status;

    UseType(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
