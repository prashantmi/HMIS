package hisglobal.vo;

public class PatientMlcVO extends ValueObject

{
	private String patCrNo;
	private String mlcNo;
	private String episodeCode;
	private String caseNo;
	private String policeName;
	private String policeDesignation;
	private String policeStation;
	private String officerIncharge;
	private String mlcDate;
	private String cmoCode;
	private String isTransfer;
	private String seatId;
	private String entryDate;
	private String isBroughtDead;
	private String patCondition;
	private String doctorName;
	private String mlcRemark;
	private String isvalid;

	public String getCaseNo()
	{
		return caseNo;
	}

	public void setCaseNo(String caseNo)
	{
		this.caseNo = caseNo;
	}

	public String getCmoCode()
	{
		return cmoCode;
	}

	public void setCmoCode(String cmoCode)
	{
		this.cmoCode = cmoCode;
	}

	public String getDoctorName()
	{
		return doctorName;
	}

	public void setDoctorName(String doctorName)
	{
		this.doctorName = doctorName;
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

	public String getIsBroughtDead()
	{
		return isBroughtDead;
	}

	public void setIsBroughtDead(String isBroughtDead)
	{
		this.isBroughtDead = isBroughtDead;
	}

	public String getIsTransfer()
	{
		return isTransfer;
	}

	public void setIsTransfer(String isTransfer)
	{
		this.isTransfer = isTransfer;
	}

	public String getIsvalid()
	{
		return isvalid;
	}

	public void setIsvalid(String isvalid)
	{
		this.isvalid = isvalid;
	}

	public String getMlcDate()
	{
		return mlcDate;
	}

	public void setMlcDate(String mlcDate)
	{
		this.mlcDate = mlcDate;
	}

	public String getMlcNo()
	{
		return mlcNo;
	}

	public void setMlcNo(String mlcNo)
	{
		this.mlcNo = mlcNo;
	}

	public String getMlcRemark()
	{
		return mlcRemark;
	}

	public void setMlcRemark(String mlcRemark)
	{
		this.mlcRemark = mlcRemark;
	}

	public String getOfficerIncharge()
	{
		return officerIncharge;
	}

	public void setOfficerIncharge(String officerIncharge)
	{
		this.officerIncharge = officerIncharge;
	}

	public String getPatCondition()
	{
		return patCondition;
	}

	public void setPatCondition(String patCondition)
	{
		this.patCondition = patCondition;
	}

	public String getPatCrNo()
	{
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo)
	{
		this.patCrNo = patCrNo;
	}

	public String getPoliceDesignation()
	{
		return policeDesignation;
	}

	public void setPoliceDesignation(String policeDesignation)
	{
		this.policeDesignation = policeDesignation;
	}

	public String getPoliceName()
	{
		return policeName;
	}

	public void setPoliceName(String policeName)
	{
		this.policeName = policeName;
	}

	public String getPoliceStation()
	{
		return policeStation;
	}

	public void setPoliceStation(String policeStation)
	{
		this.policeStation = policeStation;
	}

	public String getSeatId()
	{
		return seatId;
	}

	public void setSeatId(String seatId)
	{
		this.seatId = seatId;
	}

}
