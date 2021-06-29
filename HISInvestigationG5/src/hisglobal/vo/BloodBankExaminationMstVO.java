package hisglobal.vo;

public class BloodBankExaminationMstVO extends ValueObject
{
	private String donorQuesTypeCode;

	private String examParaID;

	// From Exam Para Master
	private String examParaName;
	private String examParaUnit;
	private String validationFunction;
	private String validationMessage;

	private String maleMinValue;
	private String examSlNo;
	private String hospitalCode;
	private String maleMaxValue;
	private String femaleMinValue;
	private String femaleMaxValue;
	private String forceUnfit;
	private String paraOrder;
	private String isCompulsory;
	private String isValid;
	private String seatID;
	private String entryDate;
	private String lastModifyDate;
	private String lastModifiedSeatID;
	private String quesTypeFlag;

	public String getExamParaID()
	{
		return examParaID;
	}

	public void setExamParaID(String examParaID)
	{
		this.examParaID = examParaID;
	}

	public String getMaleMinValue()
	{
		return maleMinValue;
	}

	public void setMaleMinValue(String maleMinValue)
	{
		this.maleMinValue = maleMinValue;
	}

	public String getExamSlNo()
	{
		return examSlNo;
	}

	public void setExamSlNo(String examSlNo)
	{
		this.examSlNo = examSlNo;
	}

	public String getHospitalCode()
	{
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
	}

	public String getMaleMaxValue()
	{
		return maleMaxValue;
	}

	public void setMaleMaxValue(String maleMaxValue)
	{
		this.maleMaxValue = maleMaxValue;
	}

	public String getFemaleMinValue()
	{
		return femaleMinValue;
	}

	public void setFemaleMinValue(String femaleMinValue)
	{
		this.femaleMinValue = femaleMinValue;
	}

	public String getFemaleMaxValue()
	{
		return femaleMaxValue;
	}

	public void setFemaleMaxValue(String femaleMaxValue)
	{
		this.femaleMaxValue = femaleMaxValue;
	}

	public String getForceUnfit()
	{
		return forceUnfit;
	}

	public void setForceUnfit(String forceUnfit)
	{
		this.forceUnfit = forceUnfit;
	}

	public String getParaOrder()
	{
		return paraOrder;
	}

	public void setParaOrder(String paraOrder)
	{
		this.paraOrder = paraOrder;
	}

	public String getIsCompulsory()
	{
		return isCompulsory;
	}

	public void setIsCompulsory(String isCompulsory)
	{
		this.isCompulsory = isCompulsory;
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

	public String getQuesTypeFlag()
	{
		return quesTypeFlag;
	}

	public void setQuesTypeFlag(String quesTypeFlag)
	{
		this.quesTypeFlag = quesTypeFlag;
	}

	public String getExamParaName()
	{
		return examParaName;
	}

	public void setExamParaName(String examParaName)
	{
		this.examParaName = examParaName;
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

	public String getValidationMessage()
	{
		return validationMessage;
	}

	public void setValidationMessage(String validationMessage)
	{
		this.validationMessage = validationMessage;
	}

	public String toString()
	{
		String s = "donorQuesTypeCode:" + this.donorQuesTypeCode + ", examParaID:" + this.examParaID + " {examParaName:"
				+ this.examParaName + ", examParaUnit:" + this.examParaUnit + ", validationFunction:"
				+ this.validationFunction + ", validationMessage:" + this.validationMessage
				+ "}, maleMinValue:" + this.maleMinValue + ", maleMaxValue:" + this.maleMaxValue
				+ ", femaleMinValue:" + this.femaleMinValue + ", femaleMaxValue:" + this.femaleMaxValue
				+ ", forceUnfit:" + this.forceUnfit + ", paraOrder:" + this.paraOrder + ", isCompulsory:"
				+ this.isCompulsory + ", quesTypeFlag" + this.quesTypeFlag;
		return s;
	}

	public String getDonorQuesTypeCode()
	{
		return donorQuesTypeCode;
	}

	public void setDonorQuesTypeCode(String donorQuesTypeCode)
	{
		this.donorQuesTypeCode = donorQuesTypeCode;
	}
}
