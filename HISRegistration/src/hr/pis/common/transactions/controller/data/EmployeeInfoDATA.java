/* Module Name: Common List Page
*  Name of Process: List Generation 
*  Name of Developer: Sh. Ashwini Mishra
*  Date of Creation: 26-Mar-2015
*/

package hr.pis.common.transactions.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;
import hr.pis.common.bo.transactions.EmployeeInfoBO;

import java.util.List;
import java.util.Map;

import vo.hr.pis.common.transactions.EmployeeInfoVO;

public class EmployeeInfoDATA extends ControllerDATA {

	
	
	public static Map<String, Object> getEmpList_AJAX(UserVO _UserVO, EmployeeInfoVO voObj,  int p_page, int p_limit, String p_sidx, String p_sord, String p_where)
	{
		EmployeeInfoBO  essentialBO=new EmployeeInfoBO();
		return essentialBO.getEmpList_AJAX(_UserVO, voObj, p_page, p_limit, p_sidx, p_sord, p_where);
	}
	
	
	public static List getEmpData(UserVO p_UserVO, EmployeeInfoVO voObj ) {
		EmployeeInfoBO  essentialBO=new EmployeeInfoBO();
		return essentialBO.getEmpData(p_UserVO, voObj);
	}
	
	public static List<List<String>> findEmp(UserVO _userVO, EmployeeInfoVO voObj,String _searchStr)
	{
		List<List<String>> alRecord = null;
		EmployeeInfoBO  boObj=new EmployeeInfoBO();
		alRecord=boObj.findEmp(_userVO, voObj, _searchStr);
		return alRecord;		
	}
	
}
