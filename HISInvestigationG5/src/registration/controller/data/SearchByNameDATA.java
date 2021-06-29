package registration.controller.data;

import java.util.HashMap;
import java.util.Map;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.PatientVO;
import hisglobal.vo.UserVO;
import registration.bo.delegate.EssentialDelegate;
import registration.bo.delegate.PatientDelegate;

public class SearchByNameDATA extends ControllerDATA {
	/**
	 * gets patient details by name
	 * @param searchName string containing text to search
	 * @param _userVO provides user details
	 * @return  PatientVO[] array of patientVO providing patient details
	 */
	public static PatientVO[] getPatientDtlByName(String searchName,  UserVO _userVO){
		PatientDelegate patDelegate = new PatientDelegate();		
		return patDelegate.searchPatientByName(searchName,_userVO);
	}
	
	public static PatientVO[] getPatientByContactNo(String contactNo,  UserVO _userVO){
		PatientDelegate patDelegate = new PatientDelegate();		
		return patDelegate.searchPatientByContactNo(contactNo,_userVO);
	}
	
	public static PatientVO[] getPatientBynationalID(String nationalID,  UserVO _userVO){
		PatientDelegate patDelegate = new PatientDelegate();		
		return patDelegate.searchPatientByNationalID(nationalID,_userVO);
	}
	public static PatientVO[] getPatientByEmployeelID(String employeeID,  UserVO _userVO){
		PatientDelegate patDelegate = new PatientDelegate();		
		return patDelegate.searchPatientByEmployeeID(employeeID,_userVO);
	}
	
	public static PatientVO[] searchPatient(HashMap _searchMap,  UserVO _userVO){
		PatientDelegate patDelegate = new PatientDelegate();		
		return patDelegate.searchPatient(_searchMap,_userVO);
	}

	public static Map getEssentialForSearch(UserVO userVO) {
		EssentialDelegate essentialDelegate = new EssentialDelegate();		
		return essentialDelegate.getEssentialForSearch(userVO);
	}
}

