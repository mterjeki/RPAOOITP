
package hu.bme.RPAOOITP.page;

import hu.bme.RPAOOITP.RPAOOITPAuthControl;
import hu.bme.RPAOOITP.domain.io.LoginDTO;
import hu.bme.RPAOOITP.domain.query.exception.LoginException;
import hu.bme.RPAOOITP.form.LoginForm;

import javax.inject.Inject;

import com.vaadin.cdi.CDIView;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

@CDIView( value = LoginPage.NAV_PATH )
public class LoginPage extends AbstractRPAOOITPPage implements ClickListener {
	
	public static final String NAV_PATH = "login";
	
	@Inject
	private RPAOOITPAuthControl authControl;
	
	private LoginForm loginForm;
	
	@Override
	protected void initLayout() {
		setStyleName( "root" );
		VerticalLayout root = new VerticalLayout();
		root.setStyleName( "login" );
		root.setSizeUndefined();
		addComponent( root );
		setComponentAlignment( root, Alignment.MIDDLE_CENTER );
		
		HorizontalLayout hbTitle = new HorizontalLayout();
		hbTitle.setSpacing( true );
		root.addComponent( hbTitle );
		
		Label title = new Label( "Login" );
		title.setStyleName( Reindeer.LABEL_H1 );
		hbTitle.addComponent( title );
		hbTitle.setComponentAlignment( title, Alignment.MIDDLE_CENTER );
		
		loginForm = new LoginForm( new LoginDTO() );
		root.addComponent( loginForm );
		
		final Button btnLogin = new Button( "Log In" );
		btnLogin.setId( "login" );
		btnLogin.setStyleName( "login" );
		btnLogin.setWidth( "100px" );
		btnLogin.setClickShortcut( KeyCode.ENTER );
		btnLogin.addClickListener( this );
		
		final Link btnRegistration = new Link( "Registration", new ExternalResource( "#!" + RegistrationPage.NAV_PATH ) );
		btnRegistration.setId( "registration" );
		
		VerticalLayout hlButtons = new VerticalLayout( btnLogin, btnRegistration );
		hlButtons.setSizeFull();
		hlButtons.setSpacing( true );
		root.addComponent( hlButtons );
	}
	
	@Override
	public void buttonClick( final ClickEvent event ) {
		String id = event.getButton().getId();
		
		if (id.equals( "login" )) {
			try {
				authControl.login( loginForm.getData() );
				getUI().getNavigator().navigateTo( FirstLoggedInPage.NAV_PATH );
			}
			catch (LoginException e) {
				Notification.show( e.getMessage(), Type.ERROR_MESSAGE );
			}
		}
		
	}
	
}
