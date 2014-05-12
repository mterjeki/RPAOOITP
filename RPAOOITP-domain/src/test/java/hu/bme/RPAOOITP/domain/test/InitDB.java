
package hu.bme.RPAOOITP.domain.test;

import hu.bme.RPAOOITP.domain.modul.RPAOOITPModule;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.collect.Maps;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class InitDB {
	
	private static EntityManagerFactory entityManagerFactory;
	
	private EntityManager em;
	
	private Injector injector;
	
	@BeforeClass
	public static void setup() throws Exception {
		final Map<String, Object> properties = Maps.newTreeMap();
		properties.put( "hibernate.hbm2ddl.auto", "create" );
		properties.put( "hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect" );
		properties.put( "hibernate.cache.use_second_level_cache", "false" );
		properties.put( "hibernate.cache.use_query_cache", "false" );
		properties.put( "hibernate.connection.url", "jdbc:mysql://localhost:3306/rpaooitp" );
		properties.put( "hibernate.connection.username", "root" );
		properties.put( "hibernate.connection.password", "root" );
		properties.put( "hibernate.connection.driver_class", "com.mysql.jdbc.Driver" );
		
		entityManagerFactory = Persistence.createEntityManagerFactory( "test::rpaooitpDomain", properties );
	}
	
	@Before
	public void start() throws Exception {
		em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		injector = Guice.createInjector( new RPAOOITPModule( em ) );
	}
	
	@After
	public void end() throws Exception {
		try {
			em.flush();
			final EntityTransaction transaction = em.getTransaction();
			if (transaction.isActive()) {
				transaction.commit();
			}
		}
		finally {
			em.close();
			em = null;
		}
		
	}
	
	@Test
	public void initDBTest() {
		
	}
	
}
