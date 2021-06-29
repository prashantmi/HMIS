package hisglobal.vo;

public class ProcedureConsumablesMasterVO extends ValueObject
{
	private String serviceAreaCode;
	private String serviceAreaName;
	private String hospitalCode;
	private String itemName;
	private String qty;
	private String uom;
	private String procedureName;
	private String itemType;
	private String ageBreakupString;
	private String qtyString;
	private String uomString;
	private String ageBreakup;
	
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public String getProcedureName() {
		return procedureName;
	}
	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}
	private String isActive;

	public String getServiceAreaCode()
	{
		return serviceAreaCode;
	}
	public void setServiceAreaCode(String serviceAreaCode)
	{
		this.serviceAreaCode = serviceAreaCode;
	}
	public String getServiceAreaName()
	{
		return serviceAreaName;
	}
	public void setServiceAreaName(String serviceAreaName)
	{
		this.serviceAreaName = serviceAreaName;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getAgeBreakupString() {
		return ageBreakupString;
	}
	public void setAgeBreakupString(String ageBreakupString) {
		this.ageBreakupString = ageBreakupString;
	}
	public String getQtyString() {
		return qtyString;
	}
	public void setQtyString(String qtyString) {
		this.qtyString = qtyString;
	}
	public String getUomString() {
		return uomString;
	}
	public void setUomString(String uomString) {
		this.uomString = uomString;
	}
	public String getAgeBreakup() {
		return ageBreakup;
	}
	public void setAgeBreakup(String ageBreakup) {
		this.ageBreakup = ageBreakup;
	}
}
