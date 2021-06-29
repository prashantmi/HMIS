/**********************************************************
 Project:	   AHIMS_G5	
 File:         HISTransactionException.java
 Created:      Jan, 2014
 Last Changed: Mar, 2016
 Author:       Pragya Sharma

This code is copyright (c) 2014 C-DAC Noida.
***********************************************************/

package hisglobal.exceptions;

public class HISTransactionException extends HISException
{
	private static final long serialVersionUID = 0104L;

	public HISTransactionException()
	{
		super("HIS Transaction Exception");
		this.errorId = "0104";
	}

	public HISTransactionException(String strMsg)
	{
		super("HIS Transaction Exception::" + strMsg);
	}
}