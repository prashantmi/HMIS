package hisglobal.vo;

public class TransfusionReactionParaDtlVO extends ValueObject
{
	private String recordDate;
	private String reactionID;
	private String paraSerealNo;
	private String templateId;
	private String paraValue;
	private String isValid;
	private String seatID;
	private String paraId;
	private String entryDate;
	private String hospitalCode;
	
	public String getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}
	public String getReactionID() {
		return reactionID;
	}
	public void setReactionID(String reactionID) {
		this.reactionID = reactionID;
	}
	public String getParaSerealNo() {
		return paraSerealNo;
	}
	public void setParaSerealNo(String paraSerealNo) {
		this.paraSerealNo = paraSerealNo;
	}
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public String getParaValue() {
		return paraValue;
	}
	public void setParaValue(String paraValue) {
		this.paraValue = paraValue;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getSeatID() {
		return seatID;
	}
	public void setSeatID(String seatID) {
		this.seatID = seatID;
	}
	public String getParaId() {
		return paraId;
	}
	public void setParaId(String paraId) {
		this.paraId = paraId;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
}
