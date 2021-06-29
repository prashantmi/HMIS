package hisglobal.vo;

public class DepartmentUnitRoomMasterVO extends ValueObject
{

	private String departmentUnitCode;
	private String departmentCode;
	private String unitCode;
	private String roomCode;
	private String capacity;
	private String sequenceNo;
	private String seatID;
	private String entryDate;
	private String isValid;

	private String effectiveFrom;
	private String effectiveTo;
	private String roomName;
	private String hospitalCode;
	private String capacityMode;

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	public String getCapacityMode() {
		return capacityMode;
	}

	public void setCapacityMode(String capacityMode) {
		this.capacityMode = capacityMode;
	}

	public String getHospitalCode()
	{
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
	}

	public DepartmentUnitRoomMasterVO()
	{
		// TODO Auto-generated constructor stub
	}

	public DepartmentUnitRoomMasterVO(String _str, String _str1)
	{
		departmentUnitCode = _str;
		roomCode = _str1;
	}

	public boolean equals(Object arg0)
	{
		if (arg0 == this) return true;
		if (!(arg0 instanceof DepartmentUnitRoomMasterVO)) return false;
		DepartmentUnitRoomMasterVO vo = (DepartmentUnitRoomMasterVO) arg0;
		return (vo.getDepartmentUnitCode().equalsIgnoreCase(this.departmentUnitCode) && vo.getRoomCode().equalsIgnoreCase(this.roomCode));
	}

	public String getCapacity()
	{
		return capacity;
	}

	public void setCapacity(String capacity)
	{
		this.capacity = capacity;
	}

	public String getDepartmentCode()
	{
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode)
	{
		this.departmentCode = departmentCode;
	}

	public String getDepartmentUnitCode()
	{
		return departmentUnitCode;
	}

	public void setDepartmentUnitCode(String departmentUnitCode)
	{
		this.departmentUnitCode = departmentUnitCode;
	}

	public String getIsValid()
	{
		return isValid;
	}

	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	public String getRoomCode()
	{
		return roomCode;
	}

	public void setRoomCode(String roomCode)
	{
		this.roomCode = roomCode;
	}

	public String getSeatID()
	{
		return seatID;
	}

	public void setSeatID(String seatID)
	{
		this.seatID = seatID;
	}

	public String getSequenceNo()
	{
		return sequenceNo;
	}

	public void setSequenceNo(String sequenceNo)
	{
		this.sequenceNo = sequenceNo;
	}

	public String getUnitCode()
	{
		return unitCode;
	}

	public void setUnitCode(String unitCode)
	{
		this.unitCode = unitCode;
	}

	public String getRoomName()
	{
		return roomName;
	}

	public void setRoomName(String roomName)
	{
		this.roomName = roomName;
	}

	public String getEffectiveFrom()
	{
		return effectiveFrom;
	}

	public void setEffectiveFrom(String effectiveFrom)
	{
		this.effectiveFrom = effectiveFrom;
	}

	public String getEffectiveTo()
	{
		return effectiveTo;
	}

	public void setEffectiveTo(String effectiveTo)
	{
		this.effectiveTo = effectiveTo;
	}

}
