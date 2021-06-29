package registration.master.controller.data;

import hisglobal.presentation.ControllerDATA;
import java.util.Date;
import java.util.List;
import java.util.Map;
//import org.apache.tools.ant.taskdefs.condition.Http;
import registration.bo.delegate.EssentialDelegate;
import registration.master.bo.delegate.MasterDelegate;
import hisglobal.vo.CityLocationMasterVO;
import hisglobal.vo.DepartmentMasterVO;
import hisglobal.vo.EmployeeMasterVO;
import hisglobal.vo.RoomMasterVO;
import hisglobal.vo.UserVO;

/**
 * Project   : MHIMS
 * Module 	 : Registration
 * Developer : Singaravelan
 * Creation Date : 25-Jul-2014
 * Process Name : Employee Master
 * 
 */

public class EmployeeMstDATA extends ControllerDATA{
	
	public static Map getEssentialForEmployee(UserVO _userVO)
	{
		return new EssentialDelegate().getEssentialForEmployee(_userVO);
	}
	public static void saveEmployeeDetail(EmployeeMasterVO _empMasterVO,UserVO _userVO)	
	{
		new MasterDelegate().saveEmployeeDetail(_empMasterVO,_userVO);
	}
	public static Map  getEmployeeMasterDetails(String _empCode,UserVO _userVO)
	{
		return new MasterDelegate().getEmployeeMasterDetails(_empCode,_userVO);	
	}
	public static void updateEmployeeMaster(EmployeeMasterVO _empMasterVO, UserVO _userVO){
		new MasterDelegate().updateEmployeeMaster(_empMasterVO,_userVO);	
	}

}
