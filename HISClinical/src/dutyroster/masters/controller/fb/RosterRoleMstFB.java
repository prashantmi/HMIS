package dutyroster.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class RosterRoleMstFB extends ActionForm
{
	private String hmode;
	private String chk;
	private String serialNo;
	
	private String rosterTypeId;
	private String dutyRole[];
	private String selectedDutyRole[];
	private String fetchedList;
	
	
		
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.rosterTypeId="";
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String getChk() {
		return chk;
	}

	public void setChk(String chk) {
		this.chk = chk;
	}


	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getRosterTypeId() {
		return rosterTypeId;
	}

	public void setRosterTypeId(String rosterTypeId) {
		this.rosterTypeId = rosterTypeId;
	}

	public String[] getDutyRole() {
		return dutyRole;
	}

	public void setDutyRole(String[] dutyRole) {
		this.dutyRole = dutyRole;
	}

	public String getFetchedList() {
		return fetchedList;
	}

	public void setFetchedList(String fetchedList) {
		this.fetchedList = fetchedList;
	}

	public String[] getSelectedDutyRole() {
		return selectedDutyRole;
	}

	public void setSelectedDutyRole(String[] selectedDutyRole) {
		this.selectedDutyRole = selectedDutyRole;
	}

}
