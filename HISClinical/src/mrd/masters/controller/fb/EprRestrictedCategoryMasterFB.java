package mrd.masters.controller.fb;

import org.apache.struts.action.ActionForm;

public class EprRestrictedCategoryMasterFB extends ActionForm
{

	private String []patCatCode;
	private String []selectedPatCatCode;
	private String patientCategoryName;
	private String hmode;
	private String fetchedList;
	
	public String[] getPatCatCode() {
		return patCatCode;
	}
	public void setPatCatCode(String[] patCatCode) {
		this.patCatCode = patCatCode;
	}
	public String[] getSelectedPatCatCode() {
		return selectedPatCatCode;
	}
	public void setSelectedPatCatCode(String[] selectedPatCatCode) {
		this.selectedPatCatCode = selectedPatCatCode;
	}
	public String getPatientCategoryName() {
		return patientCategoryName;
	}
	public void setPatientCategoryName(String patientCategoryName) {
		this.patientCategoryName = patientCategoryName;
	}
	public String getFetchedList() {
		return fetchedList;
	}
	public void setFetchedList(String fetchedList) {
		this.fetchedList = fetchedList;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	
	
	
}
