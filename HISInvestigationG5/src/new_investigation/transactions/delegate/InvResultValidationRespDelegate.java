package new_investigation.transactions.delegate;


import hisglobal.business.Delegate;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import new_investigation.transactions.bo.InvResultValidationRespBO;
import new_investigation.transactions.bo.InvResultValidationRespBOi;
import new_investigation.transactions.bo.InvestigationEssentialBOi;
import new_investigation.transactions.controller.fb.InvResultValidationRespFB;
import new_investigation.vo.Inv_SampleCollectionVO;
import new_investigation.vo.template.ResultEntryVO;

import new_investigation.vo.InvResultValidationRespVO;


public class InvResultValidationRespDelegate extends Delegate
{
	public InvResultValidationRespDelegate()
	{
		super(new InvResultValidationRespBO());
	}

	 
	public List<Inv_SampleCollectionVO> AajaxGetCollAreaList(UserVO _userVO)
	 
	{
		InvResultValidationRespBOi serviceBO = (InvResultValidationRespBOi) super.getServiceProvider();
		 return serviceBO.AajaxGetCollAreaList(_userVO);
	}
	
	public Map  AjaxGetLabList(InvResultValidationRespVO invResultValidationRespVO, UserVO _UserVO)
	{
		InvResultValidationRespBOi serviceBO = (InvResultValidationRespBOi) super.getServiceProvider();
		return serviceBO.AjaxGetLabList(invResultValidationRespVO, _UserVO);
	}
	
	public Map AjaxGetValidationReqnList(InvResultValidationRespVO invResultValidationRespVO , UserVO _UserVO)
	{
		InvResultValidationRespBOi serviceBO = (InvResultValidationRespBOi) super.getServiceProvider();
		return serviceBO.AjaxGetValidationReqnList(invResultValidationRespVO, _UserVO);
	} 
	
	
	
	
	
	
	public Map setPatientResultValidationEssentials(InvResultValidationRespVO invResultValidationRespVO , UserVO _UserVO)
	{
		InvResultValidationRespBOi serviceBO = (InvResultValidationRespBOi) super.getServiceProvider();
		return serviceBO.setPatientResultValidationEssentials(invResultValidationRespVO, _UserVO);
	} 
	

	
	public Map getResultEntryData(ResultEntryVO InvResultEntryVO)
	{
		InvResultValidationRespBOi serviceBO = (InvResultValidationRespBOi) super.getServiceProvider();
		return serviceBO.getResultEntryData(InvResultEntryVO);
	}
	
	
	
	//Save Logic
	 
		public Map  saveResultValidationDetails(List<ResultEntryVO> InvResultEntryVO,List<ResultEntryVO>  invResultValidationForParameterDtlVO,  UserVO _userVO,HttpServletRequest _request,InvResultValidationRespFB fb)
		{
			InvResultValidationRespBOi serviceBO = (InvResultValidationRespBOi) super.getServiceProvider();
			 return serviceBO.saveResultValidationDetails(InvResultEntryVO, invResultValidationForParameterDtlVO, _userVO, _request,fb);
		}
	
	//direct revalidate
		public void AjaxValidateReqnResult(List<InvResultValidationRespVO> listInvResultValidationRespVO, UserVO _UserVO)
		{
			InvResultValidationRespBOi serviceBO = (InvResultValidationRespBOi) super.getServiceProvider();
			serviceBO.AjaxValidateReqnResult(listInvResultValidationRespVO, _UserVO);
		}
		
		
		public String  checkcannedCodeName(InvResultValidationRespFB fb, UserVO _UserVO)
		{
			InvResultValidationRespBOi serviceBO = (InvResultValidationRespBOi) super.getServiceProvider();
			return serviceBO.checkcannedCodeName(fb,_UserVO);
		}	

	
}
