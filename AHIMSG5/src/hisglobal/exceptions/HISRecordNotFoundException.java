/**********************************************************
 Project:	   AHIMS_G5	
 File:         HISRecordNotFoundException.java
 Created:      Jan, 2014
 Last Changed: Mar, 2016
 Author:       Pragya Sharma

This code is copyright (c) 2014 C-DAC Noida.
***********************************************************/

package hisglobal.exceptions;

import java.util.Map;

public class HISRecordNotFoundException extends HISException
{
	private static final long serialVersionUID = 0106L;

	private Map essentialMap;

	public HISRecordNotFoundException()
	{
		super("HIS Record Not Found Exception");
		this.errorId = "0106";
	}

	public HISRecordNotFoundException(String strMsg)
	{
		super("HIS Record Not Found Exception::" + strMsg);
	}

	public Map getEssentialMap()
	{
		return essentialMap;
	}

	public void setEssentialMap(Map essentialMap)
	{
		this.essentialMap = essentialMap;
	}

	public HISRecordNotFoundException(String strMsg, Map essentialMap)
	{
		super(strMsg);
		this.essentialMap = essentialMap;
	}
}
