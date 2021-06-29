package opd.master.dao;

import hisglobal.vo.EpisodeKeywordsMasterVO;
import hisglobal.vo.UserVO;

public interface EpisodeKeywordsMasterDAOi
{
	public void creat(EpisodeKeywordsMasterVO keywordMstVO, UserVO userVO);

	public String checkDuplicateName(EpisodeKeywordsMasterVO keywordMstVO, UserVO userVO);

	public EpisodeKeywordsMasterVO getDataForModify(EpisodeKeywordsMasterVO keywordMstVO, UserVO _UserVO);

	public String checkDuplicateNameForModify(EpisodeKeywordsMasterVO keywordMstVO, UserVO userVO);

	public void updateKeywordMaster(EpisodeKeywordsMasterVO keywordMstVO, UserVO _UserVO);

	public void saveModEnclosureMaster(EpisodeKeywordsMasterVO keywordMstVO, UserVO userVO);
}
