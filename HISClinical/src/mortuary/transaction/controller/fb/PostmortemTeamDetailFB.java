package mortuary.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class PostmortemTeamDetailFB extends ActionForm
{
	private String hmode;
	private String deceasedNo;
	private String postmortemId;
	private String teamMember;
	private String role;
	private String hiddenTeamMemberId;
	private String hiddenTeamMemberName;
	private String hiddenAddedTeamMemberId[];
	private String hiddenAddedTeamMemberName[];
	private String notPerformed[];
	private String reason[];
	private String notPerformedValue[];
	
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getPostmortemId() {
		return postmortemId;
	}
	public void setPostmortemId(String postmortemId) {
		this.postmortemId = postmortemId;
	}
	public String getTeamMember() {
		return teamMember;
	}
	public void setTeamMember(String teamMember) {
		this.teamMember = teamMember;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getHiddenTeamMemberId() {
		return hiddenTeamMemberId;
	}
	public void setHiddenTeamMemberId(String hiddenTeamMemberId) {
		this.hiddenTeamMemberId = hiddenTeamMemberId;
	}
	public String getHiddenTeamMemberName() {
		return hiddenTeamMemberName;
	}
	public void setHiddenTeamMemberName(String hiddenTeamMemberName) {
		this.hiddenTeamMemberName = hiddenTeamMemberName;
	}
	public String getDeceasedNo() {
		return deceasedNo;
	}
	public void setDeceasedNo(String deceasedNo) {
		this.deceasedNo = deceasedNo;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setRole("-1");
		this.setTeamMember("-1");
	}
	public String[] getHiddenAddedTeamMemberId() {
		return hiddenAddedTeamMemberId;
	}
	public void setHiddenAddedTeamMemberId(String[] hiddenAddedTeamMemberId) {
		this.hiddenAddedTeamMemberId = hiddenAddedTeamMemberId;
	}
	public String[] getHiddenAddedTeamMemberName() {
		return hiddenAddedTeamMemberName;
	}
	public void setHiddenAddedTeamMemberName(String[] hiddenAddedTeamMemberName) {
		this.hiddenAddedTeamMemberName = hiddenAddedTeamMemberName;
	}
	public String[] getNotPerformed() {
		return notPerformed;
	}
	public void setNotPerformed(String[] notPerformed) {
		this.notPerformed = notPerformed;
	}
	public String[] getReason() {
		return reason;
	}
	public void setReason(String[] reason) {
		this.reason = reason;
	}
	public String[] getNotPerformedValue() {
		return notPerformedValue;
	}
	public void setNotPerformedValue(String[] notPerformedValue) {
		this.notPerformedValue = notPerformedValue;
	}
}
