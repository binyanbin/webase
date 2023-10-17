package com.bin.api.operate.domain.cache;

import com.bin.webase.core.entity.UniqueId;
import lombok.Data;

@Data
public class ActiveEmployee implements UniqueId {
    public static String PREFIX = "active_employee_";
    private Long id;
    private String code;

    public ActiveEmployee(Long id, String code) {
        this.id = id;
        this.code = code;
    }
    
    @Override
    public String getUniqueId() {
        return PREFIX + id;
    }
}
