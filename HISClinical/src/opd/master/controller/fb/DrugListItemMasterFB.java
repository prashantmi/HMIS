package opd.master.controller.fb;

import org.apache.struts.action.ActionForm;

public class DrugListItemMasterFB extends ActionForm
{
	private String hmode;
	private String itemId;
	private String doseId;
	private String frequencyId;
	private String days;
	private String isEmptyStomatch;
	private String deleteIndex;
	private String drugListNameId;
	private String remark;
	private String chk[];
	private String drugListName;
	private String statusFlag;
	private String length;
	
	
	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getStatusFlag() {
		return statusFlag;
	}

	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
	}

	public String getDrugListName() {
		return drugListName;
	}

	public void setDrugListName(String drugListName) {
		this.drugListName = drugListName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDrugListNameId() {
		return drugListNameId;
	}

	public void setDrugListNameId(String drugListNameId) {
		this.drugListNameId = drugListNameId;
	}

	public String getDeleteIndex() {
		return deleteIndex;
	}

	public void setDeleteIndex(String deleteIndex) {
		this.deleteIndex = deleteIndex;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getDoseId() {
		return doseId;
	}

	public void setDoseId(String doseId) {
		this.doseId = doseId;
	}

	public String getFrequencyId() {
		return frequencyId;
	}

	public void setFrequencyId(String frequencyId) {
		this.frequencyId = frequencyId;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getIsEmptyStomatch() {
		return isEmptyStomatch;
	}

	public void setIsEmptyStomatch(String isEmptyStomatch) {
		this.isEmptyStomatch = isEmptyStomatch;
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
}
