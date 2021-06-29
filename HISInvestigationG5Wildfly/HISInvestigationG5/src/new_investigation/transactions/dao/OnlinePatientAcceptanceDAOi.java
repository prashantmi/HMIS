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
import new_investigation.vo.OnlinePatientAcceptanceVO;
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

public interface OnlinePatientAcceptanceDAOi
{
	
	  
	
	 
	
	 
	public void updateRequisitionSequenceInMaintainer(String sequence,String labcCode,UserVO _UserVO);
	
	public void insertRequisitionDtl(InvestigationRequistionVO voLabTest, UserVO _userVO);
	
	 

	public List getLabCombos(OnlinePatientAcceptanceVO onlinePatientvo,UserVO _UserVO);
	
	public List<OnlinePatientAcceptanceVO> setPatientEssentials(OnlinePatientAcceptanceVO onlinePatientvo, UserVO _UserVO);
	
	
	public List<OnlinePatientAcceptanceVO> setPatientEssentialsOnLoad(OnlinePatientAcceptanceVO onlinePatientvo, UserVO _UserVO);

	//added by krishnan nema on 08/10/2018
	public List getMachineCombos(OnlinePatientAcceptanceVO onlinePatientvo,UserVO _UserVO);

	/* Added by prashantMi */
	public List<OnlinePatientAcceptanceVO> setAcceptedPatientEssentials(OnlinePatientAcceptanceVO onlinePatientvo, UserVO _UserVO);

	
	
}

