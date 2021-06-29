package hisglobal.vo;

public class DeskDetailVO extends ValueObject
{
	private String deskId;
	private String deskMenuId;
	private String location;
	private String displayOrder;
	private String userDeskMenuName;
	private String color;
	private String seatId;
	private String entryDate;	
	private String isValid;
	private String hospitalCode;
	private String serialNo;
	private String isDefaultProfile;
	private String profileOrder;
	private String isLoginBound;
	private String dutyRoleId;
	
		// Desk Menu Detail
	private String deskUrl;
	private String deskMenuName;
	private String deskType;
	private String isTemplateBased;
	private String templateCategory;
	private String usabilityFlag;
	private String deskMenuType;
	private String deskMenuImg;

	public String getDeskUrl()
	{
		return deskUrl;
	}

	public void setDeskUrl(String deskUrl)
	{
		this.deskUrl = deskUrl;
	}

	public String getColor()
	{
		return color;
	}

	public void setColor(String color)
	{
		this.color = color;
	}

	public String getDeskId()
	{
		return deskId;
	}

	public void setDeskId(String deskId)
	{
		this.deskId = deskId;
	}

	public String getDeskMenuId()
	{
		return deskMenuId;
	}

	public void setDeskMenuId(String deskMenuId)
	{
		this.deskMenuId = deskMenuId;
	}

	public String getDisplayOrder()
	{
		return displayOrder;
	}

	public void setDisplayOrder(String displayOrder)
	{
		this.displayOrder = displayOrder;
	}

	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	public String getIsValid()
	{
		return isValid;
	}

	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	public String getLocation()
	{
		return location;
	}

	public void setLocation(String location)
	{
		this.location = location;
	}

	public String getSeatId()
	{
		return seatId;
	}

	public void setSeatId(String seatId)
	{
		this.seatId = seatId;
	}

	public String getUserDeskMenuName()
	{
		return userDeskMenuName;
	}

	public void setUserDeskMenuName(String userDeskMenuName)
	{
		this.userDeskMenuName = userDeskMenuName;
	}

	public String getHospitalCode()
	{
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
	}

	public String getSerialNo()
	{
		return serialNo;
	}

	public void setSerialNo(String serialNo)
	{
		this.serialNo = serialNo;
	}

	public String getIsDefaultProfile()
	{
		return isDefaultProfile;
	}

	public void setIsDefaultProfile(String isDefaultProfile)
	{
		this.isDefaultProfile = isDefaultProfile;
	}

	public String getProfileOrder()
	{
		return profileOrder;
	}

	public void setProfileOrder(String profileOrder)
	{
		this.profileOrder = profileOrder;
	}

	public String getIsLoginBound()
	{
		return isLoginBound;
	}

	public void setIsLoginBound(String isLoginBound)
	{
		this.isLoginBound = isLoginBound;
	}

	public String getDutyRoleId()
	{
		return dutyRoleId;
	}

	public void setDutyRoleId(String dutyRoleId)
	{
		this.dutyRoleId = dutyRoleId;
	}

	public String getDeskMenuName()
	{
		return deskMenuName;
	}

	public void setDeskMenuName(String deskMenuName)
	{
		this.deskMenuName = deskMenuName;
	}

	public String getDeskType()
	{
		return deskType;
	}

	public void setDeskType(String deskType)
	{
		this.deskType = deskType;
	}

	public String getIsTemplateBased()
	{
		return isTemplateBased;
	}

	public void setIsTemplateBased(String isTemplateBased)
	{
		this.isTemplateBased = isTemplateBased;
	}

	public String getTemplateCategory()
	{
		return templateCategory;
	}

	public void setTemplateCategory(String templateCategory)
	{
		this.templateCategory = templateCategory;
	}

	public String getUsabilityFlag()
	{
		return usabilityFlag;
	}

	public void setUsabilityFlag(String usabilityFlag)
	{
		this.usabilityFlag = usabilityFlag;
	}

	public String getDeskMenuType() {
		return deskMenuType;
	}

	public void setDeskMenuType(String deskMenuType) {
		this.deskMenuType = deskMenuType;
	}

	public String getDeskMenuImg() {
		return deskMenuImg;
	}

	public void setDeskMenuImg(String deskMenuImg) {
		this.deskMenuImg = deskMenuImg;
	}
}
