package new_investigation.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class InvParameterMstFB extends ActionForm
{


	private String hmode;
	private String chk[];
	private String checklistPrevious;
	private String parameterName;
	private String remarks;
	private String parameterCode;
	private String isActive;
	private String selectedChk;


	public String getIsActive() {
		return isActive;
	}



	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}


	public String getParameterCode() {
		return parameterCode;
	}



	public void setParameterCode(String parameterCode) {
		this.parameterCode = parameterCode;
	}



	public String getRemarks() {
		return remarks;
	}



	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}




	public String getParameterName() {
		return parameterName;
	}



	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request)
	{

		this.parameterName="";
		this.remarks="";
	}




	public String getHmode()
	{
		return hmode;
	}
	public void setHmode(String hmode)
	{
		this.hmode = hmode;
	}
	public String[] getChk()
	{
		return chk;
	}
	public void setChk(String[] chk)
	{
		this.chk = chk;
	}
	public String getChecklistPrevious()
	{
		return checklistPrevious;
	}
	public void setChecklistPrevious(String checklistPrevious)
	{
		this.checklistPrevious = checklistPrevious;
	}



	public String getSelectedChk() {
		return selectedChk;
	}



	public void setSelectedChk(String selectedChk) {
		this.selectedChk = selectedChk;
	}

}
