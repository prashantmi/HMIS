package dutyroster.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class OtherAreaMstFB extends ActionForm
{
	private String hmode;
	private String chk;
	private String isValid;
	private String hospitalcode;
	private String otherAreaName;
	 private String otherAreaCode;
	 private String serialNo;
	

	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		//this.hmode = "";
		this.otherAreaName = "";
		this.otherAreaCode = "";
		this.isValid = "-1";
		this.serialNo = "";	
		this.hospitalcode = "";
		
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String getChk() {
		return chk;
	}

	public void setChk(String chk) {
		this.chk = chk;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getHospitalcode() {
		return hospitalcode;
	}

	public void setHospitalcode(String hospitalcode) {
		this.hospitalcode = hospitalcode;
	}

	

	public String getOtherAreaName() {
		return otherAreaName;
	}

	public void setOtherAreaName(String otherAreaName) {
		this.otherAreaName = otherAreaName;
	}

	public String getOtherAreaCode() {
		return otherAreaCode;
	}

	public void setOtherAreaCode(String otherAreaCode) {
		this.otherAreaCode = otherAreaCode;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	
}
