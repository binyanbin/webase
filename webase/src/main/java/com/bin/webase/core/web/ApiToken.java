package com.bin.webase.core.web;

import com.bin.webase.core.model.FunctionId;
import com.bin.webase.core.entity.UniqueId;

import java.util.Set;

public class ApiToken implements UniqueId {
    private String uniqueId;
    private String secretKey;
    private Long expirationTime;
    private Set<Integer> functionIds;


    public ApiToken() {
    }

    public ApiToken(String uniqueId, String secretKey, Set<Integer> functionIds, Long expirationTime) {
        this.uniqueId = uniqueId;
        this.secretKey = secretKey;
        this.expirationTime = expirationTime;
        this.functionIds = functionIds;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public Long getExpirationTime() {
        return expirationTime;
    }


    public Set<Integer> getFunctionIds() {
        return functionIds;
    }

    public void setFunctionIds(Set<Integer> functionIds) {
        this.functionIds = functionIds;
    }

    public boolean validFunction(FunctionId functionId) {
        if (functionId != null) {
            return functionIds.contains(functionId.getId());
        } else {
            return true;
        }
    }

    @Override
    public String getUniqueId() {
        return uniqueId;
    }


}
