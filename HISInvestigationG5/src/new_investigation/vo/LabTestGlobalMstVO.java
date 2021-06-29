package new_investigation.vo;

import hisglobal.vo.ValueObject;

public class LabTestGlobalMstVO extends ValueObject {
	
	//private String hmode;
	private String testCode;
	private String labCode;
	private String testDays;
	private String noOfTest;
	private String isTestAvailable;
	private String isAppointment;
	private String entryDate;
	private String testOrder;
	private String isMultisession;
	private String isMandatoryReq;
	private String isRequisitionFormNeeded;
	private String lstModDate;
	private String isSampleFormNeeded;
	private String genderBound;
	private String ageBound;
	private String lowAgeRange;
	private String highAgeRange;
	private String isSecurePrinting;
	private String textHeader;
	private String isGrossingReq;
    private String footerText;
    private String isFilmReq;
    private String isConsent;
    private String testMethod;
    private String isConfidential;
    private String samCallArea;
    private String testPrintingType;
    private String printingTemplateId;
    private String reportAvailableAfter;
    private String headerText;
    private String userTestCode;
	private String isOPDBayDoctorDesk;
    private String isIPDDoctorDesk;
    private String isOPDDoctorDesk;
    private String deskProperties;
    private String machineId;
    private String machineName;
    private String instructionPat;
    private String instructionPost;
    private String instructionTech;
    private String instructionColl;
    
    private String isNablAccritedTest;
    
    private String requisitionForm;
    private String printedWith;
    private String printingTemplateCode;
    
    private String preferenceOrder;
    private String IsPID;
    
    
    public String getIsPID() {
		return IsPID;
	}
	public void setIsPID(String isPID) {
		IsPID = isPID;
	}
	public String getMachineName() {
		return machineName;
	}
	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}
	public String getMachineId() {
		return machineId;
	}
	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}
	public String getDeskProperties() {
		return deskProperties;
	}
	public void setDeskProperties(String deskProperties) {
		this.deskProperties = deskProperties;
	}
	public String getIsOPDBayDoctorDesk() {
		return isOPDBayDoctorDesk;
	}
	public void setIsOPDBayDoctorDesk(String isOPDBayDoctorDesk) {
		this.isOPDBayDoctorDesk = isOPDBayDoctorDesk;
	}
	public String getIsIPDDoctorDesk() {
		return isIPDDoctorDesk;
	}
	public void setIsIPDDoctorDesk(String isIPDDoctorDesk) {
		this.isIPDDoctorDesk = isIPDDoctorDesk;
	}
	public String getIsOPDDoctorDesk() {
		return isOPDDoctorDesk;
	}
	public void setIsOPDDoctorDesk(String isOPDDoctorDesk) {
		this.isOPDDoctorDesk = isOPDDoctorDesk;
	}
	public String getUserTestCode() {
		return userTestCode;
	}
	public void setUserTestCode(String userTestCode) {
		this.userTestCode = userTestCode;
	}
	private String labName;
    private String testName;
    private String count;
	/*public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}*/
	public String getTestCode() {
		return testCode;
	}
	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}
	public String getLabCode() {
		return labCode;
	}
	public void setLabCode(String labCode) {
		this.labCode = labCode;
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
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
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
	public String getLstModDate() {
		return lstModDate;
	}
	public void setLstModDate(String lstModDate) {
		this.lstModDate = lstModDate;
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
	public String getTextHeader() {
		return textHeader;
	}
	public void setTextHeader(String textHeader) {
		this.textHeader = textHeader;
	}
	public String getIsGrossingReq() {
		return isGrossingReq;
	}
	public void setIsGrossingReq(String isGrossingReq) {
		this.isGrossingReq = isGrossingReq;
	}
	public String getFooterText() {
		return footerText;
	}
	public void setFooterText(String footerText) {
		this.footerText = footerText;
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
	public String getHeaderText() {
		return headerText;
	}
	public void setHeaderText(String headerText) {
		this.headerText = headerText;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getInstructionPat() {
		return instructionPat;
	}
	public void setInstructionPat(String instructionPat) {
		this.instructionPat = instructionPat;
	}
	public String getInstructionPost() {
		return instructionPost;
	}
	public void setInstructionPost(String instructionPost) {
		this.instructionPost = instructionPost;
	}
	public String getInstructionTech() {
		return instructionTech;
	}
	public void setInstructionTech(String instructionTech) {
		this.instructionTech = instructionTech;
	}
	public String getInstructionColl() {
		return instructionColl;
	}
	public void setInstructionColl(String instructionColl) {
		this.instructionColl = instructionColl;
	}
	public String getIsNablAccritedTest() {
		return isNablAccritedTest;
	}
	public void setIsNablAccritedTest(String isNablAccritedTest) {
		this.isNablAccritedTest = isNablAccritedTest;
	}
	public String getRequisitionForm() {
		return requisitionForm;
	}
	public void setRequisitionForm(String requisitionForm) {
		this.requisitionForm = requisitionForm;
	}
	public String getPrintedWith() {
		return printedWith;
	}
	public void setPrintedWith(String printedWith) {
		this.printedWith = printedWith;
	}
	public String getPrintingTemplateCode() {
		return printingTemplateCode;
	}
	public void setPrintingTemplateCode(String printingTemplateCode) {
		this.printingTemplateCode = printingTemplateCode;
	}
	public String getPreferenceOrder() {
		return preferenceOrder;
	}
	public void setPreferenceOrder(String preferenceOrder) {
		this.preferenceOrder = preferenceOrder;
	}
    
    
    

}
