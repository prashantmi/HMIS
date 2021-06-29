package registration.bo;
import hisglobal.tools.Tree;
import hisglobal.vo.CityLocationMasterVO;
import hisglobal.vo.EpisodeCloseVO;
import hisglobal.vo.EpisodeDiagnosisVO;
import hisglobal.vo.EpisodeTriageDetailVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.MlcVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PoliceExaminationReqtDtlVO;
import hisglobal.vo.ReportVO;
import hisglobal.vo.RoomMasterVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.YellowSlipEntryDtlVO;
import hisglobal.vo.YellowSlipMonitoringVO;

import java.sql.ResultSet;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.WebRowSet;

import registration.vo.BPLDetailsVO;
import registration.vo.DSSEpisodeVO;
import registration.vo.DSSRegistrationVO;
import registration.vo.DSSReportVO;

import net.sf.jasperreports.engine.JasperPrint;

public interface EssentialBOi {
	public Map getEssentialForRoom(UserVO _userVO);
	public RoomMasterVO getEssentialToModifyRoom(String roomCode,UserVO _userVO);
	public Map getEssentialForCityLocation(UserVO _userVO);
	public Map getNewPatientRegEssential(UserVO _userVO);
	public Map getNewPatientRegEssentialForUnkonwn(UserVO _userVO);
	public Map getSpecialClinicNewPatientRegEssential(UserVO _userVO);
	
	public Map getEmgNewPatientRegEssential(UserVO _userVO);
	public Map getAllEmergencyOldPatientVisitEssentials(UserVO _userVO);
	public List getSecondaryCategory(UserVO _userVO);
	public Map getNewDeptVisitEssential(String _crNo, UserVO _userVO);
	public Map getNewSplDeptVisitEssential(String _crNo, UserVO _userVO);
	public Map getNewDeptVisitRoomWiseEssential(String _crNo, UserVO _userVO);
	public Map getOldDeptVisitEssential(String _crNo, UserVO _userVO);
	//public Map getDeptRevisitEssential(UserVO _userVO);
	public Map getChangePrimaryCategoryEssential(String crNo,UserVO _userVO);
	public Map getChangeSecondaryCategoryEssential(UserVO _userVO);
	public Map getFileNoChangeInitials(UserVO _userVO);
	public Map getRenwalOfRegistrationInitials(UserVO _userVO);
	public Map getNewSpecialClinicInitials(UserVO _userVO);
	
	public Map getPatientDtlModificationEssential(UserVO _userVO);
	public Map getAddressDtlModificationEssential(UserVO _userVO);
	public List getStateBasedOnCountry(String _countryCode,UserVO _userVO);	
	public Map getEpisodeDeathEssential(UserVO _userVO);
	public List getOptionsCmoRegisterEssential(UserVO _userVO);
	public List getOptionsDoctorDeskEssential(UserVO _userVO);
	public Map getRefHospEssential(UserVO _userVO);	
	public List getDeptWithCasuality(UserVO _userVO);	
	public Tree getDiagnosis(UserVO _userVO);
	public List getDepartmentEssential(UserVO _userVO);	
	public List getWardBasedOnDept(String _deptCode,UserVO _userVO);
	public List getOptionsMlcDtl(UserVO _userVO);
	public List getOptionCrNoMlcNO(UserVO _userVO);
	public List getSearchOptions(UserVO _userVO);
	public Map getUnitChangeEssential(String deptUnitCode, UserVO _userVO);
	public List getRegCategoryEssential(UserVO _userVO);
	public List getVerificationDocuments(UserVO _userVO);

	public List getRefFromDepartment(String crNo, UserVO _userVO);
	public List getRefToDepartment(String crNo, UserVO _userVO);
	public Map getReferDtlEssential(UserVO _userVO);
	public List getUnitBasedOnDept(String _deptCode,UserVO _userVO);
	//public List getUnitBasedOnSpeciality(String speciality,UserVO _userVO);
	public RoomMasterVO[] getRoomSequenceDtl(String _deptCode,String _unitCode,UserVO _userVO);
	public void updateSequenceDtl(RoomMasterVO[] _rmMasterVO,UserVO _userVO);	
/*	public RosterMasterVO[] getUnitSequenceDtl(String _deptCode,String _unitCode,UserVO _userVO);
	public void updateUnitSequenceDtl(RosterMasterVO[] _roMasterVO,UserVO _userVO);*/
	//public Map getReferDtlEssential(UserVO _userVO);
	//public List getUnitBasedOnDept(String _deptCode,UserVO _userVO);
	public Map getReferDeptVisitEssential(String crNo, UserVO _userVO);
	public List getDeptBasedOnWeekday(String _WeekDay, UserVO _userVO); 
	public List getWeekdayEssential(UserVO _userVO);
	public List getDiagnosisTypeEssential(UserVO _userVO);
	public List getAllDepartment(UserVO _userVO);
	public List getShiftEssential(UserVO _userVO);
	public Map getUnitEssentials(UserVO _userVO);
	public List getAllRooms(UserVO _userVO);
	public Map getRoomsToUnitEssentials(UserVO _userVO);
	
