package hisglobal.vo;

/**
 * PatProfileDtlVO is the class that specifies getters and setters for all the identifiers which are used for retrieving and
 * inserting data in the DB tables.
 * 
 * @author AHIS
 */
public class PatProfileDtlVO extends ValueObject
{
	private String patProfileId;
	private String serialNo;
	private String patCrNo;
	private String departmentCode;
	private String departmentUnitCode;
	private String patProfileHeader;
	private String patProfileRemark;
	private String isValid;
	private String seatId;
	private String entryDate;
	private String lastModifyDate;
	private String lastModifiedSeatID;
	private String prevEffectiveFrom;
	private String prevEffectiveTo;
	private String episodeDate;
	private String lastVisitDate;
	private String episodeCode;
	private String diagnosticCode;
	private String dignosisName;
	private String diagnosticTypeName;
	private String remarks;
	private String fileString;

	public String getFileString()
	{
		return fileString;
	}

	public void setFileString(String fileString)
	{
		this.fileString = fileString;
	}

	public String getEpisodeCode()
	{
		return episodeCode;
	}

	public void setEpisodeCode(String episodeCode)
	{
		this.episodeCode = episodeCode;
	}

	public String getPatProfileId()
	{
		return patProfileId;
	}

	public void setPatProfileId(String patProfileId)
	{
		this.patProfileId = patProfileId;
	}

	public String getSerialNo()
	{
		return serialNo;
	}

	public void setSerialNo(String serialNo)
	{
		this.serialNo = serialNo;
	}

	public String getPatCrNo()
	{
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo)
	{
		this.patCrNo = patCrNo;
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

	public String getPatProfileHeader()
	{
		return patProfileHeader;
	}

	public void setPatProfileHeader(String patProfileHeader)
	{
		this.patProfileHeader = patProfileHeader;
	}

	public String getPatProfileRemark()
	{
		return patProfileRemark;
	}

	public void setPatProfileRemark(String patProfileRemark)
	{
		this.patProfileRemark = patProfileRemark;
	}

	public String getIsValid()
	{
		return isValid;
	}

	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	public String getSeatId()
	{
		return seatId;
	}

	public void setSeatId(String seatId)
	{
		this.seatId = seatId;
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

	public String getPrevEffectiveFrom()
	{
		return prevEffectiveFrom;
	}

	public void setPrevEffectiveFrom(String prevEffectiveFrom)
	{
		this.prevEffectiveFrom = prevEffectiveFrom;
	}

	public String getPrevEffectiveTo()
	{
		return prevEffectiveTo;
	}

	public void setPrevEffectiveTo(String prevEffectiveTo)
	{
		this.prevEffectiveTo = prevEffectiveTo;
	}

	public String getEpisodeDate()
	{
		return episodeDate;
	}

	public void setEpisodeDate(String episodeDate)
	{
		this.episodeDate = episodeDate;
	}

	public String getLastVisitDate()
	{
		return lastVisitDate;
	}

	public void setLastVisitDate(String lastVisitDate)
	{
		this.lastVisitDate = lastVisitDate;
	}

	public String getDiagnosticCode()
	{
		return diagnosticCode;
	}

	public void setDiagnosticCode(String diagnosticCode)
	{
		this.diagnosticCode = diagnosticCode;
	}

	public String getDignosisName()
	{
		return dignosisName;
	}

	public void setDignosisName(String dignosisName)
	{
		this.dignosisName = dignosisName;
	}

	public String getDiagnosticTypeName()
	{
		return diagnosticTypeName;
	}

	public void setDiagnosticTypeName(String diagnosticTypeName)
	{
		this.diagnosticTypeName = diagnosticTypeName;
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
