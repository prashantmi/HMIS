package new_investigation.vo;

import hisglobal.vo.ValueObject;

public class TestGroupMasterVO extends ValueObject 
{
	private String groupCode;
	private String hospitalCode;
	private String groupName;
	private String isvalid;
	private String seat_id;
	private String entryDate;
	private String groupType;
	private String lstmod_date;
	private String lstmod_seatid;
	private String remarks;
	private String isActive;
	private String groupCde;
	private String chk[];
	private String preferenceOrder;
	private String isprint;
	
	public String[] getChk() {
		return chk;
	}
	public void setChk(String[] chk) {
		this.chk = chk;
	}
	public String getGroupCde() {
		return groupCde;
	}
	public void setGroupCde(String groupCde) {
		this.groupCde = groupCde;
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
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
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
	
	
	
	
}