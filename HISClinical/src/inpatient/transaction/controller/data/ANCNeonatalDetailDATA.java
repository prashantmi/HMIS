package inpatient.transaction.controller.data;

import inpatient.transaction.delegate.InPatientEssentialDelegate;
import inpatient.transaction.delegate.InpatientDelegate;

import java.util.List;
import java.util.Map;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.ANCNeonatalApgarVO;
import hisglobal.vo.ANCNeonatalDetailVO;
import hisglobal.vo.UserVO;

public class ANCNeonatalDetailDATA extends ControllerDATA
{
	// Getting ANC Detail Essentials
	public static Map<String, Object> getEssentials(ANCNeonatalDetailVO _ancNeoNatVO, UserVO _userVO)
	{
		InPatientEssentialDelegate delegate = new InPatientEssentialDelegate();
		return delegate.getANCNeonatalDetailEssentials(_ancNeoNatVO, _userVO);
	}

	// Saving Complete ANC New Natal Detail & Apgar Detail
	public static void saveANCNewNatalDetail(ANCNeonatalDetailVO _ancNewNatalVO, List<ANCNeonatalApgarVO> _lstApgars,
			UserVO _userVO)
	{
		InpatientDelegate delegate = new InpatientDelegate();
		delegate.saveANCNewNatalDetail(_ancNewNatalVO, _lstApgars, _userVO);
	}
}
