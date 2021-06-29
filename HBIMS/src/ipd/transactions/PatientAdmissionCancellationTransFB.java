package ipd.transactions;

import org.apache.struts.action.ActionForm;

public class  PatientAdmissionCancellationTransFB extends ActionForm {

	private static final long serialVersionUID = 02L;
	
	private String strMsgString = "";
	private String strMsgType = "0";
	private String strDeptUnitName="";
	private String strCrNo = "";
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
	private String strAdmissionCharge="";
	private String strAdmissionChargeValue="";
	private String strAdviceStatus="";
	private String strHospCode="";
	private String strSeatId="";
	private String strIpAddress="";
	private String strDeptValue="";
	private String StrDeptNameNewBorn="";
	private String strMotherDeptCode="0";
	private String strMotherUnitCode="0";
	private String strMotherDeptName="";
	private String strMotherUnitName="";
	private String strMotherWardCode="";
	private String strMotherWardName="";
	private String strMotherWardTypeCode="";
	private String strUnitValue="";
	private String strMotherRoomTypeCode="";
	private String strMotherBedTypeTypeCode="";
	private String strTreatmentCategVal="";
	 private String strUnitName="";
	private String strNewBornRegistrationCharge="0";
	private String strDeptName="";
	private String strConsultantValues="";
	private String strBillFlag="";
	private String  strAdvanceRefund="";
	private String strCheckForServiceAndAdvance="";
	private String  strAdvanceDepositStatus="";//Advance Deposited 1-Yes,0-No
	private String  strAdvanceDetails="";
	private String isSingleMenu="";
	private String status="1";
	
	public String getStrBillFlag() {
		return strBillFlag;
	}
	public void setStrBillFlag(String strBillFlag) {
		this.strBillFlag = strBillFlag;
	}
	public String getStrAdvanceRefund() {
		return strAdvanceRefund;
	}
	public void setStrAdvanceRefund(String strAdvanceRefund) {
		this.strAdvanceRefund = strAdvanceRefund;
	}
	public String getStrConsultantValues() {
		return strConsultantValues;
	}
	public void setStrConsultantValues(String strConsultantValues) {
		this.strConsultantValues = strConsultantValues;
	}
	public String getStrDeptName() {
		return strDeptName;
	}
	public void setStrDeptName(String strDeptName) {
		this.strDeptName = strDeptName;
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
		return StrDeptNameNewBorn;
	}
	public void setStrDeptNameNewBorn(String strDeptNameNewBorn) {
		StrDeptNameNewBorn = strDeptNameNewBorn;
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
	public String getStrUnitName() {
		return strUnitName;
	}
	public void setStrUnitName(String strUnitName) {
		this.strUnitName = strUnitName;
	}
	public String getStrCheckForServiceAndAdvance() {
		return strCheckForServiceAndAdvance;
	}
	public void setStrCheckForServiceAndAdvance(String strCheckForServiceAndAdvance) {
		this.strCheckForServiceAndAdvance = strCheckForServiceAndAdvance;
	}
	public String getStrAdvanceDepositStatus() {
		return strAdvanceDepositStatus;
	}
	public void setStrAdvanceDepositStatus(String strAdvanceDepositStatus) {
		this.strAdvanceDepositStatus = strAdvanceDepositStatus;
	}
	public String getStrAdvanceDetails() {
		return strAdvanceDetails;
	}
	public void setStrAdvanceDetails(String strAdvanceDetails) {
		this.strAdvanceDetails = strAdvanceDetails;
	}
	public String getIsSingleMenu() {
		return isSingleMenu;
	}
	public void setIsSingleMenu(String isSingleMenu) {
		this.isSingleMenu = isSingleMenu;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
