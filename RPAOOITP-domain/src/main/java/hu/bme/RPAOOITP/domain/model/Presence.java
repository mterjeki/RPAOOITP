
package hu.bme.RPAOOITP.domain.model;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table( name = "PRESENCE" )
@Inheritance( strategy = InheritanceType.SINGLE_TABLE )
@Access( AccessType.FIELD )
@Getter
@Setter
@NoArgsConstructor
public class Presence extends AbstractUuidIdentifiable {
	
	@Temporal( TemporalType.TIMESTAMP )
	private Date start;
	
	@Temporal( TemporalType.TIMESTAMP )
	private Date end;
	
}
