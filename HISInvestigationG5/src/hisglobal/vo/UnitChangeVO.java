package hisglobal.vo;

/**
 * UnitChangeVO is the class that specifies getters and setters for all the identifiers which are used for retrieving and
 * inserting data in the DB tables.
 * 
 * @author AHIS
 */
public class UnitChangeVO extends ValueObject
{
	private String patCrNo;
	// private String serialNo ;
	private String department;
	private String departmentCode;
	private String fromDepartmentUnit;
	private String fromDepartmentUnitCode;
	private String toDepartmentUnit;
	private String toDepartmentUnitCode;
	private String episodeCode;
	private String episodeVisitNo;
	private String unitChangeReason;
	private String isValid;
	private String seatId;
	private String entryDate;
	private String departmentUnit;
	private String departmentUnitCode;
	private String room;
	private String roomCode;
	private String queNo;
	private String hospitalCode;

	/**
	 * Retrieves departmentUnit.
	 * 
	 * @return Value of departmentUnit.
	 */
	public String getDepartmentUnit()
	{
		return departmentUnit;
	}

	/**
	 * Sets departmentUnit.
	 * 
	 * @param departmentUnit
	 */
	public void setDepartmentUnit(String departmentUnit)
	{
		this.departmentUnit = departmentUnit;
	}

	/**
	 * Retrieves departmentUnitCode.
	 * 
	 * @return Value of departmentUnitCode.
	 */
	public String getDepartmentUnitCode()
	{
		return departmentUnitCode;
	}

	/**
	 * Sets departmentUnitCode.
	 * 
	 * @param departmentUnitCode
	 */
	public void setDepartmentUnitCode(String departmentUnitCode)
	{
		this.departmentUnitCode = departmentUnitCode;
	}

	/**
	 * Retrieves queNo.
	 * 
	 * @return Value of queNo.
	 */
	public String getQueNo()
	{
		return queNo;
	}

	/**
	 * Sets queNo.
	 * 
	 * @param queNo
	 */
	public void setQueNo(String queNo)
	{
		this.queNo = queNo;
	}

	/**
	 * Retrieves room.
	 * 
	 * @return Value of room.
	 */
	public String getRoom()
	{
		return room;
	}

	/**
	 * Sets room.
	 * 
	 * @param room
	 */
	public void setRoom(String room)
	{
		this.room = room;
	}

	/**
	 * Retrieves roomCode.
	 * 
	 * @return Value of roomCode.
	 */
	public String getRoomCode()
	{
		return roomCode;
	}

	/**
	 * Sets roomCode.
	 * 
	 * @param roomCode
	 */
	public void setRoomCode(String roomCode)
	{
		this.roomCode = roomCode;
	}

	/**
	 * Retrieves department.
	 * 
	 * @return Value of department.
	 */
	public String getDepartment()
	{
		return department;
	}

	/**
	 * Sets department.
	 * 
	 * @param department
	 */
	public void setDepartment(String department)
	{
		this.department = department;
	}

	/**
	 * Retrieves departmentCode.
	 * 
	 * @return Value of departmentCode.
	 */
	public String getDepartmentCode()
	{
		return departmentCode;
	}

	/**
	 * Sets departmentCode.
	 * 
	 * @param departmentCode
	 */
	public void setDepartmentCode(String departmentCode)
	{
		this.departmentCode = departmentCode;
	}

	/**
	 * Retrieves entryDate.
	 * 
	 * @return Value of entryDate.
	 */
	public String getEntryDate()
	{
		return entryDate;
	}

	/**
	 * Sets entryDate.
	 * 
	 * @param entryDate
	 */
	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	/**
	 * Retrieves episodeCode.
	 * 
	 * @return Value of episodeCode.
	 */
	public String getEpisodeCode()
	{
		return episodeCode;
	}

	/**
	 * Sets episodeCode.
	 * 
	 * @param episodeCode
	 */
	public void setEpisodeCode(String episodeCode)
	{
		this.episodeCode = episodeCode;
	}

