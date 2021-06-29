package hisglobal.vo;

public class LaborRoomAreaTypeMasterVO extends ValueObject
{
	private String laborRoomAreaTypeId;
	private String slNo;
	private String hospitalCode;
	private String laborRoomAreaType;
	private String isValid;
	private String query;
	private String seatID;
	private String isAreaBound;
	private String entryDate;
	private String lastModifyDate;
	private String lastModifiedSeatID;

	public String getLaborRoomAreaTypeId()
	{
		return laborRoomAreaTypeId;
	}

	public void setLaborRoomAreaTypeId(String laborRoomAreaTypeId)
	{
		this.laborRoomAreaTypeId = laborRoomAreaTypeId;
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

	public String getLaborRoomAreaType()
	{
		return laborRoomAreaType;
	}

	public void setLaborRoomAreaType(String laborRoomAreaType)
	{
		this.laborRoomAreaType = laborRoomAreaType;
	}

	public String getIsValid()
	{
		return isValid;
	}

	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	public String getQuery()
	{
		return query;
	}

	public void setQuery(String query)
	{
		this.query = query;
	}

	public String getSeatID()
	{
		return seatID;
	}

	public void setSeatID(String seatID)
	{
		this.seatID = seatID;
	}

	public String getIsAreaBound()
	{
		return isAreaBound;
	}

	public void setIsAreaBound(String isAreaBound)
	{
		this.isAreaBound = isAreaBound;
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
