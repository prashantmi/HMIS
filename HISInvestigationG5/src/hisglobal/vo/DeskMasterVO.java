package hisglobal.vo;

public class DeskMasterVO extends ValueObject
{
	private String deskId;
	private String deskName;
	private String deskType;
	private String deskTypeDesc;
	private String entryDate;
	private String seatId;
	private String isValid;
	private String isDefault;

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
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

	public String getDeskId()
	{
		return deskId;
	}

	public void setDeskId(String deskId)
	{
		this.deskId = deskId;
	}

	public String getDeskName()
	{
		return deskName;
	}

	public void setDeskName(String deskName)
	{
		this.deskName = deskName;
	}

	public String getDeskType()
	{
		return deskType;
	}

	public void setDeskType(String deskType)
	{
		this.deskType = deskType;
	}

	public String getSeatId()
	{
		return seatId;
	}

	public void setSeatId(String seatId)
	{
		this.seatId = seatId;
	}

	public String getDeskTypeDesc() {
		return deskTypeDesc;
	}

	public void setDeskTypeDesc(String deskTypeDesc) {
		this.deskTypeDesc = deskTypeDesc;
	}
}
