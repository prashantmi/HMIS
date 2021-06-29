package mortuary.reports.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import hisglobal.presentation.ReportFB;

public class IoNameAndPSWisePostMortemFB extends ReportFB
{
	private String orderBy;
	

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		super.reset(mapping,request);
		this.setOrderBy("");
		
	}
}
