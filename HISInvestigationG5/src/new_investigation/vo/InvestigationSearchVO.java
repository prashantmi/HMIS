package new_investigation.vo;

import hisglobal.vo.ValueObject;

public class InvestigationSearchVO extends ValueObject {

	
	private String resultValidationType;
	private String resultReValidationType;
	private String resultModificationType;
	private String resultPrintingType;
	private String resultDuplicatePrintingType;
	private String labTestSampleCode;
	private String referredByHospitalCode;
	private String referredByHospitalName;
	private String selectedLab;
	private String firstName;
	private String middleName;
	private String lastName;
	private String selectedLabSampleCode;
	private String selectedLabTestCode;
	private String fromDate;
	private String toDate;
	private String patientCrNo;
	private String sampleNo;
	private String resultEntryType;
	private String hmode;
	private String labCode;
	private String packingListType;
	private String mode;
	private String selectedPackingList;
	private String selectedUserSampleNo;
	private String selectedBillNo;
	private String patientType;
	private String raisedDeptCode;
	private String selectedTestGroup;
	
	private String searchLabName;
	private String searchTestName; 
	
	private String searchTestGroupName; 
	private String searchTestGroup;
	private String tstOrTestGroupValue;
	private String testCodeWise;
	private String delLabCode;
	private String delTestCode;
	private String dupTestCode;
	private String dupLabCode;
	
	private String appointmentNo;
	private String errorMessage;
	private String aptNo;
	
	//check if values in session empty or not. 1- empty 0- not empty
	private String labEmpty="1";
	private String testEmpty="1";
	private String testCodeEmpty="1";
	private String groupCodeEmpty="1";
	private String userGroupCodeWise;
	private String isLabAvailable;
	private String reqSampleShortName;
	
	private String isAddendum;
	private String requisitionNo;
	private String testSearchKeyword;
	private String testCodesSearchKeyword;
	private String labwisetestteriff;
    
    private String searchTestNamelabwise;
	private String requisitingtypeforbilling;
	private String warcode;
	private String patcatcode;
	private String usertestcode;
	private String groupcode;
	private String isamountsufficientflag;
	    private String deletedtestdataviaresultentry;;
	    private String isbilledornot;;
	    private String ispidexist;
	    
