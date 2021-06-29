package registration.reports.controller.data;
/**
 * @author s.singaravelan 	
 * Creation Date:- 25-Mar-2014
 * Modifying Date:- 
 * Used For:-	
 * Team Lead By:- 
 * Module:- Registration(HIS Project)
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

public class MctsPatientlistingReportDATA {
	
	public static void save(Collection col,String unitRoomMasterCapacity,String unitCode,String roomCode, UserVO userVO) throws HisException{
		RegMasterBO bo = new RegMasterBO();
		bo.saveDeptUnitRoster(col,unitRoomMasterCapacity,unitCode,roomCode, userVO);
	}
	
}
