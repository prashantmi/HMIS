package opd.master.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ProfileTypeTabMappingFB extends ActionForm
{
	private String hmode;
	private String chk;
	private String isActive;
	private String profileType;
	//private String designationId;
	private String remarks;
	private String entryDate;
	private String isValid;
	private String maxDays;
	private String deskMenuId[];
	private String selectedDeskMenuId[];
	private String fetchedList;
	
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.profileType="";
		this.fetchedList="";
	
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


	public String getIsActive() {
		return isActive;
	}


	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
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


	public String getMaxDays() {
		return maxDays;
	}


	public void setMaxDays(String maxDays) {
		this.maxDays = maxDays;
	}


	public String getProfileType() {
		return profileType;
	}

	public void setProfileType(String profileType) {
		this.profileType = profileType;
	}

	public String[] getDeskMenuId() {
		return deskMenuId;
	}

	public void setDeskMenuId(String[] deskMenuId) {
		this.deskMenuId = deskMenuId;
	}

	public String[] getSelectedDeskMenuId() {
		return selectedDeskMenuId;
	}

	public void setSelectedDeskMenuId(String[] selectedDeskMenuId) {
		this.selectedDeskMenuId = selectedDeskMenuId;
	}

	public String getFetchedList() {
		return fetchedList;
	}


	public void setFetchedList(String fetchedList) {
		this.fetchedList = fetchedList;
	}

	
		
}
