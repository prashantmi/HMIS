package hisglobal.vo;

public class DoctorDesigMappingVO extends ValueObject
{
	private String processType;
	private String designationCode;
	private String hospitalCode;
	private String seatId;
	private String designationId;
	private String remarks;
	private String entryDate;
	private String isValid;
	private String maxDays;
	
	
	public String getProcessType() {
		return processType;
	}
	public void setProcessType(String processType) {
		this.processType = processType;
	}
	
	public String getDesignationCode() {
		return designationCode;
	}
	public void setDesignationCode(String designationCode) {
		this.designationCode = designationCode;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getDesignationId() {
		return designationId;
	}
	public void setDesignationId(String designationId) {
		this.designationId = designationId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getMaxDays() {
		return maxDays;
	}
	public void setMaxDays(String maxDays) {
		this.maxDays = maxDays;
	}


	

}
