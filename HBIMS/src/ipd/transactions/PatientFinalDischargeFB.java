package ipd.transactions;

import javax.sql.rowset.WebRowSet;

import org.apache.struts.action.ActionForm;

public class PatientFinalDischargeFB extends ActionForm
{
    private static final long serialVersionUID = 02L;
	
    private String strErrMsgString = null;
    private String strNormalMsgString = null;
    private String strWarningMsgString = null;
   
	private String strDepartmentName="";
	private String strVacantBed="";
	private String strMsg="";
	private String curDept_Unt_RomCode = "";
	private String strCurrentDeptUnitRoom="";
	private String strAjaxVAL="";
	private String strSaveStatus = "0";
	private String strAdmissionNo = "";
	private String isDead = "";
	private WebRowSet strPatientDetailsWs = null;
	private WebRowSet strOnLineReqDiscountWs = null;
    private String strMsgString = null;
    private String strMsgType = "";
    private String strChk ="";
    private String strCrNo = "";
    private String strCrNo1 = "";
    private String strBId ="";
    private String strApproval_id="";
    private String strExpnseAmt ="";
    private String strRecFrClnt ="";
    private String lastDis ="";
    private String discountType ="";
    private String strDisAmt ="";
    private String strRecFrPat ="";
    private String strNetAmt ="";
    private String strDetls="";
    private WebRowSet strDisBy =null;
    private WebRowSet strDisRsn =null;
    private String strRsn="";
    private String strRmk="0";
    private String strMLCDetails="";
    private String strIsMLC="";
    private String strAccPreDis ="";
    private String strDepartment ="";     //combo name
	private String strUnit ="";            //combo name
	private String strWard ="";             //combo name
	private String strRoom ="";
	private String strWardTypeCode="0";
	private String strWardCode="";
	private String strBedTypeCode="0";
	private String strRoomTypeCode="0";
	private String strWardName="";
	private String strDeptName="";
	private String strDeptCode="";
	private String strDeptUnitCode="";
	private String strUnitName="";
	private String strTreatmentCategoryName="";
	private String strPatCatCode="";
	private String strConsultantCode="";
	private String strConsultantName="";
	private String strOccRelation="";
	private String strAdmNo="";
	private String strEpisodeCode="";
	private String strVisitNo="";
	private String strMlcNo="";
	private String strIsUrban="";
	private String strAdviceAdmNo="";
	private String strRoomCode="";
	private String strOccEmpName="";
	private String strTreatmentCategoryCode="";
	private String strNewBorn="";
	private String strBedCode="0";
	private String strBedType="";
	private String strBedProperty="";
	private String strBed="";
	private String strServArea="";
	private String strServAreaCode="";
	private String strMsApprovalFlag="";
	private String strMsApprovalStatus="";
	private String strdeptproperty  ="";
	private String[] strhward =null;
	private String strwardproperty  ="";
	private String strunitproperty  ="";
	private String strroomproperty  ="";
	private String strwardType="";
	private String strRoomType="";
	private String strPopUp="0";
	private String strEntryDate="";
	private String strOldWardCode="";
	private String strOldRoomCode="";
	private String strOldBedCode="";
	private String strTransFlg="";
	private String strApplicationMode="";
	private String strPvtBedFlg="";
	private String strCmbRed="0";
	private String strCtDate="";
	private String strValidFromDp="";
	private String strValidFromDp1="";
	private String strValidFrom="";
	private String strValidTo="";
	private String strPatCondL="";
	private String strAdviceL="";
	private String strDaysOnFinalDischarge="";
	private WebRowSet roomTypeWS=null;
	private WebRowSet strCheckAdminWS=null;
	private WebRowSet strServAreaWS=null;
	private WebRowSet wardWS=null;
	private WebRowSet roomWs=null;
	private WebRowSet bedTypeWS=null;
	private WebRowSet bedDetailWS=null;
	private WebRowSet  strDepartmenttWs =null;
	private WebRowSet  strUnitWs =null;
	private WebRowSet  strWardWs =null;
	private WebRowSet  strRoomWs =null;
	private WebRowSet wardTypeWS=null;
    private String[] strComponentName=null;
    private String[] strComponentID=null;
	private String strPatientDisParam = ""; 
	private String[] strPatientDisParamDetails=null;
	private String strDeptUntRomCode="";
	private WebRowSet wsFinalDiagnosis=null;
	private WebRowSet wsIcd10Diagnosis=null;
	private String strDiagnosisType="1";
	private String strProvisionDiagnosisValues="";
	private String strDiagnosisTypeCombo="";
	private String strPatientDischargeTypeComboValues="";
	private String strDeathDateAndTime="";
	private String strDeathCauseIM="";
	private String strDeathCauseAN="";
	private String strDeathManner="";
	private String strDeathMannerID="";
	private String strAdmStatCode ="";
	private String strDeathMannerComboValues="";
	private String strDeathDetails="";
	private String strDeathCauseComboValues = "";
	private String strSearchString="";
	private String strSearchMode="";
	private String[] strProvisionDiagnosis=null;
	private String strOnsetDeathDateAndTime="";
	private String strVerifyDateAndTime="";
	private String strHandoverDateAndTime="";
	private String strShiftDateAndTime="";
	private String strDeathCauseID="";
	private String strIsSiftToMortuary="";
	private String strHandoverTo="";
	private String strIsPregnant="";
	private String strIsDelivery="";
	private String strTransferUnit="";
	private String strIsDead = "false";
	private String strIsFemale="false";
	private String strHospitalCode="";
	private String curAdmNo="";
	private String strSeatID="";
	private String strApprovalComboMode="";
	private String strPoliceInfrmDtMLC="";
	private String strApprovDtMLC="";
	private String strApprovRmkMLC="";
	private String strApprovByMLC="";
	private WebRowSet strApprovByWS_MLC=null;
	private String strNxtVisitOPD="";
	private String strRoomOPD="";
	private WebRowSet strRoomOPDWS=null;
	private String strDischrg_Mode="";
	private String strDischrg_Param_Req="";
	private String strDisplay_MLC_Dtls="";
	private String strDisplay_OPD_Visit_Dtls="";
	private String strHlpMLC="";
	private String strHlpOPD="";
	private String strAbscondedValue="";
	private String strReportId = "";
	private String strAdviceDate = "";
	private String strDischargeType = "";
	private String strDischargeTypeName="";
	private String strRemarksOnline="";
	private String strAdvisedBy="";
	private String strICD10CodeValues="";
	private String strClearForDischarge = "";
	private String strDiagType="";
	
	private String strDiagnosticTypeValues="";
	
	private String[] strDiagCodeType=null;//ICD/Hospital
	private String[] strisRepaeat=null;
	private String[] strDiagRemarks=null;
	private String[] strDiseaseSite=null;
	private String[] strDiagTypeCode=null;//Provisional
	
	private String strDischargeDiagnosisRequired="";
	private String strDischargeAdviceFieldRequired="";
	private String strDischargeSummaryPrintRequired="";
	private String strDeathFlag="";
	private String strAntecedentCauseDeath="";
	private String strInjuryDetail="";
	private String strDescpCauseDeath="";
	private String curWrdBedCode="";
	private String strDischargeTypeLAMA="2";
	private String strDischargeTypeAbsconded="3";
	private String strDischargeTypeReferral="7";
	private String strDischargeTypeTransfer="8";
	private String strDischargeTypeDeath="13";
	private String strTreatmentResult="";
	private String strTreatmentResultComboValues="";
	private String strConsentRequired="";
	private String strConsentSignedByRelativeName="";
	private String consentHtmlCode="";
	private String strConsentTemplateId="";
	 private String hmode = null;
	 private String strAbscondedDetails="";
	 private String strAbscondedDate="";
	 private String strPatHeight="";
	 private String strPatColor="";
	 private String strPatIdentifyMark="";
	 private String strLastSeenDate="";
	 private String strIsPatWearHospClothes="";
	 private String strClothesDetails="";
	 private String strAbscondedHour="";
	 private String strAbscondedMin="";
	 private String strAbscondedSec="";
	 private String strDeathDetailsRequired="";
	 private String strNormalDischargeType="";
	 private String strProfileId="";
	 private String callFromIpdDesk="0";
	 private String strDisDate="";
	 private String isDuplicateSlip="";
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
		private String strTehsil="";
		private String strPatName="";
		private String strStateName="";
		private String strCountryName="";
		private String strCity="";
		private String strState="";
		private String strHouseNo="";
		private String strStreet="";
		private String strDistrict="";
		private String strPhoneNo="";
		private String strMobileNo="";
		private String strPinCode="";
		
