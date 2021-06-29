package new_investigation.reports.delegate;

import hisglobal.business.Delegate;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import new_investigation.masters.bo.InvestigationMasterBO;
import new_investigation.masters.bo.InvestigationMasterBOi;
import new_investigation.reports.bo.AgewiseDistributionReportBO;
import new_investigation.vo.BookMarkMstVO;
import new_investigation.vo.CannedMasterVO;
import new_investigation.vo.CollAreaSampleNoConfigMasterVO;
import new_investigation.vo.CollectionAreaMasterVO;
import new_investigation.vo.ContainerMasterVO;
import new_investigation.vo.InvParameterMasterVO;
import new_investigation.vo.InvSampleMasterVO;
import new_investigation.vo.InvTestSampleMasterVO;
import new_investigation.vo.AgewiseDistributionReportVO;
import new_investigation.vo.LabCannedMasterVO;
import new_investigation.vo.LabCollectionAreaMasterVO;
import new_investigation.vo.LabConfigratorMstVO;
import new_investigation.vo.LabMacroMapMasterVO;
import new_investigation.vo.LabMasterVO;
import new_investigation.vo.LabNoConfigMasterVO;
import new_investigation.vo.LabTestGlobalMstVO;
import new_investigation.vo.LabTestSampleMstVO;
import new_investigation.vo.LocalTestMandRefMasterVO;
import new_investigation.vo.LoincMstVO;
import new_investigation.vo.MacroMasterVO;
import new_investigation.vo.MandatoryComboMasterVO;
import new_investigation.vo.MandatoryMasterVO;
import new_investigation.vo.RejectionReasonMasterVO;
import new_investigation.vo.RequisitionListingReportVO;
import new_investigation.vo.SampleNoConfigMasterVO;
import new_investigation.vo.TestGroupInfoMasterVO;
import new_investigation.vo.TestGroupInfoMasterVO;
import new_investigation.vo.TestGroupMasterVO;
import new_investigation.vo.TestMandRefMasterVO;
import new_investigation.vo.TestMandatoryMstVO;
import new_investigation.vo.TestNewMasterVO;
import new_investigation.vo.TestParaComboMasterVO;
import new_investigation.vo.TestParameterMasterVO;

 
 
import new_investigation.vo.ContainerMasterVO;
import new_investigation.vo.CollectionAreaMasterVO;
import new_investigation.vo.MandatoryComboMasterVO;
import new_investigation.vo.TestParaComboMasterVO;
import new_investigation.vo.InvParameterMasterVO;
import new_investigation.vo.MandatoryMasterVO;
import new_investigation.vo.InvTestSampleMasterVO;
import new_investigation.vo.UserBookMarkMstVO;

 

import new_investigation.vo.UOMMasterVO;

public class AgewiseDistributionReportDelegate extends Delegate
{
	public AgewiseDistributionReportDelegate()
	{
		super(new AgewiseDistributionReportBO());
	}  

	public Map fetchLab(AgewiseDistributionReportVO reqList_VO, UserVO _UserVO)
	{
		AgewiseDistributionReportBO serviceBO = (AgewiseDistributionReportBO) super.getServiceProvider();
		return serviceBO.fetchLab(reqList_VO, _UserVO);
	}
	
	public Map fetchTest(AgewiseDistributionReportVO reqList_VO, UserVO _UserVO)
	{
		AgewiseDistributionReportBO serviceBO = (AgewiseDistributionReportBO) super.getServiceProvider();
		return serviceBO.fetchTest(reqList_VO, _UserVO);
	}
	
	public Map fetchDept(AgewiseDistributionReportVO reqList_VO, UserVO _UserVO)
	{
		AgewiseDistributionReportBO serviceBO = (AgewiseDistributionReportBO) super.getServiceProvider();
		return serviceBO.fetchDept(reqList_VO, _UserVO);
	}

	public Map fetchSample(AgewiseDistributionReportVO reqList_VO, UserVO _UserVO)
	{
		AgewiseDistributionReportBO serviceBO = (AgewiseDistributionReportBO) super.getServiceProvider();
		return serviceBO.fetchSample(reqList_VO, _UserVO);
	}
	
	

}