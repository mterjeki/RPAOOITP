
package hu.bme.RPAOOITP.page;

import hu.bme.RPAOOITP.domain.io.PlannedResult;
import hu.bme.RPAOOITP.domain.model.AbstractProjectUnit;
import hu.bme.RPAOOITP.domain.model.Competency;
import hu.bme.RPAOOITP.domain.model.Process;
import hu.bme.RPAOOITP.domain.model.Project;
import hu.bme.RPAOOITP.domain.model.Task;
import hu.bme.RPAOOITP.ejb.CompanyManager;
import hu.bme.RPAOOITP.ejb.CompetencyManager;
import hu.bme.RPAOOITP.ejb.PlanningManager;
import hu.bme.RPAOOITP.ejb.ProjectManager;
import hu.bme.RPAOOITP.ejb.UserManager;
import hu.bme.RPAOOITP.util.DataContainer;
import hu.bme.RPAOOITP.util.FieldUtil;
import hu.bme.RPAOOITP.util.FormUtil;
import hu.bme.RPAOOITP.util.FormUtil.TotallyValidForm;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;

import org.vaadin.activelink.ActiveLink;
import org.vaadin.activelink.ActiveLink.LinkActivatedEvent;
import org.vaadin.activelink.ActiveLink.LinkActivatedListener;

import com.google.common.collect.Lists;
import com.vaadin.addon.calendar.event.BasicEvent;
import com.vaadin.addon.calendar.event.BasicEventProvider;
import com.vaadin.addon.calendar.ui.Calendar;
import com.vaadin.cdi.CDIView;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@CDIView( value = ProjectsPage.NAV_PATH )
public class ProjectsPage extends AbstractLoggedInPage implements ClickListener {
	
	public static final String NAV_PATH = "projects";
	
	@Inject
	private ProjectManager projectManager;
	
	@EJB
	private CompanyManager companyManager;
	
	@EJB
	private CompetencyManager competencyManager;
	
	@EJB
	private UserManager userManager;
	@EJB
	private PlanningManager planningManager;
	
	private BeanItemContainer<AbstractProjectUnit> beanItemContainer;
	private BeanItemContainer<Competency> competencyContainer;
	
	private Calendar calendar;
	
	private VerticalLayout normalLayout;
	
	private boolean calendarView;
	
	private Button addButton;
	
