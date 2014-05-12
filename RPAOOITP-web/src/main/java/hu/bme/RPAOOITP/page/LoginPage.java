
package hu.bme.RPAOOITP.page;

import hu.bme.RPAOOITP.RPAOOITPAuthControl;
import hu.bme.RPAOOITP.domain.io.LoginDTO;
import hu.bme.RPAOOITP.domain.query.exception.LoginException;
import hu.bme.RPAOOITP.form.LoginForm;

import javax.inject.Inject;

import com.vaadin.cdi.CDIView;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.VerticalLayout;

@CDIView( value = LoginPage.NAV_PATH )
public class LoginPage extends AbstractRPAOOITPPage implements ClickListener {
	
	public static final String NAV_PATH = "login";
	
	@Inject
	private RPAOOITPAuthControl authControl;
	
	private LoginForm loginForm;
	
	@Override
	protected void initLayout() {
		VerticalLayout root = new VerticalLayout();
		root.setSizeUndefined();
		addComponent( root );
		setComponentAlignment( root, Alignment.MIDDLE_CENTER );
		
		HorizontalLayout hbTitle = new HorizontalLayout();
		hbTitle.setSpacing( true );
		root.addComponent( hbTitle );
		
		Label title = new Label( "Login" );
		hbTitle.addComponent( title );
		hbTitle.setComponentAlignment( title, Alignment.MIDDLE_CENTER );
		
		loginForm = new LoginForm( new LoginDTO() );
		root.addComponent( loginForm );
		
		final Button btnLogin = new Button( "Login" );
		btnLogin.setId( "login" );
		btnLogin.setClickShortcut( KeyCode.ENTER );
		btnLogin.addClickListener( this );
		
		final Button btnRegistration = new Button( "Registration" );
		btnRegistration.setId( "registration" );
		btnRegistration.addClickListener( this );
		
		HorizontalLayout hlButtons = new HorizontalLayout( btnLogin, btnRegistration );
		hlButtons.setSizeFull();
		hlButtons.setComponentAlignment( btnRegistration, Alignment.TOP_LEFT );
		hlButtons.setComponentAlignment( btnLogin, Alignment.TOP_RIGHT );
		root.addComponent( hlButtons );
	}
	
	@Override
	public void buttonClick( final ClickEvent event ) {
		String id = event.getButton().getId();
		
		if (id.equals( "login" )) {
			try {
				authControl.login( loginForm.getData() );
				getUI().getNavigator().navigateTo( AdminPage.NAV_PATH );
			}
			catch (LoginException e) {
				Notification.show( e.getMessage(), Type.ERROR_MESSAGE );
			}
		}
		
		if (id.equals( "registration" )) {
			getUI().getNavigator().navigateTo( RegistrationPage.NAV_PATH );
		}
		
	}
	
}
