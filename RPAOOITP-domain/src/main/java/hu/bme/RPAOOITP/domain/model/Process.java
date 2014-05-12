
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
@Table( name = "PROCESS" )
@Inheritance( strategy = InheritanceType.SINGLE_TABLE )
@Access( AccessType.FIELD )
@Getter
@Setter
@NoArgsConstructor
public class Process extends AbstractProjectUnit {
	
	@OneToOne
	private Project project;
	
}
