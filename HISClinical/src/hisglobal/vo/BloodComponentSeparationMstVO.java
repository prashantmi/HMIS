package hisglobal.vo;

public class BloodComponentSeparationMstVO extends ValueObject
{
	private String bloodComponentID;
	private String bloodComponentSlNo;
	private String bloodSeparationComponentID;
	private String bloodSeparationComponentName;
	private String flag;
	private String lastModifyDate;
	private String lastModifiedSeatID;
	private String noOfExpiryDays;
	
	private String bagType;
	private String bagVolume;
	private String method;
	
	public String getBloodComponentID()
	{
		return bloodComponentID;
	}
	public void setBloodComponentID(String bloodComponentID)
	{
		this.bloodComponentID = bloodComponentID;
	}
	public String getBloodComponentSlNo()
	{
		return bloodComponentSlNo;
	}
	public void setBloodComponentSlNo(String bloodComponentSlNo)
	{
		this.bloodComponentSlNo = bloodComponentSlNo;
	}
	public String getBloodSeparationComponentID()
	{
		return bloodSeparationComponentID;
	}
	public void setBloodSeparationComponentID(String bloodSeparationComponentID)
	{
		this.bloodSeparationComponentID = bloodSeparationComponentID;
	}
	public String getFlag()
	{
		return flag;
	}
	public void setFlag(String flag)
	{
		this.flag = flag;
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
	public String getNoOfExpiryDays()
	{
		return noOfExpiryDays;
	}
	public void setNoOfExpiryDays(String noOfExpiryDays)
	{
		this.noOfExpiryDays = noOfExpiryDays;
	}
	public String getBloodSeparationComponentName()
	{
		return bloodSeparationComponentName;
	}
	public void setBloodSeparationComponentName(String bloodSeparationComponentName)
	{
		this.bloodSeparationComponentName = bloodSeparationComponentName;
	}
	public String getBagType() {
		return bagType;
	}
	public void setBagType(String bagType) {
		this.bagType = bagType;
	}
	public String getBagVolume() {
		return bagVolume;
	}
	public void setBagVolume(String bagVolume) {
		this.bagVolume = bagVolume;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
}
