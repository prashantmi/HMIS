package inpatient.transaction.dao;

import java.util.List;

import hisglobal.vo.ANCNeonatalDetailVO;
import hisglobal.vo.UserVO;

public interface ANCNeonatalDtlDAOi 
{
	/**
	 * Create Initial Entry for ANC Neonatal Detail
	 * @param _ancHistDtlVO ANC History Delivery Detail VO
	 * @param _userVO User Detail
	 */
	public void create(ANCNeonatalDetailVO _ancNeonatalDtlVO, UserVO _userVO);

	/**
	 * Getting Last Delivered New Nats Detail
	 * @param _ancNeonatalDtlVO
	 * @param _userVO User Detail
	 * @return List of New Nats
	 */
	public List<ANCNeonatalDetailVO> getLastAllNeoNatDtl(ANCNeonatalDetailVO _ancNeonatalDtlVO, UserVO _userVO);

	/**
	 * Update ANC New Natal Detail
	 * @param _ancNeonatalDtlVO ANC Detail VO
	 * @param _userVO User Detail
	 * @return
	 */
	public void update(ANCNeonatalDetailVO _ancNeonatalDtlVO, UserVO _userVO);
	
	/**
	 * Updating Newnatal Death from Patient Death Detail Process  
	 * @param childCrNo
	 * @param userVO
	 */
	public void updateNewnatalDeath(String deathDate, String childCrNo, UserVO userVO);

	/**
	 * Getting Child Neo Nat Detail By Pat Crno
	 * @param _ancNeonatalDtlVO
	 * @param _userVO User Detail
	 * @return 
	 */
	public ANCNeonatalDetailVO getNeoNatDtlByChild(ANCNeonatalDetailVO _ancNeonatalDtlVO, UserVO _userVO);
}
