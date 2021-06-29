/**
##		Date					: 19-01-2018
##		Reason	(CR/PRS)		: Treatment Given new process
##		Created By				: Vasu
*/


package ehr.treatmentgiven.presentation;

import javax.servlet.http.HttpServletRequest;



import org.apache.struts.action.ActionMapping;

import ehr.treatmentdetail.vo.EHRSection_TreatmentVO;
import registration.controller.fb.CRNoFB;

public class EHRSection_TreatmentGivenFB extends CRNoFB
{
	private String hmode;
	private String patCrNo;
	private String episodeCode;
	private String episodeVisitNo;
	private String admissionNo;

	private String entryDate;
	private String treatmentRecordStatus[];
	
	private String[] rxContinue;
	private String[] revoke;
	private String index;
	
	
	private String[] rxContinueFlag;
	private String[] todayVisitFlag;
	private String[] serealNo; 
	private String[] prevEntryDate;
	private String[] prevDoseQty;
	private String[] prevIssueQty;
	private String[] prevRequiredQty;
	private String[] prevEndDate;
	private String[] prevStartDate;
	private String[] prevDoseId;
	private String[] prevDoseName;
	private String[] prevFreqId;
	private String[] prevQuantity;
	private String[] prevDays;
	private String[] prevRouteId;
	private String[] prevDrugAdminName;
	private String[] prevDrugAdminId;
	private String[] prevRouteName;
	private String[] prevFrequencyName;


	
	private String drugDetailRows;
	private String[] selDrugId;
	private String[] selDrugBrandId;
	private String[] selDrugItemTypeId;
	private String[] selDrugName;
	private String[] selDoseId;
	private String[] selDoseName;
	private String[] selFrequencyId;
	private String[] selFrequencyName;
	private String[] selDays;
	private String[] selStartDay;
	private String[] selInstructions;
	private String[] selIsEmptyStomach;
	private String[] selDrugAdminId;
	private String[] selDrugAdminName;
	private String emptyStomachIndexArray;
	private String isPregnantFlag;
	
	//private String[] requirmentTimeHrs;
	//private String[] requirmentTimeMin;
	
	
	private String[] selPrevFreq;
	
	private String[] quantity;
	private String sysHours;
	private String sysMinut;
	private String indexDelete;
	
	private String hiddenPatDeadStatus;
	
	private String summary;
	
	private String[] selEndDate;
	private String sysDate;
	
	private String[] prevDrugId;
	private String[] prevDrugBrandId;
	
	private String prescriptionDate;
	
	private String[] changeStartDate; 
	
	private String empName;
	private int currentPage;
	
	private String sysDateForValidation;
	
	private String drugInstructionId;
	private String selFinalInstructions;
	
	private String drugListId;
	private String activatePopUpTab;
	
	// Added By Pawan Kumar B N
	private String[] revokeRemarks;
	
	private String drugAdminId;
	private String drugAdminName;
	private String[] selPatPregCode;
	private String drugAdminList;

	private String[] cutOffTimeBefore;
	private String[] cutOffTimeAfter;
	private String firstDoseTime;
	private String doseDuration;
	private String isSetTREATMENT;
	private String revokeHidden;
	
	private String adviceType;
	private String [] selStock; 
	
	
	private String selectedRow;
	
	//Added by Vasu on 23.Jan.2018
	private String[] treatmentType;
	private String[] snomedCTTreatmentName;
	private String[] snomedCTTreatmentCode;
	private String[] treatmentDate;
	private String[] snomedPTRemarks;
	private String[] snomedCIdRemarks;
	private String[] treatmentGivenRecordStatus;
	//End Vasu
	public String getActivatePopUpTab() {
		return activatePopUpTab;
	}


	public void setActivatePopUpTab(String activatePopUpTab) {
		this.activatePopUpTab = activatePopUpTab;
	}




	public String getDrugListId() {
		return drugListId;
	}




	public void setDrugListId(String drugListId) {
		this.drugListId = drugListId;
	}




	public String getSelFinalInstructions() {
		return selFinalInstructions;
	}




	public void setSelFinalInstructions(String selFinalInstructions) {
		this.selFinalInstructions = selFinalInstructions;
	}




	public String getDrugInstructionId() {
		return drugInstructionId;
	}




	public void setDrugInstructionId(String drugInstructionId) {
		this.drugInstructionId = drugInstructionId;
	}




	public String getSysDateForValidation() {
		return sysDateForValidation;
	}




	public void setSysDateForValidation(String sysDateForValidation) {
		this.sysDateForValidation = sysDateForValidation;
	}




