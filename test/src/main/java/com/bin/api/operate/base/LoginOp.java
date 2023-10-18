package com.bin.api.operate.base;


import com.bin.api.controller.param.UserLoginParam;
import com.bin.api.dao.enums.ClientType;
import com.bin.api.dao.mybatis.model.Branch;
import com.bin.api.dao.repository.view.EmployeeModelView;
import com.bin.api.operate.domain.cache.WebSessionDo;
import com.bin.api.operate.domain.db.BranchDo;
import com.bin.api.operate.domain.db.UserDo;
import com.bin.api.operate.method.NewSession;
import com.bin.api.utils.UuidUtil;
import com.bin.api.web.base.OperateDef;
import com.bin.webase.core.operate.Operator;
import com.bin.webase.core.operate.Result;
import com.bin.webase.core.model.OperateId;
import com.bin.webase.core.web.ThreadWebContextHolder;
import com.bin.webase.exception.ErrorCheck;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Set;

@Service
@Transactional(rollbackFor = Exception.class)
public class LoginOp extends Operator<UserLoginParam> {

    @Override
    protected Result dispose(UserLoginParam param) {

        UserDo user = new UserDo(param.getOpenId());
        ErrorCheck.checkArgument(!user.isNull(), "未绑定小程序");
        ErrorCheck.checkArgument(StringUtils.hasText(user.getPhone()), "未绑定手机号");

        Branch branch = BranchDo.REPOSITORY.getModel(user.getBranchId());
        ErrorCheck.checkNotNull(branch, "门店未找到");

        String versionId = ThreadWebContextHolder.getContext().getVersionId();
        EmployeeModelView employeeView = new EmployeeModelView(branch.getId(), user.getId());
        ErrorCheck.checkArgument(!employeeView.isRootNull(), "人员不存在");
        Set<Integer> functions = employeeView.listFunctions();
        String sessionId = UuidUtil.newUuidString();
        String secretKey = UuidUtil.newUuidString();
        WebSessionDo webSessionDo = WebSessionDo.newInstance(sessionId, secretKey, branch.getId(), user.getId(), employeeView.getModel().getId(), ClientType.manager.getId(), functions,
                getTime(), versionId);
        save(webSessionDo);

        doMethod(new NewSession(webSessionDo,getCommandId()));

        user.login(getTime());
        save(user);

        return Result.success(webSessionDo);
    }

    @Override
    protected OperateId getCommandId() {
        return OperateDef.LOGIN;
    }
}
