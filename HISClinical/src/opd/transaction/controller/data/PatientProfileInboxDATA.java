package opd.transaction.controller.data;

import java.util.List;

import opd.bo.delegate.OpdPatientDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.PatientProfileDetailVO;
import hisglobal.vo.UserVO;

public class PatientProfileInboxDATA extends ControllerDATA
{
	// Getting Patient Episode Profiles List For Inbox
	public static List<PatientProfileDetailVO> getPatientProfilesForInbox(String _patCrNo, String _unitCode, UserVO _userVO)
	{
		OpdPatientDelegate delegate = new OpdPatientDelegate();
		return delegate.getPatientProfilesForAll(_patCrNo, _unitCode, _userVO);
	}
}
