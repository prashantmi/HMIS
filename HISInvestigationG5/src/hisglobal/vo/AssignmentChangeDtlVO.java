package hisglobal.vo;

public class AssignmentChangeDtlVO extends ValueObject
{
	private String summonId;
	private String oldAssignee;
	private String serealNo;
	private String oldOtherAssignReason;
	private String oldSummonAck;
	private String oldAssignSame;
	private String newAssignee;
	private String reason;
	private String entryDate;
	private String isValid;
	private String seatId;
	private String hospitalCode;
	
	
	public String getSummonId() {
		return summonId;
	}
	public void setSummonId(String summonId) {
		this.summonId = summonId;
	}
	public String getOldAssignee() {
		return oldAssignee;
	}
	public void setOldAssignee(String oldAssignee) {
		this.oldAssignee = oldAssignee;
	}
	public String getSerealNo() {
		return serealNo;
	}
	public void setSerealNo(String serealNo) {
		this.serealNo = serealNo;
	}
	public String getOldOtherAssignReason() {
		return oldOtherAssignReason;
	}
	public void setOldOtherAssignReason(String oldOtherAssignReason) {
		this.oldOtherAssignReason = oldOtherAssignReason;
	}
	public String getOldSummonAck() {
		return oldSummonAck;
	}
	public void setOldSummonAck(String oldSummonAck) {
		this.oldSummonAck = oldSummonAck;
	}
	public String getOldAssignSame() {
		return oldAssignSame;
	}
	public void setOldAssignSame(String oldAssignSame) {
		this.oldAssignSame = oldAssignSame;
	}
	public String getNewAssignee() {
		return newAssignee;
	}
	public void setNewAssignee(String newAssignee) {
		this.newAssignee = newAssignee;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	
}
