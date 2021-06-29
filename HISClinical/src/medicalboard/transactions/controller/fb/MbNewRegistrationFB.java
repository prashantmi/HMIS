package medicalboard.transactions.controller.fb;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import registration.RegistrationConfig;

public class MbNewRegistrationFB extends ActionForm{

	 private String hmode;
	 private String certificateTypeID; 
	 private String patCrNo;
	 private String patPrimaryCat;
	 private String patPrimaryCatCode;
	 private String patFirstName;
	 private String patMiddleName;
	 private String patLastName;
	 private String patGender;
     private String patGenderCode;
	 private String patAge;
	 private String patDOB="";
	 private String isActualDob;

	 private String patGuardianName;
	 private String patMotherName;
	 private String spouseName;
	 private String patNationalityCode;
	 private String patNationality;
	 private String patDesignation;
     private String registrationFee;
     private String patAddCountryCode;
     private String patAddCityLocCode;
     private String patAddCityLoc;
     private String patAddHNo;
     private String patAddStreet;
     private String patAddDistrictCode;
     private String patAddDistrict;
     private String patAddCity;
     private String patAddPIN;
     private String patAddContactNo;
     private String patIsUrban;
     private String patAgeUnit;
	 private String patAddStateCode;
	 private String patAddStateName;
	 private String patMaritalStatusCode;
     
	private String patRegCatCode;
	private String entryDate;
	private String isUnknown;
	private String seatId="1";
	private String patAddTypeCode;
	private String isCurrentAddress;	
    private String crNo;
	
	 // for Search
	 
	 
	 private String reqID;
	 private String slNo;
	 private String requestFrom;
	 private String designation;
	 private String orgID;
	 private String orgType;
	 private String orgTypeID;
	 private String orgName;
	 private String orgAddress;
	 private String reqStatus;
	 private String examDate;
	 private String approvedDate;
	 private String approvedBy;
	 //private String boardID;
	 private String hospitalCode;
	 private String isValid;
	 private String seatID;

	 private String departmentCode;
	 private String departmentUnit;
	 private String departmentUnitCode;
	 private String room;
	 private String roomCode;
	 private String queNo;
	 private String episodeCode;
	 private String episodeVisitNo;
	 private String patAmountCollected;
	 private String isAddressDelhi;
	 private String[] selectedCheckListId;
	 private String isCompulsoryArray;
	 private String[] checkListIdArray;
	 private String[] checkByIdArray;
	 private String[] remarks;
	
	 private String boardTypeID;
	 private String weekDay;
	 private String minRequest;
	 private String maxRequest;
 
	 
	 public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getCertificateTypeID() {
		return certificateTypeID;
	}
	public void setCertificateTypeID(String certificateTypeID) {
		this.certificateTypeID = certificateTypeID;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getPatPrimaryCat() {
		return patPrimaryCat;
	}
	public void setPatPrimaryCat(String patPrimaryCat) {
		this.patPrimaryCat = patPrimaryCat;
	}
	public String getPatPrimaryCatCode() {
		return patPrimaryCatCode;
	}
	public void setPatPrimaryCatCode(String patPrimaryCatCode) {
		this.patPrimaryCatCode = patPrimaryCatCode;
	}
	public String getPatFirstName() {
		return patFirstName;
	}
	public void setPatFirstName(String patFirstName) {
		this.patFirstName = patFirstName;
	}
	public String getPatMiddleName() {
		return patMiddleName;
	}
	public void setPatMiddleName(String patMiddleName) {
		this.patMiddleName = patMiddleName;
	}
	public String getPatLastName() {
		return patLastName;
	}
	public void setPatLastName(String patLastName) {
		this.patLastName = patLastName;
	}
	public String getPatGender() {
		return patGender;
	}
	public void setPatGender(String patGender) {
		this.patGender = patGender;
	}
	public String getPatGenderCode() {
		return patGenderCode;
	}
	public void setPatGenderCode(String patGenderCode) {
		this.patGenderCode = patGenderCode;
	}
	public String getPatAge() {
		return patAge;
	}
	public void setPatAge(String patAge) {
		this.patAge = patAge;
	}
	public String getPatDOB() {
		return patDOB;
	}
	public void setPatDOB(String patDOB) {
		this.patDOB = patDOB;
	}
	public String getIsActualDob() {
		return isActualDob;
	}
	public void setIsActualDob(String isActualDob) {
		this.isActualDob = isActualDob;
	}
	public String getReqID() {
		return reqID;
	}
	public void setReqID(String reqID) {
		this.reqID = reqID;
	}
	public String getRequestFrom() {
		return requestFrom;
	}
	public void setRequestFrom(String requestFrom) {
		this.requestFrom = requestFrom;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getOrgID() {
		return orgID;
	}
	public void setOrgID(String orgID) {
		this.orgID = orgID;
	}
	public String getOrgType() {
		return orgType;
	}
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgAddress() {
		return orgAddress;
	}
	public void setOrgAddress(String orgAddress) {
		this.orgAddress = orgAddress;
	}
	public String getReqStatus() {
		return reqStatus;
	}
	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
	}
	public String getExamDate() {
		return examDate;
	}
	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}
	public String getApprovedDate() {
		return approvedDate;
	}
	public void setApprovedDate(String approvedDate) {
		this.approvedDate = approvedDate;
	}
	public String getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getSeatID() {
		return seatID;
	}
	public void setSeatID(String seatID) {
		this.seatID = seatID;
	}

