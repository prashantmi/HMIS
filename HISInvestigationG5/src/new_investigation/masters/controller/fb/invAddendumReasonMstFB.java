package new_investigation.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class invAddendumReasonMstFB extends ActionForm {

	
	private String hmode;
	private String reason;
	private String[] chk;
	private String reasonCode;
	
	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String[] getChk() {
		return chk;
	}

	public void setChk(String[] chk) {
		this.chk = chk;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}
	
	
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.reason="";
	
	}	
	
	
}
