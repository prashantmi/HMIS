/**
 * 
 */
package ipd.transactions;

import javax.sql.rowset.WebRowSet;

/**
 * @author pankaj kumar
 *
 */
public class WardDueIPDTransVO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String strMsg = "";
    private String strErr = "";
    private String strWarning = "";
    private String strMsgType = "";
    private String strDeptValue = "";
    private String strDept = "";
    private String strDeptUnitValue = "";
    private String strDeptUnit = "";
    private String strWardValue = "";
    private String strWard = "";
    private String strRelationValue = "";
    private String strCurrentDate = "";
    private String strAdmNo = "";
    private String strHospCode = "";
    private String strItemType = "";
    private String strCRNo = "";
    private String strSeatId = "";
    
    private String[] strRemarks = null;
    private String[] strItemReturnDate = null;
    private String[] strStatus = null;
    private String[] strRelation = null;
    private String[] strSlno = null;
    private String[] strItemTypes = null;
    private String[] strItemReturnTo = null;
    
    private WebRowSet wBPatientDueList = null;
    private WebRowSet wBPatientList = null;
    private WebRowSet wBPatItemList = null;
    private String strBelMode="";
    
    
	/**
	 * @return the strSeatId
	 */
	public String getStrSeatId() {
		return strSeatId;
	}

	/**
	 * @param strSeatId the strSeatId to set
	 */
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}

	/**
	 * @return the strItemType
	 */
	public String getStrItemType() {
		return strItemType;
	}

	/**
	 * @param strItemType the strItemType to set
	 */
	public void setStrItemType(String strItemType) {
		this.strItemType = strItemType;
	}

	/**
	 * @return the strItemTypes
	 */
	public String[] getStrItemTypes() {
		return strItemTypes;
	}

	/**
	 * @param strItemTypes the strItemTypes to set
	 */
	public void setStrItemTypes(String[] strItemTypes) {
		this.strItemTypes = strItemTypes;
	}

	/**
	 * @return the strRemarks
	 */
	public String[] getStrRemarks() {
		return strRemarks;
	}

	/**
	 * @param strRemarks the strRemarks to set
	 */
	public void setStrRemarks(String[] strRemarks) {
		this.strRemarks = strRemarks;
	}

	/**
	 * @return the strItemReturnDate
	 */
	public String[] getStrItemReturnDate() {
		return strItemReturnDate;
	}

	/**
	 * @param strItemReturnDate the strItemReturnDate to set
	 */
	public void setStrItemReturnDate(String[] strItemReturnDate) {
		this.strItemReturnDate = strItemReturnDate;
	}

	/**
	 * @return the strStatus
	 */
	public String[] getStrStatus() {
		return strStatus;
	}

	/**
	 * @param strStatus the strStatus to set
	 */
	public void setStrStatus(String[] strStatus) {
		this.strStatus = strStatus;
	}

	/**
	 * @return the strRelation
	 */
	public String[] getStrRelation() {
		return strRelation;
	}

	/**
	 * @param strRelation the strRelation to set
	 */
	public void setStrRelation(String[] strRelation) {
		this.strRelation = strRelation;
	}

	/**
	 * @return the strSlno
	 */
	public String[] getStrSlno() {
		return strSlno;
	}

	/**
	 * @param strSlno the strSlno to set
	 */
	public void setStrSlno(String[] strSlno) {
		this.strSlno = strSlno;
	}

	/**
	 * @return the strItemReturnTo
	 */
	public String[] getStrItemReturnTo() {
		return strItemReturnTo;
	}

	/**
	 * @param strItemReturnTo the strItemReturnTo to set
	 */
	public void setStrItemReturnTo(String[] strItemReturnTo) {
		this.strItemReturnTo = strItemReturnTo;
	}

	/**
	 * @return the strCRNo
	 */
	public String getStrCRNo() {
		return strCRNo;
	}

	/**
	 * @param strCRNo the strCRNo to set
	 */
	public void setStrCRNo(String strCRNo) {
		this.strCRNo = strCRNo;
	}

	/**
	 * @return the wBPatientList
	 */
	public WebRowSet getWBPatientList() {
		return wBPatientList;
	}

	/**
	 * @param patientList the wBPatientList to set
	 */
	public void setWBPatientList(WebRowSet patientList) {
		wBPatientList = patientList;
	}

	/**
	 * @return the strMsg
	 */
	public String getStrMsg() {
		return strMsg;
	}

	/**
	 * @param strMsg the strMsg to set
	 */
	public void setStrMsg(String strMsg) {
		this.strMsg = strMsg;
	}

	/**
	 * @return the strErr
	 */
	public String getStrErr() {
		return strErr;
	}

	/**
	 * @param strErr the strErr to set
	 */
	public void setStrErr(String strErr) {
		this.strErr = strErr;
	}

	/**
	 * @return the strWarning
	 */
	public String getStrWarning() {
		return strWarning;
	}

	/**
	 * @param strWarning the strWarning to set
	 */
	public void setStrWarning(String strWarning) {
		this.strWarning = strWarning;
	}

	/**
	 * @return the wBPatientDueList
	 */
	public WebRowSet getWBPatientDueList() {
		return wBPatientDueList;
	}

	/**
	 * @param patientDueList the wBPatientDueList to set
	 */
	public void setWBPatientDueList(WebRowSet patientDueList) {
		wBPatientDueList = patientDueList;
	}

	/**
	 * @return the wBPatItemList
	 */
	public WebRowSet getWBPatItemList() {
		return wBPatItemList;
	}

	/**
	 * @param patItemList the wBPatItemList to set
	 */
	public void setWBPatItemList(WebRowSet patItemList) {
		wBPatItemList = patItemList;
	}

	/**
	 * @return the strAdmNo
	 */
	public String getStrAdmNo() {
		return strAdmNo;
	}

	/**
	 * @param strAdmNo the strAdmNo to set
	 */
	public void setStrAdmNo(String strAdmNo) {
		this.strAdmNo = strAdmNo;
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
	 * @return the strCurrentDate
	 */
	public String getStrCurrentDate() {
		return strCurrentDate;
	}

	/**
	 * @param strCurrentDate the strCurrentDate to set
	 */
	public void setStrCurrentDate(String strCurrentDate) {
		this.strCurrentDate = strCurrentDate;
	}

	/**
	 * @return the strRelationValue
	 */
	public String getStrRelationValue() {
		return strRelationValue;
	}

	/**
	 * @param strRelationValue the strRelationValue to set
	 */
	public void setStrRelationValue(String strRelationValue) {
		this.strRelationValue = strRelationValue;
	}

	/**
	 * @return the strDeptValue
	 */
	public String getStrDeptValue() {
		return strDeptValue;
	}

	/**
	 * @param strDeptValue the strDeptValue to set
	 */
	public void setStrDeptValue(String strDeptValue) {
		this.strDeptValue = strDeptValue;
	}

	/**
	 * @return the strDept
	 */
	public String getStrDept() {
		return strDept;
	}

	/**
	 * @param strDept the strDept to set
	 */
	public void setStrDept(String strDept) {
		this.strDept = strDept;
	}

	/**
	 * @return the strDeptUnitValue
	 */
	public String getStrDeptUnitValue() {
		return strDeptUnitValue;
	}

	/**
	 * @param strDeptUnitValue the strDeptUnitValue to set
	 */
	public void setStrDeptUnitValue(String strDeptUnitValue) {
		this.strDeptUnitValue = strDeptUnitValue;
	}

	/**
	 * @return the strDeptUnit
	 */
	public String getStrDeptUnit() {
		return strDeptUnit;
	}

	/**
	 * @param strDeptUnit the strDeptUnit to set
	 */
	public void setStrDeptUnit(String strDeptUnit) {
		this.strDeptUnit = strDeptUnit;
	}

	/**
	 * @return the strWardValue
	 */
	public String getStrWardValue() {
		return strWardValue;
	}

	/**
	 * @param strWardValue the strWardValue to set
	 */
	public void setStrWardValue(String strWardValue) {
		this.strWardValue = strWardValue;
	}

	/**
	 * @return the strWard
	 */
	public String getStrWard() {
		return strWard;
	}

	/**
	 * @param strWard the strWard to set
	 */
	public void setStrWard(String strWard) {
		this.strWard = strWard;
	}

	/**
	 * @return the strMsgType
	 */
	public String getStrMsgType() {
		return strMsgType;
	}

	/**
	 * @param strMsgType the strMsgType to set
	 */
	public void setStrMsgType(String strMsgType) {
		this.strMsgType = strMsgType;
	}

	public String getStrBelMode() {
		return strBelMode;
	}

	public void setStrBelMode(String strBelMode) {
		this.strBelMode = strBelMode;
	}
}
