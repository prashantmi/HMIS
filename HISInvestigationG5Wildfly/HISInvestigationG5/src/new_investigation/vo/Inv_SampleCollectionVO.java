package new_investigation.vo;

import hisglobal.vo.ValueObject;

public class Inv_SampleCollectionVO extends ValueObject{
	private String patCRNo;
	private String patFirstName;
    private String patMiddleName;
	private String patLastName;
    private String patGender;
	private String patGenderCode;
    
    private String patAgeUnit;
    private String patDOB;
    private String isActualDob;
    private String patCategory;
	private String patCategoryCode;
   	private String patHusbandName;
	private String patGuardianName;
	private String patDepartment;
	private String  sampleAreaName;
	public String sampleAreaCode;
	//patientDtl table
	
	public String patLabName;
	public String patReqDate;	
	public String patName;
	private String patAge;
	public String patUnitName;
	public String patVisitNo;
	public String patVisitDate;
	public String patReqNo;
	private String patStatus;
	
	private String patStatusCode;
	
	private String patWardName;
	private String patRoomName;	
	private String patBedName;
	private String 	patOrderByDoc;
	
	
	private String 	fromDate;
	private String 	toDate;
	
	private String billDetail;
	private String requisitionDNo;
	
	private String labCode;
	private String labName;
	private String testCode;
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
	private String sampleQnty;
	private String defaultContainerCode;
	
	private String defaultUOMCode;
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
	
	private String sampleNo;
	
	private String tempSampleNo;
	
	private String billNo;
	
	private String printStatus;
	
	private String typeOfComponent;
	
	private String sampleStatusCode;
	
	private String sampleCollectionDate;
	
	private String packingListNo;
	
	
	private String listStatus;
	
	private String isPackingListOffline;
	
	private String sampleStatus;
	
	
	private String packingListGenerationType;
	private String patType;
	private String sampleNoFormat;
	private String entryDate;
	private String initType;
	private String fromSeries;
	private String toSeries;
	private String runningSampleNo;
	private String noOfSeqDigit;
	private String temparorySampleNO;
	private String checkAutoLabConfig;
	private String initDate;
	
	private String configLab;
	private String configTest;
	private String configSeq;
	private String configType;
	private String configArea;
   private String priorityAll;	
   private String priorityAllCode;	

	private String patInstruct;
	private String collInstruct;
	private String instructionPat;
	private String advice;
	private String isrequisitionformneeded;
	private String chkval;
    private String  patAdmittedStatus;
    private String  reportalltest;
    private String  defaultmachineCode;
    private String machineCode;
	private String wardCode; 
	public String patientType;
	private String isRepeatSample;
	private String groupName;
	private String testNamee;
	private String isBilled;
	private String billDtl;
	private String sugarTestCode;
	private String priorityOfReq;
	private String islonnfsamplenousetestwise;;

	


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


	private String patMobileNo;

	private String isCatExpired;
	private String patEmailId;
	/* IPD Specific */
	private String admissionDate;

	private String patEpisodeCode;
	private String admittedDepartmentCode;
	private String patAdmissionNo;
	private String patDeptUnit;
	private String admittedDepartmentName;

	private String patRoomNo;

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
	
	
	private String is_dept_barcode_print ;
	private String departmentName ;
	
	
	private String isanyInstruction;
	private String fastingTime;
	private String fastingType;
	private String bladderintr;
	
	/* Prashant SMC */
	public String getPriorityOfReq() {
		return priorityOfReq;
	}

	public void setPriorityOfReq(String priorityOfReq) {
		this.priorityOfReq = priorityOfReq;
	}
	

