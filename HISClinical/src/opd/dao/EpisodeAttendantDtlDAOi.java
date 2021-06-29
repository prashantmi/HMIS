package opd.dao;

import hisglobal.vo.EpisodeAttendantDetailVO;
import hisglobal.vo.UserVO;

import java.util.List;

public interface EpisodeAttendantDtlDAOi
{
	public void create(EpisodeAttendantDetailVO epiAttendantVO,UserVO userVO);
	
	// Getting List of Attendants of Given Episode
	public List<EpisodeAttendantDetailVO> getEpisodeAttendantsList(String strPatCrNo_p, String strEpisodeCode_p, UserVO strUserVO_p);

}
