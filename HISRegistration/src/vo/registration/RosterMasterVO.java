/**
 * 
 */
package vo.registration;

import hisglobal.vo.ValueObject;

/**
 * @author s.singaravelan
 * DATE : 28-Jan-2014
 */
public class RosterMasterVO extends ValueObject
{
	private static final long serialVersionUID=1L;
	private String strDeptUnitCode;
	private String strRoomCode;
	private String strRoomSequence;
	private String departmentUnit;
	private String weekOfMonth;
	private String dayOfWeek;
	private String strShiftCode;
	private String strShiftName;
	private String unitSequenceNo;
	private String strOpdName;
	private String strIsValid;
	private String effectDate;
	private String inactiveFromDate;
	private String inactiveTillDate;
	private String entryDate;
	private String strSeatId;
	private String strDeptCode;
	private String strDeptName;
	private String strHospitalCode;
	private String roomUsability;
	private String strRoomCapacity;
	private String strRoomCapacityInUnitRoomMst;
	
	private String strMsgString;
	private String strMsgType;



	public String getRoomUsability() {
		return roomUsability;
	}

	public void setRoomUsability(String roomUsability) {
		this.roomUsability = roomUsability;
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

	
	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	public String getDayOfWeek()
	{
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek)
	{
		this.dayOfWeek = dayOfWeek;
	}

	public String getWeekOfMonth()
	{
		return weekOfMonth;
	}

	public void setWeekOfMonth(String weekOfMonth)
	{
		this.weekOfMonth = weekOfMonth;
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

		if (objRosterMasterVO.getStrDeptUnitCode() == null || objRosterMasterVO.getWeekOfMonth() == null
				|| objRosterMasterVO.getDayOfWeek() == null || objRosterMasterVO.getStrShiftCode() == null) return false;
		else if (objRosterMasterVO.getStrDeptUnitCode().equals(this.getStrDeptUnitCode())
				&& objRosterMasterVO.getWeekOfMonth().equals(this.weekOfMonth) && objRosterMasterVO.getDayOfWeek().equals(this.dayOfWeek)
				&& objRosterMasterVO.getStrShiftCode().equals(this.getStrShiftCode())) return true;
		else return false;

	}

	public String getStrDeptUnitCode() {
		return strDeptUnitCode;
	}

	public void setStrDeptUnitCode(String strDeptUnitCode) {
		this.strDeptUnitCode = strDeptUnitCode;
	}

	public String getStrRoomCode() {
		return strRoomCode;
	}

	public void setStrRoomCode(String strRoomCode) {
		this.strRoomCode = strRoomCode;
	}

	public String getStrRoomSequence() {
		return strRoomSequence;
	}

	public void setStrRoomSequence(String strRoomSequence) {
		this.strRoomSequence = strRoomSequence;
	}

	public String getStrShiftCode() {
		return strShiftCode;
	}

	public void setStrShiftCode(String strShiftCode) {
		this.strShiftCode = strShiftCode;
	}

	public String getStrShiftName() {
		return strShiftName;
	}

	public void setStrShiftName(String strShiftName) {
		this.strShiftName = strShiftName;
	}

	public String getStrOpdName() {
		return strOpdName;
	}

	public void setStrOpdName(String strOpdName) {
		this.strOpdName = strOpdName;
	}

	public String getStrIsValid() {
		return strIsValid;
	}

	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
	}

	public String getStrSeatId() {
		return strSeatId;
	}

	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}

	public String getStrDeptCode() {
		return strDeptCode;
	}

	public void setStrDeptCode(String strDeptCode) {
		this.strDeptCode = strDeptCode;
	}

	public String getStrDeptName() {
		return strDeptName;
	}

	public void setStrDeptName(String strDeptName) {
		this.strDeptName = strDeptName;
	}

	public String getStrHospitalCode() {
		return strHospitalCode;
	}

	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	public String getStrRoomCapacity() {
		return strRoomCapacity;
	}

	public void setStrRoomCapacity(String strRoomCapacity) {
		this.strRoomCapacity = strRoomCapacity;
	}

	public String getStrRoomCapacityInUnitRoomMst() {
		return strRoomCapacityInUnitRoomMst;
	}

	public void setStrRoomCapacityInUnitRoomMst(String strRoomCapacityInUnitRoomMst) {
		this.strRoomCapacityInUnitRoomMst = strRoomCapacityInUnitRoomMst;
	}

	public String getStrMsgString() {
		return strMsgString;
	}

	public void setStrMsgString(String strMsgString) {
		this.strMsgString = strMsgString;
	}

	public String getStrMsgType() {
		return strMsgType;
	}

	public void setStrMsgType(String strMsgType) {
		this.strMsgType = strMsgType;
	}

	


	
}
