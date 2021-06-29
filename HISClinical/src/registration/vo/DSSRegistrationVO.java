package registration.vo;

import hisglobal.vo.ValueObject;

public class DSSRegistrationVO extends ValueObject
{
	private String strHospitalCode;
	private String strDepartmentCode;
	private String strPatCatCode;
	private String strStateCode;
	private String strPatCasteCode;
	private String strReligionCode;

	private String strMonYear;

	private DSSStatisticVO arrDaysStats[];
	
	private DSSStatisticVO voMonthStat;
	
	
	public DSSRegistrationVO()
	{
		this.arrDaysStats = new DSSStatisticVO[31];
		this.voMonthStat = new DSSStatisticVO(DSSStatisticVO.STAT_MODE_MONTH);
	}

	public String getStrHospitalCode()
	{
		return strHospitalCode;
	}

	public void setStrHospitalCode(String strHospitalCode)
	{
		this.strHospitalCode = strHospitalCode;
	}

	public String getStrDepartmentCode()
	{
		return strDepartmentCode;
	}

	public void setStrDepartmentCode(String strDepartmentCode)
	{
		this.strDepartmentCode = strDepartmentCode;
	}

	public String getStrPatCatCode()
	{
		return strPatCatCode;
	}

	public void setStrPatCatCode(String strPatCatCode)
	{
		this.strPatCatCode = strPatCatCode;
	}

	public String getStrStateCode()
	{
		return strStateCode;
	}

	public void setStrStateCode(String strStateCode)
	{
		this.strStateCode = strStateCode;
	}

	public String getStrPatCasteCode()
	{
		return strPatCasteCode;
	}

	public void setStrPatCasteCode(String strPatCasteCode)
	{
		this.strPatCasteCode = strPatCasteCode;
	}

	public String getStrReligionCode()
	{
		return strReligionCode;
	}

	public void setStrReligionCode(String strReligionCode)
	{
		this.strReligionCode = strReligionCode;
	}

	public String getStrMonYear()
	{
		return strMonYear;
	}

	public void setStrMonYear(String strMonYear)
	{
		this.strMonYear = strMonYear;
	}

	public DSSStatisticVO[] getArrDaysStats()
	{
		return arrDaysStats;
	}

	public void setArrDaysStats(DSSStatisticVO[] arrDaysStats)
	{
		this.arrDaysStats = arrDaysStats;
	}

	public DSSStatisticVO getVoMonthStat()
	{
		return voMonthStat;
	}

	public void setVoMonthStat(DSSStatisticVO voMonthStat)
	{
		this.voMonthStat = voMonthStat;
	}
	
	// Day Wise Common Automatic Getter & Setter
	// strTotGen, strTotEmg, strTotMLC, strTotBDead, strTotUnk 
	public String getStrTotGen(String strDay)
	{
		String strTot=null;
		try
		{
			int nDay = 0;
			if(strDay!=null) nDay = Integer.parseInt(strDay);
			if(this.arrDaysStats!=null && this.arrDaysStats.length>=nDay && this.arrDaysStats[nDay]!=null)
				strTot = this.arrDaysStats[nDay].getStrTotGen();
		}
		catch(Exception e)
		{
			strTot = null;
		}
		return strTot;
	}
	public void setStrTotGen(String strDay, String strTot)
	{
		try
		{
			int nDay = 0;
			if(strDay!=null) nDay = Integer.parseInt(strDay);
			if(this.arrDaysStats!=null && this.arrDaysStats.length>=nDay)
			{
				if(this.arrDaysStats[nDay]==null)
					this.arrDaysStats[nDay] = new DSSStatisticVO(DSSStatisticVO.STAT_MODE_DAY, strDay);
				this.arrDaysStats[nDay].setStrTotGen(strTot);
			}
		}
		catch(Exception e){}
	}

	public String getStrTotEmg(String strDay)
	{
		String strTot=null;
		try
		{
			int nDay = 0;
			if(strDay!=null) nDay = Integer.parseInt(strDay);
			if(this.arrDaysStats!=null && this.arrDaysStats.length>=nDay && this.arrDaysStats[nDay]!=null)
				strTot = this.arrDaysStats[nDay].getStrTotEmg();
		}
		catch(Exception e)
		{
			strTot = null;
		}
		return strTot;
	}
	public void setStrTotEmg(String strDay, String strTot)
	{
		try
		{
			int nDay = 0;
			if(strDay!=null) nDay = Integer.parseInt(strDay);
			if(this.arrDaysStats!=null && this.arrDaysStats.length>=nDay)
			{
				if(this.arrDaysStats[nDay]==null)
					this.arrDaysStats[nDay] = new DSSStatisticVO(DSSStatisticVO.STAT_MODE_DAY,strDay);
				this.arrDaysStats[nDay].setStrTotEmg(strTot);
			}
		}
		catch(Exception e){}
	}
	
