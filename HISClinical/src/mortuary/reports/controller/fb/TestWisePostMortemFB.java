package mortuary.reports.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import hisglobal.presentation.ReportFB;

public class TestWisePostMortemFB extends ReportFB
{
	
	private String labTestId;
	
	public String getLabTestId() {
		return labTestId;
	}
	public void setLabTestId(String labTestId) {
		this.labTestId = labTestId;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		super.reset(mapping,request);
		this.setLabTestId("");
		
	}
}
