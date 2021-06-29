package hisglobal.vo;


public class RosterMasterVO extends ValueObject
{
	private static final long serialVersionUID=1L;
	private String departmentUnitCode;
	private String roomCode;
	private String roomSequenceNo;
	private String departmentUnit;
	private String weekOfMonth;
	private String dayOfWeek;
	private String shiftCode;
	private String shift;
	private String unitSequenceNo;
	private String opdName;
	private String isValid;
	private String effectDate;
	private String inactiveFromDate;
	private String inactiveTillDate;
	private String entryDate;
	private String seatID;
	private String departmentCode;
	private String department;
	private String hospitalCode;
	private String roomUsability;
	private String roomCapcity;
	private String roomCapacityInUnitRoomMst;

	public String getRoomUsability() {
		return roomUsability;
	}

	public void setRoomUsability(String roomUsability) {
		this.roomUsability = roomUsability;
	}

	public String getHospitalCode()
	{
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
	}

	public String getEffectDate()
	{
		return effectDate;
	}

	public void setEffectDate(String effectDate)
	{
		this.effectDate = effectDate;
	}

	public String getInactiveFromDate()
	{
		return inactiveFromDate;
	}

	public void setInactiveFromDate(String inactiveFromDate)
	{
		this.inactiveFromDate = inactiveFromDate;
	}

	public String getInactiveTillDate()
	{
		return inactiveTillDate;
	}

	public void setInactiveTillDate(String inactiveTillDate)
	{
		this.inactiveTillDate = inactiveTillDate;
	}

	public String getUnitSequenceNo()
	{
		return unitSequenceNo;
	}

	public void setUnitSequenceNo(String unitSequenceNo)
	{
		this.unitSequenceNo = unitSequenceNo;
	}

	public String getDepartmentUnit()
	{
		return departmentUnit;
	}

	public void setDepartmentUnit(String departmentUnit)
	{
		this.departmentUnit = departmentUnit;
	}

	public String getDepartmentUnitCode()
	{
		return departmentUnitCode;
	}

	public void setDepartmentUnitCode(String departmentUnitCode)
	{
		this.departmentUnitCode = departmentUnitCode;
	}

	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	public String getIsValid()
	{
		return isValid;
	}

	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	public String getOpdName()
	{
		return opdName;
	}

	public void setOpdName(String opdName)
	{
		this.opdName = opdName;
	}

	public String getSeatID()
	{
		return seatID;
	}

	public void setSeatID(String seatID)
	{
		this.seatID = seatID;
	}

	public String getDayOfWeek()
	{
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek)
	{
		this.dayOfWeek = dayOfWeek;
	}

	public String getShift()
	{
		return shift;
	}

	public void setShift(String shift)
	{
		this.shift = shift;
	}

	public String getShiftCode()
	{
		return shiftCode;
	}

	public void setShiftCode(String shiftCode)
	{
		this.shiftCode = shiftCode;
	}

	public String getWeekOfMonth()
	{
		return weekOfMonth;
	}

	public void setWeekOfMonth(String weekOfMonth)
	{
		this.weekOfMonth = weekOfMonth;
	}

	public String getDepartment()
	{
		return department;
	}

	public void setDepartment(String department)
	{
		this.department = department;
	}

	public String getDepartmentCode()
	{
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode)
	{
		this.departmentCode = departmentCode;
	}

	public boolean equals(Object obj)
	{
		//System.out.println("RosterMasterVO equals:  ");
		if (obj == this) // (2)
		return true;
		if (obj == null || !(obj instanceof RosterMasterVO)) // (3)
		return false;

		//Entry objEntry= (Entry)obj;
		RosterMasterVO objRosterMasterVO = (RosterMasterVO) obj;
		//System.out.println("objEnt"+objEntry.value);
		//System.out.println("objEnt"+this.value);
		//System.out.println("objRosterMasterVO.departmentUnitCode::: " + objRosterMasterVO.getDepartmentUnitCode());
		//System.out.println("objRosterMasterVO.weekOfMonth::: " + objRosterMasterVO.getWeekOfMonth());
		//System.out.println("objRosterMasterVO.dayOfWeek::: " + objRosterMasterVO.getDayOfWeek());
		//System.out.println("objRosterMasterVO.shiftCode::: " + objRosterMasterVO.getShiftCode());
		//System.out.println("departmentUnitCode::: " + this.departmentUnitCode);
		//System.out.println("weekOfMonth::: " + this.weekOfMonth);
		//System.out.println("dayOfWeek::: " + this.dayOfWeek);
		//System.out.println("shiftCode::: " + this.shiftCode);

		if (objRosterMasterVO.getDepartmentUnitCode() == null || objRosterMasterVO.getWeekOfMonth() == null
				|| objRosterMasterVO.getDayOfWeek() == null || objRosterMasterVO.getShiftCode() == null) return false;
		else if (objRosterMasterVO.getDepartmentUnitCode().equals(this.departmentUnitCode)
				&& objRosterMasterVO.getWeekOfMonth().equals(this.weekOfMonth) && objRosterMasterVO.getDayOfWeek().equals(this.dayOfWeek)
				&& objRosterMasterVO.getShiftCode().equals(this.shiftCode)) return true;
		else return false;

	}

	public String getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}

	public String getRoomSequenceNo() {
		return roomSequenceNo;
	}

	public void setRoomSequenceNo(String roomSequenceNo) {
		this.roomSequenceNo = roomSequenceNo;
	}

	public String getRoomCapcity() {
		return roomCapcity;
	}

	public void setRoomCapcity(String roomCapcity) {
		this.roomCapcity = roomCapcity;
	}

	public String getRoomCapacityInUnitRoomMst() {
		return roomCapacityInUnitRoomMst;
	}

	public void setRoomCapacityInUnitRoomMst(String roomCapacityInUnitRoomMst) {
		this.roomCapacityInUnitRoomMst = roomCapacityInUnitRoomMst;
	}



	
}
