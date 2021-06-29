package mrd.transaction.controller.fb;

import registration.controller.fb.CRNoFB;

public class PatientEmrAuditFB extends CRNoFB{
	
	private String strSearchPatCrNo;
	private String strEmrAuditTypeId;
	private String strAuditTypeName;
	private String strLitingType="0";
	private String strEmrAuditUserId;
	private String strEmrAuditUserName;
	private String strEpisodeCode;
	private String strAdmissionNo;
	private String strDEOName;
	private String strDEOSeatId;
	private String strDeoDateTime;
	private String strAuditStatus;
	private String strAuditStatusVal;
	//private String strPreviousAuditStatus;
	private String strRemark;
	private String strHospitalCode;
	private String strIsValid;
	private String strSeatId;
	private String strReportingType;
	private String strFromDate;
	private String strToDate;
	private String hmode;
	private String sysdate;
	private String startIndex;
	private String endIndex;
	private int currentPage=1;
	private String strPrimaryKey;
	private String strVisitNo;
	private String strPrimaryKeyIndex;
	private String strPreviousAuditDate;
	private String strPreviousAuditBy;
	private String strPreviousAuditStatus;
	private String strAuditStatusId;
	private String strVisitDate;
	private String strEntryDate;
	private String strDepartment;
	private String strDepartmentUnit;
	
	
	public String getStrEmrAuditTypeId() {
		return strEmrAuditTypeId;
	}
	public void setStrEmrAuditTypeId(String strEmrAuditTypeId) {
		this.strEmrAuditTypeId = strEmrAuditTypeId;
	}
	public String getStrAuditTypeName() {
		return strAuditTypeName;
	}
	public void setStrAuditTypeName(String strAuditTypeName) {
		this.strAuditTypeName = strAuditTypeName;
	}
	public String getStrLitingType() {
		return strLitingType;
	}
	public void setStrLitingType(String strLitingType) {
		this.strLitingType = strLitingType;
	}
	
	public String getStrEmrAuditUserId() {
		return strEmrAuditUserId;
	}
	public void setStrEmrAuditUserId(String strEmrAuditUserId) {
		this.strEmrAuditUserId = strEmrAuditUserId;
	}
	public String getStrEmrAuditUserName() {
		return strEmrAuditUserName;
	}
	public void setStrEmrAuditUserName(String strEmrAuditUserName) {
		this.strEmrAuditUserName = strEmrAuditUserName;
	}
	public String getStrEpisodeCode() {
		return strEpisodeCode;
	}
	public void setStrEpisodeCode(String strEpisodeCode) {
		this.strEpisodeCode = strEpisodeCode;
	}
	public String getStrAdmissionNo() {
		return strAdmissionNo;
	}
	public void setStrAdmissionNo(String strAdmissionNo) {
		this.strAdmissionNo = strAdmissionNo;
	}
	public String getStrDEOName() {
		return strDEOName;
	}
	public void setStrDEOName(String strDEOName) {
		this.strDEOName = strDEOName;
	}
	public String getStrDEOSeatId() {
		return strDEOSeatId;
	}
	public void setStrDEOSeatId(String strDEOSeatId) {
		this.strDEOSeatId = strDEOSeatId;
	}
	public String getStrDeoDateTime() {
		return strDeoDateTime;
	}
	public void setStrDeoDateTime(String strDeoDateTime) {
		this.strDeoDateTime = strDeoDateTime;
	}
	public String getStrAuditStatus() {
		return strAuditStatus;
	}
	public void setStrAuditStatus(String strAuditStatus) {
		this.strAuditStatus = strAuditStatus;
	}
	public String getStrAuditStatusVal() {
		return strAuditStatusVal;
	}
	public void setStrAuditStatusVal(String strAuditStatusVal) {
		this.strAuditStatusVal = strAuditStatusVal;
	}
	public String getStrRemark() {
		return strRemark;
	}
	public void setStrRemark(String strRemark) {
		this.strRemark = strRemark;
	}
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public String getStrIsValid() {
		return strIsValid;
	}
	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
	}
	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	public String getStrReportingType() {
		return strReportingType;
	}
	public void setStrReportingType(String strReportingType) {
		this.strReportingType = strReportingType;
	}
	public String getStrFromDate() {
		return strFromDate;
	}
	public void setStrFromDate(String strFromDate) {
		this.strFromDate = strFromDate;
	}
	public String getStrToDate() {
		return strToDate;
	}
	public void setStrToDate(String strToDate) {
		this.strToDate = strToDate;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getSysdate() {
		return sysdate;
	}
	public void setSysdate(String sysdate) {
		this.sysdate = sysdate;
	}
	public String getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(String startIndex) {
		this.startIndex = startIndex;
	}
	public String getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(String endIndex) {
		this.endIndex = endIndex;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public String getStrPrimaryKey() {
		return strPrimaryKey;
	}
	public void setStrPrimaryKey(String strPrimaryKey) {
		this.strPrimaryKey = strPrimaryKey;
	}
	public String getStrVisitNo() {
		return strVisitNo;
	}
	public void setStrVisitNo(String strVisitNo) {
		this.strVisitNo = strVisitNo;
	}
	public String getStrPrimaryKeyIndex() {
		return strPrimaryKeyIndex;
	}
	public void setStrPrimaryKeyIndex(String strPrimaryKeyIndex) {
		this.strPrimaryKeyIndex = strPrimaryKeyIndex;
	}
	public String getStrPreviousAuditDate() {
		return strPreviousAuditDate;
	}
	public void setStrPreviousAuditDate(String strPreviousAuditDate) {
		this.strPreviousAuditDate = strPreviousAuditDate;
	}
	public String getStrPreviousAuditBy() {
		return strPreviousAuditBy;
	}
	public void setStrPreviousAuditBy(String strPreviousAuditBy) {
		this.strPreviousAuditBy = strPreviousAuditBy;
	}
	public String getStrPreviousAuditStatus() {
		return strPreviousAuditStatus;
	}
	public void setStrPreviousAuditStatus(String strPreviousAuditStatus) {
		this.strPreviousAuditStatus = strPreviousAuditStatus;
	}
	public String getStrAuditStatusId() {
		return strAuditStatusId;
	}
	public void setStrAuditStatusId(String strAuditStatusId) {
		this.strAuditStatusId = strAuditStatusId;
	}
	public String getStrVisitDate() {
		return strVisitDate;
	}
	public void setStrVisitDate(String strVisitDate) {
		this.strVisitDate = strVisitDate;
	}
	public String getStrSearchPatCrNo() {
		return strSearchPatCrNo;
	}
	public void setStrSearchPatCrNo(String strSearchPatCrNo) {
		this.strSearchPatCrNo = strSearchPatCrNo;
	}
	public String getStrEntryDate() {
		return strEntryDate;
	}
	public void setStrEntryDate(String strEntryDate) {
		this.strEntryDate = strEntryDate;
	}
	public String getStrDepartment() {
		return strDepartment;
	}
	public void setStrDepartment(String strDepartment) {
		this.strDepartment = strDepartment;
	}
	public String getStrDepartmentUnit() {
		return strDepartmentUnit;
	}
	public void setStrDepartmentUnit(String strDepartmentUnit) {
		this.strDepartmentUnit = strDepartmentUnit;
	}
	

}