	    private String patAdmNo;

	    
	    private String totalviewcount;
	    private String totalreqviewcount;
	    private String totalreqviewtyp;
	    private String raisethrough;
	    private String testrates;

	    
	public String getUserGroupCodeWise() {
		return userGroupCodeWise;
	}
	public void setUserGroupCodeWise(String userGroupCodeWise) {
		this.userGroupCodeWise = userGroupCodeWise;
	}
	public String getGroupCodeEmpty() {
		return groupCodeEmpty;
	}
	public void setGroupCodeEmpty(String groupCodeEmpty) {
		this.groupCodeEmpty = groupCodeEmpty;
	}
	public String getAptNo() {
		return aptNo;
	}
	public void setAptNo(String aptNo) {
		this.aptNo = aptNo;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getAppointmentNo() {
		return appointmentNo;
	}
	public void setAppointmentNo(String appointmentNo) {
		this.appointmentNo = appointmentNo;
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
	public String getTestCodeWise() {
		return testCodeWise;
	}
	public void setTestCodeWise(String testCodeWise) {
		this.testCodeWise = testCodeWise;
	}
	public String getTstOrTestGroupValue() {
		return tstOrTestGroupValue;
	}
	public void setTstOrTestGroupValue(String tstOrTestGroupValue) {
		this.tstOrTestGroupValue = tstOrTestGroupValue;
	}
	public String getSearchTestGroup() {
		return searchTestGroup;
	}
	public void setSearchTestGroup(String searchTestGroup) {
		this.searchTestGroup = searchTestGroup;
	}
	private String bookMarkCode; 
	private String testCode;

	
	public String getPatientType() {
		return patientType;
	}
	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}
	public String getSelectedPackingList() {
		return selectedPackingList;
	}
	public void setSelectedPackingList(String selectedPackingList) {
		this.selectedPackingList = selectedPackingList;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getResultValidationType() {
		return resultValidationType;
	}
	public void setResultValidationType(String resultValidationType) {
		this.resultValidationType = resultValidationType;
	}
	public String getResultReValidationType() {
		return resultReValidationType;
	}
	public void setResultReValidationType(String resultReValidationType) {
		this.resultReValidationType = resultReValidationType;
	}
	public String getResultModificationType() {
		return resultModificationType;
	}
	public void setResultModificationType(String resultModificationType) {
		this.resultModificationType = resultModificationType;
	}
	public String getResultPrintingType() {
		return resultPrintingType;
	}
	public void setResultPrintingType(String resultPrintingType) {
		this.resultPrintingType = resultPrintingType;
	}
	public String getResultDuplicatePrintingType() {
		return resultDuplicatePrintingType;
	}
	public void setResultDuplicatePrintingType(String resultDuplicatePrintingType) {
		this.resultDuplicatePrintingType = resultDuplicatePrintingType;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getSelectedLabTestCode() {
		return selectedLabTestCode;
	}
	public void setSelectedLabTestCode(String selectedLabTestCode) {
		this.selectedLabTestCode = selectedLabTestCode;
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
	public String getPatientCrNo() {
		return patientCrNo;
	}
	public void setPatientCrNo(String patientCrNo) {
		this.patientCrNo = patientCrNo;
	}
	public String getSampleNo() {
		return sampleNo;
	}
	public void setSampleNo(String sampleNo) {
		this.sampleNo = sampleNo;
	}
	public String getResultEntryType() {
		return resultEntryType;
	}
	public void setResultEntryType(String resultEntryType) {
		this.resultEntryType = resultEntryType;
	}
	public String getLabTestSampleCode() {
		return labTestSampleCode;
	}
	public void setLabTestSampleCode(String labTestSampleCode) {
		this.labTestSampleCode = labTestSampleCode;
	}
	public String getReferredByHospitalCode() {
		return referredByHospitalCode;
	}
	public void setReferredByHospitalCode(String referredByHospitalCode) {
		this.referredByHospitalCode = referredByHospitalCode;
	}
	public String getReferredByHospitalName() {
		return referredByHospitalName;
	}
	public void setReferredByHospitalName(String referredByHospitalName) {
		this.referredByHospitalName = referredByHospitalName;
	}
	public String getSelectedLab() {
		return selectedLab;
	}
	public void setSelectedLab(String selectedLab) {
		this.selectedLab = selectedLab;
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
	public String getSelectedLabSampleCode() {
		return selectedLabSampleCode;
	}
	public void setSelectedLabSampleCode(String selectedLabSampleCode) {
		this.selectedLabSampleCode = selectedLabSampleCode;
	}
	public String getLabCode() {
		return labCode;
	}
	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}
	public String getPackingListType() {
		return packingListType;
	}
	public void setPackingListType(String packingListType) {
		this.packingListType = packingListType;
	}
	public String getRaisedDeptCode() {
		return raisedDeptCode;
	}
	public void setRaisedDeptCode(String raisedDeptCode) {
		this.raisedDeptCode = raisedDeptCode;
	}
	public String getSelectedUserSampleNo() {
		return selectedUserSampleNo;
	}
	public void setSelectedUserSampleNo(String selectedUserSampleNo) {
		this.selectedUserSampleNo = selectedUserSampleNo;
	}
	public String getSelectedBillNo() {
		return selectedBillNo;
	}
	public void setSelectedBillNo(String selectedBillNo) {
		this.selectedBillNo = selectedBillNo;
	}
	public String getSelectedTestGroup() {
		return selectedTestGroup;
	}
	public void setSelectedTestGroup(String selectedTestGroup) {
		this.selectedTestGroup = selectedTestGroup;
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
	public String getBookMarkCode() {
		return bookMarkCode;
	}
	public void setBookMarkCode(String bookMarkCode) {
		this.bookMarkCode = bookMarkCode;
	}
	public String getSearchTestGroupName() {
		return searchTestGroupName;
	}
	public void setSearchTestGroupName(String searchTestGroupName) {
		this.searchTestGroupName = searchTestGroupName;
	}
	public String getTestCode() {
		return testCode;
	}
	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}
	public String getLabEmpty() {
		return labEmpty;
	}
	public void setLabEmpty(String labEmpty) {
		this.labEmpty = labEmpty;
	}
	public String getTestEmpty() {
		return testEmpty;
	}
	public void setTestEmpty(String testEmpty) {
		this.testEmpty = testEmpty;
	}
	public String getTestCodeEmpty() {
		return testCodeEmpty;
	}
	public void setTestCodeEmpty(String testCodeEmpty) {
		this.testCodeEmpty = testCodeEmpty;
	}
	public String getIsLabAvailable() {
		return isLabAvailable;
	}
	public void setIsLabAvailable(String isLabAvailable) {
		this.isLabAvailable = isLabAvailable;
	}
	public String getReqSampleShortName() {
		return reqSampleShortName;
	}
	public void setReqSampleShortName(String reqSampleShortName) {
		this.reqSampleShortName = reqSampleShortName;
	}
	public String getIsAddendum() {
		return isAddendum;
	}
	public void setIsAddendum(String isAddendum) {
		this.isAddendum = isAddendum;
	}
	public String getRequisitionNo() {
		return requisitionNo;
	}
	public void setRequisitionNo(String requisitionNo) {
		this.requisitionNo = requisitionNo;
	}
	public String getTestSearchKeyword() {
		return testSearchKeyword;
	}
	public void setTestSearchKeyword(String testSearchKeyword) {
		this.testSearchKeyword = testSearchKeyword;
	}
	public String getTestCodesSearchKeyword() {
		return testCodesSearchKeyword;
	}
	public void setTestCodesSearchKeyword(String testCodesSearchKeyword) {
		this.testCodesSearchKeyword = testCodesSearchKeyword;
	}
	public String getDeletedtestdataviaresultentry() {
		return deletedtestdataviaresultentry;
	}
	public void setDeletedtestdataviaresultentry(
			String deletedtestdataviaresultentry) {
		this.deletedtestdataviaresultentry = deletedtestdataviaresultentry;
	}
	public String getLabwisetestteriff() {
		return labwisetestteriff;
	}
	public void setLabwisetestteriff(String labwisetestteriff) {
		this.labwisetestteriff = labwisetestteriff;
	}
	public String getSearchTestNamelabwise() {
		return searchTestNamelabwise;
	}
	public void setSearchTestNamelabwise(String searchTestNamelabwise) {
		this.searchTestNamelabwise = searchTestNamelabwise;
	}
	public String getRequisitingtypeforbilling() {
		return requisitingtypeforbilling;
	}
	public void setRequisitingtypeforbilling(String requisitingtypeforbilling) {
		this.requisitingtypeforbilling = requisitingtypeforbilling;
	}
	public String getWarcode() {
		return warcode;
	}
	public void setWarcode(String warcode) {
		this.warcode = warcode;
	}
	public String getPatcatcode() {
		return patcatcode;
	}
	public void setPatcatcode(String patcatcode) {
		this.patcatcode = patcatcode;
	}
	public String getUsertestcode() {
		return usertestcode;
	}
	public void setUsertestcode(String usertestcode) {
		this.usertestcode = usertestcode;
	}
	public String getGroupcode() {
		return groupcode;
	}
	public void setGroupcode(String groupcode) {
		this.groupcode = groupcode;
	}
	public String getIsamountsufficientflag() {
		return isamountsufficientflag;
	}
	public void setIsamountsufficientflag(String isamountsufficientflag) {
		this.isamountsufficientflag = isamountsufficientflag;
	}
	public String getIsbilledornot() {
		return isbilledornot;
	}
	public void setIsbilledornot(String isbilledornot) {
		this.isbilledornot = isbilledornot;
	}
	public String getIspidexist() {
		return ispidexist;
	}
	public void setIspidexist(String ispidexist) {
		this.ispidexist = ispidexist;
	}
	public String getPatAdmNo() {
		return patAdmNo;
	}
	public void setPatAdmNo(String patAdmNo) {
		this.patAdmNo = patAdmNo;
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
	public String getRaisethrough() {
		return raisethrough;
	}
	public void setRaisethrough(String raisethrough) {
		this.raisethrough = raisethrough;
	}
	public String getTestrates() {
		return testrates;
	}
	public void setTestrates(String testrates) {
		this.testrates = testrates;
	}


	
	
	

}
