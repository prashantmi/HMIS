package mortuary.reports.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import hisglobal.presentation.ReportFB;

public class DeathMannerWisePostMortemCountFB extends ReportFB
{
	
	private String deathManner;


	public String getDeathManner() {
		return deathManner;
	}

	public void setDeathManner(String deathManner) {
		this.deathManner = deathManner;
	}

	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		super.reset(mapping,request);
		this.setDeathManner("");
	}
}
