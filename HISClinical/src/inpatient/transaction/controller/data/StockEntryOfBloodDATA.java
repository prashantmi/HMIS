package inpatient.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.PatBloodStockDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import inpatient.transaction.delegate.InPatientEssentialDelegate;
import inpatient.transaction.delegate.InpatientDelegate;

import java.util.Map;

public class StockEntryOfBloodDATA extends ControllerDATA
{
	
	public static Map getStockEntryOfBloodEssential(PatientDetailVO patientDetailVO, UserVO _userVO)
	{
		InPatientEssentialDelegate delegate = new InPatientEssentialDelegate();
		return delegate.getStockEntryOfBloodEssential(patientDetailVO, _userVO);
	}
	
	public static void saveExternalBloodStockDtl(PatBloodStockDtlVO[] patBloodStockDtlVO,UserVO _userVO)
	{
		InpatientDelegate delegate=new InpatientDelegate();
		delegate.saveExternalBloodStockDtl(patBloodStockDtlVO,_userVO);
	}
}
