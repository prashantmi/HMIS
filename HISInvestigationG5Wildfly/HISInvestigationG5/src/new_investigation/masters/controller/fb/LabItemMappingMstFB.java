package new_investigation.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class LabItemMappingMstFB extends ActionForm
{
	
	
	private	String[] unmappedList;
	private	String[] mappedList;
	private String hmode;
	private String chk[];
	private String labCode;
	private String hospitalCode;
	private String selectedChk;

	



	





	public void reset(ActionMapping mapping, HttpServletRequest request)
	{

		
		this.labCode="-1";
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



	

	public String getHospitalCode() {
		return hospitalCode;
	}



	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}



	public String getSelectedChk() {
		return selectedChk;
	}



	public void setSelectedChk(String selectedChk) {
		this.selectedChk = selectedChk;
	}




	public String getLabCode() {
		return labCode;
	}



	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}



	public String[] getUnmappedList() {
		return unmappedList;
	}



	public void setUnmappedList(String[] unmappedList) {
		this.unmappedList = unmappedList;
	}



	public String[] getMappedList() {
		return mappedList;
	}



	public void setMappedList(String[] mappedList) {
		this.mappedList = mappedList;
	}



	

}
