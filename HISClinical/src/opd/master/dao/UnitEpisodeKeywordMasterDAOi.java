package opd.master.dao;

import java.util.List;

import hisglobal.vo.UnitEpisodeKeywordVO;
import hisglobal.vo.UserVO;

public interface UnitEpisodeKeywordMasterDAOi
{
	// Inserting Episode Keyword Unit Record
	public void create(UnitEpisodeKeywordVO _unitEpisodeKeywordVO, UserVO _UserVO);

	// Getting Unit List to which pisode Keywords not added yet
	public List getNotAddedUnitList(UserVO _userVO);

	// Deleting Episode Keywords Unit Wise 
	public void deleteEpiKeywordsUnitWise(String _deptUnitCode, UserVO _userVO);
}
