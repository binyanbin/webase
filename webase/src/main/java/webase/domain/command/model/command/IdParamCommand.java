package webase.domain.command.model.command;

import com.alibaba.fastjson.JSON;
import com.bin.webase.domain.command.model.IParam;

public abstract class IdParamCommand<T extends IParam> extends IdCommand {
    private T param;

    public IdParamCommand(Long id, T param) {
        super(id);
        param.validate();
        this.param = param;
    }

    public T getParam() {
        return param;
    }

    public void setParam(T param) {
        this.param = param;
    }

    @Override
    public String getInfo() {
        return "id:" + getId().toString() + ",param:" + JSON.toJSONString(param);
    }
}
