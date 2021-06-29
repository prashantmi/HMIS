package new_investigation.vo;

import hisglobal.vo.ValueObject;

public class RejectionReasonMasterVO extends ValueObject 
{
	private String rejectionreasonId;
	private String acceptanceType;
	private String rejectionReason;
	private String previousRejectionReason;
	private String isvalid;
	private String isActive;
	private String seat_id;
	private String lstmod_seatid;
	private String entryDate;
	private String lstmod_date;
	
	public String getPreviousRejectionReason() {
		return previousRejectionReason;
	}
	public void setPreviousRejectionReason(String previousRejectionReason) {
		this.previousRejectionReason = previousRejectionReason;
	}
	
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	public String getRejectionreasonId() {
		return rejectionreasonId;
	}
	public void setRejectionreasonId(String rejectionreasonId) {
		this.rejectionreasonId = rejectionreasonId;
	}
	public String getAcceptanceType() {
		return acceptanceType;
	}
	public void setAcceptanceType(String acceptanceType) {
		this.acceptanceType = acceptanceType;
	}
	public String getRejectionReason() {
		return rejectionReason;
	}
	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}
	public String getIsvalid() {
		return isvalid;
	}
	public void setIsvalid(String isvalid) {
		this.isvalid = isvalid;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getLstmod_date() {
		return lstmod_date;
	}
	public void setLstmod_date(String lstmod_date) {
		this.lstmod_date = lstmod_date;
	}
	public String getSeat_id() {
		return seat_id;
	}
	public void setSeat_id(String seat_id) {
		this.seat_id = seat_id;
	}
	public String getLstmod_seatid() {
		return lstmod_seatid;
	}
	public void setLstmod_seatid(String lstmod_seatid) {
		this.lstmod_seatid = lstmod_seatid;
	}
}