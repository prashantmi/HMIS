package registration.masters.controller.data;
/**
 * @author s.singaravelan 	
 * Creation Date:- 16-Jan-2014
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
import vo.registration.RoomVO;
import vo.registration.UnitConsultantVO;
import vo.registration.UnitVO;


public class UnitConsultantMstDATA {
	
		
	public List getConsultantList(UnitConsultantVO objModelUnitCon_p,UserVO uservo){
		RegMasterBO bo = new RegMasterBO();
		return bo.getConsultantList(objModelUnitCon_p, uservo);	
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
	
	public static Boolean saveUnitConsultantDtl(UnitConsultantVO[] objModelUnitConsultant_p,String strMode_p,UserVO userVo) {
		RegMasterBO bo = new RegMasterBO();
		return bo.saveUnitConsultantDtl(objModelUnitConsultant_p, strMode_p, userVo);
	}
	
	public static UnitConsultantVO[] getAllotedConsultantsToUnit(String _deptUnitCode,UserVO _userVO) {
		RegMasterBO bo = new RegMasterBO();
		return bo.getAllotedConsultantsToUnits(_deptUnitCode,_userVO);	
	}
		
	public Boolean updateUnitConsultantDtl(UnitConsultantVO[] objModelDoc_p,String strMode_p,UserVO userVo) {
		RegMasterBO bo = new RegMasterBO();
		return bo.updateUnitConsultantDtl(objModelDoc_p, strMode_p, userVo);
	}
	
	public UnitConsultantVO modifyRecordUnitConsultantMst(UnitConsultantVO vo) {
		UnitConsultantVO UnitConsultantVO_p = null;
		RegMasterBO bo = new RegMasterBO();
		try {
			UnitConsultantVO_p = bo.modifyRecordUnitConsultantMst(vo);			
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
		return UnitConsultantVO_p;
	}
	
	
}
