package registration.vo;

import java.util.LinkedHashMap;

import hisglobal.vo.ValueObject;

public class DSSEpisodeStatisticVO extends ValueObject
{
	public static final String STAT_MODE_DAY = "1";
	public static final String STAT_MODE_MONTH = "2";
	public static final String STAT_MODE_YEAR = "3";

	public static final int STAT_AGE_COUNT_SIZE = 3;
	public static final int STAT_DAY_COUNT_SIZE = 3;
	public static final int STAT_MONTH_COUNT_SIZE = 5;

	public static class GenderCount
	{
		public int maleOld = 0;
		public int maleNew = 0;
		public int femaleOld = 0;
		public int femaleNew = 0;
		public int otherOld = 0;
		public int otherNew = 0;

		public int childMaleOld = 0;
		public int childMaleNew = 0;
		public int childFemaleOld = 0;
		public int childFemaleNew = 0;

		
		
		public void addCounts(GenderCount objGenCnt_p)
		{
			maleOld += objGenCnt_p.maleOld;
			maleNew += objGenCnt_p.maleNew;
			femaleOld += objGenCnt_p.femaleOld;
			femaleNew += objGenCnt_p.femaleNew;
			otherOld += objGenCnt_p.otherOld;
			otherNew += objGenCnt_p.otherNew;

			childMaleOld += objGenCnt_p.childMaleOld;
			childMaleNew += objGenCnt_p.childMaleNew;
			childFemaleOld += objGenCnt_p.childFemaleOld;
			childFemaleNew += objGenCnt_p.childFemaleNew;
		}
		
		public void addCountsInChild(GenderCount objGenCnt_p)
		{
			childMaleOld += objGenCnt_p.maleOld;
			childMaleNew += objGenCnt_p.maleNew;
			childFemaleOld += objGenCnt_p.femaleOld;
			childFemaleNew += objGenCnt_p.femaleNew;
		}

		
		public int getMaleOld()
		{
			return maleOld;
		}
		
		public void setMaleOld(int maleOld)
		{
			this.maleOld = maleOld;
		}
		
		public int getMaleNew()
		{
			return maleNew;
		}

		public void setMaleNew(int maleNew)
		{
			this.maleNew = maleNew;
		}

		public int getFemaleOld()
		{
			return femaleOld;
		}

		public void setFemaleOld(int femaleOld)
		{
			this.femaleOld = femaleOld;
		}

		public int getFemaleNew()
		{
			return femaleNew;
		}

		public void setFemaleNew(int femaleNew)
		{
			this.femaleNew = femaleNew;
		}

		public int getOtherOld()
		{
			return otherOld;
		}

		public void setOtherOld(int otherOld)
		{
			this.otherOld = otherOld;
		}

		public int getOtherNew()
		{
			return otherNew;
		}

		public void setOtherNew(int otherNew)
		{
			this.otherNew = otherNew;
		}

		public int getChildMaleOld()
		{
			return childMaleOld;
		}

		public void setChildMaleOld(int childMaleOld)
		{
			this.childMaleOld = childMaleOld;
		}

		public int getChildMaleNew()
		{
			return childMaleNew;
		}

		public void setChildMaleNew(int childMaleNew)
		{
			this.childMaleNew = childMaleNew;
		}

		public int getChildFemaleOld()
		{
			return childFemaleOld;
		}

		public void setChildFemaleOld(int childFemaleOld)
		{
			this.childFemaleOld = childFemaleOld;
		}

		public int getChildFemaleNew()
		{
			return childFemaleNew;
		}

		public void setChildFemaleNew(int childFemaleNew)
		{
			this.childFemaleNew = childFemaleNew;
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

	public DSSEpisodeStatisticVO(String strStatMode_p)
	{
		this.strStatMode = strStatMode_p;
	}

	public DSSEpisodeStatisticVO(String strStatMode_p, String strDay_p)
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
			String strAge;
			String strMaleNewCount="0", strMaleOldCount="0";
			String strFemaleNewCount="0", strFemaleOldCount="0";
			String strOtherNewCount="0", strOtherOldCount="0";
			
			int curPos = STAT_AGE_COUNT_SIZE;
			int cntSize = STAT_DAY_COUNT_SIZE;
			if(this.strStatMode.equals(STAT_MODE_MONTH)) cntSize = STAT_MONTH_COUNT_SIZE;
			
			strAge = strAgeGenCount.substring(0, curPos);
			do
			{
				String cntNew = strAgeGenCount.substring(curPos+1, curPos+cntSize+1);
				String cntOld = strAgeGenCount.substring(curPos+cntSize+2, curPos+cntSize+cntSize+2);
				if(strAgeGenCount.charAt(curPos)=='M')
				{
					strMaleNewCount = cntNew;
					strMaleOldCount = cntOld;
				}
				else if(strAgeGenCount.charAt(curPos)=='F')
				{
					strFemaleNewCount = cntNew;
					strFemaleNewCount = cntNew;
				}
				else if(strAgeGenCount.charAt(curPos)=='O')
				{
					strOtherNewCount = cntNew;
					strOtherOldCount = cntOld;
				}
				
				curPos += (cntSize+cntSize+2);
				
			} while(curPos<strAgeGenCount.length());
			
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
			genCount.maleNew = Integer.parseInt(strMaleNewCount);
			genCount.maleOld = Integer.parseInt(strMaleOldCount);
			genCount.femaleNew = Integer.parseInt(strFemaleNewCount);
			genCount.femaleOld = Integer.parseInt(strFemaleOldCount);
			genCount.otherNew = Integer.parseInt(strOtherNewCount);
			genCount.otherOld = Integer.parseInt(strOtherOldCount);
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
