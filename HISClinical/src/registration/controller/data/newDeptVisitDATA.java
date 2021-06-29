package registration.controller.data;

import hisglobal.exceptions.HisException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerDATA;
import hisglobal.presentation.Status;
import hisglobal.vo.EpisodeRefDtlVO;
import hisglobal.vo.EpisodeReferVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import registration.RegistrationConfig;
import registration.bo.delegate.*;


public class newDeptVisitDATA extends ControllerDATA {
	/**
	 * gets all new registration essentials
	 * @param _UserVO Provides User details.
	 * @return map containing all new registraion essenials
	 */
	public static Map getAllNewRegistrationEssentials(UserVO _UserVO)
	{
		EssentialDelegate  essentialDelegate=new EssentialDelegate();
		Map mp=essentialDelegate.getNewPatientRegEssential(_UserVO);
		return mp;		
	}
	
	/**
	 * get patient details
	 * @param _patVO Provides patient details.
	 * @param _userVO Provides User details.
	 * @return  object of patientVO
	 */
	public static PatientVO getPatientDtl(PatientVO _patVO, UserVO _userVO){
	   	PatientDelegate  patientDelegate= new PatientDelegate();
		_patVO=patientDelegate.searchPatientByCrNo(_patVO,_userVO);
		return _patVO;
	}
	
	/**
	 * gets patient status
	 * @param _patVO  Provides patient details.
	 * @param _userVO Provides User details.
	 * @return string defining patient status
	 */
	public static String getPatientStatus(PatientVO _patVO, UserVO _userVO){
	    PatientDelegate  patientDelegate= new PatientDelegate();
	    String patStatus="";
	    patStatus=patientDelegate.checkPatientStatus(_patVO,_userVO);
	    return patStatus;
	}
	/**
	 * gets new department to be visited
	 * @param _patVO  Provides patient details.
	 * @param _episodeVO  Provides patient's episode details.
	 * @param _userVO Provides User details.
	 * @return  array of episodeVO
	 */
	public static EpisodeVO[] 	newDepartmentVisit(PatientVO _patVO, EpisodeVO[] _episodeVO, EpisodeRefDtlVO episodeRefVO, UserVO _userVO,PatientVO _oldPatientVO){
		PatientDelegate patDelegate = new PatientDelegate();
		int l=_episodeVO.length;		
		return patDelegate.newDepartmentVisitStamp(_episodeVO, _patVO, episodeRefVO, _userVO,_oldPatientVO);
	}
	/**
	 * gets new department to be visited
	 * @param _patVO  Provides patient details.
	 * @param _episodeVO  Provides patient's episode details.
	 * @param _userVO Provides User details.
	 * @return  array of episodeVO
	 */
	public static EpisodeVO[] 	newSplDepartmentVisit(PatientVO _patVO, EpisodeVO[] _episodeVO, EpisodeRefDtlVO episodeRefVO, UserVO _userVO,PatientVO _oldPatientVO){
		PatientDelegate patDelegate = new PatientDelegate();
		int l=_episodeVO.length;		
		return patDelegate.newSplDepartmentVisitStamp(_episodeVO, _patVO, episodeRefVO, _userVO,_oldPatientVO);
	}
	/**
	 * get all initial new department visit essentials
	 * 	@return map containing  all initial new department visit essentials
	 */
	public static Map getAllInitialNewDeptVisitEssentials(){
		Map mp = new HashMap();
		mp.put(Config.ESSENTIALBO_OPTION_DEPARTMENT, new ArrayList());
		mp.put(RegistrationConfig.ESSENTIALBO_OPTION_NEW_DEPT_VISIT_DEPARTMENT, new ArrayList());
		mp.put(RegistrationConfig.ESSENTIALBO_OPTION_OLD_DEPT_VISIT_DEPARTMENT, new ArrayList());
		return mp;
	}
	/**
	 * gets all new department visit essentials
	 * @param crNo string  containing patient's cr no
	 * @param _UserVO Provides User details.
	 * @return  map containing all  new department visit essentials
	 */
	public static Map getAllNewDeptVisitEssentials(String crNo,UserVO _UserVO) {
		
		
		EssentialDelegate  essentialDelegate=new EssentialDelegate();
		
		Map mp = essentialDelegate.getNewDeptVisitEssential(crNo,_UserVO);
		
		try{
		//create a new collection with departments - already Visisted
			
		// Collection colAllDept = (Collection)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_DEPARTMENT);
		
		// Collection colAvailableDept = (Collection)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_NEW_DEPT_VISIT_DEPARTMENT);
				
		// Collection colVisitedDept = (Collection)colAllDept.getClass().newInstance();
		//colVisitedDept.addAll(colAllDept);
		//colVisitedDept.removeAll(colAvailableDept);
		
		//mp.put(RegistrationConfig.ESSENTIALBO_OPTION_OLD_DEPT_VISIT_DEPARTMENT, colVisitedDept);		
		
		}catch(Exception e){throw new HisException("getAllNewDeptVisitEssentials:  "+e);}
	    return mp; 				
	}
	/**
	 * gets all new department visit essentials
	 * @param crNo string  containing patient's cr no
	 * @param _UserVO Provides User details.
	 * @return  map containing all  new department visit essentials
	 */
	public static Map getAllSplNewDeptVisitEssentials(String crNo,UserVO _UserVO) {
		
		
		EssentialDelegate  essentialDelegate=new EssentialDelegate();
		
		Map mp = essentialDelegate.getNewSplDeptVisitEssential(crNo,_UserVO);
		
		try{
		//create a new collection with departments - already Visisted
			
		// Collection colAllDept = (Collection)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_DEPARTMENT);
		
		// Collection colAvailableDept = (Collection)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_NEW_DEPT_VISIT_DEPARTMENT);
				
		// Collection colVisitedDept = (Collection)colAllDept.getClass().newInstance();
		//colVisitedDept.addAll(colAllDept);
		//colVisitedDept.removeAll(colAvailableDept);
		
		//mp.put(RegistrationConfig.ESSENTIALBO_OPTION_OLD_DEPT_VISIT_DEPARTMENT, colVisitedDept);		
		
		}catch(Exception e){throw new HisException("getAllNewDeptVisitEssentials:  "+e);}
	    return mp; 				
	}
	/**
	 * gets Secondary Category Essentials
	 * calls getSecondaryCategory() from EssentialDelegate
	 * @param _crno string containing patient's cr no
	 * @param _UserVO Provides User details.
	 * @param _objStatus
	 * @return List containing Secondary Category Essentials
	 */
	
