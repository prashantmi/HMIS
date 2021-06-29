package hisglobal.masterxml.masterworkshop.tools;

import java.util.*;

public abstract class MasterTO
{

	String title;
	String masterName;
	String tblHeading;
	ArrayList controls = new ArrayList();
	String hasSequence;
	String hasRosterSequence;
	String seatId;
	String hospitalCode;

	public String getHospitalCode()
	{
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
	}

	public java.util.ArrayList getControls()
	{
		return controls;
	}

	public void setControls(java.util.ArrayList controls)
	{
		this.controls = controls;
	}

	public void setTitle(java.lang.String title)
	{
		this.title = title;
	}

	public java.lang.String getTitle()
	{
		return title;
	}

	public void setTblHeading(java.lang.String tblHeading)
	{
		this.tblHeading = tblHeading;
	}

	public java.lang.String getTblHeading()
	{
		return tblHeading;
	}

	public String getMasterName()
	{
		return masterName;
	}

	public void setMasterName(String masterName)
	{
		this.masterName = masterName;
	}

	public String getHasSequence()
	{
		return hasSequence;
	}

	public void setHasSequence(String hasSequence)
	{
		this.hasSequence = hasSequence;
	}

	public String getHasRosterSequence()
	{
		return hasRosterSequence;
	}

	public void setHasRosterSequence(String hasRosterSequence)
	{
		this.hasRosterSequence = hasRosterSequence;
	}

	public String getSeatId()
	{
		return seatId;
	}

	public void setSeatId(String seatId)
	{
		this.seatId = seatId;
	}

}// end of class
