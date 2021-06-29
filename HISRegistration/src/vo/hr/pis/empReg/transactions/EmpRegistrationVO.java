package vo.hr.pis.empReg.transactions;

public class EmpRegistrationVO {
	
	protected String intNatureOfJobId;
	protected String strIsExistingEmployee="";
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
    protected String strLastEmploymentType;

    protected String strEmployeeUpdateFlag;
    protected String strEmployeeValidationStatusCategory; // 1 - Pending (Temp Table), 2 - Validated (Main Table), 3 - Rejected (Log Table)
    
    protected String intMobileNumber;
    protected String strPersonalEmailId;
    protected String strPANNumber;
    
    private String strMsgString;
	private String strMsgType;
	private String strWarning;
	private String strMsg;
	private String strErrorMsg;
	
	
	public String getStrEmployeeUpdateFlag() {
		return strEmployeeUpdateFlag;
	}
	public void setStrEmployeeUpdateFlag(String strEmployeeUpdateFlag) {
		this.strEmployeeUpdateFlag = strEmployeeUpdateFlag;
	}
	public String getIntMobileNumber() {
		return intMobileNumber;
	}
	public void setIntMobileNumber(String intMobileNumber) {
		this.intMobileNumber = intMobileNumber;
	}
	
	public String getStrPersonalEmailId() {
		return strPersonalEmailId;
	}
	public void setStrPersonalEmailId(String strPersonalEmailId) {
		this.strPersonalEmailId = strPersonalEmailId;
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
	public String getStrWarning() {
		return strWarning;
	}
	public void setStrWarning(String strWarning) {
		this.strWarning = strWarning;
	}
	public String getStrMsg() {
		return strMsg;
	}
	public void setStrMsg(String strMsg) {
		this.strMsg = strMsg;
	}
	public String getStrErrorMsg() {
		return strErrorMsg;
	}
	public void setStrErrorMsg(String strErrorMsg) {
		this.strErrorMsg = strErrorMsg;
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
	
	
}
