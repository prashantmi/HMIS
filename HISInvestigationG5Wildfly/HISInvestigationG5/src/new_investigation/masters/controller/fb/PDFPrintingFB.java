package new_investigation.masters.controller.fb;


import org.apache.struts.action.ActionForm;

import new_investigation.InvestigationConfig;
//import investigation.usefulmethods.DateFormatter;
import new_investigation.Helper.DateFormatter;

public class PDFPrintingFB   extends ActionForm {
		private String sampleNo;
		private String crNo;
		private String resultPrintingType="PATIENTWISERESULTPRINTING";
		private String patientCategory;
		private String testCode;
		private String testName;
		private String fromDate;
		private String toDate;
		private String selectedLab;
		private String selectedSample;
		private String selectedLabTestCode;
		private String hmode;
		private String searchHmode;//Nitin Tyagi
		private String patientcategory;
		private String currentPageNo="1";
		private String currentblock="1";
		public static final String recordPerPage="10";
		public static final String pagesPerBlock="10";
		private String[] selectedWorkOrderNo;
		private String sessionOptionSampleList;
		public String pageString="";
		private String selectedLabSampleCode;
		private String selectedPatientCode;
		//this field belongs to PATIENTWISERESULVALIDATION
		private String patientCrNo;
		private String firstName;
		private String middleName;
		private String lastName;
		private String referredByHospitalName ;
		private String sessionId; 
		private String referredByHospitalCode="-1";
		private String sampleTest;
		private String labTestSampleCode;
		private String optionRefHospital;
		private String optionTest;
		private String labNameReport;
		private String resultValidatedBy;
		private String validatedBy;
		private String patGenderShortName;
		private String wardName;
		private String bedName;
		private String roomName;
		private String visitDate;
		private String sampleName;
		private String printedWith="reportWithNormalValues.xsl";
		private String departmentName;
		private String laboratoryName;
		private String resultEnteredBy="";
		public String getPrintedWith() {
			return printedWith;
		}
		public void setPrintedWith(String printedWith) {
			this.printedWith = printedWith;
		}
		public String getPatGenderShortName() {
			return patGenderShortName;
		}
		public void setPatGenderShortName(String patGenderShortName) {
			this.patGenderShortName = patGenderShortName;
		}
		public String getWardName() {
			return wardName;
		}
		public void setWardName(String wardName) {
			this.wardName = wardName;
		}
		public String getBedName() {
			return bedName;
		}
		public void setBedName(String bedName) {
			this.bedName = bedName;
		}
		public String getRoomName() {
			return roomName;
		}
		public void setRoomName(String roomName) {
			this.roomName = roomName;
		}
		public String getVisitDate() {
			return visitDate;
		}
		public void setVisitDate(String visitDate) {
			this.visitDate = visitDate;
		}
		public String getSampleName() {
			return sampleName;
		}
		public void setSampleName(String sampleName) {
			this.sampleName = sampleName;
		}
		public String getValidatedBy() {
			return validatedBy;
		}
		public void setValidatedBy(String validatedBy) {
			this.validatedBy = validatedBy;
		}
		public String getResultValidatedBy() {
			return resultValidatedBy;
		}
		public void setResultValidatedBy(String resultValidatedBy) {
			this.resultValidatedBy = resultValidatedBy;
		}
		public String getPageString() {
			return pageString;
		}
		public void setPageString(String pageString) {
			this.pageString = pageString;
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
		
		public  String getRecordPerPage() {
			return recordPerPage;
		}
		public  String getPagesPerBlock() {
			return pagesPerBlock;
		}
		public String getPatientcategory() {
			return patientcategory;
		}
		public void setPatientcategory(String patientcategory) {
			this.patientcategory = patientcategory;
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
	
		public String getSelectedLab() {
			return selectedLab;
		}
		public void setSelectedLab(String selectedLab) {
			this.selectedLab = selectedLab;
		}
		public String getSelectedSample() {
			return selectedSample;
		}
		public void setSelectedSample(String selectedSample) {
			this.selectedSample = selectedSample;
		}
		
		public String getSelectedLabTestCode() {
			return selectedLabTestCode;
		}
		public void setSelectedLabTestCode(String selectedLabTestCode) {
			this.selectedLabTestCode = selectedLabTestCode;
		}
		public String getToDate() {
			return toDate;
		}
	
		public String getCrNo() {
			return crNo;
		}
		public void setCrNo(String crNo) {
			this.crNo = crNo;
		}
		
	   public String getPatientCategory() {
			return patientCategory;
		}
		public void setPatientCategory(String patientCategory) {
			this.patientCategory = patientCategory;
		}
		public String getSampleNo() {
			return sampleNo;
		}
		public void setSampleNo(String sampleNo) {
			this.sampleNo = sampleNo;
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
		public String[] getSelectedWorkOrderNo() {
			return selectedWorkOrderNo;
		}
		public void setSelectedWorkOrderNo(String[] selectedWorkOrderNo) {
			this.selectedWorkOrderNo = selectedWorkOrderNo;
		}
		public String getSessionOptionSampleList() {
			return sessionOptionSampleList;
		}
		public void setSessionOptionSampleList(String sessionOptionSampleList) {
			this.sessionOptionSampleList = sessionOptionSampleList;
		}
		public String getSelectedLabSampleCode() {
			return selectedLabSampleCode;
		}
		public void setSelectedLabSampleCode(String selectedLabSampleCode) {
			this.selectedLabSampleCode = selectedLabSampleCode;
		}
		public String getSelectedPatientCode() {
			return selectedPatientCode;
		}
		public void setSelectedPatientCode(String selectedPatientCode) {
			this.selectedPatientCode = selectedPatientCode;
		}
		public String getPatientCrNo() {
			return patientCrNo;
		}
		public void setPatientCrNo(String patientCrNo) {
			this.patientCrNo = patientCrNo;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getMiddleName() {
			return middleName;
		}
		public void setMiddleName(String middleName) {
			this.middleName = middleName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getReferredByHospitalName() {
			return referredByHospitalName;
		}
		public void setReferredByHospitalName(String hospitalName) {
			this.referredByHospitalName = hospitalName;
		}
		public String getReferredByHospitalCode() {
			return referredByHospitalCode;
		}
		public void setReferredByHospitalCode(String referredByHospitalCode) {
			this.referredByHospitalCode = referredByHospitalCode;
		}
		public String getSearchHmode() {
			return searchHmode;
		}
		public void setSearchHmode(String searchHmode) {
			this.searchHmode = searchHmode;
		}
		public String getSessionId() {
			return sessionId;
		}
		public void setSessionId(String sessionId) {
			this.sessionId = sessionId;
		}
		
		public String getSampleTest() {
			return sampleTest;
		}
		public void setSampleTest(String sampleTest) {
			this.sampleTest = sampleTest;
		}
		public String getLabTestSampleCode() {
			return labTestSampleCode;
		}
		public void setLabTestSampleCode(String labTestSampleCode) {
			this.labTestSampleCode = labTestSampleCode;
		}
		public String getOptionRefHospital() {
			return optionRefHospital;
		}
		public void setOptionRefHospital(String optionRefHospital) {
			this.optionRefHospital = optionRefHospital;
		}
		public String getOptionTest() {
			return optionTest;
		}
		public void setOptionTest(String optionTest) {
			this.optionTest = optionTest;
		}
		public String getResultPrintingType() {
			return resultPrintingType;
		}
		public void setResultPrintingType(String resultPrintingType) {
			this.resultPrintingType = resultPrintingType;
		}
		public String getLabNameReport() {
			return labNameReport;
		}
		public void setLabNameReport(String labNameReport) {
			this.labNameReport = labNameReport;
		}
		public String getDepartmentName() {
			return departmentName;
		}
		public void setDepartmentName(String departmentName) {
			this.departmentName = departmentName;
		}
		public String getLaboratoryName() {
			return laboratoryName;
		}
		public void setLaboratoryName(String laboratoryName) {
			this.laboratoryName = laboratoryName;
		}
		public void setToDate(String toDate) {
			this.toDate = DateFormatter.getDate(toDate, InvestigationConfig.formDateFormate, InvestigationConfig.databaseDateFormate);
		}
		public void setFromDate(String fromDate) {
			this.fromDate = DateFormatter.getDate(fromDate,InvestigationConfig.formDateFormate, InvestigationConfig.databaseDateFormate);
		}
		
		
		
}
