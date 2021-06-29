package opd.transaction.controller.fb;

import hisglobal.vo.PatDrugTreatmentDetailVO;
import hisglobal.vo.PatExtTreatmentDetailVO;
import opd.OpdConfig;

import org.apache.struts.action.ActionForm;

public class GenericPatientProfileFB extends ActionForm
{
	private String hmode;
	private String profileGenerationMode;
	private String patCrNo;
	private String episodeCode;
	private String episodeVisitNo;
	private String admissionNo;
	private String departmentUnitCode;
	private String deskType;
	private String deskId;
	private String patCategoryCode; 
	private String wardCode;
	
	private String profileId;
	private String serialNo;
	private String profileHeader;
	private String remarks;
	private String profileType;
	private String accessType;
	private String userLevel;

	private String summary;
	private String entryDate;

	private String[] selectedProfileId;
	private String[] selectedSerialNo;
	private String[] selectedIndex;
	private String selectedAccessType;
	private String selectedUserLevel;
	private String selectedUnitUserLevel;
	private String sysDateForValidation;
	private String accessPrivilSerialNo;

	private String prescriptionDate;
	private String[] unitList;
	private String[] selectedUnits;	

	private String[] usersList;
	private String[] selectedUsers;

	private String searchMode;
	private String searchString;
	private String searchUserName;
	private String searchEmpName;
	private String searchEmpId;
	private String[] selectedUserIndex;

	private String selectedMenu;
	private String selectedMenuId;
	private String selectedRow[];
	
	private String profileHTML;
	private String profileBound;
	private String profileGenerationType;
	private String selectedUserUnitSpecificType[];
	private String previousUnitUser;
	private String[] profileHeaderValue;
	private String[] fetchSelectedRow; 
	private String diagnosisCheckFlag;
	private String allergyCheckFlag;
	private String complaintsCheckFlag;
	private String complaintsDateWiseCheckFlag;
	private String investigationCheckFlag;
	private String operationCheckFlag;
	private String imageCheckFlag;
	private String treatmentCheckFlag;
	private String vitalChartCheckFlag;
	private String[] requisitionDNo;
	private String[] unitListLeft;
	private String deleteIndex;
	private String genericCheckFlag;
	private String reviewDate="";
	private String isDesclaimerRequired;
	private String disclaimerDesc1;
	private String disclaimerDesc2;
	private String disclaimerDesc3;
	private String userSeatId;
	//private String deskMenuId;
	private String deskMenuName;
	private String dischargeModifyFlag;
	private String extInvCheckFlag;
	private String progNotesCheckFlag;
	
	/////Advice On Discharge
	
	private String[] rxContinue;
	private String[] revoke;
	private String index;
	private String instActivityType;
	
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
	private String[] prevFreqId;
	
	private String drugDetailRows;
	private String[] selDrugId;
	private String[] selDrugItemTypeId;
	private String[] selDrugName;
	private String[] selDoseId;
	private String[] selDoseName;
	private String[] selFrequencyId;
	private String[] selDays;
	private String[] selStartDay;
	private String[] selInstructions;
	private String[] selIsEmptyStomach;
	private String[] selDoseQuantity;
	private String emptyStomachIndexArray;
	
	private String[] selPrevFreq;
	
	private String extDrugDetailRows;
	private String[] selExtDrugId;
	private String[] selExtDrugItemTypeId;
	private String[] selExtDrugName;
	private String[] selExtDoseId;
	private String[] selExtDoseName;
	private String[] selExtFrequencyId;
	private String[] selExtDays;
	private String[] selExtStartDay;
	private String[] selExtInstructions;
	private String[] selExtTreatmentId;
	private String[] selExtTreatmentName;
	private String[] extRxContinue;
	private String[] extRevoke;
	private String extIndex;
	private String[] prevSelExtFrequencyId;
	private String[] prevSelExtTreatmentName;
	private String[] prevExtTreatmentName;
	
	private String[] extension;
	private String[] extensionDays;
	
	private String revokeHidden;
	private String extensionHidden;
	private String extensionDaysHidden;
	private String[] quantity;
	private String searchDrugName;
	private String searchDrugItemTypeId;
	private String selectedDrugIndex;
	private String selectedDrug[];
	
	
	private String icdCode[];
	private String dignosisName[];
	private String hospitalDiagnosisCode[];
	private String hospitalDiagnosisName[];
	private String diagonisticTypeCode[];
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
	
	private String restReason;
	private String restDays;
	private String restStartDate;
	private String restRemark;
	private String restSerialNo;
	private String dietType;
	private String dietDays;
	private String dietStartDate;
	private String dietRemark;
	private String dietTypeId;
	private String dietSerialNo;
	private String[] prevDietTypeId;
	
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
	
	private String scheduleIndex;
	
	private String[] selDrugRouteId;
	private String[] selDrugRouteName;
	private String[] prevSelInstructionArray; 
	private String popupInstruction;
	
	private String[] activityExtId;
	private String[] otherInstructionExtId;
	private String[] activityExt;
	private String[] otherInstruction; 
	
	private String viewMode;
	private String[] selectedParas;
	private String[] selectedDates;
	//Complaints
	private String reportMode;
	private String recordDate;
	
	private String showProgNotes;
	private String hiddenProgNotes;

	// Discharge Profile Footer
	private String dischargeType;
	private String dischargeAdvisedBy;
	private String dischargeAdvisedDept;
	private String dischargeTypeName;
	private String dischargeAdvisedByName;
	private String dischargeAdvisedDeptName;
	
	//Calling Profile Value [ patientProfile =0, patientRefered=1, externalRefered=2 ]
	private String autoProfileCalledFrom;
	
	
	private String[] selDrugAdminId;
	private String[] selDrugAdminName;
	
	private String snomdPTRemarks;
	private String snomdCIdRemarks;
	
	
	
