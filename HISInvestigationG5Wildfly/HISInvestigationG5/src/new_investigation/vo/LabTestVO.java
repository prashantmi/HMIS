package new_investigation.vo;

import java.util.List;

import new_investigation.vo.template.ResultEntryVO;
import hisglobal.vo.ValueObject;

public class LabTestVO extends ValueObject {

	private String labCode;
	private String testCode;
	private String labName;
	private String testName;
	private String description;
	private String testOrder;
	private String isMultiSession;
	private String isAppointment;
	private String isRequisitionFormNeeded;
	private String isSampleFormNeeded ;
	private String isMandatoryReq;
	private String genderBound;
	private String isGrossingRequired;
	private String ageBound;
	private String lowAgeRange;
	private String highAgeRange;
	private String testheaderText;
	private String testFooterText;
	private String isFilmRequired;
	private String isSecurePrinting;
	private String isConsent;
	private String  testMethod;
	private String  isConfidential;
	private String samCollectionArea;
	private String testPrintingType;	
	private String printTemplateID;
	private String reportAvailableAfter;
	private String defaultSampleCode;
	private String testType;
	private String patAge;
	 
	private String strTestGroupCode;
	 private String status;
	 private String sampleName;
	 private String reqDate;
	 private String prvLabCode;
	 private String prvTestCode;
	 private String prvReqStatus;
	 private String appoitmentNo;
	 private String hideAptNo;
	 private String reqDateHeaderTable;
	 private String reqNo;
	 private String patStatus;
	 private String patName;
	 private String requisitionDNo;
	private String patType;
	private String groupCode;
	private String priorityCode;
	private String patDeptCode;
	private String patCategoryCode;
	private String episodeCode;
	private String patAddress;
	private String patMobile;
	private String patWardCode;
	private String patAdmNo;
	private String patGenderCode;
	private String patVisitNo;
	private String patOrderByDoc;
	private String alreadyRaised;
	private String singleSample;
	private String sampleString;
	private String userTestCode;
	private String sCode;
	private String sName;
	private String reqdSampleName;
	private String advice;
	private List<ResultEntryVO> resultEntryVOListValidatedBy;
	private String requisitionFormData;
	private String reqSampleShortName;
	private String tariffTestRate;
	private String deskcallingid;
	private String ispidshow;
	private String piddata;
	private String pidtestcontains;
    private String aptTyp;
    private String labbasedaptdatetime;
    private String site;
    private String  islabappointmentbased;
    private String viewCode;
    private String viewName;
    private String viewscount;
    private String xraycount;
    private String totalviewcount;
    private String totalreqviewcount;
    private String totalreqviewtyp;
    
    private String  sugartestcode;
    private String  tariiff;
    private String  requisitionraisedtrough;
    private String  testrate;
	private String advisedByDocNo;

	
	  private String chief_complaints_code[] ;
	  private String chief_complaints_name[] ;
	  private String diagnosis_code[] ;
	  private String diagnosis_name[] ;
	  private String is_pregnant ;
	  private String is_mlc ;
	  private String is_vip ;
	  private String is_dead ;
	  private String is_newborn ;
	  private String instruction_testwise ;
	  
	  private String isanyInstruction ;
	  private String fastingTime ;
	  private String fastingType ;
	  private String bladderintr ;
	  private String advisedByDocName;
			private String visitReason;
			  private String testwiseinst ;
			  private String chiefname ;
			  private String diagname ;
			  
			  
    public String getReqdSampleName() {
		return reqdSampleName;
	}
	public void setReqdSampleName(String reqdSampleName) {
		this.reqdSampleName = reqdSampleName;
	}
			//sample coll vo
			private String uomCode;
			private String containerCode;
			private String containerVolume;
			private String tempSampleNo;
		//	private String reqNo;
			private String sampleNo;
			private String reqDno;
			private String billNo;
			private String requisitionDtlStatus;
			private String workingOrderSequence;
			private String typeOfComponent;
			private String sampleAreaCode;
			private String printStatus;
			private String uomComboStr;
			private String containerComboStr;
			private String defaultContainerVol;
			private String sampleValues;
			private String sampleNoConfig;
			