	public Map getCollectionSecondaryCategory(Collection primaryCatCode,UserVO _userVO);
	public List getEmployeesIsConsultants(String designationMappingProcessId, UserVO _userVO);
	public Map getAgeWiseReportEssentials(UserVO _userVO);
	public Map getEmergencyRegPatReportEssential(UserVO _userVO);
	public Map getGroupWiseCashCollReportEssential(UserVO _userVO);
	public Map getMlcPatientListReportsEssentials(UserVO _userVO);
	public Map getBroughtDeadPatReportsEssentials(UserVO _userVO);
	public Map getPinCodeWiseReportEssential(UserVO _userVO);
	public Map getDepartmentWiseRegPatReportEssentials(UserVO _userVO);
	public Map getDeptWisePatCatReportsEssentials(UserVO _userVO);
	public Map getSpecialityWiseOutdoorPatientReportEssentials(UserVO _userVO);
	public Map getSpecialityGenderWiseOPDPatientReportEssentials(UserVO _userVO);
	public Map getSpecialityUnitWiseSplClinicOPDReportEssentials(UserVO _userVO);
	public Map getGenderWiseOutdoorPatientReportEssentials(UserVO _userVO);
	public Map getSpecialityWiseOperationReportEssentials(UserVO _userVO);
	public Map getSpecialityWiseInvestigationReportEssentials(UserVO _userVO);
	public Map getSpecialityUnitWiseOPDPatientReportEssentials(UserVO _userVO);
	public Map getSpecialityGenderWiseOpdPatientReportEssentials(UserVO _userVO);
	
	
	public Map getDepartmentWiseTotalPatReportEssentials(UserVO _userVO);
	public Map getRoomWiseTotalPatReportEssentials(UserVO _userVO);
	public Map getTotalPatStatReportEssentials(UserVO _userVO);
	public Map getUserWiseCashCollReportEssentials(UserVO _userVO);
	public Map getUserWisePatListReportEssentials(UserVO _userVO);
	public Map getUserWiseRegReportEssentials(UserVO _userVO);
	public Map getCategoryWiseReportEssential(UserVO _userVO);
	public Map getEmergencyReportEssential(UserVO _userVO);
	
	public String getBillAmountBasedOnCategory(String _deptCode,UserVO _userVO);
	public String checkBplDetails(String _bplcardNo,UserVO _userVO);
	public WebRowSet getBplSearchDetails(BPLDetailsVO bplVO,UserVO _userVO);
	public String getBillAmountAndIdRequiredBasedOnCategory(String _catCode,UserVO _userVO);
	public Map getSpecialUnitDetail( UserVO _userVO,String crNo, String patPriCatCode);
	public Map getEmgVisitUnitDetail( UserVO _userVO,String crNo, String patPriCatCode);
	public Map getPatientMRDRegEssential(UserVO _userVO);
	public List getUnitConsultant(UserVO _userVO);
	public String getNextDate();
	public String getNextExpiryDate(String _expiryDate);
	public List getAllUnitBasedOnSeatID(UserVO _userVO);
	public  List getEpisodeActionList(UserVO _userVO);
	public  List getIcdCodeList(UserVO _userVO);
	public  List getDiagnosisTypeList(UserVO _userVO);
	public  List getIcdCodes(String _searchIcdCode,UserVO _userVO);
	public  List getDiseaseName(String _searchDisease,UserVO _userVO);
	public List getOpdUser(UserVO _userVO);
	public List getIcdCodeBasedOnDept(String _deptCode,UserVO _userVO);
	public List getDoctorNameBasedOnUnit(String _unitCode,UserVO _userVO);
	public List getState(UserVO userVO);
	public List getAllUnit(UserVO userVO);
	public List getSeason(UserVO userVO);
	public List getShiftForRegistration(UserVO _userVO);
	public List getAllDeptList(UserVO userVO);
	public List getPatientCategoryList(UserVO _userVO);
	public List getRegCategoryList(UserVO _userVO);

