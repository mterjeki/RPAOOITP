
package hu.bme.RPAOOITP.page;

import hu.bme.RPAOOITP.components.GenericFormLayout;
import hu.bme.RPAOOITP.domain.io.LoggedInUserDTO;
import hu.bme.RPAOOITP.domain.model.Presence;
import hu.bme.RPAOOITP.ejb.PresenceManager;
import hu.bme.RPAOOITP.util.FieldUtil;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.EJB;

import com.google.common.collect.Lists;
import com.vaadin.addon.calendar.event.BasicEvent;
import com.vaadin.addon.calendar.event.BasicEventProvider;
import com.vaadin.addon.calendar.event.CalendarEvent;
import com.vaadin.addon.calendar.ui.Calendar;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.EventClick;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.EventClickHandler;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.RangeSelectEvent;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.RangeSelectHandler;
import com.vaadin.cdi.CDIView;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;

@CDIView( value = PresencePage.NAV_PATH )
public class PresencePage extends AbstractLoggedInPage {
	
	public static final String NAV_PATH = "presence";
	
	@EJB
	private PresenceManager presenceManager;
	
	private Calendar calendar;
	
	private List<Presence> presencesOfUser = Lists.newArrayList();
	
	@Override
	protected void initLayout() {
		super.initLayout();
		calendar = new Calendar( new PresenceBasicEventProvider() );
		calendar.setWidth( "100%" );
		calendar.setHandler( new CalendarEventClickHandler() );
		calendar.setHandler( new RangeEventClickHandler() );
		//		calendar.setFirstVisibleHourOfDay( 8 );
		//		calendar.setLastVisibleHourOfDay( 17 );
		addComponentToContent( calendar );
		
		Button week = new Button( "Week" );
		week.setId( "week" );
		week.setStyleName( "calendar" );
		Button month = new Button( "Month" );
		month.setId( "month" );
		month.setStyleName( "calendar" );
		
		CalenderModifyListener calenderModifyListener = new CalenderModifyListener();
		week.addClickListener( calenderModifyListener );
		month.addClickListener( calenderModifyListener );
		
		HorizontalLayout buttons = new HorizontalLayout( week, month );
		buttons.setStyleName( "calendarButtons" );
		addComponentToContent( buttons );
		
	}
	
	private class CalendarEventClickHandler implements EventClickHandler {
		
		@Override
		public void eventClick( final EventClick event ) {
			CalendarEvent calendarEvent = event.getCalendarEvent();
			Date start = calendarEvent.getStart();
			Date end = calendarEvent.getEnd();
			
			System.out.println( start );
			System.out.println( end );
		}
		
	}
	
	private class RangeEventClickHandler implements RangeSelectHandler {
		
		@Override
		public void rangeSelect( final RangeSelectEvent event ) {
			Date start = event.getStart();
			Date end = event.getEnd();
			
			Presence presence = new Presence();
			presence.setStart( start );
			presence.setEnd( end );
			
			EventWindow eventWindow = new EventWindow( presence );
			getUI().addWindow( eventWindow );
		}
		
	}
	
	private class CalenderModifyListener implements ClickListener {
		
		@Override
		public void buttonClick( final ClickEvent event ) {
			String id = event.getButton().getId();
			
			if (id.equals( "week" )) {
				GregorianCalendar startDate = new GregorianCalendar();
				startDate.set( java.util.Calendar.DAY_OF_WEEK, java.util.Calendar.SUNDAY );
				calendar.setStartDate( startDate.getTime() );
				
				GregorianCalendar endDate = new GregorianCalendar();
				endDate.set( java.util.Calendar.DAY_OF_WEEK, java.util.Calendar.SATURDAY );
				calendar.setEndDate( endDate.getTime() );
			}
			
			if (id.equals( "month" )) {
				
				GregorianCalendar startDate = new GregorianCalendar();
				startDate.set( java.util.Calendar.WEEK_OF_MONTH, 1 );
				calendar.setStartDate( startDate.getTime() );
				
				GregorianCalendar endDate = new GregorianCalendar();
				endDate.set( java.util.Calendar.WEEK_OF_MONTH, 5 );
				calendar.setEndDate( endDate.getTime() );
				
			}
			
		}
	}
	
	@Override
	public void enter( final ViewChangeEvent event ) {
		super.enter( event );
		LoggedInUserDTO loggedInUser = authControl.getLoggedInUser();
		presencesOfUser = presenceManager.findAllPresenceToUser( loggedInUser );
		
		PresenceBasicEventProvider eventProvider = (PresenceBasicEventProvider) calendar.getEventProvider();
		eventProvider.removeAll();
		
		for (Presence presence : presencesOfUser) {
			BasicEvent basicEvent = new BasicEvent( authControl.getPrincipalName() + " - Presence", "I will work on this day",
				presence.getStart(), presence.getEnd() );
			calendar.addEvent( basicEvent );
		}
		
	}
	
	private class PresenceBasicEventProvider extends BasicEventProvider {
		
		public void removeAll() {
			eventList = Lists.newArrayList();
		}
		
	}
	
	private class EventWindow extends Window implements ClickListener {
		
		private final Label presenceLabel;
		private final EventForm eventForm;
		
		public EventWindow( final Presence presence ) {
			super();
			center();
			setModal( true );
			setClosable( true );
			VerticalLayout content = new VerticalLayout();
			presenceLabel = new Label( "Presence" );
			presenceLabel.setStyleName( Reindeer.LABEL_H2 );
			content.addComponent( presenceLabel );
			eventForm = new EventForm( presence );
			content.addComponent( eventForm );
			
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
			calendar.addEvent( new BasicEvent( authControl.getPrincipalName() + " - Presence", "I will work on this day", eventForm
				.getData().getStart(),
				eventForm.getData().getEnd() ) );
			presenceManager.addNewPresenceToUser( authControl.getLoggedInUser(), eventForm.getData() );
			getUI().removeWindow( this );
		}
		
		private class EventForm extends GenericFormLayout<Presence> {
			
			private final DateField start;
			private final DateField end;
			
			public EventForm( final Presence presence ) {
				super( presence );
				
				start = FieldUtil.createRequiredDateField( this, "start" );
				end = FieldUtil.createRequiredDateField( this, "end" );
				
				addComponent( start );
				addComponent( end );
			}
		}
		
	}
	
}
