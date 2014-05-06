
package hu.bme.RPAOOITP.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class AbstractRPAOOITPDTO implements RPAOOITPDTO {
	
	@Getter
	@Setter
	private UUID id;
	
}
