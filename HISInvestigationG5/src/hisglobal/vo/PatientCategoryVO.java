package hisglobal.vo;

public class PatientCategoryVO extends ValueObject
{

	private String patCatCode;
	private String patCatSlNo;
	private String patCatName;
	private String patCatShortName;
	private String patCatType;
	private String isPaid;
	private String priority;
	private String isExpiry;
	private String expiryMonth;
	private String expiryDay;
	private String hl7Code;
	private String remarks;
	private String expiryDate;

	private String isValid;
	private String choice;
	private String effectiveFrom;
	private String effectiveTo;
	private String transactionMode;
	private String isActive;
	private String chk;
	private String entryDate;
	private String viewOrModify;
	private String lastModifyDate;
	private String lastModifiedSeatID;
	private String seatID;
	private String hospitalCode;
	private String idRequired;
	private String criteriaRemarks;
	private String categoryBenefits;
	

	

	public String getHospitalCode()
	{
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
	}

	public String getPriority()
	{
		return priority;
	}

	public void setPriority(String priority)
	{
		this.priority = priority;
	}

	public String getSeatID()
	{
		return seatID;
	}

	public void setSeatID(String seatID)
	{
		this.seatID = seatID;
	}

	public String getLastModifiedSeatID()
	{
		return lastModifiedSeatID;
	}

	public void setLastModifiedSeatID(String lastModifiedSeatID)
	{
		this.lastModifiedSeatID = lastModifiedSeatID;
	}

	public String getLastModifyDate()
	{
		return lastModifyDate;
	}

	public void setLastModifyDate(String lastModifyDate)
	{
		this.lastModifyDate = lastModifyDate;
	}

	public String getChk()
	{
		return chk;
	}

	public void setChk(String chk)
	{
		this.chk = chk;
	}

	public String getChoice()
	{
		return choice;
	}

	public void setChoice(String choice)
	{
		this.choice = choice;
	}

	public String getEffectiveFrom()
	{
		return effectiveFrom;
	}

	public void setEffectiveFrom(String effectiveFrom)
	{
		this.effectiveFrom = effectiveFrom;
	}

	public String getEffectiveTo()
	{
		return effectiveTo;
	}

	public void setEffectiveTo(String effectiveTo)
	{
		this.effectiveTo = effectiveTo;
	}

	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	public String getExpiryDate()
	{
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate)
	{
		this.expiryDate = expiryDate;
	}

	public String getHl7Code()
	{
		return hl7Code;
	}

	public void setHl7Code(String hl7Code)
	{
		this.hl7Code = hl7Code;
	}

	public String getIsActive()
	{
		return isActive;
	}

	public void setIsActive(String isActive)
	{
		this.isActive = isActive;
	}

	public String getIsExpiry()
	{
		return isExpiry;
	}

	public void setIsExpiry(String isExpiry)
	{
		this.isExpiry = isExpiry;
	}

	public String getIsPaid()
	{
		return isPaid;
	}

	public void setIsPaid(String isPaid)
	{
		this.isPaid = isPaid;
	}

	public String getIsValid()
	{
		return isValid;
	}

	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	public String getPatCatCode()
	{
		return patCatCode;
	}

	public void setPatCatCode(String patCatCode)
	{
		this.patCatCode = patCatCode;
	}

	public String getPatCatName()
	{
		return patCatName;
	}

	public void setPatCatName(String patCatName)
	{
		this.patCatName = patCatName;
	}

	public String getPatCatShortName()
	{
		return patCatShortName;
	}

	public void setPatCatShortName(String patCatShortName)
	{
		this.patCatShortName = patCatShortName;
	}

	public String getPatCatSlNo()
	{
		return patCatSlNo;
	}

	public void setPatCatSlNo(String patCatSlNo)
	{
		this.patCatSlNo = patCatSlNo;
	}

	public String getPatCatType()
	{
		return patCatType;
	}

	public void setPatCatType(String patCatType)
	{
		this.patCatType = patCatType;
	}

	public String getRemarks()
	{
		return remarks;
	}

	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}

	public String getTransactionMode()
	{
		return transactionMode;
	}

	public void setTransactionMode(String transactionMode)
	{
		this.transactionMode = transactionMode;
	}

	public String getViewOrModify()
	{
		return viewOrModify;
	}

	public void setViewOrModify(String viewOrModify)
	{
		this.viewOrModify = viewOrModify;
	}

	public String getExpiryMonth()
	{
		return expiryMonth;
	}

	public void setExpiryMonth(String expiryMonth)
	{
		this.expiryMonth = expiryMonth;
	}

	public String getExpiryDay()
	{
		return expiryDay;
	}

	public void setExpiryDay(String expiryDay)
	{
		this.expiryDay = expiryDay;
	}

	public String getIdRequired() {
		return idRequired;
	}

	public void setIdRequired(String idRequired) {
		this.idRequired = idRequired;
	}

	public String getCriteriaRemarks() {
		return criteriaRemarks;
	}

	public void setCriteriaRemarks(String criteriaRemarks) {
		this.criteriaRemarks = criteriaRemarks;
	}

	public String getCategoryBenefits() {
		return categoryBenefits;
	}

	public void setCategoryBenefits(String categoryBenefits) {
		this.categoryBenefits = categoryBenefits;
	}
}
