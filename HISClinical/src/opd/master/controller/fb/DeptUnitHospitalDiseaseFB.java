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
public class DeptUnitHospitalDiseaseFB extends ActionForm{
	
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
	private String hosdiseaseCode;
	private String hosdiseaseList;
	private String []selecteHospitaldDisease;
	private String hosDisease;
	private String deptUnitCode;
	private String chk;
	private String deleteIndex;
	private String tempMode;
	private String controls[];
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setDeptCode("");
			
	}
	public DeptUnitHospitalDiseaseFB()
	{
		this.controls = new String[1];
	}
	public String getTempMode() {
		return tempMode;
	}
	public void setTempMode(String tempMode) {
		this.tempMode = tempMode;
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
	public String getDeptUnitName() {
		return deptUnitName;
	}
	public void setDeptUnitName(String deptUnitName) {
		this.deptUnitName = deptUnitName;
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
	public String getHosdiseaseCode() {
		return hosdiseaseCode;
	}
	public void setHosdiseaseCode(String hosdiseaseCode) {
		this.hosdiseaseCode = hosdiseaseCode;
	}
	public String getHosdiseaseList() {
		return hosdiseaseList;
	}
	public void setHosdiseaseList(String hosdiseaseList) {
		this.hosdiseaseList = hosdiseaseList;
	}
	public String[] getSelecteHospitaldDisease() {
		return selecteHospitaldDisease;
	}
	public void setSelecteHospitaldDisease(String[] selecteHospitaldDisease) {
		this.selecteHospitaldDisease = selecteHospitaldDisease;
	}
	public String getHosDisease() {
		return hosDisease;
	}
	public void setHosDisease(String hosDisease) {
		this.hosDisease = hosDisease;
	}
	public String getDeptUnitCode() {
		return deptUnitCode;
	}
	public void setDeptUnitCode(String deptUnitCode) {
		this.deptUnitCode = deptUnitCode;
	}
	public String getChk() {
		return chk;
	}
	public void setChk(String chk) {
		this.chk = chk;
	}
	public String getDeleteIndex() {
		return deleteIndex;
	}
	public void setDeleteIndex(String deleteIndex) {
		this.deleteIndex = deleteIndex;
	}
	
	

}
