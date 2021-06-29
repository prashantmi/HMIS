package mortuary.transaction.controller.fb;

import org.apache.struts.action.ActionForm;

public class PostmortemDeskFB extends ActionForm
{
	private String mode;
	private String deskMenuId;
	private String postmortemId;
	private String postmortemIdSelected;
	private String deceasedNo;
	private String crNoSelected;
	protected String patCrNo;
	
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getDeskMenuId() {
		return deskMenuId;
	}
	public void setDeskMenuId(String deskMenuId) {
		this.deskMenuId = deskMenuId;
	}
	public String getDeceasedNo() {
		return deceasedNo;
	}
	public void setDeceasedNo(String deceasedNo) {
		this.deceasedNo = deceasedNo;
	}
	public String getCrNoSelected() {
		return crNoSelected;
	}
	public void setCrNoSelected(String crNoSelected) {
		this.crNoSelected = crNoSelected;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getPostmortemId() {
		return postmortemId;
	}
	public void setPostmortemId(String postmortemId) {
		this.postmortemId = postmortemId;
	}
	public String getPostmortemIdSelected() {
		return postmortemIdSelected;
	}
	public void setPostmortemIdSelected(String postmortemIdSelected) {
		this.postmortemIdSelected = postmortemIdSelected;
	}
}
