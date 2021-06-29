package inpatient.transaction.controller.data;

import inpatient.transaction.delegate.InPatientEssentialDelegate;
import inpatient.transaction.delegate.InpatientDelegate;

import java.util.Date;
import java.util.List;
import java.util.Map;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.ANCChecklistDetailVO;
import hisglobal.vo.ANCDetailVO;
import hisglobal.vo.UserVO;

public class ANCTrimesterChecklistDetailDATA extends ControllerDATA
{
	// Getting ANC Trimester Checklist Detail Essentials
	public static Map<String, Object> getEssentials(ANCDetailVO _ancDtlVO, Date _sysdate, UserVO _userVO)
	{
		InPatientEssentialDelegate delegate = new InPatientEssentialDelegate();
		return delegate.getANCTrimesterChklistEssentials(_ancDtlVO, _sysdate, _userVO);
	}

	// Saving ANC Trimester Checklist Detail
	public static void saveANCTrimesterChecklistDetail(List<ANCChecklistDetailVO> _lstDrugChkLst, List<ANCChecklistDetailVO> _lstInvstChkLst, UserVO _userVO)
	{
		InpatientDelegate delegate = new InpatientDelegate();
		delegate.saveANCTrimesterChecklistDetail(_lstDrugChkLst, _lstInvstChkLst, _userVO);
	}
}
