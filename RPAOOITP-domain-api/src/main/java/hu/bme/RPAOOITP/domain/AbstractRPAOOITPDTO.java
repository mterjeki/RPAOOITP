
package hu.bme.RPAOOITP.domain;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AbstractRPAOOITPDTO implements RPAOOITPBaseEntity {
	
	private UUID id;
	
}
