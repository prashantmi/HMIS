package opd.master.dao;

import hisglobal.utility.Entry;

//import hisglobal.vo.IcdCrossRefMasterVO;
//import hisglobal.vo.IcdIndexLevelMasterVO;
import hisglobal.vo.IcdCrossRefMasterVO;
import hisglobal.vo.IcdIndexLevelMasterVO;
import hisglobal.vo.UserVO;

import java.util.List;

public interface IIcdCrossRefMasterDAOi
{
	/*
	 * Getting the List of all Index Terms
	 * 
	 * @param _userVO User Detail
	 */
	public List<Entry> getIndexTermCombo(UserVO _userVO);

	/*
	 * Getting the List of all Modifier
	 * 
	 * @param _userVO User Detail
	 */

	public List<Entry> getModifier(String strIndex, UserVO _userVO);

	public List<IcdIndexLevelMasterVO> getSeeTerms(String strIndex, UserVO _userVO);

	/*
	 * Getting the List of all Modifier at level 2
	 * 
	 * @param _userVO User Detail
	 */
	public List<Entry> getInitializeSubModifierNext(String strModifierID, String level, UserVO _userVO);

	public List<IcdIndexLevelMasterVO> getSeeTermsForModi(String strModId, UserVO _userVO);

	// To save Data on Add Page
	public void saveIcdCrossRefMaster(IcdCrossRefMasterVO vo, UserVO userVO);
}
