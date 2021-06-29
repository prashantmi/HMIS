package mrd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class OPDFileTrackingFB extends ActionForm {

	private String mode;
	private String mrdCode;
	private String isMrdListOne;
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getMrdCode() {
		return mrdCode;
	}
	public void setMrdCode(String mrdCode) {
		this.mrdCode = mrdCode;
	}
	public String getIsMrdListOne() {
		return isMrdListOne;
	}
	public void setIsMrdListOne(String isMrdListOne) {
		this.isMrdListOne = isMrdListOne;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setMrdCode("");
	}
}
