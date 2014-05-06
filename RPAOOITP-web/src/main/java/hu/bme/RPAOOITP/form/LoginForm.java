
package hu.bme.RPAOOITP.form;

import hu.bme.RPAOOITP.base.User;
import hu.bme.RPAOOITP.util.FieldUtil;

import com.vaadin.ui.FormLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

public class LoginForm extends AbstractFormLayout {
	
	private User user;
	
	private TextField username;
	private PasswordField password;
	
	public LoginForm( final User user ) {
		super();
		this.user = user;
	}
	
	@Override
	protected void initFormLayout( final FormLayout form ) {
		username = FieldUtil.createRequiredTextField( user, "username" );
		password = FieldUtil.createRequiredPasswordField( user, "password" );
		form.addComponent( username );
		form.addComponent( password );
	}
	
	@Override
	protected void submit() {
		super.submit();
		
	}
	
}
