package mortuary.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class PostmortemRequestAcceptanceFB extends ActionForm
{
	private String hmode;
	private String patCrNo;
	private String deceasedNo;
	private String postmortemId;
	private String selectedPostmortemReq;
	private String postmortemStatus;
	private String postmortemType;
	private String revokeOpinion[];
	private String revokeItem[];
	private String requestedOpinion;
	private String opinionRemark;
	private String hiddenOpinionCode;
	private String hiddenOpinionName;
	
	private String requestedItem;
	private String itemRemark;
	private String hiddenItemCode;
	private String hiddenItemName;
	
	private String autopsyType;
	private String conductedBy;
	private String employeeIncharge;
	private String approvedBy;
	private String postmortemRequestValue;
	private String acceptanceFlag;
	private String cancelReason;
	private String addOpinionFlag;
	private String roleId;
	private String hiddenInchargeId;
	private String hiddenInchargeName;
	private String teamEmployeeIncharge;
	private String addItemFlag;
	
	
	private String relativeName;
	private String relativeCode;
	private String relativeAddress;
	private String relativeContactNo;
	private String isConsentTaken;
	private String consentStatus;
	
	
	public String getIsConsentTaken() {
		return isConsentTaken;
	}
	public void setIsConsentTaken(String isConsentTaken) {
		this.isConsentTaken = isConsentTaken;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getPostmortemStatus() {
		return postmortemStatus;
	}
	public void setPostmortemStatus(String postmortemStatus) {
		this.postmortemStatus = postmortemStatus;
	}
	public String getPostmortemType() {
		return postmortemType;
	}
	public void setPostmortemType(String postmortemType) {
		this.postmortemType = postmortemType;
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
	public String getPostmortemId() {
		return postmortemId;
	}
	public void setPostmortemId(String postmortemId) {
		this.postmortemId = postmortemId;
	}
	public String getSelectedPostmortemReq() {
		return selectedPostmortemReq;
	}
	public void setSelectedPostmortemReq(String selectedPostmortemReq) {
		this.selectedPostmortemReq = selectedPostmortemReq;
	}
	public String[] getRevokeOpinion() {
		return revokeOpinion;
	}
	public void setRevokeOpinion(String[] revokeOpinion) {
		this.revokeOpinion = revokeOpinion;
	}
	public String getRequestedOpinion() {
		return requestedOpinion;
	}
	public void setRequestedOpinion(String requestedOpinion) {
		this.requestedOpinion = requestedOpinion;
	}
	public String getOpinionRemark() {
		return opinionRemark;
	}
	public void setOpinionRemark(String opinionRemark) {
		this.opinionRemark = opinionRemark;
	}
	public String getHiddenOpinionCode() {
		return hiddenOpinionCode;
	}
	public void setHiddenOpinionCode(String hiddenOpinionCode) {
		this.hiddenOpinionCode = hiddenOpinionCode;
	}
	public String getHiddenOpinionName() {
		return hiddenOpinionName;
	}
	public void setHiddenOpinionName(String hiddenOpinionName) {
		this.hiddenOpinionName = hiddenOpinionName;
	}
	
	public void resetOpinion(ActionMapping mapping,HttpServletRequest request)
	{
		this.setRequestedOpinion("");
		this.setOpinionRemark("");
	}
	public String getRequestedItem() {
		return requestedItem;
	}
	public void setRequestedItem(String requestedItem) {
		this.requestedItem = requestedItem;
	}
	public String getItemRemark() {
		return itemRemark;
	}
	public void setItemRemark(String itemRemark) {
		this.itemRemark = itemRemark;
	}
	public String getHiddenItemCode() {
		return hiddenItemCode;
	}
	public void setHiddenItemCode(String hiddenItemCode) {
		this.hiddenItemCode = hiddenItemCode;
	}
	public String getHiddenItemName() {
		return hiddenItemName;
	}
	public void setHiddenItemName(String hiddenItemName) {
		this.hiddenItemName = hiddenItemName;
	}
	
	public void resetItem(ActionMapping mapping,HttpServletRequest request)
	{
		this.setRequestedItem("");
		this.setItemRemark("");
	}
	public String getAutopsyType() {
		return autopsyType;
	}
	public void setAutopsyType(String autopsyType) {
		this.autopsyType = autopsyType;
	}
	public String getConductedBy() {
		return conductedBy;
	}
	public void setConductedBy(String conductedBy) {
		this.conductedBy = conductedBy;
	}
	public String getEmployeeIncharge() {
		return employeeIncharge;
	}
	public void setEmployeeIncharge(String employeeIncharge) {
		this.employeeIncharge = employeeIncharge;
	}
	public String getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	public String[] getRevokeItem() {
		return revokeItem;
	}
	public void setRevokeItem(String[] revokeItem) {
		this.revokeItem = revokeItem;
	}
	public String getPostmortemRequestValue() {
		return postmortemRequestValue;
	}
	public void setPostmortemRequestValue(String postmortemRequestValue) {
		this.postmortemRequestValue = postmortemRequestValue;
	}
	public String getAcceptanceFlag() {
		return acceptanceFlag;
	}
	public void setAcceptanceFlag(String acceptanceFlag) {
		this.acceptanceFlag = acceptanceFlag;
	}
	public String getCancelReason() {
		return cancelReason;
	}
	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}
	public String getAddOpinionFlag() {
		return addOpinionFlag;
	}
	public void setAddOpinionFlag(String addOpinionFlag) {
		this.addOpinionFlag = addOpinionFlag;
	}
	public String getHiddenInchargeId() {
		return hiddenInchargeId;
	}
	public void setHiddenInchargeId(String hiddenInchargeId) {
		this.hiddenInchargeId = hiddenInchargeId;
	}
	public String getHiddenInchargeName() {
		return hiddenInchargeName;
	}
	public void setHiddenInchargeName(String hiddenInchargeName) {
		this.hiddenInchargeName = hiddenInchargeName;
	}
	public String getTeamEmployeeIncharge() {
		return teamEmployeeIncharge;
	}
	public void setTeamEmployeeIncharge(String teamEmployeeIncharge) {
		this.teamEmployeeIncharge = teamEmployeeIncharge;
	}
	public String getAddItemFlag() {
		return addItemFlag;
	}
	public void setAddItemFlag(String addItemFlag) {
		this.addItemFlag = addItemFlag;
	}
	public String getRelativeName() {
		return relativeName;
	}
	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}
	public String getRelativeCode() {
		return relativeCode;
	}
	public void setRelativeCode(String relativeCode) {
		this.relativeCode = relativeCode;
	}
	public String getRelativeAddress() {
		return relativeAddress;
	}
	public void setRelativeAddress(String relativeAddress) {
		this.relativeAddress = relativeAddress;
	}
	public String getRelativeContactNo() {
		return relativeContactNo;
	}
	public void setRelativeContactNo(String relativeContactNo) {
		this.relativeContactNo = relativeContactNo;
	}
	public String getConsentStatus() {
		return consentStatus;
	}
	public void setConsentStatus(String consentStatus) {
		this.consentStatus = consentStatus;
	}
}
