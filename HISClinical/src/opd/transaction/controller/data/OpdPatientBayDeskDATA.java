package opd.transaction.controller.data;


import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import opd.bo.OpdEssentialBO;
import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdPatientDelegate;

public class OpdPatientBayDeskDATA extends ControllerDATA
{
	// *
	public static PatientDetailVO[] getTodayPatientList(UserVO _UserVO, String unitCode, String roomCode)
	{
		OpdEssentialDelegate opdessentialDelegate = new OpdEssentialDelegate();
		PatientDetailVO[] dailyPatientVOs = opdessentialDelegate.getTodayPatientList(_UserVO, unitCode, roomCode);
		return dailyPatientVOs;
	}

	// *
	public static void saveOpdPatientEpisode(String patCrNo, String SerialNo, String visitNo, String episodeCode,
			String isConfirmed, String episodeIsOpen, String nextVisitDate, UserVO _userVO)
	{
		OpdPatientDelegate opdPatientDelegate = new OpdPatientDelegate();
		opdPatientDelegate.saveOpdPatientEpisode(patCrNo, SerialNo, visitNo, episodeCode, isConfirmed, episodeIsOpen,
				nextVisitDate, _userVO);

	}

	public static List getOpdDeskEssentials(UserVO userVO) {
		OpdEssentialDelegate opdEssentialDelegate = new OpdEssentialDelegate();
		List list = opdEssentialDelegate.opdDeskEssentials(userVO);
		return list;
	}

	// *
	public static PatientDetailVO[] getTodayAllPatientList(UserVO _UserVO, String unitCode, String roomCode)
	{
		System.out.println("OpdPatientDeskDATA.getTodayAllPatientList()");
		OpdEssentialBO opdEssentialBO = new OpdEssentialBO();
		PatientDetailVO[] dailyPatientVOs = opdEssentialBO.getTodayAllPatientList(_UserVO, unitCode, roomCode);
		return dailyPatientVOs;
	}

	public static Map<String, Object> getTodayAllPatientList_AJAX(UserVO _UserVO, int p_page, int p_limit, String p_sidx, String p_sord, String p_where)
	{
		OpdEssentialBO opdEssentialBO = new OpdEssentialBO();
		return opdEssentialBO.getTodayAllPatientList_AJAX(_UserVO, p_page, p_limit, p_sidx, p_sord, p_where);
		
	}
}