package hisglobal.vo;

/**
 * apt_Slot_MstVO is the class that specifies getters and setters for all the identifiers which are used for retrieving and
 * inserting data in the DB tables.
 * 
 * @author AHIS
 */
public class Apt_slotMstVO extends ValueObject
{

	private String refId;
	private String slotcode;
	private String slotDay;
	private String startHour;
	private String startMin;
	private String endHour;
	private String endMin;
	private String slotStartTime;
	private String slotEndTime;
	private String personPerSlot;
	private String percentagePerSlot;
	private String isvalid;
	private String seatId;
	private String entryDate;
	private String slotDayName;
	private String para1;
	private String para2;
	private String para3;
	private String para4;
	private String para5;
	private String para6;
	private String para7;
	private String actCode;

	public String getActCode()
	{
		return actCode;
	}

	public void setActCode(String actCode)
	{
		this.actCode = actCode;
	}

	public String getSlotcode()
	{
		return slotcode;
	}

	public void setSlotcode(String slotcode)
	{
		this.slotcode = slotcode;
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

	public String getSlotDayName()
	{
		return slotDayName;
	}

	public void setSlotDayName(String slotDayName)
	{
		this.slotDayName = slotDayName;
	}

	/**
	 * Retrieves entryDate.
	 * 
	 * @return Returns the entryDate.
	 */
	public String getEntryDate()
	{
		return entryDate;
	}

	/**
	 * Sets entryDate.
	 * 
	 * @param entryDate The entryDate to set.
	 */
	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	/**
	 * Retrieves isvalid.
	 * 
	 * @return Returns the isvalid.
	 */
	public String getIsvalid()
	{
		return isvalid;
	}

	/**
	 * Sets isvalid.
	 * 
	 * @param isvalid The isvalid to set.
	 */
	public void setIsvalid(String isvalid)
	{
		this.isvalid = isvalid;
	}

	/**
	 * Retrieves percentagePerSlot.
	 * 
	 * @return Returns the percentagePerSlot.
	 */
	public String getPercentagePerSlot()
	{
		return percentagePerSlot;
	}

	/**
	 * Sets percentagePerSlot.
	 * 
	 * @param percentagePerSlot The percentagePerSlot to set.
	 */
	public void setPercentagePerSlot(String percentagePerSlot)
	{
		this.percentagePerSlot = percentagePerSlot;
	}

	/**
	 * Retrieves personPerSlot.
	 * 
	 * @return Returns the personPerSlot.
	 */
	public String getPersonPerSlot()
	{
		return personPerSlot;
	}

	/**
	 * Sets personPerSlot.
	 * 
	 * @param personPerSlot The personPerSlot to set.
	 */
	public void setPersonPerSlot(String personPerSlot)
	{
		this.personPerSlot = personPerSlot;
	}

	/**
	 * Retrieves seatId.
	 * 
	 * @return Returns the seatId.
	 */
	public String getSeatId()
	{
		return seatId;
	}

	/**
	 * Sets seatId.
	 * 
	 * @param seatId The seatId to set.
	 */
	public void setSeatId(String seatId)
	{
		this.seatId = seatId;
	}

	/**
	 * Retrieves slotDay.
	 * 
	 * @return Returns the slotDay.
	 */
	public String getSlotDay()
	{
		return slotDay;
	}

	/**
	 * Sets slotDay.
	 * 
	 * @param slotDay The slotDay to set.
	 */
	public void setSlotDay(String slotDay)
	{
		this.slotDay = slotDay;
	}

	/**
	 * Retrieves slotEndTime.
	 * 
	 * @return Returns the slotEndTime.
	 */
	public String getSlotEndTime()
	{
		return slotEndTime;
	}

	/**
	 * Sets slotEndTime.
	 * 
	 * @param slotEndTime The slotEndTime to set.
	 */
	public void setSlotEndTime(String slotEndTime)
	{
		this.slotEndTime = slotEndTime;
	}

	/**
	 * Retrieves slotStartTime.
	 * 
	 * @return Returns the slotStartTime.
	 */
	public String getSlotStartTime()
	{
		return slotStartTime;
	}

	/**
	 * Sets slotStartTime.
	 * 
	 * @param slotStartTime The slotStartTime to set.
	 */
	public void setSlotStartTime(String slotStartTime)
	{
		this.slotStartTime = slotStartTime;
	}

	public String getStartHour()
	{
		return startHour;
	}

	public void setStartHour(String startHour)
	{
		this.startHour = startHour;
	}

	public String getStartMin()
	{
		return startMin;
	}

	public void setStartMin(String startMin)
	{
		this.startMin = startMin;
	}

	public String getEndHour()
	{
		return endHour;
	}

	public void setEndHour(String endHour)
	{
		this.endHour = endHour;
	}

	public String getEndMin()
	{
		return endMin;
	}

	public void setEndMin(String endMin)
	{
		this.endMin = endMin;
	}

	public String getRefId()
	{
		return refId;
	}

	public void setRefId(String refId)
	{
		this.refId = refId;
	}

}
