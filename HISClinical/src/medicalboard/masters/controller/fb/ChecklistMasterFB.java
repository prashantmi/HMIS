package medicalboard.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ChecklistMasterFB extends ActionForm
{
	private String hmode;
	private String chk[];
	private String isValid;
	private String controls[];
	private String checklistID;
	private String remarks;
	private String isCompulsory;
	private String serialNo;
	private String checklist;
		
	
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

	public String getChecklistID() {
		return checklistID;
	}

	public void setChecklistID(String checklistID) {
		this.checklistID = checklistID;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getIsCompulsory() {
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

	public String getChecklist() {
		return checklist;
	}

	public void setChecklist(String checklist) {
		this.checklist = checklist;
	}


	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		super.reset(mapping, request);
		this.controls=new String[2];
		this.checklist="";
		this.isCompulsory="-1";
		this.checklistID="";
		this.remarks="";
		
	}
}