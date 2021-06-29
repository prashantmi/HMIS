package opd.reports.controller.fb;

import hisglobal.presentation.ReportFB;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;


public class OpdReferredPatientListFB extends ReportFB
{
	private String fromDepartment;
	private String toDepartment;
	
	public String getFromDepartment() {
		return fromDepartment;
	}
	public void setFromDepartment(String fromDepartment) {
		this.fromDepartment = fromDepartment;
	}
	public String getToDepartment() {
		return toDepartment;
	}
	public void setToDepartment(String toDepartment) {
		this.toDepartment = toDepartment;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		super.reset(mapping,request);
		this.setFromDepartment("");
		this.setToDepartment("");
	}	
}
