package inpatient.transaction.controller.data;

import inpatient.transaction.delegate.InPatientEssentialDelegate;
import inpatient.transaction.delegate.InpatientDelegate;

import java.util.List;
import java.util.Map;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DrugAdminDtlVO;
import hisglobal.vo.DrugReactionVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

public class DrugReactionData extends ControllerDATA 
{
	public static Map getPatDrugReactionEssential(PatientDetailVO patientDetailVO,DrugAdminDtlVO drugAdminDtlVO ,UserVO _userVO)
	{
		InPatientEssentialDelegate delegate = new InPatientEssentialDelegate();
		return delegate.getPatDrugReactionEssential(patientDetailVO, drugAdminDtlVO,_userVO);
	}
	
	public static void saveDrugReactionDtl(DrugReactionVO drugReactionVO,List templateParaList ,UserVO _userVO)
	{
		InpatientDelegate delegate=new InpatientDelegate();
		delegate.saveDrugReactionDtl(drugReactionVO,templateParaList,_userVO);
	}
	
	public static List getDrugReactionDtl(DrugReactionVO drugReactionVO,UserVO _userVO)
	{
		InpatientDelegate delegate=new InpatientDelegate();
		return delegate.getDrugReactionDtl(drugReactionVO,_userVO);
	}
} 
