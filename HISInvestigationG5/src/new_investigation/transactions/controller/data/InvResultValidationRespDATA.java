package new_investigation.transactions.controller.data;

import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import new_investigation.transactions.controller.fb.InvResultValidationRespFB;
import new_investigation.transactions.delegate.InvResultValidationRespDelegate;
import new_investigation.transactions.delegate.InvestigationEssentialDelegate;
import new_investigation.vo.Inv_SampleCollectionVO;
import new_investigation.vo.template.ResultEntryVO;
import new_investigation.vo.InvResultValidationRespVO;

public class InvResultValidationRespDATA
{

	public static List<Inv_SampleCollectionVO> AajaxGetCollAreaList(UserVO userVO)
	{
		InvResultValidationRespDelegate   daoDelegate=new InvResultValidationRespDelegate();
		return daoDelegate.AajaxGetCollAreaList(userVO);
	}
	
	public static Map AjaxGetLabList(InvResultValidationRespVO invResultValidationRespVO, UserVO _UserVO)
	{
		InvResultValidationRespDelegate masterDelegate = new InvResultValidationRespDelegate();
		return masterDelegate.AjaxGetLabList(invResultValidationRespVO, _UserVO);
	}
	
	public static Map AjaxGetValidationReqnList(InvResultValidationRespVO invResultValidationRespVO, UserVO _UserVO)
	{
		InvResultValidationRespDelegate masterDelegate = new InvResultValidationRespDelegate();
		return masterDelegate.AjaxGetValidationReqnList(invResultValidationRespVO, _UserVO);
	}
	
	
	
	
	
	
	
	public static Map setPatientResultValidationEssentials(InvResultValidationRespVO invResultValidationRespVO, UserVO _UserVO)
	{
		InvResultValidationRespDelegate masterDelegate = new InvResultValidationRespDelegate();
		return masterDelegate.setPatientResultValidationEssentials(invResultValidationRespVO, _UserVO);
	} 


	public static Map getResultEntryData(ResultEntryVO InvResultEntryVO)
	{
		InvResultValidationRespDelegate masterDelegate = new InvResultValidationRespDelegate();
		return masterDelegate.getResultEntryData(InvResultEntryVO);
	}

	


	//ResultValidation Save Logic
	public static Map saveResultValidationDetails(List<ResultEntryVO> voResultValidation,List<ResultEntryVO>  invResultValidationForParameterDtlVO, UserVO _userVO,HttpServletRequest _request,InvResultValidationRespFB fb)
	{
		InvResultValidationRespDelegate masterDelegate = new InvResultValidationRespDelegate();
		return masterDelegate.saveResultValidationDetails(voResultValidation,invResultValidationForParameterDtlVO,  _userVO,  _request,fb);
	}

	//update on re validating directly
	public static void AjaxValidateReqnResult(List<InvResultValidationRespVO> listInvResultValidationRespVO, UserVO _UserVO)
	{
		InvResultValidationRespDelegate masterDelegate = new InvResultValidationRespDelegate();
		masterDelegate.AjaxValidateReqnResult(listInvResultValidationRespVO, _UserVO);
	}
	
	public static String checkcannedCodeName(InvResultValidationRespFB fb, UserVO _UserVO)
	{
		InvResultValidationRespDelegate masterDelegate = new InvResultValidationRespDelegate();
		return masterDelegate.checkcannedCodeName(fb ,_UserVO);
	}



} 
