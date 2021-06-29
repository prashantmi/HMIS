package hisglobal.vo;

public class RecordEnclosureDtlVO extends ValueObject
{
	private String mrdRecordId;
	private String slNo;
	private String enclosureId;
	private String pages;
	private String isLost;
	private String remarks;
	
	
	public String getMrdRecordId() {
		return mrdRecordId;
	}
	public void setMrdRecordId(String mrdRecordId) {
		this.mrdRecordId = mrdRecordId;
	}
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getEnclosureId() {
		return enclosureId;
	}
	public void setEnclosureId(String enclosureId) {
		this.enclosureId = enclosureId;
	}
	public String getPages() {
		return pages;
	}
	public void setPages(String pages) {
		this.pages = pages;
	}
	public String getIsLost() {
		return isLost;
	}
	public void setIsLost(String isLost) {
		this.isLost = isLost;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
