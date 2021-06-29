package mrd.masters.controller.fb;

import org.apache.struts.action.ActionForm;

public class RecordBoundingFB extends ActionForm
{
	private String boundingMode;
	private String[] recordType;
	private String boundingId;
	private String slNo;
	private String hospitalCode;
	private String hmode;
	private String mrdCode;
	private String mrdCodeRack;
	private String rackId;
	private String shelfId;
	private String mrdBoundedRecType;
	private String mrdCodeShelf;
	private String rackIdShelf;
	private String isRecordTypeExist;
	
	
	
	public String getBoundingMode() {
		return boundingMode;
	}
	public void setBoundingMode(String boundingMode) {
		this.boundingMode = boundingMode;
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
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getMrdCode() {
		return mrdCode;
	}
	public void setMrdCode(String mrdCode) {
		this.mrdCode = mrdCode;
	}
	public String getRackId() {
		return rackId;
	}
	public void setRackId(String rackId) {
		this.rackId = rackId;
	}
	public String getShelfId() {
		return shelfId;
	}
	public void setShelfId(String shelfId) {
		this.shelfId = shelfId;
	}
	public String[] getRecordType() {
		return recordType;
	}
	public void setRecordType(String[] recordType) {
		this.recordType = recordType;
	}
	public String getMrdBoundedRecType() {
		return mrdBoundedRecType;
	}
	public void setMrdBoundedRecType(String mrdBoundedRecType) {
		this.mrdBoundedRecType = mrdBoundedRecType;
	}
	public String getMrdCodeRack() {
		return mrdCodeRack;
	}
	public void setMrdCodeRack(String mrdCodeRack) {
		this.mrdCodeRack = mrdCodeRack;
	}
	public String getMrdCodeShelf() {
		return mrdCodeShelf;
	}
	public void setMrdCodeShelf(String mrdCodeShelf) {
		this.mrdCodeShelf = mrdCodeShelf;
	}
	public String getRackIdShelf() {
		return rackIdShelf;
	}
	public void setRackIdShelf(String rackIdShelf) {
		this.rackIdShelf = rackIdShelf;
	}
	public String getIsRecordTypeExist() {
		return isRecordTypeExist;
	}
	public void setIsRecordTypeExist(String isRecordTypeExist) {
		this.isRecordTypeExist = isRecordTypeExist;
	}
}
