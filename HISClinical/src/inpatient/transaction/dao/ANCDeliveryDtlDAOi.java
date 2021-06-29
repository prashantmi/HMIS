package inpatient.transaction.dao;

import hisglobal.vo.ANCDeliveryDetailVO;
import hisglobal.vo.ANCDetailVO;
import hisglobal.vo.UserVO;

public interface ANCDeliveryDtlDAOi 
{
	/**
	 * Getting ANC Delivery Detail 
	 * @param _ancDeliveryDetailVO ANC Delivery Detail VO
	 * @param _userVO User Detail
	 * @return
	 */
	public ANCDeliveryDetailVO getANCDeliveryDetail(ANCDeliveryDetailVO _ancDeliveryDetailVO, UserVO _userVO);
	
	/**
	 * Create New ANC Delivery Detail
	 * @param _ancDeliveryDetailVO ANC Delivery Detail VO
	 * @param _userVO User Detail
	 */
	public void create(ANCDeliveryDetailVO _ancDeliveryDetailVO, UserVO _userVO);

	/**
	 * Update ANC Detail
	 * @param _ancDetailVO ANC Detail VO
	 * @param _userVO User Detail
	 * @return
	 */
	//public void update(ANCDetailVO _ancDetailVO, UserVO _userVO);
	
	/**
	 * Updating Mother Death Status
	 * @param ancDetailVO
	 * @param userVO
	 */
	public void updateMotherStatus(ANCDetailVO ancDetailVO,UserVO userVO);
}
