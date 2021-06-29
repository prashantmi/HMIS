package mrd.transaction.controller.data;

import java.util.HashMap;
import java.util.List;

import mrd.transaction.delegate.MrdDelegate;
import mrd.transaction.delegate.MrdEssentialDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.CrNoMergeDtlVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.UserVO;

public class CrNoMergeDtlDATA extends ControllerDATA
{
	public static PatientVO getNotUsedCrNo(String crNo,UserVO userVO)
	{
		MrdEssentialDelegate mrdEssDelegate=new MrdEssentialDelegate();
		return mrdEssDelegate.getNotUsedCrNo(crNo,userVO);
	}
	
	public static void saveNotUsedCrNo(List<CrNoMergeDtlVO> lstNotUsedCRNo,UserVO userVO)
	{
		MrdDelegate mrdDelegate=new MrdDelegate();
		mrdDelegate.saveNotUsedCrNo(lstNotUsedCRNo,userVO);
	}
	
	public static PatientVO[] searchPatient(HashMap searchMap,UserVO userVO)
	{
		MrdEssentialDelegate mrdEssDelegate=new MrdEssentialDelegate();
		return mrdEssDelegate.searchPatient(searchMap,userVO);
	}
	
	public static List<PatientVO> getMergedCrNo(String crNo,UserVO userVO)
	{
		MrdDelegate mrdDelegate=new MrdDelegate();
		return mrdDelegate.getMergedCrNo(crNo,userVO);
	}
	
	public static void revokeMergedCRNo(String reason,String mainCrNo,String crNo,UserVO userVO)
	{
		MrdDelegate mrdDelegate=new MrdDelegate();
		mrdDelegate.revokeMergedCRNo(reason,mainCrNo,crNo,userVO);
	}
	
}
