package registration.transactions.controller.data;


import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisRegistrationTimingExpiredException;
import hisglobal.presentation.ControllerDATA;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import registration.bo.PatientBO;
import registration.bo.RegEssentialBO;
import vo.registration.EpisodeVO;
import vo.registration.PatientIdVO;
import vo.registration.PatientVO;




public class SplRegistrationDATA extends ControllerDATA {

	public static Map getSplRegistrationFormInitialEssentials(UserVO _UserVO)
	{
		RegEssentialBO  essentialBO=new RegEssentialBO();
		Map mp=essentialBO.getSplRegistrationFormInitialEssentials(_UserVO);
		
		return mp;		
	}
 /**
 * gets Spl Registration Essentials
 * calls getSplPatientRegEssential from EssentialDelegate
 * @param _UserVO Provides User details.
 * @return map containing new patient registration essentials
 */
	/*public static Map getAllSplRegistrationEssentials(UserVO _UserVO, String strStateCode)
	{
		RegEssentialBO  essentialBO=new RegEssentialBO();
		Map mp=essentialBO.getNewPatientRegEssential(_UserVO,strStateCode);
		return mp;		
	}	*/
	
	public static Map getRegistrationFormEssentials_AJAX(UserVO _UserVO,String strStateCode, HttpServletRequest objRequest_p)
	{
		RegEssentialBO  essentialBO=new RegEssentialBO();
		Map mp=essentialBO.getSplRegistrationFormEssentials_AJAX(_UserVO,strStateCode,objRequest_p);
		
		return mp;		
	}
	
	public static List getState_AJAX(UserVO _UserVO, String strCountryCode_p)
	{
		RegEssentialBO  essentialBO=new RegEssentialBO();
		return essentialBO.getStateBasedOnCountry_AJAX(_UserVO,strCountryCode_p);
	}
	public static List getDistrict_AJAX(UserVO _UserVO,String strStateCode, String strCountryCode_p)
	{
		RegEssentialBO  essentialBO=new RegEssentialBO();
		return essentialBO.getDistrictBasedOnState_AJAX(_UserVO,strStateCode,strCountryCode_p);
	}
	
	public static List getRefDept_AJAX(UserVO _UserVO, String strRefHospCode_p)
	{
		RegEssentialBO  essentialBO=new RegEssentialBO();
		return essentialBO.getRefDeptBasedOnRefHosp_AJAX(_UserVO, strRefHospCode_p);
	}

	public static List getPatDtlOnPatCatId(UserVO userVO_p,
			String patPrimaryCatCode_p, String strSearchCatName_p,String strSearchCatId_p, String strMode_p) {
		RegEssentialBO  essentialBO=new RegEssentialBO();
		return essentialBO.getPatDtlOnPatCatId(userVO_p,patPrimaryCatCode_p,strSearchCatName_p,strSearchCatId_p,strMode_p);
	}
	
	/**
	 * registers new patient 
	 * calls newPatRegistration from PatientDelegate
	 * @param voRenewalConfigVO_p 
	 * @param _patVO Provides patient details.
	 * @param _episodeVO Provides patient's episode details.
	 * @param _userVO Provides User details.
	 * @return EpisodeVO[] array of episodeVO
	 */
	public static EpisodeVO[] registerSplPatient(PatientVO objPatVO_p, EpisodeVO[] objEpisodeVO_p,UserVO objUserVO_p, HttpServletRequest  objRequest_p) {
		PatientBO objPatientBO = new PatientBO(); 
		return objPatientBO.newSplRegistration(objEpisodeVO_p, objPatVO_p,objUserVO_p, objRequest_p);
	}
	
	public static String checkUniqueIdDuplicy(UserVO objUserVO_p, PatientIdVO objPatientIdVO_p,String strNationalId_p) {
		RegEssentialBO  essentialBO=new RegEssentialBO();
		return essentialBO.checkUniqueIdDuplicay(objUserVO_p, objPatientIdVO_p,strNationalId_p);
	}
	
	public static PatientVO[] getCRNoWithAppointment(UserVO _UserVO,String episodeVisitType){	
		RegEssentialBO  essentialBO=new RegEssentialBO();			
		return  essentialBO.getCRNoWithAppointment(_UserVO,episodeVisitType);
	    		
	}
	
	public static PatientVO getDetailsWithAppointment(PatientVO _patVO, UserVO _userVO, String _mode){	
		RegEssentialBO  essentialBO=new RegEssentialBO();			
		return  essentialBO.getDetailsWithAppointment(_patVO,_userVO,_mode);
	    		
	}
	
