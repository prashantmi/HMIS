package hisglobal.vo;

/**
 * EpisodeReferVO is the class that specifies getters and setters for all the identifiers
 * which are used for retrieving and inserting data in the DB tables. 
 * @author AHIS
 */

public class EpisodeReferVO extends ValueObject
{
	private String patCrNo;
	private String episodeCode;
	private String episodeVisitNo;
	private String serialNo;
	private String admissionNo;
	private String isRefferInOut;
	private String wardCode;
	private String wardName;
	private String patRefGnctdHospitalCode;
	private String patRefHospitalName;
	private String reffDateTime;
	private String patRefDoctor;
	private String seatId;
	private String entryDate;
	private String isValid;
	private String systemIPAddress;//IP address of the system from which the details are entered
	private String prevEpisodeCode;
	private String prevEpisodeVisitNo;
	private String episodeDate;
	private String patRefGnctdHospitalCrno;
	private String patRefGnctdHospitalDept;
	private String patRefGnctdHospitalDeptUnit;
	private String patRefDoctorCode;

	private String deptUnitIsOnDuty;
	private String visitedToday;
	private String department;
	private String departmentCode;
	private String departmentUnit;
	private String departmentUnitCode;
	private String room;
	private String roomCode;
	private String complainDetail;
	private String refToDepartmentCode;
	private String refToDepartment;
	private String refFromDepartmentCode;
	private String refFromDepartment;
	

	/*
    Created By :- 	Akash Singh
    Date	:-	Nov-03-2014
    Used For:-	OPD Doctor Desk
	*/
	private String remarks;
	private String hospCode;
	private String referMode;
	private String extHospitalCode; 
	private String extHospitalName;
	private String extHospDoctName;
	private String hrgnumExtHospitalCrno;
	private String extHospitalDept;
	private String extHospitalDeptUnit;
	/**
	 * Retrieves refFromDepartmentCode.
	 * @return Value of refFromDepartmentCode.	
	 */
	public String getRefFromDepartmentCode()
	{
		return refFromDepartmentCode;
	}

	/**
	 * Sets refFromDepartmentCode.
	 * @param refFromDepartmentCode
	 */
	public void setRefFromDepartmentCode(String refFromDepartmentCode)
	{
		this.refFromDepartmentCode = refFromDepartmentCode;
	}

	/**
	 * Retrieves refToDepartmentCode.
	 * @return Value of refToDepartmentCode.	
	 */
	public String getRefToDepartmentCode()
	{
		return refToDepartmentCode;
	}

	/**
	 * Sets refToDepartmentCode.
	 * @param refToDepartmentCode
	 */
	public void setRefToDepartmentCode(String refToDepartmentCode)
	{
		this.refToDepartmentCode = refToDepartmentCode;
	}

	/**
	 * Retrieves refFromDepartment.
	 * @return Value of refFromDepartment.	
	 */
	public String getRefFromDepartment()
	{
		return refFromDepartment;
	}

	/**
	 * Sets refFromDepartment.
	 * @param refFromDepartment
	 */
	public void setRefFromDepartment(String refFromDepartment)
	{
		this.refFromDepartment = refFromDepartment;
	}

	/**
	 * Retrieves refToDepartment.
	 * @return Value of refToDepartment.	
	 */
	public String getRefToDepartment()
	{
		return refToDepartment;
	}

	/**
	 * Sets refToDepartment.
	 * @param refToDepartment
	 */
	public void setRefToDepartment(String refToDepartment)
	{
		this.refToDepartment = refToDepartment;
	}

	/**
	 * Retrieves complainDetail.
	 * @return Value of complainDetail.	
	 */
	public String getComplainDetail()
	{
		return complainDetail;
	}

	/**
	 * Sets complainDetail.
	 * @param complainDetail
	 */
	public void setComplainDetail(String complainDetail)
	{
		this.complainDetail = complainDetail;
	}

	/**
	 * Sets roomCode.
	 * @param roomCode
	 */
	public void setRoomCode(String roomCode)
	{
		this.roomCode = roomCode;
	}

	/**
	 * Retrieves roomCode.
	 * @return Value of roomCode.	
	 */
	public String getRoomCode()
	{
		return roomCode;
	}

	/**
	 * Sets room.
	 * @param room
	 */
	public void setRoom(String room)
	{
		this.room = room;
	}

