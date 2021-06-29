package hisglobal.vo;

public class DrugDoseVO extends ValueObject
{
	private String doseId;
	private String serialNo;
	private String doseName;
	private String doseInstructions;
	private String itemTypeId;
	private String entryDate;
	private String seatId;
	private String isValid;
	private String hospitalCode;
	private String isFrequencyBound;
	private String doseQty;

	public String getIsFrequencyBound() {
		return isFrequencyBound;
	}

	public void setIsFrequencyBound(String isFrequencyBound) {
		this.isFrequencyBound = isFrequencyBound;
	}

	public String getDoseQty() {
		return doseQty;
	}

	public void setDoseQty(String doseQty) {
		this.doseQty = doseQty;
	}

	public String getDoseId()
	{
		return doseId;
	}

	public void setDoseId(String doseId)
	{
		this.doseId = doseId;
	}

	public String getSerialNo()
	{
		return serialNo;
	}

	public void setSerialNo(String serialNo)
	{
		this.serialNo = serialNo;
	}

	public String getDoseName()
	{
		return doseName;
	}

	public void setDoseName(String doseName)
	{
		this.doseName = doseName;
	}

	public String getDoseInstructions()
	{
		return doseInstructions;
	}

	public void setDoseInstructions(String doseInstructions)
	{
		this.doseInstructions = doseInstructions;
	}

	public String getItemTypeId()
	{
		return itemTypeId;
	}

	public void setItemTypeId(String itemTypeId)
	{
		this.itemTypeId = itemTypeId;
	}

	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	public String getSeatId()
	{
		return seatId;
	}

	public void setSeatId(String seatId)
	{
		this.seatId = seatId;
	}

	public String getIsValid()
	{
		return isValid;
	}

	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
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
