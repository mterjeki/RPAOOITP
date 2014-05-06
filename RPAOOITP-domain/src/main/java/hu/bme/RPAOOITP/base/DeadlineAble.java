
package hu.bme.RPAOOITP.base;

import java.util.Date;

public interface DeadlineAble extends RPAOOITPEntity {
	
	void setDeadline( final Date date );
	
	Date getDeadline();
	
}
