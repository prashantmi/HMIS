package new_investigation.transactions.delegate;


import hisglobal.business.Delegate;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import new_investigation.transactions.bo.InvResultReValidationRespBO;
import new_investigation.transactions.bo.InvResultReValidationRespBOi;
import new_investigation.transactions.bo.InvestigationEssentialBOi;
import new_investigation.transactions.controller.fb.InvResultValidationRespFB;
import new_investigation.vo.Inv_SampleCollectionVO;
import new_investigation.vo.template.ResultEntryVO;

import new_investigation.vo.InvResultValidationRespVO;


public class InvResultReValidationRespDelegate extends Delegate
{
	public InvResultReValidationRespDelegate()
	{
		super(new InvResultReValidationRespBO());
	}

	 
	
	public Map AjaxGetReValidationReqnList(InvResultValidationRespVO invResultValidationRespVO , UserVO _UserVO)
	{
		InvResultReValidationRespBOi serviceBO = (InvResultReValidationRespBOi) super.getServiceProvider();
		return serviceBO.AjaxGetReValidationReqnList(invResultValidationRespVO, _UserVO);
	} 
	
	
	//direct revalidate
	public void AjaxReValidateReqnResult(List<InvResultValidationRespVO> listInvResultValidationRespVO, UserVO _UserVO)
	{
		InvResultReValidationRespBOi serviceBO = (InvResultReValidationRespBOi) super.getServiceProvider();
		serviceBO.AjaxReValidateReqnResult(listInvResultValidationRespVO, _UserVO);
	}
	
	
	
	public Map setPatientResultValidationEssentials(InvResultValidationRespVO invResultValidationRespVO , UserVO _UserVO)
	{
		InvResultReValidationRespBOi serviceBO = (InvResultReValidationRespBOi) super.getServiceProvider();
		return serviceBO.setPatientResultValidationEssentials(invResultValidationRespVO, _UserVO);
	} 
	

	
	public Map getResultEntryData(ResultEntryVO InvResultEntryVO)
	{
		InvResultReValidationRespBOi serviceBO = (InvResultReValidationRespBOi) super.getServiceProvider();
		return serviceBO.getResultEntryData(InvResultEntryVO);
	}
	
	
	
	//Save Logic
	 
		public Map  saveResultValidationDetails(List<ResultEntryVO> InvResultEntryVO,List<ResultEntryVO>  invResultValidationForParameterDtlVO,  UserVO _userVO,HttpServletRequest _request,InvResultValidationRespFB fb)
		{
			InvResultReValidationRespBOi serviceBO = (InvResultReValidationRespBOi) super.getServiceProvider();
			 return serviceBO.saveResultValidationDetails(InvResultEntryVO, invResultValidationForParameterDtlVO, _userVO, _request,fb);
		}
	
	
		
		
		public String  checkcannedCodeName(InvResultValidationRespFB fb, UserVO _UserVO)
		{
			InvResultReValidationRespBOi serviceBO = (InvResultReValidationRespBOi) super.getServiceProvider();
			return serviceBO.checkcannedCodeName(fb,_UserVO);
		}	

	
}
