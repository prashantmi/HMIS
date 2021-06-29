package new_investigation.transactions.controller.fb;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class InvestigationRaisingDtlXrayFB
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
  private String selectedCheckbox;
  private String[] allotAppointment;
  private String dupPatCrNo;
  private String priorityAll;
  private String visitReason;
  private String userGroupCodeWise;
  private String reqdSampleName;
  private String mobileNo;
  private String patName;
  private String advice;
  private String requisitionFormData;
  private String isrequisitionFormNeeded;
  private String reqSampleShortName;
  private String testCodee;
  private String labCodee;
  private String isAddendum;
  private String samplecodestr;
  private String newlabtestcodearray;
  private String requisitionNo;
  private String cashCrNo;
  private String userTestCode;
  private String testSearchKeyword;
  private String isentry;
  private String requisitionDetailsforResultEntry;
  private String deletedtestdataviaresultentry;
  private String labwisetestteriff;
  private String selectlabid;
  private String callingdesk;
  private String patAdmNo;
  private String issearchtestnamewise;
  private String searchTestNamelabwise;
  private String flagDesk;
  private String isamountfill;
  private String isamountsufficientflag;
  private String groupraisedalready;
  private String usergrpcode;
  private String pidd;
  private String piddata;
  private String piddmodalopen;
  private String ispidexist;
  private String labbasedapppointment;
  private String labName;
  private String aptTyp;
  private String tmpsite;
  private String patvisittypecode_appointment;
  private String casualitydesk;
  private String followup;
  private String selectedEpisodedetails;
  private String testlabcode;
  private String adivcedbycode;
  private String adivcedbyname;
  private String cheifcomplaints;
  private String prioriotytype;
  private String[] selectedtestdataatrray;
  private String[] selectedtestviews;
  private String[] selectedsite;
  private String shwdiv;
  private String iscalligprevreq;
  private String totalviewcount;
  private String totalreqviewcount;
  private String totalreqviewtyp;
  private String prevreqfromwhichcall;
  private String tstGrpArr;
  private String appointmentDate;
  private String appointmentTime;
  
  public String getReqdSampleName() { return this.reqdSampleName; }



  
  public void setReqdSampleName(String reqdSampleName) { this.reqdSampleName = reqdSampleName; }



  
  public String getUserGroupCodeWise() { return this.userGroupCodeWise; }



  
  public void setUserGroupCodeWise(String userGroupCodeWise) { this.userGroupCodeWise = userGroupCodeWise; }



  
  public String getDupPatCrNo() { return this.dupPatCrNo; }



  
  public void setDupPatCrNo(String dupPatCrNo) { this.dupPatCrNo = dupPatCrNo; }



  
  public String[] getAllotAppointment() { return this.allotAppointment; }



  
  public void setAllotAppointment(String[] allotAppointment) { this.allotAppointment = allotAppointment; }



  
  public String getSelectedCheckbox() { return this.selectedCheckbox; }



  
  public void setSelectedCheckbox(String selectedCheckbox) { this.selectedCheckbox = selectedCheckbox; }



  
  public String getLengendStatus() { return this.lengendStatus; }



  
  public void setLengendStatus(String lengendStatus) { this.lengendStatus = lengendStatus; }



  
  public String getOfflineAptDtl() { return this.offlineAptDtl; }



  
  public void setOfflineAptDtl(String offlineAptDtl) { this.offlineAptDtl = offlineAptDtl; }



  
  public String getHidAptNo() { return this.hidAptNo; }



  
  public void setHidAptNo(String hidAptNo) { this.hidAptNo = hidAptNo; }



  
  public String getAppoitmentNo() { return this.appoitmentNo; }



  
  public void setAppoitmentNo(String appoitmentNo) { this.appoitmentNo = appoitmentNo; }



  
  public String getDupTestCode() { return this.dupTestCode; }



  
  public void setDupTestCode(String dupTestCode) { this.dupTestCode = dupTestCode; }



  
  public String getDupLabCode() { return this.dupLabCode; }



  
  public void setDupLabCode(String dupLabCode) { this.dupLabCode = dupLabCode; }



  
  public String getDelTestCode() { return this.delTestCode; }



  
  public void setDelTestCode(String delTestCode) { this.delTestCode = delTestCode; }



  
  public String getDelLabCode() { return this.delLabCode; }



  
  public void setDelLabCode(String delLabCode) { this.delLabCode = delLabCode; }



  
  public String getTestCodeLabValue() { return this.testCodeLabValue; }



  
  public void setTestCodeLabValue(String testCodeLabValue) { this.testCodeLabValue = testCodeLabValue; }



  
  public String getTestCodeWise() { return this.testCodeWise; }



  
  public void setTestCodeWise(String testCodeWise) { this.testCodeWise = testCodeWise; }



  
  public String getTestLabTestCodeWise() { return this.testLabTestCodeWise; }



  
  public void setTestLabTestCodeWise(String testLabTestCodeWise) { this.testLabTestCodeWise = testLabTestCodeWise; }



  
  public String getTstOrTestGroupValue() { return this.tstOrTestGroupValue; }



  
  public void setTstOrTestGroupValue(String tstOrTestGroupValue) { this.tstOrTestGroupValue = tstOrTestGroupValue; }



  
  public String getLabtestGroupValues() { return this.labtestGroupValues; }



  
  public void setLabtestGroupValues(String labtestGroupValues) { this.labtestGroupValues = labtestGroupValues; }



  
  public String getChkLabTestGroup() { return this.chkLabTestGroup; }



  
  public void setChkLabTestGroup(String chkLabTestGroup) { this.chkLabTestGroup = chkLabTestGroup; }



  
  public String getSearchTestGroup() { return this.searchTestGroup; }



  
  public void setSearchTestGroup(String searchTestGroup) { this.searchTestGroup = searchTestGroup; }







  
  private String fromDate = "";
  private String toDate = "";
  
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
  private String sysDate;
  private String[] labTestAptDate;
  private String[] labTestAptTime;
  private String[] labTestAptRefNo;
  private String aptLabCode;
  
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



  
  public String getSysDate() { return this.sysDate; }



  
  public void setSysDate(String sysDate) { this.sysDate = sysDate; }



  
  public String[] getLabTestAptDate() { return this.labTestAptDate; }



  
  public void setLabTestAptDate(String[] labTestAptDate) { this.labTestAptDate = labTestAptDate; }



  
  public String[] getLabTestAptTime() { return this.labTestAptTime; }



  
  public void setLabTestAptTime(String[] labTestAptTime) { this.labTestAptTime = labTestAptTime; }



  
  public String[] getLabTestAptRefNo() { return this.labTestAptRefNo; }



  
  public void setLabTestAptRefNo(String[] labTestAptRefNo) { this.labTestAptRefNo = labTestAptRefNo; }



  
  public String getAptLabCode() { return this.aptLabCode; }



  
  public void setAptLabCode(String aptLabCode) { this.aptLabCode = aptLabCode; }



  
  public String getPriorityAll() { return this.priorityAll; }



  
  public void setPriorityAll(String priorityAll) { this.priorityAll = priorityAll; }



  
  public String getVisitReason() { return this.visitReason; }



  
  public void setVisitReason(String visitReason) { this.visitReason = visitReason; }



  
  public String getMobileNo() { return this.mobileNo; }



  
  public void setMobileNo(String mobileNo) { this.mobileNo = mobileNo; }



  
  public String getPatName() { return this.patName; }



  
  public void setPatName(String patName) { this.patName = patName; }



  
  public String getAdvice() { return this.advice; }



  
  public void setAdvice(String advice) { this.advice = advice; }



  
  public String getRequisitionFormData() { return this.requisitionFormData; }



  
  public void setRequisitionFormData(String requisitionFormData) { this.requisitionFormData = requisitionFormData; }



  
  public String getIsrequisitionFormNeeded() { return this.isrequisitionFormNeeded; }



  
  public void setIsrequisitionFormNeeded(String isrequisitionFormNeeded) { this.isrequisitionFormNeeded = isrequisitionFormNeeded; }



  
  public String getReqSampleShortName() { return this.reqSampleShortName; }



  
  public void setReqSampleShortName(String reqSampleShortName) { this.reqSampleShortName = reqSampleShortName; }



  
  public String getTestCodee() { return this.testCodee; }



  
  public void setTestCodee(String testCodee) { this.testCodee = testCodee; }



  
  public String getLabCodee() { return this.labCodee; }



  
  public void setLabCodee(String labCodee) { this.labCodee = labCodee; }



  
  public String getIsAddendum() { return this.isAddendum; }



  
  public void setIsAddendum(String isAddendum) { this.isAddendum = isAddendum; }



  
  public String getSamplecodestr() { return this.samplecodestr; }



  
  public void setSamplecodestr(String samplecodestr) { this.samplecodestr = samplecodestr; }



  
  public String getNewlabtestcodearray() { return this.newlabtestcodearray; }



  
  public void setNewlabtestcodearray(String newlabtestcodearray) { this.newlabtestcodearray = newlabtestcodearray; }



  
  public String getRequisitionNo() { return this.requisitionNo; }



  
  public void setRequisitionNo(String requisitionNo) { this.requisitionNo = requisitionNo; }



  
  public String getCashCrNo() { return this.cashCrNo; }



  
  public void setCashCrNo(String cashCrNo) { this.cashCrNo = cashCrNo; }



  
  public String getUserTestCode() { return this.userTestCode; }



  
  public void setUserTestCode(String userTestCode) { this.userTestCode = userTestCode; }



  
  public String getTestSearchKeyword() { return this.testSearchKeyword; }



  
  public void setTestSearchKeyword(String testSearchKeyword) { this.testSearchKeyword = testSearchKeyword; }



  
  public String getIsentry() { return this.isentry; }



  
  public void setIsentry(String isentry) { this.isentry = isentry; }



  
  public String getRequisitionDetailsforResultEntry() { return this.requisitionDetailsforResultEntry; }




  
  public void setRequisitionDetailsforResultEntry(String requisitionDetailsforResultEntry) { this.requisitionDetailsforResultEntry = requisitionDetailsforResultEntry; }



  
  public String getDeletedtestdataviaresultentry() { return this.deletedtestdataviaresultentry; }




  
  public void setDeletedtestdataviaresultentry(String deletedtestdataviaresultentry) { this.deletedtestdataviaresultentry = deletedtestdataviaresultentry; }



  
  public String getLabwisetestteriff() { return this.labwisetestteriff; }



  
  public void setLabwisetestteriff(String labwisetestteriff) { this.labwisetestteriff = labwisetestteriff; }



  
  public String getSelectlabid() { return this.selectlabid; }



  
  public void setSelectlabid(String selectlabid) { this.selectlabid = selectlabid; }



  
  public String getCallingdesk() { return this.callingdesk; }



  
  public void setCallingdesk(String callingdesk) { this.callingdesk = callingdesk; }



  
  public String getPatAdmNo() { return this.patAdmNo; }



  
  public void setPatAdmNo(String patAdmNo) { this.patAdmNo = patAdmNo; }



  
  public String getIssearchtestnamewise() { return this.issearchtestnamewise; }



  
  public void setIssearchtestnamewise(String issearchtestnamewise) { this.issearchtestnamewise = issearchtestnamewise; }



  
  public String getSearchTestNamelabwise() { return this.searchTestNamelabwise; }



  
  public void setSearchTestNamelabwise(String searchTestNamelabwise) { this.searchTestNamelabwise = searchTestNamelabwise; }



  
  public String getFlagDesk() { return this.flagDesk; }



  
  public void setFlagDesk(String flagDesk) { this.flagDesk = flagDesk; }



  
  public String getIsamountfill() { return this.isamountfill; }



  
  public void setIsamountfill(String isamountfill) { this.isamountfill = isamountfill; }



  
  public String getIsamountsufficientflag() { return this.isamountsufficientflag; }



  
  public void setIsamountsufficientflag(String isamountsufficientflag) { this.isamountsufficientflag = isamountsufficientflag; }



  
  public String getGroupraisedalready() { return this.groupraisedalready; }



  
  public void setGroupraisedalready(String groupraisedalready) { this.groupraisedalready = groupraisedalready; }



  
  public String getUsergrpcode() { return this.usergrpcode; }



  
  public void setUsergrpcode(String usergrpcode) { this.usergrpcode = usergrpcode; }



  
  public String getPidd() { return this.pidd; }



  
  public void setPidd(String pidd) { this.pidd = pidd; }



  
  public String getPiddata() { return this.piddata; }



  
  public void setPiddata(String piddata) { this.piddata = piddata; }



  
  public String getPiddmodalopen() { return this.piddmodalopen; }



  
  public void setPiddmodalopen(String piddmodalopen) { this.piddmodalopen = piddmodalopen; }



  
  public String getIspidexist() { return this.ispidexist; }



  
  public void setIspidexist(String ispidexist) { this.ispidexist = ispidexist; }



  
  public String getLabbasedapppointment() { return this.labbasedapppointment; }



  
  public void setLabbasedapppointment(String labbasedapppointment) { this.labbasedapppointment = labbasedapppointment; }



  
  public String getLabName() { return this.labName; }



  
  public void setLabName(String labName) { this.labName = labName; }



  
  public String getAptTyp() { return this.aptTyp; }



  
  public void setAptTyp(String aptTyp) { this.aptTyp = aptTyp; }



  
  public String getTmpsite() { return this.tmpsite; }



  
  public void setTmpsite(String tmpsite) { this.tmpsite = tmpsite; }



  
  public String getPatvisittypecode_appointment() { return this.patvisittypecode_appointment; }



  
  public void setPatvisittypecode_appointment(String patvisittypecode_appointment) { this.patvisittypecode_appointment = patvisittypecode_appointment; }



  
  public String getCasualitydesk() { return this.casualitydesk; }



  
  public void setCasualitydesk(String casualitydesk) { this.casualitydesk = casualitydesk; }



  
  public String getFollowup() { return this.followup; }



  
  public void setFollowup(String followup) { this.followup = followup; }



  
  public String getSelectedEpisodedetails() { return this.selectedEpisodedetails; }



  
  public void setSelectedEpisodedetails(String selectedEpisodedetails) { this.selectedEpisodedetails = selectedEpisodedetails; }



  
  public String getTestlabcode() { return this.testlabcode; }



  
  public void setTestlabcode(String testlabcode) { this.testlabcode = testlabcode; }



  
  public String getAdivcedbycode() { return this.adivcedbycode; }



  
  public void setAdivcedbycode(String adivcedbycode) { this.adivcedbycode = adivcedbycode; }



  
  public String getAdivcedbyname() { return this.adivcedbyname; }



  
  public void setAdivcedbyname(String adivcedbyname) { this.adivcedbyname = adivcedbyname; }



  
  public String getCheifcomplaints() { return this.cheifcomplaints; }



  
  public void setCheifcomplaints(String cheifcomplaints) { this.cheifcomplaints = cheifcomplaints; }



  
  public String getPrioriotytype() { return this.prioriotytype; }



  
  public void setPrioriotytype(String prioriotytype) { this.prioriotytype = prioriotytype; }



  
  public String[] getSelectedtestdataatrray() { return this.selectedtestdataatrray; }



  
  public void setSelectedtestdataatrray(String[] selectedtestdataatrray) { this.selectedtestdataatrray = selectedtestdataatrray; }



  
  public String[] getSelectedtestviews() { return this.selectedtestviews; }



  
  public void setSelectedtestviews(String[] selectedtestviews) { this.selectedtestviews = selectedtestviews; }



  
  public String[] getSelectedsite() { return this.selectedsite; }



  
  public void setSelectedsite(String[] selectedsite) { this.selectedsite = selectedsite; }



  
  public String getShwdiv() { return this.shwdiv; }



  
  public void setShwdiv(String shwdiv) { this.shwdiv = shwdiv; }



  
  public String getIscalligprevreq() { return this.iscalligprevreq; }



  
  public void setIscalligprevreq(String iscalligprevreq) { this.iscalligprevreq = iscalligprevreq; }



  
  public String getTotalviewcount() { return this.totalviewcount; }



  
  public void setTotalviewcount(String totalviewcount) { this.totalviewcount = totalviewcount; }



  
  public String getTotalreqviewcount() { return this.totalreqviewcount; }



  
  public void setTotalreqviewcount(String totalreqviewcount) { this.totalreqviewcount = totalreqviewcount; }



  
  public String getTotalreqviewtyp() { return this.totalreqviewtyp; }



  
  public void setTotalreqviewtyp(String totalreqviewtyp) { this.totalreqviewtyp = totalreqviewtyp; }



  
  public String getPrevreqfromwhichcall() { return this.prevreqfromwhichcall; }



  
  public void setPrevreqfromwhichcall(String prevreqfromwhichcall) { this.prevreqfromwhichcall = prevreqfromwhichcall; }
}
