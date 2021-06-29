package registration.masters.controller.data;

/**
 * @author s.singaravelan 	
 * Creation Date:- 22-Jan-2014
 * Modifying Date:- 
 * Used For:-	
 * Team Lead By:- 
 * Module:- Registration(HIS Project)
 */

import java.util.List;
import java.util.Map;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.vo.UserVO;
import registration.bo.RegMasterBO;
import vo.registration.ExtInstituteVO;
import vo.registration.ShiftVO;
import vo.registration.OccupationVO;

public class ExtInstituteMstDATA {
	
	public List getStateList(UserVO uservo) {
		RegMasterBO bo = new RegMasterBO();
		return bo.getStateList(uservo);
	}

	public Boolean saveExtInstituteDtl(ExtInstituteVO objModelExt_p,
			String strMode_p, UserVO userVo) {
		RegMasterBO bo = new RegMasterBO();
		return bo.saveExtInstituteDtl(objModelExt_p, strMode_p, userVo);
	}	

	public Boolean updateExtInstituteDtl(ExtInstituteVO objModelExt_p,
			String strMode_p, UserVO userVo) {
		RegMasterBO bo = new RegMasterBO();
		return bo.updateExtInstituteDtl(objModelExt_p, strMode_p, userVo);
	}

	public ExtInstituteVO modifyRecordExtInstituteMst(ExtInstituteVO vo) {
		ExtInstituteVO ExtInstituteVO_p = null;
		RegMasterBO bo = new RegMasterBO();
		try {
			ExtInstituteVO_p = bo.modifyRecordExtInstituteMst(vo);

			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("RegMasterBO.modifyRecord(vo) --> "
						+ vo.getStrMsgString());
			}
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
		return ExtInstituteVO_p;
	}

}
