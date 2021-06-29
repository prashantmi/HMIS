package ipd.transactions;

import hisglobal.utility.TransferObject;

import javax.sql.rowset.WebRowSet;

public class AdmissionAdviceTransVO implements TransferObject {

	private static final long serialVersionUID = 02L;

	private String strMsgString = "";
	private String strMsgType = "0";
    private String strAccDetval="";
	
	private String strDepartment = "";
	private String strDepartmentValue = "";
	private String strUnitValue = "";
	private String strUnit = "";
	private String strPropAdmissionDate = "";
	private String strLenStay = "";
	private String strWard = "0";
	private String strAdmissionPriority = "";
	private String strTreatmentCategory = "0";
	private String strRemarks = "";
	private String strConsultantName = "";
	private String strEpisodeNumber = "";
	private String strMlcNo = "";
	private String strVisitValue = "";
	private String strPrimaryCategory = "0";
	private String strSecondaryCategory = "0";
	private String strRoomType = "0";
	private String strBedType = "0";
	private String strBedStatusMode = "";
	private String strDiagnosisType = "";
	private String strSeatId = "10001";
	private String strApprovalStatus = "0";
	private String strAge = "0";
	private String strSex = "0";
	private String strRoomCode = "";
	private String strTreatmentCategoryCode = "";
	private String strDiseaseTypeCode = "";
	private String strDiseaseType = "";
	private String strTreatment = "";
	private String strUnitCode = "";
	private String patCrNo = "";
	private String strAdviceDate = "";
	private String strWardTypeCode = "";
	private String strFlag = "0";
	private String strHospCode = "";
	private String strMsg = "";
	private String strWarning = "";
	private String[] strProvisionDiagnosis = null;
	private String strMode="";

	private String strSearchMode = "";
	private String strSearchString = "";
	private String strAdvanceAmount="";
	private String strAdvanceAmtpaid="";
	private String strAccountNo="";
	private String strAdvDepDate=null;
	private WebRowSet strAdmissionTypeWS=null;
	private WebRowSet strTariffNameWS=null;
	private String strTariffNameValues="";
	
	private WebRowSet wardWS = null;
	private WebRowSet provisionDiagnosisWs = null;
	private WebRowSet treatmentCategoryWs = null;
	private WebRowSet consultantNameWs = null;
	private WebRowSet roomTypeWs = null;
	private WebRowSet bedTypeWs = null;
	private WebRowSet bedStatusWs = null;
	private WebRowSet bedStatusPatientWs = null;
	private WebRowSet hospitalDiagnosisWs = null;
	private WebRowSet icd10DiagnosisWs = null;
	private WebRowSet wardTYPES = null;
	private WebRowSet departTypeWS = null;
	private WebRowSet diseaseTypeWs = null;
	private WebRowSet roomWs = null;
	private boolean deptNameFound = false;
	private WebRowSet bedDetailWs = null;
	private WebRowSet listWs = null;
	private WebRowSet previousDiagnosisWs = null;
	private String strBillFlag = "";
	private String strAdvanceFlag = "";
	private WebRowSet strDeptComboValuesWs=null;
	private String strDeptComboValues="";
	private String strCrNo="";
	private String strDepartmentCode="";
	private WebRowSet strUnitComboValuesWs=null;
	private String strUnitComboValues="";
	private String strDeptUnitCode="";
	private String strICD10CodeValues="";
	private String strPatName="";
	private String strEpNo="";

	
	private String[] strDiagCodeType=null;//ICD/Hospital
	private String[] strisRepaeat=null;
	private String[] strDiagRemarks=null;
	private String[] strDiseaseSite=null;
	private String[] strDiagTypeCode=null;//Provisional
	
	private String strAdmissionTypeValues="";
	private String strMaxLenStay="";
	private String strPlannedSurgeryDate="";
	private String strIpAddress="";
	private boolean strIssueEstCertificate=false;
	
	private String strAdmissionNo="";
	

