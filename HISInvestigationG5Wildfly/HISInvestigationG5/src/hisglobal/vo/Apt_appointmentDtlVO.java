package hisglobal.vo;

/**
 * apt_Appointment_dtlVO is the class that specifies getters and setters for all the identifiers which are used for
 * retrieving and inserting data in the DB tables.
 * 
 * @author AHIS
 */
public class Apt_appointmentDtlVO extends ValueObject
{

	private String aptReqNo;
	private String actCode;
	private String activityName;
	private String aptActCode;
	private String slotCode;
	private String slotDate;
	private String patCrno;
	private String patName;
	private String aptStatus;
	private String isvalid;
	private String seatId;
	private String remark;
	private String statusRemark;
	private String slotStartTime;
	private String slotEndTime;
	private String patAge;
	private String patSex;
	private String patGuardianName;
	private String getCrNoToRetrieve;
	private String oldslotDate;
	private String oldslotDay;
	private String oldslotCode;
	private String oldslotStartTime;
	private String oldslotEndTime;
	private String oldSlotRefId;
	private String episodeCode;// for HRGNUM_EPISODE_NO
	private String episodeVisitNo;// for HRGNUM_VISIT_NO
	private String para1;
	private String para2;
	private String para3;
	private String para4;
	private String para5;
	private String para6;
	private String para7;
	private String paraLabel1;
	private String paraLabel2;
	private String paraLabel3;
	private String paraLabel4;
	private String paraLabel5;
	private String paraLabel6;
	private String paraLabel7;
	private String paraName1;
	private String paraName2;
	private String paraName3;
	private String paraName4;
	private String paraName5;
	private String paraName6;
	private String paraName7;
	private String modSpecificKeyName;
	private String modSpecificKeyId;

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

	public String getParaName5()
	{
		return paraName5;
	}

	public void setParaName5(String paraName5)
	{
		this.paraName5 = paraName5;
	}

	public String getParaName6()
	{
		return paraName6;
	}

	public void setParaName6(String paraName6)
	{
		this.paraName6 = paraName6;
	}

	public String getParaName7()
	{
		return paraName7;
	}

	public void setParaName7(String paraName7)
	{
		this.paraName7 = paraName7;
	}

	public String getParaLabel1()
	{
		return paraLabel1;
	}

	public void setParaLabel1(String paraLabel1)
	{
		this.paraLabel1 = paraLabel1;
	}

	public String getParaLabel2()
	{
		return paraLabel2;
	}

	public void setParaLabel2(String paraLabel2)
	{
		this.paraLabel2 = paraLabel2;
	}

	public String getParaLabel3()
	{
		return paraLabel3;
	}

	public void setParaLabel3(String paraLabel3)
	{
		this.paraLabel3 = paraLabel3;
	}

	public String getParaLabel4()
	{
		return paraLabel4;
	}

	public void setParaLabel4(String paraLabel4)
	{
		this.paraLabel4 = paraLabel4;
	}

	public String getParaLabel5()
	{
		return paraLabel5;
	}

	public void setParaLabel5(String paraLabel5)
	{
		this.paraLabel5 = paraLabel5;
	}

	public String getParaLabel6()
	{
		return paraLabel6;
	}

	public void setParaLabel6(String paraLabel6)
	{
		this.paraLabel6 = paraLabel6;
	}

	public String getParaLabel7()
	{
		return paraLabel7;
	}

	public void setParaLabel7(String paraLabel7)
	{
		this.paraLabel7 = paraLabel7;
	}

	public String getParaName1()
	{
		return paraName1;
	}

	public void setParaName1(String paraName1)
	{
		this.paraName1 = paraName1;
	}

	public String getOldSlotRefId()
	{
		return oldSlotRefId;
	}

	public void setOldSlotRefId(String oldSlotRefId)
	{
		this.oldSlotRefId = oldSlotRefId;
	}

	public String getModSpecificKeyName()
	{
		return modSpecificKeyName;
	}

	public void setModSpecificKeyName(String modSpecificKeyName)
	{
		this.modSpecificKeyName = modSpecificKeyName;
	}

	public String getModSpecificKeyId()
	{
		return modSpecificKeyId;
	}

	public void setModSpecificKeyId(String modSpecificKeyId)
	{
		this.modSpecificKeyId = modSpecificKeyId;
	}

	public String getEpisodeCode()
	{
		return episodeCode;
	}

	public void setEpisodeCode(String episodeCode)
	{
		this.episodeCode = episodeCode;
	}

	public String getEpisodeVisitNo()
	{
		return episodeVisitNo;
	}

	public void setEpisodeVisitNo(String episodeVisitNo)
	{
		this.episodeVisitNo = episodeVisitNo;
	}

	public String getSlotEndTime()
	{
		return slotEndTime;
	}

	public void setSlotEndTime(String slotEndTime)
	{
		this.slotEndTime = slotEndTime;
	}

	public String getSlotStartTime()
	{
		return slotStartTime;
	}

	public void setSlotStartTime(String slotStartTime)
	{
		this.slotStartTime = slotStartTime;
	}

	/**
	 * Retrieves actCode.
	 * 
	 * @return Returns the actCode.
	 */
	public String getActCode()
	{
		return actCode;
	}

	/**
	 * Sets actCode.
	 * 
	 * @param actCode The actCode to set.
	 */
	public void setActCode(String actCode)
	{
		this.actCode = actCode;
	}

	/**
	 * Retrieves aptActCode.
	 * 
	 * @return Returns the aptActCode.
	 */
	public String getAptActCode()
	{
		return aptActCode;
	}

