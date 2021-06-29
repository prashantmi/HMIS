package ipd.transactions;

import org.apache.struts.action.ActionForm;

public class DischargeDeskFB extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 02L;
	private String deskmode="";

	public String getDeskmode() {
		return deskmode;
	}

	public void setDeskmode(String deskmode) {
		this.deskmode = deskmode;
	}
}
