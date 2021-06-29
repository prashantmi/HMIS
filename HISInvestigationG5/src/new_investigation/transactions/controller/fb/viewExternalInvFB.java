package new_investigation.transactions.controller.fb;

import org.apache.struts.action.ActionForm;

public class viewExternalInvFB extends ActionForm {

	private String patCrNo;
	private String hmode;
    private String showStatus;
    
    
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
	private String fileName;
	private String contentType;
    private String dowloadFile;
    private String dowloadFileid;
    private String dowloadFilecontent;
    
	public String getPatCrNo() {
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
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

	public static String getRecordperpage() {
		return recordPerPage;
	}

	public static String getPagesperblock() {
		return pagesPerBlock;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getDowloadFile() {
		return dowloadFile;
	}

	public void setDowloadFile(String dowloadFile) {
		this.dowloadFile = dowloadFile;
	}

	public String getDowloadFileid() {
		return dowloadFileid;
	}

	public void setDowloadFileid(String dowloadFileid) {
		this.dowloadFileid = dowloadFileid;
	}

	public String getDowloadFilecontent() {
		return dowloadFilecontent;
	}

	public void setDowloadFilecontent(String dowloadFilecontent) {
		this.dowloadFilecontent = dowloadFilecontent;
	}
	
	
	
	
	
	
	
	
	
	
	
}
