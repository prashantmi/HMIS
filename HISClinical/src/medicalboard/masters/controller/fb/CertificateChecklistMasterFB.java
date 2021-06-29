package medicalboard.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class CertificateChecklistMasterFB extends ActionForm
{
	private String hmode;
	private String mode;
	private String chk[];
	private String isValid;
	private String controls[]=new String[1];
	private String checklistID;
	private String selectedChecklistID[];
	private String certificateTypeID;
	private String certificateType;
	private String isCompulsory;
	private String serialNo;	
	private String checklistToRemove;	
	private String []existingIsCompulsory;	
	private int noOfChecklistAdded;	
	
	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String[] getChk() {
		return chk;
	}

	public void setChk(String[] chk) {
		this.chk = chk;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String[] getControls() {
		return controls;
	}

	public void setControls(String[] controls) {
		this.controls = controls;
	}

	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		super.reset(mapping, request);
		//this.isCompulsory="-1";
		//this.certificateTypeID="";
		this.noOfChecklistAdded=0;
				
	}

	public String getChecklistID() {
		return checklistID;
	}

	public void setChecklistID(String checklistID) {
		this.checklistID = checklistID;
	}

	public String[] getSelectedChecklistID() {
		return selectedChecklistID;
	}

	public void setSelectedChecklistID(String[] selectedChecklistID) {
		this.selectedChecklistID = selectedChecklistID;
	}

	public String getCertificateTypeID() {
		return certificateTypeID;
	}

	public void setCertificateTypeID(String certificateTypeID) {
		this.certificateTypeID = certificateTypeID;
	}

	public String  getIsCompulsory() {
		return isCompulsory;
	}

	public void setIsCompulsory(String isCompulsory) {
		this.isCompulsory = isCompulsory;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}

	public String getChecklistToRemove() {
		return checklistToRemove;
	}

	public void setChecklistToRemove(String checklistToRemove) {
		this.checklistToRemove = checklistToRemove;
	}

	public int getNoOfChecklistAdded() {
		return noOfChecklistAdded;
	}

	public void setNoOfChecklistAdded(int noOfChecklistAdded) {
		this.noOfChecklistAdded = noOfChecklistAdded;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String[] getExistingIsCompulsory() {
		return existingIsCompulsory;
	}

	public void setExistingIsCompulsory(String[] existingIsCompulsory) {
		this.existingIsCompulsory = existingIsCompulsory;
	}
}