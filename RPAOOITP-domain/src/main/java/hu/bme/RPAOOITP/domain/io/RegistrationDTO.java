
package hu.bme.RPAOOITP.domain.io;

import hu.bme.RPAOOITP.domain.util.SecurityUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegistrationDTO extends AbstractRPAOOITPDTO {
	
	private String username;
	
	private String email;
	
	private String password;
	
	public void setPassword( final String password ) {
		this.password = SecurityUtil.doMD5Hash( password );
	}
	
}
