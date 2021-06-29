package hisglobal.vo;

public class DonorExamDetailVO extends ValueObject
{
	private String donorRegistrationNo;
	private String donorVisitNo;
	private String examParaID;
	private String examParaValue;
	private String isValid;
	private String seatID;
	private String entryDate;
	private String hospitalCode;

	public String getDonorRegistrationNo()
	{
		return donorRegistrationNo;
	}

	public void setDonorRegistrationNo(String donorRegistrationNo)
	{
		this.donorRegistrationNo = donorRegistrationNo;
	}

	public String getDonorVisitNo()
	{
		return donorVisitNo;
	}

	public void setDonorVisitNo(String donorVisitNo)
	{
		this.donorVisitNo = donorVisitNo;
	}

	public String getExamParaID()
	{
		return examParaID;
	}

	public void setExamParaID(String examParaID)
	{
		this.examParaID = examParaID;
	}

	public String getExamParaValue()
	{
		return examParaValue;
	}

	public void setExamParaValue(String examParaValue)
	{
		this.examParaValue = examParaValue;
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
		return seatID;
	}

	public void setSeatID(String seatID)
	{
		this.seatID = seatID;
	}

	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	public String getHospitalCode()
	{
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
	}
}
