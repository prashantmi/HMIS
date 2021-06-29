package hisglobal.vo;

public class ProfileInvestigationVO extends ValueObject
{
	private String patCrNo;
	private String reqDNo;
	private String labTestCode;
	private String visitDate;
	private String labName;
	private String testName;
	private String labCode;
	private String testCode;
	private String resultStatusCode;
	private String resultStatus;
	private String reportURL;
	private String episodeCode;
	private String episodeVisitNo;
	private String departmentUnitCode;
	private String patAdmNo;
	private String hospitalCode;
	private String hospitalName;
	
	private String parameterName;
	private String refRange;
	private String[] invResultStatus;
	private String testValue;
	private String isConfidential;
	private String sampleName;//Added by Vasu on 31.Jan.2019
	
	public String getPatAdmNo() {
		return patAdmNo;
	}
	public void setPatAdmNo(String patAdmNo) {
		this.patAdmNo = patAdmNo;
	}
	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}
	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
	}
	public String getEpisodeCode() {
		return episodeCode;
	}
	public void setEpisodeCode(String episodeCode) {
		this.episodeCode = episodeCode;
	}
	public String getEpisodeVisitNo() {
		return episodeVisitNo;
	}
	public void setEpisodeVisitNo(String episodeVisitNo) {
		this.episodeVisitNo = episodeVisitNo;
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
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getResultStatusCode() {
		return resultStatusCode;
	}
	public void setResultStatusCode(String resultStatusCode) {
		this.resultStatusCode = resultStatusCode;
	}
	public String getReportURL() {
		return reportURL;
	}
	public void setReportURL(String reportURL) {
		this.reportURL = reportURL;
	}
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	public String getRefRange() {
		return refRange;
	}
	public void setRefRange(String refRange) {
		this.refRange = refRange;
	}
	public String[] getInvResultStatus() {
		return invResultStatus;
	}
	public void setInvResultStatus(String[] invResultStatus) {
		this.invResultStatus = invResultStatus;
	}
	public String getTestValue() {
		return testValue;
	}
	public void setTestValue(String testValue) {
		this.testValue = testValue;
	}
	public String getIsConfidential() {
		return isConfidential;
	}
	public void setIsConfidential(String isConfidential) {
		this.isConfidential = isConfidential;
	}
	public String getSampleName() {
		return sampleName;
	}
	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}
	

}
