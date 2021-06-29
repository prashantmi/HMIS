package opd.master.controller.data;

import java.util.Map;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdMasterDelegate;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DepartmentHosDisMasterVO;
import hisglobal.vo.UserVO;

/**
 * @author user
 *
 */
/**
 * @author user
 *
 */
/**
 * @author user
 *
 */
/**
 * @author user
 *
 */
/**
 * @author user
 *
 */
/**
 * @author user
 *
 */
/**
 * @author user
 *
 */
public class DepartmentHosDisMasterDATA extends ControllerDATA {
	
	/**
	 * @param _userVO
	 * @return
	 */
	public static Map getDeptHosDisEssential(UserVO _userVO)
	{
		OpdEssentialDelegate opdEssDelegate= new OpdEssentialDelegate();
		return opdEssDelegate.getDeptHosDisEssential(_userVO);
	}
	
	public static Map getDeptHosDisRemovalDetails(String _choice,String _code,UserVO _userVO)
	{
		OpdEssentialDelegate opdEssDelegate= new OpdEssentialDelegate();
		return opdEssDelegate.getDeptHosDisRemovalDetails(_choice,_code,_userVO);
	}
	
	/**
	 * @param _deptHosDisVO
	 * @param _userVO
	 * @return
	 */
	public static DepartmentHosDisMasterVO[] getDeptHosDisDetail(DepartmentHosDisMasterVO _deptHosDisVO,UserVO _userVO){
		OpdMasterDelegate opdMasterDelegate= new OpdMasterDelegate();
		return opdMasterDelegate.getDeptHosDisDetail(_deptHosDisVO,_userVO);
	}
	
	/**
	 * @param _deptHosDisVO
	 * @param _hosDisCodeType
	 * @param _userVO
	 */
	public static void saveDeptHosDisCode(DepartmentHosDisMasterVO[] _deptHosDisVO,String _hosDisCodeType,UserVO _userVO)
	{
		OpdMasterDelegate opdMasterDelegate= new OpdMasterDelegate();
		opdMasterDelegate.saveDeptHosDisCode(_deptHosDisVO,_hosDisCodeType,_userVO);
	}
	
	/**
	 * @param _deptHosDisVO
	 * @param selectedRecord
	 * @param _choice
	 * @param _userVO
	 */
	public static void deleteDeptHosDisCode(DepartmentHosDisMasterVO _deptHosDisVO,String[] selectedRecord,String _choice,UserVO _userVO)
	{
		OpdMasterDelegate opdMasterDelegate= new OpdMasterDelegate();
		opdMasterDelegate.deleteDeptHosDisCode(_deptHosDisVO,selectedRecord,_choice,_userVO);
	}

}
