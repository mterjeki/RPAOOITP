
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
	private final TextField firstName;
	private final TextField lastName;
	private final PasswordField password;
	
	public RegistrationForm( final RegistrationDTO dto ) {
		super( dto );
		
		username = FieldUtil.createRequiredTextField( this, "username" );
		email = FieldUtil.createRequiredTextField( this, "email" );
		firstName = FieldUtil.createRequiredTextField( this, "firstName" );
		lastName = FieldUtil.createRequiredTextField( this, "lastName" );
		password = FieldUtil.createRequiredPasswordField( this, "password" );
		
		email.addValidator( new EmailValidator( "Not a valid email address" ) );
		
		addComponent( firstName );
		addComponent( lastName );
		addComponent( username );
		addComponent( email );
		addComponent( password );
	}
	
}
