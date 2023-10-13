package com.bin.webase.domain.command.model.command;

import com.bin.webase.domain.command.BaseReceiver;
import com.bin.webase.domain.command.Result;
import com.bin.webase.domain.entity.DbDomain;
import com.bin.webase.domain.entity.FunctionObject;
import com.bin.webase.domain.entity.IBranch;
import com.bin.webase.domain.entity.statemachine.BizStateMachine;
import com.bin.webase.domain.entity.statemachine.IState;
import com.bin.webase.domain.web.ApiToken;
import com.bin.webase.exception.ErrorCheck;
import com.bin.webase.exception.ErrorCode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class BaseCommand extends FunctionObject {

    private Date now;
    private String message;
    private List<DbDomain> dbDomain;
    private Result result;

    public BaseCommand() {
        super();
        ErrorCheck.checkNotNull(getCommandId(), ErrorCode.NoFunctionID);
        this.now = new Date();
        this.dbDomain = new ArrayList<>();
    }

    public Date getNow() {
        return now;
    }

    public void setNow(Date now) {
        this.now = now;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DbDomain> getDbDomain() {
        return dbDomain;
    }

    private void addDbDomain(DbDomain dbDomain) {
        this.dbDomain.add(dbDomain);
    }

    public boolean isSameBranch(IBranch iBranch) {
        ApiToken token = getToken();
        if (token != null) {
            if (token instanceof IBranch) {
                IBranch mToken = (IBranch) token;
                if (mToken.getBranchId() != null && iBranch.getBranchId() != null) {
                    return iBranch.getBranchId().equals(mToken.getBranchId());
                }
            }
        }
        return false;
    }

    public void saveBranchLog(String msg, DbDomain domain) {
        setMessage(msg);
        addDbDomain(domain);
    }

    public void saveBranchLog(DbDomain domain) {
        addDbDomain(domain);
        setMessage("");
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }


    public abstract BaseReceiver getReceiver();

    public abstract CommandId getCommandId();

    public abstract String getInfo();

    public void updateState(IState domain) {
        BizStateMachine.disposeState(domain, this);
    }
}
