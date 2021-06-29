package registration.dao;


import hisglobal.vo.EpisodeDeathVO;
import hisglobal.vo.JSYRegitrationVO;
import hisglobal.vo.UserVO;

public interface EpisodeDeathDAOi {
	 public void create(EpisodeDeathVO _episodeDeathVO, UserVO _userVO);
	 public void updateTreatementCatCode(JSYRegitrationVO jRegitrationVO,String jsyCategoryCode,UserVO _userVO);
}
