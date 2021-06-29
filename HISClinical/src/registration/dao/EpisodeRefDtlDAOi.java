package registration.dao;

import hisglobal.vo.EpisodeRefDtlVO;
import hisglobal.vo.UserVO;

public interface EpisodeRefDtlDAOi {

	public EpisodeRefDtlVO create(EpisodeRefDtlVO _episodeRefDtlVO, UserVO _userVO);
	
	public EpisodeRefDtlVO[] getEpisodeReferDtlByCrNoEpisodeCode(EpisodeRefDtlVO episodeRefDtlVO,UserVO userVO);
	
	public EpisodeRefDtlVO[] getEpisodeReferDtlByCrNoEmr(EpisodeRefDtlVO episodeRefDtlVO,UserVO userVO);
}
