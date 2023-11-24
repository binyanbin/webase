package com.bin.webase.core.operate;


import com.bin.webase.core.context.IOperateLog;
import com.bin.webase.core.context.WebaseContext;
import com.bin.webase.core.entity.DbDomain;
import com.bin.webase.core.entity.IBranch;
import com.bin.webase.core.entity.statemachine.BizStateMachine;
import com.bin.webase.core.entity.statemachine.IState;
import com.bin.webase.core.model.IParam;
import com.bin.webase.core.model.NoParam;
import com.bin.webase.core.model.OperateId;
import com.bin.webase.core.web.ApiToken;
import com.bin.webase.exception.ApplicationException;
import com.bin.webase.exception.ErrorCode;

import java.util.Date;

/**
 * 操作
 */
public abstract class Operator<C extends IParam> extends BaseOperate {


    protected abstract Result dispose(C param);

    public Result execute(C param) {
        validateFunction();
        param.validate();
        Result result = dispose(param);
        if (result.getState() != ResultState.fail) {
            UnitWorkUtils.commit();
        }
        return result;
    }

    public Result execute() {
        return this.execute((C) NoParam.NO_PARAM);
    }

    protected void doMethod(Method operator) {
        Date date = new Date();
        operator.commit(date);
    }


    protected void updateState(IState domain) {
        BizStateMachine.disposeState(domain, getCommandId());
    }

    protected abstract OperateId getCommandId();


    protected <T extends ApiToken> boolean isSameBranch(Class<T> clazz, IBranch iBranch) {
        T token = getToken(clazz);
        if (token instanceof IBranch) {
            IBranch mToken = (IBranch) token;
            if (mToken.getBranchId() != null && iBranch.getBranchId() != null) {
                return iBranch.getBranchId().equals(mToken.getBranchId());
            }
        }
        return false;
    }

    protected void saveBranchLog(DbDomain domain, IParam param, String msg) {
        IOperateLog operateLog = WebaseContext.getOperateLog();
        if (operateLog == null) {
            throw new ApplicationException(ErrorCode.NullPointerException, "日志接口未实现");
        }
        WebaseContext.getOperateLog().log(getCommandId(), domain, param, msg);
    }

}

