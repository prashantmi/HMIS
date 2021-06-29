package ipd.transactions;

import javax.sql.rowset.WebRowSet;

import hisglobal.utility.TransferObject;

public class PatientAdmissionTransVO implements TransferObject {

	private static final long serialVersionUID = 02L;
	private String strIsMLC="";
	private String strMLCNo="";
	private String strIsNewBorn="";
	private String strMotherDtl="";
	private String strMsgString = "";
	private String strMsgType = "0";
	private String strHiddenPatDtl="";
	private String strDeptUnitName="";
	private String strCrNo = "";
	private String strDeptName="";
	private String strUnitName="";
	private String strWardTypeCode="0";
	private String strWardCode="0";
	private String strBedTypeCode="0";
	private String strRoomTypeCode="0";
	private String strWardName="";
	private String strDeptCode="";
	private String strDeptUnitCode="";
	private String strRoom="";
	private String strTreatmentCategoryName="";
	private String strPatCatCode="";
	private String strPatStatusCode="";
	private String strConsultantCode="";
	private String strConsultantName="";
	private String strOccRelation="";
	private String strAdmNo="";
	private String strEpisodeCode="";
	private String strVisitNo="";
	private String strMlcNo="";
	private String strIsUrban="";
	private String strAdviceAdmNo="";
	private String strRoomCode="0";
	private String strOccEmpName="";
	private String strTreatmentCategoryCode="";
	private String strNewBorn="";
	private String strBedCode="0";
	private String strBed="";
	private String strOccEmpNo="";
	private String strOccIsGovtEmp="";
	private String strOccBasic="";
	private String strOccDesc="";
	private String strOccOffName="";
	private String strOccAdd1="";
	private String strOccOrgType="";
	private String strOccAdd2="";
	private String strOccCity="";
	private String strOccState="";
	private String strOccOffPhNo="";
	private String strRemarks="";
	private String strFatherName="";
	private String strReligion="";
	private String strAddress="";
	private String strState="";
	private String strCity="";
	private String strStateCode="0";
	private String strReligionCode="0";
	private String strBookingDate="";
	private String strAddress2="";
	private String strHouseNo="";
	private String strStreet="";
	private String strDistrict="";
	private String strPhoneNo="";
	private String strMobileNo="";
	private String strVacantBed="";
	private String strMsApprovalFlag="0";
	private String strCountry="";
	private String strCountryCode="0";
	private String strPinCode="";
	private String strMsApprovalStatus="0";
	private String strMotherCrNo="";
	private String strMotherAdmissionNo="";
	private String strMotherName="";
	private String strMotherNationalityCode="";
	private String strMotherNationality="";
	private String strMotherReligionCode="";
	private String strMotherReligion="";
	private String strAdmissionCharge="";
	private String strAdmissionChargeValue="";
	private String strAdviceStatus="";
	private String strHospCode="";
	private String strSeatId="";
	private String strIpAddress="";
	private String strAdmissionAdviceValidityTo="0";
	private String strAdmissionAdviceValidityFrom="0";
	private String strNoOfFreePass="0";
	private String strFreePassValid="0";
	private String strCrNoValid="0";
	private String strIsAdmissionOnline="1";
	private String strCurrentDate="";
	private String strSpouseName="";
	private String strIsIntegratedWithBilling="0";
	private String strIsAdvanceAmountAtAdmission="0";
	private String strPrimaryCategoryCode="";
	private String strIsAdvanceAmountAtAdmissionTaken="";
	private String strDobTime;
	private String strBabyName;
	private String strGenderName;
	private String strStateName="";
	private String strCountryName="";
	private String strBabyDeptUntName="";
	private String strAdmDateTime="";
	private String strPatientCaste="";
	private String strTehsil="";
	private String strMaritalStatus="";
	private String strAdmissionType="";
	private String strReliefFund="";
	private String strIdMark="";
	private String strCityLocation="";

		// Advanced Amount
	private String strAdvanceAmountDate="";
	private String strAdvanceAmountReceiptNo="";
	private String strAdvanceAmount="";
	
	private String strAgeUnit="0";
	private String strSexCode="0";
	private String strAge="0";
	private WebRowSet wardTypeWS=null;
	private WebRowSet roomTypeWS=null;
	private WebRowSet wardWS=null;
	private WebRowSet roomWs=null;
	private WebRowSet bedTypeWS=null;
	private WebRowSet bedDetailWS=null;
	private WebRowSet patientDetailWS=null;
	private WebRowSet religionWs=null;
	private WebRowSet stateWS=null;
	private WebRowSet countryWS=null;
	private WebRowSet departWS=null;
	private WebRowSet unitWS=null;
	private WebRowSet treatmentCategWS=null;
	private WebRowSet consultantWS=null;
	private WebRowSet wrsPatientCaste;
	private WebRowSet wrsMaritalStatus;
	private WebRowSet wrsAdmissionTypeValues;
	private WebRowSet wrsReliefFundValues;
	private WebRowSet StrRelationWs;
	
	private String strPatName="";
	private String strHusbandName="";
	private String strNationality="";
	private String strMonthlyIncome="";
	private String strHospDtl="";
	private String strSecondPersonName="";
	private String strSecondPersonRelationCode="";
	private String strFirstPersonName="";
	private String strFirstPersonRelationCode="";
	private String strEmgAddress1="";
	private String strEmgAddress2="";
	private String strEmgAddress="";
	private String strIsDead="0";
	private String strPatIsUnknown="";
	private String strPatCategoryName="";
	private String strEmgAddressFlag="0";
	private String strPatientIdNumber="";
	private String strPatientAdhaarNo="";
	private String strFirstpersonphone="";
	private String strSecondpersonphone="";
	private String strAdmissionChargeAtCounter="";
	private String strBillNo="";
	
