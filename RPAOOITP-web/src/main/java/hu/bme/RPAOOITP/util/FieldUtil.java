
package hu.bme.RPAOOITP.util;

import hu.bme.RPAOOITP.components.GenericLayout;

import com.vaadin.data.util.NestedMethodProperty;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

public class FieldUtil {
	
	public static TextField createRequiredTextField( final GenericLayout<?> layout, final String propertyName ) {
		return createRequiredAbstractTextField( new TextField(), layout.getData(), propertyName );
	}
	
	public static TextField createRequiredTextField( final Object instance, final String propertyName ) {
		return createRequiredAbstractTextField( new TextField(), instance, propertyName );
	}
	
	public static TextField createTextField( final GenericLayout<?> layout, final String propertyName ) {
		return createAbstractTextField( new TextField(), layout.getData(), propertyName );
	}
	
	public static TextField createTextField( final Object instance, final String propertyName ) {
		return createAbstractTextField( new TextField(), instance, propertyName );
	}
	
	public static PasswordField createRequiredPasswordField( final GenericLayout<?> layout, final String propertyName ) {
		return createRequiredAbstractTextField( new PasswordField(), layout.getData(), propertyName );
	}
	
	public static PasswordField createRequiredPasswordField( final Object instance, final String propertyName ) {
		return createRequiredAbstractTextField( new PasswordField(), instance, propertyName );
	}
	
	public static PasswordField createPasswordField( final GenericLayout<?> layout, final String propertyName ) {
		return createAbstractTextField( new PasswordField(), layout.getData(), propertyName );
	}
	
	public static PasswordField createPasswordField( final Object instance, final String propertyName ) {
		return createAbstractTextField( new PasswordField(), instance, propertyName );
	}
	
	private static <T extends AbstractTextField> T createRequiredAbstractTextField( final T field, final Object instance,
		final String propertyName ) {
		
		T generatedField = createAbstractTextField( field, instance, propertyName );
		
		generatedField.setRequired( true );
		generatedField.setRequiredError( propertyName + " is required" );
		
		return generatedField;
	}
	
	private static <T extends AbstractTextField> T createAbstractTextField( final T field, final Object instance,
		final String propertyName ) {
		
		field.setId( propertyName + "Id" );
		field.setCaption( propertyName );
		field.setPropertyDataSource( new NestedMethodProperty<String>( instance, propertyName ) );
		field.setNullRepresentation( "" );
		
		return field;
	}
	
}