	@Override
	protected void initLayout() {
		super.initLayout();
		
		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setWidth( "480px" );
		addButton = new Button( "Add" );
		addButton.setId( "add" );
		addButton.setStyleName( "login" );
		addButton.addClickListener( this );
		buttonLayout.addComponent( addButton );
		addComponentToContent( buttonLayout );
		
		Button changeView = new Button( "Change View" );
		changeView.setId( "change" );
		changeView.setStyleName( "login" );
		changeView.addClickListener( this );
		buttonLayout.addComponent( changeView );
		
		Button generate = new Button( "Generate" );
		generate.setId( "generate" );
		generate.setStyleName( "login" );
		generate.addClickListener( this );
		buttonLayout.addComponent( generate );
		
		addComponentToContent( buttonLayout );
		
		calendar = new Calendar( new PresenceBasicEventProvider() );
		calendar.setWidth( "100%" );
		addComponentToContent( calendar );
		
		normalLayout = new VerticalLayout();
		Table projects = new Table( "Projects" );
		List<AbstractProjectUnit> findAllProjectUnitsByCompany = projectManager.findAllProjectUnitsByCompany( companyManager
			.getCompanyByUser( authControl.getLoggedInUser() ) );
		Collections.sort( findAllProjectUnitsByCompany, new ProjectCompare() );
		
		beanItemContainer = new BeanItemContainer<AbstractProjectUnit>( AbstractProjectUnit.class );
		beanItemContainer.addAll( findAllProjectUnitsByCompany );
		projects.setContainerDataSource( beanItemContainer );
		projects.addGeneratedColumn( "remove", new ColumnGenerator() {
			
			@Override
			public Object generateCell( final Table source, final Object itemId, final Object columnId ) {
				ActiveLink link = new ActiveLink();
				link.setCaption( "Remove" );
				final AbstractProjectUnit bean = beanItemContainer.getItem( itemId ).getBean();
				link.addListener( new LinkActivatedListener() {
					
					@Override
					public void linkActivated( final LinkActivatedEvent event ) {
						
						if (bean instanceof Project) {
							List<AbstractProjectUnit> itemIds = beanItemContainer.getItemIds();
							for (AbstractProjectUnit abstractProjectUnit : itemIds) {
								if (abstractProjectUnit instanceof Process) {
									if (((Process) abstractProjectUnit).getProject().equals( bean )) {
										Notification.show( "Project has process, first delete it", Type.WARNING_MESSAGE );
										return;
									}
								}
								
								if (abstractProjectUnit instanceof Task) {
									if (((Task) abstractProjectUnit).getProject().equals( bean )) {
										Notification.show( "Project has task, first delete it", Type.WARNING_MESSAGE );
										return;
									}
								}
							}
							
							projectManager.removeProject( (Project) bean );
						}
						
						if (bean instanceof Process) {
							List<AbstractProjectUnit> itemIds = beanItemContainer.getItemIds();
							for (AbstractProjectUnit abstractProjectUnit : itemIds) {
								
								if (abstractProjectUnit instanceof Task) {
									if (((Task) abstractProjectUnit).getProcess().equals( bean )) {
										Notification.show( "Project has task, first delete it", Type.WARNING_MESSAGE );
										return;
									}
								}
								
							}
							projectManager.removeProcess( (Process) bean );
						}
						
						if (bean instanceof Task) {
							projectManager.removeTask( (Task) bean );
						}
						
						beanItemContainer.removeItem( bean );
					}
				} );
				
				return link;
			}
		} );
		
		projects.setVisibleColumns( new String[] { "projectName", "start", "end", "time", "remove" } );
		normalLayout.addComponent( projects );
		addComponentToContent( normalLayout );
	}
	
	@Override
	public void enter( final ViewChangeEvent event ) {
		super.enter( event );
		
		List<AbstractProjectUnit> findAllProjectUnitsByCompany = projectManager.findAllProjectUnitsByCompany( companyManager
			.getCompanyByUser( authControl.getLoggedInUser() ) );
		Collections.sort( findAllProjectUnitsByCompany, new ProjectCompare() );
		beanItemContainer.removeAllItems();
		beanItemContainer.addAll( findAllProjectUnitsByCompany );
		
		calendarView = false;
		calendar.setVisible( false );
	}
	
	private static class ProjectCompare implements Comparator<AbstractProjectUnit> {
		
		@Override
		public int compare( final AbstractProjectUnit o1, final AbstractProjectUnit o2 ) {
			return 0;
		}
		
	}
	
	private class PresenceBasicEventProvider extends BasicEventProvider {
		
		public void removeAll() {
			eventList = Lists.newArrayList();
		}
		
	}
	
	@Override
	public void buttonClick( final ClickEvent event ) {
		if (event.getButton().getId().equals( "change" )) {
			if (!calendarView) {
				PresenceBasicEventProvider eventProvider = (PresenceBasicEventProvider) calendar.getEventProvider();
				eventProvider.removeAll();
				
				List<AbstractProjectUnit> findAllProjectUnitsByCompany = projectManager.findAllProjectUnitsByCompany( companyManager
					.getCompanyByUser( authControl.getLoggedInUser() ) );
				
				for (AbstractProjectUnit abstractProjectUnit : findAllProjectUnitsByCompany) {
					BasicEvent basicEvent = new BasicEvent( abstractProjectUnit.getProjectName(), abstractProjectUnit.getProjectName(),
						abstractProjectUnit.getStart(), abstractProjectUnit.getEnd() );
					calendar.addEvent( basicEvent );
				}
				
				calendar.setVisible( true );
				addButton.setVisible( false );
				normalLayout.setVisible( false );
			}
			else {
				calendar.setVisible( false );
				addButton.setVisible( true );
				normalLayout.setVisible( true );
			}
			
			calendarView = !calendarView;
		}
		else if (event.getButton().getId().equals( "generate" )) {
			getUI().addWindow( new GenerateWindow() );
			
		}
		else {
			AddWindow eventWindow = new AddWindow();
			getUI().addWindow( eventWindow );
		}
		
	}
	
