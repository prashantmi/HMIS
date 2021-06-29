package hisglobal.vo;

public class DrugDoseMstVO extends ValueObject
{
	private String hmode;
	private String doseName;
	private String doseInstruction;
	private String itemType;
	private String itemTypeId;
	private String sereialNo;
	private String doseId;
	private String doseQty;
	private String isFrequencyBound;
	private String isValid;
	private String isActive;
	private String hospitalCode;
	
	
	
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getDoseQty() {
		return doseQty;
	}
	public void setDoseQty(String doseQty) {
		this.doseQty = doseQty;
	}
	public String getIsFrequencyBound() {
		return isFrequencyBound;
	}
	public void setIsFrequencyBound(String isFrequencyBound) {
		this.isFrequencyBound = isFrequencyBound;
	}
	public String getDoseId() {
		return doseId;
	}
	public void setDoseId(String doseId) {
		this.doseId = doseId;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getDoseName() {
		return doseName;
	}
	public void setDoseName(String doseName) {
		this.doseName = doseName;
	}
	
	
	public String getDoseInstruction() {
		return doseInstruction;
	}
	public void setDoseInstruction(String doseInstruction) {
		this.doseInstruction = doseInstruction;
	}
	public String getSereialNo() {
		return sereialNo;
	}
	public void setSereialNo(String sereialNo) {
		this.sereialNo = sereialNo;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getItemTypeId() {
		return itemTypeId;
	}
	public void setItemTypeId(String itemTypeId) {
		this.itemTypeId = itemTypeId;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

}
