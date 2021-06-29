package new_investigation.masters.controller.fb;

import org.apache.struts.action.ActionForm;

public class LabConfigratorMstFB extends ActionForm {
	
	
	private String checklistID;
	private String slNO;
	private String checklist;
	private String isCompulsory;
	private String hmode;
	private String chk[];
	private String checklistPrevious;
	private String sampleChkBox[];
	private String testChkBox[];
	private String mandChkBox[];
	private String groupChkBox[];
	private String cannedChkBox[];
	private String macroChkBox[];
	
	
	private String labCode;
	private String hospitalCode;
	private String laboratoryName;
	private String labShortSName;
	private String department;
	private String sampStartHr;
	private String sampStartMin;
	private String sampEndHr;
	private String sampEndMin;
	private String labType;
	private String labWorkingDays;
	private String numberofTests;
	private String headertext;
	private String footerText;
	private String remarks;
	private String isActive;
	private String sampleCStrtTime;
	private String sampleCEndTime;
	private String deptCode;
	private String systemDate;
	private String systemTimeInHr;
	private String systemTimeInMin;
	private String time; 
	private String systemTime; 
	private String PreviousLaboratoryName;
	private String sampleNumberConfig;
	private String selectedChk;
	private String labNumberConfig;
	private String location;
	private String labIncharge;
	
	private String testCode;
	private String testName;
	private String sampleCode;
	private String sampleName;
	private String mandCode;
	private String mandName;
	
