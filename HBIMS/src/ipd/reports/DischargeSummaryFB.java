package ipd.reports;

import org.apache.struts.action.ActionForm;


public class DischargeSummaryFB extends ActionForm {
	
	
	private static final long serialVersionUID = 02L;
	
	private String strHospCode = "";
	private String strAdmNo = "";
	private String strIsFooter = "";
	private String strUserRemarks = "";
	public String getStrHospCode() {
		return strHospCode;
	}
	public void setStrHospCode(String strHospCode) {
		this.strHospCode = strHospCode;
	}
	public String getStrAdmNo() {
		return strAdmNo;
	}
	public void setStrAdmNo(String strAdmNo) {
		this.strAdmNo = strAdmNo;
	}
	public String getStrIsFooter() {
		return strIsFooter;
	}
	public void setStrIsFooter(String strIsFooter) {
		this.strIsFooter = strIsFooter;
	}
	public String getStrUserRemarks() {
		return strUserRemarks;
	}
	public void setStrUserRemarks(String strUserRemarks) {
		this.strUserRemarks = strUserRemarks;
	}
}
