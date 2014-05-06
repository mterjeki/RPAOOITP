
package hu.bme.RPAOOITP.base.impl;

import hu.bme.RPAOOITP.base.RPAOOITPEntity;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractRPAOOITPEntity implements RPAOOITPEntity {
	
	private UUID id;
	
	public AbstractRPAOOITPEntity() {
		super();
		generateIdIfNotDefined();
	}
	
	private void generateIdIfNotDefined() {
		if (id == null) {
			id = UUID.randomUUID();
		}
	}
	
}
