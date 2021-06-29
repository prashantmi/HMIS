package opd.master.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class IcdMasterFB extends ActionForm{
	
	private String deskmode="";
	private String hmode;

	
	
	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public void reset(ActionMapping mapping,HttpServletRequest req)
	{		 
		deskmode="";
	}

	public String getDeskmode() {
		return deskmode;
	}

	public void setDeskmode(String deskmode) {
		this.deskmode = deskmode;
	}

}
