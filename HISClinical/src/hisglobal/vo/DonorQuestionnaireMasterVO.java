package hisglobal.vo;

public class DonorQuestionnaireMasterVO extends ValueObject
{
	private String donorTypeCode;
	private String questionID;
	
	// Question Detail (Not in Actual Physical Table but in Questionnaire Table)
	private String questionDesc;
	private String recordType;
	private String parentID;
	private String gender;
	private String answerType;
	
	
	
	
	private String displayOrder;
	private String yesMessage;
	private String noMessage;
	private String forceUnfit;
	private String isCompulsary;
	private String isValid;
	private String seatID;
	private String entryDate;
	private String lastModifyDate;
	private String lastModifiedSeatID;
	private String hospitalCode;
	private String defaultValue;

	public String getDonorTypeCode()
	{
		return donorTypeCode;
	}

	public void setDonorTypeCode(String donorTypeCode)
	{
		this.donorTypeCode = donorTypeCode;
	}

	public String getQuestionID()
	{
		return questionID;
	}

	public void setQuestionID(String questionID)
	{
		this.questionID = questionID;
	}

	public String getDisplayOrder()
	{
		return displayOrder;
	}

	public void setDisplayOrder(String displayOrder)
	{
		this.displayOrder = displayOrder;
	}

	public String getYesMessage()
	{
		return yesMessage;
	}

	public void setYesMessage(String yesMessage)
	{
		this.yesMessage = yesMessage;
	}

	public String getNoMessage()
	{
		return noMessage;
	}

	public void setNoMessage(String noMessage)
	{
		this.noMessage = noMessage;
	}

	public String getForceUnfit()
	{
		return forceUnfit;
	}

	public void setForceUnfit(String forceUnfit)
	{
		this.forceUnfit = forceUnfit;
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

	public String getHospitalCode()
	{
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
	}

	public String getQuestionDesc()
	{
		return questionDesc;
	}

	public void setQuestionDesc(String questionDesc)
	{
		this.questionDesc = questionDesc;
	}

	public String getRecordType()
	{
		return recordType;
	}

	public void setRecordType(String recordType)
	{
		this.recordType = recordType;
	}

	public String getParentID()
	{
		return parentID;
	}

	public void setParentID(String parentID)
	{
		this.parentID = parentID;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public String getAnswerType()
	{
		return answerType;
	}

	public void setAnswerType(String answerType)
	{
		this.answerType = answerType;
	}

	public String getIsCompulsary()
	{
		return isCompulsary;
	}

	public void setIsCompulsary(String isCompulsary)
	{
		this.isCompulsary = isCompulsary;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
}
