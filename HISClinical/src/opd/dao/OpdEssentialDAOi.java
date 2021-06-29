package opd.dao;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.Entry;
import hisglobal.vo.ChartMasterVO;
import hisglobal.vo.DailyPatientVO;
import hisglobal.vo.DeskDetailVO;
import hisglobal.vo.DeskMenuMasterVO;
import hisglobal.vo.DisclaimerMstVO;
import hisglobal.vo.DrugDoseVO;
import hisglobal.vo.DrugFrequencyMstVO;
import hisglobal.vo.EpisodeKeywordsMasterVO;
import hisglobal.vo.IcdDiseaseMasterVO;
import hisglobal.vo.IcdGroupMasterVO;
import hisglobal.vo.IcdSubgroupMasterVO;
import hisglobal.vo.OpdClinicalDetailVO;
import hisglobal.vo.PatDietAdviceDetailVO;
import hisglobal.vo.PatDrugTreatmentDetailVO;
import hisglobal.vo.PatExtTreatmentDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.ProfileInvestigationVO;
import hisglobal.vo.TemplateMasterVO;
import hisglobal.vo.UnitExtTreatMstVO;
import hisglobal.vo.UnitImageDescMasterVO;
import hisglobal.vo.UnitWiseMacroMstVO;
import hisglobal.vo.UserDeskMenuMasterVO;
import hisglobal.vo.UserDeskMenuTemplateMasterVO;
import hisglobal.vo.UserDeskUnitWardMappingMasterVO;
import hisglobal.vo.UserVO;

import java.util.List;

public interface OpdEssentialDAOi
{
	// Menu Detail By Detail By Seat Id
	public DeskDetailVO[] getMenuDtlBySeatId(UserVO _userVO, String location, String unitCode, String deskType);

	// Units List By Seat Id
	public List getUnitsBySeatId(UserVO _userVO);

	// Rooms List By Unit
	public List getRoomsByUnitCode(UserVO _userVO, String unitCode);

	public List getMenusList(String _deskType, UserVO _userVO);

	public String getSystemDate();

	// Returning List of Seats that are not yet assigned a desk/template for given units in List
	//public List getSeatsNotforUnits(String[] _UnitsList, String deskType, UserVO _userVO, String groupCode);
	// Change By Chetan According to Global Mapping Concept
	public List getSeatsNotforUnits(String deskType, UserVO _userVO, String groupCode);
	
	public List getSeatsNotforUnits(String[] _UnitsList,UserVO _userVO, String groupCode);

	// Getting All Desk By given Desk Type
	public List getDeskByType(String _DeskType, UserVO _userVO);

	public List getCosultantListForMail(UserVO _userVO);

	public List getCosultantListNameAndNoBySeatId(UserVO _userVO);

	public List getCosultantListWithNoAndSeatId(UserVO _userVO);

	public List getVerificationDocumentsList();

	// Setting Source Seats List not already added a Template for Selected Units
	// Getting User Seats from User Desk Menu Master common to all Selected Units(unit-seat wise mode)
	public List getUserDeskMenuSeatsInAllUnits(String[] _UnitsList, UserVO _userVO);
	
	//Getting seats list for unit-ward-seat wise mode
	public List getUserDeskMenuSeats(String[] _UnitsList,String[] _WardsList, UserVO _userVO);
		
	
	// Setting Source Seats List not already added a Template for Selected Units
	// Getting User Seats from User Desk Menu Master common to all Selected Units
	public UserDeskMenuTemplateMasterVO getUserDeskMenuSeats(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _userVO);
	
	//Getting ward list for un it-ward wise mode
	public UserDeskMenuTemplateMasterVO getUserDeskMenuWards(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _userVO);

	// Getting Desk List By Seats and Units
	public List getUserDeskMenuDesksInAllUnitsnSeats(String[] _UnitsList, String[] _SeatsList, UserVO _userVO);
	
	//Getting desk list for unit-ward wise mode
	public List getUserDeskMenuDesksInAllUnitsnWards(String[] _UnitsList, String[] _WardsList, UserVO _userVO);
	
	//Getting desk list for unit-ward-seat wise mode
	public List getUserDeskMenuDesksInAllUnitsnWardsSeat(String[] _UnitsList, String[] _WardsList,String[] _SeatsList, UserVO _userVO);
	
	//Getting ward list for unit-ward wise mode
	public List getWardsInAllUnits(String[] _UnitsList, UserVO _userVO);
	
