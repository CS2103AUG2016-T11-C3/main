package seedu.address.logic.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.commons.core.UnmodifiableObservableList;
import seedu.address.model.task.ReadOnlyTask;
import seedu.address.model.task.UniqueTaskList.TaskNotFoundException;

public class RefreshCommand extends Command {

    public static final String COMMAND_WORD = "refresh";
    public static final String MESSAGE_SUCCESS = "Task Manager has been refreshed with local time!";

    
    public RefreshCommand (){}

	@Override
	public CommandResult execute() {
		
        UnmodifiableObservableList<ReadOnlyTask> lastShownList = model.getFilteredDatedTaskList();
        LocalDateTime currentTime = LocalDateTime.now();
        
        for (ReadOnlyTask target : lastShownList) {
        	if(target.getDatetime().getEnd() == null){
        		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
        		LocalDateTime dateTime = LocalDateTime.parse(target.getDatetime().toString(), formatter);
        		if(dateTime.isAfter(currentTime)){
        			try {
        				model.overdueTask(target);
        			} catch (TaskNotFoundException e) {
        				System.out.println("hello world!");
        			}				
				
        		}
        	}
		}		
        return new CommandResult(String.format(MESSAGE_SUCCESS));
	}

}