package hisglobal.vo;

public class AudioVideoMasterVO extends ValueObject
{
	private String fileCode;
	private String fileName;
	private String ext;
	private String isValid;
	private String seatId;
	private String entryDate;
	private byte[] file;
	private String hospitalCode;
	private String fileHeader;
	private String isDefault;
	private String slNo;

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public String getSlNo() {
		return slNo;
	}

	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}

	public String getFileHeader() {
		return fileHeader;
	}

	public void setFileHeader(String fileHeader) {
		this.fileHeader = fileHeader;
	}

	public String getHospitalCode()
	{
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
	}

	public String getFileCode()
	{
		return fileCode;
	}

	public void setFileCode(String fileCode)
	{
		this.fileCode = fileCode;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public String getExt()
	{
		return ext;
	}

	public void setExt(String ext)
	{
		this.ext = ext;
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

	public byte[] getFile()
	{
		return file;
	}

	public void setFile(byte[] file)
	{
		this.file = file;
	}
}
