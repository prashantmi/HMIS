package ehr.allergies.presentation;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ehr.global.presentation.EHRSection_GlobalFB;

public class EHRSection_AllergiesFB extends EHRSection_GlobalFB
{
	private String selectedEpisode;
	private String selectedEpisodeRadio;
	private String hmode;
	private String currentPageNo="1";
	private String currentblock="1";
	private String sampleCollectionNeeded="false";
	public String pageString;
	
	public String sampleCollectionForwardinRequired;
	public static final String recordPerPage="10";
	public static final String pagesPerBlock="10";
	
	private String searchLabName;
	private String searchTestName;

	private String alertName;
	private String patAlertId;
	private String effectiveFrom;
	private String remarks;
	private String durationDate;
	private String effectiveTo;

	private String revokeRemarks;
	
	private String entryDate;

	private String isValid;
	private String durationDays;


	
	private boolean checkedRevoke;
	private int currentPage;
	private int startIndex;
	private int endIndex;
	
	private String[] chkLabTest;
	
	private String labTestCodeArray;
	
	private String[] labCode;
	private String[] testCode;
	private String[] sampleInfo;
	private String[] mandatoryInfo;
	
	
	private String tmpLabCode;
	private String tmpTestCode;
	private String tmpSampleCode;
	private String tmpPriority;
	
	private String numberOfRow;
	
	private String bookMarkCode;
	
	private String isBookMark;
	private String tempPatCRNo;
	
	private String tstValArr;
	
	private String isTestGroup;
	
	
	private String searchTestGroupName;
	
	private String searchTestGroup;
	private String chkLabTestGroup;
	private String labtestGroupValues;
	private String tstOrTestGroupValue;
	private String testLabTestCodeWise;
	private String testCodeWise;  
	private String testCodeLabValue;
	private String delLabCode;
	private String delTestCode;
	private String dupTestCode;
	private String dupLabCode;
	private String appoitmentNo;
	private String hidAptNo;
	private String offlineAptDtl;
	private String lengendStatus;

	private String labTestAptDate[];
	private String labTestAptTime[];
	private String labTestAptRefNo[];
	private String aptLabCode;
	
	private String requisitionDNo[];
	
	private String testName;
	private String testValue;
	private String parameterName;
	private String ftlValueForAllergies;
	
private String tstGrpArr;
	
	private String appointmentDate;
	
	private String appointmentTime;
	private String fromDate;
	private String toDate;
	private String tmpCrNo;
	private String prvTestDtl; 
	private String aptNO;
	private String pukNo;
	
	private String aptDate;
	private String slotTime;
	private String age;
	private String aptStatus;
	private String aptTestCode;
	private String offlineAppoitMentDate;
	private String offlineAppoitmentDtl;
	private String hideSaveButton;
	private String mandCombo;
	private String mandComboTextBoxComboNames;
	private String mandComboTextBoxComboNamesOnPage;
	
	private String finalMandValues;
	
	private String mandCode;
	
	private String finalMandCode;
	
	private String finalMandCodeValuesOnBookmark;
	private boolean callFromDesk;
	

	
	
	public String getTestLabTestCodeWise() {
		return testLabTestCodeWise;
	}


	public void setTestLabTestCodeWise(String testLabTestCodeWise) {
		this.testLabTestCodeWise = testLabTestCodeWise;
	}


	public String getTestCodeWise() {
		return testCodeWise;
	}


	public void setTestCodeWise(String testCodeWise) {
		this.testCodeWise = testCodeWise;
	}


	public String getTestCodeLabValue() {
		return testCodeLabValue;
	}


	public void setTestCodeLabValue(String testCodeLabValue) {
		this.testCodeLabValue = testCodeLabValue;
	}


	public String getDelLabCode() {
		return delLabCode;
	}


	public void setDelLabCode(String delLabCode) {
		this.delLabCode = delLabCode;
	}


	public String getDelTestCode() {
		return delTestCode;
	}


	public void setDelTestCode(String delTestCode) {
		this.delTestCode = delTestCode;
	}


	public String getDupTestCode() {
		return dupTestCode;
	}


	public void setDupTestCode(String dupTestCode) {
		this.dupTestCode = dupTestCode;
	}


	public String getDupLabCode() {
		return dupLabCode;
	}


	public void setDupLabCode(String dupLabCode) {
		this.dupLabCode = dupLabCode;
	}


	public String getAppoitmentNo() {
		return appoitmentNo;
	}


	public void setAppoitmentNo(String appoitmentNo) {
		this.appoitmentNo = appoitmentNo;
	}


	public String getHidAptNo() {
		return hidAptNo;
	}


	public void setHidAptNo(String hidAptNo) {
		this.hidAptNo = hidAptNo;
	}


	public String getOfflineAptDtl() {
		return offlineAptDtl;
	}


	public void setOfflineAptDtl(String offlineAptDtl) {
		this.offlineAptDtl = offlineAptDtl;
	}


	public String getLengendStatus() {
		return lengendStatus;
	}


