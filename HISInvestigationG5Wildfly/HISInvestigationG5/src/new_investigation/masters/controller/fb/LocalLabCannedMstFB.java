package new_investigation.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class LocalLabCannedMstFB extends ActionForm
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
	private String laboratorycannedCode;
	private String count;
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{

		
		this.labCode="-1";
		
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

	public String getCannedCode() {
		return cannedCode;
	}

	public void setCannedCode(String cannedCode) {
		this.cannedCode = cannedCode;
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

	public String getSelectedChk() {
		return selectedChk;
	}

	public void setSelectedChk(String selectedChk) {
		this.selectedChk = selectedChk;
	}

	public String getTemplab() {
		return templab;
	}

	public void setTemplab(String templab) {
		this.templab = templab;
	}

	public String getLaboratorycannedCode() {
		return laboratorycannedCode;
	}

	public void setLaboratorycannedCode(String laboratorycannedCode) {
		this.laboratorycannedCode = laboratorycannedCode;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
	
	
	}
