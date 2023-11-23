package com.bin.api.operate.domain.cache;

import com.bin.webase.core.entity.IBranch;
import com.bin.webase.core.entity.UniqueId;
import com.bin.webase.core.web.ApiToken;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.Set;

public class WebSession extends ApiToken implements UniqueId, IBranch {
    @ApiModelProperty(value = "门店id")
    private Long branchId;
    @ApiModelProperty(value = "版本id")
    private String versionId;
    @ApiModelProperty(value = "员工id")
    private Long employeeId;
    @ApiModelProperty(value = "用户id")
    private Long userId;
    private Integer clientType;

    public WebSession() {
    }

    public WebSession(String sessionId, String secretKey, Long branchId, Long userId, Long employeeId, Integer clientType, Set<Integer> functionIds, Date expirationTime, String versionId) {
        super(sessionId, secretKey, functionIds, expirationTime.getTime());
        this.clientType = clientType;
        this.branchId = branchId;
        this.versionId = versionId;
        this.employeeId = employeeId;
        this.userId = userId;
    }

    public Long getBranchId() {
        return branchId;
    }

    @Override
    public boolean branchCache() {
        return false;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getVersionId() {
        return versionId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getClientType() {
        return clientType;
    }

    public WebSessionDo toDo() {
        return new WebSessionDo(this);
    }

    public Long getUserId() {
        return userId;
    }
}
