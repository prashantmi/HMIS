/**
 * 
 */
package ipd.transactions;

import org.apache.struts.action.ActionForm;

/**
 * @author pankaj kumar
 *
 */
public class WardDueIPDTransFB extends ActionForm {

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
    private String strPatient = "";
 
    private String[] strChkItem = null;
    private String[] strRemarks = null;
    private String[] strItemReturnDate = null;
    private String[] strStatus = null;
    private String[] strRelation = null;
    private String[] strItemReturnTo = null;
    
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
	 * @return the strChkItem
	 */
	public String[] getStrChkItem() {
		return strChkItem;
	}
	/**
	 * @param strChkItem the strChkItem to set
	 */
	public void setStrChkItem(String[] strChkItem) {
		this.strChkItem = strChkItem;
	}
	/**
	 * @return the strPatient
	 */
	public String getStrPatient() {
		return strPatient;
	}
	/**
	 * @param strPatient the strPatient to set
	 */
	public void setStrPatient(String strPatient) {
		this.strPatient = strPatient;
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
}
