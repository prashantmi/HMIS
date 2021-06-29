package registration.transactions.controller.data;
/**
 * Developed By : Aadil Wasi
 * Date			: May 2013
 */

import vo.registration.BeneficiaryPatientVO;
import vo.registration.EpisodeRefDtlVO;
import vo.registration.EpisodeVO;
import vo.registration.PatientVO;
import vo.registration.RegistrationConfigVO;
import vo.registration.RenewalConfigVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import registration.bo.PatientBO;
import registration.bo.RegEssentialBO;
import registration.config.RegistrationConfig;
import registration.transactions.controller.actionsupport.EmgPatientVisitSUP;
import registration.transactions.controller.actionsupport.PatientVisitSUP;
import registration.transactions.controller.fb.SplPatientVisitFB;


public class PatientVisitDATA  {
	
	public static Map<String, RenewalConfigVO> getMapOfRenewalConfigDtlOnKeyPatCat(UserVO objUserVO_p)
	{
		RegEssentialBO objRegEssentialBO = new RegEssentialBO();
		return objRegEssentialBO.getMapOfRenewalConfigDtlOnKeyPatCat(objUserVO_p,RegistrationConfig.REGISTRATIONDESK_RENEWEL_CONFIG_OPD);
	}	
	
	public static RegistrationConfigVO getRegistrationConfigDtl(UserVO objUserVO_p)
	{
		RegEssentialBO objRegEssentialBO = new RegEssentialBO();
		return objRegEssentialBO.getRegistrationConfigDtl(objUserVO_p,RegistrationConfig.REGISTRATIONDESK_RENEWEL_CONFIG_OPD,"2");
	}
	
