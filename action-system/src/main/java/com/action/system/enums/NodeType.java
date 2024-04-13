package com.action.system.enums;

public enum NodeType {
    PARENT("0"),//父节点
    CHILD("1");//子节点
    private String type;

    NodeType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
