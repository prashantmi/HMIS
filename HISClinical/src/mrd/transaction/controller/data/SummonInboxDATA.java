package mrd.transaction.controller.data;

import java.util.Map;

import mrd.transaction.delegate.MrdDelegate;
import mrd.transaction.delegate.MrdEssentialDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;

public class SummonInboxDATA extends ControllerDATA
{
	public static Map getEssentialForSummonInbox(UserVO userVO)
	{
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		return delegate.getEssentialForSummonInbox(userVO); 
	} 
	
	public static void saveSummonAcceptenceDtl (String summonId,UserVO userVO)
	{
		MrdDelegate delegate= new MrdDelegate();
		delegate.saveSummonAcceptedDetail(summonId, userVO); 
	}
}