	public static List getSecondaryCatEssential(String _crno, UserVO _UserVO){
		EssentialDelegate  essentialDelegate=new EssentialDelegate();
	    List secondaryCatList=essentialDelegate.getSecondaryCategory(_crno,_UserVO);
	    return secondaryCatList;
	}
	
	/**
	 * checks open episodes 
	 * @param _patVO Provides patient's details.
	 * @param _userVO Provides User details.
	 * @return boolean value returning whether the episode is open or closed
	 */
	public static boolean getEmgOpenEpisodeCheck(PatientVO _patVO, UserVO _userVO){
	    PatientDelegate  patientDelegate= new PatientDelegate();
		return (patientDelegate.checkOpenEmgEpisodeByCrNo(_patVO,_userVO));
	}
	
	
	public static Map getMapDeptWiseFileNo(String _crno, UserVO _UserVO){
		PatientDelegate  patientDelegate= new PatientDelegate();
		Map mapDeptWiseFileNo=patientDelegate.getMapDeptWiseFileNo(_crno,_UserVO);
	    return mapDeptWiseFileNo;
	}
	
	public static String getNewDeptVisitAmount(String crNo,String primCatCode,String deptcode, UserVO _userVO){		
	    return new PatientDelegate().getNewDeptVisitAmount(crNo,primCatCode,deptcode,_userVO);		
	}
	
	public static EpisodeRefDtlVO[] getOpenEpisodeOPD(String crNo, UserVO _userVO){
		PatientDelegate patientDelegate = new PatientDelegate();
		EpisodeRefDtlVO[] arrOpenEpisodeVO =patientDelegate.retrieveAllOpenOPDEpisodes(crNo, _userVO);
	    return arrOpenEpisodeVO;
	}
	
	public static String getBillAmount(String _patCategoryCode, UserVO _userVO){
		EssentialDelegate  essentialDelegate= new EssentialDelegate();
		return essentialDelegate.getBillAmountBasedOnCategory(_patCategoryCode,_userVO);
		
	}
	
	public static void saveRenewalDetail(PatientVO _patientVO,String _amount, UserVO _userVO,String _newExpiryDate){
		PatientDelegate  patientDelegate= new PatientDelegate();
		patientDelegate.saveRenewalDetail(_patientVO,_amount,_userVO,_newExpiryDate);
		
	}
	
	public static String getNextDate(){ 
		EssentialDelegate  essentialDelegate= new EssentialDelegate();
		return essentialDelegate.getNextDate();
		
	}
	public static EpisodeRefDtlVO[] getReferPat(UserVO _userVO)
	{
		PatientDelegate patientDelegate= new PatientDelegate();
		EpisodeRefDtlVO[] arrEpisodeRefDtlVO=patientDelegate.getReferPat(_userVO);
		return arrEpisodeRefDtlVO;
	}
	
	public static String getBillAmountByUnitGrouping(String _categoryCode,String _fromDeptUnit,String _toDeptUnit,UserVO _userVO){
		EssentialDelegate  essentialDelegate= new EssentialDelegate();
		return essentialDelegate.getBillAmountBasedOnUnitGrouping(_categoryCode, _fromDeptUnit, _toDeptUnit, _userVO);
		
	}
		
	public static String getBillAmountByDeptGrouping(String _categoryCode,String _fromDept,String _toDept,String entryDate, UserVO _userVO){
		EssentialDelegate  essentialDelegate= new EssentialDelegate();
		return essentialDelegate.getBillAmountBasedOnDeptGrouping(_categoryCode, _fromDept, _toDept, entryDate,_userVO);
		
	}
	
	public static String getBillAmountBasedOnUnitGrouping(String _categoryCode,String _fromDept,String _toDept,String entryDate, UserVO _userVO){
		EssentialDelegate  essentialDelegate= new EssentialDelegate();
		return essentialDelegate.getBillAmountBasedOnUnitGrouping(_categoryCode, _fromDept, _toDept,_userVO);
		
	}
	
	
}