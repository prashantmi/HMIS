/**
 * 
 */
package vo.registration;

import java.util.Map;

import hisglobal.vo.ValueObject;

/**
 * @author s.singaravelan
 * DATE : 16-Jan-2014
 */
public class UnitConsultantVO extends ValueObject
{
	
	private String strDeptUnitCode;
	private String strEmpCode;
	private String strSequence;
	private String strLocCode;
	private String strHierarchyLevel;
	private String strIsHeadOfUnit;

	private String strHospitalCode;
	private String strSeatId;
	private String strIsValid;
	private String strMsgString;
	private String strMsgType;
	private String strWarning;
	private String strMsg;
	private String strErrorMsg;

	private String strDeptName;
	private String strUnitName;
	private String strEmpName;
	private String removeConsultant;
	
	private String[] employeeIdExisting;
	private String[] employeeNameExisting;
	private String[] employeeHOU;
	private String[] hierarchyLevel;
	private String[] consultantNameNew=new String[]{};	
	
	
	public void reset() 
	{
		
	}
	public UnitConsultantVO()
	{
		
	}
	
	public UnitConsultantVO(String empCode,String empName)
	{
		this.strEmpCode=empCode;
		this.strEmpName=empName;
	}
		
	public String getStrDeptUnitCode() {
		return strDeptUnitCode;
	}
	
	public void setStrDeptUnitCode(String strDeptUnitCode) {
		this.strDeptUnitCode = strDeptUnitCode;
	}	
	
	public String getStrEmpCode() {
		return strEmpCode;
	}

	public void setStrEmpCode(String strEmpCode) {
		this.strEmpCode = strEmpCode;
	}

	public String getStrSequence() {
		return strSequence;
	}

	public void setStrSequence(String strSequence) {
		this.strSequence = strSequence;
	}

	public String getStrLocCode() {
		return strLocCode;
	}

	public void setStrLocCode(String strLocCode) {
		this.strLocCode = strLocCode;
	}

	public String getStrHierarchyLevel() {
		return strHierarchyLevel;
	}

	public void setStrHierarchyLevel(String strHierarchyLevel) {
		this.strHierarchyLevel = strHierarchyLevel;
	}

	public String getStrIsHeadOfUnit() {
		return strIsHeadOfUnit;
	}

	public void setStrIsHeadOfUnit(String strIsHeadOfUnit) {
		this.strIsHeadOfUnit = strIsHeadOfUnit;
	}

	public String getRemoveConsultant() {
		return removeConsultant;
	}

	public void setRemoveConsultant(String removeConsultant) {
		this.removeConsultant = removeConsultant;
	}

	public String[] getEmployeeIdExisting() {
		return employeeIdExisting;
	}

	public void setEmployeeIdExisting(String[] employeeIdExisting) {
		this.employeeIdExisting = employeeIdExisting;
	}

	public String[] getEmployeeNameExisting() {
		return employeeNameExisting;
	}

	public void setEmployeeNameExisting(String[] employeeNameExisting) {
		this.employeeNameExisting = employeeNameExisting;
	}

	public String[] getHierarchyLevel() {
		return hierarchyLevel;
	}

	public void setHierarchyLevel(String[] hierarchyLevel) {
		this.hierarchyLevel = hierarchyLevel;
	}

	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	public String getStrIsValid() {
		return strIsValid;
	}
	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
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

	public String getStrEmpName() {
		return strEmpName;
	}

	public void setStrEmpName(String strEmpName) {
		this.strEmpName = strEmpName;
	}

	public String getStrHospitalCode() {
		return strHospitalCode;
	}

	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	public String[] getConsultantNameNew() {
		return consultantNameNew;
	}

	public void setConsultantNameNew(String[] consultantNameNew) {
		this.consultantNameNew = consultantNameNew;
	}
	public String[] getEmployeeHOU() {
		return employeeHOU;
	}
	public void setEmployeeHOU(String[] employeeHOU) {
		this.employeeHOU = employeeHOU;
	}

		
}
