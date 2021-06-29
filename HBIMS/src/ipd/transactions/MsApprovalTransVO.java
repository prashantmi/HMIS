package ipd.transactions;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;
import hisglobal.utility.TransferObject;

import java.util.ArrayList;

import javax.sql.rowset.WebRowSet;

public class MsApprovalTransVO implements TransferObject{
	
	private static final long serialVersionUID = 02L;
	
	private String strHospitalCode ="";
	private String strRoomNo = "";
	private String strprivateward ="";
	private String strbedvalue ="";
//	private String StrRoomValue ="";
	private String strDeptUnitCode ="";
	private WebRowSet strBedWs =null;
	private WebRowSet  StrRoomWs =null; 
	private String strCrNo ="";
	private String strPatStatus  ="";
	private String strPatStatusCode  ="";
	private String strPatCategory ="";
	private String strPatCategoryCode ="";
	private String strNname ="";
	private String strAdmDate ="";
	private String strCtDate="";
	private String strAdmNo ="";
	private String strFatherHusband ="";
	private String straddress ="";
	private String hstrCrNo= "";
	private String hstrAdviceNo= "";
	private String hstrBookDate= "";
	private String isOffline ="";
	private WebRowSet straddressWs=null;
	private ArrayList<String> strNotApprovedList=null;
	private ArrayList<String> strCancelList=null;
	private String strNotApprovedData ="";
	private String strCancelData ="";
    private String strDeptUnit ="";
	private String strProvisionDiagnosis="";
	private String strWaitPeriod = "";
	private String strConsultantName = "";
	private String strEpisodeNumber = "";
	private String strMlcNo = "";
	private String strEmpName ="";
	private String strEmp_No ="";//hidden
	private String strIsPgiEmp="0";
   	private String strIsDependent="";
   	private String strdependent ="";
	private String strBasicIncom ="";
	private String strDesigName="";
	private String strOfficeName ="";
	private String strOfficeAddress ="";
	private String strOfficeAddress1 ="";
	private String strOfficeAddress2 ="";
	private String strPhoneNo ="";
	private String strFormNo ="";
	private String strApprovAuthority ="";
	private String strRemark ="";
	private String strReason ="";
	private String strPropAdminssionDate ="";
	private String strageSex="";
	private String strGovtEmployee = "1";
	private String strGovtType ="";
	private String strGovtTypeHidden =""; 
	private String strapproveed ="1";
	private String strCancelDate ="";
	private String strRelation ="";
	private String hstrRelation ="";
	private String strcancel ="1";
	private String strSubmitDate ="";
	private String strSeatId ="10074";
	private String strLastModiSeat ="10074";
	private String strVerifiedBy="";
	private String strVerifiedDate =" ";
	private String strRequestDate ="";
	private WebRowSet strVerifiedByWs = null;
	private String strDeptUnitCmb ="";
	private WebRowSet strDeptUnitWs = null;
	private String strAdviceNo ="";
	private String strBooking_date="";
	private String strBookingQno ="";
	private String strWardype ="";
	private String strWardCode ="";
	private String strBedType ="";
	private String strBedCode ="";
	private String strPrefAdmDate ="";
	private String strApproveDate ="";
	private String strMsgString = "";
	private String strMsgType = "";
    private String strApprovedBY =""; 
    private String strApprovedStatus="";
	private WebRowSet[] approvalListWs = null;
	private WebRowSet strwardallotementListWs = null;
    private String strPriority ="";
    private String strPriorityReason ="";
    private String strCancelReason ="";
    private String strOrderBy ="";
   	private String strprivatewardName = "";
	private String strprivatewardValue ="";
	private String strRoomName ="";
	private String strRoomValue ="";
 	private String strroom = "";
 	private String strbedno = "";
	private WebRowSet strprivatewardWs = null;
	private WebRowSet strprivateRoomWs = null;
	private String strBuilding ="";
	private String strBlock ="";
	private String hstrRoomType ="";
	private String strRoomType ="";
	private String strFloor ="";
    private String strListDate ="";
	private String strPrivateBed ="";
	private String strBedName ="";
	private String strWardName ="";
	
	
	private String hstrbedType ="";
	private String hstrbedcode ="";
	private String allotementlist ="";
	//local variable for occupation 
	
	
	private String  strOccEmpNo ="";     
	 private String  strOccEmpName ="";   
	 private String  strOccRelation =""; 	  
	 private String  strOccIsDept ="";        
	 private String  strOccIsGovtEmp  =""; 
	 private String   strOccBasic   ="";  	     
	 private String   strOccDesc   ="";    
	 private String   strOccOrgType  ="";
	 private String   strOccOffName  =""; 	 
	 private String   strOccAdd1 ="";		
	 private String   strOccAdd2 ="";		
	 private String   strOccCity ="";		
	 private String   strOccState="";		
	 private String   strOccOffPhNo	="";
	
	
	//hidden variables on approval page
	 
	 private String[] hcrnoArr =null;
     private String[] hadvnoArr =null;
	 private String[] hbkdateArr =null;
	 
