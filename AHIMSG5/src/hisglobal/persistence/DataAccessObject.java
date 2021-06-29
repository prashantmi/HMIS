/**********************************************************
 Project:	   AHIMS_G5	
 File:         DataAccessObject.java
 Created:      Jan, 2014
 Last Changed: Mar, 2016
 Author:       Pragya Sharma

This code is copyright (c) 2014 C-DAC Noida.
***********************************************************/

package hisglobal.persistence;

public abstract class DataAccessObject
{
	protected TransactionContext transactionContext;

	protected DataAccessObject(TransactionContext objTransaction)
	{
		this.transactionContext = objTransaction;
	}

	protected TransactionContext getTransactionContext()
	{
		return transactionContext;
	}
}