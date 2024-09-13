package com.action.system.enums;

public enum DataType {
    MENU("M"),//菜单
    TABLE("T");//按钮
    private String type;

    DataType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
