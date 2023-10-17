package com.bin.api.controller;

import com.bin.api.controller.param.IdParam;
import com.bin.api.controller.param.LoginParam;
import com.bin.api.controller.param.UserLoginParam;
import com.bin.api.operate.base.LoginPwdOp;
import com.bin.api.operate.base.LoginOp;
import com.bin.api.operate.base.LogoutOp;
import com.bin.api.operate.base.SwitchBranchR;

import com.bin.webase.core.context.WeContext;
import com.bin.webase.core.operate.Result;
import com.bin.webase.core.query.NoResult;
import com.bin.webase.core.query.Query;
import com.bin.webase.core.query.QueryUtils;
import com.bin.webase.core.web.ApiMethodAttribute;
import com.bin.webase.core.web.ApiToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {


    @ApiMethodAttribute(nonSessionValidation = true)
    @RequestMapping(value = "login", method = {RequestMethod.POST})
    public Query<ApiToken> Login(@RequestBody UserLoginParam param) {
        Result result = WeContext.getBean(LoginOp.class).execute(param);
        return QueryUtils.success(result.getData(ApiToken.class));
    }

    @ApiMethodAttribute(nonSessionValidation = true)
    @RequestMapping(value = "login2", method = {RequestMethod.POST})
    public Query<ApiToken> Login(@RequestBody LoginParam param) {
        Result result = WeContext.getBean(LoginPwdOp.class).execute(param);
        return QueryUtils.success(result.getData(ApiToken.class));
    }

    @ApiMethodAttribute()
    @RequestMapping(value = "logout", method = {RequestMethod.POST})
    public NoResult logout() {
        WeContext.getBean(LogoutOp.class).execute();
        return QueryUtils.success();
    }

    @ApiMethodAttribute()
    @RequestMapping(value = "switch/{branchId}", method = {RequestMethod.POST})
    public NoResult switcha(@PathVariable IdParam param) {
        WeContext.getBean(SwitchBranchR.class).execute(param);
        return QueryUtils.success();
    }


}
