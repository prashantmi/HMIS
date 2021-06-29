package hisglobal.vo;

public class MethodMasterVO extends ValueObject 
{
	private String methodID;
	private String method;
	private String methodTypeName;
	private String slNo;
	private String methodType;
	private String isActive;
	private String seatID;
	private String entryDate;
	private String lastModifyDate;
	private String lastModifiedSeatID;
	private String hospitalCode;
	public String getMethodID() {
		return methodID;
	}
	public void setMethodID(String methodID) {
		this.methodID = methodID;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getMethodTypeName() {
		return methodTypeName;
	}
	public void setMethodTypeName(String methodTypeName) {
		this.methodTypeName = methodTypeName;
	}
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getMethodType() {
		return methodType;
	}
	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
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
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

}
