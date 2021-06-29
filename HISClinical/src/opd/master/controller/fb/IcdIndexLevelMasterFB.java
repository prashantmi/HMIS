package opd.master.controller.fb;

import javax.servlet.http.HttpServletRequest;


import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *  Developer : Vivek Aggarwal
 *  Created Date : 1-Feb-2011
 *  Process Name : Icd Index Level Master
 *  Last Modified Date : 28-Mar-2011  
 */
public class IcdIndexLevelMasterFB extends ActionForm
{
	
	private static final long serialVersionUID = 1L;
	
	private String hmode ;
	private String chk ;
	
	private String isActive ;
	private String controls[] ;
	
	private String diseaseCodeChk;
	
	private String icdGroup;
	private String icdGroupCode;
	
	private String icdSubgroup;
	private String icdSubgroupCode;
	
	private String dualDiseaseCodeChk;
	private String dualIcdGroup;
	private String dualIcdGroupCode;
	
	private String dualIcdSubGroupCode;

	private String indexCode;
	private String indexModifierID;
	private String slNo;
	private String parentIndexModifierCode;
	private String modifier;
	private String modifierLevel;
	private String diseaseCode;
	private String seatId;
	private String isWith;
	private String entryDate;
	private String isValid;
	private String dualDiseaseCode;
	private String seeTerm;
	private String seeTermCode;
	
	private String seeIndexModifierId;
	private String seeAlsoTerm;
	private String seeAlsoTermCode;
	private String seeAlsoIndexModifierId;
	private String remark;
	private String hospitalCode;     
 
    private String parentModifier;
	
    private String lstModDate ;          
    private String lstModSeatId ;
    
    private String pageFlag;
    
    public IcdIndexLevelMasterFB()
    {
    	this.controls = new String[1];
    }
    
    /*
     *  Getters and Setters of above attributes
     */
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getChk() {
		return chk;
	}
	public void setChk(String chk) {
		this.chk = chk;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String[] getControls() {
		return controls;
	}
	public void setControls(String[] controls) {
		this.controls = controls;
	}
	public String getDiseaseCodeChk() {
		return diseaseCodeChk;
	}
	public void setDiseaseCodeChk(String diseaseCodeChk) {
		this.diseaseCodeChk = diseaseCodeChk;
	}
	public String getIcdGroup() {
		return icdGroup;
	}
	public void setIcdGroup(String icdGroup) {
		this.icdGroup = icdGroup;
	}
	public String getIcdGroupCode() {
		return icdGroupCode;
	}
	public void setIcdGroupCode(String icdGroupCode) {
		this.icdGroupCode = icdGroupCode;
	}
	public String getIcdSubgroup() {
		return icdSubgroup;
	}
	public void setIcdSubgroup(String icdSubgroup) {
		this.icdSubgroup = icdSubgroup;
	}
	public String getIcdSubgroupCode() {
		return icdSubgroupCode;
	}
	public void setIcdSubgroupCode(String icdSubgroupCode) {
		this.icdSubgroupCode = icdSubgroupCode;
	}
	public String getDualDiseaseCodeChk() {
		return dualDiseaseCodeChk;
	}
	public void setDualDiseaseCodeChk(String dualDiseaseCodeChk) {
		this.dualDiseaseCodeChk = dualDiseaseCodeChk;
	}
	public String getDualIcdGroup() {
		return dualIcdGroup;
	}
	public void setDualIcdGroup(String dualIcdGroup) {
		this.dualIcdGroup = dualIcdGroup;
	}
	public String getDualIcdGroupCode() {
		return dualIcdGroupCode;
	}
	public void setDualIcdGroupCode(String dualIcdGroupCode) {
		this.dualIcdGroupCode = dualIcdGroupCode;
	}
	public String getDualIcdSubGroupCode() {
		return dualIcdSubGroupCode;
	}
	public void setDualIcdSubGroupCode(String dualIcdSubGroupCode) {
		this.dualIcdSubGroupCode = dualIcdSubGroupCode;
	}
	public String getIndexCode() {
		return indexCode;
	}
	public void setIndexCode(String indexCode) {
		this.indexCode = indexCode;
	}
	public String getIndexModifierID() {
		return indexModifierID;
	}
	public void setIndexModifierID(String indexModifierID) {
		this.indexModifierID = indexModifierID;
	}
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getParentIndexModifierCode() {
		return parentIndexModifierCode;
	}
	public void setParentIndexModifierCode(String parentIndexModifierCode) {
		this.parentIndexModifierCode = parentIndexModifierCode;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public String getModifierLevel() {
		return modifierLevel;
	}
	public void setModifierLevel(String modifierLevel) {
		this.modifierLevel = modifierLevel;
	}
	public String getDiseaseCode() {
		return diseaseCode;
	}
	public void setDiseaseCode(String diseaseCode) {
		this.diseaseCode = diseaseCode;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getIsWith() {
		return isWith;
	}
	public void setIsWith(String isWith) {
		this.isWith = isWith;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getDualDiseaseCode() {
		return dualDiseaseCode;
	}
	public void setDualDiseaseCode(String dualDiseaseCode) {
		this.dualDiseaseCode = dualDiseaseCode;
	}
	public String getSeeTerm() {
		return seeTerm;
	}
	public void setSeeTerm(String seeTerm) {
		this.seeTerm = seeTerm;
	}
	public String getSeeTermCode() {
		return seeTermCode;
	}
	public void setSeeTermCode(String seeTermCode) {
		this.seeTermCode = seeTermCode;
	}
	public String getSeeIndexModifierId() {
		return seeIndexModifierId;
	}
	public void setSeeIndexModifierId(String seeIndexModifierId) {
		this.seeIndexModifierId = seeIndexModifierId;
	}
	public String getSeeAlsoTerm() {
		return seeAlsoTerm;
	}
	public void setSeeAlsoTerm(String seeAlsoTerm) {
		this.seeAlsoTerm = seeAlsoTerm;
	}
	public String getSeeAlsoTermCode() {
		return seeAlsoTermCode;
	}
	public void setSeeAlsoTermCode(String seeAlsoTermCode) {
		this.seeAlsoTermCode = seeAlsoTermCode;
	}
	public String getSeeAlsoIndexModifierId() {
		return seeAlsoIndexModifierId;
	}
	public void setSeeAlsoIndexModifierId(String seeAlsoIndexModifierId) {
		this.seeAlsoIndexModifierId = seeAlsoIndexModifierId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getParentModifier() {
		return parentModifier;
	}
	public void setParentModifier(String parentModifier) {
		this.parentModifier = parentModifier;
	}
	public String getLstModDate() {
		return lstModDate;
	}
	public void setLstModDate(String lstModDate) {
		this.lstModDate = lstModDate;
	}
	public String getLstModSeatId() {
		return lstModSeatId;
	}
	public void setLstModSeatId(String lstModSeatId) {
		this.lstModSeatId = lstModSeatId;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getPageFlag() {
		return pageFlag;
	}

	public void setPageFlag(String pageFlag) {
		this.pageFlag = pageFlag;
	}

	/*
	 * Reset the Add Page Components
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{		
			this.indexCode=getControls()[0];
			this.modifierLevel ="";
			this.modifier="";
			this.isWith="0";
			this.parentIndexModifierCode="";
			
			this.diseaseCodeChk="";
			this.icdGroupCode="";
			this.icdSubgroupCode="";
			
			this.dualDiseaseCodeChk="";
			
			this.diseaseCode="";
			this.dualDiseaseCode="";
			this.remark="";
	}

	
}
