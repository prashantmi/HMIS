package opd.transaction.controller.data;

import hisglobal.vo.PatientProfileDetailVO;
import hisglobal.vo.UserVO;

import java.util.List;

import opd.bo.delegate.OpdPatientDelegate;

public class PatientProfileAllDATA {

	public static List<PatientProfileDetailVO> getPatientProfilesForAll(String _patCrNo, String _unitCode, UserVO _userVO)
	{
		OpdPatientDelegate delegate = new OpdPatientDelegate();
		return delegate.getPatientProfilesForAll(_patCrNo, _unitCode, _userVO);
	}

}
