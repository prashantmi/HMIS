package mrd.reports.controller.fb;

import hisglobal.presentation.ReportFB;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

public class SpecialityGenderWiseOPDPatientsFB extends ReportFB
{
	private String departmentCode;
	
	private String hmode;
	private String option;
	private String deptCode;
	
	
		public void reset(ActionMapping mapping,HttpServletRequest request)
		{
			super.reset(mapping, request);
			this.setDepartmentCode("%");
			
		}


		public String getDepartmentCode() {
			return departmentCode;
		}


		public void setDepartmentCode(String departmentCode) {
			this.departmentCode = departmentCode;
		}


		public String getHmode() {
			return hmode;
		}


		public void setHmode(String hmode) {
			this.hmode = hmode;
		}


		public String getOption() {
			return option;
		}


		public void setOption(String option) {
			this.option = option;
		}


		public String getDeptCode() {
			return deptCode;
		}


		public void setDeptCode(String deptCode) {
			this.deptCode = deptCode;
		}
}
