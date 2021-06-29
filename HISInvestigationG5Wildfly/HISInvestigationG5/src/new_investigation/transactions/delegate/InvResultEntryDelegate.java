package new_investigation.transactions.delegate;

/**
 * @author C-DAC, Noida Project : HISInvestigationG5 Module 
 * @Date 20 Aug, 2008 
 * Process: Donor Registration
 * Modified By: Pawan Kumar B N
 * Modified On: 18-11-2011
 * 
 */
import hisglobal.business.Delegate;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import new_investigation.transactions.bo.InvestigationEssentialBO;
import new_investigation.transactions.bo.InvestigationEssentialBOi;

import new_investigation.transactions.controller.fb.InvResultEntryFB;
import new_investigation.vo.Inv_EpisodeVO;
import new_investigation.vo.Inv_PatientAdmissionDtlVO;
import new_investigation.vo.InvestigationSearchVO;
import new_investigation.vo.LabTestVO;
import new_investigation.vo.InvResultEntryVO;
import new_investigation.vo.RequisitionListVO;
import new_investigation.vo.SampleAcceptanceVO;

import new_investigation.vo.template.ResultEntryVO;
import new_investigation.vo.template.TestWiseConsumableVO;




public class InvResultEntryDelegate extends Delegate
{
	public InvResultEntryDelegate()
	{
		super(new InvestigationEssentialBO());
	}

	 
	 
	public Map  LabComboForResultEntry(ResultEntryVO invResultEntryvo, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.LabComboForResultEntry(invResultEntryvo, _UserVO);
	}
	
	public Map setPatientResultEntryEssentials(ResultEntryVO invResultEntryvo, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.setPatientResultEntryEssentials(invResultEntryvo, _UserVO);
	}
	
	
	//Save Logic
	 
		public Map  saveResultEntryDetails(List<ResultEntryVO> invResultEntryvo,List<ResultEntryVO>  invResultEntryForParameterDtlVO,  UserVO _userVO,HttpServletRequest _request,InvResultEntryFB fb)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			 return serviceBO.saveResultEntryDetails(invResultEntryvo, invResultEntryForParameterDtlVO, _userVO, _request,fb);
		}
	
		
	//get the loinc code
		
		public String getLoincCode(String passValue)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			 return serviceBO.getLoincCode(passValue);
		}
		
		public Map  showCannedDetails(String labCode,String cannedMacroCheck, UserVO _UserVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			return serviceBO.showCannedDetails(labCode, cannedMacroCheck,_UserVO);
		}
		
		public Map  modifyResultEntryDetails(List<ResultEntryVO> invResultEntryvo,List<ResultEntryVO>  invResultEntryForParameterDtlVO,  UserVO _userVO,HttpServletRequest _request,InvResultEntryFB _fb)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			 return serviceBO.modifyResultEntryDetails(invResultEntryvo, invResultEntryForParameterDtlVO, _userVO, _request,_fb);
		}
		
		public Map  autoCannedDetails(String labCode,String cannedMacroCheck, UserVO _UserVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			return serviceBO.autoCannedDetails(labCode, cannedMacroCheck,_UserVO);
		}
//Test Wise Consumable Process Added By Jatin 
		public Map  LabComboForTestWiseConsumable(TestWiseConsumableVO invResultEntryvo, UserVO _UserVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			return serviceBO.LabComboForTestWiseConsumable(invResultEntryvo, _UserVO);
		}
		public Map setPatientTestWiseConsumableEssentials(TestWiseConsumableVO invResultEntryvo, UserVO _UserVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			return serviceBO.setPatientTestWiseConsumableEssentials(invResultEntryvo, _UserVO);
		}
		public Map getTestWiseConsumableList(TestWiseConsumableVO invResultEntryvo, UserVO _UserVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			return serviceBO.getTestWiseConsumableList(invResultEntryvo, _UserVO);
		}
		public Map saveConsumableList(TestWiseConsumableVO invResultEntryvo, UserVO _UserVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			return serviceBO.saveConsumableList(invResultEntryvo, _UserVO);
		}
		public Map getPatientDetailsTestWiseConsumable(TestWiseConsumableVO invResultEntryvo, UserVO _UserVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			return serviceBO.getPatientDetailsTestWiseConsumable(invResultEntryvo, _UserVO);
		}
		public Map updateTestWiseConsumableList(TestWiseConsumableVO invResultEntryvo, UserVO _UserVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			return serviceBO.updateTestWiseConsumableList(invResultEntryvo, _UserVO);
		}
		
//==========================================================================
		public String  checkcannedCodeName(InvResultEntryFB fb, UserVO _UserVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			return serviceBO.checkcannedCodeName(fb,_UserVO);
		}
	
		
		public String  checkBilling(InvResultEntryFB fb, UserVO _UserVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			return serviceBO.checkBilling(fb,_UserVO);
		}
		
		public  Map saveAddendumDetails(InvResultEntryVO newPatVO,UserVO _userVO)		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			 return serviceBO.saveAddendumDetails(newPatVO, _userVO);
		}

		
		public String  CHECKCISPARAMETERDEPENDENT(String fb, UserVO _UserVO,InvResultEntryFB fbb)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			return serviceBO.CHECKCISPARAMETERDEPENDENTRF(fb,_UserVO,fbb);
		}	

}
