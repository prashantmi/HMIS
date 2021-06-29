package new_investigation.transactions.delegate;


import hisglobal.business.Delegate;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import new_investigation.transactions.bo.InvestigationEssentialBO;
import new_investigation.transactions.bo.InvestigationEssentialBOi;
import new_investigation.transactions.controller.fb.InvResultValidationFB;
import new_investigation.vo.template.ResultEntryVO;




public class InvResultValidationDelegate extends Delegate
{
	public InvResultValidationDelegate()
	{
		super(new InvestigationEssentialBO());
	}

	 
	 
	public Map  LabComboForResultValidation(ResultEntryVO InvResultEntryVO, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.LabComboForResultValidation(InvResultEntryVO, _UserVO);
	}
	
	public Map setPatientResultValidationEssentials(ResultEntryVO InvResultEntryVO, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.setPatientResultValidationEssentials(InvResultEntryVO, _UserVO);
	}
	

	
	public Map getResultEntryData(ResultEntryVO InvResultEntryVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.getResultEntryData(InvResultEntryVO);
	}
	
	
	
	//Save Logic
	 
		public Map  saveResultValidationDetails(List<ResultEntryVO> InvResultEntryVO,List<ResultEntryVO>  invResultValidationForParameterDtlVO,  UserVO _userVO,HttpServletRequest _request,InvResultValidationFB fb)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			 return serviceBO.saveResultValidationDetails(InvResultEntryVO, invResultValidationForParameterDtlVO, _userVO, _request,fb);
		}
	
	//direct revalidate
		public void revalidate(List<ResultEntryVO> InvResultEntryVO, UserVO _UserVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			serviceBO.revalidate(InvResultEntryVO, _UserVO);
		}
		
		
		public String  checkcannedCodeName(InvResultValidationFB fb, UserVO _UserVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			return serviceBO.checkcannedCodeName(fb,_UserVO);
		}	

	
}
