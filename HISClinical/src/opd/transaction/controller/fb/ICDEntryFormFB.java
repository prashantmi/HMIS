package opd.transaction.controller.fb;

import hisglobal.hisconfig.Config;

import javax.servlet.http.HttpServletRequest;

import opd.OpdConfig;

import org.apache.struts.action.ActionMapping;

import registration.controller.fb.CRNoFB;

public class ICDEntryFormFB extends CRNoFB
{
	private String hmode;
	private String patCrNo;
	private String patFirstName;
	private String patMiddleName;
	private String patLastName;
	private String patName;
	private String fromDate;
	private String toDate;
	private String sysDate;
	private String departmentUnitCode;
	private String roomCode;
	private String icdCodeFlag;
	private int currentPage;
	private String[] selectedPatCrNo;
	private String selectedPatLen;
	private String[] selectedPatICD;
	private String[] seenPat;
	private String orderBy;
	private String tabMode;

	
	public String getTabMode()
	{
		return tabMode;
	}

	public void setTabMode(String tabMode)
	{
		this.tabMode = tabMode;
	}

	public ICDEntryFormFB()
	{
		this.patCrNo = "";
		this.patName = "";
		this.fromDate = "";
		this.toDate = "";
		this.sysDate = "";
		this.departmentUnitCode = "";
		this.roomCode = "";
		this.icdCodeFlag = OpdConfig.ICD_ENTRY_FORM_ICD_FLAG_UNREAD;
		this.currentPage = 1;
		this.selectedPatCrNo = new String[]{};
		this.selectedPatLen = "0";
		this.selectedPatICD = new String[]{};
		this.seenPat = new String[]{};
		this.orderBy = Config.SORT_TYPE_ASC;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.patCrNo = "";
		this.patName = "";
		this.fromDate = "";
		this.toDate = "";
		this.sysDate = "";
		this.departmentUnitCode = "";
		this.roomCode = "";
		this.icdCodeFlag = OpdConfig.ICD_ENTRY_FORM_ICD_FLAG_UNREAD;
		this.currentPage = 1;
		this.selectedPatCrNo = new String[]{};
		this.selectedPatLen = "0";
		this.selectedPatICD = new String[]{};
		this.seenPat = new String[]{};
		this.orderBy = Config.SORT_TYPE_ASC;
	}

	public void resetSave(ActionMapping mapping, HttpServletRequest request)
	{
		this.selectedPatCrNo = new String[]{};
		this.selectedPatLen = "0";
		this.selectedPatICD = new String[]{};
	}

	public String getHmode()
	{
		return hmode;
	}

	public void setHmode(String hmode)
	{
		this.hmode = hmode;
	}

	public String getPatCrNo()
	{
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo)
	{
		this.patCrNo = patCrNo;
	}

	public String getPatName()
	{
		return patName;
	}

	public void setPatName(String patName)
	{
		this.patName = patName;
	}

	public String getFromDate()
	{
		return fromDate;
	}

	public void setFromDate(String fromDate)
	{
		this.fromDate = fromDate;
	}

	public String getToDate()
	{
		return toDate;
	}

	public void setToDate(String toDate)
	{
		this.toDate = toDate;
	}

	public String getDepartmentUnitCode()
	{
		return departmentUnitCode;
	}

	public void setDepartmentUnitCode(String departmentUnitCode)
	{
		this.departmentUnitCode = departmentUnitCode;
	}

	public String getRoomCode()
	{
		return roomCode;
	}

	public void setRoomCode(String roomCode)
	{
		this.roomCode = roomCode;
	}

	public int getCurrentPage()
	{
		return currentPage;
	}

	public void setCurrentPage(int currentPage)
	{
		this.currentPage = currentPage;
	}

	public String getSysDate()
	{
		return sysDate;
	}

	public void setSysDate(String sysDate)
	{
		this.sysDate = sysDate;
	}

	public String[] getSelectedPatCrNo()
	{
		return selectedPatCrNo;
	}

	public void setSelectedPatCrNo(String[] selectedPatCrNo)
	{
		this.selectedPatCrNo = selectedPatCrNo;
	}

	public String getPatFirstName()
	{
		return patFirstName;
	}

	public void setPatFirstName(String patFirstName)
	{
		this.patFirstName = patFirstName;
	}

	public String getPatMiddleName()
	{
		return patMiddleName;
	}

	public void setPatMiddleName(String patMiddleName)
	{
		this.patMiddleName = patMiddleName;
	}

	public String getPatLastName()
	{
		return patLastName;
	}

	public void setPatLastName(String patLastName)
	{
		this.patLastName = patLastName;
	}

	public String[] getSelectedPatICD()
	{
		return selectedPatICD;
	}

	public void setSelectedPatICD(String[] selectedPatICD)
	{
		this.selectedPatICD = selectedPatICD;
	}

	public String getSelectedPatLen()
	{
		return selectedPatLen;
	}

	public void setSelectedPatLen(String selectedPatLen)
	{
		this.selectedPatLen = selectedPatLen;
	}

	public String getOrderBy()
	{
		return orderBy;
	}

	public void setOrderBy(String orderBy)
	{
		this.orderBy = orderBy;
	}

	public String getIcdCodeFlag()
	{
		return icdCodeFlag;
	}

	public void setIcdCodeFlag(String icdCodeFlag)
	{
		this.icdCodeFlag = icdCodeFlag;
	}

	public String[] getSeenPat()
	{
		return seenPat;
	}

	public void setSeenPat(String[] seenPat)
	{
		this.seenPat = seenPat;
	}
}
