package mrd.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class MrdMasterFB extends ActionForm
{
	private String hmode;
	private String roomId;
	private String floorId;
	private String blockId;
	private String buildingCode;
	private String locationDesc;
	private String empNo;
	private String departmentCode;
	private String mrdType;
	private String mrdShortName;
	private String mrdName;
	private String hod;
	private String chk[];
	private String sereialNo;
	private String mrdCode;
	private String tempMode;
	private String mainMrdFlag;
	private String isActive;
	
	
	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.blockId="-1";
		this.buildingCode="-1";
		this.departmentCode="-1";
		this.empNo="-1";
		this.floorId="-1";
		this.locationDesc="";
		this.mrdName="";
		this.mrdShortName="";
		this.mrdType=null;
		this.roomId="-1";
	}
	
	public String getMainMrdFlag() {
		return mainMrdFlag;
	}

	public void setMainMrdFlag(String mainMrdFlag) {
		this.mainMrdFlag = mainMrdFlag;
	}

	public String getHod() {
		return hod;
	}

	public void setHod(String hod) {
		this.hod = hod;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getFloorId() {
		return floorId;
	}

	public void setFloorId(String floorId) {
		this.floorId = floorId;
	}

	public String getBlockId() {
		return blockId;
	}

	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}

	public String getBuildingCode() {
		return buildingCode;
	}

	public void setBuildingCode(String buildingCode) {
		this.buildingCode = buildingCode;
	}

	public String getLocationDesc() {
		return locationDesc;
	}

	public void setLocationDesc(String locationDesc) {
		this.locationDesc = locationDesc;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getMrdType() {
		return mrdType;
	}

	public void setMrdType(String mrdType) {
		this.mrdType = mrdType;
	}

	public String getMrdShortName() {
		return mrdShortName;
	}

	public void setMrdShortName(String mrdShortName) {
		this.mrdShortName = mrdShortName;
	}

	public String getMrdName() {
		return mrdName;
	}

	public void setMrdName(String mrdName) {
		this.mrdName = mrdName;
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String[] getChk() {
		return chk;
	}

	public void setChk(String[] chk) {
		this.chk = chk;
	}

	public String getSereialNo() {
		return sereialNo;
	}

	public void setSereialNo(String sereialNo) {
		this.sereialNo = sereialNo;
	}

	public String getMrdCode() {
		return mrdCode;
	}

	public void setMrdCode(String mrdCode) {
		this.mrdCode = mrdCode;
	}

	public String getTempMode() {
		return tempMode;
	}

	public void setTempMode(String tempMode) {
		this.tempMode = tempMode;
	}

	
}
