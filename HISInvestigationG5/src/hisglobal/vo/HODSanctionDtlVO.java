package hisglobal.vo;

public class HODSanctionDtlVO extends ValueObject
{
	private String requisitionNo;
	private String sanctionType;
	private String unitSanction;
	private String reasonCode;
	private String reasonDesc;
	private String remarks;
	private String isValid;
	private String seatId;
	private String entryDate;
	private String hospitalCode;
	
	
	public String getRequisitionNo()
	{
		return requisitionNo;
	}
	public void setRequisitionNo(String requisitionNo)
	{
		this.requisitionNo = requisitionNo;
	}
	public String getSanctionType()
	{
		return sanctionType;
	}
	public void setSanctionType(String sanctionType)
	{
		this.sanctionType = sanctionType;
	}
	public String getUnitSanction()
	{
		return unitSanction;
	}
	public void setUnitSanction(String unitSanction)
	{
		this.unitSanction = unitSanction;
	}
	public String getReasonCode()
	{
		return reasonCode;
	}
	public void setReasonCode(String reasonCode)
	{
		this.reasonCode = reasonCode;
	}
	public String getReasonDesc()
	{
		return reasonDesc;
	}
	public void setReasonDesc(String reasonDesc)
	{
		this.reasonDesc = reasonDesc;
	}
	public String getRemarks()
	{
		return remarks;
	}
	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
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
	
	
	
}
