package investigationDesk.vo;

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
	public String getConfigArea() {
		return configArea;
	}
	public void setConfigArea(String configArea) {
		this.configArea = configArea;
	}
	
	
}
