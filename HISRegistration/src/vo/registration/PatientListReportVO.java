/**
 * 
 */
package vo.registration;

import registration.reports.controller.actionsupport.ReportSUP;

/**
 * @author s.singaravelan
 * DATE : 25-Mar-2014
 */
public class PatientListReportVO extends ReportSUP{

	private static final long serialVersionUID = 1L;
	private String strChoice;
	private String strDeptCode;
	private String strUnitCode;
	private String strPatCategoryCode;
	private String strPatVisit;
	private String strOrderBy;
	private String strGroupBy;
	
	private String strSeatId;
	private String strIsValid;
	private String strMsgString;
	private String strMsgType;
	private String strWarning;
	private String strMsg;
	private String strErrorMsg;
	
	
	public void reset()
	{
		strChoice="0";
		strDeptCode="";
		strUnitCode="";
		strPatCategoryCode="";
		strPatVisit="";
	}

	
	public String getStrChoice() {
		return strChoice;
	}


	public void setStrChoice(String strChoice) {
		this.strChoice = strChoice;
	}

	public String getStrDeptCode() {
		return strDeptCode;
	}

	public void setStrDeptCode(String strDeptCode) {
		this.strDeptCode = strDeptCode;
	}

	public String getStrUnitCode() {
		return strUnitCode;
	}

	public void setStrUnitCode(String strUnitCode) {
		this.strUnitCode = strUnitCode;
	}

	public String getStrPatCategoryCode() {
		return strPatCategoryCode;
	}

	public void setStrPatCategoryCode(String strPatCategoryCode) {
		this.strPatCategoryCode = strPatCategoryCode;
	}

	public String getStrPatVisit() {
		return strPatVisit;
	}

	public void setStrPatVisit(String strPatVisit) {
		this.strPatVisit = strPatVisit;
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
	public void setStrReportType(String strReportType) {
		super.setReportType(strReportType);
	}
	public String getStrReportType() {
		return super.getReportType();
	}	
	public void setStrGraphOrText(String strGraphOrText) {
		super.setGraphOrText(strGraphOrText);
	}
	public String getStrGraphOrText() {
		return super.getGraphOrText();
	}	
	public String getTitle() {
		return super.getTitle();
	}
	public String getReportPath() {
		return super.getRptDesignPath();
	}	
	public String getHospitalCode() {
		return super.getHospitalCode();
	}
	public String getStrFromDate() {
		return super.getFromDate();
	}
	public String getStrToDate() {
		return super.getToDate();
	}
	public String getStrFromHour() {
		return super.getFromHour();
	}
	public String getStrToHour() {
		return super.getToHour();
	}
	public String getStrFromMin() {
		return super.getFromMin();
	}
	public String getStrToMin() {
		return super.getToMin();
	}


	public String getStrOrderBy() {
		return strOrderBy;
	}


	public void setStrOrderBy(String strOrderBy) {
		this.strOrderBy = strOrderBy;
	}


	public String getStrGroupBy() {
		return strGroupBy;
	}


	public void setStrGroupBy(String strGroupBy) {
		this.strGroupBy = strGroupBy;
	}
	

}
