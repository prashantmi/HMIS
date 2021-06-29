package investigationDesk.transactions.dao;

import hisglobal.vo.UserVO;

import java.util.List;

import investigationDesk.vo.BookMarkVO;
import investigationDesk.vo.Inv_EpisodeVO;
import investigationDesk.vo.Inv_RequisitionRaisingPatientVO;
import investigationDesk.vo.InvestigationRequistionVO;
import investigationDesk.vo.InvestigationSearchVO;
import investigationDesk.vo.LabTestVO;

/**
 * @author : C-DAC, Noida Project : HISInvestigationG5 Module : Blood Bank Created On : 18
 *         Aug, 2008
 * 
 *         Developed By : For Common Use
 * 
 *         Purpose : This Interface should be used for all Essential Data Access
 *         Data Layer Methods regarding all processes
 * 
 *         Modified By: Pawan Kumar B N
 * 
 *         Modified On: 18-11-2011
 * 
 */

public interface InvestigationEssentialDAOi {

	public Inv_RequisitionRaisingPatientVO getInvRaisingPatDetails(
			Inv_RequisitionRaisingPatientVO patVO, UserVO _UserVO);

	public List<LabTestVO> searchLabWiseTestDtls(
			InvestigationSearchVO searchVO, UserVO _UserVO);

	public String generateRequisitionNoSequence(String labCode, UserVO userVO);

	public void insertRequisitionSequenceInMaintainer(String labCode,
			String sequence, String yymmdd, UserVO _userVO);

	public void updateRequisitionSequenceInMaintainer(String sequence,
			String labcCode, UserVO _UserVO);

	public void insertRequisitionDtl(InvestigationRequistionVO voLabTest,
			UserVO _userVO);

	public List<Inv_EpisodeVO> getPatientEpisode(
			Inv_RequisitionRaisingPatientVO patVO, UserVO _userVO);

	public List<BookMarkVO> getBookMarkList(UserVO _userVO);

	public List<LabTestVO> searchBookMark(InvestigationSearchVO searchVO,
			UserVO _UserVO,String bookType);
	
	public List getLabNames(UserVO _UserVO);
	
	public List getTestNames(UserVO _UserVO);
	
	public List getTestNamesUsingAJAX(String labCode,UserVO _UserVO);
	
	public  List<LabTestVO> searchTestGroup(InvestigationSearchVO searchVO ,UserVO _UserVO);
	
	public List getTestGroupUsingAJAX(String labCode,UserVO _UserVO); 
	
	public  List<LabTestVO> getTestsUsingGroup(InvestigationSearchVO searchVO ,UserVO _UserVO);

}