	public String getStrTotMLC(String strDay)
	{
		String strTot=null;
		try
		{
			int nDay = 0;
			if(strDay!=null) nDay = Integer.parseInt(strDay);
			if(this.arrDaysStats!=null && this.arrDaysStats.length>=nDay && this.arrDaysStats[nDay]!=null)
				strTot = this.arrDaysStats[nDay].getStrTotMLC();
		}
		catch(Exception e)
		{
			strTot = null;
		}
		return strTot;
	}
	public void setStrTotMLC(String strDay, String strTot)
	{
		try
		{
			int nDay = 0;
			if(strDay!=null) nDay = Integer.parseInt(strDay);
			if(this.arrDaysStats!=null && this.arrDaysStats.length>=nDay)
			{
				if(this.arrDaysStats[nDay]==null)
					this.arrDaysStats[nDay] = new DSSStatisticVO(DSSStatisticVO.STAT_MODE_DAY,strDay);
				this.arrDaysStats[nDay].setStrTotMLC(strTot);
			}
		}
		catch(Exception e){}
	}

	public String getStrTotBDead(String strDay)
	{
		String strTot=null;
		try
		{
			int nDay = 0;
			if(strDay!=null) nDay = Integer.parseInt(strDay);
			if(this.arrDaysStats!=null && this.arrDaysStats.length>=nDay && this.arrDaysStats[nDay]!=null)
				strTot = this.arrDaysStats[nDay].getStrTotBDead();
		}
		catch(Exception e)
		{
			strTot = null;
		}
		return strTot;
	}
	public void setStrTotBDead(String strDay, String strTot)
	{
		try
		{
			int nDay = 0;
			if(strDay!=null) nDay = Integer.parseInt(strDay);
			if(this.arrDaysStats!=null && this.arrDaysStats.length>=nDay)
			{
				if(this.arrDaysStats[nDay]==null)
					this.arrDaysStats[nDay] = new DSSStatisticVO(DSSStatisticVO.STAT_MODE_DAY,strDay);
				this.arrDaysStats[nDay].setStrTotBDead(strTot);
			}
		}
		catch(Exception e){}
	}

	public String getStrTotUnk(String strDay)
	{
		String strTot=null;
		try
		{
			int nDay = 0;
			if(strDay!=null) nDay = Integer.parseInt(strDay);
			if(this.arrDaysStats!=null && this.arrDaysStats.length>=nDay && this.arrDaysStats[nDay]!=null)
				strTot = this.arrDaysStats[nDay].getStrTotUnk();
		}
		catch(Exception e)
		{
			strTot = null;
		}
		return strTot;
	}
	public void setStrTotUnk(String strDay, String strTot)
	{
		try
		{
			int nDay = 0;
			if(strDay!=null) nDay = Integer.parseInt(strDay);
			if(this.arrDaysStats!=null && this.arrDaysStats.length>=nDay)
			{
				if(this.arrDaysStats[nDay]==null)
					this.arrDaysStats[nDay] = new DSSStatisticVO(DSSStatisticVO.STAT_MODE_DAY,strDay);
				this.arrDaysStats[nDay].setStrTotUnk(strTot);
			}
		}
		catch(Exception e){}
	}

	// Day Wise Automatic Getter & Setter
	// strTotGen, strTotEmg, strTotMLC, strTotBDead, strTotUnk 
	public String getStrTotGen1()
	{
		return getStrTotGen("1");
	}

	public void setStrTotGen1(String strTotGen1)
	{
		setStrTotGen("1", strTotGen1);
	}

	public String getStrTotEmg1()
	{
		return getStrTotEmg("1");
	}

	public void setStrTotEmg1(String strTotEmg1)
	{
		setStrTotEmg("1", strTotEmg1);
	}

	public String getStrTotMLC1()
	{
		return getStrTotMLC("1");
	}

	public void setStrTotMLC1(String strTotMLC1)
	{
		setStrTotMLC("1", strTotMLC1);
	}

	public String getStrTotBDead1()
	{
		return getStrTotBDead("1");
	}

	public void setStrTotBDead1(String strTotBDead1)
	{
		setStrTotBDead("1", strTotBDead1);
	}

	public String getStrTotUnk1()
	{
		return getStrTotUnk("1");
	}

	public void setStrTotUnk1(String strTotUnk1)
	{
		setStrTotUnk("1", strTotUnk1);
	}

	public String getStrTotGen2()
	{
		return getStrTotGen("2");
	}

	public void setStrTotGen2(String strTotGen2)
	{
		setStrTotGen("2", strTotGen2);
	}

	public String getStrTotEmg2()
	{
		return getStrTotEmg("2");
	}

	public void setStrTotEmg2(String strTotEmg2)
	{
		setStrTotEmg("2", strTotEmg2);
	}

	public String getStrTotMLC2()
	{
		return getStrTotMLC("2");
	}

	public void setStrTotMLC2(String strTotMLC2)
	{
		setStrTotMLC("2", strTotMLC2);
	}

	public String getStrTotBDead2()
	{
		return getStrTotBDead("2");
	}

	public void setStrTotBDead2(String strTotBDead2)
	{
		setStrTotBDead("2", strTotBDead2);
	}

	public String getStrTotUnk2()
	{
		return getStrTotUnk("2");
	}

	public void setStrTotUnk2(String strTotUnk2)
	{
		setStrTotUnk("2", strTotUnk2);
	}

	public String getStrTotGen3()
	{
		return getStrTotGen("3");
	}

	public void setStrTotGen3(String strTotGen3)
	{
		setStrTotGen("3", strTotGen3);
	}

