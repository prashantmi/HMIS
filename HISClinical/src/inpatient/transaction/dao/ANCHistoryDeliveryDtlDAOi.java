package inpatient.transaction.dao;

import hisglobal.vo.ANCHistoryDeliveryDetailVO;
import hisglobal.vo.UserVO;

import java.util.List;

public interface ANCHistoryDeliveryDtlDAOi 
{
	/**
	 * Getting ANC History Delivery Detail 
	 * @param _patCrNo Patient Cr No
	 * @param _userVO User Detail
	 * @return List of ANC History
	 */
	public List<ANCHistoryDeliveryDetailVO> getANCHistoryDeliveryDetail(String _patCrNo, UserVO _userVO);

	/**
	 * Getting ANC History Delivery Detail
	 * @param _patCrNo Patient CR No
	 * @param _gravidaNo Gravida No
	 * @param _userVO User Detail
	 * @return ANC History Delivery Detail
	 */
	public ANCHistoryDeliveryDetailVO get(String _patCrNo, String _gravidaNo, UserVO _userVO);

	/**
	 * Create New ANC History Delivery Detail
	 * @param _ancHistDtlVO ANC History Delivery Detail VO
	 * @param _userVO User Detail
	 */
	public void create(ANCHistoryDeliveryDetailVO _ancHistDeliveryDtlVO, UserVO _userVO);

	/**
	 * Update ANC History Delivery Detail
	 * @param _ancHistDtlVO ANC History Delivery Detail VO
	 * @param _userVO User Detail
	 */
	public void update(ANCHistoryDeliveryDetailVO _ancHistDtlVO, UserVO _userVO);
}
