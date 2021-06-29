package enquiry.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class FreePatientListEnquiryFB extends ActionForm {
	
	private String hmode;
	private String patientCategory;
	
	public void reset(ActionMapping mapping,HttpServletRequest request){
		this.patientCategory="";
	}
	
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getPatientCategory() {
		return patientCategory;
	}
	public void setPatientCategory(String patientCategory) {
		this.patientCategory = patientCategory;
	}

}
