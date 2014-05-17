
package hu.bme.RPAOOITP.page;

import hu.bme.RPAOOITP.domain.model.Company;
import hu.bme.RPAOOITP.domain.model.User;
import hu.bme.RPAOOITP.ejb.CompanyManager;
import hu.bme.RPAOOITP.ejb.UserManager;
import hu.bme.RPAOOITP.util.FieldUtil;

import java.util.List;

import javax.ejb.EJB;

import lombok.Getter;

import org.vaadin.activelink.ActiveLink;
import org.vaadin.activelink.ActiveLink.LinkActivatedEvent;
import org.vaadin.activelink.ActiveLink.LinkActivatedListener;

import com.google.common.collect.Lists;
import com.vaadin.cdi.CDIView;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@CDIView( value = CompanyPage.NAV_PATH )
public class CompanyPage extends AbstractLoggedInPage {
	
	public static final String NAV_PATH = "company";
	
	@EJB
	private CompanyManager companyManager;
	@EJB
	private UserManager userManager;
	
	private TextField companyName;
	private Label owner;
	
	private Company company;
	
	private BeanItemContainer<User> userContainer;
	private BeanItemContainer<User> managerContainer;
	
	private EmployeeSelector managerSelector;
	
	private boolean hasRights;
	
	private EmployeeSelector employeeSelector;
	
	private UserList managersTable;
	
	private UserList usersTable;
	
	@Override
	public void enter( final ViewChangeEvent event ) {
		super.enter( event );
		hasRights = false;
		
		if (company.getOwner().getId().equals( authControl.getLoggedInUser().getId() )) {
			hasRights = true;
		}
		
		if (!hasRights) {
			List<User> managersOfCompany = companyManager.getManagersOfCompany( company );
			for (User user : managersOfCompany) {
				if (user.getId().equals( authControl.getLoggedInUser().getId() )) {
					hasRights = true;
					break;
				}
			}
		}
		
		employeeSelector.setVisible( hasRights );
		managerSelector.setVisible( hasRights );
		usersTable.markAsDirtyRecursive();
		managersTable.markAsDirtyRecursive();
		companyName.setReadOnly( !authControl.getLoggedInUser().isOwner() );
	}
	
	@Override
	protected void initLayout() {
		super.initLayout();
		company = companyManager.getCompanyByUser( authControl.getLoggedInUser() );
		
		FormLayout form = new FormLayout();
		companyName = FieldUtil.createTextField( company, "companyName" );
		
		companyName.addValueChangeListener( new Property.ValueChangeListener() {
			
			@Override
			public void valueChange( final ValueChangeEvent event ) {
				companyManager.modifyCompanyName( company );
				header.getCompany().setCaption( company.getCompanyName() );
			}
			
		} );
		
		companyName.setImmediate( true );
		companyName.setReadOnly( !authControl.getLoggedInUser().isOwner() );
		form.addComponent( companyName );
		addComponentToContent( form );
		
		owner = new Label( "Owner: " + company.getOwner().fullName() );
		addComponentToContent( owner );
		
		List<User> employees = companyManager.getEmployeesOfCompany( company );
		List<UserContainer> availableSelectableUsersContainer = createUserContainers( userManager.findAllUser() );
		
		VerticalLayout employeesLayout = new VerticalLayout();
		
		userContainer = new BeanItemContainer<User>( User.class );
		if (employees != null) {
			userContainer.addAll( employees );
		}
		
		employeeSelector = new EmployeeSelector( userContainer, availableSelectableUsersContainer ) {
			
			@Override
			protected void callAdd( final User user ) {
				companyManager.addUserToCompany( user, company );
				List<UserContainer> createUserContainers = createUserContainers( Lists.newArrayList( user ) );
				
				for (UserContainer userContainer : createUserContainers) {
					managerSelector.addItem( userContainer );
				}
			}
			
		};
		
		usersTable = new UserList() {
			
			@Override
			protected void removeUserManager( final User user ) {
				List<User> itemIds = managerContainer.getItemIds();
				if (itemIds.contains( user )) {
					Notification.show( "User is a manager, first delete it from the manager list", Type.WARNING_MESSAGE );
				}
				else {
					userContainer.removeItem( user );
					companyManager.removeUserFromCompany( user, company );
				}
				
			}
		};
		usersTable.setCaption( "Employees" );
		usersTable.setContainerDataSource( userContainer );
		usersTable.setVisibleColumns( new String[] { "username", "email", "remove" } );
		
		employeesLayout.addComponent( employeeSelector );
		employeesLayout.addComponent( usersTable );
		
		VerticalLayout managersLayout = new VerticalLayout();
		
		managerContainer = new BeanItemContainer<User>( User.class );
		managerContainer.addItem( company.getOwner() );
		managerContainer.addAll( companyManager.getManagersOfCompany( company ) );
		managerSelector = new EmployeeSelector( managerContainer, createUserContainers( employees ) ) {
			
			@Override
			protected void callAdd( final User user ) {
				companyManager.addManagerToCompany( user, company );
			}
			
		};
		
		managersTable = new UserList() {
			
			@Override
			protected void removeUserManager( final User user ) {
				User owner = company.getOwner();
				if (user.equals( owner )) {
					Notification.show( "You can not delete the owner from manager list", Type.WARNING_MESSAGE );
				}
				else {
					companyManager.removeManagerFromCompany( user, company );
					managerContainer.removeItem( user );
				}
			}
		};
		managersTable.setCaption( "Managers" );
		managersTable.setContainerDataSource( managerContainer );
		managersTable.setVisibleColumns( new String[] { "username", "email", "remove" } );
		
		managersLayout.addComponent( managerSelector );
		managersLayout.addComponent( managersTable );
		
		HorizontalLayout tables = new HorizontalLayout();
		tables.addComponent( employeesLayout );
		tables.addComponent( managersLayout );
		tables.setComponentAlignment( employeesLayout, Alignment.MIDDLE_LEFT );
		tables.setComponentAlignment( managersLayout, Alignment.MIDDLE_RIGHT );
		tables.setWidth( "600px" );
		addComponentToContent( tables );
	}
	
