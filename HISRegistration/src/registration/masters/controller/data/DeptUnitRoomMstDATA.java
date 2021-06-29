package registration.masters.controller.data;
/**
 * @author s.singaravelan 	
 * Creation Date:- 09-Jan-2014
 * Modifying Date:- 
 * Used For:-	
 * Team Lead By:- 
 * Module:- Registration(HIS Project)
 */

import java.util.List;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.vo.UserVO;
import registration.bo.RegMasterBO;
import vo.registration.DepartmentVO;
import vo.registration.DeptUnitRoomVO;
import vo.registration.RoomVO;
import vo.registration.UnitVO;


public class DeptUnitRoomMstDATA {
	
		
	public List getRoomsList(DeptUnitRoomVO objModelDeptUnitRoom_p,UserVO uservo){
		RegMasterBO bo = new RegMasterBO();
		return bo.getRoomsList(objModelDeptUnitRoom_p, uservo);	
	}	
	
	public RoomVO getRoomDtl(RoomVO objModelRoom_p){
		RegMasterBO bo = new RegMasterBO();
		return bo.modifyRecordRoomMst(objModelRoom_p);	
	}
		
	public DepartmentVO getDeptDtl(DepartmentVO deptModel){
		RegMasterBO bo = new RegMasterBO();
		return bo.modifyRecordDepartmentMst(deptModel);		
	}
	
	public UnitVO getUnitDtl(UnitVO unitModel){
		RegMasterBO bo = new RegMasterBO();
		return bo.modifyRecordUnitMst(unitModel);		
	}	
	
	public static Boolean saveDeptUnitRoomDtl(DeptUnitRoomVO[] objModelDeptUnitRoom_p,String strMode_p,UserVO userVo) {
		RegMasterBO bo = new RegMasterBO();
		return bo.saveDeptUnitRoomDtl(objModelDeptUnitRoom_p, strMode_p, userVo);
	}
	
	public static DeptUnitRoomVO[] getAllotedRoomsToUnit(String _deptUnitCode,UserVO _userVO) {
		RegMasterBO bo = new RegMasterBO();
		return bo.getAllotedRoomsToUnitsWithSequence(_deptUnitCode,_userVO);	
	}
		
	public Boolean updateDeptUnitRoomDtl(DeptUnitRoomVO objModelDoc_p,String strMode_p,UserVO userVo) {
		RegMasterBO bo = new RegMasterBO();
		return bo.updateDeptUnitRoomDtl(objModelDoc_p, strMode_p, userVo);
	}
	
	public DeptUnitRoomVO modifyRecordDepartmentMst(DeptUnitRoomVO vo) {
		DeptUnitRoomVO DeptUnitRoomVO_p = null;
		RegMasterBO bo = new RegMasterBO();
		try {
			DeptUnitRoomVO_p = bo.modifyRecordDeptUnitRoomMst(vo);			
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		} finally {
		}
		return DeptUnitRoomVO_p;
	}
	
	
}
