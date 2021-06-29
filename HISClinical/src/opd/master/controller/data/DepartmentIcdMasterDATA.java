package opd.master.controller.data;

import java.util.Map;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdMasterDelegate;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DepartmentIcdMasterVO;
import hisglobal.vo.UserVO;

public class DepartmentIcdMasterDATA extends ControllerDATA {
	
	public static Map getDeptIcdEssential(UserVO _userVO)
	{
		OpdEssentialDelegate opdEssDelegate= new OpdEssentialDelegate();
		return opdEssDelegate.getDeptIcdEssential(_userVO);
	}
	
	public static Map getDeptIcdRemovalDetails(String _choice,String _code,UserVO _userVO)
	{
		OpdEssentialDelegate opdEssDelegate= new OpdEssentialDelegate();
		return opdEssDelegate.getDeptIcdRemovalDetails(_choice,_code,_userVO);
	}
	
	public static DepartmentIcdMasterVO[] getDeptIcdDetail(DepartmentIcdMasterVO _deptIcdVO,UserVO _userVO){
		OpdMasterDelegate opdMasterDelegate= new OpdMasterDelegate();
		return opdMasterDelegate.getDeptIcdDetail(_deptIcdVO,_userVO);
	}
	
	public static void saveDeptIcdCode(DepartmentIcdMasterVO[] _deptIcdVO,String _icdCodeType,UserVO _userVO)
	{
		OpdMasterDelegate opdMasterDelegate= new OpdMasterDelegate();
		opdMasterDelegate.saveDeptIcdCode(_deptIcdVO,_icdCodeType,_userVO);
	}
	
	public static void deleteDeptIcdCode(DepartmentIcdMasterVO _deptIcdVO,String[] selectedRecord,String _choice,UserVO _userVO)
	{
		OpdMasterDelegate opdMasterDelegate= new OpdMasterDelegate();
		opdMasterDelegate.deleteDeptIcdCode(_deptIcdVO,selectedRecord,_choice,_userVO);
	}

}
