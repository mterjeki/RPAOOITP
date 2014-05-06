
package hu.bme.RPAOOITP;

import java.lang.annotation.Inherited;

import javax.enterprise.inject.Stereotype;

/**
 * - * All UIs need to be declared with this annotation. CDIUI annotation binds the
 * - * lifecycle of a given UI to Vaadin's view lifecycle. There is one UI instance
 * - * per tab and so multiple instances per session.
 * + * All UIs need to be declared with this annotation. CDIUI annotation binds the lifecycle of a given UI to Vaadin's
 * view
 * + * lifecycle. There is one UI instance per tab and so multiple instances per session.
 * 
 */
@Stereotype
@Inherited
public @interface CDIUI {
	
	/**
	 * + * An optional URI mapping. If not specified, the mapping is going to be derived from the simple name of the
	 * class.
	 * + * A class WelcomeVaadin is going to be bound to "/welcomeVaadin" uri.
	 * + *
	 * + * @return the URI mapping of this UI
	 * +
	 */
	public String value() default "";
	
	/**
	 * + * Determines whether the dynamically deployed servlet should support async operation. For backward
	 * compatibility it
	 * + * defaults to false.
	 * + *
	 * + * @return
	 * +
	 */
	public boolean asyncSupported() default false;
}
