package mrd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import registration.controller.fb.CRNoFB;

public class CaseSheetDispatchFB extends CRNoFB
{
	
	private String patAdmNo;
	private String caseSheetId;
	private String caseSheetType;
	//private String patName;
	private String patFirstName;
	private String patMiddleName;
	private String patLastName;
	private String patAge;
	private String patGender;
	private String patGenderCode;
	private String episodeCode;
	private String episodeVisitNo;
	private String admDateTime;
	private String registerDate;
	private String admStatusCode;
	private String hmode;
	private String mlcNo;
	private String wardCode;
	private String bedCode;
	private String wardName;
	private String bedName;
	private String roomCode;
	private String departmentName;
	private String departmentUnitName;
	private String departmentCode;
	private String departmentUnitCode;
	private String recordId;
	private String recordDesc;
	private String slNo;
	private String recordType;
	private String remarks[];
	private String senderSeatId;
	private String senderDate;
	private String recipientSeatId;
	private String recipientDate;
	private String receiveFrom;
	private String recordStatus;
	private String returnReason;
	private String patCrNo;
	private String enclosurePages;
	private String pages[];
	private String []selectCRNo;
	private String handoverTo;
	private String index;
	private String []isEnclosureSelected;
	private String isChecklistSelected;
	private String []patientName;
	private String delayReason;
	private String  strCheckMode;//added by swati sagar on date:03-04-2019
	private String  strCrNo;//added by swati sagar on date:03-04-2019	
	//
	private String selectedAdmnNo;
	private String selectedCheckListId[];
	private String selectedEnclosureId[];
	private String isCompulsoryArray[];
	private String checkListNameArray[];
	private String enclosureNameArray[];
	private String compulsoryEnclosureArray[];
	private int currentPage=1;
	private String checkedItem;
	private String wardCodeNew;//added by swati sagar on date:03-04-2019
	private String clearmsg;//added by swati sagar on date:03-may-2019
	private String checkedChecklist;
	private String isdelay;
	
	public String getIsdelay() {
		return isdelay;
	}
	public void setIsdelay(String isdelay) {
		this.isdelay = isdelay;
	}
	public String getIsmlc() {
		return ismlc;
	}
	public void setIsmlc(String ismlc) {
		this.ismlc = ismlc;
	}
	private String ismlc;
	
	/*To populate from Vo*/
	
	private String patName;
	private String disDateTime;
	private String ownDepartmentUnitCode;
	private String ownDepartmentCode;
	private String flag;
	