			private String sampleFormate;
			 private String initDate;
			 private String noofSegDigits;
			 private	 String fromSeries;
			 private String toSeries;
			 private String initType;
			 private String runningSampleNo;
	//		 private String patType;
			 private String sampleNoFormat;
			 private String NoOfSeqDigit;
			 private String temparorySampleNO;
			 private String testLabTestCodeWise;
				private String testCodeWise;  
				private String testCodeLabValue;
				private String delLabCode;
				private String delTestCode;
				private String dupTestCode;
				private String dupLabCode;
		//		private String appoitmentNo;
				private String hidAptNo;
				private String offlineAptDtl;
				private String lengendStatus;
				
				 
			
				 private String deskProperties;
				 private String raiseAdvise;
	
				 
					private String configLab;
					private String configTest;
					private String configSeq;
					private String configType;
					private String configArea;
					
					private String sampleQnty;
		
					private String defaultContainerCode;
					private String defaultUOMCode;
					private String sampleNoConfiguration;
					
					//add by chandan on 15th-july-2016
					private String instructionPat;
					private String instructionColl;
					private String instructionTech;
					
					private String isLabAvailable;//Added By Jatin 26/7/2016
					
					public String getSampleNoConfiguration() {
						return sampleNoConfiguration;
					}
					public void setSampleNoConfiguration(String sampleNoConfiguration) {
						this.sampleNoConfiguration = sampleNoConfiguration;
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
					
	public String getAlreadyRaised() {
		return alreadyRaised;
	}
	public void setAlreadyRaised(String alreadyRaised) {
		this.alreadyRaised = alreadyRaised;
	}
	public String getPatOrderByDoc() {
		return patOrderByDoc;
	}
	public void setPatOrderByDoc(String patOrderByDoc) {
		this.patOrderByDoc = patOrderByDoc;
	}
	public String getPatGenderCode() {
		return patGenderCode;
	}
	public void setPatGenderCode(String patGenderCode) {
		this.patGenderCode = patGenderCode;
	}
	public String getPatAdmNo() {
		return patAdmNo;
	}
	public void setPatAdmNo(String patAdmNo) {
		this.patAdmNo = patAdmNo;
	}
	public String getPatVisitNo() {
		return patVisitNo;
	}
	public void setPatVisitNo(String patVisitNo) {
		this.patVisitNo = patVisitNo;
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
	public String getPatAddress() {
		return patAddress;
	}
	public void setPatAddress(String patAddress) {
		this.patAddress = patAddress;
	}
	public String getEpisodeCode() {
		return episodeCode;
	}
	public void setEpisodeCode(String episodeCode) {
		this.episodeCode = episodeCode;
	}
	public String getPatCategoryCode() {
		return patCategoryCode;
	}
	public void setPatCategoryCode(String patCategoryCode) {
		this.patCategoryCode = patCategoryCode;
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
	private String patDeptUnitCode;
	
	
	public String getPriorityCode() {
		return priorityCode;
	}
	public void setPriorityCode(String priorityCode) {
		this.priorityCode = priorityCode;
	}
	public String getRequisitionDNo() {
		return requisitionDNo;
	}
	public void setRequisitionDNo(String requisitionDNo) {
		this.requisitionDNo = requisitionDNo;
	}
	public String getPatName() {
		return patName;
	}
	public void setPatName(String patName) {
		this.patName = patName;
	}
	public String getPatStatus() {
		return patStatus;
	}
	public void setPatStatus(String patStatus) {
		this.patStatus = patStatus;
	}
	public String getReqNo() {
		return reqNo;
	}
	public void setReqNo(String reqNo) {
		this.reqNo = reqNo;
	}
	public String getReqDateHeaderTable() {
		return reqDateHeaderTable;
	}
	public void setReqDateHeaderTable(String reqDateHeaderTable) {
		this.reqDateHeaderTable = reqDateHeaderTable;
	}
	public String getHideAptNo() {
		return hideAptNo;
	}
	public void setHideAptNo(String hideAptNo) {
		this.hideAptNo = hideAptNo;
	}
	public String getAppoitmentNo() {
		return appoitmentNo;
	}
	public void setAppoitmentNo(String appoitmentNo) {
		this.appoitmentNo = appoitmentNo;
	}
	public String getPrvLabCode() {
		return prvLabCode;
	}
	public void setPrvLabCode(String prvLabCode) {
		this.prvLabCode = prvLabCode;
	}
	public String getPrvTestCode() {
		return prvTestCode;
	}
	public void setPrvTestCode(String prvTestCode) {
		this.prvTestCode = prvTestCode;
	}
	public String getPrvReqStatus() {
		return prvReqStatus;
	}
	public void setPrvReqStatus(String prvReqStatus) {
		this.prvReqStatus = prvReqStatus;
	}
	public String getReqDate() {
		return reqDate;
	}
	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}
	public String getSampleName() {
		return sampleName;
	}
	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	private String sampleComboStr;
	
	private String sampleCode;
	
	private String patCrNo;
	
	private String mandInfo;
	
	private String mandatoryType;
	private String mandatoryComboStr;
	
	private String priority;
	
	private String testGroupCode;
	private String testGroupName;

	
	private String testGroupType;
	
	private String appointmentDate;
	
	private String appointmentTime;
	
	
	private String aptNO;
	private String pukNo;
	private String patFirstName;
	private String PatMiddleName;
	private String patLastNAme;
	private String gender;
	private String aptDate;
	private String slotTime;
	private String age;
	private String offlineAppoitMentDate;
	private String bookMarkCode;
	private String mandCombo;
	
	private String setMandTextBoxCombo;
	private String mandComboTextBoxComboNames;
	private String finalMandValues;
	
	private String mandCode;
	
	private String finalMandCode;
	
	private String finalMandCodeValuesOnBookmark;
	
	private String advisedByDoctorName;
	private String advisedBy;
	private String appointmentRefNo;
	private String searchTestGroup;
	private String groupType;
	
	 
	public String getPatType() {
		return patType;
	}
	public void setPatType(String patType) {
		this.patType = patType;
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
	public String getSearchTestGroup() {
		return searchTestGroup;
	}
	public void setSearchTestGroup(String searchTestGroup) {
		this.searchTestGroup = searchTestGroup;
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
	public String getFinalMandValues() {
		return finalMandValues;
	}
	public void setFinalMandValues(String finalMandValues) {
		this.finalMandValues = finalMandValues;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public String getTestOrder() {
		return testOrder;
	}
	public void setTestOrder(String testOrder) {
		this.testOrder = testOrder;
	}
	public String getIsMultiSession() {
		return isMultiSession;
	}
	public void setIsMultiSession(String isMultiSession) {
		this.isMultiSession = isMultiSession;
	}
	public String getIsAppointment() {
		return isAppointment;
	}
	public void setIsAppointment(String isAppointment) {
		this.isAppointment = isAppointment;
	}
	public String getIsRequisitionFormNeeded() {
		return isRequisitionFormNeeded;
	}
	public void setIsRequisitionFormNeeded(String isRequisitionFormNeeded) {
		this.isRequisitionFormNeeded = isRequisitionFormNeeded;
	}
	public String getIsSampleFormNeeded() {
		return isSampleFormNeeded;
	}
	public void setIsSampleFormNeeded(String isSampleFormNeeded) {
		this.isSampleFormNeeded = isSampleFormNeeded;
	}
	public String getIsMandatoryReq() {
		return isMandatoryReq;
	}
	public void setIsMandatoryReq(String isMandatoryReq) {
		this.isMandatoryReq = isMandatoryReq;
	}
	public String getGenderBound() {
		return genderBound;
	}
	public void setGenderBound(String genderBound) {
		this.genderBound = genderBound;
	}
	public String getIsGrossingRequired() {
		return isGrossingRequired;
	}
	public void setIsGrossingRequired(String isGrossingRequired) {
		this.isGrossingRequired = isGrossingRequired;
	}
	public String getAgeBound() {
		return ageBound;
	}
	public void setAgeBound(String ageBound) {
		this.ageBound = ageBound;
	}
	public String getLowAgeRange() {
		return lowAgeRange;
	}
	public void setLowAgeRange(String lowAgeRange) {
		this.lowAgeRange = lowAgeRange;
	}
	public String getHighAgeRange() {
		return highAgeRange;
	}
	public void setHighAgeRange(String highAgeRange) {
		this.highAgeRange = highAgeRange;
	}
	public String getTestheaderText() {
		return testheaderText;
	}
	public void setTestheaderText(String testheaderText) {
		this.testheaderText = testheaderText;
	}
	public String getTestFooterText() {
		return testFooterText;
	}
	public void setTestFooterText(String testFooterText) {
		this.testFooterText = testFooterText;
	}
	public String getIsFilmRequired() {
		return isFilmRequired;
	}
	public void setIsFilmRequired(String isFilmRequired) {
		this.isFilmRequired = isFilmRequired;
	}
	public String getIsSecurePrinting() {
		return isSecurePrinting;
	}
	public void setIsSecurePrinting(String isSecurePrinting) {
		this.isSecurePrinting = isSecurePrinting;
	}
	public String getIsConsent() {
		return isConsent;
	}
	public void setIsConsent(String isConsent) {
		this.isConsent = isConsent;
	}
	public String getTestMethod() {
		return testMethod;
	}
	public void setTestMethod(String testMethod) {
		this.testMethod = testMethod;
	}
	public String getIsConfidential() {
		return isConfidential;
	}
	public void setIsConfidential(String isConfidential) {
		this.isConfidential = isConfidential;
	}
	public String getSamCollectionArea() {
		return samCollectionArea;
	}
	public void setSamCollectionArea(String samCollectionArea) {
		this.samCollectionArea = samCollectionArea;
	}
	public String getTestPrintingType() {
		return testPrintingType;
	}
	public void setTestPrintingType(String testPrintingType) {
		this.testPrintingType = testPrintingType;
	}
	public String getPrintTemplateID() {
		return printTemplateID;
	}
	public void setPrintTemplateID(String printTemplateID) {
		this.printTemplateID = printTemplateID;
	}
	public String getReportAvailableAfter() {
		return reportAvailableAfter;
	}
	public void setReportAvailableAfter(String reportAvailableAfter) {
		this.reportAvailableAfter = reportAvailableAfter;
	}
	public String getDefaultSampleCode() {
		return defaultSampleCode;
	}
	public void setDefaultSampleCode(String defaultSampleCode) {
		this.defaultSampleCode = defaultSampleCode;
	}
	public String getTestType() {
		return testType;
	}
	public void setTestType(String testType) {
		this.testType = testType;
	}
	public String getSampleComboStr() {
		return sampleComboStr;
	}
	public void setSampleComboStr(String sampleComboStr) {
		this.sampleComboStr = sampleComboStr;
	}
	public String getSampleCode() {
		return sampleCode;
	}
	public void setSampleCode(String sampleCode) {
		this.sampleCode = sampleCode;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getMandatoryComboStr() {
		return mandatoryComboStr;
	}
	public void setMandatoryComboStr(String mandatoryComboStr) {
		this.mandatoryComboStr = mandatoryComboStr;
	}
	public String getMandInfo() {
		return mandInfo;
	}
	public void setMandInfo(String mandInfo) {
		this.mandInfo = mandInfo;
	}
	public String getMandatoryType() {
		return mandatoryType;
	}
	public void setMandatoryType(String mandatoryType) {
		this.mandatoryType = mandatoryType;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getStrTestGroupCode() {
		return strTestGroupCode;
	}
	public void setStrTestGroupCode(String strTestGroupCode) {
		this.strTestGroupCode = strTestGroupCode;
	}
	public String getTestGroupCode() {
		return testGroupCode;
	}
	public void setTestGroupCode(String testGroupCode) {
		this.testGroupCode = testGroupCode;
	}
	public String getTestGroupType() {
		return testGroupType;
	}
	public void setTestGroupType(String testGroupType) {
		this.testGroupType = testGroupType;
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
	public String getOfflineAppoitMentDate() {
		return offlineAppoitMentDate;
	}
	public void setOfflineAppoitMentDate(String offlineAppoitMentDate) {
		this.offlineAppoitMentDate = offlineAppoitMentDate;
	}
	public String getBookMarkCode() {
		return bookMarkCode;
	}
	public void setBookMarkCode(String bookMarkCode) {
		this.bookMarkCode = bookMarkCode;
	}
	public String getPatAge() {
		return patAge;
	}
	public void setPatAge(String patAge) {
		this.patAge = patAge;
	}
	public String getMandCombo() {
		return mandCombo;
	}
	public void setMandCombo(String mandCombo) {
		this.mandCombo = mandCombo;
	}
	public String getSetMandTextBoxCombo() {
		return setMandTextBoxCombo;
	}
	public void setSetMandTextBoxCombo(String setMandTextBoxCombo) {
		this.setMandTextBoxCombo = setMandTextBoxCombo;
	}
	public String getMandComboTextBoxComboNames() {
		return mandComboTextBoxComboNames;
	}
	public void setMandComboTextBoxComboNames(String mandComboTextBoxComboNames) {
		this.mandComboTextBoxComboNames = mandComboTextBoxComboNames;
	}
	public String getSingleSample() {
		return singleSample;
	}
	public void setSingleSample(String singleSample) {
		this.singleSample = singleSample;
	}
	public String getSampleString() {
		return sampleString;
	}
	public void setSampleString(String sampleString) {
		this.sampleString = sampleString;
	}
	public String getUserTestCode() {
		return userTestCode;
	}
	public void setUserTestCode(String userTestCode) {
		this.userTestCode = userTestCode;
	}
	public String getsCode() {
		return sCode;
	}
	public void setsCode(String sCode) {
		this.sCode = sCode;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public String getUomCode() {
		return uomCode;
	}
	public void setUomCode(String uomCode) {
		this.uomCode = uomCode;
	}
	public String getContainerCode() {
		return containerCode;
	}
	public void setContainerCode(String containerCode) {
		this.containerCode = containerCode;
	}
	public String getContainerVolume() {
		return containerVolume;
	}
	public void setContainerVolume(String containerVolume) {
		this.containerVolume = containerVolume;
	}
	public String getTempSampleNo() {
		return tempSampleNo;
	}
	public void setTempSampleNo(String tempSampleNo) {
		this.tempSampleNo = tempSampleNo;
	}
	public String getSampleNo() {
		return sampleNo;
	}
	public void setSampleNo(String sampleNo) {
		this.sampleNo = sampleNo;
	}
	public String getReqDno() {
		return reqDno;
	}
	public void setReqDno(String reqDno) {
		this.reqDno = reqDno;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getRequisitionDtlStatus() {
		return requisitionDtlStatus;
	}
	public void setRequisitionDtlStatus(String requisitionDtlStatus) {
		this.requisitionDtlStatus = requisitionDtlStatus;
	}
	public String getWorkingOrderSequence() {
		return workingOrderSequence;
	}
	public void setWorkingOrderSequence(String workingOrderSequence) {
		this.workingOrderSequence = workingOrderSequence;
	}
	public String getTypeOfComponent() {
		return typeOfComponent;
	}
	public void setTypeOfComponent(String typeOfComponent) {
		this.typeOfComponent = typeOfComponent;
	}
	public String getSampleAreaCode() {
		return sampleAreaCode;
	}
	public void setSampleAreaCode(String sampleAreaCode) {
		this.sampleAreaCode = sampleAreaCode;
	}
	public String getPrintStatus() {
		return printStatus;
	}
	public void setPrintStatus(String printStatus) {
		this.printStatus = printStatus;
	}
	public String getUomComboStr() {
		return uomComboStr;
	}
	public void setUomComboStr(String uomComboStr) {
		this.uomComboStr = uomComboStr;
	}
	public String getContainerComboStr() {
		return containerComboStr;
	}
	public void setContainerComboStr(String containerComboStr) {
		this.containerComboStr = containerComboStr;
	}
	public String getDefaultContainerVol() {
		return defaultContainerVol;
	}
	public void setDefaultContainerVol(String defaultContainerVol) {
		this.defaultContainerVol = defaultContainerVol;
	}
	public String getSampleValues() {
		return sampleValues;
	}
	public void setSampleValues(String sampleValues) {
		this.sampleValues = sampleValues;
	}
	public String getSampleNoConfig() {
		return sampleNoConfig;
	}
	public void setSampleNoConfig(String sampleNoConfig) {
		this.sampleNoConfig = sampleNoConfig;
	}
	public String getSampleFormate() {
		return sampleFormate;
	}
	public void setSampleFormate(String sampleFormate) {
		this.sampleFormate = sampleFormate;
	}
	public String getInitDate() {
		return initDate;
	}
	public void setInitDate(String initDate) {
		this.initDate = initDate;
	}
	public String getNoofSegDigits() {
		return noofSegDigits;
	}
	public void setNoofSegDigits(String noofSegDigits) {
		this.noofSegDigits = noofSegDigits;
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
	public String getInitType() {
		return initType;
	}
	public void setInitType(String initType) {
		this.initType = initType;
	}
	public String getRunningSampleNo() {
		return runningSampleNo;
	}
	public void setRunningSampleNo(String runningSampleNo) {
		this.runningSampleNo = runningSampleNo;
	}
	public String getSampleNoFormat() {
		return sampleNoFormat;
	}
	public void setSampleNoFormat(String sampleNoFormat) {
		this.sampleNoFormat = sampleNoFormat;
	}
	public String getNoOfSeqDigit() {
		return NoOfSeqDigit;
	}
	public void setNoOfSeqDigit(String noOfSeqDigit) {
		NoOfSeqDigit = noOfSeqDigit;
	}
	public String getTemparorySampleNO() {
		return temparorySampleNO;
	}
	public void setTemparorySampleNO(String temparorySampleNO) {
		this.temparorySampleNO = temparorySampleNO;
	}
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
	public String getDeskProperties() {
		return deskProperties;
	}
	public void setDeskProperties(String deskProperties) {
		this.deskProperties = deskProperties;
	}
	public String getRaiseAdvise() {
		return raiseAdvise;
	}
	public void setRaiseAdvise(String raiseAdvise) {
		this.raiseAdvise = raiseAdvise;
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
	public String getInstructionTech() {
		return instructionTech;
	}
	public void setInstructionTech(String instructionTech) {
		this.instructionTech = instructionTech;
	}

	public String getIsLabAvailable() {
		return isLabAvailable;
	}
	public void setIsLabAvailable(String isLabAvailable) {
		this.isLabAvailable = isLabAvailable;
	}
	public String getAdvice() {
		return advice;
	}
	public void setAdvice(String advice) {
		this.advice = advice;
	}
	public List<ResultEntryVO> getResultEntryVOListValidatedBy() {
		return resultEntryVOListValidatedBy;
	}
	public void setResultEntryVOListValidatedBy(
			List<ResultEntryVO> resultEntryVOListValidatedBy) {
		this.resultEntryVOListValidatedBy = resultEntryVOListValidatedBy;
	}
	public String getRequisitionFormData() {
		return requisitionFormData;
	}
	public void setRequisitionFormData(String requisitionFormData) {
		this.requisitionFormData = requisitionFormData;
	}
	public String getReqSampleShortName() {
		return reqSampleShortName;
	}
	public void setReqSampleShortName(String reqSampleShortName) {
		this.reqSampleShortName = reqSampleShortName;
	}
	public String getTariffTestRate() {
		return tariffTestRate;
	}
	public void setTariffTestRate(String tariffTestRate) {
		this.tariffTestRate = tariffTestRate;
	}
	public String getDeskcallingid() {
		return deskcallingid;
	}
	public void setDeskcallingid(String deskcallingid) {
		this.deskcallingid = deskcallingid;
	}
	public String getTestGroupName() {
		return testGroupName;
	}
	public void setTestGroupName(String testGroupName) {
		this.testGroupName = testGroupName;
	}
	public String getIspidshow() {
		return ispidshow;
	}
	public void setIspidshow(String ispidshow) {
		this.ispidshow = ispidshow;
	}
	public String getPiddata() {
		return piddata;
	}
	public void setPiddata(String piddata) {
		this.piddata = piddata;
	}
	public String getPidtestcontains() {
		return pidtestcontains;
	}
	public void setPidtestcontains(String pidtestcontains) {
		this.pidtestcontains = pidtestcontains;
	}
	public String getAptTyp() {
		return aptTyp;
	}
	public void setAptTyp(String aptTyp) {
		this.aptTyp = aptTyp;
	}
	public String getLabbasedaptdatetime() {
		return labbasedaptdatetime;
	}
	public void setLabbasedaptdatetime(String labbasedaptdatetime) {
		this.labbasedaptdatetime = labbasedaptdatetime;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getIslabappointmentbased() {
		return islabappointmentbased;
	}
	public void setIslabappointmentbased(String islabappointmentbased) {
		this.islabappointmentbased = islabappointmentbased;
	}
	public String getViewCode() {
		return viewCode;
	}
	public void setViewCode(String viewCode) {
		this.viewCode = viewCode;
	}
	public String getViewName() {
		return viewName;
	}
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
	public String getViewscount() {
		return viewscount;
	}
	public void setViewscount(String viewscount) {
		this.viewscount = viewscount;
	}
	public String getXraycount() {
		return xraycount;
	}
	public void setXraycount(String xraycount) {
		this.xraycount = xraycount;
	}
	public String getTotalviewcount() {
		return totalviewcount;
	}
	public void setTotalviewcount(String totalviewcount) {
		this.totalviewcount = totalviewcount;
	}
	public String getTotalreqviewcount() {
		return totalreqviewcount;
	}
	public void setTotalreqviewcount(String totalreqviewcount) {
		this.totalreqviewcount = totalreqviewcount;
	}
	public String getTotalreqviewtyp() {
		return totalreqviewtyp;
	}
	public void setTotalreqviewtyp(String totalreqviewtyp) {
		this.totalreqviewtyp = totalreqviewtyp;
	}
	public String getSugartestcode() {
		return sugartestcode;
	}
	public void setSugartestcode(String sugartestcode) {
		this.sugartestcode = sugartestcode;
	}
	public String getTariiff() {
		return tariiff;
	}
	public void setTariiff(String tariiff) {
		this.tariiff = tariiff;
	}
	public String getRequisitionraisedtrough() {
		return requisitionraisedtrough;
	}
	public void setRequisitionraisedtrough(String requisitionraisedtrough) {
		this.requisitionraisedtrough = requisitionraisedtrough;
	}
	public String getTestrate() {
		return testrate;
	}
	public void setTestrate(String testrate) {
		this.testrate = testrate;
	}
	public String getAdvisedByDocNo() {
		return advisedByDocNo;
	}
	public void setAdvisedByDocNo(String advisedByDocNo) {
		this.advisedByDocNo = advisedByDocNo;
	}
	public String[] getChief_complaints_code() {
		return chief_complaints_code;
	}
	public void setChief_complaints_code(String[] chief_complaints_code) {
		this.chief_complaints_code = chief_complaints_code;
	}
	public String[] getChief_complaints_name() {
		return chief_complaints_name;
	}
	public void setChief_complaints_name(String[] chief_complaints_name) {
		this.chief_complaints_name = chief_complaints_name;
	}
	public String[] getDiagnosis_code() {
		return diagnosis_code;
	}
	public void setDiagnosis_code(String[] diagnosis_code) {
		this.diagnosis_code = diagnosis_code;
	}
	public String[] getDiagnosis_name() {
		return diagnosis_name;
	}
	public void setDiagnosis_name(String[] diagnosis_name) {
		this.diagnosis_name = diagnosis_name;
	}
	public String getIs_pregnant() {
		return is_pregnant;
	}
	public void setIs_pregnant(String is_pregnant) {
		this.is_pregnant = is_pregnant;
	}
	public String getIs_mlc() {
		return is_mlc;
	}
	public void setIs_mlc(String is_mlc) {
		this.is_mlc = is_mlc;
	}
	public String getIs_vip() {
		return is_vip;
	}
	public void setIs_vip(String is_vip) {
		this.is_vip = is_vip;
	}
	public String getIs_dead() {
		return is_dead;
	}
	public void setIs_dead(String is_dead) {
		this.is_dead = is_dead;
	}
	public String getIs_newborn() {
		return is_newborn;
	}
	public void setIs_newborn(String is_newborn) {
		this.is_newborn = is_newborn;
	}
	public String getInstruction_testwise() {
		return instruction_testwise;
	}
	public void setInstruction_testwise(String instruction_testwise) {
		this.instruction_testwise = instruction_testwise;
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
	public String getTestwiseinst() {
		return testwiseinst;
	}
	public void setTestwiseinst(String testwiseinst) {
		this.testwiseinst = testwiseinst;
	}
	public String getAdvisedByDocName() {
		return advisedByDocName;
	}
	public void setAdvisedByDocName(String advisedByDocName) {
		this.advisedByDocName = advisedByDocName;
	}
	public String getVisitReason() {
		return visitReason;
	}
	public void setVisitReason(String visitReason) {
		this.visitReason = visitReason;
	}
	public String getChiefname() {
		return chiefname;
	}
	public void setChiefname(String chiefname) {
		this.chiefname = chiefname;
	}
	public String getDiagname() {
		return diagname;
	}
	public void setDiagname(String diagname) {
		this.diagname = diagname;
	}



	
	
	
}
