package hr.pis.empReg.transactions.controller.actionsupport;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;


import registration.config.CharacterEncoding;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public abstract class EmpRegistrationSUP extends ActionSupport implements Preparable,ServletRequestAware, ServletResponseAware, SessionAware, ServletContextAware
{
	protected static final long serialVersionUID = 1L;

	protected HttpServletRequest objRequest;
	protected HttpServletResponse objResponse;
	protected ServletContext objContext;
	protected Map mapSesion;

	protected String intNatureOfJobId;
	protected String strIsExistingEmployee;
	protected String strEmployeeNumber;
    protected String intRecordSlNo;
	protected String strOldEmployeeNumber;
    protected String strUID;
    protected String intAppellationCode1;
    protected String intAppellationCode2;
    protected String strEmployeeFullName;
    protected String strEmployeeFullRegionalLangName;
    protected String strEmployeeShortName;
    protected String strEmployeeShortRegionalLangName;
    protected String intSuffixCode;
    protected String intNationalityId;
	protected String strGenderCode;
	protected String dtEmployeeDOB;
	protected String strServiceGroupCode;
	protected String intDesignationCode;
    protected String intDepartmentCode;
    protected String dtDateOfJoining;
    protected String strEmployeeStatus;
    protected String dtEmployeeStatusDate;
    protected String strEmployeeFinalStatus;
	protected String dtEmployeeFinalStatusDate;
    protected String strValidateStatus;
    protected String strValidateId;
    protected String strValidatorName;
	protected String dtValidateDate;
    protected String strValidatorRemarks;
    protected String intIsValid;
    protected String dtEntryDate;
    protected String intSeatId;
    protected String intHospitalCode;
    
    protected String strEmployeeUpdateFlag;
    protected String strLastEmploymentType;
    
    protected String strEmployeeValidationStatusCategory; // 1 - Pending (Temp Table), 2 - Validated (Main Table), 3 - Rejected (Log Table)
    
    protected String intMobileNumber;
    protected String strPersonalEmailId;
    protected String strPANNumber;
    
    protected String strGestModifyEmployeeNumber;
    protected String dtGestModifyEmployeeDoB;
   
	public String getStrGestModifyEmployeeNumber() {
		return strGestModifyEmployeeNumber;
	}

	public void setStrGestModifyEmployeeNumber(String strGestModifyEmployeeNumber) {
		this.strGestModifyEmployeeNumber = strGestModifyEmployeeNumber;
	}

	public String getDtGestModifyEmployeeDoB() {
		return dtGestModifyEmployeeDoB;
	}

	public void setDtGestModifyEmployeeDoB(String dtGestModifyEmployeeDoB) {
		this.dtGestModifyEmployeeDoB = dtGestModifyEmployeeDoB;
	}

	public String getStrPersonalEmailId() {
		return strPersonalEmailId;
	}

	public void setStrPersonalEmailId(String strPersonalEmailId) {
		this.strPersonalEmailId = strPersonalEmailId;
	}

	public String getIntMobileNumber() {
		return intMobileNumber;
	}

	public void setIntMobileNumber(String intMobileNumber) {
		this.intMobileNumber = intMobileNumber;
	}

	public String getStrPANNumber() {
		return strPANNumber;
	}

	public void setStrPANNumber(String strPANNumber) {
		this.strPANNumber = strPANNumber;
	}

	public String getStrEmployeeValidationStatusCategory() {
		return strEmployeeValidationStatusCategory;
	}

	public void setStrEmployeeValidationStatusCategory(
			String strEmployeeValidationStatusCategory) {
		this.strEmployeeValidationStatusCategory = strEmployeeValidationStatusCategory;
	}

	public String getStrLastEmploymentType() {
		return strLastEmploymentType;
	}

	public void setStrLastEmploymentType(String strLastEmploymentType) {
		this.strLastEmploymentType = strLastEmploymentType;
	}

	public String getStrEmployeeUpdateFlag() {
		return strEmployeeUpdateFlag;
	}

	public void setStrEmployeeUpdateFlag(String strEmployeeUpdateFlag) {
		this.strEmployeeUpdateFlag = strEmployeeUpdateFlag;
	}

	public String getStrIsExistingEmployee() {
		return strIsExistingEmployee;
	}

	public void setStrIsExistingEmployee(String strIsExistingEmployee) {
		this.strIsExistingEmployee = strIsExistingEmployee;
	}

	public String getStrEmployeeFullRegionalLangName() {
		return strEmployeeFullRegionalLangName;
	}

	public void setStrEmployeeFullRegionalLangName(
			String strEmployeeFullRegionalLangName) {
		this.strEmployeeFullRegionalLangName = strEmployeeFullRegionalLangName;
	}

	public String getStrEmployeeShortRegionalLangName() {
		return strEmployeeShortRegionalLangName;
	}

	public void setStrEmployeeShortRegionalLangName(
			String strEmployeeShortRegionalLangName) {
		this.strEmployeeShortRegionalLangName = strEmployeeShortRegionalLangName;
	}

	public String getIntNatureOfJobId() {
		return intNatureOfJobId;
	}

	public void setIntNatureOfJobId(String intNatureOfJobId) {
		this.intNatureOfJobId = intNatureOfJobId;
	}

	public String getStrEmployeeNumber() {
		return strEmployeeNumber;
	}

	public void setStrEmployeeNumber(String strEmployeeNumber) {
		this.strEmployeeNumber = strEmployeeNumber;
	}

	public String getIntRecordSlNo() {
		return intRecordSlNo;
	}

	public void setIntRecordSlNo(String intRecordSlNo) {
		this.intRecordSlNo = intRecordSlNo;
	}

	public String getStrOldEmployeeNumber() {
		return strOldEmployeeNumber;
	}

	public void setStrOldEmployeeNumber(String strOldEmployeeNumber) {
		this.strOldEmployeeNumber = strOldEmployeeNumber;
	}

	public String getStrUID() {
		return strUID;
	}

	public void setStrUID(String strUID) {
		this.strUID = strUID;
	}

	public String getIntAppellationCode1() {
		return intAppellationCode1;
	}

	public void setIntAppellationCode1(String intAppellationCode1) {
		this.intAppellationCode1 = intAppellationCode1;
	}

	public String getIntAppellationCode2() {
		return intAppellationCode2;
	}

	public void setIntAppellationCode2(String intAppellationCode2) {
		this.intAppellationCode2 = intAppellationCode2;
	}

	public String getStrEmployeeFullName() {
		return strEmployeeFullName;
	}

	public void setStrEmployeeFullName(String strEmployeeFullName) {
		this.strEmployeeFullName = strEmployeeFullName;
	}

	public String getStrEmployeeShortName() {
		return strEmployeeShortName;
	}

	public void setStrEmployeeShortName(String strEmployeeShortName) {
		this.strEmployeeShortName = strEmployeeShortName;
	}

	public String getIntSuffixCode() {
		return intSuffixCode;
	}

	public void setIntSuffixCode(String intSuffixCode) {
		this.intSuffixCode = intSuffixCode;
	}

	public String getIntNationalityId() {
		return intNationalityId;
	}

	public void setIntNationalityId(String intNationalityId) {
		this.intNationalityId = intNationalityId;
	}

	public String getStrGenderCode() {
		return strGenderCode;
	}

	public void setStrGenderCode(String strGenderCode) {
		this.strGenderCode = strGenderCode;
	}

	public String getDtEmployeeDOB() {
		return dtEmployeeDOB;
	}

	public void setDtEmployeeDOB(String dtEmployeeDOB) {
		this.dtEmployeeDOB = dtEmployeeDOB;
	}

	public String getStrServiceGroupCode() {
		return strServiceGroupCode;
	}

	public void setStrServiceGroupCode(String strServiceGroupCode) {
		this.strServiceGroupCode = strServiceGroupCode;
	}

	public String getIntDesignationCode() {
		return intDesignationCode;
	}

	public void setIntDesignationCode(String intDesignationCode) {
		this.intDesignationCode = intDesignationCode;
	}

	public String getIntDepartmentCode() {
		return intDepartmentCode;
	}

	public void setIntDepartmentCode(String intDepartmentCode) {
		this.intDepartmentCode = intDepartmentCode;
	}

	public String getDtDateOfJoining() {
		return dtDateOfJoining;
	}

	public void setDtDateOfJoining(String dtDateOfJoining) {
		this.dtDateOfJoining = dtDateOfJoining;
	}

	public String getStrEmployeeStatus() {
		return strEmployeeStatus;
	}

	public void setStrEmployeeStatus(String strEmployeeStatus) {
		this.strEmployeeStatus = strEmployeeStatus;
	}

	public String getDtEmployeeStatusDate() {
		return dtEmployeeStatusDate;
	}

	public void setDtEmployeeStatusDate(String dtEmployeeStatusDate) {
		this.dtEmployeeStatusDate = dtEmployeeStatusDate;
	}

	public String getStrEmployeeFinalStatus() {
		return strEmployeeFinalStatus;
	}

	public void setStrEmployeeFinalStatus(String strEmployeeFinalStatus) {
		this.strEmployeeFinalStatus = strEmployeeFinalStatus;
	}

	public String getDtEmployeeFinalStatusDate() {
		return dtEmployeeFinalStatusDate;
	}

	public void setDtEmployeeFinalStatusDate(String dtEmployeeFinalStatusDate) {
		this.dtEmployeeFinalStatusDate = dtEmployeeFinalStatusDate;
	}

	public String getStrValidateStatus() {
		return strValidateStatus;
	}

	public void setStrValidateStatus(String strValidateStatus) {
		this.strValidateStatus = strValidateStatus;
	}

	public String getStrValidateId() {
		return strValidateId;
	}

	public void setStrValidateId(String strValidateId) {
		this.strValidateId = strValidateId;
	}

	public String getStrValidatorName() {
		return strValidatorName;
	}

	public void setStrValidatorName(String strValidatorName) {
		this.strValidatorName = strValidatorName;
	}

	public String getDtValidateDate() {
		return dtValidateDate;
	}

	public void setDtValidateDate(String dtValidateDate) {
		this.dtValidateDate = dtValidateDate;
	}

	public String getStrValidatorRemarks() {
		return strValidatorRemarks;
	}

	public void setStrValidatorRemarks(String strValidatorRemarks) {
		this.strValidatorRemarks = strValidatorRemarks;
	}

	public String getIntIsValid() {
		return intIsValid;
	}

	public void setIntIsValid(String intIsValid) {
		this.intIsValid = intIsValid;
	}

	public String getDtEntryDate() {
		return dtEntryDate;
	}

	public void setDtEntryDate(String dtEntryDate) {
		this.dtEntryDate = dtEntryDate;
	}

	public String getIntSeatId() {
		return intSeatId;
	}

	public void setIntSeatId(String intSeatId) {
		this.intSeatId = intSeatId;
	}

	public String getIntHospitalCode() {
		return intHospitalCode;
	}

	public void setIntHospitalCode(String intHospitalCode) {
		this.intHospitalCode = intHospitalCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public EmpRegistrationSUP()
	{
    	intNatureOfJobId="";
    	strIsExistingEmployee="";
    	strEmployeeNumber="";
    	intRecordSlNo="";
    	strOldEmployeeNumber="";
        strUID="";
        intAppellationCode1="";
        intAppellationCode2="";
        strEmployeeFullName="";
        strEmployeeFullRegionalLangName="";
        strEmployeeShortName=""; 
        strEmployeeShortRegionalLangName=""; 
        intSuffixCode="";
        intNationalityId="";
    	strGenderCode="";
    	dtEmployeeDOB="";
    	strServiceGroupCode="";
    	intDesignationCode="";
        intDepartmentCode="";
        dtDateOfJoining="";
        strEmployeeStatus="";
        dtEmployeeStatusDate="";
        strEmployeeFinalStatus="";
    	dtEmployeeFinalStatusDate="";
        strValidateStatus="";
        strValidateId="";
        strValidatorName="";
    	dtValidateDate="";
        strValidatorRemarks="";
        intIsValid="";
        dtEntryDate="";
        intSeatId="";
        intHospitalCode="";
        strEmployeeUpdateFlag="";
        strLastEmploymentType="";
        
        intMobileNumber="";
        strPersonalEmailId="";
        strPANNumber="";
        
	}
	
	
	
	public void clear()
	{
		intNatureOfJobId="";
		strIsExistingEmployee="";
    	strEmployeeNumber="";
    	intRecordSlNo="";
    	strOldEmployeeNumber="";
        strUID="";
        intAppellationCode1="";
        intAppellationCode2="";
        strEmployeeFullName="";
        strEmployeeFullRegionalLangName="";
        strEmployeeShortName=""; 
        strEmployeeShortRegionalLangName="";  
        intSuffixCode="";
        intNationalityId="";
    	strGenderCode="";
    	dtEmployeeDOB="";
    	strServiceGroupCode="";
    	intDesignationCode="";
        intDepartmentCode="";
        dtDateOfJoining="";
        strEmployeeStatus="";
        dtEmployeeStatusDate="";
        strEmployeeFinalStatus="";
    	dtEmployeeFinalStatusDate="";
        strValidateStatus="";
        strValidateId="";
        strValidatorName="";
    	dtValidateDate="";
        strValidatorRemarks="";
        intIsValid="";
        dtEntryDate="";
        intSeatId="";
        intHospitalCode="";
        strEmployeeUpdateFlag="";
        strLastEmploymentType="";
        
        intMobileNumber="";
        strPersonalEmailId="";
        strPANNumber="";
        
	}
	
	
	public HttpServletRequest getObjRequest()
	{
		return objRequest;
	}

	public void setObjRequest(HttpServletRequest objRequest)
	{
		this.objRequest = objRequest;
	}

	public HttpServletResponse getObjResponse()
	{
		return objResponse;
	}

	public void setObjResponse(HttpServletResponse objResponse)
	{
		this.objResponse = objResponse;
	}

	public ServletContext getObjContext() {
		return objContext;
	}

	public void setObjContext(ServletContext objContext) {
		this.objContext = objContext;
	}

	public Map getMapSesion() {
		return mapSesion;
	}

	public void setMapSesion(Map mapSesion) {
		this.mapSesion = mapSesion;
	}

	@Override 
	public void setServletRequest(HttpServletRequest request)
	{
		
		this.objRequest = request;
		CharacterEncoding.setCharacterEncoding(this.objRequest);
	}

	@Override 
	public void setServletResponse(HttpServletResponse response)
	{
		this.objResponse = response;
	}

	@Override 
	public void setSession(Map sessionMap)
	{
		this.mapSesion = sessionMap;
	}
	
	public void prepare() throws Exception {
		
		
	}
	
}
