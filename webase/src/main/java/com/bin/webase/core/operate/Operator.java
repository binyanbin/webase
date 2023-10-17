package com.bin.webase.core.operate;


import com.bin.webase.core.context.WeContext;
import com.bin.webase.core.context.IBranchLog;
import com.bin.webase.core.entity.DbDomain;
import com.bin.webase.core.entity.FunctionId;
import com.bin.webase.core.entity.IBranch;
import com.bin.webase.core.entity.statemachine.BizStateMachine;
import com.bin.webase.core.entity.statemachine.IState;
import com.bin.webase.core.web.ApiToken;
import com.bin.webase.core.web.ThreadWebContextHolder;
import com.bin.webase.core.web.WebContext;
import com.bin.webase.exception.ApplicationException;
import com.bin.webase.exception.ErrorCheck;
import com.bin.webase.exception.ErrorCode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 操作
 */
public abstract class Operator<C extends IParam> extends BaseOperate {
    private static final NoParam NO_PARAM = new NoParam();

    /**
     * 执行方法实现
     */
    protected abstract Result dispose(C param);

    /**
     * 命令执行
     */
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
        return this.execute((C) NO_PARAM);
    }

    protected void doMethod(Method operator) {
        Date date = new Date();
        operator.commit(date);
    }


    protected void updateState(IState domain) {
        BizStateMachine.disposeState(domain, getCommandId());
    }

    protected abstract OperateId getCommandId();


    protected boolean isSameBranch(IBranch iBranch) {
        WebContext webContext = ThreadWebContextHolder.getContext();
        if (webContext != null) {
            ApiToken token = webContext.getToken();
            if (token != null) {
                if (token instanceof IBranch) {
                    IBranch mToken = (IBranch) token;
                    if (mToken.getBranchId() != null && iBranch.getBranchId() != null) {
                        return iBranch.getBranchId().equals(mToken.getBranchId());
                    }
                }
            }
        }
        return false;
    }


    protected void saveBranchLog(String msg, IParam param, DbDomain domain) {
        WeContext.getBranchLog().newBranchLog(getCommandId(), domain, param, msg);
    }

    protected List<FunctionId> getFunction() {
        return new ArrayList<>();
    }

    public <T extends ApiToken> T getToken(Class<T> clazz) {
        WebContext webContext = ThreadWebContextHolder.getContext();
        ApiToken token = webContext.getToken();
        if (token != null) {
            return (T) token;
        } else {
            return null;
        }
    }

    protected Date getTime() {
        return ThreadWebContextHolder.getContext().getBeginTime();
    }

    protected ApiToken getToken() {
        return ThreadWebContextHolder.getContext().getToken();
    }

    private void validateFunction() {
        List<FunctionId> functionIds = getFunction();
        if (functionIds != null && functionIds.size() > 0){
            ErrorCheck.check(FunctionId.validate(getToken(), functionIds),ErrorCode.NoFunctionID);
        }
    }

    protected void saveBranchLog(DbDomain domain, IParam param) {
        IBranchLog branchLog = WeContext.getBranchLog();
        if (branchLog != null) {
            WeContext.getBranchLog().newBranchLog(this.getCommandId(), domain, param, "");
        }
    }

    protected void saveBranchLog(DbDomain domain) {
        IBranchLog branchLog = WeContext.getBranchLog();
        if (branchLog != null) {
            WeContext.getBranchLog().newBranchLog(this.getCommandId(), domain, null, "");
        }
    }
}

