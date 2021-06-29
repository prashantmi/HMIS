package mrd.reports.controller.fb;
import hisglobal.presentation.ReportFB;

import javax.servlet.http.HttpServletRequest;

import mrd.MrdConfig;

import org.apache.struts.action.ActionMapping;


public class DeptWiseMonthlyAbstractOfPatientFB extends ReportFB 
{
	private String departmentCode;
	private String option;
	private String deptCode;
	private String depCode;
		private String combo;
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		super.reset(mapping,request);
		this.setDepartmentCode("%");
		this.setOption(MrdConfig.DEPT_WISE);
		this.setDeptCode("%");
		this.setChoice(MrdConfig.DEPT_WISE);
		
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
	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}


	public String getCombo() {
		return combo;
	}

	public void setCombo(String combo) {
		this.combo = combo;
	}

}
