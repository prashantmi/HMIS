package hisglobal.vo;

public class MrdRecordReturnDtlVO extends ValueObject
{
	private String requestId;
	private String mrdRecordId;
	private String returnDate;
	private String recordType;
	private String mrdCode;
	private String remarks;
	private String returnBy;
	private String expectedReturnDate;
	private String hospitalCode;
	private String returnByName;
	
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getMrdRecordId() {
		return mrdRecordId;
	}
	public void setMrdRecordId(String mrdRecordId) {
		this.mrdRecordId = mrdRecordId;
	}
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getMrdCode() {
		return mrdCode;
	}
	public void setMrdCode(String mrdCode) {
		this.mrdCode = mrdCode;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public String getReturnBy() {
		return returnBy;
	}
	public void setReturnBy(String returnBy) {
		this.returnBy = returnBy;
	}
	public String getExpectedReturnDate() {
		return expectedReturnDate;
	}
	public void setExpectedReturnDate(String expectedReturnDate) {
		this.expectedReturnDate = expectedReturnDate;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getReturnByName() {
		return returnByName;
	}
	public void setReturnByName(String returnByName) {
		this.returnByName = returnByName;
	}
	
}
