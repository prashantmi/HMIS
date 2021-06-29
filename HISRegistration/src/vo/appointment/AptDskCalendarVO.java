package vo.appointment;

import hisglobal.vo.ValueObject;

public class AptDskCalendarVO extends ValueObject {

	private String paraId;
	private String paraRefId;
	private String slotST;
	private String slotET;
	private String slotDuration;
	private String aptId;
	private String startDate;
	private String endDate;
	
	public String getParaId() {
		return paraId;
	}
	public void setParaId(String paraId) {
		this.paraId = paraId;
	}
	public String getParaRefId() {
		return paraRefId;
	}
	public void setParaRefId(String paraRefId) {
		this.paraRefId = paraRefId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getSlotST() {
		return slotST;
	}
	public void setSlotST(String slotST) {
		this.slotST = slotST;
	}
	public String getSlotET() {
		return slotET;
	}
	public void setSlotET(String slotET) {
		this.slotET = slotET;
	}
	public String getSlotDuration() {
		return slotDuration;
	}
	public void setSlotDuration(String slotDuration) {
		this.slotDuration = slotDuration;
	}
	public String getAptId() {
		return aptId;
	}
	public void setAptId(String aptId) {
		this.aptId = aptId;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
		
}
