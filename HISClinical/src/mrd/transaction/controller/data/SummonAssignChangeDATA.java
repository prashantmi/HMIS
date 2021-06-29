package mrd.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.AssignmentChangeDtlVO;
import hisglobal.vo.SummonDetailVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import mrd.transaction.delegate.MrdDelegate;
import mrd.transaction.delegate.MrdEssentialDelegate;

public class SummonAssignChangeDATA extends ControllerDATA
{
	public static Map getEssenForSummonAssignChange(UserVO userVO)
	{
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		return delegate.getEssenForSummonAssignChange(userVO); 
	}
	
	public static List searchEmpDetail(String _fName,String _mName,String _lName,UserVO userVO)
	{
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		return delegate.searchEmpDetail(_fName,_mName,_lName,userVO); 
	}
	
	public static void saveChangeAssignDetail(AssignmentChangeDtlVO assignChangeDtlVO,SummonDetailVO summonDetailVO,UserVO userVO)
	{
		MrdDelegate delegate= new MrdDelegate();
		delegate.saveChangeAssignDetail(assignChangeDtlVO,summonDetailVO,userVO); 
	}
}
