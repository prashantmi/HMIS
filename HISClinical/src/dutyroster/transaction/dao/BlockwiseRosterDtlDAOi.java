
package dutyroster.transaction.dao;

import java.util.List;

import hisglobal.vo.BlockwiseRosterDtlVO;
import hisglobal.vo.UserVO;

public interface BlockwiseRosterDtlDAOi {

	public void create(BlockwiseRosterDtlVO _rosterDtlVO,UserVO _userVO) ;

	public List fetchDistinctRostersBlockWise(String _rosterId,String _blockId,UserVO _userVO);
	
	public List fetchDistinctRostersAreaWise(String _rosterId,String _areaTypeCode,String _areaCode,UserVO _userVO);
	
	public int checkDateRangeForBlockWiseRoster(String _rosterId,String _blockId,String _startDate,String _endDate,UserVO _userVO);
	
	public int checkDateRangeForAreaWiseRoster(String _rosterId,String _areaTypeCode,String _areaCode,String _startDate,String _endDate, UserVO _userVO);
	
	public BlockwiseRosterDtlVO[] fetchLocationRosterAreaWise(String _startDate,String _endDate,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO);
	
	public BlockwiseRosterDtlVO[] fetchLocationRosterBlockWise(String _startDate,String _endDate,String _blockId,String _rosterId,UserVO _userVO);
	
	public int deleteAreaWiseRoster(BlockwiseRosterDtlVO _rosterDtlVO,UserVO _userVO,String startDateTimeOld,String endDateTimeOld);
	
	public int deleteBlockWiseRoster(BlockwiseRosterDtlVO _rosterDtlVO,UserVO _userVO,String startDateTimeOld,String endDateTimeOld);


	public int generateLocationWiseRosterForArea(String _startDate,String _endDate,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO);
	
	
	public int generateLocationWiseRosterForBlock(String _startDate,String _endDate,String _blockId,String _rosterId,UserVO _userVO);

}
