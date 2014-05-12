
package hu.bme.RPAOOITP.domain.io;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoggedInUserDTO extends AbstractRPAOOITPDTO {
	
	private String username;
	private String email;
	
	public LoggedInUserDTO( final UUID id, final String username, final String email ) {
		super( id );
		this.username = username;
		this.email = email;
	}
	
}
