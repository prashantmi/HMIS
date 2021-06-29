/*
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: Manisha Gangwar
 ## Module Name					        : MRD
 ## Process/Database Object Name	    : UNIT WISE ESTIMATE PROCEDURE MAPPING MASTER
 ## Purpose						        : 
 ## Date of Creation					: 04-NOV-2015 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 
*/

package mrd.masters.controller.fb;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;
public class UnitWiseEstProcedureMappingMstFB extends ActionForm{
	
	private String hmode;
	private String tempMode;
	private String deptUnitName;
	private String deptCode;
	private ArrayList mainUnitsList;
	private ArrayList unitsList;
	private String[] selectedUnit;
	private ArrayList procedureListCodeList;
	private String[] selectedProcedureListCode;
	private String defaultProcedureListCode;
	private String chk;
	private String controls[];
	private String isDefault;
	
	public UnitWiseEstProcedureMappingMstFB()
	{
		this.controls = new String[1];
	}
	
	public String getHmode() 
	{
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
	public ArrayList getProcedureListCodeList() {
		return procedureListCodeList;
	}
	public void setProcedureListCodeList(ArrayList procedureListCodeList) {
		this.procedureListCodeList = procedureListCodeList;
	}
	public String[] getSelectedProcedureListCode() {
		return selectedProcedureListCode;
	}
	public void setSelectedProcedureListCode(String[] selectedProcedureListCode) {
		this.selectedProcedureListCode = selectedProcedureListCode;
	}
	public String getDefaultProcedureListCode() {
		return defaultProcedureListCode;
	}
	public void setDefaultProcedureListCode(String defaultProcedureListCode) {
		this.defaultProcedureListCode = defaultProcedureListCode;
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

