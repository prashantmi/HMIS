package new_investigation.transactions.controller.Helper;

import hisglobal.masterutil.GenericFormBean;

public class InvestigationViewTemplateActionForm extends GenericFormBean {
	String selectedTest;
	String hmode;
	String paraType;
 String mastertestCode;
	public String getSelectedTest() {
		return selectedTest;
	}
	public void setSelectedTest(String selectedTest) {
		this.selectedTest = selectedTest;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getParaType() {
		return paraType;
	}
	public void setParaType(String paraType) {
		this.paraType = paraType;
	}
	public String getMastertestCode() {
		return mastertestCode;
	}
	public void setMastertestCode(String mastertestCode) {
		this.mastertestCode = mastertestCode;
	}
	
}
