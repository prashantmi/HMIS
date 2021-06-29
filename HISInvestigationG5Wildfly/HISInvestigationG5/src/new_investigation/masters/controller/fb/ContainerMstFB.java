package new_investigation.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ContainerMstFB extends ActionForm
{
	
	
	private String hmode;
	private String chk[];

	private String containerName;
	private String containerCode;
	private String containerVolume;
	private String remarks;
	private String uomCode;
	private String selectedChk;
	private String isActive;

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
		
		 this.containerName="";
		 this.containerVolume="";
		 this.uomCode="-1";
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
	
	public String getContainerName() {
		return containerName;
	}



	public void setContainerName(String containerName) {
		this.containerName = containerName;
	}



	public String getContainerCode() {
		return containerCode;
	}



	public void setContainerCode(String containerCode) {
		this.containerCode = containerCode;
	}



	public String getContainerVolume() {
		return containerVolume;
	}



	public void setContainerVolume(String containerVolume) {
		this.containerVolume = containerVolume;
	}



	public String getUomCode() {
		return uomCode;
	}



	public void setUomCode(String uomCode) {
		this.uomCode = uomCode;
	}



	public String getSelectedChk() {
		return selectedChk;
	}



	public void setSelectedChk(String selectedChk) {
		this.selectedChk = selectedChk;
	}

}