	/**
	 * Retrieves room.
	 * @return Value of room.	
	 */
	public String getRoom()
	{
		return room;
	}

	/**
	 * Sets departmentUnitCode.
	 * @param departmentUnitCode
	 */
	public void setDepartmentUnitCode(String departmentUnitCode)
	{
		this.departmentUnitCode = departmentUnitCode;
	}

	/**
	 * Retrieves departmentUnitCode.
	 * @return Value of departmentUnitCode.	
	 */
	public String getDepartmentUnitCode()
	{
		return departmentUnitCode;
	}

	/**
	 * Sets departmentUnit.
	 * @param departmentUnit
	 */
	public void setDepartmentUnit(String departmentUnit)
	{
		this.departmentUnit = departmentUnit;
	}

	/**
	 * Retrieves departmentUnit.
	 * @return Value of departmentUnit.	
	 */
	public String getDepartmentUnit()
	{
		return departmentUnit;
	}

	/**
	 * Sets departmentCode.
	 * @param departmentCode
	 */
	public void setDepartmentCode(String departmentCode)
	{
		this.departmentCode = departmentCode;
	}

	/**
	 * Retrieves departmentCode.
	 * @return Value of departmentCode.	
	 */
	public String getDepartmentCode()
	{
		return departmentCode;
	}

	/**
	 * Sets department.
	 * @param department
	 */
	public void setDepartment(String department)
	{
		this.department = department;
	}

	/**
	 * Retrieves department.
	 * @return Value of department.	
	 */
	public String getDepartment()
	{
		return department;
	}

	/**
	 * Retrieves visitedToday.
	 * @return Value of visitedToday.	
	 */
	public String getVisitedToday()
	{
		return visitedToday;
	}

	/**
	 * Sets visitedToday.
	 * @param visitedToday
	 */
	public void setVisitedToday(String visitedToday)
	{
		this.visitedToday = visitedToday;
	}

	/**
	 * Retrieves deptUnitIsOnDuty.
	 * @return Value of deptUnitIsOnDuty.	
	 */
	public String getDeptUnitIsOnDuty()
	{
		return deptUnitIsOnDuty;
	}

	/**
	 * Sets deptUnitIsOnDuty.
	 * @param deptUnitIsOnDuty
	 */
	public void setDeptUnitIsOnDuty(String deptUnitIsOnDuty)
	{
		this.deptUnitIsOnDuty = deptUnitIsOnDuty;
	}

	public String getPatRefDoctorCode()
	{
		return patRefDoctorCode;
	}

	public void setPatRefDoctorCode(String patRefDoctorCode)
	{
		this.patRefDoctorCode = patRefDoctorCode;
	}

	/**
	 * Retrieves admissionNo.
	 * @return Value of admissionNo.	
	 */
	public String getAdmissionNo()
	{
		return admissionNo;
	}

	/**
	 * Sets admissionNo.
	 * @param admissionNo
	 */
	public void setAdmissionNo(String admissionNo)
	{
		this.admissionNo = admissionNo;
	}

	/**
	 * Retrieves entryDate.
	 * @return Value of entryDate.	
	 */
	public String getEntryDate()
	{
		return entryDate;
	}

	/**
	 * Sets entryDate.
	 * @param entryDate
	 */
	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	/**
	 * Retrieves episodeCode.
	 * @return Value of episodeCode.	
	 */
	public String getEpisodeCode()
	{
		return episodeCode;
	}

	/**
	 * Sets episodeCode.
	 * @param episodeCode
	 */
	public void setEpisodeCode(String episodeCode)
	{
		this.episodeCode = episodeCode;
	}

	/**
	 * Retrieves episodeVisitNo.
	 * @return Value of episodeVisitNo.	
	 */
	public String getEpisodeVisitNo()
	{
		return episodeVisitNo;
	}

	/**
	 * Sets episodeVisitNo.
	 * @param episodeVisitNo
	 */
	public void setEpisodeVisitNo(String episodeVisitNo)
	{
		this.episodeVisitNo = episodeVisitNo;
	}

	/**
	 * Retrieves isRefferInOut.
	 * @return Value of isRefferInOut.	
	 */
	public String getIsRefferInOut()
	{
		return isRefferInOut;
	}

	/**
	 * Sets isRefferInOut.
	 * @param isRefferInOut
	 */
	public void setIsRefferInOut(String isRefferInOut)
	{
		this.isRefferInOut = isRefferInOut;
	}

