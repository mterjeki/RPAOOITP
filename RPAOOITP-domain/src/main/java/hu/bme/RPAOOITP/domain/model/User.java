
package hu.bme.RPAOOITP.domain.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table( name = "USER" )
@Inheritance( strategy = InheritanceType.SINGLE_TABLE )
@Access( AccessType.FIELD )
@Getter
@Setter
@NoArgsConstructor
public class User extends AbstractUuidIdentifiable {
	
	@Column( unique = true )
	private String username;
	
	@Column( unique = true )
	private String email;
	
	private String password;
	
	public User( final String username, final String email, final String password ) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
}