	//Getting ward list for  unit-ward-seat wise mode
	public List getWardsForWardSeatWise(String[] _UnitsList, UserVO _userVO);

	// Getting Desk List for unit-wise mode 
	public List getUserDeskMenuDesksInAllUnits(String[] _UnitsList, UserVO _userVO);

	
	// Getting Desk Menu that are Template-Based by Desk Id
	public List getAllTemplateBasedDeskMenusByDeskId(String _deskId, UserVO _UserVO);

	// Getting Templates By Unit,Seat,Desk Id
	public List getTemplatesByUnitSeatDesk(UserDeskMenuTemplateMasterVO voUDMT, UserVO _UserVO);
	
	// Getting Templates By Unit,Desk Id
	public List getTemplatesByUnitDesk(UserDeskMenuTemplateMasterVO voUDMT, UserVO _UserVO);
	
	// Getting Templates By Desk Id
	public List getTemplatesByDesk(UserDeskMenuTemplateMasterVO voUDMT, UserVO _UserVO);
	
	// Getting Templates By unit Id,wardId
	public List getTemplatesByUnitWard(UserDeskMenuTemplateMasterVO voUDMT, UserVO _UserVO);
	
	// Getting Templates By unit Id,wardId, USERSEATID
	public List getTemplatesByUnitWardSeat(UserDeskMenuTemplateMasterVO voUDMT, UserVO _UserVO);

	// Getting Clinical Template Data
	public List getClinicalTemplateData(OpdClinicalDetailVO voClnData, UserVO _UserVO);

	public List getAllServiceReqForCrNo(DailyPatientVO selectedPatientVO);

	// Getting Previous Visit Dates List
	public List getPrevVisitDates(String _patCrNo, String _episodeCode, UserVO _userVO);

	// Getting Previous Visit Report Dates List
	public List getPrevVisitReportDates(String _patCrNo, String _episodeCode, String deskmenuId,  UserVO _userVO);

	// Getting All Parameters List of given Templates
	public List getOpdDeskTemplatesAllParas(String tempIds, UserVO _userVO);

	// Getting Template Parameter Names
	public List getOpdTemplParaNames(String paraIds, UserVO _userVO);

	// Getting Previous Visits In Between Given Dates
	public List getPrevVisitsInBetween(String _patCrNo, String _episodeCode, String _fromDate, String _toDate, UserVO _userVO);

	// Getting OPD Clinical Data By Selected Parameters
	public List getOpdClinDataBySelParas(OpdClinicalDetailVO voOpdCD, String qryVisits, String paraQuery, UserVO _userVO);

	public List getAllImageList(UserVO _userVO);

 	public List getSelectedImages(String _deptUnitCode, UserVO _userVO);

	public List getNotAddedUnitList(UserVO _userVO);

	// Getting Casulatity Units By Seat Id
	public List getCsultyUnitsBySeatId(UserVO _userVO);

	// Getting Roles List
	public List getRoleList(UserVO _userVO);
	
	// * Getting Clinical Department List
	public List getAllClinicalDepartmentList(UserVO _userVO);

	//public List getUnitExceptAssignedByDeskType(String _deskType,UserVO _userVO); Commented By Chetan dated 2015.11.17
	public List getUnitExceptAssignedByDeskType(UserDeskMenuMasterVO _voDeskMapping, UserVO _voUser);
	
	public List getAllGroupList(UserVO _userVO);

	public List getUnitExceptTemplate(UserVO _userVO);

	
	
	//User Desk Unit Ward Mapping Master
	public List getUnitExceptAssignedByDeskTypeForUnitWardMappingMaster(String _deskType,UserVO _userVO);
	
	public List getAllUnitsList(UserVO _userVO);
	
	public List getWardExceptAssignedByDeskTypeForUnitWardMappingMaster(String _deskType,UserVO _userVO,String UnitId);
	
	public List getWardExceptAssignedByDeskType(String _deskType,UserVO _userVO,String UnitId);
	
	public List getWardExceptAssignedByDeskTypeForUnitWardSeat(String _deskType,UserVO _userVO,String UnitId);
	
	
	// Returning List of Seats that are not yet assigned a desk/template for given wards in List
	public List getSeatsByNotWards(String[] _WardsList, String deskType, UserVO _userVO, String groupCode);
	
	public List getSeatsByNotWards(String[] _WardsList,  UserVO _userVO, String groupCode);
	
	public List getAllWardInUnitWardSeatMode(String _deskType,UserVO _userVO,String UnitId);

	
	//getting Service Type List
	public List getServiceTypeList(UserVO _UserVO);


