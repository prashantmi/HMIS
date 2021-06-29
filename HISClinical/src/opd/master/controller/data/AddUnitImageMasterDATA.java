package opd.master.controller.data;

/**
 * @author  CADC
 */

import java.util.List;
import java.util.Map;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdMasterDelegate;
import hisglobal.vo.OPDUnitImageMasterVO;
import hisglobal.vo.UserVO;

public class AddUnitImageMasterDATA
{
	// Getting Unit Image Master ADD Essentials
	public static Map getEssentials(UserVO _UserVO)
	{
		OpdEssentialDelegate essentialDelegate = new OpdEssentialDelegate();
		return essentialDelegate.getAddUnitImageMasterEssentials(_UserVO);
	}

	// Saving Image Unit Record Unit-Wise
	public static void AddImageUnitWise(List<OPDUnitImageMasterVO> _lstUnitImages, UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		masterDelegate.saveImageUnit(_lstUnitImages, _UserVO);
	}

	// Getting Unit Image Master MODIFY Essentials
	public static Map getModifyUnitImageMasterEssentials(String _deptUnitCode, UserVO _userVO)
	{
		OpdEssentialDelegate essentialDelegate = new OpdEssentialDelegate();
		return essentialDelegate.getModifyUnitImageMasterEssentials(_deptUnitCode,_userVO);
	}

	
	public static void ModifyAddImageUnitWise(List<OPDUnitImageMasterVO> _lstUnitImages, String _deptUnitCode, UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		masterDelegate.ModifysaveImageUnit(_lstUnitImages, _deptUnitCode, _UserVO);
	}
	
	//* Getting Units Detail by DeptUnitId
	public static String getUnitName(String _deptUnitCode,UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		String unitName = (masterDelegate.getUnitName(_deptUnitCode, _UserVO));
		return unitName;
	}


	//* Getting Image for View
	public static OPDUnitImageMasterVO[] getImageForView(String deptUnitCode, UserVO userVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		OPDUnitImageMasterVO[] opdUnitImageMasterVO = masterDelegate.getImageForView(deptUnitCode, userVO);
		return opdUnitImageMasterVO;
	}

}
