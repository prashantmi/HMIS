package registration.transactions.controller.data;


import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import registration.bo.PatientBO;
import registration.bo.RegEssentialBO;
import vo.registration.EpisodeVO;
import vo.registration.PatientIdVO;
import vo.registration.PatientVO;
import vo.registration.RenewalConfigVO;




public class ExternalPatientRegistrationDATA extends ControllerDATA {

 /**
 * gets New Registration Essentials
 * calls getNewPatientRegEssential from EssentialDelegate
 * @param _UserVO Provides User details.
 * @return map containing new patient registration essentials
 */
	public static Map getAllNewRegistrationEssentials(UserVO _UserVO, String strStateCode)
	{
		RegEssentialBO  essentialBO=new RegEssentialBO();
		Map mp=essentialBO.getNewPatientRegEssential(_UserVO,strStateCode);
		return mp;		
	}	
	
	public static Map getRegistrationFormInitialEssentials(UserVO _UserVO,String isUnitWiseRegistration)
	{
		RegEssentialBO  essentialBO=new RegEssentialBO();
		Map mp=essentialBO.getRegistrationFormInitialEssentials(_UserVO,isUnitWiseRegistration);
		
		return mp;		
	}
	public static Map getRegistrationFormEssentials_AJAX(UserVO _UserVO,String strStateCode,HttpServletRequest objRequest_p)
	{
		RegEssentialBO  essentialBO=new RegEssentialBO();
		Map mp=essentialBO.getRegistrationFormEssentials_AJAX(_UserVO,strStateCode,objRequest_p);
		
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
	
	public static List getVerDocExceptCatDoc_AJAX(UserVO _UserVO, String strDocumentCode)
	{
		RegEssentialBO  essentialBO=new RegEssentialBO();
		return essentialBO.getVerificationDocumetExceptDocCode(_UserVO, strDocumentCode);
	}

	public static List getPatDtlOnPatCatId(UserVO userVO_p,String patPrimaryCatCode_p, String strSearchCatName_p,String strSearchCatId_p, String strMode_p) {
		System.out.println("NewRegistrationDATA :: getPatDtlOnPatCatId");
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
	public static PatientVO registerNewPatient(PatientVO objPatVO_p,UserVO objUserVO_p, HttpServletRequest  objRequest_p) {
		PatientBO objPatientBO = new PatientBO(); 
		return objPatientBO.ExternalPatRegistration( objPatVO_p,objUserVO_p, objRequest_p);
	}
	
	public static String checkUniqueIdDuplicy(UserVO objUserVO_p, PatientIdVO objPatientIdVO_p,String strNationalId_p) {
		RegEssentialBO  essentialBO=new RegEssentialBO();
		return essentialBO.checkUniqueIdDuplicay(objUserVO_p, objPatientIdVO_p,strNationalId_p);
	}
	
	public static String getCounterName(UserVO objUserVO_p,String ipAddress) {
		RegEssentialBO  essentialBO=new RegEssentialBO();
		return essentialBO.getCounterName(objUserVO_p,ipAddress);
	}

	
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