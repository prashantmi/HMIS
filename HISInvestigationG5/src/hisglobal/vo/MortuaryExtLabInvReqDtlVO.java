package hisglobal.vo;

public class MortuaryExtLabInvReqDtlVO extends ValueObject
{
	private String postmortemId;
	private String requestId;
	private String srNo;
	private String labTestId;
	private String labTestName;
	private String labTestRemrks;
	private String labTestResult;
	
	public String getPostmortemId() {
		return postmortemId;
	}
	public void setPostmortemId(String postmortemId) {
		this.postmortemId = postmortemId;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getSrNo() {
		return srNo;
	}
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	public String getLabTestId() {
		return labTestId;
	}
	public void setLabTestId(String labTestId) {
		this.labTestId = labTestId;
	}
	public String getLabTestRemrks() {
		return labTestRemrks;
	}
	public void setLabTestRemrks(String labTestRemrks) {
		this.labTestRemrks = labTestRemrks;
	}
	public String getLabTestResult() {
		return labTestResult;
	}
	public void setLabTestResult(String labTestResult) {
		this.labTestResult = labTestResult;
	}
	public String getLabTestName() {
		return labTestName;
	}
	public void setLabTestName(String labTestName) {
		this.labTestName = labTestName;
	}
}