	/**
	 * gets all initial old department visit essentials
	 * @return map containing all intial old department visit essentials
	 */
	public static Map getAllInitialOldDeptVisitEssentials(){
		Map mp = new HashMap();
		mp.put(RegistrationConfig.ESSENTIALBO_OPTION_OLD_DEPT_VISIT_DEPARTMENT, new ArrayList());
		mp.put(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR,new EpisodeVO[]{} );
		return mp;
	}
	/**
	 * get all initial new department visit essentials
	 * 	@return map containing  all initial new department visit essentials
	 */
	public static Map getAllInitialNewDeptVisitEssentials(){
		Map mp = new HashMap();
		mp.put(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT, new ArrayList());
		mp.put(RegistrationConfig.ESSENTIALBO_OPTION_NEW_DEPT_VISIT_DEPARTMENT, new ArrayList());
		mp.put(RegistrationConfig.ESSENTIALBO_OPTION_OLD_DEPT_VISIT_DEPARTMENT, new ArrayList());
		return mp;
	}
	/**
	 * gets old department visit essentials
	 * @param objPatVisitSUP_p PatientVisitFB form bean
	 * @param objUserVO_p provides User Details
	 * @return map containg old department visit essentials
	 */
	public static Map getOldDeptVisitEssentials(PatientVisitSUP objPatVisitSUP_p, UserVO objUserVO_p){
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
	public static Map getAllNewDeptVisitEssentials(String strCrNo_p,UserVO objUserVO_p) {
		
		
		RegEssentialBO  objRegEssentialBO=new RegEssentialBO();
		
		return  objRegEssentialBO.getNewDeptVisitEssential(strCrNo_p,objUserVO_p,RegistrationConfig.UNIT_TYPE_GENERAL,"1","3");
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
		EpisodeVO[] vo = objPatientBO.searchOldPatientEpisodesByCrNo(strCrNo_p,strPatCatCode, strRenewalType_p, objUserVO_p, visitType,"6");
		return vo;
	}
	
	/*Start : Surabhi
	 * Reason : to get the episode details of the externally referred patients
	 * date : 7th oct 2016 */
	public static EpisodeVO[] getExtOldPatientEpisodes(PatientVisitSUP objPatVisitSUP_p,String strCrNo_p, String strPatCatCode,String visitType,String strRenewalType_p,UserVO objUserVO_p){
		PatientBO objPatientBO = new PatientBO();
		EpisodeVO[] vo = objPatientBO.searchExtOldPatientEpisodesByCrNo(objPatVisitSUP_p,strCrNo_p,strPatCatCode, strRenewalType_p, objUserVO_p, visitType,"1");
		return vo;
	}
	//end
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
	
	public static EpisodeVO[] newDepartmentVisit(PatientVO _patVO, EpisodeVO[] _episodeVO, EpisodeRefDtlVO episodeRefVO, UserVO _userVO,PatientVO _oldPatientVO){
		PatientBO objPatientBO = new PatientBO();
		int l=_episodeVO.length;		
		return objPatientBO.newDepartmentVisitStamp(_episodeVO, _patVO, episodeRefVO, _userVO,_oldPatientVO);
	}
	
	/*Start : Surabhi
	 * Reason : to get the visit details of the externally referred patients
	 * date : 7th oct 2016 */
	public static EpisodeVO[] newExtDepartmentVisit(PatientVO _patVO, EpisodeVO[] _episodeVO, EpisodeRefDtlVO episodeRefVO, UserVO _userVO,PatientVO _oldPatientVO){
		PatientBO objPatientBO = new PatientBO();
		int l=_episodeVO.length;		
		return objPatientBO.newExtDepartmentVisitStamp(_episodeVO, _patVO, episodeRefVO, _userVO,_oldPatientVO);
	}
	//end
	/**
	 * saves old patient visit details
	 * @param objPatVO_p provides patient Details
	 * @param _episodeVO provides patient's episode Details
	 * @param objUserVO_p provides User Details
	 * @return array of episodeVO providing episode details
	 */
	public static EpisodeVO[] 	saveOldPatientVisit(PatientVO objPatVO_p, EpisodeVO[] _episodeVO, UserVO objUserVO_p,EpisodeRefDtlVO episodeRefDtlVO){
		PatientBO objPatientBO = new PatientBO();		
		return objPatientBO.oldDeptVisitStamp(_episodeVO, objPatVO_p, objUserVO_p,episodeRefDtlVO);  
	}
	
	
	public static PatientVO getPatientDtl(PatientVO objPatVO_p, UserVO objUserVO_p, String visitType){
	   
		PatientBO  objPatientBO= new PatientBO();
		objPatVO_p=objPatientBO.searchPatientByCrNo(objPatVO_p,objUserVO_p);
		
	    return objPatVO_p;
	}
	public static EpisodeRefDtlVO[] getOpenEpisodeOPD(String strCrNo_p, UserVO objUserVO_p, String strMode_p){
		PatientBO objPatientBO = new PatientBO();
		EpisodeRefDtlVO[] arrOpenEpisodeVO =objPatientBO.retrieveAllOpenOPDEpisodes(strCrNo_p, objUserVO_p, strMode_p,RegistrationConfig.EPISODE_VISIT_TYPE_OPD);
	    return arrOpenEpisodeVO;
	}
	
	/*Start : Surabhi
	 * Reason : to get the episode details of the externally referred patients
	 * date : 7th oct 2016 */
	public static EpisodeRefDtlVO[] getExtOpenEpisodeOPD(PatientVisitSUP objPatVisitSUP_p,UserVO objUserVO_p, String strMode_p){
		PatientBO objPatientBO = new PatientBO();
		EpisodeRefDtlVO[] arrOpenEpisodeVO =objPatientBO.retrieveAllExtOpenOPDEpisodes(objPatVisitSUP_p,objUserVO_p, strMode_p);
	    return arrOpenEpisodeVO;
	}
	//end
	
	public static Map referedEssentialDepartment(UserVO userVO){
		RegEssentialBO  objRegEssentialBO =new RegEssentialBO();
		return objRegEssentialBO.getReferDtlEssential(userVO);
	}
	
	public static String getBillAmount(String _patCategoryCode, UserVO objUserVO_p){
		RegEssentialBO  essentialDelegate= new RegEssentialBO();
		return essentialDelegate.getBillAmountBasedOnCategory(_patCategoryCode,objUserVO_p);
		
	}
	
	/*Start : Surabhi
	 * Reason : to get the bill amount details of the externally referred patients
	 * date : 7th oct 2016 */
	public static String getBillAmountForExt(PatientVO objPatVO_p, UserVO objUserVO_p){
		RegEssentialBO  essentialDelegate= new RegEssentialBO();
		return essentialDelegate.getBillAmountBasedOnCategoryForExt(objPatVO_p,objUserVO_p);
		
	}
	//end
	
	
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
	
	/*Start : Surabhi
	 * Reason : to get the episode details of the externally referred patients
	 * date : 7th oct 2016 */
	public static EpisodeVO[] getExtOldPatientEpisodesByDept(PatientVisitSUP objPatVisitSUP_p,String strCrNo_p, String strDept_p,String strRenewalType_p, UserVO objUserVO_p, String visitType)
	{
		PatientBO objPatientBO = new PatientBO();
		EpisodeVO[] vo = objPatientBO.searchExtOldPatientEpisodesByCrNoByDept(objPatVisitSUP_p,strCrNo_p, strDept_p,strRenewalType_p, objUserVO_p, visitType);
		return vo;
	}
	//end
	
	public static EpisodeVO[] getOldPatientEpisodesByUnit(String strCrNo_p, String unit,String strRenewalType_p, UserVO objUserVO_p, String visitType)
	{
		PatientBO objPatientBO = new PatientBO();
		EpisodeVO[] vo = objPatientBO.searchOldPatientEpisodesByCrNoByUnit(strCrNo_p, unit,strRenewalType_p, objUserVO_p, visitType);
		return vo;
	}
	
	/*Start : Surabhi
	 * Reason : to get the episode details of the externally referred patients
	 * date : 7th oct 2016 */
	public static EpisodeVO[] getExtOldPatientEpisodesByUnit(PatientVisitSUP objPatVisitSUP_p,String strCrNo_p, String unit,String strRenewalType_p, UserVO objUserVO_p, String visitType)
	{
		PatientBO objPatientBO = new PatientBO();
		EpisodeVO[] vo = objPatientBO.searchExtOldPatientEpisodesByCrNoByUnit(objPatVisitSUP_p,strCrNo_p, unit,strRenewalType_p, objUserVO_p, visitType);
		return vo;
	}
	// end
	
	public static Map 	savePatientVisit(PatientVO objPatVO_p, EpisodeVO[] _episodeVO, UserVO objUserVO_p,EpisodeRefDtlVO episodeRefDtlVO, EpisodeVO[] _episodeNewDeptVO, PatientVisitSUP objPatVisitSUP_p){
		PatientBO objPatientBO = new PatientBO();		
		return objPatientBO.savePatientVisit( objPatVisitSUP_p, _episodeVO, objPatVO_p, objUserVO_p,episodeRefDtlVO,_episodeNewDeptVO);  
	}
	
	public static BeneficiaryPatientVO getCreditBeneficiaryEssentials(PatientVisitSUP objPatVisitSUP_p, UserVO objUserVO_p){
		PatientBO objPatientBO = new PatientBO();		
		return objPatientBO.getCreditBeneficiaryEssentials(objPatVisitSUP_p.getPatCrNo(), objUserVO_p);
	}
	
	public static BeneficiaryPatientVO getCreditBeneficiaryEssentials(EmgPatientVisitSUP objPatVisitSUP_p, UserVO objUserVO_p){
		PatientBO objPatientBO = new PatientBO();		
		return objPatientBO.getCreditBeneficiaryEssentials(objPatVisitSUP_p.getPatCrNo(), objUserVO_p);
	}
	
	public static BeneficiaryPatientVO getCreditBeneficiaryEssentials(SplPatientVisitFB objPatVisitSUP_p, UserVO objUserVO_p){
		PatientBO objPatientBO = new PatientBO();		
		return objPatientBO.getCreditBeneficiaryEssentials(objPatVisitSUP_p.getPatCrNo(), objUserVO_p);
	}
	
	public static List getDistrict_AJAX(UserVO _UserVO,String strStateCode, String strCountryCode_p)
	{
		RegEssentialBO  essentialBO=new RegEssentialBO();
		return essentialBO.getDistrictBasedOnState_AJAX(_UserVO,strStateCode,strCountryCode_p);
	}
	
	public static List getRelationsList(UserVO _UserVO)
	{
		RegEssentialBO  essentialBO=new RegEssentialBO();
		return essentialBO.getRelationList_AJAX(_UserVO);
	}
	/*Added By Mukund on 27.07.2016*/
	public static List getLetterTypeList(UserVO _UserVO)
	{
		RegEssentialBO  essentialBO=new RegEssentialBO();
		return essentialBO.getLetterTypeList_AJAX(_UserVO);
	}
	
	/*End:Mukund*/
	public static List getClientList(UserVO _UserVO)
	{
		RegEssentialBO  essentialBO=new RegEssentialBO();
		return essentialBO.getClientList_AJAX(_UserVO);
	}
	
	public static String checkPatientIsAdmitted(PatientVisitSUP objPatVisitSUP_p, UserVO objUserVO_p){
		PatientBO objPatientBO = new PatientBO();		
		return objPatientBO.checkPatientIsAdmitted(objPatVisitSUP_p.getPatCrNo(), objUserVO_p);
	}
	
	/***
	 * get patient's detail by name
	 * @param searchName  string containing text to search
	 * @param objUserVO_p provides User Details
	 * @return array of patientVO providing episode details
	 */
	/*public static PatientVO[] getPatientDtlByName(String searchName,  UserVO objUserVO_p){
		PatientBO objPatientBO = new PatientBO();		
		return objPatientBO.searchPatientByName(searchName,objUserVO_p);
	}
	public static PatientVO getPatientDtl(PatientVO objPatVO_p,  UserVO objUserVO_p){
		System.out.println("patient detials.............."+objPatVO_p.getPatCrNo());
		PatientBO objPatientBO = new PatientBO();
		return objPatientBO.getPatientDtl(objPatVO_p,objUserVO_p);
	}
	
	public static void saveRenewalDetail(PatientVO _patientVO,String _amount, UserVO objUserVO_p,String _newExpiryDate){
		PatientBO  objPatientBO= new PatientBO();
		objPatientBO.saveRenewalDetail(_patientVO,_amount,objUserVO_p,_newExpiryDate);
		
	}
	
	public static EpisodeRefDtlVO[] getOldPatReferDtl(UserVO userVO)
	{
		PatientBO  objPatientBO= new PatientBO();
		EpisodeRefDtlVO[] arrEpisodeOldPatReferVO=objPatientBO.getOldPatReferDtl(userVO);
		return arrEpisodeOldPatReferVO;
	}
	public static String[] getDeptsForRenewal(String strCrNo_p, UserVO objUserVO_p){
		PatientBO objPatientBO = new PatientBO();		
		return objPatientBO.getDeptsForRenewal(strCrNo_p,objUserVO_p);
	}
	
	public static EpisodeVO[] saveDeptWiseRenewalAndStaming(PatientVO objPatVO_p,EpisodeVO[] _selectedEpisodeVO,EpisodeVO[] _arrRenewalEpisodeVO,EpisodeVO[] _arrEpisodeVO,EpisodeRefDtlVO _episodeRefVO,String _sysDate,UserVO objUserVO_p){
		PatientBO objPatientBO = new PatientBO();		
		return objPatientBO.saveDeptWiseRenewalAndStaming( objPatVO_p,_selectedEpisodeVO,_arrRenewalEpisodeVO,_arrEpisodeVO,_episodeRefVO,_sysDate,objUserVO_p);
	}
	public static List setRefDepartment(UserVO userVO) {
		RegEssentialBO  objRegEssentialBO= new RegEssentialBO();
		return objRegEssentialBO.getRefDepartmentList(userVO);
	}
	
	
	public static String getBillAmountByUnitGrouping(String _categoryCode,String strFromDeptUnit_p,String strToDeptUnit_p,UserVO objUserVO_p){
		RegEssentialBO  objRegEssentialBO= new RegEssentialBO();
		return objRegEssentialBO.getBillAmountBasedOnUnitGrouping(_categoryCode, strFromDeptUnit_p, strToDeptUnit_p, objUserVO_p);
		
	}
	
	public static String getBillAmountByDeptGrouping(String _categoryCode,String _fromDept,String _toDept,String entryDate, UserVO objUserVO_p){
		RegEssentialBO  objRegEssentialBO= new RegEssentialBO();
		return objRegEssentialBO.getBillAmountBasedOnDeptGrouping(_categoryCode, _fromDept, _toDept, entryDate,objUserVO_p);
		
	}*/
	/* #Start					:Sheeldarshi 
	#Modify Date(CR/PRS)	:28thMay'15 
	#Reason					: UAT Test Report Requirements:Requirement 8
	 */
	public static EpisodeRefDtlVO[] getReferEpisodeDtl(String strCrNo_p, String unit, UserVO objUserVO_p)
	{
		PatientBO objPatientBO = new PatientBO();
		EpisodeRefDtlVO[] vo = objPatientBO.getOpdReferEpisodeDtl(strCrNo_p, unit, objUserVO_p);
		return vo;
	}
	
	
	/*Start : Surabhi
	 * Reason : to get the episode details of the externally referred patients
	 * date : 7th oct 2016 */
	public static EpisodeRefDtlVO[] getExtReferEpisodeDtl(PatientVisitSUP objPatVisitSUP_p, String unit, UserVO objUserVO_p)
	{
		PatientBO objPatientBO = new PatientBO();
		EpisodeRefDtlVO[] vo = objPatientBO.getOpdExtReferEpisodeDtl(objPatVisitSUP_p, unit, objUserVO_p);
		return vo;
	}
	//end
	
	public static void continueWithExistingDuplicateRecord(UserVO userVO, PatientVO patVO, String duplicateSearchSlNo)
	{
		RegEssentialBO  objRegEssentialBO= new RegEssentialBO();
		objRegEssentialBO.continueWithExistingDuplicateRecord(userVO, patVO, duplicateSearchSlNo);
		
	}
	public static List getPaymentModeList(UserVO _UserVO)
	{
		RegEssentialBO  essentialBO=new RegEssentialBO();
		return essentialBO.getPaymentModeList_AJAX(_UserVO);
	}
	
}