		private String strMotherCrNo="";
		private String strMotherAdmissionNo="";
		private String strMotherName="";
		private String strIsAdvanceAmountAtAdmission="0";
		private String strIsAdvanceAmountAtAdmissionTaken="0";
		private String strAdvanceAmountDate="";
		private String strAdvanceAmountReceiptNo="";
		private String strAdvanceAmount="";
		private String strFirstPersonName="";
		private String strEmgAddressFlag="0";

		private String strPatientIdNumber="";
		private String strPatientAdhaarNo="";
		private String strFirstpersonphone="";
		private String strEmgAddress1="";
		private String strHospDtl="";
		private String strAdmDateTime="";
		private String strHusbandName="";
		private String strNationality="";
		private String strMonthlyIncome="";
		private String strFatherName="";
		private String strAddress="";
		private String strDeptUnitName="";
		private String strIsNewBorn="";
		private String strAge;
		private String strPatCategoryName="";
		private String strMaritalStatus="";
		private String strCaseSheetNo="";
		private String strDetPs="";
		private String strPolInfo="";
		private String strNamInf="";
		private String strIdenMark="";
		private String strRefRem="";
		private String strHospName="";
		private String strEmailId="";	
		private String strDistrictCode="0";
		private String strAdmissionCharge="0";
		private String strAdmissionChargeValue="0";
		private String strSlipCrNo;
		private String strDisDateTime="";
		private String strDaysStay="";
		private String strAbsHour="";
		private String strAbsMin="";
		private String strAbsSec="";
	
		private String strAbs1="";
		private String strAbs2="";
		private String billcount="0";
		private String strAbsAmPm="";
		
		public String getStrAbs1() {
			return strAbs1;
		}

		public void setStrAbs1(String strAbs1) {
			this.strAbs1 = strAbs1;
		}

		public String getStrAbs2() {
			return strAbs2;
		}

		public void setStrAbs2(String strAbs2) {
			this.strAbs2 = strAbs2;
		}

		public String getStrAbsHour() {
			return strAbsHour;
		}

		public void setStrAbsHour(String strAbsHour) {
			this.strAbsHour = strAbsHour;
		}

		public String getStrAbsMin() {
			return strAbsMin;
		}

		public void setStrAbsMin(String strAbsMin) {
			this.strAbsMin = strAbsMin;
		}

		public String getStrAbsSec() {
			return strAbsSec;
		}

		public void setStrAbsSec(String strAbsSec) {
			this.strAbsSec = strAbsSec;
		}

		

		public String getStrDisDateTime() {
			return strDisDateTime;
		}

		public void setStrDisDateTime(String strDisDateTime) {
			this.strDisDateTime = strDisDateTime;
		}

	
		
	

		public String getStrDaysStay() {
			return strDaysStay;
		}

		public void setStrDaysStay(String strDaysStay) {
			this.strDaysStay = strDaysStay;
		}

		public String getStrSlipCrNo() {
			return strSlipCrNo;
		}

		public void setStrSlipCrNo(String strSlipCrNo) {
			this.strSlipCrNo = strSlipCrNo;
		}

		public String getStrMaritalStatus() {
			return strMaritalStatus;
		}

		public void setStrMaritalStatus(String strMaritalStatus) {
			this.strMaritalStatus = strMaritalStatus;
		}

		public String getStrDeptUnitName() {
			return strDeptUnitName;
		}

		public void setStrDeptUnitName(String strDeptUnitName) {
			this.strDeptUnitName = strDeptUnitName;
		}

		public String getStrIsNewBorn() {
			return strIsNewBorn;
		}

		public void setStrIsNewBorn(String strIsNewBorn) {
			this.strIsNewBorn = strIsNewBorn;
		}

		public String getStrAge() {
			return strAge;
		}

		public void setStrAge(String strAge) {
			this.strAge = strAge;
		}

		public String getStrPatCategoryName() {
			return strPatCategoryName;
		}

		public void setStrPatCategoryName(String strPatCategoryName) {
			this.strPatCategoryName = strPatCategoryName;
		}

		public String getStrFatherName() {
			return strFatherName;
		}

		public void setStrFatherName(String strFatherName) {
			this.strFatherName = strFatherName;
		}

		public String getStrAddress() {
			return strAddress;
		}

		public void setStrAddress(String strAddress) {
			this.strAddress = strAddress;
		}

		public String getStrFirstPersonName() {
			return strFirstPersonName;
		}

		public void setStrFirstPersonName(String strFirstPersonName) {
			this.strFirstPersonName = strFirstPersonName;
		}

		public String getStrEmgAddressFlag() {
			return strEmgAddressFlag;
		}

