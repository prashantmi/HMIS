package mrd.transaction.controller.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mrd.transaction.delegate.MrdDelegate;
import mrd.transaction.delegate.MrdEssentialDelegate;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.CrNoMergeDtlVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.UserVO;

public class OnlineCRNoMergeDATA extends ControllerDATA
{
	public static Map getEssentialForOnlineCRNoMerge(UserVO userVO)
	{
		MrdEssentialDelegate mrdEssDelegate=new MrdEssentialDelegate();
		return mrdEssDelegate.getEssentialForOnlineCRNoMerge(userVO);
	}
	
	public static PatientVO[] searchPatient(HashMap searchMap,UserVO userVO)
	{
		MrdEssentialDelegate mrdEssDelegate=new MrdEssentialDelegate();
		return mrdEssDelegate.searchPatient(searchMap,userVO);
	}
	
	public static void saveNotUsedCrNo(List<CrNoMergeDtlVO> lstNotUsedCRNo,UserVO userVO)
	{
		MrdDelegate mrdDelegate=new MrdDelegate();
		mrdDelegate.saveNotUsedCrNo(lstNotUsedCRNo,userVO);
	}
	
	public static List getMainCRNumberList(UserVO userVO)
	{
		MrdDelegate mrdDelegate=new MrdDelegate();
		return mrdDelegate.getMainCRNumberList(userVO);
	}
}
