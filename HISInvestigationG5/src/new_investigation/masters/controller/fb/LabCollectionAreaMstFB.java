package new_investigation.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class LabCollectionAreaMstFB extends ActionForm
{
	
	
	private String selectedLab;
	private String templab;
	
	private	String[] unmappedList;
	private	String[] mappedList;

	private String hmode;
	private String chk[];
	private String collectionareaCode;
	private String remarks;
	private String labCode;
	private String hospitalCode;
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

		
		this.labCode="-1";
		this.remarks="";
		this.collectionareaCode="-1";
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



	public String getCollectionareaCode() {
		return collectionareaCode;
	}



	public void setCollectionareaCode(String collectionareaCode) {
		this.collectionareaCode = collectionareaCode;
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



	public String getSelectedLab() {
		return selectedLab;
	}



	public void setSelectedLab(String selectedLab) {
		this.selectedLab = selectedLab;
	}



	public String getTemplab() {
		return templab;
	}



	public void setTemplab(String templab) {
		this.templab = templab;
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
