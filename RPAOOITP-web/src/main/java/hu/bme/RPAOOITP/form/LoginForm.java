
package hu.bme.RPAOOITP.form;

import hu.bme.RPAOOITP.components.GenericFormLayout;
import hu.bme.RPAOOITP.domain.io.LoginDTO;
import hu.bme.RPAOOITP.util.FieldUtil;

import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

public class LoginForm extends GenericFormLayout<LoginDTO> {
	
	private final TextField username;
	private final PasswordField password;
	
	public LoginForm( final LoginDTO loginDTO ) {
		super( loginDTO );
		username = FieldUtil.createRequiredTextField( this, "usernameOrEmail" );
		password = FieldUtil.createRequiredPasswordField( this, "password" );
		
		addComponent( username );
		addComponent( password );
		
		setSpacing( true );
	}
	
}
