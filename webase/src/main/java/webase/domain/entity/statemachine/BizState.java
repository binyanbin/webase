package webase.domain.entity.statemachine;

import com.bin.webase.domain.command.model.command.BaseCommand;
import com.bin.webase.domain.command.model.command.CommandType;
import com.bin.webase.exception.ApplicationException;
import com.bin.webase.exception.ErrorCode;

import java.util.ArrayList;
import java.util.List;

public class BizState {
    private BizStateId id;
    private List<BizAction> actions;

    public BizState(BizStateId stateId) {
        this.id = stateId;
        this.actions = new ArrayList<>();
    }

    public List<BizAction> getActions() {
        return actions;
    }

    public BizStateId getId() {
        return id;
    }

    public BizState addAction(CommandType command, BizStateId resultState) {
        if (command != CommandType.NOOP) {
            validate(command);
            actions.add(new BizAction(command, resultState));
        }
        return this;
    }

    public BizState addAction(CommandType commandType) {
        validate(commandType);
        actions.add(new BizAction(commandType, null));
        return this;
    }

    private void validate(CommandType commandType) {
        for (BizAction bizAction : this.actions) {
            if (bizAction.getCommand().equals(commandType)) {
                throw new ApplicationException(ErrorCode.StateMachineSameGroupId);
            }
        }
    }

    BizAction getAction(BaseCommand baseCommand) {
        for (BizAction bizAction : this.actions) {
            if (bizAction.getCommand().equals(baseCommand.getCommandType())) {
                return bizAction;
            }
        }
        return null;
    }
}
