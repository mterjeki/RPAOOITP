
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
@Table( name = "COMPETENCY" )
@Inheritance( strategy = InheritanceType.SINGLE_TABLE )
@Access( AccessType.FIELD )
@Getter
@Setter
@NoArgsConstructor
public class Competency extends AbstractUuidIdentifiable {
	
	@Column( unique = true )
	private String competency;
	
	public Competency( final String competency ) {
		super();
		generateIdIfNotSpecified();
		this.competency = competency;
	}
	
	@Override
	public String toString() {
		return competency;
	}
	
}
