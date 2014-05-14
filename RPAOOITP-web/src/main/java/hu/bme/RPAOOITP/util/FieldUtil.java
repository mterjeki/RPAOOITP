
package hu.bme.RPAOOITP.util;

import hu.bme.RPAOOITP.components.GenericLayout;

import com.vaadin.data.util.NestedMethodProperty;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.DateField;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

public class FieldUtil {
	
	public static TextField createRequiredTextField( final GenericLayout<?> layout, final String propertyName ) {
		return createRequiredAbstracField( new TextField(), layout.getData(), propertyName );
	}
	
	public static TextField createRequiredTextField( final Object instance, final String propertyName ) {
		return createRequiredAbstracField( new TextField(), instance, propertyName );
	}
	
	public static TextField createTextField( final GenericLayout<?> layout, final String propertyName ) {
		return createAbstractField( new TextField(), layout.getData(), propertyName );
	}
	
	public static TextField createTextField( final Object instance, final String propertyName ) {
		return createAbstractField( new TextField(), instance, propertyName );
	}
	
	public static DateField createRequiredDateField( final GenericLayout<?> layout, final String propertyName ) {
		return createRequiredAbstracField( new DateField(), layout.getData(), propertyName );
	}
	
	public static DateField createRequiredDateField( final Object instance, final String propertyName ) {
		return createRequiredAbstracField( new DateField(), instance, propertyName );
	}
	
	public static DateField createDateField( final GenericLayout<?> layout, final String propertyName ) {
		return createAbstractField( new DateField(), layout.getData(), propertyName );
	}
	
	public static DateField createDateField( final Object instance, final String propertyName ) {
		return createAbstractField( new DateField(), instance, propertyName );
	}
	
	public static PasswordField createRequiredPasswordField( final GenericLayout<?> layout, final String propertyName ) {
		return createRequiredAbstracField( new PasswordField(), layout.getData(), propertyName );
	}
	
	public static PasswordField createRequiredPasswordField( final Object instance, final String propertyName ) {
		return createRequiredAbstracField( new PasswordField(), instance, propertyName );
	}
	
	public static PasswordField createPasswordField( final GenericLayout<?> layout, final String propertyName ) {
		return createAbstractField( new PasswordField(), layout.getData(), propertyName );
	}
	
	public static PasswordField createPasswordField( final Object instance, final String propertyName ) {
		return createAbstractField( new PasswordField(), instance, propertyName );
	}
	
	private static <T extends AbstractField<?>> T createRequiredAbstracField( final T field, final Object instance,
		final String propertyName ) {
		
		T generatedField = createAbstractField( field, instance, propertyName );
		
		MessageUtil messageUtil = new MessageUtil();
		generatedField.setRequired( true );
		generatedField.setRequiredError( messageUtil.getMessage( propertyName ) + " is required" );
		
		return generatedField;
	}
	
	private static <T extends AbstractField<?>> T createAbstractField( final T field, final Object instance,
		final String propertyName ) {
		MessageUtil messageUtil = new MessageUtil();
		field.setId( propertyName + "Id" );
		field.setStyleName( "input" );
		field.setCaption( messageUtil.getMessage( propertyName ) );
		field.setPropertyDataSource( new NestedMethodProperty<String>( instance, propertyName ) );
		field.setImmediate( true );
		
		if (field instanceof AbstractTextField) {
			((AbstractTextField) field).setNullRepresentation( "" );
		}
		
		return field;
	}
	
}
