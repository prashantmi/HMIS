package registration.transactions.controller.data;

import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import registration.bo.PatientBO;
import registration.bo.RegEssentialBO;
import registration.config.RegistrationConfig;
import registration.config.RegistrationConfigurationBean;
import registration.transactions.controller.actionsupport.EmgPatientVisitSUP;
import vo.registration.EpisodeRefDtlVO;
import vo.registration.EpisodeVO;
import vo.registration.PatientVO;
import vo.registration.RegistrationConfigVO;
import vo.registration.RenewalConfigVO;
import vo.registration.PatientBroughtByDtlVO;

public class EmgPatientVisitDATA {
	
	public static Map<String, RenewalConfigVO> getMapOfRenewalConfigDtlOnKeyPatCat(UserVO objUserVO_p)
	{
		RegEssentialBO objRegEssentialBO = new RegEssentialBO();
		return objRegEssentialBO.getMapOfRenewalConfigDtlOnKeyPatCat(objUserVO_p,RegistrationConfig.REGISTRATIONDESK_RENEWEL_CONFIG_EMG);
	}
	
	public static RegistrationConfigVO getRegistrationConfigDtl(UserVO objUserVO_p)
	{
		RegEssentialBO objRegEssentialBO = new RegEssentialBO();
		return objRegEssentialBO.getRegistrationConfigDtl(objUserVO_p, RegistrationConfig.REGISTRATIONDESK_RENEWEL_CONFIG_EMG,"2");
	}
	
	/**
	 * gets all initial old department visit essentials
	 * @return map containing all intial old department visit essentials
	 */
	public static Map getAllInitialOldDeptVisitEssentials(){
		Map mp = new HashMap();
		mp.put(RegistrationConfig.EMGREGISTRATIONDESK_OPTION_DEPARTMENT, new ArrayList());
		mp.put(RegistrationConfig.ESSENTIALBO_OPTION_EMG_OLD_DEPT_VISIT_DEPARTMENT, new ArrayList());
		mp.put(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR,new EpisodeVO[]{} );
		return mp;
	}
	/**
	 * get all initial new department visit essentials
	 * 	@return map containing  all initial new department visit essentials
	 */
	public static Map getAllInitialNewDeptVisitEssentials(){
		Map mp = new HashMap();
		//mp.put(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT, new ArrayList());
		mp.put(RegistrationConfig.ESSENTIALBO_OPTION_EMG_NEW_DEPT_VISIT_DEPARTMENT, new ArrayList());
		mp.put(RegistrationConfig.ESSENTIALBO_OPTION_EMG_OLD_DEPT_VISIT_DEPARTMENT, new ArrayList());
		return mp;
	}
	
	/**
	 * gets old department visit essentials
	 * @param objPatVisitSUP_p EmgPatientVisitFB form bean
	 * @param objUserVO_p provides User Details
	 * @return map containg old department visit essentials
	 */
	public static Map getOldDeptVisitEssentials(EmgPatientVisitSUP objPatVisitSUP_p, UserVO objUserVO_p){
		Map mp = new HashMap();
		RegEssentialBO objRegEssentialBO = new RegEssentialBO();
		mp = objRegEssentialBO.getOldDeptVisitEssential(objPatVisitSUP_p.getPatCrNo(), objUserVO_p);
		return mp;
	}
	
	/**
	 * gets all new department visit essentials
	 * @param crNo string  containing patient's cr no
	 * @param objUserVO_p Provides User details.
	 * @return  map containing all  new department visit essentials
	 */
	public static Map getAllNewEmgDeptVisitEssentials(String strCrNo_p,UserVO objUserVO_p) {
		
		
		RegEssentialBO  objRegEssentialBO=new RegEssentialBO();
		
		return  objRegEssentialBO.getEmgDeptVisitEssential(strCrNo_p,objUserVO_p,RegistrationConfig.UNIT_TYPE_GENERAL,"3","6");
	}
	
	/**
	 * gets episode by cr no
	 * @param objPatVO_p provides patient Details
	 * @param objUserVO_p provides User Details
	 * @param registrationServiceId 
	 * @param renewalTariffId 
	 * @param string2 
	 * @param string 
	 * @return array of episodeVO providing patient's episode details
	 */
	public static EpisodeVO[] getOldPatientEpisodes(String strCrNo_p, String strPatCatCode,String visitType,String strRenewalType_p,UserVO objUserVO_p){
		PatientBO objPatientBO = new PatientBO();
		EpisodeVO[] vo = objPatientBO.searchOldPatientEpisodesByCrNo(strCrNo_p, strPatCatCode,strRenewalType_p, objUserVO_p, visitType,"7");
		return vo;
	}
	/**
	 * gets patient status
	 * @param objPatVO_p provides patient Details
	 * @param objUserVO_p provides User Details
	 * @return string containing patient Status
	 */
//	public static String getPatientStatus(PatientVO objPatVO_p, UserVO objUserVO_p){
//	    PatientBO  objPatientBO= new PatientBO();
//	    String patStatus="";
//	    patStatus=objPatientBO.checkPatientStatus(objPatVO_p,objUserVO_p);
//	    return patStatus;
//	}
	/**
	 * gets patient's episode details
	 * @param arrEpisodeVO array of episodeVO providing episode details
	 * @param patVO provides patient Details
	 * @param userVO provides User Details
	 * @return array of episodeVO providing episode details
	 */
	public static EpisodeVO[] getEpisodeDetails(EpisodeVO[] arrEpisodeVO,PatientVO  patVO,UserVO userVO,EpisodeRefDtlVO episodeRefDtlVO){
		PatientBO objPatientBO = new PatientBO();
		return objPatientBO.oldDeptVisitStamp(arrEpisodeVO,patVO,userVO,episodeRefDtlVO);
	}
	/**
	 * 
	 */
	