	public static PatientVO[] searchSpecialCRNoWithAppointment(PatientVO _patVO,UserVO _UserVO){	
		RegEssentialBO  essentialBO=new RegEssentialBO();			
		return  essentialBO.searchSpecialCRNoWithAppointment(_patVO,_UserVO);
	    		
	}
	
	public static String getBillAmountBasedOnCategory(String _CatCode, UserVO _userVO){		
		RegEssentialBO  essentialBO=new RegEssentialBO();			
		return  essentialBO.getBillAmountBasedOnCategory(_CatCode,_userVO);		
	}
	//Start:Sheeldarshi: 30thSep'14
	public static Map getRegistrationFormInitialEssentials(UserVO _UserVO)
	{
		RegEssentialBO  essentialBO=new RegEssentialBO();
		Map mp=essentialBO.getSpecialRegistrationFormInitialEssentials(_UserVO);
		
		return mp;		
	}
	public static Map getRegistrationFormRegConfigEssentials(UserVO _UserVO)
	{
		RegEssentialBO  essentialBO=new RegEssentialBO();
		Map mp=essentialBO.getSpecialRegistrationFormRegConfigEssentials(_UserVO);
		
		return mp;		
	}
	//End:Sheeldarshi: 30thSep'14
	/**
	 * gets Secondary Category Essentials
	 * calls getSecondaryCategory() from EssentialDelegate
	 * @param _crno string containing patient's cr no
	 * @param _UserVO Provides User details.
	 * @param _objStatus
	 * @return List containg secondary category  Essentials
	 *//*
	
	public static List getSecondaryCatEssential(String _crno, UserVO _UserVO, Status _objStatus){
		EssentialDelegate  essentialDelegate=new EssentialDelegate();
	    List secondaryCatList=essentialDelegate.getSecondaryCategory(_crno,_UserVO);
	    return secondaryCatList;
	}	
	*//**
	 * gets Previous System Details
	 * calls getPrevSystemPatDetail() from PatientDelegate
	 * @param _patVO Provides patient details.
	 * @param _userVO Provides User details.
	 * @return PatientVO Provides  patient details in previous system
	 *//*	
	public static PatientVO getPatientDtl(PatientVO _patVO, UserVO _userVO){	
		
		
		PatientDelegate  patientDelegate= new PatientDelegate();
		_patVO=patientDelegate.getPrevSystemPatDetail(_patVO, _userVO);
		return _patVO;
	}
	public static List getStateBasedOnCountry(String _CountryCode, UserVO _userVO){
		EssentialDelegate  essentialDelegate=new EssentialDelegate();
	    List stateList=essentialDelegate.getStateBasedOnCountry(_CountryCode,_userVO);
	    return stateList;		
	}	
	
	public static String getBillAmountBasedOnCategory(String _CatCode, UserVO _userVO){		
	    return new EssentialDelegate().getBillAmountBasedOnCategory(_CatCode,_userVO);		
	}
	public static String getBillAmountAndIdRequiredBasedOnCategory(String _CatCode, UserVO _userVO){		
	    return new EssentialDelegate().getBillAmountAndIdRequiredBasedOnCategory(_CatCode,_userVO);		
	}
	public static String checkBplDetails(String _bplcardNo, UserVO _userVO){		
	    return new EssentialDelegate().checkBplDetails(_bplcardNo,_userVO);		
	}
	public static WebRowSet getBplSearchDetails(BPLDetailsVO bplVO, UserVO _userVO){		
	    return new EssentialDelegate().getBplSearchDetails(bplVO,_userVO);		
	}
	public static List getDistrictListBasedOnState(String _stateCode, UserVO _userVO){		
	    return new EssentialDelegate().getDistrictOnBasisOfState(_stateCode,_userVO);		
	}
	
	public static EmpMasterVO getEmpDetailsAgainstID (String _empID, UserVO _userVO){		
	    return  new MasterDelegate().getEmpDetailsAgainstID(_empID,_userVO);		
	}
	public static EpisodeVO[] newDepartmentVisitForDuplicate(PatientVO patientVO, EpisodeVO[] arrEpisodeVO,
			EpisodeRefDtlVO episodeRefVO, UserVO userVO) {
		PatientDelegate patDelegate = new PatientDelegate();
		return patDelegate.newDepartmentVisitStampForDuplicate(arrEpisodeVO, patientVO, episodeRefVO, userVO);
		
	}
	
	public static PatientAdhaarDtlVO checkDupAdhaarPatient(PatientVO _patVO,  UserVO _userVO){
		PatientDelegate patDelegate = new PatientDelegate();
		return patDelegate.checkDupAdhaarPatient( _patVO, _userVO);
	}*/
	
}