	public List getAllAllergySite(UserVO userVO);
	
	public UserDeskMenuMasterVO getSeats(UserDeskMenuMasterVO _voUDM, UserVO _userVO);

	//Getting ward list for un it-ward wise mode
	public UserDeskUnitWardMappingMasterVO gettingWards(UserDeskUnitWardMappingMasterVO _voUDMT, UserVO _userVO);
	
	public UserDeskUnitWardMappingMasterVO gettingSeats(UserDeskUnitWardMappingMasterVO _voUDMT, UserVO _userVO);
	
	public void updateTable(UserDeskUnitWardMappingMasterVO _voUDMT, UserVO _userVO);
	
	//public List getUnitRoomList(UserVO _userVO,String _unitCode,String _roomCode);
	
	public List<TemplateMasterVO> getAllTemplatesNotAdded(String deskId,UserVO userVO);
	
	public List getDeskListByUnit(String unitCode,UserVO userVO);
	
	public List getAllUnitForMapping(UserVO userVO);
	
	public List getUnitForSeatWise(UserVO userVO);
	
	/**
	 * Getting Dosage Frequecy List
	 * @param _userVO User VO
	 * @return List of Dosage Frequecy 
	 */
	public List<Entry> getDosageFrequecy(UserVO _userVO);

	/**
	 * Getting Drug List
	 * @param _userVO User VO
	 * @return List of Drugs 
	 */
	
	
/*	 * Commented By Chetan 2015.11.24 Not in Use Now*/
	
	/*public List<Entry> getDrugList(UserVO _userVO);*/

	public List getDrugListDetail(HisDAO hisDAO_p, String mode, PatientDetailVO _patDetailVO, UserVO _userVO);
	
	
	/**
	 * Getting Drug Doses List
	 * @param _userVO User VO
	 * @return List of Drugs Doses 
	 * Commented By Chetan 2015.11.24 Not in Use Now
	 */
	//public List<Entry> getDrugListDetail(UserVO _userVO, String depUnitCode);
	
	public List<DrugDoseVO> getDrugDosesList(UserVO _userVO);
	
	public List getDrugRouteList(UserVO _userVO);

	/**
	 * Getting Item Type List
	 * @param _userVO User VO
	 * @return List of Item Types 
	 */
	//public List<Entry> getDrugItemTypeList(UserVO _userVO);

	public List getSeatListByUnit(String unitCode,UserVO userVO);
	
	public List getSeatListByUnitForUnitSeat(String _deskId, String unitCode, UserVO userVO);
	
	public List getDeskListByUnitNSeat(String seatId,String unitCode,UserVO userVO);
	
	public List getUnitForWardWise(UserVO userVO);
	
	public List getUnitForWardSeatWise(UserVO userVO);
	
	public List getWardListByUnit(String _deskId, String unitCode, UserVO userVO);
	
	public List getDeskListByUnitNWard(String wardCode,String unitCode,UserVO userVO);
	
	public List getWardListByUnitForUWS(String _deskId, String unitCode, UserVO userVO);
	
	public List getSeatListByUnitNWard(String _deskId, String wardCode, String unitCode, UserVO userVO);
	
	public List getDeskListByUnitNWardNSeat(String seatId,String wardCode,String unitCode,UserVO userVO);

	/**
	 * Getting Previous Drug Detail
	 * @param _patCrNo Cr No.
	 * @param _episodeCode Episode Code 
	 * @param _userVO User VO
	 * @return List of Drug Treatment Detail
	 */
	public List<PatDrugTreatmentDetailVO> getPrevPatDrugDetail(String _patCrNo, String _episodeCode, UserVO _userVO);

	
	//public List<PatDrugTreatmentDetailVO> getPrvTestDtlUsingAJAX(String _patCrNo, String _episodeCode, UserVO _userVO);

	
	
	public List getPrevPatRestDetail(String _patCrNo, String _episodeCode, UserVO _userVO);
	
	public List<Entry> getEXTTreatmentList(UserVO _userVO);
	
	public List<PatExtTreatmentDetailVO> getPrevPatExtTreatmentDetail(String _patCrNo, String _episodeCode, UserVO _userVO);
	
	public List<Entry> getAllDietTypeList(UserVO _userVO);
	
	public List<PatDietAdviceDetailVO> getPrevPatDietAdviceDetail(String _patCrNo, String _episodeCode, UserVO _userVO);

	
	public List<TemplateMasterVO> getAllTemplatesVO(UserVO userVO);
	
