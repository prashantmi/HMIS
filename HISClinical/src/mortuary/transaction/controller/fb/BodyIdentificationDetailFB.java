package mortuary.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class BodyIdentificationDetailFB extends DeceasedTileFB
{
	private String hmode;
	private String isIdentifiyBy;
	private String identifiyByName;
	private String identifiyByAddress;
	private String identifiyByPhone;
	private String officerDesignation;
	private String officerBadgeNo;
	private String relativeCode;
	private String empId;
	private String deceasedNo;
	private String postmortemId;
	private String identityMode;
	private String officerName;
	private String identityRemarks;
	
	private String existingRelativeFlag;
	private String relativeExist;
	private String relativeExistRadioBtn;
	private String relativeExistChk[];
	
	public String getExistingRelativeFlag() {
		return existingRelativeFlag;
	}
	public void setExistingRelativeFlag(String existingRelativeFlag) {
		this.existingRelativeFlag = existingRelativeFlag;
	}
	public String getRelativeExist() {
		return relativeExist;
	}
	public void setRelativeExist(String relativeExist) {
		this.relativeExist = relativeExist;
	}
	public String getRelativeExistRadioBtn() {
		return relativeExistRadioBtn;
	}
	public void setRelativeExistRadioBtn(String relativeExistRadioBtn) {
		this.relativeExistRadioBtn = relativeExistRadioBtn;
	}
	public String getOfficerName() {
		return officerName;
	}
	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getIsIdentifiyBy() {
		return isIdentifiyBy;
	}
	public void setIsIdentifiyBy(String isIdentifiyBy) {
		this.isIdentifiyBy = isIdentifiyBy;
	}
	public String getIdentifiyByName() {
		return identifiyByName;
	}
	public void setIdentifiyByName(String identifiyByName) {
		this.identifiyByName = identifiyByName;
	}
	public String getIdentifiyByAddress() {
		return identifiyByAddress;
	}
	public void setIdentifiyByAddress(String identifiyByAddress) {
		this.identifiyByAddress = identifiyByAddress;
	}
	public String getIdentifiyByPhone() {
		return identifiyByPhone;
	}
	public void setIdentifiyByPhone(String identifiyByPhone) {
		this.identifiyByPhone = identifiyByPhone;
	}
	public String getOfficerDesignation() {
		return officerDesignation;
	}
	public void setOfficerDesignation(String officerDesignation) {
		this.officerDesignation = officerDesignation;
	}
	public String getOfficerBadgeNo() {
		return officerBadgeNo;
	}
	public void setOfficerBadgeNo(String officerBadgeNo) {
		this.officerBadgeNo = officerBadgeNo;
	}
	public String getRelativeCode() {
		return relativeCode;
	}
	public void setRelativeCode(String relativeCode) {
		this.relativeCode = relativeCode;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
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
	public String getIdentityMode() {
		return identityMode;
	}
	public void setIdentityMode(String identityMode) {
		this.identityMode = identityMode;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setIsIdentifiyBy("");
		this.setRelativeCode("");
		this.setIdentifiyByName("");
		this.setIdentifiyByAddress("");
		this.setIdentifiyByPhone("");
		this.setOfficerBadgeNo("");
		this.setOfficerDesignation("");
		this.setOfficerName("");
	}
	public String getIdentityRemarks() {
		return identityRemarks;
	}
	public void setIdentityRemarks(String identityRemarks) {
		this.identityRemarks = identityRemarks;
	}
	public String[] getRelativeExistChk() {
		return relativeExistChk;
	}
	public void setRelativeExistChk(String[] relativeExistChk) {
		this.relativeExistChk = relativeExistChk;
	}
	
	
}
