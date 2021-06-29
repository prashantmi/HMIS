package hisglobal.vo;

public class DonorQuestionniareDetailVO extends ValueObject
{
	private String donorRegistrationNo;
	private String donorVisitNo;
	private String questionID;
	private String answerValue;
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

	public String getQuestionID()
	{
		return questionID;
	}

	public void setQuestionID(String questionID)
	{
		this.questionID = questionID;
	}

	public String getAnswerValue()
	{
		return answerValue;
	}

	public void setAnswerValue(String answerValue)
	{
		this.answerValue = answerValue;
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
