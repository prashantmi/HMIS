package registration.controller.fb;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class PatientAuditTrailFB extends CRNoFB {

	String hmode="";
	
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		super.reset(mapping, request);
		this.hmode="";
		
				
	}


	public String getHmode() {
		return hmode;
	}


	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	
	
		
}
