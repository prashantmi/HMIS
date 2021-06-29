package inpatient.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DrugAdministrationFB extends ActionForm
{
	private String hmode;
	private String patCrNo;
	private String episodeCode;
	private String episodeVisitNo;
	private String admissionNo;
	private String summary;
	private String departmentUnitcode;
	private String[] selVolumeArray; 
	private String[] selDoseTimeArray; 
	private String[] selRemarksArray;
	private String[] selExpriryDateArray;
	private String[] drugStoreSourceArray; 
	private String[] selBatchNoArray;
	private String[] selPatientBatchNoArray; 
	private String[] selDrugBrandArray; 
	private String[] selDrugBrandArrayFromStore;

	private String[] selEndExecutionTimeMin;
	private String[] selEndExecutionTimeHrs;
	private String[] selStartExecutionTimeMin;
	private String[] selStartExecutionTimeHrs;
	private String[] selDrugRouteIdArray;
	private String[] selDoseIdArray;
	private String[] selDoseNameArray;
	private String[] selIvFluidFlagArray;
	private String[] selItemTypeIdArray;
	private String[] selItemIdArray;
	private String[] selItemBrandIdArray; 
	private String[] selIndexArray;
	private String[] selAdviceDateArray;
	private String[] selSerealNoArray;
	private String[] prevDoseId;
	private String[] prevDrugRouteId;
	
	private String[] ivFluidSelIndexArray;
	private String[] ivFluidSelEndExecutionTimeHrs;
	private String[] ivFluidSelEndExecutionTimeMin;
	private String[] ivFluidSelRemarksArray;
	private String[] ivFluidSelAdviceDateArray;
	private String[] ivFluidSelSerealNoArray;
	private String[] ivFluidSelVolumeArray; 
	private String[] ivFluidSelDrugBrandIdArray;
	
	private String sysDate;
	
	private String sosSelDrugId;
	private String sosSelDoseId;
	private String sosSelDrugRouteId;
	private String sosSelDrugRouteName;
	private String sosSelStartExecutionTimeHrs;
	private String sosSelStartExecutionTimeMin;
	private String sosSelEndExecutionTimeHrs;
	private String sosSelEndExecutionTimeMin;
	private String sosSelBatchNo;
	private String sosSelExpriryDate;
	private String sosSelRemarks;
	private String sosSelVolume;
	private String[] sosFlagArray;
	
	private String activityExtId;
	private String activitySelStartExecTimeHrs;
	private String activitySelStartExecTimeMin;
	private String activitySelRemarks;
	
	private String[] extSelIndexArray;
	private String[] extSelTreatmentNameArray;
	private String[] extSelDoseTimeArray;
	private String[] extSelStartExecutionTimeHrs;
	private String[] extSelStartExecutionTimeMin;
	private String[] extSelEndExecutionTimeHrs;
	private String[] extSelEndExecutionTimeMin;
	private String[] extSelRemarksArray;
	private String[] extSelIsDurationBound;
	private String[] extSelAdviceDateArray; 
	private String[] extSelSerealNoArray; 
	
	private String sysHours;
	private String sysMinut;
	private String timeLimit;
	private String beforeTimeLimit;
	private String choice;
	private String[] reactionStatusArray;
	private String[] allergyStatusArray;
	
	////////////////////////////////////////////////////////////////////////
	
	private String selDrugID;
	private String selExecutionRemark;
	private String drugCombo;
	private String selDrugRouteId;
	private String selDrugRouteName;
	private String selDrugExcecutionDate;
	private String selBatchNo;
	private String selExpriryDate;
	private String selBrand;
	private String selDrugBrandName;
	private String PatDrugAdminRows;
	private String index;
	
	
	private String[] selDrugIdArray;
	private String[] prevSelDrugIdArray; 
	private String[] selDrugNameArray;
	private String[] prevDrugRouteName;
	private String[] prevDrugId;
	private String[] selPrevFreq;
	private String[] prevDrugName;
	private String[] selDrugExecTimeArray;
	private String[] prevSelDrufExecTimeArray; 
	private String[] prevSelInstructionArray; 
	
	private String popupInstruction; 
	private String administrationDate;
	private String viewTreatAdminChoice;
	
	
	private String scheduleIndex;
	// configration field
	private String drugSource;
	private String drugSourceFromPat;
	
	private String treatFromDate;
	private String treatToDate;
	private String admissionDate;
	private String ivfluidFlag;
	private String[] selEndExecutionTimeHrsArray;
	private String[] selEndExecutionTimeMinArray;
	private String[] selExecutionRemarkArray;
	
	private String showRemarks;
	
	private String[] selStartTimeForInTakeArray;
	private String[] ivFluidSelItemIdArray;
	private String[] ivFluidSelRouteIdArray; 
	
	private String deskMenuId;
	private String[] selDrugBrandArraySOS;
	private String selDrugBrandNameSOS;
	
	private String[] selScheduleDateArray;
	
	private String[] beforeTimeLimitArray;
	private String[] afterTimeLimitArray;
	
	public String[] getBeforeTimeLimitArray() {
		return beforeTimeLimitArray;
	}



	public void setBeforeTimeLimitArray(String[] beforeTimeLimitArray) {
		this.beforeTimeLimitArray = beforeTimeLimitArray;
	}



	public String[] getAfterTimeLimitArray() {
		return afterTimeLimitArray;
	}



	public void setAfterTimeLimitArray(String[] afterTimeLimitArray) {
		this.afterTimeLimitArray = afterTimeLimitArray;
	}



	public String[] getIvFluidSelRouteIdArray() {
		return ivFluidSelRouteIdArray;
	}



	public void setIvFluidSelRouteIdArray(String[] ivFluidSelRouteIdArray) {
		this.ivFluidSelRouteIdArray = ivFluidSelRouteIdArray;
	}



	public String[] getIvFluidSelItemIdArray() {
		return ivFluidSelItemIdArray;
	}



	public void setIvFluidSelItemIdArray(String[] ivFluidSelItemIdArray) {
		this.ivFluidSelItemIdArray = ivFluidSelItemIdArray;
	}



	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.selStartExecutionTimeHrs=null;
		this.selStartExecutionTimeMin=null;
		this.selEndExecutionTimeHrs=null;
		this.selEndExecutionTimeMin=null;
		this.ivFluidSelEndExecutionTimeHrs=null;
		this.ivFluidSelEndExecutionTimeMin=null;
		this.sosSelStartExecutionTimeHrs="";
		this.sosSelStartExecutionTimeMin="";
		this.sosSelEndExecutionTimeHrs="";
		this.sosSelEndExecutionTimeMin="";
		this.sosSelVolume="";
		this.selVolumeArray=null;
		this.ivFluidSelVolumeArray=null;
		this.activitySelStartExecTimeHrs=null;
		this.activitySelStartExecTimeMin=null;
		this.extSelEndExecutionTimeHrs=null;
		this.extSelEndExecutionTimeMin=null;
		this.extSelStartExecutionTimeHrs=null;
		this.extSelStartExecutionTimeMin=null;
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
	public String[] getSelRemarksArray() {
		return selRemarksArray;
	}
	public void setSelRemarksArray(String[] selRemarksArray) {
		this.selRemarksArray = selRemarksArray;
	}
	public String[] getSelExpriryDateArray() {
		return selExpriryDateArray;
	}
	public void setSelExpriryDateArray(String[] selExpriryDateArray) {
		this.selExpriryDateArray = selExpriryDateArray;
	}
	public String[] getSelBatchNoArray() {
		return selBatchNoArray;
	}
	public void setSelBatchNoArray(String[] selBatchNoArray) {
		this.selBatchNoArray = selBatchNoArray;
	}
	public String[] getSelEndExecutionTimeMin() {
		return selEndExecutionTimeMin;
	}
	public void setSelEndExecutionTimeMin(String[] selEndExecutionTimeMin) {
		this.selEndExecutionTimeMin = selEndExecutionTimeMin;
	}
	public String[] getSelEndExecutionTimeHrs() {
		return selEndExecutionTimeHrs;
	}
	public void setSelEndExecutionTimeHrs(String[] selEndExecutionTimeHrs) {
		this.selEndExecutionTimeHrs = selEndExecutionTimeHrs;
	}
	public String[] getSelStartExecutionTimeMin() {
		return selStartExecutionTimeMin;
	}
	public void setSelStartExecutionTimeMin(String[] selStartExecutionTimeMin) {
		this.selStartExecutionTimeMin = selStartExecutionTimeMin;
	}
	public String[] getSelStartExecutionTimeHrs() {
		return selStartExecutionTimeHrs;
	}
	public void setSelStartExecutionTimeHrs(String[] selStartExecutionTimeHrs) {
		this.selStartExecutionTimeHrs = selStartExecutionTimeHrs;
	}
	public String[] getSelDrugRouteIdArray() {
		return selDrugRouteIdArray;
	}
	public void setSelDrugRouteIdArray(String[] selDrugRouteIdArray) {
		this.selDrugRouteIdArray = selDrugRouteIdArray;
	}
	public String[] getSelDoseIdArray() {
		return selDoseIdArray;
	}
	public void setSelDoseIdArray(String[] selDoseIdArray) {
		this.selDoseIdArray = selDoseIdArray;
	}
	public String[] getSelIvFluidFlagArray() {
		return selIvFluidFlagArray;
	}
	public void setSelIvFluidFlagArray(String[] selIvFluidFlagArray) {
		this.selIvFluidFlagArray = selIvFluidFlagArray;
	}
	public String[] getSelItemTypeIdArray() {
		return selItemTypeIdArray;
	}
	public void setSelItemTypeIdArray(String[] selItemTypeIdArray) {
		this.selItemTypeIdArray = selItemTypeIdArray;
	}
	public String[] getSelItemIdArray() {
		return selItemIdArray;
	}
	public void setSelItemIdArray(String[] selItemIdArray) {
		this.selItemIdArray = selItemIdArray;
	}
	public String getSelDrugID() {
		return selDrugID;
	}
	public void setSelDrugID(String selDrugID) {
		this.selDrugID = selDrugID;
	}
	public String getSelExecutionRemark() {
		return selExecutionRemark;
	}
	public void setSelExecutionRemark(String selExecutionRemark) {
		this.selExecutionRemark = selExecutionRemark;
	}
	public String getDrugCombo() {
		return drugCombo;
	}
	public void setDrugCombo(String drugCombo) {
		this.drugCombo = drugCombo;
	}
	public String getSelDrugRouteId() {
		return selDrugRouteId;
	}
	public void setSelDrugRouteId(String selDrugRouteId) {
		this.selDrugRouteId = selDrugRouteId;
	}
	public String getSelDrugRouteName() {
		return selDrugRouteName;
	}
	public void setSelDrugRouteName(String selDrugRouteName) {
		this.selDrugRouteName = selDrugRouteName;
	}
	public String getSelDrugExcecutionDate() {
		return selDrugExcecutionDate;
	}
	public void setSelDrugExcecutionDate(String selDrugExcecutionDate) {
		this.selDrugExcecutionDate = selDrugExcecutionDate;
	}
	public String getSelBatchNo() {
		return selBatchNo;
	}
	public void setSelBatchNo(String selBatchNo) {
		this.selBatchNo = selBatchNo;
	}
	public String getSelExpriryDate() {
		return selExpriryDate;
	}
	public void setSelExpriryDate(String selExpriryDate) {
		this.selExpriryDate = selExpriryDate;
	}
	public String getSelBrand() {
		return selBrand;
	}
	public void setSelBrand(String selBrand) {
		this.selBrand = selBrand;
	}
	public String getPatDrugAdminRows() {
		return PatDrugAdminRows;
	}
	public void setPatDrugAdminRows(String patDrugAdminRows) {
		PatDrugAdminRows = patDrugAdminRows;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getSysDate() {
		return sysDate;
	}
	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}
	public String[] getSelDrugIdArray() {
		return selDrugIdArray;
	}
	public void setSelDrugIdArray(String[] selDrugIdArray) {
		this.selDrugIdArray = selDrugIdArray;
	}
	public String[] getPrevSelDrugIdArray() {
		return prevSelDrugIdArray;
	}
	public void setPrevSelDrugIdArray(String[] prevSelDrugIdArray) {
		this.prevSelDrugIdArray = prevSelDrugIdArray;
	}
	public String[] getSelDrugNameArray() {
		return selDrugNameArray;
	}
	public void setSelDrugNameArray(String[] selDrugNameArray) {
		this.selDrugNameArray = selDrugNameArray;
	}
	public String[] getPrevDrugRouteName() {
		return prevDrugRouteName;
	}
	public void setPrevDrugRouteName(String[] prevDrugRouteName) {
		this.prevDrugRouteName = prevDrugRouteName;
	}
	public String[] getPrevDrugRouteId() {
		return prevDrugRouteId;
	}
	public void setPrevDrugRouteId(String[] prevDrugRouteId) {
		this.prevDrugRouteId = prevDrugRouteId;
	}
	public String[] getPrevDrugId() {
		return prevDrugId;
	}
	public void setPrevDrugId(String[] prevDrugId) {
		this.prevDrugId = prevDrugId;
	}
	public String[] getSelPrevFreq() {
		return selPrevFreq;
	}
	public void setSelPrevFreq(String[] selPrevFreq) {
		this.selPrevFreq = selPrevFreq;
	}
	public String[] getPrevDrugName() {
		return prevDrugName;
	}
	public void setPrevDrugName(String[] prevDrugName) {
		this.prevDrugName = prevDrugName;
	}
	public String[] getSelDrugExecTimeArray() {
		return selDrugExecTimeArray;
	}
	public void setSelDrugExecTimeArray(String[] selDrugExecTimeArray) {
		this.selDrugExecTimeArray = selDrugExecTimeArray;
	}
	public String[] getPrevSelDrufExecTimeArray() {
		return prevSelDrufExecTimeArray;
	}
	public void setPrevSelDrufExecTimeArray(String[] prevSelDrufExecTimeArray) {
		this.prevSelDrufExecTimeArray = prevSelDrufExecTimeArray;
	}
	public String[] getPrevSelInstructionArray() {
		return prevSelInstructionArray;
	}
	public void setPrevSelInstructionArray(String[] prevSelInstructionArray) {
		this.prevSelInstructionArray = prevSelInstructionArray;
	}
	public String getPopupInstruction() {
		return popupInstruction;
	}
	public void setPopupInstruction(String popupInstruction) {
		this.popupInstruction = popupInstruction;
	}
	public String getAdministrationDate() {
		return administrationDate;
	}
	public void setAdministrationDate(String administrationDate) {
		this.administrationDate = administrationDate;
	}
	public String getViewTreatAdminChoice() {
		return viewTreatAdminChoice;
	}
	public void setViewTreatAdminChoice(String viewTreatAdminChoice) {
		this.viewTreatAdminChoice = viewTreatAdminChoice;
	}
	public String getScheduleIndex() {
		return scheduleIndex;
	}
	public void setScheduleIndex(String scheduleIndex) {
		this.scheduleIndex = scheduleIndex;
	}
	public String getDrugSource() {
		return drugSource;
	}
	public void setDrugSource(String drugSource) {
		this.drugSource = drugSource;
	}
	public String getDrugSourceFromPat() {
		return drugSourceFromPat;
	}
	public void setDrugSourceFromPat(String drugSourceFromPat) {
		this.drugSourceFromPat = drugSourceFromPat;
	}
	public String getTreatFromDate() {
		return treatFromDate;
	}
	public void setTreatFromDate(String treatFromDate) {
		this.treatFromDate = treatFromDate;
	}
	public String getTreatToDate() {
		return treatToDate;
	}
	public void setTreatToDate(String treatToDate) {
		this.treatToDate = treatToDate;
	}
	public String getAdmissionDate() {
		return admissionDate;
	}
	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}
	public String getIvfluidFlag() {
		return ivfluidFlag;
	}
	public void setIvfluidFlag(String ivfluidFlag) {
		this.ivfluidFlag = ivfluidFlag;
	}
	public String[] getSelEndExecutionTimeHrsArray() {
		return selEndExecutionTimeHrsArray;
	}
	public void setSelEndExecutionTimeHrsArray(String[] selEndExecutionTimeHrsArray) {
		this.selEndExecutionTimeHrsArray = selEndExecutionTimeHrsArray;
	}
	public String[] getSelEndExecutionTimeMinArray() {
		return selEndExecutionTimeMinArray;
	}
	public void setSelEndExecutionTimeMinArray(String[] selEndExecutionTimeMinArray) {
		this.selEndExecutionTimeMinArray = selEndExecutionTimeMinArray;
	}
	public String[] getSelExecutionRemarkArray() {
		return selExecutionRemarkArray;
	}
	public void setSelExecutionRemarkArray(String[] selExecutionRemarkArray) {
		this.selExecutionRemarkArray = selExecutionRemarkArray;
	}
	public String getShowRemarks() {
		return showRemarks;
	}
	public void setShowRemarks(String showRemarks) {
		this.showRemarks = showRemarks;
	}
	public String[] getSelIndexArray() {
		return selIndexArray;
	}
	public void setSelIndexArray(String[] selIndexArray) {
		this.selIndexArray = selIndexArray;
	}



	public String[] getSelAdviceDateArray() {
		return selAdviceDateArray;
	}



	public void setSelAdviceDateArray(String[] selAdviceDateArray) {
		this.selAdviceDateArray = selAdviceDateArray;
	}



	public String[] getSelSerealNoArray() {
		return selSerealNoArray;
	}



	public void setSelSerealNoArray(String[] selSerealNoArray) {
		this.selSerealNoArray = selSerealNoArray;
	}



	public String[] getIvFluidSelIndexArray() {
		return ivFluidSelIndexArray;
	}



	public void setIvFluidSelIndexArray(String[] ivFluidSelIndexArray) {
		this.ivFluidSelIndexArray = ivFluidSelIndexArray;
	}



	public String[] getIvFluidSelEndExecutionTimeHrs() {
		return ivFluidSelEndExecutionTimeHrs;
	}



	public void setIvFluidSelEndExecutionTimeHrs(
			String[] ivFluidSelEndExecutionTimeHrs) {
		this.ivFluidSelEndExecutionTimeHrs = ivFluidSelEndExecutionTimeHrs;
	}



	public String[] getIvFluidSelEndExecutionTimeMin() {
		return ivFluidSelEndExecutionTimeMin;
	}



	public void setIvFluidSelEndExecutionTimeMin(
			String[] ivFluidSelEndExecutionTimeMin) {
		this.ivFluidSelEndExecutionTimeMin = ivFluidSelEndExecutionTimeMin;
	}



	public String[] getIvFluidSelAdviceDateArray() {
		return ivFluidSelAdviceDateArray;
	}



	public void setIvFluidSelAdviceDateArray(String[] ivFluidSelAdviceDateArray) {
		this.ivFluidSelAdviceDateArray = ivFluidSelAdviceDateArray;
	}



	public String[] getIvFluidSelSerealNoArray() {
		return ivFluidSelSerealNoArray;
	}



	public void setIvFluidSelSerealNoArray(String[] ivFluidSelSerealNoArray) {
		this.ivFluidSelSerealNoArray = ivFluidSelSerealNoArray;
	}



	public String[] getIvFluidSelRemarksArray() {
		return ivFluidSelRemarksArray;
	}



	public void setIvFluidSelRemarksArray(String[] ivFluidSelRemarksArray) {
		this.ivFluidSelRemarksArray = ivFluidSelRemarksArray;
	}



	public String getSosSelDrugId() {
		return sosSelDrugId;
	}



	public void setSosSelDrugId(String sosSelDrugId) {
		this.sosSelDrugId = sosSelDrugId;
	}



	public String getSosSelDoseId() {
		return sosSelDoseId;
	}



	public void setSosSelDoseId(String sosSelDoseId) {
		this.sosSelDoseId = sosSelDoseId;
	}



	public String getSosSelDrugRouteId() {
		return sosSelDrugRouteId;
	}



	public void setSosSelDrugRouteId(String sosSelDrugRouteId) {
		this.sosSelDrugRouteId = sosSelDrugRouteId;
	}



	public String getSosSelStartExecutionTimeHrs() {
		return sosSelStartExecutionTimeHrs;
	}



	public void setSosSelStartExecutionTimeHrs(String sosSelStartExecutionTimeHrs) {
		this.sosSelStartExecutionTimeHrs = sosSelStartExecutionTimeHrs;
	}



	public String getSosSelStartExecutionTimeMin() {
		return sosSelStartExecutionTimeMin;
	}



	public void setSosSelStartExecutionTimeMin(String sosSelStartExecutionTimeMin) {
		this.sosSelStartExecutionTimeMin = sosSelStartExecutionTimeMin;
	}



	public String getSosSelEndExecutionTimeHrs() {
		return sosSelEndExecutionTimeHrs;
	}



	public void setSosSelEndExecutionTimeHrs(String sosSelEndExecutionTimeHrs) {
		this.sosSelEndExecutionTimeHrs = sosSelEndExecutionTimeHrs;
	}



	public String getSosSelEndExecutionTimeMin() {
		return sosSelEndExecutionTimeMin;
	}



	public void setSosSelEndExecutionTimeMin(String sosSelEndExecutionTimeMin) {
		this.sosSelEndExecutionTimeMin = sosSelEndExecutionTimeMin;
	}



	public String getSosSelBatchNo() {
		return sosSelBatchNo;
	}



	public void setSosSelBatchNo(String sosSelBatchNo) {
		this.sosSelBatchNo = sosSelBatchNo;
	}



	public String getSosSelExpriryDate() {
		return sosSelExpriryDate;
	}



	public void setSosSelExpriryDate(String sosSelExpriryDate) {
		this.sosSelExpriryDate = sosSelExpriryDate;
	}



	public String getSosSelRemarks() {
		return sosSelRemarks;
	}



	public void setSosSelRemarks(String sosSelRemarks) {
		this.sosSelRemarks = sosSelRemarks;
	}



	public String[] getSosFlagArray() {
		return sosFlagArray;
	}



	public void setSosFlagArray(String[] sosFlagArray) {
		this.sosFlagArray = sosFlagArray;
	}



	public String[] getPrevDoseId() {
		return prevDoseId;
	}



	public void setPrevDoseId(String[] prevDoseId) {
		this.prevDoseId = prevDoseId;
	}



	public String[] getIvFluidSelVolumeArray() {
		return ivFluidSelVolumeArray;
	}



	public void setIvFluidSelVolumeArray(String[] ivFluidSelVolumeArray) {
		this.ivFluidSelVolumeArray = ivFluidSelVolumeArray;
	}



	public String getSysHours() {
		return sysHours;
	}



	public void setSysHours(String sysHours) {
		this.sysHours = sysHours;
	}



	public String getSysMinut() {
		return sysMinut;
	}



	public void setSysMinut(String sysMinut) {
		this.sysMinut = sysMinut;
	}



	public String[] getSelDoseTimeArray() {
		return selDoseTimeArray;
	}



	public void setSelDoseTimeArray(String[] selDoseTimeArray) {
		this.selDoseTimeArray = selDoseTimeArray;
	}



	public String getTimeLimit() {
		return timeLimit;
	}



	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}



	public String getBeforeTimeLimit() {
		return beforeTimeLimit;
	}



	public void setBeforeTimeLimit(String beforeTimeLimit) {
		this.beforeTimeLimit = beforeTimeLimit;
	}



	public String[] getSelVolumeArray() {
		return selVolumeArray;
	}



	public void setSelVolumeArray(String[] selVolumeArray) {
		this.selVolumeArray = selVolumeArray;
	}



	public String getSosSelVolume() {
		return sosSelVolume;
	}



	public void setSosSelVolume(String sosSelVolume) {
		this.sosSelVolume = sosSelVolume;
	}



	public String getActivityExtId() {
		return activityExtId;
	}



	public void setActivityExtId(String activityExtId) {
		this.activityExtId = activityExtId;
	}



	public String getActivitySelStartExecTimeHrs() {
		return activitySelStartExecTimeHrs;
	}



	public void setActivitySelStartExecTimeHrs(String activitySelStartExecTimeHrs) {
		this.activitySelStartExecTimeHrs = activitySelStartExecTimeHrs;
	}



	public String getActivitySelStartExecTimeMin() {
		return activitySelStartExecTimeMin;
	}



	public void setActivitySelStartExecTimeMin(String activitySelStartExecTimeMin) {
		this.activitySelStartExecTimeMin = activitySelStartExecTimeMin;
	}



	public String getActivitySelRemarks() {
		return activitySelRemarks;
	}



	public void setActivitySelRemarks(String activitySelRemarks) {
		this.activitySelRemarks = activitySelRemarks;
	}



	public String[] getExtSelIndexArray() {
		return extSelIndexArray;
	}



	public void setExtSelIndexArray(String[] extSelIndexArray) {
		this.extSelIndexArray = extSelIndexArray;
	}



	public String[] getExtSelTreatmentNameArray() {
		return extSelTreatmentNameArray;
	}



	public void setExtSelTreatmentNameArray(String[] extSelTreatmentNameArray) {
		this.extSelTreatmentNameArray = extSelTreatmentNameArray;
	}



	public String[] getExtSelDoseTimeArray() {
		return extSelDoseTimeArray;
	}



	public void setExtSelDoseTimeArray(String[] extSelDoseTimeArray) {
		this.extSelDoseTimeArray = extSelDoseTimeArray;
	}



	public String[] getExtSelStartExecutionTimeHrs() {
		return extSelStartExecutionTimeHrs;
	}



	public void setExtSelStartExecutionTimeHrs(String[] extSelStartExecutionTimeHrs) {
		this.extSelStartExecutionTimeHrs = extSelStartExecutionTimeHrs;
	}



	public String[] getExtSelStartExecutionTimeMin() {
		return extSelStartExecutionTimeMin;
	}



	public void setExtSelStartExecutionTimeMin(String[] extSelStartExecutionTimeMin) {
		this.extSelStartExecutionTimeMin = extSelStartExecutionTimeMin;
	}



	public String[] getExtSelEndExecutionTimeHrs() {
		return extSelEndExecutionTimeHrs;
	}



	public void setExtSelEndExecutionTimeHrs(String[] extSelEndExecutionTimeHrs) {
		this.extSelEndExecutionTimeHrs = extSelEndExecutionTimeHrs;
	}



	public String[] getExtSelEndExecutionTimeMin() {
		return extSelEndExecutionTimeMin;
	}



	public void setExtSelEndExecutionTimeMin(String[] extSelEndExecutionTimeMin) {
		this.extSelEndExecutionTimeMin = extSelEndExecutionTimeMin;
	}



	public String[] getExtSelRemarksArray() {
		return extSelRemarksArray;
	}



	public void setExtSelRemarksArray(String[] extSelRemarksArray) {
		this.extSelRemarksArray = extSelRemarksArray;
	}



	public String[] getExtSelIsDurationBound() {
		return extSelIsDurationBound;
	}



	public void setExtSelIsDurationBound(String[] extSelIsDurationBound) {
		this.extSelIsDurationBound = extSelIsDurationBound;
	}



	public String[] getExtSelAdviceDateArray() {
		return extSelAdviceDateArray;
	}



	public void setExtSelAdviceDateArray(String[] extSelAdviceDateArray) {
		this.extSelAdviceDateArray = extSelAdviceDateArray;
	}



	public String[] getExtSelSerealNoArray() {
		return extSelSerealNoArray;
	}



	public void setExtSelSerealNoArray(String[] extSelSerealNoArray) {
		this.extSelSerealNoArray = extSelSerealNoArray;
	}



	public String getChoice() {
		return choice;
	}



	public void setChoice(String choice) {
		this.choice = choice;
	}



	public String[] getReactionStatusArray() {
		return reactionStatusArray;
	}



	public void setReactionStatusArray(String[] reactionStatusArray) {
		this.reactionStatusArray = reactionStatusArray;
	}



	public String[] getAllergyStatusArray() {
		return allergyStatusArray;
	}



	public void setAllergyStatusArray(String[] allergyStatusArray) {
		this.allergyStatusArray = allergyStatusArray;
	}



	public String[] getSelStartTimeForInTakeArray() {
		return selStartTimeForInTakeArray;
	}



	public void setSelStartTimeForInTakeArray(String[] selStartTimeForInTakeArray) {
		this.selStartTimeForInTakeArray = selStartTimeForInTakeArray;
	}



	public String[] getSelDoseNameArray()
	{
		return selDoseNameArray;
	}



	public void setSelDoseNameArray(String[] selDoseNameArray)
	{
		this.selDoseNameArray = selDoseNameArray;
	}



	public String getDeskMenuId() {
		return deskMenuId;
	}



	public void setDeskMenuId(String deskMenuId) {
		this.deskMenuId = deskMenuId;
	}



	public String[] getDrugStoreSourceArray() {
		return drugStoreSourceArray;
	}



	public void setDrugStoreSourceArray(String[] drugStoreSourceArray) {
		this.drugStoreSourceArray = drugStoreSourceArray;
	}



	public String[] getSelPatientBatchNoArray() {
		return selPatientBatchNoArray;
	}



	public void setSelPatientBatchNoArray(String[] selPatientBatchNoArray) {
		this.selPatientBatchNoArray = selPatientBatchNoArray;
	}



	public String[] getSelDrugBrandArray() {
		return selDrugBrandArray;
	}



	public void setSelDrugBrandArray(String[] selDrugBrandArray) {
		this.selDrugBrandArray = selDrugBrandArray;
	}



	public String[] getSelDrugBrandArrayFromStore() {
		return selDrugBrandArrayFromStore;
	}



	public void setSelDrugBrandArrayFromStore(String[] selDrugBrandArrayFromStore) {
		this.selDrugBrandArrayFromStore = selDrugBrandArrayFromStore;
	}



	public String getDepartmentUnitcode() {
		return departmentUnitcode;
	}



	public void setDepartmentUnitcode(String departmentUnitcode) {
		this.departmentUnitcode = departmentUnitcode;
	}



	public String getSelDrugBrandName() {
		return selDrugBrandName;
	}



	public void setSelDrugBrandName(String selDrugBrandName) {
		this.selDrugBrandName = selDrugBrandName;
	}



	public String getSummary() {
		return summary;
	}



	public void setSummary(String summary) {
		this.summary = summary;
	}



	public String[] getSelDrugBrandArraySOS() {
		return selDrugBrandArraySOS;
	}



	public void setSelDrugBrandArraySOS(String[] selDrugBrandArraySOS) {
		this.selDrugBrandArraySOS = selDrugBrandArraySOS;
	}



	public String getSelDrugBrandNameSOS() {
		return selDrugBrandNameSOS;
	}



	public void setSelDrugBrandNameSOS(String selDrugBrandNameSOS) {
		this.selDrugBrandNameSOS = selDrugBrandNameSOS;
	}



	public String[] getSelItemBrandIdArray() {
		return selItemBrandIdArray;
	}



	public void setSelItemBrandIdArray(String[] selItemBrandIdArray) {
		this.selItemBrandIdArray = selItemBrandIdArray;
	}



	public String[] getIvFluidSelDrugBrandIdArray() {
		return ivFluidSelDrugBrandIdArray;
	}



	public void setIvFluidSelDrugBrandIdArray(
			String[] ivFluidSelDrugBrandIdArray) {
		this.ivFluidSelDrugBrandIdArray = ivFluidSelDrugBrandIdArray;
	}



	public String getSosSelDrugRouteName() {
		return sosSelDrugRouteName;
	}



	public void setSosSelDrugRouteName(String sosSelDrugRouteName) {
		this.sosSelDrugRouteName = sosSelDrugRouteName;
	}



	public String[] getSelScheduleDateArray() {
		return selScheduleDateArray;
	}



	public void setSelScheduleDateArray(String[] selScheduleDateArray) {
		this.selScheduleDateArray = selScheduleDateArray;
	}



	


	



	


	
	
}
