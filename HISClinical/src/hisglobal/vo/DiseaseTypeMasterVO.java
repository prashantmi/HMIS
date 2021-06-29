package hisglobal.vo;

public class DiseaseTypeMasterVO extends ValueObject
{
	private String diseaseTypeCode;
	private String hospitalCode;
	private String diseaseTypeName;
	private String actionRemarks;
	private String entryDate;
	private String isValid;
	private String seatId;
	private String mainType;

	public String getDiseaseTypeCode()
	{
		return diseaseTypeCode;
	}

	public void setDiseaseTypeCode(String diseaseTypeCode)
	{
		this.diseaseTypeCode = diseaseTypeCode;
	}

	public String getHospitalCode()
	{
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
	}

	public String getDiseaseTypeName()
	{
		return diseaseTypeName;
	}

	public void setDiseaseTypeName(String diseaseTypeName)
	{
		this.diseaseTypeName = diseaseTypeName;
	}

	public String getActionRemarks()
	{
		return actionRemarks;
	}

	public void setActionRemarks(String actionRemarks)
	{
		this.actionRemarks = actionRemarks;
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

	public String getSeatId()
	{
		return seatId;
	}

	public void setSeatId(String seatId)
	{
		this.seatId = seatId;
	}

	public String getMainType()
	{
		return mainType;
	}

	public void setMainType(String mainType)
	{
		this.mainType = mainType;
	}
}
