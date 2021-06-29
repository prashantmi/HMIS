/**********************************************************
 Project:	   AHIMS_G5	
 File:         HISApplicationExecutionException.java
 Created:      Jan, 2014
 Last Changed: Mar, 2016
 Author:       Pragya Sharma

This code is copyright (c) 2014 C-DAC Noida.
***********************************************************/

package hisglobal.exceptions;

public class HISApplicationExecutionException extends HISException
{
	private static final long serialVersionUID = 0101L;

	public HISApplicationExecutionException()
	{
		super("HIS Application Execution Exception");
		this.errorId = "0101";
	}

	public HISApplicationExecutionException(String strMsg)
	{
		super("HIS Application Execution Exception::" + strMsg);
	}
}