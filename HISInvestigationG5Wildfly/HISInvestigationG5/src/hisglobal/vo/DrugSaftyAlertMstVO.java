package hisglobal.vo;

public class DrugSaftyAlertMstVO extends ValueObject
{
	private String itemId;
	private String hospitalCode;
	private String CL;
	private String SP;
	private String INT;
	private String ADR;
	private String INT_POT_HAZ;
	private String ADR_POT_LT;
	private String LAB_INT;
	private String INT_FOOD;
	private String remarks;
	private String effectiveFrom;
	private String lastModificationSeatId;
	private String lastModifyDate;
	private String entryDate;
	private String seatId;
	private String isValid;
	
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getCL() {
		return CL;
	}
	public void setCL(String cl) {
		CL = cl;
	}
	public String getSP() {
		return SP;
	}
	public void setSP(String sp) {
		SP = sp;
	}
	public String getINT() {
		return INT;
	}
	public void setINT(String int1) {
		INT = int1;
	}
	public String getADR() {
		return ADR;
	}
	public void setADR(String adr) {
		ADR = adr;
	}
	public String getINT_POT_HAZ() {
		return INT_POT_HAZ;
	}
	public void setINT_POT_HAZ(String int_pot_haz) {
		INT_POT_HAZ = int_pot_haz;
	}
	public String getADR_POT_LT() {
		return ADR_POT_LT;
	}
	public void setADR_POT_LT(String adr_pot_lt) {
		ADR_POT_LT = adr_pot_lt;
	}
	public String getLAB_INT() {
		return LAB_INT;
	}
	public void setLAB_INT(String lab_int) {
		LAB_INT = lab_int;
	}
	public String getINT_FOOD() {
		return INT_FOOD;
	}
	public void setINT_FOOD(String int_food) {
		INT_FOOD = int_food;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getEffectiveFrom() {
		return effectiveFrom;
	}
	public void setEffectiveFrom(String effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}
	public String getLastModificationSeatId() {
		return lastModificationSeatId;
	}
	public void setLastModificationSeatId(String lastModificationSeatId) {
		this.lastModificationSeatId = lastModificationSeatId;
	}
	public String getLastModifyDate() {
		return lastModifyDate;
	}
	public void setLastModifyDate(String lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
}
