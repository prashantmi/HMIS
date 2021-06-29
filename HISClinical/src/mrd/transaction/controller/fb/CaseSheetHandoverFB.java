package mrd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import registration.controller.fb.CRNoFB;

public class CaseSheetHandoverFB extends CRNoFB
{
	
	private String patAdmNo;
	private String dispatchId;
	private String episodeCode;
	private String episodeVisitNo;
	private String admDateTime;
	private String registerDate;
	private String hmode;
	private String wardCode;
	private String departmentName;
	private String departmentUnitName;
	private String departmentCode;
	private String departmentUnitCode;
	private String recordId;
	private String recordDesc;
	private String slNo;
	private String recordType;
	private String remarks[];
	private String recordStatus;
	private String returnReason;
	private String patCrNo;
	private String enclosurePages;
	private String wardCodeNew;//added by swati sagar on date:06-04-2019
	private String clearmsg;//added by swati sagar on date:06-may-2019
	private String pages[];
	private String selectedRecord[];
	private String handoverTo;
	private String receivedFrom;
	private String index;
	private String []isEnclosureSelected;
	private String isChecklistSelected;
	private String []patientName;
	private String selection;
	private String selectedCheckListId[];
	private String selectedEnclosureId[];
	private String isCompulsoryArray[];
	private String checkListNameArray[];
	private String enclosureNameArray[];
	private String compulsoryEnclosureArray[];
	private int currentPage=1;
	private String searchMode;
	private String isAccept;
	private String checkedItem ;
	private String []checkedDispatchId ;
	private String lastSelectedPage="1" ;
	private String checkedChecklist ;
	private String  strCheckMode;//added by swati sagar on date:30-04-2019
	private String  strCrNo;//added by swati sagar on date:30-04-2019	
	
	//Added by Prachi
	private String mrdCode;
	private String recordTypeName;
	private String sendDateTime;
	
	private String selectedPatient;
	
	
	public String getPatAdmNo() {
		return patAdmNo;
	}
	public void setPatAdmNo(String patAdmNo) {
		this.patAdmNo = patAdmNo;
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
	public String getAdmDateTime() {
		return admDateTime;
	}
	public void setAdmDateTime(String admDateTime) {
		this.admDateTime = admDateTime;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getWardCode() {
		return wardCode;
	}
	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getDepartmentUnitName() {
		return departmentUnitName;
	}
	public void setDepartmentUnitName(String departmentUnitName) {
		this.departmentUnitName = departmentUnitName;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}
	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
	}
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public String getRecordDesc() {
		return recordDesc;
	}
	public void setRecordDesc(String recordDesc) {
		this.recordDesc = recordDesc;
	}
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	public String[] getRemarks() {
		return remarks;
	}
	public void setRemarks(String[] remarks) {
		this.remarks = remarks;
	}
	public String getRecordStatus() {
		return recordStatus;
	}
	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}
	public String getReturnReason() {
		return returnReason;
	}
	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getEnclosurePages() {
		return enclosurePages;
	}
	public void setEnclosurePages(String enclosurePages) {
		this.enclosurePages = enclosurePages;
	}
	public String[] getPages() {
		return pages;
	}
	public void setPages(String[] pages) {
		this.pages = pages;
	}