	/**
	 * Sets aptActCode.
	 * 
	 * @param aptActCode The aptActCode to set.
	 */
	public void setAptActCode(String aptActCode)
	{
		this.aptActCode = aptActCode;
	}

	/**
	 * Retrieves aptReqNo.
	 * 
	 * @return Returns the aptReqNo.
	 */
	public String getAptReqNo()
	{
		return aptReqNo;
	}

	/**
	 * Sets aptReqNo.
	 * 
	 * @param aptReqNo The aptReqNo to set.
	 */
	public void setAptReqNo(String aptReqNo)
	{
		this.aptReqNo = aptReqNo;
	}

	/**
	 * Retrieves aptStatus.
	 * 
	 * @return Returns the aptStatus.
	 */
	public String getAptStatus()
	{
		return aptStatus;
	}

	/**
	 * Sets aptStatus.
	 * 
	 * @param aptStatus The aptStatus to set.
	 */
	public void setAptStatus(String aptStatus)
	{
		this.aptStatus = aptStatus;
	}

	/**
	 * Retrieves is valid.
	 * 
	 * @return Returns the is valid.
	 */
	public String getIsvalid()
	{
		return isvalid;
	}

	/**
	 * Sets is valid.
	 * 
	 * @param isvalid The is valid to set.
	 */
	public void setIsvalid(String isvalid)
	{
		this.isvalid = isvalid;
	}

	public String getPatCrno()
	{
		return patCrno;
	}

	/**
	 * Sets patCrno.
	 * 
	 * @param patCrno The patCrno to set.
	 */
	public void setPatCrno(String patCrno)
	{
		this.patCrno = patCrno;
	}

	/**
	 * Retrieves patName.
	 * 
	 * @return Returns the patName.
	 */
	public String getPatName()
	{
		return patName;
	}

	/**
	 * Sets patName.
	 * 
	 * @param patName The patName to set.
	 */
	public void setPatName(String patName)
	{
		this.patName = patName;
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
	 * Retrieves slotCode.
	 * 
	 * @return Returns the slotCode.
	 */
	public String getSlotCode()
	{
		return slotCode;
	}

	/**
	 * Sets slotCode.
	 * 
	 * @param slotCode The slotCode to set.
	 */
	public void setSlotCode(String slotCode)
	{
		this.slotCode = slotCode;
	}

	/**
	 * Retrieves slotDate.
	 * 
	 * @return Returns the slotDate.
	 */
	public String getSlotDate()
	{
		return slotDate;
	}

	/**
	 * Sets slotDate.
	 * 
	 * @param slotDate The slotDate to set.
	 */
	public void setSlotDate(String slotDate)
	{
		this.slotDate = slotDate;
	}

	/**
	 * Retrieves remark.
	 * 
	 * @return Returns the remark.
	 */
	public String getRemark()
	{
		return remark;
	}

	/**
	 * Sets remark.
	 * 
	 * @param remark The remark to set.
	 */
	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	/**
	 * Retrieves statusRemark.
	 * 
	 * @return Returns the statusRemark.
	 */
	public String getStatusRemark()
	{
		return statusRemark;
	}

	/**
	 * Sets statusRemark.
	 * 
	 * @param statusRemark The statusRemark to set.
	 */
	public void setStatusRemark(String statusRemark)
	{
		this.statusRemark = statusRemark;
	}

	public String getPatAge()
	{
		return patAge;
	}

	public void setPatAge(String patAge)
	{
		this.patAge = patAge;
	}

	public String getPatGuardianName()
	{
		return patGuardianName;
	}

	public void setPatGuardianName(String patGuardianName)
	{
		this.patGuardianName = patGuardianName;
	}

	public String getGetCrNoToRetrieve()
	{
		return getCrNoToRetrieve;
	}

	public void setGetCrNoToRetrieve(String getCrNoToRetrieve)
	{
		this.getCrNoToRetrieve = getCrNoToRetrieve;
	}

	public String getOldslotDate()
	{
		return oldslotDate;
	}

	public void setOldslotDate(String oldslotDate)
	{
		this.oldslotDate = oldslotDate;
	}

	public String getOldslotDay()
	{
		return oldslotDay;
	}

	public void setOldslotDay(String oldslotDay)
	{
		this.oldslotDay = oldslotDay;
	}

	public String getOldslotCode()
	{
		return oldslotCode;
	}

	public void setOldslotCode(String oldslotCode)
	{
		this.oldslotCode = oldslotCode;
	}

	public String getOldslotStartTime()
	{
		return oldslotStartTime;
	}

	public void setOldslotStartTime(String oldslotStartTime)
	{
		this.oldslotStartTime = oldslotStartTime;
	}

	public String getOldslotEndTime()
	{
		return oldslotEndTime;
	}

	public void setOldslotEndTime(String oldslotEndTime)
	{
		this.oldslotEndTime = oldslotEndTime;
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

	public String getParaName2()
	{
		return paraName2;
	}

	public void setParaName2(String paraName2)
	{
		this.paraName2 = paraName2;
	}

	public String getParaName3()
	{
		return paraName3;
	}

	public void setParaName3(String paraName3)
	{
		this.paraName3 = paraName3;
	}

	public String getParaName4()
	{
		return paraName4;
	}

	public void setParaName4(String paraName4)
	{
		this.paraName4 = paraName4;
	}

	public String getPatSex()
	{
		return patSex;
	}

	public void setPatSex(String patSex)
	{
		this.patSex = patSex;
	}

	public String getActivityName()
	{
		return activityName;
	}

	public void setActivityName(String activityName)
	{
		this.activityName = activityName;
	}
}
