
package hu.bme.RPAOOITP;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewProvider;
import com.vaadin.ui.UI;

public class RPAOOITPViewProvider implements ViewProvider {
	
	@Inject
	private BeanManager beanManager;
	
	@Inject
	private RPAOOITPAuthControl authControl;
	
	private transient CreationalContext<?> currentViewCreationalContext;
	
	@Override
	public String getViewName( final String viewAndParameters ) {
		String name = parseViewName( viewAndParameters );
		
		Bean<?> viewBean = getViewBean( name );
		
		if (viewBean == null) {
			return null;
		}
		
		if (isUserHavingAccessToView( viewBean )) {
			if (viewBean.getBeanClass().isAnnotationPresent( CDIView.class )) {
				String specifiedViewName = viewBean.getBeanClass()
					.getAnnotation( CDIView.class ).value();
				if (!specifiedViewName.isEmpty()) {
					return specifiedViewName;
				}
			}
			return name;
		}
		
		return null;
	}
	
	@Override
	public View getView( final String viewName ) {
		Bean<?> viewBean = getViewBean( viewName );
		
		if (viewBean != null) {
			if (!isUserHavingAccessToView( viewBean )) {
				return null;
			}
			
			if (currentViewCreationalContext != null) {
				currentViewCreationalContext.release();
			}
			
			currentViewCreationalContext = beanManager
				.createCreationalContext( viewBean );
			View view = (View) beanManager.getReference( viewBean,
				viewBean.getBeanClass(), currentViewCreationalContext );
			return view;
		}
		
		throw new RuntimeException( "Unable to instantiate view" );
	}
	
	private String parseViewName( final String viewAndParameters ) {
		
		String viewName = viewAndParameters;
		if (viewName.startsWith( "!" )) {
			viewName = viewName.substring( 1 );
		}
		
		if (viewName.contains( "/" )) {
			viewName = viewName.split( "/" )[0];
		}
		
		return viewName;
	}
	
	private Bean<?> getViewBean( final String viewName ) {
		Set<Bean<?>> matching = new HashSet<Bean<?>>();
		Set<Bean<?>> all = beanManager.getBeans( View.class,
			new AnnotationLiteral<Any>() {} );
		if (all.isEmpty()) {
			return null;
		}
		for (Bean<?> bean : all) {
			Class<?> beanClass = bean.getBeanClass();
			CDIView viewAnnotation = beanClass.getAnnotation( CDIView.class );
			if (viewAnnotation == null) {
				continue;
			}
			
			String mapping = viewAnnotation.value();
			
			// In the case of an empty fragment, use the root view.
			// Note that the root view should not support parameters if other
			// views are used.
			
			if (viewAnnotation.supportsParameters()
				&& viewName.startsWith( mapping )) {
				matching.add( bean );
			}
			else if (viewName.equals( mapping )) {
				matching.add( bean );
			}
		}
		
		Set<Bean<?>> viewBeansForThisProvider = getViewBeansForCurrentUI( matching );
		if (viewBeansForThisProvider.isEmpty()) {
			return null;
		}
		
		if (viewBeansForThisProvider.size() > 1) {
			throw new RuntimeException(
				"Multiple views mapped with same name for same UI" );
		}
		
		return viewBeansForThisProvider.iterator().next();
	}
	
	private Set<Bean<?>> getViewBeansForCurrentUI( final Set<Bean<?>> beans ) {
		Set<Bean<?>> viewBeans = new HashSet<Bean<?>>();
		
		for (Bean<?> bean : beans) {
			CDIView viewAnnotation = bean.getBeanClass().getAnnotation(
				CDIView.class );
			
			if (viewAnnotation == null) {
				continue;
			}
			
			List<Class<? extends UI>> uiClasses = Arrays.asList( viewAnnotation
				.uis() );
			
			if (uiClasses.contains( UI.class )
				|| uiClasses.contains( UI.getCurrent().getClass() )) {
				viewBeans.add( bean );
			}
		}
		
		return viewBeans;
	}
	
	private boolean isUserHavingAccessToView( final Bean<?> viewBean ) {
		
		if (viewBean.getBeanClass().isAnnotationPresent( CDIView.class )) {
			if (!viewBean.getBeanClass()
				.isAnnotationPresent( RolesAllowed.class )) {
				// No roles defined, everyone is allowed
				return true;
			}
			else {
				RolesAllowed rolesAnnotation = viewBean.getBeanClass()
					.getAnnotation( RolesAllowed.class );
				boolean hasAccess = authControl
					.isUserInSomeRole( rolesAnnotation.value() );
				
				return hasAccess;
			}
		}
		
		// No annotation defined, everyone is allowed
		return true;
	}
	
}