	public static EpisodeVO[] saveEmgNewDepartmentVisit(PatientVO _patVO, EpisodeVO[] _episodeVO, EpisodeRefDtlVO episodeRefVO, UserVO _userVO,PatientVO _oldPatientVO,HttpServletRequest objRequest_p,PatientBroughtByDtlVO patientBroughtByDtlVO){
		PatientBO objPatientBO = new PatientBO();
		int l=_episodeVO.length;		
		return objPatientBO.emgNewDepartmentVisitStamp(_episodeVO, _patVO, episodeRefVO, _userVO,_oldPatientVO, objRequest_p,patientBroughtByDtlVO);
	}
	/**
	 * saves old patient visit details
	 * @param objPatVO_p provides patient Details
	 * @param _episodeVO provides patient's episode Details
	 * @param objUserVO_p provides User Details
	 * @return array of episodeVO providing episode details
	 */
	public static EpisodeVO[] 	saveOldEmgPatientVisit(PatientVO objPatVO_p, EpisodeVO[] _episodeVO, UserVO objUserVO_p,EpisodeRefDtlVO episodeRefDtlVO,PatientBroughtByDtlVO patientBroughtByDtlVO){
		PatientBO objPatientBO = new PatientBO();		
		return objPatientBO.emgOldDeptVisitStamp(_episodeVO, objPatVO_p, objUserVO_p,episodeRefDtlVO,patientBroughtByDtlVO);  
	}
	
	public static PatientVO getPatientDtl(PatientVO objPatVO_p, UserVO objUserVO_p, String mode){
	   
		PatientBO  objPatientBO= new PatientBO();
		objPatVO_p=objPatientBO.searchPatientByCrNo(objPatVO_p,objUserVO_p);
		
	    return objPatVO_p;
	}
	public static EpisodeRefDtlVO[] getOpenEpisodeOPD(String strCrNo_p, UserVO objUserVO_p, String strMode_p){
		PatientBO objPatientBO = new PatientBO();
		EpisodeRefDtlVO[] arrOpenEpisodeVO =objPatientBO.retrieveAllOpenOPDEpisodes(strCrNo_p, objUserVO_p, strMode_p, "");	// Last parameter (i.e visitType, after strMode_p) is blank, It is handled in procedure itself
	    return arrOpenEpisodeVO;
	}
	public static Map referedEssentialDepartment(UserVO userVO){
		RegEssentialBO  objRegEssentialBO =new RegEssentialBO();
		return objRegEssentialBO.getReferDtlEssential(userVO);
	}
	
	public static List getRefDept_AJAX(UserVO _UserVO, String strRefHospCode_p)
	{
		RegEssentialBO  essentialBO=new RegEssentialBO();
		return essentialBO.getRefDeptBasedOnRefHosp_AJAX(_UserVO, strRefHospCode_p);
	}
	
	public static String getBillAmount(String _patCategoryCode, UserVO objUserVO_p){
		RegEssentialBO  essentialDelegate= new RegEssentialBO();
		return essentialDelegate.getBillAmountBasedOnCategory(_patCategoryCode,objUserVO_p);
		
	}
	
	public static EpisodeVO[] getOldPatientEpisodesByDept(String strCrNo_p, String strDept_p,String strRenewalType_p, UserVO objUserVO_p, String visitType)
	{
		PatientBO objPatientBO = new PatientBO();
		EpisodeVO[] vo = objPatientBO.searchOldPatientEpisodesByCrNoByDept(strCrNo_p, strDept_p,strRenewalType_p, objUserVO_p, visitType);
		return vo;
	}
	
	public static EpisodeVO[] getOldPatientEpisodesByUnit(String strCrNo_p, String unit,String strRenewalType_p, UserVO objUserVO_p, String visitType)
	{
		PatientBO objPatientBO = new PatientBO();
		EpisodeVO[] vo = objPatientBO.searchOldPatientEpisodesByCrNoByUnit(strCrNo_p, unit,strRenewalType_p, objUserVO_p, visitType);
		return vo;
	}
	
	public static Map 	saveEmgPatientVisit(PatientVO objPatVO_p, EpisodeVO[] _episodeVO, UserVO objUserVO_p,EpisodeRefDtlVO episodeRefDtlVO, EpisodeVO[] _episodeNewDeptVO,HttpServletRequest objRequest_p,PatientBroughtByDtlVO patientBroughtByDtlVO){
		PatientBO objPatientBO = new PatientBO();		
		return objPatientBO.saveEmgPatientVisit(_episodeVO, objPatVO_p, objUserVO_p,episodeRefDtlVO,_episodeNewDeptVO, objRequest_p,patientBroughtByDtlVO);  
	}
	
	public static RegistrationConfigurationBean getRegistrationConfigDtl2(UserVO userVO){
		RegEssentialBO  objRegEssentialBO =new RegEssentialBO();
		return objRegEssentialBO.getRegistrationConfigDtl2(userVO);
	}
	
	public static String getPatientQRCode(String patCrNo, UserVO userVO){
		RegEssentialBO  objRegEssentialBO =new RegEssentialBO();
		return objRegEssentialBO.getPatientQRCode(patCrNo, userVO);
	}
	
}