package hisglobal.vo;

public class UnitDrugListMasterVO extends ValueObject
{
	private String deptUnitCode;
	private String drugListId;
	private String isValid;
	private String isDefault;
	private String slNo;
	private String seatId;
	private String entryDate;
	private String hospitalCode;
	private String lastModifydate;
	private String lastModifySeatId;
	private String drugListDesc;
	
	public String getDrugListDesc() {
		return drugListDesc;
	}
	public void setDrugListDesc(String drugListDesc) {
		this.drugListDesc = drugListDesc;
	}
	public String getDeptUnitCode() {
		return deptUnitCode;
	}
	public void setDeptUnitCode(String deptUnitCode) {
		this.deptUnitCode = deptUnitCode;
	}
	public String getDrugListId() {
		return drugListId;
	}
	public void setDrugListId(String drugListId) {
		this.drugListId = drugListId;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
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
	public String getLastModifydate() {
		return lastModifydate;
	}
	public void setLastModifydate(String lastModifydate) {
		this.lastModifydate = lastModifydate;
	}
	public String getLastModifySeatId() {
		return lastModifySeatId;
	}
	public void setLastModifySeatId(String lastModifySeatId) {
		this.lastModifySeatId = lastModifySeatId;
	}
	
}