	private String strWardCode = "";
	private WebRowSet packageComboValuesWs=null;
	private String strPackageApply = "";
	private String strAccType = "";

	
	public String getStrWardCode() {
		return strWardCode;
	}
	public void setStrWardCode(String strWardCode) {
		this.strWardCode = strWardCode;
	}
	public String getStrAdmissionNo() {
		return strAdmissionNo;
	}
	public void setStrAdmissionNo(String strAdmissionNo) {
		this.strAdmissionNo = strAdmissionNo;
	}
	public boolean isStrIssueEstCertificate() {
		return strIssueEstCertificate;
	}
	public void setStrIssueEstCertificate(boolean strIssueEstCertificate) {
		this.strIssueEstCertificate = strIssueEstCertificate;
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
	 * @return the strDeptUnitCode
	 */
	public String getStrDeptUnitCode() {
		return strDeptUnitCode;
	}
	/**
	 * @param strDeptUnitCode the strDeptUnitCode to set
	 */
	public void setStrDeptUnitCode(String strDeptUnitCode) {
		this.strDeptUnitCode = strDeptUnitCode;
	}
	/**
	 * @return the strUnitComboValuesWs
	 */
	public WebRowSet getStrUnitComboValuesWs() {
		return strUnitComboValuesWs;
	}
	/**
	 * @param strUnitComboValuesWs the strUnitComboValuesWs to set
	 */
	public void setStrUnitComboValuesWs(WebRowSet strUnitComboValuesWs) {
		this.strUnitComboValuesWs = strUnitComboValuesWs;
	}
	/**
	 * @return the strUnitComboValues
	 */
	public String getStrUnitComboValues() {
		return strUnitComboValues;
	}
	/**
	 * @param strUnitComboValues the strUnitComboValues to set
	 */
	public void setStrUnitComboValues(String strUnitComboValues) {
		this.strUnitComboValues = strUnitComboValues;
	}
	/**
	 * @return the strDepartmentCode
	 */
	public String getStrDepartmentCode() {
		return strDepartmentCode;
	}
	/**
	 * @param strDepartmentCode the strDepartmentCode to set
	 */
	public void setStrDepartmentCode(String strDepartmentCode) {
		this.strDepartmentCode = strDepartmentCode;
	}
	/**
	 * @return the strCrNo
	 */
	public String getStrCrNo() {
		return strCrNo;
	}
	/**
	 * @param strCrNo the strCrNo to set
	 */
	public void setStrCrNo(String strCrNo) {
		this.strCrNo = strCrNo;
	}
	/**
	 * @return the strDeptComboValuesWs
	 */
	public WebRowSet getStrDeptComboValuesWs() {
		return strDeptComboValuesWs;
	}
	/**
	 * @param strDeptComboValuesWs the strDeptComboValuesWs to set
	 */
	public void setStrDeptComboValuesWs(WebRowSet strDeptComboValuesWs) {
		this.strDeptComboValuesWs = strDeptComboValuesWs;
	}
	/**
	 * @return the strDeptComboValues
	 */
	public String getStrDeptComboValues() {
		return strDeptComboValues;
	}
	/**
	 * @param strDeptComboValues the strDeptComboValues to set
	 */
	public void setStrDeptComboValues(String strDeptComboValues) {
		this.strDeptComboValues = strDeptComboValues;
	}
	/**
	 * @return the strBillFlag
	 */
	public String getStrBillFlag() {
		return strBillFlag;
	}
	/**
	 * @param strBillFlag
	 *            the strBillFlag to set
	 */
	public void setStrBillFlag(String strBillFlag) {
		this.strBillFlag = strBillFlag;
	}
	/**
	 * @return the strAdvanceFlag
	 */
	public String getStrAdvanceFlag() {
		return strAdvanceFlag;
	}
	/**
	 * @param strAdvanceFlag
	 *            the strAdvanceFlag to set
	 */
	public void setStrAdvanceFlag(String strAdvanceFlag) {
		this.strAdvanceFlag = strAdvanceFlag;
	}
	public WebRowSet getHospitalDiagnosisWs() {
		return hospitalDiagnosisWs;
	}
	public void setHospitalDiagnosisWs(WebRowSet hospitalDiagnosisWs) {
		this.hospitalDiagnosisWs = hospitalDiagnosisWs;
	}
	public WebRowSet getIcd10DiagnosisWs() {
		return icd10DiagnosisWs;
	}
	public void setIcd10DiagnosisWs(WebRowSet icd10DiagnosisWs) {
		this.icd10DiagnosisWs = icd10DiagnosisWs;
	}
	public WebRowSet getRoomTypeWs() {
		return roomTypeWs;
	}
	public void setRoomTypeWs(WebRowSet roomTypeWs) {
		this.roomTypeWs = roomTypeWs;
	}
	public WebRowSet getBedTypeWs() {
		return bedTypeWs;
	}
	public void setBedTypeWs(WebRowSet bedTypeWs) {
		this.bedTypeWs = bedTypeWs;
	}
	public WebRowSet getProvisionDiagnosisWs() {
		return provisionDiagnosisWs;
	}
	public void setProvisionDiagnosisWs(WebRowSet provisionDiagnosisWs) {
		this.provisionDiagnosisWs = provisionDiagnosisWs;
	}
	public WebRowSet getTreatmentCategoryWs() {
		return treatmentCategoryWs;
	}
	public void setTreatmentCategoryWs(WebRowSet treatmentCategoryWs) {
		this.treatmentCategoryWs = treatmentCategoryWs;
	}
	public WebRowSet getConsultantNameWs() {
		return consultantNameWs;
	}
	public void setConsultantNameWs(WebRowSet consultantNameWs) {
		this.consultantNameWs = consultantNameWs;
	}
	public WebRowSet getWardWS() {
		return wardWS;
	}
	public void setWardWS(WebRowSet wardWS) {
		this.wardWS = wardWS;
	}
	public String getStrDepartmentValue() {
		return strDepartmentValue;
	}
	public void setStrDepartmentValue(String strDepartment) {
		this.strDepartmentValue = strDepartment;
	}
	public String getStrUnitValue() {
		return strUnitValue;
	}
	public void setStrUnitValue(String strUnit) {
		this.strUnitValue = strUnit;
	}
	public String getStrPropAdmissionDate() {
		return strPropAdmissionDate;
	}
	public void setStrPropAdmissionDate(String strPropAdmissionDate) {
		this.strPropAdmissionDate = strPropAdmissionDate;
	}
	public String getStrLenStay() {
		return strLenStay;
	}
	public void setStrLenStay(String strLenStay) {
		this.strLenStay = strLenStay;
	}
	public String getStrWard() {
		return strWard;
	}
	public void setStrWard(String strWard) {
		this.strWard = strWard;
	}
	public String getStrAdmissionPriority() {
		return strAdmissionPriority;
	}
	public void setStrAdmissionPriority(String strAdmissionPriority) {
		this.strAdmissionPriority = strAdmissionPriority;
	}
	public String getStrTreatmentCategory() {
		return strTreatmentCategory;
	}
	public void setStrTreatmentCategory(String strTreatmentCategory) {
		this.strTreatmentCategory = strTreatmentCategory;
	}
	public String getStrRemarks() {
		return strRemarks;
	}
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}
	public String getStrConsultantName() {
		return strConsultantName;
	}
	public void setStrConsultantName(String strConsultantName) {
		this.strConsultantName = strConsultantName;
	}
	public String getStrEpisodeNumber() {
		return strEpisodeNumber;
	}
	public void setStrEpisodeNumber(String strEpisodeNumber) {
		this.strEpisodeNumber = strEpisodeNumber;
	}
	public String getStrMlcNo() {
		return strMlcNo;
	}
	public void setStrMlcNo(String strMlcNo) {
		this.strMlcNo = strMlcNo;
	}
	public String getStrVisitValue() {
		return strVisitValue;
	}
	public void setStrVisitValue(String strVisitValue) {
		this.strVisitValue = strVisitValue;
	}
	public String getStrPrimaryCategory() {
		return strPrimaryCategory;
	}
	public void setStrPrimaryCategory(String strPrimaryCategory) {
		this.strPrimaryCategory = strPrimaryCategory;
	}
	public String getStrSecondaryCategory() {
		return strSecondaryCategory;
	}
	public void setStrSecondaryCategory(String strSecondaryCategory) {
		this.strSecondaryCategory = strSecondaryCategory;
	}
	public String getStrMsgString() {
		return strMsgString;
	}
	public String getStrMsgType() {
		// TODO Auto-generated method stub
		return strMsgType;
	}
	public void setStrMsgString(String MSGSTR) {
		this.strMsgString = MSGSTR;
	}
	public void setStrMsgType(String MSGTYPE) {
		this.strMsgType = MSGTYPE;
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
	public WebRowSet getBedStatusWs() {
		return bedStatusWs;
	}
	public void setBedStatusWs(WebRowSet bedStatusWs) {
		this.bedStatusWs = bedStatusWs;
	}
	public String getStrBedStatusMode() {
		return strBedStatusMode;
	}
	public void setStrBedStatusMode(String strBedStatusMode) {
		this.strBedStatusMode = strBedStatusMode;
	}
	public WebRowSet getBedStatusPatientWs() {
		return bedStatusPatientWs;
	}
	public void setBedStatusPatientWs(WebRowSet bedStatusPatientWs) {
		this.bedStatusPatientWs = bedStatusPatientWs;
	}
	public String getStrDiagnosisType() {
		return strDiagnosisType;
	}
	public void setStrDiagnosisType(String strDiagnosisType) {
		this.strDiagnosisType = strDiagnosisType;
	}
	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	public String[] getStrProvisionDiagnosis() {
		return strProvisionDiagnosis;
	}
	public void setStrProvisionDiagnosis(String[] strProvisionDiagnosis) {
		this.strProvisionDiagnosis = strProvisionDiagnosis;
	}
	public String getStrApprovalStatus() {
		return strApprovalStatus;
	}
	public void setStrApprovalStatus(String strApprovalStatus) {
		this.strApprovalStatus = strApprovalStatus;
	}
	public String getStrAge() {
		return strAge;
	}
	public void setStrAge(String strAge) {
		this.strAge = strAge;
	}
	public String getStrSex() {
		return strSex;
	}
	public void setStrSex(String strSex) {
		this.strSex = strSex;
	}
	public WebRowSet getWardTYPES() {
		return wardTYPES;
	}
	public void setWardTYPES(WebRowSet wardTYPES) {
		this.wardTYPES = wardTYPES;
	}
	public boolean isDeptNameFound() {
		return deptNameFound;
	}
	public void setDeptNameFound(boolean deptNameFound) {
		this.deptNameFound = deptNameFound;
	}
	public WebRowSet getDepartTypeWS() {
		return departTypeWS;
	}
	public void setDepartTypeWS(WebRowSet departTypeWS) {
		this.departTypeWS = departTypeWS;
	}
	public String getStrTreatmentCategoryCode() {
		return strTreatmentCategoryCode;
	}
	public void setStrTreatmentCategoryCode(String strTreatmentCategoryCode) {
		this.strTreatmentCategoryCode = strTreatmentCategoryCode;
	}
	public WebRowSet getRoomWs() {
		return roomWs;
	}
	public void setRoomWs(WebRowSet roomWs) {
		this.roomWs = roomWs;
	}
	public WebRowSet getBedDetailWs() {
		return bedDetailWs;
	}
	public void setBedDetailWs(WebRowSet bedDetailWs) {
		this.bedDetailWs = bedDetailWs;
	}
	public String getStrRoomCode() {
		return strRoomCode;
	}
	public void setStrRoomCode(String strRoomCode) {
		this.strRoomCode = strRoomCode;
	}
	public WebRowSet getDiseaseTypeWs() {
		return diseaseTypeWs;
	}
	public void setDiseaseTypeWs(WebRowSet diseaseTypeWs) {
		this.diseaseTypeWs = diseaseTypeWs;
	}
	public String getStrDiseaseTypeCode() {
		return strDiseaseTypeCode;
	}
	public void setStrDiseaseTypeCode(String strDiseaseTypeCode) {
		this.strDiseaseTypeCode = strDiseaseTypeCode;
	}
	public String getStrDiseaseType() {
		return strDiseaseType;
	}
	public void setStrDiseaseType(String strDiseaseType) {
		this.strDiseaseType = strDiseaseType;
	}
	public String getStrTreatment() {
		return strTreatment;
	}
	public void setStrTreatment(String strTreatment) {
		this.strTreatment = strTreatment;
	}
	public String getStrUnitCode() {
		return strUnitCode;
	}

	public void setStrUnitCode(String strUnitCode) {
		this.strUnitCode = strUnitCode;
	}
	public String getStrAdviceDate() {
		return strAdviceDate;
	}
	public void setStrAdviceDate(String strAdviceDate) {
		this.strAdviceDate = strAdviceDate;
	}
	public WebRowSet getListWs() {
		return listWs;
	}
	public void setListWs(WebRowSet listWs) {
		this.listWs = listWs;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getStrWardTypeCode() {
		return strWardTypeCode;
	}
	public void setStrWardTypeCode(String strWardTypeCode) {
		this.strWardTypeCode = strWardTypeCode;
	}
	public String getStrFlag() {
		return strFlag;
	}
	public void setStrFlag(String strFlag) {
		this.strFlag = strFlag;
	}
	public String getStrMsg() {
		return strMsg;
	}
	public void setStrMsg(String strMsg) {
		this.strMsg = strMsg;
	}
	public String getStrWarning() {
		return strWarning;
	}
	public void setStrWarning(String strWarning) {
		this.strWarning = strWarning;
	}
	public String getStrHospCode() {
		return strHospCode;
	}
	public void setStrHospCode(String strHospCode) {
		this.strHospCode = strHospCode;
	}
	public WebRowSet getPreviousDiagnosisWs() {
		return previousDiagnosisWs;
	}
	public void setPreviousDiagnosisWs(WebRowSet previousDiagnosisWs) {
		this.previousDiagnosisWs = previousDiagnosisWs;
	}
	public String getStrMode() {
		return strMode;
	}
	public void setStrMode(String strMode) {
		this.strMode = strMode;
	}
	public String getStrSearchMode() {
		return strSearchMode;
	}
	public void setStrSearchMode(String strSearchMode) {
		this.strSearchMode = strSearchMode;
	}
	public String getStrSearchString() {
		return strSearchString;
	}
	public void setStrSearchString(String strSearchString) {
		this.strSearchString = strSearchString;
	}
	public String getStrPatName() {
		return strPatName;
	}
	public void setStrPatName(String strPatName) {
		this.strPatName = strPatName;
	}
	public String getStrEpNo() {
		return strEpNo;
	}
	public void setStrEpNo(String strEpNo) {
		this.strEpNo = strEpNo;
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
	public WebRowSet getStrAdmissionTypeWS() {
		return strAdmissionTypeWS;
	}
	public void setStrAdmissionTypeWS(WebRowSet strAdmissionTypeWS) {
		this.strAdmissionTypeWS = strAdmissionTypeWS;
	}
	public String getStrAdvanceAmount() {
		return strAdvanceAmount;
	}
	public void setStrAdvanceAmount(String strAdvanceAmount) {
		this.strAdvanceAmount = strAdvanceAmount;
	}
	public String getStrAdmissionTypeValues() {
		return strAdmissionTypeValues;
	}
	public void setStrAdmissionTypeValues(String strAdmissionTypeValues) {
		this.strAdmissionTypeValues = strAdmissionTypeValues;
	}
	public String getStrMaxLenStay() {
		return strMaxLenStay;
	}
	public void setStrMaxLenStay(String strMaxLenStay) {
		this.strMaxLenStay = strMaxLenStay;
	}
	public String getStrPlannedSurgeryDate() {
		return strPlannedSurgeryDate;
	}
	public void setStrPlannedSurgeryDate(String strPlannedSurgeryDate) {
		this.strPlannedSurgeryDate = strPlannedSurgeryDate;
	}
	public String getStrIpAddress() {
		return strIpAddress;
	}
	public void setStrIpAddress(String strIpAddress) {
		this.strIpAddress = strIpAddress;
	}
	public WebRowSet getStrTariffNameWS() {
		return strTariffNameWS;
	}
	public void setStrTariffNameWS(WebRowSet strTariffNameWS) {
		this.strTariffNameWS = strTariffNameWS;
	}
	public String getStrTariffNameValues() {
		return strTariffNameValues;
	}
	public void setStrTariffNameValues(String strTariffNameValues) {
		this.strTariffNameValues = strTariffNameValues;
	}

	public String getStrAdvanceAmtpaid() {
		return strAdvanceAmtpaid;
	}
	public void setStrAdvanceAmtpaid(String strAdvanceAmtpaid) {
		this.strAdvanceAmtpaid = strAdvanceAmtpaid;
	}
	public String getStrAccountNo() {
		return strAccountNo;
	}
	public void setStrAccountNo(String strAccountNo) {
		this.strAccountNo = strAccountNo;
	}
	public String getStrAdvDepDate() {
		return strAdvDepDate;
	}
	public void setStrAdvDepDate(String strAdvDepDate) {
		this.strAdvDepDate = strAdvDepDate;
	}
	
	public String getStrAccDetval() {
		return strAccDetval;
	}
	public void setStrAccDetval(String strAccDetval) {
		this.strAccDetval = strAccDetval;
	}
	public WebRowSet getPackageComboValuesWs() {
		return packageComboValuesWs;
	}
	public void setPackageComboValuesWs(WebRowSet packageComboValuesWs) {
		this.packageComboValuesWs = packageComboValuesWs;
	}
	public String getStrPackageApply() {
		return strPackageApply;
	}
	public void setStrPackageApply(String strPackageApply) {
		this.strPackageApply = strPackageApply;
	}
	public String getStrAccType() {
		return strAccType;
	}
	public void setStrAccType(String strAccType) {
		this.strAccType = strAccType;
	}
	
	
}