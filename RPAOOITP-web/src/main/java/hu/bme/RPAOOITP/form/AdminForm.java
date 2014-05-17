
package hu.bme.RPAOOITP.form;

import hu.bme.RPAOOITP.RPAOOITPAuthControl;
import hu.bme.RPAOOITP.components.GenericFormLayout;
import hu.bme.RPAOOITP.domain.io.RegistrationDTO;
import hu.bme.RPAOOITP.domain.model.User;
import hu.bme.RPAOOITP.util.FieldUtil;
import hu.bme.RPAOOITP.util.MessageUtil;

import com.vaadin.data.validator.EmailValidator;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

public class AdminForm extends GenericFormLayout<RegistrationDTO> {
	
	private final TextField username;
	private final TextField firstName;
	private final TextField lastName;
	private final TextField email;
	private final PasswordField password;
	
	private final MessageUtil messageUtil = new MessageUtil();
	
	public AdminForm( final RPAOOITPAuthControl authControl ) {
		super( new RegistrationDTO() );
		
		User user = authControl.getLoggedInUser();
		
		username = FieldUtil.createTextField( this, "username" );
		firstName = FieldUtil.createTextField( this, "firstName" );
		lastName = FieldUtil.createTextField( this, "lastName" );
		password = FieldUtil.createPasswordField( this, "password" );
		email = FieldUtil.createTextField( this, "email" );
		
		email.addValidator( new EmailValidator( "Not a valid email address" ) );
		
		Label usernameLabel = new Label( messageUtil.getMessage( "username" ) + ": (current: " + user.getUsername() + ")" );
		Label firstNameLabel = new Label( messageUtil.getMessage( "firstName" ) + ": (current: " + user.getFirstName() + ")" );
		Label lastNameLabel = new Label( messageUtil.getMessage( "lastName" ) + ": (current: " + user.getLastName() + ")" );
		Label eamilLabel = new Label( messageUtil.getMessage( "lastName" ) + ": (current: " + user.getEmail() + ")" );
		
		//		addComponent( password );
		
		addComponent( usernameLabel );
		addComponent( username );
		
		addComponent( eamilLabel );
		addComponent( email );
		
		addComponent( firstNameLabel );
		addComponent( firstName );
		
		addComponent( lastNameLabel );
		addComponent( lastName );
	}
	
}
