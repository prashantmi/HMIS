package opd.reports.controller.data;

import java.util.List;
import java.util.Map;

import opd.bo.delegate.OpdEssentialDelegate;

import hisglobal.presentation.ReportDATA;
import hisglobal.vo.UserVO;
import registration.bo.delegate.EssentialDelegate;

public class OpdDeptUnitUserWiseStatDATA extends ReportDATA
{
	public static List getUser(UserVO _userVO)
	{
		return new EssentialDelegate().getOpdUser(_userVO); 
	}
	public static List getAllGroupList(UserVO userVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		return essentialDelegate.getAllGroupList(userVO); 
	}
	public static Map getDepartment(UserVO _userVO)
	{
		return new EssentialDelegate().getDepartmentWiseTotalPatReportEssentials(_userVO);
	}
	
	public static List getUnitBasedOnDepartment(String _deptCode,UserVO _userVO)
	{
		return new EssentialDelegate().getUnitBasedOnDept(_deptCode,_userVO);
	}
	
	public static List getUserBasedOnGroup(String groupCode,UserVO _userVO)
	{
		return new OpdEssentialDelegate().getUserBasedOnGroup(groupCode,_userVO);
	}
}