	private String sampleQty;
	private String containerCode;
	private String uomCode;
	private String sampleCollRemarks;
	private String isDefaultSample;
	
	
	private String testDays;
	private String noOfTest;
	private String isTestAvailable;
	private String isAppointment;
	private String testOrder;
	private String isMultisession;
	private String isMandatoryReq;
	private String isRequisitionFormNeeded;
	private String isSampleFormNeeded;
	private String genderBound;
	private String ageBound;
	private String lowAgeRange;
	private String highAgeRange;
	private String isSecurePrinting;
	private String isGrossingReq;
    private String isFilmReq;
    private String isConsent;
    private String testMethod;
    private String isConfidential;
    private String samCallArea;
    private String testPrintingType;
    private String printingTemplateId;
    private String reportAvailableAfter;

	
	public String getChecklistID() {
		return checklistID;
	}
	public void setChecklistID(String checklistID) {
		this.checklistID = checklistID;
	}
	public String getSlNO() {
		return slNO;
	}
	public void setSlNO(String slNO) {
		this.slNO = slNO;
	}
	public String getChecklist() {
		return checklist;
	}
	public void setChecklist(String checklist) {
		this.checklist = checklist;
	}
	public String getIsCompulsory() {
		return isCompulsory;
	}
	public void setIsCompulsory(String isCompulsory) {
		this.isCompulsory = isCompulsory;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String[] getChk() {
		return chk;
	}
	public void setChk(String[] chk) {
		this.chk = chk;
	}
	public String getChecklistPrevious() {
		return checklistPrevious;
	}
	public void setChecklistPrevious(String checklistPrevious) {
		this.checklistPrevious = checklistPrevious;
	}
	public String getLabCode() {
		return labCode;
	}
	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getLaboratoryName() {
		return laboratoryName;
	}
	public void setLaboratoryName(String laboratoryName) {
		this.laboratoryName = laboratoryName;
	}
	public String getLabShortSName() {
		return labShortSName;
	}
	public void setLabShortSName(String labShortSName) {
		this.labShortSName = labShortSName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getSampStartHr() {
		return sampStartHr;
	}
	public void setSampStartHr(String sampStartHr) {
		this.sampStartHr = sampStartHr;
	}
	public String getSampStartMin() {
		return sampStartMin;
	}
	public void setSampStartMin(String sampStartMin) {
		this.sampStartMin = sampStartMin;
	}
	public String getSampEndHr() {
		return sampEndHr;
	}
	public void setSampEndHr(String sampEndHr) {
		this.sampEndHr = sampEndHr;
	}
	public String getSampEndMin() {
		return sampEndMin;
	}
	public void setSampEndMin(String sampEndMin) {
		this.sampEndMin = sampEndMin;
	}
	public String getLabType() {
		return labType;
	}
	public void setLabType(String labType) {
		this.labType = labType;
	}
	public String getLabWorkingDays() {
		return labWorkingDays;
	}
	public void setLabWorkingDays(String labWorkingDays) {
		this.labWorkingDays = labWorkingDays;
	}
	public String getNumberofTests() {
		return numberofTests;
	}
	public void setNumberofTests(String numberofTests) {
		this.numberofTests = numberofTests;
	}
	public String getHeadertext() {
		return headertext;
	}
	public void setHeadertext(String headertext) {
		this.headertext = headertext;
	}
	public String getFooterText() {
		return footerText;
	}
	public void setFooterText(String footerText) {
		this.footerText = footerText;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getSampleCStrtTime() {
		return sampleCStrtTime;
	}
	public void setSampleCStrtTime(String sampleCStrtTime) {
		this.sampleCStrtTime = sampleCStrtTime;
	}
	public String getSampleCEndTime() {
		return sampleCEndTime;
	}
	public void setSampleCEndTime(String sampleCEndTime) {
		this.sampleCEndTime = sampleCEndTime;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getSystemDate() {
		return systemDate;
	}
	public void setSystemDate(String systemDate) {
		this.systemDate = systemDate;
	}
	public String getSystemTimeInHr() {
		return systemTimeInHr;
	}
	public void setSystemTimeInHr(String systemTimeInHr) {
		this.systemTimeInHr = systemTimeInHr;
	}
	public String getSystemTimeInMin() {
		return systemTimeInMin;
	}
	public void setSystemTimeInMin(String systemTimeInMin) {
		this.systemTimeInMin = systemTimeInMin;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSystemTime() {
		return systemTime;
	}
	public void setSystemTime(String systemTime) {
		this.systemTime = systemTime;
	}
	public String getPreviousLaboratoryName() {
		return PreviousLaboratoryName;
	}
	public void setPreviousLaboratoryName(String previousLaboratoryName) {
		PreviousLaboratoryName = previousLaboratoryName;
	}
	public String getSampleNumberConfig() {
		return sampleNumberConfig;
	}
	public void setSampleNumberConfig(String sampleNumberConfig) {
		this.sampleNumberConfig = sampleNumberConfig;
	}
	public String getSelectedChk() {
		return selectedChk;
	}
	public void setSelectedChk(String selectedChk) {
		this.selectedChk = selectedChk;
	}
	public String getLabNumberConfig() {
		return labNumberConfig;
	}
	public void setLabNumberConfig(String labNumberConfig) {
		this.labNumberConfig = labNumberConfig;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getLabIncharge() {
		return labIncharge;
	}
	public void setLabIncharge(String labIncharge) {
		this.labIncharge = labIncharge;
	}
	public String[] getSampleChkBox() {
		return sampleChkBox;
	}
	public void setSampleChkBox(String[] sampleChkBox) {
		this.sampleChkBox = sampleChkBox;
	}
	public String[] getTestChkBox() {
		return testChkBox;
	}
	public void setTestChkBox(String[] testChkBox) {
		this.testChkBox = testChkBox;
	}
	public String[] getMandChkBox() {
		return mandChkBox;
	}
	public void setMandChkBox(String[] mandChkBox) {
		this.mandChkBox = mandChkBox;
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
	public String getMandCode() {
		return mandCode;
	}
	public void setMandCode(String mandCode) {
		this.mandCode = mandCode;
	}
	public String getMandName() {
		return mandName;
	}
	public void setMandName(String mandName) {
		this.mandName = mandName;
	}
	public String getSampleQty() {
		return sampleQty;
	}
	public void setSampleQty(String sampleQty) {
		this.sampleQty = sampleQty;
	}
	public String getContainerCode() {
		return containerCode;
	}
	public void setContainerCode(String containerCode) {
		this.containerCode = containerCode;
	}
	public String getUomCode() {
		return uomCode;
	}
	public void setUomCode(String uomCode) {
		this.uomCode = uomCode;
	}
	public String getSampleCollRemarks() {
		return sampleCollRemarks;
	}
	public void setSampleCollRemarks(String sampleCollRemarks) {
		this.sampleCollRemarks = sampleCollRemarks;
	}
	public String getIsDefaultSample() {
		return isDefaultSample;
	}
	public void setIsDefaultSample(String isDefaultSample) {
		this.isDefaultSample = isDefaultSample;
	}
	public String getTestDays() {
		return testDays;
	}
	public void setTestDays(String testDays) {
		this.testDays = testDays;
	}
	public String getNoOfTest() {
		return noOfTest;
	}
	public void setNoOfTest(String noOfTest) {
		this.noOfTest = noOfTest;
	}
	public String getIsTestAvailable() {
		return isTestAvailable;
	}
	public void setIsTestAvailable(String isTestAvailable) {
		this.isTestAvailable = isTestAvailable;
	}
	public String getIsAppointment() {
		return isAppointment;
	}
	public void setIsAppointment(String isAppointment) {
		this.isAppointment = isAppointment;
	}
	public String getTestOrder() {
		return testOrder;
	}
	public void setTestOrder(String testOrder) {
		this.testOrder = testOrder;
	}
	public String getIsMultisession() {
		return isMultisession;
	}
	public void setIsMultisession(String isMultisession) {
		this.isMultisession = isMultisession;
	}
	public String getIsMandatoryReq() {
		return isMandatoryReq;
	}
	public void setIsMandatoryReq(String isMandatoryReq) {
		this.isMandatoryReq = isMandatoryReq;
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
	public String getGenderBound() {
		return genderBound;
	}
	public void setGenderBound(String genderBound) {
		this.genderBound = genderBound;
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
	public String getIsSecurePrinting() {
		return isSecurePrinting;
	}
	public void setIsSecurePrinting(String isSecurePrinting) {
		this.isSecurePrinting = isSecurePrinting;
	}
	public String getIsGrossingReq() {
		return isGrossingReq;
	}
	public void setIsGrossingReq(String isGrossingReq) {
		this.isGrossingReq = isGrossingReq;
	}
	public String getIsFilmReq() {
		return isFilmReq;
	}
	public void setIsFilmReq(String isFilmReq) {
		this.isFilmReq = isFilmReq;
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
	public String getSamCallArea() {
		return samCallArea;
	}
	public void setSamCallArea(String samCallArea) {
		this.samCallArea = samCallArea;
	}
	public String getTestPrintingType() {
		return testPrintingType;
	}
	public void setTestPrintingType(String testPrintingType) {
		this.testPrintingType = testPrintingType;
	}
	public String getPrintingTemplateId() {
		return printingTemplateId;
	}
	public void setPrintingTemplateId(String printingTemplateId) {
		this.printingTemplateId = printingTemplateId;
	}
	public String getReportAvailableAfter() {
		return reportAvailableAfter;
	}
	public void setReportAvailableAfter(String reportAvailableAfter) {
		this.reportAvailableAfter = reportAvailableAfter;
	}
	public String[] getGroupChkBox() {
		return groupChkBox;
	}
	public void setGroupChkBox(String[] groupChkBox) {
		this.groupChkBox = groupChkBox;
	}
	public String[] getCannedChkBox() {
		return cannedChkBox;
	}
	public void setCannedChkBox(String[] cannedChkBox) {
		this.cannedChkBox = cannedChkBox;
	}
	public String[] getMacroChkBox() {
		return macroChkBox;
	}
	public void setMacroChkBox(String[] macroChkBox) {
		this.macroChkBox = macroChkBox;
	}
	
	

}
