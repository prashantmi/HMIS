/**
 * 
 */
package opd.master.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author ashas
 *
 */
public class OpdIcdMappingMasterFB extends ActionForm{
	
	private String mappingType;
	private String mappingID;
	private String icdDiseaseCode;
	private String isValid;
	private String chk;
	private String controls[];
	private String entryDate;
	private String seatId;
	private String hospitalCode;
	private String mappingTypeDesc;
	private String hmode;
	private String hospitalDisease;
	private String chronicDisease;
	private String icdGroupCode;
	private String icdGroup;
	private String icdSubgroupCode;
	private String icdSubgroup;
	private String diseaseCode;
	private String diseaseList;
	private String []selectedDisease;
	private String disease;
	private String deleteIndex;
	
	
	
	
	public OpdIcdMappingMasterFB()
	{
	
	this.controls = new String[1];
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) 
	{
		this.chronicDisease="";
		this.hospitalDisease="";
		this.diseaseCode="";
		this.icdGroupCode="";
		this.icdSubgroupCode="";
		this.disease="";
		this.diseaseList="";
		
	}
	
	public String getMappingType() {
		return mappingType;
	}
	public void setMappingType(String mappingType) {
		this.mappingType = mappingType;
	}
	public String getMappingID() {
		return mappingID;
	}
	public void setMappingID(String mappingID) {
		this.mappingID = mappingID;
	}
	public String getIcdDiseaseCode() {
		return icdDiseaseCode;
	}
	public void setIcdDiseaseCode(String icdDiseaseCode) {
		this.icdDiseaseCode = icdDiseaseCode;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getChk() {
		return chk;
	}
	public void setChk(String chk) {
		this.chk = chk;
	}
	public String[] getControls() {
		return controls;
	}
	public void setControls(String[] controls) {
		this.controls = controls;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getMappingTypeDesc() {
		return mappingTypeDesc;
	}
	public void setMappingTypeDesc(String mappingTypeDesc) {
		this.mappingTypeDesc = mappingTypeDesc;
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String getHospitalDisease() {
		return hospitalDisease;
	}

	public void setHospitalDisease(String hospitalDisease) {
		this.hospitalDisease = hospitalDisease;
	}

	public String getChronicDisease() {
		return chronicDisease;
	}

	public void setChronicDisease(String chronicDisease) {
		this.chronicDisease = chronicDisease;
	}

	public String getIcdGroupCode() {
		return icdGroupCode;
	}

	public void setIcdGroupCode(String icdGroupCode) {
		this.icdGroupCode = icdGroupCode;
	}

	public String getIcdGroup() {
		return icdGroup;
	}

	public void setIcdGroup(String icdGroup) {
		this.icdGroup = icdGroup;
	}

	public String getIcdSubgroupCode() {
		return icdSubgroupCode;
	}

	public void setIcdSubgroupCode(String icdSubgroupCode) {
		this.icdSubgroupCode = icdSubgroupCode;
	}

	public String getIcdSubgroup() {
		return icdSubgroup;
	}

	public void setIcdSubgroup(String icdSubgroup) {
		this.icdSubgroup = icdSubgroup;
	}

	public String getDiseaseCode() {
		return diseaseCode;
	}

	public void setDiseaseCode(String diseaseCode) {
		this.diseaseCode = diseaseCode;
	}

	public String getDiseaseList() {
		return diseaseList;
	}

	public void setDiseaseList(String diseaseList) {
		this.diseaseList = diseaseList;
	}

	public String[] getSelectedDisease() {
		return selectedDisease;
	}

	public void setSelectedDisease(String[] selectedDisease) {
		this.selectedDisease = selectedDisease;
	}

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public String getDeleteIndex() {
		return deleteIndex;
	}

	public void setDeleteIndex(String deleteIndex) {
		this.deleteIndex = deleteIndex;
	}
	
	
	
	
	

}
