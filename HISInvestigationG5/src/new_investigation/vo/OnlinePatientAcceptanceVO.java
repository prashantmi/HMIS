package new_investigation.vo;
import hisglobal.vo.ValueObject;



public class OnlinePatientAcceptanceVO extends ValueObject
{
	                    
	private static final long serialVersionUID = -4294089437863920752L;
	private String patCRNo;
	private String patepisodecode;
	 
	private String departmentcode;
	private String patdeptunitcode;
	private String patdeptunit;
	private String department;
	private String room;
	private String patepisodetypecode;
	private String patvisittypecode;
	private String hospitalcode;
	private String patmlcno;
	private String diagnosis;
	
	private String patVisitNo;
	
	
	
	private String dignosisName;
	
	private String fromDate="";
	 
	 
	private String toDate="";
   
	private String labCode;
	private String patLabName;
	private String patReqDate;
	private String patName;
	private String patAge;
	private String patStatus;
	private String patUnitName;
	
	private String reqNo;
	private String selectedCheckbox;
	private String isPatDetailPage;
	private String chkSamplePatient;
	private String patCategory;
	private String patWardName;
	private String patRoomName;
	private String patBedName;
	private String patOrderByDoc;
    private String patGender;
	public String patPuk;
	public String patReqNo;
	private String patVisitDat;
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
	private String labNoConfiguration;
	private String accepted;
	private String rejectionAction;
	private String requisitionDNo;
	private String rejectionId;
	private String rejectionReason;
	private String priorityCode;
	private String mobileNo;
	private String emailId;
	private String labCodeSamp;
	private String address;
	private String reqDtlStatus;
	private String groupType;
	private String groupCode;
	private String testCode;
	private String billNo;
	private String consQty;
	private String tempPatCRNo;
	private String TempDailyLabNo;
	private String strDailyLabNo;
	private String labNoFormat;
	private String fromSeries;
	private String toSeries;
	private String runningLabNo;
	private String entryDate;
	private String initType;
	private String noOfSeqDigit;
	private String checkAutoLabConfig;
	private String initDate;
	
	private String configLab;
	private String configTest;
	private String configType;
	private String configSeq;
	private String patType;
	private String instructionPat;
	private String instructionColl;
	private String isrequisitionformneeded;
	
	private String isSampleAreaSelected;
	private String sampleAreaCode;

	private String sampleAreaName;

	private String islabNoAreaWise;
	
	//added by krishnan nema on 08/10/2018
	private String patientType;
	private String machineCode;
	private String selectedmachineCode;
	
	private String apttype;
	private String apttime;
	private String  extrarejectionReason;
	private String requisitionNorefund;

	private String checkboxajaxvalues;

	private String acceptanceDate;
	
	/* Added by prashantMi */
	private String acceptedToNotAccepted;
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
	public String getAcceptanceDate() {
		return acceptanceDate;
	}
	public void setAcceptanceDate(String acceptanceDate) {
		this.acceptanceDate = acceptanceDate;
	}
	public String getPatType() {
		return patType;
	}
	public void setPatType(String patType) {
		this.patType = patType;
	}
	public String getConfigLab() {
		return configLab;
	}
	public void setConfigLab(String configLab) {
		this.configLab = configLab;
	}
	public String getConfigTest() {
		return configTest;
	}
	public void setConfigTest(String configTest) {
		this.configTest = configTest;
	}
	public String getConfigType() {
		return configType;
	}
	public void setConfigType(String configType) {
		this.configType = configType;
	}
	public String getConfigSeq() {
		return configSeq;
	}
	public void setConfigSeq(String configSeq) {
		this.configSeq = configSeq;
	}
	public String getInitDate() {
		return initDate;
	}
	public void setInitDate(String initDate) {
		this.initDate = initDate;
	}
	public String getCheckAutoLabConfig() {
		return checkAutoLabConfig;
	}
	public void setCheckAutoLabConfig(String checkAutoLabConfig) {
		this.checkAutoLabConfig = checkAutoLabConfig;
	}
	public String getPatCRNo() {
		return patCRNo;
	}
	public void setPatCRNo(String patCRNo) {
		this.patCRNo = patCRNo;
	}
	public String getPatepisodecode() {
		return patepisodecode;
	}
	public void setPatepisodecode(String patepisodecode) {
		this.patepisodecode = patepisodecode;
	}
	 