	public DeskMenuMasterVO[] getMenuNameBasedOnDeskId(String deskId,UserVO userVO);
	
	/** List of Alert Names that are not assigned to the Patient
	 * @param crNo
	 * @param userVO
	 * @return
	 */
	public List getAllPatAlerts(String crNo,UserVO userVO);

	
	public DrugFrequencyMstVO[] getDrugFrequencyVOList(UserVO userVO);
	
	public List getPrevDrugSchedule(String _patCrNo, String _episodeCode, UserVO _userVO);

	
	public List getUserBasedOnGroup(String groupCode,UserVO _userVO);


	/**
	 * Getting Serach Users By User Name
	 * @param _str Search String 
	 * @param _userVO User Detail
	 * @return
	 */
	public List<UserVO> getSearchUsersByUserName(String _str, UserVO _userVO);

	/**
	 * Getting Serach Users By Emp Id
	 * @param _str Search String 
	 * @param _userVO User Detail
	 * @return
	 */
	public List<UserVO> getSearchUsersByEmpId(String _str, UserVO _userVO);

	/**
	 * Getting Serach Users By Emp Name
	 * @param _str Search String 
	 * @param _userVO User Detail
	 * @return
	 */
	public List<UserVO> getSearchUsersByEmpName(String _str, UserVO _userVO);
	
	public List getParameterForExtInv(UserVO userVO);
	
	/**
	 * Getting Profile Bound Users By Desk Type
	 * @param _str Desk Type String 
	 * @param _userVO User Detail
	 * @return
	 */
	public String getProfileBound(String _deskType, UserVO _userVO);
	
	public String getMaxEntryDateFromDrugDetail(String _patCrNo,String _episodeCode,UserVO _userVO);
	
	public String getMaxEntryDateFromExtTreatDetail(String _patCrNo,String _episodeCode,UserVO _userVO);
	
	public List getItemTypeList(UserVO _userVO);
	
	public List<Entry> getOneTimeActivityList(String genderFlag,String deptUnitCode,UserVO _userVO);
	
	public List<Entry> getOtherInstructionList(String genderFlag,String deptUnitCode,UserVO _userVO);
	
	public List<PatExtTreatmentDetailVO> getPrevOtherInstructionDetail(String _patCrNo, String _episodeCode, UserVO _userVO);
	
	public DisclaimerMstVO fetchDisclaimerDetails(String _deptUnitCode,String profileType, UserVO _userVO);

	
	public List<Entry> getAllOneTimeActivityList(String genderFlag,String deptUnitCode,UserVO _userVO);
	
	public List<Entry> getAllOtherInstructionList(String genderFlag,String deptUnitCode,UserVO _userVO);


	public ProfileInvestigationVO[] getPatientInvestigationDetailsEMR(String _crNo,String [] departmentUnitArray,String accessType,UserVO _userVO);

	/**
	 * Getting List of Duty Roles
	 * @param _userVO User Detail
	 * @return
	 */
	public List<Entry> getDutyRolesList(UserVO _userVO);
	/**
	 * @param crNo
	 * @param accessType 
	 * @param departmentUnitArray 
	 * @param _userVO
	 * @return List
	 */
	public List getOperationPerformedListEMR(String crNo,String[] departmentUnitArray, String accessType, UserVO _userVO);
	
	/**get Template List
	 * @param deptCode
	 * @param _userVO
	 * @return List
	 */
	public List getOTTemplateListEMR(String deptCode,UserVO _userVO);
	
	/**To get Map of OT para id and value  
	 * @param crNo
	 * @param _userVO
	 * @return List of operations
	 */
	public List getOTTemplateParaValueEMR(String deptCode,String crNo,String reqNo,UserVO _userVO);
	

	
	public List getPatAlertDetail(UserVO userVO);
	

	/**
	 * Getting Macros By Process Id
	 * @param processId
	 * @param userVO
	 * @return
	 */
	public List<Entry> getMacrosByProcessId(String _processId, UserVO _userVO);

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
	public List<Entry> getOpdRosterScheduleDates(String _deptUnitCode, String _userId, String _date, UserVO _userVO);

	public List getAllergyTypeList(UserVO _userVO);
	
	//public List<Entry> getDrugListForSearch(UserVO _userVO);
	
	//public List<Entry> getDrugListDetailForSearch(UserVO _userVO, String depUnitCode);
	
	public List<PatDrugTreatmentDetailVO> getPrevPatDrugDetailForLog(String _patCrNo, String _episodeCode, UserVO _userVO);
	
