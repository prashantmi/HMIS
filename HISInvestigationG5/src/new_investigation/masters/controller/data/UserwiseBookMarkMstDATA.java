package new_investigation.masters.controller.data;

import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import new_investigation.masters.InvestigationMasterDelegate;
import new_investigation.vo.LabTestSampleMstVO;
import new_investigation.vo.UserBookMarkMstVO;
import new_investigation.vo.UserwiseBookMarkMstVO;

public class UserwiseBookMarkMstDATA {
	
	public static Map getEssential(UserwiseBookMarkMstVO userwiseBookMarkMstVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.getEssential(userwiseBookMarkMstVO, _UserVO);
	}
	public static void saveUserBookMark(UserwiseBookMarkMstVO userwiseBookMarkMstVO,List<UserwiseBookMarkMstVO> lstUserwiseBookMarkVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.saveUserBookMark(userwiseBookMarkMstVO,lstUserwiseBookMarkVO, _UserVO);
	}

	public static Map fetchModifyUserBookMark(UserwiseBookMarkMstVO userwiseBookMarkMstVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchModifyUserBookMark(userwiseBookMarkMstVO, _UserVO);
	}
	public static Map getBookMarkType(UserwiseBookMarkMstVO userwiseBookMarkMstVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.getBookMarkType(userwiseBookMarkMstVO, _UserVO);
	}
	public static List getUnit(String deptcode, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.getUnit(deptcode, _UserVO);
	}
	public static List getTest(UserwiseBookMarkMstVO userwiseBookMarkMstVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.getTest(userwiseBookMarkMstVO, _UserVO);
	}
	
	public static List<UserwiseBookMarkMstVO> getTestByGroup(UserwiseBookMarkMstVO userwiseBookMarkMstVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.getTestByGroup(userwiseBookMarkMstVO, _UserVO);
	}
	
	public static void modifySaveUserBookmark(UserwiseBookMarkMstVO userwiseBookMarkMstVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.modifySaveUserBookmark(userwiseBookMarkMstVO, _UserVO);
	}
	public static void getTestName(UserwiseBookMarkMstVO userwiseBookMarkMstVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.getTestName(userwiseBookMarkMstVO, _UserVO);
	}
	
	public static List getTestgroup(UserwiseBookMarkMstVO userwiseBookMarkMstVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.getTestgroup(userwiseBookMarkMstVO, _UserVO);
	}
}
