
package opd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ChangePatientUnitRoomFB extends ActionForm {
	
	private String patCrNo;
	private String hmode;	
	private String remarks;
	private String roomCode;
	private String oldRoomName;
	

	

	public String getPatCrNo() {
		return patCrNo;
	}





	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}





	public String getHmode() {
		return hmode;
	}





	public void setHmode(String hmode) {
		this.hmode = hmode;
	}





	public String getRemarks() {
		return remarks;
	}





	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}





	public String getRoomCode() {
		return roomCode;
	}





	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}





	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
	}





	public String getOldRoomName() {
		return oldRoomName;
	}





	public void setOldRoomName(String oldRoomName) {
		this.oldRoomName = oldRoomName;
	}
}
