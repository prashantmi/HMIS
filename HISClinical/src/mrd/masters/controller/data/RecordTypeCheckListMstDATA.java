package mrd.masters.controller.data;

import hisglobal.vo.RecordTypeCheckListMstVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import mrd.masters.delegate.MrdMasterDelegate;

public class RecordTypeCheckListMstDATA 
{
	public static Map getEssentialForRecordTypeCheckListMst(UserVO userVO)
	{
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		return masterDelegate.getEssentialForRecordTypeCheckListMst(userVO);
	}
	
	public static Map getAllAddedCheckListByRecordType(RecordTypeCheckListMstVO vo,UserVO userVO)
	{
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		return masterDelegate.getAllAddedCheckListByRecordType(vo,userVO);
	}
	
	public static List getCheckListNotMapped(RecordTypeCheckListMstVO recordTypeCheckListVO,UserVO userVO)
	{
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		return masterDelegate.getCheckListNotMapped(recordTypeCheckListVO, userVO);
	}
	
	public static boolean saveRecordTypeCheckListMst(List recordTypeCheckListVOLst, UserVO _UserVO)
	{
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		boolean hasFlag=masterDelegate.saveRecordTypeCheckListMst(recordTypeCheckListVOLst,_UserVO);
		return hasFlag; 
	}
	
	
}
