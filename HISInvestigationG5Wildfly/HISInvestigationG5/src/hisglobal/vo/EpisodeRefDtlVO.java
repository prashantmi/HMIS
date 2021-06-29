package hisglobal.vo;

public class EpisodeRefDtlVO extends ValueObject
{
	private String patCrNo;
	private String episodeCode;
	private String episodeVisitNo;
	private String serialNo;
	private String patAdmNo;
	private String fromDepartmentCode;
	private String fromDepartment;
	private String fromDepartmentUnitCode;
	private String fromDepartmentUnit;
	private String fromDoctorCode;
	private String fromWardCode;
	private String fromWard;
	private String fromEpisodeCode;
	private String toDepartmentCode;
	private String toDepartment;
	private String toDepartmentUnitCode;
	private String toDepartmentUnit;
	private String toDoctorCode;
	private String toWardCode;
	private String toWard;
	private String toEpisodeCode;
	private String toEpisodeVisitNo;
	private String externalHospitalCode;
	private String externalHospitalName;
	private String externalHospitalDoctorName;
	private String externalHospitalPatCrNo;
	private String externalHospitalDepartment;
	private String externalHospitalDepartmentUnit;
	private String isRefferInOut;
	private String reffDateTime;
	private String episodeReferAcceptDate;
	private String systemIPAddress;
	private String seatId;
	private String entryDate;
	private String isValid;
	private String lastModifiedDate;
	private String lastModifiedSeatId;
	private String remarks;
	private String patName;
	private String deptUnitIsOnDuty;
	private String deptUnitIsClosed;
	private String isAccepted;
	private String mlcNo;

	
	

	public String getMlcNo() {
		return mlcNo;
	}

	public void setMlcNo(String mlcNo) {
		this.mlcNo = mlcNo;
	}

	public String getDeptUnitIsClosed() {
		return deptUnitIsClosed;
	}

	public void setDeptUnitIsClosed(String deptUnitIsClosed) {
		this.deptUnitIsClosed = deptUnitIsClosed;
	}

	public String getDeptUnitIsOnDuty() {
		return deptUnitIsOnDuty;
	}

	public void setDeptUnitIsOnDuty(String deptUnitIsOnDuty) {
		this.deptUnitIsOnDuty = deptUnitIsOnDuty;
	}

	public String getPatName()
	{
		return patName;
	}

	public void setPatName(String patName)
	{
		this.patName = patName;
	}

	public String getFromDepartment()
	{
		return fromDepartment;
	}

	public void setFromDepartment(String fromDepartment)
	{
		this.fromDepartment = fromDepartment;
	}

	public String getFromDepartmentUnit()
	{
		return fromDepartmentUnit;
	}

	public void setFromDepartmentUnit(String fromDepartmentUnit)
	{
		this.fromDepartmentUnit = fromDepartmentUnit;
	}

	public String getFromWard()
	{
		return fromWard;
	}

	public void setFromWard(String fromWard)
	{
		this.fromWard = fromWard;
	}

	public String getToDepartment()
	{
		return toDepartment;
	}

	public void setToDepartment(String toDepartment)
	{
		this.toDepartment = toDepartment;
	}

	public String getToDepartmentUnit()
	{
		return toDepartmentUnit;
	}

	public void setToDepartmentUnit(String toDepartmentUnit)
	{
		this.toDepartmentUnit = toDepartmentUnit;
	}

	public String getToWard()
	{
		return toWard;
	}

	public void setToWard(String toWard)
	{
		this.toWard = toWard;
	}

	

	public String getPatAdmNo() {
		return patAdmNo;
	}

	public void setPatAdmNo(String patAdmNo) {
		this.patAdmNo = patAdmNo;
	}

	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	public String getEpisodeCode()
	{
		return episodeCode;
	}

	public void setEpisodeCode(String episodeCode)
	{
		this.episodeCode = episodeCode;
	}

	public String getEpisodeReferAcceptDate()
	{
		return episodeReferAcceptDate;
	}

	public void setEpisodeReferAcceptDate(String episodeReferAcceptDate)
	{
		this.episodeReferAcceptDate = episodeReferAcceptDate;
	}

	public String getEpisodeVisitNo()
	{
		return episodeVisitNo;
	}

	public void setEpisodeVisitNo(String episodeVisitNo)
	{
		this.episodeVisitNo = episodeVisitNo;
	}

	public String getExternalHospitalCode()
	{
		return externalHospitalCode;
	}

	public void setExternalHospitalCode(String externalHospitalCode)
	{
		this.externalHospitalCode = externalHospitalCode;
	}

	public String getExternalHospitalDepartment()
	{
		return externalHospitalDepartment;
	}

	public void setExternalHospitalDepartment(String externalHospitalDepartment)
	{
		this.externalHospitalDepartment = externalHospitalDepartment;
	}

	public String getExternalHospitalDepartmentUnit()
	{
		return externalHospitalDepartmentUnit;
	}

