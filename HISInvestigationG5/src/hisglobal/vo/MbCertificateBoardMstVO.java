package hisglobal.vo;

public class MbCertificateBoardMstVO extends ValueObject{

	
	private String certificateTypeID;
	private String boardID;
	private String slNo;
	private String boardTypeId;
	
	
	public String getCertificateTypeID() {
		return certificateTypeID;
	}
	public void setCertificateTypeID(String certificateTypeID) {
		this.certificateTypeID = certificateTypeID;
	}
	public String getBoardID() {
		return boardID;
	}
	public void setBoardID(String boardID) {
		this.boardID = boardID;
	}
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getBoardTypeId() {
		return boardTypeId;
	}
	public void setBoardTypeId(String boardTypeId) {
		this.boardTypeId = boardTypeId;
	}
	
	
}
