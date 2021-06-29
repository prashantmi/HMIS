package opd.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserDeskMenuMasterVO;
import hisglobal.vo.UserVO;
import opd.bo.OpdEssentialBO;

public class OpdBayDeskDATA extends ControllerDATA {

	public StringBuffer getOpdPatientCount(UserDeskMenuMasterVO userDeskVO, UserVO userVO) {
		OpdEssentialBO opdEssentialBO = new OpdEssentialBO();
		return opdEssentialBO.getSingleDeptPatientCount(userDeskVO,userVO);
	}
}
