
package hu.bme.RPAOOITP.base.impl;

import hu.bme.RPAOOITP.base.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleImpl extends AbstractRPAOOITPEntity implements Role {
	
	private String name;
	
}
