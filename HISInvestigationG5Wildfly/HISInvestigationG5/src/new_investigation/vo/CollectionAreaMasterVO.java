package new_investigation.vo;

import hisglobal.vo.ValueObject;

public class CollectionAreaMasterVO extends ValueObject 
{

	private String collectionareaName;
	private String collectionareaCode;
	private String collectionareaType;
	private String remarks;
	private String locationCode;
	private String hospitalCode;
	private String wardCode;
	private String hmode;

	private String isActive;
	private String sopbased;


	public String getSopbased() {
		return sopbased;
	}
	public void setSopbased(String sopbased) {
		this.sopbased = sopbased;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public String getCollectionareaName() {
		return collectionareaName;
	}
	public void setCollectionareaName(String collectionareaName) {
		this.collectionareaName = collectionareaName;
	}
	public String getCollectionareaCode() {
		return collectionareaCode;
	}
	public void setCollectionareaCode(String collectionareaCode) {
		this.collectionareaCode = collectionareaCode;
	}
	public String getCollectionareaType() {
		return collectionareaType;
	}
	public void setCollectionareaType(String collectionareaType) {
		this.collectionareaType = collectionareaType;
	}
	public String getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getWardCode() {
		return wardCode;
	}
	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

}
