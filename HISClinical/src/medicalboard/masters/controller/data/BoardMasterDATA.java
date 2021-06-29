package medicalboard.masters.controller.data;

import hisglobal.vo.MedicalBoardMasterVO;
import hisglobal.vo.UserVO;
import java.util.Map;
import medicalboard.masters.delegate.MbMasterDelegate;
import medicalboard.masters.delegate.MbMasterEssentialDelegate;


public class BoardMasterDATA
{
	
	public static Map getBoardEssentials(UserVO _UserVO)
	{
		MbMasterEssentialDelegate mEssentialDelegate=new MbMasterEssentialDelegate();
		Map mp=mEssentialDelegate.getBoardEssentials(_UserVO);
		return mp;		
	}	
	
	public static void saveBoardInfo(MedicalBoardMasterVO _medicalBoardMasterVO,String[] empId,String[] escortedBy,String[] roleID,UserVO _UserVO)
	{
		MbMasterDelegate masterDelegate = new MbMasterDelegate();
		masterDelegate.saveBoardInfo(_medicalBoardMasterVO,empId,escortedBy,roleID,_UserVO);
	}
	

	public static Map fetchBoardDetail(MedicalBoardMasterVO medicalBoardMasterVO, UserVO _UserVO)
	{
		MbMasterDelegate masterDelegate = new MbMasterDelegate();
		return (masterDelegate.fetchBoardDetail(medicalBoardMasterVO, _UserVO));
	}


	public static void saveModBoardInfo(MedicalBoardMasterVO _medicalBoardMasterVO,String[] empId,String[] escortedBy,String[] roleID,UserVO _UserVO)
	{
		MbMasterDelegate masterDelegate = new MbMasterDelegate();
		masterDelegate.saveModBoardInfo(_medicalBoardMasterVO,empId,escortedBy,roleID,_UserVO);
	}
}
