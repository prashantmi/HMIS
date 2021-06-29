package investigationDesk.transactions.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class InvestigationRaisingDtlFB extends ActionForm
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
	private String patStatusCode;
	private String searchLabName;
	private String searchTestName;
	
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
	private String deskType;
	
	private String labTestAptDate[];
	private String labTestAptTime[];
	private String labTestAptRefNo[];
	private String aptLabCode;
	
	private String departmentUnitCode; // Added by Vasu on 28.Nov.2017 
	
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


	private String tstGrpArr;
	
	private String appointmentDate;
	
	private String appointmentTime;
	private String fromDate;
	private String toDate;
	private String tmpCrNo;
	private String prvTestDtl; 
	private String aptNO;
	private String pukNo;
	private String patFirstName;
	private String PatMiddleName;
	private String patLastNAme;
	private String gender;
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
	
	private String accountNo;
	private String advisedByName;
	private String advisedByDoctorName;
	private String advisedBy;
	private String admissionDate;
    private String appointmentRefNo;
    private String patCatCode;
	
	
	public String getPatCatCode() {
		return patCatCode;
	}


	public void setPatCatCode(String patCatCode) {
		this.patCatCode = patCatCode;
	}


	public String getAppointmentRefNo() {
		return appointmentRefNo;
	}


	public void setAppointmentRefNo(String appointmentRefNo) {
		this.appointmentRefNo = appointmentRefNo;
	}


	public String getAdvisedBy() {
		return advisedBy;
	}


	public void setAdvisedBy(String advisedBy) {
		this.advisedBy = advisedBy;
	}


	public String getAdvisedByDoctorName() {
		return advisedByDoctorName;
	}


	public void setAdvisedByDoctorName(String advisedByDoctorName) {
		this.advisedByDoctorName = advisedByDoctorName;
	}


	public String getAdvisedByName() {
		return advisedByName;
	}


	public void setAdvisedByName(String advisedByName) {
		this.advisedByName = advisedByName;
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


	public String getPatFirstName() {
		return patFirstName;
	}


	public void setPatFirstName(String patFirstName) {
		this.patFirstName = patFirstName;
	}


	public String getPatMiddleName() {
		return PatMiddleName;
	}


	public void setPatMiddleName(String patMiddleName) {
		PatMiddleName = patMiddleName;
	}


	public String getPatLastNAme() {
		return patLastNAme;
	}


	public void setPatLastNAme(String patLastNAme) {
		this.patLastNAme = patLastNAme;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
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


	public String getAccountNo() {
		return accountNo;
	}


	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}


	public String getPatStatusCode() {
		return patStatusCode;
	}


	public void setPatStatusCode(String patStatusCode) {
		this.patStatusCode = patStatusCode;
	}


	public String getAdmissionDate() {
		return admissionDate;
	}


	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}


	public String getDeskType() {
		return deskType;
	}


	public void setDeskType(String deskType) {
		this.deskType = deskType;
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


	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}


	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
	}


	
}
