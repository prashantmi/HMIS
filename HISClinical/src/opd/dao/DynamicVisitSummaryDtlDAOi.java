package opd.dao;

import hisglobal.vo.EpisodeVO;
import hisglobal.vo.UserVO;

import java.util.LinkedHashMap;
import java.util.List;

public interface DynamicVisitSummaryDtlDAOi
{
	// Getting Map of Dynaically Fetched Details of Visit
	public LinkedHashMap<String, List<List<Object>>> getDynamicVisitDetail(EpisodeVO _epiVO, List<String> _lstMenuIds, UserVO _userVO);
}
