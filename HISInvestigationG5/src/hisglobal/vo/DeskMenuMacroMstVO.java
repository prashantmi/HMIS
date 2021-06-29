package hisglobal.vo;

public class DeskMenuMacroMstVO extends ValueObject
{
	private String macroID;
	private String deskType;
	private String deskName;
	private String deskMenuID;
	private String deskMenu;
	private String deptUnitCode;
	private String deptUnitName;
	private String macroHead;
	private String macroDesc;
	private String seatId;
	private String entryDate;
	private String isValid;

	public String getMacroID()
	{
		return macroID;
	}

	public void setMacroID(String macroID)
	{
		this.macroID = macroID;
	}

	public String getDeskType()
	{
		return deskType;
	}

	public void setDeskType(String deskType)
	{
		this.deskType = deskType;
	}

	public String getDeskMenuID()
	{
		return deskMenuID;
	}

	public void setDeskMenuID(String deskMenuID)
	{
		this.deskMenuID = deskMenuID;
	}

	public String getDeptUnitCode()
	{
		return deptUnitCode;
	}

	public void setDeptUnitCode(String deptUnitCode)
	{
		this.deptUnitCode = deptUnitCode;
	}

	public String getMacroHead()
	{
		return macroHead;
	}

	public void setMacroHead(String macroHead)
	{
		this.macroHead = macroHead;
	}

	public String getMacroDesc()
	{
		return macroDesc;
	}

	public void setMacroDesc(String macroDesc)
	{
		this.macroDesc = macroDesc;
	}

	public String getSeatId()
	{
		return seatId;
	}

	public void setSeatId(String seatId)
	{
		this.seatId = seatId;
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

	public String getDeskName()
	{
		return deskName;
	}

	public void setDeskName(String deskName)
	{
		this.deskName = deskName;
	}

	public String getDeskMenu()
	{
		return deskMenu;
	}

	public void setDeskMenu(String deskMenu)
	{
		this.deskMenu = deskMenu;
	}

	public String getDeptUnitName()
	{
		return deptUnitName;
	}

	public void setDeptUnitName(String deptUnitName)
	{
		this.deptUnitName = deptUnitName;
	}
}
