package hisglobal.vo;

/**
 * @author Pragya
 * Creation Date: 30-May-2011
 */

import hisglobal.vo.ValueObject;

public class GuestHouseMasterVO extends ValueObject
{
	private String strHospitalCode;
	private String strGuestHouseId;
	private String strGuestHouseCode;
	private String strBuildingCode;
	private String strBlockId;
	private String strFloorId;
	private String strRoomId;
	private String strTotalRooms;
	private String strTotalBeds;
	private String strGuestHouseAttendant;
	private String strEffectiveFrom;
	private String strEffectiveTo;
	private String strRemarks;
	private String strStatusFlag;
	private String strRent;
	private String strReqType;
	private String strIsValid;
	private String strEntryDate;
	private String strSeatID;
	private String strLastModifyDate;
	private String strLastModifiedSeatID;

	private String strBuildingName;
	private String strBlockName;
	private String strFloorName;
	private String strRoomName;
	private String strGuestHouseAddress;

	public String getStrHospitalCode()
	{
		return strHospitalCode;
	}

	public void setStrHospitalCode(String strHospitalCode)
	{
		this.strHospitalCode = strHospitalCode;
	}

	public String getStrGuestHouseId()
	{
		return strGuestHouseId;
	}

	public void setStrGuestHouseId(String strGuestHouseId)
	{
		this.strGuestHouseId = strGuestHouseId;
	}

	public String getStrGuestHouseCode()
	{
		return strGuestHouseCode;
	}

	public void setStrGuestHouseCode(String strGuestHouseCode)
	{
		this.strGuestHouseCode = strGuestHouseCode;
	}

	public String getStrBuildingCode()
	{
		return strBuildingCode;
	}

	public void setStrBuildingCode(String strBuildingCode)
	{
		this.strBuildingCode = strBuildingCode;
	}

	public String getStrBlockId()
	{
		return strBlockId;
	}

	public void setStrBlockId(String strBlockId)
	{
		this.strBlockId = strBlockId;
	}

	public String getStrFloorId()
	{
		return strFloorId;
	}

	public void setStrFloorId(String strFloorId)
	{
		this.strFloorId = strFloorId;
	}

	public String getStrRoomId()
	{
		return strRoomId;
	}

	public void setStrRoomId(String strRoomId)
	{
		this.strRoomId = strRoomId;
	}

	public String getStrTotalRooms()
	{
		return strTotalRooms;
	}

	public void setStrTotalRooms(String strTotalRooms)
	{
		this.strTotalRooms = strTotalRooms;
	}

	public String getStrTotalBeds()
	{
		return strTotalBeds;
	}

	public void setStrTotalBeds(String strTotalBeds)
	{
		this.strTotalBeds = strTotalBeds;
	}

	public String getStrGuestHouseAttendant()
	{
		return strGuestHouseAttendant;
	}

	public void setStrGuestHouseAttendant(String strGuestHouseAttendant)
	{
		this.strGuestHouseAttendant = strGuestHouseAttendant;
	}

	public String getStrEffectiveFrom()
	{
		return strEffectiveFrom;
	}

	public void setStrEffectiveFrom(String strEffectiveFrom)
	{
		this.strEffectiveFrom = strEffectiveFrom;
	}

	public String getStrEffectiveTo()
	{
		return strEffectiveTo;
	}

	public void setStrEffectiveTo(String strEffectiveTo)
	{
		this.strEffectiveTo = strEffectiveTo;
	}

	public String getStrRemarks()
	{
		return strRemarks;
	}

	public void setStrRemarks(String strRemarks)
	{
		this.strRemarks = strRemarks;
	}

	public String getStrStatusFlag()
	{
		return strStatusFlag;
	}

	public void setStrStatusFlag(String strStatusFlag)
	{
		this.strStatusFlag = strStatusFlag;
	}

	public String getStrRent()
	{
		return strRent;
	}

	public void setStrRent(String strRent)
	{
		this.strRent = strRent;
	}

	public String getStrReqType()
	{
		return strReqType;
	}

	public void setStrReqType(String strReqType)
	{
		this.strReqType = strReqType;
	}

	public String getStrIsValid()
	{
		return strIsValid;
	}

	public void setStrIsValid(String strIsValid)
	{
		this.strIsValid = strIsValid;
	}

	public String getStrEntryDate()
	{
		return strEntryDate;
	}

	public void setStrEntryDate(String strEntryDate)
	{
		this.strEntryDate = strEntryDate;
	}

	public String getStrSeatID()
	{
		return strSeatID;
	}

	public void setStrSeatID(String strSeatID)
	{
		this.strSeatID = strSeatID;
	}

	public String getStrLastModifyDate()
	{
		return strLastModifyDate;
	}

	public void setStrLastModifyDate(String strLastModifyDate)
	{
		this.strLastModifyDate = strLastModifyDate;
	}

	public String getStrLastModifiedSeatID()
	{
		return strLastModifiedSeatID;
	}

	public void setStrLastModifiedSeatID(String strLastModifiedSeatID)
	{
		this.strLastModifiedSeatID = strLastModifiedSeatID;
	}

	public String getStrBuildingName()
	{
		return strBuildingName;
	}

	public void setStrBuildingName(String strBuildingName)
	{
		this.strBuildingName = strBuildingName;
	}

	public String getStrBlockName()
	{
		return strBlockName;
	}

	public void setStrBlockName(String strBlockName)
	{
		this.strBlockName = strBlockName;
	}

	public String getStrFloorName()
	{
		return strFloorName;
	}

	public void setStrFloorName(String strFloorName)
	{
		this.strFloorName = strFloorName;
	}

	public String getStrRoomName()
	{
		return strRoomName;
	}

	public void setStrRoomName(String strRoomName)
	{
		this.strRoomName = strRoomName;
	}

	public String getStrGuestHouseAddress()
	{
		this.strGuestHouseAddress = "";
		if (this.strBuildingName != null && !this.strBuildingName.trim().equals("")) this.strGuestHouseAddress = this.strBuildingName;
		if (this.strBlockName != null && !this.strBlockName.trim().equals("")) this.strGuestHouseAddress = this.strBlockName + ", " +  this.strGuestHouseAddress;
		if (this.strFloorName != null && !this.strFloorName.trim().equals("")) this.strGuestHouseAddress = this.strFloorName + ", " +  this.strGuestHouseAddress;
		if (this.strRoomName != null && !this.strRoomName.trim().equals("")) this.strGuestHouseAddress = this.strRoomName  + ", " +  this.strGuestHouseAddress;
		return this.strGuestHouseAddress;
	}

	public void setStrGuestHouseAddress(String strGuestHouseAddress)
	{
		this.strGuestHouseAddress = strGuestHouseAddress;
	}

}
