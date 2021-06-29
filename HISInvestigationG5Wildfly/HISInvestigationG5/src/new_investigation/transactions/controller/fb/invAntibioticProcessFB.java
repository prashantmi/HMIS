package new_investigation.transactions.controller.fb;

import org.apache.struts.action.ActionForm;

public class invAntibioticProcessFB extends ActionForm {

	private String hmode;
	
	   private String currentPageNo="1";
		private int currentPage=1;
		private String chk[];
		private String[] chkSamplePatient;
		private String[] chkSamplePatientName;
		
		public static final String recordPerPage="10";
		public static final String pagesPerBlock="10";
		public String pageString;
		private int startIndex;
		private int endIndex;
		private String selectedCheckbox;
		private String showStatus;
		
		private String[] chkInfo;	
		private String[]chkInfo1 ;
		private String[] chkInfo2;
		private String requisitionDNo;
		private String testParaCode;
		private String flag;
		private String growthCode;
		private String[] remarks;
		private String data;
		private String organismnamee;
		private String growthname;
	private String organismCode;
	private String organismName;
	private String requisitionNo;
	private String isaddlist;
	private String counterrr;

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

	public String getOrganismCode() {
		return organismCode;
	}

	public void setOrganismCode(String organismCode) {
		this.organismCode = organismCode;
	}

	public static String getRecordperpage() {
		return recordPerPage;
	}

	public static String getPagesperblock() {
		return pagesPerBlock;
	}

	public String getShowStatus() {
		return showStatus;
	}

	public void setShowStatus(String showStatus) {
		this.showStatus = showStatus;
	}

	public String[] getChkInfo() {
		return chkInfo;
	}

	public void setChkInfo(String[] chkInfo) {
		this.chkInfo = chkInfo;
	}

	public String[] getChkInfo1() {
		return chkInfo1;
	}

	public void setChkInfo1(String[] chkInfo1) {
		this.chkInfo1 = chkInfo1;
	}

	public String[] getChkInfo2() {
		return chkInfo2;
	}

	public void setChkInfo2(String[] chkInfo2) {
		this.chkInfo2 = chkInfo2;
	}

	public String[] getChkSamplePatientName() {
		return chkSamplePatientName;
	}

	public void setChkSamplePatientName(String[] chkSamplePatientName) {
		this.chkSamplePatientName = chkSamplePatientName;
	}

	public String getOrganismName() {
		return organismName;
	}

	public void setOrganismName(String organismName) {
		this.organismName = organismName;
	}

	public String getRequisitionDNo() {
		return requisitionDNo;
	}

	public void setRequisitionDNo(String requisitionDNo) {
		this.requisitionDNo = requisitionDNo;
	}

	public String getTestParaCode() {
		return testParaCode;
	}

	public void setTestParaCode(String testParaCode) {
		this.testParaCode = testParaCode;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getGrowthCode() {
		return growthCode;
	}

	public void setGrowthCode(String growthCode) {
		this.growthCode = growthCode;
	}

	public String[] getRemarks() {
		return remarks;
	}

	public void setRemarks(String[] remarks) {
		this.remarks = remarks;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getOrganismnamee() {
		return organismnamee;
	}

	public void setOrganismnamee(String organismnamee) {
		this.organismnamee = organismnamee;
	}

	public String getGrowthname() {
		return growthname;
	}

	public void setGrowthname(String growthname) {
		this.growthname = growthname;
	}

	public String getRequisitionNo() {
		return requisitionNo;
	}

	public void setRequisitionNo(String requisitionNo) {
		this.requisitionNo = requisitionNo;
	}

	public String getIsaddlist() {
		return isaddlist;
	}

	public void setIsaddlist(String isaddlist) {
		this.isaddlist = isaddlist;
	}

	public String getCounterrr() {
		return counterrr;
	}

	public void setCounterrr(String counterrr) {
		this.counterrr = counterrr;
	}

	

	

	
	
	
	
	
}
