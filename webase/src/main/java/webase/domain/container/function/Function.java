package webase.domain.container.function;

import com.bin.webase.domain.command.model.command.IdName;

public class Function {
    private IdName<Integer> idName;

    public Function(Integer id, String name) {
        this.idName = new IdName<>(id, name);
    }

    public static Function newF(Integer id, String name) {
        return new Function(id, name);
    }

    public Integer getId() {
        return idName.getId();
    }

    public Integer getFunctionId() {
        return idName.getId();
    }

    public String getName() {
        return idName.getName();
    }

}
