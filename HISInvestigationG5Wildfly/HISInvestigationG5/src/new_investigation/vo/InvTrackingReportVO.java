package new_investigation.vo;

import hisglobal.vo.ValueObject;

@SuppressWarnings("serial")
public class InvTrackingReportVO extends ValueObject {
	
	private String hmode;
	private String fromDate="";
	private String toDate="";
	private String billNo;
	private String crNo;
	private String crNumber;
	private String dataFromArchival;
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
	private String isNewBorn;
	private String isPregnant;
	private String isVip;
	private String isConfidential;
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
	private String patStatus;
	private String patMobileNo;
	private String patAddress;
	private String isCatExpired;
	private String patEmailId;
	/* IPD Specific */
	private String admissionDate;
	private String patDeptUnitCode;
	private String patVisitNo;
	private String patEpisodeCode;
	private String admittedDepartmentCode;
	private String patAdmissionNo;
	private String patDeptUnit;
	private String admittedDepartmentName;
	private String patWardCode;
	private String patWardName;
	private String patRoomNo;
	private String patRoomName;
	private String bedCode;
	private String bedName;
	private String hospitalCode;
	private String consultantName;
	private String patMlcNo;
	private String diagnosis;
	private String patAccNo;

	private String labCode;
	private String patName;
	private String patType;
	private String age_gender;
	private String wardbed_no;
	private String deptCode;
	private String deptName;
	private String wardName;
	
	private String requisitionDate;
	private String labName;
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
	private String groupName;
	private String groupCode;
	private String testName;
	private String testCode;
	private String billDate;
	private String testRateDetail;
	private String billDetail;

	private String sampleName;
	private String sampleNo;
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
	private String reportPrintBy;
	private String reportURL;
	
	private String selectedCheckbox;
	private String ftpserver;
	private String pdfName;
	private String showStatus;
	private String selectedPDFName;
	
	private String requisitionDNo;
	private String requisitionNo; 
	private String testParamRefRange;
	private String testParamName ;
	private String testParamCode ;
	private String testParamValue ;
	private String testParamOutOfBound ;
	
	
	
	
	public String getForTestOrGroupOrAll() {
		return forTestOrGroupOrAll;
	}

