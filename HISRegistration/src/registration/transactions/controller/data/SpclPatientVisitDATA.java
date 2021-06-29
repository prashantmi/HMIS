package registration.transactions.controller.data;
/**
 * Created By 	: Aadil Wasi
 * Date			: March 2013
 */
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import registration.bo.PatientBO;
import registration.bo.RegEssentialBO;
import registration.config.RegistrationConfig;
import registration.transactions.controller.actionsupport.SpclPatientVisitSUP;
import registration.transactions.controller.fb.SplPatientVisitFB;
import vo.registration.EpisodeRefDtlVO;
import vo.registration.EpisodeVO;
import vo.registration.PatientVO;
import vo.registration.RegistrationConfigVO;
import vo.registration.RenewalConfigVO;


public class SpclPatientVisitDATA extends ControllerDATA {
	
	public static Map<String, RenewalConfigVO> getMapOfRenewalConfigDtlOnKeyPatCat(UserVO objUserVO_p)
	{
		RegEssentialBO objRegEssentialBO = new RegEssentialBO();
		return objRegEssentialBO.getMapOfRenewalConfigDtlOnKeyPatCat(objUserVO_p,RegistrationConfig.REGISTRATIONDESK_RENEWEL_CONFIG_SPL);
	}
	
	public static RegistrationConfigVO getRegistrationConfigDtl(UserVO objUserVO_p)
	{
		RegEssentialBO objRegEssentialBO = new RegEssentialBO();
		return objRegEssentialBO.getRegistrationConfigDtl(objUserVO_p,RegistrationConfig.REGISTRATIONDESK_RENEWEL_CONFIG_SPL,"2");
	}
	/**
	 * gets all initial old department visit essentials
	 * @return map containing all intial old department visit essentials
	 */
	public static Map getAllInitialOldDeptVisitEssentials(){
		Map mp = new HashMap();
		mp.put(RegistrationConfig.ESSENTIALBO_OPTION_SPL_OLD_DEPT_VISIT_DEPARTMENT, new ArrayList());
		mp.put(RegistrationConfig.SPLREGISTRATIONDESK_EPISODEVO_ARR,new EpisodeVO[]{} );
		return mp;
	}
	/**
	 * get all initial new department visit essentials
	 * 	@return map containing  all initial new department visit essentials
	 */
	public static Map getAllInitialNewDeptVisitEssentials(){
		Map mp = new HashMap();
		mp.put(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT, new ArrayList());
		mp.put(RegistrationConfig.ESSENTIALBO_OPTION_SPL_NEW_DEPT_VISIT_DEPARTMENT, new ArrayList());
		mp.put(RegistrationConfig.ESSENTIALBO_OPTION_SPL_OLD_DEPT_VISIT_DEPARTMENT, new ArrayList());
		return mp;
	}
	/**
	 * gets old department visit essentials
	 * @param objSplPatVisitSUP_p SplPatientVisitFB form bean
	 * @param objUserVO_p provides User Details
	 * @return map containg old department visit essentials
	 */
	public static Map getOldDeptVisitEssentials(SpclPatientVisitSUP objSplPatVisitSUP_p, UserVO objUserVO_p){
		Map mp = new HashMap();
		RegEssentialBO objRegEssentialBO = new RegEssentialBO();
		mp = objRegEssentialBO.getOldDeptVisitEssential(objSplPatVisitSUP_p.getPatCrNo(), objUserVO_p);
		return mp;
	}
	
	/**
	 * gets old department visit essentials
	 * @param objSplPatVisitSUP_p SplPatientVisitFB form bean
	 * @param objUserVO_p provides User Details
	 * @return map containg old department visit essentials
	 */
	public static Map getOldDeptVisitEssentials(SplPatientVisitFB objSplPatVisitSUP_p, UserVO objUserVO_p){
		Map mp = new HashMap();
		RegEssentialBO objRegEssentialBO = new RegEssentialBO();
		mp = objRegEssentialBO.getOldDeptVisitEssential(objSplPatVisitSUP_p.getPatCrNo(), objUserVO_p);
		return mp;
	}
	
