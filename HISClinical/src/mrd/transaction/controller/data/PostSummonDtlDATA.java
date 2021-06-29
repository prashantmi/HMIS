package mrd.transaction.controller.data;

import java.util.Map;

import mrd.transaction.delegate.MrdDelegate;
import mrd.transaction.delegate.MrdEssentialDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.SummonDetailVO;
import hisglobal.vo.UserVO;

public class PostSummonDtlDATA extends ControllerDATA
{
	public static Map getEssenForPostSummonDtl(UserVO userVO)
	{
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		return delegate.getEssenForPostSummonDtl(userVO); 
	}
	public static void savePostSummonDetail(SummonDetailVO summonDetailVO,UserVO userVO)
	{
		MrdDelegate delegate= new MrdDelegate();
		delegate.savePostSummonDetail(summonDetailVO,userVO); 
	}
}
