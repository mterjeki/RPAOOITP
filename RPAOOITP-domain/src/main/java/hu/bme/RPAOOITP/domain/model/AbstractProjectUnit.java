
package hu.bme.RPAOOITP.domain.model;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Access( AccessType.FIELD )
@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractProjectUnit extends AbstractUuidIdentifiable {
	
	private Date deadLine;
	
	@OneToOne
	private Company owner;
	
}
