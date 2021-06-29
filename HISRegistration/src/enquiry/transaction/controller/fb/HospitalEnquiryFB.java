
package enquiry.transaction.controller.fb;

import org.apache.struts.action.ActionForm;

public class HospitalEnquiryFB extends ActionForm {

	private String hmode;
	private String isDirectCall;
	private String hospitalCode;

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String getIsDirectCall() {
		return isDirectCall;
	}

	public void setIsDirectCall(String isDirectCall) {
		this.isDirectCall = isDirectCall;
	}

	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	} 
	
	
	
}
