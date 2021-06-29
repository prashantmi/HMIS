package hisglobal.vo;

public class ComponentSeparationDtlVO extends ValueObject
{
	private String bagNo;
	private String bloodComponentID;
	private String bloodComponentName;
	private String bloodBagParentSeqNo;
	private String bloodBagChildSeqNo;
	private String parentBagVolume;
	private String childBagVolume;
	private String methodID;
	private String parentBloodComponentId;
	public String getParentBloodComponentId()
	{
		return parentBloodComponentId;
	}
	public void setParentBloodComponentId(String parentBloodComponentId)
	{
		this.parentBloodComponentId = parentBloodComponentId;
	}
	public String getBagNo()
	{
		return bagNo;
	}
	public void setBagNo(String bagNo)
	{
		this.bagNo = bagNo;
	}
	public String getBloodComponentID()
	{
		return bloodComponentID;
	}
	public void setBloodComponentID(String bloodComponentID)
	{
		this.bloodComponentID = bloodComponentID;
	}
	public String getBloodComponentName()
	{
		return bloodComponentName;
	}
	public void setBloodComponentName(String bloodComponentName)
	{
		this.bloodComponentName = bloodComponentName;
	}
	public String getBloodBagParentSeqNo()
	{
		return bloodBagParentSeqNo;
	}
	public void setBloodBagParentSeqNo(String bloodBagParentSeqNo)
	{
		this.bloodBagParentSeqNo = bloodBagParentSeqNo;
	}
	public String getBloodBagChildSeqNo()
	{
		return bloodBagChildSeqNo;
	}
	public void setBloodBagChildSeqNo(String bloodBagChildSeqNo)
	{
		this.bloodBagChildSeqNo = bloodBagChildSeqNo;
	}
	public String getParentBagVolume()
	{
		return parentBagVolume;
	}
	public void setParentBagVolume(String parentBagVolume)
	{
		this.parentBagVolume = parentBagVolume;
	}
	public String getChildBagVolume()
	{
		return childBagVolume;
	}
	public void setChildBagVolume(String childBagVolume)
	{
		this.childBagVolume = childBagVolume;
	}
	public String getMethodID()
	{
		return methodID;
	}
	public void setMethodID(String methodID)
	{
		this.methodID = methodID;
	}
	
}
