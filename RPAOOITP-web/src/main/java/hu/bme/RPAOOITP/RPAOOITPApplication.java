
package hu.bme.RPAOOITP;

import hu.bme.RPAOOITP.page.DefaultPage;
import hu.bme.RPAOOITP.page.LoginPage;
import hu.bme.RPAOOITP.page.RegistrationPage;

import java.util.Map;

import javax.inject.Inject;

import lombok.Getter;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

@CDIUI
@Theme( "rpaooitp" )
@Widgetset( value = "hu.bme.RPAOOITP.widgetset" )
public class RPAOOITPApplication extends UI {
	
	private static final String TITLE = "Resource planning and optimization of IT projects";
	
	@Inject
	private RPAOOITPApplicationLayout mainScreen;
	
	@Inject
	private RPAOOITPAuthControl authControl;
	
	@Inject
	private CDIViewProvider viewProvider;
	
	@Getter
	private Navigator navigator;
	
	@Override
	protected void init( final VaadinRequest request ) {
		getPage().setTitle( TITLE );
		
		String viewId = null;
		Map<String, String[]> parameterMap = request.getParameterMap();
		if (parameterMap != null) {
			
			if (parameterMap.containsKey( "viewId" )) {
				viewId = request.getParameter( "viewId" );
			}
			
		}
		
		setSizeFull();
		setContent( mainScreen );
		
		setStyleName( "application" );
		
		navigator = new Navigator( this, mainScreen );
		navigator.addViewChangeListener( new RPAOOITPViewChangeListener( request, this ) );
		navigator.addProvider( viewProvider );
		final String currentPage = handleCurrentNavigation( viewId );
		getPage().setUriFragment( "!" + currentPage );
	}
	
	private String handleCurrentNavigation( final String viewId ) {
		String f = Page.getCurrent().getUriFragment();
		
		if (viewId != null) {
			return viewId;
		}
		
		if (f != null && f.startsWith( "!" )) {
			f = f.substring( 1 );
		}
		
		if (f == null || f.equals( "" ) || f.equals( "/" )) {
			return DefaultPage.NAV_PATH;
		}
		
		return f;
	}
	
	public class RPAOOITPViewChangeListener implements ViewChangeListener {
		
		private final UI ui;
		private final VaadinRequest request;
		
		public RPAOOITPViewChangeListener( final VaadinRequest request, final UI ui ) {
			super();
			this.request = request;
			this.ui = ui;
		}
		
		@Override
		public boolean beforeViewChange( final ViewChangeEvent event ) {
			View oldView = event.getOldView();
			View newView = event.getNewView();
			
			if (newView instanceof LoginPage || newView instanceof RegistrationPage) {
				ui.setStyleName( "application-blue" );
				return true;
			}
			else {
				ui.setStyleName( "application" );
			}
			
			if (!authControl.isUserSignedIn()) {
				String fragment = Page.getCurrent().getUriFragment();
				
				if (fragment == null) {
					return false;
				}
				
				if (fragment.startsWith( "!" )) {
					fragment = fragment.substring( 1 );
					request.getWrappedSession().setAttribute( "viewId", fragment );
				}
				
				navigator.navigateTo( LoginPage.NAV_PATH );
				return false;
			}
			
			return true;
		}
		
		@Override
		public void afterViewChange( final ViewChangeEvent event ) {
			//
		}
		
	}
	
}
