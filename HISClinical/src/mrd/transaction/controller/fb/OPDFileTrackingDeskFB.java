package mrd.transaction.controller.fb;

import org.apache.struts.action.ActionForm;

public class OPDFileTrackingDeskFB extends ActionForm {
	
	private String deskmode;
	private String hmode;;

	public String getDeskmode() {
		return deskmode;
	}

	public void setDeskmode(String deskmode) {
		this.deskmode = deskmode;
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

}
