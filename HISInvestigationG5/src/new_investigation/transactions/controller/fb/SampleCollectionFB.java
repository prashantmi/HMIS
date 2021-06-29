package new_investigation.transactions.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SampleCollectionFB extends ActionForm
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
	public String sampleAreaName;
	public String sampleAreaCode;
	public static final String recordPerPage="10";
	public static final String pagesPerBlock="10";
	public String isSearchclicked;
	public String issearchviacrno;
	public String getIsSearchclicked() {
		return isSearchclicked;
	}





	public void setIsSearchclicked(String isSearchclicked) {
		this.isSearchclicked = isSearchclicked;
	}





	private String patStatus;
	
	private String searchLabName;
	private String searchTestName;
	
	private int currentPage;
	private int startIndex;
	private int endIndex;
	private int startIndex1;
	private int endIndex1;
	private String allSelectLab;
	private String instructionPat;
	private String patCrNo;
	private String[] defaultmachineCode;
	private String modebarcode;
	private String statuschange;
	
	private String patName;

	private String fromDateHidden="";
	private String toDateHidden="";
	private String isRepeatSample;
	
	private String  duplicateBarcodeGeneration;
	
	

	private String reportPrintBy;
	private String reportURL;

	private String ftpserver;
	private String pdfName;

	private String selectedPDFName;

	private String testParamRefRange;
	private String testParamName ;
	private String testParamCode ;
	private String testParamValue ;
	private String testParamOutOfBound ;
	private String responseMgs ;
	private String issetmsg="0" ;

	
	public String getResponseMgs() {
		return responseMgs;
	}





	public void setResponseMgs(String responseMgs) {
		this.responseMgs = responseMgs;
	}





	public String getIsRepeatSample() {
		return isRepeatSample;
	}





	public void setIsRepeatSample(String isRepeatSample) {
		this.isRepeatSample = isRepeatSample;
	}





	public String getFromDateHidden() {
		return fromDateHidden;
	}





	public void setFromDateHidden(String fromDateHidden) {
		this.fromDateHidden = fromDateHidden;
	}





	public String getToDateHidden() {
		return toDateHidden;
	}





	public void setToDateHidden(String toDateHidden) {
		this.toDateHidden = toDateHidden;
	}





	public String getAllSelectLab() {
		return allSelectLab;
	}





	public void setAllSelectLab(String allSelectLab) {
		this.allSelectLab = allSelectLab;
	}





	private String labTestCodeArray;
	
	private String[] chkSamplePatient;
	
	
	
	
	private String numberOfRow;
	public String selectedCheckbox;
	private String fromDate;
	private String toDate;
	private String sysDate;
	private String billDetail;
	private String requisitionDNo;
	
	private String labName;
	private String testName;
	private String isConfidential;
	private String priorityCode;
	private String priority;
	private String requisitionDate;
	
	private String reqDtlStatus;
	
	private String sampleCode;
	private String sampleName;
	private String requisitionNo;
	private String groupCode;
	private String groupType;
	private String[] sampleQnty;
	private String[] defaultContainerCode;
	
	private String[] defaultUOMCode;
	private String defaultSampelCode;
	private String sampleNoConfiguration;
	
	private String reqHeaderStatus;
	
	private String episodeCode;
	
	private String reqType;
	
	private String patAdmNo;
	
	private String patAddress;
	
	private String patWardCode;
	
	private String patMobile;
	private String patEmail;
	
	private String patRoomCode;
	
	private String patBedCode;
	
	private String patMlcNo;
	
	private String patDeptName;
	
	private String patDeptCode;
	
	private String patDeptUnitCode;
	
	
	private String showStatus;
	
	private String[] sampleNo;
	
	private String strSampleNo;
	
	private String isSampleAreaSelected;
	
	private String isCancel;
	private String tempPatCRNo;
	private String labCode;
	private String testCode;
	private String patType;
	private String tempSampleNo;
	private String saveConfirmFlag;
	private String barCodeGenSize; 
	
	private String wardCode; 
	
	private String flagforipddesk; 
	
	private String billNo;
	private String crNo;
	private String crNumber;
	private String dataFromArchival;
	private String miscDate="";
	private String searchType;
	private String forTestOrGroupOrAll;
	
	private String phoneNo;
	private String emailId;
	private String latestBillNo;
	private String memberSince;
	private String totalPaymentsDone;
	private String pendingPayments;
	private String address;
	private String note;
	/* OPD Pat Data */
	private String isUnknown;
	private String isDead;
	private String isMlc;
	private String patFirstName;
	private String patMiddleName;
	private String patLastName;
	private String patGuardianName;
	private String patStatusCode;
	private String patCategoryCode;
	private String patAge;
	private String patHusbandName;
	private String patGenderCode;
	private String patCategory;
	private String patDOB;
	private String isActualDob;
	private String patGender;

	private String patMobileNo;

	private String isCatExpired;
	private String patEmailId;
	/* IPD Specific */
	private String admissionDate;

	private String patVisitNo;
	private String patEpisodeCode;
	private String admittedDepartmentCode;
	private String patAdmissionNo;
	private String patDeptUnit;
	private String admittedDepartmentName;

	private String patWardName;
	private String patRoomNo;
	private String patRoomName;
	private String bedCode;
	private String bedName;
	private String hospitalCode;
	private String consultantName;

	private String diagnosis;
	private String patAccNo;

	private String age_gender;
	private String wardbed_no;
	private String deptCode;
	private String deptName;
	private String wardName;

	private String advisedByDept;
	private String requisitionStatus;
	private String requisitionStatusCode;
	private String turnAroundTime;
	private String testRate;
	private String billedUnbilled;
	private String testNote;
	
	private String requisitionBy;
	private String advisedByDoc;
	private String appointmentDate;
	private String isGroup;

	private String billDate;
	private String testRateDetail;

	private String collectionArea;
	private String sampleCollDate;
	private String sampleCollBy;

	private String packingListNo;
	private String packingListDateTime;
	private String packingListBy;

	private String labNo;
	private String sampleAccepDate;
	private String sampleAccepBy;
	private String sampleAccepMode;
	private String machineName;

	private String accessionNo;
	private String patientAccepDate;
	private String patientAccepBy;
	private String patientAccepMode;

	private String sampleRejecDate;
	private String sampleRejecBy;
	private String sampleRejecReason;

	private String patientRejecDate;
	private String patientRejecBy;
	private String patientRejecReason;

	private String resultEntryDate;
	private String resultEntryBy;
	private String resultEntryParam;
	private String testParameterDetails;
	private String resultValDate;
	private String resultValBy;
	private String resultReValDate;
	private String resultReValidBy;

	private String reportGenerationDate;
	private String reportPrintDate;
	
	
	
	public String getReportPrintBy() {
		return reportPrintBy;
	}





	public void setReportPrintBy(String reportPrintBy) {
		this.reportPrintBy = reportPrintBy;
	}





	public String getReportURL() {
		return reportURL;
	}





	public void setReportURL(String reportURL) {
		this.reportURL = reportURL;
	}





	public String getFtpserver() {
		return ftpserver;
	}





	public void setFtpserver(String ftpserver) {
		this.ftpserver = ftpserver;
	}





	public String getPdfName() {
		return pdfName;
	}





	public void setPdfName(String pdfName) {
		this.pdfName = pdfName;
	}





	public String getSelectedPDFName() {
		return selectedPDFName;
	}





	public void setSelectedPDFName(String selectedPDFName) {
		this.selectedPDFName = selectedPDFName;
	}





	public String getTestParamRefRange() {
		return testParamRefRange;
	}





	public void setTestParamRefRange(String testParamRefRange) {
		this.testParamRefRange = testParamRefRange;
	}





	public String getTestParamName() {
		return testParamName;
	}





	public void setTestParamName(String testParamName) {
		this.testParamName = testParamName;
	}





	public String getTestParamCode() {
		return testParamCode;
	}





	public void setTestParamCode(String testParamCode) {
		this.testParamCode = testParamCode;
	}





	public String getTestParamValue() {
		return testParamValue;
	}





	public void setTestParamValue(String testParamValue) {
		this.testParamValue = testParamValue;
	}





	public String getTestParamOutOfBound() {
		return testParamOutOfBound;
	}





	public void setTestParamOutOfBound(String testParamOutOfBound) {
		this.testParamOutOfBound = testParamOutOfBound;
	}





	public String getBillNo() {
		return billNo;
	}





	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}





	public String getCrNo() {
		return crNo;
	}





	public void setCrNo(String crNo) {
		this.crNo = crNo;
	}





	public String getCrNumber() {
		return crNumber;
	}





	public void setCrNumber(String crNumber) {
		this.crNumber = crNumber;
	}





	public String getDataFromArchival() {
		return dataFromArchival;
	}





	public void setDataFromArchival(String dataFromArchival) {
		this.dataFromArchival = dataFromArchival;
	}





	public String getMiscDate() {
		return miscDate;
	}





	public void setMiscDate(String miscDate) {
		this.miscDate = miscDate;
	}





	public String getSearchType() {
		return searchType;
	}





	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}





	public String getForTestOrGroupOrAll() {
		return forTestOrGroupOrAll;
	}





	public void setForTestOrGroupOrAll(String forTestOrGroupOrAll) {
		this.forTestOrGroupOrAll = forTestOrGroupOrAll;
	}





	public String getPhoneNo() {
		return phoneNo;
	}





	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}





	public String getEmailId() {
		return emailId;
	}





	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}





	public String getLatestBillNo() {
		return latestBillNo;
	}





	public void setLatestBillNo(String latestBillNo) {
		this.latestBillNo = latestBillNo;
	}





	public String getMemberSince() {
		return memberSince;
	}





	public void setMemberSince(String memberSince) {
		this.memberSince = memberSince;
	}





	public String getTotalPaymentsDone() {
		return totalPaymentsDone;
	}





	public void setTotalPaymentsDone(String totalPaymentsDone) {
		this.totalPaymentsDone = totalPaymentsDone;
	}





	public String getPendingPayments() {
		return pendingPayments;
	}





	public void setPendingPayments(String pendingPayments) {
		this.pendingPayments = pendingPayments;
	}





	public String getAddress() {
		return address;
	}





	public void setAddress(String address) {
		this.address = address;
	}





	public String getNote() {
		return note;
	}





	public void setNote(String note) {
		this.note = note;
	}





	public String getIsUnknown() {
		return isUnknown;
	}





	public void setIsUnknown(String isUnknown) {
		this.isUnknown = isUnknown;
	}





	public String getIsDead() {
		return isDead;
	}





	public void setIsDead(String isDead) {
		this.isDead = isDead;
	}





	public String getIsMlc() {
		return isMlc;
	}





	public void setIsMlc(String isMlc) {
		this.isMlc = isMlc;
	}





	public String getPatFirstName() {
		return patFirstName;
	}





	public void setPatFirstName(String patFirstName) {
		this.patFirstName = patFirstName;
	}





	public String getPatMiddleName() {
		return patMiddleName;
	}





	public void setPatMiddleName(String patMiddleName) {
		this.patMiddleName = patMiddleName;
	}





	public String getPatLastName() {
		return patLastName;
	}





	public void setPatLastName(String patLastName) {
		this.patLastName = patLastName;
	}





	public String getPatGuardianName() {
		return patGuardianName;
	}





	public void setPatGuardianName(String patGuardianName) {
		this.patGuardianName = patGuardianName;
	}





	public String getPatStatusCode() {
		return patStatusCode;
	}





	public void setPatStatusCode(String patStatusCode) {
		this.patStatusCode = patStatusCode;
	}





	public String getPatCategoryCode() {
		return patCategoryCode;
	}





	public void setPatCategoryCode(String patCategoryCode) {
		this.patCategoryCode = patCategoryCode;
	}





	public String getPatAge() {
		return patAge;
	}





	public void setPatAge(String patAge) {
		this.patAge = patAge;
	}





	public String getPatHusbandName() {
		return patHusbandName;
	}





	public void setPatHusbandName(String patHusbandName) {
		this.patHusbandName = patHusbandName;
	}





	public String getPatGenderCode() {
		return patGenderCode;
	}





	public void setPatGenderCode(String patGenderCode) {
		this.patGenderCode = patGenderCode;
	}





	public String getPatCategory() {
		return patCategory;
	}





	public void setPatCategory(String patCategory) {
		this.patCategory = patCategory;
	}





	public String getPatDOB() {
		return patDOB;
	}





	public void setPatDOB(String patDOB) {
		this.patDOB = patDOB;
	}





	public String getIsActualDob() {
		return isActualDob;
	}





	public void setIsActualDob(String isActualDob) {
		this.isActualDob = isActualDob;
	}





	public String getPatGender() {
		return patGender;
	}





	public void setPatGender(String patGender) {
		this.patGender = patGender;
	}





	public String getPatMobileNo() {
		return patMobileNo;
	}





	public void setPatMobileNo(String patMobileNo) {
		this.patMobileNo = patMobileNo;
	}





	public String getIsCatExpired() {
		return isCatExpired;
	}





	public void setIsCatExpired(String isCatExpired) {
		this.isCatExpired = isCatExpired;
	}





	public String getPatEmailId() {
		return patEmailId;
	}





	public void setPatEmailId(String patEmailId) {
		this.patEmailId = patEmailId;
	}





	public String getAdmissionDate() {
		return admissionDate;
	}





	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}





	public String getPatVisitNo() {
		return patVisitNo;
	}





	public void setPatVisitNo(String patVisitNo) {
		this.patVisitNo = patVisitNo;
	}





	public String getPatEpisodeCode() {
		return patEpisodeCode;
	}





	public void setPatEpisodeCode(String patEpisodeCode) {
		this.patEpisodeCode = patEpisodeCode;
	}





	public String getAdmittedDepartmentCode() {
		return admittedDepartmentCode;
	}





	public void setAdmittedDepartmentCode(String admittedDepartmentCode) {
		this.admittedDepartmentCode = admittedDepartmentCode;
	}





	public String getPatAdmissionNo() {
		return patAdmissionNo;
	}





	public void setPatAdmissionNo(String patAdmissionNo) {
		this.patAdmissionNo = patAdmissionNo;
	}





	public String getPatDeptUnit() {
		return patDeptUnit;
	}





	public void setPatDeptUnit(String patDeptUnit) {
		this.patDeptUnit = patDeptUnit;
	}





	public String getAdmittedDepartmentName() {
		return admittedDepartmentName;
	}





	public void setAdmittedDepartmentName(String admittedDepartmentName) {
		this.admittedDepartmentName = admittedDepartmentName;
	}





	public String getPatWardName() {
		return patWardName;
	}





	public void setPatWardName(String patWardName) {
		this.patWardName = patWardName;
	}





	public String getPatRoomNo() {
		return patRoomNo;
	}





	public void setPatRoomNo(String patRoomNo) {
		this.patRoomNo = patRoomNo;
	}





	public String getPatRoomName() {
		return patRoomName;
	}





	public void setPatRoomName(String patRoomName) {
		this.patRoomName = patRoomName;
	}





	public String getBedCode() {
		return bedCode;
	}





	public void setBedCode(String bedCode) {
		this.bedCode = bedCode;
	}





	public String getBedName() {
		return bedName;
	}





	public void setBedName(String bedName) {
		this.bedName = bedName;
	}





	public String getHospitalCode() {
		return hospitalCode;
	}





	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}





	public String getConsultantName() {
		return consultantName;
	}





	public void setConsultantName(String consultantName) {
		this.consultantName = consultantName;
	}





	public String getDiagnosis() {
		return diagnosis;
	}





	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}





	public String getPatAccNo() {
		return patAccNo;
	}





	public void setPatAccNo(String patAccNo) {
		this.patAccNo = patAccNo;
	}





	public String getAge_gender() {
		return age_gender;
	}





	public void setAge_gender(String age_gender) {
		this.age_gender = age_gender;
	}





	public String getWardbed_no() {
		return wardbed_no;
	}





	public void setWardbed_no(String wardbed_no) {
		this.wardbed_no = wardbed_no;
	}





	public String getDeptCode() {
		return deptCode;
	}





	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}





	public String getDeptName() {
		return deptName;
	}





	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}





	public String getWardName() {
		return wardName;
	}





	public void setWardName(String wardName) {
		this.wardName = wardName;
	}





	public String getAdvisedByDept() {
		return advisedByDept;
	}





	public void setAdvisedByDept(String advisedByDept) {
		this.advisedByDept = advisedByDept;
	}





	public String getRequisitionStatus() {
		return requisitionStatus;
	}





	public void setRequisitionStatus(String requisitionStatus) {
		this.requisitionStatus = requisitionStatus;
	}





	public String getRequisitionStatusCode() {
		return requisitionStatusCode;
	}





	public void setRequisitionStatusCode(String requisitionStatusCode) {
		this.requisitionStatusCode = requisitionStatusCode;
	}





	public String getTurnAroundTime() {
		return turnAroundTime;
	}





	public void setTurnAroundTime(String turnAroundTime) {
		this.turnAroundTime = turnAroundTime;
	}





	public String getTestRate() {
		return testRate;
	}





	public void setTestRate(String testRate) {
		this.testRate = testRate;
	}





	public String getBilledUnbilled() {
		return billedUnbilled;
	}





	public void setBilledUnbilled(String billedUnbilled) {
		this.billedUnbilled = billedUnbilled;
	}





	public String getTestNote() {
		return testNote;
	}





	public void setTestNote(String testNote) {
		this.testNote = testNote;
	}





	public String getRequisitionBy() {
		return requisitionBy;
	}





	public void setRequisitionBy(String requisitionBy) {
		this.requisitionBy = requisitionBy;
	}





	public String getAdvisedByDoc() {
		return advisedByDoc;
	}





	public void setAdvisedByDoc(String advisedByDoc) {
		this.advisedByDoc = advisedByDoc;
	}





	public String getAppointmentDate() {
		return appointmentDate;
	}





	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}





	public String getIsGroup() {
		return isGroup;
	}





	public void setIsGroup(String isGroup) {
		this.isGroup = isGroup;
	}





	public String getBillDate() {
		return billDate;
	}





	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}





	public String getTestRateDetail() {
		return testRateDetail;
	}





	public void setTestRateDetail(String testRateDetail) {
		this.testRateDetail = testRateDetail;
	}





	public String getCollectionArea() {
		return collectionArea;
	}





	public void setCollectionArea(String collectionArea) {
		this.collectionArea = collectionArea;
	}





	public String getSampleCollDate() {
		return sampleCollDate;
	}





	public void setSampleCollDate(String sampleCollDate) {
		this.sampleCollDate = sampleCollDate;
	}





	public String getSampleCollBy() {
		return sampleCollBy;
	}





	public void setSampleCollBy(String sampleCollBy) {
		this.sampleCollBy = sampleCollBy;
	}





	public String getPackingListNo() {
		return packingListNo;
	}





	public void setPackingListNo(String packingListNo) {
		this.packingListNo = packingListNo;
	}





	public String getPackingListDateTime() {
		return packingListDateTime;
	}





	public void setPackingListDateTime(String packingListDateTime) {
		this.packingListDateTime = packingListDateTime;
	}





	public String getPackingListBy() {
		return packingListBy;
	}





	public void setPackingListBy(String packingListBy) {
		this.packingListBy = packingListBy;
	}





	public String getLabNo() {
		return labNo;
	}





	public void setLabNo(String labNo) {
		this.labNo = labNo;
	}





	public String getSampleAccepDate() {
		return sampleAccepDate;
	}





	public void setSampleAccepDate(String sampleAccepDate) {
		this.sampleAccepDate = sampleAccepDate;
	}





	public String getSampleAccepBy() {
		return sampleAccepBy;
	}





	public void setSampleAccepBy(String sampleAccepBy) {
		this.sampleAccepBy = sampleAccepBy;
	}





	public String getSampleAccepMode() {
		return sampleAccepMode;
	}





	public void setSampleAccepMode(String sampleAccepMode) {
		this.sampleAccepMode = sampleAccepMode;
	}





	public String getMachineName() {
		return machineName;
	}





	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}





	public String getAccessionNo() {
		return accessionNo;
	}





	public void setAccessionNo(String accessionNo) {
		this.accessionNo = accessionNo;
	}





	public String getPatientAccepDate() {
		return patientAccepDate;
	}





	public void setPatientAccepDate(String patientAccepDate) {
		this.patientAccepDate = patientAccepDate;
	}





	public String getPatientAccepBy() {
		return patientAccepBy;
	}





	public void setPatientAccepBy(String patientAccepBy) {
		this.patientAccepBy = patientAccepBy;
	}





	public String getPatientAccepMode() {
		return patientAccepMode;
	}





	public void setPatientAccepMode(String patientAccepMode) {
		this.patientAccepMode = patientAccepMode;
	}





	public String getSampleRejecDate() {
		return sampleRejecDate;
	}





	public void setSampleRejecDate(String sampleRejecDate) {
		this.sampleRejecDate = sampleRejecDate;
	}





	public String getSampleRejecBy() {
		return sampleRejecBy;
	}





	public void setSampleRejecBy(String sampleRejecBy) {
		this.sampleRejecBy = sampleRejecBy;
	}





	public String getSampleRejecReason() {
		return sampleRejecReason;
	}





	public void setSampleRejecReason(String sampleRejecReason) {
		this.sampleRejecReason = sampleRejecReason;
	}





	public String getPatientRejecDate() {
		return patientRejecDate;
	}





	public void setPatientRejecDate(String patientRejecDate) {
		this.patientRejecDate = patientRejecDate;
	}





	public String getPatientRejecBy() {
		return patientRejecBy;
	}





	public void setPatientRejecBy(String patientRejecBy) {
		this.patientRejecBy = patientRejecBy;
	}





	public String getPatientRejecReason() {
		return patientRejecReason;
	}





	public void setPatientRejecReason(String patientRejecReason) {
		this.patientRejecReason = patientRejecReason;
	}





	public String getResultEntryDate() {
		return resultEntryDate;
	}





	public void setResultEntryDate(String resultEntryDate) {
		this.resultEntryDate = resultEntryDate;
	}





	public String getResultEntryBy() {
		return resultEntryBy;
	}





	public void setResultEntryBy(String resultEntryBy) {
		this.resultEntryBy = resultEntryBy;
	}





	public String getResultEntryParam() {
		return resultEntryParam;
	}





	public void setResultEntryParam(String resultEntryParam) {
		this.resultEntryParam = resultEntryParam;
	}





	public String getTestParameterDetails() {
		return testParameterDetails;
	}





	public void setTestParameterDetails(String testParameterDetails) {
		this.testParameterDetails = testParameterDetails;
	}





	public String getResultValDate() {
		return resultValDate;
	}





	public void setResultValDate(String resultValDate) {
		this.resultValDate = resultValDate;
	}





	public String getResultValBy() {
		return resultValBy;
	}





	public void setResultValBy(String resultValBy) {
		this.resultValBy = resultValBy;
	}





	public String getResultReValDate() {
		return resultReValDate;
	}





	public void setResultReValDate(String resultReValDate) {
		this.resultReValDate = resultReValDate;
	}





	public String getResultReValidBy() {
		return resultReValidBy;
	}





	public void setResultReValidBy(String resultReValidBy) {
		this.resultReValidBy = resultReValidBy;
	}





	public String getReportGenerationDate() {
		return reportGenerationDate;
	}





	public void setReportGenerationDate(String reportGenerationDate) {
		this.reportGenerationDate = reportGenerationDate;
	}





	public String getReportPrintDate() {
		return reportPrintDate;
	}





	public void setReportPrintDate(String reportPrintDate) {
		this.reportPrintDate = reportPrintDate;
	}





	public String getBarCodeGenSize() {
		return barCodeGenSize;
	}





	public void setBarCodeGenSize(String barCodeGenSize) {
		this.barCodeGenSize = barCodeGenSize;
	}





	public String getSaveConfirmFlag() {
		return saveConfirmFlag;
	}





	public void setSaveConfirmFlag(String saveConfirmFlag) {
		this.saveConfirmFlag = saveConfirmFlag;
	}





	public String getTempSampleNo() {
		return tempSampleNo;
	}





	public void setTempSampleNo(String tempSampleNo) {
		this.tempSampleNo = tempSampleNo;
	}





	public String getLabCode() {
		return labCode;
	}





	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}





	public String getTestCode() {
		return testCode;
	}





	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}





	public String getPatType() {
		return patType;
	}





	public void setPatType(String patType) {
		this.patType = patType;
	}





	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.currentPage=1;
		this.searchLabName="";
		this.searchTestName="";
		 
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
	public String getSampleAreaName() {
		return sampleAreaName;
	}
	public void setSampleAreaName(String sampleAreaName) {
		this.sampleAreaName = sampleAreaName;
	}
	public String getSampleAreaCode() {
		return sampleAreaCode;
	}
	public void setSampleAreaCode(String sampleAreaCode) {
		this.sampleAreaCode = sampleAreaCode;
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
	
	public String getSelectedCheckbox() {
		return selectedCheckbox;
	}
	public void setSelectedCheckbox(String selectedCheckbox) {
		this.selectedCheckbox = selectedCheckbox;
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
	public String getBillDetail() {
		return billDetail;
	}
	public void setBillDetail(String billDetail) {
		this.billDetail = billDetail;
	}
	public String getRequisitionDNo() {
		return requisitionDNo;
	}
	public void setRequisitionDNo(String requisitionDNo) {
		this.requisitionDNo = requisitionDNo;
	}
	public String getLabName() {
		return labName;
	}
	public void setLabName(String labName) {
		this.labName = labName;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getIsConfidential() {
		return isConfidential;
	}
	public void setIsConfidential(String isConfidential) {
		this.isConfidential = isConfidential;
	}
	public String getPriorityCode() {
		return priorityCode;
	}
	public void setPriorityCode(String priorityCode) {
		this.priorityCode = priorityCode;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getRequisitionDate() {
		return requisitionDate;
	}
	public void setRequisitionDate(String requisitionDate) {
		this.requisitionDate = requisitionDate;
	}
	public String getReqDtlStatus() {
		return reqDtlStatus;
	}
	public void setReqDtlStatus(String reqDtlStatus) {
		this.reqDtlStatus = reqDtlStatus;
	}
	public String getSampleCode() {
		return sampleCode;
	}
	public void setSampleCode(String sampleCode) {
		this.sampleCode = sampleCode;
	}
	public String getSampleName() {
		return sampleName;
	}
	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}
	public String getRequisitionNo() {
		return requisitionNo;
	}
	public void setRequisitionNo(String requisitionNo) {
		this.requisitionNo = requisitionNo;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	public String getDefaultSampelCode() {
		return defaultSampelCode;
	}
	public void setDefaultSampelCode(String defaultSampelCode) {
		this.defaultSampelCode = defaultSampelCode;
	}
	public String getSampleNoConfiguration() {
		return sampleNoConfiguration;
	}
	public void setSampleNoConfiguration(String sampleNoConfiguration) {
		this.sampleNoConfiguration = sampleNoConfiguration;
	}
	public String getReqHeaderStatus() {
		return reqHeaderStatus;
	}
	public void setReqHeaderStatus(String reqHeaderStatus) {
		this.reqHeaderStatus = reqHeaderStatus;
	}
	public String getEpisodeCode() {
		return episodeCode;
	}
	public void setEpisodeCode(String episodeCode) {
		this.episodeCode = episodeCode;
	}
	public String getReqType() {
		return reqType;
	}
	public void setReqType(String reqType) {
		this.reqType = reqType;
	}
	public String getPatAdmNo() {
		return patAdmNo;
	}
	public void setPatAdmNo(String patAdmNo) {
		this.patAdmNo = patAdmNo;
	}
	public String getPatAddress() {
		return patAddress;
	}
	public void setPatAddress(String patAddress) {
		this.patAddress = patAddress;
	}
	public String getPatWardCode() {
		return patWardCode;
	}
	public void setPatWardCode(String patWardCode) {
		this.patWardCode = patWardCode;
	}
	public String getPatMobile() {
		return patMobile;
	}
	public void setPatMobile(String patMobile) {
		this.patMobile = patMobile;
	}
	public String getPatEmail() {
		return patEmail;
	}
	public void setPatEmail(String patEmail) {
		this.patEmail = patEmail;
	}
	public String getPatRoomCode() {
		return patRoomCode;
	}
	public void setPatRoomCode(String patRoomCode) {
		this.patRoomCode = patRoomCode;
	}
	public String getPatBedCode() {
		return patBedCode;
	}
	public void setPatBedCode(String patBedCode) {
		this.patBedCode = patBedCode;
	}
	public String getPatMlcNo() {
		return patMlcNo;
	}
	public void setPatMlcNo(String patMlcNo) {
		this.patMlcNo = patMlcNo;
	}
	public String getPatDeptName() {
		return patDeptName;
	}
	public void setPatDeptName(String patDeptName) {
		this.patDeptName = patDeptName;
	}
	public String getPatDeptCode() {
		return patDeptCode;
	}
	public void setPatDeptCode(String patDeptCode) {
		this.patDeptCode = patDeptCode;
	}
	public String getPatDeptUnitCode() {
		return patDeptUnitCode;
	}
	public void setPatDeptUnitCode(String patDeptUnitCode) {
		this.patDeptUnitCode = patDeptUnitCode;
	}
	public String[] getChkSamplePatient() {
		return chkSamplePatient;
	}
	public void setChkSamplePatient(String[] chkSamplePatient) {
		this.chkSamplePatient = chkSamplePatient;
	}
	public String[] getDefaultContainerCode() {
		return defaultContainerCode;
	}
	public void setDefaultContainerCode(String[] defaultContainerCode) {
		this.defaultContainerCode = defaultContainerCode;
	}
	public String[] getDefaultUOMCode() {
		return defaultUOMCode;
	}
	public void setDefaultUOMCode(String[] defaultUOMCode) {
		this.defaultUOMCode = defaultUOMCode;
	}
	public String[] getSampleNo() {
		return sampleNo;
	}
	public void setSampleNo(String[] sampleNo) {
		this.sampleNo = sampleNo;
	}
	public String[] getSampleQnty() {
		return sampleQnty;
	}
	public void setSampleQnty(String[] sampleQnty) {
		this.sampleQnty = sampleQnty;
	}
	public String getStrSampleNo() {
		return strSampleNo;
	}
	public void setStrSampleNo(String strSampleNo) {
		this.strSampleNo = strSampleNo;
	}
	public String getIsSampleAreaSelected() {
		return isSampleAreaSelected;
	}
	public void setIsSampleAreaSelected(String isSampleAreaSelected) {
		this.isSampleAreaSelected = isSampleAreaSelected;
	}
	public String getIsCancel() {
		return isCancel;
	}
	public void setIsCancel(String isCancel) {
		this.isCancel = isCancel;
	}
	public String getTempPatCRNo() {
		return tempPatCRNo;
	}
	public void setTempPatCRNo(String tempPatCRNo) {
		this.tempPatCRNo = tempPatCRNo;
	}





	public String getSysDate() {
		return sysDate;
	}





	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}





	public String getInstructionPat() {
		return instructionPat;
	}





	public void setInstructionPat(String instructionPat) {
		this.instructionPat = instructionPat;
	}





	public int getStartIndex1() {
		return startIndex1;
	}





	public void setStartIndex1(int startIndex1) {
		this.startIndex1 = startIndex1;
	}





	public int getEndIndex1() {
		return endIndex1;
	}





	public void setEndIndex1(int endIndex1) {
		this.endIndex1 = endIndex1;
	}





	public String getPatCrNo() {
		return patCrNo;
	}





	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}





	public String[] getDefaultmachineCode() {
		return defaultmachineCode;
	}





	public void setDefaultmachineCode(String[] defaultmachineCode) {
		this.defaultmachineCode = defaultmachineCode;
	}





	public String getModebarcode() {
		return modebarcode;
	}





	public void setModebarcode(String modebarcode) {
		this.modebarcode = modebarcode;
	}





	public String getStatuschange() {
		return statuschange;
	}





	public void setStatuschange(String statuschange) {
		this.statuschange = statuschange;
	}





	public String getPatName() {
		return patName;
	}





	public void setPatName(String patName) {
		this.patName = patName;
	}





	public String getWardCode() {
		return wardCode;
	}





	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}





	public String getFlagforipddesk() {
		return flagforipddesk;
	}





	public void setFlagforipddesk(String flagforipddesk) {
		this.flagforipddesk = flagforipddesk;
	}





	public String getDuplicateBarcodeGeneration() {
		return duplicateBarcodeGeneration;
	}





	public void setDuplicateBarcodeGeneration(String duplicateBarcodeGeneration) {
		this.duplicateBarcodeGeneration = duplicateBarcodeGeneration;
	}





	public String getIssetmsg() {
		return issetmsg;
	}





	public void setIssetmsg(String issetmsg) {
		this.issetmsg = issetmsg;
	}





	public String getIssearchviacrno() {
		return issearchviacrno;
	}





	public void setIssearchviacrno(String issearchviacrno) {
		this.issearchviacrno = issearchviacrno;
	}



	
	
	
}