	public String getStrTotEmg3()
	{
		return getStrTotEmg("3");
	}

	public void setStrTotEmg3(String strTotEmg3)
	{
		setStrTotEmg("3", strTotEmg3);
	}

	public String getStrTotMLC3()
	{
		return getStrTotMLC("3");
	}

	public void setStrTotMLC3(String strTotMLC3)
	{
		setStrTotMLC("3", strTotMLC3);
	}

	public String getStrTotBDead3()
	{
		return getStrTotBDead("3");
	}

	public void setStrTotBDead3(String strTotBDead3)
	{
		setStrTotBDead("3", strTotBDead3);
	}

	public String getStrTotUnk3()
	{
		return getStrTotUnk("3");
	}

	public void setStrTotUnk3(String strTotUnk3)
	{
		setStrTotUnk("3", strTotUnk3);
	}

	public String getStrTotGen4()
	{
		return getStrTotGen("4");
	}

	public void setStrTotGen4(String strTotGen4)
	{
		setStrTotGen("4", strTotGen4);
	}

	public String getStrTotEmg4()
	{
		return getStrTotEmg("4");
	}

	public void setStrTotEmg4(String strTotEmg4)
	{
		setStrTotEmg("4", strTotEmg4);
	}

	public String getStrTotMLC4()
	{
		return getStrTotMLC("4");
	}

	public void setStrTotMLC4(String strTotMLC4)
	{
		setStrTotMLC("4", strTotMLC4);
	}

	public String getStrTotBDead4()
	{
		return getStrTotBDead("4");
	}

	public void setStrTotBDead4(String strTotBDead4)
	{
		setStrTotBDead("4", strTotBDead4);
	}

	public String getStrTotUnk4()
	{
		return getStrTotUnk("4");
	}

	public void setStrTotUnk4(String strTotUnk4)
	{
		setStrTotUnk("4", strTotUnk4);
	}

	public String getStrTotGen5()
	{
		return getStrTotGen("5");
	}

	public void setStrTotGen5(String strTotGen5)
	{
		setStrTotGen("5", strTotGen5);
	}

	public String getStrTotEmg5()
	{
		return getStrTotEmg("5");
	}

	public void setStrTotEmg5(String strTotEmg5)
	{
		setStrTotEmg("5", strTotEmg5);
	}

	public String getStrTotMLC5()
	{
		return getStrTotMLC("5");
	}

	public void setStrTotMLC5(String strTotMLC5)
	{
		setStrTotMLC("5", strTotMLC5);
	}

	public String getStrTotBDead5()
	{
		return getStrTotBDead("5");
	}

	public void setStrTotBDead5(String strTotBDead5)
	{
		setStrTotBDead("5", strTotBDead5);
	}

	public String getStrTotUnk5()
	{
		return getStrTotUnk("5");
	}

	public void setStrTotUnk5(String strTotUnk5)
	{
		setStrTotUnk("5", strTotUnk5);
	}

	public String getStrTotGen6()
	{
		return getStrTotGen("6");
	}

	public void setStrTotGen6(String strTotGen6)
	{
		setStrTotGen("6", strTotGen6);
	}

	public String getStrTotEmg6()
	{
		return getStrTotEmg("6");
	}

	public void setStrTotEmg6(String strTotEmg6)
	{
		setStrTotEmg("6", strTotEmg6);
	}

	public String getStrTotMLC6()
	{
		return getStrTotMLC("6");
	}

	public void setStrTotMLC6(String strTotMLC6)
	{
		setStrTotMLC("6", strTotMLC6);
	}

	public String getStrTotBDead6()
	{
		return getStrTotBDead("6");
	}

	public void setStrTotBDead6(String strTotBDead6)
	{
		setStrTotBDead("6", strTotBDead6);
	}

	public String getStrTotUnk6()
	{
		return getStrTotUnk("6");
	}

	public void setStrTotUnk6(String strTotUnk6)
	{
		setStrTotUnk("6", strTotUnk6);
	}

	public String getStrTotGen7()
	{
		return getStrTotGen("7");
	}

	public void setStrTotGen7(String strTotGen7)
	{
		setStrTotGen("7", strTotGen7);
	}

	public String getStrTotEmg7()
	{
		return getStrTotEmg("7");
	}

	public void setStrTotEmg7(String strTotEmg7)
	{
		setStrTotEmg("7", strTotEmg7);
	}

	public String getStrTotMLC7()
	{
		return getStrTotMLC("7");
	}

	public void setStrTotMLC7(String strTotMLC7)
	{
		setStrTotMLC("7", strTotMLC7);
	}

	public String getStrTotBDead7()
	{
		return getStrTotBDead("7");
	}

	public void setStrTotBDead7(String strTotBDead7)
	{
		setStrTotBDead("7", strTotBDead7);
	}

	public String getStrTotUnk7()
	{
		return getStrTotUnk("7");
	}

	public void setStrTotUnk7(String strTotUnk7)
	{
		setStrTotUnk("7", strTotUnk7);
	}

	public String getStrTotGen8()
	{
		return getStrTotGen("8");
	}

	public void setStrTotGen8(String strTotGen8)
	{
		setStrTotGen("8", strTotGen8);
	}

