package opd.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

import java.util.Map;

import opd.bo.OpdEssentialBO;

public class OpdBayDeskDashboardDATA extends ControllerDATA 
{

	public Map<String, Object> getDailyPatientDetail(PatientDetailVO patientDetailVO, UserVO userVO) 
	{
		OpdEssentialBO opdEssentialBO = new OpdEssentialBO();
		return opdEssentialBO.getDailyPatientDetail(patientDetailVO, userVO);
	}
	
	public Map<String, Object> getDeskPatDtl(PatientDetailVO patientDetailVO,UserVO userVO, String deskType) 
	{
		OpdEssentialBO opdEssentialBO = new OpdEssentialBO();
		return opdEssentialBO.getDeskPatDtl(patientDetailVO, userVO, deskType);
	}

}
