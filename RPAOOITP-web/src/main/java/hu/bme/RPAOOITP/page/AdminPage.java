
package hu.bme.RPAOOITP.page;

import hu.bme.RPAOOITP.domain.io.LoggedInUserDTO;
import hu.bme.RPAOOITP.domain.io.RegistrationDTO;
import hu.bme.RPAOOITP.domain.model.Competency;
import hu.bme.RPAOOITP.domain.query.exception.RegistrationException;
import hu.bme.RPAOOITP.ejb.CompetencyManager;
import hu.bme.RPAOOITP.ejb.UserManager;
import hu.bme.RPAOOITP.form.AdminForm;
import hu.bme.RPAOOITP.util.FormUtil;
import hu.bme.RPAOOITP.util.FormUtil.TotallyValidForm;

import java.util.List;
import java.util.Set;

import javax.ejb.EJB;

import com.google.common.base.Function;
import com.google.common.base.Strings;
import com.vaadin.cdi.CDIView;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

@CDIView( value = AdminPage.NAV_PATH )
public class AdminPage extends AbstractLoggedInPage implements ClickListener {
	
	public static final String NAV_PATH = "admin";
	
	@EJB
	private UserManager userManager;
	
	@EJB
	private CompetencyManager competencyManager;
	
	private AdminForm adminForm;
	
	private Embedded image;
	
	@Override
	protected void initLayout() {
		super.initLayout();
		VerticalLayout verticalLayout = new VerticalLayout();
		Label modifyDatas = new Label( "Modify datas" );
		modifyDatas.setStyleName( Reindeer.LABEL_H1 );
		verticalLayout.addComponent( modifyDatas );
		adminForm = new AdminForm( authControl );
		verticalLayout.addComponent( adminForm );
		addComponentToContent( verticalLayout );
		content.setComponentAlignment( verticalLayout, Alignment.MIDDLE_CENTER );
		
		addComponentToContent( new CompetencySelector() );
		
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
	
	private class CompetencySelector extends VerticalLayout {
		
		private final BeanItemContainer<Competency> beanItemContainer;
		
		public CompetencySelector() {
			super();
			Set<Competency> findAllCompetencies = competencyManager.findAllCompetencies();
			System.out.println( findAllCompetencies.size() );
			Set<Competency> findAllCompetencyByUser = userManager.findAllCompetencyByUser( authControl.getLoggedInUser() );
			
			FormLayout selector = new FormLayout();
			ComboBox competenciesBox = new ComboBox( "Competencies", findAllCompetencies );
			competenciesBox.setImmediate( true );
			competenciesBox.setNullSelectionAllowed( false );
			competenciesBox.addValueChangeListener( new Property.ValueChangeListener() {
				
				@Override
				public void valueChange( final ValueChangeEvent event ) {
					Competency value = (Competency) event.getProperty().getValue();
					if (!beanItemContainer.getItemIds().contains( value )) {
						beanItemContainer.addBean( value );
						userManager.addCompetency( authControl.getLoggedInUser(), value );
					}
					else {
						Notification.show( "One user can have one competency only once", Type.WARNING_MESSAGE );
					}
					
				}
			} );
			
			selector.addComponent( competenciesBox );
			addComponent( selector );
			
			VerticalLayout table = new VerticalLayout();
			
			Table ownedCompetenciesTable = new Table( "Owned competencies" );
			beanItemContainer = new BeanItemContainer<Competency>( Competency.class );
			beanItemContainer.addAll( findAllCompetencyByUser );
			ownedCompetenciesTable.setContainerDataSource( beanItemContainer );
			ownedCompetenciesTable.setVisibleColumns( "competency" );
			table.addComponent( ownedCompetenciesTable );
			addComponent( table );
		}
	}
	
	@Override
	public void buttonClick( final ClickEvent event ) {
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
				LoggedInUserDTO user = authControl.getLoggedInUser();
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
	
	private static class UserToRegister implements Function<LoggedInUserDTO, RegistrationDTO> {
		
		@Override
		public RegistrationDTO apply( final LoggedInUserDTO user ) {
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
