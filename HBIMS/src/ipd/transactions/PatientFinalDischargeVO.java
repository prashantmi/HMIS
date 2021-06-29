package ipd.transactions;

import hisglobal.utility.TransferObject;

import javax.sql.rowset.WebRowSet;

public class PatientFinalDischargeVO implements TransferObject
{
	private static final long serialVersionUID = 02L;
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
    private String strRmk="";
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
	private WebRowSet  hospitalListWs =null;
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
	private String strDiagnosisType=null;
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
	private String strBillIntegrated="";
	private String strClearForDischarge = "";
	private String strDischargeDiagnosisRequired="";
	private String strDischargeAdviceFieldRequired="";
	private String strDischargeSummaryPrintRequired="";
	private String strAntecedentCauseDeath="";
	private String strInjuryDetail="";
	private String strCauseDeath="";
	private String strDescpCauseDeath="";
	private String strIsNewBorn="";
	private String strDeathCode="";
	private String strBedStatusVacantCode="";
	private String strAdmDate="";
	private String strDeathFlag="";
	private String curDept_Unt_RomCode = "";
	private String strCurrentDeptUnitRoom="";
	private String curWrdBedCode="";
	private String strTreatmentResultComboValues = "";
	private String strTreatmentResult="";
	private String strConsentRequired="";
	private String strConsentSignedByRelativeName="";
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
	 private String strProfileId="";
	 private String strDisDate="";
		private String strAbsHour="";
		private String strAbsMin="";
		private String strAbsSec="";
		private String strAbsAmPm="";
		private String billcount="0";
		private String strAdvisedBy="";
		private String strRemarksOnline="";
		
		
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

	public String getStrAbsAmPm() {
		return strAbsAmPm;
	}

	public void setStrAbsAmPm(String strAbsAmPm) {
		this.strAbsAmPm = strAbsAmPm;
	}


	public String getStrProfileId() {
		return strProfileId;
	}

