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

import com.google.gson.JsonObject;

import new_investigation.masters.bo.InvestigationMasterBOi;
import new_investigation.transactions.bo.InvPatientAcceptanceRespBOi;
import new_investigation.transactions.bo.InvPatientAcceptanceRespBOi;
import new_investigation.transactions.bo.InvPatientAcceptanceRespBO;
import new_investigation.transactions.bo.InvestigationEssentialBO;
import new_investigation.transactions.bo.InvestigationEssentialBOi;
import new_investigation.vo.Inv_EpisodeVO;
import new_investigation.vo.Inv_PatientAdmissionDtlVO;
import new_investigation.vo.InvestigationSearchVO;
import new_investigation.vo.LabTestVO;
import new_investigation.vo.InvPatientAcceptanceRespVO;
import new_investigation.vo.InvPatientAcceptanceRespVO;
import new_investigation.vo.RequisitionListVO;

public class InvPatientAcceptanceRespDelegate extends Delegate {
	public InvPatientAcceptanceRespDelegate() {
		super(new InvPatientAcceptanceRespBO());
	}
	
	public Map AjaxGetPatAcceptanceReqnList(InvPatientAcceptanceRespVO invPatientAcceptanceRespVO , UserVO _UserVO)
	{
		InvPatientAcceptanceRespBOi serviceBO = (InvPatientAcceptanceRespBOi) super.getServiceProvider();
		return serviceBO.AjaxGetPatAcceptanceReqnList(invPatientAcceptanceRespVO, _UserVO);
	} 
	
	public Map getUserList(UserVO _UserVO) {
		InvPatientAcceptanceRespBOi userList = (InvPatientAcceptanceRespBOi) new InvPatientAcceptanceRespBO();
		return userList.getUserList(_UserVO);
	}

	// Save Logic
	public JsonObject savePatientDetails(Map<String, Map<String, Map<String, List<InvPatientAcceptanceRespVO>>>> mp,
			UserVO _userVO) {
		InvPatientAcceptanceRespBOi serviceBO = (InvPatientAcceptanceRespBOi) super.getServiceProvider();
		return serviceBO.savePatientDetails(mp, _userVO);
	}

	public Map LabCombos(InvPatientAcceptanceRespVO onlinePatientvo, UserVO _UserVO) {
		InvPatientAcceptanceRespBOi serviceBO = (InvPatientAcceptanceRespBOi) super.getServiceProvider();
		return serviceBO.LabCombos(onlinePatientvo, _UserVO);
	}

	public Map setPatientEssentialsOnLoad(InvPatientAcceptanceRespVO onlinePatientvo, UserVO _UserVO) {
		InvPatientAcceptanceRespBOi serviceBO = (InvPatientAcceptanceRespBOi) super.getServiceProvider();
		return serviceBO.setPatientEssentialsOnLoad(onlinePatientvo, _UserVO);
	}

	public Map setPatientEssentials(InvPatientAcceptanceRespVO onlinePatientvo, UserVO _UserVO) {
		InvPatientAcceptanceRespBOi serviceBO = (InvPatientAcceptanceRespBOi) super.getServiceProvider();
		return serviceBO.setPatientEssentials(onlinePatientvo, _UserVO);
	}

	public Map patientDetails(List<InvPatientAcceptanceRespVO> onlinePatientvo, List<String> reqList, UserVO _UserVO) {
		InvPatientAcceptanceRespBOi serviceBO = (InvPatientAcceptanceRespBOi) super.getServiceProvider();
		return serviceBO.patientDetails(onlinePatientvo, reqList, _UserVO);
	}

	public List<String> getStaffDetails(String crNo, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)new InvestigationEssentialBO();
		return(serviceBO.getStaffDetails(crNo, _UserVO));
	}

	public boolean checkDailyLabNoDuplicacy(InvPatientAcceptanceRespVO onlinePatientvo, UserVO _UserVO) {
		InvPatientAcceptanceRespBOi serviceBO = (InvPatientAcceptanceRespBOi) super.getServiceProvider();
		return serviceBO.checkDailyLabNoDuplicacy(onlinePatientvo, _UserVO);
	}

	public String checkAutoGenFormate(InvPatientAcceptanceRespVO onlinePatientvo, UserVO _UserVO) {
		InvPatientAcceptanceRespBOi serviceBO = (InvPatientAcceptanceRespBOi) super.getServiceProvider();
		return serviceBO.checkAutoGenFormate(onlinePatientvo, _UserVO);
	}

	// added by krishnan nema on 08/10/2018
	public Map MachineCombos(InvPatientAcceptanceRespVO onlinePatientvo, UserVO userVO) {
		InvPatientAcceptanceRespBOi serviceBO = (InvPatientAcceptanceRespBOi) super.getServiceProvider();
		return serviceBO.MachineCombos(onlinePatientvo, userVO);
	}



}
