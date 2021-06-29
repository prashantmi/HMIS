package new_investigation.transactions.controller.data;

import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import new_investigation.transactions.delegate.filmUsedDelegate;
import new_investigation.vo.filmUsedVO;


public class filmUsedDATA
{
	 
	
	 
	
	//Requisition Save Logic
	public static List savePatientDetails(List<filmUsedVO> lstFilm,List<filmUsedVO> lstFilmAdd,List<filmUsedVO> lstFilmWaste,filmUsedVO filmvo, UserVO _userVO,Map mp)
	{
		filmUsedDelegate daoDelegate=new filmUsedDelegate();
		return daoDelegate.savePatientDetails(lstFilm,lstFilmAdd,lstFilmWaste,filmvo,_userVO,mp);
	}
	 
 
	
	public static Map LabCombos(filmUsedVO onlinePatientvo, UserVO _UserVO)
	{
		filmUsedDelegate masterDelegate = new filmUsedDelegate();
		return masterDelegate.LabCombos(onlinePatientvo, _UserVO);
	}
	
	public static Map setPatientEssentials(filmUsedVO onlinePatientvo, UserVO _UserVO)
	{
		filmUsedDelegate masterDelegate = new filmUsedDelegate();
		return masterDelegate.setPatientEssentials(onlinePatientvo, _UserVO);
	}
	
	
	public static Map setPatientEssentialsOnLoad(filmUsedVO onlinePatientvo, UserVO _UserVO)
	{
		filmUsedDelegate masterDelegate = new filmUsedDelegate();
		return masterDelegate.setPatientEssentialsOnLoad(onlinePatientvo, _UserVO);
	}
	
	
	
	public static Map patientDetails(filmUsedVO onlinePatientvo,List<String> reqList,UserVO _UserVO)
	{
		filmUsedDelegate masterDelegate = new filmUsedDelegate();
		return masterDelegate.patientDetails(onlinePatientvo, reqList,_UserVO);
	}
	 
	

}
