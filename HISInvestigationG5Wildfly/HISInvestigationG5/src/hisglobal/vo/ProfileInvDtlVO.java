package hisglobal.vo;

public class ProfileInvDtlVO extends ValueObject
{
	private String profileId;
	private String reqDNo;
	private String labTestCode;
	private String visitDate;
	private String labName;
	private String testName;
	private String labCode;
	private String testCode;
	private String resultStatus;
	public String getProfileId() {
		return profileId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	public String getReqDNo() {
		return reqDNo;
	}
	public void setReqDNo(String reqDNo) {
		this.reqDNo = reqDNo;
	}
	public String getLabTestCode() {
		return labTestCode;
	}
	public void setLabTestCode(String labTestCode) {
		this.labTestCode = labTestCode;
	}
	public String getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
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
	public String getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}
	
}
