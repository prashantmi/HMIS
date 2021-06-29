package enquiry.transaction.controller.fb;

import org.apache.struts.action.ActionForm;

public class DeceasedEnquiryFB extends ActionForm
{
	private String hmode;
	private String deceasedNo;
	private String chkRadioBtn;
	
	
	
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getDeceasedNo() {
		return deceasedNo;
	}
	public void setDeceasedNo(String deceasedNo) {
		this.deceasedNo = deceasedNo;
	}
	public String getChkRadioBtn() {
		return chkRadioBtn;
	}
	public void setChkRadioBtn(String chkRadioBtn) {
		this.chkRadioBtn = chkRadioBtn;
	}
}
