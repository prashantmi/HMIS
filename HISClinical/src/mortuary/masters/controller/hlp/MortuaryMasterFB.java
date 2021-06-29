package mortuary.masters.controller.hlp;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class MortuaryMasterFB extends ActionForm
{
	private String mortuaryCode;
	private String slNo;
	private String mortuaryName;
	private String mortuaryShortName;
	private String autopsyType;
	private String autopsyFor;
	private String departmentCode;
	private String empNo;
	private String roomNo;
	private String locationDesc;
	private String buildingCode;
	private String blockId;
	private String emailId;
	private String website;
	private String floorId;
	private String roomId;
	private String mortuaryType;
	private String effectiveFrom;
	private String effectiveTo;
	private String sysDate="";
	private String hod;
	private String chk[];
	private String hmode;
	private String tempMode;
	private String isActive;

	public String getMortuaryCode() {
		return mortuaryCode;
	}

	public void setMortuaryCode(String mortuaryCode) {
		this.mortuaryCode = mortuaryCode;
	}

	public String getSlNo() {
		return slNo;
	}

	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}

	public String getMortuaryName() {
		return mortuaryName;
	}

	public void setMortuaryName(String mortuaryName) {
		this.mortuaryName = mortuaryName;
	}

	public String getMortuaryShortName() {
		return mortuaryShortName;
	}

	public void setMortuaryShortName(String mortuaryShortName) {
		this.mortuaryShortName = mortuaryShortName;
	}

	public String getAutopsyType() {
		return autopsyType;
	}

	public void setAutopsyType(String autopsyType) {
		this.autopsyType = autopsyType;
	}

	public String getAutopsyFor() {
		return autopsyFor;
	}

	public void setAutopsyFor(String autopsyFor) {
		this.autopsyFor = autopsyFor;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getLocationDesc() {
		return locationDesc;
	}

	public void setLocationDesc(String locationDesc) {
		this.locationDesc = locationDesc;
	}

	public String getBuildingCode() {
		return buildingCode;
	}

	public void setBuildingCode(String buildingCode) {
		this.buildingCode = buildingCode;
	}

	public String getBlockId() {
		return blockId;
	}

	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getFloorId() {
		return floorId;
	}

	public void setFloorId(String floorId) {
		this.floorId = floorId;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getMortuaryType() {
		return mortuaryType;
	}

	public void setMortuaryType(String mortuaryType) {
		this.mortuaryType = mortuaryType;
	}

	public String getEffectiveFrom() {
		return effectiveFrom;
	}

	public void setEffectiveFrom(String effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public String getEffectiveTo() {
		return effectiveTo;
	}

	public void setEffectiveTo(String effectiveTo) {
		this.effectiveTo = effectiveTo;
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setMortuaryName("");
		this.setMortuaryShortName("");
		this.setAutopsyType("");
		this.setAutopsyFor("");
		this.setMortuaryType("");
		this.setDepartmentCode("");
		this.setEmpNo("");
		this.setLocationDesc("");
		this.setEffectiveFrom("");
		this.setEffectiveTo("");
		this.setBuildingCode("");
		this.setBlockId("");
		this.setFloorId("");
		this.setRoomId("");
		this.setRoomNo("");
		this.setEmailId("");
		this.setWebsite("");
		
	}

	public String getTempMode() {
		return tempMode;
	}

	public void setTempMode(String tempMode) {
		this.tempMode = tempMode;
	}

	public String getSysDate() {
		return sysDate;
	}

	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}

	public String getHod() {
		return hod;
	}

	public void setHod(String hod) {
		this.hod = hod;
	}

	public String[] getChk() {
		return chk;
	}

	public void setChk(String[] chk) {
		this.chk = chk;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
}
