package mrd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ReceiveCertificateInMrdDeskFB extends ActionForm 
{
	private String deskmode;
	private String hmode;

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

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
}
