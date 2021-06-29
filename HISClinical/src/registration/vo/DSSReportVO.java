package registration.vo;

import hisglobal.vo.ValueObject;

public class DSSReportVO extends ValueObject
{
	private String strHospitalCode;
	private String strPatCatCode;
	private String strPatCasteCode;
	private String strDepartmentCode;
	private String strReligionCode;
	private String strStateCode;
	private String strIsConsolidated; // 1:Yes, 0:No

	private String strDurationMode; // 1:Yearly, 2:Monthly, 3:Date-Wise
	private String strFromDate;
	private String strToDate;
	private String strFromYear = "";
	private String strToYear = "";
	private String strFromMonth = "";
	private String strToMonth = "";

	public String getStrHospitalCode()
	{
		return strHospitalCode;
	}

	public void setStrHospitalCode(String strHospitalCode)
	{
		this.strHospitalCode = strHospitalCode;
	}

	public String getStrPatCatCode()
	{
		return strPatCatCode;
	}

	public void setStrPatCatCode(String strPatCatCode)
	{
		this.strPatCatCode = strPatCatCode;
	}

	public String getStrPatCasteCode()
	{
		return strPatCasteCode;
	}

	public void setStrPatCasteCode(String strPatCasteCode)
	{
		this.strPatCasteCode = strPatCasteCode;
	}

	public String getStrDepartmentCode()
	{
		return strDepartmentCode;
	}

	public void setStrDepartmentCode(String strDepartmentCode)
	{
		this.strDepartmentCode = strDepartmentCode;
	}

	public String getStrReligionCode()
	{
		return strReligionCode;
	}

	public void setStrReligionCode(String strReligionCode)
	{
		this.strReligionCode = strReligionCode;
	}

	public String getStrStateCode()
	{
		return strStateCode;
	}

	public void setStrStateCode(String strStateCode)
	{
		this.strStateCode = strStateCode;
	}

	public String getStrDurationMode()
	{
		return strDurationMode;
	}

	public void setStrDurationMode(String strDurationMode)
	{
		this.strDurationMode = strDurationMode;
	}

	public String getStrFromDate()
	{
		return strFromDate;
	}

	public void setStrFromDate(String strFromDate)
	{
		this.strFromDate = strFromDate;
	}

	public String getStrToDate()
	{
		return strToDate;
	}

	public void setStrToDate(String strToDate)
	{
		this.strToDate = strToDate;
	}

	public String getStrFromYear()
	{
		return strFromYear;
	}

	public void setStrFromYear(String strFromYear)
	{
		this.strFromYear = strFromYear;
	}

	public String getStrToYear()
	{
		return strToYear;
	}

	public void setStrToYear(String strToYear)
	{
		this.strToYear = strToYear;
	}

	public String getStrFromMonth()
	{
		return strFromMonth;
	}

	public void setStrFromMonth(String strFromMonth)
	{
		this.strFromMonth = strFromMonth;
	}

	public String getStrToMonth()
	{
		return strToMonth;
	}

	public void setStrToMonth(String strToMonth)
	{
		this.strToMonth = strToMonth;
	}

	public String getStrIsConsolidated()
	{
		return strIsConsolidated;
	}

	public void setStrIsConsolidated(String strIsConsolidated)
	{
		this.strIsConsolidated = strIsConsolidated;
	}

}