	public String getStrTotEmg8()
	{
		return getStrTotEmg("8");
	}

	public void setStrTotEmg8(String strTotEmg8)
	{
		setStrTotEmg("8", strTotEmg8);
	}

	public String getStrTotMLC8()
	{
		return getStrTotMLC("8");
	}

	public void setStrTotMLC8(String strTotMLC8)
	{
		setStrTotMLC("8", strTotMLC8);
	}

	public String getStrTotBDead8()
	{
		return getStrTotBDead("8");
	}

	public void setStrTotBDead8(String strTotBDead8)
	{
		setStrTotBDead("8", strTotBDead8);
	}

	public String getStrTotUnk8()
	{
		return getStrTotUnk("8");
	}

	public void setStrTotUnk8(String strTotUnk8)
	{
		setStrTotUnk("8", strTotUnk8);
	}

	public String getStrTotGen9()
	{
		return getStrTotGen("9");
	}

	public void setStrTotGen9(String strTotGen9)
	{
		setStrTotGen("9", strTotGen9);
	}

	public String getStrTotEmg9()
	{
		return getStrTotEmg("9");
	}

	public void setStrTotEmg9(String strTotEmg9)
	{
		setStrTotEmg("9", strTotEmg9);
	}

	public String getStrTotMLC9()
	{
		return getStrTotMLC("9");
	}

	public void setStrTotMLC9(String strTotMLC9)
	{
		setStrTotMLC("9", strTotMLC9);
	}

	public String getStrTotBDead9()
	{
		return getStrTotBDead("9");
	}

	public void setStrTotBDead9(String strTotBDead9)
	{
		setStrTotBDead("9", strTotBDead9);
	}

	public String getStrTotUnk9()
	{
		return getStrTotUnk("9");
	}

	public void setStrTotUnk9(String strTotUnk9)
	{
		setStrTotUnk("9", strTotUnk9);
	}

	public String getStrTotGen10()
	{
		return getStrTotGen("10");
	}

	public void setStrTotGen10(String strTotGen10)
	{
		setStrTotGen("10", strTotGen10);
	}

	public String getStrTotEmg10()
	{
		return getStrTotEmg("10");
	}

	public void setStrTotEmg10(String strTotEmg10)
	{
		setStrTotEmg("10", strTotEmg10);
	}

	public String getStrTotMLC10()
	{
		return getStrTotMLC("10");
	}

	public void setStrTotMLC10(String strTotMLC10)
	{
		setStrTotMLC("10", strTotMLC10);
	}

	public String getStrTotBDead10()
	{
		return getStrTotBDead("10");
	}

	public void setStrTotBDead10(String strTotBDead10)
	{
		setStrTotBDead("10", strTotBDead10);
	}

	public String getStrTotUnk10()
	{
		return getStrTotUnk("10");
	}

	public void setStrTotUnk10(String strTotUnk10)
	{
		setStrTotUnk("10", strTotUnk10);
	}

	public String getStrTotGen11()
	{
		return getStrTotGen("11");
	}

	public void setStrTotGen11(String strTotGen11)
	{
		setStrTotGen("11", strTotGen11);
	}

	public String getStrTotEmg11()
	{
		return getStrTotEmg("11");
	}

	public void setStrTotEmg11(String strTotEmg11)
	{
		setStrTotEmg("11", strTotEmg11);
	}

	public String getStrTotMLC11()
	{
		return getStrTotMLC("11");
	}

	public void setStrTotMLC11(String strTotMLC11)
	{
		setStrTotMLC("11", strTotMLC11);
	}

	public String getStrTotBDead11()
	{
		return getStrTotBDead("11");
	}

	public void setStrTotBDead11(String strTotBDead11)
	{
		setStrTotBDead("11", strTotBDead11);
	}

	public String getStrTotUnk11()
	{
		return getStrTotUnk("11");
	}

	public void setStrTotUnk11(String strTotUnk11)
	{
		setStrTotUnk("11", strTotUnk11);
	}

	public String getStrTotGen12()
	{
		return getStrTotGen("12");
	}

	public void setStrTotGen12(String strTotGen12)
	{
		setStrTotGen("12", strTotGen12);
	}

	public String getStrTotEmg12()
	{
		return getStrTotEmg("12");
	}

	public void setStrTotEmg12(String strTotEmg12)
	{
		setStrTotEmg("12", strTotEmg12);
	}

	public String getStrTotMLC12()
	{
		return getStrTotMLC("12");
	}

	public void setStrTotMLC12(String strTotMLC12)
	{
		setStrTotMLC("12", strTotMLC12);
	}

	public String getStrTotBDead12()
	{
		return getStrTotBDead("12");
	}

	public void setStrTotBDead12(String strTotBDead12)
	{
		setStrTotBDead("12", strTotBDead12);
	}

	public String getStrTotUnk12()
	{
		return getStrTotUnk("12");
	}

	public void setStrTotUnk12(String strTotUnk12)
	{
		setStrTotUnk("12", strTotUnk12);
	}

	public String getStrTotGen13()
	{
		return getStrTotGen("13");
	}

	public void setStrTotGen13(String strTotGen13)
	{
		setStrTotGen("13", strTotGen13);
	}

