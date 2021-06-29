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
import new_investigation.transactions.bo.InvestigationEssentialReportsBO;
import new_investigation.transactions.bo.InvestigationEssentialReportsBOi;
import new_investigation.vo.InvResultReportPrintingVO;
import new_investigation.vo.Inv_EpisodeVO;
import new_investigation.vo.Inv_PatientAdmissionDtlVO;
import new_investigation.vo.InvestigationSearchVO;
import new_investigation.vo.LabTestVO;
import new_investigation.vo.InvResultEntryVO;
import new_investigation.vo.RequisitionListVO;
import new_investigation.vo.SampleAcceptanceVO;




public class InvResultReportPrintingDelegateResp extends Delegate
{
	public InvResultReportPrintingDelegateResp()
	{
		super(new InvestigationEssentialReportsBO());
	}

	 
	 
	public Map  LabComboForResultReportPrinting(InvResultReportPrintingVO invresultreportprintingvo, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.LabComboForResultReportPrinting(invresultreportprintingvo, _UserVO);
	}
	
	public Map setResultReportPrintingEssentials(InvResultReportPrintingVO invresultreportprintingvo, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.setResultReportPrintingEssentials(invresultreportprintingvo, _UserVO);
	}
	
	
	
	public Map PdfDetails(List<InvResultReportPrintingVO> invresultreportprintingvo, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.pdfDetails(invresultreportprintingvo, _UserVO);
	}
	
	
	public Map setResultReportPrintingEssentialsOnLoad(InvResultReportPrintingVO invresultreportprintingvo, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.setResultReportPrintingEssentialsOnLoad(invresultreportprintingvo, _UserVO);
	}
	
	
	public Map saveResultReportPrintingDetails(List<InvResultReportPrintingVO> invresultreportprintingvo, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.saveResultReportPrintingDetails(invresultreportprintingvo, _UserVO);
	}

	
	public String  isfromFTPorMONGO( UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.isfromFTPorMONGO( _UserVO);
	}
	
	
	/*
	//Save Logic
	 
		public Map  saveResultEntryDetails(List<InvResultEntryVO> invResultEntryvo,List<InvResultEntryVO>  invResultEntryForParameterDtlVO,  UserVO _userVO,HttpServletRequest _request)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			 return serviceBO.saveResultEntryDetails(invResultEntryvo, invResultEntryForParameterDtlVO, _userVO, _request);
		}
	*/
	 
	
	public Map setResultReportPrintingEssentialsnew(InvResultReportPrintingVO invresultreportprintingvo, UserVO _UserVO)
	{
		InvestigationEssentialReportsBOi serviceBO = (InvestigationEssentialReportsBOi) super.getServiceProvider();
		return serviceBO.setResultReportPrintingEssentialsOnLoadnew(invresultreportprintingvo, _UserVO);
	}
	
	public String UPDATEREPORTS(InvResultReportPrintingVO invresultreportprintingvo, UserVO _UserVO)
	{
		InvestigationEssentialReportsBOi serviceBO = (InvestigationEssentialReportsBOi) super.getServiceProvider();
		return serviceBO.UPDATEREPORTS(invresultreportprintingvo, _UserVO);
	}
}
