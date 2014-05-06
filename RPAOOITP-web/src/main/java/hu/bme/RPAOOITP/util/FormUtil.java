
package hu.bme.RPAOOITP.util;

import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Lists;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;

public class FormUtil {
	
	@SuppressWarnings( "rawtypes" )
	public static boolean isAllRequiredFieldIsFilled( final FormLayout formLayout ) {
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
	
	public static List<String> getAllRequiredErrors( final FormLayout formLayout ) {
		List<String> errorMessages = Lists.newArrayList();
		Iterator<Component> iterator = formLayout.iterator();
		
		while (iterator.hasNext()) {
			Component component = iterator.next();
			
			if (component instanceof Field) {
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
