package mrd.vo;


import hisglobal.vo.ValueObject;

public class UnitWiseEstProcedureMappingMstVO extends ValueObject
{
	private String tariffId;
	private String hospitalCode;
	private String deptUnitCode;
	private String isDefault;
	private String isValid;
	private String seatID;
	private String entryDate;
	private String lastModifyDate;
	private String lastModifiedSeatID;
	private String procedureListDesc;
	private String defaultProcedureListCode;
	
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getDeptUnitCode() {
		return deptUnitCode;
	}
	public void setDeptUnitCode(String deptUnitCode) {
		this.deptUnitCode = deptUnitCode;
	}
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getSeatID() {
		return seatID;
	}
	public void setSeatID(String seatID) {
		this.seatID = seatID;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getLastModifyDate() {
		return lastModifyDate;
	}
	public void setLastModifyDate(String lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}
	public String getLastModifiedSeatID() {
		return lastModifiedSeatID;
	}
	public void setLastModifiedSeatID(String lastModifiedSeatID) {
		this.lastModifiedSeatID = lastModifiedSeatID;
	}
	public String getProcedureListDesc() {
		return procedureListDesc;
	}
	public void setProcedureListDesc(String procedureListDesc) {
		this.procedureListDesc = procedureListDesc;
	}
	public String getDefaultProcedureListCode() {
		return defaultProcedureListCode;
	}
	public void setDefaultProcedureListCode(String defaultProcedureListCode) {
		this.defaultProcedureListCode = defaultProcedureListCode;
	}
	public String getTariffId() {
		return tariffId;
	}
	public void setTariffId(String tariffId) {
		this.tariffId = tariffId;
	}

	
}
