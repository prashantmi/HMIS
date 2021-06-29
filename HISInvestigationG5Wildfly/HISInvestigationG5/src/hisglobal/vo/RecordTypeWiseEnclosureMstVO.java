package hisglobal.vo;

public class RecordTypeWiseEnclosureMstVO extends ValueObject
{
private String serialNo;
	
	private String dispatchId;
	private String enclosureId;
	private String recordTypeId;
	private String recordTypeName;
	private String isCompulsoryLabel;
	private String isCompulsory;
	private String remarks;
	private String enclosure;
	private String hospitalCode;
	private String isValid;
	private String pages;
	private String slNo;
	private String recordId;
	private String verifiedByMrd;	
	private String verifiedByPeon;
	private String displayOrder;	
	
	public String getVerifiedByMrd() {
		return verifiedByMrd;
	}

	public void setVerifiedByMrd(String verifiedByMrd) {
		this.verifiedByMrd = verifiedByMrd;
	}

	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getEnclosureId() {
		return enclosureId;
	}

	public void setEnclosureId(String enclosureId) {
		this.enclosureId = enclosureId;
	}

	public String getRecordTypeId() {
		return recordTypeId;
	}

	public void setRecordTypeId(String recordTypeId) {
		this.recordTypeId = recordTypeId;
	}

	public String getRecordTypeName() {
		return recordTypeName;
	}

	public void setRecordTypeName(String recordTypeName) {
		this.recordTypeName = recordTypeName;
	}

	public String getIsCompulsory() {
		return isCompulsory;
	}

	public void setIsCompulsory(String isCompulsory) {
		this.isCompulsory = isCompulsory;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getEnclosure() {
		return enclosure;
	}

	public void setEnclosure(String enclosure) {
		this.enclosure = enclosure;
	}

	public String getSlNo() {
		return slNo;
	}

	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getDispatchId() {
		return dispatchId;
	}

	public void setDispatchId(String dispatchId) {
		this.dispatchId = dispatchId;
	}

	public String getVerifiedByPeon() {
		return verifiedByPeon;
	}

	public void setVerifiedByPeon(String verifiedByPeon) {
		this.verifiedByPeon = verifiedByPeon;
	}

	public String getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getIsCompulsoryLabel() {
		return isCompulsoryLabel;
	}

	public void setIsCompulsoryLabel(String isCompulsoryLabel) {
		this.isCompulsoryLabel = isCompulsoryLabel;
	}
}
