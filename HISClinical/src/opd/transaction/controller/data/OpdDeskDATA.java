package opd.transaction.controller.data;

import java.util.Map;

import opd.bo.OpdEssentialBO;
import opd.bo.delegate.OpdEssentialDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.vo.UserDeskMenuMasterVO;
import hisglobal.vo.UserVO;

public class OpdDeskDATA extends ControllerDATA {

	public StringBuffer getPatientCount(UserDeskMenuMasterVO userDeskVO, UserVO userVO) {
		OpdEssentialBO opdEssentialBO = new OpdEssentialBO();
		return opdEssentialBO.getPatientCount(userDeskVO,userVO);
	}
	
	public static Map<String, Object> AJX_G_PATIENTS_COUNT_NEW(UserVO _UserVO, int p_page, int p_limit, String p_sidx, String p_sord, String p_where)
	{
		OpdEssentialBO opdEssentialBO = new OpdEssentialBO();
		return opdEssentialBO.AJX_G_PATIENTS_COUNT_NEW(_UserVO, p_page, p_limit, p_sidx, p_sord, p_where);
		
	}
}
