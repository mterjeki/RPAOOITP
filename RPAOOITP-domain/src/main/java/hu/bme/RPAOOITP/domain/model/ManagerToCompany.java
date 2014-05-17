
package hu.bme.RPAOOITP.domain.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table( name = "MANAGER_TO_COMPANY" )
@Inheritance( strategy = InheritanceType.SINGLE_TABLE )
@Access( AccessType.FIELD )
@Getter
@Setter
@NoArgsConstructor
public class ManagerToCompany extends AbstractUuidIdentifiable {
	
	@OneToOne
	private User user;
	
	@OneToOne
	private Company company;
	
	public ManagerToCompany( final User user, final Company company ) {
		super();
		this.user = user;
		this.company = company;
	}
	
}
