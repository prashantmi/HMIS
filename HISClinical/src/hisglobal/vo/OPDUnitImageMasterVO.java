package hisglobal.vo;

public class OPDUnitImageMasterVO extends ValueObject
{

	private String imageUnitCode;
	private String deptUnitCode;
	private String imageName;
	private String fileName;

	private String entryDate;

	private String isValid;

	public String getImageUnitCode()
	{
		return imageUnitCode;
	}

	public void setImageUnitCode(String imageUnitCode)
	{
		this.imageUnitCode = imageUnitCode;
	}

	public String getDeptUnitCode()
	{
		return deptUnitCode;
	}

	public void setDeptUnitCode(String deptUnitCode)
	{
		this.deptUnitCode = deptUnitCode;
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

	public String getImageName()
	{
		return imageName;
	}

	public void setImageName(String imageName)
	{
		this.imageName = imageName;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

}
