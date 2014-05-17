
package hu.bme.RPAOOITP.page;

import hu.bme.RPAOOITP.domain.model.Competency;
import hu.bme.RPAOOITP.ejb.CompetencyManager;
import hu.bme.RPAOOITP.ejb.UserManager;

import java.util.List;
import java.util.Set;

import javax.ejb.EJB;

import org.vaadin.activelink.ActiveLink;
import org.vaadin.activelink.ActiveLink.LinkActivatedEvent;
import org.vaadin.activelink.ActiveLink.LinkActivatedListener;

import com.vaadin.cdi.CDIView;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.VerticalLayout;

@CDIView( value = CompetencyPage.NAV_PATH )
public class CompetencyPage extends AbstractLoggedInPage {
	
	public static final String NAV_PATH = "competency";
	
	@EJB
	private UserManager userManager;
	
	@EJB
	private CompetencyManager competencyManager;
	
	private BeanItemContainer<Competency> beanItemContainer;
	private VerticalLayout table;
	
	@Override
	protected void initLayout() {
		super.initLayout();
		
		addComponentToContent( new CompetencySelector() );
	}
	
	@Override
	public void enter( final ViewChangeEvent event ) {
		super.enter( event );
		Set<Competency> findAllCompetencyByUser = userManager.findAllCompetencyByUser( authControl.getLoggedInUser() );
		beanItemContainer.removeAllItems();
		beanItemContainer.addAll( findAllCompetencyByUser );
		if (!findAllCompetencyByUser.isEmpty()) {
			table.setVisible( true );
		}
		
	}
	
	private class CompetencySelector extends VerticalLayout {
		
		public CompetencySelector() {
			super();
			List<Competency> findAllCompetencies = competencyManager.findAllCompetencies();
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
						competencyManager.addCompetency( authControl.getLoggedInUser(), value );
						
						if (!table.isVisible()) {
							table.setVisible( true );
						}
					}
					else {
						Notification.show( "One user can have one competency only once", Type.WARNING_MESSAGE );
					}
					
				}
			} );
			
			selector.addComponent( competenciesBox );
			addComponent( selector );
			
			table = new VerticalLayout();
			
			Table ownedCompetenciesTable = new Table( "Owned competencies" );
			beanItemContainer = new BeanItemContainer<Competency>( Competency.class );
			if (findAllCompetencyByUser.isEmpty()) {
				table.setVisible( false );
			}
			else {
				beanItemContainer.addAll( findAllCompetencyByUser );
			}
			ownedCompetenciesTable.setContainerDataSource( beanItemContainer );
			ownedCompetenciesTable.addGeneratedColumn( "remove", new ColumnGenerator() {
				
				@Override
				public Object generateCell( final Table source, final Object itemId, final Object columnId ) {
					ActiveLink link = new ActiveLink();
					link.setCaption( "Remove" );
					final Competency bean = beanItemContainer.getItem( itemId ).getBean();
					link.addListener( new LinkActivatedListener() {
						
						@Override
						public void linkActivated( final LinkActivatedEvent event ) {
							beanItemContainer.removeItem( bean );
							competencyManager.removeCompetency( authControl.getLoggedInUser(), bean );
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
	
}
