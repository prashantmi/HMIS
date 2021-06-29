/**
 * 
 */
package opd.dao;

import hisglobal.vo.DepartmentHosDiseaseMstVO;
import hisglobal.vo.UserVO;

import java.util.List;

/**
 * @author ashas
 *
 */
public interface DepartmentUnitHosDiseaseMstDAOi {
	
	public void create(DepartmentHosDiseaseMstVO _departmentHosDisVOLst,UserVO _userVO);
	public DepartmentHosDiseaseMstVO[] getDeptUnitHosDiseaseForModify(String _deptUnitCode, UserVO _userVO);
	public List getNotMappedHosDisease(String _deptCode,UserVO _userVO);
	public void update(String _deptUnitCode,UserVO _userVO);
	

}
