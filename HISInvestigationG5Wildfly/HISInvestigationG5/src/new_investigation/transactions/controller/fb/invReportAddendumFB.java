package new_investigation.transactions.controller.fb;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class invReportAddendumFB extends ActionForm
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
	private String[] chkResultValidationPatient;
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
	private String[] resultValidationTemplateValue;
	private String testParaMeterCode;
	private String ParantParaCode;
	private String[] parameterCode;
	private String[] parantParameterCode;
	private String[] requisitionDNo; 
	private String resultValidationTemplateValueWithHash;
	private int startDisplay;
	private int hideDisplay;
	private String uomCode;
	
	private String cannedLabCode;
	private String currentElement;
	private String currentElementName;
	private String cannedOrMacroCheck;
	private String checkProcessFb;
	private String getSearchType;
	private String testGroupCode;
	private String toSampleNo;
	private String fromSampleNo;
	private String fromLabNo;
	private String toLabNo;
	private String generationType;
	private String testWiseCode;
	private String testGroupCodeWise;
	private String onLoadValue;
	private String userCannedCode;
	private String editorName;
	private String newEntry;
	private String tempPatName;
	private String finalRemarksValue[];
	
	
	private String diagnosis;
	private String amendment;
	private String addendum;
	private String amendmentCheck;
	private String addendumCheck;
	private String diagnosisCheck;
	private String addendumRemarks[];
	private String crNoReqNoStringAddendum[];
	private String fileName;
	private String showStatusLegends;
	private String reason;
	private String reasonCode[];
	private String crNoReqNoStringReason[];
	
	private String isAddendum;
	
	//added by krishnan nema on 01/10/2018
	private String patTestType;
	private String fileuploaddatabase64;
	private String fileuploaddata;
    private String  echovar;

	public String getPatTestType() {
				return patTestType;
	}






	public void setPatTestType(String patTestType) {
		this.patTestType = patTestType;
	}
	
	public String getFileName() {
		return fileName;
	}






	public void setFileName(String fileName) {
		this.fileName = fileName;
	}






	public String[] getCrNoReqNoStringAddendum() {
		return crNoReqNoStringAddendum;
	}






	public void setCrNoReqNoStringAddendum(String[] crNoReqNoStringAddendum) {
		this.crNoReqNoStringAddendum = crNoReqNoStringAddendum;
	}






	public String[] getAddendumRemarks() {
		return addendumRemarks;
	}






	public void setAddendumRemarks(String[] addendumRemarks) {
		this.addendumRemarks = addendumRemarks;
	}






	public String getDiagnosisCheck() {
		return diagnosisCheck;
	}






	public void setDiagnosisCheck(String diagnosisCheck) {
		this.diagnosisCheck = diagnosisCheck;
	}






	public String getAmendmentCheck() {
		return amendmentCheck;
	}






	public void setAmendmentCheck(String amendmentCheck) {
		this.amendmentCheck = amendmentCheck;
	}






	public String getAddendumCheck() {
		return addendumCheck;
	}






	public void setAddendumCheck(String addendumCheck) {
		this.addendumCheck = addendumCheck;
	}


	
	public String getDiagnosis() {
		return diagnosis;
	}






	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}






	public String getAmendment() {
		return amendment;
	}






	public void setAmendment(String amendment) {
		this.amendment = amendment;
	}






	public String getAddendum() {
		return addendum;
	}






	public void setAddendum(String addendum) {
		this.addendum = addendum;
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

	private String crNoReqNoString[];
	
	public String getNewEntry() {
		return newEntry;
	}






	public void setNewEntry(String newEntry) {
		this.newEntry = newEntry;
	}






	public String getGetSearchType() {
		return getSearchType;
	}






	public void setGetSearchType(String getSearchType) {
		this.getSearchType = getSearchType;
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






	public String getTestWiseCode() {
		return testWiseCode;
	}






	public void setTestWiseCode(String testWiseCode) {
		this.testWiseCode = testWiseCode;
	}






	public String getTestGroupCodeWise() {
		return testGroupCodeWise;
	}






	public void setTestGroupCodeWise(String testGroupCodeWise) {
		this.testGroupCodeWise = testGroupCodeWise;
	}






	public String getOnLoadValue() {
		return onLoadValue;
	}






	public void setOnLoadValue(String onLoadValue) {
		this.onLoadValue = onLoadValue;
	}






	public String getCheckProcessFb() {
		return checkProcessFb;
	}






	public void setCheckProcessFb(String checkProcessFb) {
		this.checkProcessFb = checkProcessFb;
	}






	public String getCannedLabCode() {
		return cannedLabCode;
	}






	public void setCannedLabCode(String cannedLabCode) {
		this.cannedLabCode = cannedLabCode;
	}






	public String getCurrentElement() {
		return currentElement;
	}






	public void setCurrentElement(String currentElement) {
		this.currentElement = currentElement;
	}






	public String getCurrentElementName() {
		return currentElementName;
	}






	public void setCurrentElementName(String currentElementName) {
		this.currentElementName = currentElementName;
	}






	public String getCannedOrMacroCheck() {
		return cannedOrMacroCheck;
	}






	public void setCannedOrMacroCheck(String cannedOrMacroCheck) {
		this.cannedOrMacroCheck = cannedOrMacroCheck;
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

	private String reportAvailableAfter;
	private String episodeCode;
	 
	
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






	public String[] getChkResultValidationPatient() {
		return chkResultValidationPatient;
	}






	public void setChkResultValidationPatient(String[] chkResultValidationPatient) {
		this.chkResultValidationPatient = chkResultValidationPatient;
	}






	public String[] getResultValidationTemplateValue() {
		return resultValidationTemplateValue;
	}






	public void setResultValidationTemplateValue(String[] resultValidationTemplateValue) {
		this.resultValidationTemplateValue = resultValidationTemplateValue;
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






	public String getResultValidationTemplateValueWithHash() {
		return resultValidationTemplateValueWithHash;
	}






	public void setResultValidationTemplateValueWithHash(
			String resultValidationTemplateValueWithHash) {
		this.resultValidationTemplateValueWithHash = resultValidationTemplateValueWithHash;
	}






	public int getStartDisplay() {
		return startDisplay;
	}






	public void setStartDisplay(int startDisplay) {
		this.startDisplay = startDisplay;
	}






	public int getHideDisplay() {
		return hideDisplay;
	}






	public void setHideDisplay(int hideDisplay) {
		this.hideDisplay = hideDisplay;
	}






	public String getSysDate() {
		return sysDate;
	}






	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
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






	public String getShowStatusLegends() {
		return showStatusLegends;
	}






	public void setShowStatusLegends(String showStatusLegends) {
		this.showStatusLegends = showStatusLegends;
	}






	public String getReason() {
		return reason;
	}






	public void setReason(String reason) {
		this.reason = reason;
	}






	public String[] getReasonCode() {
		return reasonCode;
	}






	public void setReasonCode(String[] reasonCode) {
		this.reasonCode = reasonCode;
	}






	public String[] getCrNoReqNoStringReason() {
		return crNoReqNoStringReason;
	}






	public void setCrNoReqNoStringReason(String[] crNoReqNoStringReason) {
		this.crNoReqNoStringReason = crNoReqNoStringReason;
	}






	public String getIsAddendum() {
		return isAddendum;
	}






	public void setIsAddendum(String isAddendum) {
		this.isAddendum = isAddendum;
	}






	public String getFileuploaddatabase64() {
		return fileuploaddatabase64;
	}






	public void setFileuploaddatabase64(String fileuploaddatabase64) {
		this.fileuploaddatabase64 = fileuploaddatabase64;
	}






	public String getFileuploaddata() {
		return fileuploaddata;
	}






	public void setFileuploaddata(String fileuploaddata) {
		this.fileuploaddata = fileuploaddata;
	}






	public String getEchovar() {
		return echovar;
	}






	public void setEchovar(String echovar) {
		this.echovar = echovar;
	}














 






	 
}