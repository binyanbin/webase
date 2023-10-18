package com.bin.api.controller.param;

import com.bin.webase.core.model.IParam;
import com.bin.webase.exception.ErrorCheck;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Data
public class RoleParam implements IParam {
    @ApiModelProperty(name = "角色名称", required = true)
    private String name;
    @ApiModelProperty(name = "权限ID列表", required = true)
    private List<Long> functionIds;




    @Override
    public void validate() {
        ErrorCheck.checkArgument(StringUtils.isNotBlank(name),"名称不能为空");
        ErrorCheck.checkArgument(!CollectionUtils.isEmpty(functionIds),"权限不能为空");
    }
}

