package hisglobal.vo;

public class UnitDrugMstVO extends ValueObject{
	private String extTreatId;
	private String deptUnitCode;
	private String hospitalCode;
	private String slNo;
	private String isValid;
	private String seatId;
	private String entryDate;
	private String extTreatName;
	
	public String getExtTreatName() {
		return extTreatName;
	}
	public void setExtTreatName(String extTreatName) {
		this.extTreatName = extTreatName;
	}
	public String getExtTreatId() {
		return extTreatId;
	}
	public void setExtTreatId(String extTreatId) {
		this.extTreatId = extTreatId;
	}
	public String getDeptUnitCode() {
		return deptUnitCode;
	}
	public void setDeptUnitCode(String deptUnitCode) {
		this.deptUnitCode = deptUnitCode;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
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

}
