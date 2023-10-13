package webase.domain.container;


import com.bin.webase.domain.command.model.command.BaseCommand;
import com.bin.webase.domain.entity.DbDomain;

import java.util.List;

public interface IBranchLog {
    List<DbDomain> newBranchLog(BaseCommand command);
}
