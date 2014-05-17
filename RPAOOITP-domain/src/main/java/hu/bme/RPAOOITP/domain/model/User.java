
package hu.bme.RPAOOITP.domain.model;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	private String lastName;
	
	private String firstName;
	
	@OneToMany( fetch = FetchType.EAGER )
	private List<Presence> presences;
	
	@ManyToOne( fetch = FetchType.EAGER )
	private Company company;
	
	public User( final String username, final String email, final String password, final String lastName, final String firstName
		, final Company company ) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.lastName = lastName;
		this.firstName = firstName;
		this.company = company;
	}
	
	public String fullName() {
		return lastName + " " + firstName;
	}
	
	public String getFullName() {
		return fullName();
	}
	
	public boolean hasCompany() {
		return company != null;
	}
	
	public boolean isOwner() {
		return company.getOwner().equals( this );
	}
	
	@Override
	public String toString() {
		return username;
	}
	
}