	private List<UserContainer> createUserContainers( final List<User> availableSelectableUsers ) {
		List<UserContainer> userList = Lists.newArrayList();
		
		for (User user : availableSelectableUsers) {
			userList.add( new UserContainer( user.getEmail(), user ) );
			userList.add( new UserContainer( user.getFullName(), user ) );
			userList.add( new UserContainer( user.getUsername(), user ) );
		}
		
		return userList;
	}
	
	private abstract class EmployeeSelector extends ComboBox {
		
		private BeanItemContainer<User> container;
		
		public EmployeeSelector( final BeanItemContainer<User> container, final List<UserContainer> users ) {
			super( "List of users", users );
			setImmediate( true );
			setNullSelectionAllowed( false );
			addValueChangeListener( new Property.ValueChangeListener() {
				
				@Override
				public void valueChange( final com.vaadin.data.Property.ValueChangeEvent event ) {
					UserContainer userContaner = (UserContainer) event.getProperty().getValue();
					User user = userContaner.getUser();
					
					if (!container.getItemIds().contains( user )) {
						container.addBean( user );
						callAdd( user );
					}
					else {
						Notification.show( "One user can be added only once to table", Type.WARNING_MESSAGE );
					}
				}
			} );
			
		}
		
		protected abstract void callAdd( final User user );
	}
	
	private abstract class UserList extends Table {
		
		private List<User> managersOfCompany;
		
		public UserList() {
			super();
			managersOfCompany = companyManager.getManagersOfCompany( company );
			addGeneratedColumn( "remove", new ColumnGenerator() {
				
				@Override
				public Object generateCell( final Table source, final Object itemId, final Object columnId ) {
					ActiveLink removeLink = new ActiveLink();
					removeLink.setCaption( "Remove" );
					final BeanItemContainer<User> containerDataSource =
						(BeanItemContainer<User>) UserList.this.getContainerDataSource();
					final User user = containerDataSource.getItem( itemId ).getBean();
					removeLink.addListener( new LinkActivatedListener() {
						
						@Override
						public void linkActivated( final LinkActivatedEvent event ) {
							removeUserManager( user );
						}
					} );
					
					removeLink.setEnabled( hasRights );
					return removeLink;
				}
			} );
		}
		
		protected abstract void removeUserManager( final User user );
		
	}
	
	@Getter
	private class UserContainer {
		
		private final String searchedValue;
		private final User user;
		
		public UserContainer( final String searchedValue, final User user ) {
			super();
			this.searchedValue = searchedValue;
			this.user = user;
		}
		
		@Override
		public String toString() {
			return searchedValue;
		}
		
	}
	
}
