package com.bin.api.operate.base;

import com.bin.api.controller.param.LoginParam;
import com.bin.api.dao.enums.ClientType;
import com.bin.api.dao.mybatis.model.User;
import com.bin.api.dao.repository.view.EmployeeModelView;
import com.bin.api.operate.domain.cache.WebSessionDo;
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

import java.util.Set;

@Service
@Transactional(rollbackFor = Exception.class)
public class LoginPwdOp extends Operator<LoginParam> {
    @Override
    protected Result dispose(LoginParam param) {
        User user = UserDo.REPOSITORY.getByPhone(param.getPhone());
        ErrorCheck.checkNotNull(user, "用户不存在");
        ErrorCheck.checkArgument(user.getPassword().equals(param.getPassword()), "密码错误");
        EmployeeModelView employeeView = new EmployeeModelView(param.getBranchId(),user.getId());
        ErrorCheck.checkArgument(!employeeView.isRootNull(),"人员不存在");
        String versionId = ThreadWebContextHolder.getContext().getVersionId();
        Set<Integer> functions = employeeView.listFunctions();
        String sessionId = UuidUtil.newUuidString();
        String secretKey = UuidUtil.newUuidString();
        WebSessionDo webSessionDo = WebSessionDo.newInstance(sessionId,secretKey,param.getBranchId(), user.getId(), employeeView.getModel().getId(), ClientType.manager.getId(), functions,
                getTime(), versionId);
        save(webSessionDo);
        UserDo userDo = new UserDo(user);

        doMethod(new NewSession(webSessionDo,getCommandId()));

        userDo.login(getTime());
        save(userDo);

        return Result.success(webSessionDo);
    }

    @Override
    protected OperateId getCommandId() {
        return OperateDef.LOGIN_PASSWORD;
    }
}
