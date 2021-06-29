package opd.master.controller.fb;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

public class ChartUnitMappingMstFB extends ActionForm{
	
	private String hmode;
	private String tempMode;
	private String deptUnitName;
	private String deptCode;
	private ArrayList mainUnitsList;
	private ArrayList unitsList;
	private String[] selectedUnit;
	private ArrayList chartListCodeList;
	private String[] selectedChartListCode;
	private String defaultChartListCode;
	private String chk;
	private String controls[];
	private String isDefault;
	
	public ChartUnitMappingMstFB()
	{
		this.controls = new String[1];
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getTempMode() {
		return tempMode;
	}
	public void setTempMode(String tempMode) {
		this.tempMode = tempMode;
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
	public ArrayList getChartListCodeList() {
		return chartListCodeList;
	}
	public void setChartListCodeList(ArrayList chartListCodeList) {
		this.chartListCodeList = chartListCodeList;
	}
	public String[] getSelectedChartListCode() {
		return selectedChartListCode;
	}
	public void setSelectedChartListCode(String[] selectedChartListCode) {
		this.selectedChartListCode = selectedChartListCode;
	}
	public String getDefaultChartListCode() {
		return defaultChartListCode;
	}
	public void setDefaultChartListCode(String defaultChartListCode) {
		this.defaultChartListCode = defaultChartListCode;
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
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	
}
