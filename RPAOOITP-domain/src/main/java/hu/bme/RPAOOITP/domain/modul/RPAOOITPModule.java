
package hu.bme.RPAOOITP.domain.modul;

import javax.persistence.EntityManager;

import com.google.inject.AbstractModule;
import com.google.inject.binder.AnnotatedBindingBuilder;

public class RPAOOITPModule extends AbstractModule {
	
	private final EntityManager em;
	
	public RPAOOITPModule( final EntityManager em ) {
		super();
		this.em = em;
	}
	
	@Override
	protected void configure() {
		final AnnotatedBindingBuilder<EntityManager> bindingBuilder = bind( EntityManager.class );
		bindingBuilder.toInstance( em );
		
	}
}
