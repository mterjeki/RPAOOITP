
package hu.bme.RPAOOITP.page;

import javax.annotation.PostConstruct;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;

public abstract class AbstractRPAOOITPPage extends VerticalLayout implements View {
	
	@PostConstruct
	public void init() {
		setStyleName( "screen" );
		setSizeFull();
		initLayout();
	}
	
	protected abstract void initLayout();
	
	@Override
	public void enter( final ViewChangeEvent event ) {
		
	}
	
}
