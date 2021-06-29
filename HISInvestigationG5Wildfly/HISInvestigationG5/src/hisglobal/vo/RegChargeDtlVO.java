package hisglobal.vo;

public class RegChargeDtlVO extends ValueObject
{

	private String entryDate;
	private String departmentCode;
	private String departmentUnitCode;
	private String isValid;
	private String seatId;
	private String serviceId;
	private String tariffId;
	private String admissionNo;
	private String patAmountCollected;
	private String patSecondaryCatCode;
	private String patPrimaryCatCode;
	private String patCrNo;
	private String episodeVisitNo;
	private String episodeCode;
	private String moduleId;
	
	// private String ipAddress;
	private String systemIPAddress;// IP address of the system from which the details are entered
	private String isFree;

	public String getIsFree()
	{
		return isFree;
	}

	public void setIsFree(String isFree)
	{
		this.isFree = isFree;
	}

	/**
	 * Retrieves systemIPAddress.
	 * 
	 * @return Value of systemIPAddress.
	 */
	public String getSystemIPAddress()
	{
		return systemIPAddress;
	}

	/**
	 * Sets systemIPAddress.
	 * 
	 * @param systemIPAddress
	 */
	public void setSystemIPAddress(String systemIPAddress)
	{
		this.systemIPAddress = systemIPAddress;
	}

	public String getAdmissionNo()
	{
		return admissionNo;
	}

	public void setAdmissionNo(String admissionNo)
	{
		this.admissionNo = admissionNo;
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

	public String getEpisodeVisitNo()
	{
		return episodeVisitNo;
	}

	public void setEpisodeVisitNo(String episodeVisitNo)
	{
		this.episodeVisitNo = episodeVisitNo;
	}

	/*
	 * public String getIpAddress() { return ipAddress; } public void setIpAddress(String ipAddress) { this.ipAddress =
	 * ipAddress; }
	 */
	public String getIsValid()
	{
		return isValid;
	}

	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	public String getPatCrNo()
	{
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo)
	{
		this.patCrNo = patCrNo;
	}

	public String getPatPrimaryCatCode()
	{
		return patPrimaryCatCode;
	}

	public void setPatPrimaryCatCode(String patPrimaryCatCode)
	{
		this.patPrimaryCatCode = patPrimaryCatCode;
	}

	public String getPatSecondaryCatCode()
	{
		return patSecondaryCatCode;
	}

	public void setPatSecondaryCatCode(String patSecondaryCatCode)
	{
		this.patSecondaryCatCode = patSecondaryCatCode;
	}

	public String getSeatId()
	{
		return seatId;
	}

	public void setSeatId(String seatId)
	{
		this.seatId = seatId;
	}

	public String getServiceId()
	{
		return serviceId;
	}

	public void setServiceId(String serviceId)
	{
		this.serviceId = serviceId;
	}

	public String getTariffId()
	{
		return tariffId;
	}

	public void setTariffId(String tariffId)
	{
		this.tariffId = tariffId;
	}

	public String getPatAmountCollected()
	{
		return patAmountCollected;
	}

	public void setPatAmountCollected(String patAmountCollected)
	{
		this.patAmountCollected = patAmountCollected;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

}
