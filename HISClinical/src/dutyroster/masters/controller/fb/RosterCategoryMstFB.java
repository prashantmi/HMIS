package dutyroster.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class RosterCategoryMstFB extends ActionForm
{
	private String hmode;
	private String chk;
	private String isValid;
	private String hospitalcode;
	private String rosterCategoryName;
	 private String rosterCategoryCode;
	 private String serialNo;
	 private String rosterMainCategoryCode;
	 private String rosterMode;
	 private String maxOff;
	 private String maxContinuousOff;
	 private String dutyOffFlag;
	 private String dutyOffAccount;
	 private String noOfHours;
	 
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		//this.hmode = "";
		this.rosterCategoryName = "";
		this.rosterCategoryCode = "";
		this.isValid = "-1";
		this.serialNo = "";	
		this.hospitalcode = "";
		this.rosterMainCategoryCode="-1";
		this.rosterMode="1";
		this.maxOff="";
		this.maxContinuousOff="";
		this.dutyOffFlag="0";
		this.dutyOffAccount="";
		this.noOfHours="";
		
	}

	
	
	public String getRosterMode() {
		return rosterMode;
	}



	public void setRosterMode(String rosterMode) {
		this.rosterMode = rosterMode;
	}



	public String getMaxOff() {
		return maxOff;
	}



	public void setMaxOff(String maxOff) {
		this.maxOff = maxOff;
	}



	public String getMaxContinuousOff() {
		return maxContinuousOff;
	}



	public void setMaxContinuousOff(String maxContinuousOff) {
		this.maxContinuousOff = maxContinuousOff;
	}



	public String getDutyOffFlag() {
		return dutyOffFlag;
	}



	public void setDutyOffFlag(String dutyOffFlag) {
		this.dutyOffFlag = dutyOffFlag;
	}



	public String getDutyOffAccount() {
		return dutyOffAccount;
	}



	public void setDutyOffAccount(String dutyOffAccount) {
		this.dutyOffAccount = dutyOffAccount;
	}



	public String getNoOfHours() {
		return noOfHours;
	}



	public void setNoOfHours(String noOfHours) {
		this.noOfHours = noOfHours;
	}



	public String getRosterMainCategoryCode() {
		return rosterMainCategoryCode;
	}
	
	public void setRosterMainCategoryCode(String rosterMainCategoryCode) {
		this.rosterMainCategoryCode = rosterMainCategoryCode;
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

	public String getRosterCategoryName() {
		return rosterCategoryName;
	}

	public void setRosterCategoryName(String rosterCategoryName) {
		this.rosterCategoryName = rosterCategoryName;
	}

	public String getRosterCategoryCode() {
		return rosterCategoryCode;
	}

	public void setRosterCategoryCode(String rosterCategoryCode) {
		this.rosterCategoryCode = rosterCategoryCode;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}


	
}
