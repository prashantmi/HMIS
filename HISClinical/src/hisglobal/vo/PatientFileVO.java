package hisglobal.vo;

public class PatientFileVO extends ValueObject
{
	private String paraId;
	private String paraName;
	private String paraValue;
	private String paraBound;
	private String paraType;
	private String isValid;
	private String seatId;
	private String entryDate;
	private String hospitalCode;

	public String getParaBound()
	{
		return paraBound;
	}

	public void setParaBound(String paraBound)
	{
		this.paraBound = paraBound;
	}

	public String getParaType()
	{
		return paraType;
	}

	public void setParaType(String paraType)
	{
		this.paraType = paraType;
	}

	public String getHospitalCode()
	{
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
	}

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

	public String getParaValue() {
		return paraValue;
	}

	public void setParaValue(String paraValue) {
		this.paraValue = paraValue;
	}
	
}
