package new_investigation.vo;

import hisglobal.vo.ValueObject;

public class LabCollectionAreaMasterVO extends ValueObject 
{

	private String selectedLab;
	private String templab;
	
			String[] leftList;
			String[] rightList;

	private String collectionareaCode;
	private String remarks;
	private String labCode;
	private String hospitalCode;


	private String isActive;



	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCollectionareaCode() {
		return collectionareaCode;
	}
	public void setCollectionareaCode(String collectionareaCode) {
		this.collectionareaCode = collectionareaCode;
	}
	
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getSelectedLab() {
		return selectedLab;
	}
	public void setSelectedLab(String selectedLab) {
		this.selectedLab = selectedLab;
	}
	public String getTemplab() {
		return templab;
	}
	public void setTemplab(String templab) {
		this.templab = templab;
	}
	public String getLabCode() {
		return labCode;
	}
	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}
	
	}

