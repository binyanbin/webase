package com.bin.webase.domain.web;

import com.bin.webase.domain.container.function.Function;
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

    public void setFunctionIds(Set<Integer> functionIds){
        this.functionIds = functionIds;
    }

    public boolean validFunction(Function function) {
        if (function != null) {
            return functionIds.contains(function.getFunctionId());
        } else {
            return true;
        }
    }


    @Override
    public String getUniqueId() {
        return uniqueId;
    }




}
