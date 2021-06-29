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
import new_investigation.vo.SampleAcceptanceVO;
import hisglobal.vo.PatientDetailVO;
import new_investigation.vo.TestParameterMasterVO;
import hisglobal.vo.UserVO;

/**
 * @author : C-DAC, Noida Project : HISInvestigationG5 Module :  
 * 
 * Developed By : For Common Use
 * 
 * Purpose : This Interface should be used for all Essential Data Access Data Layer Methods regarding all processes
 * 
 * Modified By: Pathan Basha
 * 
 * Modified On: 11-03-2015
 * 
 */

public interface SampleAcceptanceDAOi
{
	
	public List sampleAcceptanceLabCombo(SampleAcceptanceVO sampleAcceptanceVO,UserVO _UserVO);
	
	
	public List<SampleAcceptanceVO> getSampleAcceptanceDetail(SampleAcceptanceVO sampleAcceptanceVO, UserVO _UserVO);

	public List<SampleAcceptanceVO> getSampleAcceptanceDetailOnLoad(SampleAcceptanceVO sampleAcceptanceVO, UserVO _UserVO);

	public String getSampleAcceptanceDetailForCheckPackNo(SampleAcceptanceVO sampleAcceptanceVO, UserVO _UserVO);
	
	public List<SampleAcceptanceVO> getSampleAcceptanceDetailForCheckPackNoToReject(SampleAcceptanceVO sampleAcceptanceVO, UserVO _UserVO);

	
	
	public List<SampleAcceptanceVO> getSampleAcceptanceDetailBarCode(SampleAcceptanceVO sampleAcceptanceVO, UserVO _UserVO);
  
	public List<SampleAcceptanceVO> getSampleAcceptanceDetailBarCode1(SampleAcceptanceVO sampleAcceptanceVO, UserVO _UserVO);
	
	public String checkAutoGenFormate(SampleAcceptanceVO voSample,UserVO _UserVO)	;
	 
	public List machinelist(SampleAcceptanceVO sampleAcceptanceVO,UserVO _UserVO);
	
	
}

