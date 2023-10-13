package com.bin.webase.domain.web;

import com.bin.webase.domain.entity.FunctionId;
import com.bin.webase.domain.entity.UniqueId;

import java.util.Set;

public class ApiToken implements UniqueId {
    private String uniqueId;
    private String secretKey;
    private Long expirationTime;
    private Integer clientType;
    private Set<Integer> functionIds;


    public ApiToken() {
    }

    public ApiToken(String uniqueId, String secretKey, Set<Integer> functionIds, Long expirationTime, Integer clientType) {
        this.uniqueId = uniqueId;
        this.secretKey = secretKey;
        this.expirationTime = expirationTime;
        this.clientType = clientType;
        this.functionIds = functionIds;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public Long getExpirationTime() {
        return expirationTime;
    }

    public Integer getClientType() {
        return clientType;
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
