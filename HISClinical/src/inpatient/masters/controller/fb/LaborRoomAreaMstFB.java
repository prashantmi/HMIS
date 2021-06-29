package inpatient.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class LaborRoomAreaMstFB extends ActionForm {
	
	private String laborRoom;
	private String laborRoomAreaId;
	private String slNo;
	private String hospitalCode;
	private String laborRoomId;
	private String laborRoomAreaType;
	private String areaId;
	private String isValid;
	private String seatID;
	private String laborRoomAreaDescription;
	private String entryDate;
	private String lastModifyDate;
	private String lastModifiedSeatID;
	private String hmode;
	private String chk;

	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
	}

	public String getHmode()
	{
		return hmode;
	}

	public void setHmode(String hmode)
	{
		this.hmode = hmode;
	}
	
	public String getChk() {
		return chk;
	}

	public void setChk(String chk) {
		this.chk = chk;
	}
	
	
	public String getLaborRoom()
	{
		return laborRoom;
	}
	
	public void setLaborRoom(String laborRoom)
	{
		this.laborRoom = laborRoom;
	}

		
	public String getLaborRoomAreaId()
	{
		return laborRoomAreaId;
	}

	public void setLaborRoomAreaId(String laborRoomAreaId)
	{
		this.laborRoomAreaId = laborRoomAreaId;
	}

	public String getSlNo()
	{
		return slNo;
	}

	public void setSlNo(String slNo)
	{
		this.slNo = slNo;
	}

	public String getHospitalCode()
	{
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
	}

	public String getLaborRoomId()
	{
		return laborRoomId;
	}

	public void setLaborRoomId(String laborRoomId)
	{
		this.laborRoomId = laborRoomId;
	}

	public String getLaborRoomAreaType()
	{
		return laborRoomAreaType;
	}

	public void setLaborRoomAreaType(String laborRoomAreaType)
	{
		this.laborRoomAreaType = laborRoomAreaType;
	}

	public String getAreaId()
	{
		return areaId;
	}

	public void setAreaId(String areaId)
	{
		this.areaId = areaId;
	}

	public String getIsValid()
	{
		return isValid;
	}

	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	public String getSeatID()
	{
		return seatID;
	}

	public void setSeatID(String seatID)
	{
		this.seatID = seatID;
	}

	public String getLaborRoomAreaDescription()
	{
		return laborRoomAreaDescription;
	}

	public void setLaborRoomAreaDescription(String laborRoomAreaDescription)
	{
		this.laborRoomAreaDescription = laborRoomAreaDescription;
	}

	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	public String getLastModifyDate()
	{
		return lastModifyDate;
	}

	public void setLastModifyDate(String lastModifyDate)
	{
		this.lastModifyDate = lastModifyDate;
	}

	public String getLastModifiedSeatID()
	{
		return lastModifiedSeatID;
	}

	public void setLastModifiedSeatID(String lastModifiedSeatID)
	{
		this.lastModifiedSeatID = lastModifiedSeatID;
	}
}
