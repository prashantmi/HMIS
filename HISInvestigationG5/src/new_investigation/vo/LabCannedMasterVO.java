package new_investigation.vo;

import hisglobal.vo.ValueObject;

public class LabCannedMasterVO extends ValueObject 
{

	String[] leftList;
	String[] rightList;

	private String cannedCode;
	private String labCode;
	private String hospitalCode;
	private String cannedName;
	private String localLaboratoryName;
	private String globalLaboratoryName;
	private String count;
	private String userCannedCode;

	public String getCannedName() {
		return cannedName;
	}


	public void setCannedName(String cannedName) {
		this.cannedName = cannedName;
	}


	public String getCannedCode() {
		return cannedCode;
	}


	public void setCannedCode(String cannedCode) {
		this.cannedCode = cannedCode;
	}


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


	public String getUserCannedCode() {
		return userCannedCode;
	}


	public void setUserCannedCode(String userCannedCode) {
		this.userCannedCode = userCannedCode;
	}



}

