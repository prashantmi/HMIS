package new_investigation.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class MandatoryMstFB extends ActionForm
{


	private String hmode;
	private String chk[];
	private String mandatoryName;
	private String remarks;
	private String mandatoryCode;
	private String isActive;
	private String mandatoryType;
	private String selectedChk;

	//	private String previousSampleName;

	public String getMandatoryType() {
		return mandatoryType;
	}



	public void setMandatoryType(String mandatoryType) {
		this.mandatoryType = mandatoryType;
	}



	public String getMandatoryName() {
		return mandatoryName;
	}



	public void setMandatoryName(String mandatoryName) {
		this.mandatoryName = mandatoryName;
	}



	public String getMandatoryCode() {
		return mandatoryCode;
	}

	public void setMandatoryCode(String mandatoryCode) {
		this.mandatoryCode = mandatoryCode;
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

	public void reset(ActionMapping mapping, HttpServletRequest request)
	{

		this.mandatoryName="";
		this.mandatoryType="1";
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

	public String getSelectedChk() {
		return selectedChk;
	}

	public void setSelectedChk(String selectedChk) {
		this.selectedChk = selectedChk;
	}

}
