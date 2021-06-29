package inpatient.transaction.controller.data;

import inpatient.transaction.delegate.InPatientEssentialDelegate;

import java.util.Map;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;

public class PatientMedicalRecordDATA extends ControllerDATA
{
	public static Map getPatientChronicNAllergy(String crNo,UserVO userVO)
	{
		InPatientEssentialDelegate essDelegate=new InPatientEssentialDelegate();
		return essDelegate.getPatientChronicNAllergy(crNo,userVO);
	}
}
