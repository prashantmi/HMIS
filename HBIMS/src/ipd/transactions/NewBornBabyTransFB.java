package ipd.transactions;

import org.apache.struts.action.ActionForm;

public class NewBornBabyTransFB extends ActionForm {

	private static final long serialVersionUID = 02L;
	private String strIsMLC="";
	private String strMLCNo="";
	private String strIsNewBorn="";
	private String strMotherDtl="";
	private String strBabyDeptUntName="";
	private String strHiddenPatDtl="";
	private String strMsgString = "";
	private String strMsgType = "0";
	private String strDeptUnitName="";
	private String strGenderName="";
	private String strCrNo = "";
	private String strSaveFlag="";
	private String strwardType="";
	private String strWard="";
	private String strRoom="";
	private String strRoomType="";
	private String strWardTypeCode="";
	private String strWardCode="";
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
	private String strDepartmentName="";
	private String strAddress2="";
	private String strHouseNo="";
	private String strStreet="";
	private String strDistrict="";
	private String strPhoneNo="";
	private String strMobileNo="";
	private String strVacantBed="";
	private String strMsApprovalFlag="";
	private String strMsApprovalStatus="";
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
	private String strDeptValue="";
	private String strDeptNameNewBorn="";
	private String strMotherDeptCode="0";
	private String strMotherUnitCode="0";
	private String strMotherDeptName="";
	private String strMotherUnitName="";
	private String strMotherWardCode="";
	private String strMotherRoomCode="";
	private String strMotherWardName="";
	private String strMotherWardTypeCode="";
	private String strUnitValue="";
	private String strMotherRoomTypeCode="";
	private String strMotherBedTypeTypeCode="";
	private String strTreatmentCategVal="";
	private String strNewBornRegistrationCharge="0";
	private String strNewBornRegistrationChargeVal="0";
	private String strGender="";
	private String strDobTime="";
	private String strUnitNewBorn="";
	private String strIdMark1="";
	private String strIdMark2="";
	private String strBabyName="";
	private String strMotherTreatmentCateg="";
	private String strAdmissionAdvanceChargeValue="0";
	private String strAdmissionAdvance="0";
	private String strAdmissionChargeHidden="0";
	private String strRegistrationChargeHidden="0";
	private String strBillFlag="0";
	private String strAdvanceAmount="";
	private String strAmountChargeFinal="";
	
	private String strAgeUnit;
	private String strAge;
	private String strSexCode;
	private String strOnlineBabyList="";

	private String strSlNo="";
	private String strGravidaNo="";
	private String strOnlineOrNot="";
	private String strStateName="";
	private String strCountryName="";
	private String strPatientCaste="";
	private String strTehsil="";
	private String strAdmissionType="";
	private String strReliefFund="";
	private String strAdmissionTypeValues="";
	private String strReliefFundValues="";
	private String strSameBedAsMotherFlg="0";
	private String sameBedAsMother="";
	private String strIsWardRoomCriteriaMatch="";
	private String strIsBedSharable="";
	
	private String isOpenPopUp;
	private String printMode="";
	private String filePath="";
	private String strNumberOfChildrenBorn="";
	private String strAdmittedBabyDetails;// details of baby(s) admitted....
	private String babyCountWhoseDetailIsEntered;// count of babies whose details are entered into the system..
	private String strWardBedStatusFlag="0";
	private String strPrintingMode="";
	private String strMaxBabyAllowed="";
	private String strIsGivenBirth="0";
	private String strPatientCrNo="";
    private String strCaseSheetNo="";
    private String strDistrictName="";
    public String setStrMotherCatgrp=""; 
    public String strConsNewBorn="";
    public String strConsValue="";
    public String strCtDate="";
    public String strMothAdmDate="";
    public String datetime="";
    private String isSingleMenu="";
	
	public String getStrCaseSheetNo() {
		return strCaseSheetNo;
	}
	public void setStrCaseSheetNo(String strCaseSheetNo) {
		this.strCaseSheetNo = strCaseSheetNo;
	}
	
