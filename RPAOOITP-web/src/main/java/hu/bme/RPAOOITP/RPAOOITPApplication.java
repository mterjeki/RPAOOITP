
package hu.bme.RPAOOITP;

import hu.bme.RPAOOITP.page.LoginPage;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import lombok.Getter;

import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.UIScoped;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;

@CDIUI
public class RPAOOITPApplication extends UI {
	
	private static final String TITLE = "Resource planning and optimization of IT projects";
	
	@Inject
	private RPAOOITPApplicationLayout mainScreen;
	
	@Inject
	private RPAOOITPAuthControl authControl;
	
	@Inject
	private RPAOOITPViewProvider viewProvider;
	
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
		
		setSizeUndefined();
		setWidth( "100%" );
		setContent( mainScreen );
		
		navigator = new Navigator( this, mainScreen );
		navigator.addViewChangeListener( new RPAOOITPViewChangeListener( request ) );
		navigator.addProvider( viewProvider );
	}
	
	public class RPAOOITPViewChangeListener implements ViewChangeListener {
		
		private final VaadinRequest request;
		
		public RPAOOITPViewChangeListener( final VaadinRequest request ) {
			super();
			this.request = request;
		}
		
		@Override
		public boolean beforeViewChange( final ViewChangeEvent event ) {
			View oldView = event.getOldView();
			View newView = event.getNewView();
			
			if (newView instanceof LoginPage) {
				return true;
			}
			
			if (!authControl.isUserSignedIn()) {
				String fragment = Page.getCurrent().getUriFragment();
				if (fragment.startsWith( "!" )) {
					fragment = fragment.substring( 1 );
					request.getWrappedSession().setAttribute( "viewId", fragment );
				}
				
				navigator.navigateTo( LoginPage.NAV_PATH );
				return false;
			}
			
			if (oldView == newView) {
				return true;
			}
			
			return true;
		}
		
		@Override
		public void afterViewChange( final ViewChangeEvent event ) {
			//
		}
		
	}
	
	@UIScoped
	public class RPAOOITPApplicationLayout extends HorizontalLayout {
		
		@PostConstruct
		public void init() {
			setSizeUndefined();
			setWidth( "100%" );
		}
		
	}
	
}