	public String getStrTotEmg13()
	{
		return getStrTotEmg("13");
	}

	public void setStrTotEmg13(String strTotEmg13)
	{
		setStrTotEmg("13", strTotEmg13);
	}

	public String getStrTotMLC13()
	{
		return getStrTotMLC("13");
	}

	public void setStrTotMLC13(String strTotMLC13)
	{
		setStrTotMLC("13", strTotMLC13);
	}

	public String getStrTotBDead13()
	{
		return getStrTotBDead("13");
	}

	public void setStrTotBDead13(String strTotBDead13)
	{
		setStrTotBDead("13", strTotBDead13);
	}

	public String getStrTotUnk13()
	{
		return getStrTotUnk("13");
	}

	public void setStrTotUnk13(String strTotUnk13)
	{
		setStrTotUnk("13", strTotUnk13);
	}

	public String getStrTotGen14()
	{
		return getStrTotGen("14");
	}

	public void setStrTotGen14(String strTotGen14)
	{
		setStrTotGen("14", strTotGen14);
	}

	public String getStrTotEmg14()
	{
		return getStrTotEmg("14");
	}

	public void setStrTotEmg14(String strTotEmg14)
	{
		setStrTotEmg("14", strTotEmg14);
	}

	public String getStrTotMLC14()
	{
		return getStrTotMLC("14");
	}

	public void setStrTotMLC14(String strTotMLC14)
	{
		setStrTotMLC("14", strTotMLC14);
	}

	public String getStrTotBDead14()
	{
		return getStrTotBDead("14");
	}

	public void setStrTotBDead14(String strTotBDead14)
	{
		setStrTotBDead("14", strTotBDead14);
	}

	public String getStrTotUnk14()
	{
		return getStrTotUnk("14");
	}

	public void setStrTotUnk14(String strTotUnk14)
	{
		setStrTotUnk("14", strTotUnk14);
	}

	public String getStrTotGen15()
	{
		return getStrTotGen("15");
	}

	public void setStrTotGen15(String strTotGen15)
	{
		setStrTotGen("15", strTotGen15);
	}

	public String getStrTotEmg15()
	{
		return getStrTotEmg("15");
	}

	public void setStrTotEmg15(String strTotEmg15)
	{
		setStrTotEmg("15", strTotEmg15);
	}

	public String getStrTotMLC15()
	{
		return getStrTotMLC("15");
	}

	public void setStrTotMLC15(String strTotMLC15)
	{
		setStrTotMLC("15", strTotMLC15);
	}

	public String getStrTotBDead15()
	{
		return getStrTotBDead("15");
	}

	public void setStrTotBDead15(String strTotBDead15)
	{
		setStrTotBDead("15", strTotBDead15);
	}

	public String getStrTotUnk15()
	{
		return getStrTotUnk("15");
	}

	public void setStrTotUnk15(String strTotUnk15)
	{
		setStrTotUnk("15", strTotUnk15);
	}

	public String getStrTotGen16()
	{
		return getStrTotGen("16");
	}

	public void setStrTotGen16(String strTotGen16)
	{
		setStrTotGen("16", strTotGen16);
	}

	public String getStrTotEmg16()
	{
		return getStrTotEmg("16");
	}

	public void setStrTotEmg16(String strTotEmg16)
	{
		setStrTotEmg("16", strTotEmg16);
	}

	public String getStrTotMLC16()
	{
		return getStrTotMLC("16");
	}

	public void setStrTotMLC16(String strTotMLC16)
	{
		setStrTotMLC("16", strTotMLC16);
	}

	public String getStrTotBDead16()
	{
		return getStrTotBDead("16");
	}

	public void setStrTotBDead16(String strTotBDead16)
	{
		setStrTotBDead("16", strTotBDead16);
	}

	public String getStrTotUnk16()
	{
		return getStrTotUnk("16");
	}

	public void setStrTotUnk16(String strTotUnk16)
	{
		setStrTotUnk("16", strTotUnk16);
	}

	public String getStrTotGen17()
	{
		return getStrTotGen("17");
	}

	public void setStrTotGen17(String strTotGen17)
	{
		setStrTotGen("17", strTotGen17);
	}

	public String getStrTotEmg17()
	{
		return getStrTotEmg("17");
	}

	public void setStrTotEmg17(String strTotEmg17)
	{
		setStrTotEmg("17", strTotEmg17);
	}

	public String getStrTotMLC17()
	{
		return getStrTotMLC("17");
	}

	public void setStrTotMLC17(String strTotMLC17)
	{
		setStrTotMLC("17", strTotMLC17);
	}

	public String getStrTotBDead17()
	{
		return getStrTotBDead("17");
	}

	public void setStrTotBDead17(String strTotBDead17)
	{
		setStrTotBDead("17", strTotBDead17);
	}

	public String getStrTotUnk17()
	{
		return getStrTotUnk("17");
	}

	public void setStrTotUnk17(String strTotUnk17)
	{
		setStrTotUnk("17", strTotUnk17);
	}

	public String getStrTotGen18()
	{
		return getStrTotGen("18");
	}

	public void setStrTotGen18(String strTotGen18)
	{
		setStrTotGen("18", strTotGen18);
	}

	public String getStrTotEmg18()
	{
		return getStrTotEmg("18");
	}

