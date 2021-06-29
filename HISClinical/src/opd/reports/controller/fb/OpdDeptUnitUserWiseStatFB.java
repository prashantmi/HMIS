package opd.reports.controller.fb;

import hisglobal.presentation.ReportFB;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;


public class OpdDeptUnitUserWiseStatFB extends ReportFB
{
	private String departmentCode;
	private String unit;
	private String userCode;
	private String groupCode;
	
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
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		super.reset(mapping,request);
		this.setDepartmentCode("");
		this.setUnit("");
		this.setUserCode("");
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
}
