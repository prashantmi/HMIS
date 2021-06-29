package hisglobal.vo;

public class RecordBoundingVO extends ValueObject
{
	private String boundingMode;
	private String recordType;
	private String boundingId;
	private String slNo;
	private String hospitalCode;
	
	
	public String getBoundingMode() {
		return boundingMode;
	}
	public void setBoundingMode(String boundingMode) {
		this.boundingMode = boundingMode;
	}
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	public String getBoundingId() {
		return boundingId;
	}
	public void setBoundingId(String boundingId) {
		this.boundingId = boundingId;
	}
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	
	
}
