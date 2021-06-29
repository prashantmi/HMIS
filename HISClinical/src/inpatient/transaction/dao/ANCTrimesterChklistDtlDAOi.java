package inpatient.transaction.dao;

import java.util.List;

import hisglobal.vo.ANCChecklistDetailVO;
import hisglobal.vo.UserVO;

public interface ANCTrimesterChklistDtlDAOi 
{
	/**
	 * Insert ANC Trimester Drug Checklist Detail
	 * @param _ancChklistDtlVO ANC CheckList Detail VO
	 * @param _userVO User Detail
	 */
	public void createDrugChklst(ANCChecklistDetailVO _ancChklistDtlVO, UserVO _userVO);

	/**
	 * Insert ANC Trimester Investigation Checklist Detail
	 * @param _ancChklistDtlVO ANC CheckList Detail VO
	 * @param _userVO User Detail
	 */
	public void createInvstChklst(ANCChecklistDetailVO _ancChklistDtlVO, UserVO _userVO);

	/**
	 * Getting Checklist Type Wise ANC Checklist Detail
	 * @param _ancChklistDtlVO ANC CheckList Detail VO
	 * @param _type Checklist Type
	 * @param _userVO User Detail
	 * @return List of Chechlist Detail
	 */
	public List<ANCChecklistDetailVO> getChecklistDtlTypeWise(ANCChecklistDetailVO _ancChklistDtlVO, String _type, UserVO _userVO);
}
