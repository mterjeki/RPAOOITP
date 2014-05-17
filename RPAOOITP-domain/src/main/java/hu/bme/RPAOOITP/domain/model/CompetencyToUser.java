
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
@Table( name = "COMPETENCY_TO_USER" )
@Inheritance( strategy = InheritanceType.SINGLE_TABLE )
@Access( AccessType.FIELD )
@Getter
@Setter
@NoArgsConstructor
public class CompetencyToUser extends AbstractUuidIdentifiable {
	
	@OneToOne
	private User user;
	
	@OneToOne
	private Competency competency;
	
	public CompetencyToUser( final User user, final Competency competency ) {
		super();
		this.user = user;
		this.competency = competency;
	}
	
}
