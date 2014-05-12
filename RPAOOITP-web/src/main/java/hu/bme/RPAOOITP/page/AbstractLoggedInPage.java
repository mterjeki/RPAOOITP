
package hu.bme.RPAOOITP.page;

import hu.bme.RPAOOITP.RPAOOITPAuthControl;

import javax.inject.Inject;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Link;
import com.vaadin.ui.VerticalLayout;

public abstract class AbstractLoggedInPage extends AbstractRPAOOITPPage {
	
	@Inject
	private RPAOOITPAuthControl authControl;
	
	private HeaderPanel header;
	private VerticalLayout content;
	
	@Override
	protected void initLayout() {
		header = new HeaderPanel();
		header.setSizeFull();
		addComponent( header );
		setComponentAlignment( header, Alignment.TOP_LEFT );
		
		content = new VerticalLayout();
		addComponent( content );
	}
	
	protected void addComponentToContent( final Component component ) {
		content.addComponent( component );
	}
	
	private class HeaderPanel extends HorizontalLayout {
		
		private Link admin;
		private Button logout;
		
		public HeaderPanel() {
			super();
			initLayout();
		}
		
		private void initLayout() {
			admin = new Link( authControl.getPrincipalName(), new ExternalResource( "#!" + AdminPage.NAV_PATH ) );
			logout = new Button( "logout" );
			logout.addClickListener( new LogoutClickListener() );
			
			HorizontalLayout basicActions = new HorizontalLayout();
			basicActions.setWidth( "250px" );
			basicActions.addComponent( admin );
			basicActions.addComponent( logout );
			basicActions.setComponentAlignment( admin, Alignment.MIDDLE_LEFT );
			basicActions.setComponentAlignment( logout, Alignment.MIDDLE_RIGHT );
			
			addComponent( basicActions );
			setComponentAlignment( basicActions, Alignment.MIDDLE_RIGHT );
		}
		
		private class LogoutClickListener implements ClickListener {
			
			@Override
			public void buttonClick( final ClickEvent event ) {
				authControl.logout();
				getUI().getSession().close();
				getUI().getNavigator().navigateTo( LoginPage.NAV_PATH );
			}
			
		}
	}
	
}
