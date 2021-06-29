/**********************************************************
 Project:	   AHIMS_G5	
 File:         HISInvalidTransactionStateException.java
 Created:      Jan, 2014
 Last Changed: Mar, 2016
 Author:       Pragya Sharma

This code is copyright (c) 2014 C-DAC Noida.
***********************************************************/

package hisglobal.exceptions;

public class HISInvalidTransactionStateException extends HISTransactionException
{
	private static final long serialVersionUID = 0105L;

	public HISInvalidTransactionStateException()
	{
		super("HIS Invalid Transaction State Exception");
		this.errorId = "0105";
	}

	public HISInvalidTransactionStateException(String strMsg)
	{
		super("HIS Invalid Transaction State Exception::" + strMsg);
	}
}