	//Vital Chart View
    private String epiAdmStartDate;
	private String fromDate;
	private String toDate;
	private String filterCriteria;
	private String chartId;
	private String generationType;
	private String chartHeader;
	private String isGraph;
	private String[] chartPara;
	private boolean haveDefault;
	private String targetPage;
	private String sortType;
	private String oldSortType;
	private String sortChartId;
	private String graphChartId;
	private String graphChartName;
	private String chartHtml;
	// For Pagination
	private int currentPage;
	private int maxRowsCount;
	
    // End Vital  Chart vIew
	
	//Added by VASU on 06.11.2017
	
	private String reqDnoList;
	private String strHiddenPatDtl;
	private String reqDNo;
	private String[] resultStatus;
	private String isSinglePageFlag; //Added by Vasu on 18.Jan.2019
	private String isCallFromEMR;
	
	
	public PatDrugTreatmentDetailVO resetVO(PatDrugTreatmentDetailVO _vo)
	{
		_vo.setDrugId("0"+" # "+"0");
		//_vo.setDrugId(" # ");
		_vo.setDrugName("");
		_vo.setDoseId("0"+"^"+"0"+"^"+"0");
		_vo.setDoseName("");
		_vo.setFrequencyId("-1");
		_vo.setDays("");
		_vo.setStartDay("0");
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
		return _vo;
	}
	
	public PatExtTreatmentDetailVO resetExtTreatmentVO(PatExtTreatmentDetailVO _vo)
	{
		_vo.setDoseId("0");
		_vo.setDoseName("");
		_vo.setFrequencyId("-1");
		_vo.setDays("");
		_vo.setStartDay("0");
		_vo.setRemarks("");
		_vo.setExtTreatmentId("-1");
		_vo.setExtTreatmentName("");
		return _vo;
	}
	
	public String getPreviousUnitUser() {
		return previousUnitUser;
	}

	public void setPreviousUnitUser(String previousUnitUser) {
		this.previousUnitUser = previousUnitUser;
	}

	public String getProfileGenerationType() {
		return profileGenerationType;
	}

	public void setProfileGenerationType(String profileGenerationType) {
		this.profileGenerationType = profileGenerationType;
	}

	public String getProfileBound() {
		return profileBound;
	}

	public void setProfileBound(String profileBound) {
		this.profileBound = profileBound;
	}

	public GenericPatientProfileFB()
	{
		this.profileId="";
		this.serialNo="";
		this.profileHeader="";
		this.profileGenerationType="";
		this.remarks="";
		this.profileType=OpdConfig.PATIENT_PROFILE_TYPE_FORWORD;
	//	this.accessType=OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_ALL;
		this.setAccessType("");
		// this.userLevel="-1";
		this.setUserLevel("");

		this.entryDate="";

		this.selectedProfileId= new String[0];
		this.selectedSerialNo= new String[0];
		this.selectedIndex= new String[0];
		
		this.selectedUserLevel="";
		
		this.unitList= new String[0];
		this.selectedUnits = new String[0];
		this.usersList = new String[0];
		this.selectedUsers = new String[0];
		
		this.selectedUserIndex = new String[0];

		this.selectedMenu="";
		this.selectedMenuId="";
		this.selectedRow=new String[0];
	}
	
	public void reset()
	{
		this.profileId="";
		this.serialNo="";
		this.profileHeader="";
		this.profileGenerationType="";
		this.remarks="";
		this.profileType="";
		this.accessType=OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_ALL;
		this.userLevel="-1";
		this.dischargeModifyFlag="";
		this.entryDate="";

		this.selectedProfileId= new String[0];
		this.selectedSerialNo= new String[0];
		this.selectedIndex= new String[0];
		this.setSelectedAccessType("");
		this.selectedUserLevel="";

		this.unitList= new String[0];
		this.selectedUnits = new String[0];
		this.usersList = new String[0];
		this.selectedUsers = new String[0];

		this.selectedUserIndex = new String[0];

		this.selectedMenu="";
		this.selectedMenuId="";
		this.selectedRow=new String[0];
		this.setSelectedUserUnitSpecificType(new String[]{""});
		this.setPreviousUnitUser("");
	}

	public String getEpisodeCode()
	{
		return episodeCode;
	}

	public void setEpisodeCode(String episodeCode)
	{
		this.episodeCode = episodeCode;
	}

	public String getEpisodeVisitNo()
	{
		return episodeVisitNo;
	}

	public void setEpisodeVisitNo(String episodeVisitNo)
	{
		this.episodeVisitNo = episodeVisitNo;
	}

	public String getAdmissionNo()
	{
		return admissionNo;
	}

	public void setAdmissionNo(String admissionNo)
	{
		this.admissionNo = admissionNo;
	}

	public String getDeskType()
	{
		return deskType;
	}

	public void setDeskType(String deskType)
	{
		this.deskType = deskType;
	}

	public String getDeskId()
	{
		return deskId;
	}

	public void setDeskId(String deskId)
	{
		this.deskId = deskId;
	}

	public String[] getSelectedProfileId()
	{
		return selectedProfileId;
	}

	public void setSelectedProfileId(String[] selectedProfileId)
	{
		this.selectedProfileId = selectedProfileId;
	}

	public String[] getSelectedRow()
	{
		return selectedRow;
	}

	public void setSelectedRow(String[] selectedRow)
	{
		this.selectedRow = selectedRow;
	}

