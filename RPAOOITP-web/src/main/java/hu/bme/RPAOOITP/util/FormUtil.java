
package hu.bme.RPAOOITP.util;

import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Lists;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;

public class FormUtil {
	
	@SuppressWarnings( "rawtypes" )
	private static boolean isAllFieldValidated( final FormLayout formLayout ) {
		Iterator<Component> iterator = formLayout.iterator();
		
		while (iterator.hasNext()) {
			Component component = iterator.next();
			
			if (component instanceof Field) {
				if (!((Field) component).isValid()) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public static List<String> validateForm( final FormLayout formLayout ) throws TotallyValidForm {
		List<String> allRequiredErrors = getAllRequiredErrors( formLayout );
		
		if (!allRequiredErrors.isEmpty()) {
			return allRequiredErrors;
		}
		
		List<String> allNotValidFieldErrors = getAllNotValidFieldErrors( formLayout );
		
		if (!allNotValidFieldErrors.isEmpty()) {
			return allNotValidFieldErrors;
		}
		
		throw new TotallyValidForm();
	}
	
	private static List<String> getAllNotValidFieldErrors( final FormLayout formLayout ) {
		List<String> validateErrors = Lists.newArrayList();
		Iterator<Component> iterator = formLayout.iterator();
		
		while (iterator.hasNext()) {
			Component component = iterator.next();
			
			if (component instanceof Field && component.isVisible()) {
				Field castedComponent = (Field) component;
				
				try {
					castedComponent.validate();
				}
				catch (InvalidValueException notValidException) {
					String validationMessage = notValidException.getMessage();
					validateErrors.add( validationMessage );
				}
				
			}
		}
		
		return validateErrors;
	}
	
	public static class TotallyValidForm extends Exception {
		
	}
	
	@SuppressWarnings( "rawtypes" )
	private static boolean isAllRequiredFieldIsFilled( final FormLayout formLayout ) {
		Iterator<Component> iterator = formLayout.iterator();
		
		while (iterator.hasNext()) {
			Component component = iterator.next();
			
			if (component instanceof Field) {
				if (!((Field) component).isRequired()) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	private static List<String> getAllRequiredErrors( final FormLayout formLayout ) {
		List<String> errorMessages = Lists.newArrayList();
		Iterator<Component> iterator = formLayout.iterator();
		
		while (iterator.hasNext()) {
			Component component = iterator.next();
			
			if (component instanceof Field && component.isVisible()) {
				Field castedComponent = (Field) component;
				
				if (castedComponent.isRequired() && castedComponent.getValue() == null) {
					errorMessages.add( castedComponent.getRequiredError() );
				}
				
			}
		}
		
		return errorMessages;
	}
	
	@SuppressWarnings( "rawtypes" )
	public static void setFieldsToImmediate( final FormLayout formLayout, final boolean immediate ) {
		Iterator<Component> iterator = formLayout.iterator();
		
		while (iterator.hasNext()) {
			Component component = iterator.next();
			
			if (component instanceof AbstractField) {
				((AbstractField) component).setImmediate( immediate );
			}
		}
		
	}
	
}
