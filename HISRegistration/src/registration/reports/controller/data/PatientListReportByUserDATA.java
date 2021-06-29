package registration.reports.controller.data;
/**
 * @author Raj Kumar 	
 * Creation Date:- 29-Oct-2018
 * Modifying Date:- 
 * Used For:-	
 * Team Lead By:- Garima Gotra
 * Module:- Registration(HIS Project-AIIMS PATNA)
 */

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

import hisglobal.exceptions.HisException;
import hisglobal.vo.UserVO;

import java.util.Collection;
import java.util.List;

import registration.bo.RegMasterBO;

public class PatientListReportByUserDATA {
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
	public static List getUsersList(UserVO uservo)
	{
		RegMasterBO bo = new RegMasterBO();
		return bo.getAllUsers(uservo);	
	}
	
		
	public static void save(Collection col,String unitRoomMasterCapacity,String unitCode,String roomCode, UserVO userVO) throws HisException{
		RegMasterBO bo = new RegMasterBO();
		bo.saveDeptUnitRoster(col,unitRoomMasterCapacity,unitCode,roomCode, userVO);
	}


	/*public List getSeatId(UserVO uservo) {
		RegMasterBO bo = new RegMasterBO();
		return bo.getSeatId(uservo);	
	}*/
	
}