	/**
	 * gets all new department visit essentials
	 * @param crNo string  containing patient's cr no
	 * @param objUserVO_p Provides User details.
	 * @return  map containing all  new department visit essentials
	 */
	public static Map getAllNewDeptVisitEssentials(String strCrNo_p,UserVO objUserVO_p) {
		
		
		RegEssentialBO  objRegEssentialBO=new RegEssentialBO();
		
		return  objRegEssentialBO.getSpclNewDeptVisitEssential(strCrNo_p,objUserVO_p,RegistrationConfig.UNIT_TYPE_SPECIALITY,"2","7");
	}
	
	/**
	 * gets all new department visit essentials
	 * @param crNo string  containing patient's cr no
	 * @param objUserVO_p Provides User details.
	 * @return  map containing all  new department visit essentials
	 */
	public static Map getAllNewDeptVisitEssentialsForSplWithAppt(String strCrNo_p,UserVO objUserVO_p) {
		
		
		RegEssentialBO  objRegEssentialBO=new RegEssentialBO();
		
		return  objRegEssentialBO.getSpclNewDeptVisitEssential(strCrNo_p,objUserVO_p,RegistrationConfig.UNIT_TYPE_SPECIALITY,"2","8");
	}
	
	/**
	 * gets episode by cr no
	 * @param objPatientVO_p provides patient Details
	 * @param objUserVO_p provides User Details
	 * @param registrationServiceId 
	 * @param renewalTariffId 
	 * @param string2 
	 * @param string 
	 * @return array of episodeVO providing patient's episode details
	 */
	public static EpisodeVO[] getOldPatientEpisodes(String strCrNo_p, String strPatCatCode_p, String visitType,String strRenewalType_p,UserVO objUserVO_p){
		PatientBO objPatientBO = new PatientBO();
		EpisodeVO[] vo = objPatientBO.searchOldPatientEpisodesByCrNo(strCrNo_p, strPatCatCode_p,strRenewalType_p, objUserVO_p, visitType,"6");
		return vo;
	}
	/**
	 * gets patient status
	 * @param objPatientVO_p provides patient Details
	 * @param objUserVO_p provides User Details
	 * @return string containing patient Status
	 */
//	public static String getPatientStatus(PatientVO objPatientVO_p, UserVO objUserVO_p){
//	    PatientBO  objPatientBO= new PatientBO();
//	    String patStatus="";
//	    patStatus=objPatientBO.checkPatientStatus(objPatientVO_p,objUserVO_p);
//	    return patStatus;
//	}
	/**
	 * gets patient's episode details
	 * @param arrEpisodeVO array of episodeVO providing episode details
	 * @param objPatientVO_p provides patient Details
	 * @param userVO provides User Details
	 * @return array of episodeVO providing episode details
	 */
	public static EpisodeVO[] getEpisodeDetails(EpisodeVO[] arrEpisodeVO,PatientVO  objPatientVO_p,UserVO userVO,EpisodeRefDtlVO episodeRefDtlVO){
		PatientBO objPatientBO = new PatientBO();
		return objPatientBO.oldDeptVisitStamp(arrEpisodeVO,objPatientVO_p,userVO,episodeRefDtlVO);
	}
	/**
	 * 
	 */
	
