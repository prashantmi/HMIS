package mortuary.masters.controller.hlp;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class MortuaryAreaMasterFB extends ActionForm
{
	private String mortuaryCode;
	private String slNo;
	private String areaName;
	private String areaTypeCode;
	private String areaCode;
	private String areaDesc;
	private String controls[];
	
	private String empNo;
	private String roomNo;
	
	private String buildingCode;
	private String blockId;
	private String floorId;
	private String roomId;
	
	private String chk[];
	private String hmode;
	private String tempMode;
	private String isActive;
	private String mortuaryName;
	
	public String getMortuaryName() {
		return mortuaryName;
	}

	public void setMortuaryName(String mortuaryName) {
		this.mortuaryName = mortuaryName;
	}

	public MortuaryAreaMasterFB()
	{
		controls=new String[1];
	}

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

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaTypeCode() {
		return areaTypeCode;
	}

	public void setAreaTypeCode(String areaTypeCode) {
		this.areaTypeCode = areaTypeCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaDesc() {
		return areaDesc;
	}

	public void setAreaDesc(String areaDesc) {
		this.areaDesc = areaDesc;
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

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		
		this.areaName="";
		this.areaDesc="";
		this.areaTypeCode="-1";
		this.blockId="-1";
		this.buildingCode="-1";
		this.floorId="-1";
		this.roomId="-1";
		this.roomNo="";
	}

	public String[] getControls() {
		return controls;
	}

	public void setControls(String[] controls) {
		this.controls = controls;
	}

	public String getTempMode() {
		return tempMode;
	}

	public void setTempMode(String tempMode) {
		this.tempMode = tempMode;
	}
}