	// Getting Triage Detail Essentials
	public Map getTriageDetailEssentials(EpisodeTriageDetailVO _episodeTriageVO, UserVO _userVO);
	
	public String getBillAmountBasedOnUnitGrouping(String _categoryCode,String _fromUnit,String _toUnit,UserVO _userVO);
	public String getBillAmountBasedOnDeptGrouping(String _categoryCode,String _fromDept,String _toDept,String entryDate, UserVO _userVO);
	public Map getYellowSlipEssential(EpisodeVO episodeVO, String _unitCode,UserVO _userVO);
	public Map getMlcDtlEssentials(UserVO _userVO);
	public List getPrimaryCatDetailVOs(UserVO _userVO);	

	public List getMlcNoBasedOnCrNo(String crNo,UserVO _userVO);

	public Map getCsultyMlcDetailEssentials(UserVO _userVO);
	public Map getParichitEssentials(UserVO _userVO);
	public List getDistrictOnBasisOfState(String stateCode,UserVO _userVO);
	public CityLocationMasterVO getDetailOnBasisOfLocation(String locCode,UserVO userVO);
	public List getSpecialClinicUnitList(UserVO userVO);
	
	public Map getLocationEssential(UserVO _userVO);
	public List getBlockComboBasedOnBuilding(UserVO _userVO,String _buildingCode);
	public List getFloorComboBasedOnBlock(UserVO _userVO,String _blockCode);
	public List getRoomComboBasedOnFloor(UserVO _userVO,String _floorCode);
	
	public Map getModifyLocationEssential(String locationCode,String hospitalCode,UserVO _userVO);

	public String getMarqueMessage(UserVO _userVO);
	public Map getEssentialsForShiftWiseCasesByCMOReport(UserVO _uservo);

	public List getDepartmentType(UserVO _userVO);

	public Map getEssentialsForDiagnosisStatLocationWiseReport(UserVO _userVO);
	
	public PatientDetailVO[] getCMOPatientList(String _unitCode, UserVO _userVO);
	
	public String getCMOTotalPatCount(UserVO userVO,String unitCode);

	public ResultSet getAllPatientList();
	
	public List getPatConditionMacro(String processId,UserVO userVO);
	
	public Map getBroughtByNPoliceVerDtl(MlcVO mlcVO,UserVO userVO);

	public List getRefDepartmentList(UserVO userVO);

	public Map getPatientdeathDetailEssential(String crNo,String epiCode,UserVO userVO, String deptUnitCode);
	
	public Map getClinicalDeptList(UserVO _uservo);
	
	public List getRelationList(UserVO _userVO);
	
	public List getYellowSlipEntryUserList(String fromDate, String toDate,UserVO userVO);
	
	public List getYellowSlipEntryByUser(YellowSlipEntryDtlVO yellowSlipEntryVO, UserVO userVO);
	
	public Map getYellowSlipEntryByCRNoEpisodeNo(YellowSlipEntryDtlVO yellowSlipEntryDtlVO,UserVO userVO);
	
	public void saveYellowSlipMonitoringDtl(
			YellowSlipEntryDtlVO yellowSlipEntryVO,
			YellowSlipMonitoringVO yellowSlipMonitoringVO, EpisodeVO episodeVO, EpisodeDiagnosisVO[] episodeDiagnosisInsertVO, EpisodeDiagnosisVO[] episodeDiagnosisUpdateVO, EpisodeCloseVO episodeCloseVO, UserVO userVO);

	
	public Map getUnitBasedOnWeekDay(String day,UserVO _userVO) ;

	
	public Map getEssentialForSearch(UserVO userVO);


	public List getVerificationDocumetByCatCode(String catCode,UserVO userVO);


	public List getUnitRoom(String _unitCode,UserVO _userVO);
	
	public Map getRoomsAndConsultantsByUnit(String _unitCode,UserVO _userVO);

	public List getPatientPrimaryCategory(UserVO _userVO);
	
	public Map getCategoryWiseMappedUnMappedDocument(String _categoryCode,UserVO _userVO);
	
	public Map getDepartments(UserVO _userVO);
	
	public ReportVO[] getReportForHosPatStat(ReportVO vo, UserVO _UserVO);
	
	public ReportVO[] getReportForHosPatStatState(ReportVO vo, UserVO _UserVO);

