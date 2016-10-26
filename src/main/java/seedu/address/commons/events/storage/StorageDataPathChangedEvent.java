package seedu.address.commons.events.storage;

import seedu.address.commons.events.BaseEvent;

/**
 * @author A0139528W
 * 
 * Indicates the file path of the task.xml file has been changed.
 */

public class StorageDataPathChangedEvent extends BaseEvent {
	
	public final String oldDataPath;
	public final String newDataPath;

	public StorageDataPathChangedEvent(String oldDataPath, String newDataPath) {
		this.oldDataPath = oldDataPath;
		this.newDataPath = newDataPath;
	}

	@Override
	public String toString() {
		return newDataPath;
	}

}