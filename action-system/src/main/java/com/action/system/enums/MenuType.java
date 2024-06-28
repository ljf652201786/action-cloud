package com.action.system.enums;

public enum MenuType {
    LIST("C"),//目录
    MENU("M"),//菜单
    BUTTON("B");//按钮
    private String type;

    MenuType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