	public void setStrTotEmg18(String strTotEmg18)
	{
		setStrTotEmg("18", strTotEmg18);
	}

	public String getStrTotMLC18()
	{
		return getStrTotMLC("18");
	}

	public void setStrTotMLC18(String strTotMLC18)
	{
		setStrTotMLC("18", strTotMLC18);
	}

	public String getStrTotBDead18()
	{
		return getStrTotBDead("18");
	}

	public void setStrTotBDead18(String strTotBDead18)
	{
		setStrTotBDead("18", strTotBDead18);
	}

	public String getStrTotUnk18()
	{
		return getStrTotUnk("18");
	}

	public void setStrTotUnk18(String strTotUnk18)
	{
		setStrTotUnk("18", strTotUnk18);
	}

	public String getStrTotGen19()
	{
		return getStrTotGen("19");
	}

	public void setStrTotGen19(String strTotGen19)
	{
		setStrTotGen("19", strTotGen19);
	}

	public String getStrTotEmg19()
	{
		return getStrTotEmg("19");
	}

	public void setStrTotEmg19(String strTotEmg19)
	{
		setStrTotEmg("19", strTotEmg19);
	}

	public String getStrTotMLC19()
	{
		return getStrTotMLC("19");
	}

	public void setStrTotMLC19(String strTotMLC19)
	{
		setStrTotMLC("19", strTotMLC19);
	}

	public String getStrTotBDead19()
	{
		return getStrTotBDead("19");
	}

	public void setStrTotBDead19(String strTotBDead19)
	{
		setStrTotBDead("19", strTotBDead19);
	}

	public String getStrTotUnk19()
	{
		return getStrTotUnk("19");
	}

	public void setStrTotUnk19(String strTotUnk19)
	{
		setStrTotUnk("19", strTotUnk19);
	}

	public String getStrTotGen20()
	{
		return getStrTotGen("20");
	}

	public void setStrTotGen20(String strTotGen20)
	{
		setStrTotGen("20", strTotGen20);
	}

	public String getStrTotEmg20()
	{
		return getStrTotEmg("20");
	}

	public void setStrTotEmg20(String strTotEmg20)
	{
		setStrTotEmg("20", strTotEmg20);
	}

	public String getStrTotMLC20()
	{
		return getStrTotMLC("20");
	}

	public void setStrTotMLC20(String strTotMLC20)
	{
		setStrTotMLC("20", strTotMLC20);
	}

	public String getStrTotBDead20()
	{
		return getStrTotBDead("20");
	}

	public void setStrTotBDead20(String strTotBDead20)
	{
		setStrTotBDead("20", strTotBDead20);
	}

	public String getStrTotUnk20()
	{
		return getStrTotUnk("20");
	}

	public void setStrTotUnk20(String strTotUnk20)
	{
		setStrTotUnk("20", strTotUnk20);
	}

	public String getStrTotGen21()
	{
		return getStrTotGen("21");
	}

	public void setStrTotGen21(String strTotGen21)
	{
		setStrTotGen("21", strTotGen21);
	}

	public String getStrTotEmg21()
	{
		return getStrTotEmg("21");
	}

	public void setStrTotEmg21(String strTotEmg21)
	{
		setStrTotEmg("21", strTotEmg21);
	}

	public String getStrTotMLC21()
	{
		return getStrTotMLC("21");
	}

	public void setStrTotMLC21(String strTotMLC21)
	{
		setStrTotMLC("21", strTotMLC21);
	}

	public String getStrTotBDead21()
	{
		return getStrTotBDead("21");
	}

	public void setStrTotBDead21(String strTotBDead21)
	{
		setStrTotBDead("21", strTotBDead21);
	}

	public String getStrTotUnk21()
	{
		return getStrTotUnk("21");
	}

	public void setStrTotUnk21(String strTotUnk21)
	{
		setStrTotUnk("21", strTotUnk21);
	}

	public String getStrTotGen22()
	{
		return getStrTotGen("22");
	}

	public void setStrTotGen22(String strTotGen22)
	{
		setStrTotGen("22", strTotGen22);
	}

	public String getStrTotEmg22()
	{
		return getStrTotEmg("22");
	}

	public void setStrTotEmg22(String strTotEmg22)
	{
		setStrTotEmg("22", strTotEmg22);
	}

	public String getStrTotMLC22()
	{
		return getStrTotMLC("22");
	}

	public void setStrTotMLC22(String strTotMLC22)
	{
		setStrTotMLC("22", strTotMLC22);
	}

	public String getStrTotBDead22()
	{
		return getStrTotBDead("22");
	}

	public void setStrTotBDead22(String strTotBDead22)
	{
		setStrTotBDead("22", strTotBDead22);
	}

	public String getStrTotUnk22()
	{
		return getStrTotUnk("22");
	}

	public void setStrTotUnk22(String strTotUnk22)
	{
		setStrTotUnk("22", strTotUnk22);
	}

	public String getStrTotGen23()
	{
		return getStrTotGen("23");
	}

	public void setStrTotGen23(String strTotGen23)
	{
		setStrTotGen("23", strTotGen23);
	}

	public String getStrTotEmg23()
	{
		return getStrTotEmg("23");
	}

