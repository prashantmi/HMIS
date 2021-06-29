package mortuary.reports.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import hisglobal.presentation.ReportFB;

public class DoctorWisePostMortemCountFB extends ReportFB
{
	private String empNo;
	

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		super.reset(mapping,request);
		this.setEmpNo("");
	}
}
