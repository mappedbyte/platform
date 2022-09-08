package com.francis.platform.log;

/**
 * @Author Francis
 * @Date 2022-09-05 16:41
 **/
public enum Logger {
    ADD("新增"),
    DELETE("删除"),
    UPDATE("修改"),
    QUERY("查询");


    private String value;

    public void setValue(String value) {
        this.value = value;
    }
    public String  getValue() {
        return value;
    }

    Logger(String s) {
        this.value = s;
    }
}
