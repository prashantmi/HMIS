/**
 <!--
  
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: JATIN KUMAR
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    : TEST WISE CONSUMABLE PROCESS
 ## Purpose						        : 
 ## Date of Creation					: 06/09/2016
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 


*/


package new_investigation.transactions.controller.fb;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

public class TestWiseConsumableFB extends ActionForm
{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	 
	private String rejectionId;
	private String rejectionReason;
	private String priorityCode;
	private String mobileNo;
	private String emailId;
	private String[] chkSamplePatient;
	private String[] chkResultEntryPatient;
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
	private String patientWise;
	private String[] resultEntryTemplateValue;
	private String testParaMeterCode;
	private String ParantParaCode;
	private String[] parameterCode;
	private String[] parantParameterCode;
	private String[] requisitionDNo; 
	private String resultEntryTemplateValueWithHash;
	private String reportAvailableAfter;
	private String episodeCode;
	private String uomCode;
	private String pdfGenerationType;
	private String cannedLabCode;
	private String currentElement;
	private String currentElementName;
	private String cannedOrMacroCheck;
	private String checkProcessFb;
	
	private String getSearchType;
	private String generationType;
	private String testGroupCode;
	private String toSampleNo;
	private String fromSampleNo;
	private String fromLabNo;
	private String toLabNo;
	private String testWiseCode;
	private String testGroupCodeWise;
	private String onLoadValue;
	private String newEntry;
	private String previousValue;
	private String userCannedCode;
	private String editorName;
	private String tempPatName;
	private String finalRemarksValue[];
	private String crNoReqNoString[];
	private String itemName;
	private String []defaultQuantity;
	private String unit;
	private String batchNo;
	private String manufacture;
	private String expiryDate="";
	private String storeName;
	private String itemType;
	private String []lotNo;
	private String []selectedItem;
	private String []isMapped;
	private String []unitCode;
	private String []tempSelectedItem;
	private String deleteIndex;
	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	private  List  file;
	
	private List formFiles = new ArrayList();
	
	private String[] crreqnoFile;
	
	private List<FormFile> files = new ArrayList<FormFile>();
	
	private File[] fileUpload;
	private int sizeOfFile=0;
	public int getSizeOfFile() {
		return sizeOfFile;
	}

	public void setSizeOfFile(int sizeOfFile) {
		this.sizeOfFile = sizeOfFile;
	}

	public String getOnLoadValue() {
		return onLoadValue;
	}

	public void setOnLoadValue(String onLoadValue) {
		this.onLoadValue = onLoadValue;
	}

	public String getTestGroupCodeWise() {
		return testGroupCodeWise;
	}

	public void setTestGroupCodeWise(String testGroupCodeWise) {
		this.testGroupCodeWise = testGroupCodeWise;
	}

	public String getTestWiseCode() {
		return testWiseCode;
	}

	public void setTestWiseCode(String testWiseCode) {
		this.testWiseCode = testWiseCode;
	}

	public String getTestGroupCode() {
		return testGroupCode;
	}

	public void setTestGroupCode(String testGroupCode) {
		this.testGroupCode = testGroupCode;
	}

	public String getToSampleNo() {
		return toSampleNo;
	}