	/**
	 * Retrieves isValid.
	 * @return Value of isValid.	
	 */
	public String getIsValid()
	{
		return isValid;
	}

	/**
	 * Sets isValid.
	 * @param isValid
	 */
	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	/**
	 * Retrieves patCrNo.
	 * @return Value of patCrNo.	
	 */
	public String getPatCrNo()
	{
		return patCrNo;
	}

	/**
	 * Sets patCrNo.
	 * @param patCrNo
	 */
	public void setPatCrNo(String patCrNo)
	{
		this.patCrNo = patCrNo;
	}

	/**
	 * Retrieves patRefDoctor.
	 * @return Value of patRefDoctor.	
	 */
	public String getPatRefDoctor()
	{
		return patRefDoctor;
	}

	/**
	 * Sets patRefDoctor.
	 * @param patRefDoctor
	 */
	public void setPatRefDoctor(String patRefDoctor)
	{
		this.patRefDoctor = patRefDoctor;
	}

	/**
	 * Retrieves patRefGnctdHospitalCode.
	 * @return Value of patRefGnctdHospitalCode.	
	 */
	public String getPatRefGnctdHospitalCode()
	{
		return patRefGnctdHospitalCode;
	}

	/**
	 * Sets patRefGnctdHospitalCode.
	 * @param patRefGnctdHospitalCode
	 */
	public void setPatRefGnctdHospitalCode(String patRefGnctdHospitalCode)
	{
		this.patRefGnctdHospitalCode = patRefGnctdHospitalCode;
	}

	/**
	 * Retrieves patRefHospitalName.
	 * @return Value of patRefHospitalName.	
	 */
	public String getPatRefHospitalName()
	{
		return patRefHospitalName;
	}

	/**
	 * Sets patRefHospitalName.
	 * @param patRefHospitalName
	 */
	public void setPatRefHospitalName(String patRefHospitalName)
	{
		this.patRefHospitalName = patRefHospitalName;
	}

	/**
	 * Retrieves reffDateTime.
	 * @return Value of reffDateTime.	
	 */
	public String getReffDateTime()
	{
		return reffDateTime;
	}

	/**
	 * Sets reffDateTime.
	 * @param reffDateTime
	 */
	public void setReffDateTime(String reffDateTime)
	{
		this.reffDateTime = reffDateTime;
	}

	/**
	 * Retrieves seatId.
	 * @return Value of seatId.	
	 */
	public String getSeatId()
	{
		return seatId;
	}

	/**
	 * Sets seatId.
	 * @param seatId
	 */
	public void setSeatId(String seatId)
	{
		this.seatId = seatId;
	}

	/**
	 * Retrieves serialNo.
	 * @return Value of serialNo.	
	 */
	public String getSerialNo()
	{
		return serialNo;
	}

	/**
	 * Sets serialNo.
	 * @param serialNo
	 */
	public void setSerialNo(String serialNo)
	{
		this.serialNo = serialNo;
	}

	/**
	 * Retrieves systemIPAddress.
	 * @return Value of systemIPAddress.	
	 */
	public String getSystemIPAddress()
	{
		return systemIPAddress;
	}

	/**
	 * Sets systemIPAddress.
	 * @param systemIPAddress
	 */
	public void setSystemIPAddress(String systemIPAddress)
	{
		this.systemIPAddress = systemIPAddress;
	}

	/**
	 * Retrieves wardCode.
	 * @return Value of wardCode.	
	 */
	public String getWardCode()
	{
		return wardCode;
	}

	/**
	 * Sets wardCode.
	 * @param wardCode
	 */
	public void setWardCode(String wardCode)
	{
		this.wardCode = wardCode;
	}

	/**
	 * Retrieves wardName.
	 * @return Value of wardName.	
	 */
	public String getWardName()
	{
		return wardName;
	}

	/**
	 * Sets wardName.
	 * @param wardName
	 */
	public void setWardName(String wardName)
	{
		this.wardName = wardName;
	}

	/**
	 * Retrieves prevEpisodeCode.
	 * @return Value of prevEpisodeCode.	
	 */
	public String getPrevEpisodeCode()
	{
		return prevEpisodeCode;
	}