	private class GenerateWindow extends Window {
		
		public GenerateWindow() {
			super();
			center();
			setModal( true );
			setClosable( true );
			setWidth( "400px" );
			
			BeanItemContainer<PlannedResult> container = new BeanItemContainer<PlannedResult>( PlannedResult.class );
			List<PlannedResult> doPlanningForCompany = planningManager.doPlanningForCompany( companyManager.getCompanyByUser( authControl
				.getLoggedInUser() ) );
			
			if (doPlanningForCompany != null && !doPlanningForCompany.isEmpty()) {
				container.addAll( doPlanningForCompany );
			}
			
			VerticalLayout layout = new VerticalLayout();
			Table table = new Table( "Plan" );
			table.setContainerDataSource( container );
			table.setVisibleColumns( new String[] { "user", "projectUnit", "startTime", "endTime" } );
			layout.addComponent( table );
			setContent( layout );
			
		}
	}
	
	private class AddWindow extends Window implements ClickListener {
		
		private final DataContainer data;
		private SelectType type;
		private final FormLayout form;
		private AbstractProjectUnit selectedProject;
		private final ComboBox parent;
		
		public AddWindow() {
			super();
			center();
			setModal( true );
			setClosable( true );
			setWidth( "400px" );
			type = SelectType.PROJECT;
			data = new DataContainer();
			
			VerticalLayout content = new VerticalLayout();
			form = new FormLayout();
			content.setStyleName( "padding" );
			
			ComboBox typeBox = new ComboBox( "Type", Arrays.asList( SelectType.values() ) );
			typeBox.setImmediate( true );
			typeBox.setNullSelectionAllowed( false );
			List<AbstractProjectUnit> itemIds = beanItemContainer.getItemIds();
			typeBox.setVisible( !itemIds.isEmpty() );
			typeBox.addValueChangeListener( new Property.ValueChangeListener() {
				
				@Override
				public void valueChange( final ValueChangeEvent event ) {
					type = (SelectType) event.getProperty().getValue();
					
					if (type == SelectType.PROJECT) {
						parent.setVisible( false );
					}
					else {
						parent.setVisible( true );
					}
				}
			} );
			form.addComponent( typeBox );
			
			parent = new ComboBox( "Parent", itemIds );
			parent.setImmediate( true );
			parent.setNullSelectionAllowed( false );
			parent.setVisible( !itemIds.isEmpty() );
			parent.addValueChangeListener( new Property.ValueChangeListener() {
				
				@Override
				public void valueChange( final ValueChangeEvent event ) {
					selectedProject = (AbstractProjectUnit) event.getProperty().getValue();
				}
			} );
			parent.setRequired( true );
			parent.setRequiredError( "Parent project must not be null" );
			parent.setVisible( false );
			
			form.addComponent( parent );
			
			form.addComponent( FieldUtil.createRequiredTextField( data, "name" ) );
			form.addComponent( FieldUtil.createRequiredDateField( data, "startDate" ) );
			form.addComponent( FieldUtil.createRequiredDateField( data, "endDate" ) );
			form.addComponent( FieldUtil.createTextField( data, "time" ) );
			content.addComponent( form );
			
			content.addComponent( new CompetencySelector() );
			final Button btnSave = new Button( "Save" );
			btnSave.setStyleName( "login" );
			btnSave.setWidth( "100px" );
			btnSave.setClickShortcut( KeyCode.ENTER );
			btnSave.addClickListener( this );
			content.addComponent( btnSave );
			
			setContent( content );
		}
		
		private class CompetencySelector extends VerticalLayout {
			
			private VerticalLayout table;
			