	public String getPatGuardianName() {
		return patGuardianName;
	}
	public void setPatGuardianName(String patGuardianName) {
		this.patGuardianName = patGuardianName;
	}
	public String getSpouseName() {
		return spouseName;
	}
	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}
	public String getPatNationalityCode() {
		return patNationalityCode;
	}
	public void setPatNationalityCode(String patNationalityCode) {
		this.patNationalityCode = patNationalityCode;
	}
	public String getPatNationality() {
		return patNationality;
	}
	public void setPatNationality(String patNationality) {
		this.patNationality = patNationality;
	}
	public String getPatDesignation() {
		return patDesignation;
	}
	public void setPatDesignation(String patDesignation) {
		this.patDesignation = patDesignation;
	}
	public String getRegistrationFee() {
		return registrationFee;
	}
	public void setRegistrationFee(String registrationFee) {
		this.registrationFee = registrationFee;
	}
	public String getPatAddCountryCode() {
		return patAddCountryCode;
	}
	public void setPatAddCountryCode(String patAddCountryCode) {
		this.patAddCountryCode = patAddCountryCode;
	}
	public String getPatAddCityLocCode() {
		return patAddCityLocCode;
	}
	public void setPatAddCityLocCode(String patAddCityLocCode) {
		this.patAddCityLocCode = patAddCityLocCode;
	}
	public String getPatAddCityLoc() {
		return patAddCityLoc;
	}
	public void setPatAddCityLoc(String patAddCityLoc) {
		this.patAddCityLoc = patAddCityLoc;
	}
	public String getPatAddHNo() {
		return patAddHNo;
	}
	public void setPatAddHNo(String patAddHNo) {
		this.patAddHNo = patAddHNo;
	}
	public String getPatAddStreet() {
		return patAddStreet;
	}
	public void setPatAddStreet(String patAddStreet) {
		this.patAddStreet = patAddStreet;
	}
	public String getPatAddDistrictCode() {
		return patAddDistrictCode;
	}
	public void setPatAddDistrictCode(String patAddDistrictCode) {
		this.patAddDistrictCode = patAddDistrictCode;
	}
	public String getPatAddCity() {
		return patAddCity;
	}
	public void setPatAddCity(String patAddCity) {
		this.patAddCity = patAddCity;
	}
	public String getPatAddPIN() {
		return patAddPIN;
	}
	public void setPatAddPIN(String patAddPIN) {
		this.patAddPIN = patAddPIN;
	}
	public String getPatAddContactNo() {
		return patAddContactNo;
	}
	public void setPatAddContactNo(String patAddContactNo) {
		this.patAddContactNo = patAddContactNo;
	}
	public String getPatIsUrban() {
		return patIsUrban;
	}
	public void setPatIsUrban(String patIsUrban) {
		this.patIsUrban = patIsUrban;
	}
	public String getPatAgeUnit() {
		return patAgeUnit;
	}
	public void setPatAgeUnit(String patAgeUnit) {
		this.patAgeUnit = patAgeUnit;
	}
	public String getPatAddStateCode() {
		return patAddStateCode;
	}
	public void setPatAddStateCode(String patAddStateCode) {
		this.patAddStateCode = patAddStateCode;
	}
	public String getPatAddStateName() {
		return patAddStateName;
	}
	public void setPatAddStateName(String patAddStateName) {
		this.patAddStateName = patAddStateName;
	}
	public String getPatAddDistrict() {
		return patAddDistrict;
	}
	public void setPatAddDistrict(String patAddDistrict) {
		this.patAddDistrict = patAddDistrict;
	}
	public String getPatMotherName() {
		return patMotherName;
	}
	public void setPatMotherName(String patMotherName) {
		this.patMotherName = patMotherName;
	}
	public String getPatMaritalStatusCode() {
		return patMaritalStatusCode;
	}
	public void setPatMaritalStatusCode(String patMaritalStatusCode) {
		this.patMaritalStatusCode = patMaritalStatusCode;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getDepartmentUnit() {
		return departmentUnit;
	}
	public void setDepartmentUnit(String departmentUnit) {
		this.departmentUnit = departmentUnit;
	}
	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}
	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getRoomCode() {
		return roomCode;
	}
	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}
	public String getQueNo() {
		return queNo;
	}
	public void setQueNo(String queNo) {
		this.queNo = queNo;
	}
	public String getEpisodeCode() {
		return episodeCode;
	}
	public void setEpisodeCode(String episodeCode) {
		this.episodeCode = episodeCode;
	}
	public String getEpisodeVisitNo() {
		return episodeVisitNo;
	}
	public void setEpisodeVisitNo(String episodeVisitNo) {
		this.episodeVisitNo = episodeVisitNo;
	}
	public String getPatAmountCollected() {
		return patAmountCollected;
	}
	public void setPatAmountCollected(String patAmountCollected) {
		this.patAmountCollected = patAmountCollected;
	}
	public String getIsAddressDelhi() {
		return isAddressDelhi;
	}
	public void setIsAddressDelhi(String isAddressDelhi) {
		this.isAddressDelhi = isAddressDelhi;
	}

	public String getOrgTypeID() {
		return orgTypeID;
	}
	public void setOrgTypeID(String orgTypeID) {
		this.orgTypeID = orgTypeID;
	}

	public String getCrNo() {
		return crNo;
	}

	public void setCrNo(String crNo) {
		this.crNo = crNo;
	}

	public String getIsCompulsoryArray() {
		return isCompulsoryArray;
	}

	public void setIsCompulsoryArray(String isCompulsoryArray) {
		this.isCompulsoryArray = isCompulsoryArray;
	}



	 
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setPatCrNo("            ");
		this.setPatPrimaryCatCode("");
		this.setPatMaritalStatusCode("");
		this.setPatFirstName("");
		this.setPatMiddleName("");
		this.setPatLastName("");
		this.setPatGenderCode("");
		this.setPatAge("");
		this.setPatDOB("");
		this.setIsActualDob("0"); 
		this.setPatIsUrban("");
		this.setPatAmountCollected("");
		this.setPatGuardianName("");
		this.setPatRegCatCode("");
		this.setIsUnknown("");
		this.setSeatId("");
		this.setPatAddTypeCode(RegistrationConfig.REGISTRATIONDESK_DEFAULT_ADDRESS_TYPE_CODE);
		this.setPatAddCityLoc("");
		this.setPatAddCityLocCode("");
		this.setPatAddStateCode(RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE);
		this.setPatAddCountryCode("");
		this.setPatAddHNo("");
		this.setPatAddStreet("");
		this.setPatAddDistrict("");
		this.setPatAddCity("");
		this.setPatAddPIN("");
		this.setPatAddContactNo("");
		this.setIsAddressDelhi("1");
		this.setIsCurrentAddress(RegistrationConfig.IS_ADDRESS_DELHI_TRUE);
		//this.setEpisodeType("");
		this.setHmode("");
		this.setDepartmentCode("");
		this.setPatNationalityCode(RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE);
		this.setPatMotherName("");
		this.setPatAddStateName("");
		this.setPatMotherName("");
		this.setPatAddDistrictCode("");
		this.requestFrom="1";
	}
	
	
	public String getPatRegCatCode() {
		return patRegCatCode;
	}
	public void setPatRegCatCode(String patRegCatCode) {
		this.patRegCatCode = patRegCatCode;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getIsUnknown() {
		return isUnknown;
	}
	public void setIsUnknown(String isUnknown) {
		this.isUnknown = isUnknown;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getPatAddTypeCode() {
		return patAddTypeCode;
	}
	public void setPatAddTypeCode(String patAddTypeCode) {
		this.patAddTypeCode = patAddTypeCode;
	}
	public String getIsCurrentAddress() {
		return isCurrentAddress;
	}
	public void setIsCurrentAddress(String isCurrentAddress) {
		this.isCurrentAddress = isCurrentAddress;
	}

	public String[] getCheckListIdArray() {
		return checkListIdArray;
	}

	public void setCheckListIdArray(String[] checkListIdArray) {
		this.checkListIdArray = checkListIdArray;
	}

	public String[] getCheckByIdArray() {
		return checkByIdArray;
	}

	public void setCheckByIdArray(String[] checkByIdArray) {
		this.checkByIdArray = checkByIdArray;
	}

	public String[] getRemarks() {
		return remarks;
	}

	public void setRemarks(String[] remarks) {
		this.remarks = remarks;
	}

	public String getBoardTypeID() {
		return boardTypeID;
	}

	public void setBoardTypeID(String boardTypeID) {
		this.boardTypeID = boardTypeID;
	}

	public String getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}

	public String getMinRequest() {
		return minRequest;
	}

	public void setMinRequest(String minRequest) {
		this.minRequest = minRequest;
	}

	public String getMaxRequest() {
		return maxRequest;
	}

	public void setMaxRequest(String maxRequest) {
		this.maxRequest = maxRequest;
	}

	public String getSlNo() {
		return slNo;
	}

	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String[] getSelectedCheckListId() {
		return selectedCheckListId;
	}
	public void setSelectedCheckListId(String[] selectedCheckListId) {
		this.selectedCheckListId = selectedCheckListId;
	}


	
	
}
