package mortuary.transaction.controller.fb;

import org.apache.struts.action.ActionForm;

public class PostmortemHandoverDetailFB extends ActionForm
{
	private String hmode;
	private String postmortemId;
	private String patCrNo;
	private String deceasedNo;
	private String isHandoverTo;
	private String empId;
	private String relativeName;
	private String relativeAddress;
	private String relativePhone;
	private String relativeCode;
	private String officerName;
	private String officerDesignation;
	private String officerBadgeNo;
	private String handoverBy;
	private String selectedPostmortemId;
	private String postmortemType;
	
	
	public String getSelectedPostmortemId() {
		return selectedPostmortemId;
	}
	public void setSelectedPostmortemId(String selectedPostmortemId) {
		this.selectedPostmortemId = selectedPostmortemId;
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
	public String getDeceasedNo() {
		return deceasedNo;
	}
	public void setDeceasedNo(String deceasedNo) {
		this.deceasedNo = deceasedNo;
	}
	public String getIsHandoverTo() {
		return isHandoverTo;
	}
	public void setIsHandoverTo(String isHandoverTo) {
		this.isHandoverTo = isHandoverTo;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getRelativeCode() {
		return relativeCode;
	}
	public void setRelativeCode(String relativeCode) {
		this.relativeCode = relativeCode;
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
	public String getHandoverBy() {
		return handoverBy;
	}
	public void setHandoverBy(String handoverBy) {
		this.handoverBy = handoverBy;
	}
	public String getPostmortemId() {
		return postmortemId;
	}
	public void setPostmortemId(String postmortemId) {
		this.postmortemId = postmortemId;
	}
	public String getRelativeName() {
		return relativeName;
	}
	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}
	public String getRelativeAddress() {
		return relativeAddress;
	}
	public void setRelativeAddress(String relativeAddress) {
		this.relativeAddress = relativeAddress;
	}
	public String getRelativePhone() {
		return relativePhone;
	}
	public void setRelativePhone(String relativePhone) {
		this.relativePhone = relativePhone;
	}
	public String getOfficerName() {
		return officerName;
	}
	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}
	public String getPostmortemType() {
		return postmortemType;
	}
	public void setPostmortemType(String postmortemType) {
		this.postmortemType = postmortemType;
	}
}
