
package hu.bme.RPAOOITP.ejb.internal;

import hu.bme.RPAOOITP.domain.modul.RPAOOITPModule;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.google.inject.Guice;
import com.google.inject.Injector;

public abstract class AbstractManagerEJB {
	
	@PersistenceContext( unitName = "hu.bme.rpaooitp.jpa" )
	protected EntityManager em;
	
	protected Injector buildInjector() {
		return Guice.createInjector( new RPAOOITPModule( em ) );
	}
	
}
