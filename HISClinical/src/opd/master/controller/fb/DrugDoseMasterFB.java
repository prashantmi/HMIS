package opd.master.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DrugDoseMasterFB extends ActionForm
{
	private String hmode;
	private String doseName;
	private String itemType;
	private String itemTypeId;
	private String chk;
	private String doseInstruction;
	private String sereialNo;
	private String index;
	private String modDoseName;
	private String modDoseInstruction;
	private String doseId;
	private String prevDoseName;
	private String prevDoseInstruction;
	private String checkItemTypeId;
	private String doseQty;
	private String isFrequencyBound;
	private String isActive;
	private String isValid;
	private String hospitalCode;
	
	//Added By : Chetan Sharma
	// Date    :22/12/2015
	private String itemTypeName;
	
	public String getItemTypeName() {
		return itemTypeName;
	}

	public void setItemTypeName(String itemTypeName) {
		this.itemTypeName = itemTypeName;
	}

	private String controls[];
	
	public DrugDoseMasterFB()
	{
		this.controls = new String[1];
	}
	
	public String[] getControls() {
		return controls;
	}
	public void setControls(String[] controls) {
		this.controls = controls;
	}
	
	public String getCheckItemTypeId() {
		return checkItemTypeId;
	}
	public void setCheckItemTypeId(String checkItemTypeId) {
		this.checkItemTypeId = checkItemTypeId;
	}
	public String getPrevDoseName() {
		return prevDoseName;
	}
	public void setPrevDoseName(String prevDoseName) {
		this.prevDoseName = prevDoseName;
	}
	public String getPrevDoseInstruction() {
		return prevDoseInstruction;
	}
	public void setPrevDoseInstruction(String prevDoseInstruction) {
		this.prevDoseInstruction = prevDoseInstruction;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.doseName="";
		this.doseInstruction="";
		this.doseQty="";
		this.isFrequencyBound="1";
		//this.itemTypeId="-1";
	}
	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getDoseName() {
		return doseName;
	}

	public void setDoseName(String doseName) {
		this.doseName = doseName;
	}

	

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getItemTypeId() {
		return itemTypeId;
	}

	public void setItemTypeId(String itemTypeId) {
		this.itemTypeId = itemTypeId;
	}

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

	

	public String getDoseInstruction() {
		return doseInstruction;
	}

	public void setDoseInstruction(String doseInstruction) {
		this.doseInstruction = doseInstruction;
	}

	public String getSereialNo() {
		return sereialNo;
	}

	public void setSereialNo(String sereialNo) {
		this.sereialNo = sereialNo;
	}

	public String getModDoseName() {
		return modDoseName;
	}

	public void setModDoseName(String modDoseName) {
		this.modDoseName = modDoseName;
	}

	public String getModDoseInstruction() {
		return modDoseInstruction;
	}

	public void setModDoseInstruction(String modDoseInstruction) {
		this.modDoseInstruction = modDoseInstruction;
	}

	public String getDoseId() {
		return doseId;
	}

	public void setDoseId(String doseId) {
		this.doseId = doseId;
	}

	public String getDoseQty() {
		return doseQty;
	}

	public void setDoseQty(String doseQty) {
		this.doseQty = doseQty;
	}

	public String getIsFrequencyBound() {
		return isFrequencyBound;
	}

	public void setIsFrequencyBound(String isFrequencyBound) {
		this.isFrequencyBound = isFrequencyBound;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	

}
