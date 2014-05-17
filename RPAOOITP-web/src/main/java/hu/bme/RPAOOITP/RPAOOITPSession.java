
package hu.bme.RPAOOITP;

import hu.bme.RPAOOITP.domain.model.User;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

import lombok.Getter;
import lombok.Setter;

@SessionScoped
@Getter
@Setter
public class RPAOOITPSession implements Serializable {
	
	private User userDTO;
	
}
