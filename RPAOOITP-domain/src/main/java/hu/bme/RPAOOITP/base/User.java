
package hu.bme.RPAOOITP.base;

import java.util.List;

public interface User extends RPAOOITPEntity {
	
	List<Competency> getCompetencies();
	
	void setCompetencies( final List<Competency> competencies );
	
}
