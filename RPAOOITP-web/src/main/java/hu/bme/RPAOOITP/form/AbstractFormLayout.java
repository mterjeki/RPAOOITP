
package hu.bme.RPAOOITP.form;

import hu.bme.RPAOOITP.util.FormUtil;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public abstract class AbstractFormLayout extends VerticalLayout {
	
	protected final FormLayout form = new FormLayout();
	
	private Button cancelButton = new Button( "Cancel" );
	private Button submitButton = new Button( "Submit" );
	
	public AbstractFormLayout() {
		super();
		initLayout();
		initFormLayout( form );
		FormUtil.setFieldsToImmediate( form, true );
	}
	
	protected abstract void initFormLayout( final FormLayout form );
	
	private void initLayout() {
		setSizeFull();
		setSpacing( true );
		
		addComponent( form );
		form.setSizeFull();
		form.setSpacing( true );
		
		HorizontalLayout buttonsBar = new HorizontalLayout();
		
		buttonsBar.addComponent( cancelButton );
		buttonsBar.addComponent( submitButton );
		
		cancelButton.addClickListener( new CancelButtonClickListener() );
		submitButton.addClickListener( new SubmitButtonClickListener() );
		
		addComponent( buttonsBar );
	}
	
	public void setSubmitButtonCaption( final String caption ) {
		submitButton.setCaption( caption );
	}
	
	public void setSubmitButtonVisibility( final boolean visible ) {
		submitButton.setVisible( visible );
	}
	
	public void setCancelButtonVisibility( final boolean visible ) {
		cancelButton.setVisible( visible );
	}
	
	protected void submitCheck() {
		if (!FormUtil.isAllRequiredFieldIsFilled( form )) {
			
		}
		else {
			submit();
		}
	}
	
	protected void submit() {
		
	}
	
	protected void cancel() {
		
	}
	
	private class SubmitButtonClickListener implements ClickListener {
		
		@Override
		public void buttonClick( final ClickEvent event ) {
			submitCheck();
		}
		
	}
	
	private class CancelButtonClickListener implements ClickListener {
		
		@Override
		public void buttonClick( final ClickEvent event ) {
			cancel();
		}
		
	}
	
}
