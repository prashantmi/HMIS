package hisglobal.vo;

public class BoardDetailVO extends ValueObject 
{
	private String boardNo;
	private String serealNo;
	private String boardStartTime;
	private String actualDateTime;
	private String certificateTypeId;
	private String location;
	private String boardStatus;
	private String closeDateTime;
	private String approvedDate;
	private String approvedBy;
	private String isValid;
	private String seatId;
	private String entryDate;
	private String hospitalCode;
	private String boardId;
	
	private String boardName;
	
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	public String getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(String boardNo) {
		this.boardNo = boardNo;
	}
	public String getSerealNo() {
		return serealNo;
	}
	public void setSerealNo(String serealNo) {
		this.serealNo = serealNo;
	}
	public String getBoardStartTime() {
		return boardStartTime;
	}
	public void setBoardStartTime(String boardStartTime) {
		this.boardStartTime = boardStartTime;
	}
	public String getActualDateTime() {
		return actualDateTime;
	}
	public void setActualDateTime(String actualDateTime) {
		this.actualDateTime = actualDateTime;
	}
	public String getCertificateTypeId() {
		return certificateTypeId;
	}
	public void setCertificateTypeId(String certificateTypeId) {
		this.certificateTypeId = certificateTypeId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getBoardStatus() {
		return boardStatus;
	}
	public void setBoardStatus(String boardStatus) {
		this.boardStatus = boardStatus;
	}
	public String getCloseDateTime() {
		return closeDateTime;
	}
	public void setCloseDateTime(String closeDateTime) {
		this.closeDateTime = closeDateTime;
	}
	public String getApprovedDate() {
		return approvedDate;
	}
	public void setApprovedDate(String approvedDate) {
		this.approvedDate = approvedDate;
	}
	public String getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
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
	public String getBoardId() {
		return boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
}
