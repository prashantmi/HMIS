package mrd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class BirthDeathRegistrationUploadDeskFB extends ActionForm 
{
	private String deskmode;

	public String getDeskmode() {
		return deskmode;
	}

	public void setDeskmode(String deskmode) {
		this.deskmode = deskmode;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		deskmode="";
	}
}