	public void setLengendStatus(String lengendStatus) {
		this.lengendStatus = lengendStatus;
	}


	public String getTstOrTestGroupValue() {
		return tstOrTestGroupValue;
	}


	public void setTstOrTestGroupValue(String tstOrTestGroupValue) {
		this.tstOrTestGroupValue = tstOrTestGroupValue;
	}


	public String getLabtestGroupValues() {
		return labtestGroupValues;
	}


	public void setLabtestGroupValues(String labtestGroupValues) {
		this.labtestGroupValues = labtestGroupValues;
	}


	public String getChkLabTestGroup() {
		return chkLabTestGroup;
	}


	public void setChkLabTestGroup(String chkLabTestGroup) {
		this.chkLabTestGroup = chkLabTestGroup;
	}


	public String getSearchTestGroup() {
		return searchTestGroup;
	}


	public void setSearchTestGroup(String searchTestGroup) {
		this.searchTestGroup = searchTestGroup;
	}


	
	

	public String getFinalMandCodeValuesOnBookmark() {
		return finalMandCodeValuesOnBookmark;
	}


	public void setFinalMandCodeValuesOnBookmark(
			String finalMandCodeValuesOnBookmark) {
		this.finalMandCodeValuesOnBookmark = finalMandCodeValuesOnBookmark;
	}


	public String getFinalMandCode() {
		return finalMandCode;
	}


	public void setFinalMandCode(String finalMandCode) {
		this.finalMandCode = finalMandCode;
	}


	public String getMandCode() {
		return mandCode;
	}


	public void setMandCode(String mandCode) {
		this.mandCode = mandCode;
	}


	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.currentPage=1;
		this.searchLabName="";
		this.searchTestName="";
		this.searchTestGroupName="";
		this.bookMarkCode="";
		this.sampleInfo=null;
		this.isBookMark="B";
		this.tstValArr="";
		this.tstGrpArr="";
		this.isTestGroup="0";
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


	public String[] getLabCode() {
		return labCode;
	}


	public void setLabCode(String[] labCode) {
		this.labCode = labCode;
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


	public String getTmpLabCode() {
		return tmpLabCode;
	}


	public void setTmpLabCode(String tmpLabCode) {
		this.tmpLabCode = tmpLabCode;
	}


	public String getTmpTestCode() {
		return tmpTestCode;
	}


	public void setTmpTestCode(String tmpTestCode) {
		this.tmpTestCode = tmpTestCode;
	}


	public String getTmpSampleCode() {
		return tmpSampleCode;
	}


	public void setTmpSampleCode(String tmpSampleCode) {
		this.tmpSampleCode = tmpSampleCode;
	}


	public String getTmpPriority() {
		return tmpPriority;
	}


	public void setTmpPriority(String tmpPriority) {
		this.tmpPriority = tmpPriority;
	}
 


	public String getTempPatCRNo() {
		return tempPatCRNo;
	}


	public void setTempPatCRNo(String tempPatCRNo) {
		this.tempPatCRNo = tempPatCRNo;
	}


	 
 


	public String getTstValArr() {
		return tstValArr;
	}


	public void setTstValArr(String tstValArr) {
		this.tstValArr = tstValArr;
	}


	public String getIsTestGroup() {
		return isTestGroup;
	}


	public void setIsTestGroup(String isTestGroup) {
		this.isTestGroup = isTestGroup;
	}


	public String getSearchTestGroupName() {
		return searchTestGroupName;
	}


	public void setSearchTestGroupName(String searchTestGroupName) {
		this.searchTestGroupName = searchTestGroupName;
	}


	public String getTstGrpArr() {
		return tstGrpArr;
	}


	public void setTstGrpArr(String tstGrpArr) {
		this.tstGrpArr = tstGrpArr;
	}


	public String getAppointmentDate() {
		return appointmentDate;
	}


	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}


	public String getAppointmentTime() {
		return appointmentTime;
	}


	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
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


	public String getTmpCrNo() {
		return tmpCrNo;
	}


	public void setTmpCrNo(String tmpCrNo) {
		this.tmpCrNo = tmpCrNo;
	}


	public String getPrvTestDtl() {
		return prvTestDtl;
	}


	public void setPrvTestDtl(String prvTestDtl) {
		this.prvTestDtl = prvTestDtl;
	}


	public String getAptNO() {
		return aptNO;
	}


	public void setAptNO(String aptNO) {
		this.aptNO = aptNO;
	}


	public String getPukNo() {
		return pukNo;
	}


	public void setPukNo(String pukNo) {
		this.pukNo = pukNo;
	}
	
	public String getAptDate() {
		return aptDate;
	}


	public void setAptDate(String aptDate) {
		this.aptDate = aptDate;
	}


	public String getSlotTime() {
		return slotTime;
	}


	public void setSlotTime(String slotTime) {
		this.slotTime = slotTime;
	}


	public String getAge() {
		return age;
	}


	public void setAge(String age) {
		this.age = age;
	}


	public String getAptStatus() {
		return aptStatus;
	}


