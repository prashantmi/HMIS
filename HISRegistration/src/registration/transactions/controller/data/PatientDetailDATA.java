package registration.transactions.controller.data;

/**
 * @author s.singaravelan
 * Creation Date:- 04-Feb-2014
 * Modifying Date:- 
 * Used For:-	
 * Team Lead By:- 
 * Module:- Registration(HIS Project)
 *
 */

import java.util.List;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;
import registration.bo.PatientBO;
import vo.registration.DailyPatientVO;
import vo.registration.MlcVO;
import vo.registration.PatientIdVO;
import vo.registration.PatientVO;

public class PatientDetailDATA extends ControllerDATA {
	/**
	 * gets patient details 
	 * @param _patVO provides patient details
	 * @param _userVO provides user details
	 * @return 
	 */
	public static PatientVO getPatientDtl(PatientVO _patVO, UserVO _userVO){
	  
		PatientBO serviceBO = new PatientBO() ;
		return (serviceBO.searchPatientByCrNo(_patVO, _userVO));

	}
	
	
	/**
	 * gets patient details
	 * 
	 * @param _patVO provides patient details
	 * @param _userVO provides user details
	 * @param _constraint
	 * @return
	 * Modified By Pragya at 05-Aug-2011
	 */
	public static PatientVO getPatientDtlDaily(PatientVO _patVO, UserVO _userVO, String _constraint)
	{
		PatientBO serviceBO = new PatientBO() ;
		return (serviceBO.searchPatientByCrNoDaily(_patVO, _userVO, _constraint));
	}
	
//	public static PatientVO getPatientDtlDailyPatient(PatientVO _patVO, UserVO _userVO, String _constraint)
//	{
//		PatientBO serviceBO = new PatientBO() ;
//		return (serviceBO.searchPatientByCrNoDailyPatient(_patVO, _userVO, _constraint));
//	}
//	
	
	/**
	 * gets MLC number if the patient is MLC
	 * @param _patVO provides patient details
	 * @param _userVO provides user details
	 * @return MlcVO provides  MLC details
	 */
	public static MlcVO getMlcNo(PatientVO _patVO, UserVO _userVO){
		PatientBO serviceBO = new PatientBO() ;
		return (serviceBO.isPatientMlc(_patVO,_userVO));				
	   }
	public static DailyPatientVO getDailyPatientDtl(DailyPatientVO dailyPatientVO, UserVO _userVO){
		   
		PatientBO serviceBO = new PatientBO() ;
		dailyPatientVO=serviceBO.searchDailyPatientByCrNo(dailyPatientVO,_userVO);
		return dailyPatientVO;
	}
	
	/**
	 * gets patient details 
	 * @param _patVO provides patient details
	 * @param _userVO provides user details
	 * @return 
	 */
	public static List searchPatientDetailByCRNo(PatientVO _patVO, UserVO _userVO){
	  
		PatientBO serviceBO = new PatientBO() ;
		return (serviceBO.searchPatientDetailByCRNoForSearchTile(_patVO, _userVO));

	}
	
	/**
	 * gets patient details 
	 * @param _patVO provides patient details
	 * @param _userVO provides user details
	 * @return 
	 */
	public static List searchPatientDetailByUniqueId(PatientIdVO _objPatientIdVO, UserVO _userVO){
	  
		PatientBO serviceBO = new PatientBO() ;
		return (serviceBO.searchPatientDetailByUniqueIdForSearchTile(_objPatientIdVO, _userVO));

	}
	
	/**
	 * gets patient details 
	 * @param _patVO provides patient details
	 * @param _userVO provides user details
	 * @return 
	 */
	public static List searchPatientDetailByMobileNo(PatientVO _patVO, UserVO _userVO){
	  
		PatientBO serviceBO = new PatientBO() ;
		return (serviceBO.searchPatientDetailByMobileNOForSearchTile(_patVO, _userVO));

	}
	
	/**
	 * gets patient details 
	 * @param _patVO provides patient details
	 * @param _userVO provides user details
	 * @return 
	 */
	public static List searchPatientDetailByEmail(PatientVO _patVO, UserVO _userVO){
	  
		PatientBO serviceBO = new PatientBO() ;
		return (serviceBO.searchPatientDetailByEmailForSearchTile(_patVO, _userVO));

	}
	
	/**
	 * gets patient details 
	 * @param _patVO provides patient details
	 * @param _userVO provides user details
	 * @return 
	 */
	public static List searchPatientDetailByDemographicDetails(PatientVO _patVO, UserVO _userVO){
	  
		PatientBO serviceBO = new PatientBO() ;
		return (serviceBO.searchPatientDetailByDemographicDetailForSearchTile(_patVO, _userVO));

	}

	
}
