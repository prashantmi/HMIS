package inpatient.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DrugReactionFB extends ActionForm
{
	private String hmode;
	private String patCrNo;
	private String episodeCode;
	private String episodeVisitNo;
	private String admissionNo;
	private String admissionDate;
	private String sysDate;
	
	private String viewTreatAdminChoice;
	private String reactionStartDate;
	private String reactionEndDate;
	private String reactionStartHr;
	private String reactionStartMin;
	private String reactionEndHr;
	private String reactionEndMin;
	private String reactionSummary;
	private String controlSummary;
		
	private String selAdministrationDate;
	private String selectedDrug;
	private String serialNo;
	
	private String templateId;
	private String prevItemId;
	
	private String[] itemIdArray;
	private String[] batchNoArray;
	private String[] drugGivenTimeArray;
	private String[] selectedDrugArray; 
	
	private String showControlSummary;
	private String showReactionSummary;
	private String administrationDate;
	private String administrationTimeHour;
	private String administrationTimeMinute;
	private String hiddenReactionSummary;
	private String hiddenControlSummary;
	private String itemName;
	private String drugBrandName;
	private String drugBrandId;
	
	private String deskMenuId;
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getHiddenReactionSummary() {
		return hiddenReactionSummary;
	}
	public void setHiddenReactionSummary(String hiddenReactionSummary) {
		this.hiddenReactionSummary = hiddenReactionSummary;
	}
	public String getHiddenControlSummary() {
		return hiddenControlSummary;
	}
	public void setHiddenControlSummary(String hiddenControlSummary) {
		this.hiddenControlSummary = hiddenControlSummary;
	}
	public String getAdministrationDate() {
		return administrationDate;
	}
	public void setAdministrationDate(String administrationDate) {
		this.administrationDate = administrationDate;
	}
	
	public String getAdministrationTimeHour() {
		return administrationTimeHour;
	}
	public void setAdministrationTimeHour(String administrationTimeHour) {
		this.administrationTimeHour = administrationTimeHour;
	}
	public String getAdministrationTimeMinute() {
		return administrationTimeMinute;
	}
	public void setAdministrationTimeMinute(String administrationTimeMinute) {
		this.administrationTimeMinute = administrationTimeMinute;
	}
	public String getShowControlSummary() {
		return showControlSummary;
	}
	public void setShowControlSummary(String showControlSummary) {
		this.showControlSummary = showControlSummary;
	}
	public String getShowReactionSummary() {
		return showReactionSummary;
	}
	public void setShowReactionSummary(String showReactionSummary) {
		this.showReactionSummary = showReactionSummary;
	}
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		//this.selectedDrug="";
		this.reactionEndHr="";
		this.reactionEndDate="";
		this.reactionEndMin="";
		this.reactionStartDate="";
		this.reactionStartHr="";
		this.reactionStartMin="";
		this.reactionSummary="";
		this.controlSummary="";
	}
	public String[] getSelectedDrugArray() {
		return selectedDrugArray;
	}

	public void setSelectedDrugArray(String[] selectedDrugArray) {
		this.selectedDrugArray = selectedDrugArray;
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


	public String getViewTreatAdminChoice() {
		return viewTreatAdminChoice;
	}

	public void setViewTreatAdminChoice(String viewTreatAdminChoice) {
		this.viewTreatAdminChoice = viewTreatAdminChoice;
	}

	public String getSysDate() {
		return sysDate;
	}

	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
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

	public String getAdmissionNo() {
		return admissionNo;
	}

	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}

	public String[] getItemIdArray() {
		return itemIdArray;
	}

	public void setItemIdArray(String[] itemIdArray) {
		this.itemIdArray = itemIdArray;
	}

	public String[] getBatchNoArray() {
		return batchNoArray;
	}

	public void setBatchNoArray(String[] batchNoArray) {
		this.batchNoArray = batchNoArray;
	}

	public String[] getDrugGivenTimeArray() {
		return drugGivenTimeArray;
	}

	public void setDrugGivenTimeArray(String[] drugGivenTimeArray) {
		this.drugGivenTimeArray = drugGivenTimeArray;
	}

	public String getSelAdministrationDate() {
		return selAdministrationDate;
	}

	public void setSelAdministrationDate(String selAdministrationDate) {
		this.selAdministrationDate = selAdministrationDate;
	}

	public String getSelectedDrug() {
		return selectedDrug;
	}

	public void setSelectedDrug(String selectedDrug) {
		this.selectedDrug = selectedDrug;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public String getPrevItemId() {
		return prevItemId;
	}
	public void setPrevItemId(String prevItemId) {
		this.prevItemId = prevItemId;
	}
	public String getDeskMenuId() {
		return deskMenuId;
	}
	public void setDeskMenuId(String deskMenuId) {
		this.deskMenuId = deskMenuId;
	}
	public String getDrugBrandName() {
		return drugBrandName;
	}
	public void setDrugBrandName(String drugBrandName) {
		this.drugBrandName = drugBrandName;
	}
	public String getDrugBrandId() {
		return drugBrandId;
	}
	public void setDrugBrandId(String drugBrandId) {
		this.drugBrandId = drugBrandId;
	}
}