	private String selectedPatient;
	
	
	public String getPatAdmNo() {
		return patAdmNo;
	}
	public void setPatAdmNo(String patAdmNo) {
		this.patAdmNo = patAdmNo;
	}
	public String getPatFirstName() {
		return patFirstName;
	}
	public void setPatFirstName(String patFirstName) {
		this.patFirstName = patFirstName;
	}
	public String getPatMiddleName() {
		return patMiddleName;
	}
	public void setPatMiddleName(String patMiddleName) {
		this.patMiddleName = patMiddleName;
	}
	public String getPatLastName() {
		return patLastName;
	}
	public void setPatLastName(String patLastName) {
		this.patLastName = patLastName;
	}
	public String getPatAge() {
		return patAge;
	}
	public void setPatAge(String patAge) {
		this.patAge = patAge;
	}
	public String getPatGender() {
		return patGender;
	}
	public void setPatGender(String patGender) {
		this.patGender = patGender;
	}
	public String getPatGenderCode() {
		return patGenderCode;
	}
	public void setPatGenderCode(String patGenderCode) {
		this.patGenderCode = patGenderCode;
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
	public String getAdmStatusCode() {
		return admStatusCode;
	}
	public void setAdmStatusCode(String admStatusCode) {
		this.admStatusCode = admStatusCode;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getMlcNo() {
		return mlcNo;
	}
	public void setMlcNo(String mlcNo) {
		this.mlcNo = mlcNo;
	}
	public String getWardCode() {
		return wardCode;
	}
	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}
	public String getBedCode() {
		return bedCode;
	}
	public void setBedCode(String bedCode) {
		this.bedCode = bedCode;
	}
	public String getWardName() {
		return wardName;
	}
	public void setWardName(String wardName) {
		this.wardName = wardName;
	}
	public String getBedName() {
		return bedName;
	}
	public void setBedName(String bedName) {
		this.bedName = bedName;
	}
	public String getRoomCode() {
		return roomCode;
	}
	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
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
	public String getSenderSeatId() {
		return senderSeatId;
	}
	public void setSenderSeatId(String senderSeatId) {
		this.senderSeatId = senderSeatId;
	}
	public String getSenderDate() {
		return senderDate;
	}
	public void setSenderDate(String senderDate) {
		this.senderDate = senderDate;
	}
	public String getRecipientSeatId() {
		return recipientSeatId;
	}
	public void setRecipientSeatId(String recipientSeatId) {
		this.recipientSeatId = recipientSeatId;
	}
	public String getRecipientDate() {
		return recipientDate;
	}
	public void setRecipientDate(String recipientDate) {
		this.recipientDate = recipientDate;
	}
	public String getReceiveFrom() {
		return receiveFrom;
	}
	public void setReceiveFrom(String receiveFrom) {
		this.receiveFrom = receiveFrom;
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
	public String[] getSelectCRNo() {
		return selectCRNo;
	}
	public void setSelectCRNo(String[] selectCRNo) {
		this.selectCRNo = selectCRNo;
	}
	public String getSelectedAdmnNo() {
		return selectedAdmnNo;
	}
	public void setSelectedAdmnNo(String selectedAdmnNo) {
		this.selectedAdmnNo = selectedAdmnNo;
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
	public String getPatName() {
		return patName;
	}
	public void setPatName(String patName) {
		this.patName = patName;
	}
	public String getDisDateTime() {
		return disDateTime;
	}
	public void setDisDateTime(String disDateTime) {
		this.disDateTime = disDateTime;
	}
	public String getOwnDepartmentUnitCode() {
		return ownDepartmentUnitCode;
	}
	public void setOwnDepartmentUnitCode(String ownDepartmentUnitCode) {
		this.ownDepartmentUnitCode = ownDepartmentUnitCode;
	}
	public String getOwnDepartmentCode() {
		return ownDepartmentCode;
	}
	public void setOwnDepartmentCode(String ownDepartmentCode) {
		this.ownDepartmentCode = ownDepartmentCode;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getHandoverTo() {
		return handoverTo;
	}
	public void setHandoverTo(String handoverTo) {
		this.handoverTo = handoverTo;
	}
	
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
		super.reset(mapping, request);
		this.departmentUnitCode="-1";
		this.wardCode="-1";
	}
	public String getSelectedPatient() {
		return selectedPatient;
	}
	public void setSelectedPatient(String selectedPatient) {
		this.selectedPatient = selectedPatient;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getCaseSheetId() {
		return caseSheetId;
	}
	public void setCaseSheetId(String caseSheetId) {
		this.caseSheetId = caseSheetId;
	}
	public String getCaseSheetType() {
		return caseSheetType;
	}
	public void setCaseSheetType(String caseSheetType) {
		this.caseSheetType = caseSheetType;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
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
	public String getCheckedItem() {
		return checkedItem;
	}
	public void setCheckedItem(String checkedItem) {
		this.checkedItem = checkedItem;
	}
	public String getCheckedChecklist() {
		return checkedChecklist;
	}
	public void setCheckedChecklist(String checkedChecklist) {
		this.checkedChecklist = checkedChecklist;
	}
	public String getDelayReason() {
		return delayReason;
	}
	public void setDelayReason(String delayReason) {
		this.delayReason = delayReason;
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
