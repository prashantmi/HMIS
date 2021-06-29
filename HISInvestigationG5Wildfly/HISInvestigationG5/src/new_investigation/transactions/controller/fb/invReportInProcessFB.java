package new_investigation.transactions.controller.fb;

import org.apache.struts.action.ActionForm;

public class invReportInProcessFB extends ActionForm {

	private String hmode;
	private String showStatus;
	
	//private String currentPageNo="1";
	private int currentPage=1;
	private String chk[];
	private String[] chkSamplePatient;
	//public static final String recordPerPage="10";
	//public static final String pagesPerBlock="10";
	//public String pageString;
	private int startIndex;
	private int endIndex;
	private String selectedCheckbox;
	private String fromDate="";
	private String toDate="";
	private String tempPatCRNo;
	 private String tempPatName;
	 private String patCRNo;
	public String getPatCRNo() {
		return patCRNo;
	}
	public void setPatCRNo(String patCRNo) {
		this.patCRNo = patCRNo;
	}
	public String getTempPatCRNo() {
		return tempPatCRNo;
	}
	public void setTempPatCRNo(String tempPatCRNo) {
		this.tempPatCRNo = tempPatCRNo;
	}
	public String getTempPatName() {
		return tempPatName;
	}
	public void setTempPatName(String tempPatName) {
		this.tempPatName = tempPatName;
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
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getShowStatus() {
		return showStatus;
	}
	public void setShowStatus(String showStatus) {
		this.showStatus = showStatus;
	}

	/*
	 * public String getCurrentPageNo() { return currentPageNo; } public void
	 * setCurrentPageNo(String currentPageNo) { this.currentPageNo = currentPageNo;
	 * } public int getCurrentPage() { return currentPage; } public void
	 * setCurrentPage(int currentPage) { this.currentPage = currentPage; }
	 */
	public String[] getChk() {
		return chk;
	}
	public void setChk(String[] chk) {
		this.chk = chk;
	}
	public String[] getChkSamplePatient() {
		return chkSamplePatient;
	}
	public void setChkSamplePatient(String[] chkSamplePatient) {
		this.chkSamplePatient = chkSamplePatient;
	}

	/*
	 * public String getPageString() { return pageString; } public void
	 * setPageString(String pageString) { this.pageString = pageString; }
	 */
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
	public String getSelectedCheckbox() {
		return selectedCheckbox;
	}
	public void setSelectedCheckbox(String selectedCheckbox) {
		this.selectedCheckbox = selectedCheckbox;
	}
	/*
	 * public static String getRecordperpage() { return recordPerPage; } public
	 * static String getPagesperblock() { return pagesPerBlock; }
	 */
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	
	
	
	
	
	
	
	
	
	
}
