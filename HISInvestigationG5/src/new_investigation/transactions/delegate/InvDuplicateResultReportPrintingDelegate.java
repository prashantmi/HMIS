package new_investigation.transactions.delegate;

/**
 * @author C-DAC, Noida Project : AHIMS Module 
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
import new_investigation.vo.InvDuplicateResultReportPrintingVO;
import new_investigation.vo.InvResultReportPrintingVO;
import new_investigation.vo.Inv_EpisodeVO;
import new_investigation.vo.Inv_PatientAdmissionDtlVO;
import new_investigation.vo.InvestigationSearchVO;
import new_investigation.vo.LabTestVO;
import new_investigation.vo.InvResultEntryVO;
import new_investigation.vo.RequisitionListVO;
import new_investigation.vo.SampleAcceptanceVO;




public class InvDuplicateResultReportPrintingDelegate extends Delegate
{
	public InvDuplicateResultReportPrintingDelegate()
	{
		super(new InvestigationEssentialBO());
	}

	 
	 
	public Map  LabComboForDuplicateResultReportPrinting(InvDuplicateResultReportPrintingVO invresultreportprintingvo, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.LabComboForDuplicateResultReportPrinting(invresultreportprintingvo, _UserVO);
	}
	
	public Map setDuplicateResultReportPrintingEssentials(InvDuplicateResultReportPrintingVO invresultreportprintingvo, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.setDuplicateResultReportPrintingEssentials(invresultreportprintingvo, _UserVO);
	}
	
	
	
	public Map duplicatePdfDetails(List<InvDuplicateResultReportPrintingVO> invresultreportprintingvo, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.duplicatePdfDetails(invresultreportprintingvo, _UserVO);
	}
	
	
	public Map setDuplicateResultReportPrintingEssentialsOnLoad(InvDuplicateResultReportPrintingVO invresultreportprintingvo, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.setDuplicateResultReportPrintingEssentialsOnLoad(invresultreportprintingvo, _UserVO);
	}
	
	/*
	//Save Logic
	 
		public Map  saveResultEntryDetails(List<InvResultEntryVO> invResultEntryvo,List<InvResultEntryVO>  invResultEntryForParameterDtlVO,  UserVO _userVO,HttpServletRequest _request)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			 return serviceBO.saveResultEntryDetails(invResultEntryvo, invResultEntryForParameterDtlVO, _userVO, _request);
		}
	*/
	 
	
}
