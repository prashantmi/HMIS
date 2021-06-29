package new_investigation.transactions.controller.data;

import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import new_investigation.transactions.controller.fb.InvResultValidationRespFB;
import new_investigation.transactions.delegate.InvResultReValidationRespDelegate;
import new_investigation.transactions.delegate.InvestigationEssentialDelegate;
import new_investigation.vo.Inv_SampleCollectionVO;
import new_investigation.vo.template.ResultEntryVO;
import new_investigation.vo.InvResultValidationRespVO;

public class InvResultReValidationRespDATA
{


	public static Map AjaxGetReValidationReqnList(InvResultValidationRespVO invResultValidationRespVO, UserVO _UserVO)
	{
		InvResultReValidationRespDelegate masterDelegate = new InvResultReValidationRespDelegate();
		return masterDelegate.AjaxGetReValidationReqnList(invResultValidationRespVO, _UserVO);
	}
	
	//update on re validating directly
		public static void AjaxReValidateReqnResult(List<InvResultValidationRespVO> listInvResultValidationRespVO, UserVO _UserVO)
		{
			InvResultReValidationRespDelegate masterDelegate = new InvResultReValidationRespDelegate();
			masterDelegate.AjaxReValidateReqnResult(listInvResultValidationRespVO, _UserVO);
		}
		
	
	
	
	
	
	public static Map setPatientResultValidationEssentials(InvResultValidationRespVO invResultValidationRespVO, UserVO _UserVO)
	{
		InvResultReValidationRespDelegate masterDelegate = new InvResultReValidationRespDelegate();
		return masterDelegate.setPatientResultValidationEssentials(invResultValidationRespVO, _UserVO);
	} 


	public static Map getResultEntryData(ResultEntryVO InvResultEntryVO)
	{
		InvResultReValidationRespDelegate masterDelegate = new InvResultReValidationRespDelegate();
		return masterDelegate.getResultEntryData(InvResultEntryVO);
	}

	


	//ResultValidation Save Logic
	public static Map saveResultValidationDetails(List<ResultEntryVO> voResultValidation,List<ResultEntryVO>  invResultValidationForParameterDtlVO, UserVO _userVO,HttpServletRequest _request,InvResultValidationRespFB fb)
	{
		InvResultReValidationRespDelegate masterDelegate = new InvResultReValidationRespDelegate();
		return masterDelegate.saveResultValidationDetails(voResultValidation,invResultValidationForParameterDtlVO,  _userVO,  _request,fb);
	}

	
	public static String checkcannedCodeName(InvResultValidationRespFB fb, UserVO _UserVO)
	{
		InvResultReValidationRespDelegate masterDelegate = new InvResultReValidationRespDelegate();
		return masterDelegate.checkcannedCodeName(fb ,_UserVO);
	}



} 
