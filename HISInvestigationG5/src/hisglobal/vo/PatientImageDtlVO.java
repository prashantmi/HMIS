package hisglobal.vo;

/**
 * PatientImageDtlVO is the class that specifies getters and setters for all the identifiers which are used for retrieving
 * and inserting data in the DB tables.
 * 
 * @author AHIS
 */
public class PatientImageDtlVO extends ValueObject
{
	private String patCrNo;
	private String serialNo;
	private String episodeCode;
	private String seatId;
	private String isImageDefault;
	private String systemIPAddress;
	private String entryDate;
	private String isValid;
	private String lastModifiedDate;
	private String lastModifiedSeatId;
	private String fileNo;
	private String filePath;
	private String medicalBoardPatientImage;

	public String getPatCrNo()
	{
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo)
	{
		this.patCrNo = patCrNo;
	}

	public String getSerialNo()
	{
		return serialNo;
	}

	public void setSerialNo(String serialNo)
	{
		this.serialNo = serialNo;
	}

	public String getEpisodeCode()
	{
		return episodeCode;
	}

	public void setEpisodeCode(String episodeCode)
	{
		this.episodeCode = episodeCode;
	}

	public String getSeatId()
	{
		return seatId;
	}

	public void setSeatId(String seatId)
	{
		this.seatId = seatId;
	}

	public String getIsImageDefault()
	{
		return isImageDefault;
	}

	public void setIsImageDefault(String isImageDefault)
	{
		this.isImageDefault = isImageDefault;
	}

	public String getSystemIPAddress()
	{
		return systemIPAddress;
	}

	public void setSystemIPAddress(String systemIPAddress)
	{
		this.systemIPAddress = systemIPAddress;
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

	public String getFileNo()
	{
		return fileNo;
	}

	public void setFileNo(String fileNo)
	{
		this.fileNo = fileNo;
	}

	public String getFilePath()
	{
		return filePath;
	}

	public void setFilePath(String filePath)
	{
		this.filePath = filePath;
	}
	
	public String getMedicalBoardPatientImage()
	{
		return medicalBoardPatientImage;
	}

	public void setMedicalBoardPatientImage(String medicalBoardPatientImage)
	{
		this.medicalBoardPatientImage = medicalBoardPatientImage;
	}

}
