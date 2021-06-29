package inpatient.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class BloodTransfusionFB extends ActionForm
{
	private String hmode;
	private String patCrNo;
	private String episodeCode;
	private String episodeVisitNo;
	private String admissionNo;
	private String[] bloodBagExpiry;
	private String[] bloodBagSequenceNo;
	private String[] bloodBagNo;
	private String[] startHr;
	private String[] startMin;
	private String[] endHr;
	private String[] endMin;
	private String[] selectedBag;
	private String[] remarks;
	private String[] bloodGroup;
	private String[] componentName;
	private String[] phlebotomyArm;
	private String[] transfusionStartDate;
	private String[] transfusionEndDate;
	private String[] transfusionStatus;
	
	private String[] stockBagSerialNo;
	private String[] stockDate;
	
	private String transfusionEndDateForView;
	private String transfusionStartDateForView;
	private String sysdate;
	
	private String popupBloodgroup;
	private String popupcomponent;
	private String popupBagExpiry;
	private String popupBloodBagNo;
	private String popupTransfusionDate;
	private String popupStartHr;
	private String popupStartMin;
	
	private String[] transfussionRate;
	private String[] qtyTransfussed;
	private String[] requisitionNo;
	private String[] bagVolume;
	
	private String isTransfusionReaction;
	private String selBagNSeqNo;
	private String countChk;
	
	
	private String popupCount;
	private String reqNo;
	private String reactionFormSendDate;
	private String reactionStartDate;
	private String reactionStartHr;
	private String reactionStartMin;
	private String controlSummary;
	private String reactionSummary;
	private String isFormFilled;
	private String reactionEndHr;
	private String reactionEndMin;
	private String isTransfusionSet;
	private String isPostTransfusionSample;
	private String bloodComponentTransfusedAmount;
	private String reactionFormSendTimeHr;
	private String reactionFormSendTimeMin;
	private String bagTransfuseDate;
	
	private String[] selectedBagArray;
	private String[] bagTransfuseDateArray;
	
	private String strUnitNo;
	
	private String strAdverseEffect;

	/**
	 * @return the bagTransfuseDateArray
	 */
	public String[] getBagTransfuseDateArray() {
		return bagTransfuseDateArray;
	}


	/**
	 * @param bagTransfuseDateArray the bagTransfuseDateArray to set
	 */
	public void setBagTransfuseDateArray(String[] bagTransfuseDateArray) {
		this.bagTransfuseDateArray = bagTransfuseDateArray;
	}


	/**
	 * @return the selectedBagArray
	 */
	public String[] getSelectedBagArray() {
		return selectedBagArray;
	}


	/**
	 * @param selectedBagArray the selectedBagArray to set
	 */
	public void setSelectedBagArray(String[] selectedBagArray) {
		this.selectedBagArray = selectedBagArray;
	}


	/**
	 * @return the bagTransfuseDate
	 */
	public String getBagTransfuseDate() {
		return bagTransfuseDate;
	}


	/**
	 * @param bagTransfuseDate the bagTransfuseDate to set
	 */
	public void setBagTransfuseDate(String bagTransfuseDate) {
		this.bagTransfuseDate = bagTransfuseDate;
	}


	/**
	 * @return the isTransfusionReaction
	 */
	public String getIsTransfusionReaction() {
		return isTransfusionReaction;
	}


	/**
	 * @param isTransfusionReaction the isTransfusionReaction to set
	 */
	public void setIsTransfusionReaction(String isTransfusionReaction) {
		this.isTransfusionReaction = isTransfusionReaction;
	}


	public String[] getBagVolume() {
		return bagVolume;
	}


	public void setBagVolume(String[] bagVolume) {
		this.bagVolume = bagVolume;
	}


	public String[] getTransfussionRate() {
		return transfussionRate;
	}


	public void setTransfussionRate(String[] transfussionRate) {
		this.transfussionRate = transfussionRate;
	}


	public String[] getQtyTransfussed() {
		return qtyTransfussed;
	}


	public void setQtyTransfussed(String[] qtyTransfussed) {
		this.qtyTransfussed = qtyTransfussed;
	}


	public String[] getRequisitionNo() {
		return requisitionNo;
	}


	public void setRequisitionNo(String[] requisitionNo) {
		this.requisitionNo = requisitionNo;
	}


	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.endHr=null;
		this.endMin=null;
		this.startHr=null;
		this.startMin=null;
		this.phlebotomyArm=null;
		this.remarks=null;
		this.qtyTransfussed=null;
		this.transfussionRate=null;
		this.isTransfusionReaction=null;
	}
	

	public String getPopupBloodBagNo() {
		return popupBloodBagNo;
	}

	public void setPopupBloodBagNo(String popupBloodBagNo) {
		this.popupBloodBagNo = popupBloodBagNo;
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

	public String[] getRemarks() {
		return remarks;
	}

	public void setRemarks(String[] remarks) {
		this.remarks = remarks;
	}

	public String[] getStartHr() {
		return startHr;
	}

	public void setStartHr(String[] startHr) {
		this.startHr = startHr;
	}

	public String[] getStartMin() {
		return startMin;
	}

	public void setStartMin(String[] startMin) {
		this.startMin = startMin;
	}

	public String[] getEndHr() {
		return endHr;
	}

	public void setEndHr(String[] endHr) {
		this.endHr = endHr;
	}

	public String[] getEndMin() {
		return endMin;
	}

	public void setEndMin(String[] endMin) {
		this.endMin = endMin;
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

	public String[] getSelectedBag() {
		return selectedBag;
	}

	public void setSelectedBag(String[] selectedBag) {
		this.selectedBag = selectedBag;
	}

	public String[] getBloodBagSequenceNo() {
		return bloodBagSequenceNo;
	}

	public void setBloodBagSequenceNo(String[] bloodBagSequenceNo) {
		this.bloodBagSequenceNo = bloodBagSequenceNo;
	}

	public String[] getBloodBagNo() {
		return bloodBagNo;
	}

	public void setBloodBagNo(String[] bloodBagNo) {
		this.bloodBagNo = bloodBagNo;
	}

	public String[] getBloodBagExpiry() {
		return bloodBagExpiry;
	}

	public void setBloodBagExpiry(String[] bloodBagExpiry) {
		this.bloodBagExpiry = bloodBagExpiry;
	}

	public String[] getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String[] bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String[] getComponentName() {
		return componentName;
	}

	public void setComponentName(String[] componentName) {
		this.componentName = componentName;
	}

	public String[] getPhlebotomyArm() {
		return phlebotomyArm;
	}

	public void setPhlebotomyArm(String[] phlebotomyArm) {
		this.phlebotomyArm = phlebotomyArm;
	}

	public String[] getTransfusionStartDate() {
		return transfusionStartDate;
	}

	public void setTransfusionStartDate(String[] transfusionStartDate) {
		this.transfusionStartDate = transfusionStartDate;
	}

	public String[] getTransfusionEndDate() {
		return transfusionEndDate;
	}

	public void setTransfusionEndDate(String[] transfusionEndDate) {
		this.transfusionEndDate = transfusionEndDate;
	}

	public String getTransfusionEndDateForView() {
		return transfusionEndDateForView;
	}

	public void setTransfusionEndDateForView(String transfusionEndDateForView) {
		this.transfusionEndDateForView = transfusionEndDateForView;
	}

	public String getTransfusionStartDateForView() {
		return transfusionStartDateForView;
	}

	public void setTransfusionStartDateForView(String transfusionStartDateForView) {
		this.transfusionStartDateForView = transfusionStartDateForView;
	}

	public String[] getStockBagSerialNo() {
		return stockBagSerialNo;
	}

	public void setStockBagSerialNo(String[] stockBagSerialNo) {
		this.stockBagSerialNo = stockBagSerialNo;
	}

	public String[] getStockDate() {
		return stockDate;
	}

	public void setStockDate(String[] stockDate) {
		this.stockDate = stockDate;
	}


	public String[] getTransfusionStatus() {
		return transfusionStatus;
	}


	public void setTransfusionStatus(String[] transfusionStatus) {
		this.transfusionStatus = transfusionStatus;
	}


	public String getSysdate() {
		return sysdate;
	}


	public void setSysdate(String sysdate) {
		this.sysdate = sysdate;
	}


	/**
	 * @return the selBagNSeqNo
	 */
	public String getSelBagNSeqNo() {
		return selBagNSeqNo;
	}


	/**
	 * @param selBagNSeqNo the selBagNSeqNo to set
	 */
	public void setSelBagNSeqNo(String selBagNSeqNo) {
		this.selBagNSeqNo = selBagNSeqNo;
	}


	/**
	 * @return the countChk
	 */
	public String getCountChk() {
		return countChk;
	}


	/**
	 * @param countChk the countChk to set
	 */
	public void setCountChk(String countChk) {
		this.countChk = countChk;
	}


	/**
	 * @return the popupCount
	 */
	public String getPopupCount() {
		return popupCount;
	}


	/**
	 * @param popupCount the popupCount to set
	 */
	public void setPopupCount(String popupCount) {
		this.popupCount = popupCount;
	}


	/**
	 * @return the reactionFormSendDate
	 */
	public String getReactionFormSendDate() {
		return reactionFormSendDate;
	}


	/**
	 * @param reactionFormSendDate the reactionFormSendDate to set
	 */
	public void setReactionFormSendDate(String reactionFormSendDate) {
		this.reactionFormSendDate = reactionFormSendDate;
	}


	/**
	 * @return the reactionStartDate
	 */
	public String getReactionStartDate() {
		return reactionStartDate;
	}


	/**
	 * @param reactionStartDate the reactionStartDate to set
	 */
	public void setReactionStartDate(String reactionStartDate) {
		this.reactionStartDate = reactionStartDate;
	}


	/**
	 * @return the reactionStartHr
	 */
	public String getReactionStartHr() {
		return reactionStartHr;
	}


	/**
	 * @param reactionStartHr the reactionStartHr to set
	 */
	public void setReactionStartHr(String reactionStartHr) {
		this.reactionStartHr = reactionStartHr;
	}



	/**
	 * @return the reactionStartMin
	 */
	public String getReactionStartMin() {
		return reactionStartMin;
	}


	/**
	 * @param reactionStartMin the reactionStartMin to set
	 */
	public void setReactionStartMin(String reactionStartMin) {
		this.reactionStartMin = reactionStartMin;
	}


	/**
	 * @return the controlSummary
	 */
	public String getControlSummary() {
		return controlSummary;
	}


	/**
	 * @param controlSummary the controlSummary to set
	 */
	public void setControlSummary(String controlSummary) {
		this.controlSummary = controlSummary;
	}


	/**
	 * @return the reactionSummary
	 */
	public String getReactionSummary() {
		return reactionSummary;
	}


	/**
	 * @param reactionSummary the reactionSummary to set
	 */
	public void setReactionSummary(String reactionSummary) {
		this.reactionSummary = reactionSummary;
	}


	/**
	 * @return the isFormFilled
	 */
	public String getIsFormFilled() {
		return isFormFilled;
	}


	/**
	 * @param isFormFilled the isFormFilled to set
	 */
	public void setIsFormFilled(String isFormFilled) {
		this.isFormFilled = isFormFilled;
	}


	/**
	 * @return the isTransfusionSet
	 */
	public String getIsTransfusionSet() {
		return isTransfusionSet;
	}


	/**
	 * @param isTransfusionSet the isTransfusionSet to set
	 */
	public void setIsTransfusionSet(String isTransfusionSet) {
		this.isTransfusionSet = isTransfusionSet;
	}


	/**
	 * @return the isPostTransfusionSample
	 */
	public String getIsPostTransfusionSample() {
		return isPostTransfusionSample;
	}


	/**
	 * @param isPostTransfusionSample the isPostTransfusionSample to set
	 */
	public void setIsPostTransfusionSample(String isPostTransfusionSample) {
		this.isPostTransfusionSample = isPostTransfusionSample;
	}


	/**
	 * @return the bloodComponentTransfusedAmount
	 */
	public String getBloodComponentTransfusedAmount() {
		return bloodComponentTransfusedAmount;
	}


	/**
	 * @param bloodComponentTransfusedAmount the bloodComponentTransfusedAmount to set
	 */
	public void setBloodComponentTransfusedAmount(
			String bloodComponentTransfusedAmount) {
		this.bloodComponentTransfusedAmount = bloodComponentTransfusedAmount;
	}


	/**
	 * @return the reactionFormSendTimeHr
	 */
	public String getReactionFormSendTimeHr() {
		return reactionFormSendTimeHr;
	}


	/**
	 * @param reactionFormSendTimeHr the reactionFormSendTimeHr to set
	 */
	public void setReactionFormSendTimeHr(String reactionFormSendTimeHr) {
		this.reactionFormSendTimeHr = reactionFormSendTimeHr;
	}


	/**
	 * @return the reactionFormSendTimeMin
	 */
	public String getReactionFormSendTimeMin() {
		return reactionFormSendTimeMin;
	}


	/**
	 * @param reactionFormSendTimeMin the reactionFormSendTimeMin to set
	 */
	public void setReactionFormSendTimeMin(String reactionFormSendTimeMin) {
		this.reactionFormSendTimeMin = reactionFormSendTimeMin;
	}


	/**
	 * @return the reqNo
	 */
	public String getReqNo() {
		return reqNo;
	}


	/**
	 * @param reqNo the reqNo to set
	 */
	public void setReqNo(String reqNo) {
		this.reqNo = reqNo;
	}


	/**
	 * @return the reactionEndHr
	 */
	public String getReactionEndHr() {
		return reactionEndHr;
	}


	/**
	 * @param reactionEndHr the reactionEndHr to set
	 */
	public void setReactionEndHr(String reactionEndHr) {
		this.reactionEndHr = reactionEndHr;
	}


	/**
	 * @return the reactionEndMin
	 */
	public String getReactionEndMin() {
		return reactionEndMin;
	}


	/**
	 * @param reactionEndMin the reactionEndMin to set
	 */
	public void setReactionEndMin(String reactionEndMin) {
		this.reactionEndMin = reactionEndMin;
	}


	/**
	 * @return the popupTransfusionDate
	 */
	public String getPopupTransfusionDate() {
		return popupTransfusionDate;
	}


	/**
	 * @param popupTransfusionDate the popupTransfusionDate to set
	 */
	public void setPopupTransfusionDate(String popupTransfusionDate) {
		this.popupTransfusionDate = popupTransfusionDate;
	}


	/**
	 * @return the popupStartHr
	 */
	public String getPopupStartHr() {
		return popupStartHr;
	}


	/**
	 * @param popupStartHr the popupStartHr to set
	 */
	public void setPopupStartHr(String popupStartHr) {
		this.popupStartHr = popupStartHr;
	}


	/**
	 * @return the popupStartMin
	 */
	public String getPopupStartMin() {
		return popupStartMin;
	}


	/**
	 * @param popupStartMin the popupStartMin to set
	 */
	public void setPopupStartMin(String popupStartMin) {
		this.popupStartMin = popupStartMin;
	}
	public String getStrUnitNo() {
		return strUnitNo;
	}


	public void setStrUnitNo(String strUnitNo) {
		this.strUnitNo = strUnitNo;
	}


	public String getStrAdverseEffect() {
		return strAdverseEffect;
	}


	public void setStrAdverseEffect(String strAdverseEffect) {
		this.strAdverseEffect = strAdverseEffect;
	}
	
}
