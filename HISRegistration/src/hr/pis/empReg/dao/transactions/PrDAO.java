package hr.pis.empReg.dao.transactions;

import hisglobal.persistence.TransactionContext;
import hisglobal.persistence.DataAccessObject;

public abstract class PrDAO extends DataAccessObject {
	private static Object lock = new Object();
	
	protected PrDAO(TransactionContext _tx) {
		super(_tx);
	}

	public Object getLock() {
		return lock;
	}
}
