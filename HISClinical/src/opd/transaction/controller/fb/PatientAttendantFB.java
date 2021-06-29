package opd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class PatientAttendantFB extends ActionForm
{
	private String patCrNo;
	private String episodeCode;
	private String episodeVisitNo;
	private String hmode;
	private String tempMode;
	private String isDirectCall;
	private String relativeName;
	private String relationCode;
	private String relativeCrNo;
	private String address;
	private String email;
	private String phoneNo;
	private String mobileNo;
	private String existingNewFlag;
	private String isAttendantExist;
	private String selectedEpisode;
	private String attendantReasonId;
	private String hiddenIndex;
	private String selectedAttendant;
	private String[] hiddenRelationCode;
	private String patFarherMotherSpouceName;
	
	public PatientAttendantFB()
	{
		this.isDirectCall="";
	}
	
	public String getRelativeName() {
		return relativeName;
	}
	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}
	public String getRelationCode() {
		return relationCode;
	}
	public void setRelationCode(String relationCode) {
		this.relationCode = relationCode;
	}
	public String getRelativeCrNo() {
		return relativeCrNo;
	}
	public void setRelativeCrNo(String relativeCrNo) {
		this.relativeCrNo = relativeCrNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getExistingNewFlag() {
		return existingNewFlag;
	}
	public void setExistingNewFlag(String existingNewFlag) {
		this.existingNewFlag = existingNewFlag;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
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
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getTempMode() {
		return tempMode;
	}
	public void setTempMode(String tempMode) {
		this.tempMode = tempMode;
	}
	public String getIsDirectCall() {
		return isDirectCall;
	}
	public void setIsDirectCall(String isDirectCall) {
		this.isDirectCall = isDirectCall;
	}
	public String getIsAttendantExist() {
		return isAttendantExist;
	}
	public void setIsAttendantExist(String isAttendantExist) {
		this.isAttendantExist = isAttendantExist;
	}
	public String getSelectedEpisode() {
		return selectedEpisode;
	}
	public void setSelectedEpisode(String selectedEpisode) {
		this.selectedEpisode = selectedEpisode;
	}
	public String getAttendantReasonId() {
		return attendantReasonId;
	}
	public void setAttendantReasonId(String attendantReasonId) {
		this.attendantReasonId = attendantReasonId;
	}
	public String getHiddenIndex() {
		return hiddenIndex;
	}
	public void setHiddenIndex(String hiddenIndex) {
		this.hiddenIndex = hiddenIndex;
	}
	public String getSelectedAttendant() {
		return selectedAttendant;
	}
	public void setSelectedAttendant(String selectedAttendant) {
		this.selectedAttendant = selectedAttendant;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setAttendantReasonId("");
		this.setRelativeName("");
		this.setRelationCode("");
		this.setPhoneNo("");
		this.setMobileNo("");
		this.setEmail("");
		this.setAddress("");
		this.setSelectedAttendant("");
	}

	public String[] getHiddenRelationCode() {
		return hiddenRelationCode;
	}

	public void setHiddenRelationCode(String[] hiddenRelationCode) {
		this.hiddenRelationCode = hiddenRelationCode;
	}

	public String getPatFarherMotherSpouceName() {
		return patFarherMotherSpouceName;
	}

	public void setPatFarherMotherSpouceName(String patFarherMotherSpouceName) {
		this.patFarherMotherSpouceName = patFarherMotherSpouceName;
	}
}
