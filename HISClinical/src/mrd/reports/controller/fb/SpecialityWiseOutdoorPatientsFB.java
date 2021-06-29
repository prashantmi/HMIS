package mrd.reports.controller.fb;

import hisglobal.presentation.ReportFB;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

public class SpecialityWiseOutdoorPatientsFB extends ReportFB
{
	private String departmentCode;
	private String unit;
	private String hmode;
	private String option;
	private String deptCode;
	private String depCode;
	private String speciality;
	private String specialityForDept;
	private String[] unitLst;
	private String[] specificUnitLst;
	private String combo;
	
	
	public String getSpecialityForDept() {
		return specialityForDept;
	}

	public void setSpecialityForDept(String specialityForDept) {
		this.specialityForDept = specialityForDept;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getDepCode() {
		return depCode;
	}

	public void setDepCode(String depCode) {
		this.depCode = depCode;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}


	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		super.reset(mapping,request);
		this.setDepartmentCode("%");
		this.setUnit("%");	
		this.setSpeciality("%");
		//this.setChoice(MrdConfig.DEPT_WISE);
		
	}

		public String[] getSpecificUnitLst() {
		return specificUnitLst;
	}

		public String[] getUnitLst() {
		return unitLst;
	}

	public void setUnitLst(String[] unitLst) {
		this.unitLst = unitLst;
	}

	public void setSpecificUnitLst(String[] specificUnitLst) {
		this.specificUnitLst = specificUnitLst;
	}

	public String getCombo() {
		return combo;
	}

	public void setCombo(String combo) {
		this.combo = combo;
	}

}
