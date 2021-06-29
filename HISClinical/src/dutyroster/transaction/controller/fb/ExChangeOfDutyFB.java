package dutyroster.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ExChangeOfDutyFB extends ActionForm {

	private String year;
	private String month;
	private String rosterMainCatg;
	private String rosterCatg;
	private String mode;
	private String selectedDate;	
	private String hmode;
	private String isValid;
	private String selectedEmpList;	
	private String concatedData="";
	private String areaName="";
	private String requestedEmp="";
	private String exchangedEmp="";
	private String selectRequestedEmp="";
	private String selectExchangedEmp="";
	private String generatedRosterId;
	private String reason="";
	private String sysDate="";
	private String exchangeWithMonth="";
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.year="-1";
		this.month="-1";
		this.rosterMainCatg = "-1";	
		this.rosterCatg = "-1";	
		this.mode="1";
		this.selectedDate="";
		this.selectedEmpList="";	
		this.concatedData="";
		this.areaName="-1";
		this.requestedEmp="-1";
		this.exchangedEmp="-1";
		this.generatedRosterId="";
		this.selectRequestedEmp="";
		this.selectExchangedEmp="";
		this.reason="";
		this.sysDate="";
		this.exchangeWithMonth="C";
	}



	public String getYear() {
		return year;
	}



	public void setYear(String year) {
		this.year = year;
	}



	public String getMonth() {
		return month;
	}



	public void setMonth(String month) {
		this.month = month;
	}







	public String getRosterMainCatg() {
		return rosterMainCatg;
	}



	public void setRosterMainCatg(String rosterMainCatg) {
		this.rosterMainCatg = rosterMainCatg;
	}



	public String getRosterCatg() {
		return rosterCatg;
	}



	public void setRosterCatg(String rosterCatg) {
		this.rosterCatg = rosterCatg;
	}



	public String getSelectedDate() {
		return selectedDate;
	}



	public void setSelectedDate(String selectedDate) {
		this.selectedDate = selectedDate;
	}



	public String getHmode() {
		return hmode;
	}



	public void setHmode(String hmode) {
		this.hmode = hmode;
	}



	public String getIsValid() {
		return isValid;
	}



	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}


	public String getSelectedEmpList() {
		return selectedEmpList;
	}



	public void setSelectedEmpList(String selectedEmpList) {
		this.selectedEmpList = selectedEmpList;
	}



	public String getConcatedData() {
		return concatedData;
	}



	public void setConcatedData(String concatedData) {
		this.concatedData = concatedData;
	}



	public String getMode() {
		return mode;
	}



	public void setMode(String mode) {
		this.mode = mode;
	}



	public String getAreaName() {
		return areaName;
	}



	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}



	



	public String getRequestedEmp() {
		return requestedEmp;
	}



	public void setRequestedEmp(String requestedEmp) {
		this.requestedEmp = requestedEmp;
	}



	public String getExchangedEmp() {
		return exchangedEmp;
	}



	public void setExchangedEmp(String exchangedEmp) {
		this.exchangedEmp = exchangedEmp;
	}



	public String getGeneratedRosterId() {
		return generatedRosterId;
	}



	public void setGeneratedRosterId(String generatedRosterId) {
		this.generatedRosterId = generatedRosterId;
	}



	public String getSelectRequestedEmp() {
		return selectRequestedEmp;
	}



	public void setSelectRequestedEmp(String selectRequestedEmp) {
		this.selectRequestedEmp = selectRequestedEmp;
	}



	public String getSelectExchangedEmp() {
		return selectExchangedEmp;
	}



	public void setSelectExchangedEmp(String selectExchangedEmp) {
		this.selectExchangedEmp = selectExchangedEmp;
	}



	public String getReason() {
		return reason;
	}



	public void setReason(String reason) {
		this.reason = reason;
	}



	public String getSysDate() {
		return sysDate;
	}



	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}



	public String getExchangeWithMonth() {
		return exchangeWithMonth;
	}



	public void setExchangeWithMonth(String exchangeWithMonth) {
		this.exchangeWithMonth = exchangeWithMonth;
	}

	
	

	
		
}