	public List getDepartmentsForAgeWiseReport(UserVO _userVO);
	
	public ReportVO[] getAgeWiseRegReport(ReportVO vo, UserVO _UserVO);
	
	public JasperPrint createReport(String jrxmlFileName,ReportVO[] reportVO,UserVO _userVO);
	
	public Map setEssentialForRedirectOfOPDPat(UserVO _userVO);
	
	public List getRoomListByDeptUnit(String deptUnitCode,UserVO _userVO);
	
	public Map getParticularRoomDetail(String deptUnitCode,String roomCode,UserVO _userVO) ;
	
	public List getTodayVistPatListByRoom(String deptUnitCode,String roomCode,UserVO userVO);
	
	public List getAllActiveRoomListByDeptUnit(String deptUnitCode,UserVO _userVO);

	
	public Map getAgeSexReligionDeptRegEssentials(UserVO _userVO);
	
	public Map getAgeSexReligionHosRegEssentials(UserVO _userVO);
	
	public Map getSpecialityWiseIndoorPatientReportEssentials(UserVO _userVO);

	public Map getDiseaseType(UserVO _userVO);

	
	public Map offlineRegistrationEssential(UserVO _uservo);

	public List getUnitDateWiseRoomRosterList(String unitCode,String deactivationDate,UserVO _userVO);


	public List getRoomsByUnitOfDayWiseCapacity(String _unitCode,UserVO _userVO);

	public List getRoomsByUnitForRoster(String _unitCode,UserVO _userVO);

	
	public List getMaritalStatus(UserVO userVO);
	
	public List getReligionList(UserVO userVO);
	
	public List getOcccupationList(UserVO userVO);
	public List getAllCounter(UserVO _userVO);

	public Map setUnitConsultantInitials(String _deptCode,UserVO _userVO);
	
	public List getDepartmentsForFilePrinting(UserVO userVO);
	
	public List getUnitForFileNoPrinting(String deptCode, UserVO userVO);
	
	public Map getEssentialForDeptUserWiseReport(UserVO userVO);
	
	public List getTodayWorkingRoomBasedOnUnit(String departmentUnitCode,UserVO userVO) ;
	
	//public Map getEssentialForHospitalWiseReport(UserVO _uservo);
	
	/**
	 * Getting Patient Modification Detail
	 * @param _userVO
	 * @return
	 * Created By Pragya at 04-Aug-2011
	 */
	public Map getPatientModificationEssential(UserVO _userVO);

	/**
	 * Getting Cash Collection User Wise Essentails
	 * @param _userVO
	 * @return
	 * Created By Pragya at 16-Aug-2011
	 */ 
	public Map getUserWiseCashCollectionReportEssentials(UserVO _userVO);
	
	/**
	 * Getting Hospital Combo List
	 * 
	 * @param _uservo
	 * @return
	 * 
 	 * Created By Vivek at 16-Aug-2011
	 */
	public Map getHospitalEssentialCombo(UserVO _uservo);

	public Map getPatientListingReportEssentials(UserVO userVO);
	
	public Map getEssentialForUserWiseReport(UserVO userVO);
	public Map getEssentialForRosterReport(UserVO userVO);
	public Map getEssentialDSSReport(String strMode_p, UserVO userVO);
	public DSSRegistrationVO[] getAgeWiseDSS(DSSRegistrationVO vo);
	public List<DSSRegistrationVO> getDSSAgeWiseStatReportData(DSSReportVO vo);
	public List<DSSEpisodeVO> getDSSOPDStatsReportData(DSSReportVO vo);
	public List<DSSEpisodeVO> getDSSEMGStatsReportData(DSSReportVO vo);
	public List getUserComboForCategoryWiseUserWiseReport(UserVO userVO);
	
	public Map getEssentialForAmbulanceWiseReport(UserVO userVO);
	
	public String getPatCrNoFrmMCTSNo(String mctsNo, UserVO _UserVO);
	
	public Map getPatVisitDtlReport(String patcrno, UserVO _UserVO,String fromDate,String toDate, String choice);
	
	public Map getPoliceExaminationReqtEssentials(UserVO _userVO);
	public List<PoliceExaminationReqtDtlVO> getPoliceExaminationReqtDtl(String strMode_p,
			String strPatCrNo_p, String strEpisode_p,
			String strEpisodeVisitNo_p, UserVO objUserVO_p);
	
	public Map getEssentialForEmployee(UserVO _userVO);


}




