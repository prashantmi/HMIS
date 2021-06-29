/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DataHelper.persistence;

/**
 *
 * @author Siddharth Srivastava <siddharthsrivastava@cdac.in>
 */

public class TransactionException extends RuntimeException
{
	protected TransactionException()
	{
		super();
	}

	public TransactionException(String message)
	{
		super(message);
	}
}
