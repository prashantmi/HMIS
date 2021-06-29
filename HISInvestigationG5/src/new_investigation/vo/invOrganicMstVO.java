package new_investigation.vo;

import hisglobal.vo.ValueObject;

public class invOrganicMstVO extends ValueObject {

	
	
	private String organicName;
	private String organicNameCode;
	private String organicNameOrder;
	
	
	
	
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
	public String getOrganicNameCode() {
		return organicNameCode;
	}
	public void setOrganicNameCode(String organicNameCode) {
		this.organicNameCode = organicNameCode;
	}
	
	
	
	
	
}
