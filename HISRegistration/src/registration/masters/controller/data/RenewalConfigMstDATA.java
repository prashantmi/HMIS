package registration.masters.controller.data;
/**
 * Created By 	: Aadil Wasi
 * Date			: Dec 2013
 */
import java.util.List;

import hisglobal.vo.UserVO;
import registration.bo.RegMasterBO;
import vo.registration.RenewalConfigVO;


public class RenewalConfigMstDATA {


	public void saveRenewalConfigDtl(List<RenewalConfigVO> lstRenewalConfigVO_p,String strMode_p,UserVO userVo) {
		RegMasterBO bo = new RegMasterBO();
		 bo.saveRenewalConfigDtl(lstRenewalConfigVO_p,strMode_p,userVo);
	}
	public RenewalConfigVO[] getRenewalConfigDtl( String strMode_p,UserVO userVo) {
		RegMasterBO bo = new RegMasterBO();
		return bo.getRenewalConfigDtl(userVo);
	}
	public List getGlobalPatCategory(UserVO userVo){
		RegMasterBO bo = new RegMasterBO();
		return bo.getGlobalPatCategory(userVo,"2");
	}
}
