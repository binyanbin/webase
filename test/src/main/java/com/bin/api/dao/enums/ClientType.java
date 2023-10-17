package com.bin.api.dao.enums;

public enum ClientType {
    manager(1, "管理端"),
    guest(2, "游客端"),
    ;

    private final Integer id;
    private final String name;

    ClientType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static ClientType parse(Integer value) {
        if (null == value) {
            return null;
        }
        ClientType[] coll = values();
        for (ClientType item : coll) {
            if (item.getId().equals(value)) {
                return item;
            }
        }
        return null;
    }

}
