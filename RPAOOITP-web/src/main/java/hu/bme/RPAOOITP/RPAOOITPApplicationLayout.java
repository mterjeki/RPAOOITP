
package hu.bme.RPAOOITP;

import javax.annotation.PostConstruct;

import com.vaadin.cdi.UIScoped;
import com.vaadin.ui.HorizontalLayout;

@UIScoped
public class RPAOOITPApplicationLayout extends HorizontalLayout {
	
	@PostConstruct
	public void init() {
		setSizeUndefined();
		setWidth( "100%" );
	}
	
}
