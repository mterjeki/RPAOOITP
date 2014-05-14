
package hu.bme.RPAOOITP.page;

import hu.bme.RPAOOITP.domain.io.RegistrationDTO;
import hu.bme.RPAOOITP.domain.query.exception.RegistrationException;
import hu.bme.RPAOOITP.ejb.UserManager;
import hu.bme.RPAOOITP.form.RegistrationForm;
import hu.bme.RPAOOITP.util.FormUtil;
import hu.bme.RPAOOITP.util.FormUtil.TotallyValidForm;

import java.util.List;

import javax.ejb.EJB;

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

@CDIView( value = RegistrationPage.NAV_PATH )
public class RegistrationPage extends AbstractRPAOOITPPage implements ClickListener {
	
	public static final String NAV_PATH = "registration";
	
	private RegistrationForm registrationForm;
	
	@EJB
	private UserManager userManager;
	
	@Override
	protected void initLayout() {
		setStyleName( "root" );
		VerticalLayout root = new VerticalLayout();
		root.setStyleName( "registration" );
		root.setSizeUndefined();
		root.setHeight( "100%" );
		addComponent( root );
		setComponentAlignment( root, Alignment.MIDDLE_CENTER );
		
		HorizontalLayout hbTitle = new HorizontalLayout();
		hbTitle.setSpacing( true );
		root.addComponent( hbTitle );
		
		Label title = new Label( "Registration" );
		title.setStyleName( Reindeer.LABEL_H1 );
		hbTitle.addComponent( title );
		hbTitle.setComponentAlignment( title, Alignment.MIDDLE_CENTER );
		
		registrationForm = new RegistrationForm( new RegistrationDTO() );
		root.addComponent( registrationForm );
		
		final Button btnRegistration = new Button( "Registration" );
		btnRegistration.setStyleName( "login" );
		btnRegistration.setClickShortcut( KeyCode.ENTER );
		btnRegistration.addClickListener( this );
		
		final Link admin = new Link( "Back", new ExternalResource( "#!" + LoginPage.NAV_PATH ) );
		VerticalLayout hlButtons = new VerticalLayout( btnRegistration, admin );
		hlButtons.setSizeFull();
		hlButtons.setSpacing( true );
		root.addComponent( hlButtons );
		
	}
	
	@Override
	public void buttonClick( final ClickEvent event ) {
		try {
			try {
				List<String> errors = FormUtil.validateForm( registrationForm );
				
				if (!errors.isEmpty()) {
					Notification.show( errors.toString(), Type.WARNING_MESSAGE );
				}
				
			}
			catch (TotallyValidForm e) {
				userManager.registerUser( registrationForm.getData() );
				getUI().getNavigator().navigateTo( LoginPage.NAV_PATH );
			}
		}
		catch (RegistrationException e) {
			Notification.show( e.getMessage(), Type.WARNING_MESSAGE );
		}
	}
	
}
