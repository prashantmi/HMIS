package opd.bo.delegate;

import hisglobal.business.Delegate;
import hisglobal.transactionmgnt.HisDAO;
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

import opd.bo.OpdEssentialBO;
import opd.bo.OpdEssentialBOi;

public class OpdEssentialDelegate extends Delegate
{
	/**
	 * Constructor for Setting Service Provider
	 */
	public OpdEssentialDelegate()
	{
		super(new OpdEssentialBO()); // /<<Setting the service provider
	}

	/**
	 * gets Menu Display Essentials calls getOpdMenuDetail from OpdEssentialBOi through Service Provider
	 * 
	 * @param _UserVO
	 *            Provides User details.
	 * @return UserDeskMenuMasterVO containing All Details For Menu Display
	 */
	// *
	public DeskDetailVO[] getOpdMenuDetail(UserVO _userVO, String location, String unitCode, String deskType)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getOpdMenuDetail(_userVO, location, unitCode, deskType));
	}

	/**
	 * gets get Today Patient List calls getTodayPatientList from OpdEssentialBOi through Service Provider
	 * 
	 * @param _UserVO
	 *            Provides User details.
	 * @return DailyPatientVO containing Details For Patient Listing
	 */
	// *
	public PatientDetailVO[] getTodayPatientList(UserVO _userVO, String unitCode, String roomCode)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getTodayPatientList(_userVO, unitCode, roomCode));
	}

	/**
	 * gets get Opd Desk Essentials calls opdDeskEssentials from OpdEssentialBOi through Service Provider
	 * 
	 * @param _UserVO
	 *            Provides User details.
	 * @return List containing Opd Desk Essentials
	 */
	// *
	public List opdDeskEssentials(UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.opdDeskEssentials(_userVO));
	}

	// *
	public List getRoomsByUnitCode(UserVO _userVO, String unitCode)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getRoomsByUnitCode(_userVO, unitCode));
	}

	// * Getting Essentials for Adding into User Desk Menu Master
	public Map getAddUserDeskMenuMasterEssentials(UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getAddUserDeskMenuMasterEssentials(_userVO));
	}

	public Map getDeptAndUnit(UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getDeptAndUnit(_userVO));
	}

	// Getting Clinical Dept & Unit List Mode Wise 
	/*public Map getDeptNUnitModeWise(String _mode, String _deskId, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getDeptNUnitModeWise(_mode, _deskId, _userVO));
	}*/
	public Map getDeptNUnitModeWise(String _mappingType, String _deskId, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getDeptNUnitModeWise(_mappingType, _deskId, _userVO));
	}
	
	
	
	
	// Getting All Group & Seat List Mode Wise
	public Map getGroupNSeatModeWise(String _mode, String _deskId, List<Entry> _units, List<Entry> _wards, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getGroupNSeatModeWise(_mode, _deskId, _units, _wards, _userVO));
	}

	/**
	 * gets get Opd Patient Count calls getOpdPatientCount from OpdEssentialBOi through Service Provider
	 * 
	 * @param _UserVO
	 *            Provides User details.
	 * @return String containing Opd Today Paient Count
	 */
	// *
	public String getOpdPatientCount(UserVO _userVO, String unitCode, String roomCode)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getOpdPatientCount(_userVO, unitCode, roomCode));
	}

	public String getAttendedOpdPatientCount(UserVO _userVO, String unitCode, String roomCode)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getAttendedOpdPatientCount(_userVO, unitCode, roomCode));
	}

	// * Getting Desk Menu Master ADD Essentials
	public Map getAddModifyMenuToDeskMasterEssentials(String _deskType, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getAddModifyMenuToDeskMasterEssentials(_deskType, _userVO));
	}

	public Map getDeptIcdRemovalDetails(String _choice, String _code, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getDeptIcdRemovalDetails(_choice, _code, _userVO));
	}

	
	public Map getDeptHosDisRemovalDetails(String _choice, String _code, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getDeptHosDisRemovalDetails(_choice, _code, _userVO));
	}

	public Map getDeptIcdEssential(UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getDeptIcdEssential(_userVO));
	}
	
	
	/**
	 * @param _userVO
	 * @return
	 */
	public Map getDeptHosDisEssential(UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getDeptHosDisEssential(_userVO));
	}


	public Map getIcdDiagnosisEssential(PatientDetailVO _patDtlVO, UserVO _userVO)
	{
		OpdEssentialBO serviceBO = (OpdEssentialBO) super.getServiceProvider();
		return (serviceBO.getIcdDiagnosisEssential(_patDtlVO, _userVO));
	}

	public Map getHospitalDiagnosisEssential(PatientDetailVO _patDtlVO, UserVO _userVO)
	{
		OpdEssentialBO serviceBO = (OpdEssentialBO) super.getServiceProvider();
		return (serviceBO.getHospitalDiagnosisEssential(_patDtlVO, _userVO));
	}
	
	public Map getSnomdDiagnosisEssential(PatientDetailVO _patDtlVO, UserVO _userVO)
	{
		OpdEssentialBO serviceBO = (OpdEssentialBO) super.getServiceProvider();
		return (serviceBO.getSnomdDiagnosisEssential(_patDtlVO, _userVO));
	}
	
	

	public List getIcdCodes(String _searchCode, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getIcdCodes(_searchCode, _userVO);
	}

	public List getDiseaseName(String _searchDisease, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getDiseaseName(_searchDisease, _userVO);
	}

	/**
	 * Getting List of ICD Diseases on the basis of Search Criteria
	 * 
	 * @param _strSearch Search String either ICD Code or Disease Name Segment
	 * @param _strSearchType Search Style either Code-based or Name-based
	 * @param _userVO User Detail
	 * @return List ICD Disease Detail
	 */
	public List<IcdDiseaseMasterVO> getICDCodesSearchDetail(String _strSearch, String _strSearchType, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getICDCodesSearchDetail(_strSearch, _strSearchType, _userVO);
	}

	/**
	 * Getting List of ICD Sub Diseases on the basis of Clicked Parent ICD Disease Code
	 * 
	 * @param _strICDCode Clicked ICD Code
	 * @param _userVO User Detail
	 * @return List of ICD Sub Disease
	 */
	public List<IcdDiseaseMasterVO> getICDSubDiseases(String _strICDCode, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getICDSubDiseases(_strICDCode, _userVO);
	}

	public List getHospitalDiagnosisName(String _searchDisease, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getHospitalDiagnosisName(_searchDisease, _userVO);
	}

	public List getHospitalDiagnosisCodes(String _searchCode, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getHospitalDiagnosisCodes(_searchCode, _userVO);
	}

	// * Getting Seats not assigned to given Department Units
	/*public List getAddUserDeskMenuMasterSeatsByNotUnits(String[] _UnitsList, String deskType, UserVO _userVO,String groupCode)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getAddUserDeskMenuMasterSeatsByNotUnits(_UnitsList, deskType, _userVO, groupCode));
	}*/

	public List getAddUserDeskMenuMasterSeatsByNotUnits(String deskType, UserVO _userVO,String groupCode)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getAddUserDeskMenuMasterSeatsByNotUnits(deskType, _userVO, groupCode));
	}
	
	
	
	
	// * Getting All Desk By given Desk Type
	public List getAddUserDeskMenuMasterDeskByType(String _DeskType, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getAddUserDeskMenuMasterDeskByType(_DeskType, _userVO));
	}

	
	
	// * Getting User Desk Menu Master Record
	public UserDeskMenuMasterVO getModifyViewUserDeskMenuMstVO(UserDeskMenuMasterVO _UserDeskVO, UserVO _UserVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getModifyViewUserDeskMenuMstVO(_UserDeskVO, _UserVO));
	}

	public Map getReferPatientEssentials(String _crNO, String _deptCode, UserVO _UserVO, String deskType, String episodeCode)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getReferPatientEssentials(_crNO,_deptCode,_UserVO, deskType, episodeCode));
	}

	// ///////////List AllergyType///////
	public Map getAllergyType(UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getAllergyType(_userVO));
	}

	public List getAllergiesEssential(String _allergyCode, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getAllergiesEssential(_allergyCode, _userVO));
	}

	public Map getEConsultionEssentials(ConsultationDtlVO consultationDtlVO, UserVO _UserVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getEConsultionEssentials(consultationDtlVO,_UserVO));
	}

	// /////////////////////Next Visit Appointment/////////////////////////
	/*public Map getSlotDtl(Apt_slotDtlVO _slotDtlVO,UserVO userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getSlotDtl(_slotDtlVO,userVO));
	}*/

	/*public Apt_slotDtlVO getNextSlotDate(Apt_slotDtlVO _slotDtlVO,UserVO userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		_slotDtlVO = serviceBO.getNextSlotDate(_slotDtlVO,userVO);
		return _slotDtlVO;
	}*/

	public Map getConsultionInboxEssentials(UserVO _UserVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getConsultionInboxEssentials(_UserVO));
	}

	public ConsultationDtlVO[] getconsultationInboxEssentials(UserVO _UserVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getconsultationInboxEssentials(_UserVO));
	}

	public Map getDocumentArchivalEssentials(String patCrNo,String episodeCode, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getDocumentUploadEssentials(patCrNo,episodeCode, _userVO));
	}

	// * Getting Parameter List
	public Map getAddTempleteParameterMasterParaList(UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getAddTempleteParameterMasterParaList(_userVO));
	}

	// * Getting Templates List
	public List getEntryTemplateAllTempList(UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getEntryTemplateAllTempList(_userVO));
	}

	public List getTemplateParaListByTempId(String _tempId, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getTemplateParaListByTempId(_tempId, _userVO));
	}

	// * Getting Template Parameter Detail List
	public List getTemplateParaDetailListByTempId(String _tempId, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getTemplateParaDetailListByTempId(_tempId, _userVO));
	}

	// * Getting Parameter Dynamic Data
	public List getParameterDynamicData(String _query, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getParameterDynamicData(_query,_userVO));
	}

	public Map getAllPatientParaCrNoList(UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getAllPatientParaCrNoList(_userVO));
	}

	public Map getSetTemplateReportEssentials(String _crNo, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getSetTemplateReportEssentials(_crNo, _userVO));
	}

	public List getActualTempIds(String _crNo, String[] _aPV, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getActualTempIds(_crNo, _aPV, _userVO));
	}

	public List getPatActualParaValues(String _crNo, String[] _aPV, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getPatActualParaValues(_crNo, _aPV, _userVO));
	}

	public List getPatParaValues(String _crNo, String[] _aPV, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getPatParaValues(_crNo, _aPV, _userVO));
	}

	// * Getting Desk Menu Template List for Current Active Desk
	public List getOpdDeskMenuTemplates(String _unitCode, String _deskMenuId, UserVO _UserVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getOpdDeskMenuTemplates(_unitCode, _deskMenuId, _UserVO));
	}

	// * Getting Desk Menu Template Master ADD Essentials
	public Map getAddUserDeskMenuTemplateEssentialsUnitWise(UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getAddUserDeskMenuTemplateUnitWiseEssentials(_userVO));
	}
	
	// * Getting All desks in desk wise mode
	public Map getAllDeskEssentials(UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getAllDeskEssentials(_userVO));
	}
	
	
	public Map getAllDeskBasedOnDeskType(String deskType,UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getAllDeskBasedOnDeskType(deskType,_userVO));
	}
	
	public Map getMappedUnits(String deskId,UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getMappedUnits(deskId,_userVO));
	}

	public Map getMappedUnitsForUnitSeat(String deskId,UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getMappedUnitsForUnitSeat(deskId,_userVO));
	}
	
	public Map getMappedUnitsForUnitSeatWard(String deskId,UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getMappedUnitsForUnitSeatWard(deskId,_userVO));
	}
	
	public Map getMappedUnitsForWard(String deskId,UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getMappedUnitsForWard(deskId,_userVO));
	}
	
	public Map getAllDeskTypeEssentials(UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getAllDeskTypeEssentials(_userVO));
	}

	// * Setting Source Seats List not already added a Template for Selected Units
	public List getAddUsrDskMnuTempSeatsByInAllUnits(String[] _UnitsList, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getAddUsrDskMnuTempSeatsByInAllUnits(_UnitsList, _userVO));
	}
	
	public List getAddUsrDskMnuTempSeats(String[] _UnitsList,String[] _WardsList, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getAddUsrDskMnuTempSeats(_UnitsList,_WardsList, _userVO));
	}
	
	// * Setting Source Seats List not already added a Template for Selected Units
	public UserDeskMenuTemplateMasterVO getAddUsrDskMnuTempSeats(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getAddUsrDskMnuTempSeats(_voUDMT, _userVO));
	}

	public UserDeskMenuTemplateMasterVO getAddUsrDskMnuTempWards(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getAddUsrDskMnuTempWards(_voUDMT, _userVO));
	}
	
	// * Getting Desk List By Seats and Units
	public List getAddUsrDskMnuTempdeksInUnitsnSeats(String[] _UnitsList, String[] _SeatsList, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getAddUsrDskMnuTempdeksInUnitsnSeats(_UnitsList, _SeatsList, _userVO));
	}
	
	public List getAddUsrDskMnuTempdeksInUnitsnWards(String[] _UnitsList, String[] _WardsList, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getAddUsrDskMnuTempdeksInUnitsnWards(_UnitsList, _WardsList, _userVO));
	}
	
	public List getAddUsrDskMnuTempdeksInUnitsnWardsSeat(String[] _UnitsList, String[] _WardsList,String[] _SeatsList, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getAddUsrDskMnuTempdeksInUnitsnWardsSeat(_UnitsList, _WardsList,_SeatsList, _userVO));
	}
	
	//Get ward list for which USERSEATID is null
	public List getAddUsrDskMnuTempWardsInUnits(String[] _UnitsList, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getAddUsrDskMnuTempWardsInUnits(_UnitsList, _userVO));
	}
	
	public List getAddUsrDskMnuTempWardsForWardSeatWise(String[] _UnitsList, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getAddUsrDskMnuTempWardsForWardSeatWise(_UnitsList, _userVO));
	}
	
	// * Getting Desk List By Seats 
	public List getAddUsrDskMnuTempdeksInUnits(String[] _UnitsList, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getAddUsrDskMnuTempdeksInUnits(_UnitsList, _userVO));
	}

	// * Getting Desk Menu that are Template-Based by Desk Id
	public List getAllTemplateBasedDeskMenusByDeskId(String _deskId, UserVO _UserVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getAllTemplateBasedDeskMenusByDeskId(_deskId, _UserVO));
	}

	// * Getting Templates By Unit,Seat,Desk Id
	public List getTemplatesByUnitSeatDesk(UserDeskMenuTemplateMasterVO voUDMT, UserVO _UserVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getTemplatesByUnitSeatDesk(voUDMT, _UserVO));
	}
	
	// * Getting Templates By Unit,Desk Id
	public List getTemplatesByUnitDesk(UserDeskMenuTemplateMasterVO voUDMT, UserVO _UserVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getTemplatesByUnitDesk(voUDMT, _UserVO));
	}
	
	// * Getting Templates By Unit,Desk Id
	//public List getTemplatesByDesk(UserDeskMenuTemplateMasterVO voUDMT, UserVO _UserVO)
	/*{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getTemplatesByDesk(voUDMT, _UserVO));
	}*/

	// * Getting Clinical Template Data
	public List getClinicalTemplateData(OpdClinicalDetailVO voClnData, UserVO _UserVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getClinicalTemplateData(voClnData, _UserVO));
	}

	// * Getting Previous Visit Dates List
	public List getPrevVisitDates(String _patCrNo, String _episodeCode, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getPrevVisitDates(_patCrNo, _episodeCode, _userVO));
	}

	// * Getting All Parameters List of given Templates
	public List getOpdDeskTemplatesAllParas(List tempIds, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getOpdDeskTemplatesAllParas(tempIds, _userVO));
	}

	// * Getting Template Parameter Names
	public List getOpdTemplParaNames(List paraIds, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getOpdTemplParaNames(paraIds, _userVO));
	}

	/*public Map getServiceEssentials(UserVO _userVO, DailyPatientVO selectedPatientVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getServiceEssentials(_userVO, selectedPatientVO));
	}

	public List getDeptWiseServiceAreaList(ServiceAreaVO _serviceAreaVO, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getDeptWiseServiceAreaList(_serviceAreaVO, _userVO));
	}

	public List getServiceAreaWiseServiceList(ServiceVO _serviceVO, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getServiceAreaWiseServiceList(_serviceVO, _userVO));
	}

	public Map TemplateDtl(ServiceAreaVO _serviceAreaVO, ServiceVO _serviceVO, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.TemplateDtl(_serviceAreaVO, _serviceVO, _userVO));
	}*/

	// * Getting Desk Type List
	public List getDeskType(UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getDeskType(_userVO));
	}

	// * Getting Desk Type Description
	public String getDeskTypeDesc(String _deskType, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getDeskTypeDesc(_deskType, _userVO);
	}
	
	public Map getPatientBelongingEssentials(String patCrNo, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getPatientBelongingEssentials(patCrNo,_userVO);
	}

	// * Getting Previous Visits In Between Given Dates
	public List getPrevVisitsInBetween(String _patCrNo, String _episodeCode, String _fromDate, String _toDate, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getPrevVisitsInBetween(_patCrNo, _episodeCode, _fromDate, _toDate, _userVO));
	}

	// * Getting OPD Clinical Data By Selected Parameters
	public List getOpdClinDataBySelParas(OpdClinicalDetailVO voOpdCD, String qryVisits, String paraQuery, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getOpdClinDataBySelParas(voOpdCD, qryVisits, paraQuery, _userVO));
	}

	public AudioVideoMasterVO[] getAudioVideoEssentials(String _unitCode, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getAudioVideoEssentials(_unitCode, _userVO));
	}

	public List getDeskMenuBasedOnDeskType(String _deskType, UserVO _UserVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getDeskMenuBasedOnDeskType(_deskType, _UserVO));
	}

	public DeskMenuMacroMstVO[] getMacroHead(String _deskType, String _deskMenu, UserVO _UserVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getMacroHead(_deskType, _deskMenu, _UserVO);
	}

	public List getAllUnit(UserVO _UserVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getAllUnit(_UserVO));
	}

	public void deleteMacroHead(DeskMenuMacroMstVO _deskMenuMacroMstVO, UserVO _UserVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		serviceBO.deleteMacroHead(_deskMenuMacroMstVO, _UserVO);
	}

	// Getting Image Examination Essentials
	public Map getImageExaminationEssentials(String _unitCode, OpdPatientImageDtlVO _patImgVO, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getImageExaminationEssentials(_unitCode, _patImgVO, _userVO);
	}

	// Getting Image Log Detail
	public OpdPatientImageDtlVO getImageLogDetail(String _imageFileName, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getImageLogDetail(_imageFileName, _userVO);
	}

	// * Getting Images List of Current Unit
	public List getOPDImagesListOfUnit(String _unitCode, UserVO _UserVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getOPDImagesListOfUnit(_unitCode, _UserVO);
	}

	// * Getting Editor Essentials e.g. Color-Description List 
	public String getUnitsImageColorDesc(String _unitCode, UserVO _UserVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getUnitsImageColorDesc(_unitCode, _UserVO);
	}

	public List getAllImageDesc(UserVO userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getAllImageDesc(userVO));
	}

	public Map getUnitNotInTable(UserVO userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getUnitNotInTable(userVO));
	}

	public void addImageDescription(String imageDesc, UserVO userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		serviceBO.addImageDescription(imageDesc, userVO);
	}

	//* Getting Unit Image Master ADD Essentials
	public Map getAddUnitImageMasterEssentials(UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getAddUnitImageMasterEssentials(_userVO));
	}

	//* Getting Unit Image Master MODIFY Essentials
	public Map getModifyUnitImageMasterEssentials(String _deptUnitCode, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getModifyUnitImageMasterEssentials(_deptUnitCode, _userVO);
	}

	public List getAllAudioVideoFile(UserVO userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getAllAudioVideoFile(userVO);
	}

	
	public List getAllAudioVideoFileHeader(UserVO userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getAllAudioVideoFileHeader(userVO);
	}
	
	public List getAllUnitNotInTable(UserVO userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getAllUnitNotInTable(userVO);
	}
	public List getAllDept(UserVO userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getAllDept(userVO);
	}

	// Getting Casulaity Desk Essentials
	public List csultyDeskEssentials(UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.csultyDeskEssentials(_userVO));
	}

	// Getting Casuality Patients List
	public PatientDetailVO[] getCsultyPatientList(String _unitCode, String _roomCode, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getCsultyPatientList(_unitCode, _roomCode, _userVO));
	}
	
	// Getting Roles List
	public List getRoleList(UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getRoleList(_userVO));
	}

	
	// Getting ICD Hospital Disease Essentials
	public  List getIcdHospitalMasterEssentails(UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi)super.getServiceProvider();
		return serviceBO.getIcdHospitalMasterEssentails(_userVO);
	}
	// Getting ICD Disease List By Hospital Disease Code
	public  List fetchDiseaseList(IcdHospitalMasterVO _diseaseMasterVO,UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi)super.getServiceProvider();
		return serviceBO.fetchDiseaseList(_diseaseMasterVO,_userVO);
	}

	
	public List getUnitExceptAssignedByDeskType(String _deskType,UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getUnitExceptAssignedByDeskType(_deskType,_userVO));
	}

	
	//*  Getting is Default status for OPD Desk Master
	public boolean getisDefault(DeskMasterVO _deskMstVO,UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getisDefault(_deskMstVO,_userVO));
	}
	
	//*  Getting is Default status for OPD Desk Master
		public boolean getisDefaultGlobal(DeskMasterVO _deskMstVO,UserVO _userVO)
		{
			OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
			return (serviceBO.getisDefaultGlobal(_deskMstVO,_userVO));
		}
	
	public List getAllGroupList(UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getAllGroupList(_userVO));
	}

	
	public List getUnitExceptTemplate(UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getUnitExceptTemplate(_userVO));
	}

	//*User Desk Unit Ward Mapping Master
	public Map getAddUserDeskUnitWardMappingMasterEssentials(UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getAddUserDeskUnitWardMappingMasterEssentials(_userVO));
	}
	
	public List getUnitExceptAssignedByDeskTypeForUnitWardMappingMaster(String _deskType,UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getUnitExceptAssignedByDeskTypeForUnitWardMappingMaster(_deskType,_userVO));
	}	
	public List getWardExceptAssignedByDeskTypeForUnitWardMappingMaster(String _deskType,UserVO _userVO,String deptCode)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getWardExceptAssignedByDeskTypeForUnitWardMappingMaster(_deskType,_userVO,deptCode));
	}
	
	public List getWardExceptAssignedByDeskType(String _deskType,UserVO _userVO,String UnitId)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getWardExceptAssignedByDeskType(_deskType,_userVO,UnitId));
	}
	
	public List getWardExceptAssignedByDeskTypeForUnitWardSeat(String _deskType,UserVO _userVO,String UnitId)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getWardExceptAssignedByDeskTypeForUnitWardSeat(_deskType,_userVO,UnitId));
	}
	
	public List getAllWardInUnitWardSeatMode(String _deskType,UserVO _userVO,String UnitId)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getAllWardInUnitWardSeatMode(_deskType,_userVO,UnitId));
	}	
	
	public List getSeatsByNotWards(String[] _WardsList, String deskType, UserVO _userVO,String groupCode)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getSeatsByNotWards(_WardsList, deskType, _userVO, groupCode));
	}
	// * Getting User Desk Unit Ward Mapping Master Record
	public UserDeskUnitWardMappingMasterVO getModifyViewUserDeskUnitWardMappingMstVO(UserDeskUnitWardMappingMasterVO _UserDeskVO, UserVO _UserVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getModifyViewUserDeskUnitWardMappingMstVO(_UserDeskVO, _UserVO));
	}

	//Getting ServiceType List
	public List getServiceTypeList(UserVO _UserVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getServiceTypeList(_UserVO);
	}

	public List getAllAllergySite(UserVO userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getAllAllergySite(userVO));
	}

		public Map getCommonNAllergyTypeSymptom(String allergyTypeCode,UserVO userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getCommonNAllergyTypeSymptom(allergyTypeCode,userVO);
	}
	public Map getEssentials(ConsentRequestVO consentRequestVO,UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getEssentials(consentRequestVO,_userVO);
	}
	
	public UserDeskMenuMasterVO getSeats(UserDeskMenuMasterVO _voUDM, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getSeats(_voUDM, _userVO));
	}
	
	public UserDeskMenuTemplateMasterVO getUnitName(String _deptUnitCode,UserVO _UserVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi)super.getServiceProvider();
		return serviceBO.getUnitName(_deptUnitCode,_UserVO);
	}

	public UserDeskUnitWardMappingMasterVO gettingWards(UserDeskUnitWardMappingMasterVO _voUDMT, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.gettingWards(_voUDMT, _userVO));
	}
	
	public UserDeskUnitWardMappingMasterVO gettingSeats(UserDeskUnitWardMappingMasterVO _voUDMT, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.gettingSeats(_voUDMT, _userVO));
	}
	
	public void updateDeskUnitWardMappingMaster(UserDeskUnitWardMappingMasterVO _voUDMT, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		serviceBO.updateDeskUnitWardMappingMaster(_voUDMT, _userVO);
	}
	
	public Map getEpisodeRoomChangeEssential(UserVO _userVO,String _unitCode,String _roomCode,PatientDetailVO patDtlVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getEpisodeRoomChangeEssential(_userVO,_unitCode,_roomCode,patDtlVO));
	}


	public List<TemplateMasterVO> getAllTemplatesNotAdded(String deskId,UserVO userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getAllTemplatesNotAdded(deskId,userVO));
	}
	
	public List getDeskListByUnit(String unitCode,UserVO userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getDeskListByUnit(unitCode,userVO));
	}
	
	public Map getAllUnitForMapping(UserVO userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getAllUnitForMapping(userVO));
	}
	
	public Map getUnitSeatWise(UserVO userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getUnitSeatWise(userVO));
	}
	
	public List getSeatListByUnit(String _deskId, String unitCode, UserVO userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getSeatListByUnit(_deskId, unitCode, userVO));
	}
	
	public List getDeskListByUnitNSeat(String seatId,String unitCode,UserVO userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getDeskListByUnitNSeat(seatId,unitCode,userVO));
	}
	
	public Map getUnitForWardWise(UserVO userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getUnitForWardWise(userVO));
	}
	
	public Map getUnitForWardSeatWise(UserVO userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getUnitForWardSeatWise(userVO));
	}
	
	public List getWardListByUnit(String _deskId, String unitCode, UserVO userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getWardListByUnit(_deskId, unitCode, userVO));
	}
	
	public List getDeskListByUnitNWard(String wardCode,String unitCode,UserVO userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getDeskListByUnitNWard(wardCode,unitCode,userVO));
	}
	
	public List getWardListByUnitForUWS(String _deskId, String unitCode, UserVO userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getWardListByUnitForUWS(_deskId, unitCode, userVO));
	}

	public Map getDeskMenuMasterEssentails(UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getDeskMenuMasterEssentails(_userVO));
	}
	
	

	/**
	 * Getting Patient Treatment Detail Essentials
	 * @param _patCrNo Patient CR No.
	 * @param _episodeCode Episode Code
	 * @param _userVO User VO 
	 * @return Map of Essentials
	 */
	public Map getPatTreatmentDetailEssential(String _patCrNo, String _episodeCode,String deptUnitCode,String genderCode, PatientDetailVO _patDetailVO,  UserVO _userVO)
	{
		OpdEssentialBO serviceBO = (OpdEssentialBO) super.getServiceProvider();
		return (serviceBO.getPatTreatmentDetailEssential(_patCrNo, _episodeCode,deptUnitCode,genderCode, _patDetailVO,  _userVO));
	}
	
	public List getSeatListByUnitNWard(String _deskId, String wardCode, String unitCode, UserVO userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getSeatListByUnitNWard(_deskId, wardCode, unitCode, userVO));
	}
	
	public List getDeskListByUnitNWardNSeat(String seatId,String wardCode,String unitCode,UserVO userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getDeskListByUnitNWardNSeat(seatId,wardCode,unitCode,userVO));
	}

	
	/////////drug dose master/////////
	public Map getDrugDoseMasterEssentails(UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getDrugDoseMasterEssentails(_userVO));
	}

	//
	public Map getDrugRouteMasterEssentails(UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getDrugRouteMasterEssentails(_userVO));
	}

	public List<TemplateMasterVO> getAllTemplatesVO(UserVO userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getAllTemplatesVO(userVO));
	}
	
	public DeskMenuMasterVO[] getMenuNameBasedOnDeskId(String deskId,UserVO userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getMenuNameBasedOnDeskId(deskId,userVO));
	}
	
	/** List of Alert Names that are not assigned to the Patient
	 * @param crNo
	 * @param userVO
	 * @return
	 */
	public List getAllPatAlerts(String crNo,UserVO userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getAllPatAlerts(crNo,userVO));
	}
	
	public List getAdvice(UserVO userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getAdvice(userVO));
	}
	
	public String getCsultyTotalPatCount(UserVO userVO,String unitCode,String roomCode)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getCsultyTotalPatCount(userVO,unitCode,roomCode));
	}
	
	public String getCsultyTodayAdmPatCount(UserVO userVO,String unitCode,String roomCode)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getCsultyTodayAdmPatCount(userVO,unitCode,roomCode));
	}

	
	public List getUserBasedOnGroup(String groupCode,UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getUserBasedOnGroup(groupCode,_userVO));
	}

	/**
	 * Getting Patient Episode Essentials
	 * @param _patProfileVO Patient Profile Process Details
	 * @param _userVO User Detail
	 * @return Map of Essentials of the Process
	 */
	public Map<String, Object> getPatientProfilesEssentials(PatientProfileDetailVO _patProfileVO, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getPatientProfilesEssentials(_patProfileVO, _userVO));
	}

	/**
	 * Getting Serach Result of Users for the Profile Access Priviledges
	 * @param _mode Search Mode
	 * @param _str Search String 
	 * @param _userVO User Detail
	 * @return
	 */
	public List<UserVO> getSearchUsersForProfileAccessPrivil(String _mode, String _str, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getSearchUsersForProfileAccessPrivil(_mode, _str, _userVO));
	}
	
	public Map getOfflineConsentEssentials(ConsentRequestVO consentRequestVO,UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getOfflineConsentEssentials(consentRequestVO,_userVO);
	}
	
	public  Map getDeskTypeMenuMappingMstEssentails(DeskTypeMenuMappingVO deskTypeMenuMappingVO ,UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getDeskTypeMenuMappingMstEssentails(deskTypeMenuMappingVO,_userVO);
	}
	
	public List getParameterForExtInv(UserVO userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getParameterForExtInv(userVO));
	}
	
	public DisclaimerMstVO fetchDisclaimerDetails(String _deptUnitCode,String profileType, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.fetchDisclaimerDetails(_deptUnitCode,profileType, _userVO);
	}
	
	public List getAddUserDeskMenuMasterSeatsByNotUnits(String[] _WardsList, UserVO _userVO,String groupCode)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getAddUserDeskMenuMasterSeatsByNotUnits(_WardsList, _userVO, groupCode));
	}

	/**
	 * Getting Visit Summary Essentials
	 * @param _episodeVO Episode Detail VO
	 * @param _userVO User Detail VO
	 * @return Map of Essentials
	 */
	public Map getVisitSummaryEssentials(EpisodeVO _episodeVO, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getVisitSummaryEssentials(_episodeVO, _userVO));
	}

	// Getting Dynamic Previous Visit Summary Detail
	public LinkedHashMap<String, List<List<Object>>> getDynamicVisitSummaryDetail(EpisodeVO _episodeVO, List<String> _lstMenuIds, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getDynamicVisitSummaryDetail(_episodeVO, _lstMenuIds, _userVO));
	}

	/**
	 * Getting Macros of given Process Id
	 * @param _processId Process Id
	 * @param _userVO User Detail
	 * @return
	 */
	public List<Entry> getMacrosListByProcessId(String _processId, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getMacrosListByProcessId(_processId, _userVO));
	}

	/**
	 * Getting Macros of given Process Id & Unit 
	 * @param _processId Process Id
	 * @param _unitCode Unit Code
	 * @param _userVO User Detail
	 * @return
	 */
	public List<Entry> getUnitMacrosByProcessId(String _processId, String _unitCode, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getUnitMacrosByProcessId(_processId, _unitCode, _userVO));
	}

	/**
	 * Getting Schedule Dates Unit User Date Wise
	 * @param _deptUnitCode Unit Code
	 * @param _userId User ID
	 * @param _date Date
	 * @param _userVO User Detail
	 * @return List of Schedule Dates
	 */
	public List<Entry> getOpdRosterSchedule(String _deptUnitCode, String _userId, String _date, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getOpdRosterSchedule(_deptUnitCode, _userId, _date, _userVO));
	}
	
	public Map fetchRestrictedCategoryEssentials(UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.fetchRestrictedCategoryEssentials(_userVO));
	}
	
	public Map getIcdNHospitalDiagnosisEssential(UserVO _userVO, String _patCrNo, String _episodeCode)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getIcdNHospitalDiagnosisEssential(_userVO, _patCrNo, _episodeCode));
	}

	public Map getExtTreatMasterEssential(UserVO userVO) {
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getExtTreatMasterEssential(userVO);
	}
	public Map getDrugMasterEssential(UserVO userVO) {
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getDrugMasterEssential(userVO);
	}

	public Map getUnitExtTreatForModify(UnitExtTreatMstVO vo, UserVO userVO) {
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getUnitExtTreatForModify(vo,userVO);
	}
	public Map getUnitDrugLisyForModify(UnitDrugMstVO vo, UserVO userVO) {
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getUnitDrugLisyForModify(vo,userVO);
	}
	
	public Map getPatientAddedAttendant(String strPatCrNo_p, String strEpisodeCode_p, UserVO strUserVO_p)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getPatientAddedAttendant(strPatCrNo_p, strEpisodeCode_p, strUserVO_p));
	}
	
	public EpisodeVO[] getAllEpisodeOfThePatientTodayVisited(String crNo,UserVO userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getAllEpisodeOfThePatientTodayVisited(crNo, userVO);
	}

	public Map getUnitMacroMasterEssential(UserVO userVO) {
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getUnitMacroMasterEssential(userVO);
	}

	public Map getUnitExtTreatForModify(UnitWiseMacroMstVO vo, UserVO userVO) {
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getUnitExtTreatForModify(vo,userVO);
	}


	public Map getPatientCategoryForProfileType(String profileType,
			UserVO _uservo) {
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getPatientCategoryForProfileType(profileType,_uservo);
	}

	public Map getUnitImageDescForModify(UnitImageDescMasterVO vo, UserVO userVO) {
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getUnitImageDescForModify(vo,userVO);
	}

	public Map getUnitDrugListMasterEssential(UserVO userVO) {
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getUnitDrugListMasterEssential(userVO);
	}

	public Map getUnitMacroForModify(UnitDrugListMasterVO vo, UserVO userVO) {
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getUnitMacroForModify(vo,userVO);
	}

	public List getParticularDrugListDtl(String drugListId, UserVO userVO) {
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getParticularDrugListDtl(drugListId,userVO);
	}

	// Getting Unit Keyword ADD Essentials
	public Map getUnitEpisodeKeywordEssentials(UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getUnitEpisodeKeywordEssentials(_userVO));				
	}
	
	// Getting Unit Episode Keyword MODIFY Essentails
	public Map getModifyUnitEpisodeKeywordEssentials(String _deptUnitCode, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getModifyUnitEpisodeKeywordEssentials(_deptUnitCode, _userVO);
	}

	// Getting Unit Episode Keyword VIEW Essentails
	public Map getViewUnitEpisodeKeywordEssentials(String _deptUnitCode, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getViewUnitEpisodeKeywordEssentials(_deptUnitCode, _userVO);
	}

	// Getting ICD Include Exclude Essentails
	public Map getICDIncludeExcludeEssential(String _groupCode, String _subgroupCode, String _diseaseCode,UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getICDIncludeExcludeEssential(_groupCode,_subgroupCode,_diseaseCode,_userVO);
	}

	// Getting ICD Code Essentials
	public Map getICDAllEssentials(UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getICDAllEssentials(_userVO));
	}

	// Getting Disease Site Essentails
	public Map getDiseaseSiteEssential(UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getDiseaseSiteEssential(_userVO);
	}

	// Get Subgroups Group Wise
	public List<IcdSubgroupMasterVO> getSubGroupsByGroup(String _icdGroupCode, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getSubGroupsByGroup(_icdGroupCode, _userVO);
	}

	// Get Disease SubGroup Wise
	public List<IcdDiseaseMasterVO> getDiseaseBySubGroup(String _icdSubgroupCode, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getDiseaseBySubGroup(_icdSubgroupCode, _userVO);
	}

	/**
	 * Getting Generic Chart Reporting Essentials
	 * 
	 * @param _deskType Desk Type
	 * @param _unitCode Department Unit Code
	 * @param _patDtlVO Patient Detail
	 * @param _userVO User Detail
	 * @return Map of Essentials
	 */
	public Map<String, Object> getChartReportingEssentials(String _deskType, String _unitCode, PatientDetailVO _patDtlVO, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getChartReportingEssentials(_deskType, _unitCode, _patDtlVO, _userVO);
	}
	
	// getting all essential fields for Chart unit mapping
	public Map getChartUnitListEssential(UserVO _userVO) {
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getChartUnitListEssential(_userVO);
	}

	public Map getChartUnitListForModify(ChartUnitMapppingVO _vo, UserVO _userVO) {
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getChartUnitListForModify(_vo,_userVO);
	}
	
	// Dept unit icd mapping
	public Map getUnitListEssential(UserVO _userVO) {
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getDeptUnitListEssential(_userVO);
	}
	
	public Map getEssential(UserVO _userVO) 
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getEssential(_userVO);
	}
