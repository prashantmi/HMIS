package hisglobal.vo;

/**
 * apt_slot_dtlVO is the class that specifies getters and setters for all the identifiers which are used for retrieving and
 * inserting data in the DB tables.
 * 
 * @author AHIS
 */
public class Apt_slotDtlVO extends ValueObject
{
	private String slotRefId;
	private String slotCode;
	private String slotDate;
	private String actCode;
	private String maxPersons;
	private String totAptPersons;
	private String slotStatus;
	private String para1;
	private String para2;
	private String para3;
	private String para4;
	private String para5;
	private String para6;
	private String para7;
	private String slotStartTime;
	private String slotEndTime;
	private String aptPersonLeft;
	private String searchType;
	private String searchValue = "";
	private String oldslotCode;
	private String oldslotDate;

	public String getSlotRefId()
	{
		return slotRefId;
	}

	public void setSlotRefId(String slotRefId)
	{
		this.slotRefId = slotRefId;
	}

	public String getSlotCode()
	{
		return slotCode;
	}

	public void setSlotCode(String slotCode)
	{
		this.slotCode = slotCode;
	}

	public String getSlotDate()
	{
		return slotDate;
	}

	public void setSlotDate(String slotDate)
	{
		this.slotDate = slotDate;
	}

	public String getActCode()
	{
		return actCode;
	}

	public void setActCode(String actCode)
	{
		this.actCode = actCode;
	}

	public String getMaxPersons()
	{
		return maxPersons;
	}

	public void setMaxPersons(String maxPersons)
	{
		this.maxPersons = maxPersons;
	}

	public String getTotAptPersons()
	{
		return totAptPersons;
	}

	public void setTotAptPersons(String totAptPersons)
	{
		this.totAptPersons = totAptPersons;
	}

	public String getSlotStatus()
	{
		return slotStatus;
	}

	public void setSlotStatus(String slotStatus)
	{
		this.slotStatus = slotStatus;
	}

	public String getPara1()
	{
		return para1;
	}

	public void setPara1(String para1)
	{
		this.para1 = para1;
	}

	public String getPara2()
	{
		return para2;
	}

	public void setPara2(String para2)
	{
		this.para2 = para2;
	}

	public String getPara3()
	{
		return para3;
	}

	public void setPara3(String para3)
	{
		this.para3 = para3;
	}

	public String getPara4()
	{
		return para4;
	}

	public void setPara4(String para4)
	{
		this.para4 = para4;
	}

	public String getPara5()
	{
		return para5;
	}

	public void setPara5(String para5)
	{
		this.para5 = para5;
	}

	public String getPara6()
	{
		return para6;
	}

	public void setPara6(String para6)
	{
		this.para6 = para6;
	}

	public String getPara7()
	{
		return para7;
	}

	public void setPara7(String para7)
	{
		this.para7 = para7;
	}

	public String getSlotStartTime()
	{
		return slotStartTime;
	}

	public void setSlotStartTime(String slotStartTime)
	{
		this.slotStartTime = slotStartTime;
	}

	public String getSlotEndTime()
	{
		return slotEndTime;
	}

	public void setSlotEndTime(String slotEndTime)
	{
		this.slotEndTime = slotEndTime;
	}

	public String getAptPersonLeft()
	{
		return aptPersonLeft;
	}

	public void setAptPersonLeft(String aptPersonLeft)
	{
		this.aptPersonLeft = aptPersonLeft;
	}

	public String getSearchType()
	{
		return searchType;
	}

	public void setSearchType(String searchType)
	{
		this.searchType = searchType;
	}

	public String getSearchValue()
	{
		return searchValue;
	}

	public void setSearchValue(String searchValue)
	{
		this.searchValue = searchValue;
	}

	public String getOldslotCode()
	{
		return oldslotCode;
	}

	public void setOldslotCode(String oldslotCode)
	{
		this.oldslotCode = oldslotCode;
	}

	public String getOldslotDate()
	{
		return oldslotDate;
	}

	public void setOldslotDate(String oldslotDate)
	{
		this.oldslotDate = oldslotDate;
	}

}