	public String getBillDtl() {
		return billDtl;
	}
	public void setBillDtl(String billDtl) {
		this.billDtl = billDtl;
	}
	public String getIsBilled() {
		return isBilled;
	}
	public void setIsBilled(String isBilled) {
		this.isBilled = isBilled;
	}
	public String getIsRepeatSample() {
		return isRepeatSample;
	}
	public void setIsRepeatSample(String isRepeatSample) {
		this.isRepeatSample = isRepeatSample;
	}
	public String getChkval() {
		return chkval;
	}
	public void setChkval(String chkval) {
		this.chkval = chkval;
	}
	public String getCollInstruct() {
		return collInstruct;
	}
	public void setCollInstruct(String collInstruct) {
		this.collInstruct = collInstruct;
	}
	public String getPatInstruct() {
		return patInstruct;
	}
	public void setPatInstruct(String patInstruct) {
		this.patInstruct = patInstruct;
	}
	public String getConfigArea() {
		return configArea;
	}
	public void setConfigArea(String configArea) {
		this.configArea = configArea;
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
	public String getConfigSeq() {
		return configSeq;
	}
	public void setConfigSeq(String configSeq) {
		this.configSeq = configSeq;
	}
	public String getConfigType() {
		return configType;
	}
	public void setConfigType(String configType) {
		this.configType = configType;
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
	public String getPatWardName() {
		return patWardName;
	}
	public void setPatWardName(String patWardName) {
		this.patWardName = patWardName;
	}
	public String getpatOrderByDoc() {
		return patOrderByDoc;
	}
	public void setpatOrderByDoc(String patOrderByDoc) {
		this.patOrderByDoc = patOrderByDoc;
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
	
	public String getpatLabName() {
		return patLabName;
	}
	public void setpatLabName(String patLabName) {
		this.patLabName = patLabName;
	}
	public String getpatReqDate() {
		return patReqDate;
	}
	public void setpatReqDate(String patReqDate) {
		this.patReqDate = patReqDate;
	}
	public String getpatName() {
		return patName;
	}
	public void setpatName(String patName) {
		this.patName = patName;
	}
	public String getpatAge() {
		return patAge;
	}
	public void setpatAge(String patAge) {
		this.patAge = patAge;
	}
	public String getpatUnitName() {
		return patUnitName;
	}
	public void setPatUnitName(String patUnitName) {
		this.patUnitName = patUnitName;
	}
	public String getPatVisitDate() {
		return patVisitDate;
	}
	public void setPatVisitDate(String patVisitDate) {
		this.patVisitDate = patVisitDate;
	}		
	public String getpatReqNo() {
		return patReqNo;
	}
	public void setpatReqNo(String patReqNo) {
		this.patReqNo = patReqNo;
	}
	public String getPatStatus() {
		return patStatus;
	}
	public void setPatStatus(String patStatus) {
		this.patStatus = patStatus;
	}
	public String getPatDepartment() {
		return patDepartment;
	}
	public void setPatDepartment(String patDepartment) {
		this.patDepartment = patDepartment;
	}
	
	
	public String getIsActualDob() {
		return isActualDob;
	}
	public void setIsActualDob(String isActualDob) {
		this.isActualDob = isActualDob;
	}
	public String getPatAge() {
		return patAge;
	}
	public void setPatAge(String patAge) {
		this.patAge = patAge;
	}
	public String getPatAgeUnit() {
		return patAgeUnit;
	}
	public void setPatAgeUnit(String patAgeUnit) {
		this.patAgeUnit = patAgeUnit;
	}
	public String getPatCRNo() {
		return patCRNo;
	}
	public void setPatCRNo(String patCrNo) {
		this.patCRNo = patCrNo;
	}
	public String getPatDOB() {
		return patDOB;
	}
	public void setPatDOB(String patDOB) {
		this.patDOB = patDOB;
	}
	public String getPatFirstName() {
		return patFirstName;
	}
	public void setPatFirstName(String patFirstName) {
		this.patFirstName = patFirstName;
	}
	public String getPatGender() {
		return patGender;
	}
	public void setPatGender(String patGender) {
		this.patGender = patGender;
	}
	public String getPatGenderCode() {
		return patGenderCode;
	}
	public void setPatGenderCode(String patGenderCode) {
		this.patGenderCode = patGenderCode;
	}
	public String getPatGuardianName() {
		return patGuardianName;
	}
	public void setPatGuardianName(String patGuardianName) {
		this.patGuardianName = patGuardianName;
	}
	public String getPatHusbandName() {
		return patHusbandName;
	}
	public void setPatHusbandName(String patHusbandName) {
		this.patHusbandName = patHusbandName;
	}
	public String getPatLastName() {
		return patLastName;
	}
	public void setPatLastName(String patLastName) {
		this.patLastName = patLastName;
	}
	public String getPatMiddleName() {
		return patMiddleName;
	}
	public void setPatMiddleName(String patMiddleName) {
		this.patMiddleName = patMiddleName;
	}
	public String getPatCategory() {
		return patCategory;
	}
	public void setPatCategory(String patCategory) {
		this.patCategory = patCategory;
	}
	public String getPatCategoryCode() {
		return patCategoryCode;
	}
	public void setPatCategoryCode(String patCategoryCode) {
		this.patCategoryCode = patCategoryCode;
	}
	public String getPatStatusCode() {
		return patStatusCode;
	}
	public void setPatStatusCode(String patStatusCode) {
		this.patStatusCode = patStatusCode;
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
	public String getPatReqNo() {
		return patReqNo;
	}
	public void setPatReqNo(String patReqNo) {
		this.patReqNo = patReqNo;
	}
	public String getPatOrderByDoc() {
		return patOrderByDoc;
	}
	public void setPatOrderByDoc(String patOrderByDoc) {
		this.patOrderByDoc = patOrderByDoc;
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
	public String getPatUnitName() {
		return patUnitName;
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
	public String getLabCode() {
		return labCode;
	}
	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}
	public String getLabName() {
		return labName;
	}
	public void setLabName(String labName) {
		this.labName = labName;
	}
	public String getTestCode() {
		return testCode;
	}
	public void setTestCode(String testCode) {
		this.testCode = testCode;
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
	public String getSampleQnty() {
		return sampleQnty;
	}
	public void setSampleQnty(String sampleQnty) {
		this.sampleQnty = sampleQnty;
	}
	public String getDefaultContainerCode() {
		return defaultContainerCode;
	}
	public void setDefaultContainerCode(String defaultContainerCode) {
		this.defaultContainerCode = defaultContainerCode;
	}
	public String getDefaultUOMCode() {
		return defaultUOMCode;
	}
	public void setDefaultUOMCode(String defaultUOMCode) {
		this.defaultUOMCode = defaultUOMCode;
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
	public String getPatVisitNo() {
		return patVisitNo;
	}
	public void setPatVisitNo(String patVisitNo) {
		this.patVisitNo = patVisitNo;
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
	public String getPatMlcNo() {
		return patMlcNo;
	}
	public void setPatMlcNo(String patMlcNo) {
		this.patMlcNo = patMlcNo;
	}
	public String getSampleNo() {
		return sampleNo;
	}
	public void setSampleNo(String sampleNo) {
		this.sampleNo = sampleNo;
	}
	public String getTempSampleNo() {
		return tempSampleNo;
	}
	public void setTempSampleNo(String tempSampleNo) {
		this.tempSampleNo = tempSampleNo;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getPrintStatus() {
		return printStatus;
	}
	public void setPrintStatus(String printStatus) {
		this.printStatus = printStatus;
	}
	public String getTypeOfComponent() {
		return typeOfComponent;
	}
	public void setTypeOfComponent(String typeOfComponent) {
		this.typeOfComponent = typeOfComponent;
	}
	public String getSampleStatusCode() {
		return sampleStatusCode;
	}
	public void setSampleStatusCode(String sampleStatusCode) {
		this.sampleStatusCode = sampleStatusCode;
	}
	public String getSampleCollectionDate() {
		return sampleCollectionDate;
	}
	public void setSampleCollectionDate(String sampleCollectionDate) {
		this.sampleCollectionDate = sampleCollectionDate;
	}
	public String getPackingListNo() {
		return packingListNo;
	}
	public void setPackingListNo(String packingListNo) {
		this.packingListNo = packingListNo;
	}
	public String getListStatus() {
		return listStatus;
	}
	public void setListStatus(String listStatus) {
		this.listStatus = listStatus;
	}
	public String getIsPackingListOffline() {
		return isPackingListOffline;
	}
	public void setIsPackingListOffline(String isPackingListOffline) {
		this.isPackingListOffline = isPackingListOffline;
	}
	public String getSampleStatus() {
		return sampleStatus;
	}
	public void setSampleStatus(String sampleStatus) {
		this.sampleStatus = sampleStatus;
	}
	public String getPackingListGenerationType() {
		return packingListGenerationType;
	}
	public void setPackingListGenerationType(String packingListGenerationType) {
		this.packingListGenerationType = packingListGenerationType;
	}
	public String getPatType() {
		return patType;
	}
	public void setPatType(String patType) {
		this.patType = patType;
	}
	public String getSampleNoFormat() {
		return sampleNoFormat;
	}
	public void setSampleNoFormat(String sampleNoFormat) {
		this.sampleNoFormat = sampleNoFormat;
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
	 
	public String getNoOfSeqDigit() {
		return noOfSeqDigit;
	}
	public void setNoOfSeqDigit(String noOfSeqDigit) {
		this.noOfSeqDigit = noOfSeqDigit;
	}
	public String getRunningSampleNo() {
		return runningSampleNo;
	}
	public void setRunningSampleNo(String runningSampleNo) {
		this.runningSampleNo = runningSampleNo;
	}
	public String getTemparorySampleNO() {
		return temparorySampleNO;
	}
	public void setTemparorySampleNO(String temparorySampleNO) {
		this.temparorySampleNO = temparorySampleNO;
	}
	public String getPriorityAll() {
		return priorityAll;
	}
	public void setPriorityAll(String priorityAll) {
		this.priorityAll = priorityAll;
	}
	public String getPriorityAllCode() {
		return priorityAllCode;
	}
	public void setPriorityAllCode(String priorityAllCode) {
		this.priorityAllCode = priorityAllCode;
	}
	public String getInstructionPat() {
		return instructionPat;
	}
	public void setInstructionPat(String instructionPat) {
		this.instructionPat = instructionPat;
	}
	public String getAdvice() {
		return advice;
	}
	public void setAdvice(String advice) {
		this.advice = advice;
	}
	public String getIsrequisitionformneeded() {
		return isrequisitionformneeded;
	}
	public void setIsrequisitionformneeded(String isrequisitionformneeded) {
		this.isrequisitionformneeded = isrequisitionformneeded;
	}
	public String getPatAdmittedStatus() {
		return patAdmittedStatus;
	}
	public void setPatAdmittedStatus(String patAdmittedStatus) {
		this.patAdmittedStatus = patAdmittedStatus;
	}
	public String getReportalltest() {
		return reportalltest;
	}
	public void setReportalltest(String reportalltest) {
		this.reportalltest = reportalltest;
	}
	public String getDefaultmachineCode() {
		return defaultmachineCode;
	}
	public void setDefaultmachineCode(String defaultmachineCode) {
		this.defaultmachineCode = defaultmachineCode;
	}
	public String getMachineCode() {
		return machineCode;
	}
	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}
	public String getWardCode() {
		return wardCode;
	}
	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}
	public String getPatientType() {
		return patientType;
	}
	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getTestNamee() {
		return testNamee;
	}
	public void setTestNamee(String testNamee) {
		this.testNamee = testNamee;
	}
	public String getSugarTestCode() {
		return sugarTestCode;
	}
	public void setSugarTestCode(String sugarTestCode) {
		this.sugarTestCode = sugarTestCode;
	}

	public String getIslonnfsamplenousetestwise() {
		return islonnfsamplenousetestwise;
	}

	public void setIslonnfsamplenousetestwise(String islonnfsamplenousetestwise) {
		this.islonnfsamplenousetestwise = islonnfsamplenousetestwise;
	}

	public String getIs_dept_barcode_print() {
		return is_dept_barcode_print;
	}

	public void setIs_dept_barcode_print(String is_dept_barcode_print) {
		this.is_dept_barcode_print = is_dept_barcode_print;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
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

	public String getPatRoomNo() {
		return patRoomNo;
	}

	public void setPatRoomNo(String patRoomNo) {
		this.patRoomNo = patRoomNo;
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

	public String getIsanyInstruction() {
		return isanyInstruction;
	}

	public void setIsanyInstruction(String isanyInstruction) {
		this.isanyInstruction = isanyInstruction;
	}

	public String getFastingTime() {
		return fastingTime;
	}

	public void setFastingTime(String fastingTime) {
		this.fastingTime = fastingTime;
	}

	public String getFastingType() {
		return fastingType;
	}

	public void setFastingType(String fastingType) {
		this.fastingType = fastingType;
	}

	public String getBladderintr() {
		return bladderintr;
	}

	public void setBladderintr(String bladderintr) {
		this.bladderintr = bladderintr;
	}


	
	
}