	/**
	 * Sets prevEpisodeCode.
	 * @param prevEpisodeCode
	 */
	public void setPrevEpisodeCode(String prevEpisodeCode)
	{
		this.prevEpisodeCode = prevEpisodeCode;
	}

	/**
	 * Retrieves prevEpisodeVisitNo.
	 * @return Value of prevEpisodeVisitNo.	
	 */
	public String getPrevEpisodeVisitNo()
	{
		return prevEpisodeVisitNo;
	}

	/**
	 * Sets prevEpisodeVisitNo.
	 * @param prevEpisodeVisitNo
	 */
	public void setPrevEpisodeVisitNo(String prevEpisodeVisitNo)
	{
		this.prevEpisodeVisitNo = prevEpisodeVisitNo;
	}

	/**
	 * Retrieves patRefGnctdHospitalCrno.
	 * @return Value of patRefGnctdHospitalCrno.	
	 */
	public String getPatRefGnctdHospitalCrno()
	{
		return patRefGnctdHospitalCrno;
	}

	/**
	 * Sets patRefGnctdHospitalCrno.
	 * @param patRefGnctdHospitalCrno
	 */
	public void setPatRefGnctdHospitalCrno(String patRefGnctdHospitalCrno)
	{
		this.patRefGnctdHospitalCrno = patRefGnctdHospitalCrno;
	}

	/**
	 * Retrieves patRefGnctdHospitalDept.
	 * @return Value of patRefGnctdHospitalDept.	
	 */
	public String getPatRefGnctdHospitalDept()
	{
		return patRefGnctdHospitalDept;
	}

	/**
	 * Sets patRefGnctdHospitalDept.
	 * @param patRefGnctdHospitalDept
	 */
	public void setPatRefGnctdHospitalDept(String patRefGnctdHospitalDept)
	{
		this.patRefGnctdHospitalDept = patRefGnctdHospitalDept;
	}

	/**
	 * Retrieves patRefGnctdHospitalDeptUnit.
	 * @return Value of patRefGnctdHospitalDeptUnit.	
	 */
	public String getPatRefGnctdHospitalDeptUnit()
	{
		return patRefGnctdHospitalDeptUnit;
	}

	/**
	 * Sets patRefGnctdHospitalDeptUnit.
	 * @param patRefGnctdHospitalDeptUnit
	 */
	public void setPatRefGnctdHospitalDeptUnit(String patRefGnctdHospitalDeptUnit)
	{
		this.patRefGnctdHospitalDeptUnit = patRefGnctdHospitalDeptUnit;
	}

	/**
	 * Retrieves episodeDate.
	 * @return Value of episodeDate.	
	 */
	public String getEpisodeDate()
	{
		return episodeDate;
	}

	/**
	 * Sets episodeDate.
	 * @param episodeDate
	 */
	public void setEpisodeDate(String episodeDate)
	{
		this.episodeDate = episodeDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getHospCode() {
		return hospCode;
	}

	public void setHospCode(String hospCode) {
		this.hospCode = hospCode;
	}

	public String getReferMode() {
		return referMode;
	}

	public void setReferMode(String referMode) {
		this.referMode = referMode;
	}

	public String getExtHospitalCode() {
		return extHospitalCode;
	}

	public void setExtHospitalCode(String extHospitalCode) {
		this.extHospitalCode = extHospitalCode;
	}

	public String getExtHospitalName() {
		return extHospitalName;
	}

	public void setExtHospitalName(String extHospitalName) {
		this.extHospitalName = extHospitalName;
	}

	public String getExtHospDoctName() {
		return extHospDoctName;
	}

	public void setExtHospDoctName(String extHospDoctName) {
		this.extHospDoctName = extHospDoctName;
	}

	public String getHrgnumExtHospitalCrno() {
		return hrgnumExtHospitalCrno;
	}

	public void setHrgnumExtHospitalCrno(String hrgnumExtHospitalCrno) {
		this.hrgnumExtHospitalCrno = hrgnumExtHospitalCrno;
	}

	public String getExtHospitalDept() {
		return extHospitalDept;
	}

	public void setExtHospitalDept(String extHospitalDept) {
		this.extHospitalDept = extHospitalDept;
	}

	public String getExtHospitalDeptUnit() {
		return extHospitalDeptUnit;
	}

	public void setExtHospitalDeptUnit(String extHospitalDeptUnit) {
		this.extHospitalDeptUnit = extHospitalDeptUnit;
	}

}
