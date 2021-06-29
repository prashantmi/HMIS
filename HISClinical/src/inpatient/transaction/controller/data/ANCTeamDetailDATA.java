package inpatient.transaction.controller.data;

import inpatient.transaction.delegate.InPatientEssentialDelegate;

import java.util.Map;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;

public class ANCTeamDetailDATA extends ControllerDATA
{
	public static Map getANCTeamDtlEssential(UserVO _userVO)
	{
		InPatientEssentialDelegate delegate = new InPatientEssentialDelegate();
		return delegate.getANCTeamDtlEssential(_userVO);
	}
}