	public String getDepartmentcode() {
		return departmentcode;
	}
	public void setDepartmentcode(String departmentcode) {
		this.departmentcode = departmentcode;
	}
	public String getPatdeptunitcode() {
		return patdeptunitcode;
	}
	public void setPatdeptunitcode(String patdeptunitcode) {
		this.patdeptunitcode = patdeptunitcode;
	}
	public String getPatdeptunit() {
		return patdeptunit;
	}
	public void setPatdeptunit(String patdeptunit) {
		this.patdeptunit = patdeptunit;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getPatepisodetypecode() {
		return patepisodetypecode;
	}
	public void setPatepisodetypecode(String patepisodetypecode) {
		this.patepisodetypecode = patepisodetypecode;
	}
	public String getPatvisittypecode() {
		return patvisittypecode;
	}
	public void setPatvisittypecode(String patvisittypecode) {
		this.patvisittypecode = patvisittypecode;
	}
	public String getHospitalcode() {
		return hospitalcode;
	}
	public void setHospitalcode(String hospitalcode) {
		this.hospitalcode = hospitalcode;
	}
	public String getPatmlcno() {
		return patmlcno;
	}
	public void setPatmlcno(String patmlcno) {
		this.patmlcno = patmlcno;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getPatVisitNo() {
		return patVisitNo;
	}
	public void setPatVisitNo(String patVisitNo) {
		this.patVisitNo = patVisitNo;
	}
	public String getDignosisName() {
		return dignosisName;
	}
	public void setDignosisName(String dignosisName) {
		this.dignosisName = dignosisName;
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
	
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	public String getPatStatus() {
		return patStatus;
	}
	public void setPatStatus(String patStatus) {
		this.patStatus = patStatus;
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
	public String getChkSamplePatient() {
		return chkSamplePatient;
	}
	public void setChkSamplePatient(String chkSamplePatient) {
		this.chkSamplePatient = chkSamplePatient;
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
	public String getPatPuk() {
		return patPuk;
	}
	public void setPatPuk(String patPuk) {
		this.patPuk = patPuk;
	}
	public String getPatReqNo() {
		return patReqNo;
	}
	public void setPatReqNo(String patReqNo) {
		this.patReqNo = patReqNo;
	}
	public String getPatVisitDat() {
		return patVisitDat;
	}
	public void setPatVisitDat(String patVisitDat) {
		this.patVisitDat = patVisitDat;
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
	public String getLabNoConfiguration() {
		return labNoConfiguration;
	}
	public void setLabNoConfiguration(String labNoConfiguration) {
		this.labNoConfiguration = labNoConfiguration;
	}
	public String getAccepted() {
		return accepted;
	}
	public void setAccepted(String accepted) {
		this.accepted = accepted;
	}
	public String getRejectionAction() {
		return rejectionAction;
	}
	public void setRejectionAction(String rejectionAction) {
		this.rejectionAction = rejectionAction;
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
	public String getTestCode() {
		return testCode;
	}
	public void setTestCode(String testCode) {
		this.testCode = testCode;
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
	public String getLabNoFormat() {
		return labNoFormat;
	}
	public void setLabNoFormat(String labNoFormat) {
		this.labNoFormat = labNoFormat;
	}
	public String getFromSeries() {
		return fromSeries;
	}
	public void setFromSeries(String fromSeries) {
		this.fromSeries = fromSeries;
	}
	public String getToSeries() {
		return toSeries;
	}
	public void setToSeries(String toSeries) {
		this.toSeries = toSeries;
	}
	public String getRunningLabNo() {
		return runningLabNo;
	}
	public void setRunningLabNo(String runningLabNo) {
		this.runningLabNo = runningLabNo;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getInitType() {
		return initType;
	}
	public void setInitType(String initType) {
		this.initType = initType;
	}
	public String getNoOfSeqDigit() {
		return noOfSeqDigit;
	}
	public void setNoOfSeqDigit(String noOfSeqDigit) {
		this.noOfSeqDigit = noOfSeqDigit;
	}
	public String getInstructionPat() {
		return instructionPat;
	}
	public void setInstructionPat(String instructionPat) {
		this.instructionPat = instructionPat;
	}
	public String getInstructionColl() {
		return instructionColl;
	}
	public void setInstructionColl(String instructionColl) {
		this.instructionColl = instructionColl;
	}
	public String getIsrequisitionformneeded() {
		return isrequisitionformneeded;
	}
	public void setIsrequisitionformneeded(String isrequisitionformneeded) {
		this.isrequisitionformneeded = isrequisitionformneeded;
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
	public String getIslabNoAreaWise() {
		return islabNoAreaWise;
	}
	public void setIslabNoAreaWise(String islabNoAreaWise) {
		this.islabNoAreaWise = islabNoAreaWise;
	}
	public String getPatientType() {
		return patientType;
	}
	public void setPatientType(String patientType) {
		this.patientType = patientType;
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
	public String getApttype() {
		return apttype;
	}
	public void setApttype(String apttype) {
		this.apttype = apttype;
	}
	public String getApttime() {
		return apttime;
	}
	public void setApttime(String apttime) {
		this.apttime = apttime;
	}
	public String getExtrarejectionReason() {
		return extrarejectionReason;
	}
	public void setExtrarejectionReason(String extrarejectionReason) {
		this.extrarejectionReason = extrarejectionReason;
	}
	public String getRequisitionNorefund() {
		return requisitionNorefund;
	}
	public void setRequisitionNorefund(String requisitionNorefund) {
		this.requisitionNorefund = requisitionNorefund;
	}
	public String getCheckboxajaxvalues() {
		return checkboxajaxvalues;
	}
	public void setCheckboxajaxvalues(String checkboxajaxvalues) {
		this.checkboxajaxvalues = checkboxajaxvalues;
	}
	public String getPatCategoryCode() {
		return patCategoryCode;
	}
	public void setPatCategoryCode(String patCategoryCode) {
		this.patCategoryCode = patCategoryCode;
	}
	
	


}

