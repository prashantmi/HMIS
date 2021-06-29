package registration.bo.delegate;

import hisglobal.business.Delegate;
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

import net.sf.jasperreports.engine.JasperPrint;
import registration.bo.EssentialBO;
import registration.bo.EssentialBOi;
import registration.vo.BPLDetailsVO;
import registration.vo.DSSEpisodeVO;
import registration.vo.DSSRegistrationVO;
import registration.vo.DSSReportVO;

public class EssentialDelegate extends Delegate {

	public EssentialDelegate() {
		super(new EssentialBO()); // /<<Setting the service provider
	}

	public Map getEssentialForRoom(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return (serviceBO.getEssentialForRoom(_userVO));
	}

	public RoomMasterVO getEssentialToModifyRoom(String roomCode, UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return (serviceBO.getEssentialToModifyRoom(roomCode, _userVO));
	}

	public Map getEssentialForCityLocation(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return (serviceBO.getEssentialForCityLocation(_userVO));
	}

	public Map getNewPatientRegEssentialForUnknown(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return (serviceBO.getNewPatientRegEssentialForUnkonwn(_userVO));
	}

	public Map getNewPatientRegEssential(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return (serviceBO.getNewPatientRegEssential(_userVO));
	}

	public Map getPatientMRDRegEssential(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return (serviceBO.getPatientMRDRegEssential(_userVO));
	}

	public Map getEmgNewPatientRegEssential(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return (serviceBO.getEmgNewPatientRegEssential(_userVO));
	}

	public Map getAllEmergencyOldPatientVisitEssentials(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return (serviceBO.getAllEmergencyOldPatientVisitEssentials(_userVO));
	}

	public List getRegistrationCategoryEssentials(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return (serviceBO.getRegCategoryEssential(_userVO));
	}

	public List getSecondaryCategory(String _PrimaryCatCode, UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return (serviceBO.getSecondaryCategory(_userVO));
	}

	public Map getNewDeptVisitEssential(String _crNo, UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return (serviceBO.getNewDeptVisitEssential(_crNo, _userVO));
	}
	public Map getNewSplDeptVisitEssential(String _crNo, UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return (serviceBO.getNewSplDeptVisitEssential(_crNo, _userVO));
	}

	public Map getNewDeptVisitRoomWiseEssential(String _crNo, UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return (serviceBO.getNewDeptVisitRoomWiseEssential(_crNo, _userVO));
	}
	
	public Map getOldDeptVisitEssential(String _crNo, UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return (serviceBO.getOldDeptVisitEssential(_crNo, _userVO));
	}

	public Map getChangePrimaryCategoryEssential(String crNo, UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return (serviceBO.getChangePrimaryCategoryEssential(crNo, _userVO));
	}

	public Map getChangeSecondaryCategoryEssential(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return (serviceBO.getChangeSecondaryCategoryEssential(_userVO));
	}

	public Map getFileNoChangeInitials(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return (serviceBO.getFileNoChangeInitials(_userVO));
	}

	public Map getRenwalOfRegistrationInitials(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return (serviceBO.getRenwalOfRegistrationInitials(_userVO));
	}

	public Map getNewSpecialClinicInitials(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return (serviceBO.getNewSpecialClinicInitials(_userVO));
	}

	public Map getPatientDtlModificationEssential(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return (serviceBO.getPatientDtlModificationEssential(_userVO));
	}

	public Map getAddressDtlModificationEssential(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return (serviceBO.getAddressDtlModificationEssential(_userVO));
	}

	public List getOptionsCmoregisterEssential(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return (serviceBO.getOptionsCmoRegisterEssential(_userVO));
	}

	// * Getting Episode Death Essential
	public Map getEpisodeDeathEssential(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return (serviceBO.getEpisodeDeathEssential(_userVO));
	}

	public Map getRefHospEssential(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return (serviceBO.getRefHospEssential(_userVO));
	}

	public List getDeptWithCasuality(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return (serviceBO.getDeptWithCasuality(_userVO));
	}

	public Tree getDiagnosis(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return (serviceBO.getDiagnosis(_userVO));
	}

