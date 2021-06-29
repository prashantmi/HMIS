package new_investigation.vo;

import hisglobal.vo.ValueObject;

public class MandatoryMasterVO extends ValueObject 
{

	private String mandatoryName;
	private String remarks;
	private String mandatoryCode;
	private String mandatoryType;


	public String getMandatoryType() {
		return mandatoryType;
	}
	public void setMandatoryType(String mandatoryType) {
		this.mandatoryType = mandatoryType;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	private String isActive;



	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public String getMandatoryName() {
		return mandatoryName;
	}
	public void setMandatoryName(String mandatoryName) {
		this.mandatoryName = mandatoryName;
	}
	public String getMandatoryCode() {
		return mandatoryCode;
	}
	public void setMandatoryCode(String mandatoryCode) {
		this.mandatoryCode = mandatoryCode;
	}
	


}
