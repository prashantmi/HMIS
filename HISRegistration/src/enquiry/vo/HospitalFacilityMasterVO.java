package enquiry.vo;

import hisglobal.vo.ValueObject;

public class HospitalFacilityMasterVO extends ValueObject {

	private String facilityId;
	private String facilityName;
	private String slNo;
	private String facilityDescType;
	private String facilityDesc;
	private String isEmergencyService;
	private String displayOrder;
	private String isLocationSpecific;
	private String isValid;
	private String locationQuery;
	
	public String getFacilityId() {
		return facilityId;
	}
	public void setFacilityId(String facilityId) {
		this.facilityId = facilityId;
	}
	public String getFacilityName() {
		return facilityName;
	}
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getFacilityDescType() {
		return facilityDescType;
	}
	public void setFacilityDescType(String facilityDescType) {
		this.facilityDescType = facilityDescType;
	}
	public String getFacilityDesc() {
		return facilityDesc;
	}
	public void setFacilityDesc(String facilityDesc) {
		this.facilityDesc = facilityDesc;
	}
	public String getIsEmergencyService() {
		return isEmergencyService;
	}
	public void setIsEmergencyService(String isEmergencyService) {
		this.isEmergencyService = isEmergencyService;
	}
	public String getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}
	public String getIsLocationSpecific() {
		return isLocationSpecific;
	}
	public void setIsLocationSpecific(String isLocationSpecific) {
		this.isLocationSpecific = isLocationSpecific;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getLocationQuery() {
		return locationQuery;
	}
	public void setLocationQuery(String locationQuery) {
		this.locationQuery = locationQuery;
	}
	
	
}