	public List getDepartmentList(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return (serviceBO.getDepartmentEssential(_userVO));
	}

	public List getWardBasedOndept(String _deptCode, UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return (serviceBO.getWardBasedOnDept(_deptCode, _userVO));
	}

	public List getOptionsDoctorDeskEssential(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return (serviceBO.getOptionsDoctorDeskEssential(_userVO));
	}

	public List getOptionsMlcDtl(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return (serviceBO.getOptionsMlcDtl(_userVO));
	}

	public List getOptionCrNoMlcNO(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return (serviceBO.getOptionCrNoMlcNO(_userVO));
	}

	public List getSearchOptions(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return (serviceBO.getSearchOptions(_userVO));
	}

	public Map getUnitChangeEssential(String deptUnitCode, UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return (serviceBO.getUnitChangeEssential(deptUnitCode, _userVO));
	}

	public List getVerificationDocuments(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return (serviceBO.getVerificationDocuments(_userVO));
	}

	public Map getReferDeptVisitEssential(String crNo, UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return (serviceBO.getReferDeptVisitEssential(crNo, _userVO));
	}

	public void updateSequenceDtl(RoomMasterVO[] _rmMasterVO, UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		serviceBO.updateSequenceDtl(_rmMasterVO, _userVO);
	}

	public RoomMasterVO[] getRoomSequenceDtl(String _deptCode,
			String _unitCode, UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getRoomSequenceDtl(_deptCode, _unitCode, _userVO);
	}

	public List getUnitBasedOnDept(String _deptCode, UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getUnitBasedOnDept(_deptCode, _userVO);
	}
	
	/*public List getUnitBasedOnSpeciality(String speciality, UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getUnitBasedOnSpeciality(speciality, _userVO);
	}*/

	public Map getReferDtlEssential(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getReferDtlEssential(_userVO);
	}

	/*
	 * public void updateUnitSequenceDtl(RosterMasterVO[] _roMasterVO,UserVO
	 * _userVO){ EssentialBOi serviceBO =
	 * (EssentialBOi)super.getServiceProvider();
	 * serviceBO.updateUnitSequenceDtl(_roMasterVO,_userVO); }
	 */
	public List getDeptBasedOnWeekday(String _WeekDay, UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getDeptBasedOnWeekday(_WeekDay, _userVO);
	}

	public List getWeekdayEssential(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getWeekdayEssential(_userVO);
	}

	/*
	 * public RosterMasterVO[] getUnitSequenceDtl(String _deptCode,String
	 * _unitCode,UserVO _userVO){ EssentialBOi serviceBO =
	 * (EssentialBOi)super.getServiceProvider(); return
	 * serviceBO.getUnitSequenceDtl(_deptCode,_unitCode,_userVO); }
	 */
	public List getDiagnosisTypeEssential(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getDiagnosisTypeEssential(_userVO);
	}

	public List getAllDepartments(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getAllDepartment(_userVO);
	}

	public Map getUnitEssentials(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getUnitEssentials(_userVO);
	}

	public List getAllRooms(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getAllRooms(_userVO);
	}

	public Map getRoomsToUnitEssentials(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getRoomsToUnitEssentials(_userVO);
	}

	public List getShiftEssential(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getShiftEssential(_userVO);
	}

	public Map getCollectionSecondaryCategory(Collection primaryCatCode,
			UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO
				.getCollectionSecondaryCategory(primaryCatCode, _userVO);
	}

	public List getEmployeesIsConsultants(String designationMappingProcessId,
			UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getEmployeesIsConsultants(designationMappingProcessId,
				_userVO);
	}

	public List getStateBasedOnCountry(String _conurtyCode, UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getStateBasedOnCountry(_conurtyCode, _userVO);
	}

	public Map getAgeWiseReportEssentials(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getAgeWiseReportEssentials(_userVO);
	}

	public Map getEmergencyRegPatReportEssential(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getEmergencyRegPatReportEssential(_userVO);
	}

	public Map getGroupWiseCashCollReportEssential(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getGroupWiseCashCollReportEssential(_userVO);
	}

