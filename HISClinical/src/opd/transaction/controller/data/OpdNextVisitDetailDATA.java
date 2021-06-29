package opd.transaction.controller.data;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdPatientDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.EpisodeActionDtlVO;
import hisglobal.vo.EpisodeCloseVO;
import hisglobal.vo.EpisodeSummaryDetailVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.UserVO;

public class OpdNextVisitDetailDATA extends ControllerDATA
{
	/** Saving Next Visit Detail
	 * @param _episodeVO Episode VO
	 * @param _userVO UserVO
	 */
	public static void saveNextVisitDetail(EpisodeVO _episodeVO, UserVO _userVO,EpisodeActionDtlVO _episodeActDtlVO,String _deskType)
	{
		OpdPatientDelegate delegate = new OpdPatientDelegate();
		delegate.saveNextVisitDetail(_episodeVO, _userVO,_episodeActDtlVO,_deskType);
	}

	/** 
	 * Retrieving Episode Detail 
	 * @param _episodeVO 
	 * @param _userVO
	 * @return Episode VO 
	 */
	public static EpisodeVO retrieveEpisodeDetail(EpisodeVO _episodeVO, UserVO _userVO)
	{
		OpdPatientDelegate delegate = new OpdPatientDelegate();
		return delegate.retrieveEpisodeDetail(_episodeVO, _userVO);
	}
	
	// Getting Visit Summary Essentials
	public static Map getEssentials(EpisodeVO _episodeVO, UserVO _userVO)
	{
		OpdEssentialDelegate delegate = new OpdEssentialDelegate();
		return delegate.getVisitSummaryEssentials(_episodeVO, _userVO);
	}

	// Saving Visit Summary Detail
	public static void saveVisitSummaryDetail(EpisodeSummaryDetailVO _episodeSummaryVO, UserVO _userVO, EpisodeActionDtlVO _episodeActDtlVO, String _deskType, EpisodeCloseVO _episodeCloseVO)
	{
		OpdPatientDelegate delegate = new OpdPatientDelegate();
		delegate.saveVisitSummaryDetail(_episodeSummaryVO, _userVO,_episodeActDtlVO,_deskType, _episodeCloseVO);
	}

	// Modifying Visit Summary Detail
	public static void modifyVisitSummaryDetail(EpisodeSummaryDetailVO _episodeSummaryVO, UserVO _userVO, EpisodeActionDtlVO _episodeActDtlVO, String _deskType, EpisodeCloseVO _episodeCloseVO)
	{
		OpdPatientDelegate delegate = new OpdPatientDelegate();
		delegate.modifyVisitSummaryDetail(_episodeSummaryVO, _userVO,_episodeActDtlVO,_deskType, _episodeCloseVO);
	}
	
	// Getting Dynamic Previous Visit Summary Detail
	public static LinkedHashMap<String, List<List<Object>>> getDynamicVisitSummaryDetail(EpisodeVO _episodeVO, List<String> _lstMenuIds, UserVO _userVO)
	{
		OpdEssentialDelegate delegate = new OpdEssentialDelegate();
		return delegate.getDynamicVisitSummaryDetail(_episodeVO, _lstMenuIds, _userVO);
	}
	public static Map getVisitSummaryDetails(EpisodeVO _episodeVO, UserVO _userVO)
	{
		OpdEssentialDelegate delegate = new OpdEssentialDelegate();
		return delegate.getVisitSummaryDetails(_episodeVO, _userVO);
	}
	
}
