package hisglobal.vo;

public class CrNoMergeDtlVO extends ValueObject
{
	private String patMainCrNo;
	private String patNotUsedCrNo;
	private String isMerged;
	private String reason;
	private String remarks;
	
	public String getPatMainCrNo() {
		return patMainCrNo;
	}
	public void setPatMainCrNo(String patMainCrNo) {
		this.patMainCrNo = patMainCrNo;
	}
	public String getPatNotUsedCrNo() {
		return patNotUsedCrNo;
	}
	public void setPatNotUsedCrNo(String patNotUsedCrNo) {
		this.patNotUsedCrNo = patNotUsedCrNo;
	}
	public String getIsMerged() {
		return isMerged;
	}
	public void setIsMerged(String isMerged) {
		this.isMerged = isMerged;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
