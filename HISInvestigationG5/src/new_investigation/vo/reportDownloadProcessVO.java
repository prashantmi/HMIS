package new_investigation.vo;

import hisglobal.vo.ValueObject;

public class reportDownloadProcessVO extends ValueObject {

	private String patMobileNo;
    private String crNo;
    private String otp;
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
