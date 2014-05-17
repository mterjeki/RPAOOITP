
package hu.bme.RPAOOITP.page;

import hu.bme.RPAOOITP.domain.io.RegistrationDTO;
import hu.bme.RPAOOITP.domain.model.Company;
import hu.bme.RPAOOITP.domain.model.User;
import hu.bme.RPAOOITP.domain.query.exception.RegistrationException;
import hu.bme.RPAOOITP.ejb.CompanyManager;
import hu.bme.RPAOOITP.ejb.UserManager;
import hu.bme.RPAOOITP.form.AdminForm;
import hu.bme.RPAOOITP.util.FieldUtil;
import hu.bme.RPAOOITP.util.FormUtil;
import hu.bme.RPAOOITP.util.FormUtil.TotallyValidForm;

import java.util.List;

import javax.ejb.EJB;

import com.google.common.base.Function;
import com.google.common.base.Strings;
import com.vaadin.cdi.CDIView;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;

@CDIView( value = AdminPage.NAV_PATH )
public class AdminPage extends AbstractLoggedInPage implements ClickListener {
	
	public static final String NAV_PATH = "admin";
	
	@EJB
	private UserManager userManager;
	
	@EJB
	private CompanyManager companyManager;
	
	private AdminForm adminForm;
	
	private Embedded image;
	
	private Button createCompany;
	
	@Override
	protected void initLayout() {
		super.initLayout();
		
		createCompany = new Button( "Create company" );
		createCompany.setId( "createCompany" );
		createCompany.setStyleName( "login" );
		createCompany.addClickListener( this );
		createCompany.setVisible( !authControl.getLoggedInUser().hasCompany() );
		addComponentToContent( createCompany );
		
		VerticalLayout verticalLayout = new VerticalLayout();
		Label modifyDatas = new Label( "Modify datas" );
		modifyDatas.setStyleName( Reindeer.LABEL_H1 );
		verticalLayout.addComponent( modifyDatas );
		adminForm = new AdminForm( authControl );
		verticalLayout.addComponent( adminForm );
		addComponentToContent( verticalLayout );
		content.setComponentAlignment( verticalLayout, Alignment.MIDDLE_CENTER );
		
		final Button btnModify = new Button( "Modify" );
		btnModify.setStyleName( "login" );
		btnModify.setWidth( "100px" );
		btnModify.setClickShortcut( KeyCode.ENTER );
		btnModify.addClickListener( this );
		
		VerticalLayout hlButtons = new VerticalLayout( btnModify );
		hlButtons.setSizeFull();
		hlButtons.setSpacing( true );
		content.addComponent( hlButtons );
	}
	
	private class CompanyWindow extends Window implements ClickListener {
		
		private final Company company;
		
		public CompanyWindow() {
			super();
			center();
			setModal( true );
			setClosable( true );
			company = new Company();
			company.setOwner( authControl.getLoggedInUser() );
			
			VerticalLayout content = new VerticalLayout();
			FormLayout form = new FormLayout();
			form.addComponent( FieldUtil.createRequiredTextField( company, "companyName" ) );
			content.addComponent( form );
			
			final Button btnSave = new Button( "Save" );
			btnSave.setStyleName( "login" );
			btnSave.setWidth( "100px" );
			btnSave.setClickShortcut( KeyCode.ENTER );
			btnSave.addClickListener( this );
			content.addComponent( btnSave );
			
			setContent( content );
			
		}
		
		@Override
		public void buttonClick( final ClickEvent event ) {
			authControl.getLoggedInUser().setCompany( company );
			companyManager.createCompany( authControl.getLoggedInUser(), company );
			createCompany.setVisible( false );
			header.getCompany().setVisible( true );
			header.getCompany().setCaption( company.getCompanyName() );
			header.getRpaooit().setVisible( false );
			header.getProjects().setVisible( true );
			getUI().removeWindow( this );
		}
		
	}
	
	@Override
	public void buttonClick( final ClickEvent event ) {
		if (event.getButton().getId().equals( "createCompany" )) {
			CompanyWindow eventWindow = new CompanyWindow();
			getUI().addWindow( eventWindow );
		}
		
		else {
			try {
				try {
					List<String> errors = FormUtil.validateForm( adminForm );
					
					if (!errors.isEmpty()) {
						Notification.show( errors.toString(), Type.WARNING_MESSAGE );
					}
					
				}
				catch (TotallyValidForm e) {
					RegistrationDTO data = adminForm.getData();
					RegistrationDTO fromLoggedInUser = new UserToRegister().apply( authControl.getLoggedInUser() );
					
					if (!Strings.isNullOrEmpty( data.getEmail() )) {
						fromLoggedInUser.setEmail( data.getEmail() );
					}
					
					if (!Strings.isNullOrEmpty( data.getFirstName() )) {
						fromLoggedInUser.setFirstName( data.getFirstName() );
					}
					
					if (!Strings.isNullOrEmpty( data.getLastName() )) {
						fromLoggedInUser.setLastName( data.getLastName() );
					}
					
					if (!Strings.isNullOrEmpty( data.getPassword() )) {
						fromLoggedInUser.setPassword( data.getPassword() );
					}
					
					if (!Strings.isNullOrEmpty( data.getUsername() )) {
						fromLoggedInUser.setUsername( data.getUsername() );
					}
					
					userManager.modifyUser( fromLoggedInUser );
					User user = authControl.getLoggedInUser();
					user.setEmail( fromLoggedInUser.getEmail() );
					user.setFirstName( fromLoggedInUser.getFirstName() );
					user.setLastName( fromLoggedInUser.getLastName() );
					user.setPassword( fromLoggedInUser.getPassword() );
					user.setUsername( fromLoggedInUser.getUsername() );
					
					getUI().getNavigator().navigateTo( AdminPage.NAV_PATH );
				}
			}
			catch (RegistrationException e) {
				Notification.show( e.getMessage(), Type.WARNING_MESSAGE );
			}
		}
	}
	
	private static class UserToRegister implements Function<User, RegistrationDTO> {
		
		@Override
		public RegistrationDTO apply( final User user ) {
			RegistrationDTO dto = new RegistrationDTO();
			dto.setId( user.getId() );
			
			dto.setEmail( user.getEmail() );
			dto.setFirstName( user.getFirstName() );
			dto.setLastName( user.getLastName() );
			dto.setPassword( user.getPassword() );
			dto.setUsername( user.getUsername() );
			
			return dto;
		}
		
	}
	
}
