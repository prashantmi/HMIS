package hisglobal.vo;

public class MlcDocUploadDtlVO extends ValueObject
{

	private String mlcNo;
	private String patCrNo;
	private String serialNo;
	private String verificationDocuments;
	private String documentName;
	private String remarks;
	private String isValid;
	private String seatId;
	private String entryDate;

	public String getMlcNo()
	{
		return mlcNo;
	}

	public void setMlcNo(String mlcNo)
	{
		this.mlcNo = mlcNo;
	}

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

	public String getVerificationDocuments()
	{
		return verificationDocuments;
	}

	public void setVerificationDocuments(String verificationDocuments)
	{
		this.verificationDocuments = verificationDocuments;
	}

	public String getDocumentName()
	{
		return documentName;
	}

	public void setDocumentName(String documentName)
	{
		this.documentName = documentName;
	}

	public String getRemarks()
	{
		return remarks;
	}

	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
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
}
