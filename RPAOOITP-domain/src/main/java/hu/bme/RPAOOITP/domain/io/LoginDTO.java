
package hu.bme.RPAOOITP.domain.io;

import hu.bme.RPAOOITP.domain.util.SecurityUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
public class LoginDTO extends AbstractRPAOOITPDTO {
	
	@Setter
	private String usernameOrEmail;
	
	private String password;
	
	public LoginDTO() {
		super();
	}
	
	public void setPassword( final String password ) {
		this.password = SecurityUtil.doMD5Hash( password );
	}
	
}