	private WebRowSet strPatAdmBillingSlip=null;
	
	private String strPatCatGrp="";
	
	private String strIsCreditAdvanceBilling="0";
	private String strCreditLetterRefNo="";
	private String strCreditLetterDate="";
	private String strClientName="";
	private String strEligibleAmount="";
	private String strStaffName="";
	private String strRelationship="";
	private String strStaffNo="";
	private String strWAPCardNo="";
	private String strCodeNo="";
	private String strCaseSheetNo="";
	private String strDetPs="";
	private String strPolInfo="";
	private String strNamInf="";
	private String strIdenMark="";
	private String strRefRem="";
    private String strHospName="";
    private String strDistrictCode="0";
	private WebRowSet districtWS=null;
	private String disDateTime="";
	private String daysStay="";
	private String admissionTypeName="";
	private String strMsAppStatus="";
	private String billcount="0";
	private String strRegChg="";
	private String strAdvanceAmountVal="0";
	private String strIsSingleWindowAdmission="0";
	private String strDeptWardChangeChk="0";
	private WebRowSet strPaymentMode=null;
	private String strPaymentModeValue="";
	private String strPayDetail="";
	private String strActiveWallet="";
	private String strDormantAcc="";


	public String getDisDateTime() {
		return disDateTime;
	}
	public void setDisDateTime(String disDateTime) {
		this.disDateTime = disDateTime;
	}
	public String getDaysStay() {
		return daysStay;
	}
	public void setDaysStay(String daysStay) {
		this.daysStay = daysStay;
	}
	public String getStrCaseSheetNo() {
		return strCaseSheetNo;
	}
	public void setStrCaseSheetNo(String strCaseSheetNo) {
		this.strCaseSheetNo = strCaseSheetNo;
	}
	public String getStrDetPs() {
		return strDetPs;
	}
	public void setStrDetPs(String strDetPs) {
		this.strDetPs = strDetPs;
	}
	public String getStrPolInfo() {
		return strPolInfo;
	}
	public void setStrPolInfo(String strPolInfo) {
		this.strPolInfo = strPolInfo;
	}
	public String getStrNamInf() {
		return strNamInf;
	}
	public void setStrNamInf(String strNamInf) {
		this.strNamInf = strNamInf;
	}
	public String getStrIdenMark() {
		return strIdenMark;
	}
	public void setStrIdenMark(String strIdenMark) {
		this.strIdenMark = strIdenMark;
	}
	public String getStrRefRem() {
		return strRefRem;
	}
	public void setStrRefRem(String strRefRem) {
		this.strRefRem = strRefRem;
	}
	public String getStrHospName() {
		return strHospName;
	}
	public void setStrHospName(String strHospName) {
		this.strHospName = strHospName;
	}
	public String getStrPatCatGrp() {
		return strPatCatGrp;
	}
	public void setStrPatCatGrp(String strPatCatGrp) {
		this.strPatCatGrp = strPatCatGrp;
	}
	public WebRowSet getStrPatAdmBillingSlip() {
		return strPatAdmBillingSlip;
	}
	public void setStrPatAdmBillingSlip(WebRowSet strPatAdmBillingSlip) {
		this.strPatAdmBillingSlip = strPatAdmBillingSlip;
	}
	public String getStrFirstpersonphone() {
		return strFirstpersonphone;
	}
	public void setStrFirstpersonphone(String strFirstpersonphone) {
		this.strFirstpersonphone = strFirstpersonphone;
	}
	public String getStrSecondpersonphone() {
		return strSecondpersonphone;
	}
	public void setStrSecondpersonphone(String strSecondpersonphone) {
		this.strSecondpersonphone = strSecondpersonphone;
	}
	public String getStrPatientIdNumber() {
		return strPatientIdNumber;
	}
	public void setStrPatientIdNumber(String strPatientIdNumber) {
		this.strPatientIdNumber = strPatientIdNumber;
	}
	public String getStrPatientAdhaarNo() {
		return strPatientAdhaarNo;
	}
	public void setStrPatientAdhaarNo(String strPatientAdhaarNo) {
		this.strPatientAdhaarNo = strPatientAdhaarNo;
	}
	public String getStrEmgAddressFlag() {
		return strEmgAddressFlag;
	}
	public void setStrEmgAddressFlag(String strEmgAddressFlag) {
		this.strEmgAddressFlag = strEmgAddressFlag;
	}
	public String getStrEmgAddress() {
		return strEmgAddress;
	}
	public void setStrEmgAddress(String strEmgAddress) {
		this.strEmgAddress = strEmgAddress;
	}
	public String getStrEmgAddress1() {
		return strEmgAddress1;
	}
	public void setStrEmgAddress1(String strEmgAddress1) {
		this.strEmgAddress1 = strEmgAddress1;
	}
	public String getStrEmgAddress2() {
		return strEmgAddress2;
	}
	public void setStrEmgAddress2(String strEmgAddress2) {
		this.strEmgAddress2 = strEmgAddress2;
	}
	public String getStrMsgString() {
		return strMsgString;
	}
	public void setStrMsgString(String strMsgString) {
		this.strMsgString = strMsgString;
	}
	public String getStrMsgType() {
		return strMsgType;
	}
	public void setStrMsgType(String strMsgType) {
		this.strMsgType = strMsgType;
	}
	public String getStrCrNo() {
		return strCrNo;
	}
	public void setStrCrNo(String strCrNo) {
		this.strCrNo = strCrNo;
	}
	public String getStrDeptName() {
		return strDeptName;
	}
	public void setStrDeptName(String strDeptName) {
		this.strDeptName = strDeptName;
	}
	public String getStrUnitName() {
		return strUnitName;
	}
	public void setStrUnitName(String strUnitName) {
		this.strUnitName = strUnitName;
	}
	public WebRowSet getWardTypeWS() {
		return wardTypeWS;
	}
	public void setWardTypeWS(WebRowSet wardTypeWS) {
		this.wardTypeWS = wardTypeWS;
	}
	public WebRowSet getRoomTypeWS() {
		return roomTypeWS;
	}
	public void setRoomTypeWS(WebRowSet roomTypeWS) {
		this.roomTypeWS = roomTypeWS;
	}
	public String getStrWardTypeCode() {
		return strWardTypeCode;
	}
	public void setStrWardTypeCode(String strWardTypeCode) {
		this.strWardTypeCode = strWardTypeCode;
	}
	public String getStrWardCode() {
		return strWardCode;
	}
	public void setStrWardCode(String strWardCode) {
		this.strWardCode = strWardCode;
	}
	public String getStrBedTypeCode() {
		return strBedTypeCode;
	}
	public void setStrBedTypeCode(String strBedTypeCode) {
		this.strBedTypeCode = strBedTypeCode;
	}
	public String getStrRoomTypeCode() {
		return strRoomTypeCode;
	}
	public void setStrRoomTypeCode(String strRoomTypeCode) {
		this.strRoomTypeCode = strRoomTypeCode;
	}
	public String getStrWardName() {
		return strWardName;
	}
	public void setStrWardName(String strWardName) {
		this.strWardName = strWardName;
	}
	public String getStrDeptCode() {
		return strDeptCode;
	}
	public void setStrDeptCode(String strDeptCode) {
		this.strDeptCode = strDeptCode;
	}
	public WebRowSet getWardWS() {
		return wardWS;
	}
	public void setWardWS(WebRowSet wardWS) {
		this.wardWS = wardWS;
	}
	public WebRowSet getRoomWs() {
		return roomWs;
	}
	public void setRoomWs(WebRowSet roomWs) {
		this.roomWs = roomWs;
	}
	public WebRowSet getBedTypeWS() {
		return bedTypeWS;
	}
	public void setBedTypeWS(WebRowSet bedTypeWS) {
		this.bedTypeWS = bedTypeWS;
	}
	public String getStrRoom() {
		return strRoom;
	}
	public void setStrRoom(String strRoom) {
		this.strRoom = strRoom;
	}
	public WebRowSet getBedDetailWS() {
		return bedDetailWS;
	}
	public void setBedDetailWS(WebRowSet bedDetailWS) {
		this.bedDetailWS = bedDetailWS;
	}
	public String getStrTreatmentCategoryName() {
		return strTreatmentCategoryName;
	}
	public void setStrTreatmentCategoryName(String strTreatmentCategoryName) {
		this.strTreatmentCategoryName = strTreatmentCategoryName;
	}
	public WebRowSet getPatientDetailWS() {
		return patientDetailWS;
	}
	public void setPatientDetailWS(WebRowSet patientDetailWS) {
		this.patientDetailWS = patientDetailWS;
	}
	public String getStrPatCatCode() {
		return strPatCatCode;
	}
	public void setStrPatCatCode(String strPatCatCode) {
		this.strPatCatCode = strPatCatCode;
	}
	public String getStrDeptUnitCode() {
		return strDeptUnitCode;
	}
	public void setStrDeptUnitCode(String strDeptUnitCode) {
		this.strDeptUnitCode = strDeptUnitCode;
	}
	public String getStrConsultantCode() {
		return strConsultantCode;
	}
	public void setStrConsultantCode(String strConsultantCode) {
		this.strConsultantCode = strConsultantCode;
	}
	public String getStrConsultantName() {
		return strConsultantName;
	}
	public void setStrConsultantName(String strConsultantName) {
		this.strConsultantName = strConsultantName;
	}
	public String getStrOccRelation() {
		return strOccRelation;
	}
	public void setStrOccRelation(String strOccRelation) {
		this.strOccRelation = strOccRelation;
	}
	public String getStrAdmNo() {
		return strAdmNo;
	}
	public void setStrAdmNo(String strAdmNo) {
		this.strAdmNo = strAdmNo;
	}
	public String getStrEpisodeCode() {
		return strEpisodeCode;
	}
	public void setStrEpisodeCode(String strEpisodeCode) {
		this.strEpisodeCode = strEpisodeCode;
	}
	public String getStrVisitNo() {
		return strVisitNo;
	}
	public void setStrVisitNo(String strVisitNo) {
		this.strVisitNo = strVisitNo;
	}
	public String getStrMlcNo() {
		return strMlcNo;
	}
	public void setStrMlcNo(String strMlcNo) {
		this.strMlcNo = strMlcNo;
	}
	public String getStrAdviceAdmNo() {
		return strAdviceAdmNo;
	}
	public void setStrAdviceAdmNo(String strAdviceAdmNo) {
		this.strAdviceAdmNo = strAdviceAdmNo;
	}
	public String getStrRoomCode() {
		return strRoomCode;
	}
	public void setStrRoomCode(String strRoomCode) {
		this.strRoomCode = strRoomCode;
	}
	public String getStrTreatmentCategoryCode() {
		return strTreatmentCategoryCode;
	}
	public void setStrTreatmentCategoryCode(String strTreatmentCategoryCode) {
		this.strTreatmentCategoryCode = strTreatmentCategoryCode;
	}
	public String getStrNewBorn() {
		return strNewBorn;
	}
	public void setStrNewBorn(String strNewBorn) {
		this.strNewBorn = strNewBorn;
	}
	public String getStrIsUrban() {
		return strIsUrban;
	}
	public void setStrIsUrban(String strIsUrban) {
		this.strIsUrban = strIsUrban;
	}
	public String getStrBedCode() {
		return strBedCode;
	}
	public void setStrBedCode(String strBedCode) {
		this.strBedCode = strBedCode;
	}
	public String getStrOccEmpNo() {
		return strOccEmpNo;
	}
	public void setStrOccEmpNo(String strOccEmpNo) {
		this.strOccEmpNo = strOccEmpNo;
	}
	public String getStrOccEmpName() {
		return strOccEmpName;
	}
	public void setStrOccEmpName(String strOccEmpName) {
		this.strOccEmpName = strOccEmpName;
	}
	public String getStrOccIsGovtEmp() {
		return strOccIsGovtEmp;
	}
	public void setStrOccIsGovtEmp(String strOccIsGovtEmp) {
		this.strOccIsGovtEmp = strOccIsGovtEmp;
	}
	public String getStrOccBasic() {
		return strOccBasic;
	}
	public void setStrOccBasic(String strOccBasic) {
		this.strOccBasic = strOccBasic;
	}
	public String getStrOccDesc() {
		return strOccDesc;
	}
	public void setStrOccDesc(String strOccDesc) {
		this.strOccDesc = strOccDesc;
	}
	public String getStrOccOrgType() {
		return strOccOrgType;
	}
	public void setStrOccOrgType(String strOccOrgType) {
		this.strOccOrgType = strOccOrgType;
	}
	public String getStrOccOffName() {
		return strOccOffName;
	}
	public void setStrOccOffName(String strOccOffName) {
		this.strOccOffName = strOccOffName;
	}
	public String getStrOccAdd1() {
		return strOccAdd1;
	}
	public void setStrOccAdd1(String strOccAdd1) {
		this.strOccAdd1 = strOccAdd1;
	}
	public String getStrOccAdd2() {
		return strOccAdd2;
	}
	public void setStrOccAdd2(String strOccAdd2) {
		this.strOccAdd2 = strOccAdd2;
	}
	public String getStrOccCity() {
		return strOccCity;
	}
	public void setStrOccCity(String strOccCity) {
		this.strOccCity = strOccCity;
	}
	public String getStrOccState() {
		return strOccState;
	}
	public void setStrOccState(String strOccState) {
		this.strOccState = strOccState;
	}
	public String getStrOccOffPhNo() {
		return strOccOffPhNo;
	}
	public void setStrOccOffPhNo(String strOccOffPhNo) {
		this.strOccOffPhNo = strOccOffPhNo;
	}
	public String getStrRemarks() {
		return strRemarks;
	}
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}
	public String getStrFatherName() {
		return strFatherName;
	}
	public void setStrFatherName(String strFatherName) {
		this.strFatherName = strFatherName;
	}
	public String getStrReligion() {
		return strReligion;
	}
	public void setStrReligion(String strReligion) {
		this.strReligion = strReligion;
	}
	public String getStrAddress() {
		return strAddress;
	}
	public void setStrAddress(String strAddress) {
		this.strAddress = strAddress;
	}
	public String getStrCity() {
		return strCity;
	}
	public void setStrCity(String strCity) {
		this.strCity = strCity;
	}
	public String getStrState() {
		return strState;
	}
	public void setStrState(String strState) {
		this.strState = strState;
	}
	public String getStrReligionCode() {
		return strReligionCode;
	}
	public void setStrReligionCode(String strReligionCode) {
		this.strReligionCode = strReligionCode;
	}
	public String getStrStateCode() {
		return strStateCode;
	}
	public void setStrStateCode(String strStateCode) {
		this.strStateCode = strStateCode;
	}
	public WebRowSet getReligionWs() {
		return religionWs;
	}
	public void setReligionWs(WebRowSet religionWs) {
		this.religionWs = religionWs;
	}
	public WebRowSet getStateWS() {
		return stateWS;
	}
	public void setStateWS(WebRowSet stateWS) {
		this.stateWS = stateWS;
	}
	public String getStrBookingDate() {
		return strBookingDate;
	}
	public void setStrBookingDate(String strBookingDate) {
		this.strBookingDate = strBookingDate;
	}
	public String getStrAddress2() {
		return strAddress2;
	}
	public void setStrAddress2(String strAddress2) {
		this.strAddress2 = strAddress2;
	}
	public String getStrHouseNo() {
		return strHouseNo;
	}
	public void setStrHouseNo(String strHouseNo) {
		this.strHouseNo = strHouseNo;
	}
	public String getStrStreet() {
		return strStreet;
	}
	public void setStrStreet(String strStreet) {
		this.strStreet = strStreet;
	}
	public String getStrDistrict() {
		return strDistrict;
	}
	public void setStrDistrict(String strDistrict) {
		this.strDistrict = strDistrict;
	}
	public String getStrPhoneNo() {
		return strPhoneNo;
	}
	public void setStrPhoneNo(String strPhoneNo) {
		this.strPhoneNo = strPhoneNo;
	}
	public String getStrMobileNo() {
		return strMobileNo;
	}
	public void setStrMobileNo(String strMobileNo) {
		this.strMobileNo = strMobileNo;
	}
	public String getStrVacantBed() {
		return strVacantBed;
	}
	public void setStrVacantBed(String strVacantBed) {
		this.strVacantBed = strVacantBed;
	}
	public String getStrMsApprovalFlag() {
		return strMsApprovalFlag;
	}
	public void setStrMsApprovalFlag(String strMsApprovalFlag) {
		this.strMsApprovalFlag = strMsApprovalFlag;
	}
	public WebRowSet getCountryWS() {
		return countryWS;
	}
	public void setCountryWS(WebRowSet countryWS) {
		this.countryWS = countryWS;
	}
	public String getStrCountry() {
		return strCountry;
	}
	public void setStrCountry(String strCountry) {
		this.strCountry = strCountry;
	}
	public String getStrCountryCode() {
		return strCountryCode;
	}
	public void setStrCountryCode(String strCountryCode) {
		this.strCountryCode = strCountryCode;
	}
	public String getStrPinCode() {
		return strPinCode;
	}
	public void setStrPinCode(String strPinCode) {
		this.strPinCode = strPinCode;
	}
	public String getStrMsApprovalStatus() {
		return strMsApprovalStatus;
	}
	public void setStrMsApprovalStatus(String strMsApprovalStatus) {
		this.strMsApprovalStatus = strMsApprovalStatus;
	}
	public String getStrPatStatusCode() {
		return strPatStatusCode;
	}
	public void setStrPatStatusCode(String strPatStatusCode) {
		this.strPatStatusCode = strPatStatusCode;
	}
	public String getStrMotherCrNo() {
		return strMotherCrNo;
	}
	public void setStrMotherCrNo(String strMotherCrNo) {
		this.strMotherCrNo = strMotherCrNo;
	}
	public String getStrMotherAdmissionNo() {
		return strMotherAdmissionNo;
	}
	public void setStrMotherAdmissionNo(String strMotherAdmissionNo) {
		this.strMotherAdmissionNo = strMotherAdmissionNo;
	}
	public String getStrMotherName() {
		return strMotherName;
	}
	public void setStrMotherName(String strMotherName) {
		this.strMotherName = strMotherName;
	}
	public String getStrMotherNationalityCode() {
		return strMotherNationalityCode;
	}
	public void setStrMotherNationalityCode(String strMotherNationalityCode) {
		this.strMotherNationalityCode = strMotherNationalityCode;
	}
	public String getStrMotherNationality() {
		return strMotherNationality;
	}
	public void setStrMotherNationality(String strMotherNationality) {
		this.strMotherNationality = strMotherNationality;
	}
	public String getStrMotherReligionCode() {
		return strMotherReligionCode;
	}
	public void setStrMotherReligionCode(String strMotherReligionCode) {
		this.strMotherReligionCode = strMotherReligionCode;
	}
	public String getStrMotherReligion() {
		return strMotherReligion;
	}
	public void setStrMotherReligion(String strMotherReligion) {
		this.strMotherReligion = strMotherReligion;
	}
	public String getStrAdmissionCharge() {
		return strAdmissionCharge;
	}
	public void setStrAdmissionCharge(String strAdmissionCharge) {
		this.strAdmissionCharge = strAdmissionCharge;
	}
	public String getStrAdviceStatus() {
		return strAdviceStatus;
	}
	public void setStrAdviceStatus(String strAdviceStatus) {
		this.strAdviceStatus = strAdviceStatus;
	}
	public String getStrHospCode() {
		return strHospCode;
	}
	public void setStrHospCode(String strHospCode) {
		this.strHospCode = strHospCode;
	}
	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	public String getStrIpAddress() {
		return strIpAddress;
	}
	public void setStrIpAddress(String strIpAddress) {
		this.strIpAddress = strIpAddress;
	}
	public String getStrAdmissionAdviceValidityTo() {
		return strAdmissionAdviceValidityTo;
	}
	public void setStrAdmissionAdviceValidityTo(String strAdmissionAdviceValidityTo) {
		this.strAdmissionAdviceValidityTo = strAdmissionAdviceValidityTo;
	}
	public String getStrAdmissionAdviceValidityFrom() {
		return strAdmissionAdviceValidityFrom;
	}
	public void setStrAdmissionAdviceValidityFrom(
			String strAdmissionAdviceValidityFrom) {
		this.strAdmissionAdviceValidityFrom = strAdmissionAdviceValidityFrom;
	}
	public String getStrAdmissionChargeValue() {
		return strAdmissionChargeValue;
	}
	public void setStrAdmissionChargeValue(String strAdmissionChargeValue) {
		this.strAdmissionChargeValue = strAdmissionChargeValue;
	}
	public String getStrNoOfFreePass() {
		return strNoOfFreePass;
	}
	public void setStrNoOfFreePass(String strNoOfFreePass) {
		this.strNoOfFreePass = strNoOfFreePass;
	}
	public String getStrFreePassValid() {
		return strFreePassValid;
	}
	public void setStrFreePassValid(String strFreePassValid) {
		this.strFreePassValid = strFreePassValid;
	}
	public String getStrCrNoValid() {
		return strCrNoValid;
	}
	public void setStrCrNoValid(String strCrNoValid) {
		this.strCrNoValid = strCrNoValid;
	}
	public String getStrHiddenPatDtl() {
		return strHiddenPatDtl;
	}
	public void setStrHiddenPatDtl(String strHiddenPatDtl) {
		this.strHiddenPatDtl = strHiddenPatDtl;
	}
	public String getStrDeptUnitName() {
		return strDeptUnitName;
	}
	public void setStrDeptUnitName(String strDeptUnitName) {
		this.strDeptUnitName = strDeptUnitName;
	}
	public String getStrCurrentDate() {
		return strCurrentDate;
	}
	public void setStrCurrentDate(String strCurrentDate) {
		this.strCurrentDate = strCurrentDate;
	}
	public String getStrIsMLC() {
		return strIsMLC;
	}
	public void setStrIsMLC(String strIsMLC) {
		this.strIsMLC = strIsMLC;
	}
	public String getStrMLCNo() {
		return strMLCNo;
	}
	public void setStrMLCNo(String strMLCNo) {
		this.strMLCNo = strMLCNo;
	}
	public String getStrIsNewBorn() {
		return strIsNewBorn;
	}
	public void setStrIsNewBorn(String strIsNewBorn) {
		this.strIsNewBorn = strIsNewBorn;
	}
	public String getStrMotherDtl() {
		return strMotherDtl;
	}
	public void setStrMotherDtl(String strMotherDtl) {
		this.strMotherDtl = strMotherDtl;
	}
	public String getStrSpouseName() {
		return strSpouseName;
	}
	public void setStrSpouseName(String strSpouseName) {
		this.strSpouseName = strSpouseName;
	}
	public String getStrIsAdmissionOnline() {
		return strIsAdmissionOnline;
	}
	public void setStrIsAdmissionOnline(String strIsAdmissionOnline) {
		this.strIsAdmissionOnline = strIsAdmissionOnline;
	}
	public WebRowSet getDepartWS() {
		return departWS;
	}
	public void setDepartWS(WebRowSet departWS) {
		this.departWS = departWS;
	}
	public WebRowSet getUnitWS() {
		return unitWS;
	}
	public void setUnitWS(WebRowSet unitWS) {
		this.unitWS = unitWS;
	}
	public WebRowSet getTreatmentCategWS() {
		return treatmentCategWS;
	}
	public void setTreatmentCategWS(WebRowSet treatmentCategWS) {
		this.treatmentCategWS = treatmentCategWS;
	}
	public WebRowSet getConsultantWS() {
		return consultantWS;
	}
	public void setConsultantWS(WebRowSet consultantWS) {
		this.consultantWS = consultantWS;
	}
	public String getStrIsIntegratedWithBilling() {
		return strIsIntegratedWithBilling;
	}
	public void setStrIsIntegratedWithBilling(String strIsIntegratedWithBilling) {
		this.strIsIntegratedWithBilling = strIsIntegratedWithBilling;
	}
	public String getStrIsAdvanceAmountAtAdmission() {
		return strIsAdvanceAmountAtAdmission;
	}
	public void setStrIsAdvanceAmountAtAdmission(
			String strIsAdvanceAmountAtAdmission) {
		this.strIsAdvanceAmountAtAdmission = strIsAdvanceAmountAtAdmission;
	}
	public String getStrIsAdvanceAmountAtAdmissionTaken() {
		return strIsAdvanceAmountAtAdmissionTaken;
	}
	public void setStrIsAdvanceAmountAtAdmissionTaken(
			String strIsAdvanceAmountAtAdmissionTaken) {
		this.strIsAdvanceAmountAtAdmissionTaken = strIsAdvanceAmountAtAdmissionTaken;
	}
	public String getStrPrimaryCategoryCode() {
		return strPrimaryCategoryCode;
	}
	public void setStrPrimaryCategoryCode(String strPrimaryCategoryCode) {
		this.strPrimaryCategoryCode = strPrimaryCategoryCode;
	}
	public String getStrAgeUnit() {
		return strAgeUnit;
	}
	public void setStrAgeUnit(String strAgeUnit) {
		this.strAgeUnit = strAgeUnit;
	}
	public String getStrSexCode() {
		return strSexCode;
	}
	public void setStrSexCode(String strSexCode) {
		this.strSexCode = strSexCode;
	}
	public String getStrAge() {
		return strAge;
	}
	public void setStrAge(String strAge) {
		this.strAge = strAge;
	}
	public String getStrDobTime()
	{
		return strDobTime;
	}
	public void setStrDobTime(String strDobTime)
	{
		this.strDobTime = strDobTime;
	}
	public String getStrBabyName()
	{
		return strBabyName;
	}
	public void setStrBabyName(String strBabyName)
	{
		this.strBabyName = strBabyName;
	}
	public String getStrGenderName()
	{
		return strGenderName;
	}
	public void setStrGenderName(String strGenderName)
	{
		this.strGenderName = strGenderName;
	}
	public String getStrBed()
	{
		return strBed;
	}
	public void setStrBed(String strBed)
	{
		this.strBed = strBed;
	}
	public String getStrStateName()
	{
		return strStateName;
	}
	public void setStrStateName(String strStateName)
	{
		this.strStateName = strStateName;
	}
	public String getStrCountryName()
	{
		return strCountryName;
	}
	public void setStrCountryName(String strCountryName)
	{
		this.strCountryName = strCountryName;
	}
	public String getStrBabyDeptUntName()
	{
		return strBabyDeptUntName;
	}
	public void setStrBabyDeptUntName(String strBabyDeptUntName)
	{
		this.strBabyDeptUntName = strBabyDeptUntName;
	}
	public String getStrAdvanceAmountDate()
	{
		return strAdvanceAmountDate;
	}
	public void setStrAdvanceAmountDate(String strAdvanceAmountDate)
	{
		this.strAdvanceAmountDate = strAdvanceAmountDate;
	}
	public String getStrAdvanceAmountReceiptNo()
	{
		return strAdvanceAmountReceiptNo;
	}
	public void setStrAdvanceAmountReceiptNo(String strAdvanceAmountReceiptNo)
	{
		this.strAdvanceAmountReceiptNo = strAdvanceAmountReceiptNo;
	}
	public String getStrAdvanceAmount()
	{
		return strAdvanceAmount;
	}
	public void setStrAdvanceAmount(String strAdvanceAmount)
	{
		this.strAdvanceAmount = strAdvanceAmount;
	}
	public String getStrAdmDateTime() {
		return strAdmDateTime;
	}
	public void setStrAdmDateTime(String strAdmDateTime) {
		this.strAdmDateTime = strAdmDateTime;
	}
	public String getStrPatientCaste() {
		return strPatientCaste;
	}
	public void setStrPatientCaste(String strPatientCaste) {
		this.strPatientCaste = strPatientCaste;
	}
	public WebRowSet getWrsPatientCaste() {
		return wrsPatientCaste;
	}
	public void setWrsPatientCaste(WebRowSet wrsPatientCaste) {
		this.wrsPatientCaste = wrsPatientCaste;
	}
	
	public String getStrTehsil() {
		return strTehsil;
	}
	public void setStrTehsil(String strTehsil) {
		this.strTehsil = strTehsil;
	}
	public String getStrMaritalStatus() {
		return strMaritalStatus;
	}
	public void setStrMaritalStatus(String strMaritalStatus) {
		this.strMaritalStatus = strMaritalStatus;
	}
	public WebRowSet getWrsMaritalStatus() {
		return wrsMaritalStatus;
	}
	public void setWrsMaritalStatus(WebRowSet wrsMaritalStatus) {
		this.wrsMaritalStatus = wrsMaritalStatus;
	}
	public String getStrAdmissionType() {
		return strAdmissionType;
	}
	public void setStrAdmissionType(String strAdmissionType) {
		this.strAdmissionType = strAdmissionType;
	}
	public WebRowSet getWrsAdmissionTypeValues() {
		return wrsAdmissionTypeValues;
	}
	public void setWrsAdmissionTypeValues(WebRowSet wrsAdmissionTypeValues) {
		this.wrsAdmissionTypeValues = wrsAdmissionTypeValues;
	}
	public String getStrReliefFund() {
		return strReliefFund;
	}
	public void setStrReliefFund(String strReliefFund) {
		this.strReliefFund = strReliefFund;
	}
	
	public String getStrIdMark() {
		return strIdMark;
	}
	public void setStrIdMark(String strIdMark) {
		this.strIdMark = strIdMark;
	}
	public WebRowSet getWrsReliefFundValues() {
		return wrsReliefFundValues;
	}
	public void setWrsReliefFundValues(WebRowSet wrsReliefFundValues) {
		this.wrsReliefFundValues = wrsReliefFundValues;
	}
	public String getStrPatName() {
		return strPatName;
	}
	public void setStrPatName(String strPatName) {
		this.strPatName = strPatName;
	}
	public String getStrHusbandName() {
		return strHusbandName;
	}
	public void setStrHusbandName(String strHusbandName) {
		this.strHusbandName = strHusbandName;
	}
	public String getStrNationality() {
		return strNationality;
	}
	public void setStrNationality(String strNationality) {
		this.strNationality = strNationality;
	}
	public String getStrMonthlyIncome() {
		return strMonthlyIncome;
	}
	public void setStrMonthlyIncome(String strMonthlyIncome) {
		this.strMonthlyIncome = strMonthlyIncome;
	}
	public String getStrHospDtl() {
		return strHospDtl;
	}
	public void setStrHospDtl(String strHospDtl) {
		this.strHospDtl = strHospDtl;
	}
	public String getStrCityLocation() {
		return strCityLocation;
	}
	public void setStrCityLocation(String strCityLocation) {
		this.strCityLocation = strCityLocation;
	}
	public WebRowSet getStrRelationWs() {
		return StrRelationWs;
	}
	public void setStrRelationWs(WebRowSet strRelationWs) {
		StrRelationWs = strRelationWs;
	}
	
	public String getStrSecondPersonName() {
		return strSecondPersonName;
	}
	public void setStrSecondPersonName(String strSecondPersonName) {
		this.strSecondPersonName = strSecondPersonName;
	}
	public String getStrSecondPersonRelationCode() {
		return strSecondPersonRelationCode;
	}
	public void setStrSecondPersonRelationCode(String strSecondPersonRelationCode) {
		this.strSecondPersonRelationCode = strSecondPersonRelationCode;
	}
	public String getStrFirstPersonName() {
		return strFirstPersonName;
	}
	public void setStrFirstPersonName(String strFirstPersonName) {
		this.strFirstPersonName = strFirstPersonName;
	}
	public String getStrFirstPersonRelationCode() {
		return strFirstPersonRelationCode;
	}
	public void setStrFirstPersonRelationCode(String strFirstPersonRelationCode) {
		this.strFirstPersonRelationCode = strFirstPersonRelationCode;
	}
	public String getStrIsDead() {
		return strIsDead;
	}
	public void setStrIsDead(String strIsDead) {
		this.strIsDead = strIsDead;
	}
	public String getStrPatIsUnknown() {
		return strPatIsUnknown;
	}
	public void setStrPatIsUnknown(String strPatIsUnknown) {
		this.strPatIsUnknown = strPatIsUnknown;
	}
	public String getStrPatCategoryName() {
		return strPatCategoryName;
	}
	public void setStrPatCategoryName(String strPatCategoryName) {
		this.strPatCategoryName = strPatCategoryName;
	}
	public String getStrAdmissionChargeAtCounter() {
		return strAdmissionChargeAtCounter;
	}
	public void setStrAdmissionChargeAtCounter(String strAdmissionChargeAtCounter) {
		this.strAdmissionChargeAtCounter = strAdmissionChargeAtCounter;
	}
	public String getStrBillNo() {
		return strBillNo;
	}
	public void setStrBillNo(String strBillNo) {
		this.strBillNo = strBillNo;
	}
	public String getStrIsCreditAdvanceBilling() {
		return strIsCreditAdvanceBilling;
	}
	public void setStrIsCreditAdvanceBilling(String strIsCreditAdvanceBilling) {
		this.strIsCreditAdvanceBilling = strIsCreditAdvanceBilling;
	}
	public String getStrCreditLetterRefNo() {
		return strCreditLetterRefNo;
	}
	public void setStrCreditLetterRefNo(String strCreditLetterRefNo) {
		this.strCreditLetterRefNo = strCreditLetterRefNo;
	}
	public String getStrCreditLetterDate() {
		return strCreditLetterDate;
	}
	public void setStrCreditLetterDate(String strCreditLetterDate) {
		this.strCreditLetterDate = strCreditLetterDate;
	}
	public String getStrClientName() {
		return strClientName;
	}
	public void setStrClientName(String strClientName) {
		this.strClientName = strClientName;
	}
	public String getStrEligibleAmount() {
		return strEligibleAmount;
	}
	public void setStrEligibleAmount(String strEligibleAmount) {
		this.strEligibleAmount = strEligibleAmount;
	}
	public String getStrStaffName() {
		return strStaffName;
	}
	public void setStrStaffName(String strStaffName) {
		this.strStaffName = strStaffName;
	}
	public String getStrRelationship() {
		return strRelationship;
	}
	public void setStrRelationship(String strRelationship) {
		this.strRelationship = strRelationship;
	}
	public String getStrStaffNo() {
		return strStaffNo;
	}
	public void setStrStaffNo(String strStaffNo) {
		this.strStaffNo = strStaffNo;
	}
	public String getStrWAPCardNo() {
		return strWAPCardNo;
	}
	public void setStrWAPCardNo(String strWAPCardNo) {
		this.strWAPCardNo = strWAPCardNo;
	}
	public String getStrCodeNo() {
		return strCodeNo;
	}
	public void setStrCodeNo(String strCodeNo) {
		this.strCodeNo = strCodeNo;
	}
	public String getStrDistrictCode() {
		return strDistrictCode;
	}
	public void setStrDistrictCode(String strDistrictCode) {
		this.strDistrictCode = strDistrictCode;
	}
	public WebRowSet getDistrictWS() {
		return districtWS;
	}
	public void setDistrictWS(WebRowSet districtWS) {
		this.districtWS = districtWS;
	}
	public String getAdmissionTypeName() {
		return admissionTypeName;
	}
	public void setAdmissionTypeName(String admissionTypeName) {
		this.admissionTypeName = admissionTypeName;
	}
	public String getStrMsAppStatus() {
		return strMsAppStatus;
	}
	public void setStrMsAppStatus(String strMsAppStatus) {
		this.strMsAppStatus = strMsAppStatus;
	}
	public String getBillcount() {
		return billcount;
	}
	public void setBillcount(String billcount) {
		this.billcount = billcount;
	}
	public String getStrRegChg() {
		return strRegChg;
	}
	public void setStrRegChg(String strRegChg) {
		this.strRegChg = strRegChg;
	}
	public String getStrAdvanceAmountVal() {
		return strAdvanceAmountVal;
	}
	public void setStrAdvanceAmountVal(String strAdvanceAmountVal) {
		this.strAdvanceAmountVal = strAdvanceAmountVal;
	}
	public String getStrIsSingleWindowAdmission() {
		return strIsSingleWindowAdmission;
	}
	public void setStrIsSingleWindowAdmission(String strIsSingleWindowAdmission) {
		this.strIsSingleWindowAdmission = strIsSingleWindowAdmission;
	}
	public String getStrDeptWardChangeChk() {
		return strDeptWardChangeChk;
	}
	public void setStrDeptWardChangeChk(String strDeptWardChangeChk) {
		this.strDeptWardChangeChk = strDeptWardChangeChk;
	}
	
	public WebRowSet getStrPaymentMode() {
		return strPaymentMode;
	}
	public void setStrPaymentMode(WebRowSet strPaymentMode) {
		this.strPaymentMode = strPaymentMode;
	}
	public String getStrPaymentModeValue() {
		return strPaymentModeValue;
	}
	public void setStrPaymentModeValue(String strPaymentModeValue) {
		this.strPaymentModeValue = strPaymentModeValue;
	}
	public String getStrPayDetail() {
		return strPayDetail;
	}
	public void setStrPayDetail(String strPayDetail) {
		this.strPayDetail = strPayDetail;
	}
	public String getStrActiveWallet() {
		return strActiveWallet;
	}
	public void setStrActiveWallet(String strActiveWallet) {
		this.strActiveWallet = strActiveWallet;
	}
	public String getStrDormantAcc() {
		return strDormantAcc;
	}
	public void setStrDormantAcc(String strDormantAcc) {
		this.strDormantAcc = strDormantAcc;
	}

	
}