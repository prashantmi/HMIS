package hisglobal.vo;

public class UnitAudioVideoMasterVO extends ValueObject
{
	private String fileCode;
	private String fileName;
	private String isValid;
	private String entryDate;
	private String seatID;
	private String unitCode;

	public String getUnitCode()
	{
		return unitCode;
	}

	public void setUnitCode(String unitCode)
	{
		this.unitCode = unitCode;
	}

	public String getFileCode()
	{
		return fileCode;
	}

	public void setFileCode(String fileCode)
	{
		this.fileCode = fileCode;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public String getIsValid()
	{
		return isValid;
	}

	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	public String getSeatID()
	{
		return seatID;
	}

	public void setSeatID(String seatID)
	{
		this.seatID = seatID;
	}
}
