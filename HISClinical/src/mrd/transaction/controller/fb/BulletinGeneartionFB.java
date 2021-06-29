package mrd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import hisglobal.presentation.ReportFB;

public class BulletinGeneartionFB extends ActionForm  {
	private String deskmode;
	private String hmode;
	private String bulletinType;
	private String selectedYear;
	private String selectedMonth;
	private String selectedMonthNumber;
	public String getDeskmode() {
		return deskmode;
	}

	public void setDeskmode(String deskmode) {
		this.deskmode = deskmode;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		deskmode="";
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String getBulletinType() {
		return bulletinType;
	}

	public void setBulletinType(String bulletinType) {
		this.bulletinType = bulletinType;
	}

	public String getSelectedYear() {
		return selectedYear;
	}

	public void setSelectedYear(String selectedYear) {
		this.selectedYear = selectedYear;
	}

	public String getSelectedMonth() {
		return selectedMonth;
	}

	public void setSelectedMonth(String selectedMonth) {
		this.selectedMonth = selectedMonth;
	}

	public String getSelectedMonthNumber()
	{
		return selectedMonthNumber;
	}

	public void setSelectedMonthNumber(String selectedMonthNumber)
	{
		this.selectedMonthNumber = selectedMonthNumber;
	}

}
