package registration.controller.data;

import java.util.Map;

import registration.bo.delegate.EssentialDelegate;
import registration.bo.delegate.PatientDelegate;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.ANCDetailVO;
import hisglobal.vo.AddressVO;
import hisglobal.vo.PatientDeathDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

public class PatientDeathDetailDATA extends ControllerDATA
{
	public static Map getPatientdeathDetailEssential(String crNo,String epiCode,UserVO userVO, String deptUnitCode)
	{
		EssentialDelegate essentialDelegate = new EssentialDelegate();
		return essentialDelegate.getPatientdeathDetailEssential(crNo,epiCode,userVO,deptUnitCode);
	}
	
	public static void savePatientDeathDetail(ANCDetailVO ancDetailVO, PatientDeathDetailVO patDeathDtlVO,UserVO userVO)
	{
		PatientDelegate patDelegate = new PatientDelegate();
		patDelegate.savePatientDeathDetail(ancDetailVO,patDeathDtlVO,userVO);
	}
	
	public static boolean checkRecordAdded(String crNo,UserVO userVO)
	{
		PatientDelegate patDelegate = new PatientDelegate();
		return patDelegate.checkRecordAdded(crNo,userVO);
	}
	
	public static PatientDeathDetailVO getDeathDetailByCrNo(String crNo,UserVO userVO)
	{
		PatientDelegate patDelegate = new PatientDelegate();
		return patDelegate.getDeathDetailByCrNo(crNo,userVO);
	}
	
	public static AddressVO getPatAddress(String crNo,UserVO userVO)
	{
		PatientDelegate patDelegate = new PatientDelegate();
		return patDelegate.getPatAddress(crNo,userVO);
	}
	
	public static String getPatientMlcNo(PatientDetailVO selectedPatientVO,UserVO userVO)
	{
		PatientDelegate patDelegate = new PatientDelegate();
		return patDelegate.getPatientMlcNo(selectedPatientVO,userVO);
	}
	
	public static ANCDetailVO getPatientANCDetail(String crNo,UserVO userVO)
	{
		PatientDelegate patDelegate = new PatientDelegate();
		return patDelegate.getPatientANCDetail(crNo,userVO);
	}
}
