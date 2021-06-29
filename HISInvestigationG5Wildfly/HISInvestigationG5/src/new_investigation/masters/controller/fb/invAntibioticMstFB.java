package new_investigation.masters.controller.fb;

import org.apache.struts.action.ActionForm;

public class invAntibioticMstFB extends ActionForm {

	
	private String hmode;
	private String antibioticName;
	private String antibioticNameOrder;
	private String antibioticNameCode;
		
	private String[] chk;
	
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	
	public String getAntibioticName() {
		return antibioticName;
	}
	public void setAntibioticName(String antibioticName) {
		this.antibioticName = antibioticName;
	}
	public String getAntibioticNameOrder() {
		return antibioticNameOrder;
	}
	public void setAntibioticNameOrder(String antibioticNameOrder) {
		this.antibioticNameOrder = antibioticNameOrder;
	}
	public String[] getChk() {
		return chk;
	}
	public void setChk(String[] chk) {
		this.chk = chk;
	}
	public String getAntibioticNameCode() {
		return antibioticNameCode;
	}
	public void setAntibioticNameCode(String antibioticNameCode) {
		this.antibioticNameCode = antibioticNameCode;
	}
	
	
	
	
	
	
}