	public Map getMlcPatientListReportsEssentials(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getMlcPatientListReportsEssentials(_userVO);
	}

	public Map getBroughtDeadPatReportsEssentials(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getBroughtDeadPatReportsEssentials(_userVO);
	}

	public Map getPinCodeWiseReportEssential(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getPinCodeWiseReportEssential(_userVO);
	}

	public Map getDepartmentWiseRegPatReportEssentials(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getDepartmentWiseRegPatReportEssentials(_userVO);
	}

	public Map getDeptWisePatCatReportsEssentials(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getDeptWisePatCatReportsEssentials(_userVO);
	}

	public Map getDepartmentWiseTotalPatReportEssentials(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getDepartmentWiseTotalPatReportEssentials(_userVO);
	}

	public Map getRoomWiseTotalPatReportEssentials(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getRoomWiseTotalPatReportEssentials(_userVO);
	}

	public Map getTotalPatStatReportEssentials(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getTotalPatStatReportEssentials(_userVO);
	}

	public Map getUserWiseCashCOllReportEssentials(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getUserWiseCashCollReportEssentials(_userVO);
	}

	public Map getUserWisePatListReportEssentials(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getUserWisePatListReportEssentials(_userVO);
	}

	public Map getUserWiseRegReportEssentials(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getUserWiseRegReportEssentials(_userVO);
	}

	public Map getCategoryWiseReportEssential(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getCategoryWiseReportEssential(_userVO);
	}

	public String getBillAmountBasedOnCategory(String _catCode, UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getBillAmountBasedOnCategory(_catCode, _userVO);
	}
	public String checkBplDetails(String _bplcardNo, UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.checkBplDetails(_bplcardNo, _userVO);
	}
	public WebRowSet getBplSearchDetails(BPLDetailsVO bplVO, UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getBplSearchDetails(bplVO, _userVO);
	}
	public String getBillAmountAndIdRequiredBasedOnCategory(String _catCode,
			UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getBillAmountAndIdRequiredBasedOnCategory(_catCode,
				_userVO);
	}