	public List<PatDrugTreatmentDetailVO> getPrevPatDrugDetailForPrinting(String _patCrNo, String _episodeCode, UserVO _userVO);
	
	public List getAllUnitNotMappedWithExtTreat(UserVO userVO);
	
	public List getAllExternalTreatment(UserVO userVO);
	
	public UnitExtTreatMstVO[] getUnitExtTreatLstByDeptUnit(String deptUnitCode, UserVO _userVO);


	/**
	 * Getting List of Episode Keywords Unit Wise
	 * @param _unitCode Department Unit Code
	 * @param _userVO User Detail
	 * @return List of Episode Keywords Objects
	 */
	public List<EpisodeKeywordsMasterVO> getEpisodeKeywordsUnitWise(String _unitCode, UserVO _userVO);
	
	public List getAllUnitNotMappedWithMacro(UserVO userVO);
	
	public List getAllMacro(UserVO userVO);
	
	public List getAllMacroProcessList(UserVO userVO);
	
	public UnitWiseMacroMstVO[] getUnitMacroLstByDeptUnit(String deptUnitCode, UserVO _userVO);
	
	public List getAllImageDescWithColorCode(UserVO userVO);
	
	public UnitImageDescMasterVO[] getUnitImageDescLstByDeptUnit(String deptUnitCode, UserVO _userVO);
	
	public List<PatDrugTreatmentDetailVO> getDefaultDrugProfileForUnit(String depUnitCode, UserVO _userVO);
	
	public List getAllDrugListByDeptUnit(String deptUnitCode,UserVO userVO);
	
	public List<PatDrugTreatmentDetailVO> getParticularDrugListDetail(String drugListId, UserVO _userVO);

	// List of All Episode Keywords
	public List<EpisodeKeywordsMasterVO> getAllEpisodeKeywordList(UserVO _userVO);

	// List of All Used ICD Group
	public List<IcdGroupMasterVO> getAllUsedICDGroups(UserVO _userVO);

	// List of All Used ICD Sub Group
	public List<IcdSubgroupMasterVO> getAllUsedICDSubGroups(UserVO _userVO);

	// List of All ICD Disese & Sub Diseases
	public List<IcdDiseaseMasterVO> getAllICDDisease(UserVO _userVO);

	// Get Subgroup Group Wise
	public List<IcdSubgroupMasterVO> getSubgroupsByGroup(String _icdGroupCode, UserVO _userVO);

	// Get Disease SubGroup Wise
	public List<IcdDiseaseMasterVO> getDiseaseBySubGroup(String _icdSubgroupCode, UserVO _userVO);

	/**
	 * Get Chart Category Wise Chart List
	 * 
	 * @param _chartCategory Chart Category
	 * @param _userVO User Detail
	 * @return List of Chart Detail
	 */
	public List<ChartMasterVO> getChartsByCategory(String _chartCategory, UserVO _userVO);
	
	/**
	 * Getting Start Date of The Patient Episode i.e Date of First Visit of the Episode
	 *  
	 * @param _patDetailVO Patient Detail
	 * @param _userVO User Detail
	 * @return Start Date
	 */
	public String getEpiosdeStartDate(PatientDetailVO _patDetailVO, UserVO _userVO);

	/**
	 * Getting Start Date of The Patient Admission
	 *  
	 * @param _patDetailVO Patient Detail
	 * @param _userVO User Detail
	 * @return Start Date
	 */
	public String getAdmissionStartDate(PatientDetailVO _patDetailVO, UserVO _userVO);

	/**
	 * Getting List of All General Units of the Given Department
	 * 
	 * @param _deptCode Department Code
	 * @param _userVO User Detail
	 * @return List of Entry objects of the Unit Code and Name
	 */
	public List<Entry> getGeneralUnitsListByDept(String _deptCode, UserVO _userVO);
	
	public List getHospitalDisease(UserVO _userVO);
	
	public List getChronicDisease(UserVO _userVO);
	
	public List getDischargeTypeList(UserVO _userVO);
	
	public List<Entry> getConsultantList(String _deptUnitCode, UserVO _userVO);
	
	/* Functions Created By Pawan Kumar B N*/
	public List getParameterForPatientComplaints(UserVO userVO);

	public List<Entry> getProfileTypes(String usablity,String generationMode, UserVO _uservo);

	public List getDeptList(UserVO _userVO);

	public List getAllUnitList1(UserVO _userVO);
	
	
	
}
