
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
@Table( name = "COMPETENCY_TO_PROJECT" )
@Inheritance( strategy = InheritanceType.SINGLE_TABLE )
@Access( AccessType.FIELD )
@Getter
@Setter
@NoArgsConstructor
public class CompetencyToProject extends AbstractUuidIdentifiable {
	
	@OneToOne
	private Project projectUnit;
	
	@OneToOne
	private Competency competency;
	
	public CompetencyToProject( final Project projectUnit, final Competency competency ) {
		super();
		this.projectUnit = projectUnit;
		this.competency = competency;
	}
	
}
