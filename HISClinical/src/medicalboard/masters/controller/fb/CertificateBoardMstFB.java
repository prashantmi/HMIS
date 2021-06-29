package medicalboard.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class CertificateBoardMstFB extends ActionForm {

	private String certificateTypeID;
	private String[] boardID;
	private String slNo;
	private String hmode;
	private String certificateTypeName;
	private String boardName;
	private String[] selectedBoardId;
	private String[] oldSelectedBoardId; 
	
	
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.certificateTypeID="-1";
		this.selectedBoardId=null;
		this.boardID=null;
	}
	
	public String getCertificateTypeID() {
		return certificateTypeID;
	}
	public void setCertificateTypeID(String certificateTypeID) {
		this.certificateTypeID = certificateTypeID;
	}
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getCertificateTypeName() {
		return certificateTypeName;
	}
	public void setCertificateTypeName(String certificateTypeName) {
		this.certificateTypeName = certificateTypeName;
	}
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	public String[] getBoardID() {
		return boardID;
	}
	public void setBoardID(String[] boardID) {
		this.boardID = boardID;
	}
	public String[] getSelectedBoardId() {
		return selectedBoardId;
	}
	public void setSelectedBoardId(String[] selectedBoardId) {
		this.selectedBoardId = selectedBoardId;
	}
	public String[] getOldSelectedBoardId() {
		return oldSelectedBoardId;
	}
	public void setOldSelectedBoardId(String[] oldSelectedBoardId) {
		this.oldSelectedBoardId = oldSelectedBoardId;
	}
	
}
