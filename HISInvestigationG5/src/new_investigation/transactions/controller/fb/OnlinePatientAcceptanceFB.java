package new_investigation.transactions.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class OnlinePatientAcceptanceFB extends ActionForm
{
	private String patCRNo;
	private String selectedEpisode;
	private String selectedEpisodeRadio;
	private String hmode;
	private String currentPageNo="1";
	private String currentblock="1";
	private String sampleCollectionNeeded="false";
	public String pageString;
	private String seatId;
	public String sampleCollectionForwardinRequired;
	public static final String recordPerPage="10";
	public static final String pagesPerBlock="10";
	
	private String patCrNo;
	private String patStatus;
	
	private String searchLabName;
	private String searchTestName;
	
	private int currentPage;
	private int startIndex;
	private int endIndex;
	
	private String[] chkLabTest;
	
	private String labTestCodeArray;
	
	 
	private String[] testCode;
	private String[] sampleInfo;
	private String[] mandatoryInfo;
	private String tmpTestCode;
	private String patType;



//added by chandan
private String isSampleAreaSelected;
private String sampleAreaCode;
private String sampleAreaName;

//added by krishnan
private String patientType;
private String machineCode;
private String selectedmachineCode;
private String[]  extrarejectionReason;

private String numberOfRow;

private String bookMarkCode;

private String isBookMark;


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
private String billDetail;
private String labName;
private String requisitionNo;
private String requisitionDate;
private String testName;
private String sampleName;
private String sampleCode;
private String sampleQnty;
private String defaultUOMCode;
private String defaultContainerCode;
private String priority;
private String[] labNoConfiguration;
private String[] accepted;
private String[] rejectionAction;
private String requisitionDNo;
private String rejectionId;
private String[] rejectionReason;
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
private String tempPatCRNo;
private String TempDailyLabNo;
private String strDailyLabNo;
private String[] chkSamplePatientOnSave;
private String config;


//added by prashantMi
private String acceptedToNotAccepted;
private String entryDate;
private String labNoConfiguration2;
private String userHasPermission;
private String patCategoryCode;

public String getUserHasPermission() {
	return userHasPermission;
}
public void setUserHasPermission(String userHasPermission) {
	this.userHasPermission = userHasPermission;
}
public String getLabNoConfiguration2() {
	return labNoConfiguration2;
}
public void setLabNoConfiguration2(String labNoConfiguration2) {
	this.labNoConfiguration2 = labNoConfiguration2;
}
public String getAcceptedToNotAccepted() {
	return acceptedToNotAccepted;
}
public void setAcceptedToNotAccepted(String acceptedToNotAccepted) {
	this.acceptedToNotAccepted = acceptedToNotAccepted;
}

public String getEntryDate() {
	return entryDate;
}


public void setEntryDate(String entryDate) {
	this.entryDate = entryDate;
}


	public String getTmpTestCode() {
		return tmpTestCode;
	}


	public void setTmpTestCode(String tmpTestCode) {
		this.tmpTestCode = tmpTestCode;
	}


	
	public String getConfig() {
		return config;
	}


	public void setConfig(String config) {
		this.config = config;
	}


	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.currentPage=1;
		this.searchLabName="";
		this.searchTestName="";
		this.bookMarkCode="";
		this.sampleInfo=null;
		this.isBookMark="B";
	}
	
	
	public String getPatCRNo() {
		return patCRNo;
	}
	public void setPatCRNo(String patCRNo) {
		this.patCRNo = patCRNo;
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
	public String getSampleCollectionNeeded() {
		return sampleCollectionNeeded;
	}
	public void setSampleCollectionNeeded(String sampleCollectionNeeded) {
		this.sampleCollectionNeeded = sampleCollectionNeeded;
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
	public String getSampleCollectionForwardinRequired() {
		return sampleCollectionForwardinRequired;
	}
	public void setSampleCollectionForwardinRequired(
			String sampleCollectionForwardinRequired) {
		this.sampleCollectionForwardinRequired = sampleCollectionForwardinRequired;
	}
	public static String getRecordperpage() {
		return recordPerPage;
	}
	public static String getPagesperblock() {
		return pagesPerBlock;
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
	public String getSearchLabName() {
		return searchLabName;
	}
	public void setSearchLabName(String searchLabName) {
		this.searchLabName = searchLabName;
	}
	public String getSearchTestName() {
		return searchTestName;
	}
	public void setSearchTestName(String searchTestName) {
		this.searchTestName = searchTestName;
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


	public String[] getChkLabTest() {
		return chkLabTest;
	}


	public void setChkLabTest(String[] chkLabTest) {
		this.chkLabTest = chkLabTest;
	}


	public String getLabTestCodeArray() {
		return labTestCodeArray;
	}


	public void setLabTestCodeArray(String labTestCodeArray) {
		this.labTestCodeArray = labTestCodeArray;
	}


	public String getNumberOfRow() {
		return numberOfRow;
	}


	public void setNumberOfRow(String numberOfRow) {
		this.numberOfRow = numberOfRow;
	}


	 

	public String[] getTestCode() {
		return testCode;
	}


	public void setTestCode(String[] testCode) {
		this.testCode = testCode;
	}


	public String[] getSampleInfo() {
		return sampleInfo;
	}


	public void setSampleInfo(String[] sampleInfo) {
		this.sampleInfo = sampleInfo;
	}


	public String[] getMandatoryInfo() {
		return mandatoryInfo;
	}


	public void setMandatoryInfo(String[] mandatoryInfo) {
		this.mandatoryInfo = mandatoryInfo;
	}


	public String getBookMarkCode() {
		return bookMarkCode;
	}


	public void setBookMarkCode(String bookMarkCode) {
		this.bookMarkCode = bookMarkCode;
	}


	public String getIsBookMark() {
		return isBookMark;
	}


	public void setIsBookMark(String isBookMark) {
		this.isBookMark = isBookMark;
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


	public String getBillDetail() {
		return billDetail;
	}


	public void setBillDetail(String billDetail) {
		this.billDetail = billDetail;
	}


	public String getLabName() {
		return labName;
	}


	public void setLabName(String labName) {
		this.labName = labName;
	}


	public String getRequisitionNo() {
		return requisitionNo;
	}


	public void setRequisitionNo(String requisitionNo) {
		this.requisitionNo = requisitionNo;
	}


	public String getRequisitionDate() {
		return requisitionDate;
	}


	public void setRequisitionDate(String requisitionDate) {
		this.requisitionDate = requisitionDate;
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


	public String getRejectionId() {
		return rejectionId;
	}


	public void setRejectionId(String rejectionId) {
		this.rejectionId = rejectionId;
	}


	


	public String[] getRejectionReason() {
		return rejectionReason;
	}


	public void setRejectionReason(String[] rejectionReason) {
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


	public String getTempPatCRNo() {
		return tempPatCRNo;
	}


	public void setTempPatCRNo(String tempPatCRNo) {
		this.tempPatCRNo = tempPatCRNo;
	}


	public String[] getAccepted() {
		return accepted;
	}


	public void setAccepted(String[] accepted) {
		this.accepted = accepted;
	}


	public String[] getRejectionAction() {
		return rejectionAction;
	}


	public void setRejectionAction(String[] rejectionAction) {
		this.rejectionAction = rejectionAction;
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


	public String[] getLabNoConfiguration() {
		return labNoConfiguration;
	}


	public void setLabNoConfiguration(String[] labNoConfiguration) {
		this.labNoConfiguration = labNoConfiguration;
	}


	public String[] getChkSamplePatientOnSave() {
		return chkSamplePatientOnSave;
	}


	public void setChkSamplePatientOnSave(String[] chkSamplePatientOnSave) {
		this.chkSamplePatientOnSave = chkSamplePatientOnSave;
	}


	public String getSysDate() {
		return sysDate;
	}


	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}


	public String getIsSampleAreaSelected() {
		return isSampleAreaSelected;
	}


	public void setIsSampleAreaSelected(String isSampleAreaSelected) {
		this.isSampleAreaSelected = isSampleAreaSelected;
	}


	public String getSampleAreaCode() {
		return sampleAreaCode;
	}


	public void setSampleAreaCode(String sampleAreaCode) {
		this.sampleAreaCode = sampleAreaCode;
	}


	public String getSampleAreaName() {
		return sampleAreaName;
	}


	public void setSampleAreaName(String sampleAreaName) {
		this.sampleAreaName = sampleAreaName;
	}


	public String getPatientType() {
		return patientType;
	}


	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}


	public String getPatType() {
		return patType;
	}


	public void setPatType(String patType) {
		this.patType = patType;
	}


	public String getMachineCode() {
		return machineCode;
	}


	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}


	public String getSelectedmachineCode() {
		return selectedmachineCode;
	}


	public void setSelectedmachineCode(String selectedmachineCode) {
		this.selectedmachineCode = selectedmachineCode;
	}


	public String[] getExtrarejectionReason() {
		return extrarejectionReason;
	}


	public void setExtrarejectionReason(String[] extrarejectionReason) {
		this.extrarejectionReason = extrarejectionReason;
	}
	public String getPatCategoryCode() {
		return patCategoryCode;
	}
	public void setPatCategoryCode(String patCategoryCode) {
		this.patCategoryCode = patCategoryCode;
	}



	 

	 
	
}
