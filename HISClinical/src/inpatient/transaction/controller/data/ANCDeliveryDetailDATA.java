package inpatient.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.ANCDeliveryDetailVO;
import hisglobal.vo.ANCDetailVO;
import hisglobal.vo.ANCHistoryDeliveryDetailVO;
import hisglobal.vo.ANCHistoryDetailVO;
import hisglobal.vo.ANCNeonatalDetailVO;
import hisglobal.vo.UserVO;
import inpatient.transaction.delegate.InPatientEssentialDelegate;
import inpatient.transaction.delegate.InpatientDelegate;

import java.util.List;
import java.util.Map;

public class ANCDeliveryDetailDATA extends ControllerDATA
{
	// Getting ANC Detail Essentials
	public static Map<String, Object> getEssentials(UserVO _userVO)
	{
		InPatientEssentialDelegate delegate = new InPatientEssentialDelegate();
		return delegate.getANCDeliveryDetailEssentials(_userVO);
	}

	// Getting ANC Details
	public static Map getANCDetailsForDelivery(ANCDetailVO _ancDetailVO, UserVO _userVO)
	{
		InpatientDelegate delegate = new InpatientDelegate();
		return delegate.getANCDetailsForDelivery(_ancDetailVO, _userVO);
	}

	// Getting ANC Detail
	public static List<ANCHistoryDetailVO> getANCHistoryDetail(String _patCrNo, UserVO _userVO)
	{
		InpatientDelegate delegate = new InpatientDelegate();
		return delegate.getANCHistoryDetail(_patCrNo, _userVO);
	}

	// Saving Complete ANC Detail
	public static void saveANCDeliveryDetail(ANCDeliveryDetailVO _ancDeliveryDetailVO, ANCDetailVO _ancDetailVO, ANCHistoryDetailVO _ancHistVO,
			List<ANCNeonatalDetailVO> _lstANCChildren, List<ANCHistoryDeliveryDetailVO> _lstANCHistChildren, UserVO _userVO)
	{
		InpatientDelegate delegate = new InpatientDelegate();
		delegate.saveANCDeliveryDetail(_ancDeliveryDetailVO, _ancDetailVO, _ancHistVO, _lstANCChildren, _lstANCHistChildren, _userVO);
	}
}
