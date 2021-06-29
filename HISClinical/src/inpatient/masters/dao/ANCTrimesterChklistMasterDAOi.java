package inpatient.masters.dao;

import java.util.List;

import hisglobal.vo.ANCTrimesterChecklistMasterVO;
import hisglobal.vo.UserVO;

public interface ANCTrimesterChklistMasterDAOi 
{
	/**
	 * Insert Child Handover Detail
	 * @param _ancChildHandoverDtlVO ANC Child Handover Detail VO
	 * @param _userVO User Detail
	 */
	//public void create(ANCChildHandoverDetailVO _ancChildHandoverDtlVO, UserVO _userVO);

	/**
	 * Getting Child Handover Detail
	 * @param _ancNeonatalDtlVO ANC Child Handover Detail VO
	 * @param _userVO User Detail
	 * @return 
	 */
	//public ANCChildHandoverDetailVO get(ANCChildHandoverDetailVO _ancChildHandoverDtlVO, UserVO _userVO);

	/**
	 * Getting All Trimester Checklist Detail
	 * @param _userVO User Detail
	 * @return 
	 */
	public List<ANCTrimesterChecklistMasterVO> getAll(UserVO _userVO);
}
