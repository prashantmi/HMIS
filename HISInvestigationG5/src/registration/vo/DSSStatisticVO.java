package registration.vo;

import java.util.LinkedHashMap;

import hisglobal.vo.ValueObject;

public class DSSStatisticVO extends ValueObject
{
	public static final String STAT_MODE_DAY = "1";
	public static final String STAT_MODE_MONTH = "2";
	public static final String STAT_MODE_YEAR = "3";

	public static final int STAT_AGE_COUNT_SIZE = 3;
	public static final int STAT_DAY_COUNT_SIZE = 3;
	public static final int STAT_MONTH_COUNT_SIZE = 5;

	public static class GenderCount
	{
		public int male = 0;
		public int female = 0;
		public int other = 0;

		public int getMale()
		{
			return male;
		}

		public int getFemale()
		{
			return female;
		}

		public int getOther()
		{
			return other;
		}

		public void addCounts(GenderCount objGenCnt_p)
		{
			male += objGenCnt_p.male;
			female += objGenCnt_p.female;
			other += objGenCnt_p.other;
		}
	};

	private String strStatMode; // 1:Day, 2:Month, 3:Year
	private String strMonth; // Month : MM
	private String strYear; // Year : YYYY
	private String strDay; // Day : DD
	private String strDate; // Date : DD-Mon-YYYY

	private String strTotGen;
	private String strTotEmg;
	private String strTotMLC;
	private String strTotBDead;
	private String strTotUnk;

	public LinkedHashMap<Integer, GenderCount> mapAgeWiseTotGen;
	public LinkedHashMap<Integer, GenderCount> mapAgeWiseTotEmg;
	public LinkedHashMap<Integer, GenderCount> mapAgeWiseTotMLC;
	public LinkedHashMap<Integer, GenderCount> mapAgeWiseTotBDead;
	public LinkedHashMap<Integer, GenderCount> mapAgeWiseTotUnk;

	public DSSStatisticVO(String strStatMode_p)
	{
		this.strStatMode = strStatMode_p;
	}

	public DSSStatisticVO(String strStatMode_p, String strDay_p)
	{
		this.strStatMode = strStatMode_p;
		this.strDay = strDay_p;
	}

	public boolean haveData()
	{
		if ((strTotGen == null || strTotGen.trim().equals("")) && (strTotEmg == null || strTotEmg.trim().equals(""))
				&& (strTotMLC == null || strTotMLC.trim().equals("")) && (strTotBDead == null || strTotBDead.trim().equals(""))
				&& (strTotUnk == null || strTotUnk.trim().equals(""))) return false;
		else return true;
	}

	public boolean isExtracted()
	{
		if (!haveData()) return true;
		else if (mapAgeWiseTotGen == null && mapAgeWiseTotEmg == null && mapAgeWiseTotMLC == null && mapAgeWiseTotBDead == null
				&& mapAgeWiseTotUnk == null) return false;
		else return true;
	}

	public boolean extract()
	{
		if (!isExtracted())
		{
			if (this.strTotGen != null && !strTotGen.trim().equals("")) this.mapAgeWiseTotGen = creteExtractMap(this.strTotGen);
			if (this.strTotEmg != null && !strTotEmg.trim().equals("")) this.mapAgeWiseTotEmg = creteExtractMap(this.strTotEmg);
			if (this.strTotMLC != null && !strTotMLC.trim().equals("")) this.mapAgeWiseTotMLC = creteExtractMap(this.strTotMLC);
			if (this.strTotBDead != null && !strTotBDead.trim().equals("")) this.mapAgeWiseTotBDead = creteExtractMap(this.strTotBDead);
			if (this.strTotUnk != null && !strTotUnk.trim().equals("")) this.mapAgeWiseTotUnk = creteExtractMap(this.strTotUnk);
			return true;
		}
		else return false;
	}

