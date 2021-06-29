package opd.master.controller.data;

/**
 * @author  CADC
 */

import java.util.List;
import java.util.Map;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdMasterDelegate;

import hisglobal.vo.UnitEpisodeKeywordVO;
import hisglobal.vo.UserVO;

public class UnitEpisodeKeywordDATA
{
	// Getting Unit Keyword ADD Essentials
	public static Map getEssentials(UserVO _UserVO)
	{
		OpdEssentialDelegate essentialDelegate = new OpdEssentialDelegate();
		return essentialDelegate.getUnitEpisodeKeywordEssentials(_UserVO);
	}

	// Saving Keyword Unit Record Unit-Wise
	public static void AddKeywordUnitWise(List<UnitEpisodeKeywordVO> _lstUnitKeywords, UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		masterDelegate.saveUnitWiseKeyword(_lstUnitKeywords, _UserVO);
	}

	// Getting Units Detail by DeptUnitCode
	public static String getUnitName(String _deptUnitCode, UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return (masterDelegate.getUnitName(_deptUnitCode, _UserVO));
	}

	// Getting Unit Episode Keyword MODIFY Essentails
	public static Map getModifyUnitEpisodeKeywordEssentials(String _deptUnitCode, UserVO _userVO)
	{
		OpdEssentialDelegate essentialDelegate = new OpdEssentialDelegate();
		return essentialDelegate.getModifyUnitEpisodeKeywordEssentials(_deptUnitCode, _userVO);
	}

	// Modifying Unit Episode Keywords
	public static void modifyUnitEpisodeKeywords(List<UnitEpisodeKeywordVO> _lstUnitKeywords, String _deptUnitCode, UserVO _userVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		masterDelegate.modifyUnitEpisodeKeywords(_lstUnitKeywords, _deptUnitCode, _userVO);
	}

	// Getting Unit Episode Keyword VIEW Essentails
	public static Map getViewUnitEpisodeKeywordEssentials(String _deptUnitCode, UserVO _userVO)
	{
		OpdEssentialDelegate essentialDelegate = new OpdEssentialDelegate();
		return essentialDelegate.getViewUnitEpisodeKeywordEssentials(_deptUnitCode, _userVO);
	}
}
