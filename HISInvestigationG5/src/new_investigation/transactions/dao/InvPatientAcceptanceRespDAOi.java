package new_investigation.transactions.dao;

import java.util.List;

 



import new_investigation.vo.BookMarkVO;
import new_investigation.vo.Inv_EpisodeVO;
import new_investigation.vo.InvestigationRequistionVO;
 
import new_investigation.vo.Inv_EpisodeVO;
import new_investigation.vo.InvestigationRequistionVO;
 
import new_investigation.vo.InvestigationSearchVO;
import new_investigation.vo.LabTestVO;
import new_investigation.vo.Inv_RequisitionRaisingPatientVO;
import new_investigation.vo.InvPatientAcceptanceRespVO;
import hisglobal.vo.PatientDetailVO;
import new_investigation.vo.TestParameterMasterVO;
import hisglobal.vo.UserVO;

/**
 * @author : C-DAC, Noida Project : HISInvestigationG5 Module : Blood Bank Created On : 18 Aug, 2008
 * 
 * Developed By : For Common Use
 * 
 * Purpose : This Interface should be used for all Essential Data Access Data Layer Methods regarding all processes
 * 
 * Modified By: Pathan Basha
 * 
 * Modified On: 26-02-2015
 * 
 */

public interface InvPatientAcceptanceRespDAOi
{
	
	  
	
	 
	
	 
	public void updateRequisitionSequenceInMaintainer(String sequence,String labcCode,UserVO _UserVO);
	
	public void insertRequisitionDtl(InvestigationRequistionVO voLabTest, UserVO _userVO);
	
	 

	public List getLabCombos(InvPatientAcceptanceRespVO onlinePatientvo,UserVO _UserVO);
	
	public List<InvPatientAcceptanceRespVO> setPatientEssentials(InvPatientAcceptanceRespVO onlinePatientvo, UserVO _UserVO);
	
	
	public List<InvPatientAcceptanceRespVO> setPatientEssentialsOnLoad(InvPatientAcceptanceRespVO onlinePatientvo, UserVO _UserVO);

	//added by krishnan nema on 08/10/2018
	public List getMachineCombos(InvPatientAcceptanceRespVO onlinePatientvo,UserVO _UserVO);

	/* Added by prashantMi */
	public List<InvPatientAcceptanceRespVO> setAcceptedPatientEssentials(InvPatientAcceptanceRespVO onlinePatientvo, UserVO _UserVO);

	public List<InvPatientAcceptanceRespVO> AjaxGetPatAcceptanceReqnList(InvPatientAcceptanceRespVO invPatientAcceptanceRespVO,
			UserVO _UserVO);

	
	
}

