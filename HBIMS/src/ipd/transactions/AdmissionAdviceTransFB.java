package ipd.transactions;

import javax.sql.rowset.WebRowSet;

import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;


public class AdmissionAdviceTransFB extends ActionForm {

	private static final long serialVersionUID = 02L;
    
	private String strAdvanceAmtpaid="";
	private String strAccountNo="";
	private String strCrNo = "";
	private String strPatName="";
	private String strDepartment = "";
	private String strUnit = "";
	private String strPropAdmissionDate = "";
	private String strLenStay = "";
	private String strWard = "0";
	private String strAdmissionPriority = "0";
	private String strTreatmentCategory = "0";
	private String[] strProvisionDiagnosis;
	private String strRemarks = "";
	private String strConsultantName = "";
	private String strEpisodeNumber = "";
	private String strMlcNo = "";
	private String strDepartmentValue = "";
	private String strWardTypeValue="";
	private String strUnitValue = "";
	private String strVisitValue = "";
	private String strPrimaryCategory = "0";
	private String strSecondaryCategory = "0";
	private String strBedStatusMode = "";
	private String strDiagnosisType = "1";
	private String strRoomType="0";
	private String strBedType = "0";
	private String strSeatId = "10001";
	private String strSex="";
	private String strAge="";
	private String strTreatment="";
	private String strUnitCode="";
	private String strAdviceDate="";
	private String patCrNo="";
	private String strMsg="";
	private String strWarning="";
	private String strPreviousDiagDtl="";
	private String strMode="";
	//private String[] strDiagnosisCode = null;
	private String strHospCode = "";
	private String strSearchMode = "";
	private String strSearchString = "";
	
	private String strWardValues = "";
	private String strTreatmentCategoryValues = "";
	private String strConsultantNameValues = "";
	private String strProvisionDiagnosisValues = "";
	private String strRoomTypeValues = "";
	private String strBedTypeValues = "";
	private String strHospitalDiagnosisValues = "0";
	private String strIcd10DiagnosisValues = "0";
	private String strDiseaseTypeValues="";
	private String strDiseaseType="";
	private String strMsgString = "";
	private String strMsgType = "0";
	private String listView="";
	private String strRoomValues="";
	private String strCtDate = null;
    private String strAdvDepDate=null;
	private boolean deptFound=false;
	private String strDeptComboValues="";
	private String strDepartmentCode="";
	private String strUnitComboValues="";
	private String strICD10CodeValues="";
	private String strAdmissionTypeValues="";
	private String strTariffNameValues=null;
	private String strHospDiagValues="";
	private String strDiagType="";
	private String epFlag="";
	private FormFile patimg=null;
	
	private String strDiagnosticTypeValues="";
	private String strAdvanceAmount="";
	private String strMaxLenStay="";
	private String strPlannedSurgeryDate="";
	private String StrIpAddress="";
	private boolean strIssueEstCertificate=false;
	

	
	private String[] strDiagCodeType=null;//ICD/Hospital
	private String[] strisRepaeat=null;
	private String[] strDiagRemarks=null;
	private String[] strDiseaseSite=null;
	private String[] strDiagTypeCode=null;//Provisional
	private String strExcessCreditLimit = "0";
	private String strChkBoxComboValue = "";
	private String strChk ="";
	private String strClientApprovalDtl = "";
	private String[] strComboValue = {"0"};
	private String strIpdThirdPartyBilling ="";
    private String strIpdRoundOff = "";
    private String strPackageComboValues = "";
    private String strPackageApply = "";
    private String strAccType = "";
	
	public String getStrExcessCreditLimit() {
		return strExcessCreditLimit;
	}

	public void setStrExcessCreditLimit(String strExcessCreditLimit) {
		this.strExcessCreditLimit = strExcessCreditLimit;
	}

	public String getStrChkBoxComboValue() {
		return strChkBoxComboValue;
	}

	public void setStrChkBoxComboValue(String strChkBoxComboValue) {
		this.strChkBoxComboValue = strChkBoxComboValue;
	}

	public String getStrChk() {
		return strChk;
	}

	public void setStrChk(String strChk) {
		this.strChk = strChk;
	}

	public String getStrClientApprovalDtl() {
		return strClientApprovalDtl;
	}

	public void setStrClientApprovalDtl(String strClientApprovalDtl) {
		this.strClientApprovalDtl = strClientApprovalDtl;
	}

	public String[] getStrComboValue() {
		return strComboValue;
	}

