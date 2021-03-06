
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
@Table( name = "COMPETENCY_TO_PROCESS" )
@Inheritance( strategy = InheritanceType.SINGLE_TABLE )
@Access( AccessType.FIELD )
@Getter
@Setter
@NoArgsConstructor
public class CompetencyToProcess extends AbstractUuidIdentifiable {
	
	@OneToOne
	private Process projectUnit;
	
	@OneToOne
	private Competency competency;
	
	public CompetencyToProcess( final Process projectUnit, final Competency competency ) {
		super();
		this.projectUnit = projectUnit;
		this.competency = competency;
	}
	
}
