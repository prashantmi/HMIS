package inpatient.transaction.dao;

import hisglobal.vo.ANCHistoryDetailVO;
import hisglobal.vo.UserVO;

import java.util.List;

public interface ANCHistoryDtlDAOi 
{
	/**
	 * Getting ANC History Detail 
	 * @param _patCrNo Patient Cr No
	 * @param _userVO User Detail
	 * @return List of ANC History
	 */
	public List<ANCHistoryDetailVO> getANCHistoryDetail(String _patCrNo, UserVO _userVO);

	/**
	 * Getting ANC History Detail
	 * @param _patCrNo Patient CR No
	 * @param _gravidaNo Gravida No
	 * @param _userVO User Detail
	 * @return ANC History Detail
	 */
	public ANCHistoryDetailVO get(String _patCrNo, String _gravidaNo, UserVO _userVO);

	/**
	 * Create New ANC History Detail
	 * @param _ancHistDtlVO ANC History Detail VO
	 * @param _userVO User Detail
	 */
	public void create(ANCHistoryDetailVO _ancHistDtlVO, UserVO _userVO);

	/**
	 * Updating ANC History Detail
	 * @param _ancHistDtlVO ANC History Detail VO
	 * @param _userVO User Detail
	 */
	public void update(ANCHistoryDetailVO _ancHistDtlVO, UserVO _userVO);
}
