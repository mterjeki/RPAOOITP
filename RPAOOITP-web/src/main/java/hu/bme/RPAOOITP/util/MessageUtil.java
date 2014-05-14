
package hu.bme.RPAOOITP.util;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class MessageUtil implements Serializable {
	
	private static final String I18_BASE_PATH = "i18n/messages";
	private final ResourceBundle bundle;
	
	public MessageUtil() {
		// ResourceBundle docs say that returned instances are cached, so it's not a problem
		// to call this on every request
		bundle = ResourceBundle.getBundle( I18_BASE_PATH );
	}
	
	public String getMessage( final String key ) {
		try {
			return bundle.getString( key );
		}
		catch (MissingResourceException e) {
			return "Missing: " + key;
		}
	}
	
	public String getMessage( final String key, final String... args ) {
		return MessageFormat.format( getMessage( key ), (Object[]) args );
	}
	
	/**
	 * @param status
	 * @return
	 */
	public String getTranslatedEnum( final Enum<?> enumValue ) {
		return getMessage( enumValue.getClass().getCanonicalName() + "." + enumValue.name() );
	}
	
}
