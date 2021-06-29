package mrd.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ProcessWiseDesigMappingFB extends ActionForm
{
	private String hmode;
	private String chk;
	private String isActive;
	private String processType;
	//private String designationId;
	private String remarks;
	private String entryDate;
	private String isValid;
	private String maxDays;
	private String designationId[];
	private String selectedDesignationId[];
	private String fetchedList;
	
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.processType="";
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


	public String getProcessType() {
		return processType;
	}


	public void setProcessType(String processType) {
		this.processType = processType;
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


	public String[] getDesignationId() {
		return designationId;
	}


	public void setDesignationId(String[] designationId) {
		this.designationId = designationId;
	}


	public String[] getSelectedDesignationId() {
		return selectedDesignationId;
	}


	public void setSelectedDesignationId(String[] selectedDesignationId) {
		this.selectedDesignationId = selectedDesignationId;
	}


	public String getFetchedList() {
		return fetchedList;
	}


	public void setFetchedList(String fetchedList) {
		this.fetchedList = fetchedList;
	}

	
		
}
