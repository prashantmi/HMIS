package opd.master.controller.fb;

import org.apache.struts.action.ActionForm;

public class ProfileGroupMasterFB extends ActionForm 
{
	
	private String profileGroupId;
	private String profileGroupName;
	private String deptUnitCode;
	private String seatID;
	private String entryDate;
	private String isValid;
	private String hmode;
	private String serialNo;
	private String isActive;
	private String lastModifiedDate;
	private String lastModifiedSeatId;
	private String chk;
	public String getProfileGroupId() {
		return profileGroupId;
	}
	public void setProfileGroupId(String profileGroupId) {
		this.profileGroupId = profileGroupId;
	}
	public String getProfileGroupName() {
		return profileGroupName;
	}
	public void setProfileGroupName(String profileGroupName) {
		this.profileGroupName = profileGroupName;
	}
	public String getDeptUnitCode() {
		return deptUnitCode;
	}
	public void setDeptUnitCode(String deptUnitCode) {
		this.deptUnitCode = deptUnitCode;
	}
	public String getSeatID() {
		return seatID;
	}
	public void setSeatID(String seatID) {
		this.seatID = seatID;
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
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public String getLastModifiedSeatId() {
		return lastModifiedSeatId;
	}
	public void setLastModifiedSeatId(String lastModifiedSeatId) {
		this.lastModifiedSeatId = lastModifiedSeatId;
	}
	public String getChk() {
		return chk;
	}
	public void setChk(String chk) {
		this.chk = chk;
	}

}
