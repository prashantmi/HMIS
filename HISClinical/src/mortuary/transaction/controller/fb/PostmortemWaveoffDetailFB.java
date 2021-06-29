package mortuary.transaction.controller.fb;

import org.apache.struts.action.ActionForm;

public class PostmortemWaveoffDetailFB extends ActionForm
{
	private String hmode;
	private String deceasedNo;
	private String postmortemId;
	private String selectedDeceased;
	private String patCrNo;
	private String postmortemType;
	private String postmortemStatus;
	private String postmortemRequestValue;
	private String waveoffBy;
	private String letterNo;
	private String waveoffReason;
	private String approvedBy;
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getDeceasedNo() {
		return deceasedNo;
	}
	public void setDeceasedNo(String deceasedNo) {
		this.deceasedNo = deceasedNo;
	}
	public String getPostmortemId() {
		return postmortemId;
	}
	public void setPostmortemId(String postmortemId) {
		this.postmortemId = postmortemId;
	}
	public String getSelectedDeceased() {
		return selectedDeceased;
	}
	public void setSelectedDeceased(String selectedDeceased) {
		this.selectedDeceased = selectedDeceased;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getPostmortemType() {
		return postmortemType;
	}
	public void setPostmortemType(String postmortemType) {
		this.postmortemType = postmortemType;
	}
	public String getPostmortemStatus() {
		return postmortemStatus;
	}
	public void setPostmortemStatus(String postmortemStatus) {
		this.postmortemStatus = postmortemStatus;
	}
	public String getPostmortemRequestValue() {
		return postmortemRequestValue;
	}
	public void setPostmortemRequestValue(String postmortemRequestValue) {
		this.postmortemRequestValue = postmortemRequestValue;
	}
	public String getWaveoffBy() {
		return waveoffBy;
	}
	public void setWaveoffBy(String waveoffBy) {
		this.waveoffBy = waveoffBy;
	}
	public String getLetterNo() {
		return letterNo;
	}
	public void setLetterNo(String letterNo) {
		this.letterNo = letterNo;
	}
	public String getWaveoffReason() {
		return waveoffReason;
	}
	public void setWaveoffReason(String waveoffReason) {
		this.waveoffReason = waveoffReason;
	}
	public String getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
}
