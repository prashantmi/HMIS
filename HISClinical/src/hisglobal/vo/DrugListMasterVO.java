package hisglobal.vo;

public class DrugListMasterVO extends ValueObject
{
	private String drugListId;
	private String drugListName;
	private String isValid;
	private String remark;
	private String slNo;
	private String seatId;
	private String diseaseCode;
	private String entryDate;
	private String hospitalCode;
	private String lastModifyDate;
	private String lastModofySeatId;
	
	
	public String getDrugListId() {
		return drugListId;
	}
	public void setDrugListId(String drugListId) {
		this.drugListId = drugListId;
	}
	public String getDrugListName() {
		return drugListName;
	}
	public void setDrugListName(String drugListName) {
		this.drugListName = drugListName;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getDiseaseCode() {
		return diseaseCode;
	}
	public void setDiseaseCode(String diseaseCode) {
		this.diseaseCode = diseaseCode;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getLastModifyDate() {
		return lastModifyDate;
	}
	public void setLastModifyDate(String lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}
	public String getLastModofySeatId() {
		return lastModofySeatId;
	}
	public void setLastModofySeatId(String lastModofySeatId) {
		this.lastModofySeatId = lastModofySeatId;
	}
	
	
}
