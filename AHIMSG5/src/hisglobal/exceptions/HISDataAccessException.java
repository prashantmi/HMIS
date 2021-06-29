/**********************************************************
 Project:	   AHIMS_G5	
 File:         HISDataAccessException.java
 Created:      Jan, 2014
 Last Changed: Mar, 2016
 Author:       Pragya Sharma

This code is copyright (c) 2014 C-DAC Noida.
***********************************************************/

package hisglobal.exceptions;

public class HISDataAccessException extends HISException
{
	private static final long serialVersionUID = 0102L;

	public HISDataAccessException()
	{
		super("HIS Data Access Exception");
		this.errorId = "0102";
	}

	public HISDataAccessException(String strMsg)
	{
		super("HIS Data Access Exception::" + strMsg);
	}
}