package hisglobal.vo;

public class EpisodeActionDtlVO extends ValueObject
{
	private String episodeCode;
	private String episodeVisitNo;
	private String patCrNo;
	private String episodeActionCode;
	private String diagonisticTypeCode;
	private String diagnosticDesc;
	private String complainDtl;
	private String remarks;
	private String episodeActionDate;
	private String episodeActionTime;
	private String departmentCode;
	private String wardCode;
	private String patRefGnctdHospitalCode;
	private String patRefHospitalName;
	private String entryDate;
	private String seatId;
	private String isValid;
	private String hospitalCode;

	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

	public String getComplainDtl()
	{
		return complainDtl;
	}

	public void setComplainDtl(String complainDtl)
	{
		this.complainDtl = complainDtl;
	}

	public String getDepartmentCode()
	{
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode)
	{
		this.departmentCode = departmentCode;
	}

	public String getDiagonisticTypeCode()
	{
		return diagonisticTypeCode;
	}

	public void setDiagonisticTypeCode(String diagonisticTypeCode)
	{
		this.diagonisticTypeCode = diagonisticTypeCode;
	}

	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	public String getEpisodeActionCode()
	{
		return episodeActionCode;
	}

	public void setEpisodeActionCode(String episodeActionCode)
	{
		this.episodeActionCode = episodeActionCode;
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

	public String getPatRefGnctdHospitalCode()
	{
		return patRefGnctdHospitalCode;
	}

	public void setPatRefGnctdHospitalCode(String patRefGnctdHospitalCode)
	{
		this.patRefGnctdHospitalCode = patRefGnctdHospitalCode;
	}

	public String getPatRefHospitalName()
	{
		return patRefHospitalName;
	}

	public void setPatRefHospitalName(String patRefHospitalName)
	{
		this.patRefHospitalName = patRefHospitalName;
	}

	public String getSeatId()
	{
		return seatId;
	}

	public void setSeatId(String seatId)
	{
		this.seatId = seatId;
	}

	public String getWardCode()
	{
		return wardCode;
	}

	public void setWardCode(String wardCode)
	{
		this.wardCode = wardCode;
	}

	public String getEpisodeActionDate()
	{
		return episodeActionDate;
	}

	public void setEpisodeActionDate(String episodeActionDate)
	{
		this.episodeActionDate = episodeActionDate;
	}

	public String getEpisodeActionTime()
	{
		return episodeActionTime;
	}

	public void setEpisodeActionTime(String episodeActionTime)
	{
		this.episodeActionTime = episodeActionTime;
	}

	public String getDiagnosticDesc()
	{
		return diagnosticDesc;
	}

	public void setDiagnosticDesc(String diagnosticDesc)
	{
		this.diagnosticDesc = diagnosticDesc;
	}

	public String getRemarks()
	{
		return remarks;
	}

	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}

}
