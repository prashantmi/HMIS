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
import new_investigation.transactions.bo.InvResultReportPrintingRespBO;
import new_investigation.transactions.bo.InvResultReportPrintingRespBOi;
import new_investigation.vo.InvResultReportPrintingRespVO;
import new_investigation.vo.Inv_EpisodeVO;
import new_investigation.vo.Inv_PatientAdmissionDtlVO;
import new_investigation.vo.InvestigationSearchVO;
import new_investigation.vo.LabTestVO;
import new_investigation.vo.InvResultEntryVO;
import new_investigation.vo.RequisitionListVO;
import new_investigation.vo.SampleAcceptanceVO;




public class InvResultReportPrintingRespDelegate extends Delegate
{
	public InvResultReportPrintingRespDelegate()
	{
		super(new InvResultReportPrintingRespBO());
	}

	 
	 
	public Map  LabComboForResultReportPrintingResp(InvResultReportPrintingRespVO invresultreportprintingvo, UserVO _UserVO)
	{
		InvResultReportPrintingRespBOi serviceBO = (InvResultReportPrintingRespBOi) super.getServiceProvider();
		return serviceBO.LabComboForResultReportPrintingResp(invresultreportprintingvo, _UserVO);
	}
	
	public Map setResultReportPrintingRespEssentials(InvResultReportPrintingRespVO invresultreportprintingvo, UserVO _UserVO)
	{
		InvResultReportPrintingRespBOi serviceBO = (InvResultReportPrintingRespBOi) super.getServiceProvider();
		return serviceBO.setResultReportPrintingRespEssentials(invresultreportprintingvo, _UserVO);
	}
	
	
	
	public Map PdfDetails(List<InvResultReportPrintingRespVO> invresultreportprintingvo, UserVO _UserVO)
	{
		InvResultReportPrintingRespBOi serviceBO = (InvResultReportPrintingRespBOi) super.getServiceProvider();
		return serviceBO.pdfDetails(invresultreportprintingvo, _UserVO);
	}
	
	
	public Map setResultReportPrintingRespEssentialsOnLoad(InvResultReportPrintingRespVO invresultreportprintingvo, UserVO _UserVO)
	{
		InvResultReportPrintingRespBOi serviceBO = (InvResultReportPrintingRespBOi) super.getServiceProvider();
		return serviceBO.setResultReportPrintingRespEssentialsOnLoad(invresultreportprintingvo, _UserVO);
	}
	
	
	public Map saveResultReportPrintingRespDetails(List<InvResultReportPrintingRespVO> invresultreportprintingvo, UserVO _UserVO)
	{
		InvResultReportPrintingRespBOi serviceBO = (InvResultReportPrintingRespBOi) super.getServiceProvider();
		return serviceBO.saveResultReportPrintingRespDetails(invresultreportprintingvo, _UserVO);
	}

	
	public String  isfromFTPorMONGO( UserVO _UserVO)
	{
		InvResultReportPrintingRespBOi serviceBO = (InvResultReportPrintingRespBOi) super.getServiceProvider();
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
	 
	
}
