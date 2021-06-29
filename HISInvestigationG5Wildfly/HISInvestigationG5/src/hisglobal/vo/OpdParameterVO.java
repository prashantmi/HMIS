package hisglobal.vo;

public class OpdParameterVO extends ValueObject
{
	private String paraId;
	private String paraName;
	private String isValid;
	private String seatId;
	private String entryDate;

	public String getIsValid()
	{
		return isValid;
	}

	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
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

	public String getParaId()
	{
		return paraId;
	}

	public void setParaId(String paraId)
	{
		this.paraId = paraId;
	}

	public String getParaName()
	{
		return paraName;
	}

	public void setParaName(String paraName)
	{
		this.paraName = paraName;
	}
}
