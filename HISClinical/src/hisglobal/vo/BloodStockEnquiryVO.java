package hisglobal.vo;

public class BloodStockEnquiryVO extends ValueObject{
	
	private String componentName;
	private String bloodGroup;
	private String bagCount;
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public String getBagCount() {
		return bagCount;
	}
	public void setBagCount(String bagCount) {
		this.bagCount = bagCount;
	}

}
