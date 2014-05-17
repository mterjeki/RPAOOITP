
package hu.bme.RPAOOITP.domain.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Access( AccessType.FIELD )
@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractProjectUnit extends AbstractUuidIdentifiable {
	
	private String projectName;
	
	@Temporal( TemporalType.TIMESTAMP )
	private Date end;
	
	@Temporal( TemporalType.TIMESTAMP )
	private Date start;
	
	private int time;
	
	@ManyToOne( fetch = FetchType.EAGER )
	private Company company;
	
	@Transient
	private List<Competency> competencies;
	
	@Override
	public String toString() {
		return projectName;
	}
	
}
