
package hu.bme.RPAOOITP.dto;

import java.io.Serializable;
import java.util.UUID;

public interface RPAOOITPDTO extends Serializable {
	
	UUID getId();
	
	void setId( final UUID id );
	
}