	public String getStrPatientCrNo() {
		return strPatientCrNo;
	}
	public void setStrPatientCrNo(String strPatientCrNo) {
		this.strPatientCrNo = strPatientCrNo;
	}
	public String getStrMaxBabyAllowed() {
		return strMaxBabyAllowed;
	}
	public void setStrMaxBabyAllowed(String strMaxBabyAllowed) {
		this.strMaxBabyAllowed = strMaxBabyAllowed;
	}
	public String getStrIsGivenBirth() {
		return strIsGivenBirth;
	}
	public void setStrIsGivenBirth(String strIsGivenBirth) {
		this.strIsGivenBirth = strIsGivenBirth;
	}
	public String getStrPrintingMode() {
		return strPrintingMode;
	}
	public void setStrPrintingMode(String strPrintingMode) {
		this.strPrintingMode = strPrintingMode;
	}
	public String getStrWardBedStatusFlag() {
		return strWardBedStatusFlag;
	}
	public void setStrWardBedStatusFlag(String strWardBedStatusFlag) {
		this.strWardBedStatusFlag = strWardBedStatusFlag;
	}
	public String getStrAdmittedBabyDetails() {
		return strAdmittedBabyDetails;
	}
	public void setStrAdmittedBabyDetails(String strAdmittedBabyDetails) {
		this.strAdmittedBabyDetails = strAdmittedBabyDetails;
	}
	public String getBabyCountWhoseDetailIsEntered() {
		return babyCountWhoseDetailIsEntered;
	}
	public void setBabyCountWhoseDetailIsEntered(
			String babyCountWhoseDetailIsEntered) {
		this.babyCountWhoseDetailIsEntered = babyCountWhoseDetailIsEntered;
	}
	public String getStrNumberOfChildrenBorn() {
		return strNumberOfChildrenBorn;
	}
	public void setStrNumberOfChildrenBorn(String strNumberOfChildrenBorn) {
		this.strNumberOfChildrenBorn = strNumberOfChildrenBorn;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getIsOpenPopUp() {
		return isOpenPopUp;
	}
	public void setIsOpenPopUp(String isOpenPopUp) {
		this.isOpenPopUp = isOpenPopUp;
	}
	public String getPrintMode() {
		return printMode;
	}
	public void setPrintMode(String printMode) {
		this.printMode = printMode;
	}
	public String getStrIsBedSharable() {
		return strIsBedSharable;
	}
	public void setStrIsBedSharable(String strIsBedSharable) {
		this.strIsBedSharable = strIsBedSharable;
	}
	public String getStrSameBedAsMotherFlg() {
		return strSameBedAsMotherFlg;
	}
	public void setStrSameBedAsMotherFlg(String strSameBedAsMotherFlg) {
		this.strSameBedAsMotherFlg = strSameBedAsMotherFlg;
	}
	public String getSameBedAsMother() {
		return sameBedAsMother;
	}
	public void setSameBedAsMother(String sameBedAsMother) {
		this.sameBedAsMother = sameBedAsMother;
	}
	public String getStrIsWardRoomCriteriaMatch() {
		return strIsWardRoomCriteriaMatch;
	}
	public void setStrIsWardRoomCriteriaMatch(String strIsWardRoomCriteriaMatch) {
		this.strIsWardRoomCriteriaMatch = strIsWardRoomCriteriaMatch;
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
	public String getStrOnlineOrNot() {
		return strOnlineOrNot;
	}
	public void setStrOnlineOrNot(String strOnlineOrNot) {
		this.strOnlineOrNot = strOnlineOrNot;
	}
	public String getStrSlNo() {
		return strSlNo;
	}
	public void setStrSlNo(String strSlNo) {
		this.strSlNo = strSlNo;
	}
	public String getStrGravidaNo() {
		return strGravidaNo;
	}
	public void setStrGravidaNo(String strGravidaNo) {
		this.strGravidaNo = strGravidaNo;
	}
	/**
	 * @return the strOnlineBabyList
	 */
	public String getStrOnlineBabyList() {
		return strOnlineBabyList;
	}
	/**
	 * @param strOnlineBabyList the strOnlineBabyList to set
	 */
	public void setStrOnlineBabyList(String strOnlineBabyList) {
		this.strOnlineBabyList = strOnlineBabyList;
	}
	public String getStrAgeUnit() {
		return strAgeUnit;
	}
	public void setStrAgeUnit(String strAgeUnit) {
		this.strAgeUnit = strAgeUnit;
	}
	public String getStrAge() {
		return strAge;
	}
	public void setStrAge(String strAge) {
		this.strAge = strAge;
	}
	public String getStrSexCode() {
		return strSexCode;
	}
	public void setStrSexCode(String strSexCode) {
		this.strSexCode = strSexCode;
	}
	public String getStrAmountChargeFinal() {
		return strAmountChargeFinal;
	}
	public void setStrAmountChargeFinal(String strAmountChargeFinal) {
		this.strAmountChargeFinal = strAmountChargeFinal;
	}
	public String getStrAdvanceAmount() {
		return strAdvanceAmount;
	}
	public void setStrAdvanceAmount(String strAdvanceAmount) {
		this.strAdvanceAmount = strAdvanceAmount;
	}
	public String getStrBillFlag() {
		return strBillFlag;
	}
	public void setStrBillFlag(String strBillFlag) {
		this.strBillFlag = strBillFlag;
	}
	public String getStrAdmissionAdvance() {
		return strAdmissionAdvance;
	}
	public void setStrAdmissionAdvance(String strAdmissionAdvance) {
		this.strAdmissionAdvance = strAdmissionAdvance;
	}
	public String getStrAdmissionAdvanceChargeValue()
	{
		return strAdmissionAdvanceChargeValue;
	}
	public void setStrAdmissionAdvanceChargeValue(
			String strAdmissionAdvanceChargeValue) {
		this.strAdmissionAdvanceChargeValue = strAdmissionAdvanceChargeValue;
	}
	public String getStrBabyName() {
		return strBabyName;
	}
	public void setStrBabyName(String strBabyName) {
		this.strBabyName = strBabyName;
	}
	public String getStrMotherTreatmentCateg() {
		return strMotherTreatmentCateg;
	}
	public void setStrMotherTreatmentCateg(String strMotherTreatmentCateg) {
		this.strMotherTreatmentCateg = strMotherTreatmentCateg;
	}
	public String getStrGender() {
		return strGender;
	}
	public void setStrGender(String strGender) {
		this.strGender = strGender;
	}
	public String getStrDobTime() {
		return strDobTime;
	}
	public void setStrDobTime(String strDobTime) {
		this.strDobTime = strDobTime;
	}
	public String getStrUnitNewBorn() {
		return strUnitNewBorn;
	}
	public void setStrUnitNewBorn(String strUnitNewBorn) {
		this.strUnitNewBorn = strUnitNewBorn;
	}
	public String getStrMotherUnitCode() {
		return strMotherUnitCode;
	}
	public void setStrMotherUnitCode(String strMotherUnitCode) {
		this.strMotherUnitCode = strMotherUnitCode;
	}
	public String getStrMotherDeptName() {
		return strMotherDeptName;
	}
	public void setStrMotherDeptName(String strMotherDeptName) {
		this.strMotherDeptName = strMotherDeptName;
	}
	public String getStrMotherUnitName() {
		return strMotherUnitName;
	}
	public void setStrMotherUnitName(String strMotherUnitName) {
		this.strMotherUnitName = strMotherUnitName;
	}
	public String getStrMotherWardCode() {
		return strMotherWardCode;
	}
	public void setStrMotherWardCode(String strMotherWardCode) {
		this.strMotherWardCode = strMotherWardCode;
	}
	public String getStrMotherWardName() {
		return strMotherWardName;
	}
	public void setStrMotherWardName(String strMotherWardName) {
		this.strMotherWardName = strMotherWardName;
	}
	public String getStrMotherWardTypeCode() {
		return strMotherWardTypeCode;
	}
	public void setStrMotherWardTypeCode(String strMotherWardTypeCode) {
		this.strMotherWardTypeCode = strMotherWardTypeCode;
	}
	
	public String getStrDeptNameNewBorn() {
		return strDeptNameNewBorn;
	}
	public void setStrDeptNameNewBorn(String strDeptNameNewBorn) {
		this.strDeptNameNewBorn = strDeptNameNewBorn;
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
	public String getStrDeptValue() {
		return strDeptValue;
	}
	public void setStrDeptValue(String strDeptValue) {
		this.strDeptValue = strDeptValue;
	}
	public String getStrMotherDeptCode() {
		return strMotherDeptCode;
	}
	public void setStrMotherDeptCode(String strMotherDeptCode) {
		this.strMotherDeptCode = strMotherDeptCode;
	}
	public String getStrUnitValue() {
		return strUnitValue;
	}
	public void setStrUnitValue(String strUnitValue) {
		this.strUnitValue = strUnitValue;
	}
	public String getStrMotherRoomTypeCode() {
		return strMotherRoomTypeCode;
	}
	public void setStrMotherRoomTypeCode(String strMotherRoomTypeCode) {
		this.strMotherRoomTypeCode = strMotherRoomTypeCode;
	}
	public String getStrMotherBedTypeTypeCode() {
		return strMotherBedTypeTypeCode;
	}
	public void setStrMotherBedTypeTypeCode(String strMotherBedTypeTypeCode) {
		this.strMotherBedTypeTypeCode = strMotherBedTypeTypeCode;
	}
	public String getStrTreatmentCategVal() {
		return strTreatmentCategVal;
	}
	public void setStrTreatmentCategVal(String strTreatmentCategVal) {
		this.strTreatmentCategVal = strTreatmentCategVal;
	}
	public String getStrNewBornRegistrationCharge() {
		return strNewBornRegistrationCharge;
	}
	public void setStrNewBornRegistrationCharge(String strNewBornRegistrationCharge) {
		this.strNewBornRegistrationCharge = strNewBornRegistrationCharge;
	}
	public String getStrNewBornRegistrationChargeVal() {
		return strNewBornRegistrationChargeVal;
	}
	public void setStrNewBornRegistrationChargeVal(
			String strNewBornRegistrationChargeVal) {
		this.strNewBornRegistrationChargeVal = strNewBornRegistrationChargeVal;
	}
	public String getStrIdMark1() {
		return strIdMark1;
	}
	public void setStrIdMark1(String strIdMark1) {
		this.strIdMark1 = strIdMark1;
	}
	public String getStrIdMark2() {
		return strIdMark2;
	}
	public void setStrIdMark2(String strIdMark2) {
		this.strIdMark2 = strIdMark2;
	}
	public String getStrAdmissionChargeHidden() {
		return strAdmissionChargeHidden;
	}
	public void setStrAdmissionChargeHidden(String strAdmissionChargeHidden) {
		this.strAdmissionChargeHidden = strAdmissionChargeHidden;
	}
	public String getStrRegistrationChargeHidden() {
		return strRegistrationChargeHidden;
	}
	public void setStrRegistrationChargeHidden(String strRegistrationChargeHidden) {
		this.strRegistrationChargeHidden = strRegistrationChargeHidden;
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
	public String getStrBabyDeptUntName() {
		return strBabyDeptUntName;
	}
	public void setStrBabyDeptUntName(String strBabyDeptUntName) {
		this.strBabyDeptUntName = strBabyDeptUntName;
	}
	public String getStrGenderName() {
		return strGenderName;
	}
	public void setStrGenderName(String strGenderName) {
		this.strGenderName = strGenderName;
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
	public String getStrAdmissionTypeValues() {
		return strAdmissionTypeValues;
	}
	public void setStrAdmissionTypeValues(String strAdmissionTypeValues) {
		this.strAdmissionTypeValues = strAdmissionTypeValues;
	}
	public String getStrReliefFundValues() {
		return strReliefFundValues;
	}
	public void setStrReliefFundValues(String strReliefFundValues) {
		this.strReliefFundValues = strReliefFundValues;
	}
	public String getStrSaveFlag() {
		return strSaveFlag;
	}
	public void setStrSaveFlag(String strSaveFlag) {
		this.strSaveFlag = strSaveFlag;
	}
	public String getStrDistrictName() {
		return strDistrictName;
	}
	public void setStrDistrictName(String strDistrictName) {
		this.strDistrictName = strDistrictName;
	}
	public String getSetStrMotherCatgrp() {
		return setStrMotherCatgrp;
	}
	public void setSetStrMotherCatgrp(String setStrMotherCatgrp) {
		this.setStrMotherCatgrp = setStrMotherCatgrp;
	}
	public String getStrMotherRoomCode() {
		return strMotherRoomCode;
	}
	public void setStrMotherRoomCode(String strMotherRoomCode) {
		this.strMotherRoomCode = strMotherRoomCode;
	}
	public String getStrConsNewBorn() {
		return strConsNewBorn;
	}
	public void setStrConsNewBorn(String strConsNewBorn) {
		this.strConsNewBorn = strConsNewBorn;
	}
	public String getStrConsValue() {
		return strConsValue;
	}
	public void setStrConsValue(String strConsValue) {
		this.strConsValue = strConsValue;
	}
	public String getStrCtDate() {
		return strCtDate;
	}
	public void setStrCtDate(String strCtDate) {
		this.strCtDate = strCtDate;
	}
	public String getStrMothAdmDate() {
		return strMothAdmDate;
	}
	public void setStrMothAdmDate(String strMothAdmDate) {
		this.strMothAdmDate = strMothAdmDate;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getIsSingleMenu() {
		return isSingleMenu;
	}
	public void setIsSingleMenu(String isSingleMenu) {
		this.isSingleMenu = isSingleMenu;
	}
	
	
}
