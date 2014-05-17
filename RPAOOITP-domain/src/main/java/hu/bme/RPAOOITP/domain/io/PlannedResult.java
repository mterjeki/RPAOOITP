
package hu.bme.RPAOOITP.domain.io;

import hu.bme.RPAOOITP.domain.model.AbstractProjectUnit;
import hu.bme.RPAOOITP.domain.model.User;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PlannedResult extends AbstractRPAOOITPDTO {
	
	private User user;
	private AbstractProjectUnit projectUnit;
	private Date startTime;
	private Date endTime;
	
}