	public void setStrProfileId(String strProfileId) {
		this.strProfileId = strProfileId;
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

	public String getStrTreatmentResult()
	{
		return strTreatmentResult;
	}

	public void setStrTreatmentResult(String strTreatmentResult)
	{
		this.strTreatmentResult = strTreatmentResult;
	}

	public String getCurWrdBedCode() {
		return curWrdBedCode;
	}

	public void setCurWrdBedCode(String curWrdBedCode) {
		this.curWrdBedCode = curWrdBedCode;
	}

	public String getStrCurrentDeptUnitRoom() {
		return strCurrentDeptUnitRoom;
	}

	public void setStrCurrentDeptUnitRoom(String strCurrentDeptUnitRoom) {
		this.strCurrentDeptUnitRoom = strCurrentDeptUnitRoom;
	}

	public String getCurDept_Unt_RomCode() {
		return curDept_Unt_RomCode;
	}

	public void setCurDept_Unt_RomCode(String curDept_Unt_RomCode) {
		this.curDept_Unt_RomCode = curDept_Unt_RomCode;
	}

	public String getStrDeathFlag() {
		return strDeathFlag;
	}

	public void setStrDeathFlag(String strDeathFlag) {
		this.strDeathFlag = strDeathFlag;
	}

	public String getStrDischargeSummaryPrintRequired() {
		return strDischargeSummaryPrintRequired;
	}

	public void setStrDischargeSummaryPrintRequired(
			String strDischargeSummaryPrintRequired) {
		this.strDischargeSummaryPrintRequired = strDischargeSummaryPrintRequired;
	}

	public String getStrDischargeAdviceFieldRequired() {
		return strDischargeAdviceFieldRequired;
	}

	public void setStrDischargeAdviceFieldRequired(
			String strDischargeAdviceFieldRequired) {
		this.strDischargeAdviceFieldRequired = strDischargeAdviceFieldRequired;
	}

	public String getStrDischargeDiagnosisRequired() {
		return strDischargeDiagnosisRequired;
	}

	public void setStrDischargeDiagnosisRequired(
			String strDischargeDiagnosisRequired) {
		this.strDischargeDiagnosisRequired = strDischargeDiagnosisRequired;
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
	 * @return the hospitalListWs
	 */
	public WebRowSet getHospitalListWs() {
		return hospitalListWs;
	}

	/**
	 * @param hospitalListWs the hospitalListWs to set
	 */
	public void setHospitalListWs(WebRowSet hospitalListWs) {
		this.hospitalListWs = hospitalListWs;
	}

	/**
	 * @return the strBillIntegrated
	 */
	public String getStrBillIntegrated() {
		return strBillIntegrated;
	}

	/**
	 * @param strBillIntegrated the strBillIntegrated to set
	 */
	public void setStrBillIntegrated(String strBillIntegrated) {
		this.strBillIntegrated = strBillIntegrated;
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

	public void setWardTypeWS(WebRowSet ws) {
		// TODO Auto-generated method stub

	}

	public WebRowSet getWardTypeWS() {
		return wardTypeWS;
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

	public String getStrDeptUnitCode() {
		return strDeptUnitCode;
	}

	public void setStrDeptUnitCode(String strDeptUnitCode) {
		this.strDeptUnitCode = strDeptUnitCode;
	}

	public String getStrTreatmentCategoryName() {
		return strTreatmentCategoryName;
	}

	public void setStrTreatmentCategoryName(String strTreatmentCategoryName) {
		this.strTreatmentCategoryName = strTreatmentCategoryName;
	}

	public String getStrPatCatCode() {
		return strPatCatCode;
	}

	public void setStrPatCatCode(String strPatCatCode) {
		this.strPatCatCode = strPatCatCode;
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

	public String getStrIsUrban() {
		return strIsUrban;
	}

	public void setStrIsUrban(String strIsUrban) {
		this.strIsUrban = strIsUrban;
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

	public String getStrOccEmpName() {
		return strOccEmpName;
	}

	public void setStrOccEmpName(String strOccEmpName) {
		this.strOccEmpName = strOccEmpName;
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

	public String getStrMsApprovalStatus() {
		return strMsApprovalStatus;
	}

	public void setStrMsApprovalStatus(String strMsApprovalStatus) {
		this.strMsApprovalStatus = strMsApprovalStatus;
	}

	public WebRowSet getRoomTypeWS() {
		return roomTypeWS;
	}

	public void setRoomTypeWS(WebRowSet roomTypeWS) {
		this.roomTypeWS = roomTypeWS;
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

	public WebRowSet getBedDetailWS() {
		return bedDetailWS;
	}

	public void setBedDetailWS(WebRowSet bedDetailWS) {
		this.bedDetailWS = bedDetailWS;
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

	public String getStrMsApprovalFlag() {
		return strMsApprovalFlag;
	}

	public void setStrMsApprovalFlag(String strMsApprovalFlag) {
		this.strMsApprovalFlag = strMsApprovalFlag;
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

	public String getStrBedType() {
		return strBedType;
	}

	public void setStrBedType(String strBedType) {
		this.strBedType = strBedType;
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

	public String getStrPopUp() {
		return strPopUp;
	}

	public void setStrPopUp(String strPopUp) {
		this.strPopUp = strPopUp;
	}

	public String getStrServArea() {
		return strServArea;
	}

	public void setStrServArea(String strServArea) {
		this.strServArea = strServArea;
	}

	public WebRowSet getStrServAreaWS() {
		return strServAreaWS;
	}

	public void setStrServAreaWS(WebRowSet strServAreaWS) {
		this.strServAreaWS = strServAreaWS;
	}

	public String getStrServAreaCode() {
		return strServAreaCode;
	}

	public void setStrServAreaCode(String strServAreaCode) {
		this.strServAreaCode = strServAreaCode;
	}

	public String getStrEntryDate() {
		return strEntryDate;
	}

	public void setStrEntryDate(String strEntryDate) {
		this.strEntryDate = strEntryDate;
	}

	public String getStrOldWardCode() {
		return strOldWardCode;
	}

	public void setStrOldWardCode(String strOldWardCode) {
		this.strOldWardCode = strOldWardCode;
	}

	public String getStrOldRoomCode() {
		return strOldRoomCode;
	}

	public void setStrOldRoomCode(String strOldRoomCode) {
		this.strOldRoomCode = strOldRoomCode;
	}

	public String getStrOldBedCode() {
		return strOldBedCode;
	}

	public void setStrOldBedCode(String strOldBedCode) {
		this.strOldBedCode = strOldBedCode;
	}


	public String getStrTransFlg() {
		return strTransFlg;
	}

	public void setStrTransFlg(String strTransFlg) {
		this.strTransFlg = strTransFlg;
	}

	public String getStrPvtBedFlg() {
		return strPvtBedFlg;
	}

	public void setStrPvtBedFlg(String strPvtBedFlg) {
		this.strPvtBedFlg = strPvtBedFlg;
	}

	public String getStrCmbRed() {
		return strCmbRed;
	}

	public void setStrCmbRed(String strCmbRed) {
		this.strCmbRed = strCmbRed;
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

	public String getStrAdmStatCode() {
		return strAdmStatCode;
	}

	public void setStrAdmStatCode(String strAdmStatCode) {
		this.strAdmStatCode = strAdmStatCode;
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

	public String getStrCauseDeath() {
		return strCauseDeath;
	}

	public void setStrCauseDeath(String strCauseDeath) {
		this.strCauseDeath = strCauseDeath;
	}

	public String getStrDescpCauseDeath() {
		return strDescpCauseDeath;
	}

	public void setStrDescpCauseDeath(String strDescpCauseDeath) {
		this.strDescpCauseDeath = strDescpCauseDeath;
	}

	public String getStrIsNewBorn() {
		return strIsNewBorn;
	}

	public void setStrIsNewBorn(String strIsNewBorn) {
		this.strIsNewBorn = strIsNewBorn;
	}

	public String getStrDeathCode() {
		return strDeathCode;
	}

	public void setStrDeathCode(String strDeathCode) {
		this.strDeathCode = strDeathCode;
	}

	public String getStrBedStatusVacantCode() {
		return strBedStatusVacantCode;
	}

	public void setStrBedStatusVacantCode(String strBedStatusVacantCode) {
		this.strBedStatusVacantCode = strBedStatusVacantCode;
	}

	public String getStrAdmDate() {
		return strAdmDate;
	}

	public void setStrAdmDate(String strAdmDate) {
		this.strAdmDate = strAdmDate;
	}

	public String getStrTreatmentResultComboValues()
	{
		return strTreatmentResultComboValues;
	}

	public void setStrTreatmentResultComboValues(String strTreatmentResultComboValues)
	{
		this.strTreatmentResultComboValues = strTreatmentResultComboValues;
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

	public String getStrAdvisedBy() {
		return strAdvisedBy;
	}

	public void setStrAdvisedBy(String strAdvisedBy) {
		this.strAdvisedBy = strAdvisedBy;
	}

	public String getStrRemarksOnline() {
		return strRemarksOnline;
	}

	public void setStrRemarksOnline(String strRemarksOnline) {
		this.strRemarksOnline = strRemarksOnline;
	}


}