	private String hcrno ="";
	private String hadvno ="";
	private String hbkdate ="";
	private String hrowlengthApproval ="";
	
	
	//in case of listing
	private String strListWardcode ="";
	private String strListWardName ="";
	private String strListWardtype ="";
	private String strListRoomtype ="";
	private String strListBedType ="";
	private String strListBedCode ="";
	private String strListRoomCode ="";
	private String strListBookingStatus ="";
	private String strListApprovalStatus ="";
	private String strListCrNo ="";
	private String strListAdviceNo ="";
	private String strListBookingDate ="";
	private String strListPatName ="";
	private String strMode ="";
	private ArrayList<String> strApprovepatList =null;
	private String StrAllocatedList =null;
	private ArrayList<String> strBedList =null;
	private String strAllotementlist ="";
	private String strErr ="";
	private String strdateLable ="";
	private String strHiddenDesignation ="";
	private String strFromDate ="";
	private String strToDate ="";
    private String strErrorMsg =""; 
	private String strBedLable="";
	private String strRoomBedNo="";
	private String strWardNo="";
	private String strEmployeeComboValues="";
	private String strWardType="";
	private String[] strChk1=null;
	private String strLength="";
	private WebRowSet wsPatApprovedList=null;
	private String strOccID="";
	private String strDeptCode="";
	private String strCancelBy="";
	private String strVerifyRemark="";
	private String strVerifyByName="";
	private String strVisitNo="";
	private WebRowSet strListWs = null;
	private String strAge="";
	private String strAgeCode="";
	private String strSex="";
	private String strPatCatCode="";
	private String appStatus="1";
	
	private String strAdmissionType="";
	private String strSurgeryDate="";
	private String strLengthOfStay="";
	private String strVisitValue="";
	private WebRowSet PreviousDiagnosisWs=null;
	private String strPriorityType="";
	private String strUrgentTypeRemarks="";
	private String strUrgentTypeReason="";
	private String strIsdead="0";
	private String strDepartmentValue = "";
	private String strDeptComboValues="";
	private String strUnitValue = "";
	private String strWard = "0";
	private WebRowSet strDeptComboValuesWs=null;
	private String strDepartment = "";
	private String strPrimaryCategory = "0";
	private String strSecondaryCategory = "0";
	private String strUnit = "";
	
	private String strRoomCode = "";
	private String strTreatmentCategoryCode = "";
	private String strUnitCode = "";
	private WebRowSet wardWS = null;
	private WebRowSet treatmentCategoryWs = null;
	private String strDepartmentCode="";
	private WebRowSet strUnitComboValuesWs=null;
	private WebRowSet consultantNameWs = null;
	private WebRowSet roomTypeWs = null;
	private WebRowSet bedTypeWs = null;

	private WebRowSet wardTYPES = null;
	private WebRowSet departTypeWS = null;
	
	private WebRowSet roomWs = null;
	private String strFlag = "0";
	private String strPatName="";
	private String strWardTypeCode = "";
	private boolean deptNameFound = false;






	
	
