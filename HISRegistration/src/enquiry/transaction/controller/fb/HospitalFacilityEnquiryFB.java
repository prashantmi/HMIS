package enquiry.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class HospitalFacilityEnquiryFB extends ActionForm {
	

	private String facilityId;
	private String facilityName;
	private String slNo;
	private String facilityDescType;
	private String facilityDesc;
	private String facilityDescMultiple;
	private String isEmergencyService;
	private String displayOrder;
	private String isLocationSpecific;
	private String isValid;
	private String locationQuery;
	private String selectedFacilityId;
	private String [] desc;
	private String descLen;
	private String hmode;
	private int noOfRows=0;
	private String isDirectCall;

	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		
		super.reset(mapping, request);
		this.facilityId="";
		this.facilityName="";
		this.slNo="";
		this.facilityDescType="";
		this.facilityDesc="";
		this.facilityDescMultiple="";
		this.noOfRows=0;
	}


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


	public String getHmode() {
		return hmode;
	}


	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String getSelectedFacilityId() {
		return selectedFacilityId;
	}


	public void setSelectedFacilityId(String selectedFacilityId) {
		this.selectedFacilityId = selectedFacilityId;
	}


	public String getFacilityDescMultiple() {
		return facilityDescMultiple;
	}


	public void setFacilityDescMultiple(String facilityDescMultiple) {
		this.facilityDescMultiple = facilityDescMultiple;
	}


	public int getNoOfRows() {
		return noOfRows;
	}


	public void setNoOfRows(int noOfRows) {
		this.noOfRows = noOfRows;
	}


	public String[] getDesc() {
		return desc;
	}


	public void setDesc(String[] desc) {
		this.desc = desc;
	}


	public String getDescLen() {
		return descLen;
	}


	public void setDescLen(String descLen) {
		this.descLen = descLen;
	}


	public String getIsDirectCall() {
		return isDirectCall;
	}


	public void setIsDirectCall(String isDirectCall) {
		this.isDirectCall = isDirectCall;
	}
	
		
}
