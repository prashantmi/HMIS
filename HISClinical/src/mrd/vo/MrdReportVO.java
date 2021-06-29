package mrd.vo;

import hisglobal.vo.ValueObject;

public class MrdReportVO extends ValueObject
{
	private String reportType; // rtf/pdf
	private String reportMode; // report or chart
	private String mode; // report or chart
	private String fromDate;
	private String toDate;
	private String fromHour;
	private String toHour;
	private String fromMin;
	private String toMin;
	private String choice;
	private String strImmunizationType;
	
	public String getReportType()
	{
		return reportType;
	}

	public void setReportType(String reportType)
	{
		this.reportType = reportType;
	}

	public String getReportMode()
	{
		return reportMode;
	}

	public void setReportMode(String reportMode)
	{
		this.reportMode = reportMode;
	}

	public String getMode()
	{
		return mode;
	}

	public void setMode(String mode)
	{
		this.mode = mode;
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

	public String getFromHour()
	{
		return fromHour;
	}

	public void setFromHour(String fromHour)
	{
		this.fromHour = fromHour;
	}

	public String getToHour()
	{
		return toHour;
	}

	public void setToHour(String toHour)
	{
		this.toHour = toHour;
	}

	public String getFromMin()
	{
		return fromMin;
	}

	public void setFromMin(String fromMin)
	{
		this.fromMin = fromMin;
	}

	public String getToMin()
	{
		return toMin;
	}

	public void setToMin(String toMin)
	{
		this.toMin = toMin;
	}

	public String getChoice()
	{
		return choice;
	}

	public void setChoice(String choice)
	{
		this.choice = choice;
	}

	public String getStrImmunizationType()
	{
		return strImmunizationType;
	}

	public void setStrImmunizationType(String strImmunizationType)
	{
		this.strImmunizationType = strImmunizationType;
	}

}
