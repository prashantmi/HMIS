package opd.bo;

import hisglobal.utility.Entry;
import hisglobal.vo.AudioVideoMasterVO;
import hisglobal.vo.ChartUnitMapppingVO;
import hisglobal.vo.ConsentRequestVO;
import hisglobal.vo.ConsultationDtlVO;
import hisglobal.vo.DeskDetailVO;
import hisglobal.vo.DeskMasterVO;
import hisglobal.vo.DeskMenuMacroMstVO;
import hisglobal.vo.DeskMenuMasterVO;
import hisglobal.vo.DeskTypeMenuMappingVO;
import hisglobal.vo.DisclaimerMstVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.EstimateCertificateReqVO;
import hisglobal.vo.IcdDiseaseMasterVO;
import hisglobal.vo.IcdHospitalMasterVO;
import hisglobal.vo.IcdSubgroupMasterVO;
import hisglobal.vo.OpdClinicalDetailVO;
import hisglobal.vo.OpdPatientImageDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientProfileDetailVO;
import hisglobal.vo.TemplateMasterVO;
import hisglobal.vo.UnitDrugListMasterVO;
import hisglobal.vo.UnitDrugMstVO;
import hisglobal.vo.UnitExtTreatMstVO;
import hisglobal.vo.UnitImageDescMasterVO;
import hisglobal.vo.UnitWiseMacroMstVO;
import hisglobal.vo.UserDeskMenuMasterVO;
import hisglobal.vo.UserDeskMenuTemplateMasterVO;
import hisglobal.vo.UserDeskUnitWardMappingMasterVO;
import hisglobal.vo.UserVO;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface OpdEssentialBOi
{

	public Map getDeptIcdEssential(UserVO _userVO);
	
	/**
	 * @param _userVO
	 * @return
	 */
	public Map getDeptHosDisEssential(UserVO _userVO);

	public Map getDeptIcdRemovalDetails(String _choice, String _code, UserVO _userVO);
	
	public Map getDeptHosDisRemovalDetails(String _choice, String _code, UserVO _userVO);

	// *
	public DeskDetailVO[] getOpdMenuDetail(UserVO _userVO, String location, String unitCode, String deskType);

	// public Map getEssentials(UserVO _userVO);
	// *
	public PatientDetailVO[] getTodayPatientList(UserVO _userVO, String unitCode, String roomCode);

	// *
	public List opdDeskEssentials(UserVO _userVO);

	// * Getting Essentials for Adding into User Desk Menu Master
	public Map getAddUserDeskMenuMasterEssentials(UserVO _userVO);
	
	public Map getDeptAndUnit(UserVO _userVO);

	// Getting Clinical Dept & Unit List Mode Wise 

	/*public Map getDeptNUnitModeWise(String _mode, String _deskId, UserVO _userVO);*/
	public Map getDeptNUnitModeWise(String _mappingType, String _deskId, UserVO _userVO);

	// Getting All Group & Seat List Mode Wise
	public Map getGroupNSeatModeWise(String _mode, String _deskId, List<Entry> _units, List<Entry> _wards, UserVO _userVO);

	// *
	public List getRoomsByUnitCode(UserVO _userVO, String unitCode);

	// *
	public String getOpdPatientCount(UserVO _userVO, String unitCode, String roomCode);

	public String getAttendedOpdPatientCount(UserVO _userVO, String unitCode, String roomCode);

	// * Getting Essentials for Add-Modify Menus To Desk Master
	public Map getAddModifyMenuToDeskMasterEssentials(String _deskType, UserVO _userVO);

	public Map getIcdDiagnosisEssential(UserVO _userVO, String _patCrNo, String _episodeCode);

	//public Map getHospitalDiagnosisEssential(UserVO _userVO, String _patCrNo, String _episodeCode);

	public List getIcdCodes(String _searchIcdCode, UserVO _userVO);

	public List getDiseaseName(String _searchDisease, UserVO _userVO);

	/**
	 * Getting List of ICD Diseases on the basis of Search Criteria
	 * 
	 * @param _strSearch Search String either ICD Code or Disease Name Segment
	 * @param _strSearchType Search Style either Code-based or Name-based
	 * @param _userVO User Detail
	 * @return List ICD Disease Detail
	 */
	public List<IcdDiseaseMasterVO> getICDCodesSearchDetail(String _strSearch, String _strSearchType, UserVO _userVO);

	/**
	 * Getting List of ICD Sub Diseases on the basis of Clicked Parent ICD Disease Code
	 * 
	 * @param _strICDCode Clicked ICD Code
	 * @param _userVO User Detail
	 * @return List of ICD Sub Disease
	 */
	public List<IcdDiseaseMasterVO> getICDSubDiseases(String _strICDCode, UserVO _userVO);

	public List getHospitalDiagnosisName(String _searchDisease, UserVO _userVO);

	public List getHospitalDiagnosisCodes(String _searchCode, UserVO _userVO);

	// * Getting UserDesk Menu Master Record
	public UserDeskMenuMasterVO getModifyViewUserDeskMenuMstVO(UserDeskMenuMasterVO _UserDeskVO, UserVO _UserVO);

	// * Getting Seats not assigned to given Department Units
/*	public List getAddUserDeskMenuMasterSeatsByNotUnits(String[] _UnitsList, String deskType, UserVO _userVO,String groupCode);*/
	// Change By Chetan According to Global Mapping Concept
	public List getAddUserDeskMenuMasterSeatsByNotUnits(String deskType, UserVO _userVO,String groupCode);
	
 	//Get seats without deskType
	public List getAddUserDeskMenuMasterSeatsByNotUnits(String[] _WardsList, UserVO _userVO,String groupCode);

	// * Getting All Desk By given Desk Type
	public List getAddUserDeskMenuMasterDeskByType(String _DeskType, UserVO _userVO);

	public Map getReferPatientEssentials(String _crNO, String _deptCode, UserVO _userVO, String deskType, String episodeCode);

	public Map getAllergyType(UserVO _userVO);

	public List getAllergiesEssential(String _allergyCode, UserVO _userVO);

	public Map getEConsultionEssentials(ConsultationDtlVO consultationDtlVO, UserVO _userVO);

	public ConsultationDtlVO[] getconsultationInboxEssentials(UserVO _userVO);

	public Map getConsultionInboxEssentials(UserVO _userVO);

	public Map getDocumentUploadEssentials(String patCrNo,String episodeCode, UserVO _userVO);

	public Map getPatientBelongingEssentials(String patCrNo, UserVO _userVO);

	// * Getting Parameter List
	public Map getAddTempleteParameterMasterParaList(UserVO _userVO);

	// * Getting Templates List
	public List getEntryTemplateAllTempList(UserVO _userVO);

	// Template Parameter List By Template Id
	public List getTemplateParaListByTempId(String _tempId, UserVO _userVO);

	// * Getting Template Parameter Detail List
	public List getTemplateParaDetailListByTempId(String _tempId, UserVO _userVO);

	// * Getting Parameter Dynamic Data
	public List getParameterDynamicData(String _query, UserVO _userVO);

	// Patient Para CR No List
	public Map getAllPatientParaCrNoList(UserVO _userVO);

	// Setting Template Report Essentials
	public Map getSetTemplateReportEssentials(String _crNo, UserVO _userVO);

	// Getting Actual Template Ids
	public List getActualTempIds(String _crNo, String[] _aPV, UserVO _userVO);

	// Getting Actual Parameter Values
	public List getPatActualParaValues(String _crNo, String[] _aPV, UserVO _userVO);

	// Getting Patient Parameter Values
	public List getPatParaValues(String _crNo, String[] _aPV, UserVO _userVO);

	// * Getting Desk Menu Template List for Current Active Desk
	public List getOpdDeskMenuTemplates(String _unitCode, String _deskMenuId, UserVO _UserVO);
//////////////////////
	// * Getting Essentials for various modes
	public Map getAddUserDeskMenuTemplateUnitWiseEssentials(UserVO _userVO);
	
	// * Getting all desks during desk wise mode
	public Map getAllDeskEssentials(UserVO _userVO);
	
	public Map getAllDeskBasedOnDeskType(String deskType,UserVO _userVO);
	
	public Map getMappedUnits(String deskId,UserVO _userVO);
	
	public Map getMappedUnitsForUnitSeat(String deskId,UserVO _userVO);
	
	public Map getMappedUnitsForUnitSeatWard(String deskId,UserVO _userVO);
	
	public Map getMappedUnitsForWard(String deskId,UserVO _userVO);
	
	public Map getAllDeskTypeEssentials(UserVO _userVO);
	
	// * Getting User Seats from User Desk Menu Master common to all Selected
	// Units(unit-seat wise mode)
	public List getAddUsrDskMnuTempSeatsByInAllUnits(String[] _UnitsList, UserVO _userVO);
	
	//Get seats for unit-ward-seat mode
	public List getAddUsrDskMnuTempSeats(String[] _UnitsList,String[] _WardsList, UserVO _userVO);
		
	// * Getting User Seats from User Desk Menu Master common to all Selected
	// Units (unit-ward-seat mode)
	public UserDeskMenuTemplateMasterVO getAddUsrDskMnuTempSeats(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _userVO);

	public UserDeskMenuTemplateMasterVO getAddUsrDskMnuTempWards(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _userVO);
	
	// * Getting Desk List By Seats and Units (unit-seat wise mode)
	public List getAddUsrDskMnuTempdeksInUnitsnSeats(String[] _UnitsList, String[] _SeatsList, UserVO _userVO);
	
	//Getting desk list for unit-ward wise mode
	public List getAddUsrDskMnuTempdeksInUnitsnWards(String[] _UnitsList, String[] _WardsList, UserVO _userVO);
	
	//Getting desk list for unit-ward-seat wise mode
	public List getAddUsrDskMnuTempdeksInUnitsnWardsSeat(String[] _UnitsList, String[] _WardsList,String[] _SeatsList, UserVO _userVO);
	
	//Getting ward list for which USERSEATID is null
	public List getAddUsrDskMnuTempWardsInUnits(String[] _UnitsList, UserVO _userVO);
	
	//Get wardlist for unit-ward-seat mode (USERSEATID not null)
	public List getAddUsrDskMnuTempWardsForWardSeatWise(String[] _UnitsList, UserVO _userVO);

	// * Getting Desk List for unitwise mode
	public List getAddUsrDskMnuTempdeksInUnits(String[] _UnitsList, UserVO _userVO);

	// * Getting Desk Menu that are Template-Based by Desk Id
	public List getAllTemplateBasedDeskMenusByDeskId(String _deskId, UserVO _UserVO);

	// * Getting Templates By Unit,Seat,Desk Id
	public List getTemplatesByUnitSeatDesk(UserDeskMenuTemplateMasterVO voUDMT, UserVO _UserVO);
	
	// * Getting Templates By Unit,Desk Id
	public List getTemplatesByUnitDesk(UserDeskMenuTemplateMasterVO voUDMT, UserVO _UserVO);
	
	// * Getting Templates By Unit,Desk Id
//	public List getTemplatesByDesk(UserDeskMenuTemplateMasterVO voUDMT, UserVO _UserVO);

	// * Getting Clinical Template Data
	public List getClinicalTemplateData(OpdClinicalDetailVO voClnData, UserVO _UserVO);

	// * Getting Previous Visit Dates List
	public List getPrevVisitDates(String _patCrNo, String _episodeCode, UserVO _userVO);

	// * Getting All Parameters List of given Templates
	public List getOpdDeskTemplatesAllParas(List tempIds, UserVO _userVO);

	// * Getting Template Parameter Names
	public List getOpdTemplParaNames(List paraIds, UserVO _userVO);

	// * Getting Previous Visits In Between Given Dates
	public List getPrevVisitsInBetween(String _patCrNo, String _episodeCode, String _fromDate, String _toDate, UserVO _userVO);

	// * Getting OPD Clinical Data By Selected Parameters
	public List getOpdClinDataBySelParas(OpdClinicalDetailVO voOpdCD, String qryVisits, String paraQuery, UserVO _userVO);
	
	// * Getting Desk Type List
	public List getDeskType(UserVO _UserVO);

	// * Getting Desk Type Description
	public String getDeskTypeDesc(String _deskType, UserVO _userVO);

	public AudioVideoMasterVO[] getAudioVideoEssentials(String _unitCode, UserVO _userVO);

	public List getDeskMenuBasedOnDeskType(String _deskType, UserVO _UserVO);

	public DeskMenuMacroMstVO[] getMacroHead(String _deskType, String _deskMenu, UserVO _UserVO);

	public List getAllUnit(UserVO _UserVO);

	public void deleteMacroHead(DeskMenuMacroMstVO _deskMenuMacroMstVO, UserVO _UserVO);

	public List getAllImageDesc(UserVO userVO);

	public Map getUnitNotInTable(UserVO userVO);

	public void addImageDescription(String imageDesc, UserVO userVO);

	//* Getting Unit Image Master ADD Essentials
	public Map getAddUnitImageMasterEssentials(UserVO _userVO);

	//* Getting Unit Image Master MODIFY Essentials
	public Map getModifyUnitImageMasterEssentials(String _deptUnitCode, UserVO _userVO);

	// Getting Image Examination Essentials
	public Map getImageExaminationEssentials(String _unitCode, OpdPatientImageDtlVO _patImgVO, UserVO _userVO);

	// Getting Image Log Detail
	public OpdPatientImageDtlVO getImageLogDetail(String _imageFileName, UserVO _userVO);

	// * Getting Images List of Current Unit
	public List getOPDImagesListOfUnit(String _unitCode, UserVO _UserVO);

	// * Getting Editor Essentials e.g. Color-Description List 
	// * Getting Description List of Colors in String and Format
	// * Color1:Desc1#Color2:Desc2.....
	public String getUnitsImageColorDesc(String _unitCode, UserVO _UserVO);

	
	public List getAllAudioVideoFile(UserVO userVO);

	public List getAllAudioVideoFileHeader(UserVO userVO);
	
	
	
	// Getting All Service Essensials
	/*public Map getServiceEssentials(UserVO _userVO, DailyPatientVO selectedPatientVO);*/

	// Getting department wise ServiceArea List
	/*public List getDeptWiseServiceAreaList(ServiceAreaVO _serviceAreaVO, UserVO _userVO);*/

	// Getting All service area wise Service List
	/*public List getServiceAreaWiseServiceList(ServiceVO _serviceVO, UserVO _userVO);*/
	
	//getting service wise  all the parameters	
	/*public List getServiceWiseParameters(ServiceVO _serviceVO,UserVO _userVO);*/

	// Getting template details and service Area and service Details
	/*public Map TemplateDtl(ServiceAreaVO _serviceAreaVO, ServiceVO _serviceVO, UserVO _userVO);*/

	// ///////////////opd next visit appointment///////////////////
	/*public Map getSlotDtl(Apt_slotDtlVO _slotDtlVO,UserVO userVO);*/

	/*public Apt_slotDtlVO getNextSlotDate(Apt_slotDtlVO _slotDtlVO,UserVO uservo);*/

	
	
	
	public List getAllUnitNotInTable(UserVO userVO);
	
	public List getAllDept(UserVO userVO);

	// Getting Casulaity Desk Essentials
	public List csultyDeskEssentials(UserVO _userVO);

	// Getting Casuality Patients List
	public PatientDetailVO[] getCsultyPatientList(String _unitCode, String _roomCode, UserVO _userVO);
	
	// Getting Roles List
	public List getRoleList(UserVO _userVO);

	// Getting ICD Hospital Disease Essentials
	public  List getIcdHospitalMasterEssentails(UserVO _userVO);
	
	// Getting ICD Disease List By Hospital Disease Code
	public  List fetchDiseaseList(IcdHospitalMasterVO _diseaseMasterVO,UserVO _userVO);

	
	public List getUnitExceptAssignedByDeskType(String _deskType,UserVO _userVO);

	//Getting Is Default status for OPD Desk Master
	public boolean getisDefault(DeskMasterVO _deskMstVO,UserVO _userVO);
	
	
	public boolean getisDefaultGlobal(DeskMasterVO _deskMstVO,UserVO _userVO);
	
	
	public List getAllGroupList(UserVO _userVO);

	
	public List getUnitExceptTemplate(UserVO _userVO);

	
	// * Getting Essentials for Adding into User Desk Unit Ward Mapping Master
	public Map getAddUserDeskUnitWardMappingMasterEssentials(UserVO _userVO);
	
	public List getUnitExceptAssignedByDeskTypeForUnitWardMappingMaster(String _deskType,UserVO _userVO);
	
	public List getWardExceptAssignedByDeskTypeForUnitWardMappingMaster(String _deskType,UserVO _userVO,String deptCode);
	
	public List getWardExceptAssignedByDeskType(String _deskType,UserVO _userVO,String unitId);
	
	public List getWardExceptAssignedByDeskTypeForUnitWardSeat(String _deskType,UserVO _userVO,String unitId);
	
	// * Getting Seats not assigned to given Wards
	public List getSeatsByNotWards(String[] _WardsList, String deskType, UserVO _userVO,String groupCode);

	public List getAllWardInUnitWardSeatMode(String _deskType,UserVO _userVO,String unitId);
	
	// * Getting UserDesk Unit Ward Mapping Master Record
	public UserDeskUnitWardMappingMasterVO getModifyViewUserDeskUnitWardMappingMstVO(UserDeskUnitWardMappingMasterVO _UserDeskVO, UserVO _UserVO);


	//Getting Service Type List
	public List getServiceTypeList(UserVO _UserVO);

	//Getting List of All Allergy Site 
	public List getAllAllergySite(UserVO userVO);

	
	// Getting List of All Common & Allergy Type Symptom
	public Map getCommonNAllergyTypeSymptom(String allergyTypeCode,UserVO userVO);
	
	public Map getEssentials(ConsentRequestVO consentRequestVO,UserVO _userVO);
	
	public UserDeskMenuMasterVO getSeats(UserDeskMenuMasterVO _voUDM, UserVO _userVO);

	//* Getting Unit Name
	public UserDeskMenuTemplateMasterVO getUnitName(String _deptUnitCode,UserVO _UserVO);
	
	public UserDeskUnitWardMappingMasterVO gettingWards(UserDeskUnitWardMappingMasterVO _voUDMT, UserVO _userVO);
	
	public UserDeskUnitWardMappingMasterVO gettingSeats(UserDeskUnitWardMappingMasterVO _voUDMT, UserVO _userVO);
	
	public void updateDeskUnitWardMappingMaster(UserDeskUnitWardMappingMasterVO _voUDMT, UserVO _userVO);
	
	public Map getEpisodeRoomChangeEssential(UserVO _userVO,String _unitCode,String _roomCode,PatientDetailVO patDtlVO);
	

	public List<TemplateMasterVO> getAllTemplatesNotAdded(String deskId,UserVO userVO);

	//getting desk menu master essential
	public List getDeskListByUnit(String unitCode,UserVO userVO);
	
	public Map getAllUnitForMapping(UserVO userVO);
	
	public Map getUnitSeatWise(UserVO userVO);
	
	public List getSeatListByUnit(String _deskId, String unitCode, UserVO userVO);
	
	public List getDeskListByUnitNSeat(String seatId,String unitCode,UserVO userVO);
	
	public Map getUnitForWardWise(UserVO userVO);
	
	public Map getUnitForWardSeatWise(UserVO userVO);
	
	public List getWardListByUnit(String _deskId, String unitCode, UserVO userVO);
	
	public List getDeskListByUnitNWard(String wardCode,String unitCode,UserVO userVO);
	
	public List getWardListByUnitForUWS(String _deskId, String unitCode, UserVO userVO);
	
	public List getSeatListByUnitNWard(String _deskId, String wardCode, String unitCode, UserVO userVO);
	
	public List getDeskListByUnitNWardNSeat(String seatId,String wardCode,String unitCode,UserVO userVO);

	public Map getDeskMenuMasterEssentails(UserVO _userVO);
	
	public Map getDrugDoseMasterEssentails(UserVO _userVO);
	
	//Added By : Chetan 
	//Date : 07-12-2015
	
	public Map getDrugRouteMasterEssentails(UserVO _userVO);

	/**
	 * Getting Patient Treatment Detail Essentials
	 * @param _patCrNo Patient CR No.
	 * @param _episodeCode Episode Code
	 * @param _userVO User VO 
	 * @return Map of Essentials
	 */
	//public Map getPatTreatmentDetailEssential(String _patCrNo, String _episodeCode, String depUnitCode ,String genderCode, UserVO _userVO);
	
	public List<TemplateMasterVO> getAllTemplatesVO(UserVO userVO);
	
	public DeskMenuMasterVO[] getMenuNameBasedOnDeskId(String deskId,UserVO userVO);
	
	/** List of Alert Names that are not assigned to the Patient
	 * @param crNo
	 * @param userVO
	 * @return
	 */
	public List getAllPatAlerts(String crNo,UserVO userVO);
	
	public List getAdvice(UserVO userVO);
	
	public String getCsultyTotalPatCount(UserVO userVO,String unitCode,String roomCode);
	
	public String getCsultyTodayAdmPatCount(UserVO userVO,String unitCode,String roomCode);
	
	public List getUserBasedOnGroup(String groupCode,UserVO _userVO);

	/**
	 * Getting Patient Episode Essentials
	 * @param _patProfileVO Patient Profile Process Details
	 * @param _userVO User Detail
	 * @return Map of Essentials of the Process
	 */
	public Map<String, Object> getPatientProfilesEssentials(PatientProfileDetailVO _patProfileVO, UserVO _userVO);

	/**
	 * Getting Serach Result of Users for the Profile Access Priviledges
	 * @param _mode Search Mode
	 * @param _str Search String 
	 * @param _userVO User Detail
	 * @return
	 */
	public List<UserVO> getSearchUsersForProfileAccessPrivil(String _mode, String _str, UserVO _userVO);

	
	public Map getOfflineConsentEssentials(ConsentRequestVO consentRequestVO,UserVO _userVO);
	
	public  Map getDeskTypeMenuMappingMstEssentails(DeskTypeMenuMappingVO deskTypeMenuMappingVO,UserVO _userVO);


	public List getParameterForExtInv(UserVO userVO);

	/**
	 * Getting Profile Bound
	 * @param _str desk type String 
	 * @param _userVO User Detail
	 * @return
	 */
	public String getProfileBound(String _deskType,UserVO userVO);
	
	public DisclaimerMstVO fetchDisclaimerDetails(String _deptUnitCode,String profileType, UserVO _userVO);

	/**
	 * Getting Visit Summary Essentials
	 * @param _episodeVO Episode Detail VO
	 * @param _userVO User Detail VO
	 * @return Map of Essentials
	 */
	public Map getVisitSummaryEssentials(EpisodeVO _episodeVO, UserVO _userVO);

	// Getting Dynamic Previous Visit Summary Detail
	public LinkedHashMap<String, List<List<Object>>> getDynamicVisitSummaryDetail(EpisodeVO _episodeVO, List<String> _lstMenuIds, UserVO _userVO);

	/**
	 * Getting Macros of given Process Id
	 * @param _processId Process Id
	 * @param _userVO User Detail
	 * @return
	 */
	public List<Entry> getMacrosListByProcessId(String _processId, UserVO _userVO);

	/**
	 * Getting Macros of given Process Id & Unit 
	 * @param _processId Process Id
	 * @param _unitCode Unit Code
	 * @param _userVO User Detail
	 * @return
	 */
	public List<Entry> getUnitMacrosByProcessId(String _processId, String _unitCode, UserVO _userVO);

	/**
	 * Getting Schedule Dates Unit User Date Wise
	 * @param _deptUnitCode Unit Code
	 * @param _userId User ID
	 * @param _date Date
	 * @param _userVO User Detail
	 * @return List of Schedule Dates
	 */
	public List<Entry> getOpdRosterSchedule(String _deptUnitCode, String _userId, String _date, UserVO _userVO);
	
	public Map fetchRestrictedCategoryEssentials(UserVO _userVO);
	
	public Map getIcdNHospitalDiagnosisEssential(UserVO _userVO, String _patCrNo, String _episodeCode);

	public Map getExtTreatMasterEssential(UserVO userVO);
	public Map getDrugMasterEssential(UserVO userVO);
	
	public Map getUnitExtTreatForModify(UnitExtTreatMstVO vo, UserVO userVO);
	
	public Map getUnitDrugLisyForModify(UnitDrugMstVO vo, UserVO userVO);
	
	public Map getPatientAddedAttendant(String strPatCrNo_p, String strEpisodeCode_p, UserVO strUserVO_p);
	
	public EpisodeVO[] getAllEpisodeOfThePatientTodayVisited(String crNo,UserVO userVO);

	public Map getUnitMacroMasterEssential(UserVO userVO);

	public Map getUnitExtTreatForModify(UnitWiseMacroMstVO vo, UserVO userVO);

	public Map getPatientCategoryForProfileType(String profileType,UserVO _uservo);

	public Map getUnitImageDescForModify(UnitImageDescMasterVO vo, UserVO userVO);

	public Map getUnitDrugListMasterEssential(UserVO userVO);

	public Map getUnitMacroForModify(UnitDrugListMasterVO vo, UserVO userVO);

	public List getParticularDrugListDtl(String drugListId, UserVO userVO);

	// Getting Unit Keyword ADD Essentials
	public Map getUnitEpisodeKeywordEssentials(UserVO _userVO);
	
	// Getting Unit Episode Keyword MODIFY Essentails
	public Map getModifyUnitEpisodeKeywordEssentials(String _deptUnitCode, UserVO _userVO);

	// Getting Unit Episode Keyword VIEW Essentails
	public Map getViewUnitEpisodeKeywordEssentials(String _deptUnitCode, UserVO _userVO);

	// Getting ICD Include Exclude Essentails
	public Map getICDIncludeExcludeEssential(String _groupCode, String _subgroupCode, String _diseaseCode, UserVO _userVO);

	// Getting ICD Code Essentials
	public Map getICDAllEssentials(UserVO _userVO);

	// Getting Disease Site Essentails
	public Map getDiseaseSiteEssential(UserVO _userVO);

	// Get Subgroups Group Wise
	public List<IcdSubgroupMasterVO> getSubGroupsByGroup(String _icdGroupCode, UserVO _userVO);

	// Get Disease SubGroup Wise
	public List<IcdDiseaseMasterVO> getDiseaseBySubGroup(String _icdSubgroupCode, UserVO _userVO);

	/**
	 * Getting Generic Chart Reporting Essentials
	 * 
	 * @param _deskType Desk Type
	 * @param _unitCode Department Unit Code
	 * @param _patDtlVO Patient Detail
	 * @param _userVO User Detail
	 * @return Map of Essentials
	 */
	public Map<String, Object> getChartReportingEssentials(String _deskType, String _unitCode, PatientDetailVO _patDtlVO, UserVO _userVO);
	
	/**
	 * Getting All Chart 
	 * 
	 * @param userVO
	 * @return
	 */
	public Map getChartUnitListEssential(UserVO _userVO);

	public Map getChartUnitListForModify(ChartUnitMapppingVO _vo, UserVO _userVO);
	
	public Map getDeptUnitListEssential(UserVO _userVO);
	public Map getEssential(UserVO _userVO);
	public Map getIcdDiseaseList(UserVO _userVO);
	public Map getDeptUnitEssential(UserVO _userVO);
	public Map getHospitalDisease(UserVO _userVO);
	
	/**
	 * Getting Hospital Disease For IcdMapping
	 * 
	 * @param _userVO User Detail
	 * @return Map of Hospital Disease
	 */
	public Map getHospitalDiseaseForIcdMapping(UserVO _userVO);
	
	/**
	 * Getting Chronic Disease For IcdMapping
	 * 
	 * @param _userVO User Detail
	 * @return Map of Chronic Disease
	 */
	public Map getChronicDiseaseForIcdMapping(UserVO _userVO);
	
	/**
	 * Getting Group Name for ICD Mapping
	 * 
	 * @param _userVO User Detail
	 * @return Map of Group Name
	 */
	public Map getMappingTypeWiseDiseaseEssential(UserVO _userVO);
	
	public Map getMinYearEssential(UserVO userVO,String strMode_p);
	
	/*Functions Added By Pawan Kumar B N*/
	public List getParameterForPatientComplaints(UserVO userVO);

	public List getUsersOpd(UserVO _userVO);
	
	public List treatmentDetailList(String patcrNO,String episodeCode, UserVO userVO);
	
	public Map getVisitSummaryDetails(EpisodeVO _episodeVO, UserVO _userVO);
	public Map getEstimateRequestEssentials(EstimateCertificateReqVO estReqDtlVO,  UserVO strUserVO);
	
	public Map getTariffsList(EstimateCertificateReqVO estReqDtlVO,  UserVO strUserVO);

	
	public void saveEstimateCertificateReqDtl(EstimateCertificateReqVO estReqVO,UserVO userVO);
}
