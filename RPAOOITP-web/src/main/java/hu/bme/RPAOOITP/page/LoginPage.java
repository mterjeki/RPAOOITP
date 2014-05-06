
package hu.bme.RPAOOITP.page;

import com.vaadin.cdi.CDIView; 

@CDIView( value = LoginPage.NAV_PATH )
public class LoginPage extends AbstractRPAOOITPPage {
	
	public static final String NAV_PATH = "login";
	
	@Override
	protected void initLayout() {
		addComponent( new com.vaadin.ui.Label( "alma" ) );
	}
	
}
