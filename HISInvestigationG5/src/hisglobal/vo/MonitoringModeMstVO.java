package hisglobal.vo;

public class MonitoringModeMstVO extends ValueObject
{
	private String modeId;
	private String modeName;
	private String dayFrequency;
	private String intervalInMin;
	private String entryDate;
	private String seatId;
	private String isValid;
	private String hospitalCode;
	
	
	public String getModeId() {
		return modeId;
	}
	public void setModeId(String modeId) {
		this.modeId = modeId;
	}
	public String getModeName() {
		return modeName;
	}
	public void setModeName(String modeName) {
		this.modeName = modeName;
	}
	public String getDayFrequency() {
		return dayFrequency;
	}
	public void setDayFrequency(String dayFrequency) {
		this.dayFrequency = dayFrequency;
	}
	public String getIntervalInMin() {
		return intervalInMin;
	}
	public void setIntervalInMin(String intervalInMin) {
		this.intervalInMin = intervalInMin;
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
	
}
