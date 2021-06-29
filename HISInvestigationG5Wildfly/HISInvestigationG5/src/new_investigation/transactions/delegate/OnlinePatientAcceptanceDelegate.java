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

import new_investigation.masters.bo.InvestigationMasterBOi;
import new_investigation.transactions.bo.InvestigationEssentialBO;
import new_investigation.transactions.bo.InvestigationEssentialBOi;
import new_investigation.vo.Inv_EpisodeVO;
import new_investigation.vo.Inv_PatientAdmissionDtlVO;
import new_investigation.vo.InvestigationSearchVO;
import new_investigation.vo.LabTestVO;
import new_investigation.vo.OnlinePatientAcceptanceVO;
import new_investigation.vo.RequisitionListVO;




public class OnlinePatientAcceptanceDelegate extends Delegate
{
	public OnlinePatientAcceptanceDelegate()
	{
		super(new InvestigationEssentialBO());
	}

	 
	 
	
	//Save Logic
	
	public List  savePatientDetails(Map<String,Map<String,Map<String,List<OnlinePatientAcceptanceVO>>>> mp,  UserVO _userVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		 return serviceBO.savePatientDetails(mp,  _userVO);
	}
	
	 
	public Map  LabCombos(OnlinePatientAcceptanceVO onlinePatientvo, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.LabCombos(onlinePatientvo, _UserVO);
	}
	
	public Map setPatientEssentialsOnLoad(OnlinePatientAcceptanceVO onlinePatientvo, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.setPatientEssentialsOnLoad(onlinePatientvo, _UserVO);
	}
	
	public Map setPatientEssentials(OnlinePatientAcceptanceVO onlinePatientvo, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.setPatientEssentials(onlinePatientvo, _UserVO);
	}
	
	public Map patientDetails(List<OnlinePatientAcceptanceVO> onlinePatientvo, List<String> reqList,UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.patientDetails(onlinePatientvo, reqList, _UserVO);
	}
	
	public List<String> getStaffDetails(String crNo, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
		return(serviceBO.getStaffDetails(crNo, _UserVO));
	}
	
	public boolean checkDailyLabNoDuplicacy(OnlinePatientAcceptanceVO onlinePatientvo, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.checkDailyLabNoDuplicacy(onlinePatientvo,   _UserVO);
	}
	
	public String checkAutoGenFormate(OnlinePatientAcceptanceVO onlinePatientvo, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.checkAutoGenFormate(onlinePatientvo,   _UserVO);
	}



//added  by krishnan nema on 08/10/2018
	public Map MachineCombos(OnlinePatientAcceptanceVO onlinePatientvo,UserVO userVO) {
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.MachineCombos(onlinePatientvo, userVO);
	}
	
	public Map getUserList( UserVO _UserVO )
	{
		InvestigationEssentialBOi userList = (InvestigationEssentialBOi) super.getServiceProvider();
		return userList.getUserList( _UserVO);
	}
}
