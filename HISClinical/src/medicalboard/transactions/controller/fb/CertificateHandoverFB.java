package medicalboard.transactions.controller.fb;

import registration.controller.fb.CRNoFB;

public class CertificateHandoverFB extends CRNoFB
{
	private String hmode;
	private String certificateTypeID;
	private String requistionDate;
	private String referenceNo;
	private String deliveryType;
	private String dispatchDate;
	private String handOverTo;
	private String[] reqIdArray;
	
	private String relationShipCode;
	private String relativeName;
	private String relativeAddr;
	private String relativeContactNo;
	private String relativeIdRemark;
	
	private String handOverBy;
	private String handOverDate;
	private String remarks;
	
	private String requtionBy;
	private String isAuthorityProved;
	private String authorityProofDescription;
	
	private String definedIsuueType;
	private String requtionByFlag;
	private String patCrNo;
	private String[] selReqNoArray;
	
	private String reqStatus;
	private String duplicateReason;
	private String searchType;
	private String sysdate;
	private String approvedDateArray;
	private String selApprovedDate;
	private String templateId;
	private String templateHtmlCode;
	private String print;
	private String flagClose;
	private String orgName;
	private String opinion;

	private String serialNo;
	private String maxSerialNo;
	private String isPatient;

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getMaxSerialNo() {
		return maxSerialNo;
	}

	public void setMaxSerialNo(String maxSerialNo) {
		this.maxSerialNo = maxSerialNo;
	}

	public String getSelApprovedDate() {
		return selApprovedDate;
	}

	public void setSelApprovedDate(String selApprovedDate) {
		this.selApprovedDate = selApprovedDate;
	}

	public String getApprovedDateArray() {
		return approvedDateArray;
	}

	public void setApprovedDateArray(String approvedDateArray) {
		this.approvedDateArray = approvedDateArray;
	}

	public String getSysdate() {
		return sysdate;
	}

	public void setSysdate(String sysdate) {
		this.sysdate = sysdate;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getDuplicateReason() {
		return duplicateReason;
	}

	public void setDuplicateReason(String duplicateReason) {
		this.duplicateReason = duplicateReason;
	}

	public String getReqStatus() {
		return reqStatus;
	}

	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
	}

	public String getPatCrNo() {
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}

	public String getDefinedIsuueType() {
		return definedIsuueType;
	}

	public void setDefinedIsuueType(String definedIsuueType) {
		this.definedIsuueType = definedIsuueType;
	}

	public String getAuthorityProofDescription() {
		return authorityProofDescription;
	}

	public void setAuthorityProofDescription(String authorityProofDescription) {
		this.authorityProofDescription = authorityProofDescription;
	}

	public String getIsAuthorityProved() {
		return isAuthorityProved;
	}

	public void setIsAuthorityProved(String isAuthorityProved) {
		this.isAuthorityProved = isAuthorityProved;
	}

	public String getRequtionBy() {
		return requtionBy;
	}

	public void setRequtionBy(String requtionBy) {
		this.requtionBy = requtionBy;
	}

	public String getHandOverBy() {
		return handOverBy;
	}

	public void setHandOverBy(String handOverBy) {
		this.handOverBy = handOverBy;
	}

	public String getHandOverDate() {
		return handOverDate;
	}

	public void setHandOverDate(String handOverDate) {
		this.handOverDate = handOverDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRelationShipCode() {
		return relationShipCode;
	}

	public void setRelationShipCode(String relationShipCode) {
		this.relationShipCode = relationShipCode;
	}

	public String getRelativeName() {
		return relativeName;
	}

	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}

	public String getRelativeAddr() {
		return relativeAddr;
	}

	public void setRelativeAddr(String relativeAddr) {
		this.relativeAddr = relativeAddr;
	}

	public String getRelativeContactNo() {
		return relativeContactNo;
	}

	public void setRelativeContactNo(String relativeContactNo) {
		this.relativeContactNo = relativeContactNo;
	}

	public String getRelativeIdRemark() {
		return relativeIdRemark;
	}

	public void setRelativeIdRemark(String relativeIdRemark) {
		this.relativeIdRemark = relativeIdRemark;
	}

	public String[] getReqIdArray() {
		return reqIdArray;
	}

	public void setReqIdArray(String[] reqIdArray) {
		this.reqIdArray = reqIdArray;
	}

	public String getHandOverTo() {
		return handOverTo;
	}

	public void setHandOverTo(String handOverTo) {
		this.handOverTo = handOverTo;
	}

	public String getRequistionDate() {
		return requistionDate;
	}

	public void setRequistionDate(String requistionDate) {
		this.requistionDate = requistionDate;
	}

	public String getCertificateTypeID() {
		return certificateTypeID;
	}

	public void setCertificateTypeID(String certificateTypeID) {
		this.certificateTypeID = certificateTypeID;
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public String getDispatchDate() {
		return dispatchDate;
	}

	public void setDispatchDate(String dispatchDate) {
		this.dispatchDate = dispatchDate;
	}

	public String getRequtionByFlag() {
		return requtionByFlag;
	}

	public void setRequtionByFlag(String requtionByFlag) {
		this.requtionByFlag = requtionByFlag;
	}

	public String[] getSelReqNoArray() {
		return selReqNoArray;
	}

	public void setSelReqNoArray(String[] selReqNoArray) {
		this.selReqNoArray = selReqNoArray;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}



	public String getTemplateHtmlCode() {
		return templateHtmlCode;
	}

	public void setTemplateHtmlCode(String templateHtmlCode) {
		this.templateHtmlCode = templateHtmlCode;
	}

	public String getPrint() {
		return print;
	}

	public void setPrint(String print) {
		this.print = print;
	}

	public String getFlagClose() {
		return flagClose;
	}

	public void setFlagClose(String flagClose) {
		this.flagClose = flagClose;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public String getIsPatient()
	{
		return isPatient;
	}

	public void setIsPatient(String isPatient)
	{
		this.isPatient = isPatient;
	}

	

	
}
