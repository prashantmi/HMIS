package new_investigation.transactions.controller.fb;

import org.apache.struts.action.ActionForm;

public class reportDownloadProcessFB extends ActionForm {

	private String hmode;
    private String patMobileNo;
    private String crNo;
    private String otp;
    
	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String getPatMobileNo() {
		return patMobileNo;
	}

	public void setPatMobileNo(String patMobileNo) {
		this.patMobileNo = patMobileNo;
	}

	public String getCrNo() {
		return crNo;
	}

	public void setCrNo(String crNo) {
		this.crNo = crNo;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}
	
	
	
}
