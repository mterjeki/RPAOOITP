
package hu.bme.RPAOOITP.base.mode.kanban;

import hu.bme.RPAOOITP.base.mode.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum KanbanStatus implements Status {
	
	BACKLOG( "Backlog" ),
	REQUESTED( "Requested" ),
	IN_PROGRESS( "In progress" ),
	DOING( "Doing" ),
	TESTING( "Testing" ),
	REVIEWING( "Reviewing" ),
	WAITING_FOR_INSTALLATION( "Waiting for installation" ),
	DONE( "Done" ),
	ARCHIVE( "Archive" );
	
	private String name;
	
}
