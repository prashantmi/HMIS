package new_investigation.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class RejectionReasonMstFB extends ActionForm
{
	
	private String rejectionreasonId;
	private String acceptanceType;
	private String rejectionReason;
	private String isvalid;
	private String isActive;
	private String entryDate;
	private String selectedChk;
	private String lstmod_date;
	private String seat_id;
	private String lstmod_seatid;
	private String chk[];
	private String hmode;
	
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.rejectionReason="";
		this.acceptanceType="1";
		
	}
	
	
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getSelectedChk() {
		return selectedChk;
	}
	public void setSelectedChk(String selectedChk) {
		this.selectedChk = selectedChk;
	}

	public String[] getChk()
	{
		return chk;
	}
	public void setChk(String[] chk)
	{
		this.chk = chk;
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


	public String getHmode() {
		return hmode;
	}


	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	
}