	public static EpisodeVO[] saveSpclNewDepartmentVisit(PatientVO objPatientVO_p, EpisodeVO[] objArrEepisodeVO_p, EpisodeRefDtlVO episodeRefVO, UserVO _userVO,PatientVO _oldPatientVO){
		PatientBO objPatientBO = new PatientBO();
		int l=objArrEepisodeVO_p.length;		
		return objPatientBO.spclNewDepartmentVisitStamp(objArrEepisodeVO_p, objPatientVO_p, episodeRefVO, _userVO,_oldPatientVO);
	}
	/**
	 * saves old patient visit details
	 * @param objPatientVO_p provides patient Details
	 * @param _episodeVO provides patient's episode Details
	 * @param objUserVO_p provides User Details
	 * @return array of episodeVO providing episode details
	 */
	public static EpisodeVO[] 	saveSpclOldPatientVisit(PatientVO objPatientVO_p, EpisodeVO[] objArrEepisodeVO_p, UserVO objUserVO_p,EpisodeRefDtlVO episodeRefDtlVO){
		PatientBO objPatientBO = new PatientBO();		
		return objPatientBO.spclOldDeptVisitStamp(objArrEepisodeVO_p, objPatientVO_p, objUserVO_p,episodeRefDtlVO);  
	}
	
	
	public static PatientVO getPatientDtl(PatientVO objPatientVO_p, UserVO objUserVO_p, String visitType){
	   
		PatientBO  objPatientBO= new PatientBO();
		objPatientVO_p=objPatientBO.searchPatientByCrNo(objPatientVO_p,objUserVO_p);
		
	    return objPatientVO_p;
	}
	public static EpisodeRefDtlVO[] getOpenEpisodeOPD(String strCrNo_p, UserVO objUserVO_p, String strMode_p){
		PatientBO objPatientBO = new PatientBO();
		EpisodeRefDtlVO[] arrOpenEpisodeVO =objPatientBO.retrieveAllOpenOPDEpisodes(strCrNo_p, objUserVO_p, strMode_p,RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL);
	    return arrOpenEpisodeVO;
	}
	public static Map referedEssentialDepartment(UserVO userVO){
		RegEssentialBO  objRegEssentialBO =new RegEssentialBO();
		return objRegEssentialBO.getReferDtlEssential(userVO);
	}
	
	public static String getBillAmount(String _patCategoryCode, UserVO objUserVO_p){
		RegEssentialBO  essentialDelegate= new RegEssentialBO();
		return essentialDelegate.getBillAmountBasedOnCategory(_patCategoryCode,objUserVO_p);
		
	}
	
	
	
	public static EpisodeRefDtlVO[] getReferPat(UserVO objUserVO_p, String strMode_p)
	{
		PatientBO objPatientBO= new PatientBO();
		EpisodeRefDtlVO[] arrEpisodeRefDtlVO=objPatientBO.getReferPat(objUserVO_p,strMode_p);
		return arrEpisodeRefDtlVO;
	}
	
	public static List getRefDept_AJAX(UserVO _UserVO, String strRefHospCode_p)
	{
		RegEssentialBO  essentialBO=new RegEssentialBO();
		return essentialBO.getRefDeptBasedOnRefHosp_AJAX(_UserVO, strRefHospCode_p);
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
	
	public static Map 	saveSpclPatientVisit(PatientVO objPatientVO_p, EpisodeVO[] objArrEepisodeVO_p, UserVO objUserVO_p,EpisodeRefDtlVO episodeRefDtlVO, EpisodeVO[] _episodeNewDeptVO){
		PatientBO objPatientBO = new PatientBO();		
		return objPatientBO.saveSpclPatientVisit(objArrEepisodeVO_p, objPatientVO_p, objUserVO_p,episodeRefDtlVO,_episodeNewDeptVO);  
	}
	
	public static PatientVO[] getApptmentPatRegd(UserVO _userVO)
	{
		RegEssentialBO  essentialBO=new RegEssentialBO();			
		PatientVO[] arrPatRegwithAptDtlVO=essentialBO.getCRNoWithAppointment(_userVO, "5");
		return arrPatRegwithAptDtlVO;
	}
	
	public static PatientVO[] searchSpecialCRNoWithAppointment(PatientVO _patVO,UserVO _UserVO){	
		RegEssentialBO  essentialBO=new RegEssentialBO();			
		return  essentialBO.searchSpecialCRNoWithAppointment(_patVO,_UserVO);
	    		
	}
	
	public static PatientVO getApptmentDtlsWithAptNO(PatientVO _patVO,UserVO _userVO)
	{
		RegEssentialBO  essentialBO=new RegEssentialBO();			
		PatientVO arrPatRegwithAptDtlVO=essentialBO.getDetailsWithAppointment(_patVO, _userVO, "2");
		return arrPatRegwithAptDtlVO;
	}
	
	public static EpisodeRefDtlVO[] getReferEpisodeDtl(String strCrNo_p, String unit, UserVO objUserVO_p)
	{
		PatientBO objPatientBO = new PatientBO();
		EpisodeRefDtlVO[] vo = objPatientBO.getReferEpisodeDtl(strCrNo_p, unit, objUserVO_p);
		return vo;
	}
	
	
}