package opd.master.controller.fb;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class UnitDrugMasterFB extends ActionForm{
	private String hmode;
	private String deptCode;
	private ArrayList mainUnitsList;
	private ArrayList unitsList;
	private String[] selectedUnit;
	private ArrayList extTreatCode;
	private String[] selectedExtTreatCode; 
	private String tempMode;
	private String chk;
	private String controls[];
	private String deptUnitName;
	private String treatmentType;
	private String teatListByType;	
	
	public UnitDrugMasterFB() {
		this.controls = new String[1];
	}
	
	
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
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
	public ArrayList getExtTreatCode() {
		return extTreatCode;
	}
	public void setExtTreatCode(ArrayList extTreatCode) {
		this.extTreatCode = extTreatCode;
	}
	public String[] getSelectedExtTreatCode() {
		return selectedExtTreatCode;
	}
	public void setSelectedExtTreatCode(String[] selectedExtTreatCode) {
		this.selectedExtTreatCode = selectedExtTreatCode;
	}
	public String getTempMode() {
		return tempMode;
	}
	public void setTempMode(String tempMode) {
		this.tempMode = tempMode;
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
	public String getDeptUnitName() {
		return deptUnitName;
	}
	public void setDeptUnitName(String deptUnitName) {
		this.deptUnitName = deptUnitName;
	}
	public String getTreatmentType() {
		return treatmentType;
	}
	public void setTreatmentType(String treatmentType) {
		this.treatmentType = treatmentType;
	}
	public String getTeatListByType() {
		return teatListByType;
	}
	public void setTeatListByType(String teatListByType) {
		this.teatListByType = teatListByType;
	}
}
