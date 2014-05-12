
package hu.bme.RPAOOITP.components;

import hu.bme.RPAOOITP.domain.io.AbstractRPAOOITPDTO;

public interface GenericLayout<DTO extends AbstractRPAOOITPDTO> {
	
	DTO getData();
	
}