		public void setStrEmgAddressFlag(String strEmgAddressFlag) {
			this.strEmgAddressFlag = strEmgAddressFlag;
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

		public String getStrEmgAddress1() {
			return strEmgAddress1;
		}

		public void setStrEmgAddress1(String strEmgAddress1) {
			this.strEmgAddress1 = strEmgAddress1;
		}

		public String getStrHospDtl() {
			return strHospDtl;
		}

		public void setStrHospDtl(String strHospDtl) {
			this.strHospDtl = strHospDtl;
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

		public String getStrAdvanceAmountDate() {
			return strAdvanceAmountDate;
		}

		public void setStrAdvanceAmountDate(String strAdvanceAmountDate) {
			this.strAdvanceAmountDate = strAdvanceAmountDate;
		}

		public String getStrAdvanceAmountReceiptNo() {
			return strAdvanceAmountReceiptNo;
		}

		public void setStrAdvanceAmountReceiptNo(String strAdvanceAmountReceiptNo) {
			this.strAdvanceAmountReceiptNo = strAdvanceAmountReceiptNo;
		}

		public String getStrAdvanceAmount() {
			return strAdvanceAmount;
		}

		public void setStrAdvanceAmount(String strAdvanceAmount) {
			this.strAdvanceAmount = strAdvanceAmount;
		}

		public String getStrPinCode() {
			return strPinCode;
		}

		public void setStrPinCode(String strPinCode) {
			this.strPinCode = strPinCode;
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

		public String getStrAdmissionCharge() {
			return strAdmissionCharge;
		}

		public void setStrAdmissionCharge(String strAdmissionCharge) {
			this.strAdmissionCharge = strAdmissionCharge;
		}

		public String getStrAdmissionChargeValue() {
			return strAdmissionChargeValue;
		}

		public void setStrAdmissionChargeValue(String strAdmissionChargeValue) {
			this.strAdmissionChargeValue = strAdmissionChargeValue;
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

		public String getStrPatName() {
			return strPatName;
		}

		public void setStrPatName(String strPatName) {
			this.strPatName = strPatName;
		}

		public String getStrStateName() {
			return strStateName;
		}

		public void setStrStateName(String strStateName) {
			this.strStateName = strStateName;
		}

		public String getStrCountryName() {
			return strCountryName;
		}

		public void setStrCountryName(String strCountryName) {
			this.strCountryName = strCountryName;
		}

		public String getStrTehsil() {
			return strTehsil;
		}

		public void setStrTehsil(String strTehsil) {
			this.strTehsil = strTehsil;
		}

		public String getStrPatCatSlip() {
			return strPatCatSlip;
		}

		public void setStrPatCatSlip(String strPatCatSlip) {
			this.strPatCatSlip = strPatCatSlip;
		}

		public String getStrPatCatGrp() {
			return strPatCatGrp;
		}

		public void setStrPatCatGrp(String strPatCatGrp) {
			this.strPatCatGrp = strPatCatGrp;
		}

		public String getStrDOB() {
			return strDOB;
		}

		public void setStrDOB(String strDOB) {
			this.strDOB = strDOB;
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

		public String getStrEmailId() {
			return strEmailId;
		}

		public void setStrEmailId(String strEmailId) {
			this.strEmailId = strEmailId;
		}

		public String getStrDistrictCode() {
			return strDistrictCode;
		}

		public void setStrDistrictCode(String strDistrictCode) {
			this.strDistrictCode = strDistrictCode;
		}


	public String getIsDuplicateSlip() {
		return isDuplicateSlip;
	}

	public void setIsDuplicateSlip(String isDuplicateSlip) {
		this.isDuplicateSlip = isDuplicateSlip;
	}

	public String getCallFromIpdDesk() {
		return callFromIpdDesk;
	}

	public void setCallFromIpdDesk(String callFromIpdDesk) {
		this.callFromIpdDesk = callFromIpdDesk;
	}

	public String getStrProfileId() {
		return strProfileId;
	}

	public void setStrProfileId(String strProfileId) {
		this.strProfileId = strProfileId;
	}

	public String getStrNormalDischargeType() {
		return strNormalDischargeType;
	}

	public void setStrNormalDischargeType(String strNormalDischargeType) {
		this.strNormalDischargeType = strNormalDischargeType;
	}

	public String getStrDeathDetailsRequired() {
		return strDeathDetailsRequired;
	}

	public void setStrDeathDetailsRequired(String strDeathDetailsRequired) {
		this.strDeathDetailsRequired = strDeathDetailsRequired;
	}

	public String getStrAbscondedDetails() {
		return strAbscondedDetails;
	}

	public void setStrAbscondedDetails(String strAbscondedDetails) {
		this.strAbscondedDetails = strAbscondedDetails;
	}

	public String getStrTreatmentResultComboValues()
	{
		return strTreatmentResultComboValues;
	}

	public void setStrTreatmentResultComboValues(String strTreatmentResultComboValues)
	{
		this.strTreatmentResultComboValues = strTreatmentResultComboValues;
	}

	public String getStrTreatmentResult()
	{
		return strTreatmentResult;
	}

	public void setStrTreatmentResult(String strTreatmentResult)
	{
		this.strTreatmentResult = strTreatmentResult;
	}

	public String getStrCurrentDeptUnitRoom() {
		return strCurrentDeptUnitRoom;
	}

	public void setStrCurrentDeptUnitRoom(String strCurrentDeptUnitRoom) {
		this.strCurrentDeptUnitRoom = strCurrentDeptUnitRoom;
	}

	public String getStrAntecedentCauseDeath() {
		return strAntecedentCauseDeath;
	}

	public void setStrAntecedentCauseDeath(String strAntecedentCauseDeath) {
		this.strAntecedentCauseDeath = strAntecedentCauseDeath;
	}

	public String getStrInjuryDetail() {
		return strInjuryDetail;
	}

	public void setStrInjuryDetail(String strInjuryDetail) {
		this.strInjuryDetail = strInjuryDetail;
	}

	public String getStrDescpCauseDeath() {
		return strDescpCauseDeath;
	}

	public void setStrDescpCauseDeath(String strDescpCauseDeath) {
		this.strDescpCauseDeath = strDescpCauseDeath;
	}

	public String getStrDeathFlag() {
		return strDeathFlag;
	}

	public void setStrDeathFlag(String strDeathFlag) {
		this.strDeathFlag = strDeathFlag;
	}

	public String getStrDiagType() {
		return strDiagType;
	}

	public void setStrDiagType(String strDiagType) {
		this.strDiagType = strDiagType;
	}

	public String getStrSearchString() {
		return strSearchString;
	}

	public void setStrSearchString(String strSearchString) {
		this.strSearchString = strSearchString;
	}

	public String getStrSearchMode() {
		return strSearchMode;
	}

	public void setStrSearchMode(String strSearchMode) {
		this.strSearchMode = strSearchMode;
	}

	/**
	 * @return the strClearForDischarge
	 */
	public String getStrClearForDischarge() {
		return strClearForDischarge;
	}

	/**
	 * @param strClearForDischarge the strClearForDischarge to set
	 */
	public void setStrClearForDischarge(String strClearForDischarge) {
		this.strClearForDischarge = strClearForDischarge;
	}

	/**
	 * @return the strICD10CodeValues
	 */
	public String getStrICD10CodeValues() {
		return strICD10CodeValues;
	}

	/**
	 * @param strICD10CodeValues the strICD10CodeValues to set
	 */
	public void setStrICD10CodeValues(String strICD10CodeValues) {
		this.strICD10CodeValues = strICD10CodeValues;
	}

	/**
	 * @return the strAdvisedBy
	 */
	public String getStrAdvisedBy() {
		return strAdvisedBy;
	}

	/**
	 * @param strAdvisedBy the strAdvisedBy to set
	 */
	public void setStrAdvisedBy(String strAdvisedBy) {
		this.strAdvisedBy = strAdvisedBy;
	}

	/**
	 * @return the setStrRemarksOnline
	 */
	public String getStrRemarksOnline() {
		return strRemarksOnline;
	}

	/**
	 * @param setStrRemarksOnline the setStrRemarksOnline to set
	 */
	public void setStrRemarksOnline(String strRemarksOnline) {
		this.strRemarksOnline = strRemarksOnline;
	}

	/**
	 * @return the strDischargeTypeName
	 */
	public String getStrDischargeTypeName() {
		return strDischargeTypeName;
	}

	/**
	 * @param strDischargeTypeName the strDischargeTypeName to set
	 */
	public void setStrDischargeTypeName(String strDischargeTypeName) {
		this.strDischargeTypeName = strDischargeTypeName;
	}

	/**
	 * @return the strDischargeType
	 */
	public String getStrDischargeType() {
		return strDischargeType;
	}

	/**
	 * @param strDischargeType the strDischargeType to set
	 */
	public void setStrDischargeType(String strDischargeType) {
		this.strDischargeType = strDischargeType;
	}

	/**
	 * @return the strAdviceDate
	 */
	public String getStrAdviceDate() {
		return strAdviceDate;
	}

	/**
	 * @param strAdviceDate the strAdviceDate to set
	 */
	public void setStrAdviceDate(String strAdviceDate) {
		this.strAdviceDate = strAdviceDate;
	}

	/**
	 * @return the strAbscondedValue
	 */
	public String getStrAbscondedValue() {
		return strAbscondedValue;
	}

	/**
	 * @param strAbscondedValue the strAbscondedValue to set
	 */
	public void setStrAbscondedValue(String strAbscondedValue) {
		this.strAbscondedValue = strAbscondedValue;
	}

	/**
	 * @return the strDeptName
	 */
	public String getStrDeptName() {
		return strDeptName;
	}

	/**
	 * @param strDeptName the strDeptName to set
	 */
	public void setStrDeptName(String strDeptName) {
		this.strDeptName = strDeptName;
	}

	/**
	 * @return the strUnitName
	 */
	public String getStrUnitName() {
		return strUnitName;
	}

	/**
	 * @param strUnitName the strUnitName to set
	 */
	public void setStrUnitName(String strUnitName) {
		this.strUnitName = strUnitName;
	}

	/**
	 * @return the strPatCatCode
	 */
	public String getStrPatCatCode() {
		return strPatCatCode;
	}

	/**
	 * @param strPatCatCode the strPatCatCode to set
	 */
	public void setStrPatCatCode(String strPatCatCode) {
		this.strPatCatCode = strPatCatCode;
	}

	/**
	 * @return the strOccRelation
	 */
	public String getStrOccRelation() {
		return strOccRelation;
	}

	/**
	 * @param strOccRelation the strOccRelation to set
	 */
	public void setStrOccRelation(String strOccRelation) {
		this.strOccRelation = strOccRelation;
	}

	/**
	 * @return the strVisitNo
	 */
	public String getStrVisitNo() {
		return strVisitNo;
	}

	/**
	 * @param strVisitNo the strVisitNo to set
	 */
	public void setStrVisitNo(String strVisitNo) {
		this.strVisitNo = strVisitNo;
	}

	/**
	 * @return the strIsUrban
	 */
	public String getStrIsUrban() {
		return strIsUrban;
	}

	/**
	 * @param strIsUrban the strIsUrban to set
	 */
	public void setStrIsUrban(String strIsUrban) {
		this.strIsUrban = strIsUrban;
	}

	/**
	 * @return the strOccEmpName
	 */
	public String getStrOccEmpName() {
		return strOccEmpName;
	}

	/**
	 * @param strOccEmpName the strOccEmpName to set
	 */
	public void setStrOccEmpName(String strOccEmpName) {
		this.strOccEmpName = strOccEmpName;
	}

	/**
	 * @return the strNewBorn
	 */
	public String getStrNewBorn() {
		return strNewBorn;
	}

	/**
	 * @param strNewBorn the strNewBorn to set
	 */
	public void setStrNewBorn(String strNewBorn) {
		this.strNewBorn = strNewBorn;
	}

	/**
	 * @return the strServAreaCode
	 */
	public String getStrServAreaCode() {
		return strServAreaCode;
	}

	/**
	 * @param strServAreaCode the strServAreaCode to set
	 */
	public void setStrServAreaCode(String strServAreaCode) {
		this.strServAreaCode = strServAreaCode;
	}

	/**
	 * @return the strMsApprovalStatus
	 */
	public String getStrMsApprovalStatus() {
		return strMsApprovalStatus;
	}

	/**
	 * @param strMsApprovalStatus the strMsApprovalStatus to set
	 */
	public void setStrMsApprovalStatus(String strMsApprovalStatus) {
		this.strMsApprovalStatus = strMsApprovalStatus;
	}

	/**
	 * @return the strPopUp
	 */
	public String getStrPopUp() {
		return strPopUp;
	}

	/**
	 * @param strPopUp the strPopUp to set
	 */
	public void setStrPopUp(String strPopUp) {
		this.strPopUp = strPopUp;
	}

	/**
	 * @return the strEntryDate
	 */
	public String getStrEntryDate() {
		return strEntryDate;
	}

	/**
	 * @param strEntryDate the strEntryDate to set
	 */
	public void setStrEntryDate(String strEntryDate) {
		this.strEntryDate = strEntryDate;
	}

	/**
	 * @return the strOldWardCode
	 */
	public String getStrOldWardCode() {
		return strOldWardCode;
	}

	/**
	 * @param strOldWardCode the strOldWardCode to set
	 */
	public void setStrOldWardCode(String strOldWardCode) {
		this.strOldWardCode = strOldWardCode;
	}

	/**
	 * @return the strOldRoomCode
	 */
	public String getStrOldRoomCode() {
		return strOldRoomCode;
	}

	/**
	 * @param strOldRoomCode the strOldRoomCode to set
	 */
	public void setStrOldRoomCode(String strOldRoomCode) {
		this.strOldRoomCode = strOldRoomCode;
	}

	/**
	 * @return the strOldBedCode
	 */
	public String getStrOldBedCode() {
		return strOldBedCode;
	}

	/**
	 * @param strOldBedCode the strOldBedCode to set
	 */
	public void setStrOldBedCode(String strOldBedCode) {
		this.strOldBedCode = strOldBedCode;
	}

	/**
	 * @return the strTransFlg
	 */
	public String getStrTransFlg() {
		return strTransFlg;
	}

	/**
	 * @param strTransFlg the strTransFlg to set
	 */
	public void setStrTransFlg(String strTransFlg) {
		this.strTransFlg = strTransFlg;
	}

	/**
	 * @return the strPvtBedFlg
	 */
	public String getStrPvtBedFlg() {
		return strPvtBedFlg;
	}

	/**
	 * @param strPvtBedFlg the strPvtBedFlg to set
	 */
	public void setStrPvtBedFlg(String strPvtBedFlg) {
		this.strPvtBedFlg = strPvtBedFlg;
	}

	/**
	 * @return the strCmbRed
	 */
	public String getStrCmbRed() {
		return strCmbRed;
	}

	/**
	 * @param strCmbRed the strCmbRed to set
	 */
	public void setStrCmbRed(String strCmbRed) {
		this.strCmbRed = strCmbRed;
	}

	/**
	 * @return the roomTypeWS
	 */
	public WebRowSet getRoomTypeWS() {
		return roomTypeWS;
	}

	/**
	 * @param roomTypeWS the roomTypeWS to set
	 */
	public void setRoomTypeWS(WebRowSet roomTypeWS) {
		this.roomTypeWS = roomTypeWS;
	}

	/**
	 * @return the strServAreaWS
	 */
	public WebRowSet getStrServAreaWS() {
		return strServAreaWS;
	}

	/**
	 * @param strServAreaWS the strServAreaWS to set
	 */
	public void setStrServAreaWS(WebRowSet strServAreaWS) {
		this.strServAreaWS = strServAreaWS;
	}

	/**
	 * @return the wardWS
	 */
	public WebRowSet getWardWS() {
		return wardWS;
	}

	/**
	 * @param wardWS the wardWS to set
	 */
	public void setWardWS(WebRowSet wardWS) {
		this.wardWS = wardWS;
	}

	/**
	 * @return the roomWs
	 */
	public WebRowSet getRoomWs() {
		return roomWs;
	}

	/**
	 * @param roomWs the roomWs to set
	 */
	public void setRoomWs(WebRowSet roomWs) {
		this.roomWs = roomWs;
	}

	/**
	 * @return the bedTypeWS
	 */
	public WebRowSet getBedTypeWS() {
		return bedTypeWS;
	}

	/**
	 * @param bedTypeWS the bedTypeWS to set
	 */
	public void setBedTypeWS(WebRowSet bedTypeWS) {
		this.bedTypeWS = bedTypeWS;
	}

	/**
	 * @return the bedDetailWS
	 */
	public WebRowSet getBedDetailWS() {
		return bedDetailWS;
	}

	/**
	 * @param bedDetailWS the bedDetailWS to set
	 */
	public void setBedDetailWS(WebRowSet bedDetailWS) {
		this.bedDetailWS = bedDetailWS;
	}

	/**
	 * @return the wardTypeWS
	 */
	public WebRowSet getWardTypeWS() {
		return wardTypeWS;
	}

	/**
	 * @param wardTypeWS the wardTypeWS to set
	 */
	public void setWardTypeWS(WebRowSet wardTypeWS) {
		this.wardTypeWS = wardTypeWS;
	}

	/**
	 * @return the strDeptUntRomCode
	 */
	public String getStrDeptUntRomCode() {
		return strDeptUntRomCode;
	}

	/**
	 * @param strDeptUntRomCode the strDeptUntRomCode to set
	 */
	public void setStrDeptUntRomCode(String strDeptUntRomCode) {
		this.strDeptUntRomCode = strDeptUntRomCode;
	}

	/**
	 * @return the wsIcd10Diagnosis
	 */
	public WebRowSet getWsIcd10Diagnosis() {
		return wsIcd10Diagnosis;
	}

	/**
	 * @param wsIcd10Diagnosis the wsIcd10Diagnosis to set
	 */
	public void setWsIcd10Diagnosis(WebRowSet wsIcd10Diagnosis) {
		this.wsIcd10Diagnosis = wsIcd10Diagnosis;
	}

	/**
	 * @return the strDeathCauseComboValues
	 */
	public String getStrDeathCauseComboValues() {
		return strDeathCauseComboValues;
	}

	/**
	 * @param strDeathCauseComboValues the strDeathCauseComboValues to set
	 */
	public void setStrDeathCauseComboValues(String strDeathCauseComboValues) {
		this.strDeathCauseComboValues = strDeathCauseComboValues;
	}

	/**
	 * @return the strIsFemale
	 */
	public String getStrIsFemale() {
		return strIsFemale;
	}

	/**
	 * @param strIsFemale the strIsFemale to set
	 */
	public void setStrIsFemale(String strIsFemale) {
		this.strIsFemale = strIsFemale;
	}

	public String getIsDead() {
		return isDead;
	}

	public void setIsDead(String dead) {
		isDead = dead;
	}
	
	public String getStrAdmissionNo() {
		return strAdmissionNo;
	}

	public void setStrAdmissionNo(String strAdmissionNo) {
		this.strAdmissionNo = strAdmissionNo;
	}

	public String getStrSaveStatus() {
		return strSaveStatus;
	}

	public void setStrSaveStatus(String strSaveStatus) {
		this.strSaveStatus = strSaveStatus;
	}

	/**
	 * @return the strSeatID
	 */
	public String getStrSeatID() {
		return strSeatID;
	}

	/**
	 * @param strSeatID the strSeatID to set
	 */
	public void setStrSeatID(String strSeatID) {
		this.strSeatID = strSeatID;
	}

	/**
	 * @return the curAdmNo
	 */
	public String getCurAdmNo() {
		return curAdmNo;
	}

	/**
	 * @param curAdmNo the curAdmNo to set
	 */
	public void setCurAdmNo(String curAdmNo) {
		this.curAdmNo = curAdmNo;
	}

	/**
	 * @return the strHospitalCode
	 */
	public String getStrHospitalCode() {
		return strHospitalCode;
	}

	/**
	 * @param strHospitalCode the strHospitalCode to set
	 */
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	/**
	 * @return the strProvisionDiagnosis
	 */
	public String[] getStrProvisionDiagnosis() {
		return strProvisionDiagnosis;
	}

	/**
	 * @param strProvisionDiagnosis the strProvisionDiagnosis to set
	 */
	public void setStrProvisionDiagnosis(String[] strProvisionDiagnosis) {
		this.strProvisionDiagnosis = strProvisionDiagnosis;
	}

	/**
	 * @return the strOnsetDeathDateAndTime
	 */
	public String getStrOnsetDeathDateAndTime() {
		return strOnsetDeathDateAndTime;
	}

	/**
	 * @param strOnsetDeathDateAndTime the strOnsetDeathDateAndTime to set
	 */
	public void setStrOnsetDeathDateAndTime(String strOnsetDeathDateAndTime) {
		this.strOnsetDeathDateAndTime = strOnsetDeathDateAndTime;
	}

	/**
	 * @return the strVerifyDateAndTime
	 */
	public String getStrVerifyDateAndTime() {
		return strVerifyDateAndTime;
	}

	/**
	 * @param strVerifyDateAndTime the strVerifyDateAndTime to set
	 */
	public void setStrVerifyDateAndTime(String strVerifyDateAndTime) {
		this.strVerifyDateAndTime = strVerifyDateAndTime;
	}

	/**
	 * @return the strHandoverDateAndTime
	 */
	public String getStrHandoverDateAndTime() {
		return strHandoverDateAndTime;
	}

	/**
	 * @param strHandoverDateAndTime the strHandoverDateAndTime to set
	 */
	public void setStrHandoverDateAndTime(String strHandoverDateAndTime) {
		this.strHandoverDateAndTime = strHandoverDateAndTime;
	}

	/**
	 * @return the strShiftDateAndTime
	 */
	public String getStrShiftDateAndTime() {
		return strShiftDateAndTime;
	}

	/**
	 * @param strShiftDateAndTime the strShiftDateAndTime to set
	 */
	public void setStrShiftDateAndTime(String strShiftDateAndTime) {
		this.strShiftDateAndTime = strShiftDateAndTime;
	}

	/**
	 * @return the strDeathCauseID
	 */
	public String getStrDeathCauseID() {
		return strDeathCauseID;
	}

	/**
	 * @param strDeathCauseID the strDeathCauseID to set
	 */
	public void setStrDeathCauseID(String strDeathCauseID) {
		this.strDeathCauseID = strDeathCauseID;
	}

	/**
	 * @return the strIsSiftToMortuary
	 */
	public String getStrIsSiftToMortuary() {
		return strIsSiftToMortuary;
	}

	/**
	 * @param strIsSiftToMortuary the strIsSiftToMortuary to set
	 */
	public void setStrIsSiftToMortuary(String strIsSiftToMortuary) {
		this.strIsSiftToMortuary = strIsSiftToMortuary;
	}

	/**
	 * @return the strHandoverTo
	 */
	public String getStrHandoverTo() {
		return strHandoverTo;
	}

	/**
	 * @param strHandoverTo the strHandoverTo to set
	 */
	public void setStrHandoverTo(String strHandoverTo) {
		this.strHandoverTo = strHandoverTo;
	}

	/**
	 * @return the strIsPregnant
	 */
	public String getStrIsPregnant() {
		return strIsPregnant;
	}

	/**
	 * @param strIsPregnant the strIsPregnant to set
	 */
	public void setStrIsPregnant(String strIsPregnant) {
		this.strIsPregnant = strIsPregnant;
	}

	/**
	 * @return the strIsDelivery
	 */
	public String getStrIsDelivery() {
		return strIsDelivery;
	}

	/**
	 * @param strIsDelivery the strIsDelivery to set
	 */
	public void setStrIsDelivery(String strIsDelivery) {
		this.strIsDelivery = strIsDelivery;
	}

	/**
	 * @return the strTransferUnit
	 */
	public String getStrTransferUnit() {
		return strTransferUnit;
	}

	/**
	 * @param strTransferUnit the strTransferUnit to set
	 */
	public void setStrTransferUnit(String strTransferUnit) {
		this.strTransferUnit = strTransferUnit;
	}


	/**
	 * @return the strIsDead
	 */
	public String getStrIsDead() {
		return strIsDead;
	}

	/**
	 * @param strIsDead the strIsDead to set
	 */
	public void setStrIsDead(String strIsDead) {
		this.strIsDead = strIsDead;
	}

	/**
	 * @return the strDeathDetails
	 */
	public String getStrDeathDetails() {
		return strDeathDetails;
	}

	/**
	 * @param strDeathDetails the strDeathDetails to set
	 */
	public void setStrDeathDetails(String strDeathDetails) {
		this.strDeathDetails = strDeathDetails;
	}

	/**
	 * @return the strDeathMannerComboValues
	 */
	public String getStrDeathMannerComboValues() {
		return strDeathMannerComboValues;
	}

	/**
	 * @param strDeathMannerComboValues the strDeathMannerComboValues to set
	 */
	public void setStrDeathMannerComboValues(String strDeathMannerComboValues) {
		this.strDeathMannerComboValues = strDeathMannerComboValues;
	}

	/**
	 * @return the strDeathDateAndTime
	 */
	public String getStrDeathDateAndTime() {
		return strDeathDateAndTime;
	}

	/**
	 * @param strDeathDateAndTime the strDeathDateAndTime to set
	 */
	public void setStrDeathDateAndTime(String strDeathDateAndTime) {
		this.strDeathDateAndTime = strDeathDateAndTime;
	}

	/**
	 * @return the strDeathCauseIM
	 */
	public String getStrDeathCauseIM() {
		return strDeathCauseIM;
	}

	/**
	 * @param strDeathCauseIM the strDeathCauseIM to set
	 */
	public void setStrDeathCauseIM(String strDeathCauseIM) {
		this.strDeathCauseIM = strDeathCauseIM;
	}

	/**
	 * @return the strDeathCauseAN
	 */
	public String getStrDeathCauseAN() {
		return strDeathCauseAN;
	}

	/**
	 * @param strDeathCauseAN the strDeathCauseAN to set
	 */
	public void setStrDeathCauseAN(String strDeathCauseAN) {
		this.strDeathCauseAN = strDeathCauseAN;
	}

	/**
	 * @return the strDeathManner
	 */
	public String getStrDeathManner() {
		return strDeathManner;
	}

	/**
	 * @param strDeathManner the strDeathManner to set
	 */
	public void setStrDeathManner(String strDeathManner) {
		this.strDeathManner = strDeathManner;
	}

	

	/**
	 * @return the strDeathMannerID
	 */
	public String getStrDeathMannerID() {
		return strDeathMannerID;
	}

	/**
	 * @param strDeathMannerID the strDeathMannerID to set
	 */
	public void setStrDeathMannerID(String strDeathMannerID) {
		this.strDeathMannerID = strDeathMannerID;
	}

	/**
	 * @return the strPatientDischargeTypeComboValues
	 */
	public String getStrPatientDischargeTypeComboValues() {
		return strPatientDischargeTypeComboValues;
	}

	/**
	 * @param strPatientDischargeTypeComboValues the strPatientDischargeTypeComboValues to set
	 */
	public void setStrPatientDischargeTypeComboValues(
			String strPatientDischargeTypeComboValues) {
		this.strPatientDischargeTypeComboValues = strPatientDischargeTypeComboValues;
	}

	/**
	 * @return the strDiagnosisTypeCombo
	 */
	public String getStrDiagnosisTypeCombo() {
		return strDiagnosisTypeCombo;
	}

	/**
	 * @param strDiagnosisTypeCombo the strDiagnosisTypeCombo to set
	 */
	public void setStrDiagnosisTypeCombo(String strDiagnosisTypeCombo) {
		this.strDiagnosisTypeCombo = strDiagnosisTypeCombo;
	}

	/**
	 * @return the strProvisionDiagnosisValues
	 */
	public String getStrProvisionDiagnosisValues() {
		return strProvisionDiagnosisValues;
	}

	/**
	 * @param strProvisionDiagnosisValues the strProvisionDiagnosisValues to set
	 */
	public void setStrProvisionDiagnosisValues(String strProvisionDiagnosisValues) {
		this.strProvisionDiagnosisValues = strProvisionDiagnosisValues;
	}



	/**
	 * @return the strDiagnosisType
	 */
	public String getStrDiagnosisType() {
		return strDiagnosisType;
	}

	/**
	 * @param strDiagnosisType the strDiagnosisType to set
	 */
	public void setStrDiagnosisType(String strDiagnosisType) {
		this.strDiagnosisType = strDiagnosisType;
	}

	/**
	 * @return the wsFinalDiagnosis
	 */
	public WebRowSet getWsFinalDiagnosis() {
		return wsFinalDiagnosis;
	}

	/**
	 * @param wsFinalDiagnosis the wsFinalDiagnosis to set
	 */
	public void setWsFinalDiagnosis(WebRowSet wsFinalDiagnosis) {
		this.wsFinalDiagnosis = wsFinalDiagnosis;
	}

	/**
	 * @return the strPatientDisParam
	 */
	public String getStrPatientDisParam() {
		return strPatientDisParam;
	}

	/**
	 * @param strPatientDisParam the strPatientDisParam to set
	 */
	public void setStrPatientDisParam(String strPatientDisParam) {
		this.strPatientDisParam = strPatientDisParam;
	}



	/**
	 * @return the strPatientDisParamDetails
	 */
	public String[] getStrPatientDisParamDetails() {
		return strPatientDisParamDetails;
	}

	/**
	 * @param strPatientDisParamDetails the strPatientDisParamDetails to set
	 */
	public void setStrPatientDisParamDetails(String[] strPatientDisParamDetails) {
		this.strPatientDisParamDetails = strPatientDisParamDetails;
	}

	/**
	 * @return the strComponentName
	 */
	public String[] getStrComponentName() {
		return strComponentName;
	}

	/**
	 * @param strComponentName the strComponentName to set
	 */
	public void setStrComponentName(String[] strComponentName) {
		this.strComponentName = strComponentName;
	}

	/**
	 * @return the strComponentID
	 */
	public String[] getStrComponentID() {
		return strComponentID;
	}

	/**
	 * @param strComponentID the strComponentID to set
	 */
	public void setStrComponentID(String[] strComponentID) {
		this.strComponentID = strComponentID;
	}

	public String getStrdeptproperty() {
		return strdeptproperty;
	}

	public void setStrdeptproperty(String strdeptproperty) {
		this.strdeptproperty = strdeptproperty;
	}

	public String[] getStrhward() {
		return strhward;
	}

	public void setStrhward(String[] strhward) {
		this.strhward = strhward;
	}

	public String getStrwardproperty() {
		return strwardproperty;
	}

	public void setStrwardproperty(String strwardproperty) {
		this.strwardproperty = strwardproperty;
	}

	public String getStrunitproperty() {
		return strunitproperty;
	}

	public void setStrunitproperty(String strunitproperty) {
		this.strunitproperty = strunitproperty;
	}

	public String getStrroomproperty() {
		return strroomproperty;
	}

	public void setStrroomproperty(String strroomproperty) {
		this.strroomproperty = strroomproperty;
	}

	public WebRowSet getStrDepartmenttWs() {
		return strDepartmenttWs;
	}

	public void setStrDepartmenttWs(WebRowSet strDepartmenttWs) {
		this.strDepartmenttWs = strDepartmenttWs;
	}

	public WebRowSet getStrUnitWs() {
		return strUnitWs;
	}

	public void setStrUnitWs(WebRowSet strUnitWs) {
		this.strUnitWs = strUnitWs;
	}

	public WebRowSet getStrWardWs() {
		return strWardWs;
	}

	public void setStrWardWs(WebRowSet strWardWs) {
		this.strWardWs = strWardWs;
	}

	public WebRowSet getStrRoomWs() {
		return strRoomWs;
	}

	public void setStrRoomWs(WebRowSet strRoomWs) {
		this.strRoomWs = strRoomWs;
	}

	public String getStrDepartment() {
		return strDepartment;
	}

	public void setStrDepartment(String strDepartment) {
		this.strDepartment = strDepartment;
	}

	public String getStrUnit() {
		return strUnit;
	}

	public void setStrUnit(String strUnit) {
		this.strUnit = strUnit;
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

	public String getStrBId() {
		return strBId;
	}

	public void setStrBId(String strBId) {
		this.strBId = strBId;
	}

	public String getStrExpnseAmt() {
		return strExpnseAmt;
	}

	public void setStrExpnseAmt(String strExpnseAmt) {
		this.strExpnseAmt = strExpnseAmt;
	}

	public String getStrRecFrClnt() {
		return strRecFrClnt;
	}

	public void setStrRecFrClnt(String strRecFrClnt) {
		this.strRecFrClnt = strRecFrClnt;
	}



	public String getStrDisAmt() {
		return strDisAmt;
	}

	public void setStrDisAmt(String strDisAmt) {
		this.strDisAmt = strDisAmt;
	}

	public String getStrRecFrPat() {
		return strRecFrPat;
	}

	public void setStrRecFrPat(String strRecFrPat) {
		this.strRecFrPat = strRecFrPat;
	}

	public String getStrNetAmt() {
		return strNetAmt;
	}

	public void setStrNetAmt(String strNetAmt) {
		this.strNetAmt = strNetAmt;
	}

	public String getStrDetls() {
		return strDetls;
	}

	public void setStrDetls(String strDetls) {
		this.strDetls = strDetls;
	}





	public WebRowSet getStrDisBy() {
		return strDisBy;
	}

	public void setStrDisBy(WebRowSet strDisBy) {
		this.strDisBy = strDisBy;
	}

	public WebRowSet getStrDisRsn() {
		return strDisRsn;
	}

	public void setStrDisRsn(WebRowSet strDisRsn) {
		this.strDisRsn = strDisRsn;
	}

	public String getStrAccPreDis() {
		return strAccPreDis;
	}

	public void setStrAccPreDis(String strAccPreDis) {
		this.strAccPreDis = strAccPreDis;
	}

	public String getStrCrNo() {
		return strCrNo;
	}

	public void setStrCrNo(String strCrNo) {
		this.strCrNo = strCrNo;
	}

	public String getStrChk() {
		return strChk;
	}

	public void setStrChk(String strChk) {
		this.strChk = strChk;
	}



	public String getStrMsgType() {
		return strMsgType;
	}

	public void setStrMsgType(String strMsgType) {
		this.strMsgType = strMsgType;
	}

	public WebRowSet getStrPatientDetailsWs() {
		return strPatientDetailsWs;
	}

	public void setStrPatientDetailsWs(WebRowSet strPatientDetailsWs) {
		this.strPatientDetailsWs = strPatientDetailsWs;
	}

	public WebRowSet getStrOnLineReqDiscountWs() {
		return strOnLineReqDiscountWs;
	}

	public void setStrOnLineReqDiscountWs(WebRowSet strOnLineReqDiscountWs) {
		this.strOnLineReqDiscountWs = strOnLineReqDiscountWs;
	}

	public String getStrCrNo1() {
		return strCrNo1;
	}

	public void setStrCrNo1(String strCrNo1) {
		this.strCrNo1 = strCrNo1;
	}

	public String getStrApproval_id() {
		return strApproval_id;
	}

	public void setStrApproval_id(String strApproval_id) {
		this.strApproval_id = strApproval_id;
	}

	public String getLastDis() {
		return lastDis;
	}

	public void setLastDis(String lastDis) {
		this.lastDis = lastDis;
	}

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	public String getStrRsn() {
		return strRsn;
	}

	public void setStrRsn(String strRsn) {
		this.strRsn = strRsn;
	}

	public String getStrRmk() {
		return strRmk;
	}

	public void setStrRmk(String strRmk) {
		this.strRmk = strRmk;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
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

	public String getStrTreatmentCategoryCode() {
		return strTreatmentCategoryCode;
	}

	public void setStrTreatmentCategoryCode(String strTreatmentCategoryCode) {
		this.strTreatmentCategoryCode = strTreatmentCategoryCode;
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

	public String getStrEpisodeCode() {
		return strEpisodeCode;
	}

	public void setStrEpisodeCode(String strEpisodeCode) {
		this.strEpisodeCode = strEpisodeCode;
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

	public String getStrBedCode() {
		return strBedCode;
	}

	public void setStrBedCode(String strBedCode) {
		this.strBedCode = strBedCode;
	}

	public String getStrDepartmentName() {
		return strDepartmentName;
	}

	public void setStrDepartmentName(String strDepartmentName) {
		this.strDepartmentName = strDepartmentName;
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

	public String getStrMsg() {
		return strMsg;
	}

	public void setStrMsg(String strMsg) {
		this.strMsg = strMsg;
	}

	public String getStrBed() {
		return strBed;
	}

	public void setStrBed(String strBed) {
		this.strBed = strBed;
	}

	public String getStrBedProperty() {
		return strBedProperty;
	}

	public void setStrBedProperty(String strBedProperty) {
		this.strBedProperty = strBedProperty;
	}

	public String getStrServArea() {
		return strServArea;
	}

	public void setStrServArea(String strServArea) {
		this.strServArea = strServArea;
	}

	public String getStrCtDate() {
		return strCtDate;
	}

	public void setStrCtDate(String strCtDate) {
		this.strCtDate = strCtDate;
	}

	public String getStrValidFromDp() {
		return strValidFromDp;
	}

	public void setStrValidFromDp(String strValidFromDp) {
		this.strValidFromDp = strValidFromDp;
	}

	public String getStrValidFromDp1() {
		return strValidFromDp1;
	}

	public void setStrValidFromDp1(String strValidFromDp1) {
		this.strValidFromDp1 = strValidFromDp1;
	}

	public String getStrValidFrom() {
		return strValidFrom;
	}

	public void setStrValidFrom(String strValidFrom) {
		this.strValidFrom = strValidFrom;
	}

	public String getStrValidTo() {
		return strValidTo;
	}

	public void setStrValidTo(String strValidTo) {
		this.strValidTo = strValidTo;
	}

	public String getStrPatCondL() {
		return strPatCondL;
	}

	public void setStrPatCondL(String strPatCondL) {
		this.strPatCondL = strPatCondL;
	}

	public String getStrAdviceL() {
		return strAdviceL;
	}

	public void setStrAdviceL(String strAdviceL) {
		this.strAdviceL = strAdviceL;
	}

	public String getStrDaysOnFinalDischarge() {
		return strDaysOnFinalDischarge;
	}

	public void setStrDaysOnFinalDischarge(String strDaysOnFinalDischarge) {
		this.strDaysOnFinalDischarge = strDaysOnFinalDischarge;
	}

	public String getStrErrMsgString() {
		return strErrMsgString;
	}

	public void setStrErrMsgString(String strErrMsgString) {
		this.strErrMsgString = strErrMsgString;
	}

	public String getStrNormalMsgString() {
		return strNormalMsgString;
	}

	public void setStrNormalMsgString(String strNormalMsgString) {
		this.strNormalMsgString = strNormalMsgString;
	}

	public String getStrWarningMsgString() {
		return strWarningMsgString;
	}

	public void setStrWarningMsgString(String strWarningMsgString) {
		this.strWarningMsgString = strWarningMsgString;
	}

	public String getStrMsgString() {
		return strMsgString;
	}

	public void setStrMsgString(String strMsgString) {
		this.strMsgString = strMsgString;
	}

	public String getStrMLCDetails() {
		return strMLCDetails;
	}

	public void setStrMLCDetails(String strMLCDetails) {
		this.strMLCDetails = strMLCDetails;
	}

	public String getStrIsMLC() {
		return strIsMLC;
	}

	public void setStrIsMLC(String strIsMLC) {
		this.strIsMLC = strIsMLC;
	}

	public WebRowSet getStrCheckAdminWS() {
		return strCheckAdminWS;
	}

	public void setStrCheckAdminWS(WebRowSet strCheckAdminWS) {
		this.strCheckAdminWS = strCheckAdminWS;
	}

	public String getStrPoliceInfrmDtMLC() {
		return strPoliceInfrmDtMLC;
	}

	public void setStrPoliceInfrmDtMLC(String strPoliceInfrmDtMLC) {
		this.strPoliceInfrmDtMLC = strPoliceInfrmDtMLC;
	}

	public String getStrApprovDtMLC() {
		return strApprovDtMLC;
	}

	public void setStrApprovDtMLC(String strApprovDtMLC) {
		this.strApprovDtMLC = strApprovDtMLC;
	}

	public String getStrApprovByMLC() {
		return strApprovByMLC;
	}

	public void setStrApprovByMLC(String strApprovByMLC) {
		this.strApprovByMLC = strApprovByMLC;
	}

	public String getStrNxtVisitOPD() {
		return strNxtVisitOPD;
	}

	public void setStrNxtVisitOPD(String strNxtVisitOPD) {
		this.strNxtVisitOPD = strNxtVisitOPD;
	}

	public String getStrRoomOPD() {
		return strRoomOPD;
	}

	public void setStrRoomOPD(String strRoomOPD) {
		this.strRoomOPD = strRoomOPD;
	}

	public String getStrAdmStatCode() {
		return strAdmStatCode;
	}

	public void setStrAdmStatCode(String strAdmStatCode) {
		this.strAdmStatCode = strAdmStatCode;
	}

	public String getStrDischrg_Mode() {
		return strDischrg_Mode;
	}

	public void setStrDischrg_Mode(String strDischrg_Mode) {
		this.strDischrg_Mode = strDischrg_Mode;
	}

	public String getStrDischrg_Param_Req() {
		return strDischrg_Param_Req;
	}

	public void setStrDischrg_Param_Req(String strDischrg_Param_Req) {
		this.strDischrg_Param_Req = strDischrg_Param_Req;
	}

	public String getStrDisplay_MLC_Dtls() {
		return strDisplay_MLC_Dtls;
	}

	public void setStrDisplay_MLC_Dtls(String strDisplay_MLC_Dtls) {
		this.strDisplay_MLC_Dtls = strDisplay_MLC_Dtls;
	}

	public String getStrDisplay_OPD_Visit_Dtls() {
		return strDisplay_OPD_Visit_Dtls;
	}

	public void setStrDisplay_OPD_Visit_Dtls(String strDisplay_OPD_Visit_Dtls) {
		this.strDisplay_OPD_Visit_Dtls = strDisplay_OPD_Visit_Dtls;
	}

	public String getStrApprovRmkMLC() {
		return strApprovRmkMLC;
	}

	public void setStrApprovRmkMLC(String strApprovRmkMLC) {
		this.strApprovRmkMLC = strApprovRmkMLC;
	}

	public String getStrApprovalComboMode() {
		return strApprovalComboMode;
	}

	public void setStrApprovalComboMode(String strApprovalComboMode) {
		this.strApprovalComboMode = strApprovalComboMode;
	}

	public WebRowSet getStrApprovByWS_MLC() {
		return strApprovByWS_MLC;
	}

	public void setStrApprovByWS_MLC(WebRowSet strApprovByWS_MLC) {
		this.strApprovByWS_MLC = strApprovByWS_MLC;
	}

	public WebRowSet getStrRoomOPDWS() {
		return strRoomOPDWS;
	}

	public void setStrRoomOPDWS(WebRowSet strRoomOPDWS) {
		this.strRoomOPDWS = strRoomOPDWS;
	}

	public String getCurDept_Unt_RomCode() {
		return curDept_Unt_RomCode;
	}

	public void setCurDept_Unt_RomCode(String curDept_Unt_RomCode) {
		this.curDept_Unt_RomCode = curDept_Unt_RomCode;
	}

	public String getStrHlpMLC() {
		return strHlpMLC;
	}

	public void setStrHlpMLC(String strHlpMLC) {
		this.strHlpMLC = strHlpMLC;
	}

	public String getStrHlpOPD() {
		return strHlpOPD;
	}

	public void setStrHlpOPD(String strHlpOPD) {
		this.strHlpOPD = strHlpOPD;
	}

	public String getStrAjaxVAL() {
		return strAjaxVAL;
	}

	public void setStrAjaxVAL(String strAjaxVAL) {
		this.strAjaxVAL = strAjaxVAL;
	}

	public String getStrApplicationMode() {
		return strApplicationMode;
	}

	public void setStrApplicationMode(String strApplicationMode) {
		this.strApplicationMode = strApplicationMode;
	}

	/**
	 * @return the strReportId
	 */
	public String getStrReportId() {
		return strReportId;
	}

	/**
	 * @param strReportId the strReportId to set
	 */
	public void setStrReportId(String strReportId) {
		this.strReportId = strReportId;
	}

	public String getStrDiagnosticTypeValues() {
		return strDiagnosticTypeValues;
	}

	public void setStrDiagnosticTypeValues(String strDiagnosticTypeValues) {
		this.strDiagnosticTypeValues = strDiagnosticTypeValues;
	}

	public String[] getStrDiagCodeType() {
		return strDiagCodeType;
	}

	public void setStrDiagCodeType(String[] strDiagCodeType) {
		this.strDiagCodeType = strDiagCodeType;
	}

	public String[] getStrisRepaeat() {
		return strisRepaeat;
	}

	public void setStrisRepaeat(String[] strisRepaeat) {
		this.strisRepaeat = strisRepaeat;
	}

	public String[] getStrDiagRemarks() {
		return strDiagRemarks;
	}

	public void setStrDiagRemarks(String[] strDiagRemarks) {
		this.strDiagRemarks = strDiagRemarks;
	}

	public String[] getStrDiseaseSite() {
		return strDiseaseSite;
	}

	public void setStrDiseaseSite(String[] strDiseaseSite) {
		this.strDiseaseSite = strDiseaseSite;
	}

	public String[] getStrDiagTypeCode() {
		return strDiagTypeCode;
	}

	public void setStrDiagTypeCode(String[] strDiagTypeCode) {
		this.strDiagTypeCode = strDiagTypeCode;
	}

	public String getStrDischargeDiagnosisRequired() {
		return strDischargeDiagnosisRequired;
	}

	public void setStrDischargeDiagnosisRequired(
			String strDischargeDiagnosisRequired) {
		this.strDischargeDiagnosisRequired = strDischargeDiagnosisRequired;
	}

	public String getStrDischargeAdviceFieldRequired() {
		return strDischargeAdviceFieldRequired;
	}

	public void setStrDischargeAdviceFieldRequired(
			String strDischargeAdviceFieldRequired) {
		this.strDischargeAdviceFieldRequired = strDischargeAdviceFieldRequired;
	}

	public String getStrDischargeSummaryPrintRequired() {
		return strDischargeSummaryPrintRequired;
	}

	public void setStrDischargeSummaryPrintRequired(
			String strDischargeSummaryPrintRequired) {
		this.strDischargeSummaryPrintRequired = strDischargeSummaryPrintRequired;
	}

	public String getCurWrdBedCode() {
		return curWrdBedCode;
	}

	public void setCurWrdBedCode(String curWrdBedCode) {
		this.curWrdBedCode = curWrdBedCode;
	}

	public String getStrDischargeTypeLAMA() {
		return strDischargeTypeLAMA;
	}

	public void setStrDischargeTypeLAMA(String strDischargeTypeLAMA) {
		this.strDischargeTypeLAMA = strDischargeTypeLAMA;
	}

	public String getStrDischargeTypeAbsconded() {
		return strDischargeTypeAbsconded;
	}

	public void setStrDischargeTypeAbsconded(String strDischargeTypeAbsconded) {
		this.strDischargeTypeAbsconded = strDischargeTypeAbsconded;
	}

	public String getStrDischargeTypeReferral() {
		return strDischargeTypeReferral;
	}

	public void setStrDischargeTypeReferral(String strDischargeTypeReferral) {
		this.strDischargeTypeReferral = strDischargeTypeReferral;
	}

	public String getStrDischargeTypeTransfer() {
		return strDischargeTypeTransfer;
	}

	public void setStrDischargeTypeTransfer(String strDischargeTypeTransfer) {
		this.strDischargeTypeTransfer = strDischargeTypeTransfer;
	}

	public String getStrDischargeTypeDeath() {
		return strDischargeTypeDeath;
	}

	public void setStrDischargeTypeDeath(String strDischargeTypeDeath) {
		this.strDischargeTypeDeath = strDischargeTypeDeath;
	}

	public String getStrConsentRequired() {
		return strConsentRequired;
	}

	public void setStrConsentRequired(String strConsentRequired) {
		this.strConsentRequired = strConsentRequired;
	}

	public String getStrConsentSignedByRelativeName() {
		return strConsentSignedByRelativeName;
	}

	public void setStrConsentSignedByRelativeName(
			String strConsentSignedByRelativeName) {
		this.strConsentSignedByRelativeName = strConsentSignedByRelativeName;
	}

	public String getConsentHtmlCode()
	{
		return consentHtmlCode;
	}

	public void setConsentHtmlCode(String consentHtmlCode)
	{
		this.consentHtmlCode = consentHtmlCode;
	}

	public String getStrConsentTemplateId()
	{
		return strConsentTemplateId;
	}

	public void setStrConsentTemplateId(String strConsentTemplateId)
	{
		this.strConsentTemplateId = strConsentTemplateId;
	}

	public String getHmode()
	{
		return hmode;
	}

	public void setHmode(String hmode)
	{
		this.hmode = hmode;
	}

	public String getStrAbscondedDate() {
		return strAbscondedDate;
	}

	public void setStrAbscondedDate(String strAbscondedDate) {
		this.strAbscondedDate = strAbscondedDate;
	}

	public String getStrPatHeight() {
		return strPatHeight;
	}

	public void setStrPatHeight(String strPatHeight) {
		this.strPatHeight = strPatHeight;
	}

	public String getStrPatColor() {
		return strPatColor;
	}

	public void setStrPatColor(String strPatColor) {
		this.strPatColor = strPatColor;
	}

	public String getStrPatIdentifyMark() {
		return strPatIdentifyMark;
	}

	public void setStrPatIdentifyMark(String strPatIdentifyMark) {
		this.strPatIdentifyMark = strPatIdentifyMark;
	}

	public String getStrLastSeenDate() {
		return strLastSeenDate;
	}

	public void setStrLastSeenDate(String strLastSeenDate) {
		this.strLastSeenDate = strLastSeenDate;
	}

	public String getStrIsPatWearHospClothes() {
		return strIsPatWearHospClothes;
	}

	public void setStrIsPatWearHospClothes(String strIsPatWearHospClothes) {
		this.strIsPatWearHospClothes = strIsPatWearHospClothes;
	}

	public String getStrClothesDetails() {
		return strClothesDetails;
	}

	public void setStrClothesDetails(String strClothesDetails) {
		this.strClothesDetails = strClothesDetails;
	}

	public String getStrAbscondedHour() {
		return strAbscondedHour;
	}

	public void setStrAbscondedHour(String strAbscondedHour) {
		this.strAbscondedHour = strAbscondedHour;
	}

	public String getStrAbscondedMin() {
		return strAbscondedMin;
	}

	public void setStrAbscondedMin(String strAbscondedMin) {
		this.strAbscondedMin = strAbscondedMin;
	}

	public String getStrAbscondedSec() {
		return strAbscondedSec;
	}

	public void setStrAbscondedSec(String strAbscondedSec) {
		this.strAbscondedSec = strAbscondedSec;
	}

	public String getStrDisDate() {
		return strDisDate;
	}

	public void setStrDisDate(String strDisDate) {
		this.strDisDate = strDisDate;
	}

	public String getBillcount() {
		return billcount;
	}

	public void setBillcount(String billcount) {
		this.billcount = billcount;
	}

	public String getStrAbsAmPm() {
		return strAbsAmPm;
	}

	public void setStrAbsAmPm(String strAbsAmPm) {
		this.strAbsAmPm = strAbsAmPm;
	}

}
