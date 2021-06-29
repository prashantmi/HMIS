package mrd.transaction.controller.data;

import java.util.Map;

import mrd.transaction.delegate.MrdDelegate;
import mrd.transaction.delegate.MrdEssentialDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.SummonDetailVO;
import hisglobal.vo.UserVO;

public class SummonDetailDATA extends ControllerDATA
{
	public static Map getEssentialForSummonDtl(String cidNoFlag,UserVO userVO)
	{
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		return delegate.getEssentialForSummonDtl(cidNoFlag,userVO); 
	}
	 
	public static void saveSummonDetail(SummonDetailVO summonDetailVO,UserVO userVO)
	{
		MrdDelegate  mrdDelegate= new MrdDelegate();
		mrdDelegate.saveSummonDetail(summonDetailVO,userVO); 
	}
	
	public static Map searchPatientDtl(String searchType,String _str,UserVO userVO)
	{
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		return delegate.searchPatientDtl(searchType,_str,userVO); 
	}
	
	
}
