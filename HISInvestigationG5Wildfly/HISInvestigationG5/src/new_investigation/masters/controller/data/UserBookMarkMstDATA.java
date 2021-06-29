package new_investigation.masters.controller.data;

import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import new_investigation.masters.InvestigationMasterDelegate;
import new_investigation.vo.LabTestSampleMstVO;
import new_investigation.vo.UserBookMarkMstVO;

public class UserBookMarkMstDATA {
	
	public static Map getEssential(UserBookMarkMstVO UserBookMarkMstVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.getEssential(UserBookMarkMstVO, _UserVO);
	}
	public static void saveUserBookMark(UserBookMarkMstVO userBookMarkMstVO,List<UserBookMarkMstVO> lstUserBookMarkVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.saveUserBookMark(userBookMarkMstVO,lstUserBookMarkVO, _UserVO);
	}

	public static Map fetchModifyUserBookMark(UserBookMarkMstVO UserBookMarkMstVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchModifyUserBookMark(UserBookMarkMstVO, _UserVO);
	}
	public static Map getBookMarkType(UserBookMarkMstVO UserBookMarkMstVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.getBookMarkType(UserBookMarkMstVO, _UserVO);
	}
	public static List getUnit(String deptcode, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.getUnit(deptcode, _UserVO);
	}
	public static List getTest(UserBookMarkMstVO userBookMarkMstVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.getTest(userBookMarkMstVO, _UserVO);
	}

	public static List<UserBookMarkMstVO> getTestByGroup(UserBookMarkMstVO userBookMarkMstVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.getTestByGroup(userBookMarkMstVO, _UserVO);
	}
	
	public static void modifySaveUserBookmark(UserBookMarkMstVO userBookMarkMstVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.modifySaveUserBookmark(userBookMarkMstVO, _UserVO);
	}
	public static void getTestName(UserBookMarkMstVO userBookMarkMstVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.getTestName(userBookMarkMstVO, _UserVO);
	}
	
	public static List getTestgroup(UserBookMarkMstVO userBookMarkMstVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.getTestgroup(userBookMarkMstVO, _UserVO);
	}
	
}