	public void setAptStatus(String aptStatus) {
		this.aptStatus = aptStatus;
	}


	public String getAptTestCode() {
		return aptTestCode;
	}


	public void setAptTestCode(String aptTestCode) {
		this.aptTestCode = aptTestCode;
	}


	public String getOfflineAppoitMentDate() {
		return offlineAppoitMentDate;
	}


	public void setOfflineAppoitMentDate(String offlineAppoitMentDate) {
		this.offlineAppoitMentDate = offlineAppoitMentDate;
	}


	public String getOfflineAppoitmentDtl() {
		return offlineAppoitmentDtl;
	}


	public void setOfflineAppoitmentDtl(String offlineAppoitmentDtl) {
		this.offlineAppoitmentDtl = offlineAppoitmentDtl;
	}


	public String getHideSaveButton() {
		return hideSaveButton;
	}


	public void setHideSaveButton(String hideSaveButton) {
		this.hideSaveButton = hideSaveButton;
	}


	public String getMandCombo() {
		return mandCombo;
	}


	public void setMandCombo(String mandCombo) {
		this.mandCombo = mandCombo;
	}


	public String getMandComboTextBoxComboNames() {
		return mandComboTextBoxComboNames;
	}


	public void setMandComboTextBoxComboNames(String mandComboTextBoxComboNames) {
		this.mandComboTextBoxComboNames = mandComboTextBoxComboNames;
	}


	public String getMandComboTextBoxComboNamesOnPage() {
		return mandComboTextBoxComboNamesOnPage;
	}


	public void setMandComboTextBoxComboNamesOnPage(
			String mandComboTextBoxComboNamesOnPage) {
		this.mandComboTextBoxComboNamesOnPage = mandComboTextBoxComboNamesOnPage;
	}


	public String getFinalMandValues() {
		return finalMandValues;
	}


	public void setFinalMandValues(String finalMandValues) {
		this.finalMandValues = finalMandValues;
	}


	



	public String getAptLabCode() {
		return aptLabCode;
	}


	public void setAptLabCode(String aptLabCode) {
		this.aptLabCode = aptLabCode;
	}


	public String[] getLabTestAptDate() {
		return labTestAptDate;
	}


	public void setLabTestAptDate(String[] labTestAptDate) {
		this.labTestAptDate = labTestAptDate;
	}


	public String[] getLabTestAptTime() {
		return labTestAptTime;
	}


	public void setLabTestAptTime(String[] labTestAptTime) {
		this.labTestAptTime = labTestAptTime;
	}


	public String[] getLabTestAptRefNo() {
		return labTestAptRefNo;
	}


	public void setLabTestAptRefNo(String[] labTestAptRefNo) {
		this.labTestAptRefNo = labTestAptRefNo;
	}


	public String[] getRequisitionDNo() {
		return requisitionDNo;
	}


	public void setRequisitionDNo(String[] requisitionDNo) {
		this.requisitionDNo = requisitionDNo;
	}


	public String getTestName() {
		return testName;
	}


	public void setTestName(String testName) {
		this.testName = testName;
	}


	public String getTestValue() {
		return testValue;
	}


	public void setTestValue(String testValue) {
		this.testValue = testValue;
	}


	public String getParameterName() {
		return parameterName;
	}


	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}


	

	public String getAlertName() {
		return alertName;
	}


	public void setAlertName(String alertName) {
		this.alertName = alertName;
	}


	public String getPatAlertId() {
		return patAlertId;
	}


	public void setPatAlertId(String patAlertId) {
		this.patAlertId = patAlertId;
	}


	public String getEffectiveFrom() {
		return effectiveFrom;
	}


	public void setEffectiveFrom(String effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public String getDurationDate() {
		return durationDate;
	}


	public void setDurationDate(String durationDate) {
		this.durationDate = durationDate;
	}


	public String getEffectiveTo() {
		return effectiveTo;
	}


	public void setEffectiveTo(String effectiveTo) {
		this.effectiveTo = effectiveTo;
	}

	public String getRevokeRemarks() {
		return revokeRemarks;
	}


	public void setRevokeRemarks(String revokeRemarks) {
		this.revokeRemarks = revokeRemarks;
	}

	public String getEntryDate() {
		return entryDate;
	}


	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}


	public String getIsValid() {
		return isValid;
	}


	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}


	public String getDurationDays() {
		return durationDays;
	}


	public void setDurationDays(String durationDays) {
		this.durationDays = durationDays;
	}


	

	public boolean isCheckedRevoke() {
		return checkedRevoke;
	}


	public void setCheckedRevoke(boolean checkedRevoke) {
		this.checkedRevoke = checkedRevoke;
	}


	public void setCallFromDesk(boolean callFromDesk) {
		this.callFromDesk = callFromDesk;
	}


	public String getFtlValueForAllergies() {
		return ftlValueForAllergies;
	}


	public void setFtlValueForAllergies(String ftlValueForAllergies) {
		this.ftlValueForAllergies = ftlValueForAllergies;
	}


	
}