	public void setStrComboValue(String[] strComboValue) {
		this.strComboValue = strComboValue;
	}

	public String getStrIpdThirdPartyBilling() {
		return strIpdThirdPartyBilling;
	}

	public void setStrIpdThirdPartyBilling(String strIpdThirdPartyBilling) {
		this.strIpdThirdPartyBilling = strIpdThirdPartyBilling;
	}

	public String getStrIpdRoundOff() {
		return strIpdRoundOff;
	}

	public void setStrIpdRoundOff(String strIpdRoundOff) {
		this.strIpdRoundOff = strIpdRoundOff;
	}

	public void setStrCtDate(String strCtDate) {
		this.strCtDate = strCtDate;
	}

	private String strFlagForSave="";
	


	public String getStrFlagForSave() {
		return strFlagForSave;
	}

	public void setStrFlagForSave(String strFlagForSave) {
		this.strFlagForSave = strFlagForSave;
	}

	public String getStrDiagType() {
		return strDiagType;
	}

	public void setStrDiagType(String strDiagType) {
		this.strDiagType = strDiagType;
	}

	/**
	 * @return the strHospDiagValues
	 */
	public String getStrHospDiagValues() {
		return strHospDiagValues;
	}

	/**
	 * @param strHospDiagValues the strHospDiagValues to set
	 */
	public void setStrHospDiagValues(String strHospDiagValues) {
		this.strHospDiagValues = strHospDiagValues;
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
	 * retrieves Current Date From Application Server and returns the Same.
	 * 
	 * @return - Current Date in String Format
	 */
	
	public String getStrCtDate() {

		HisUtil util = new HisUtil("Department Ward Disease Master",
				"VODepartmentWardDiseaseMst");

		try {
			strCtDate = util.getASDate("dd-MMM-yyyy");
			util = null;
		} catch (Exception e) {

			new HisException("Department Ward Disease Master",
					"VODepartmentWardDiseaseMst.getStrCtDate()", e.getMessage());
		}

		return strCtDate;
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

	public String getStrEpisodeNumber() {
		return strEpisodeNumber;
	}

	public void setStrEpisodeNumber(String strEpisodeNumber) {
		this.strEpisodeNumber = strEpisodeNumber;
	}

	public String getStrWardValues() {

		return strWardValues;
	}

	public String getStrTreatmentCategoryValues() {

		return strTreatmentCategoryValues;
	}

	public String getStrConsultantNameValues() {

		return strConsultantNameValues;
	}

	public String getStrProvisionDiagnosisValues() {

		return strProvisionDiagnosisValues;
	}

	public String getStrCrNo() {
		return strCrNo;
	}

	public void setStrCrNo(String strCrNo) {
		this.strCrNo = strCrNo;
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

	
	public String[] getStrProvisionDiagnosis() {
		return strProvisionDiagnosis;
	}

	public void setStrProvisionDiagnosis(String[] strProvisionDiagnosis) {
		this.strProvisionDiagnosis = strProvisionDiagnosis;
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

	public String getStrMlcNo() {
		return strMlcNo;
	}

	public void setStrMlcNo(String strMlcNo) {
		this.strMlcNo = strMlcNo;
	}

	public String getStrDepartmentValue() {
		return strDepartmentValue;
	}

	public void setStrDepartmentValue(String strDepartmentValue) {
		this.strDepartmentValue = strDepartmentValue;
	}

	public String getStrUnitValue() {
		return strUnitValue;
	}

	public void setStrUnitValue(String strUnitValue) {
		this.strUnitValue = strUnitValue;
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

	public void setStrWardValues(String strWardValues) {
		this.strWardValues = strWardValues;
	}

	public void setStrTreatmentCategoryValues(String strTreatmentCategoryValues) {
		this.strTreatmentCategoryValues = strTreatmentCategoryValues;
	}

	public void setStrConsultantNameValues(String strConsultantNameValues) {
		this.strConsultantNameValues = strConsultantNameValues;
	}

	public void setStrProvisionDiagnosisValues(
			String strProvisionDiagnosisValues) {
		this.strProvisionDiagnosisValues = strProvisionDiagnosisValues;
	}

	public String getStrRoomTypeValues() {
		return strRoomTypeValues;
	}

	public void setStrRoomTypeValues(String strRoomTypeValues) {
		this.strRoomTypeValues = strRoomTypeValues;
	}

	public String getStrBedTypeValues() {
		
		return strBedTypeValues;
	}

	public void setStrBedTypeValues(String strBedTypeValues) {
		
		this.strBedTypeValues = strBedTypeValues;
	}

	public String getStrBedStatusMode() {
		return strBedStatusMode;
	}

	public void setStrBedStatusMode(String strBedStatusMode) {
		this.strBedStatusMode = strBedStatusMode;
	}

	public String getStrHospitalDiagnosisValues() {
		return strHospitalDiagnosisValues;
	}

	public void setStrHospitalDiagnosisValues(String strHospitalDiagnosisValues) {
		this.strHospitalDiagnosisValues = strHospitalDiagnosisValues;
	}

	public String getStrIcd10DiagnosisValues() {
		return strIcd10DiagnosisValues;
	}

	public void setStrIcd10DiagnosisValues(String strIcd10DiagnosisValues) {
		this.strIcd10DiagnosisValues = strIcd10DiagnosisValues;
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

	public String getStrSex() {
		return strSex;
	}

	public void setStrSex(String strSex) {
		this.strSex = strSex;
	}

	public String getStrAge() {
		return strAge;
	}

	public void setStrAge(String strAge) {
		this.strAge = strAge;
	}

	public String getStrWardTypeValue() {
		return strWardTypeValue;
	}

	public void setStrWardTypeValue(String strWardTypeValue) {
		this.strWardTypeValue = strWardTypeValue;
	}

	public boolean isDeptFound() {
		return deptFound;
	}

	public void setDeptFound(boolean deptFound) {
		this.deptFound = deptFound;
	}

	public String getStrDiseaseTypeValues() {
		return strDiseaseTypeValues;
	}

	public void setStrDiseaseTypeValues(String strDiseaseTypeValues) {
		this.strDiseaseTypeValues = strDiseaseTypeValues;
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

	public String getListView() {
		return listView;
	}

	public void setListView(String listView) {
		this.listView = listView;
	}

	public String getStrAdviceDate() {
		return strAdviceDate;
	}

	public void setStrAdviceDate(String strAdviceDate) {
		this.strAdviceDate = strAdviceDate;
	}

	public String getPatCrNo() {
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
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

	public String getStrRoomValues() {
		return strRoomValues;
	}

	public void setStrRoomValues(String strRoomValues) {
		this.strRoomValues = strRoomValues;
	}

	public String getStrPreviousDiagDtl() {
		return strPreviousDiagDtl;
	}

	public void setStrPreviousDiagDtl(String strPreviousDiagDtl) {
		this.strPreviousDiagDtl = strPreviousDiagDtl;
	}

	public String getStrMode() {
		return strMode;
	}

	public void setStrMode(String strMode) {
		this.strMode = strMode;
	}

	/**
	 * @return the strHospCode
	 */
	public String getStrHospCode() {
		return strHospCode;
	}

	/**
	 * @param strHospCode the strHospCode to set
	 */
	public void setStrHospCode(String strHospCode) {
		this.strHospCode = strHospCode;
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

	public String getEpFlag() {
		return epFlag;
	}

	public void setEpFlag(String epFlag) {
		this.epFlag = epFlag;
	}

	public FormFile getPatimg() {
		return patimg;
	}

	public void setPatimg(FormFile patimg) {
		this.patimg = patimg;
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

	public String getStrDiagnosticTypeValues() {
		return strDiagnosticTypeValues;
	}

	public void setStrDiagnosticTypeValues(String strDiagnosticTypeValues) {
		this.strDiagnosticTypeValues = strDiagnosticTypeValues;
	}

	public String getStrAdmissionTypeValues() {
		return strAdmissionTypeValues;
	}

	public void setStrAdmissionTypeValues(String strAdmissionTypeValues) {
		this.strAdmissionTypeValues = strAdmissionTypeValues;
	}

	public String getStrAdvanceAmount() {
		return strAdvanceAmount;
	}

	public void setStrAdvanceAmount(String strAdvanceAmount) {
		this.strAdvanceAmount = strAdvanceAmount;
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
		return StrIpAddress;
	}

	public void setStrIpAddress(String strIpAddress) {
		StrIpAddress = strIpAddress;
	}

	public boolean isStrIssueEstCertificate() {
		return strIssueEstCertificate;
	}

	public void setStrIssueEstCertificate(boolean strIssueEstCertificate) {
		this.strIssueEstCertificate = strIssueEstCertificate;
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

	public String getStrAccType() {
		return strAccType;
	}

	public void setStrAccType(String strAccType) {
		this.strAccType = strAccType;
	}


	
}