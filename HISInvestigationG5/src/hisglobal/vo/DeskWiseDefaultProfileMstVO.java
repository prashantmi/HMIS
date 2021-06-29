package hisglobal.vo;

public class DeskWiseDefaultProfileMstVO extends ValueObject 
{
	
	private String isActive;
	private String deskTypeId;
	private String desktypeName;
	private String hospitalCode;
	private String serialNo;
	private String deskId;
	private String deskName;
	private String deskMenuId;
	private String isDefault;
	
	private String menuListDefault[];
	private String menuListNonDefault[];
	private String profileOrder;
	
	
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getDeskTypeId() {
		return deskTypeId;
	}
	public void setDeskTypeId(String deskTypeId) {
		this.deskTypeId = deskTypeId;
	}
	public String getDesktypeName() {
		return desktypeName;
	}
	public void setDesktypeName(String desktypeName) {
		this.desktypeName = desktypeName;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getDeskId() {
		return deskId;
	}
	public void setDeskId(String deskId) {
		this.deskId = deskId;
	}
	public String getDeskName() {
		return deskName;
	}
	public void setDeskName(String deskName) {
		this.deskName = deskName;
	}
	
	
	
	public String[] getMenuListDefault() {
		return menuListDefault;
	}
	public void setMenuListDefault(String[] menuListDefault) {
		this.menuListDefault = menuListDefault;
	}
	public String[] getMenuListNonDefault() {
		return menuListNonDefault;
	}
	public void setMenuListNonDefault(String[] menuListNonDefault) {
		this.menuListNonDefault = menuListNonDefault;
	}

	public String getProfileOrder() {
		return profileOrder;
	}
	public void setProfileOrder(String profileOrder) {
		this.profileOrder = profileOrder;
	}
	public String getDeskMenuId() {
		return deskMenuId;
	}
	public void setDeskMenuId(String deskMenuId) {
		this.deskMenuId = deskMenuId;
	}
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	
}
