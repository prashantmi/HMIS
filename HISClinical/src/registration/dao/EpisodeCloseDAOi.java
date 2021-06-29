package registration.dao;

import hisglobal.presentation.Status;
import hisglobal.vo.DailyPatientVO;
import hisglobal.vo.EpisodeCloseVO;
import hisglobal.vo.UserVO;

public interface EpisodeCloseDAOi {
	
	public void create(EpisodeCloseVO _EpisodeCloseVO, UserVO _userVO);
	
	public void update(EpisodeCloseVO _episodeCloseVO, UserVO _userVO);
}