package hisglobal.vo;

public class DonationApheresisMstVO extends ValueObject
{
	private String donationTypeCode;
	private String apheresisMachineId;
	private String apheresisMachineName;
	private String slNo;
	private String isValid;
	private String seatId;
	private String entryDate;
	private String hospitalCode;
	private String lastModifyDate;
	private String lastModifiedSeatID;
	private String templateId;
	private String donationTypeDesc;
	private String templateName;

	public String getDonationTypeCode()
	{
		return donationTypeCode;
	}
	public void setDonationTypeCode(String donationTypeCode)
	{
		this.donationTypeCode = donationTypeCode;
	}
	public String getApheresisMachineId()
	{
		return apheresisMachineId;
	}
	public void setApheresisMachineId(String apheresisMachineId)
	{
		this.apheresisMachineId = apheresisMachineId;
	}
	public String getSlNo()
	{
		return slNo;
	}
	public void setSlNo(String slNo)
	{
		this.slNo = slNo;
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
	public String getHospitalCode()
	{
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
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
	public String getTemplateId()
	{
		return templateId;
	}
	public void setTemplateId(String templateId)
	{
		this.templateId = templateId;
	}
	public String getApheresisMachineName()
	{
		return apheresisMachineName;
	}
	public void setApheresisMachineName(String apheresisMachineName)
	{
		this.apheresisMachineName = apheresisMachineName;
	}
	public String getDonationTypeDesc()
	{
		return donationTypeDesc;
	}
	public void setDonationTypeDesc(String donationTypeDesc)
	{
		this.donationTypeDesc = donationTypeDesc;
	}
	public String getTemplateName()
	{
		return templateName;
	}
	public void setTemplateName(String templateName)
	{
		this.templateName = templateName;
	}
}
