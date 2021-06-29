package inpatient.transaction.controller.data;

import inpatient.transaction.delegate.InPatientEssentialDelegate;
import inpatient.transaction.delegate.InpatientDelegate;

import java.util.List;
import java.util.Map;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DrugAdminDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

public class DrugAdministrationDATA extends ControllerDATA 
{
	public static Map getPatTreatmentDetailEssential(PatientDetailVO patientDetailVO, UserVO _userVO)
	{
		InPatientEssentialDelegate delegate = new InPatientEssentialDelegate();
		return delegate.getTreatAdministrationEssential(patientDetailVO, _userVO);

	}
	
	public static void saveDrugTreatment(List drugAdminDtlVOList,List ivFluidDrugAdminVOList,List sosDrugList,List patIntakeOutDtlVOList ,UserVO _userVO)

	{
		InpatientDelegate delegate=new InpatientDelegate();
		delegate.saveDrugTreatmentExe(drugAdminDtlVOList,ivFluidDrugAdminVOList,sosDrugList,patIntakeOutDtlVOList,_userVO);
	}
	
	public static List getDateWiseTreatInfo(DrugAdminDtlVO drugAdminDtlVO, UserVO _userVO)
	{
		InPatientEssentialDelegate delegate = new InPatientEssentialDelegate();
		return delegate.getDateWiseTreatInfo(drugAdminDtlVO, _userVO);
	}
	public static List getDrugWiseTreatInfo(DrugAdminDtlVO drugAdminDtlVO, UserVO _userVO)
	{
		InPatientEssentialDelegate delegate = new InPatientEssentialDelegate();
		return delegate.getDrugWiseTreatInfo(drugAdminDtlVO, _userVO);
	}
	
	
	
	
}
