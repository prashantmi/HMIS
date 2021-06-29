package new_investigation.transactions.delegate;

/**
 * @author C-DAC, Noida Project : HISInvestigationG5 Module 
 * @Date 20 Oct, 2015
 * Process: Machine Result Entry
 * Modified By: Puneet Singh
 * Modified On: 
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
import new_investigation.transactions.bo.InvestigationEssentialMachineBO;
import new_investigation.vo.Inv_EpisodeVO;
import new_investigation.vo.Inv_PatientAdmissionDtlVO;
import new_investigation.vo.InvestigationSearchVO;
import new_investigation.vo.LabTestVO;
import new_investigation.vo.InvResultEntryVO;
import new_investigation.vo.RequisitionListVO;
import new_investigation.vo.SampleAcceptanceVO;
import new_investigation.vo.machineResultEntryVO;




public class machineResultEntryMachineDelegate extends Delegate
{
	public machineResultEntryMachineDelegate()
	{
		super(new InvestigationEssentialMachineBO());
	}

	 
	 
	public Map  LabComboForMachineResultEntry(machineResultEntryVO invResultEntryvo, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.LabComboForMachineResultEntry(invResultEntryvo, _UserVO);
	}
	
	 
	public Map  getLabBasedMachine(machineResultEntryVO invResultEntryvo, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.getLabBasedMachine(invResultEntryvo, _UserVO);
	}
	
	public Map getPatientMachineResultEntry(machineResultEntryVO invResultEntryvo, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.getPatientMachineResultEntry(invResultEntryvo, _UserVO);
	}
	
	
	//Save Logic
	 
	public Map  saveMachineResultEntry(Map<String,List<machineResultEntryVO>> mp_resultEntry, UserVO _userVO,HttpServletRequest _request)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		 return serviceBO.saveMachineResultEntry(mp_resultEntry, _userVO, _request);
	}
	
		

	public Map AjaxGetLabList(machineResultEntryVO vo, UserVO userVO) {
		InvestigationEssentialMachineBO serviceBO =(InvestigationEssentialMachineBO) super.getServiceProvider();
		return serviceBO.AjaxGetLabList(vo, userVO);
	}
	
	

	public Map AjaxGetMachineList(machineResultEntryVO vo, UserVO userVO) {
		InvestigationEssentialMachineBO serviceBO =(InvestigationEssentialMachineBO) super.getServiceProvider();
		return serviceBO.AjaxGetMachineList(vo, userVO);
	}
	
	public Map getPatientMachineResultEntrynew(machineResultEntryVO invResultEntryvo, UserVO _UserVO)
	{
		InvestigationEssentialMachineBO serviceBO = (InvestigationEssentialMachineBO) super.getServiceProvider();
		return serviceBO.getPatientMachineResultEntrynew(invResultEntryvo, _UserVO);
	}
	
	
	public Map  saveMachineResultEntrynew(Map<String,List<machineResultEntryVO>> mp_resultEntry, UserVO _userVO,HttpServletRequest _request)
	{
		InvestigationEssentialMachineBO serviceBO = (InvestigationEssentialMachineBO) super.getServiceProvider();
		 return serviceBO.saveMachineResultEntrynew(mp_resultEntry, _userVO, _request);
	}
	
}
