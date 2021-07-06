package hisglobal.masterxml.masterworkshop.tools;

public abstract class MasterDtl
{

	String masterName;
	String masterTitle;
	String hasSequence;
	String hasRosterSequence;

	public String getHasRosterSequence()
	{
		return hasRosterSequence;
	}

	public void setHasRosterSequence(String hasRosterSequence)
	{
		this.hasRosterSequence = hasRosterSequence;
	}

	public void setMasterTitle(java.lang.String masterTitle)
	{
		this.masterTitle = masterTitle;
	}

	public void setMasterName(java.lang.String masterName)
	{
		this.masterName = masterName;
	}

	public java.lang.String getMasterTitle()
	{
		return masterTitle;
	}

	public java.lang.String getMasterName()
	{
		return masterName;
	}

	public String getHasSequence()
	{
		return hasSequence;
	}

	public void setHasSequence(String hasSequence)
	{
		this.hasSequence = hasSequence;
	}

}//end of class

