package inpatient.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class BloodTransfusionReactionFB extends ActionForm
{
	private String hmode;
	private String patCrNo;
	private String episodeCode;
	private String episodeVisitNo;
	private String admissionNo;
	private String selectedBag;
	private String[] selectedBagArray;
	private String[] bagNoArray;
	private String[] bagSeqNoArray;
	private String[] transfusionStatusArray;
	private String[] sourceFlagArray;
	private String[] componentNameArray;
	private String[] bloodGroupArray;
	private String[] expiryDateArray; 
	private String[] requisitionNoArray;
	private String[] stockBagSerialNoArray;
	private String[] stockDateArray; 
	private String reactionStartDate;
	private String reactionEndDate;
	private String reactionStartHr;
	private String reactionStartMin;
	private String reactionEndHr;
	private String reactionEndMin;
	private String reactionSummary;
	private String controlSummary;
	private String sysDate;
	private String[] transfussionDateArray; 
	
	private String popupBloodgroup;
	private String popupcomponent;
	private String popupBagExpiry;
	private String popupBloodBagNo;
	private String popupSource;
	
	private String macroHead;
	private String macroProcessId;
	private String targetField;
	
	
	public String getTargetField() {
		return targetField;
	}

	public void setTargetField(String targetField) {
		this.targetField = targetField;
	}

	public String getMacroHead() {
		return macroHead;
	}

	public void setMacroHead(String macroHead) {
		this.macroHead = macroHead;
	}

	public String getMacroProcessId() {
		return macroProcessId;
	}

	public void setMacroProcessId(String macroProcessId) {
		this.macroProcessId = macroProcessId;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.bagNoArray=null;
		this.bagSeqNoArray=null;
		this.bloodGroupArray=null;
		this.componentNameArray=null;
		this.expiryDateArray=null;
		this.requisitionNoArray=null;
		this.stockBagSerialNoArray=null;
		this.stockDateArray=null;
		this.controlSummary="";
		this.reactionEndDate="";
		this.reactionEndHr="";
		this.reactionEndMin="";
		this.reactionStartDate="";
		this.reactionStartHr="";
		this.reactionStartMin="";
		this.reactionSummary="";
		this.transfusionStatusArray=null;
		this.selectedBag="";
		this.selectedBagArray=null;
		
	}

	public String getPopupBloodgroup() {
		return popupBloodgroup;
	}

	public void setPopupBloodgroup(String popupBloodgroup) {
		this.popupBloodgroup = popupBloodgroup;
	}

	public String getPopupcomponent() {
		return popupcomponent;
	}

	public void setPopupcomponent(String popupcomponent) {
		this.popupcomponent = popupcomponent;
	}

	public String getPopupBagExpiry() {
		return popupBagExpiry;
	}

	public void setPopupBagExpiry(String popupBagExpiry) {
		this.popupBagExpiry = popupBagExpiry;
	}

	public String getPopupBloodBagNo() {
		return popupBloodBagNo;
	}

	public void setPopupBloodBagNo(String popupBloodBagNo) {
		this.popupBloodBagNo = popupBloodBagNo;
	}

	public String getPopupSource() {
		return popupSource;
	}

	public void setPopupSource(String popupSource) {
		this.popupSource = popupSource;
	}

	public String getReactionStartDate() {
		return reactionStartDate;
	}

	public void setReactionStartDate(String reactionStartDate) {
		this.reactionStartDate = reactionStartDate;
	}

	public String getReactionEndDate() {
		return reactionEndDate;
	}

	public void setReactionEndDate(String reactionEndDate) {
		this.reactionEndDate = reactionEndDate;
	}

	public String getReactionStartHr() {
		return reactionStartHr;
	}

	public void setReactionStartHr(String reactionStartHr) {
		this.reactionStartHr = reactionStartHr;
	}

	public String getReactionStartMin() {
		return reactionStartMin;
	}

	public void setReactionStartMin(String reactionStartMin) {
		this.reactionStartMin = reactionStartMin;
	}

	public String getReactionEndHr() {
		return reactionEndHr;
	}

	public void setReactionEndHr(String reactionEndHr) {
		this.reactionEndHr = reactionEndHr;
	}

	public String getReactionEndMin() {
		return reactionEndMin;
	}

	public void setReactionEndMin(String reactionEndMin) {
		this.reactionEndMin = reactionEndMin;
	}

	public String getReactionSummary() {
		return reactionSummary;
	}

	public void setReactionSummary(String reactionSummary) {
		this.reactionSummary = reactionSummary;
	}

	public String getControlSummary() {
		return controlSummary;
	}

	public void setControlSummary(String controlSummary) {
		this.controlSummary = controlSummary;
	}

	public String[] getBagNoArray() {
		return bagNoArray;
	}

	public void setBagNoArray(String[] bagNoArray) {
		this.bagNoArray = bagNoArray;
	}

	public String[] getBagSeqNoArray() {
		return bagSeqNoArray;
	}

	public void setBagSeqNoArray(String[] bagSeqNoArray) {
		this.bagSeqNoArray = bagSeqNoArray;
	}

	public String[] getTransfusionStatusArray() {
		return transfusionStatusArray;
	}

	public void setTransfusionStatusArray(String[] transfusionStatusArray) {
		this.transfusionStatusArray = transfusionStatusArray;
	}

	public String[] getSourceFlagArray() {
		return sourceFlagArray;
	}

	public void setSourceFlagArray(String[] sourceFlagArray) {
		this.sourceFlagArray = sourceFlagArray;
	}

	public String getSelectedBag() {
		return selectedBag;
	}

	public void setSelectedBag(String selectedBag) {
		this.selectedBag = selectedBag;
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

	public String getAdmissionNo() {
		return admissionNo;
	}

	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
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

	public String[] getComponentNameArray() {
		return componentNameArray;
	}

	public void setComponentNameArray(String[] componentNameArray) {
		this.componentNameArray = componentNameArray;
	}

	public String[] getBloodGroupArray() {
		return bloodGroupArray;
	}

	public void setBloodGroupArray(String[] bloodGroupArray) {
		this.bloodGroupArray = bloodGroupArray;
	}

	public String[] getExpiryDateArray() {
		return expiryDateArray;
	}

	public void setExpiryDateArray(String[] expiryDateArray) {
		this.expiryDateArray = expiryDateArray;
	}

	public String[] getRequisitionNoArray() {
		return requisitionNoArray;
	}

	public void setRequisitionNoArray(String[] requisitionNoArray) {
		this.requisitionNoArray = requisitionNoArray;
	}

	public String[] getStockBagSerialNoArray() {
		return stockBagSerialNoArray;
	}

	public void setStockBagSerialNoArray(String[] stockBagSerialNoArray) {
		this.stockBagSerialNoArray = stockBagSerialNoArray;
	}

	public String[] getStockDateArray() {
		return stockDateArray;
	}

	public void setStockDateArray(String[] stockDateArray) {
		this.stockDateArray = stockDateArray;
	}

	public String getSysDate() {
		return sysDate;
	}

	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}

	public String[] getTransfussionDateArray() {
		return transfussionDateArray;
	}

	public void setTransfussionDateArray(String[] transfussionDateArray) {
		this.transfussionDateArray = transfussionDateArray;
	}

	public String[] getSelectedBagArray() {
		return selectedBagArray;
	}

	public void setSelectedBagArray(String[] selectedBagArray) {
		this.selectedBagArray = selectedBagArray;
	}

	
}