	public void setStrTotEmg23(String strTotEmg23)
	{
		setStrTotEmg("23", strTotEmg23);
	}

	public String getStrTotMLC23()
	{
		return getStrTotMLC("23");
	}

	public void setStrTotMLC23(String strTotMLC23)
	{
		setStrTotMLC("23", strTotMLC23);
	}

	public String getStrTotBDead23()
	{
		return getStrTotBDead("23");
	}

	public void setStrTotBDead23(String strTotBDead23)
	{
		setStrTotBDead("23", strTotBDead23);
	}

	public String getStrTotUnk23()
	{
		return getStrTotUnk("23");
	}

	public void setStrTotUnk23(String strTotUnk23)
	{
		setStrTotUnk("23", strTotUnk23);
	}

	public String getStrTotGen24()
	{
		return getStrTotGen("24");
	}

	public void setStrTotGen24(String strTotGen24)
	{
		setStrTotGen("24", strTotGen24);
	}

	public String getStrTotEmg24()
	{
		return getStrTotEmg("24");
	}

	public void setStrTotEmg24(String strTotEmg24)
	{
		setStrTotEmg("24", strTotEmg24);
	}

	public String getStrTotMLC24()
	{
		return getStrTotMLC("24");
	}

	public void setStrTotMLC24(String strTotMLC24)
	{
		setStrTotMLC("24", strTotMLC24);
	}

	public String getStrTotBDead24()
	{
		return getStrTotBDead("24");
	}

	public void setStrTotBDead24(String strTotBDead24)
	{
		setStrTotBDead("24", strTotBDead24);
	}

	public String getStrTotUnk24()
	{
		return getStrTotUnk("24");
	}

	public void setStrTotUnk24(String strTotUnk24)
	{
		setStrTotUnk("24", strTotUnk24);
	}

	public String getStrTotGen25()
	{
		return getStrTotGen("25");
	}

	public void setStrTotGen25(String strTotGen25)
	{
		setStrTotGen("25", strTotGen25);
	}

	public String getStrTotEmg25()
	{
		return getStrTotEmg("25");
	}

	public void setStrTotEmg25(String strTotEmg25)
	{
		setStrTotEmg("25", strTotEmg25);
	}

	public String getStrTotMLC25()
	{
		return getStrTotMLC("25");
	}

	public void setStrTotMLC25(String strTotMLC25)
	{
		setStrTotMLC("25", strTotMLC25);
	}

	public String getStrTotBDead25()
	{
		return getStrTotBDead("25");
	}

	public void setStrTotBDead25(String strTotBDead25)
	{
		setStrTotBDead("25", strTotBDead25);
	}

	public String getStrTotUnk25()
	{
		return getStrTotUnk("25");
	}

	public void setStrTotUnk25(String strTotUnk25)
	{
		setStrTotUnk("25", strTotUnk25);
	}

	public String getStrTotGen26()
	{
		return getStrTotGen("26");
	}

	public void setStrTotGen26(String strTotGen26)
	{
		setStrTotGen("26", strTotGen26);
	}

	public String getStrTotEmg26()
	{
		return getStrTotEmg("26");
	}

	public void setStrTotEmg26(String strTotEmg26)
	{
		setStrTotEmg("26", strTotEmg26);
	}

	public String getStrTotMLC26()
	{
		return getStrTotMLC("26");
	}

	public void setStrTotMLC26(String strTotMLC26)
	{
		setStrTotMLC("26", strTotMLC26);
	}

	public String getStrTotBDead26()
	{
		return getStrTotBDead("26");
	}

	public void setStrTotBDead26(String strTotBDead26)
	{
		setStrTotBDead("26", strTotBDead26);
	}

	public String getStrTotUnk26()
	{
		return getStrTotUnk("26");
	}

	public void setStrTotUnk26(String strTotUnk26)
	{
		setStrTotUnk("26", strTotUnk26);
	}

	public String getStrTotGen27()
	{
		return getStrTotGen("27");
	}

	public void setStrTotGen27(String strTotGen27)
	{
		setStrTotGen("27", strTotGen27);
	}

	public String getStrTotEmg27()
	{
		return getStrTotEmg("27");
	}

	public void setStrTotEmg27(String strTotEmg27)
	{
		setStrTotEmg("27", strTotEmg27);
	}

	public String getStrTotMLC27()
	{
		return getStrTotMLC("27");
	}

	public void setStrTotMLC27(String strTotMLC27)
	{
		setStrTotMLC("27", strTotMLC27);
	}

	public String getStrTotBDead27()
	{
		return getStrTotBDead("27");
	}

	public void setStrTotBDead27(String strTotBDead27)
	{
		setStrTotBDead("27", strTotBDead27);
	}

	public String getStrTotUnk27()
	{
		return getStrTotUnk("27");
	}

	public void setStrTotUnk27(String strTotUnk27)
	{
		setStrTotUnk("27", strTotUnk27);
	}

	public String getStrTotGen28()
	{
		return getStrTotGen("28");
	}

	public void setStrTotGen28(String strTotGen28)
	{
		setStrTotGen("28", strTotGen28);
	}

	public String getStrTotEmg28()
	{
		return getStrTotEmg("28");
	}

