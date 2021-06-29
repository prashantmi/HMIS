package new_investigation.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class LabCannedMstFB extends ActionForm
{
	private	String[] unmappedList;
	private	String[] mappedList;
	private String hmode;
	private String chk[];
	private String cannedCode;
	private String labCode;
	private String hospitalCode;
	private String isActive;
	private String selectedChk;
	private String templab;
	private String cannedName;
	private String localLaboratoryName;
	private String globalLaboratoryName;
	private String count;
	private String userCannedCode[];
	private String numberOfRow;
	
	public String getCannedName() {
		return cannedName;
	}


	public void setCannedName(String cannedName) {
		this.cannedName = cannedName;
	}


	public String getCannedCode() {
		return cannedCode;
	}


	public void setCannedCode(String cannedCode) {
		this.cannedCode = cannedCode;
	}


	public String getLocalLaboratoryName() {
		return localLaboratoryName;
	}


	public void setLocalLaboratoryName(String localLaboratoryName) {
		this.localLaboratoryName = localLaboratoryName;
	}


	public String getGlobalLaboratoryName() {
		return globalLaboratoryName;
	}


	public void setGlobalLaboratoryName(String globalLaboratoryName) {
		this.globalLaboratoryName = globalLaboratoryName;
	}



	public String getTemplab() {
		return templab;
	}


	public void setTemplab(String templab) {
		this.templab = templab;
	}


	public String getSelectedChk() {
		return selectedChk;
	}


	public void setSelectedChk(String selectedChk) {
		this.selectedChk = selectedChk;
	}


	public void reset(ActionMapping mapping, HttpServletRequest request)
	{

		
		this.labCode="-1";
		this.localLaboratoryName="";
		
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

	public String getLabCode() {
		return labCode;
	}


	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}


	public String getHospitalCode() {
		return hospitalCode;
	}


	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}


	public String getIsActive() {
		return isActive;
	}


	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}


	public String getCount() {
		return count;
	}


	public void setCount(String count) {
		this.count = count;
	}




	public String getNumberOfRow() {
		return numberOfRow;
	}


	public void setNumberOfRow(String numberOfRow) {
		this.numberOfRow = numberOfRow;
	}


	public String[] getUserCannedCode() {
		return userCannedCode;
	}


	public void setUserCannedCode(String[] userCannedCode) {
		this.userCannedCode = userCannedCode;
	}
	
	
	}
