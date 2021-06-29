package registration.reports.controller.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.vo.UserVO;
import registration.bo.RegMasterBO;
import vo.registration.DepartmentVO;
import vo.registration.DeptUnitRoomVO;
import vo.registration.DeptUnitRosterVO;
import vo.registration.RoomVO;
import vo.registration.RosterMasterVO;
import vo.registration.UnitVO;


/**
 * this new department wise opd statistics Data file for multiple hospitals 
 
 */


public class DeptwiseOPDStatNewReportDATA {
	
	public static List getDeptList(UserVO uservo){
		RegMasterBO bo = new RegMasterBO();
		return bo.getDeptList(uservo);	
	}
	
		
	public static List getUnitList(String strDeptCode,UserVO uservo){
		RegMasterBO bo = new RegMasterBO();
		return bo.getUnitList(strDeptCode, uservo);	
	}
	
	public static List getPatCategoryList(UserVO uservo){
		RegMasterBO bo = new RegMasterBO();
		return bo.getPatientPrimaryCategory(uservo);	
	}
	   
	public static List getHospitalList(UserVO uservo){
		RegMasterBO bo = new RegMasterBO();
		return bo.getHospitalList(uservo);	
	}	
	
	
		
	public static void save(Collection col,String unitRoomMasterCapacity,String unitCode,String roomCode, UserVO userVO) throws HisException{
		RegMasterBO bo = new RegMasterBO();
		bo.saveDeptUnitRoster(col,unitRoomMasterCapacity,unitCode,roomCode, userVO);
	}
	
	
	

}
