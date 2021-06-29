package registration.masters.controller.data;
/**
 * @author s.singaravelan 	
 * Creation Date:- 24-Jan-2014
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


public class DeptUnitRosterMstDATA {
	
	
	public List getDeptList(UserVO uservo){
		RegMasterBO bo = new RegMasterBO();
		return bo.getDeptList(uservo);	
	}
	
	public List getShiftEssentials(UserVO uservo){
		RegMasterBO bo = new RegMasterBO();
		return bo.getShiftEssentials(uservo);	
	}
	
	public List getUnitList(DeptUnitRosterVO objModelDeptUnitRoster_p,UserVO uservo){
		RegMasterBO bo = new RegMasterBO();
		return bo.getUnitList(objModelDeptUnitRoster_p.getStrDeptCode(), uservo);	
	}
	
	public List getRoomList(DeptUnitRosterVO objModelDeptUnitRoster_p,UserVO uservo){
		String deptUnitCode=objModelDeptUnitRoster_p.getStrDeptCode()+objModelDeptUnitRoster_p.getStrUnitCode();
		RegMasterBO bo = new RegMasterBO();
		return bo.getRoomList(deptUnitCode, uservo);	
	}	
	
	public static Collection getRoster4DeptUnit(RosterMasterVO rosterMasterVO, UserVO userVO) throws HisException {
		RegMasterBO bo = new RegMasterBO();
		Collection col= null;
		try{
			col=bo.getDeptUnitShiftWiseRosterDtl(rosterMasterVO, userVO);
	    }catch(HisRecordNotFoundException e){
	   			col=new ArrayList();
	   	}
	   	return col;
	}
	
	public static void save(Collection col,String unitRoomMasterCapacity,String unitCode,String roomCode, UserVO userVO) throws HisException{
		RegMasterBO bo = new RegMasterBO();
		bo.saveDeptUnitRoster(col,unitRoomMasterCapacity,unitCode,roomCode, userVO);
	}
	
	public static void updateUnitMaster(UnitVO unitMasterVO, UserVO userVO) {
		RegMasterBO bo = new RegMasterBO();
		bo.updateUnitMasterForRoster(unitMasterVO,userVO);
	}	
	
	public static int executeRosterForAll(String sysdate,UserVO userVO){
		RegMasterBO bo = new RegMasterBO();
		return bo.executeRosterForAll(sysdate,userVO);		   
	}
	
	public static void deleteRosterRecord(Collection col,String unitRoomMasterCapacity,String unitCode,String roomCode, UserVO userVO) throws HisException{
		RegMasterBO bo = new RegMasterBO();
		bo.deleteDeptUnitRoster(col,unitRoomMasterCapacity,unitCode,roomCode, userVO);
	}
	
}
