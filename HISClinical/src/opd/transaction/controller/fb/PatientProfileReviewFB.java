package opd.transaction.controller.fb;

import registration.controller.fb.CRNoFB;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

public class PatientProfileReviewFB extends CRNoFB
{
	private String hmode;
	private String patCrNo;
	private String selectedIndex;
	private String remarks;
	private String profileId;
	private String profileHTML;
	private String admissionNo;
	private String profileReviewValue;
	private String selectedRadio;
	private String popupRemark;
	private String profileHeader;
	private String profileTypeDesc;
	private String creationDate;
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.patCrNo="";
	}
	
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getSelectedIndex() {
		return selectedIndex;
	}
	public void setSelectedIndex(String selectedIndex) {
		this.selectedIndex = selectedIndex;
	}
	
	public String getProfileId() {
		return profileId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	public String getProfileHTML() {
		return profileHTML;
	}
	public void setProfileHTML(String profileHTML) {
		this.profileHTML = profileHTML;
	}
	public String getAdmissionNo() {
		return admissionNo;
	}
	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}
	public String getProfileReviewValue() {
		return profileReviewValue;
	}
	public void setProfileReviewValue(String profileReviewValue) {
		this.profileReviewValue = profileReviewValue;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getSelectedRadio() {
		return selectedRadio;
	}
	public void setSelectedRadio(String selectedRadio) {
		this.selectedRadio = selectedRadio;
	}
	public String getPopupRemark() {
		return popupRemark;
	}
	public void setPopupRemark(String popupRemark) {
		this.popupRemark = popupRemark;
	}
	public String getProfileHeader() {
		return profileHeader;
	}
	public void setProfileHeader(String profileHeader) {
		this.profileHeader = profileHeader;
	}

	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getProfileTypeDesc() {
		return profileTypeDesc;
	}
	public void setProfileTypeDesc(String profileTypeDesc) {
		this.profileTypeDesc = profileTypeDesc;
	}
}
