package hisglobal.vo;

public class DeskMenuMasterVO extends ValueObject
{
	private String deskMenuId;
	private String deskMenuName;
	private String deskUrl;
	private String deskType;
	private String isTemplateBased;
	private String templateCategory;
	private String usabilityFlag;
	private String entryDate;
	private String seatId;
	private String isValid;
	private String hospitalCode;

	private String deskMenuType;
	private String deskMenuImg;

	public String getDeskMenuId()
	{
		return deskMenuId;
	}

	public void setDeskMenuId(String deskMenuId)
	{
		this.deskMenuId = deskMenuId;
	}

	public String getDeskMenuName()
	{
		return deskMenuName;
	}

	public void setDeskMenuName(String deskMenuName)
	{
		this.deskMenuName = deskMenuName;
	}

	public String getDeskUrl()
	{
		return deskUrl;
	}

	public void setDeskUrl(String deskUrl)
	{
		this.deskUrl = deskUrl;
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

	public String getSeatID()
	{
		return seatId;
	}

	public void setSeatID(String seatID)
	{
		this.seatId = seatID;
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

	public String getSeatId()
	{
		return seatId;
	}

	public void setSeatId(String seatId)
	{
		this.seatId = seatId;
	}

	public String getHospitalCode()
	{
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
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