			public CompetencySelector() {
				super();
				List<Competency> findAllCompetencies = competencyManager.findAllCompetencies();
				
				FormLayout selector = new FormLayout();
				ComboBox competenciesBox = new ComboBox( "Competencies", findAllCompetencies );
				competenciesBox.setImmediate( true );
				competenciesBox.setNullSelectionAllowed( false );
				competenciesBox.addValueChangeListener( new Property.ValueChangeListener() {
					
					@Override
					public void valueChange( final ValueChangeEvent event ) {
						Competency value = (Competency) event.getProperty().getValue();
						if (!competencyContainer.getItemIds().contains( value )) {
							competencyContainer.addBean( value );
						}
						else {
							Notification.show( "One competency can be added once", Type.WARNING_MESSAGE );
						}
						
					}
				} );
				
				selector.addComponent( competenciesBox );
				addComponent( selector );
				
				table = new VerticalLayout();
				
				Table ownedCompetenciesTable = new Table( "Selected competencies" );
				competencyContainer = new BeanItemContainer<Competency>( Competency.class );
				ownedCompetenciesTable.setContainerDataSource( competencyContainer );
				ownedCompetenciesTable.addGeneratedColumn( "remove", new ColumnGenerator() {
					
					@Override
					public Object generateCell( final Table source, final Object itemId, final Object columnId ) {
						ActiveLink link = new ActiveLink();
						link.setCaption( "Remove" );
						final Competency bean = competencyContainer.getItem( itemId ).getBean();
						link.addListener( new LinkActivatedListener() {
							
							@Override
							public void linkActivated( final LinkActivatedEvent event ) {
								competencyContainer.removeItem( bean );
							}
						} );
						
						return link;
					}
				} );
				
				ownedCompetenciesTable.setVisibleColumns( "competency", "remove" );
				table.addComponent( ownedCompetenciesTable );
				addComponent( table );
				
			}
			
		}
		
		@Override
		public void buttonClick( final ClickEvent event ) {
			try {
				List<String> errors = FormUtil.validateForm( form );
				
				if (!errors.isEmpty()) {
					Notification.show( errors.toString(), Type.WARNING_MESSAGE );
				}
				
			}
			catch (TotallyValidForm e) {
				AbstractProjectUnit projectUnit = null;
				
				switch (type) {
					case PROJECT:
						projectUnit = new Project();
						break;
					case PROCESS:
						projectUnit = new hu.bme.RPAOOITP.domain.model.Process();
						break;
					case TASK:
						projectUnit = new Task();
						break;
				}
				
				projectUnit.setCompany( companyManager.getCompanyByUser( authControl.getLoggedInUser() ) );
				projectUnit.setProjectName( data.getName() );
				projectUnit.setStart( data.getStartDate() );
				projectUnit.setEnd( data.getEndDate() );
				projectUnit.setTime( data.getTime() );
				projectUnit.setCompetencies( competencyContainer.getItemIds() );
				
				switch (type) {
					case PROJECT:
						projectManager.addProjectToCompany( (Project) projectUnit,
							companyManager.getCompanyByUser( authControl.getLoggedInUser() ) );
						break;
					case PROCESS:
						if (!(selectedProject instanceof Project)) {
							Notification.show( "Selected parent should be project", Type.WARNING_MESSAGE );
							return;
						}
						
						projectManager.addProcessToProject( (Project) selectedProject, (Process) projectUnit );
						break;
					case TASK:
						if (selectedProject instanceof Task) {
							Notification.show( "Selected parent should be project or process", Type.WARNING_MESSAGE );
							return;
						}
						
						if (selectedProject instanceof Project) {
							projectManager.addTaskToProject( (Project) selectedProject, (Task) projectUnit );
						}
						else {
							projectManager.addTaskToProcess( (Process) selectedProject, (Task) projectUnit );
						}
						break;
				}
				beanItemContainer.addItem( projectUnit );
				getUI().removeWindow( this );
			}
			
		}
	}
	
	private static enum SelectType {
		
		PROJECT,
		PROCESS,
		TASK;
		
	}
	
}
