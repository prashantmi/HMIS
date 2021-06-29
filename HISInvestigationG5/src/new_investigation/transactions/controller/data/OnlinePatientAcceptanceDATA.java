package new_investigation.transactions.controller.data;

import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import new_investigation.transactions.delegate.OnlinePatientAcceptanceDelegate;
import new_investigation.vo.OnlinePatientAcceptanceVO;


public class OnlinePatientAcceptanceDATA
{
	 
	
	 
	
	//Requisition Save Logic
	public static List savePatientDetails(Map<String,Map<String,Map<String,List<OnlinePatientAcceptanceVO>>>> mp, UserVO _userVO)
	{
		OnlinePatientAcceptanceDelegate daoDelegate=new OnlinePatientAcceptanceDelegate();
		return daoDelegate.savePatientDetails(mp,  _userVO);
	}
	 
 
	
	public static Map LabCombos(OnlinePatientAcceptanceVO onlinePatientvo, UserVO _UserVO)
	{
		OnlinePatientAcceptanceDelegate masterDelegate = new OnlinePatientAcceptanceDelegate();
		return masterDelegate.LabCombos(onlinePatientvo, _UserVO);
	}
	
	public static Map setPatientEssentials(OnlinePatientAcceptanceVO onlinePatientvo, UserVO _UserVO)
	{
		OnlinePatientAcceptanceDelegate masterDelegate = new OnlinePatientAcceptanceDelegate();
		return masterDelegate.setPatientEssentials(onlinePatientvo, _UserVO);
	}
	
	
	public static Map setPatientEssentialsOnLoad(OnlinePatientAcceptanceVO onlinePatientvo, UserVO _UserVO)
	{
		OnlinePatientAcceptanceDelegate masterDelegate = new OnlinePatientAcceptanceDelegate();
		return masterDelegate.setPatientEssentialsOnLoad(onlinePatientvo, _UserVO);
	}
	
	
	
	public static Map patientDetails(List<OnlinePatientAcceptanceVO> onlinePatientvo,List<String> reqList,UserVO _UserVO)
	{
		OnlinePatientAcceptanceDelegate masterDelegate = new OnlinePatientAcceptanceDelegate();
		return masterDelegate.patientDetails(onlinePatientvo, reqList,_UserVO);
	}
	 
	public static List<String> getStaffDetails(String crNo, UserVO _UserVO)
	{
		OnlinePatientAcceptanceDelegate   daoDelegate=new OnlinePatientAcceptanceDelegate();
		return daoDelegate.getStaffDetails(crNo, _UserVO);
	}
	
	public static boolean checkDailyLabNoDuplicacy(OnlinePatientAcceptanceVO onlinePatientvo, UserVO _UserVO)
	{
		OnlinePatientAcceptanceDelegate masterDelegate = new OnlinePatientAcceptanceDelegate();
		return masterDelegate.checkDailyLabNoDuplicacy(onlinePatientvo,  _UserVO);
	}
	
	
	public static String checkAutoGenFormate(OnlinePatientAcceptanceVO onlinePatientvo, UserVO _UserVO)
	{
		OnlinePatientAcceptanceDelegate masterDelegate = new OnlinePatientAcceptanceDelegate();
		return masterDelegate.checkAutoGenFormate(onlinePatientvo, _UserVO);
	}


//added by krishnan nema on 08/10/2018
	public static Map MachineCombos(OnlinePatientAcceptanceVO onlinePatientvo,UserVO userVO) {
		OnlinePatientAcceptanceDelegate masterDelegate = new OnlinePatientAcceptanceDelegate();
		return masterDelegate.MachineCombos(onlinePatientvo, userVO);
	}
	
	public static Map getUserList(UserVO _UserVO )
	{
		OnlinePatientAcceptanceDelegate userList = new OnlinePatientAcceptanceDelegate();
		return userList.getUserList(_UserVO);
	}
}
