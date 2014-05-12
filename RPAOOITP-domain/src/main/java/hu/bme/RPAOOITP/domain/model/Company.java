
package hu.bme.RPAOOITP.domain.model;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table( name = "COMPANY" )
@Inheritance( strategy = InheritanceType.SINGLE_TABLE )
@Access( AccessType.FIELD )
@Getter
@Setter
@NoArgsConstructor
public class Company extends AbstractUuidIdentifiable {
	
	private String companyName;
	
	@OneToOne
	private User owner;
	
	@OneToMany
	private List<User> employees;
	
	@OneToMany
	private List<User> managers;
	
	public List<User> getManagers() {
		if (!managers.contains( owner )) {
			managers.add( owner );
		}
		
		return managers;
	}
	
}