	/**
	 * Retrieves episodeVisitNo.
	 * 
	 * @return Value of episodeVisitNo.
	 */
	public String getEpisodeVisitNo()
	{
		return episodeVisitNo;
	}

	/**
	 * Sets episodeVisitNo.
	 * 
	 * @param episodeVisitNo
	 */
	public void setEpisodeVisitNo(String episodeVisitNo)
	{
		this.episodeVisitNo = episodeVisitNo;
	}

	/**
	 * Retrieves isValid.
	 * 
	 * @return Value of isValid.
	 */
	public String getIsValid()
	{
		return isValid;
	}

	/**
	 * Sets isValid.
	 * 
	 * @param isValid
	 */
	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	/**
	 * Retrieves patCrNo.
	 * 
	 * @return Value of patCrNo.
	 */
	public String getPatCrNo()
	{
		return patCrNo;
	}

	/**
	 * Sets patCrNo.
	 * 
	 * @param patCrNo
	 */
	public void setPatCrNo(String patCrNo)
	{
		this.patCrNo = patCrNo;
	}

	/**
	 * Retrieves seatId.
	 * 
	 * @return Value of seatId.
	 */
	public String getSeatId()
	{
		return seatId;
	}

	/**
	 * Sets seatId.
	 * 
	 * @param seatId
	 */
	public void setSeatId(String seatId)
	{
		this.seatId = seatId;
	}

	/**
	 * Retrieves unitChangeReason.
	 * 
	 * @return Value of unitChangeReason.
	 */
	public String getUnitChangeReason()
	{
		return unitChangeReason;
	}

	/**
	 * Sets unitChangeReason.
	 * 
	 * @param unitChangeReason
	 */
	public void setUnitChangeReason(String unitChangeReason)
	{
		this.unitChangeReason = unitChangeReason;
	}

	/**
	 * Retrieves fromDepartmentUnit.
	 * 
	 * @return Value of fromDepartmentUnit.
	 */
	public String getFromDepartmentUnit()
	{
		return fromDepartmentUnit;
	}

	/**
	 * Sets fromDepartmentUnit.
	 * 
	 * @param fromDepartmentUnit
	 */
	public void setFromDepartmentUnit(String fromDepartmentUnit)
	{
		this.fromDepartmentUnit = fromDepartmentUnit;
	}

	/**
	 * Retrieves fromDepartmentUnitCode.
	 * 
	 * @return Value of fromDepartmentUnitCode.
	 */
	public String getFromDepartmentUnitCode()
	{
		return fromDepartmentUnitCode;
	}

	/**
	 * Sets fromDepartmentUnitCode.
	 * 
	 * @param fromDepartmentUnitCode
	 */
	public void setFromDepartmentUnitCode(String fromDepartmentUnitCode)
	{
		this.fromDepartmentUnitCode = fromDepartmentUnitCode;
	}

	/**
	 * Retrieves toDepartmentUnit.
	 * 
	 * @return Value of toDepartmentUnit.
	 */
	public String getToDepartmentUnit()
	{
		return toDepartmentUnit;
	}

	/**
	 * Sets toDepartmentUnit.
	 * 
	 * @param toDepartmentUnit
	 */
	public void setToDepartmentUnit(String toDepartmentUnit)
	{
		this.toDepartmentUnit = toDepartmentUnit;
	}

	/**
	 * Retrieves toDepartmentUnitCode.
	 * 
	 * @return Value of toDepartmentUnitCode.
	 */
	public String getToDepartmentUnitCode()
	{
		return toDepartmentUnitCode;
	}

	/**
	 * Sets toDepartmentUnitCode.
	 * 
	 * @param toDepartmentUnitCode
	 */
	public void setToDepartmentUnitCode(String toDepartmentUnitCode)
	{
		this.toDepartmentUnitCode = toDepartmentUnitCode;
	}

	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

}
