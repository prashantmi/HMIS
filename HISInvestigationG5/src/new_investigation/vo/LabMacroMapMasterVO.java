package new_investigation.vo;

import hisglobal.vo.ValueObject;

public class LabMacroMapMasterVO extends ValueObject 
{

			String[] leftList;
			String[] rightList;

	private String macroCode;
	private String remarks;
	private String labCode;
	private String hospitalCode;
	private String localLaboratoryName;
	private String globalLaboratoryName;
	private String count;
	private String userMacroCode;
	private String macroName;


	public String getLocalLaboratoryName() {
		return localLaboratoryName;
	}


	public void setLocalLaboratoryName(String localLaboratoryName) {
		this.localLaboratoryName = localLaboratoryName;
	}


	public String getGlobalLaboratoryName() {
		return globalLaboratoryName;
	}


	public void setGlobalLaboratoryName(String globalLaboratoryName) {
		this.globalLaboratoryName = globalLaboratoryName;
	}


	private String isActive;


	public String[] getLeftList() {
		return leftList;
	}


	public void setLeftList(String[] leftList) {
		this.leftList = leftList;
	}


	public String[] getRightList() {
		return rightList;
	}


	public void setRightList(String[] rightList) {
		this.rightList = rightList;
	}


	public String getMacroCode() {
		return macroCode;
	}


	public void setMacroCode(String macroCode) {
		this.macroCode = macroCode;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public String getLabCode() {
		return labCode;
	}


	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}


	public String getHospitalCode() {
		return hospitalCode;
	}


	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}


	public String getIsActive() {
		return isActive;
	}


	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}


	public String getCount() {
		return count;
	}


	public void setCount(String count) {
		this.count = count;
	}


	public String getUserMacroCode() {
		return userMacroCode;
	}


	public void setUserMacroCode(String userMacroCode) {
		this.userMacroCode = userMacroCode;
	}


	public String getMacroName() {
		return macroName;
	}


	public void setMacroName(String macroName) {
		this.macroName = macroName;
	}



	}