	public void setExternalHospitalDepartmentUnit(String externalHospitalDepartmentUnit)
	{
		this.externalHospitalDepartmentUnit = externalHospitalDepartmentUnit;
	}

	public String getExternalHospitalDoctorName()
	{
		return externalHospitalDoctorName;
	}

	public void setExternalHospitalDoctorName(String externalHospitalDoctorName)
	{
		this.externalHospitalDoctorName = externalHospitalDoctorName;
	}

	public String getExternalHospitalName()
	{
		return externalHospitalName;
	}

	public void setExternalHospitalName(String externalHospitalName)
	{
		this.externalHospitalName = externalHospitalName;
	}

	public String getExternalHospitalPatCrNo()
	{
		return externalHospitalPatCrNo;
	}

	public void setExternalHospitalPatCrNo(String externalHospitalPatCrNo)
	{
		this.externalHospitalPatCrNo = externalHospitalPatCrNo;
	}

	public String getFromDepartmentCode()
	{
		return fromDepartmentCode;
	}

	public void setFromDepartmentCode(String fromDepartmentCode)
	{
		this.fromDepartmentCode = fromDepartmentCode;
	}

	public String getFromDepartmentUnitCode()
	{
		return fromDepartmentUnitCode;
	}

	public void setFromDepartmentUnitCode(String fromDepartmentUnitCode)
	{
		this.fromDepartmentUnitCode = fromDepartmentUnitCode;
	}

	public String getFromDoctorCode()
	{
		return fromDoctorCode;
	}

	public void setFromDoctorCode(String fromDoctorCode)
	{
		this.fromDoctorCode = fromDoctorCode;
	}

	public String getFromWardCode()
	{
		return fromWardCode;
	}

	public void setFromWardCode(String fromWardCode)
	{
		this.fromWardCode = fromWardCode;
	}

	public String getIsRefferInOut()
	{
		return isRefferInOut;
	}

	public void setIsRefferInOut(String isRefferInOut)
	{
		this.isRefferInOut = isRefferInOut;
	}

	public String getIsValid()
	{
		return isValid;
	}

	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	public String getLastModifiedDate()
	{
		return lastModifiedDate;
	}

	public void setLastModifiedDate(String lastModifiedDate)
	{
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getLastModifiedSeatId()
	{
		return lastModifiedSeatId;
	}

	public void setLastModifiedSeatId(String lastModifiedSeatId)
	{
		this.lastModifiedSeatId = lastModifiedSeatId;
	}

	public String getPatCrNo()
	{
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo)
	{
		this.patCrNo = patCrNo;
	}

	public String getReffDateTime()
	{
		return reffDateTime;
	}

	public void setReffDateTime(String reffDateTime)
	{
		this.reffDateTime = reffDateTime;
	}

	public String getRemarks()
	{
		return remarks;
	}

	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}

	public String getSeatId()
	{
		return seatId;
	}

	public void setSeatId(String seatId)
	{
		this.seatId = seatId;
	}

	public String getSerialNo()
	{
		return serialNo;
	}

	public void setSerialNo(String serialNo)
	{
		this.serialNo = serialNo;
	}

	public String getSystemIPAddress()
	{
		return systemIPAddress;
	}

	public void setSystemIPAddress(String systemIPAddress)
	{
		this.systemIPAddress = systemIPAddress;
	}

	public String getToDepartmentCode()
	{
		return toDepartmentCode;
	}

	public void setToDepartmentCode(String toDepartmentCode)
	{
		this.toDepartmentCode = toDepartmentCode;
	}

	public String getToDepartmentUnitCode()
	{
		return toDepartmentUnitCode;
	}

	public void setToDepartmentUnitCode(String toDepartmentUnitCode)
	{
		this.toDepartmentUnitCode = toDepartmentUnitCode;
	}

	public String getToDoctorCode()
	{
		return toDoctorCode;
	}

	public void setToDoctorCode(String toDoctorCode)
	{
		this.toDoctorCode = toDoctorCode;
	}

	public String getToEpisodeCode()
	{
		return toEpisodeCode;
	}

	public void setToEpisodeCode(String toEpisodeCode)
	{
		this.toEpisodeCode = toEpisodeCode;
	}

	public String getToEpisodeVisitNo()
	{
		return toEpisodeVisitNo;
	}

	public void setToEpisodeVisitNo(String toEpisodeVisitNo)
	{
		this.toEpisodeVisitNo = toEpisodeVisitNo;
	}

	public String getToWardCode()
	{
		return toWardCode;
	}

	public void setToWardCode(String toWardCode)
	{
		this.toWardCode = toWardCode;
	}

	public String getFromEpisodeCode()
	{
		return fromEpisodeCode;
	}

	public void setFromEpisodeCode(String fromEpisodeCode)
	{
		this.fromEpisodeCode = fromEpisodeCode;
	}

	public String getIsAccepted() {
		return isAccepted;
	}

	public void setIsAccepted(String isAccepted) {
		this.isAccepted = isAccepted;
	}

}
