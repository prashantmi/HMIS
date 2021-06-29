package hisglobal.vo;

public class IncisionTypeMasterVO extends ValueObject
{
	private String incisionType;
	private String incisionTypeCode;
	private String description;
	private String slNo;
	
	private String hmode;
	private String tempMode;
	private String isActive;
	
	public String getIncisionType() {
		return incisionType;
	}
	public void setIncisionType(String incisionType) {
		this.incisionType = incisionType;
	}
	public String getIncisionTypeCode() {
		return incisionTypeCode;
	}
	public void setIncisionTypeCode(String incisionTypeCode) {
		this.incisionTypeCode = incisionTypeCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getTempMode() {
		return tempMode;
	}
	public void setTempMode(String tempMode) {
		this.tempMode = tempMode;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
}
