package mrd.vo;

import hisglobal.vo.ValueObject;

import java.util.List;

public class BulletinGenerationVO extends ValueObject
{
	private String selectedYear;
	private String selectedMonth;
	private String selectedMonthNumber;
	private String duration;

	private String bulletinId;
	private String durationType;
	private String seatId;
	private String hospitalCode;
	private List<BulletinHeadVO> headings;
	private byte[] BulletinDataPdf;
	private String htmlPreview;
	private String bulletinDate;

	public String getSelectedYear()
	{
		return selectedYear;
	}

	public void setSelectedYear(String selectedYear)
	{
		this.selectedYear = selectedYear;
	}

	public String getSelectedMonth()
	{
		return selectedMonth;
	}

	public void setSelectedMonth(String selectedMonth)
	{
		this.selectedMonth = selectedMonth;
	}

	public String getBulletinId()
	{
		return bulletinId;
	}

	public void setBulletinId(String bulletinId)
	{
		this.bulletinId = bulletinId;
	}

	public String getDurationType()
	{
		return durationType;
	}

	public void setDurationType(String durationType)
	{
		this.durationType = durationType;
	}

	public String getDuration()
	{
		return duration;
	}

	public void setDuration(String duration)
	{
		this.duration = duration;
	}

	public String getSeatId()
	{
		return seatId;
	}

	public void setSeatId(String seatId)
	{
		this.seatId = seatId;
	}

	public String getHospitalCode()
	{
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
	}

	public List<BulletinHeadVO> getHeadings()
	{
		return headings;
	}

	public void setHeadings(List<BulletinHeadVO> headings)
	{
		this.headings = headings;
	}

	public byte[] getBulletinDataPdf()
	{
		return BulletinDataPdf;
	}

	public void setBulletinDataPdf(byte[] bulletinDataPdf)
	{
		BulletinDataPdf = bulletinDataPdf;
	}

	public String getHtmlPreview()
	{
		return htmlPreview;
	}

	public void setHtmlPreview(String htmlPreview)
	{
		this.htmlPreview = htmlPreview;
	}

	public String getSelectedMonthNumber()
	{
		return selectedMonthNumber;
	}

	public void setSelectedMonthNumber(String selectedMonthNumber)
	{
		this.selectedMonthNumber = selectedMonthNumber;
	}

	public String getBulletinDate()
	{
		return bulletinDate;
	}

	public void setBulletinDate(String bulletinDate)
	{
		this.bulletinDate = bulletinDate;
	}
}