	public String getHandoverTo() {
		return handoverTo;
	}
	public void setHandoverTo(String handoverTo) {
		this.handoverTo = handoverTo;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String[] getIsEnclosureSelected() {
		return isEnclosureSelected;
	}
	public void setIsEnclosureSelected(String[] isEnclosureSelected) {
		this.isEnclosureSelected = isEnclosureSelected;
	}
	public String getIsChecklistSelected() {
		return isChecklistSelected;
	}
	public void setIsChecklistSelected(String isChecklistSelected) {
		this.isChecklistSelected = isChecklistSelected;
	}
	public String[] getPatientName() {
		return patientName;
	}
	public void setPatientName(String[] patientName) {
		this.patientName = patientName;
	}
	public String getSelection() {
		return selection;
	}
	public void setSelection(String selection) {
		this.selection = selection;
	}
	public String[] getSelectedCheckListId() {
		return selectedCheckListId;
	}
	public void setSelectedCheckListId(String[] selectedCheckListId) {
		this.selectedCheckListId = selectedCheckListId;
	}
	public String[] getSelectedEnclosureId() {
		return selectedEnclosureId;
	}
	public void setSelectedEnclosureId(String[] selectedEnclosureId) {
		this.selectedEnclosureId = selectedEnclosureId;
	}
	public String[] getIsCompulsoryArray() {
		return isCompulsoryArray;
	}
	public void setIsCompulsoryArray(String[] isCompulsoryArray) {
		this.isCompulsoryArray = isCompulsoryArray;
	}
	public String[] getCheckListNameArray() {
		return checkListNameArray;
	}
	public void setCheckListNameArray(String[] checkListNameArray) {
		this.checkListNameArray = checkListNameArray;
	}
	public String[] getEnclosureNameArray() {
		return enclosureNameArray;
	}
	public void setEnclosureNameArray(String[] enclosureNameArray) {
		this.enclosureNameArray = enclosureNameArray;
	}
	public String[] getCompulsoryEnclosureArray() {
		return compulsoryEnclosureArray;
	}
	public void setCompulsoryEnclosureArray(String[] compulsoryEnclosureArray) {
		this.compulsoryEnclosureArray = compulsoryEnclosureArray;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public String getSearchMode() {
		return searchMode;
	}
	public void setSearchMode(String searchMode) {
		this.searchMode = searchMode;
	}
	
	public String getSelectedPatient() {
		return selectedPatient;
	}
	public void setSelectedPatient(String selectedPatient) {
		this.selectedPatient = selectedPatient;
	}
	public String[] getSelectedRecord() {
		return selectedRecord;
	}
	public void setSelectedRecord(String[] selectedRecord) {
		this.selectedRecord = selectedRecord;
	}
	
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		this.patCrNo="";
		this.patAdmNo="";
		this.selection="";
		this.departmentUnitCode="-1";
		this.wardCode="-1";
		this.checkedItem="";
	}
	public String getIsAccept() {
		return isAccept;
	}
	public void setIsAccept(String isAccept) {
		this.isAccept = isAccept;
	}
	public String getCheckedItem() {
		return checkedItem;
	}
	public void setCheckedItem(String checkedItem) {
		this.checkedItem = checkedItem;
	}
	public String[] getCheckedDispatchId() {
		return checkedDispatchId;
	}
	public void setCheckedDispatchId(String []checkedDispatchId) {
		this.checkedDispatchId = checkedDispatchId;
	}
	public void setDispatchId(String dispatchId) {
		this.dispatchId = dispatchId;
	}
	public String getLastSelectedPage() {
		return lastSelectedPage;
	}
	public void setLastSelectedPage(String lastSelectedPage) {
		this.lastSelectedPage = lastSelectedPage;
	}
	public String getDispatchId() {
		return dispatchId;
	}
	public String getReceivedFrom() {
		return receivedFrom;
	}
	public void setReceivedFrom(String receivedFrom) {
		this.receivedFrom = receivedFrom;
	}
	public String getCheckedChecklist() {
		return checkedChecklist;
	}
	public void setCheckedChecklist(String checkedChecklist) {
		this.checkedChecklist = checkedChecklist;
	}
	public String getMrdCode() {
		return mrdCode;
	}
	public void setMrdCode(String mrdCode) {
		this.mrdCode = mrdCode;
	}
	public String getRecordTypeName() {
		return recordTypeName;
	}
	public void setRecordTypeName(String recordTypeName) {
		this.recordTypeName = recordTypeName;
	}
	public String getSendDateTime() {
		return sendDateTime;
	}
	public void setSendDateTime(String sendDateTime) {
		this.sendDateTime = sendDateTime;
	}
	public String getStrCheckMode() {
		return strCheckMode;
	}
	public void setStrCheckMode(String strCheckMode) {
		this.strCheckMode = strCheckMode;
	}
	public String getStrCrNo() {
		return strCrNo;
	}
	public void setStrCrNo(String strCrNo) {
		this.strCrNo = strCrNo;
	}
	public String getWardCodeNew() {
		return wardCodeNew;
	}
	public void setWardCodeNew(String wardCodeNew) {
		this.wardCodeNew = wardCodeNew;
	}
	public String getClearmsg() {
		return clearmsg;
	}
	public void setClearmsg(String clearmsg) {
		this.clearmsg = clearmsg;
	}
	
}
