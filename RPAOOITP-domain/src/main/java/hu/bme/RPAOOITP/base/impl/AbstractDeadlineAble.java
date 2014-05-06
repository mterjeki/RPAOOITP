
package hu.bme.RPAOOITP.base.impl;

import hu.bme.RPAOOITP.base.DeadlineAble;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractDeadlineAble extends AbstractRPAOOITPEntity implements DeadlineAble {
	
	private Date deadline;
	
}
