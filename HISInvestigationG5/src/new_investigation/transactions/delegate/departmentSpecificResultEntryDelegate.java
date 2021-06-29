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
import hisglobal.vo.BloodBagDtlVO;
import hisglobal.vo.BloodBankExaminationMstVO;
import hisglobal.vo.BloodDonationVO;
import hisglobal.vo.BloodRequisitionPatientDtlVO;
import hisglobal.vo.DonorAddDtlVO;
import hisglobal.vo.DonorQuestionnaireMasterVO;
import hisglobal.vo.DonorRefreshmentDetailVO;
import hisglobal.vo.DonorVisitDtlVO;
 
import new_investigation.vo.Inv_RequisitionRaisingPatientVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import new_investigation.masters.bo.InvestigationMasterBOi;
import new_investigation.transactions.bo.InvestigationEssentialBO;
import new_investigation.transactions.bo.InvestigationEssentialBOi;
import new_investigation.transactions.controller.fb.InvResultValidationFB;
import new_investigation.transactions.controller.fb.departmentSpecificResultEntryFB;
import new_investigation.vo.Inv_EpisodeVO;
import new_investigation.vo.Inv_PatientAdmissionDtlVO;
import new_investigation.vo.InvestigationSearchVO;
import new_investigation.vo.LabTestVO;
import new_investigation.vo.InvResultEntryVO;
import new_investigation.vo.RequisitionListVO;
import new_investigation.vo.SampleAcceptanceVO;
import new_investigation.vo.template.ResultEntryVO;




public class departmentSpecificResultEntryDelegate extends Delegate
{
	public departmentSpecificResultEntryDelegate()
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
		return serviceBO.setPatientDeptResultEntryEssentials(invResultEntryvo, _UserVO);
	}
	
	
	//Save Logic
	 
		public Map  saveResultEntryDetails(List<ResultEntryVO> invResultEntryvo,List<ResultEntryVO>  invResultEntryForParameterDtlVO,  UserVO _userVO,HttpServletRequest _request)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			 return serviceBO.saveDeptResultEntryDetails(invResultEntryvo, invResultEntryForParameterDtlVO, _userVO, _request);
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
		
		public Map  modifyResultEntryDetails(List<ResultEntryVO> invResultEntryvo,List<ResultEntryVO>  invResultEntryForParameterDtlVO,  UserVO _userVO,HttpServletRequest _request)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			 return serviceBO.modifyDeptResultEntryDetails(invResultEntryvo, invResultEntryForParameterDtlVO, _userVO, _request);
		}
		
		public Map  autoCannedDetails(String labCode,String cannedMacroCheck, UserVO _UserVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			return serviceBO.autoCannedDetails(labCode, cannedMacroCheck,_UserVO);
		}
		public String  checkcannedCodeName(InvResultValidationFB fb, UserVO _UserVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			return serviceBO.checkcannedCodeName(fb,_UserVO);
		}
	
		
		public Map getDeptResultEntryData(ResultEntryVO InvResultEntryVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			return serviceBO.getDeptResultEntryData(InvResultEntryVO);
		}
		
}
