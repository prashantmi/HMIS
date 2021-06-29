package opd.master.controller.fb;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class UnitMacroMasterFB extends ActionForm
{
	private String hmode;
	private String tempMode;
	private String deptCode;
	private ArrayList mainUnitsList;
	private ArrayList unitsList;
	private String[] selectedUnit;
	private String processId;
	private ArrayList macroList;
	private String[] selectedMacroList;
	private ArrayList macroListByProcessType;
	private String chk;
	private String controls[];
	private String deptUnitName;
	
	
	public String getDeptUnitName() {
		return deptUnitName;
	}

	public void setDeptUnitName(String deptUnitName) {
		this.deptUnitName = deptUnitName;
	}

	public UnitMacroMasterFB()
	{
		this.controls = new String[4];
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setDeptCode("-1");
		this.setProcessId("-1");
		
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

	public ArrayList getMacroListByProcessType() {
		return macroListByProcessType;
	}

	public void setMacroListByProcessType(ArrayList macroListByProcessType) {
		this.macroListByProcessType = macroListByProcessType;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
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

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
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

	public ArrayList getMacroList() {
		return macroList;
	}

	public void setMacroList(ArrayList macroList) {
		this.macroList = macroList;
	}

	public String[] getSelectedMacroList() {
		return selectedMacroList;
	}

	public void setSelectedMacroList(String[] selectedMacroList) {
		this.selectedMacroList = selectedMacroList;
	}
}
