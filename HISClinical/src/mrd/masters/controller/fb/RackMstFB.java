package mrd.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class RackMstFB extends ActionForm
{
	private String hmode;
	private String chk[];
	private String serialNo;
	
	
	private String rackId;
	private String rackName;
	private String rackType;
	private String itemId;
	private String remarks;
	private String roomId;
	private String hospitalCode;
	private String isValid;
	private String blockId;
	private String floorId;
	private String buildingCode;
	private String roomName;
	private String rackTypeName;	
	private String rackStatus;	
	private String mrdCode;	
	private String mrdSize;	
	private String mode;	
	

	public String getRackTypeName() {
		return rackTypeName;
	}



	public void setRackTypeName(String rackTypeName) {
		this.rackTypeName = rackTypeName;
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



	public String getSerialNo() {
		return serialNo;
	}



	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}



	public String getRackId() {
		return rackId;
	}



	public void setRackId(String rackId) {
		this.rackId = rackId;
	}



	public String getRackName() {
		return rackName;
	}



	public void setRackName(String rackName) {
		this.rackName = rackName;
	}



	public String getRackType() {
		return rackType;
	}



	public void setRackType(String rackType) {
		this.rackType = rackType;
	}


	public String getItemId() {
		return itemId;
	}



	public void setItemId(String itemId) {
		this.itemId = itemId;
	}



	public String getRemarks() {
		return remarks;
	}



	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}



	public String getRoomId() {
		return roomId;
	}



	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}



	public String getHospitalCode() {
		return hospitalCode;
	}



	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}



	public String getIsValid() {
		return isValid;
	}



	public void setIsValid(String isValid) {
		this.isValid = isValid;
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



	public String getBuildingCode() {
		return buildingCode;
	}



	public void setBuildingCode(String buildingCode) {
		this.buildingCode = buildingCode;
	}



	public String getRoomName() {
		return roomName;
	}



	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}



	public void reset(ActionMapping mapping,HttpServletRequest _request)
	{
	this.rackName="";
	this.rackType="-1";
	this.itemId="-1";
	this.remarks="";
	this.buildingCode="-1";
	this.blockId="-1";
	this.floorId="-1";
	this.roomName="-1";
	this.rackStatus="-1";
	this.mrdCode="-1";
	
	}



	public String getRackStatus() {
		return rackStatus;
	}



	public void setRackStatus(String rackStatus) {
		this.rackStatus = rackStatus;
	}



	public String getMrdCode() {
		return mrdCode;
	}



	public void setMrdCode(String mrdCode) {
		this.mrdCode = mrdCode;
	}



	public String getMode() {
		return mode;
	}



	public void setMode(String mode) {
		this.mode = mode;
	}



	public String getMrdSize() {
		return mrdSize;
	}



	public void setMrdSize(String mrdSize) {
		this.mrdSize = mrdSize;
	}

}
