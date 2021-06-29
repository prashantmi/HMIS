package new_investigation.transactions.controller.fb;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class testAvailabilityFB extends ActionForm
{
 
	private String hmode;
	private String seatId;
	public static final String recordPerPage="10";
	public static final String pagesPerBlock="10";
	private String patCrNo;
	private int startIndex;
	private int endIndex;
	private String resultArray;
	private String testCode;
	private String labCode;
	private String isAvailable;
	private String record;
	private String fromDate="";
	private String toDate="";
	private String showStatus;
	private int currentPage;
	private String sysDate="";
	private String reqDNo;
	private String selectedCheckbox;
	private String[] chkTest;
	private String remarks;
	private String testStatus;
	private String searchTest;
	
	

	public String[] getChkTest() {
		return chkTest;
	}




	public void setChkTest(String[] chkTest) {
		this.chkTest = chkTest;
	}




	public String getIsAvailable() {
		return isAvailable;
	}




	public void setIsAvailable(String isAvailable) {
		this.isAvailable = isAvailable;
	}


	
	
	public int getCurrentPage() {
		return currentPage;
	}




	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}




	public String getShowStatus() {
		return showStatus;
	}




	public void setShowStatus(String showStatus) {
		this.showStatus = showStatus;
	}




	public String getHmode() {
		return hmode;
	}




	public void setHmode(String hmode) {
		this.hmode = hmode;
	}




	public String getSeatId() {
		return seatId;
	}




	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}




	public String getPatCrNo() {
		return patCrNo;
	}




	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}




	public int getStartIndex() {
		return startIndex;
	}




	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}




	public int getEndIndex() {
		return endIndex;
	}




	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}




	public String getResultArray() {
		return resultArray;
	}




	public void setResultArray(String resultArray) {
		this.resultArray = resultArray;
	}




	public String getTestCode() {
		return testCode;
	}




	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}




	public String getLabCode() {
		return labCode;
	}




	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}








	public String getRecord() {
		return record;
	}




	public void setRecord(String record) {
		this.record = record;
	}



	public String getSysDate() {
		return sysDate;
	}




	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}




	public String getReqDNo() {
		return reqDNo;
	}




	public void setReqDNo(String reqDNo) {
		this.reqDNo = reqDNo;
	}




	public String getSelectedCheckbox() {
		return selectedCheckbox;
	}




	public void setSelectedCheckbox(String selectedCheckbox) {
		this.selectedCheckbox = selectedCheckbox;
	}





	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
	this.currentPage=1;
	}




	public String getFromDate() {
		return fromDate;
	}




	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}




	public String getToDate() {
		return toDate;
	}




	public void setToDate(String toDate) {
		this.toDate = toDate;
	}




	public String getRemarks() {
		return remarks;
	}




	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}




	public String getTestStatus() {
		return testStatus;
	}




	public void setTestStatus(String testStatus) {
		this.testStatus = testStatus;
	}




	public String getSearchTest() {
		return searchTest;
	}




	public void setSearchTest(String searchTest) {
		this.searchTest = searchTest;
	}
	
	
	



 






	 
}