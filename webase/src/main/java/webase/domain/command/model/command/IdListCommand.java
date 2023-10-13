package webase.domain.command.model.command;

import com.alibaba.fastjson.JSON;
import com.bin.webase.domain.command.model.IdListParam;

public abstract class IdListCommand extends BaseCommand {
    private IdListParam param;


    public IdListCommand(IdListParam param) {
        this.param = param;
    }

    public IdListParam getParam() {
        return param;
    }


    @Override
    public String getInfo() {
        return "param:" + JSON.toJSONString(param);
    }
}
