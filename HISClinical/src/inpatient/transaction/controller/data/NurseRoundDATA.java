package inpatient.transaction.controller.data;

import java.util.List;

import inpatient.transaction.delegate.InPatientEssentialDelegate;
import inpatient.transaction.delegate.InpatientDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.NurseRoundDtlVO;
import hisglobal.vo.UserVO;

public class NurseRoundDATA extends ControllerDATA
{
	public static void saveNurseProgNotes(NurseRoundDtlVO nurseRoundDtlVO,UserVO userVO)
	{
		InpatientDelegate delegate= new InpatientDelegate();
		delegate.saveNurseProgNotes(nurseRoundDtlVO,userVO); 
	}
	
	public static NurseRoundDtlVO[] getPreviousProgressNotes(String patAdmNo,UserVO userVO)
	{
		InpatientDelegate delegate=new InpatientDelegate();
		return delegate.getPreviousProgressNotes(patAdmNo,userVO);
	}
	
	public static List getProgressNotes(String processId,UserVO userVO)
	{
		InPatientEssentialDelegate delegate=new InPatientEssentialDelegate();
		return delegate.getProgressNotes(processId,userVO);
	}
}
