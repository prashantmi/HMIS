/**********************************************************
 Project:	   AHIMS_G5	
 File:         HISServiceLocatorException.java
 Created:      Jan, 2014
 Last Changed: Mar, 2016
 Author:       Pragya Sharma

This code is copyright (c) 2014 C-DAC Noida.
***********************************************************/

package hisglobal.exceptions;

public class HISServiceLocatorException extends HISException
{
	private static final long serialVersionUID = 0103L;

	public HISServiceLocatorException()
	{
		super("HIS Service Locator Exception");
		this.errorId = "0103";
	}

	public HISServiceLocatorException(String strMsg)
	{
		super("HIS Service Locator Exception::" + strMsg);
	}
}