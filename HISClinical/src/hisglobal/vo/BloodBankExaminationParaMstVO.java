package hisglobal.vo;

public class BloodBankExaminationParaMstVO extends ValueObject
{
	private String examParaID;
	private String examParaName;
	private String examParaSlNo;
	private String hospitalCode;
	private String examParaUnit;
	private String validationFunction;
	private String validationMessage;
	private String isValid;
	private String seatID;
	private String entryDate;
	private String lastModifyDate;
	private String lastModifiedSeatID;

	public String getExamParaID()
	{
		return examParaID;
	}

	public void setExamParaID(String examParaID)
	{
		this.examParaID = examParaID;
	}

	public String getExamParaName()
	{
		return examParaName;
	}

	public void setExamParaName(String examParaName)
	{
		this.examParaName = examParaName;
	}

	public String getExamParaSlNo()
	{
		return examParaSlNo;
	}

	public void setExamParaSlNo(String examParaSlNo)
	{
		this.examParaSlNo = examParaSlNo;
	}

	public String getHospitalCode()
	{
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
	}

	public String getExamParaUnit()
	{
		return examParaUnit;
	}

	public void setExamParaUnit(String examParaUnit)
	{
		this.examParaUnit = examParaUnit;
	}

	public String getValidationFunction()
	{
		return validationFunction;
	}

	public void setValidationFunction(String validationFunction)
	{
		this.validationFunction = validationFunction;
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

	public String getLastModifyDate()
	{
		return lastModifyDate;
	}

	public void setLastModifyDate(String lastModifyDate)
	{
		this.lastModifyDate = lastModifyDate;
	}

	public String getLastModifiedSeatID()
	{
		return lastModifiedSeatID;
	}

	public void setLastModifiedSeatID(String lastModifiedSeatID)
	{
		this.lastModifiedSeatID = lastModifiedSeatID;
	}

	public String getValidationMessage()
	{
		return validationMessage;
	}

	public void setValidationMessage(String validationMessage)
	{
		this.validationMessage = validationMessage;
	}
}
