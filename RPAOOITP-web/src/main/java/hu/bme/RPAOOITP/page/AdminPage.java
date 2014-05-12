
package hu.bme.RPAOOITP.page;

import com.vaadin.cdi.CDIView;
import com.vaadin.ui.Calendar;

@CDIView( value = AdminPage.NAV_PATH )
public class AdminPage extends AbstractLoggedInPage {
	
	public static final String NAV_PATH = "admin";
	
	private Calendar calendar;
	
	@Override
	protected void initLayout() {
		super.initLayout();
		calendar = new Calendar();
		addComponentToContent( calendar );
	}
	
}
