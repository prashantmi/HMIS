package registration.dao;

import hisglobal.persistence.TransactionContext;
import hisglobal.persistence.DataAccessObject;

public abstract class RegistrationDAO extends DataAccessObject {
	private static Object lock = new Object();
	
	protected RegistrationDAO(TransactionContext _tx) {
		super(_tx);
	}

	public Object getLock() {
		return lock;
	}
}
