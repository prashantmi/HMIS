package inpatient.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.ANCChildHandoverDetailVO;
import hisglobal.vo.UserVO;
import inpatient.transaction.delegate.InPatientEssentialDelegate;
import inpatient.transaction.delegate.InpatientDelegate;

import java.util.Map;

public class ANCChildHandoverDetailDATA extends ControllerDATA
{
	// Getting ANC Child Handover Detail Essentials
	public static Map<String, Object> getEssentials(ANCChildHandoverDetailVO _ancChildHandoverVO, UserVO _userVO)
	{
		InPatientEssentialDelegate delegate = new InPatientEssentialDelegate();
		return delegate.getANCChildHandoverEssentials(_ancChildHandoverVO, _userVO);
	}

	// Saving ANC Child Handover Detail
	public static void saveANCChildHandoverDetail(ANCChildHandoverDetailVO _ancChildHandoverVO, UserVO _userVO)
	{
		InpatientDelegate delegate = new InpatientDelegate();
		delegate.saveANCChildHandoverDetail(_ancChildHandoverVO, _userVO);
	}
}