	public int getCurrentPage() {
		return currentPage;
	}




	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}




	public String getEmpName() {
		return empName;
	}




	public void setEmpName(String empName) {
		this.empName = empName;
	}




	public String[] getChangeStartDate() {
		return changeStartDate;
	}




	public void setChangeStartDate(String[] changeStartDate) {
		this.changeStartDate = changeStartDate;
	}




	public String getPrescriptionDate() {
		return prescriptionDate;
	}




	public void setPrescriptionDate(String prescriptionDate) {
		this.prescriptionDate = prescriptionDate;
	}




	public String[] getPrevDrugId() {
		return prevDrugId;
	}




	public void setPrevDrugId(String[] prevDrugId) {
		this.prevDrugId = prevDrugId;
	}




	public String getSysDate() {
		return sysDate;
	}




	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}




	public String[] getSelEndDate() {
		return selEndDate;
	}




	public void setSelEndDate(String[] selEndDate) {
		this.selEndDate = selEndDate;
	}




	public String getSummary() {
		return summary;
	}




	public void setSummary(String summary) {
		this.summary = summary;
	}




	



	public String[] getQuantity() {
		return quantity;
	}




	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.revoke=null;
		this.restDays="";
		this.restReason="";
		this.activeTab="";
		this.summary="";
		this.revokeRemarks = null;
		this.selDrugAdminId=null;
		
	}
	

	



	public EHRSection_TreatmentVO resetVO(EHRSection_TreatmentVO _vo)
	{
		_vo.setDrugId("0"+" # "+"0");
		//_vo.setDrugId(" # ");
		_vo.setDrugName("");
		_vo.setDoseId("0"+"^"+"0"+"^"+"0");
		_vo.setDoseName("");
		_vo.setFrequencyId("-1");
		_vo.setDays("");
		_vo.setStartDate("0");
		_vo.setRemarks("");
		_vo.setRequirmentTimeHrs("");
		_vo.setRequirmentTimeMin("");
		_vo.setIsEmptyStomach("0");
		_vo.setTodayVisitFlag("");
		_vo.setRxContinueFlag("");
		_vo.setSerialNo("");
		_vo.setEntryDate("");
		_vo.setDoseQty("");
		_vo.setIssueQty("");
		_vo.setRequiredQty("");
		_vo.setEndDate("");
	//	_vo.setDoseId("");
		_vo.setFrequencyId("");
		_vo.setChangeStartDate("");
		_vo.setQuantity("");
		_vo.setDrugAdminId("");
		_vo.setDrugAdminName("");
		return _vo;
	}
	
	
	
	private String searchDrugName;
	private String searchDrugItemTypeId;
	private String selectedDrugIndex;
	private String selectedSearchIndex;

	
	
	private String icdCode[];
	private String dignosisName[];
	private String hospitalDiagnosisCode[];
	private String hospitalDiagnosisName[];
	private String diagonisticTypeCode[];
	private String remarks[];
	private String departmentUnitCode;
	private String departmentCode;
	private String selectedCode;
	private String numberOfRow = "1";
	private String repeat;
	private String diagnosisCodeType;
	private String unitDiagnosisCodeType;
	private String comboOptionString;
	private String selectedDiagnosis[];
	private String addDiagnosis[];
	private String repeatedDiagnosisLength;

	private String addDiagnosisCode[];
	private String addDiagnosticTypeCode[];
	private String addDiagnosisRemarks[];
	private String selectedDiagCode;
	private String[] endDate;
	
	
	private String drugDeatilProcess;
	private String restAdviceProcess;
	private String[] prevDrugName;
	private String[] previousDoseId;
	private String[] previousFrequencyId;
	
	private String restReason;
	private String restDays;
	private String restStartDate;
	private String restRemark;
	private String deskType;
	
	private String activeTab;
	private String[] selStartDate;
	
	private String[] drugRequirmentTimeHrs;
	private String[] drugRequirmentTimeMin;
	private String frequencyImageIndex;
	private String isEmptyStomach;
	private String popupFreqId;
	private String[] drugFreqShift;
	private String popupItemTypeId;
	private String popupIsEmptyStomach;
	private String popupDoseId;
	private String[] popupDoseIdArray;
	private String[] popupIsEmptyStomachArray;
	private String popupRowIndex;
	private String popupFreqCount;
	private String popupDrugId;
	private String popupCheckIndex;
	private String popupDoseName;
	private String[] popupDoseNameArray;
	private String cutOffTimeArray;
	private String firstDoseTimeArray;
	private String doseDurationArray;
	
	private String scheduleIndex;
	
	private String[] selDrugRouteId;
	private String[] selDrugRouteName;
	private String[] prevSelInstructionArray; 
	private String popupInstruction;
	
	private String[] activityExtId;
	private String[] otherInstructionExtId;
	private String[] activityExt;
	private String[] otherInstruction; 
	
	private String opdIpdFlag;
	private String drugInstructionIndex;

	public String getDrugInstructionIndex() {
		return drugInstructionIndex;
	}




	public void setDrugInstructionIndex(String drugInstructionIndex) {
		this.drugInstructionIndex = drugInstructionIndex;
	}




	public String getOpdIpdFlag() {
		return opdIpdFlag;
	}




	public void setOpdIpdFlag(String opdIpdFlag) {
		this.opdIpdFlag = opdIpdFlag;
	}




	public String[] getActivityExt() {
		return activityExt;
	}




	public void setActivityExt(String[] activityExt) {
		this.activityExt = activityExt;
	}




	public String[] getOtherInstruction() {
		return otherInstruction;
	}




	public void setOtherInstruction(String[] otherInstruction) {
		this.otherInstruction = otherInstruction;
	}




	public String[] getActivityExtId() {
		return activityExtId;
	}




	public void setActivityExtId(String[] activityExtId) {
		this.activityExtId = activityExtId;
	}




	public String[] getOtherInstructionExtId() {
		return otherInstructionExtId;
	}




	public void setOtherInstructionExtId(String[] otherInstructionExtId) {
		this.otherInstructionExtId = otherInstructionExtId;
	}




	public String getPopupInstruction() {
		return popupInstruction;
	}

	public void setPopupInstruction(String popupInstruction) {
		this.popupInstruction = popupInstruction;
	}

	public String[] getPrevSelInstructionArray() {
		return prevSelInstructionArray;
	}

	public void setPrevSelInstructionArray(String[] prevSelInstructionArray) {
		this.prevSelInstructionArray = prevSelInstructionArray;
	}

	public String[] getSelDrugRouteName() {
		return selDrugRouteName;
	}

	public void setSelDrugRouteName(String[] selDrugRouteName) {
		this.selDrugRouteName = selDrugRouteName;
	}

	public String getScheduleIndex() {
		return scheduleIndex;
	}

	public void setScheduleIndex(String scheduleIndex) {
		this.scheduleIndex = scheduleIndex;
	}

	public String getPopupDoseName() {
		return popupDoseName;
	}

	public void setPopupDoseName(String popupDoseName) {
		this.popupDoseName = popupDoseName;
	}

	public String getPopupCheckIndex() {
		return popupCheckIndex;
	}

	public void setPopupCheckIndex(String popupCheckIndex) {
		this.popupCheckIndex = popupCheckIndex;
	}

	public String getPopupDrugId() {
		return popupDrugId;
	}

	public void setPopupDrugId(String popupDrugId) {
		this.popupDrugId = popupDrugId;
	}

	public String getPopupFreqCount() {
		return popupFreqCount;
	}

	public void setPopupFreqCount(String popupFreqCount) {
		this.popupFreqCount = popupFreqCount;
	}

	public String getPopupRowIndex() {
		return popupRowIndex;
	}

	public void setPopupRowIndex(String popupRowIndex) {
		this.popupRowIndex = popupRowIndex;
	}

	public String getPopupItemTypeId() {
		return popupItemTypeId;
	}

	public void setPopupItemTypeId(String popupItemTypeId) {
		this.popupItemTypeId = popupItemTypeId;
	}

	

	public String getPopupFreqId() {
		return popupFreqId;
	}

	public void setPopupFreqId(String popupFreqId) {
		this.popupFreqId = popupFreqId;
	}

	public String getIsEmptyStomach() {
		return isEmptyStomach;
	}

	public void setIsEmptyStomach(String isEmptyStomach) {
		this.isEmptyStomach = isEmptyStomach;
	}

	public String getFrequencyImageIndex() {
		return frequencyImageIndex;
	}

	public void setFrequencyImageIndex(String frequencyImageIndex) {
		this.frequencyImageIndex = frequencyImageIndex;
	}


	

	public String[] getDrugRequirmentTimeHrs() {
		return drugRequirmentTimeHrs;
	}

	public void setDrugRequirmentTimeHrs(String[] drugRequirmentTimeHrs) {
		this.drugRequirmentTimeHrs = drugRequirmentTimeHrs;
	}

	public String[] getDrugRequirmentTimeMin() {
		return drugRequirmentTimeMin;
	}

	public void setDrugRequirmentTimeMin(String[] drugRequirmentTimeMin) {
		this.drugRequirmentTimeMin = drugRequirmentTimeMin;
	}

	public String[] getDrugFreqShift() {
		return drugFreqShift;
	}

	public void setDrugFreqShift(String[] drugFreqShift) {
		this.drugFreqShift = drugFreqShift;
	}

	
	public String[] getSelStartDate() {
		return selStartDate;
	}

	public void setSelStartDate(String[] selStartDate) {
		this.selStartDate = selStartDate;
	}

	/*public EHRSection_TreatmentGivenFB {
		this.activeTab = "tabDrugAdvice";
		this.currentPage=1;
	}*/

	public String getActiveTab() {
		return activeTab;
	}

	public void setActiveTab(String activeTab) {
		this.activeTab = activeTab;
	}

	public String getRestReason() {
		return restReason;
	}

	public void setRestReason(String restReason) {
		this.restReason = restReason;
	}

	public String getRestDays() {
		return restDays;
	}

	public void setRestDays(String restDays) {
		this.restDays = restDays;
	}

	public String getRestStartDate() {
		return restStartDate;
	}

	public void setRestStartDate(String restStartDate) {
		this.restStartDate = restStartDate;
	}

	public String getRestRemark() {
		return restRemark;
	}

	public void setRestRemark(String restRemark) {
		this.restRemark = restRemark;
	}

	public String[] getEndDate() {
		return endDate;
	}

	public void setEndDate(String[] endDate) {
		this.endDate = endDate;
	}

	public String getSelectedDiagCode()
	{
		return selectedDiagCode;
	}

	public void setSelectedDiagCode(String selectedDiagCode)
	{
		this.selectedDiagCode = selectedDiagCode;
	}

	public String getRepeatedDiagnosisLength()
	{
		return repeatedDiagnosisLength;
	}

	public void setRepeatedDiagnosisLength(String repeatedDiagnosisLength)
	{
		this.repeatedDiagnosisLength = repeatedDiagnosisLength;
	}

	public String getComboOptionString()
	{
		return comboOptionString;
	}

	public String[] getSelectedDiagnosis()
	{
		return selectedDiagnosis;
	}

	public void setComboOptionString(String comboOptionString)
	{
		this.comboOptionString = comboOptionString;
	}

	public String getDiagnosisCodeType()
	{
		return diagnosisCodeType;
	}

	public void setDiagnosisCodeType(String diagnosisCodeType)
	{
		this.diagnosisCodeType = diagnosisCodeType;
	}

	public String getRepeat()
	{
		return repeat;
	}

	public void setRepeat(String repeat)
	{
		this.repeat = repeat;
	}

	public String getNumberOfRow()
	{
		return numberOfRow;
	}

	public void setNumberOfRow(String numberOfRow)
	{
		this.numberOfRow = numberOfRow;
	}

	public String getSearchDrugItemTypeId()
	{
		return searchDrugItemTypeId;
	}

	public void setSearchDrugItemTypeId(String searchDrugItemTypeId)
	{
		this.searchDrugItemTypeId = searchDrugItemTypeId;
	}

	public String getSelectedCode()
	{
		return selectedCode;
	}

	public void setSelectedCode(String selectedCode)
	{
		this.selectedCode = selectedCode;
	}

	public String getDepartmentCode()
	{
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode)
	{
		this.departmentCode = departmentCode;
	}

	public String getDepartmentUnitCode()
	{
		return departmentUnitCode;
	}

	public void setDepartmentUnitCode(String departmentUnitCode)
	{
		this.departmentUnitCode = departmentUnitCode;
	}

	public String getEpisodeCode()
	{
		return episodeCode;
	}

	public void setEpisodeCode(String episodeCode)
	{
		this.episodeCode = episodeCode;
	}

	public String getHmode()
	{
		return hmode;
	}

	public void setHmode(String hmode)
	{
		this.hmode = hmode;
	}

	public String getPatCrNo()
	{
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo)
	{
		this.patCrNo = patCrNo;
	}

	public String[] getDiagonisticTypeCode()
	{
		return diagonisticTypeCode;
	}

	public void setDiagonisticTypeCode(String[] diagonisticTypeCode)
	{
		this.diagonisticTypeCode = diagonisticTypeCode;
	}

	public String[] getDignosisName()
	{
		return dignosisName;
	}

	public void setDignosisName(String[] dignosisName)
	{
		this.dignosisName = dignosisName;
	}

	public String[] getHospitalDiagnosisCode()
	{
		return hospitalDiagnosisCode;
	}

	public void setHospitalDiagnosisCode(String[] hospitalDiagnosisCode)
	{
		this.hospitalDiagnosisCode = hospitalDiagnosisCode;
	}

	public String[] getHospitalDiagnosisName()
	{
		return hospitalDiagnosisName;
	}

	public void setHospitalDiagnosisName(String[] hospitalDiagnosisName)
	{
		this.hospitalDiagnosisName = hospitalDiagnosisName;
	}

	public String[] getRemarks()
	{
		return remarks;
	}

	public void setRemarks(String[] remarks)
	{
		this.remarks = remarks;
	}

	public String[] getIcdCode()
	{
		return icdCode;
	}

	public void setIcdCode(String[] icdCode)
	{
		this.icdCode = icdCode;
	}

	public String getUnitDiagnosisCodeType()
	{
		return unitDiagnosisCodeType;
	}

	public void setUnitDiagnosisCodeType(String unitDiagnosisCodeType)
	{
		this.unitDiagnosisCodeType = unitDiagnosisCodeType;
	}

	public void setSelectedDiagnosis(String[] selectedDiagnosis)
	{
		this.selectedDiagnosis = selectedDiagnosis;
	}

	public String[] getAddDiagnosis()
	{
		return addDiagnosis;
	}

	public void setAddDiagnosis(String[] addDiagnosis)
	{
		this.addDiagnosis = addDiagnosis;
	}

	public String getEpisodeVisitNo()
	{
		return episodeVisitNo;
	}

	public void setEpisodeVisitNo(String episodeVisitNo)
	{
		this.episodeVisitNo = episodeVisitNo;
	}

	public String[] getAddDiagnosisCode()
	{
		return addDiagnosisCode;
	}

	public void setAddDiagnosisCode(String[] addDiagnosisCode)
	{
		this.addDiagnosisCode = addDiagnosisCode;
	}

	public String[] getAddDiagnosticTypeCode()
	{
		return addDiagnosticTypeCode;
	}

	public void setAddDiagnosticTypeCode(String[] addDiagnosticTypeCode)
	{
		this.addDiagnosticTypeCode = addDiagnosticTypeCode;
	}

	public String[] getAddDiagnosisRemarks()
	{
		return addDiagnosisRemarks;
	}

	public void setAddDiagnosisRemarks(String[] addDiagnosisRemarks)
	{
		this.addDiagnosisRemarks = addDiagnosisRemarks;
	}

	public String getDrugDetailRows()
	{
		return drugDetailRows;
	}

	public void setDrugDetailRows(String drugDetailRows)
	{
		this.drugDetailRows = drugDetailRows;
	}

	public String[] getSelDrugId()
	{
		return selDrugId;
	}

	public void setSelDrugId(String[] selDrugId)
	{
		this.selDrugId = selDrugId;
	}

	public String[] getSelDrugName()
	{
		return selDrugName;
	}

	public void setSelDrugName(String[] selDrugName)
	{
		this.selDrugName = selDrugName;
	}

	public String[] getSelDoseId()
	{
		return selDoseId;
	}

	public void setSelDoseId(String[] selDoseId)
	{
		this.selDoseId = selDoseId;
	}

	public String[] getSelFrequencyId()
	{
		return selFrequencyId;
	}

	public void setSelFrequencyId(String[] selFrequencyId)
	{
		this.selFrequencyId = selFrequencyId;
	}

	public String[] getSelDays()
	{
		return selDays;
	}

	public void setSelDays(String[] selDays)
	{
		this.selDays = selDays;
	}

	public String[] getSelStartDay()
	{
		return selStartDay;
	}

	public void setSelStartDay(String[] selStartDay)
	{
		this.selStartDay = selStartDay;
	}

	public String[] getSelInstructions()
	{
		return selInstructions;
	}

	public void setSelInstructions(String[] selInstructions)
	{
		this.selInstructions = selInstructions;
	}

	public String[] getSelDrugItemTypeId()
	{
		return selDrugItemTypeId;
	}

	public void setSelDrugItemTypeId(String[] selDrugItemTypeId)
	{
		this.selDrugItemTypeId = selDrugItemTypeId;
	}

	public String[] getSelDoseName()
	{
		return selDoseName;
	}

	public void setSelDoseName(String[] selDoseName)
	{
		this.selDoseName = selDoseName;
	}

	public String getSearchDrugName()
	{
		return searchDrugName;
	}

	public void setSearchDrugName(String searchDrugName)
	{
		this.searchDrugName = searchDrugName;
	}

	public String getSelectedDrugIndex()
	{
		return selectedDrugIndex;
	}

	public void setSelectedDrugIndex(String selectedDrugIndex)
	{
		this.selectedDrugIndex = selectedDrugIndex;
	}

	public String getAdmissionNo()
	{
		return admissionNo;
	}

	public void setAdmissionNo(String admissionNo)
	{
		this.admissionNo = admissionNo;
	}

	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	public String[] getSelPrevFreq() {
		return selPrevFreq;
	}

	public void setSelPrevFreq(String[] selPrevFreq) {
		this.selPrevFreq = selPrevFreq;
	}

	

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String[] getRxContinue() {
		return rxContinue;
	}

	public void setRxContinue(String[] rxContinue) {
		this.rxContinue = rxContinue;
	}

	public String[] getRevoke() {
		return revoke;
	}

	public void setRevoke(String[] revoke) {
		this.revoke = revoke;
	}

	public String[] getRxContinueFlag() {
		return rxContinueFlag;
	}

	public void setRxContinueFlag(String[] rxContinueFlag) {
		this.rxContinueFlag = rxContinueFlag;
	}

	public String getDrugDeatilProcess() {
		return drugDeatilProcess;
	}

	public void setDrugDeatilProcess(String drugDeatilProcess) {
		this.drugDeatilProcess = drugDeatilProcess;
	}

	public String getRestAdviceProcess() {
		return restAdviceProcess;
	}

	public void setRestAdviceProcess(String restAdviceProcess) {
		this.restAdviceProcess = restAdviceProcess;
	}

	public String[] getPrevDrugName() {
		return prevDrugName;
	}

	public void setPrevDrugName(String[] prevDrugName) {
		this.prevDrugName = prevDrugName;
	}

	

	
	public String getDeskType() {
		return deskType;
	}

	public void setDeskType(String deskType) {
		this.deskType = deskType;
	}
	/*
	public String[] getRequirmentTimeHrs() {
		return requirmentTimeHrs;
	}

	public void setRequirmentTimeHrs(String[] requirmentTimeHrs) {
		this.requirmentTimeHrs = requirmentTimeHrs;
	}

	public String[] getRequirmentTimeMin() {
		return requirmentTimeMin;
	}

	public void setRequirmentTimeMin(String[] requirmentTimeMin) {
		this.requirmentTimeMin = requirmentTimeMin;
	}
	*/

	public String[] getSelIsEmptyStomach() {
		return selIsEmptyStomach;
	}

	public void setSelIsEmptyStomach(String[] selIsEmptyStomach) {
		this.selIsEmptyStomach = selIsEmptyStomach;
	}

	

	public String getPopupIsEmptyStomach() {
		return popupIsEmptyStomach;
	}

	public void setPopupIsEmptyStomach(String popupIsEmptyStomach) {
		this.popupIsEmptyStomach = popupIsEmptyStomach;
	}

	public String getPopupDoseId() {
		return popupDoseId;
	}

	public void setPopupDoseId(String popupDoseId) {
		this.popupDoseId = popupDoseId;
	}

	public String[] getPopupDoseIdArray() {
		return popupDoseIdArray;
	}

	public void setPopupDoseIdArray(String[] popupDoseIdArray) {
		this.popupDoseIdArray = popupDoseIdArray;
	}

	public String[] getPopupIsEmptyStomachArray() {
		return popupIsEmptyStomachArray;
	}

	public void setPopupIsEmptyStomachArray(String[] popupIsEmptyStomachArray) {
		this.popupIsEmptyStomachArray = popupIsEmptyStomachArray;
	}

	public String getEmptyStomachIndexArray() {
		return emptyStomachIndexArray;
	}

	public void setEmptyStomachIndexArray(String emptyStomachIndexArray) {
		this.emptyStomachIndexArray = emptyStomachIndexArray;
	}

	public String[] getPopupDoseNameArray() {
		return popupDoseNameArray;
	}

	public void setPopupDoseNameArray(String[] popupDoseNameArray) {
		this.popupDoseNameArray = popupDoseNameArray;
	}

	public String[] getSelDrugRouteId() {
		return selDrugRouteId;
	}

	public void setSelDrugRouteId(String[] selDrugRouteId) {
		this.selDrugRouteId = selDrugRouteId;
	}


	public String[] getTodayVisitFlag() {
		return todayVisitFlag;
	}




	public void setTodayVisitFlag(String[] todayVisitFlag) {
		this.todayVisitFlag = todayVisitFlag;
	}




	public String[] getSerealNo() {
		return serealNo;
	}




	public void setSerealNo(String[] serealNo) {
		this.serealNo = serealNo;
	}




	public String[] getPrevEntryDate() {
		return prevEntryDate;
	}




	public void setPrevEntryDate(String[] prevEntryDate) {
		this.prevEntryDate = prevEntryDate;
	}




	public String[] getPrevDoseQty() {
		return prevDoseQty;
	}




	public void setPrevDoseQty(String[] prevDoseQty) {
		this.prevDoseQty = prevDoseQty;
	}




	public String[] getPrevIssueQty() {
		return prevIssueQty;
	}




	public void setPrevIssueQty(String[] prevIssueQty) {
		this.prevIssueQty = prevIssueQty;
	}




	public String[] getPrevRequiredQty() {
		return prevRequiredQty;
	}




	public void setPrevRequiredQty(String[] prevRequiredQty) {
		this.prevRequiredQty = prevRequiredQty;
	}




	public String[] getPrevEndDate() {
		return prevEndDate;
	}




	public void setPrevEndDate(String[] prevEndDate) {
		this.prevEndDate = prevEndDate;
	}




	public String[] getPrevStartDate() {
		return prevStartDate;
	}




	public void setPrevStartDate(String[] prevStartDate) {
		this.prevStartDate = prevStartDate;
	}




	public String[] getPrevDoseId() {
		return prevDoseId;
	}




	public void setPrevDoseId(String[] prevDoseId) {
		this.prevDoseId = prevDoseId;
	}




	public String[] getPrevFreqId() {
		return prevFreqId;
	}




	public void setPrevFreqId(String[] prevFreqId) {
		this.prevFreqId = prevFreqId;
	}


	public void setQuantity(String[] quantity) {
		this.quantity = quantity;
	}




	public String getSelectedSearchIndex() {
		return selectedSearchIndex;
	}




	public void setSelectedSearchIndex(String selectedSearchIndex) {
		this.selectedSearchIndex = selectedSearchIndex;
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




	



	public String getIndexDelete() {
		return indexDelete;
	}




	public void setIndexDelete(String indexDelete) {
		this.indexDelete = indexDelete;
	}




	public String getHiddenPatDeadStatus() {
		return hiddenPatDeadStatus;
	}




	public void setHiddenPatDeadStatus(String hiddenPatDeadStatus) {
		this.hiddenPatDeadStatus = hiddenPatDeadStatus;
	}




	public String[] getPreviousDoseId() {
		return previousDoseId;
	}




	public void setPreviousDoseId(String[] previousDoseId) {
		this.previousDoseId = previousDoseId;
	}




	public String[] getPreviousFrequencyId() {
		return previousFrequencyId;
	}




	public void setPreviousFrequencyId(String[] previousFrequencyId) {
		this.previousFrequencyId = previousFrequencyId;
	}


	public String[] getRevokeRemarks() {
		return revokeRemarks;
	}


	public void setRevokeRemarks(String[] revokeRemarks) {
		this.revokeRemarks = revokeRemarks;
	}


	public String getDrugAdminId() {
		return drugAdminId;
	}


	public void setDrugAdminId(String drugAdminId) {
		this.drugAdminId = drugAdminId;
	}


	public String getDrugAdminName() {
		return drugAdminName;
	}


	public void setDrugAdminName(String drugAdminName) {
		this.drugAdminName = drugAdminName;
	}


	public String[] getSelDrugAdminId() {
		return selDrugAdminId;
	}


	public void setSelDrugAdminId(String[] selDrugAdminId) {
		this.selDrugAdminId = selDrugAdminId;
	}


	public String getIsPregnantFlag() {
		return isPregnantFlag;
	}


	public void setIsPregnantFlag(String isPregnantFlag) {
		this.isPregnantFlag = isPregnantFlag;
	}


	public String[] getSelPatPregCode() {
		return selPatPregCode;
	}


	public void setSelPatPregCode(String[] selPatPregCode) {
		this.selPatPregCode = selPatPregCode;
	}


	public String[] getSelDrugAdminName() {
		return selDrugAdminName;
	}


	public void setSelDrugAdminName(String[] selDrugAdminName) {
		this.selDrugAdminName = selDrugAdminName;
	}


	public String getDrugAdminList() {
		return drugAdminList;
	}


	public void setDrugAdminList(String drugAdminList) {
		this.drugAdminList = drugAdminList;
	}


	public String[] getSelDrugBrandId() {
		return selDrugBrandId;
	}


	public void setSelDrugBrandId(String[] selDrugBrandId) {
		this.selDrugBrandId = selDrugBrandId;
	}


	public String[] getPrevDrugBrandId() {
		return prevDrugBrandId;
	}


	public void setPrevDrugBrandId(String[] prevDrugBrandId) {
		this.prevDrugBrandId = prevDrugBrandId;
	}


	

	


	public String getCutOffTimeArray() {
		return cutOffTimeArray;
	}


	public void setCutOffTimeArray(String cutOffTimeArray) {
		this.cutOffTimeArray = cutOffTimeArray;
	}


	public String getFirstDoseTimeArray() {
		return firstDoseTimeArray;
	}


	public void setFirstDoseTimeArray(String firstDoseTimeArray) {
		this.firstDoseTimeArray = firstDoseTimeArray;
	}


	public String getDoseDurationArray() {
		return doseDurationArray;
	}


	public void setDoseDurationArray(String doseDurationArray) {
		this.doseDurationArray = doseDurationArray;
	}



	public String getFirstDoseTime() {
		return firstDoseTime;
	}


	public void setFirstDoseTime(String firstDoseTime) {
		this.firstDoseTime = firstDoseTime;
	}


	public String getDoseDuration() {
		return doseDuration;
	}


	public void setDoseDuration(String doseDuration) {
		this.doseDuration = doseDuration;
	}


	public String[] getCutOffTimeBefore() {
		return cutOffTimeBefore;
	}


	public void setCutOffTimeBefore(String[] cutOffTimeBefore) {
		this.cutOffTimeBefore = cutOffTimeBefore;
	}


	public String[] getCutOffTimeAfter() {
		return cutOffTimeAfter;
	}


	public void setCutOffTimeAfter(String[] cutOffTimeAfter) {
		this.cutOffTimeAfter = cutOffTimeAfter;
	}


	public String[] getTreatmentRecordStatus() {
		return treatmentRecordStatus;
	}


	public void setTreatmentRecordStatus(String treatmentRecordStatus[]) {
		this.treatmentRecordStatus = treatmentRecordStatus;
	}


	public String[] getPrevQuantity() {
		return prevQuantity;
	}


	public void setPrevQuantity(String[] prevQuantity) {
		this.prevQuantity = prevQuantity;
	}


	public String[] getPrevDoseName() {
		return prevDoseName;
	}


	public void setPrevDoseName(String[] prevDoseName) {
		this.prevDoseName = prevDoseName;
	}


	public String[] getPrevRouteId() {
		return prevRouteId;
	}


	public void setPrevRouteId(String[] prevRouteId) {
		this.prevRouteId = prevRouteId;
	}


	public String[] getPrevDays() {
		return prevDays;
	}


	public void setPrevDays(String[] prevDays) {
		this.prevDays = prevDays;
	}


	public String[] getPrevDrugAdminName() {
		return prevDrugAdminName;
	}


	public void setPrevDrugAdminName(String[] prevDrugAdminName) {
		this.prevDrugAdminName = prevDrugAdminName;
	}


	public String[] getPrevDrugAdminId() {
		return prevDrugAdminId;
	}


	public void setPrevDrugAdminId(String[] prevDrugAdminId) {
		this.prevDrugAdminId = prevDrugAdminId;
	}


	public String[] getPrevRouteName() {
		return prevRouteName;
	}


	public void setPrevRouteName(String[] prevRouteName) {
		this.prevRouteName = prevRouteName;
	}


	public String getIsSetTREATMENT() {
		return isSetTREATMENT;
	}


	public void setIsSetTREATMENT(String isSetTREATMENT) {
		this.isSetTREATMENT = isSetTREATMENT;
	}


	public String getRevokeHidden() {
		return revokeHidden;
	}


	public void setRevokeHidden(String revokeHidden) {
		this.revokeHidden = revokeHidden;
	}


	public String[] getPrevFrequencyName() {
		return prevFrequencyName;
	}


	public void setPrevFrequencyName(String[] prevFrequencyName) {
		this.prevFrequencyName = prevFrequencyName;
	}


	public String[] getSelFrequencyName() {
		return selFrequencyName;
	}


	public void setSelFrequencyName(String[] selFrequencyName) {
		this.selFrequencyName = selFrequencyName;
	}


	public String getSelectedRow() {
		return selectedRow;
	}


	public void setSelectedRow(String selectedRow) {
		this.selectedRow = selectedRow;
	}


	public String getAdviceType() {
		return adviceType;
	}


	public void setAdviceType(String adviceType) {
		this.adviceType = adviceType;
	}


	public String[] getSelStock() {
		return selStock;
	}


	public void setSelStock(String[] selStock) {
		this.selStock = selStock;
	}


	public String[] getTreatmentType() {
		return treatmentType;
	}


	public void setTreatmentType(String[] treatmentType) {
		this.treatmentType = treatmentType;
	}


	public String[] getSnomedCTTreatmentName() {
		return snomedCTTreatmentName;
	}


	public void setSnomedCTTreatmentName(String[] snomedCTTreatmentName) {
		this.snomedCTTreatmentName = snomedCTTreatmentName;
	}


	public String[] getSnomedCTTreatmentCode() {
		return snomedCTTreatmentCode;
	}


	public void setSnomedCTTreatmentCode(String[] snomedCTTreatmentCode) {
		this.snomedCTTreatmentCode = snomedCTTreatmentCode;
	}


	public String[] getTreatmentDate() {
		return treatmentDate;
	}


	public void setTreatmentDate(String[] treatmentDate) {
		this.treatmentDate = treatmentDate;
	}


	public String[] getSnomedPTRemarks() {
		return snomedPTRemarks;
	}


	public void setSnomedPTRemarks(String[] snomedPTRemarks) {
		this.snomedPTRemarks = snomedPTRemarks;
	}


	public String[] getSnomedCIdRemarks() {
		return snomedCIdRemarks;
	}


	public void setSnomedCIdRemarks(String[] snomedCIdRemarks) {
		this.snomedCIdRemarks = snomedCIdRemarks;
	}


	public String[] getTreatmentGivenRecordStatus() {
		return treatmentGivenRecordStatus;
	}


	public void setTreatmentGivenRecordStatus(String[] treatmentGivenRecordStatus) {
		this.treatmentGivenRecordStatus = treatmentGivenRecordStatus;
	}


	


	

	

}

