package inpatient.transaction.dao;

import hisglobal.vo.ANCDetailVO;
import hisglobal.vo.UserVO;

public interface ANCDtlDAOi 
{
	/**
	 * Getting ANC Detail 
	 * @param _ancDetailVO ANC Detail VO
	 * @param _userVO User Detail
	 * @return
	 */
	public ANCDetailVO getANCDetail(ANCDetailVO _ancDetailVO, UserVO _userVO);
	
	/**
	 * Getting ANC Detail 
	 * @param _ancDetailVO ANC Detail VO
	 * @param _userVO User Detail
	 * @return
	 */
	public ANCDetailVO getANCDetailByEpisode(ANCDetailVO _ancDetailVO, UserVO _userVO);

	/**
	 * Getting ANC Detail By Gravida
	 * @param _ancDetailVO ANC Detail VO
	 * @param _userVO User Detail
	 * @return
	 */
	public ANCDetailVO getANCDetailByGravida(ANCDetailVO _ancDetailVO, UserVO _userVO);

	/**
	 * Getting ANC Basic History Detail
	 * @param _ancDetailVO ANC Detail VO
	 * @param _userVO User Detail
	 * @return
	 */
	public ANCDetailVO getANCBasicHistory(ANCDetailVO _ancDetailVO, UserVO _userVO);

	/**
	 * Create New ANC Detail
	 * @param _ancDetailVO ANC Detail VO
	 * @param _userVO User Detail
	 */
	public void create(ANCDetailVO _ancDetailVO, UserVO _userVO);

	/**
	 * Update ANC Detail
	 * @param _ancDetailVO ANC Detail VO
	 * @param _userVO User Detail
	 * @return
	 */
	public void update(ANCDetailVO _ancDetailVO, UserVO _userVO);
	
	public String getDeliveryStatus(UserVO _userVO,String patCrNo);
	public String getCompareGestationDate(UserVO _userVO,String patCrNo);
	public ANCDetailVO getANCDetailForJSYRegistration(String patCrNo, UserVO _userVO);
	public void updateJsyFlag(String patCrNo,String gravidaNo, UserVO _userVO);
	
	/**
	 * Getting Details for Patient Death Detail Process
	 * @param crNo
	 * @param userVO
	 * @return
	 */
	public ANCDetailVO getPatientANCDetail(String crNo,UserVO userVO);
	
	public ANCDetailVO[] getPatientAncDetails(ANCDetailVO _ancDetailVO, UserVO _userVO);
	
	public ANCDetailVO[] getPatientAncDetailsForEMR(ANCDetailVO _ancDetailVO, String[] departmentUnitArray, String accessType, UserVO _userVO);
}
