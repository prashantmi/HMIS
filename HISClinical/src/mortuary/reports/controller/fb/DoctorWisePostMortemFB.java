package mortuary.reports.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import hisglobal.presentation.ReportFB;

public class DoctorWisePostMortemFB extends ReportFB
{
	private String empNo;
	private String deathManner;

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getDeathManner() {
		return deathManner;
	}

	public void setDeathManner(String deathManner) {
		this.deathManner = deathManner;
	}

	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		super.reset(mapping,request);
		this.setEmpNo("");
		this.setDeathManner("");
	}
	
}
