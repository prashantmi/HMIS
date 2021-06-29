package hisglobal.vo;

public class RegistrationCatMstVO extends ValueObject
{

	private String regCatCode;
	private String regCatSlNo;
	private String regCatName;
	private String regCatShortName;
	private String isValid;
	private String seatID;
	private String entryDate;
	private String hl7Code;
	private String effectiveFrom;
	private String effectiveTo;
	private String lastModifyDate;
	private String lastModifySeatID;
	private String remarks;
	private String chk;
	private String transactionMode;
	private String choice;
	private String isActive;
	private String viewOrModify;
	private String regType;

	public String getRegType()
	{
		return regType;
	}

	public void setRegType(String regType)
	{
		this.regType = regType;
	}

	public String getViewOrModify()
	{
		return viewOrModify;
	}

	public void setViewOrModify(String viewOrModify)
	{
		this.viewOrModify = viewOrModify;
	}

	public String getIsActive()
	{
		return isActive;
	}

	public void setIsActive(String isActive)
	{
		this.isActive = isActive;
	}

	public String getChoice()
	{
		return choice;
	}

	public void setChoice(String choice)
	{
		this.choice = choice;
	}

	public String getTransactionMode()
	{
		return transactionMode;
	}

	public void setTransactionMode(String transactionMode)
	{
		this.transactionMode = transactionMode;
	}

	public String getChk()
	{
		return chk;
	}

	public void setChk(String chk)
	{
		this.chk = chk;
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

	public String getHl7Code()
	{
		return hl7Code;
	}

	public void setHl7Code(String hl7Code)
	{
		this.hl7Code = hl7Code;
	}

	public String getIsValid()
	{
		return isValid;
	}

	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	public String getLastModifyDate()
	{
		return lastModifyDate;
	}

	public void setLastModifyDate(String lastModifyDate)
	{
		this.lastModifyDate = lastModifyDate;
	}

	public String getLastModifySeatID()
	{
		return lastModifySeatID;
	}

	public void setLastModifySeatID(String lastModifySeatID)
	{
		this.lastModifySeatID = lastModifySeatID;
	}

	public String getRegCatCode()
	{
		return regCatCode;
	}

	public void setRegCatCode(String regCatCode)
	{
		this.regCatCode = regCatCode;
	}

	public String getRegCatName()
	{
		return regCatName;
	}

	public void setRegCatName(String regCatName)
	{
		this.regCatName = regCatName;
	}

	public String getRegCatShortName()
	{
		return regCatShortName;
	}

	public void setRegCatShortName(String regCatShortName)
	{
		this.regCatShortName = regCatShortName;
	}

	public String getRegCatSlNo()
	{
		return regCatSlNo;
	}

	public void setRegCatSlNo(String regCatSlNo)
	{
		this.regCatSlNo = regCatSlNo;
	}

	public String getRemarks()
	{
		return remarks;
	}

	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
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
