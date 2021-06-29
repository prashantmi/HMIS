package new_investigation.reports.controller.fb;

import org.apache.struts.action.ActionForm;

public class MachineTestReportNewFB extends ActionForm{


	private static final long serialVersionUID = 1L;
	private String crNo;
	private String sampleNo;
	private String accessionNo;
	private String patientName;
	private String ageGender;
	private String sampleName;
	private String groupName;
	private String testName;
	private String acceptanceDatetime;
	private String collectionDateTime;

	private String labCode;
	private String labName;
	private String machineId;
	private String machineName;

	private String fromDate;
	private String toDate;
	private String acceptance;
	private String acceptedByUser;


	public String getAcceptedByUser() {
		return acceptedByUser;
	}
	public void setAcceptedByUser(String acceptedByUser) {
		this.acceptedByUser = acceptedByUser;
	}
	public String getAcceptance() {
		return acceptance;
	}
	public void setAcceptance(String acceptance) {
		this.acceptance = acceptance;
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

	public String getLabName() {
		return labName;
	}
	public void setLabName(String labName) {
		this.labName = labName;
	}
	public String getMachineName() {
		return machineName;
	}
	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}
	public String getCrNo() {
		return crNo;
	}
	public void setCrNo(String crNo) {
		this.crNo = crNo;
	}
	public String getSampleNo() {
		return sampleNo;
	}
	public void setSampleNo(String sampleNo) {
		this.sampleNo = sampleNo;
	}

	public String getAccessionNo() {
		return accessionNo;
	}
	public void setAccessionNo(String accessionNo) {
		this.accessionNo = accessionNo;
	}

	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getAgeGender() {
		return ageGender;
	}
	public void setAgeGender(String ageGender) {
		this.ageGender = ageGender;
	}
	public String getSampleName() {
		return sampleName;
	}
	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getAcceptanceDatetime() {
		return acceptanceDatetime;
	}
	public void setAcceptanceDatetime(String acceptanceDatetime) {
		this.acceptanceDatetime = acceptanceDatetime;
	}
	public String getCollectionDateTime() {
		return collectionDateTime;
	}
	public void setCollectionDateTime(String collectionDateTime) {
		this.collectionDateTime = collectionDateTime;
	}
	public String getLabCode() {
		return labCode;
	}
	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}
	public String getMachineId() {
		return machineId;
	}
	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}



}
