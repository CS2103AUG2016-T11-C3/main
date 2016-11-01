package seedu.address.logic.commands;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.UnmodifiableObservableList;
import seedu.address.commons.events.ui.JumpToListRequestEvent;
import seedu.address.commons.util.CommandUtil;
import seedu.address.model.TaskBook.TaskType;
import seedu.address.model.task.ReadOnlyTask;
import seedu.address.ui.PersonListPanel;

//@@author A0143884W
/**
 * Selects a task identified using it's last displayed index from the address book.
 */
public class SelectCommand extends Command {

    public final String targetIndex;

    public static final String COMMAND_WORD = "select";
    public static final String COMMAND_ALIAS = "sel";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Selects the task identified by the index number used in the last task listing.\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " A1";

    public static final String MESSAGE_SELECT_TASK_SUCCESS = "Selected Task: %1$s";

    public SelectCommand(String targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute() {

        UnmodifiableObservableList<ReadOnlyTask> lastDatedTaskList = model.getFilteredDatedTaskList();
        UnmodifiableObservableList<ReadOnlyTask> lastUndatedTaskList = model.getFilteredUndatedTaskList();

        if (!CommandUtil.isValidIndex(targetIndex, lastUndatedTaskList.size(), 
                lastDatedTaskList.size())){
            indicateAttemptToExecuteIncorrectCommand();
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        TaskType type = CommandUtil.getTaskType(targetIndex);
        int indexNum = CommandUtil.getIndex(targetIndex);
        
        if (type == TaskType.DATED) {
            EventsCenter.getInstance().post(new JumpToListRequestEvent(indexNum - 1, JumpToListRequestEvent.DATED_LIST));
        }
        else if (type == TaskType.UNDATED){
            EventsCenter.getInstance().post(new JumpToListRequestEvent(indexNum - 1, JumpToListRequestEvent.UNDATED_LIST));
        }
        else {
            assert false : "Task type not found";
        }

        return new CommandResult(String.format(MESSAGE_SELECT_TASK_SUCCESS, targetIndex));
    }

}
