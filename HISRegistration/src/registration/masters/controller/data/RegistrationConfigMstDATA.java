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
import vo.registration.RegistrationConfigMstVO;
//import vo.registration.ShiftVO;
//import vo.registration.OccupationVO;
import vo.registration.RenewalConfigVO;

public class RegistrationConfigMstDATA {
	
	public void saveRegistrationConfigDtl(List<RegistrationConfigMstVO> lstRegistrationConfigVO_p,String strMode_p,UserVO userVo) {
		RegMasterBO bo = new RegMasterBO();
		 bo.saveRegistrationConfigDtl(lstRegistrationConfigVO_p,strMode_p,userVo);
	}
	public RegistrationConfigMstVO[] getRegistrationConfigDtl( String strMode_p,UserVO userVo) {
		RegMasterBO bo = new RegMasterBO();
		return bo.getRegistrationConfigDtl(userVo);
	}
	public Boolean updateRegistrationConfigDtl(List<RegistrationConfigMstVO> objModel_p,
			String strMode_p, UserVO userVo) {
		RegMasterBO bo = new RegMasterBO();
		return bo.updateRegistrationConfigDtl(objModel_p, strMode_p, userVo);
	}

	public RegistrationConfigMstVO[] fetchRecordRegistrationConfigMst(RegistrationConfigMstVO vo) {
		RegistrationConfigMstVO[] RegistrationConfigVO_p = null;
		RegMasterBO bo = new RegMasterBO();
		try {
			RegistrationConfigVO_p = bo.fetchRecordRegistrationConfigMst(vo);

			if (((RegistrationConfigMstVO) vo).getStrMsgType().equals("1")) {
				((RegistrationConfigMstVO) vo).setStrMsgString("RegMasterBO.modifyRecord(vo) --> "
						+ ((RegistrationConfigMstVO) vo).getStrMsgString());
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
		return RegistrationConfigVO_p;
	}
}