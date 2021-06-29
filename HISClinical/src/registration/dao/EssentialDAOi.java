package registration.dao;



import hisglobal.tools.Tree;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

public interface EssentialDAOi {

	 public List getPrimaryCat(UserVO _userVO);
	 //public List getSecondaryCat(String _PrimaryCatCode,UserVO _userVO);
	 public List getSecondaryCat(UserVO _userVO);
	 public List getRegCategory(UserVO _userVO);
	 public List getMaritalStatus(UserVO _userVO);
	 public List getGender(UserVO _userVO);
	 public List getReligion(UserVO _userVO); 
	 public List getDepartment(UserVO _userVO,String unitType);
	 public List getLocation(UserVO _userVO);
	 public List getState(UserVO _userVO);
	 public List getCountry(UserVO _userVO);
	 public List getRefHospital(UserVO _userVO);
	 public List getAreaCategory();
	 public List getAgeType();
	 public List getAddressType(UserVO _userVO);
	
	 public List getWardBasedOnDepartment(String _deptCode,UserVO _userVO);
	 public String getBillAmountBasedOnCategory(String _deptCode,UserVO _userVO);
	 public List getUnitsWithCasuality(UserVO _userVO);
	 
	 
	 	 
	 //public List getDepartmentRevisit( String _crNo, UserVO _userVO);
	 
	 public List getNewDeptVisitDepartment( String _crNo, UserVO _userVO);
	 public List getOldDeptVisitDepartment( String _crNo, UserVO _userVO);
	 public List getVerificationDocuments(UserVO _userVO);
	 public List getOptionsCmoRegisterEssential(UserVO _userVO);
	 public List getDoctorDeskEssential();
	 public List getDepartmentWithCasuality(UserVO _userVO);
	 public Tree getDiagnosisData(UserVO _userVO);
	 public List getOptionCrNoMlcNO(UserVO _userVO);
	 public List getSearchOptions(UserVO _userVO);
	 public List getDeptUnit(String deptUnitCode,UserVO _userVO);
	 public List getRegCategoryEssential(UserVO _userVO);
	/* public List getEmergencyTypesEssential(UserVO _userVO);*/
	 public List getNationality(UserVO _userVO);
	 
	 /**
	  * Retrieves all the departments from which a patient has been referred to some other department.
	  */
	 public List getRefFromDepartment(String _crNo, UserVO _userVO);
	 
	 /**
	  * Retrieves all the departments to which a patient has been referred from some other department.
	  */
	 public List getRefToDepartment(String _crNo, UserVO _userVO);
	 
	    /**
	     * Retrieves all the departmentsof a hospital.
	     */
	    public List getAllDepartment(UserVO _userVO);
	    public List getDeathMannerEssential(UserVO _userVO);
	    public List getDiagnosisTypeEssential(UserVO _userVO);
	    public List getUnitConsultant(UserVO _userVO) ;
	  
    public List getAgeRangeList(UserVO _userVO);  
    
    public List getPreviousDiagnosisForYellowSlip(EpisodeVO episodeVO, UserVO _userVO);
    
    public List getUnitsFromRoster(UserVO _userVO);
    
    public List getRoomsByUnitCode(UserVO _userVO, String unitCode);
    
    public List getParticularRoomDetail(String deptUnitCode,String roomCode,UserVO userVO);
    
    public List getTodayVistPatListByRoom(String deptUnitCode,String roomCode,UserVO userVO);
    
    public List getAllActiveUnitsFromRoster(UserVO _userVO);
    
    public List getAllActiveRoomsByUnitCode(UserVO _userVO, String deptUnitCode);
    
    public Map getDeptUnitRoom(UserVO userVO);

    public List getUnitDateWiseRoomRosterList(String unitCode,String deactivationDate,UserVO _userVO);

    public List getTodayWorkingRoomBasedOnUnit(String departmentUnitCode,UserVO userVO);

    public List getDeptWhoseFileIsRequired(UserVO userVO);
    
    public List getUnitWhoseFileIsRequired(UserVO userVO);
}
