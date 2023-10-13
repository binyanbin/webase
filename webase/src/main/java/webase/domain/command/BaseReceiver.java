package webase.domain.command;


import com.bin.webase.domain.command.model.command.BaseCommand;
import com.bin.webase.domain.container.DomainRegistry;
import com.bin.webase.domain.container.IBranchLog;
import com.bin.webase.domain.entity.DbDomain;

import java.util.List;

/**
 * 命令接收者 执行命令
 */
public abstract class BaseReceiver<C extends BaseCommand> extends BaseContainer {

    private BaseCommand command;

    /**
     * 执行方法实现
     */
    protected abstract Result dispose(C command);

    /**
     * 命令执行
     */
    void handler(C command) {
        this.command = command;
        Result result = dispose(command);
        command.setResult(result);
        IBranchLog branchLog = DomainRegistry.getBranchLog();
        if (branchLog != null) {
            List<DbDomain> domains = branchLog.newBranchLog(command);
            if (domains != null && domains.size() > 0) {
                for (DbDomain dbDomain : domains) {
                    save(dbDomain);
                }
            }
        }
    }

    protected void execute(BaseOperator operator) {
        operator.commit(this.command);
    }


}

