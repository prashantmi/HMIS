package opd.master.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ProfileRestrictedCategoryMasterFB extends ActionForm
{

	private String []patientCategoryCode;
	private String []selectedPatientCategoryCode;
	private String profileType;
	private String seatID;
	private String entryDate;
	private String isValid;
	private String hmode;
	private String serialNo;
	private String isActive;
	private String lastModifiedDate;
	private String lastModifiedSeatId;
	private String chk;
	private String patientCategoryName;
	private String fetchedList;
	
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.profileType="-1";
	}

	public String[] getPatientCategoryCode() {
		return patientCategoryCode;
	}
	public void setPatientCategoryCode(String[] patientCategoryCode) {
		this.patientCategoryCode = patientCategoryCode;
	}
	public String[] getSelectedPatientCategoryCode() {
		return selectedPatientCategoryCode;
	}
	public void setSelectedPatientCategoryCode(String[] selectedPatientCategoryCode) {
		this.selectedPatientCategoryCode = selectedPatientCategoryCode;
	}
	public String getProfileType() {
		return profileType;
	}
	public void setProfileType(String profileType) {
		this.profileType = profileType;
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
	
}
