package mrd.transaction.controller.data;

import java.util.Map;

import mrd.transaction.delegate.MrdEssentialDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;

public class SummonTrackingDATA extends ControllerDATA
{
	public static Map searchSummonDetail(String SearchCriteria,String fromDate,String toDate,String empId,String summonTypeId,UserVO userVO)
	{
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		return delegate.searchSummonDetail(SearchCriteria,fromDate,toDate,empId,summonTypeId,userVO); 
	}
	
	public static Map getEssentialForSummonTracking(UserVO userVO)
	{
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		return delegate.getEssentialForSummonTracking(userVO); 
	}
}