	private LinkedHashMap<Integer, GenderCount> creteExtractMap(String strTot_p)
	{
		LinkedHashMap<Integer, GenderCount> mp = new LinkedHashMap<Integer, GenderCount>();
		String[] arrAgeWise = strTot_p.substring(1).split("#");
		for (String strAgeGenCount : arrAgeWise)
		{
			String strAge, strMaleCount="0", strFemaleCount="0", strOtherCount="0";
			
			int curPos = STAT_AGE_COUNT_SIZE;
			int cntSize = STAT_DAY_COUNT_SIZE;
			if(this.strStatMode.equals(STAT_MODE_MONTH)) cntSize = STAT_MONTH_COUNT_SIZE;
			
			strAge = strAgeGenCount.substring(0, curPos);
			do
			{
				String cnt = strAgeGenCount.substring(curPos+1, curPos+cntSize+1);
				if(strAgeGenCount.charAt(curPos)=='M')
					strMaleCount = cnt;
				else if(strAgeGenCount.charAt(curPos)=='F')
					strFemaleCount = cnt;
				else if(strAgeGenCount.charAt(curPos)=='O')
					strOtherCount = cnt;
				
				curPos += (cntSize+1);
				
			}while(curPos<strAgeGenCount.length());
			
			/*if(strAgeGenCount.charAt(curPos)=='M')
			{

				if(strAgeGenCount.contains("F"))
					strMaleCount = strAgeGenCount.substring(strAgeGenCount.indexOf("M")+1, strAgeGenCount.indexOf("F"));
				else if(strAgeGenCount.contains("O"))
					strMaleCount = strAgeGenCount.substring(strAgeGenCount.indexOf("M")+1, strAgeGenCount.indexOf("O"));
				else
					strMaleCount = strAgeGenCount.substring(strAgeGenCount.indexOf("M")+1);
			}
			else if(strAgeGenCount.contains("F"))
				strAge = strAgeGenCount.substring(0, strAgeGenCount.indexOf("F"));
			else
				strAge = strAgeGenCount.substring(0, strAgeGenCount.indexOf("O"));
			
			if(strAgeGenCount.contains("F"))
			{
				if(strAgeGenCount.contains("O"))
					strFemaleCount = strAgeGenCount.substring(strAgeGenCount.indexOf("F")+1, strAgeGenCount.indexOf("O"));
				else
					strFemaleCount = strAgeGenCount.substring(strAgeGenCount.indexOf("F")+1);
			}

			if(strAgeGenCount.contains("O"))
				strOtherCount = strAgeGenCount.substring(strAgeGenCount.indexOf("O")+1);*/
			
			GenderCount genCount = new GenderCount();
			genCount.male = Integer.parseInt(strMaleCount);
			genCount.female = Integer.parseInt(strFemaleCount);
			genCount.other = Integer.parseInt(strOtherCount);
			mp.put(Integer.parseInt(strAge), genCount);
		}
		return mp;
	}

	public String getStrStatMode()
	{
		return strStatMode;
	}

	public void setStrStatMode(String strStatMode)
	{
		this.strStatMode = strStatMode;
	}

	public String getStrMonth()
	{
		return strMonth;
	}

	public void setStrMonth(String strMonth)
	{
		this.strMonth = strMonth;
	}

	public String getStrYear()
	{
		return strYear;
	}

	public void setStrYear(String strYear)
	{
		this.strYear = strYear;
	}

	public String getStrDay()
	{
		return strDay;
	}

	public void setStrDay(String strDay)
	{
		this.strDay = strDay;
	}

	public String getStrDate()
	{
		return strDate;
	}

	public void setStrDate(String strDate)
	{
		this.strDate = strDate;
	}

	public String getStrTotGen()
	{
		return strTotGen;
	}

	public void setStrTotGen(String strTotGen)
	{
		this.strTotGen = strTotGen;
	}

	public String getStrTotEmg()
	{
		return strTotEmg;
	}

	public void setStrTotEmg(String strTotEmg)
	{
		this.strTotEmg = strTotEmg;
	}

	public String getStrTotMLC()
	{
		return strTotMLC;
	}

	public void setStrTotMLC(String strTotMLC)
	{
		this.strTotMLC = strTotMLC;
	}

	public String getStrTotBDead()
	{
		return strTotBDead;
	}

	public void setStrTotBDead(String strTotBDead)
	{
		this.strTotBDead = strTotBDead;
	}

	public String getStrTotUnk()
	{
		return strTotUnk;
	}

	public void setStrTotUnk(String strTotUnk)
	{
		this.strTotUnk = strTotUnk;
	}

}
