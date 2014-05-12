
package hu.bme.RPAOOITP.domain.io;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class AbstractRPAOOITPDTO implements Serializable {
	
	@Getter
	@Setter
	private UUID id;
	
}
