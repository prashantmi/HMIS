package opd.master.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class AttendantReasonFB extends ActionForm
{
	private String hmode;
	private String chk[];
	private String isActive;

	private String attendantReasonID;
	private String hospitalCode;
	private String slNo;
	private String attendantReason;
	private String isValid;
	private String seatId;
	private String entryDate;
	private String lastModifyDate;
	private String lastModifiedSeatID;

	public AttendantReasonFB()
	{
		this.chk = new String[2];
		this.isActive = "";
		
		this.attendantReasonID = "";
		this.attendantReason = "";
		this.slNo = "";
	}

	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.chk = new String[2];
		this.isActive = "";

		this.attendantReasonID = "";
		this.attendantReason = "";
		this.slNo = "";
	}

	public String getHmode()
	{
		return hmode;
	}

	public void setHmode(String hmode)
	{
		this.hmode = hmode;
	}

	public String[] getChk()
	{
		return chk;
	}

	public void setChk(String[] chk)
	{
		this.chk = chk;
	}

	public String getAttendantReasonID()
	{
		return attendantReasonID;
	}

	public void setAttendantReasonID(String attendantReasonID)
	{
		this.attendantReasonID = attendantReasonID;
	}

	public String getHospitalCode()
	{
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
	}

	public String getSlNo()
	{
		return slNo;
	}

	public void setSlNo(String slNo)
	{
		this.slNo = slNo;
	}

	public String getAttendantReason()
	{
		return attendantReason;
	}

	public void setAttendantReason(String attendantReason)
	{
		this.attendantReason = attendantReason;
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

	public String getIsActive()
	{
		return isActive;
	}

	public void setIsActive(String isActive)
	{
		this.isActive = isActive;
	}
}
