package opd.master.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DepartmentIcdMasterVO;
import hisglobal.vo.IcdDiseaseMasterVO;
import hisglobal.vo.IcdSubgroupMasterVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdMasterDelegate;

public class DeptUnitIcdMappingDATA extends ControllerDATA
{	
	// for getting Dept Unit Essential
	public static Map getUnitListEssential(UserVO _userVO)
	{
		OpdEssentialDelegate delegate=new OpdEssentialDelegate();
		return delegate.getUnitListEssential(_userVO);
	}
	//for geting essential field after click on NEXT
	public static Map getEssential(UserVO _userVO)
	{
		OpdEssentialDelegate delegate=new OpdEssentialDelegate();
		return delegate.getEssential(_userVO);
	}
	// for getting subgroup
	public static List<IcdSubgroupMasterVO> getSubGroupsByGroup(String _icdGroupCode, UserVO _userVO)
	{
		OpdEssentialDelegate delegate = new OpdEssentialDelegate();
		return delegate.getSubGroupsByGroup(_icdGroupCode, _userVO);
	}
//	public static Map getIcdDiseaseList(UserVO _userVO)
//	{
//		OpdEssentialDelegate delegate=new OpdEssentialDelegate();
//		return delegate.getIcdDiseaseList(_userVO);
//	}
	// for getting disease list
	public static List<IcdDiseaseMasterVO> getDiseaseBySubGroup(String _icdSubgroupCode, UserVO _userVO)
	{
		OpdEssentialDelegate delegate = new OpdEssentialDelegate();
		return delegate.getDiseaseBySubGroup(_icdSubgroupCode, _userVO);
	}
	public static Map getModDisease(String _deptCode,String _icdSubgroupCode, UserVO _userVO)
	{
		OpdMasterDelegate delegate = new OpdMasterDelegate();
		return delegate.getModDisease(_deptCode,_icdSubgroupCode, _userVO);
	}
	// for saving dept unit icd mapping
	public static void saveDeptUnitIcdMapping(List _departmentIcdMasterVOLst, UserVO _userVO) {
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.saveDeptUnitIcdMapping(_departmentIcdMasterVOLst,_userVO);
		
	}
	// for getting modified details of Dept Unit Icd Mapping
	public static Map getDeptUnitIcdForModify(DepartmentIcdMasterVO _vo, UserVO _userVO) {
		OpdMasterDelegate delegate=new OpdMasterDelegate();
		return delegate.getDeptUnitIcdForModify(_vo,_userVO);
	}
	// for saving modified details
	public static void modifySaveDeptIcdMapping(List _departmentIcdMasterVOLst, UserVO _userVO) {
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.modifySaveDeptIcdMapping(_departmentIcdMasterVOLst,_userVO);
		
	}

}
