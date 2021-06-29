/**********************************************************
 Project:	   AHIMS_G5	
 File:         HISException.java
 Created:      Jan, 2014
 Last Changed: Mar, 2016
 Author:       Pragya Sharma

This code is copyright (c) 2014 C-DAC Noida.
***********************************************************/

package hisglobal.exceptions;

public class HISException extends RuntimeException
{
	private static final long serialVersionUID = 01L;

	protected String errorId = "0100";

	public HISException()
	{
		super("HIS Exception");
	}

	public HISException(String strMsg)
	{
		super(strMsg);
	}

	public String getErrorID()
	{
		return this.errorId;
	}
}