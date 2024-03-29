package com.bin.webase.core.entity.statemachine;

import com.bin.webase.core.model.IdName;

public class BizStateId {
    private IdName<Integer> idName;

    BizStateId(Integer id, String name) {
        this.idName = new IdName(id, name);
    }

    public Integer getId() {
        return idName.getId();
    }

    public String getName() {
        return idName.getName();
    }

    public static BizStateId newS(Integer id, String name) {
        return new BizStateId(id, name);
    }

}
