package new_investigation.masters.controller.fb;

import org.apache.struts.action.ActionForm;

public class invOrganicMstFB extends ActionForm {

	private String hmode;
	private String organicName;
	private String organicNameOrder;
	private String organicNameCode;
	private String[] chk;
	
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getOrganicName() {
		return organicName;
	}
	public void setOrganicName(String organicName) {
		this.organicName = organicName;
	}
	public String getOrganicNameOrder() {
		return organicNameOrder;
	}
	public void setOrganicNameOrder(String organicNameOrder) {
		this.organicNameOrder = organicNameOrder;
	}
	public String[] getChk() {
		return chk;
	}
	public void setChk(String[] chk) {
		this.chk = chk;
	}
	public String getOrganicNameCode() {
		return organicNameCode;
	}
	public void setOrganicNameCode(String organicNameCode) {
		this.organicNameCode = organicNameCode;
	}
	
	
	
	
}
