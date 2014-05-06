
package hu.bme.RPAOOITP;

public class InconsistentDeploymentExceptionExtended extends RuntimeException {
	
	public enum ID {
		MULTIPLE_ROOTS,
		PATH_COLLISION,
		CLASS_NOT_FOUND
	}
	
	private ID id;
	
	public InconsistentDeploymentExceptionExtended( final ID id, final String message ) {
		super( message );
		this.id = id;
	}
	
	public InconsistentDeploymentExceptionExtended( final ID id, final Exception e ) {
		super( e );
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "[" + id + "] Inconsistent deployment: " + getMessage();
	}
	
}
