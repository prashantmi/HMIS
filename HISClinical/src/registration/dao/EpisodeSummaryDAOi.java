package registration.dao;

import java.util.List;

import hisglobal.vo.EpisodeSummaryDetailVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.UserVO;

public interface EpisodeSummaryDAOi
{
	/**
	 * Saving Episode Summary Detail
	 * @param _episodeSummaryVO
	 * @param _userVO
	 */
	public void create(EpisodeSummaryDetailVO _episodeSummaryVO, UserVO _userVO);

	/**
	 * Retrieves All Visit Summary of Current Visit
	 * @param	_episodeVO Epispde Visit Detail
	 * @param	_userVO	User Detail
	 * @return	List of EpisodeSummaryDetailVO
	 */
	public List<EpisodeSummaryDetailVO> getAllVisitSummaryByEpisodeVisit(EpisodeVO _episodeVO, UserVO _userVO);

	/**
	 * Updating Old Episode Summary Detail as Deleted
	 * @param _episodeSummaryVO
	 * @param _userVO
	 */
	public void updateOld(EpisodeSummaryDetailVO _episodeSummaryVO, UserVO _userVO);

	//public void update(EpisodeCloseVO _episodeCloseVO, UserVO _userVO);
	
	public List<EpisodeSummaryDetailVO> getAllVisitSummaryByCrNo(EpisodeVO _episodeVO,String[] departmentUnitArray, String accessType, UserVO _userVO);
	
	public String select(String episodeKeywords, UserVO _userVO);
	
	public void updateEpisodeSummaryDtl(EpisodeSummaryDetailVO _episodeSummaryVO, UserVO _userVO);
	
	public List<EpisodeVO> getVisitSummaryDetails(EpisodeVO _episodeVO, UserVO _userVO);
	
}
