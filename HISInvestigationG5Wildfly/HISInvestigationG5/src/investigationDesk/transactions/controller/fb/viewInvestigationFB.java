package investigationDesk.transactions.controller.fb;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class viewInvestigationFB
  extends ActionForm
{
  private String patCRNo;
  private String selectedEpisode;
  private String selectedEpisodeRadio;
  private String hmode;
  private String currentPageNo = "1";
  private String currentblock = "1";
  private String sampleCollectionNeeded = "false";
  
  public String pageString;
  
  private String seatId;
  
  public String sampleCollectionForwardinRequired;
  
  public static final String recordPerPage = "10";
  
  public static final String pagesPerBlock = "10";
  
  private String fileName;
  
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
  private String fromwhichall;
  private String deskType;
  
  public String getTstOrTestGroupValue() { return this.tstOrTestGroupValue; }
  private String selectedCheckbox; private String callingmode; private String tstGrpArr; private String appointmentDate; private String appointmentTime; private String fromDate; private String toDate; private String tmpCrNo; private String prvTestDtl; private String aptNO; private String pukNo; private String patFirstName; private String PatMiddleName; private String patLastNAme; private String gender; private String aptDate; private String slotTime; private String age; private String aptStatus; private String aptTestCode; private String offlineAppoitMentDate; private String offlineAppoitmentDtl; private String hideSaveButton; private String mandCombo; private String mandComboTextBoxComboNames; private String mandComboTextBoxComboNamesOnPage; private String finalMandValues; private String mandCode; private String finalMandCode; private String finalMandCodeValuesOnBookmark; private String accountNo; private String advisedByName; private String advisedByDoctorName; private String advisedBy; private String admissionDate;
  private String appointmentRefNo;
  private String patCatCode;
  
  public void setTstOrTestGroupValue(String tstOrTestGroupValue) { this.tstOrTestGroupValue = tstOrTestGroupValue; }



  
  public String getLabtestGroupValues() { return this.labtestGroupValues; }



  
  public void setLabtestGroupValues(String labtestGroupValues) { this.labtestGroupValues = labtestGroupValues; }



  
  public String getChkLabTestGroup() { return this.chkLabTestGroup; }



  
  public void setChkLabTestGroup(String chkLabTestGroup) { this.chkLabTestGroup = chkLabTestGroup; }



  
  public String getSearchTestGroup() { return this.searchTestGroup; }



  
  public void setSearchTestGroup(String searchTestGroup) { this.searchTestGroup = searchTestGroup; }















































  
  public String getPatCatCode() { return this.patCatCode; }



  
  public void setPatCatCode(String patCatCode) { this.patCatCode = patCatCode; }



  
  public String getAppointmentRefNo() { return this.appointmentRefNo; }



  
  public void setAppointmentRefNo(String appointmentRefNo) { this.appointmentRefNo = appointmentRefNo; }



  
  public String getAdvisedBy() { return this.advisedBy; }



  
  public void setAdvisedBy(String advisedBy) { this.advisedBy = advisedBy; }



  
  public String getAdvisedByDoctorName() { return this.advisedByDoctorName; }



  
  public void setAdvisedByDoctorName(String advisedByDoctorName) { this.advisedByDoctorName = advisedByDoctorName; }



  
  public String getAdvisedByName() { return this.advisedByName; }



  
  public void setAdvisedByName(String advisedByName) { this.advisedByName = advisedByName; }



  
  public String getFinalMandCodeValuesOnBookmark() { return this.finalMandCodeValuesOnBookmark; }




  
  public void setFinalMandCodeValuesOnBookmark(String finalMandCodeValuesOnBookmark) { this.finalMandCodeValuesOnBookmark = finalMandCodeValuesOnBookmark; }



  
  public String getFinalMandCode() { return this.finalMandCode; }



  
  public void setFinalMandCode(String finalMandCode) { this.finalMandCode = finalMandCode; }



  
  public String getMandCode() { return this.mandCode; }



  
  public void setMandCode(String mandCode) { this.mandCode = mandCode; }



  
  public void reset(ActionMapping mapping, HttpServletRequest request) {
    this.currentPage = 1;
    this.searchLabName = "";
    this.searchTestName = "";
    this.searchTestGroupName = "";
    this.bookMarkCode = "";
    this.sampleInfo = null;
    this.isBookMark = "B";
    this.tstValArr = "";
    this.tstGrpArr = "";
    this.isTestGroup = "0";
  }


  
  public String getPatCRNo() { return this.patCRNo; }

  
  public void setPatCRNo(String patCRNo) { this.patCRNo = patCRNo; }

  
  public String getSelectedEpisode() { return this.selectedEpisode; }

  
  public void setSelectedEpisode(String selectedEpisode) { this.selectedEpisode = selectedEpisode; }

  
  public String getSelectedEpisodeRadio() { return this.selectedEpisodeRadio; }

  
  public void setSelectedEpisodeRadio(String selectedEpisodeRadio) { this.selectedEpisodeRadio = selectedEpisodeRadio; }

  
  public String getHmode() { return this.hmode; }

  
  public void setHmode(String hmode) { this.hmode = hmode; }

  
  public String getCurrentPageNo() { return this.currentPageNo; }

  
  public void setCurrentPageNo(String currentPageNo) { this.currentPageNo = currentPageNo; }

  
  public String getCurrentblock() { return this.currentblock; }

  
  public void setCurrentblock(String currentblock) { this.currentblock = currentblock; }

  
  public String getSampleCollectionNeeded() { return this.sampleCollectionNeeded; }

  
  public void setSampleCollectionNeeded(String sampleCollectionNeeded) { this.sampleCollectionNeeded = sampleCollectionNeeded; }

  
  public String getPageString() { return this.pageString; }

  
  public void setPageString(String pageString) { this.pageString = pageString; }

  
  public String getSeatId() { return this.seatId; }

  
  public void setSeatId(String seatId) { this.seatId = seatId; }

  
  public String getSampleCollectionForwardinRequired() { return this.sampleCollectionForwardinRequired; }


  
  public void setSampleCollectionForwardinRequired(String sampleCollectionForwardinRequired) { this.sampleCollectionForwardinRequired = sampleCollectionForwardinRequired; }

  
  public static String getRecordperpage() { return "10"; }

  
  public static String getPagesperblock() { return "10"; }

  
  public String getPatCrNo() { return this.patCrNo; }

  
  public void setPatCrNo(String patCrNo) { this.patCrNo = patCrNo; }

  
  public String getPatStatus() { return this.patStatus; }

  
  public void setPatStatus(String patStatus) { this.patStatus = patStatus; }

  
  public String getSearchLabName() { return this.searchLabName; }

  
  public void setSearchLabName(String searchLabName) { this.searchLabName = searchLabName; }

  
  public String getSearchTestName() { return this.searchTestName; }

  
  public void setSearchTestName(String searchTestName) { this.searchTestName = searchTestName; }

  
  public int getCurrentPage() { return this.currentPage; }

  
  public void setCurrentPage(int currentPage) { this.currentPage = currentPage; }

  
  public int getStartIndex() { return this.startIndex; }

  
  public void setStartIndex(int startIndex) { this.startIndex = startIndex; }

  
  public int getEndIndex() { return this.endIndex; }

  
  public void setEndIndex(int endIndex) { this.endIndex = endIndex; }



  
  public String[] getChkLabTest() { return this.chkLabTest; }



  
  public void setChkLabTest(String[] chkLabTest) { this.chkLabTest = chkLabTest; }



  
  public String getLabTestCodeArray() { return this.labTestCodeArray; }



  
  public void setLabTestCodeArray(String labTestCodeArray) { this.labTestCodeArray = labTestCodeArray; }



  
  public String getNumberOfRow() { return this.numberOfRow; }



  
  public void setNumberOfRow(String numberOfRow) { this.numberOfRow = numberOfRow; }



  
  public String[] getLabCode() { return this.labCode; }



  
  public void setLabCode(String[] labCode) { this.labCode = labCode; }



  
  public String[] getTestCode() { return this.testCode; }



  
  public void setTestCode(String[] testCode) { this.testCode = testCode; }



  
  public String[] getSampleInfo() { return this.sampleInfo; }



  
  public void setSampleInfo(String[] sampleInfo) { this.sampleInfo = sampleInfo; }



  
  public String[] getMandatoryInfo() { return this.mandatoryInfo; }



  
  public void setMandatoryInfo(String[] mandatoryInfo) { this.mandatoryInfo = mandatoryInfo; }



  
  public String getBookMarkCode() { return this.bookMarkCode; }



  
  public void setBookMarkCode(String bookMarkCode) { this.bookMarkCode = bookMarkCode; }



  
  public String getIsBookMark() { return this.isBookMark; }



  
  public void setIsBookMark(String isBookMark) { this.isBookMark = isBookMark; }



  
  public String getTmpLabCode() { return this.tmpLabCode; }



  
  public void setTmpLabCode(String tmpLabCode) { this.tmpLabCode = tmpLabCode; }



  
  public String getTmpTestCode() { return this.tmpTestCode; }



  
  public void setTmpTestCode(String tmpTestCode) { this.tmpTestCode = tmpTestCode; }



  
  public String getTmpSampleCode() { return this.tmpSampleCode; }



  
  public void setTmpSampleCode(String tmpSampleCode) { this.tmpSampleCode = tmpSampleCode; }



  
  public String getTmpPriority() { return this.tmpPriority; }



  
  public void setTmpPriority(String tmpPriority) { this.tmpPriority = tmpPriority; }




  
  public String getTempPatCRNo() { return this.tempPatCRNo; }



  
  public void setTempPatCRNo(String tempPatCRNo) { this.tempPatCRNo = tempPatCRNo; }







  
  public String getTstValArr() { return this.tstValArr; }



  
  public void setTstValArr(String tstValArr) { this.tstValArr = tstValArr; }



  
  public String getIsTestGroup() { return this.isTestGroup; }



  
  public void setIsTestGroup(String isTestGroup) { this.isTestGroup = isTestGroup; }



  
  public String getSearchTestGroupName() { return this.searchTestGroupName; }



  
  public void setSearchTestGroupName(String searchTestGroupName) { this.searchTestGroupName = searchTestGroupName; }



  
  public String getTstGrpArr() { return this.tstGrpArr; }



  
  public void setTstGrpArr(String tstGrpArr) { this.tstGrpArr = tstGrpArr; }



  
  public String getAppointmentDate() { return this.appointmentDate; }



  
  public void setAppointmentDate(String appointmentDate) { this.appointmentDate = appointmentDate; }



  
  public String getAppointmentTime() { return this.appointmentTime; }



  
  public void setAppointmentTime(String appointmentTime) { this.appointmentTime = appointmentTime; }



  
  public String getFromDate() { return this.fromDate; }



  
  public void setFromDate(String fromDate) { this.fromDate = fromDate; }



  
  public String getToDate() { return this.toDate; }



  
  public void setToDate(String toDate) { this.toDate = toDate; }



  
  public String getTmpCrNo() { return this.tmpCrNo; }



  
  public void setTmpCrNo(String tmpCrNo) { this.tmpCrNo = tmpCrNo; }



  
  public String getPrvTestDtl() { return this.prvTestDtl; }



  
  public void setPrvTestDtl(String prvTestDtl) { this.prvTestDtl = prvTestDtl; }



  
  public String getAptNO() { return this.aptNO; }



  
  public void setAptNO(String aptNO) { this.aptNO = aptNO; }



  
  public String getPukNo() { return this.pukNo; }



  
  public void setPukNo(String pukNo) { this.pukNo = pukNo; }



  
  public String getPatFirstName() { return this.patFirstName; }



  
  public void setPatFirstName(String patFirstName) { this.patFirstName = patFirstName; }



  
  public String getPatMiddleName() { return this.PatMiddleName; }



  
  public void setPatMiddleName(String patMiddleName) { this.PatMiddleName = patMiddleName; }



  
  public String getPatLastNAme() { return this.patLastNAme; }



  
  public void setPatLastNAme(String patLastNAme) { this.patLastNAme = patLastNAme; }



  
  public String getGender() { return this.gender; }



  
  public void setGender(String gender) { this.gender = gender; }



  
  public String getAptDate() { return this.aptDate; }



  
  public void setAptDate(String aptDate) { this.aptDate = aptDate; }



  
  public String getSlotTime() { return this.slotTime; }



  
  public void setSlotTime(String slotTime) { this.slotTime = slotTime; }



  
  public String getAge() { return this.age; }



  
  public void setAge(String age) { this.age = age; }



  
  public String getAptStatus() { return this.aptStatus; }



  
  public void setAptStatus(String aptStatus) { this.aptStatus = aptStatus; }



  
  public String getAptTestCode() { return this.aptTestCode; }



  
  public void setAptTestCode(String aptTestCode) { this.aptTestCode = aptTestCode; }



  
  public String getOfflineAppoitMentDate() { return this.offlineAppoitMentDate; }



  
  public void setOfflineAppoitMentDate(String offlineAppoitMentDate) { this.offlineAppoitMentDate = offlineAppoitMentDate; }



  
  public String getOfflineAppoitmentDtl() { return this.offlineAppoitmentDtl; }



  
  public void setOfflineAppoitmentDtl(String offlineAppoitmentDtl) { this.offlineAppoitmentDtl = offlineAppoitmentDtl; }



  
  public String getHideSaveButton() { return this.hideSaveButton; }



  
  public void setHideSaveButton(String hideSaveButton) { this.hideSaveButton = hideSaveButton; }



  
  public String getMandCombo() { return this.mandCombo; }



  
  public void setMandCombo(String mandCombo) { this.mandCombo = mandCombo; }



  
  public String getMandComboTextBoxComboNames() { return this.mandComboTextBoxComboNames; }



  
  public void setMandComboTextBoxComboNames(String mandComboTextBoxComboNames) { this.mandComboTextBoxComboNames = mandComboTextBoxComboNames; }



  
  public String getMandComboTextBoxComboNamesOnPage() { return this.mandComboTextBoxComboNamesOnPage; }




  
  public void setMandComboTextBoxComboNamesOnPage(String mandComboTextBoxComboNamesOnPage) { this.mandComboTextBoxComboNamesOnPage = mandComboTextBoxComboNamesOnPage; }



  
  public String getFinalMandValues() { return this.finalMandValues; }



  
  public void setFinalMandValues(String finalMandValues) { this.finalMandValues = finalMandValues; }



  
  public String getAccountNo() { return this.accountNo; }



  
  public void setAccountNo(String accountNo) { this.accountNo = accountNo; }



  
  public String getPatStatusCode() { return this.patStatusCode; }



  
  public void setPatStatusCode(String patStatusCode) { this.patStatusCode = patStatusCode; }



  
  public String getAdmissionDate() { return this.admissionDate; }



  
  public void setAdmissionDate(String admissionDate) { this.admissionDate = admissionDate; }



  
  public String getFileName() { return this.fileName; }



  
  public void setFileName(String fileName) { this.fileName = fileName; }



  
  public String getDeskType() { return this.deskType; }



  
  public void setDeskType(String deskType) { this.deskType = deskType; }



  
  public String getSelectedCheckbox() { return this.selectedCheckbox; }



  
  public void setSelectedCheckbox(String selectedCheckbox) { this.selectedCheckbox = selectedCheckbox; }



  
  public String getFromwhichall() { return this.fromwhichall; }



  
  public void setFromwhichall(String fromwhichall) { this.fromwhichall = fromwhichall; }



  
  public String getCallingmode() { return this.callingmode; }



  
  public void setCallingmode(String callingmode) { this.callingmode = callingmode; }
}
