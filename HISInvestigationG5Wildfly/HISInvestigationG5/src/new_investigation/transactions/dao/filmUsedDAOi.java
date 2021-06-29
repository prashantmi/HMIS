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
import new_investigation.vo.filmUsedVO;
import hisglobal.vo.PatientDetailVO;
import new_investigation.vo.TestParameterMasterVO;
import hisglobal.vo.UserVO;



public interface filmUsedDAOi
{
	
	  
	
	 
	
	 
	public void updateRequisitionSequenceInMaintainer(String sequence,String labcCode,UserVO _UserVO);
	
	public void insertRequisitionDtl(InvestigationRequistionVO voLabTest, UserVO _userVO);
	
	 

	public List getLabCombos(filmUsedVO filmvo,UserVO _UserVO);
	
	public List<filmUsedVO> setPatientEssentials(filmUsedVO onlinePatientvo, UserVO _UserVO);
	
	
	public List<filmUsedVO> setPatientEssentialsOnLoad(filmUsedVO onlinePatientvo, UserVO _UserVO);

	public List getFilmSizeCombo(filmUsedVO filmvo,UserVO _UserVO); 
	public String getFilmNo(filmUsedVO filmvo,UserVO _UserVO);
	public List getBatchDetails(filmUsedVO filmvo,UserVO _UserVO); 
	public List<filmUsedVO> getprevrequisition(String crno, UserVO _userVO);


	
	
}