	public void setStrTotEmg28(String strTotEmg28)
	{
		setStrTotEmg("28", strTotEmg28);
	}

	public String getStrTotMLC28()
	{
		return getStrTotMLC("28");
	}

	public void setStrTotMLC28(String strTotMLC28)
	{
		setStrTotMLC("28", strTotMLC28);
	}

	public String getStrTotBDead28()
	{
		return getStrTotBDead("28");
	}

	public void setStrTotBDead28(String strTotBDead28)
	{
		setStrTotBDead("28", strTotBDead28);
	}

	public String getStrTotUnk28()
	{
		return getStrTotUnk("28");
	}

	public void setStrTotUnk28(String strTotUnk28)
	{
		setStrTotUnk("28", strTotUnk28);
	}

	public String getStrTotGen29()
	{
		return getStrTotGen("29");
	}

	public void setStrTotGen29(String strTotGen29)
	{
		setStrTotGen("29", strTotGen29);
	}

	public String getStrTotEmg29()
	{
		return getStrTotEmg("29");
	}

	public void setStrTotEmg29(String strTotEmg29)
	{
		setStrTotEmg("29", strTotEmg29);
	}

	public String getStrTotMLC29()
	{
		return getStrTotMLC("29");
	}

	public void setStrTotMLC29(String strTotMLC29)
	{
		setStrTotMLC("29", strTotMLC29);
	}

	public String getStrTotBDead29()
	{
		return getStrTotBDead("29");
	}

	public void setStrTotBDead29(String strTotBDead29)
	{
		setStrTotBDead("29", strTotBDead29);
	}

	public String getStrTotUnk29()
	{
		return getStrTotUnk("29");
	}

	public void setStrTotUnk29(String strTotUnk29)
	{
		setStrTotUnk("29", strTotUnk29);
	}

	public String getStrTotGen30()
	{
		return getStrTotGen("30");
	}

	public void setStrTotGen30(String strTotGen30)
	{
		setStrTotGen("30", strTotGen30);
	}

	public String getStrTotEmg30()
	{
		return getStrTotEmg("30");
	}

	public void setStrTotEmg30(String strTotEmg30)
	{
		setStrTotEmg("30", strTotEmg30);
	}

	public String getStrTotMLC30()
	{
		return getStrTotMLC("30");
	}

	public void setStrTotMLC30(String strTotMLC30)
	{
		setStrTotMLC("30", strTotMLC30);
	}

	public String getStrTotBDead30()
	{
		return getStrTotBDead("30");
	}

	public void setStrTotBDead30(String strTotBDead30)
	{
		setStrTotBDead("30", strTotBDead30);
	}

	public String getStrTotUnk30()
	{
		return getStrTotUnk("30");
	}

	public void setStrTotUnk30(String strTotUnk30)
	{
		setStrTotUnk("30", strTotUnk30);
	}

	public String getStrTotGen31()
	{
		return getStrTotGen("31");
	}

	public void setStrTotGen31(String strTotGen31)
	{
		setStrTotGen("31", strTotGen31);
	}

	public String getStrTotEmg31()
	{
		return getStrTotEmg("31");
	}

	public void setStrTotEmg31(String strTotEmg31)
	{
		setStrTotEmg("31", strTotEmg31);
	}

	public String getStrTotMLC31()
	{
		return getStrTotMLC("31");
	}

	public void setStrTotMLC31(String strTotMLC31)
	{
		setStrTotMLC("31", strTotMLC31);
	}

	public String getStrTotBDead31()
	{
		return getStrTotBDead("31");
	}

	public void setStrTotBDead31(String strTotBDead31)
	{
		setStrTotBDead("31", strTotBDead31);
	}

	public String getStrTotUnk31()
	{
		return getStrTotUnk("31");
	}

	public void setStrTotUnk31(String strTotUnk31)
	{
		setStrTotUnk("31", strTotUnk31);
	}
	
	// Month Automatic Getter & Setter
	// strMonTotGen, strMonTotEmg, strMonTotMLC, strMonTotBDead, strMonTotUnk 
	public String getStrMonTotGen()
	{
		return this.voMonthStat.getStrTotGen();
	}
	public void setStrMonTotGen(String strTot)
	{
		this.voMonthStat.setStrTotGen(strTot);
	}

	public String getStrMonTotEmg(String strDay)
	{
		return this.voMonthStat.getStrTotEmg();
	}
	public void setStrMonTotEmg(String strTot)
	{
		this.voMonthStat.setStrTotEmg(strTot);
	}
	
	public String getStrMonTotMLC(String strDay)
	{
		return this.voMonthStat.getStrTotMLC();
	}
	public void setStrMonTotMLC(String strTot)
	{
		this.voMonthStat.setStrTotMLC(strTot);
	}

	public String getStrMonTotBDead(String strDay)
	{
		return this.voMonthStat.getStrTotBDead();
	}
	public void setStrMonTotBDead(String strTot)
	{
		this.voMonthStat.setStrTotBDead(strTot);
	}

	public String getStrMonTotUnk(String strDay)
	{
		return this.voMonthStat.getStrTotUnk();
	}
	public void setStrMonTotUnk(String strTot)
	{
		this.voMonthStat.setStrTotUnk(strTot);
	}
}
