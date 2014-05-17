
package hu.bme.RPAOOITP.util;

import hu.bme.RPAOOITP.domain.model.Competency;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataContainer {
	
	private String name;
	
	private Date startDate;
	
	private Date endDate;
	
	private int time;
	
	private List<Competency> competencies;
	
}
