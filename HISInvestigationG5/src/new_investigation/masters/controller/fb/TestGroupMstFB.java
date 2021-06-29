package new_investigation.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class TestGroupMstFB extends ActionForm
{
	private String groupCode;
	private String groupCde;
	private String hospitalCode;
	private String globalgroupName;
	private String localgroupName;
	private String isvalid;
	private String seat_id;
	private String entryDate;
	private String groupType;
	private String lstmod_date;
	private String lstmod_seatid;
	private String remarks;
	private String isActive;
	private String selectedChk;
	private String chk[];
	private String hmode;
	private String grupName;
	private String groupName;
	private String preferenceOrder;
	private String isprint;
	private String prefOrder;
	
	public String getGroupName() {
		return groupName;
	}


	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}


	public String getGroupCde() {
		return groupCde;
	}


	public void setGroupCde(String groupCde) {
		this.groupCde = groupCde;
	}


	public String getGrupName() {
		return grupName;
	}


	public void setGrupName(String grupName) {
		this.grupName = grupName;
	}


	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		
		this.groupCde="-1";
		this.groupName="";
		this.groupType="1";
		this.remarks="";
		
	}


	public String getGroupCode() {
		return groupCode;
	}


	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}


	public String getHospitalCode() {
		return hospitalCode;
	}


	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}


	public String getGlobalgroupName() {
		return globalgroupName;
	}


	public void setGlobalgroupName(String globalgroupName) {
		this.globalgroupName = globalgroupName;
	}


	public String getLocalgroupName() {
		return localgroupName;
	}


	public void setLocalgroupName(String localgroupName) {
		this.localgroupName = localgroupName;
	}


	public String getIsvalid() {
		return isvalid;
	}


	public void setIsvalid(String isvalid) {
		this.isvalid = isvalid;
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


	public String getGroupType() {
		return groupType;
	}


	public void setGroupType(String groupType) {
		this.groupType = groupType;
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


	public String[] getChk() {
		return chk;
	}


	public void setChk(String[] chk) {
		this.chk = chk;
	}


	public String getHmode() {
		return hmode;
	}


	public void setHmode(String hmode) {
		this.hmode = hmode;
	}


	public String getPreferenceOrder() {
		return preferenceOrder;
	}


	public void setPreferenceOrder(String preferenceOrder) {
		this.preferenceOrder = preferenceOrder;
	}
	
	public String getIsprint() {
		return isprint;
	}


    public void setIsprint(String isprint) {
		this.isprint = isprint;
	}


	public String getPrefOrder() {
		return prefOrder;
	}


	public void setPrefOrder(String prefOrder) {
		this.prefOrder = prefOrder;
	}

	
	
	
	}
