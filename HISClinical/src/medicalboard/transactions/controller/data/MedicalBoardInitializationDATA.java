package medicalboard.transactions.controller.data;

import hisglobal.vo.BoardDetailVO;
import hisglobal.vo.MbCertificateTypeMstVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import medicalboard.transactions.delegate.MbTransEssentialDelegate;
import medicalboard.transactions.delegate.MbTransactionDelegate;

public class MedicalBoardInitializationDATA 
{
	public static Map setMedicalBoardInitializationEssentials(UserVO _UserVO)
	{
		MbTransEssentialDelegate mEssentialDelegate=new MbTransEssentialDelegate();
		return mEssentialDelegate.setMedicalBoardInitializationEssentials(_UserVO);
	}
	
	public static List getScheduleList(MbCertificateTypeMstVO certificateTypeMstVO,UserVO _UserVO)
	{
		MbTransEssentialDelegate mEssentialDelegate=new MbTransEssentialDelegate();
		return mEssentialDelegate.getScheduleList(certificateTypeMstVO, _UserVO);
	}
	
	public static Map getBoardDetail(String certificateTypeId ,String scheduleDate,UserVO _UserVO)
	{
		MbTransEssentialDelegate mEssentialDelegate=new MbTransEssentialDelegate();
		return mEssentialDelegate.getBoardDetail(certificateTypeId,scheduleDate,_UserVO);
	}
	
	public static List getTeamDetailByBoardId(String boardId,UserVO _UserVO)
	{
		MbTransEssentialDelegate mEssentialDelegate=new MbTransEssentialDelegate();
		return mEssentialDelegate.getTeamDetailByBoardId(boardId,_UserVO);
	}
	
	public static void saveBoardAndBoardTeamDetail(BoardDetailVO boardDetailVO,List docRoleTeamDetailList,List escortedTeamDetailList,UserVO userVO)
	{
		MbTransactionDelegate mbDelegate=new MbTransactionDelegate();
		mbDelegate.saveBoardAndBoardTeamDetail(boardDetailVO,docRoleTeamDetailList,escortedTeamDetailList,userVO);
	}
	
	public static List getAvailableBoardDetailList(String certificateTypeId ,String scheduleDate,UserVO _UserVO)
	{
		MbTransEssentialDelegate mEssentialDelegate=new MbTransEssentialDelegate();
		return mEssentialDelegate.getAvailableBoardDetailList(certificateTypeId,scheduleDate,_UserVO);
	}
	
	public static List getAssignTeamDetail(String boardNo,UserVO _UserVO)
	{
		MbTransEssentialDelegate mEssentialDelegate=new MbTransEssentialDelegate();
		return mEssentialDelegate.getAssignTeamDetail(boardNo,_UserVO);
	}
	
	public static void addNewBoardAndBoardTeamDetail(BoardDetailVO boardDetailVO,List docRoleTeamDetailList,List escortedTeamDetailList,UserVO userVO)
	{
		MbTransactionDelegate mbDelegate=new MbTransactionDelegate();
		mbDelegate.saveBoardAndBoardTeamDetail(boardDetailVO,docRoleTeamDetailList,escortedTeamDetailList,userVO);
	}
	
	public static void removeBoard(String boardNo,UserVO userVO)
	{
		MbTransactionDelegate mbDelegate=new MbTransactionDelegate();
		mbDelegate.removeBoard(boardNo,userVO);
	}
	
	public static void updateAssignBoardAndBoardTeamDetail(BoardDetailVO boardDetailVO,List docRoleTeamDetailList,List escortedTeamDetailList,UserVO userVO)
	{
		MbTransactionDelegate mbDelegate=new MbTransactionDelegate();
		mbDelegate.updateAssignBoardAndBoardTeamDetail(boardDetailVO,docRoleTeamDetailList,escortedTeamDetailList,userVO);
	}
	
	
	
}
