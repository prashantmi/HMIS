package new_investigation.transactions.controller.fb;

import org.apache.struts.action.ActionForm;

public class InvValueAuditFB extends ActionForm {
	
    private String hmode;
    private String fromDate="";
    private String toDate="";
    private String labCode;
    private String generationType;
    private String testCode;
    private String testWiseCode;
    private String fromSampleNo;
    private String fromLabNo;
    private String testGroupCodeWise;
    private String value;
    public String showStatus;
    
    private String currentPageNo="1";
	private int currentPage=1;
	private String chk[];
	private String[] chkSamplePatient;
	public static final String recordPerPage="10";
	public static final String pagesPerBlock="10";
	public String pageString;
	private int startIndex;
	private int endIndex;
	private String selectedCheckbox;
	
	
	
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
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
	public String getLabCode() {
		return labCode;
	}
	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}
	public String getGenerationType() {
		return generationType;
	}
	public void setGenerationType(String generationType) {
		this.generationType = generationType;
	}
	public String getTestCode() {
		return testCode;
	}
	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}
	public String getTestWiseCode() {
		return testWiseCode;
	}
	public void setTestWiseCode(String testWiseCode) {
		this.testWiseCode = testWiseCode;
	}
	public String getFromSampleNo() {
		return fromSampleNo;
	}
	public void setFromSampleNo(String fromSampleNo) {
		this.fromSampleNo = fromSampleNo;
	}
	public String getFromLabNo() {
		return fromLabNo;
	}
	public void setFromLabNo(String fromLabNo) {
		this.fromLabNo = fromLabNo;
	}
	public String getTestGroupCodeWise() {
		return testGroupCodeWise;
	}
	public void setTestGroupCodeWise(String testGroupCodeWise) {
		this.testGroupCodeWise = testGroupCodeWise;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getShowStatus() {
		return showStatus;
	}
	public void setShowStatus(String showStatus) {
		this.showStatus = showStatus;
	}
	public String getCurrentPageNo() {
		return currentPageNo;
	}
	public void setCurrentPageNo(String currentPageNo) {
		this.currentPageNo = currentPageNo;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
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
	public String getPageString() {
		return pageString;
	}
	public void setPageString(String pageString) {
		this.pageString = pageString;
	}
	public static String getRecordperpage() {
		return recordPerPage;
	}
	public static String getPagesperblock() {
		return pagesPerBlock;
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
	public String getSelectedCheckbox() {
		return selectedCheckbox;
	}
	public void setSelectedCheckbox(String selectedCheckbox) {
		this.selectedCheckbox = selectedCheckbox;
	}
    
	
	

}
