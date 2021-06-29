package new_investigation.transactions.delegate;

/**
 * @author C-DAC, Noida Project : HISInvestigationG5 Module 
 * @Date 20 Aug, 2008 
 * Process: Donor Registration
 * Modified By: Pathan Basha
 * Modified On: 11-03-2015
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
 
import new_investigation.vo.SampleAcceptanceVO;
import new_investigation.vo.RequisitionListVO;




public class SampleAcceptanceBarCodeDelegate extends Delegate
{
	public SampleAcceptanceBarCodeDelegate()
	{
		super(new InvestigationEssentialBO());
	}

	 
	public Map  sampleAcceptanceLabCombo(SampleAcceptanceVO sampleAcceptanceVO, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.sampleAcceptanceLabCombo(sampleAcceptanceVO, _UserVO);
	}
	
	
	public Map getSampleAcceptanceDetail(SampleAcceptanceVO sampleAcceptanceVO, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.getSampleAcceptanceDetailBarCode(sampleAcceptanceVO, _UserVO);
	}
	
	public Map getSampleAcceptanceDetail1(SampleAcceptanceVO sampleAcceptanceVO, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.getSampleAcceptanceDetailBarCode1(sampleAcceptanceVO, _UserVO);
	}
	
	public Map getSampleAcceptanceDetailOnLoad(SampleAcceptanceVO sampleAcceptanceVO, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.getSampleAcceptanceDetailOnLoad(sampleAcceptanceVO, _UserVO);
	}
	public Map SampleAcceptanceRejectionCombo(SampleAcceptanceVO sampleAcceptanceVO, List<String> reqList,UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.SampleAcceptanceRejectionCombo(sampleAcceptanceVO, reqList, _UserVO);
	}
	
	public boolean checkDailyLabNoDuplicacy(SampleAcceptanceVO sampleAcceptanceVO, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.checkDailyLabNoDuplicacyforSampleAcc(sampleAcceptanceVO,   _UserVO);
	}
	
	
	//Save Logic
	 
	public Map  saveSampleAccDetails(List<SampleAcceptanceVO> voSampleAcc,  UserVO _userVO,HttpServletRequest _request)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		 return serviceBO.saveSampleAccDetails(voSampleAcc,  _userVO, _request);
	}
	
	 
	public Map  LabCombos(SampleAcceptanceVO onlinePatientvo, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.LabCombos(onlinePatientvo, _UserVO);
	}
	
	
	
	
	
	 
	
}