	public String getRemarks()
	{
		return remarks;
	}

	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}

	public String getSelectedMenu()
	{
		return selectedMenu;
	}

	public void setSelectedMenu(String selectedMenu)
	{
		this.selectedMenu = selectedMenu;
	}

	public String getProfileType()
	{
		return profileType;
	}

	public void setProfileType(String profileType)
	{
		this.profileType = profileType;
	}

	public String getHmode()
	{
		return hmode;
	}

	public void setHmode(String hmode)
	{
		this.hmode = hmode;
	}

	public String getSerialNo()
	{
		return serialNo;
	}

	public void setSerialNo(String serialNo)
	{
		this.serialNo = serialNo;
	}

	public String getDepartmentUnitCode()
	{
		return departmentUnitCode;
	}

	public void setDepartmentUnitCode(String departmentUnitCode)
	{
		this.departmentUnitCode = departmentUnitCode;
	}

	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	public String getPatCrNo()
	{
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo)
	{
		this.patCrNo = patCrNo;
	}

	public String getProfileId()
	{
		return profileId;
	}

	public void setProfileId(String profileId)
	{
		this.profileId = profileId;
	}

	public String getProfileHeader()
	{
		return profileHeader;
	}

	public void setProfileHeader(String profileHeader)
	{
		this.profileHeader = profileHeader;
	}

	public String getAccessType()
	{
		return accessType;
	}

	public void setAccessType(String accessType)
	{
		this.accessType = accessType;
	}

	public String[] getSelectedSerialNo()
	{
		return selectedSerialNo;
	}

	public void setSelectedSerialNo(String[] selectedSerialNo)
	{
		this.selectedSerialNo = selectedSerialNo;
	}

	public String[] getSelectedIndex()
	{
		return selectedIndex;
	}

	public void setSelectedIndex(String[] selectedIndex)
	{
		this.selectedIndex = selectedIndex;
	}



	public String getSelectedMenuId()
	{
		return selectedMenuId;
	}

	public void setSelectedMenuId(String selectedMenuId)
	{
		this.selectedMenuId = selectedMenuId;
	}

	public String getUserLevel()
	{
		return userLevel;
	}

	public void setUserLevel(String userLevel)
	{
		this.userLevel = userLevel;
	}

	public String getProfileHTML()
	{
		return profileHTML;
	}

	public void setProfileHTML(String profileHTML)
	{
		this.profileHTML = profileHTML;
	}

	

	public String getAccessPrivilSerialNo()
	{
		return accessPrivilSerialNo;
	}

	public void setAccessPrivilSerialNo(String accessPrivilSerialNo)
	{
		this.accessPrivilSerialNo = accessPrivilSerialNo;
	}

	

	public String[] getSelectedUnits()
	{
		return selectedUnits;
	}

	public void setSelectedUnits(String[] selectedUnits)
	{
		this.selectedUnits = selectedUnits;
	}

	public String getSearchUserName()
	{
		return searchUserName;
	}

	public void setSearchUserName(String searchUserName)
	{
		this.searchUserName = searchUserName;
	}

	public String getSearchEmpName()
	{
		return searchEmpName;
	}

	public void setSearchEmpName(String searchEmpName)
	{
		this.searchEmpName = searchEmpName;
	}

	public String getSearchEmpId()
	{
		return searchEmpId;
	}

	public void setSearchEmpId(String searchEmpId)
	{
		this.searchEmpId = searchEmpId;
	}

	public String getSearchMode()
	{
		return searchMode;
	}

	public void setSearchMode(String searchMode)
	{
		this.searchMode = searchMode;
	}

	public String[] getSelectedUserIndex()
	{
		return selectedUserIndex;
	}

	public void setSelectedUserIndex(String[] selectedUserIndex)
	{
		this.selectedUserIndex = selectedUserIndex;
	}

	public String getSearchString()
	{
		return searchString;
	}

	public void setSearchString(String searchString)
	{
		this.searchString = searchString;
	}

	public String[] getUsersList()
	{
		return usersList;
	}

	public void setUsersList(String[] usersList)
	{
		this.usersList = usersList;
	}

	public String[] getSelectedUsers()
	{
		return selectedUsers;
	}

	public void setSelectedUsers(String[] selectedUsers)
	{
		this.selectedUsers = selectedUsers;
	}

	public String[] getSelectedUserUnitSpecificType() {
		return selectedUserUnitSpecificType;
	}

	public void setSelectedUserUnitSpecificType(
			String[] selectedUserUnitSpecificType) {
		this.selectedUserUnitSpecificType = selectedUserUnitSpecificType;
	}

	public String getSelectedAccessType() {
		return selectedAccessType;
	}

	public void setSelectedAccessType(String selectedAccessType) {
		this.selectedAccessType = selectedAccessType;
	}

	public String[] getProfileHeaderValue() {
		return profileHeaderValue;
	}

	public void setProfileHeaderValue(String[] profileHeaderValue) {
		this.profileHeaderValue = profileHeaderValue;
	}

	public String[] getFetchSelectedRow() {
		return fetchSelectedRow;
	}

	public void setFetchSelectedRow(String[] fetchSelectedRow) {
		this.fetchSelectedRow = fetchSelectedRow;
	}

	public String getDiagnosisCheckFlag() {
		return diagnosisCheckFlag;
	}

	public void setDiagnosisCheckFlag(String diagnosisCheckFlag) {
		this.diagnosisCheckFlag = diagnosisCheckFlag;
	}

	public String getAllergyCheckFlag() {
		return allergyCheckFlag;
	}

	public void setAllergyCheckFlag(String allergyCheckFlag) {
		this.allergyCheckFlag = allergyCheckFlag;
	}

	public String[] getRequisitionDNo() {
		return requisitionDNo;
	}

	public void setRequisitionDNo(String[] requisitionDNo) {
		this.requisitionDNo = requisitionDNo;
	}

	public String getInvestigationCheckFlag() {
		return investigationCheckFlag;
	}

	public void setInvestigationCheckFlag(String investigationCheckFlag) {
		this.investigationCheckFlag = investigationCheckFlag;
	}

	public String getPatCategoryCode() {
		return patCategoryCode;
	}

	public void setPatCategoryCode(String patCategoryCode) {
		this.patCategoryCode = patCategoryCode;
	}

	public String getSelectedUserLevel() {
		return selectedUserLevel;
	}

	public void setSelectedUserLevel(String selectedUserLevel) {
		this.selectedUserLevel = selectedUserLevel;
	}

	public String[] getUnitList() {
		return unitList;
	}

	public void setUnitList(String[] unitList) {
		this.unitList = unitList;
	}

	public String[] getUnitListLeft() {
		return unitListLeft;
	}

	public void setUnitListLeft(String[] unitListLeft) {
		this.unitListLeft = unitListLeft;
	}

	public String getDeleteIndex() {
		return deleteIndex;
	}

	public void setDeleteIndex(String deleteIndex) {
		this.deleteIndex = deleteIndex;
	}

	public String getTreatmentCheckFlag() {
		return treatmentCheckFlag;
	}

	public void setTreatmentCheckFlag(String treatmentCheckFlag) {
		this.treatmentCheckFlag = treatmentCheckFlag;
	}

	public String getGenericCheckFlag() {
		return genericCheckFlag;
	}

	public void setGenericCheckFlag(String genericCheckFlag) {
		this.genericCheckFlag = genericCheckFlag;
	}

	public String getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getDisclaimerDesc1() {
		return disclaimerDesc1;
	}

	public void setDisclaimerDesc1(String disclaimerDesc1) {
		this.disclaimerDesc1 = disclaimerDesc1;
	}

	public String getDisclaimerDesc2() {
		return disclaimerDesc2;
	}

	public void setDisclaimerDesc2(String disclaimerDesc2) {
		this.disclaimerDesc2 = disclaimerDesc2;
	}

	public String getDisclaimerDesc3() {
		return disclaimerDesc3;
	}

	public void setDisclaimerDesc3(String disclaimerDesc3) {
		this.disclaimerDesc3 = disclaimerDesc3;
	}

	public String getSelectedUnitUserLevel() {
		return selectedUnitUserLevel;
	}

	public void setSelectedUnitUserLevel(String selectedUnitUserLevel) {
		this.selectedUnitUserLevel = selectedUnitUserLevel;
	}

	public String getUserSeatId() {
		return userSeatId;
	}

	public void setUserSeatId(String userSeatId) {
		this.userSeatId = userSeatId;
	}

	
	public String getDeskMenuName() {
		return deskMenuName;
	}

	public void setDeskMenuName(String deskMenuName) {
		this.deskMenuName = deskMenuName;
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

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String[] getRxContinueFlag() {
		return rxContinueFlag;
	}

	public void setRxContinueFlag(String[] rxContinueFlag) {
		this.rxContinueFlag = rxContinueFlag;
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

	public String getDrugDetailRows() {
		return drugDetailRows;
	}

	public void setDrugDetailRows(String drugDetailRows) {
		this.drugDetailRows = drugDetailRows;
	}

	public String[] getSelDrugId() {
		return selDrugId;
	}

	public void setSelDrugId(String[] selDrugId) {
		this.selDrugId = selDrugId;
	}

	public String[] getSelDrugItemTypeId() {
		return selDrugItemTypeId;
	}

	public void setSelDrugItemTypeId(String[] selDrugItemTypeId) {
		this.selDrugItemTypeId = selDrugItemTypeId;
	}

	public String[] getSelDrugName() {
		return selDrugName;
	}

	public void setSelDrugName(String[] selDrugName) {
		this.selDrugName = selDrugName;
	}

	public String[] getSelDoseId() {
		return selDoseId;
	}

	public void setSelDoseId(String[] selDoseId) {
		this.selDoseId = selDoseId;
	}

	public String[] getSelDoseName() {
		return selDoseName;
	}

	public void setSelDoseName(String[] selDoseName) {
		this.selDoseName = selDoseName;
	}

	public String[] getSelFrequencyId() {
		return selFrequencyId;
	}

	public void setSelFrequencyId(String[] selFrequencyId) {
		this.selFrequencyId = selFrequencyId;
	}

	public String[] getSelDays() {
		return selDays;
	}

	public void setSelDays(String[] selDays) {
		this.selDays = selDays;
	}

	public String[] getSelStartDay() {
		return selStartDay;
	}

	public void setSelStartDay(String[] selStartDay) {
		this.selStartDay = selStartDay;
	}

	public String[] getSelInstructions() {
		return selInstructions;
	}

	public void setSelInstructions(String[] selInstructions) {
		this.selInstructions = selInstructions;
	}

	public String[] getSelIsEmptyStomach() {
		return selIsEmptyStomach;
	}

	public void setSelIsEmptyStomach(String[] selIsEmptyStomach) {
		this.selIsEmptyStomach = selIsEmptyStomach;
	}

	public String getEmptyStomachIndexArray() {
		return emptyStomachIndexArray;
	}

	public void setEmptyStomachIndexArray(String emptyStomachIndexArray) {
		this.emptyStomachIndexArray = emptyStomachIndexArray;
	}

	public String[] getSelPrevFreq() {
		return selPrevFreq;
	}

	public void setSelPrevFreq(String[] selPrevFreq) {
		this.selPrevFreq = selPrevFreq;
	}

	public String getExtDrugDetailRows() {
		return extDrugDetailRows;
	}

	public void setExtDrugDetailRows(String extDrugDetailRows) {
		this.extDrugDetailRows = extDrugDetailRows;
	}

	public String[] getSelExtDrugId() {
		return selExtDrugId;
	}

	public void setSelExtDrugId(String[] selExtDrugId) {
		this.selExtDrugId = selExtDrugId;
	}

	public String[] getSelExtDrugItemTypeId() {
		return selExtDrugItemTypeId;
	}

	public void setSelExtDrugItemTypeId(String[] selExtDrugItemTypeId) {
		this.selExtDrugItemTypeId = selExtDrugItemTypeId;
	}

	public String[] getSelExtDrugName() {
		return selExtDrugName;
	}

	public void setSelExtDrugName(String[] selExtDrugName) {
		this.selExtDrugName = selExtDrugName;
	}

	public String[] getSelExtDoseId() {
		return selExtDoseId;
	}

	public void setSelExtDoseId(String[] selExtDoseId) {
		this.selExtDoseId = selExtDoseId;
	}

	public String[] getSelExtDoseName() {
		return selExtDoseName;
	}

	public void setSelExtDoseName(String[] selExtDoseName) {
		this.selExtDoseName = selExtDoseName;
	}

	public String[] getSelExtFrequencyId() {
		return selExtFrequencyId;
	}

	public void setSelExtFrequencyId(String[] selExtFrequencyId) {
		this.selExtFrequencyId = selExtFrequencyId;
	}

	public String[] getSelExtDays() {
		return selExtDays;
	}

	public void setSelExtDays(String[] selExtDays) {
		this.selExtDays = selExtDays;
	}

	public String[] getSelExtStartDay() {
		return selExtStartDay;
	}

	public void setSelExtStartDay(String[] selExtStartDay) {
		this.selExtStartDay = selExtStartDay;
	}

	public String[] getSelExtInstructions() {
		return selExtInstructions;
	}

	public void setSelExtInstructions(String[] selExtInstructions) {
		this.selExtInstructions = selExtInstructions;
	}

	public String[] getSelExtTreatmentId() {
		return selExtTreatmentId;
	}

	public void setSelExtTreatmentId(String[] selExtTreatmentId) {
		this.selExtTreatmentId = selExtTreatmentId;
	}

	public String[] getSelExtTreatmentName() {
		return selExtTreatmentName;
	}

	public void setSelExtTreatmentName(String[] selExtTreatmentName) {
		this.selExtTreatmentName = selExtTreatmentName;
	}

	public String[] getExtRxContinue() {
		return extRxContinue;
	}

	public void setExtRxContinue(String[] extRxContinue) {
		this.extRxContinue = extRxContinue;
	}

	public String[] getExtRevoke() {
		return extRevoke;
	}

	public void setExtRevoke(String[] extRevoke) {
		this.extRevoke = extRevoke;
	}

	public String getExtIndex() {
		return extIndex;
	}

	public void setExtIndex(String extIndex) {
		this.extIndex = extIndex;
	}

	public String[] getPrevSelExtFrequencyId() {
		return prevSelExtFrequencyId;
	}

	public void setPrevSelExtFrequencyId(String[] prevSelExtFrequencyId) {
		this.prevSelExtFrequencyId = prevSelExtFrequencyId;
	}

	public String[] getPrevSelExtTreatmentName() {
		return prevSelExtTreatmentName;
	}

	public void setPrevSelExtTreatmentName(String[] prevSelExtTreatmentName) {
		this.prevSelExtTreatmentName = prevSelExtTreatmentName;
	}

	public String[] getPrevExtTreatmentName() {
		return prevExtTreatmentName;
	}

	public void setPrevExtTreatmentName(String[] prevExtTreatmentName) {
		this.prevExtTreatmentName = prevExtTreatmentName;
	}

	public String[] getExtension() {
		return extension;
	}

	public void setExtension(String[] extension) {
		this.extension = extension;
	}

	public String[] getExtensionDays() {
		return extensionDays;
	}

	public void setExtensionDays(String[] extensionDays) {
		this.extensionDays = extensionDays;
	}

	public String getRevokeHidden() {
		return revokeHidden;
	}

	public void setRevokeHidden(String revokeHidden) {
		this.revokeHidden = revokeHidden;
	}

	public String getExtensionHidden() {
		return extensionHidden;
	}

	public void setExtensionHidden(String extensionHidden) {
		this.extensionHidden = extensionHidden;
	}

	public String getExtensionDaysHidden() {
		return extensionDaysHidden;
	}

	public void setExtensionDaysHidden(String extensionDaysHidden) {
		this.extensionDaysHidden = extensionDaysHidden;
	}

	public String[] getQuantity() {
		return quantity;
	}

	public void setQuantity(String[] quantity) {
		this.quantity = quantity;
	}

	public String getSearchDrugName() {
		return searchDrugName;
	}

	public void setSearchDrugName(String searchDrugName) {
		this.searchDrugName = searchDrugName;
	}

	public String getSearchDrugItemTypeId() {
		return searchDrugItemTypeId;
	}

	public void setSearchDrugItemTypeId(String searchDrugItemTypeId) {
		this.searchDrugItemTypeId = searchDrugItemTypeId;
	}

	public String getSelectedDrugIndex() {
		return selectedDrugIndex;
	}

	public void setSelectedDrugIndex(String selectedDrugIndex) {
		this.selectedDrugIndex = selectedDrugIndex;
	}

	public String[] getIcdCode() {
		return icdCode;
	}

	public void setIcdCode(String[] icdCode) {
		this.icdCode = icdCode;
	}

	public String[] getDignosisName() {
		return dignosisName;
	}

	public void setDignosisName(String[] dignosisName) {
		this.dignosisName = dignosisName;
	}

	public String[] getHospitalDiagnosisCode() {
		return hospitalDiagnosisCode;
	}

	public void setHospitalDiagnosisCode(String[] hospitalDiagnosisCode) {
		this.hospitalDiagnosisCode = hospitalDiagnosisCode;
	}

	public String[] getHospitalDiagnosisName() {
		return hospitalDiagnosisName;
	}

	public void setHospitalDiagnosisName(String[] hospitalDiagnosisName) {
		this.hospitalDiagnosisName = hospitalDiagnosisName;
	}

	public String[] getDiagonisticTypeCode() {
		return diagonisticTypeCode;
	}

	public void setDiagonisticTypeCode(String[] diagonisticTypeCode) {
		this.diagonisticTypeCode = diagonisticTypeCode;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getSelectedCode() {
		return selectedCode;
	}

	public void setSelectedCode(String selectedCode) {
		this.selectedCode = selectedCode;
	}

	public String getNumberOfRow() {
		return numberOfRow;
	}

	public void setNumberOfRow(String numberOfRow) {
		this.numberOfRow = numberOfRow;
	}

	public String getRepeat() {
		return repeat;
	}

	public void setRepeat(String repeat) {
		this.repeat = repeat;
	}

	public String getDiagnosisCodeType() {
		return diagnosisCodeType;
	}

	public void setDiagnosisCodeType(String diagnosisCodeType) {
		this.diagnosisCodeType = diagnosisCodeType;
	}

	public String getUnitDiagnosisCodeType() {
		return unitDiagnosisCodeType;
	}

	public void setUnitDiagnosisCodeType(String unitDiagnosisCodeType) {
		this.unitDiagnosisCodeType = unitDiagnosisCodeType;
	}

	public String getComboOptionString() {
		return comboOptionString;
	}

	public void setComboOptionString(String comboOptionString) {
		this.comboOptionString = comboOptionString;
	}

	public String[] getSelectedDiagnosis() {
		return selectedDiagnosis;
	}

	public void setSelectedDiagnosis(String[] selectedDiagnosis) {
		this.selectedDiagnosis = selectedDiagnosis;
	}

	public String[] getAddDiagnosis() {
		return addDiagnosis;
	}

	public void setAddDiagnosis(String[] addDiagnosis) {
		this.addDiagnosis = addDiagnosis;
	}

	public String getRepeatedDiagnosisLength() {
		return repeatedDiagnosisLength;
	}

	public void setRepeatedDiagnosisLength(String repeatedDiagnosisLength) {
		this.repeatedDiagnosisLength = repeatedDiagnosisLength;
	}

	public String[] getAddDiagnosisCode() {
		return addDiagnosisCode;
	}

	public void setAddDiagnosisCode(String[] addDiagnosisCode) {
		this.addDiagnosisCode = addDiagnosisCode;
	}

	public String[] getAddDiagnosticTypeCode() {
		return addDiagnosticTypeCode;
	}

	public void setAddDiagnosticTypeCode(String[] addDiagnosticTypeCode) {
		this.addDiagnosticTypeCode = addDiagnosticTypeCode;
	}

	public String[] getAddDiagnosisRemarks() {
		return addDiagnosisRemarks;
	}

	public void setAddDiagnosisRemarks(String[] addDiagnosisRemarks) {
		this.addDiagnosisRemarks = addDiagnosisRemarks;
	}

	public String getSelectedDiagCode() {
		return selectedDiagCode;
	}

	public void setSelectedDiagCode(String selectedDiagCode) {
		this.selectedDiagCode = selectedDiagCode;
	}

	public String[] getEndDate() {
		return endDate;
	}

	public void setEndDate(String[] endDate) {
		this.endDate = endDate;
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

	public String getRestSerialNo() {
		return restSerialNo;
	}

	public void setRestSerialNo(String restSerialNo) {
		this.restSerialNo = restSerialNo;
	}

	public String getDietType() {
		return dietType;
	}

	public void setDietType(String dietType) {
		this.dietType = dietType;
	}

	public String getDietDays() {
		return dietDays;
	}

	public void setDietDays(String dietDays) {
		this.dietDays = dietDays;
	}

	public String getDietStartDate() {
		return dietStartDate;
	}

	public void setDietStartDate(String dietStartDate) {
		this.dietStartDate = dietStartDate;
	}

	public String getDietRemark() {
		return dietRemark;
	}

	public void setDietRemark(String dietRemark) {
		this.dietRemark = dietRemark;
	}

	public String getDietTypeId() {
		return dietTypeId;
	}

	public void setDietTypeId(String dietTypeId) {
		this.dietTypeId = dietTypeId;
	}

	public String getDietSerialNo() {
		return dietSerialNo;
	}

	public void setDietSerialNo(String dietSerialNo) {
		this.dietSerialNo = dietSerialNo;
	}

	public String[] getPrevDietTypeId() {
		return prevDietTypeId;
	}

	public void setPrevDietTypeId(String[] prevDietTypeId) {
		this.prevDietTypeId = prevDietTypeId;
	}

	public String getActiveTab() {
		return activeTab;
	}

	public void setActiveTab(String activeTab) {
		this.activeTab = activeTab;
	}

	public String[] getSelStartDate() {
		return selStartDate;
	}

	public void setSelStartDate(String[] selStartDate) {
		this.selStartDate = selStartDate;
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

	public String getFrequencyImageIndex() {
		return frequencyImageIndex;
	}

	public void setFrequencyImageIndex(String frequencyImageIndex) {
		this.frequencyImageIndex = frequencyImageIndex;
	}

	public String getIsEmptyStomach() {
		return isEmptyStomach;
	}

	public void setIsEmptyStomach(String isEmptyStomach) {
		this.isEmptyStomach = isEmptyStomach;
	}

	public String getPopupFreqId() {
		return popupFreqId;
	}

	public void setPopupFreqId(String popupFreqId) {
		this.popupFreqId = popupFreqId;
	}

	public String[] getDrugFreqShift() {
		return drugFreqShift;
	}

	public void setDrugFreqShift(String[] drugFreqShift) {
		this.drugFreqShift = drugFreqShift;
	}

	public String getPopupItemTypeId() {
		return popupItemTypeId;
	}

	public void setPopupItemTypeId(String popupItemTypeId) {
		this.popupItemTypeId = popupItemTypeId;
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

	public String getPopupRowIndex() {
		return popupRowIndex;
	}

	public void setPopupRowIndex(String popupRowIndex) {
		this.popupRowIndex = popupRowIndex;
	}

	public String getPopupFreqCount() {
		return popupFreqCount;
	}

	public void setPopupFreqCount(String popupFreqCount) {
		this.popupFreqCount = popupFreqCount;
	}

	public String getPopupDrugId() {
		return popupDrugId;
	}

	public void setPopupDrugId(String popupDrugId) {
		this.popupDrugId = popupDrugId;
	}

	public String getPopupCheckIndex() {
		return popupCheckIndex;
	}

	public void setPopupCheckIndex(String popupCheckIndex) {
		this.popupCheckIndex = popupCheckIndex;
	}

	public String getPopupDoseName() {
		return popupDoseName;
	}

	public void setPopupDoseName(String popupDoseName) {
		this.popupDoseName = popupDoseName;
	}

	public String[] getPopupDoseNameArray() {
		return popupDoseNameArray;
	}

	public void setPopupDoseNameArray(String[] popupDoseNameArray) {
		this.popupDoseNameArray = popupDoseNameArray;
	}

	public String getScheduleIndex() {
		return scheduleIndex;
	}

	public void setScheduleIndex(String scheduleIndex) {
		this.scheduleIndex = scheduleIndex;
	}

	public String[] getSelDrugRouteId() {
		return selDrugRouteId;
	}

	public void setSelDrugRouteId(String[] selDrugRouteId) {
		this.selDrugRouteId = selDrugRouteId;
	}

	public String[] getSelDrugRouteName() {
		return selDrugRouteName;
	}

	public void setSelDrugRouteName(String[] selDrugRouteName) {
		this.selDrugRouteName = selDrugRouteName;
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

	public String[] getSelectedDrug() {
		return selectedDrug;
	}

	public void setSelectedDrug(String[] selectedDrug) {
		this.selectedDrug = selectedDrug;
	}

	public String getDischargeModifyFlag() {
		return dischargeModifyFlag;
	}

	public void setDischargeModifyFlag(String dischargeModifyFlag) {
		this.dischargeModifyFlag = dischargeModifyFlag;
	}

	public String getInstActivityType() {
		return instActivityType;
	}

	public void setInstActivityType(String instActivityType) {
		this.instActivityType = instActivityType;
	}

	public String getViewMode() {
		return viewMode;
	}

	public void setViewMode(String viewMode) {
		this.viewMode = viewMode;
	}

	public String[] getSelectedParas() {
		return selectedParas;
	}

	public void setSelectedParas(String[] selectedParas) {
		this.selectedParas = selectedParas;
	}

	public String getComplaintsCheckFlag() {
		return complaintsCheckFlag;
	}

	public void setComplaintsCheckFlag(String complaintsCheckFlag) {
		this.complaintsCheckFlag = complaintsCheckFlag;
	}

	public String getReportMode() {
		return reportMode;
	}

	public void setReportMode(String reportMode) {
		this.reportMode = reportMode;
	}

	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	public String[] getSelectedDates() {
		return selectedDates;
	}

	public void setSelectedDates(String[] selectedDates) {
		this.selectedDates = selectedDates;
	}


	public String getHiddenProgNotes() {
		return hiddenProgNotes;
	}

	public void setHiddenProgNotes(String hiddenProgNotes) {
		this.hiddenProgNotes = hiddenProgNotes;
	}

	public String getShowProgNotes() {
		return showProgNotes;
	}

	public void setShowProgNotes(String showProgNotes) {
		this.showProgNotes = showProgNotes;
	}

	public String getComplaintsDateWiseCheckFlag() {
		return complaintsDateWiseCheckFlag;
	}

	public void setComplaintsDateWiseCheckFlag(String complaintsDateWiseCheckFlag) {
		this.complaintsDateWiseCheckFlag = complaintsDateWiseCheckFlag;
	}

	public String getOperationCheckFlag() {
		return operationCheckFlag;
	}

	public void setOperationCheckFlag(String operationCheckFlag) {
		this.operationCheckFlag = operationCheckFlag;
	}

	public String getImageCheckFlag() {
		return imageCheckFlag;
	}

	public void setImageCheckFlag(String imageCheckFlag) {
		this.imageCheckFlag = imageCheckFlag;
	}

	public String getIsDesclaimerRequired() {
		return isDesclaimerRequired;
	}

	public void setIsDesclaimerRequired(String isDesclaimerRequired) {
		this.isDesclaimerRequired = isDesclaimerRequired;
	}

	public String getExtInvCheckFlag()
	{
		return extInvCheckFlag;
	}

	public void setExtInvCheckFlag(String extInvCheckFlag)
	{
		this.extInvCheckFlag = extInvCheckFlag;
	}

	public String getProgNotesCheckFlag()
	{
		return progNotesCheckFlag;
	}

	public void setProgNotesCheckFlag(String progNotesCheckFlag)
	{
		this.progNotesCheckFlag = progNotesCheckFlag;
	}

	public String getDischargeType()
	{
		return dischargeType;
	}

	public void setDischargeType(String dischargeType)
	{
		this.dischargeType = dischargeType;
	}

	public String getDischargeAdvisedBy()
	{
		return dischargeAdvisedBy;
	}

	public void setDischargeAdvisedBy(String dischargeAdvisedBy)
	{
		this.dischargeAdvisedBy = dischargeAdvisedBy;
	}

	public String getDischargeAdvisedDept()
	{
		return dischargeAdvisedDept;
	}

	public void setDischargeAdvisedDept(String dischargeAdvisedDept)
	{
		this.dischargeAdvisedDept = dischargeAdvisedDept;
	}

	public String getDischargeTypeName()
	{
		return dischargeTypeName;
	}

	public void setDischargeTypeName(String dischargeTypeName)
	{
		this.dischargeTypeName = dischargeTypeName;
	}

	public String getDischargeAdvisedByName()
	{
		return dischargeAdvisedByName;
	}

	public void setDischargeAdvisedByName(String dischargeAdvisedByName)
	{
		this.dischargeAdvisedByName = dischargeAdvisedByName;
	}

	public String getDischargeAdvisedDeptName()
	{
		return dischargeAdvisedDeptName;
	}

	public void setDischargeAdvisedDeptName(String dischargeAdvisedDeptName)
	{
		this.dischargeAdvisedDeptName = dischargeAdvisedDeptName;
	}

	public String[] getSelDoseQuantity()
	{
		return selDoseQuantity;
	}

	public void setSelDoseQuantity(String[] selDoseQuantity)
	{
		this.selDoseQuantity = selDoseQuantity;
	}

	public String getWardCode()
	{
		return wardCode;
	}

	public void setWardCode(String wardCode)
	{
		this.wardCode = wardCode;
	}

	public String getProfileGenerationMode() {
		return profileGenerationMode;
	}

	public void setProfileGenerationMode(String profileGenerationMode) {
		this.profileGenerationMode = profileGenerationMode;
	}

	public String getAutoProfileCalledFrom() {
		return autoProfileCalledFrom;
	}

	public void setAutoProfileCalledFrom(String autoProfileCalledFrom) {
		this.autoProfileCalledFrom = autoProfileCalledFrom;
	}

	public String[] getSelDrugAdminId() {
		return selDrugAdminId;
	}

	public void setSelDrugAdminId(String[] selDrugAdminId) {
		this.selDrugAdminId = selDrugAdminId;
	}

	public String[] getSelDrugAdminName() {
		return selDrugAdminName;
	}

	public void setSelDrugAdminName(String[] selDrugAdminName) {
		this.selDrugAdminName = selDrugAdminName;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getSysDateForValidation() {
		return sysDateForValidation;
	}

	public void setSysDateForValidation(String sysDateForValidation) {
		this.sysDateForValidation = sysDateForValidation;
	}

	public String getPrescriptionDate() {
		return prescriptionDate;
	}

	public void setPrescriptionDate(String prescriptionDate) {
		this.prescriptionDate = prescriptionDate;
	}

	public String getSnomdPTRemarks() {
		return snomdPTRemarks;
	}

	public void setSnomdPTRemarks(String snomdPTRemarks) {
		this.snomdPTRemarks = snomdPTRemarks;
	}

	public String getSnomdCIdRemarks() {
		return snomdCIdRemarks;
	}

	public void setSnomdCIdRemarks(String snomdCIdRemarks) {
		this.snomdCIdRemarks = snomdCIdRemarks;
	}

	public String getEpiAdmStartDate() {
		return epiAdmStartDate;
	}

	public void setEpiAdmStartDate(String epiAdmStartDate) {
		this.epiAdmStartDate = epiAdmStartDate;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getFilterCriteria() {
		return filterCriteria;
	}

	public void setFilterCriteria(String filterCriteria) {
		this.filterCriteria = filterCriteria;
	}

	public String getChartId() {
		return chartId;
	}

	public void setChartId(String chartId) {
		this.chartId = chartId;
	}

	public String getGenerationType() {
		return generationType;
	}

	public void setGenerationType(String generationType) {
		this.generationType = generationType;
	}

	public String getChartHeader() {
		return chartHeader;
	}

	public void setChartHeader(String chartHeader) {
		this.chartHeader = chartHeader;
	}

	public String getIsGraph() {
		return isGraph;
	}

	public void setIsGraph(String isGraph) {
		this.isGraph = isGraph;
	}

	public String[] getChartPara() {
		return chartPara;
	}

	public void setChartPara(String[] chartPara) {
		this.chartPara = chartPara;
	}

	public boolean isHaveDefault() {
		return haveDefault;
	}

	public void setHaveDefault(boolean haveDefault) {
		this.haveDefault = haveDefault;
	}

	public String getTargetPage() {
		return targetPage;
	}

	public void setTargetPage(String targetPage) {
		this.targetPage = targetPage;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public String getOldSortType() {
		return oldSortType;
	}

	public void setOldSortType(String oldSortType) {
		this.oldSortType = oldSortType;
	}

	public String getSortChartId() {
		return sortChartId;
	}

	public void setSortChartId(String sortChartId) {
		this.sortChartId = sortChartId;
	}

	public String getGraphChartId() {
		return graphChartId;
	}

	public void setGraphChartId(String graphChartId) {
		this.graphChartId = graphChartId;
	}

	public String getGraphChartName() {
		return graphChartName;
	}

	public void setGraphChartName(String graphChartName) {
		this.graphChartName = graphChartName;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getMaxRowsCount() {
		return maxRowsCount;
	}

	public void setMaxRowsCount(int maxRowsCount) {
		this.maxRowsCount = maxRowsCount;
	}

	public String getVitalChartCheckFlag() {
		return vitalChartCheckFlag;
	}

	public void setVitalChartCheckFlag(String vitalChartCheckFlag) {
		this.vitalChartCheckFlag = vitalChartCheckFlag;
	}

	public String getChartHtml() {
		return chartHtml;
	}

	public void setChartHtml(String chartHtml) {
		this.chartHtml = chartHtml;
	}

	public String getReqDnoList() {
		return reqDnoList;
	}

	public void setReqDnoList(String reqDnoList) {
		this.reqDnoList = reqDnoList;
	}

	public String getStrHiddenPatDtl() {
		return strHiddenPatDtl;
	}

	public void setStrHiddenPatDtl(String strHiddenPatDtl) {
		this.strHiddenPatDtl = strHiddenPatDtl;
	}

	public String getReqDNo() {
		return reqDNo;
	}

	public void setReqDNo(String reqDNo) {
		this.reqDNo = reqDNo;
	}

	public String[] getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(String[] resultStatus) {
		this.resultStatus = resultStatus;
	}

	public String getIsSinglePageFlag() {
		return isSinglePageFlag;
	}

	public void setIsSinglePageFlag(String isSinglePageFlag) {
		this.isSinglePageFlag = isSinglePageFlag;
	}

	public String getIsCallFromEMR() {
		return isCallFromEMR;
	}

	public void setIsCallFromEMR(String isCallFromEMR) {
		this.isCallFromEMR = isCallFromEMR;
	}


	
}
