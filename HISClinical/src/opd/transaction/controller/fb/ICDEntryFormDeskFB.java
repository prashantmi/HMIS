package opd.transaction.controller.fb;

/*
 * @ author Pragya Sharma
 * Creation Date : 23-Sep-2011
 */

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ICDEntryFormDeskFB extends ActionForm
{
	private String deskMode;
	
	public ICDEntryFormDeskFB()
	{
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		deskMode = "";
	}

	public String getDeskMode()
	{
		return deskMode;
	}

	public void setDeskMode(String deskmode)
	{
		this.deskMode = deskmode;
	}

}
