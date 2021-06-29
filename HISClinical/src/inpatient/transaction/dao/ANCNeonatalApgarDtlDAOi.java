package inpatient.transaction.dao;

import java.util.List;

import hisglobal.vo.ANCNeonatalApgarVO;
import hisglobal.vo.UserVO;

public interface ANCNeonatalApgarDtlDAOi 
{
	/**
	 * Create Apgar Detail Record
	 * @param _ancNeonatalApgarDtlVO Apgar Detail
	 * @param _userVO User Detail
	 */
	public void create(ANCNeonatalApgarVO _ancNeonatalApgarDtlVO, UserVO _userVO);

	/**
	 * Getting List of All Apgar Details of Given Mother Gravida 
	 * @param _ancNeonatalApgarDtlVO Neo Nat Detail
	 * @param _userVO User Detail
	 * @return List of Apgar Scores
	 */
	public List<ANCNeonatalApgarVO> getAllNeoNatApgarDtl(ANCNeonatalApgarVO _ancNeonatalApgarDtlVO, UserVO _userVO);
}
