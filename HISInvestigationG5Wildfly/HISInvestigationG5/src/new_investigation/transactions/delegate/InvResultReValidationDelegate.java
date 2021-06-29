package new_investigation.transactions.delegate;


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
import new_investigation.vo.Inv_EpisodeVO;
import new_investigation.vo.Inv_PatientAdmissionDtlVO;
import new_investigation.vo.InvestigationSearchVO;
import new_investigation.vo.LabTestVO;
import new_investigation.vo.InvResultEntryVO;
import new_investigation.vo.RequisitionListVO;
import new_investigation.vo.SampleAcceptanceVO;
import new_investigation.vo.template.ResultEntryVO;




public class InvResultReValidationDelegate extends Delegate
{
	public InvResultReValidationDelegate()
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
		return serviceBO.setPatientResultReValidationEssentials(InvResultEntryVO, _UserVO);
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
			 return serviceBO.saveResultReValidationDetails(InvResultEntryVO, invResultValidationForParameterDtlVO, _userVO, _request,fb);
		}
	
	//direct revalidate
		public void revalidate(List<ResultEntryVO> InvResultEntryVO, UserVO _UserVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			serviceBO.revalidateDirectly(InvResultEntryVO, _UserVO);
		}
		
		
		
		
	//public Map ResultValidationPatientDetails(InvResultEntryVO InvResultEntryVO, List<String> reqList,UserVO _UserVO)
	////{
	///////	InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
	//	return serviceBO.ResultValidationpatientDetails(InvResultEntryVO, reqList, _UserVO);
	//}
	
	//Save Logic 
	
/**	
	public List  savePatientDetails(Map<String,Map<String,Map<String,List<OnlinePatientAcceptanceVO>>>> mp,  UserVO _userVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		 return serviceBO.savePatientDetails(mp,  _userVO);
	}
	
	 
	
	
	
	
	
	public boolean checkDailyLabNoDuplicacy(OnlinePatientAcceptanceVO onlinePatientvo, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.checkDailyLabNoDuplicacy(onlinePatientvo,   _UserVO);
	}
	**/

		public String  checkcannedCodeName(InvResultValidationFB fb, UserVO _UserVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			return serviceBO.checkcannedCodeName1(fb,_UserVO);
		}	
		
}
