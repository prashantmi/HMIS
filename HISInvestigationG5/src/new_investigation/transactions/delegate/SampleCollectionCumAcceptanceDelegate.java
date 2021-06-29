/*
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: ANANT PATEL
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    :Sample Collection Cum Acceptance Process
 ## Purpose						        :  
 ## Date of Creation					:25-May-2015 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 
*/

package new_investigation.transactions.delegate;

import java.util.List;
import java.util.Map;

import new_investigation.transactions.bo.InvestigationEssentialBO;
import new_investigation.transactions.bo.InvestigationEssentialBOi;
import new_investigation.vo.Inv_EpisodeVO;
import new_investigation.vo.Inv_PatientAdmissionDtlVO;
import new_investigation.vo.Inv_RequisitionRaisingPatientVO;
import new_investigation.vo.SampleCollectionCumAcceptanceVO;
import new_investigation.vo.InvestigationSearchVO;
import hisglobal.business.Delegate;
import hisglobal.vo.UserVO;

public class SampleCollectionCumAcceptanceDelegate extends Delegate {

	public SampleCollectionCumAcceptanceDelegate() {
		super(new InvestigationEssentialBO());;
	}
	
	public List<SampleCollectionCumAcceptanceVO> getSampleCollectionArea(UserVO _userVO)
	 
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		 return serviceBO.getSampleCollectionAreas(_userVO);
	}
	public List<SampleCollectionCumAcceptanceVO> getPatList(SampleCollectionCumAcceptanceVO objSampleCollectionVO,UserVO _userVO)
	 
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		 return serviceBO.getPatList(objSampleCollectionVO,_userVO);
	}
	public Map  searchLabWiseTestDetails(InvestigationSearchVO searchVO,UserVO userVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
		return(serviceBO.searchLabWiseTestDtls(searchVO,userVO));
	}
	public List  saveSampleCollectionDetails(Map<String,Map<String,Map<String,List<SampleCollectionCumAcceptanceVO>>>> mp,UserVO _userVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		 return serviceBO.saveSampleCollectionDetail(mp,_userVO);
	}
	public List<Inv_EpisodeVO>  getPatientEpisodeDetail(Inv_RequisitionRaisingPatientVO patVO,UserVO _userVO)
	 
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		 return serviceBO.getPatientEpisodeDetail(patVO,_userVO);
	}
	public List<Inv_PatientAdmissionDtlVO>  getPatientAdmissionDetail(Inv_RequisitionRaisingPatientVO patVO,UserVO _userVO)
	 
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		 return serviceBO.getPatientAdmissionDetail(patVO,_userVO);
	}
	public Map  getBilledPatList(List<String> reqList,SampleCollectionCumAcceptanceVO voSample,UserVO userVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
		return(serviceBO.getBilledPatientList(reqList,voSample,userVO));
	}
	public List<String> getStaffDetails(String crNo, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
		return(serviceBO.getStaffDetails(crNo, _UserVO));
	}
	public boolean checkSampleNoDuplicacy(SampleCollectionCumAcceptanceVO voSample,UserVO userVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
		return(serviceBO.checkSampleNoDuplicacy(voSample, userVO));
	}
	
	
	public String checkAutoGenFormate(SampleCollectionCumAcceptanceVO voSample,UserVO userVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
		return(serviceBO.checkAutoGenFormateSampleCollCum(voSample, userVO));
	}
	
	public Map  getLabTestMachine(String labcode,UserVO userVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
		return(serviceBO.getLabTestMachine(labcode,userVO));
	}
	
}
