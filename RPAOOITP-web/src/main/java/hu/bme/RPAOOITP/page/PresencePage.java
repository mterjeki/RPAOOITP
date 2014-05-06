
package hu.bme.RPAOOITP.page;

import hu.bme.RPAOOITP.RPAOOITPApplication;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Calendar;

public class PresencePage extends RPAOOITPApplication {
	
	private Calendar calendar;
	
	@Override
	protected void init( final VaadinRequest request ) {
		super.init( request );
		calendar = new Calendar();
	}
	
}
