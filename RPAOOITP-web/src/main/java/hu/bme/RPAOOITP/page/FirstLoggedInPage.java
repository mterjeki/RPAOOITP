
package hu.bme.RPAOOITP.page;

import com.vaadin.cdi.CDIView;

@CDIView( value = FirstLoggedInPage.NAV_PATH )
public class FirstLoggedInPage extends AbstractLoggedInPage {
	
	public static final String NAV_PATH = "inside";
	
	@Override
	protected void initLayout() {
		super.initLayout();
	}
	
}
