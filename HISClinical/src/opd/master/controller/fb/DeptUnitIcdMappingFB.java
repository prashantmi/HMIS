package opd.master.controller.fb;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DeptUnitIcdMappingFB extends ActionForm
{
	private String unit;
	private String deptName;
	private String deptCode;
	private String []mainUnitsList;
	private String []unitsList;
	private String []selectedUnit;
	private String hmode;
	private String deptUnitName;
	private String icdGroupCode;
	private String icdGroup;
	private String icdSubgroupCode;
	private String icdSubgroup;
	private String diseaseCode;
	private String diseaseList;
	private String []selectedDisease;
	private String disease;
	private String deptUnitCode;
	private String chk;
	private String deleteIndex;
	

	public String getChk() {
		return chk;
	}

	public void setChk(String chk) {
		this.chk = chk;
	}

	public String getDeptUnitCode() {
		return deptUnitCode;
	}

	public void setDeptUnitCode(String deptUnitCode) {
		this.deptUnitCode = deptUnitCode;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) 
	{
		this.deptName="";
		this.selectedUnit=null;
	}
	
	public String getDiseaseCode() {
		return diseaseCode;
	}
	public void setDiseaseCode(String diseaseCode) {
		this.diseaseCode = diseaseCode;
	}
	public String getDisease() {
		return disease;
	}
	public void setDisease(String disease) {
		this.disease = disease;
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
	public String getDeptUnitName() {
		return deptUnitName;
	}
	public void setDeptUnitName(String deptUnitName) {
		this.deptUnitName = deptUnitName;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	
	public String[] getMainUnitsList() {
		return mainUnitsList;
	}
	public void setMainUnitsList(String[] mainUnitsList) {
		this.mainUnitsList = mainUnitsList;
	}
	public String[] getUnitsList() {
		return unitsList;
	}
	public void setUnitsList(String[] unitsList) {
		this.unitsList = unitsList;
	}
	
	public String[] getSelectedUnit() {
		return selectedUnit;
	}
	public void setSelectedUnit(String[] selectedUnit) {
		this.selectedUnit = selectedUnit;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
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

	public String getDeleteIndex() {
		return deleteIndex;
	}

	public void setDeleteIndex(String deleteIndex) {
		this.deleteIndex = deleteIndex;
	}
	
	
}
