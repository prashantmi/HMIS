package new_investigation.transactions.controller.data;

import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import com.google.gson.JsonObject;

import new_investigation.transactions.delegate.InvPatientAcceptanceRespDelegate;
import new_investigation.transactions.delegate.InvPatientAcceptanceRespDelegate;
import new_investigation.vo.InvPatientAcceptanceRespVO;
import new_investigation.vo.InvPatientAcceptanceRespVO;


public class InvPatientAcceptanceRespDATA
{
	 
	
	public static Map AjaxGetPatAcceptanceReqnList(InvPatientAcceptanceRespVO invPatientAcceptanceRespVO, UserVO _UserVO)
	{
		InvPatientAcceptanceRespDelegate masterDelegate = new InvPatientAcceptanceRespDelegate();
		return masterDelegate.AjaxGetPatAcceptanceReqnList(invPatientAcceptanceRespVO, _UserVO);
	}
	
	//Requisition Save Logic
	public static JsonObject savePatientDetails(Map<String,Map<String,Map<String,List<InvPatientAcceptanceRespVO>>>> mp, UserVO _userVO)
	{
		InvPatientAcceptanceRespDelegate daoDelegate=new InvPatientAcceptanceRespDelegate();
		return daoDelegate.savePatientDetails(mp,  _userVO);
	}
	 
 
	
	public static Map LabCombos(InvPatientAcceptanceRespVO onlinePatientvo, UserVO _UserVO)
	{
		InvPatientAcceptanceRespDelegate masterDelegate = new InvPatientAcceptanceRespDelegate();
		return masterDelegate.LabCombos(onlinePatientvo, _UserVO);
	}
	
	public static Map setPatientEssentials(InvPatientAcceptanceRespVO onlinePatientvo, UserVO _UserVO)
	{
		InvPatientAcceptanceRespDelegate masterDelegate = new InvPatientAcceptanceRespDelegate();
		return masterDelegate.setPatientEssentials(onlinePatientvo, _UserVO);
	}
	
	
	public static Map setPatientEssentialsOnLoad(InvPatientAcceptanceRespVO onlinePatientvo, UserVO _UserVO)
	{
		InvPatientAcceptanceRespDelegate masterDelegate = new InvPatientAcceptanceRespDelegate();
		return masterDelegate.setPatientEssentialsOnLoad(onlinePatientvo, _UserVO);
	}
	
	
	
	public static Map patientDetails(List<InvPatientAcceptanceRespVO> onlinePatientvo,List<String> reqList,UserVO _UserVO)
	{
		InvPatientAcceptanceRespDelegate masterDelegate = new InvPatientAcceptanceRespDelegate();
		return masterDelegate.patientDetails(onlinePatientvo, reqList,_UserVO);
	}
	 
	public static List<String> getStaffDetails(String crNo, UserVO _UserVO)
	{
		InvPatientAcceptanceRespDelegate   daoDelegate=new InvPatientAcceptanceRespDelegate();
		return daoDelegate.getStaffDetails(crNo, _UserVO);
	}
	
	public static boolean checkDailyLabNoDuplicacy(InvPatientAcceptanceRespVO onlinePatientvo, UserVO _UserVO)
	{
		InvPatientAcceptanceRespDelegate masterDelegate = new InvPatientAcceptanceRespDelegate();
		return masterDelegate.checkDailyLabNoDuplicacy(onlinePatientvo,  _UserVO);
	}
	
	
	public static String checkAutoGenFormate(InvPatientAcceptanceRespVO onlinePatientvo, UserVO _UserVO)
	{
		InvPatientAcceptanceRespDelegate masterDelegate = new InvPatientAcceptanceRespDelegate();
		return masterDelegate.checkAutoGenFormate(onlinePatientvo, _UserVO);
	}


//added by krishnan nema on 08/10/2018
	public static Map MachineCombos(InvPatientAcceptanceRespVO onlinePatientvo,UserVO userVO) {
		InvPatientAcceptanceRespDelegate masterDelegate = new InvPatientAcceptanceRespDelegate();
		return masterDelegate.MachineCombos(onlinePatientvo, userVO);
	}
	
	public static Map getUserList(UserVO _UserVO )
	{
		InvPatientAcceptanceRespDelegate userList = new InvPatientAcceptanceRespDelegate();
		return userList.getUserList(_UserVO);
	}
}
