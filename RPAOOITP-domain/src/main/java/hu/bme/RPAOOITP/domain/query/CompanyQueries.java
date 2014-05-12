
package hu.bme.RPAOOITP.domain.query;

import java.util.List;

import hu.bme.RPAOOITP.domain.model.Company;
import hu.bme.RPAOOITP.domain.model.User;

public interface CompanyQueries {
	
	void addUserToCompany( final User user, final Company company );
	
	void removeUserFromCompany( final User user, final Company company );
	
	List<User> findUsersOfCompany( final Company company );
	
}
