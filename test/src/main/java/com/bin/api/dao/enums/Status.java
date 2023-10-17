package com.bin.api.dao.enums;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author yanbin
 */

public enum Status {
    /**
     * 数据状态
     */
    Valid(1, "启用"),
    Invalid(0, "禁用"),
    Delete(2, "删除"),;

    private Integer value;

    private String desc;

    Status(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static Status parse(Integer value) {
        if (null == value) {
            return null;
        }
        Status[] coll = values();
        for (Status item : coll) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }

    public static List<Integer> notDelete(){
        return Lists.newArrayList(Status.Valid.getValue(),Status.Invalid.getValue());
    }
}
