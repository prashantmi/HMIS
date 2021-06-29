package ipd.transactions;

import org.apache.struts.action.ActionForm;

public class PackAdmTransFB extends ActionForm {

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
	private String strwardType="";
	private String strWard="";
	private String strRoom="";
	private String strRoomType="";
	private String strWardTypeCode="";
	private String strWardCode="0";
	private String strBedTypeCode="";
	private String strPatStatusCode="";
	private String strRoomTypeCode="";
	private String strWardName="";
	private String strDeptCode="";
	private String strBedType="";
	private String strTreatmentCategoryName="";
	private String strTreatmentCategoryCode="";
	private String strDeptUnitCode="";
	private String strConsultantCode="";
	private String strConsultantName="";
	private String strOccRelation="";
	private String strEpisodeCode="";
	private String strPatCatCode="";
	private String strVisitNo="";
	private String strMlcNo="";
	private String strAdmNo="";
	private String occupationDetailValues = "";
	private String strAdviceAdmNo="";
	private String strRoomCode="";
	private String strNewBorn="";
	private String strIsUrban="";
	private String strBedCode="";
	private String strBed="";
	private String strOccEmpNo="";
	private String strOccEmpName="";
	private String strOccAdd1="";
	private String strOccIsGovtEmp="";
	private String strOccBasic="";
	private String strOccDesc="";
	private String strOccOrgType="";
	private String strOccOffName="";
	private String strOccAdd2="";
	private String strOccCity="";
	private String strOccState="";
	private String strOccOffPhNo="";
	private String strRemarks="";
	private String strFatherName="";
	private String strReligion="";
	private String strReligionCode="";
	private String strAddress="";
	private String strCity="";
	private String strState="";
	private String strAddressModi="";
	private String strBookingDate="";
	private String strDepartmentName="0";
	private String strAddress2="";
	private String strHouseNo="";
	private String strStreet="";
	private String strDistrict="";
	private String strPhoneNo="";
	private String strMobileNo="";
	private String strVacantBed="";
	private String strMsApprovalFlag="";
	private String strMsApprovalStatus="0";
	private String strCountry="";
	private String strPinCode="";
	private String strMsg="";
	private String strWarningMsg="";
	private String strMotherCrNo="";
	private String strMotherAdmissionNo="";
	private String strMotherName="";
	private String strMotherNationalityCode="";
	private String strMotherNationality="";
	private String strMotherReligionCode="";
	private String strMotherReligion="";
	private String strAdmissionCharge="0";
	private String strAdmissionChargeValue="0";
	private String strAdviceStatus="";
	private String strHospCode="";
	private String strSeatId="";
	private String strIpAddress="";
	private String strAdmissionAdviceValidityTo="0";
	private String strAdmissionAdviceValidityFrom="0";
	private String strNoOfFreePass="0";
	private String strFreePassValid="0";
	private String strSpouseName="";
	private String strIsAdmissionOnline="1";
	// Advanced Amount
	private String strIsIntegratedWithBilling="1";
	private String strIsAdvanceAmountAtAdmission="0";
	private String strIsAdvanceAmountAtAdmissionTaken="0";
	private String strAdvanceAmountDate="";
	private String strAdvanceAmountReceiptNo="";
	private String strAdvanceAmount="";
	
	private String strPrimaryCategoryCode="";
	private String strFatherNameMandatoryFlag="";
	private String strUnitName="";
	
	private String strAgeUnit;
	private String strAge;
	private String strSexCode;
	private String strAdmissionMode="";
	private String strPatName="";
	private String strStateName="";
	private String strCountryName="";
	private String strWardBedModi="";
	private String strSameAsAdd="";
	private String strHiddenUnit;
	private String strHiddenRoom;
	private String strPatientCaste="";
	private String strTehsil="";
	private String strMaritalStatus="";
	private String strAdmissionType="";
	private String strReliefFund="";
	private String strIdMark="";	
	private String isDuplicateSlip="";
	private String strAdmDateTime="";
	private String strHusbandName="";
	private String strNationality="";
	private String strMonthlyIncome="";
	private String strSaveFlag="";
	private String strPatientCrNo="";
	private String strHospDtl="";
	private String strCityLocation="";
	private String strEmgAddress1="";
	private String strEmgAddress2="";
	private String strEmgAddress="";
	private String strSecondPersonName="";
	private String strSecondPersonRelationCode="";
	private String strFirstPersonName="";
	private String strFirstPersonRelationCode="";
	private String strPatIsUnknown="";
	private String strPatCategoryName="";
	private String strEmgAddressFlag="0";

