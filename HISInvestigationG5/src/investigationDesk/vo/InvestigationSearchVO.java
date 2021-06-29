package investigationDesk.vo;

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
	private String deptUnitCode;
	
	
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
	public String getDeptUnitCode() {
		return deptUnitCode;
	}
	public void setDeptUnitCode(String deptUnitCode) {
		this.deptUnitCode = deptUnitCode;
	}
	private String searchLabName;
	private String searchTestName; 
	
	private String searchTestGroupName; 
	private String searchTestGroup;
	private String tstOrTestGroupValue;
	
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
	
	
	

}
