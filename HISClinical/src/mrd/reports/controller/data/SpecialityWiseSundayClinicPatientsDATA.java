package mrd.reports.controller.data;

import hisglobal.presentation.ReportDATA;
import hisglobal.vo.UserVO;

import java.util.Map;

import registration.bo.delegate.EssentialDelegate;

public class SpecialityWiseSundayClinicPatientsDATA extends ReportDATA
{
	
	public static Map getUnitBasedOnWeekDay(String day,UserVO _userVO)
	{
		return new EssentialDelegate().getUnitBasedOnWeekDay(day,_userVO);
	}
}