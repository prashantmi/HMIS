package hisglobal.vo;

public class DrugFrequencyMstVO extends ValueObject
{
	private String frequencyId;
	private String frequency;
	private String frequencySchedule;
	private String frequencyDesc;
	private String frequencyName;
	private String morDoseTime;
	private String aftDoseTime;
	private String eveDoseTime;
	private String ngtDoseTime;
	private String serialNo;
	private String entryDate;
	private String seatId;
	private String isValid;
	private String hospitalCode;
	
	
	public String getFrequencyId() {
		return frequencyId;
	}
	public void setFrequencyId(String frequencyId) {
		this.frequencyId = frequencyId;
	}
	public String getFrequencySchedule() {
		return frequencySchedule;
	}
	public void setFrequencySchedule(String frequencySchedule) {
		this.frequencySchedule = frequencySchedule;
	}
	public String getFrequencyDesc() {
		return frequencyDesc;
	}
	public void setFrequencyDesc(String frequencyDesc) {
		this.frequencyDesc = frequencyDesc;
	}
	public String getMorDoseTime() {
		return morDoseTime;
	}
	public void setMorDoseTime(String morDoseTime) {
		this.morDoseTime = morDoseTime;
	}
	public String getAftDoseTime() {
		return aftDoseTime;
	}
	public void setAftDoseTime(String aftDoseTime) {
		this.aftDoseTime = aftDoseTime;
	}
	public String getEveDoseTime() {
		return eveDoseTime;
	}
	public void setEveDoseTime(String eveDoseTime) {
		this.eveDoseTime = eveDoseTime;
	}
	public String getNgtDoseTime() {
		return ngtDoseTime;
	}
	public void setNgtDoseTime(String ngtDoseTime) {
		this.ngtDoseTime = ngtDoseTime;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
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
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getFrequencyName() {
		return frequencyName;
	}
	public void setFrequencyName(String frequencyName) {
		this.frequencyName = frequencyName;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
}
