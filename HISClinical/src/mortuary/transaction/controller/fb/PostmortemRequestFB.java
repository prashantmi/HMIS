package mortuary.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class PostmortemRequestFB extends ActionForm
{
	private String patCrNo;
	private String hmode;
	private String patMlcNo;
	private String deceasedNo;
	private String caseNo;
	private String requestDateTime;
	private String postmortemStatus;
	private String policeStation;
	private String docketNo;
	private String officerIncharge;
	private String ioDesignation;
	private String ioBatchNo;
	private String dutyOffName;
	private String dutyOffDesignation;
	private String dutyOffBatchNo;
	private String caseRemarks;
	private String deathDateTime;
	private String incidenceDateTime;
	private String deathPlace;
	private String isRepeat;
	private String RepeatReason;
	private String approvedBy;
	private String conductBy;
	private String postmortemType;
	private String selectedDeceased;
	private String receiveDateTime;
	private String chkOpinion[];
	private String chkDeceasedItem[];
	private String existPoliceVerFlag;
	private String policeVerExistNew;
	private String dutyOfficeFlag;
	private String opinionRemark;
	private String deceasedItemRemarks;
	private String sysDate="";
	private String hiddenTimeHr;
	private String hiddenTimeMin;
	private String isMlc;
	private String requestedOpinion;
	private String hiddenOpinionCode;
	private String hiddenOpinionName;
	private String requestedItem;
	private String hiddenItemCode;
	private String hiddenItemName;
	
	/////New Police Verification
	private String newCaseNo;
	private String newPoliceStation;
	private String newDocketNo;
	private String newOfficerIncharge;
	private String newIoDesignation;
	private String newIoBatchNo;
	private String newDutyOfficeFlag;
	private String newDutyOffName;
	private String newDutyOffDesignation;
	private String newDutyOffBatchNo;
	private String newCaseRemarks;
	
	private String newDeathDate;
	private String newDeathTimeHr;
	private String newDeathTimeMin;
	private String newIncicenceDate;
	private String newIncicenceTimeHr;
	private String newIncicenceTimeMin;
	private String newDeathPlace;
	
	private String existingDeathDate;
	private String existingDeathTimeHr;
	private String existingDeathTimeMin;
	private String existingIncicenceDate;
	private String existingIncicenceTimeHr;
	private String existingIncicenceTimeMin;
	private String existingDeathPlace;
	
	//relative
	private String requestRelativeName;
	private String requestRelativeAddress;
	private String requestRelativeContactNo;
	private String requestRelativeCode;
	
	private String chkRelative[];
	
	private String identifyRelativeName;
	private String identifyRelativeAddress;
	private String identifyRelativeContactNo;
	private String identifyRelativeCode;
	private String relativeIndex;
	
	///Consent
	private String isConsentTaken;
	private String isConsentRequired;
	private String templateId;
	
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public String getRelativeIndex() {
		return relativeIndex;
	}
	public void setRelativeIndex(String relativeIndex) {
		this.relativeIndex = relativeIndex;
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
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getPatMlcNo() {
		return patMlcNo;
	}
	public void setPatMlcNo(String patMlcNo) {
		this.patMlcNo = patMlcNo;
	}
	public String getDeceasedNo() {
		return deceasedNo;
	}
	public void setDeceasedNo(String deceasedNo) {
		this.deceasedNo = deceasedNo;
	}
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	public String getRequestDateTime() {
		return requestDateTime;
	}
	public void setRequestDateTime(String requestDateTime) {
		this.requestDateTime = requestDateTime;
	}
	public String getPostmortemStatus() {
		return postmortemStatus;
	}
	public void setPostmortemStatus(String postmortemStatus) {
		this.postmortemStatus = postmortemStatus;
	}
	public String getPoliceStation() {
		return policeStation;
	}
	public void setPoliceStation(String policeStation) {
		this.policeStation = policeStation;
	}
	public String getDocketNo() {
		return docketNo;
	}
	public void setDocketNo(String docketNo) {
		this.docketNo = docketNo;
	}
	public String getOfficerIncharge() {
		return officerIncharge;
	}
	public void setOfficerIncharge(String officerIncharge) {
		this.officerIncharge = officerIncharge;
	}
	public String getIoDesignation() {
		return ioDesignation;
	}
	public void setIoDesignation(String ioDesignation) {
		this.ioDesignation = ioDesignation;
	}
	public String getIoBatchNo() {
		return ioBatchNo;
	}
	public void setIoBatchNo(String ioBatchNo) {
		this.ioBatchNo = ioBatchNo;
	}
	public String getDutyOffName() {
		return dutyOffName;
	}
	public void setDutyOffName(String dutyOffName) {
		this.dutyOffName = dutyOffName;
	}
	public String getDutyOffDesignation() {
		return dutyOffDesignation;
	}
	public void setDutyOffDesignation(String dutyOffDesignation) {
		this.dutyOffDesignation = dutyOffDesignation;
	}
	public String getDutyOffBatchNo() {
		return dutyOffBatchNo;
	}
	public void setDutyOffBatchNo(String dutyOffBatchNo) {
		this.dutyOffBatchNo = dutyOffBatchNo;
	}
	public String getCaseRemarks() {
		return caseRemarks;
	}
	public void setCaseRemarks(String caseRemarks) {
		this.caseRemarks = caseRemarks;
	}
	public String getDeathDateTime() {
		return deathDateTime;
	}
	public void setDeathDateTime(String deathDateTime) {
		this.deathDateTime = deathDateTime;
	}
	public String getIncidenceDateTime() {
		return incidenceDateTime;
	}
	public void setIncidenceDateTime(String incidenceDateTime) {
		this.incidenceDateTime = incidenceDateTime;
	}
	public String getDeathPlace() {
		return deathPlace;
	}
	public void setDeathPlace(String deathPlace) {
		this.deathPlace = deathPlace;
	}
	public String getIsRepeat() {
		return isRepeat;
	}
	public void setIsRepeat(String isRepeat) {
		this.isRepeat = isRepeat;
	}
	public String getRepeatReason() {
		return RepeatReason;
	}
	public void setRepeatReason(String repeatReason) {
		RepeatReason = repeatReason;
	}
	public String getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	public String getConductBy() {
		return conductBy;
	}
	public void setConductBy(String conductBy) {
		this.conductBy = conductBy;
	}
	public String getPostmortemType() {
		return postmortemType;
	}
	public void setPostmortemType(String postmortemType) {
		this.postmortemType = postmortemType;
	}
	public String getReceiveDateTime() {
		return receiveDateTime;
	}
	public void setReceiveDateTime(String receiveDateTime) {
		this.receiveDateTime = receiveDateTime;
	}
	public String[] getChkOpinion() {
		return chkOpinion;
	}
	public void setChkOpinion(String[] chkOpinion) {
		this.chkOpinion = chkOpinion;
	}
	public String[] getChkDeceasedItem() {
		return chkDeceasedItem;
	}
	public void setChkDeceasedItem(String[] chkDeceasedItem) {
		this.chkDeceasedItem = chkDeceasedItem;
	}
	public String getExistPoliceVerFlag() {
		return existPoliceVerFlag;
	}
	public void setExistPoliceVerFlag(String existPoliceVerFlag) {
		this.existPoliceVerFlag = existPoliceVerFlag;
	}
	public String getPoliceVerExistNew() {
		return policeVerExistNew;
	}
	public void setPoliceVerExistNew(String policeVerExistNew) {
		this.policeVerExistNew = policeVerExistNew;
	}
	public String getDutyOfficeFlag() {
		return dutyOfficeFlag;
	}
	public void setDutyOfficeFlag(String dutyOfficeFlag) {
		this.dutyOfficeFlag = dutyOfficeFlag;
	}
	public String getNewCaseNo() {
		return newCaseNo;
	}
	public void setNewCaseNo(String newCaseNo) {
		this.newCaseNo = newCaseNo;
	}
	public String getNewPoliceStation() {
		return newPoliceStation;
	}
	public void setNewPoliceStation(String newPoliceStation) {
		this.newPoliceStation = newPoliceStation;
	}
	public String getNewDocketNo() {
		return newDocketNo;
	}
	public void setNewDocketNo(String newDocketNo) {
		this.newDocketNo = newDocketNo;
	}
	public String getNewOfficerIncharge() {
		return newOfficerIncharge;
	}
	public void setNewOfficerIncharge(String newOfficerIncharge) {
		this.newOfficerIncharge = newOfficerIncharge;
	}
	public String getNewIoDesignation() {
		return newIoDesignation;
	}
	public void setNewIoDesignation(String newIoDesignation) {
		this.newIoDesignation = newIoDesignation;
	}
	public String getNewIoBatchNo() {
		return newIoBatchNo;
	}
	public void setNewIoBatchNo(String newIoBatchNo) {
		this.newIoBatchNo = newIoBatchNo;
	}
	public String getNewDutyOfficeFlag() {
		return newDutyOfficeFlag;
	}
	public void setNewDutyOfficeFlag(String newDutyOfficeFlag) {
		this.newDutyOfficeFlag = newDutyOfficeFlag;
	}
	public String getNewDutyOffName() {
		return newDutyOffName;
	}
	public void setNewDutyOffName(String newDutyOffName) {
		this.newDutyOffName = newDutyOffName;
	}
	public String getNewDutyOffDesignation() {
		return newDutyOffDesignation;
	}
	public void setNewDutyOffDesignation(String newDutyOffDesignation) {
		this.newDutyOffDesignation = newDutyOffDesignation;
	}
	public String getNewDutyOffBatchNo() {
		return newDutyOffBatchNo;
	}
	public void setNewDutyOffBatchNo(String newDutyOffBatchNo) {
		this.newDutyOffBatchNo = newDutyOffBatchNo;
	}
	public String getNewCaseRemarks() {
		return newCaseRemarks;
	}
	public void setNewCaseRemarks(String newCaseRemarks) {
		this.newCaseRemarks = newCaseRemarks;
	}
	public String getDeceasedItemRemarks() {
		return deceasedItemRemarks;
	}
	public void setDeceasedItemRemarks(String deceasedItemRemarks) {
		this.deceasedItemRemarks = deceasedItemRemarks;
	}
	public String getNewDeathDate() {
		return newDeathDate;
	}
	public void setNewDeathDate(String newDeathDate) {
		this.newDeathDate = newDeathDate;
	}
	public String getNewDeathTimeHr() {
		return newDeathTimeHr;
	}
	public void setNewDeathTimeHr(String newDeathTimeHr) {
		this.newDeathTimeHr = newDeathTimeHr;
	}
	public String getNewDeathTimeMin() {
		return newDeathTimeMin;
	}
	public void setNewDeathTimeMin(String newDeathTimeMin) {
		this.newDeathTimeMin = newDeathTimeMin;
	}
	public String getNewIncicenceDate() {
		return newIncicenceDate;
	}
	public void setNewIncicenceDate(String newIncicenceDate) {
		this.newIncicenceDate = newIncicenceDate;
	}
	public String getNewIncicenceTimeHr() {
		return newIncicenceTimeHr;
	}
	public void setNewIncicenceTimeHr(String newIncicenceTimeHr) {
		this.newIncicenceTimeHr = newIncicenceTimeHr;
	}
	public String getNewIncicenceTimeMin() {
		return newIncicenceTimeMin;
	}
	public void setNewIncicenceTimeMin(String newIncicenceTimeMin) {
		this.newIncicenceTimeMin = newIncicenceTimeMin;
	}
	public String getNewDeathPlace() {
		return newDeathPlace;
	}
	public void setNewDeathPlace(String newDeathPlace) {
		this.newDeathPlace = newDeathPlace;
	}
	public String getSysDate() {
		return sysDate;
	}
	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}
	public String getHiddenTimeHr() {
		return hiddenTimeHr;
	}
	public void setHiddenTimeHr(String hiddenTimeHr) {
		this.hiddenTimeHr = hiddenTimeHr;
	}
	public String getHiddenTimeMin() {
		return hiddenTimeMin;
	}
	public void setHiddenTimeMin(String hiddenTimeMin) {
		this.hiddenTimeMin = hiddenTimeMin;
	}
	public String getExistingIncicenceDate() {
		return existingIncicenceDate;
	}
	public void setExistingIncicenceDate(String existingIncicenceDate) {
		this.existingIncicenceDate = existingIncicenceDate;
	}
	public String getExistingIncicenceTimeHr() {
		return existingIncicenceTimeHr;
	}
	public void setExistingIncicenceTimeHr(String existingIncicenceTimeHr) {
		this.existingIncicenceTimeHr = existingIncicenceTimeHr;
	}
	public String getExistingIncicenceTimeMin() {
		return existingIncicenceTimeMin;
	}
	public void setExistingIncicenceTimeMin(String existingIncicenceTimeMin) {
		this.existingIncicenceTimeMin = existingIncicenceTimeMin;
	}
	public String getExistingDeathPlace() {
		return existingDeathPlace;
	}
	public void setExistingDeathPlace(String existingDeathPlace) {
		this.existingDeathPlace = existingDeathPlace;
	}
	public String getExistingDeathDate() {
		return existingDeathDate;
	}
	public void setExistingDeathDate(String existingDeathDate) {
		this.existingDeathDate = existingDeathDate;
	}
	public String getExistingDeathTimeHr() {
		return existingDeathTimeHr;
	}
	public void setExistingDeathTimeHr(String existingDeathTimeHr) {
		this.existingDeathTimeHr = existingDeathTimeHr;
	}
	public String getExistingDeathTimeMin() {
		return existingDeathTimeMin;
	}
	public void setExistingDeathTimeMin(String existingDeathTimeMin) {
		this.existingDeathTimeMin = existingDeathTimeMin;
	}
	public String getRequestRelativeName() {
		return requestRelativeName;
	}
	public void setRequestRelativeName(String requestRelativeName) {
		this.requestRelativeName = requestRelativeName;
	}
	public String getRequestRelativeAddress() {
		return requestRelativeAddress;
	}
	public void setRequestRelativeAddress(String requestRelativeAddress) {
		this.requestRelativeAddress = requestRelativeAddress;
	}
	public String getRequestRelativeContactNo() {
		return requestRelativeContactNo;
	}
	public void setRequestRelativeContactNo(String requestRelativeContactNo) {
		this.requestRelativeContactNo = requestRelativeContactNo;
	}
	public String getRequestRelativeCode() {
		return requestRelativeCode;
	}
	public void setRequestRelativeCode(String requestRelativeCode) {
		this.requestRelativeCode = requestRelativeCode;
	}
	public String getIsMlc() {
		return isMlc;
	}
	public void setIsMlc(String isMlc) {
		this.isMlc = isMlc;
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
	public String getRequestedItem() {
		return requestedItem;
	}
	public void setRequestedItem(String requestedItem) {
		this.requestedItem = requestedItem;
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
	public String[] getChkRelative() {
		return chkRelative;
	}
	public void setChkRelative(String[] chkRelative) {
		this.chkRelative = chkRelative;
	}
	public String getIdentifyRelativeName() {
		return identifyRelativeName;
	}
	public void setIdentifyRelativeName(String identifyRelativeName) {
		this.identifyRelativeName = identifyRelativeName;
	}
	public String getIdentifyRelativeAddress() {
		return identifyRelativeAddress;
	}
	public void setIdentifyRelativeAddress(String identifyRelativeAddress) {
		this.identifyRelativeAddress = identifyRelativeAddress;
	}
	public String getIdentifyRelativeContactNo() {
		return identifyRelativeContactNo;
	}
	public void setIdentifyRelativeContactNo(String identifyRelativeContactNo) {
		this.identifyRelativeContactNo = identifyRelativeContactNo;
	}
	public String getIdentifyRelativeCode() {
		return identifyRelativeCode;
	}
	public void setIdentifyRelativeCode(String identifyRelativeCode) {
		this.identifyRelativeCode = identifyRelativeCode;
	}
	
	public void resetOpinion(ActionMapping mapping,HttpServletRequest request)
	{
		this.setRequestedOpinion("");
		this.setOpinionRemark("");
	}
	
	public void resetItem(ActionMapping mapping,HttpServletRequest request)
	{
		this.setRequestedItem("");
		this.setDeceasedItemRemarks("");
	}
	
	public void resetRelative(ActionMapping mapping,HttpServletRequest request)
	{
		this.setIdentifyRelativeName("");
		this.setIdentifyRelativeCode("");
		this.setIdentifyRelativeAddress("");
		this.setIdentifyRelativeContactNo("");
	}
	public String getIsConsentTaken() {
		return isConsentTaken;
	}
	public void setIsConsentTaken(String isConsentTaken) {
		this.isConsentTaken = isConsentTaken;
	}
	public String getIsConsentRequired() {
		return isConsentRequired;
	}
	public void setIsConsentRequired(String isConsentRequired) {
		this.isConsentRequired = isConsentRequired;
	}
	
	
}
