
package hu.bme.RPAOOITP.domain.query.impl;

import hu.bme.RPAOOITP.domain.model.AbstractIdentifiable;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.google.inject.Inject;

public abstract class AbstractHibernateBaseRPAOOITPQueries {
	
	@Inject
	protected EntityManager em;
	
	public <ID extends Serializable, E extends AbstractIdentifiable<ID>> void persistEntity( final E entity ) {
		em.persist( entity );
	}
	
	public <ID extends Serializable, E extends AbstractIdentifiable<ID>> void mergeEntity( final E entity ) {
		em.persist( entity );
	}
	
	public <ID extends Serializable, E extends AbstractIdentifiable<ID>> E findEntityById( final Class<E> entityClass, final ID id ) {
		return em.find( entityClass, id );
	}
	
	public <ID extends Serializable, E extends AbstractIdentifiable<ID>> List<E> loadAll(
		final Class<E> entityClass ) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery( entityClass );
		Root<E> entityRoot = criteriaQuery.from( entityClass );
		
		criteriaQuery.select( entityRoot );
		
		TypedQuery<E> typedQuery = em.createQuery( criteriaQuery );
		return typedQuery.getResultList();
	}
	
}
