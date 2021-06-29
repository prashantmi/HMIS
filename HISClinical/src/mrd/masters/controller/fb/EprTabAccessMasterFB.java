package mrd.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class EprTabAccessMasterFB extends ActionForm
{
	private String hmode;
	private String departmentUnitCode;
	private String toDepartmentUnitCode;
	private String policyType;
	private String accessType[];
	private String userLevel[];
	private String tabId[];
	private String tabIdAccess;
	private String fetchedList;
	private String userId[];
	private String selectedUserId;
	private String selectedtabId;
	private String selectedUsers;
	private String selectedIndex;
	private String hiddenUserLevel[];
	private String selectAllUser;
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.hmode="";
		this.departmentUnitCode="-1";
		this.toDepartmentUnitCode="-1";
		this.policyType="-1";
		//this.accessType=new String[]{};
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}
	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
	}
	public String getPolicyType() {
		return policyType;
	}
	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}
	public String[] getAccessType() {
		return accessType;
	}
	public void setAccessType(String[] accessType) {
		this.accessType = accessType;
	}
	public String[] getTabId() {
		return tabId;
	}
	public void setTabId(String[] tabId) {
		this.tabId = tabId;
	}
	public String getTabIdAccess() {
		return tabIdAccess;
	}
	public void setTabIdAccess(String tabIdAccess) {
		this.tabIdAccess = tabIdAccess;
	}
	public String getToDepartmentUnitCode() {
		return toDepartmentUnitCode;
	}
	public void setToDepartmentUnitCode(String toDepartmentUnitCode) {
		this.toDepartmentUnitCode = toDepartmentUnitCode;
	}
	public String[] getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(String[] userLevel) {
		this.userLevel = userLevel;
	}
	public String getFetchedList() {
		return fetchedList;
	}
	public void setFetchedList(String fetchedList) {
		this.fetchedList = fetchedList;
	}
	public String []getUserId() {
		return userId;
	}
	public void setUserId(String []userId) {
		this.userId = userId;
	}
	public String getSelectedUserId() {
		return selectedUserId;
	}
	public void setSelectedUserId(String selectedUserId) {
		this.selectedUserId = selectedUserId;
	}
	public String getSelectedtabId() {
		return selectedtabId;
	}
	public void setSelectedtabId(String selectedtabId) {
		this.selectedtabId = selectedtabId;
	}
	public String getSelectedUsers() {
		return selectedUsers;
	}
	public void setSelectedUsers(String selectedUsers) {
		this.selectedUsers = selectedUsers;
	}
	public String getSelectedIndex() {
		return selectedIndex;
	}
	public void setSelectedIndex(String selectedIndex) {
		this.selectedIndex = selectedIndex;
	}
	public String[] getHiddenUserLevel() {
		return hiddenUserLevel;
	}
	public void setHiddenUserLevel(String[] hiddenUserLevel) {
		this.hiddenUserLevel = hiddenUserLevel;
	}
	public String getSelectAllUser() {
		return selectAllUser;
	}
	public void setSelectAllUser(String selectAllUser) {
		this.selectAllUser = selectAllUser;
	}
	
	
}
