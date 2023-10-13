package webase.domain.container;

import com.bin.webase.domain.command.model.command.CommandType;

import java.util.List;

public interface IListCommand {
    List<CommandType> list();
}
