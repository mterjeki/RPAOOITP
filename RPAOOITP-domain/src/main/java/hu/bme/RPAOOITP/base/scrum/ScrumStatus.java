
package hu.bme.RPAOOITP.base.scrum;

import hu.bme.RPAOOITP.base.mode.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ScrumStatus implements Status {
	
	BACKLOG( "Backlog" );
	
	private String name;
	
}
