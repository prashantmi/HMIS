package new_investigation.transactions.controller.data;

import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import new_investigation.transactions.controller.fb.InvResultValidationFB;
import new_investigation.transactions.delegate.InvResultReValidationDelegate;
import new_investigation.vo.template.ResultEntryVO;


public class InvResultReValidationDATA
{
	 
	
	public static Map LabComboForResultValidation(ResultEntryVO InvResultEntryVO, UserVO _UserVO)
	{
		InvResultReValidationDelegate masterDelegate = new InvResultReValidationDelegate();
		return masterDelegate.LabComboForResultValidation(InvResultEntryVO, _UserVO);
	}
	 
	public static Map setPatientResultValidationEssentials(ResultEntryVO InvResultEntryVO, UserVO _UserVO)
	{
		InvResultReValidationDelegate masterDelegate = new InvResultReValidationDelegate();
		return masterDelegate.setPatientResultValidationEssentials(InvResultEntryVO, _UserVO);
	}
	
	
	public static Map getResultEntryData(ResultEntryVO InvResultEntryVO)
	{
		InvResultReValidationDelegate masterDelegate = new InvResultReValidationDelegate();
		return masterDelegate.getResultEntryData(InvResultEntryVO);
	}
	
	
	
	
	//ResultValidation Save Logic
		public static Map saveResultValidationDetails(List<ResultEntryVO> voResultValidation,List<ResultEntryVO>  invResultValidationForParameterDtlVO, UserVO _userVO,HttpServletRequest _request,InvResultValidationFB fb)
		{
			InvResultReValidationDelegate masterDelegate = new InvResultReValidationDelegate();
			return masterDelegate.saveResultValidationDetails(voResultValidation,invResultValidationForParameterDtlVO,  _userVO,  _request,fb);
		}
		
	//update on re validating directly
		public static void revalidate(List<ResultEntryVO> InvResultEntryVO, UserVO _UserVO)
		{
			InvResultReValidationDelegate masterDelegate = new InvResultReValidationDelegate();
			masterDelegate.revalidate(InvResultEntryVO, _UserVO);
		}
	
	///public static Map ResultValidationPatientDetails(InvResultEntryVO InvResultEntryVO,List<String> reqList,UserVO _UserVO)
	//{
	//	InvResultReValidationDelegate masterDelegate = new InvResultReValidationDelegate();
	//	return masterDelegate.ResultValidationPatientDetails(InvResultEntryVO, reqList,_UserVO);
	//}
	
	
	//Requisition Save Logic
	/** public static List savePatientDetails(Map<String,Map<String,Map<String,List<OnlinePatientAcceptanceVO>>>> mp, UserVO _userVO)
	{
		OnlinePatientAcceptanceDelegate daoDelegate=new OnlinePatientAcceptanceDelegate();
		return daoDelegate.savePatientDetails(mp,  _userVO);
	}
	 
 
	
	
	
	
	
	
	 
	
	public static boolean checkDailyLabNoDuplicacy(OnlinePatientAcceptanceVO onlinePatientvo, UserVO _UserVO)
	{
		OnlinePatientAcceptanceDelegate masterDelegate = new OnlinePatientAcceptanceDelegate();
		return masterDelegate.checkDailyLabNoDuplicacy(onlinePatientvo,  _UserVO);
	} **/
		
		public static String checkcannedCodeName(InvResultValidationFB fb, UserVO _UserVO)
		{
			InvResultReValidationDelegate masterDelegate = new InvResultReValidationDelegate();
			return masterDelegate.checkcannedCodeName(fb ,_UserVO);
		}

} 
