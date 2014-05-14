
package hu.bme.RPAOOITP.page;

import hu.bme.RPAOOITP.RPAOOITPAuthControl;

import javax.inject.Inject;

import lombok.Getter;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public abstract class AbstractLoggedInPage extends AbstractRPAOOITPPage {
	
	@Inject
	protected RPAOOITPAuthControl authControl;
	
	protected HeaderPanel header;
	protected VerticalLayout content;
	
	@Override
	protected void initLayout() {
		header = new HeaderPanel();
		header.setSizeFull();
		header.setStyleName( "header" );
		header.setHeight( "40px" );
		addComponent( header );
		setComponentAlignment( header, Alignment.TOP_LEFT );
		
		content = new VerticalLayout();
		content.setSizeFull();
		content.setStyleName( "content" );
		addComponent( content );
		setExpandRatio( content, 1 );
		
	}
	
	protected void addComponentToContent( final Component component ) {
		content.addComponent( component );
	}
	
	protected void changeUserName() {
		header.getAdmin().setCaption( " > " + authControl.getPrincipalName() );
	}
	
	@Override
	public void enter( final ViewChangeEvent event ) {
		super.enter( event );
		changeUserName();
	}
	
	private class HeaderPanel extends HorizontalLayout {
		
		@Getter
		private Button admin;
		private Button logout;
		private Button rpaooit;
		private Button presence;
		
		public HeaderPanel() {
			super();
			initLayout();
		}
		
		private void initLayout() {
			rpaooit = new Button( " > RPAOOITP" );
			rpaooit.setId( "rpaooit" );
			admin = new Button( "" );
			admin.setId( "admin" );
			logout = new Button( " > logout" );
			logout.setId( "logout" );
			
			presence = new Button( " > Presence " );
			presence.setId( "presence" );
			
			HeaderClickListener clickListener = new HeaderClickListener();
			presence.addClickListener( clickListener );
			logout.addClickListener( clickListener );
			rpaooit.addClickListener( clickListener );
			admin.addClickListener( clickListener );
			
			HorizontalLayout basicActions = new HorizontalLayout();
			basicActions.setWidth( "400px" );
			basicActions.addComponent( presence );
			basicActions.addComponent( admin );
			basicActions.addComponent( logout );
			
			basicActions.setComponentAlignment( presence, Alignment.MIDDLE_LEFT );
			basicActions.setComponentAlignment( admin, Alignment.MIDDLE_LEFT );
			basicActions.setComponentAlignment( logout, Alignment.MIDDLE_LEFT );
			
			HorizontalLayout rpaooitpContainer = new HorizontalLayout();
			rpaooitpContainer.addComponent( rpaooit );
			rpaooitpContainer.setComponentAlignment( rpaooit, Alignment.MIDDLE_CENTER );
			addComponent( rpaooitpContainer );
			
			addComponent( basicActions );
			setComponentAlignment( basicActions, Alignment.MIDDLE_RIGHT );
			setComponentAlignment( rpaooitpContainer, Alignment.MIDDLE_LEFT );
		}
		
		private class HeaderClickListener implements ClickListener {
			
			@Override
			public void buttonClick( final ClickEvent event ) {
				String id = event.getButton().getId();
				
				if (id.equals( "logout" )) {
					authControl.logout();
					getUI().getNavigator().navigateTo( LoginPage.NAV_PATH );
				}
				
				if (id.equals( "admin" )) {
					getUI().getNavigator().navigateTo( AdminPage.NAV_PATH );
				}
				
				if (id.equals( "rpaooit" )) {
					getUI().getNavigator().navigateTo( FirstLoggedInPage.NAV_PATH );
				}
				
				if (id.equals( "presence" )) {
					getUI().getNavigator().navigateTo( PresencePage.NAV_PATH );
				}
				
			}
			
		}
	}
	
}