	public Map getSpecialUnits(UserVO _userVO, String crNo, String patPriCatCode) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getSpecialUnitDetail(_userVO, crNo, patPriCatCode);
	}

	public Map getEmgUnits(UserVO _userVO, String crNo, String patPriCatCode) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getEmgVisitUnitDetail(_userVO, crNo, patPriCatCode);
	}

	public Map getSpecialClinicNewPatientRegEssential(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getSpecialClinicNewPatientRegEssential(_userVO);
	}

	public Map getEmergencyReportEssential(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getEmergencyReportEssential(_userVO);
	}

	public List getUnitConsultant(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getUnitConsultant(_userVO);
	}

	public String getNextDate() {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getNextDate();
	}

	public String getNextExpiryDate(String _expiryDate) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getNextExpiryDate(_expiryDate);
	}

	public List getAllUnitBasedOnSeatID(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getAllUnitBasedOnSeatID(_userVO);
	}

	public List getEpisodeActionList(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getEpisodeActionList(_userVO);
	}

	public List getIcdCodeList(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getIcdCodeList(_userVO);
	}

	public List getDiagnosisTypeList(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getDiagnosisTypeList(_userVO);
	}

	public List getIcdCodes(String _searchIcdCode, UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getIcdCodes(_searchIcdCode, _userVO);
	}

	public List getDiseaseName(String _searchIcdCode, UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getDiseaseName(_searchIcdCode, _userVO);
	}

	public List getOpdUser(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getOpdUser(_userVO);
	}

	public List getIcdCodeBasedOnDept(String _deptCode, UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getIcdCodeBasedOnDept(_deptCode, _userVO);
	}

	public List getDocttorNameBasedOnUnit(String _unitCode, UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getDoctorNameBasedOnUnit(_unitCode, _userVO);
	}

	// Getting Triage Detail Essentials
	public Map getTriageDetailEssentials(EpisodeTriageDetailVO _episodeTriageVO, UserVO _userVO)
	{
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getTriageDetailEssentials(_episodeTriageVO, _userVO);
	}

	public List getState(UserVO userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getState(userVO);
	}

	public List getSeason(UserVO userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getSeason(userVO);
	}

	public String getBillAmountBasedOnDeptGrouping(String _categoryCode,
			String _fromDept, String _toDept, String entryDate, UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getBillAmountBasedOnDeptGrouping(_categoryCode,
				_fromDept, _toDept, entryDate,_userVO);
	}

	public String getBillAmountBasedOnUnitGrouping(String _categoryCode,
			String _fromUnit, String _toUnit, UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getBillAmountBasedOnUnitGrouping(_categoryCode,
				_fromUnit, _toUnit, _userVO);
	}

	public List getShiftForRegistration(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getShiftForRegistration(_userVO);
	}

	public List getAllDeptList(UserVO userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getAllDeptList(userVO);
	}

	public List getPatientCategoryList(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getPatientCategoryList(_userVO);
	}

	public List getRegCategoryList(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getRegCategoryList(_userVO);
	}

	public Map getYellowSlipEssential(EpisodeVO episodeVO, String _unitCode, UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getYellowSlipEssential(episodeVO,_unitCode, _userVO);
	}

	public Map getMlcDtlEssentials(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getMlcDtlEssentials(_userVO);
	}

	public List getAllUnit(UserVO userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getAllUnit(userVO);
	}

	public List getPrimaryCatDetailVOs(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getPrimaryCatDetailVOs(_userVO);
	}

	public List getMlcNoBasedOnCrNo(String crNo, UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getMlcNoBasedOnCrNo(crNo, _userVO);
	}

	public Map getCsultyMlcDetailEssentials(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getCsultyMlcDetailEssentials(_userVO);
	}

	public Map getParichitEssentials(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getParichitEssentials(_userVO);
	}

	public List getDistrictOnBasisOfState(String stateCode, UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getDistrictOnBasisOfState(stateCode, _userVO);
	}

	public CityLocationMasterVO getDetailOnBasisOfLocation(String locCode,
			UserVO userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getDetailOnBasisOfLocation(locCode, userVO);
	}

	public List getSpecialClinicUnitList(UserVO userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getSpecialClinicUnitList(userVO);
	}

	public Map getLocationEssential(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getLocationEssential(_userVO);
	}

	public Map getModifyLocationEssential(String locationCode,
			String hospitalCode, UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getModifyLocationEssential(locationCode, hospitalCode,
				_userVO);
	}

	public List getBlockComboBasedOnBuilding(UserVO _userVO,
			String _buildingCode) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getBlockComboBasedOnBuilding(_userVO, _buildingCode);
	}

	public List getFloorComboBasedOnBlock(UserVO _userVO, String _blockCode) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getFloorComboBasedOnBlock(_userVO, _blockCode);
	}

	public List getRoomComboBasedOnFloor(UserVO _userVO, String _floorCode) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getRoomComboBasedOnFloor(_userVO, _floorCode);
	}

	public String getMarqueMessage(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getMarqueMessage(_userVO);
	}

	public Map getEssentialsForShiftWiseCasesByCMOReport(UserVO _uservo) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getEssentialsForShiftWiseCasesByCMOReport(_uservo);
	}

	public List getDepartmentType(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getDepartmentType(_userVO);
	}

	public Map getEssentialsForDiagnosisStatLocationWiseReport(UserVO _uservo) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO
				.getEssentialsForDiagnosisStatLocationWiseReport(_uservo);
	}

	// Getting CMO Patients List
	public PatientDetailVO[] getCMOPatientList(String _unitCode, UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return (serviceBO.getCMOPatientList(_unitCode, _userVO));
	}

	public String getCMOTotalPatCount(UserVO userVO, String unitCode) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return (serviceBO.getCMOTotalPatCount(userVO, unitCode));
	}

	public ResultSet getAllPatientList() {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getAllPatientList();
	}

	public List getPatConditionMacro(String processId, UserVO userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getPatConditionMacro(processId, userVO);
	}

	public Map getBroughtByNPoliceVerDtl(MlcVO mlcVO, UserVO userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getBroughtByNPoliceVerDtl(mlcVO, userVO);
	}

	public List getRefDepartmentList(UserVO userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getRefDepartmentList(userVO);
	}

	public Map getPatientdeathDetailEssential(String crNo, String epiCode,
			UserVO userVO, String deptUnitCode) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getPatientdeathDetailEssential(crNo, epiCode, userVO, deptUnitCode);
	}

	public Map getClinicalDeptList(UserVO _uservo) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getClinicalDeptList(_uservo);
	}

	public List getRelationList(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getRelationList(_userVO);
	}

	public Map getSpecialityWiseOutdoorPatientReportEssentials(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getSpecialityWiseOutdoorPatientReportEssentials(_userVO);
	}
	
	public Map getSpecialityGenderWiseOPDPatientReportEssentials(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getSpecialityGenderWiseOPDPatientReportEssentials(_userVO);
	}
	
	public Map getSpecialityUnitWiseSplClinicOPDReportEssentials(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getSpecialityUnitWiseSplClinicOPDReportEssentials(_userVO);
	}
	
	public Map getGenderWiseOutdoorPatientReportEssentials(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getGenderWiseOutdoorPatientReportEssentials(_userVO);
	}
	
	public Map getSpecialityWiseOperationReportEssentials(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getSpecialityWiseOperationReportEssentials(_userVO);
	}
	
	public Map getSpecialityUnitWiseOPDPatientReportEssentials(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getSpecialityUnitWiseOPDPatientReportEssentials(_userVO);
	}

	public Map getSpecialityWiseInvestigationReportEssentials(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getSpecialityWiseInvestigationReportEssentials(_userVO);
	}

	
	
	public Map getSpecialityGenderWiseOpdPatientReportEssentials(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getSpecialityGenderWiseOpdPatientReportEssentials(_userVO);
	}

	public List getYellowSlipEntryUserList(String fromDate, String toDate,
			UserVO userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getYellowSlipEntryUserList(fromDate, toDate, userVO);
	}

	public List getYellowSlipEntryByUser(
			YellowSlipEntryDtlVO yellowSlipEntryVO, UserVO userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getYellowSlipEntryByUser(yellowSlipEntryVO, userVO);
	}

	public Map getYellowSlipEntryByCRNoEpisodeNo(
			YellowSlipEntryDtlVO yellowSlipEntryVO, UserVO userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getYellowSlipEntryByCRNoEpisodeNo(yellowSlipEntryVO,
				userVO);
	}

	public void saveYellowSlipMonitoringDtl(
			YellowSlipEntryDtlVO yellowSlipEntryVO,
			YellowSlipMonitoringVO yellowSlipMonitoringVO, EpisodeVO episodeVO,
			EpisodeDiagnosisVO[] episodeDiagnosisInsertVO,
			EpisodeDiagnosisVO[] episodeDiagnosisUpdateVO,
			EpisodeCloseVO episodeCloseVO, UserVO userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		serviceBO.saveYellowSlipMonitoringDtl(yellowSlipEntryVO,
				yellowSlipMonitoringVO, episodeVO, episodeDiagnosisInsertVO,
				episodeDiagnosisUpdateVO, episodeCloseVO, userVO);
	}
	
	public Map getUnitBasedOnWeekDay(String day,UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getUnitBasedOnWeekDay(day,_userVO);
	}

	
	public List getVerificationDocumetByCatCode(String catCode,UserVO userVO)
	{
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getVerificationDocumetByCatCode(catCode,userVO);
	}
	
	public Map getEssentialForSearch(UserVO userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getEssentialForSearch(userVO);
	}
	
	public List getRoomsByUnit(String departmentUnitCode, UserVO userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getUnitRoom(departmentUnitCode,
				userVO);
	}

	public Map getRoomsAndConsultantsByUnit(String departmentUnitCode, UserVO userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getRoomsAndConsultantsByUnit(departmentUnitCode,
				userVO);
	}

	public List getPatientPrimaryCategory( UserVO userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getPatientPrimaryCategory(userVO);
	}
	
	public Map getCategoryWiseMappedUnMappedDocument(String _categoryCode,UserVO _userVO){
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getCategoryWiseMappedUnMappedDocument(_categoryCode,_userVO);
	}
	
	public Map getDepartments(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getDepartments(_userVO);
	}
	
	public ReportVO[] getReportForHosPatStat(ReportVO vo,UserVO _userVO){
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getReportForHosPatStat(vo,_userVO);
	}
	


	public ReportVO[] getReportForHosPatStatState(ReportVO vo,UserVO _userVO){
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getReportForHosPatStatState(vo,_userVO);
	}
	


	public List getDepartmentsForAgeWiseReport(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getDepartmentsForAgeWiseReport(_userVO);
	}

	
	public ReportVO[] getAgeWiseRegReport(ReportVO vo,UserVO _userVO){
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getAgeWiseRegReport(vo,_userVO);
	}
	
	public JasperPrint createReport(String jrxmlFileName,ReportVO[] reportVO,UserVO _userVO){
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.createReport(jrxmlFileName,reportVO, _userVO);
		
	}
	
	public Map setEssentialForRedirectOfOPDPat(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.setEssentialForRedirectOfOPDPat(_userVO);
	}
	
	public List getRoomListByDeptUnit(String deptUnitCode,UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getRoomListByDeptUnit(deptUnitCode,_userVO);
	}
	
	public Map getParticularRoomDetailList(String deptUnitCode,String roomCode,UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getParticularRoomDetail(deptUnitCode, roomCode, _userVO);
	}
	
	public List getTodayVistPatListByRoom(String deptUnitCode,String roomCode,UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getTodayVistPatListByRoom(deptUnitCode, roomCode, _userVO);
	}
	
	public List getAllActiveRoomListByDeptUnit(String deptUnitCode,UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getAllActiveRoomListByDeptUnit(deptUnitCode, _userVO);
	}

	public Map offlineRegistrationEssential(UserVO _uservo) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return (serviceBO.offlineRegistrationEssential(_uservo));
		
	}
	
	public Map getAgeSexReligionDeptRegEssentials(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getAgeSexReligionDeptRegEssentials(_userVO);
	}

	public Map getAgeSexReligionHosRegEssentials(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getAgeSexReligionHosRegEssentials(_userVO);
	}
	
	public Map getSpecialityWiseIndoorPatientReportEssentials(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getSpecialityWiseIndoorPatientReportEssentials(_userVO);
	}

	public Map getDiseaseType(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getDiseaseType(_userVO);
	}
	
	public List getUnitDateWiseRoomRosterList(String unitCode,String deactivationDate,UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getUnitDateWiseRoomRosterList(unitCode,deactivationDate,_userVO);
	}

	public List getMaritalStatus(UserVO userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getMaritalStatus(userVO);
	}

	public List getReligionList(UserVO userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getReligionList(userVO);
	}
	
	public List getOcccupationList(UserVO userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getOcccupationList(userVO);
	}
	
	public List getRoomsByUnitOfDayWiseCapacity(String departmentUnitCode, UserVO userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getRoomsByUnitOfDayWiseCapacity(departmentUnitCode,userVO);
	}
	
	public List getRoomsByUnitForRoster(String departmentUnitCode, UserVO userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getRoomsByUnitForRoster(departmentUnitCode,
				userVO);
	}

	
	public List getAllCounter(UserVO _userVO)
	{
		EssentialBOi serviceBO= (EssentialBOi) super.getServiceProvider();
		return serviceBO.getAllCounter(_userVO);
	}

	
	public Map setUnitConsultantInitials(String _deptCode, UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.setUnitConsultantInitials(_deptCode, _userVO);
	}
	
	public List getDepartmentsForFilePrinting(UserVO userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getDepartmentsForFilePrinting(userVO);
	}

	public List getUnitForFilePrinting(String deptCode, UserVO userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getUnitForFileNoPrinting(deptCode,userVO);
	}

	public Map getEssentialForDeptUserWiseReport(UserVO _uservo) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getEssentialForDeptUserWiseReport(_uservo);
	}

	public List getRoomsForDeptUnit(String departmentUnitCode, UserVO userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getTodayWorkingRoomBasedOnUnit(departmentUnitCode,userVO);
	}

	
	
	/**
	 * Getting Patient Modification Detail
	 * @param _userVO
	 * @return
	 * Created By Pragya at 04-Aug-2011
	 */
	public Map getPatientModificationEssential(UserVO _userVO)
	{
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return (serviceBO.getPatientModificationEssential(_userVO));
	}

	/**
	 * Getting Cash Collection User Wise Essentails
	 * @param _userVO
	 * @return
	 * Created By Pragya at 16-Aug-2011
	 */ 
	public Map getUserWiseCashCollectionReportEssentials(UserVO _userVO)
	{
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getUserWiseCashCollectionReportEssentials(_userVO);
	}
	
	/**
	 * Getting Hospital Combo List
	 * 
	 * @param _uservo
	 * @return
	 * 
 	 * Created By Vivek at 16-Aug-2011
	 */
	public Map getHospitalEssentialCombo(UserVO _uservo) 
	{
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return (serviceBO.getHospitalEssentialCombo(_uservo));
	}

	/**
	 * Report : User Wise Cash Collection
	 * 
	 * @param voReport_p Provides Patient details.
	 * @param voUser_p Provides User details.
	 * @return Cretaed By Pragya at 18-Aug-2011
	 */
	public Map getPatientListingReportEssentials(UserVO _uservo)
	{
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getPatientListingReportEssentials(_uservo);
	}

	public Map getEssentialForUserWiseReport(UserVO userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getEssentialForUserWiseReport(userVO);
	}

	public Map getEssentialForRosterReport(UserVO userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getEssentialForRosterReport(userVO);
	}

	public Map getEssentialDSSReport(String strMode_p, UserVO _uservo) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getEssentialDSSReport(strMode_p, _uservo);
	}

	public DSSRegistrationVO[] getAgeWiseDSS(DSSRegistrationVO vo) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getAgeWiseDSS( vo);
	}

	public List<DSSRegistrationVO> getDSSAgeWiseStatReportData(DSSReportVO vo)
	{
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getDSSAgeWiseStatReportData(vo);
	}
	
	public List<DSSEpisodeVO> getDSSOPDStatsReportData(DSSReportVO vo)
	{
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getDSSOPDStatsReportData(vo);
	}

	public List<DSSEpisodeVO> getDSSEMGStatsReportData(DSSReportVO vo)
	{
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getDSSEMGStatsReportData(vo);
	}

	public List getUserComboForCategoryWiseUserWiseReport(UserVO userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getUserComboForCategoryWiseUserWiseReport(userVO);
	}
	
	
	public Map getEssentialForAmbulanceWiseReport(UserVO userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getEssentialForAmbulanceWiseReport(userVO);
	}
	
	public String getPatCrNoFrmMCTSNo(String mctsNo, UserVO _userVO){
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getPatCrNoFrmMCTSNo(mctsNo,_userVO);
	}
	
	public Map getPatVisitDtlReport(String patcrno,UserVO _userVO,String fromDate,String toDate,String choice){
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getPatVisitDtlReport(patcrno,_userVO, fromDate, toDate, choice);
	}
	
	public Map getPoliceExaminationReqtEssentials(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return (serviceBO.getPoliceExaminationReqtEssentials(_userVO));
	}
	public List<PoliceExaminationReqtDtlVO> getPoliceExaminationReqtDtl(String strMode_p, String strPatCrNo_p, String strEpisode_p, String strEpisodeVisitNo_p,UserVO objUserVO_p) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return (serviceBO.getPoliceExaminationReqtDtl(strMode_p, strPatCrNo_p, strEpisode_p, strEpisodeVisitNo_p, objUserVO_p));
	}	
	public Map getEssentialForEmployee(UserVO _userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return (serviceBO.getEssentialForEmployee(_userVO));
	}
}// end of class
