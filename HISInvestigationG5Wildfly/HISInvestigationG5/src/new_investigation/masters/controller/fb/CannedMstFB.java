package new_investigation.masters.controller.fb;

import hisglobal.masterutil.GenericFormBean;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

public class CannedMstFB extends GenericFormBean
{
	private String hmode;
	private String cannedCde;
	private String seat_id;
	private String entryDate;
	private String isvalid;
	private String lstmod_date;
	private String lstmod_seatid;
	private String remarks;
	private String cannedContent;
	private String cannedName;
	private String isActive;
	
	private String selectedChk;

	private String cannedFile;
	private String text1;
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.cannedName="";
		this.text1="";
		this.remarks="";
		
	}
	
	public String getCannedFile() {
		return cannedFile;
	}

	public void setCannedFile(String cannedFile) {
		this.cannedFile = cannedFile;
	}

	public String getText1() {
		return text1;
	}

	public void setText1(String text1) {
		this.text1 = text1;
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String getCannedCde() {
		return cannedCde;
	}

	public void setCannedCde(String cannedCde) {
		this.cannedCde = cannedCde;
	}

	public String getSeat_id() {
		return seat_id;
	}

	public void setSeat_id(String seat_id) {
		this.seat_id = seat_id;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	public String getIsvalid() {
		return isvalid;
	}

	public void setIsvalid(String isvalid) {
		this.isvalid = isvalid;
	}

	public String getLstmod_date() {
		return lstmod_date;
	}

	public void setLstmod_date(String lstmod_date) {
		this.lstmod_date = lstmod_date;
	}

	public String getLstmod_seatid() {
		return lstmod_seatid;
	}

	public void setLstmod_seatid(String lstmod_seatid) {
		this.lstmod_seatid = lstmod_seatid;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCannedContent() {
		return cannedContent;
	}

	public void setCannedContent(String cannedContent) {
		this.cannedContent = cannedContent;
	}

	public String getCannedName() {
		return cannedName;
	}

	public void setCannedName(String cannedName) {
		this.cannedName = cannedName;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

/*	public String[] getChk() {
		return chk;
	}

	public void setChk(String[] chk) {
		this.chk = chk;
	}*/

	public String getSelectedChk() {
		return selectedChk;
	}

	public void setSelectedChk(String selectedChk) {
		this.selectedChk = selectedChk;
	}
	
	}
