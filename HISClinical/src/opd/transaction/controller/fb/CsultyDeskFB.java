package opd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import registration.controller.fb.CRNoFB;

public class CsultyDeskFB extends CRNoFB
{
	private String mode;
	private String deskMenuId;
	private String departmentUnitCode;
	private String sortType;
	private String crNoSelected;

	public void reset(ActionMapping arg0, HttpServletRequest arg1)
	{
		this.setDeskMenuId("");
		this.setSortType("");
		this.setDepartmentUnitCode("");
		this.setMode("");
		this.setCrNoSelected("");
	}

	public String getCrNoSelected()
	{
		return crNoSelected;
	}

	public void setCrNoSelected(String crNoSelected)
	{
		this.crNoSelected = crNoSelected;
	}

	public String getSortType()
	{
		return sortType;
	}

	public void setSortType(String sortType)
	{
		this.sortType = sortType;
	}

	public String getDepartmentUnitCode()
	{
		return departmentUnitCode;
	}

	public void setDepartmentUnitCode(String departmentUnitCode)
	{
		this.departmentUnitCode = departmentUnitCode;
	}

	public String getMode()
	{
		return mode;
	}

	public void setMode(String mode)
	{
		this.mode = mode;
	}

	public String getDeskMenuId()
	{
		return deskMenuId;
	}

	public void setDeskMenuId(String deskMenuId)
	{
		this.deskMenuId = deskMenuId;
	}
}