//	public Map getIcdDiseaseList(UserVO _userVO) 
//	{
//		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
//		return serviceBO.getIcdDiseaseList(_userVO);
//	}
	
	//Department Unit Hospital Disease
	
	public Map getDeptUnitEssential(UserVO _userVO){
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getDeptUnitEssential(_userVO);
	}
	//for Hospital Disease
	public Map getHospitalDisease(UserVO _userVO) 
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getHospitalDisease(_userVO);
	}
	
	// Opd Icd Mapping 
	
	/**
	 * Getting Hospital Disease For IcdMapping
	 * 
	 * @param _userVO User Detail
	 * @return Map of Hospital Disease
	 */
	public Map getHospitalDiseaseForIcdMapping(UserVO userVO_p) 
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getHospitalDiseaseForIcdMapping(userVO_p);
	}
	/**
	 * Getting Chronic Disease For IcdMapping
	 * 
	 * @param _userVO User Detail
	 * @return Map of Chronic Disease
	 */
	public Map getChronicDiseaseForIcdMapping(UserVO userVO_p) 
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getChronicDiseaseForIcdMapping(userVO_p);
	}
	/**
	 * Getting Group Name for ICD Mapping
	 * 
	 * @param _userVO User Detail
	 * @return Map of Group Name
	 */
	public Map getMappingTypeWiseDiseaseEssential(UserVO userVO_p) 
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getMappingTypeWiseDiseaseEssential(userVO_p);
	}
	
	public Map getMinYearEssential(UserVO userVO,String strMode_p) {
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getMinYearEssential(userVO,strMode_p);
	}
	
	/* Functions Added by Pawan Kumar B N*/
	public List getParameterForPatientComplaints(UserVO userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getParameterForPatientComplaints(userVO));
	}
	
	public List getUsersOpd(UserVO _userVO) {
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.getUsersOpd(_userVO);
	}
	public List treatmentDetailList(String patcrNO,String episodeCode, UserVO userVO) {
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return serviceBO.treatmentDetailList(patcrNO,episodeCode,userVO);
	}
	public Map getVisitSummaryDetails(EpisodeVO _episodeVO, UserVO _userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getVisitSummaryDetails(_episodeVO, _userVO));
	}

	public Map getEstimateRequestEssentials(EstimateCertificateReqVO estReqDtlVO ,  UserVO strUserVO)
		{
			OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
			return (serviceBO.getEstimateRequestEssentials(estReqDtlVO, strUserVO));
		}
	
	public Map getTariffsList(EstimateCertificateReqVO estReqDtlVO ,  UserVO strUserVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getTariffsList(estReqDtlVO, strUserVO));
	}
	
	public void saveEstimateCertificateReqDtl(EstimateCertificateReqVO estReqVO,UserVO userVO)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		serviceBO.saveEstimateCertificateReqDtl(estReqVO,userVO);
	}

	public List getAddUserDeskMenuMasterDeptsByUnits(String[] _deskType, UserVO _userVO,String mappingType)
	{
		OpdEssentialBOi serviceBO = (OpdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getAddUserDeskMenuMasterSeatsByNotUnits(_deskType, _userVO, mappingType));
	}

}
