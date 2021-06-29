package hisglobal.vo;

public class DonorRefreshmentDetailVO extends ValueObject
{
	private String donorRegistrationNo;
	private String donorVisitNo;
	private String itemCode;
	private String itemDesc;
	private String slno;
	private String Qty;
	private String isValid;
	private String seatId;
	private String entryDate;
	private String hospitalcode;
	private String IP_ADD;
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
	public String getItemCode()
	{
		return itemCode;
	}
	public void setItemCode(String itemCode)
	{
		this.itemCode = itemCode;
	}
	public String getSlno()
	{
		return slno;
	}
	public void setSlno(String slno)
	{
		this.slno = slno;
	}
	public String getQty()
	{
		return Qty;
	}
	public void setQty(String qty)
	{
		Qty = qty;
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
	public String getHospitalcode()
	{
		return hospitalcode;
	}
	public void setHospitalcode(String hospitalcode)
	{
		this.hospitalcode = hospitalcode;
	}
	public String getIP_ADD()
	{
		return IP_ADD;
	}
	public void setIP_ADD(String ip_add)
	{
		IP_ADD = ip_add;
	}
	public String getItemDesc()
	{
		return itemDesc;
	}
	public void setItemDesc(String itemDesc)
	{
		this.itemDesc = itemDesc;
	}
}
