
package hu.bme.RPAOOITP.form;

import hu.bme.RPAOOITP.components.GenericFormLayout;
import hu.bme.RPAOOITP.domain.io.RegistrationDTO;
import hu.bme.RPAOOITP.util.FieldUtil;

import com.vaadin.data.validator.EmailValidator;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

public class RegistrationForm extends GenericFormLayout<RegistrationDTO> {
	
	private final TextField username;
	private final TextField email;
	private final PasswordField password;
	
	public RegistrationForm( final RegistrationDTO dto ) {
		super( dto );
		
		username = FieldUtil.createRequiredTextField( this, "username" );
		email = FieldUtil.createRequiredTextField( this, "email" );
		password = FieldUtil.createRequiredPasswordField( this, "password" );
		
		email.addValidator( new EmailValidator( "Not a valid email address" ) );
		
		addComponent( username );
		addComponent( email );
		addComponent( password );
	}
	
}
