
package hu.bme.RPAOOITP.domain.io;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoggedInUserDTO extends AbstractRPAOOITPDTO {
	
	private String lastName;
	private String firstName;
	private String username;
	private String email;
	private String password;
	
	public LoggedInUserDTO( final UUID id, final String lastName, final String firstName, final String username, final String email,
		final String password ) {
		super( id );
		this.lastName = lastName;
		this.firstName = firstName;
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
	public String getFullName() {
		return firstName + " " + lastName;
	}
	
}