	public void setForTestOrGroupOrAll(String forTestOrGroupOrAll) {
		this.forTestOrGroupOrAll = forTestOrGroupOrAll;
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

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getTestParameterDetails() {
		return testParameterDetails;
	}

	public void setTestParameterDetails(String testParameterDetails) {
		this.testParameterDetails = testParameterDetails;
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

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getRequisitionDNo() {
		return requisitionDNo;
	}

	public void setRequisitionDNo(String requisitionDNo) {
		this.requisitionDNo = requisitionDNo;
	}

	public String getRequisitionNo() {
		return requisitionNo;
	}

	public void setRequisitionNo(String requisitionNo) {
		this.requisitionNo = requisitionNo;
	}

	public String getSelectedPDFName() {
		return selectedPDFName;
	}

	public void setSelectedPDFName(String selectedPDFName) {
		this.selectedPDFName = selectedPDFName;
	}

	
	public String getShowStatus() {
		return showStatus;
	}

	public void setShowStatus(String showStatus) {
		this.showStatus = showStatus;
	}

	
	
	public String getSelectedCheckbox() {
		return selectedCheckbox;
	}
	public void setSelectedCheckbox(String selectedCheckbox) {
		this.selectedCheckbox = selectedCheckbox;
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
	public String getReportURL() {
		return reportURL;
	}
	public void setReportURL(String reportURL) {
		this.reportURL = reportURL;
	}
	public String getCrNumber() {
		return crNumber;
	}
	public void setCrNumber(String crNumber) {
		this.crNumber = crNumber;
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
	
	public String getIsNewBorn() {
		return isNewBorn;
	}

	public void setIsNewBorn(String isNewBorn) {
		this.isNewBorn = isNewBorn;
	}

	public String getIsPregnant() {
		return isPregnant;
	}

	public void setIsPregnant(String isPregnant) {
		this.isPregnant = isPregnant;
	}

	public String getIsVip() {
		return isVip;
	}

	public void setIsVip(String isVip) {
		this.isVip = isVip;
	}

	public String getIsConfidential() {
		return isConfidential;
	}

	public void setIsConfidential(String isConfidential) {
		this.isConfidential = isConfidential;
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
	public String getPatStatus() {
		return patStatus;
	}
	public void setPatStatus(String patStatus) {
		this.patStatus = patStatus;
	}
	public String getPatMobileNo() {
		return patMobileNo;
	}
	public void setPatMobileNo(String patMobileNo) {
		this.patMobileNo = patMobileNo;
	}
	public String getPatAddress() {
		return patAddress;
	}
	public void setPatAddress(String patAddress) {
		this.patAddress = patAddress;
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
	public String getPatDeptUnitCode() {
		return patDeptUnitCode;
	}
	public void setPatDeptUnitCode(String patDeptUnitCode) {
		this.patDeptUnitCode = patDeptUnitCode;
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
	public String getPatWardCode() {
		return patWardCode;
	}
	public void setPatWardCode(String patWardCode) {
		this.patWardCode = patWardCode;
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
	public String getPatMlcNo() {
		return patMlcNo;
	}
	public void setPatMlcNo(String patMlcNo) {
		this.patMlcNo = patMlcNo;
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
	public String getIsGroup() {
		return isGroup;
	}
	public void setIsGroup(String isGroup) {
		this.isGroup = isGroup;
	}
	public String getTestRateDetail() {
		return testRateDetail;
	}
	public void setTestRateDetail(String testRateDetail) {
		this.testRateDetail = testRateDetail;
	}
	public String getBillDetail() {
		return billDetail;
	}
	public void setBillDetail(String billDetail) {
		this.billDetail = billDetail;
	}
	
	public String getAdvisedByDept() {
		return advisedByDept;
	}
	public void setAdvisedByDept(String advisedByDept) {
		this.advisedByDept = advisedByDept;
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
	public String getTestCode() {
		return testCode;
	}
	public void setTestCode(String testCode) {
		this.testCode = testCode;
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
	public String getTestNote() {
		return testNote;
	}
	public void setTestNote(String testNote) {
		this.testNote = testNote;
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
	public String getReportPrintBy() {
		return reportPrintBy;
	}
	public void setReportPrintBy(String reportPrintBy) {
		this.reportPrintBy = reportPrintBy;
	}
	public String getRequisitionStatus() {
		return requisitionStatus;
	}
	public void setRequisitionStatus(String requisitionStatus) {
		this.requisitionStatus = requisitionStatus;
	}
	public String getDataFromArchival() {
		return dataFromArchival;
	}
	public void setDataFromArchival(String dataFromArchival) {
		this.dataFromArchival = dataFromArchival;
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
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
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
	public String getLabCode() {
		return labCode;
	}
	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}
	public String getPatName() {
		return patName;
	}
	public void setPatName(String patName) {
		this.patName = patName;
	}
	public String getPatType() {
		return patType;
	}
	public void setPatType(String patType) {
		this.patType = patType;
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
	public String getRequisitionDate() {
		return requisitionDate;
	}
	public void setRequisitionDate(String requisitionDate) {
		this.requisitionDate = requisitionDate;
	}
	public String getLabName() {
		return labName;
	}
	public void setLabName(String labName) {
		this.labName = labName;
	}
	public String getAdvisedByDoc() {
		return advisedByDoc;
	}
	public void setAdvisedByDoc(String advisedByDoc) {
		this.advisedByDoc = advisedByDoc;
	}
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	
	public String getRequisitionBy() {
		return requisitionBy;
	}
	public void setRequisitionBy(String requisitionBy) {
		this.requisitionBy = requisitionBy;
	}
	public String getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
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
	public String getSampleNo() {
		return sampleNo;
	}
	
	public String getCollectionArea() {
		return collectionArea;
	}
	public void setCollectionArea(String collectionArea) {
		this.collectionArea = collectionArea;
	}
	public void setSampleNo(String sampleNo) {
		this.sampleNo = sampleNo;
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
	
	
	
}
