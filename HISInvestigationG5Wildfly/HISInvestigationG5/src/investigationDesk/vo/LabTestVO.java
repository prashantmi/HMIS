package investigationDesk.vo;

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
	 
	//sample coll vo
		private String uomCode;
		private String containerCode;
		private String containerVolume;
		private String tempSampleNo;
		private String reqNo;
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
		 private String patType;
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
			private String appoitmentNo;
			private String hidAptNo;
			private String offlineAptDtl;
			private String lengendStatus;
			
			 
			 private String prvLabCode;
			 private String prvTestCode;
			 private String prvReqStatus;

			 private String deskProperties;
			 private String raiseAdvise;
			private String singleSample;
			private String configSeq;
			private String configLab;
			private String configTest;
			private String configType;


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
			public String getHideAptNo() {
				return hideAptNo;
			}
			public void setHideAptNo(String hideAptNo) {
				this.hideAptNo = hideAptNo;
			}
			public String getReqDateHeaderTable() {
				return reqDateHeaderTable;
			}
			public void setReqDateHeaderTable(String reqDateHeaderTable) {
				this.reqDateHeaderTable = reqDateHeaderTable;
			}
			private String hideAptNo;
			 private String reqDateHeaderTable;
	
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
	
	private String testGroupType;
	
	private String appointmentDate;
	
	private String appointmentTime;
	private String reqDate;
	private String resultDate;
	private String admNo;
	private String resURL;
	private String visitNo;
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
	public String getReqDate() {
		return reqDate;
	}
	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}
	public String getResultDate() {
		return resultDate;
	}
	public void setResultDate(String resultDate) {
		this.resultDate = resultDate;
	}
	public String getAdmNo() {
		return admNo;
	}
	public void setAdmNo(String admNo) {
		this.admNo = admNo;
	}
	public String getResURL() {
		return resURL;
	}
	public void setResURL(String resURL) {
		this.resURL = resURL;
	}
	public String getVisitNo() {
		return visitNo;
	}
	public void setVisitNo(String visitNo) {
		this.visitNo = visitNo;
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
	public String getReqNo() {
		return reqNo;
	}
	public void setReqNo(String reqNo) {
		this.reqNo = reqNo;
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
	public String getSingleSample() {
		return singleSample;
	}
	public void setSingleSample(String singleSample) {
		this.singleSample = singleSample;
	}
	public String getConfigSeq() {
		return configSeq;
	}
	public void setConfigSeq(String configSeq) {
		this.configSeq = configSeq;
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

	 

	 
	
}
