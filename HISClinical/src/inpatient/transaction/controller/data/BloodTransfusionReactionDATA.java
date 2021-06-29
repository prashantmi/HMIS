package inpatient.transaction.controller.data;

import inpatient.transaction.delegate.InPatientEssentialDelegate;
import inpatient.transaction.delegate.InpatientDelegate;

import java.util.List;
import java.util.Map;

import hisglobal.presentation.ControllerDATA;
import hisglobal.utility.Entry;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.TransfusionReactionDtlVO;
import hisglobal.vo.UserVO;

public class BloodTransfusionReactionDATA extends ControllerDATA 
{
	public static Map getBloodTransReactionEssential(PatientDetailVO patientDetailVO, UserVO _userVO)
	{
		InPatientEssentialDelegate delegate = new InPatientEssentialDelegate();
		return delegate.getBloodTransReactionEssential(patientDetailVO, _userVO);
	}
	
	public static void saveBloodTransReactionDtl(TransfusionReactionDtlVO vo,List bloodTrasReactionParaVOList,UserVO _userVO)
	{
		InpatientDelegate delegate=new InpatientDelegate();
		delegate.saveBloodTransReactionDtl(vo,bloodTrasReactionParaVOList,_userVO);
	}
	
	
	public static List<Entry> getReactionSummaryDetail(String _processId, UserVO _userVO)
	{
		InPatientEssentialDelegate delegate = new InPatientEssentialDelegate();
		return delegate.getReactionSummaryDetail(_processId, _userVO);
	}
	
	
}
