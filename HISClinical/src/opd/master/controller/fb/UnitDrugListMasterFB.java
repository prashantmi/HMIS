package opd.master.controller.fb;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

public class UnitDrugListMasterFB extends ActionForm 
{
	private String hmode;
	private String tempMode;
	private String deptUnitName;
	private String deptCode;
	private ArrayList mainUnitsList;
	private ArrayList unitsList;
	private String[] selectedUnit;
	private ArrayList drugListCodeList;
	private String[] selectedDrugListCode;
	private String defaultDrugListCode;
	private String chk;
	private String controls[];
	
	
	public UnitDrugListMasterFB()
	{
		this.controls = new String[4];
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

	public String getDefaultDrugListCode() {
		return defaultDrugListCode;
	}

	public void setDefaultDrugListCode(String defaultDrugListCode) {
		this.defaultDrugListCode = defaultDrugListCode;
	}

	public String getTempMode() {
		return tempMode;
	}

	public void setTempMode(String tempMode) {
		this.tempMode = tempMode;
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String getDeptUnitName() {
		return deptUnitName;
	}

	public void setDeptUnitName(String deptUnitName) {
		this.deptUnitName = deptUnitName;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public ArrayList getMainUnitsList() {
		return mainUnitsList;
	}

	public void setMainUnitsList(ArrayList mainUnitsList) {
		this.mainUnitsList = mainUnitsList;
	}

	public ArrayList getUnitsList() {
		return unitsList;
	}

	public void setUnitsList(ArrayList unitsList) {
		this.unitsList = unitsList;
	}

	public String[] getSelectedUnit() {
		return selectedUnit;
	}

	public void setSelectedUnit(String[] selectedUnit) {
		this.selectedUnit = selectedUnit;
	}

	public ArrayList getDrugListCodeList() {
		return drugListCodeList;
	}

	public void setDrugListCodeList(ArrayList drugListCodeList) {
		this.drugListCodeList = drugListCodeList;
	}

	public String[] getSelectedDrugListCode() {
		return selectedDrugListCode;
	}

	public void setSelectedDrugListCode(String[] selectedDrugListCode) {
		this.selectedDrugListCode = selectedDrugListCode;
	}
}
