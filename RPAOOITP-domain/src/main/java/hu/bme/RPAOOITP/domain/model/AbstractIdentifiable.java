
package hu.bme.RPAOOITP.domain.model;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class AbstractIdentifiable<ID> implements Serializable {
	
	private ID id;
	
}
