package com.geekcattle;

/**
 * @Author: xinxiaoqiang
 * @Description:
 * @Date: 2018/5/14 21:25
 */
public enum  StatusEnum {
    REGISTERED("10","已挂号"),
    TREAT("20","看病"),
    HOSPITALIZATION("30","住院"),
    LEAVE_HOSPITAL("40","出院");

    private String name;

    private String code;

    StatusEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
