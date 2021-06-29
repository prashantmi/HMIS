package mrd.reports.controller.fb;

import hisglobal.presentation.ReportFB;

public class SpecialityWiseInvestigationFB extends ReportFB  
{
	private String deptCode;
	private String unit;
	private String speciality;
	private String specialityForDept;
	private String combo;
	private String option;
	private String labTestOption;
	private String lab;
	private String test;
	private String labCode;

	public String getLab() {
		return lab;
	}

	public void setLab(String lab) {
		this.lab = lab;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public String getLabTestOption() {
		return labTestOption;
	}

	public void setLabTestOption(String labTestOption) {
		this.labTestOption = labTestOption;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getSpecialityForDept() {
		return specialityForDept;
	}

	public void setSpecialityForDept(String specialityForDept) {
		this.specialityForDept = specialityForDept;
	}

	public String getCombo() {
		return combo;
	}

	public void setCombo(String combo) {
		this.combo = combo;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getLabCode() {
		return labCode;
	}

	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}
}
