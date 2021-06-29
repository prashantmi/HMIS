package opd.master.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ProfileGroupDetailFB extends ActionForm  
{
	
	private String profileGroupId;
	private String profileGroupName;
	private String recordMode;
	private String accessRecordId;
	private String seatID;
	private String entryDate;
	private String isValid;
	private String hmode;
	private String serialNo;
	private String isActive;
	private String lastModifiedDate;
	private String lastModifiedSeatId;
	private String chk;
	private String[] selectedUserUnitSpecificType;
	private String unitList;
	private String[] selectedUnits;
	private String searchUserName;
	private String searchEmpId;
	private String searchEmpName;
	private String[] selectedUserIndex;
	private String[] selectedUsers;
	private String searchString;
	private String previousUnitUser;
	private String searchMode;
	private String[] usersList;
	private String tempMode;
	private String deleteIndex;
	private String userPriveledgeFlag;
	
	public void reset(ActionMapping mapping,	ActionForm form, HttpServletRequest request)
	{
		this.profileGroupId="";
		this.selectedUserUnitSpecificType=new String[0];
		this.selectedUnits = new String[0];
		this.selectedUserIndex = new String[0];
		this.selectedUsers = new String[0];
		this.setPreviousUnitUser("");
		this.usersList= new String[0];
		this.searchUserName="";
		this.searchEmpId="";
		this.searchEmpName="";
	}
	public ProfileGroupDetailFB()
	{
		this.selectedUnits = new String[0];
		this.selectedUserIndex = new String[0];
		this.selectedUsers = new String[0];
		this.setPreviousUnitUser("");
		this.usersList= new String[0];
		this.selectedUserUnitSpecificType=new String[0];
	}
	public String getProfileGroupId() {
		return profileGroupId;
	}
	public void setProfileGroupId(String profileGroupId) {
		this.profileGroupId = profileGroupId;
	}
	public String getProfileGroupName() {
		return profileGroupName;
	}
	public void setProfileGroupName(String profileGroupName) {
		this.profileGroupName = profileGroupName;
	}
	public String getRecordMode() {
		return recordMode;
	}
	public void setRecordMode(String recordMode) {
		this.recordMode = recordMode;
	}
	public String getAccessRecordId() {
		return accessRecordId;
	}
	public void setAccessRecordId(String accessRecordId) {
		this.accessRecordId = accessRecordId;
	}
	public String getSeatID() {
		return seatID;
	}
	public void setSeatID(String seatID) {
		this.seatID = seatID;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public String getLastModifiedSeatId() {
		return lastModifiedSeatId;
	}
	public void setLastModifiedSeatId(String lastModifiedSeatId) {
		this.lastModifiedSeatId = lastModifiedSeatId;
	}
	public String getChk() {
		return chk;
	}
	public void setChk(String chk) {
		this.chk = chk;
	}
	
	public String getUnitList() {
		return unitList;
	}
	
	public String getSearchUserName() {
		return searchUserName;
	}
	public void setSearchUserName(String searchUserName) {
		this.searchUserName = searchUserName;
	}
	public String getSearchEmpId() {
		return searchEmpId;
	}
	public void setSearchEmpId(String searchEmpId) {
		this.searchEmpId = searchEmpId;
	}
	public String getSearchEmpName() {
		return searchEmpName;
	}
	public void setSearchEmpName(String searchEmpName) {
		this.searchEmpName = searchEmpName;
	}

	public String[] getSelectedUnits() {
		return selectedUnits;
	}
	public void setSelectedUnits(String[] selectedUnits) {
		this.selectedUnits = selectedUnits;
	}
	public void setUnitList(String unitList) {
		this.unitList = unitList;
	}
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	public String getPreviousUnitUser() {
		return previousUnitUser;
	}
	public void setPreviousUnitUser(String previousUnitUser) {
		this.previousUnitUser = previousUnitUser;
	}
	public String getSearchMode() {
		return searchMode;
	}
	public void setSearchMode(String searchMode) {
		this.searchMode = searchMode;
	}
	public String[] getSelectedUserIndex() {
		return selectedUserIndex;
	}
	public void setSelectedUserIndex(String[] selectedUserIndex) {
		this.selectedUserIndex = selectedUserIndex;
	}
	public String[] getSelectedUsers() {
		return selectedUsers;
	}
	public void setSelectedUsers(String[] selectedUsers) {
		this.selectedUsers = selectedUsers;
	}
	public String[] getUsersList() {
		return usersList;
	}
	public void setUsersList(String[] usersList) {
		this.usersList = usersList;
	}
	public String getTempMode() {
		return tempMode;
	}
	public void setTempMode(String tempMode) {
		this.tempMode = tempMode;
	}
	public String[] getSelectedUserUnitSpecificType() {
		return selectedUserUnitSpecificType;
	}
	public void setSelectedUserUnitSpecificType(
			String[] selectedUserUnitSpecificType) {
		this.selectedUserUnitSpecificType = selectedUserUnitSpecificType;
	}
	public String getDeleteIndex() {
		return deleteIndex;
	}
	public void setDeleteIndex(String deleteIndex) {
		this.deleteIndex = deleteIndex;
	}
	public String getUserPriveledgeFlag() {
		return userPriveledgeFlag;
	}
	public void setUserPriveledgeFlag(String userPriveledgeFlag) {
		this.userPriveledgeFlag = userPriveledgeFlag;
	}

}
