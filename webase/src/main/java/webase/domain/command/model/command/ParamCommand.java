package webase.domain.command.model.command;


import com.alibaba.fastjson.JSON;
import com.bin.webase.domain.command.model.IParam;

/**
 * 命令
 */
public abstract class ParamCommand<T extends IParam> extends BaseCommand {

    private T param;

    public ParamCommand(T param) {
        super();
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
        return "param:" + JSON.toJSONString(param);
    }

}
