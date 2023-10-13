package webase.domain.entity.statemachine;

import com.bin.webase.domain.command.model.command.CommandType;

import java.util.ArrayList;
import java.util.List;

public class NormalStateMachine implements IStateMachine {
    private static final BizStateId ENABLE = BizStateId.newS(1, "正常");
    private static final BizStateId DISABLE = BizStateId.newS(0, "禁用");
    private static final BizStateId DELETE = BizStateId.newS(2, "删除");

    private BizStateMachine bizStateMachine;
    private CommandType disableCommand;
    private CommandType deleteCommand;
    private CommandType updateCommand;
    private CommandType addCommand;

    public static List<Integer> noDelete() {
        List<Integer> result = new ArrayList<>(2);
        result.add(ENABLE.getId());
        result.add(DISABLE.getId());
        return result;
    }

    public NormalStateMachine(CommandType add, CommandType update, CommandType delete, CommandType disable) {
        this.addCommand = add;
        this.updateCommand = update;
        this.deleteCommand = delete;
        this.disableCommand = disable;
        bizStateMachine = new BizStateMachine().
                addFirstAction(new BizAction(addCommand, NormalStateMachine.ENABLE)).
                addState(new BizState(NormalStateMachine.ENABLE)
                        .addAction(deleteCommand, NormalStateMachine.DELETE)
                        .addAction(disableCommand, NormalStateMachine.DISABLE)
                        .addAction(updateCommand, NormalStateMachine.ENABLE))
                .addState(new BizState(NormalStateMachine.DISABLE)
                        .addAction(deleteCommand, NormalStateMachine.DELETE))
                .addState(new BizState(NormalStateMachine.DELETE)
                        .addAction(CommandType.NOOP));
    }

    public NormalStateMachine(CommandType add, CommandType update, CommandType delete) {
        this.addCommand = add;
        this.updateCommand = update;
        this.deleteCommand = delete;
        this.disableCommand = CommandType.NOOP;
        bizStateMachine = new BizStateMachine().
                addFirstAction(new BizAction(addCommand, NormalStateMachine.ENABLE)).
                addState(new BizState(NormalStateMachine.ENABLE)
                        .addAction(deleteCommand, NormalStateMachine.DELETE)
                        .addAction(updateCommand, NormalStateMachine.ENABLE))
                .addState(new BizState(NormalStateMachine.DELETE)
                        .addAction(CommandType.NOOP));
    }

    public NormalStateMachine(CommandType add, CommandType delete) {
        this.addCommand = add;
        this.deleteCommand = delete;
        this.disableCommand = this.updateCommand = CommandType.NOOP;
        bizStateMachine = new BizStateMachine().
                addFirstAction(new BizAction(addCommand, NormalStateMachine.ENABLE)).
                addState(new BizState(NormalStateMachine.ENABLE)
                        .addAction(deleteCommand, NormalStateMachine.DELETE))
                .addState(new BizState(NormalStateMachine.DELETE)
                        .addAction(CommandType.NOOP));
    }


    @Override
    public BizStateId parse(Integer stateId) {
        return bizStateMachine.parse(stateId);
    }

    @Override
    public BizState getState(Integer stateId) {
        return bizStateMachine.getState(stateId);
    }

    @Override
    public List<BizAction> getFirstAction() {
        return bizStateMachine.listAction();
    }


}
