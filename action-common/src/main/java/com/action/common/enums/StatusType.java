package com.action.common.enums;

public enum StatusType {
    ENABLE("1"),
    DISABLED("0");
    private String status;

    StatusType(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
