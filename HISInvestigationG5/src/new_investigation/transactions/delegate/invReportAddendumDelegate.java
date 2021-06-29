package new_investigation.transactions.delegate;


import hisglobal.business.Delegate;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import new_investigation.transactions.bo.InvestigationEssentialBO;
import new_investigation.transactions.bo.InvestigationEssentialBOi;
import new_investigation.vo.invReportAddendumVO;
import new_investigation.vo.template.ResultEntryVO;




public class invReportAddendumDelegate extends Delegate
{
	public invReportAddendumDelegate()
	{
		super(new InvestigationEssentialBO());
	}

	 
	 
	public Map  LabComboForResultValidation(ResultEntryVO InvResultEntryVO, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.LabComboForResultValidationAddendum(InvResultEntryVO, _UserVO);
	}
	
	public Map setPatientReportAddendumEssentials(ResultEntryVO InvResultEntryVO, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.setPatientReportAddendumEssentials(InvResultEntryVO, _UserVO);
	}
	

	
	public Map getResultEntryData(ResultEntryVO InvResultEntryVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.getResultEntryData(InvResultEntryVO);
	}
	
	//Save Logic
	 
	public  Map saveReportAddendumDetails(ResultEntryVO newPatVO,List<ResultEntryVO> oldPatList,List<ResultEntryVO> invResultentryVO,List<ResultEntryVO>  invResultValidationForParameterDtlVO,UserVO _userVO,HttpServletRequest _request,String amendType)		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			 return serviceBO.saveReportAddendumDetails(newPatVO,oldPatList,invResultentryVO, invResultValidationForParameterDtlVO, _userVO, _request,amendType);
		}
	
	//direct revalidate
		public void revalidate(List<ResultEntryVO> InvResultEntryVO, UserVO _UserVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			serviceBO.revalidate(InvResultEntryVO, _UserVO);
		}
		
		
		
		public ResultEntryVO getNewEntriesPatient(ResultEntryVO InvResultEntryVO,UserVO _uservo)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			return serviceBO.getNewEntriesPatient(InvResultEntryVO,_uservo);
		}
		
		
		
		public ResultEntryVO getOldEntriesPatient(ResultEntryVO InvResultEntryVO,UserVO _uservo)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			return serviceBO.getOldEntriesPatient(InvResultEntryVO,_uservo);
		}
		
		public Map reasonList( UserVO _UserVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			return serviceBO.reasonList(_UserVO);
		}
		
		public  Map saveAddendumDetails(invReportAddendumVO newPatVO,UserVO _userVO)		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			 return serviceBO.saveAddendumDetails(newPatVO, _userVO);
		}
		
	
}