	public void setToSampleNo(String toSampleNo) {
		this.toSampleNo = toSampleNo;
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

	public String getToLabNo() {
		return toLabNo;
	}

	public void setToLabNo(String toLabNo) {
		this.toLabNo = toLabNo;
	}

	
	public String getGenerationType() {
		return generationType;
	}

	public void setGenerationType(String generationType) {
		this.generationType = generationType;
	}

	public String getGetSearchType() {
		return getSearchType;
	}
 
	public void setGetSearchType(String getSearchType) {
		this.getSearchType = getSearchType;
	}

 


	public String getCheckProcessFb() {
		return checkProcessFb;
	}

 


	public void setCheckProcessFb(String checkProcessFb) {
		this.checkProcessFb = checkProcessFb;
	}






	public String getCannedOrMacroCheck() {
		return cannedOrMacroCheck;
	}






	public void setCannedOrMacroCheck(String cannedOrMacroCheck) {
		this.cannedOrMacroCheck = cannedOrMacroCheck;
	}






	public String getCurrentElementName() {
		return currentElementName;
	}






	public void setCurrentElementName(String currentElementName) {
		this.currentElementName = currentElementName;
	}






	public String getCurrentElement() {
		return currentElement;
	}






	public void setCurrentElement(String currentElement) {
		this.currentElement = currentElement;
	}






	public String getCannedLabCode() {
		return cannedLabCode;
	}






	public void setCannedLabCode(String cannedLabCode) {
		this.cannedLabCode = cannedLabCode;
	}






	public String getPdfGenerationType() {
		return pdfGenerationType;
	}






	public void setPdfGenerationType(String pdfGenerationType) {
		this.pdfGenerationType = pdfGenerationType;
	}

	HashMap<String,String> paraLoinc;
	
	public HashMap<String, String> getParaLoinc() {
		return paraLoinc;
	}






	public void setParaLoinc(HashMap<String, String> paraLoinc) {
		this.paraLoinc = paraLoinc;
	}






	public String getUomCode() {
		return uomCode;
	}






	public void setUomCode(String uomCode) {
		this.uomCode = uomCode;
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
	public String getNumberOfRow() {
		return numberOfRow;
	}
	public void setNumberOfRow(String numberOfRow) {
		this.numberOfRow = numberOfRow;
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
	public String[] getLabNoConfiguration() {
		return labNoConfiguration;
	}
	public void setLabNoConfiguration(String[] labNoConfiguration) {
		this.labNoConfiguration = labNoConfiguration;
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
	 
	public String getRejectionId() {
		return rejectionId;
	}
	public void setRejectionId(String rejectionId) {
		this.rejectionId = rejectionId;
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
	public String getTempPatCRNo() {
		return tempPatCRNo;
	}
	public void setTempPatCRNo(String tempPatCRNo) {
		this.tempPatCRNo = tempPatCRNo;
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
	public static String getRecordperpage() {
		return recordPerPage;
	}
	public static String getPagesperblock() {
		return pagesPerBlock;
	}
	public String getPatientWise() {
		return patientWise;
	}
	public void setPatientWise(String patientWise) {
		this.patientWise = patientWise;
	}

	private String[] selectedWorkOrderNo;
 
	public String[] getSelectedWorkOrderNo() {
		return selectedWorkOrderNo;
	}

	public void setSelectedWorkOrderNo(String[] selectedWorkOrderNo) {
		this.selectedWorkOrderNo = selectedWorkOrderNo;
	}






	public String[] getChkResultEntryPatient() {
		return chkResultEntryPatient;
	}






	public void setChkResultEntryPatient(String[] chkResultEntryPatient) {
		this.chkResultEntryPatient = chkResultEntryPatient;
	}






	public String[] getResultEntryTemplateValue() {
		return resultEntryTemplateValue;
	}






	public void setResultEntryTemplateValue(String[] resultEntryTemplateValue) {
		this.resultEntryTemplateValue = resultEntryTemplateValue;
	}






	public String getTestParaMeterCode() {
		return testParaMeterCode;
	}






	public void setTestParaMeterCode(String testParaMeterCode) {
		this.testParaMeterCode = testParaMeterCode;
	}






	public String getParantParaCode() {
		return ParantParaCode;
	}






	public void setParantParaCode(String parantParaCode) {
		ParantParaCode = parantParaCode;
	}






	public String[] getParameterCode() {
		return parameterCode;
	}






	public void setParameterCode(String[] parameterCode) {
		this.parameterCode = parameterCode;
	}






	public String[] getParantParameterCode() {
		return parantParameterCode;
	}






	public void setParantParameterCode(String[] parantParameterCode) {
		this.parantParameterCode = parantParameterCode;
	}






	public String[] getRequisitionDNo() {
		return requisitionDNo;
	}






	public void setRequisitionDNo(String[] requisitionDNo) {
		this.requisitionDNo = requisitionDNo;
	}






	public String getResultEntryTemplateValueWithHash() {
		return resultEntryTemplateValueWithHash;
	}






	public void setResultEntryTemplateValueWithHash(
			String resultEntryTemplateValueWithHash) {
		this.resultEntryTemplateValueWithHash = resultEntryTemplateValueWithHash;
	}






	public String getReportAvailableAfter() {
		return reportAvailableAfter;
	}






	public void setReportAvailableAfter(String reportAvailableAfter) {
		this.reportAvailableAfter = reportAvailableAfter;
	}






	public String getEpisodeCode() {
		return episodeCode;
	}






	public void setEpisodeCode(String episodeCode) {
		this.episodeCode = episodeCode;
	}






	public String getSysDate() {
		return sysDate;
	}






	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}

	public String getNewEntry() {
		return newEntry;
	}

	public void setNewEntry(String newEntry) {
		this.newEntry = newEntry;
	}

	public String getPreviousValue() {
		return previousValue;
	}

	public void setPreviousValue(String previousValue) {
		this.previousValue = previousValue;
	}

	public String getUserCannedCode() {
		return userCannedCode;
	}

	public void setUserCannedCode(String userCannedCode) {
		this.userCannedCode = userCannedCode;
	}

	public String getEditorName() {
		return editorName;
	}

	public void setEditorName(String editorName) {
		this.editorName = editorName;
	}

	public String getTempPatName() {
		return tempPatName;
	}

	public void setTempPatName(String tempPatName) {
		this.tempPatName = tempPatName;
	}

	public String[] getFinalRemarksValue() {
		return finalRemarksValue;
	}

	public void setFinalRemarksValue(String[] finalRemarksValue) {
		this.finalRemarksValue = finalRemarksValue;
	}

	public String[] getCrNoReqNoString() {
		return crNoReqNoString;
	}

	public void setCrNoReqNoString(String[] crNoReqNoString) {
		this.crNoReqNoString = crNoReqNoString;
	}



	public List getFile() {
		 return this.formFiles;
	}

	public void setFile(int iIndex,FormFile formFile) {
		this.formFiles.add(formFile);
	}

	public List getFormFiles() {
		return formFiles;
	}

	public void setFormFiles(List formFiles) {
		this.formFiles = formFiles;
	}

	public File[] getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(File[] fileUpload) {
		this.fileUpload = fileUpload;
	}

	public List<FormFile> getFiles() {
		return files;
	}

	public void setFiles(List<FormFile> files) {
		this.files = files;
	}

	public String[] getCrreqnoFile() {
		return crreqnoFile;
	}

	public void setCrreqnoFile(String[] crreqnoFile) {
		this.crreqnoFile = crreqnoFile;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getManufacture() {
		return manufacture;
	}

	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String [] getDefaultQuantity() {
		return defaultQuantity;
	}

	public void setDefaultQuantity(String [] defaultQuantity) {
		this.defaultQuantity = defaultQuantity;
	}

	public String[] getLotNo() {
		return lotNo;
	}

	public void setLotNo(String []lotNo) {
		this.lotNo = lotNo;
	}

	public String [] getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(String [] selectedItem) {
		this.selectedItem = selectedItem;
	}

	public String[] getIsMapped() {
		return isMapped;
	}

	public void setIsMapped(String []isMapped) {
		this.isMapped = isMapped;
	}

	public String [] getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String [] unitCode) {
		this.unitCode = unitCode;
	}

	public String [] getTempSelectedItem() {
		return tempSelectedItem;
	}

	public void setTempSelectedItem(String [] tempSelectedItem) {
		this.tempSelectedItem = tempSelectedItem;
	}

	public String getDeleteIndex() {
		return deleteIndex;
	}

	public void setDeleteIndex(String deleteIndex) {
		this.deleteIndex = deleteIndex;
	}
	






	 
}