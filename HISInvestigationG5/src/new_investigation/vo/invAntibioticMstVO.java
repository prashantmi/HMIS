package new_investigation.vo;

import hisglobal.vo.ValueObject;

public class invAntibioticMstVO extends ValueObject {

	private String antibioticName;
	private String antibioticNameOrder;
	private String antibioticNameCode;
	
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
	public String getAntibioticNameCode() {
		return antibioticNameCode;
	}
	public void setAntibioticNameCode(String antibioticNameCode) {
		this.antibioticNameCode = antibioticNameCode;
	}
	
	
	
	
	
}
