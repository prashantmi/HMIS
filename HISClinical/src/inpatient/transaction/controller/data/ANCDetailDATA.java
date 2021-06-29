package inpatient.transaction.controller.data;

import inpatient.transaction.delegate.InPatientEssentialDelegate;
import inpatient.transaction.delegate.InpatientDelegate;

import java.util.List;
import java.util.Map;

import hisglobal.presentation.ControllerDATA;
import hisglobal.utility.Entry;
import hisglobal.vo.ANCDetailVO;
import hisglobal.vo.ANCHistoryDeliveryDetailVO;
import hisglobal.vo.ANCHistoryDetailVO;
import hisglobal.vo.ANCVisitDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

public class ANCDetailDATA extends ControllerDATA
{
	// Getting ANC Detail Essentials
	public static Map<String, Object> getEssentials(UserVO _userVO)
	{
		InPatientEssentialDelegate delegate = new InPatientEssentialDelegate();
		return delegate.getANCDetailEssentials(_userVO);
	}

	// Getting ANC Details
	public static Map getANCDetails(ANCDetailVO _ancDetailVO, UserVO _userVO)
	{
		InpatientDelegate delegate = new InpatientDelegate();
		return delegate.getANCDetails(_ancDetailVO, _userVO);
	}

	// Getting ANC Detail
	public static List<ANCHistoryDetailVO> getANCHistoryDetail(String _patCrNo, UserVO _userVO)
	{
		InpatientDelegate delegate = new InpatientDelegate();
		return delegate.getANCHistoryDetail(_patCrNo, _userVO);
	}

	// Getting ANC Detail Macros
	public static List<Entry> getANCMacroDetail(String _processId, UserVO _userVO)
	{
		InPatientEssentialDelegate delegate = new InPatientEssentialDelegate();
		return delegate.getANCMacroDetail(_processId, _userVO);
	}

	// Saving Complete ANC Detail
	public static void saveCompleteANCDetail(String _ancDtlFlag, PatientDetailVO _patDtlVO, ANCDetailVO _ancDetailVO,
			ANCVisitDetailVO _ancVisitDtlVO, List<ANCHistoryDetailVO> _lstHistVOs, List<ANCHistoryDeliveryDetailVO> _lstHistChildVOs, UserVO _userVO)
	{
		InpatientDelegate delegate = new InpatientDelegate();
		delegate.saveCompleteANCDetail(_ancDtlFlag, _patDtlVO, _ancDetailVO, _ancVisitDtlVO ,_lstHistVOs, _lstHistChildVOs, _userVO);
	}
}
