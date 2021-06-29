/**
 * 
 */
package opd.master.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DepartmentHosDiseaseMstVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdMasterDelegate;

/**
 * @author ashas
 *
 */
public class DeptUnitHospitalDiseaseDATA extends ControllerDATA{
	
	// for getting Dept Unit Essential
	public static Map getDeptUnitEssential(UserVO _userVO)
	{
		OpdEssentialDelegate delegate=new OpdEssentialDelegate();
		return delegate.getDeptUnitEssential(_userVO);
	}
	
	// for saving dept unit icd mapping
	
	public static void saveDeptUnitHosDisease(List _departmentHosDisVOLst, UserVO _userVO) {
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.saveDeptUnitHosDisease(_departmentHosDisVOLst,_userVO);
		
	}
	
	
	// for getting modified details of Dept Unit Hospital disease Mapping
	public static Map getUnitWiseMappedDiseasForModify(DepartmentHosDiseaseMstVO _vo, UserVO _userVO) {
		OpdMasterDelegate delegate=new OpdMasterDelegate();
		return delegate.getUnitWiseMappedHosDiseasForModify(_vo,_userVO);
	}
	
	
	// for saving modified details
	public static void modifySaveDeptUnitHosDiseaseMapping(List _departmentHosDiseaseMstVO, UserVO _userVO) {
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.modifySaveDeptUnitHosDiseaseMapping(_departmentHosDiseaseMstVO,_userVO);
		
	}



}
