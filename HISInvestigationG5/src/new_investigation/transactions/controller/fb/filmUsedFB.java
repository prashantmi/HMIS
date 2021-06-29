package new_investigation.transactions.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class filmUsedFB extends ActionForm
{

	private String filmNo[];
	private String additionalFilmNo[];
	private String wasteFilmNo[];
	private String filmSize[];
	private String noOfFilms;
	private String remarks;
	private String testStatus;
	private String filmReason[];
	private String filmReasonWaste[];
	
	private String selectedEpisode;
	private String selectedEpisodeRadio;
	private String hmode;
	private String currentPageNo="1";
	private String currentblock="1";
	public String pageString;
	private String seatId;
	public static final String recordPerPage="10";
	public static final String pagesPerBlock="10";
	private String patCrNo;
	private String patStatus;
	private int currentPage;
	private int startIndex;
	private int endIndex;
	private String patType;
	private String numberOfRow;
	private String labCode;
	private String fromDate="";
	private String toDate="";
	private String sysDate="";
	private String patLabName;
	private String patReqDate;
	private String patName;
	private String patAge;
	private String patUnitName;
	private String reqNo;
	private String selectedCheckbox;
	private String isPatDetailPage;
	private String patCategory;
	private String patWardName;
	private String patRoomName;
	private String patBedName;
	private String patOrderByDoc;
	private String patGender;
	private String patVisitDat;
	private String showStatus;
	private String testName;
	private String sampleName;
	private String sampleCode;
	private String sampleQnty;
	private String defaultUOMCode;
	private String defaultContainerCode;
	private String priority;
	private String requisitionDNo;
	private String rejectionReason;
	private String priorityCode;
	private String mobileNo;
	private String emailId;
	private String[] chkSamplePatient;
	private String labCodeSamp;
	private String address;
	private String reqDtlStatus;
	private String groupType;
	private String groupCode;
	private String billNo;
	private String consQty;
	private String tempPatCrNo;
	private String TempDailyLabNo;
	private String strDailyLabNo;
	private String[] chkSamplePatientOnSave;
	private String config;
	private String numberOfRowWaste;
	private String numberOfRowAdd;
	private String testCode;
	private String tempFilmNo;
	private String batchNo[];
	private String inventory[];
	private String  labcodefilm;
	private String chkBoxBatchValue[];

	
	
	public String[] getBatchNo() {
		return batchNo;
	}


	public void setBatchNo(String[] batchNo) {
		this.batchNo = batchNo;
	}


	public String[] getInventory() {
		return inventory;
	}


	public void setInventory(String[] inventory) {
		this.inventory = inventory;
	}


	
			

	public String getTempFilmNo() {
		return tempFilmNo;
	}


	public void setTempFilmNo(String tempFilmNo) {
		this.tempFilmNo = tempFilmNo;
	}


	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.currentPage=1;
		this.labCode="%";
		this.remarks="";
		this.reqNo="";
		this.patCrNo="";
		this.patType="";
		this.filmNo=null;
		this.additionalFilmNo=null;
		this.wasteFilmNo=null;
	
	}


	public String[] getFilmNo() {
		return filmNo;
	}


	public void setFilmNo(String[] filmNo) {
		this.filmNo = filmNo;
	}


	public String[] getFilmSize() {
		return filmSize;
	}


	public void setFilmSize(String[] filmSize) {
		this.filmSize = filmSize;
	}


	public String getNoOfFilms() {
		return noOfFilms;
	}


	public void setNoOfFilms(String noOfFilms) {
		this.noOfFilms = noOfFilms;
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


	public String[] getFilmReason() {
		return filmReason;
	}


	public void setFilmReason(String[] filmReason) {
		this.filmReason = filmReason;
	}


	public String getSelectedEpisode() {
		return selectedEpisode;
	}


	public void setSelectedEpisode(String selectedEpisode) {
		this.selectedEpisode = selectedEpisode;
	}


	public String getSelectedEpisodeRadio() {
		return selectedEpisodeRadio;
	}


	public void setSelectedEpisodeRadio(String selectedEpisodeRadio) {
		this.selectedEpisodeRadio = selectedEpisodeRadio;
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


	public String getCurrentblock() {
		return currentblock;
	}


	public void setCurrentblock(String currentblock) {
		this.currentblock = currentblock;
	}


	public String getPageString() {
		return pageString;
	}


	public void setPageString(String pageString) {
		this.pageString = pageString;
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


	public String getPatStatus() {
		return patStatus;
	}


	public void setPatStatus(String patStatus) {
		this.patStatus = patStatus;
	}


	public int getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
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


	public String getPatType() {
		return patType;
	}


	public void setPatType(String patType) {
		this.patType = patType;
	}


	public String getNumberOfRow() {
		return numberOfRow;
	}


	public void setNumberOfRow(String numberOfRow) {
		this.numberOfRow = numberOfRow;
	}


	public String getLabCode() {
		return labCode;
	}


	public void setLabCode(String labCode) {
		this.labCode = labCode;
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


	public String getSysDate() {
		return sysDate;
	}


	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}


	public String getPatLabName() {
		return patLabName;
	}


	public void setPatLabName(String patLabName) {
		this.patLabName = patLabName;
	}


	public String getPatReqDate() {
		return patReqDate;
	}


	public void setPatReqDate(String patReqDate) {
		this.patReqDate = patReqDate;
	}


	public String getPatName() {
		return patName;
	}


	public void setPatName(String patName) {
		this.patName = patName;
	}


	public String getPatAge() {
		return patAge;
	}


	public void setPatAge(String patAge) {
		this.patAge = patAge;
	}


	public String getPatUnitName() {
		return patUnitName;
	}


	public void setPatUnitName(String patUnitName) {
		this.patUnitName = patUnitName;
	}


	public String getReqNo() {
		return reqNo;
	}


	public void setReqNo(String reqNo) {
		this.reqNo = reqNo;
	}


	public String getSelectedCheckbox() {
		return selectedCheckbox;
	}


	public void setSelectedCheckbox(String selectedCheckbox) {
		this.selectedCheckbox = selectedCheckbox;
	}


	public String getIsPatDetailPage() {
		return isPatDetailPage;
	}


	public void setIsPatDetailPage(String isPatDetailPage) {
		this.isPatDetailPage = isPatDetailPage;
	}


	public String getPatCategory() {
		return patCategory;
	}


	public void setPatCategory(String patCategory) {
		this.patCategory = patCategory;
	}


	public String getPatWardName() {
		return patWardName;
	}


	public void setPatWardName(String patWardName) {
		this.patWardName = patWardName;
	}


	public String getPatRoomName() {
		return patRoomName;
	}


	public void setPatRoomName(String patRoomName) {
		this.patRoomName = patRoomName;
	}


	public String getPatBedName() {
		return patBedName;
	}


	public void setPatBedName(String patBedName) {
		this.patBedName = patBedName;
	}


	public String getPatOrderByDoc() {
		return patOrderByDoc;
	}


	public void setPatOrderByDoc(String patOrderByDoc) {
		this.patOrderByDoc = patOrderByDoc;
	}


	public String getPatGender() {
		return patGender;
	}


	public void setPatGender(String patGender) {
		this.patGender = patGender;
	}


	public String getPatVisitDat() {
		return patVisitDat;
	}


	public void setPatVisitDat(String patVisitDat) {
		this.patVisitDat = patVisitDat;
	}


	public String getShowStatus() {
		return showStatus;
	}


	public void setShowStatus(String showStatus) {
		this.showStatus = showStatus;
	}


	public String getTestName() {
		return testName;
	}


	public void setTestName(String testName) {
		this.testName = testName;
	}


	public String getSampleName() {
		return sampleName;
	}


	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}


	public String getSampleCode() {
		return sampleCode;
	}


	public void setSampleCode(String sampleCode) {
		this.sampleCode = sampleCode;
	}


	public String getSampleQnty() {
		return sampleQnty;
	}


	public void setSampleQnty(String sampleQnty) {
		this.sampleQnty = sampleQnty;
	}


	public String getDefaultUOMCode() {
		return defaultUOMCode;
	}


	public void setDefaultUOMCode(String defaultUOMCode) {
		this.defaultUOMCode = defaultUOMCode;
	}


	public String getDefaultContainerCode() {
		return defaultContainerCode;
	}


	public void setDefaultContainerCode(String defaultContainerCode) {
		this.defaultContainerCode = defaultContainerCode;
	}


	public String getPriority() {
		return priority;
	}


	public void setPriority(String priority) {
		this.priority = priority;
	}


	public String getRequisitionDNo() {
		return requisitionDNo;
	}


	public void setRequisitionDNo(String requisitionDNo) {
		this.requisitionDNo = requisitionDNo;
	}


	public String getRejectionReason() {
		return rejectionReason;
	}


	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}


	public String getPriorityCode() {
		return priorityCode;
	}


	public void setPriorityCode(String priorityCode) {
		this.priorityCode = priorityCode;
	}


	public String getMobileNo() {
		return mobileNo;
	}


	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public String[] getChkSamplePatient() {
		return chkSamplePatient;
	}


	public void setChkSamplePatient(String[] chkSamplePatient) {
		this.chkSamplePatient = chkSamplePatient;
	}


	public String getLabCodeSamp() {
		return labCodeSamp;
	}


	public void setLabCodeSamp(String labCodeSamp) {
		this.labCodeSamp = labCodeSamp;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getReqDtlStatus() {
		return reqDtlStatus;
	}


	public void setReqDtlStatus(String reqDtlStatus) {
		this.reqDtlStatus = reqDtlStatus;
	}


	public String getGroupType() {
		return groupType;
	}


	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}


	public String getGroupCode() {
		return groupCode;
	}


	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}


	public String getBillNo() {
		return billNo;
	}


	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}


	public String getConsQty() {
		return consQty;
	}


	public void setConsQty(String consQty) {
		this.consQty = consQty;
	}


	public String getTempPatCrNo() {
		return tempPatCrNo;
	}


	public void setTempPatCrNo(String tempPatCRNo) {
		this.tempPatCrNo = tempPatCRNo;
	}


	public String getTempDailyLabNo() {
		return TempDailyLabNo;
	}


	public void setTempDailyLabNo(String tempDailyLabNo) {
		TempDailyLabNo = tempDailyLabNo;
	}


	public String getStrDailyLabNo() {
		return strDailyLabNo;
	}


	public void setStrDailyLabNo(String strDailyLabNo) {
		this.strDailyLabNo = strDailyLabNo;
	}


	public String[] getChkSamplePatientOnSave() {
		return chkSamplePatientOnSave;
	}


	public void setChkSamplePatientOnSave(String[] chkSamplePatientOnSave) {
		this.chkSamplePatientOnSave = chkSamplePatientOnSave;
	}


	public String getConfig() {
		return config;
	}


	public void setConfig(String config) {
		this.config = config;
	}


	public static String getRecordperpage() {
		return recordPerPage;
	}


	public static String getPagesperblock() {
		return pagesPerBlock;
	}


	public String getNumberOfRowWaste() {
		return numberOfRowWaste;
	}


	public void setNumberOfRowWaste(String numberOfRowWaste) {
		this.numberOfRowWaste = numberOfRowWaste;
	}


	public String[] getAdditionalFilmNo() {
		return additionalFilmNo;
	}


	public void setAdditionalFilmNo(String[] additionalFilmNo) {
		this.additionalFilmNo = additionalFilmNo;
	}


	public String[] getWasteFilmNo() {
		return wasteFilmNo;
	}


	public void setWasteFilmNo(String[] wasteFilmNo) {
		this.wasteFilmNo = wasteFilmNo;
	}


	public String getNumberOfRowAdd() {
		return numberOfRowAdd;
	}


	public void setNumberOfRowAdd(String numberOfRowAdd) {
		this.numberOfRowAdd = numberOfRowAdd;
	}


	public String getTestCode() {
		return testCode;
	}


	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}


	public String[] getFilmReasonWaste() {
		return filmReasonWaste;
	}


	public void setFilmReasonWaste(String[] filmReasonWaste) {
		this.filmReasonWaste = filmReasonWaste;
	}


	public String[] getChkBoxBatchValue() {
		return chkBoxBatchValue;
	}


	public void setChkBoxBatchValue(String[] chkBoxBatchValue) {
		this.chkBoxBatchValue = chkBoxBatchValue;
	}


	public String getLabcodefilm() {
		return labcodefilm;
	}


	public void setLabcodefilm(String labcodefilm) {
		this.labcodefilm = labcodefilm;
	}










}
