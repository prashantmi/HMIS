package opd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class OpdRosterSchedulePopupFB extends ActionForm
{
	private String hmode;
    private String departmentUnitCode;
	private String userId;
    private String entryDate;
	private String targetFunction;
	
	private String scheduleDate;
	private int currentPage;

	public OpdRosterSchedulePopupFB()
	{
		this.targetFunction = "";
		this.departmentUnitCode = "";
		this.userId = "";
		this.entryDate = "";
		
		this.scheduleDate = "";
		this.currentPage = 1;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.targetFunction = "";
		this.departmentUnitCode = "";
		this.userId = "";
		this.entryDate = "";

		this.scheduleDate = "";
		this.currentPage = 1;
	}

	public String getHmode()
	{
		return hmode;
	}

	public void setHmode(String hmode)
	{
		this.hmode = hmode;
	}

	public String getTargetFunction()
	{
		return targetFunction;
	}

	public void setTargetFunction(String targetFunction)
	{
		this.targetFunction = targetFunction;
	}

	public String getDepartmentUnitCode()
	{
		return departmentUnitCode;
	}

	public void setDepartmentUnitCode(String departmentUnitCode)
	{
		this.departmentUnitCode = departmentUnitCode;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	public String getScheduleDate()
	{
		return scheduleDate;
	}

	public void setScheduleDate(String scheduleDate)
	{
		this.scheduleDate = scheduleDate;
	}

	public int getCurrentPage()
	{
		return currentPage;
	}

	public void setCurrentPage(int currentPage)
	{
		this.currentPage = currentPage;
	}

}
