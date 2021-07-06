package new_opd.transaction.controller.fb;

import org.apache.struts.action.ActionForm;

public class SearchemrActionFB extends ActionForm {
	

	private String strDeptCode ; 
	private String strDeptValues;
	private String strSearchParaValues;
	public String getStrDeptCode() {
		return strDeptCode;
	}
	public void setStrDeptCode(String strDeptCode) {
		this.strDeptCode = strDeptCode;
	}
	public String getStrDeptValues() {
		return strDeptValues;
	}
	public void setStrDeptValues(String strDeptValues) {
		this.strDeptValues = strDeptValues;
	}
	public String getStrSearchParaValues() {
		return strSearchParaValues;
	}
	public void setStrSearchParaValues(String strSearchParaValues) {
		this.strSearchParaValues = strSearchParaValues;
	}
	
	
	
	
}