	public String getStrUnitCode() {
		return strUnitCode;
	}
	public void setStrUnitCode(String strUnitCode) {
		this.strUnitCode = strUnitCode;
	}
	public String getStrPriorityType() {
		return strPriorityType;
	}
	public void setStrPriorityType(String strPriorityType) {
		this.strPriorityType = strPriorityType;
	}
	public String getStrUrgentTypeRemarks() {
		return strUrgentTypeRemarks;
	}
	public void setStrUrgentTypeRemarks(String strUrgentTypeRemarks) {
		this.strUrgentTypeRemarks = strUrgentTypeRemarks;
	}
	public String getStrUrgentTypeReason() {
		return strUrgentTypeReason;
	}
	public void setStrUrgentTypeReason(String strUrgentTypeReason) {
		this.strUrgentTypeReason = strUrgentTypeReason;
	}
	public String getStrVisitValue() {
		return strVisitValue;
	}
	public void setStrVisitValue(String strVisitValue) {
		this.strVisitValue = strVisitValue;
	}
	public WebRowSet getPreviousDiagnosisWs() {
		return PreviousDiagnosisWs;
	}
	public void setPreviousDiagnosisWs(WebRowSet previousDiagnosisWs) {
		PreviousDiagnosisWs = previousDiagnosisWs;
	}
	public String getStrAdmissionType() {
		return strAdmissionType;
	}
	public void setStrAdmissionType(String strAdmissionType) {
		this.strAdmissionType = strAdmissionType;
	}
	public String getStrSurgeryDate() {
		return strSurgeryDate;
	}
	public void setStrSurgeryDate(String strSurgeryDate) {
		this.strSurgeryDate = strSurgeryDate;
	}
	public String getStrLengthOfStay() {
		return strLengthOfStay;
	}
	public void setStrLengthOfStay(String strLengthOfStay) {
		this.strLengthOfStay = strLengthOfStay;
	}
	/**
	 * @return the strAge
	 */
	public String getStrAge() {
		return strAge;
	}
	/**
	 * @param strAge the strAge to set
	 */
	public void setStrAge(String strAge) {
		this.strAge = strAge;
	}
	/**
	 * @return the strAgeCode
	 */
	public String getStrAgeCode() {
		return strAgeCode;
	}
	/**
	 * @param strAgeCode the strAgeCode to set
	 */
	public void setStrAgeCode(String strAgeCode) {
		this.strAgeCode = strAgeCode;
	}
	/**
	 * @return the strSex
	 */
	public String getStrSex() {
		return strSex;
	}
	/**
	 * @param strSex the strSex to set
	 */
	public void setStrSex(String strSex) {
		this.strSex = strSex;
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
	public WebRowSet getStrListWs() {
		return strListWs;
	}
	public void setStrListWs(WebRowSet strListWs) {
		this.strListWs = strListWs;
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
	 * @return the strVerifyByName
	 */
	public String getStrVerifyByName() {
		return strVerifyByName;
	}
	/**
	 * @param strVerifyByName the strVerifyByName to set
	 */
	public void setStrVerifyByName(String strVerifyByName) {
		this.strVerifyByName = strVerifyByName;
	}
	/**
	 * @return the strVerifyRemark
	 */
	public String getStrVerifyRemark() {
		return strVerifyRemark;
	}
	/**
	 * @param strVerifyRemark the strVerifyRemark to set
	 */
	public void setStrVerifyRemark(String strVerifyRemark) {
		this.strVerifyRemark = strVerifyRemark;
	}
	/**
	 * @return the strCancelBy
	 */
	public String getStrCancelBy() {
		return strCancelBy;
	}
	/**
	 * @param strCancelBy the strCancelBy to set
	 */
	public void setStrCancelBy(String strCancelBy) {
		this.strCancelBy = strCancelBy;
	}
	/**
	 * @return the strDeptCode
	 */
	public String getStrDeptCode() {
		return strDeptCode;
	}
	/**
	 * @param strDeptCode the strDeptCode to set
	 */
	public void setStrDeptCode(String strDeptCode) {
		this.strDeptCode = strDeptCode;
	}
	/**
	 * @return the strOccID
	 */
	public String getStrOccID() {
		return strOccID;
	}
	/**
	 * @param strOccID the strOccID to set
	 */
	public void setStrOccID(String strOccID) {
		this.strOccID = strOccID;
	}
	/**
	 * @return the wsPatApprovedList
	 */
	public WebRowSet getWsPatApprovedList() {
		return wsPatApprovedList;
	}
	/**
	 * @param wsPatApprovedList the wsPatApprovedList to set
	 */
	public void setWsPatApprovedList(WebRowSet wsPatApprovedList) {
		this.wsPatApprovedList = wsPatApprovedList;
	}
	/**
	 * @return the strLength
	 */
	public String getStrLength() {
		return strLength;
	}
	/**
	 * @param strLength the strLength to set
	 */
	public void setStrLength(String strLength) {
		this.strLength = strLength;
	}
	/**
	 * @return the strChk1
	 */
	public String[] getStrChk1() {
		return strChk1;
	}
	/**
	 * @param strChk1 the strChk1 to set
	 */
	public void setStrChk1(String[] strChk1) {
		this.strChk1 = strChk1;
	}
	/**
	 * @return the strWardType
	 */
	public String getStrWardType() {
		return strWardType;
	}
	/**
	 * @param strWardType the strWardType to set
	 */
	public void setStrWardType(String strWardType) {
		this.strWardType = strWardType;
	}
	/**
	 * @return the strEmployeeComboValues
	 */
	public String getStrEmployeeComboValues() {
		return strEmployeeComboValues;
	}
	/**
	 * @param strEmployeeComboValues the strEmployeeComboValues to set
	 */
	public void setStrEmployeeComboValues(String strEmployeeComboValues) {
		this.strEmployeeComboValues = strEmployeeComboValues;
	}
	/**
	 * @return the strRoomBedNo
	 */
	public String getStrRoomBedNo() {
		return strRoomBedNo;
	}
	/**
	 * @param strRoomBedNo the strRoomBedNo to set
	 */
	public void setStrRoomBedNo(String strRoomBedNo) {
		this.strRoomBedNo = strRoomBedNo;
	}
	/**
	 * @return the strWardNo
	 */
	public String getStrWardNo() {
		return strWardNo;
	}
	/**
	 * @param strWardNo the strWardNo to set
	 */
	public void setStrWardNo(String strWardNo) {
		this.strWardNo = strWardNo;
	}
	/**
	 * @return the strBedLable
	 */
	public String getStrBedLable() {
		return strBedLable;
	}
	/**
	 * @param strBedLable the strBedLable to set
	 */
	public void setStrBedLable(String strBedLable) {
		this.strBedLable = strBedLable;
	}
	public String getStrHiddenDesignation() {
		return strHiddenDesignation;
	}
	public void setStrHiddenDesignation(String strHiddenDesignation) {
		this.strHiddenDesignation = strHiddenDesignation;
	}
	public String getStrdateLable() {
		return strdateLable;
	}
	public void setStrdateLable(String strdateLable) {
		this.strdateLable = strdateLable;
	}
	public String getStrErr() {
		return strErr;
	}
	public void setStrErr(String strErr) {
		this.strErr = strErr;
	}
	public String getStrAllotementlist() {
		return strAllotementlist;
	}
	public void setStrAllotementlist(String strAllotementlist) {
		this.strAllotementlist = strAllotementlist;
	}
	public String getStrMode() {
		return strMode;
	}
	public void setStrMode(String strMode) {
		this.strMode = strMode;
	}
	
	public ArrayList<String> getStrApprovepatList() {
		return strApprovepatList;
	}
	public void setStrApprovepatList(ArrayList<String> strApprovepatList) {
		this.strApprovepatList = strApprovepatList;
	}
	public ArrayList<String> getStrBedList() {
		return strBedList;
	}
	public void setStrBedList(ArrayList<String> strBedList) {
		this.strBedList = strBedList;
	}
	public String getHrowlengthApproval() {
		return hrowlengthApproval;
	}
	public void setHrowlengthApproval(String hrowlengthApproval) {
		this.hrowlengthApproval = hrowlengthApproval;
	}
	public String getHcrno() {
		return hcrno;
	}
	public void setHcrno(String hcrno) {
		this.hcrno = hcrno;
	}
	public String getHadvno() {
		return hadvno;
	}
	public void setHadvno(String hadvno) {
		this.hadvno = hadvno;
	}
	public String getHbkdate() {
		return hbkdate;
	}
	public void setHbkdate(String hbkdate) {
		this.hbkdate = hbkdate;
	}
	public WebRowSet[] getApprovalListWs() {
		return approvalListWs;
	}
	public void setApprovalListWs(WebRowSet[] approvalListWs) {
		this.approvalListWs = approvalListWs;
	}
	public String getStrPropAdminssionDate() {
		return strPropAdminssionDate;
	}
	public void setStrPropAdminssionDate(String strPropAdminssionDate) {
		this.strPropAdminssionDate = strPropAdminssionDate;
	}
	public String getStrCrNo() {
		return strCrNo;
	}
	public void setStrCrNo(String strCrNo) {
		this.strCrNo = strCrNo;
	}
	public String getStrPatCategory() {
		return strPatCategory;
	}
	public void setStrPatCategory(String strPatCategory) {
		this.strPatCategory = strPatCategory;
	}
	public String getStrNname() {
		return strNname;
	}
	public void setStrNname(String strNname) {
		this.strNname = strNname;
	}
	public String getStrFatherHusband() {
		return strFatherHusband;
	}
	public void setStrFatherHusband(String strFatherHusband) {
		this.strFatherHusband = strFatherHusband;
	}
	
		public String getStrDeptUnit() {
		return strDeptUnit;
	}
	public void setStrDeptUnit(String strDeptUnit) {
		this.strDeptUnit = strDeptUnit;
	}
		public String getStrEmpName() {
		return strEmpName;
	}
	public void setStrEmpName(String strEmpName) {
		this.strEmpName = strEmpName;
	}
	public String getStrDesigName() {
		return strDesigName;
	}
	public void setStrDesigName(String strDesigName) {
		this.strDesigName = strDesigName;
	}
	public String getStrOfficeName() {
		return strOfficeName;
	}
	public void setStrOfficeName(String strOfficeName) {
		this.strOfficeName = strOfficeName;
	}
	public String getStrOfficeAddress() {
		return strOfficeAddress;
	}
	public void setStrOfficeAddress(String strOfficeAddress) {
		this.strOfficeAddress = strOfficeAddress;
	}
	
	public String getStrApprovAuthority() {
		return strApprovAuthority;
	}
	public void setStrApprovAuthority(String strApprovAuthority) {
		this.strApprovAuthority = strApprovAuthority;
	}
	public String getStrRemark() {
		return strRemark;
	}
	public void setStrRemark(String strRemark) {
		this.strRemark = strRemark;
	}
	public String getStrReason() {
		return strReason;
	}
	public void setStrReason(String strReason) {
		this.strReason = strReason;
	}
	public String getStrWaitPeriod() {
		return strWaitPeriod;
	}
	public void setStrWaitPeriod(String strWaitPeriod) {
		this.strWaitPeriod = strWaitPeriod;
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
	
	
	public String getStrGovtEmployee() {
		return strGovtEmployee;
	}
	public void setStrGovtEmployee(String strGovtEmployee) {
		this.strGovtEmployee = strGovtEmployee;
	}
	public String getStrapproveed() {
		return strapproveed;
	}
	public void setStrapproveed(String strapproveed) {
		this.strapproveed = strapproveed;
	}
	public String getStrCancelDate() {
		return strCancelDate;
	}
	public void setStrCancelDate(String strCancelDate) {
		this.strCancelDate = strCancelDate;
	}
	public String getStrcancel() {
		return strcancel;
	}
	public void setStrcancel(String strcancel) {
		this.strcancel = strcancel;
	}
	public String getStrOfficeAddress1() {
		return strOfficeAddress1;
	}
	public void setStrOfficeAddress1(String strOfficeAddress1) {
		this.strOfficeAddress1 = strOfficeAddress1;
	}
	public String getStrOfficeAddress2() {
		return strOfficeAddress2;
	}
	public void setStrOfficeAddress2(String strOfficeAddress2) {
		this.strOfficeAddress2 = strOfficeAddress2;
	}
	public String getStrRelation() {
		return strRelation;
	}
	public void setStrRelation(String strRelation) {
		this.strRelation = strRelation;
	}
	public String getStrSubmitDate() {
		return strSubmitDate;
	}
	public void setStrSubmitDate(String strSubmitDate) {
		this.strSubmitDate = strSubmitDate;
	}
	public WebRowSet getStrVerifiedByWs() {
		return strVerifiedByWs;
	}
	public void setStrVerifiedByWs(WebRowSet strVerifiedByWs) {
		this.strVerifiedByWs = strVerifiedByWs;
	}
	public String getStrVerifiedBy() {
		return strVerifiedBy;
	}
	public void setStrVerifiedBy(String strVerifiedBy) {
		this.strVerifiedBy = strVerifiedBy;
	}
	public String getStrageSex() {
		return strageSex;
	}
	public void setStrageSex(String strageSex) {
		this.strageSex = strageSex;
	}
	public String getStrPatStatus() {
		return strPatStatus;
	}
	public void setStrPatStatus(String strPatStatus) {
		this.strPatStatus = strPatStatus;
	}
	public String getStraddress() {
		return straddress;
	}
	public void setStraddress(String straddress) {
		this.straddress = straddress;
	}
	public WebRowSet getStraddressWs() {
		return straddressWs;
	}
	public void setStraddressWs(WebRowSet straddressWs) {
		this.straddressWs = straddressWs;
	}
	public void setStrProvisionDiagnosis(String strProvisionDiagnosis) {
		this.strProvisionDiagnosis = strProvisionDiagnosis;
	}
	public String getStrProvisionDiagnosis() {
		return strProvisionDiagnosis;
	}
	public String getStrBasicIncom() {
		return strBasicIncom;
	}
	public void setStrBasicIncom(String strBasicIncom) {
		this.strBasicIncom = strBasicIncom;
	}
	public String getStrFormNo() {
		return strFormNo;
	}
	public void setStrFormNo(String strFormNo) {
		this.strFormNo = strFormNo;
	}
	public String getStrIsPgiEmp() {
		return strIsPgiEmp;
	}
	public void setStrIsPgiEmp(String strIsPgiEmp) {
		this.strIsPgiEmp = strIsPgiEmp;
	}
	
	public String getStrPhoneNo() {
		return strPhoneNo;
	}
	public void setStrPhoneNo(String strPhoneNo) {
		this.strPhoneNo = strPhoneNo;
	}
	public String getStrIsDependent() {
		return strIsDependent;
	}
	public void setStrIsDependent(String strIsDependent) {
		this.strIsDependent = strIsDependent;
	}
	public String getStrdependent() {
		return strdependent;
	}
	public void setStrdependent(String strdependent) {
		this.strdependent = strdependent;
	}
	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	public WebRowSet getStrDeptUnitWs() {
		return strDeptUnitWs;
	}
	public void setStrDeptUnitWs(WebRowSet strDeptUnitWs) {
		this.strDeptUnitWs = strDeptUnitWs;
	}
	public String getStrDeptUnitCmb() {
		return strDeptUnitCmb;
	}
	public void setStrDeptUnitCmb(String strDeptUnitCmb) {
		this.strDeptUnitCmb = strDeptUnitCmb;
	}
	public String getStrGovtType() {
		return strGovtType;
	}
	public void setStrGovtType(String strGovtType) {
		this.strGovtType = strGovtType;
	}
	public String getStrVerifiedDate() {
		return strVerifiedDate;
	}
	public void setStrVerifiedDate(String strVerifiedDate) {
		this.strVerifiedDate = strVerifiedDate;
	}
	public String getStrAdviceNo() {
		return strAdviceNo;
	}
	public void setStrAdviceNo(String strAdviceNo) {
		this.strAdviceNo = strAdviceNo;
	}
	public String getStrBooking_date() {
		return strBooking_date;
	}
	public void setStrBooking_date(String strBooking_date) {
		this.strBooking_date = strBooking_date;
	}
	public String getStrBookingQno() {
		return strBookingQno;
	}
	public void setStrBookingQno(String strBookingQno) {
		this.strBookingQno = strBookingQno;
	}
	public String getStrWardype() {
		return strWardype;
	}
	public void setStrWardype(String strWardype) {
		this.strWardype = strWardype;
	}
	public String getStrPrefAdmDate() {
		return strPrefAdmDate;
	}
	public void setStrPrefAdmDate(String strPrefAdmDate) {
		this.strPrefAdmDate = strPrefAdmDate;
	}
	public String getStrGovtTypeHidden() {
		return strGovtTypeHidden;
	}
	public void setStrGovtTypeHidden(String strGovtTypeHidden) {
		this.strGovtTypeHidden = strGovtTypeHidden;
	}
	public String getStrAdmDate() {
		return strAdmDate;
	}
	public void setStrAdmDate(String strAdmDate) {
		this.strAdmDate = strAdmDate;
	}
	public String getStrAdmNo() {
		return strAdmNo;
	}
	public void setStrAdmNo(String strAdmNo) {
		this.strAdmNo = strAdmNo;
	}
	public String getStrApproveDate() {
		return strApproveDate;
	}
	public void setStrApproveDate(String strApproveDate) {
		this.strApproveDate = strApproveDate;
	}
	public String getStrApprovedBY() {
		return strApprovedBY;
	}
	public void setStrApprovedBY(String strApprovedBY) {
		this.strApprovedBY = strApprovedBY;
	}
	public String getStrApprovedStatus() {
		return strApprovedStatus;
	}
	public void setStrApprovedStatus(String strApprovedStatus) {
		this.strApprovedStatus = strApprovedStatus;
	}
	public String getStrEmp_No() {
		return strEmp_No;
	}
	public void setStrEmp_No(String strEmp_No) {
		this.strEmp_No = strEmp_No;
	}
	public String getHstrRelation() {
		return hstrRelation;
	}
	public void setHstrRelation(String hstrRelation) {
		this.hstrRelation = hstrRelation;
	}
	public WebRowSet getStrprivatewardWs() {
		return strprivatewardWs;
	}
	public void setStrprivatewardWs(WebRowSet strprivatewardWs) {
		this.strprivatewardWs = strprivatewardWs;
	}
	

	public String getStrprivatewardName() {
		return strprivatewardName;
	}
	
	
	public String getStrroom() {
		return strroom;
	}
	public void setStrroom(String strroom) {
		this.strroom = strroom;
	}
	public String getStrbedno() {
		return strbedno;
	}
	public void setStrbedno(String strbedno) {
		this.strbedno = strbedno;
	}
	public WebRowSet getStrprivateRoomWs() {
		return strprivateRoomWs;
	}
	public void setStrprivateRoomWs(WebRowSet strprivateRoomWs) {
		this.strprivateRoomWs = strprivateRoomWs;
	}
	public String getStrprivatewardValue() {
		return strprivatewardValue;
	}
	public void setStrprivatewardValue(String strprivatewardValue) {
		this.strprivatewardValue = strprivatewardValue;
	}
	public String getStrRoomName() {
		return strRoomName;
	}
	public void setStrRoomName(String strRoomName) {
		this.strRoomName = strRoomName;
	}
	public String getStrRoomValue() {
		return strRoomValue;
	}
	public void setStrRoomValue(String strRoomValue) {
		this.strRoomValue = strRoomValue;
	}
	public String getStrBuilding() {
		return strBuilding;
	}
	public void setStrBuilding(String strBuilding) {
		this.strBuilding = strBuilding;
	}
	public String getStrBlock() {
		return strBlock;
	}
	public void setStrBlock(String strBlock) {
		this.strBlock = strBlock;
	}
	public String getStrRoomType() {
		return strRoomType;
	}
	public void setStrRoomType(String strRoomType) {
		this.strRoomType = strRoomType;
	}
	public String getStrFloor() {
		return strFloor;
	}
	public void setStrFloor(String strFloor) {
		this.strFloor = strFloor;
	}
	public String getStrListDate() {
		return strListDate;
	}
	public void setStrListDate(String strListDate) {
		this.strListDate = strListDate;
	}
	public String getStrPrivateBed() {
		return strPrivateBed;
	}
	public void setStrPrivateBed(String strPrivateBed) {
		this.strPrivateBed = strPrivateBed;
	}
		public String getHstrbedType() {
		return hstrbedType;
	}
	public void setHstrbedType(String hstrbedType) {
		this.hstrbedType = hstrbedType;
	}
	public String getHstrRoomType() {
		return hstrRoomType;
	}
	public void setHstrRoomType(String hstrRoomType) {
		this.hstrRoomType = hstrRoomType;
	}
	public String getStrPriority() {
		return strPriority;
	}
	public void setStrPriority(String strPriority) {
		this.strPriority = strPriority;
	}
	public String getHstrbedcode() {
		return hstrbedcode;
	}
	public void setHstrbedcode(String hstrbedcode) {
		this.hstrbedcode = hstrbedcode;
	}
	public String getStrPatCategoryCode() {
		return strPatCategoryCode;
	}
	public void setStrPatCategoryCode(String strPatCategoryCode) {
		this.strPatCategoryCode = strPatCategoryCode;
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
	public String getStrOccRelation() {
		return strOccRelation;
	}
	public void setStrOccRelation(String strOccRelation) {
		this.strOccRelation = strOccRelation;
	}
	public String getStrOccIsDept() {
		return strOccIsDept;
	}
	public void setStrOccIsDept(String strOccIsDept) {
		this.strOccIsDept = strOccIsDept;
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
	public String getStrPriorityReason() {
		return strPriorityReason;
	}
	public void setStrPriorityReason(String strPriorityReason) {
		this.strPriorityReason = strPriorityReason;
	}
	public String getStrOrderBy() {
		return strOrderBy;
	}
	public void setStrOrderBy(String strOrderBy) {
		this.strOrderBy = strOrderBy;
	}
	public String getStrListWardcode() {
		return strListWardcode;
	}
	public void setStrListWardcode(String strListWardcode) {
		this.strListWardcode = strListWardcode;
	}
	public String getStrListWardtype() {
		return strListWardtype;
	}
	public void setStrListWardtype(String strListWardtype) {
		this.strListWardtype = strListWardtype;
	}
	public String getStrListRoomtype() {
		return strListRoomtype;
	}
	public void setStrListRoomtype(String strListRoomtype) {
		this.strListRoomtype = strListRoomtype;
	}
	public String getStrListCrNo() {
		return strListCrNo;
	}
	public void setStrListCrNo(String strListCrNo) {
		this.strListCrNo = strListCrNo;
	}
	public String getStrListAdviceNo() {
		return strListAdviceNo;
	}
	public void setStrListAdviceNo(String strListAdviceNo) {
		this.strListAdviceNo = strListAdviceNo;
	}
	public String getStrListBookingDate() {
		return strListBookingDate;
	}
	public void setStrListBookingDate(String strListBookingDate) {
		this.strListBookingDate = strListBookingDate;
	}
	public String getStrListBedType() {
		return strListBedType;
	}
	public void setStrListBedType(String strListBedType) {
		this.strListBedType = strListBedType;
	}
	public String getStrListBedCode() {
		return strListBedCode;
	}
	public void setStrListBedCode(String strListBedCode) {
		this.strListBedCode = strListBedCode;
	}
	public String getStrListBookingStatus() {
		return strListBookingStatus;
	}
	public void setStrListBookingStatus(String strListBookingStatus) {
		this.strListBookingStatus = strListBookingStatus;
	}
	public String getStrListRoomCode() {
		return strListRoomCode;
	}
	public void setStrListRoomCode(String strListRoomCode) {
		this.strListRoomCode = strListRoomCode;
	}
	public String getStrListApprovalStatus() {
		return strListApprovalStatus;
	}
	public void setStrListApprovalStatus(String strListApprovalStatus) {
		this.strListApprovalStatus = strListApprovalStatus;
	}
	
	public String getStrListWardName() {
		return strListWardName;
	}
	public void setStrListWardName(String strListWardName) {
		this.strListWardName = strListWardName;
	}
	public String getStrListPatName() {
		return strListPatName;
	}
	public void setStrListPatName(String strListPatName) {
		this.strListPatName = strListPatName;
	}
	public String getStrWardCode() {
		return strWardCode;
	}
	public void setStrWardCode(String strWardCode) {
		this.strWardCode = strWardCode;
	}
	public String getStrBedType() {
		return strBedType;
	}
	public void setStrBedType(String strBedType) {
		this.strBedType = strBedType;
	}
	public String getStrBedCode() {
		return strBedCode;
	}
	public void setStrBedCode(String strBedCode) {
		this.strBedCode = strBedCode;
	}
	public String getStrCancelReason() {
		return strCancelReason;
	}
	public void setStrCancelReason(String strCancelReason) {
		this.strCancelReason = strCancelReason;
	}
	
	public void setStrNotApprovedList(ArrayList<String> strNotApprovedList) {
		this.strNotApprovedList = strNotApprovedList;
	}
	public ArrayList<String> getStrNotApprovedList() {
		return strNotApprovedList;
	}
	public String getStrNotApprovedData() {
		return strNotApprovedData;
	}
	public void setStrNotApprovedData(String strNotApprovedData) {
		this.strNotApprovedData = strNotApprovedData;
	}

	public String getStrCtDate() {
		
		HisUtil util = new HisUtil("ipd masApproval", "MsApprovalTransFB");

		try {
			strCtDate = util.getASDate("dd-MMM-yyyy");
		
		} catch (Exception e) {

			new HisException("ipd ms approval",
					"MsApprovalTransFB.getStrCtDate()", e.getMessage());
		}finally{
			util = null;
		}
		
		return strCtDate;
	}
	public ArrayList<String> getStrCancelList() {
		return strCancelList;
	}
	public void setStrCancelList(ArrayList<String> strCancelList) {
		this.strCancelList = strCancelList;
	}
	public String getStrCancelData() {
		return strCancelData;
	}
	public void setStrCancelData(String strCancelData) {
		this.strCancelData = strCancelData;
	}
	public String getAllotementlist() {
		return allotementlist;
	}
	public void setAllotementlist(String allotementlist) {
		this.allotementlist = allotementlist;
	}
	public String getStrAllocatedList() {
		return StrAllocatedList;
	}
	public void setStrAllocatedList(String strAllocatedList) {
		StrAllocatedList = strAllocatedList;
	}
	
	
	
	public void setStrBedWs(WebRowSet strBedWs) {
		this.strBedWs = strBedWs;
	}
	public WebRowSet getStrBedWs() {
		return strBedWs;
	}
	public String getStrbedvalue() {
		return strbedvalue;
	}
	public void setStrbedvalue(String strbedvalue) {
		this.strbedvalue = strbedvalue;
	}
	public String getStrPatStatusCode() {
		return strPatStatusCode;
	}
	public void setStrPatStatusCode(String strPatStatusCode) {
		this.strPatStatusCode = strPatStatusCode;
	}
	public String getStrLastModiSeat() {
		return strLastModiSeat;
	}
	public void setStrLastModiSeat(String strLastModiSeat) {
		this.strLastModiSeat = strLastModiSeat;
	}
	public String getStrRequestDate() {
		return strRequestDate;
	}
	public void setStrRequestDate(String strRequestDate) {
		this.strRequestDate = strRequestDate;
	}
	public WebRowSet getStrRoomWs() {
		return StrRoomWs;
	}
	public void setStrRoomWs(WebRowSet strRoomWs) {
		StrRoomWs = strRoomWs;
	}
	public void setStrCtDate(String strCtDate) {
		this.strCtDate = strCtDate;
	}
	public WebRowSet getStrwardallotementListWs() {
		return strwardallotementListWs;
	}
	public void setStrwardallotementListWs(WebRowSet strwardallotementListWs) {
		this.strwardallotementListWs = strwardallotementListWs;
	}
	
	public void setStrprivatewardName(String strprivatewardName) {
		this.strprivatewardName = strprivatewardName;
	}
	
	public void setStrprivateward(String strprivateward) {
		this.strprivateward = strprivateward;
	}
	public String getStrprivateward() {
		return strprivateward;
	}
	public String getHstrCrNo() {
		return hstrCrNo;
	}
	public void setHstrCrNo(String hstrCrNo) {
		this.hstrCrNo = hstrCrNo;
	}
	public String getHstrAdviceNo() {
		return hstrAdviceNo;
	}
	public void setHstrAdviceNo(String hstrAdviceNo) {
		this.hstrAdviceNo = hstrAdviceNo;
	}
	public String getHstrBookDate() {
		return hstrBookDate;
	}
	public void setHstrBookDate(String hstrBookDate) {
		this.hstrBookDate = hstrBookDate;
	}
	public String getStrDeptUnitCode() {
		return strDeptUnitCode;
	}
	public void setStrDeptUnitCode(String strDeptUnitCode) {
		this.strDeptUnitCode = strDeptUnitCode;
	}
	public String getStrBedName() {
		return strBedName;
	}
	public void setStrBedName(String strBedName) {
		this.strBedName = strBedName;
	}
	public String getStrWardName() {
		return strWardName;
	}
	public void setStrWardName(String strWardName) {
		this.strWardName = strWardName;
	}
	public String getStrRoomNo() {
		return strRoomNo;
	}
	public void setStrRoomNo(String strRoomNo) {
		this.strRoomNo = strRoomNo;
	}
	

	
	public String getStrFromDate() {
	
		HisUtil util = new HisUtil("ipd masApproval", "MsApprovalTransFB");

		try {
			strFromDate = util.getASDate("dd-MMM-yyyy");
		
		} catch (Exception e) {

			new HisException("ipd ms approval",
					"MsApprovalTransFB.getStrFromDate()", e.getMessage());
		}finally{
			util = null;
		}
			
		return strFromDate;
	}
	
	public void setStrFromDate(String strFromDate) {
		this.strFromDate = strFromDate;
	}
	public String getStrToDate() {
		
		
		
		HisUtil util = new HisUtil("ipd masApproval", "MsApprovalTransFB");

		try {
			strToDate = util.getASDate("dd-MMM-yyyy");
		
		} catch (Exception e) {

			new HisException("ipd ms approval",
					"MsApprovalTransFB.getStrToDate()", e.getMessage());
		}finally{
			util = null;
		}
			
		return strToDate;
	
	}
	
	public void setStrToDate(String strToDate) {
		this.strToDate = strToDate;
	}
	public String[] getHcrnoArr() {
		return hcrnoArr;
	}
	public void setHcrnoArr(String[] hcrnoArr) {
		this.hcrnoArr = hcrnoArr;
	}
	
	public void setHadvnoArr(String[] hadvnoArr) {
		this.hadvnoArr = hadvnoArr;
	}
	public void setHbkdateArr(String[] hbkdateArr) {
		this.hbkdateArr = hbkdateArr;
	}
	public String[] getHadvnoArr() {
		return hadvnoArr;
	}
	public String[] getHbkdateArr() {
		return hbkdateArr;
	}
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public String getStrErrorMsg() {
		return strErrorMsg;
	}
	public void setStrErrorMsg(String strErrorMsg) {
		this.strErrorMsg = strErrorMsg;
	}
	public String getIsOffline() {
		return isOffline;
	}
	public void setIsOffline(String isOffline) {
		this.isOffline = isOffline;
	}
	public String getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}
	public String getStrIsdead() {
		return strIsdead;
	}
	public void setStrIsdead(String strIsdead) {
		this.strIsdead = strIsdead;
	}
	public String getStrDepartmentValue() {
		return strDepartmentValue;
	}
	public void setStrDepartmentValue(String strDepartmentValue) {
		this.strDepartmentValue = strDepartmentValue;
	}
	public String getStrDeptComboValues() {
		return strDeptComboValues;
	}
	public void setStrDeptComboValues(String strDeptComboValues) {
		this.strDeptComboValues = strDeptComboValues;
	}
	public String getStrUnitValue() {
		return strUnitValue;
	}
	public void setStrUnitValue(String strUnitValue) {
		this.strUnitValue = strUnitValue;
	}
	public String getStrWard() {
		return strWard;
	}
	public void setStrWard(String strWard) {
		this.strWard = strWard;
	}
	public WebRowSet getStrDeptComboValuesWs() {
		return strDeptComboValuesWs;
	}
	public void setStrDeptComboValuesWs(WebRowSet strDeptComboValuesWs) {
		this.strDeptComboValuesWs = strDeptComboValuesWs;
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
	public WebRowSet getWardWS() {
		return wardWS;
	}
	public void setWardWS(WebRowSet wardWS) {
		this.wardWS = wardWS;
	}
	public WebRowSet getTreatmentCategoryWs() {
		return treatmentCategoryWs;
	}
	public void setTreatmentCategoryWs(WebRowSet treatmentCategoryWs) {
		this.treatmentCategoryWs = treatmentCategoryWs;
	}
	public String getStrDepartmentCode() {
		return strDepartmentCode;
	}
	public void setStrDepartmentCode(String strDepartmentCode) {
		this.strDepartmentCode = strDepartmentCode;
	}
	public WebRowSet getStrUnitComboValuesWs() {
		return strUnitComboValuesWs;
	}
	public void setStrUnitComboValuesWs(WebRowSet strUnitComboValuesWs) {
		this.strUnitComboValuesWs = strUnitComboValuesWs;
	}
	public WebRowSet getConsultantNameWs() {
		return consultantNameWs;
	}
	public void setConsultantNameWs(WebRowSet consultantNameWs) {
		this.consultantNameWs = consultantNameWs;
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
	public WebRowSet getWardTYPES() {
		return wardTYPES;
	}
	public void setWardTYPES(WebRowSet wardTYPES) {
		this.wardTYPES = wardTYPES;
	}
	public WebRowSet getDepartTypeWS() {
		return departTypeWS;
	}
	public void setDepartTypeWS(WebRowSet departTypeWS) {
		this.departTypeWS = departTypeWS;
	}
	public WebRowSet getRoomWs() {
		return roomWs;
	}
	public void setRoomWs(WebRowSet roomWs) {
		this.roomWs = roomWs;
	}
	public String getStrFlag() {
		return strFlag;
	}
	public void setStrFlag(String strFlag) {
		this.strFlag = strFlag;
	}
	public String getStrPatName() {
		return strPatName;
	}
	public void setStrPatName(String strPatName) {
		this.strPatName = strPatName;
	}
	public String getStrWardTypeCode() {
		return strWardTypeCode;
	}
	public void setStrWardTypeCode(String strWardTypeCode) {
		this.strWardTypeCode = strWardTypeCode;
	}
	public boolean isDeptNameFound() {
		return deptNameFound;
	}
	public void setDeptNameFound(boolean deptNameFound) {
		this.deptNameFound = deptNameFound;
	}
	
	
	
	
	
}