	private String strPatientIdNumber="";
	private String strPatientAdhaarNo="";
	private String strFirstpersonphone="";
	private String strSecondpersonphone="";
	
	private String strAdmissionChargeAtCounter="";
	private String strBillNo="";
	private String strFilePath="";
	private String strPatCatSlip="";
	private String strPatCatGrp="";
	private String strDOB="";
	
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
	
	private String strIsDead="0";
	private String strCaseSheetNo="";
	private String strDetPs="";
	private String strPolInfo="";
	private String strNamInf="";
	private String strIdenMark="";
	private String strRefRem="";
	private String strHospName="";
	private String strEmailId="";
	private String strAdmitDetailProperty  ="";
	private String strPreviousOccupiedbed="";
	private String strPackageComboValues = "";
    private String strPackageApply = "";
    private String strPackAppDate= "";
    private String strCurrentDate= "";
	
	public String getStrCurrentDate() {
		return strCurrentDate;
	}
	public void setStrCurrentDate(String strCurrentDate) {
		this.strCurrentDate = strCurrentDate;
	}
	public String getStrPackAppDate() {
		return strPackAppDate;
	}
	public void setStrPackAppDate(String strPackAppDate) {
		this.strPackAppDate = strPackAppDate;
	}
	public String getStrPackageComboValues() {
		return strPackageComboValues;
	}
	public void setStrPackageComboValues(String strPackageComboValues) {
		this.strPackageComboValues = strPackageComboValues;
	}
	public String getStrPackageApply() {
		return strPackageApply;
	}
	public void setStrPackageApply(String strPackageApply) {
		this.strPackageApply = strPackageApply;
	}
	public String getStrPreviousOccupiedbed() {
		return strPreviousOccupiedbed;
	}
	public void setStrPreviousOccupiedbed(String strPreviousOccupiedbed) {
		this.strPreviousOccupiedbed = strPreviousOccupiedbed;
	}
	public String getStrAdmitDetailProperty() {
		return strAdmitDetailProperty;
	}
	public void setStrAdmitDetailProperty(String strAdmitDetailProperty) {
		this.strAdmitDetailProperty = strAdmitDetailProperty;
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
	public String getStrIsDead() {
		return strIsDead;
	}
	public void setStrIsDead(String strIsDead) {
		this.strIsDead = strIsDead;
	}
	public String getStrDOB() {
		return strDOB;
	}
	public void setStrDOB(String strDOB) {
		this.strDOB = strDOB;
	}
	public String getStrPatCatGrp() {
		return strPatCatGrp;
	}
	public void setStrPatCatGrp(String strPatCatGrp) {
		this.strPatCatGrp = strPatCatGrp;
	}
	public String getStrPatCatSlip() {
		return strPatCatSlip;
	}
	public void setStrPatCatSlip(String strPatCatSlip) {
		this.strPatCatSlip = strPatCatSlip;
	}
	public String getStrFilePath() {
		return strFilePath;
	}
	public void setStrFilePath(String strFilePath) {
		this.strFilePath = strFilePath;
	}
	public String getStrBillNo() {
		return strBillNo;
	}
	public void setStrBillNo(String strBillNo) {
		this.strBillNo = strBillNo;
	}
	public String getStrEmgAddressFlag() {
		return strEmgAddressFlag;
	}
	public void setStrEmgAddressFlag(String strEmgAddressFlag) {
		this.strEmgAddressFlag = strEmgAddressFlag;
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
	public String getStrCityLocation() {
		return strCityLocation;
	}
	public void setStrCityLocation(String strCityLocation) {
		this.strCityLocation = strCityLocation;
	}
	public String getStrPatientCaste() {
		return strPatientCaste;
	}
	public void setStrPatientCaste(String strPatientCaste) {
		this.strPatientCaste = strPatientCaste;
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
	public String getStrAdmissionType() {
		return strAdmissionType;
	}
	public void setStrAdmissionType(String strAdmissionType) {
		this.strAdmissionType = strAdmissionType;
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
	/**
	 * @return the strAdmissionMode
	 */
	public String getStrAdmissionMode() {
		return strAdmissionMode;
	}
	/**
	 * @param strAdmissionMode the strAdmissionMode to set
	 */
	public void setStrAdmissionMode(String strAdmissionMode) {
		this.strAdmissionMode = strAdmissionMode;
	}
	/**
	 * @return the strFatherNameMandatoryFlag
	 */
	public String getStrFatherNameMandatoryFlag() {
		return strFatherNameMandatoryFlag;
	}
	/**
	 * @param strFatherNameMandatoryFlag the strFatherNameMandatoryFlag to set
	 */
	public void setStrFatherNameMandatoryFlag(String strFatherNameMandatoryFlag) {
		this.strFatherNameMandatoryFlag = strFatherNameMandatoryFlag;
	}
	public String getStrPrimaryCategoryCode() {
		return strPrimaryCategoryCode;
	}
	public void setStrPrimaryCategoryCode(String strPrimaryCategoryCode) {
		this.strPrimaryCategoryCode = strPrimaryCategoryCode;
	}
	public String getStrIsAdvanceAmountAtAdmissionTaken() {
		return strIsAdvanceAmountAtAdmissionTaken;
	}
	public void setStrIsAdvanceAmountAtAdmissionTaken(
			String strIsAdvanceAmountAtAdmissionTaken) {
		this.strIsAdvanceAmountAtAdmissionTaken = strIsAdvanceAmountAtAdmissionTaken;
	}
	public String getStrIsAdmissionOnline() {
		return strIsAdmissionOnline;
	}
	public void setStrIsAdmissionOnline(String strIsAdmissionOnline) {
		this.strIsAdmissionOnline = strIsAdmissionOnline;
	}
	public String getStrSpouseName() {
		return strSpouseName;
	}
	public void setStrSpouseName(String strSpouseName) {
		this.strSpouseName = strSpouseName;
	}
	public String getStrNoOfFreePass() {
		return strNoOfFreePass;
	}
	public void setStrNoOfFreePass(String strNoOfFreePass) {
		this.strNoOfFreePass = strNoOfFreePass;
	}
	public String getStrHospCode() {
		return strHospCode;
	}
	public void setStrHospCode(String strHospCode) {
		this.strHospCode = strHospCode;
	}
	public String getStrAdmissionCharge() {
		return strAdmissionCharge;
	}
	public void setStrAdmissionCharge(String strAdmissionCharge) {
		this.strAdmissionCharge = strAdmissionCharge;
	}
	public String getStrIsUrban() {
		return strIsUrban;
	}
	public void setStrIsUrban(String strIsUrban) {
		this.strIsUrban = strIsUrban;
	}
	public String getOccupationDetailValues() {
		return occupationDetailValues;
	}
	public void setOccupationDetailValues(String occupationDetailValues) {
		this.occupationDetailValues = occupationDetailValues;
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
	public String getStrDeptUnitName() {
		return strDeptUnitName;
	}
	public void setStrDeptUnitName(String strDeptUnitName) {
		this.strDeptUnitName = strDeptUnitName;
	}
	public String getStrwardType() {
		return strwardType;
	}
	public void setStrwardType(String strwardType) {
		this.strwardType = strwardType;
	}
	public String getStrRoomType() {
		return strRoomType;
	}
	public void setStrRoomType(String strRoomType) {
		this.strRoomType = strRoomType;
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
	public String getStrWard() {
		return strWard;
	}
	public void setStrWard(String strWard) {
		this.strWard = strWard;
	}
	public String getStrRoom() {
		return strRoom;
	}
	public void setStrRoom(String strRoom) {
		this.strRoom = strRoom;
	}
	public String getStrBedType() {
		return strBedType;
	}
	public void setStrBedType(String strBedType) {
		this.strBedType = strBedType;
	}
	public String getStrTreatmentCategoryName() {
		return strTreatmentCategoryName;
	}
	public void setStrTreatmentCategoryName(String strTreatmentCategoryName) {
		this.strTreatmentCategoryName = strTreatmentCategoryName;
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
	public String getStrAdmNo() {
		return strAdmNo;
	}
	public void setStrAdmNo(String strAdmNo) {
		this.strAdmNo = strAdmNo;
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
	public String getStrAddressModi() {
		return strAddressModi;
	}
	public void setStrAddressModi(String strAddressModi) {
		this.strAddressModi = strAddressModi;
	}
	public String getStrBookingDate() {
		return strBookingDate;
	}
	public void setStrBookingDate(String strBookingDate) {
		this.strBookingDate = strBookingDate;
	}
	public String getStrDepartmentName() {
		return strDepartmentName;
	}
	public void setStrDepartmentName(String strDepartmentName) {
		this.strDepartmentName = strDepartmentName;
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
	public String getStrCountry() {
		return strCountry;
	}
	public void setStrCountry(String strCountry) {
		this.strCountry = strCountry;
	}
	public String getStrPinCode() {
		return strPinCode;
	}
	public void setStrPinCode(String strPinCode) {
		this.strPinCode = strPinCode;
	}
	public String getStrMsg() {
		return strMsg;
	}
	public void setStrMsg(String strMsg) {
		this.strMsg = strMsg;
	}
	public String getStrMsApprovalStatus() {
		return strMsApprovalStatus;
	}
	public void setStrMsApprovalStatus(String strMsApprovalStatus) {
		this.strMsApprovalStatus = strMsApprovalStatus;
	}
	public String getStrWarningMsg() {
		return strWarningMsg;
	}
	public void setStrWarningMsg(String strWarningMsg) {
		this.strWarningMsg = strWarningMsg;
	}
	public String getStrPatCatCode() {
		return strPatCatCode;
	}
	public void setStrPatCatCode(String strPatCatCode) {
		this.strPatCatCode = strPatCatCode;
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
	public String getStrAdmissionChargeValue() {
		return strAdmissionChargeValue;
	}
	public void setStrAdmissionChargeValue(String strAdmissionChargeValue) {
		this.strAdmissionChargeValue = strAdmissionChargeValue;
	}
	public String getStrAdviceStatus() {
		return strAdviceStatus;
	}
	public void setStrAdviceStatus(String strAdviceStatus) {
		this.strAdviceStatus = strAdviceStatus;
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
	public String getStrFreePassValid() {
		return strFreePassValid;
	}
	public void setStrFreePassValid(String strFreePassValid) {
		this.strFreePassValid = strFreePassValid;
	}
	public String getStrHiddenPatDtl() {
		return strHiddenPatDtl;
	}
	public void setStrHiddenPatDtl(String strHiddenPatDtl) {
		this.strHiddenPatDtl = strHiddenPatDtl;
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
	public String getStrPatName() {
		return strPatName;
	}
	public void setStrPatName(String strPatName) {
		this.strPatName = strPatName;
	}
	public String getStrUnitName()
	{
		return strUnitName;
	}
	public void setStrUnitName(String strUnitName)
	{
		this.strUnitName = strUnitName;
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
	public String getStrWardBedModi() {
		return strWardBedModi;
	}
	public void setStrWardBedModi(String strWardBedModi) {
		this.strWardBedModi = strWardBedModi;
	}
	public String getStrSameAsAdd() {
		return strSameAsAdd;
	}
	public void setStrSameAsAdd(String strSameAsAdd) {
		this.strSameAsAdd = strSameAsAdd;
	}
	public String getStrHiddenUnit() {
		return strHiddenUnit;
	}
	public void setStrHiddenUnit(String strHiddenUnit) {
		this.strHiddenUnit = strHiddenUnit;
	}
	public String getStrHiddenRoom() {
		return strHiddenRoom;
	}
	public void setStrHiddenRoom(String strHiddenRoom) {
		this.strHiddenRoom = strHiddenRoom;
	}
	public String getIsDuplicateSlip() {
		return isDuplicateSlip;
	}
	public void setIsDuplicateSlip(String isDuplicateSlip) {
		this.isDuplicateSlip = isDuplicateSlip;
	}
	public String getStrAdmDateTime() {
		return strAdmDateTime;
	}
	public void setStrAdmDateTime(String strAdmDateTime) {
		this.strAdmDateTime = strAdmDateTime;
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
	public String getStrSaveFlag() {
		return strSaveFlag;
	}
	public void setStrSaveFlag(String strSaveFlag) {
		this.strSaveFlag = strSaveFlag;
	}
	public String getStrPatientCrNo() {
		return strPatientCrNo;
	}
	public void setStrPatientCrNo(String strPatientCrNo) {
		this.strPatientCrNo = strPatientCrNo;
	}
	public String getStrHospDtl() {
		return strHospDtl;
	}
	public void setStrHospDtl(String strHospDtl) {
		this.strHospDtl = strHospDtl;
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
	public String getStrEmgAddress() {
		return strEmgAddress;
	}
	public void setStrEmgAddress(String strEmgAddress) {
		this.strEmgAddress = strEmgAddress;
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
	public String getStrAdmissionChargeAtCounter() {
		return strAdmissionChargeAtCounter;
	}
	public void setStrAdmissionChargeAtCounter(String strAdmissionChargeAtCounter) {
		this.strAdmissionChargeAtCounter = strAdmissionChargeAtCounter;
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
	public String getStrEmailId() {
		return strEmailId;
	}
	public void setStrEmailId(String strEmailId) {
		this.strEmailId = strEmailId;
	}	
}