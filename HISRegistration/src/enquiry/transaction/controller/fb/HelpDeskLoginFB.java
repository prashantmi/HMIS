package enquiry.transaction.controller.fb;

import org.apache.struts.action.ActionForm;

public class HelpDeskLoginFB extends ActionForm
{
	private String deskType;
	private String hmode;

	public String getDeskType()
	{
		return deskType;
	}

	public void setDeskType(String deskType)
	{
		this.deskType = deskType;
	}

	public String getHmode()
	{
		return hmode;
	}

	public void setHmode(String hmode)
	{
		this.hmode = hmode;
	}
}
