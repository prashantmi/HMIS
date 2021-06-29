package opd.bo;

import hisglobal.utility.Entry;
import hisglobal.vo.AllergyTypeVO;
import hisglobal.vo.AllergyWiseSymptomMasterVO;
import hisglobal.vo.AttendantReasonMasterVO;
import hisglobal.vo.AudioVideoMasterVO;
import hisglobal.vo.ChartMasterVO;
import hisglobal.vo.ChartParameterMappingVO;
import hisglobal.vo.ConsentMappingMasterVO;
import hisglobal.vo.DepartmentHosDisMasterVO;
import hisglobal.vo.DepartmentHosDiseaseMstVO;
import hisglobal.vo.DepartmentIcdMasterVO;
import hisglobal.vo.DeskDetailVO;
import hisglobal.vo.DeskMasterVO;
import hisglobal.vo.DeskMenuMacroMstVO;
import hisglobal.vo.DeskMenuMasterVO;
import hisglobal.vo.DeskTypeMenuMappingVO;
import hisglobal.vo.DeskWiseDefaultProfileMstVO;
import hisglobal.vo.DiseaseSiteMasterVO;
import hisglobal.vo.DrugDoseMstVO;
import hisglobal.vo.DrugListItemMstVO;
import hisglobal.vo.DrugListMasterVO;
import hisglobal.vo.DrugRouteMstVO;
import hisglobal.vo.EpisodeKeywordsMasterVO;
import hisglobal.vo.IcdDiseaseMasterVO;
import hisglobal.vo.IcdHospitalMasterVO;
import hisglobal.vo.IcdIndexLevelMasterVO;
import hisglobal.vo.IcdIndexMasterVO;
import hisglobal.vo.IcdCrossRefMasterVO;
import hisglobal.vo.IcdMappingMasterVO;
import hisglobal.vo.IcdSubgroupMasterVO;
import hisglobal.vo.ImageMasterVO;
import hisglobal.vo.ImagePointerMasterVO;
import hisglobal.vo.MacroMasterVO;
import hisglobal.vo.OPDUnitImageMasterVO;
import hisglobal.vo.OpdParameterVO;
import hisglobal.vo.OpdPatientImageDtlVO;
import hisglobal.vo.OpdTemplateVO;
import hisglobal.vo.ParameterRangeMasterVO;
import hisglobal.vo.PatientAlertMasterVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.ProfileAccessPolicyVO;
import hisglobal.vo.ProfileGroupDtlVO;
import hisglobal.vo.ProfileGroupMasterVO;
import hisglobal.vo.ProfileRestrictedCategoryMasterVO;
import hisglobal.vo.ProfileTypeMstVO;
import hisglobal.vo.ProfileTypeTabMappingVO;
import hisglobal.vo.SinglePageInterfaceMasterVO;
import hisglobal.vo.SymptomWiseTemplateMappingMasterVO;
import hisglobal.vo.TemplateMappingMstVO;
import hisglobal.vo.UnitAudioVideoMasterVO;
import hisglobal.vo.UnitEpisodeKeywordVO;
import hisglobal.vo.UnitMasterVO;
import hisglobal.vo.UserDeskMenuMasterVO;
import hisglobal.vo.UserDeskMenuTemplateMasterVO;
import hisglobal.vo.UserDeskUnitWardMappingMasterVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.HospitalDiseaseMasterVO;
import hisglobal.vo.IcdSnomedMappingMasterVO;

import java.util.List;
import java.util.Map;

public interface OpdMasterBOi
{

	public void saveDeptIcdCode(DepartmentIcdMasterVO[] _deptIcdVO, String _icdCodeType, UserVO _userVO);

	public DepartmentIcdMasterVO[] getDeptIcdDetail(DepartmentIcdMasterVO _deptIcdVO, UserVO _userVO);

	/**
	 * @param _deptHosDisVO
	 * @param _userVO
	 * @return
	 */
	public DepartmentHosDisMasterVO[] getDeptHosDisDetail(DepartmentHosDisMasterVO _deptHosDisVO, UserVO _userVO);

	/**
	 * @param _deptHosDisVO
	 * @param _hosDisCodeType
	 * @param _userVO
	 */
	public void saveDeptHosDisCode(DepartmentHosDisMasterVO[] _deptHosDisVO, String _hosDisCodeType, UserVO _userVO);

	public void deleteDeptIcdCode(DepartmentIcdMasterVO _deptIcdVO, String[] _selectedRecord, String _choice, UserVO _userVO);

	/**
	 * @param _deptHosDisVO
	 * @param _selectedRecord
	 * @param _choice
	 * @param _userVO
	 */
	public void deleteDeptHosDisCode(DepartmentHosDisMasterVO _deptHosDisVO, String[] _selectedRecord, String _choice, UserVO _userVO);

	public DepartmentIcdMasterVO[] getDetailsByCode(DepartmentIcdMasterVO _deptIcdVO, String choice, UserVO _userVO);

	// * Saving Data to Desk Master
	/**
	 * @param _DskMstVO
	 * @param _userVO
	 * @return
	 */
	public String saveDesk(DeskMasterVO _DskMstVO, UserVO _userVO);

	// * Saving Data to Desk Detail
	public void saveDeskDetail(DeskDetailVO _DskDtlVO, UserVO _userVO);

	// Saving Desk Detail
	public void saveCompleteDeskDetail(DeskMasterVO _deskVO, List<DeskDetailVO> _lstDeskDtl, UserVO _userVO);

	public void saveCompleteGlobalDeskDetail(DeskMasterVO _deskVO, List<DeskDetailVO> _lstDeskDtl, UserVO _userVO);

	
	// * Getting Desk VO By Desk Id
	public DeskMasterVO fetchDeskVOByDeskId(String _DeskId, UserVO _UserVO);
	
	// * Getting Desk VO By Desk Id
		public DeskMasterVO fetchDeskVOByGlobalDeskId(String _DeskId, UserVO _UserVO);
		
	
	
	// * Getting Desk Detail VO Array By Desk
	public DeskDetailVO[] getMenuListByDeskId(DeskMasterVO _voDeskMst, UserVO _userVo);

	
	// * Getting Desk Detail VO Array By Desk
		public DeskDetailVO[] getMenuListByGlobalDeskId(DeskMasterVO _voDeskMst, UserVO _userVo);

	// * Updating Data to Desk Master
	public void updateDesk(DeskMasterVO _DskMstVO, UserVO _userVO);

	// Updating Complete Desk Detail
	public void updateCompleteDesk(DeskMasterVO _deskVO, List<DeskDetailVO> _lstDeskDtl, UserVO _userVO);

	// Updating Complete Global Desk Detail
		public void updateCompleteGlobalDesk(DeskMasterVO _deskVO, List<DeskDetailVO> _lstDeskDtl, UserVO _userVO);

	
	// * Deleting All Menus From Desk By Desk Id
	public void deleteDeskMenus(String _DeskId, UserVO _UserVo);

	// * Save User Desk Menu Record
	public void saveUserDeskMenu(UserDeskMenuMasterVO _UserDeskMenuMstVO, UserVO _userVO);

	// * Save User Desk Menu Record
		public void saveUserDeskMenu1(UserDeskMenuMasterVO[] _UserDeskMenuMstVO, UserVO _userVO);
	
	// * Updating User Desk Menu Record
	public void updateUserDeskMenu(UserDeskMenuMasterVO _UserDeskMenuMstVO, UserVO _userVO);

	// * Saving Template Record
	public void saveTemplate(OpdTemplateVO _tmpltVO, UserVO _userVO);

	// * Updating Template Record
	// public void updateTemplate(OpdTemplateVO _tmpltVO, UserVO _userVO);

	// * Adding Template with New Serial No
	// public void saveNewTemp(OpdTemplateVO _tmpltVO, UserVO _userVO);

	// * Saving Parameter Record
	public void saveOPDParameter(OpdParameterVO _paraVO, UserVO _userVO);

	// * Saving Template Parameter Detail to TemplateParameterMaster
	// public void saveParameterToTemplate(OpdTemplateParameterVO _tempParaVO, UserVO _userVO);

	// * Getting Template Data
	public void getTemplateDataById(OpdTemplateVO _voTemp, UserVO _userVO);

	// public void savePatientParameters(OpdPatientParameterVO _patParaVO, UserVO _userVO);

	// * Delete the old Template Parameter Data By Template Id
	// public void deleteTempParaById(String _TempId, UserVO _userVO);

	// * Deleting UserDeskMenuTemplate Master By Given Conditions in modify(Desk wise mode)
	public void deleteTemplateToDeskMenuTemplateMasterDeskWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO);

	// * Deleting UserDeskMenuTemplate Master By Given Conditions (Unit Wise)
	public void deleteTemplateToDeskMenuTemplateMasterUnitWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO);

	// * Deleting UserDeskMenuTemplate Master By Given Conditions (Unit-Ward Wise)
	public void deleteTemplateToDeskMenuTemplateMasterUnitWardWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO);

	// * Deleting UserDeskMenuTemplate Master By Given Conditions (UnitSeat Wise)
	public void deleteTemplateToDeskMenuTemplateMasterUnitSeatWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO);

	// * Saving User Desk Menu Template Record
	public void saveTemplateToDeskMenu(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO);

	// * Saving User Desk Menu Template Record (unit-seat wise mode)
	public void saveTemplateToDeskMenuSeatWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO);

	// Saving User Desk Menu Template Record (Unit-ward-seat wise mode)
	public void saveTemplateToDeskMenuWardSeatWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO);

	// Saving User Desk Menu Template Record (Unit-ward wise mode)
	public void saveTemplateToDeskMenuWardWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO);

	// * Saving User Desk Menu Template Record Unit Wise
	public void saveTemplateToDeskMenuUnitWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO);

	// * Saving User Desk Menu Template Record Desk Wise
	public void saveTemplateToDeskMenuDeskWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO);

	// public void saveUserDeskMenuTemplate(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO);

	// Getting Unit Detail
	public UnitMasterVO getUnitDetails(String _deptUnitCode, String _sNo, UserVO _UserVO);

	public void saveMacroHead(DeskMenuMacroMstVO _deskMenuMacroVO, UserVO _UserVO);

	public DeskMenuMacroMstVO getMacroHeadForModify(String _macroID, UserVO _UserVO);

	public void modifySaveMacroHead(DeskMenuMacroMstVO _deskMenuMacroVO, UserVO userVO);

	// * Creating Initial Deleted Entry for Image in Patient Image Detail
	/**
	 * Creating Entry for Exam Image
	 * 
	 * @param _vo Patient Image Detail VO
	 * @param _UserVO User VO
	 * @return Patient Image Detail VO
	 */
	public OpdPatientImageDtlVO createEntryForImage(OpdPatientImageDtlVO _vo, UserVO _UserVO);

	// * Removing Initial Deleted Entry for Image in Patient Image Detail
	public void removeEntryForImage(OpdPatientImageDtlVO _VO, UserVO _UserVO);

	// * Saving Image to Patient in Patient Image Detail
	public void saveOpdPatientImage(OpdPatientImageDtlVO _vo, UserVO _UserVO);

	// * Getting Images List of Patient in given Episode Visit
	public List<OpdPatientImageDtlVO> getOPDPatOldImagesList(OpdPatientImageDtlVO _PatImgVo, UserVO _UserVO);

	// Saving Image Record
	public void saveImage(ImageMasterVO imageMasterVO, UserVO userVO);

	// Getting Image for Modify
	public ImageMasterVO getImageForModify(String imageCode, String imageSlno, UserVO userVO);

	// Modifying Image Record
	public void saveModifyImage(ImageMasterVO imageMasterVO, UserVO userVO);

	public void saveUnitImageDesc(List unitImageDescVOLst, UserVO userVO);

	public void saveModUnitImageDesc(List unitImageDescVoLst, UserVO userVO);

	public List getAllColorImageDescForUnit(String selectedUnit, String imageId, String slNo, UserVO userVO);

	public void deleteUnitImageDesc(String unitCode, UserVO userVO);

	public void saveAudioVideoFile(AudioVideoMasterVO audioVideoMasterVO, UserVO userVO);

	public AudioVideoMasterVO getAudioVideoForModify(String fileCode, String slNo, UserVO userVO);

	// * Saving Image Unit Record Unit-Wise
	public void saveImageUnit(List<OPDUnitImageMasterVO> _lstUnitImages, UserVO _userVO);

	public void ModifysaveImageUnit(List<OPDUnitImageMasterVO> _lstUnitImages, String _deptUnitCode, UserVO _userVO);

	// * Getting Records for modify (all modes)
	public UserDeskMenuTemplateMasterVO getRecords(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO);

	public UserDeskMenuTemplateMasterVO getRecordsForWard(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO);

	public void saveModifyAudioVideo(AudioVideoMasterVO audioVideoVO, UserVO userVO);

	// * Getting Image for View
	public OPDUnitImageMasterVO[] getImageForView(String deptUnitCode, UserVO userVO);

	public void saveUnitAudioVideo(UnitAudioVideoMasterVO unitAVMasterVO, UserVO userVO);

	public void deleteUnitAudioVideo(String unitCode, UserVO userVO);

	// saving ICD HOspital Master Data
	public void save(IcdHospitalMasterVO[] _VOs, UserVO _userVO);

	// * Save User Desk Menu Record
	public void saveUserDeskUnitWardMappingMaster(UserDeskUnitWardMappingMasterVO _UserDeskUnitWardMappingVO, UserVO _userVO);

	// * Save User Desk Menu Record
	public void saveUserDeskUnitWardMappingMasterUnitWardWise(UserDeskUnitWardMappingMasterVO _UserDeskUnitWardMappingVO, UserVO _userVO);

	// * Updating User Desk Unit Ward Menu Master Record
	public void updateUserDeskUnitWardMappingMasterRecord(UserDeskUnitWardMappingMasterVO _UserDeskUnitWardMappingVO, UserVO _userVO);

	// getting service list by service type id
	public Map getDetail(ConsentMappingMasterVO consentMappingMasterVO, UserVO _userVO);

	// saving consent mapping master detail

	public void saveDetail(ConsentMappingMasterVO[] consentMappingMasterVO, String[] serviceId, List lstServices, UserVO _userVO);

	// getting consent mapping modify detail

	public Map getModifyDetail(ConsentMappingMasterVO consentMappingMasterVO, UserVO _userVO);

	// //saving modify consent mapping master detail
	public void saveModifyDetail(ConsentMappingMasterVO[] consentMappingMasterVO, UserVO _userVO);

	/* *********allergy Type Master ************ */

	public Map getTheValues(UserVO _userVO);

	public List getTableData(UserVO _userVO, String TableId);

	public List getPrimaryKey(String tableName);

	public List getAllergySensistivity(UserVO _UserVO);

	public boolean insertAllergyTypeDynamicMode(AllergyTypeVO _allergyTypeVO, UserVO _userVO);

	public Map getAllergyTypeNotInAllergyWiseSymptom(UserVO _userVO);

	public void addAllergyTypeAgainstSymptom(AllergyWiseSymptomMasterVO[] _voAllergyWiseSymptom, UserVO _UserVO);

	public Map getDetails(AllergyWiseSymptomMasterVO allergyWiseSymptomMasterVO, UserVO _userVO);

	public List getDetail(AllergyWiseSymptomMasterVO allergyWiseSymptomMasterVO, UserVO _userVO);

	public void saveAlergySymMasterModifyDetail(AllergyWiseSymptomMasterVO[] allergyWiseSymptomMasterVO, UserVO _UserVO);

	public AllergyTypeVO getAllergyType(String _allergyCode, UserVO _userVO);

	public boolean updateAllergyType(AllergyTypeVO _allergyTypeVO, UserVO _userVO);

	public String getAllergySensitivitydesc(String sensitivity, UserVO _userVO);

	/* ************************************************ */

	public UserDeskMenuMasterVO gettingUnitName(String _deptUnitCode, UserVO _UserVO);

	public String getUnitName(String _deptUnitCode, UserVO _UserVO);

	public void saveDetail(DeskMenuMasterVO deskMenuMasterVO, UserVO _userVO);

	public DeskMenuMasterVO getModifyDetail(DeskMenuMasterVO deskMenuMasterVO, UserVO _userVO);

	public void saveModifyDetail(DeskMenuMasterVO deskMenuMasterVO, UserVO _userVO);

	/**
	 * @param deskId
	 * @param userVO
	 * @return
	 */
	public UserDeskMenuTemplateMasterVO[] getTemplateByDeskType(String deskId, UserVO userVO);

	public void saveForDeskId(UserDeskMenuTemplateMasterVO[] userDeskMenuDeskVO, UserVO userVO);

	public void deleteForDeskId(String deskId, UserVO userVO);

	public void saveForUnit(UserDeskMenuTemplateMasterVO[] userDeskMenuDeskVO, UserVO userVO);

	public UserDeskMenuTemplateMasterVO[] getTemplateByDeskNUnit(String unitCode, String deskId, UserVO userVO);

	public void deleteForDeskNUnit(String unitCode, String deskId, UserVO userVO);

	public UserDeskMenuTemplateMasterVO[] getTemplateByDeskUnitNSeat(String seatId, String unitCode, String deskId, UserVO userVO);

	public void deleteForDeskUnitNSeat(String seatId, String unitCode, String deskId, UserVO userVO);

	public UserDeskMenuTemplateMasterVO[] getTemplateByDeskUnitNWard(String wardCode, String unitCode, String deskId, UserVO userVO);

	public void deleteForDeskUnitNWard(String wardCode, String unitCode, String deskId, UserVO userVO);

	public UserDeskMenuTemplateMasterVO[] getTemplateByDeskUnitNWardNSeat(String seatId, String wardCode, String unitCode, String deskId,
			UserVO userVO);

	public void deleteForDeskUnitNWardNSeat(String seatId, String wardCode, String unitCode, String deskId, UserVO userVO);

	/* ********************* function for Template mapping master *********************************** */

	public Map getEssentialForTemplateMapping(String categoryCode, UserVO userVO);

	public List getUnitNotAdded(TemplateMappingMstVO templateMappingVO, UserVO userVO);

	public List getAllUnits(String deptCode, UserVO userVO);

	public void saveTemplateMapping(TemplateMappingMstVO[] templateMappingVOs, UserVO userVO);

	public Map getTemplateMapping(TemplateMappingMstVO templateMappingVO, UserVO userVO);

	public void modifyTemplateMapping(TemplateMappingMstVO[] updateTemplateMappingVOs, TemplateMappingMstVO[] insertTemplateMappingVOs, UserVO userVO);

	public List getAllDepartments(UserVO userVO);

	public List getWardNotAdded(TemplateMappingMstVO templateMappingVO, UserVO userVO);

	// function for drug dose master /////////

	public void saveDrugDoseDetail(DrugDoseMstVO drugDoseMstVO, UserVO _userVO);

	public DrugDoseMstVO getDrugDoseModifyDetail(DrugDoseMstVO drugDoseMstVO, UserVO _userVO);

	public void saveDrugDoseModifyDetail(DrugDoseMstVO drugDoseMstVO, UserVO _userVO);

	public void saveDetail(DeskTypeMenuMappingVO[] _deskTypeMenuMappingVO, UserVO _userVO);

	public MacroMasterVO getProcessName(String processID, UserVO _UserVO);

	public boolean saveMacroInfo(MacroMasterVO macroMstVO, UserVO _userVO);

	public MacroMasterVO fetchMacroInfo(MacroMasterVO _macroMstVO, UserVO _UserVO);

	public void saveModMacroInfo(MacroMasterVO _macroMstVO, UserVO _UserVO);

	public DrugRouteMstVO getItemName(String itemID, UserVO _UserVO);

	public DrugDoseMstVO getDrugItemName(String itemID, UserVO _UserVO);

	public boolean saveDrugRouteInfo(DrugRouteMstVO drugRouteMstVO, UserVO _userVO);

	public DrugRouteMstVO fetchDrugRouteInfo(DrugRouteMstVO _drugRouteMstVO, UserVO _UserVO);
	
	// Added By : Chetan Sharma 
	//Date:07_12_2015
	public DrugRouteMstVO getDrugRouteInfo(DrugRouteMstVO _drugRouteMstVO, UserVO _UserVO);

	public void saveModDrugRouteInfo(DrugRouteMstVO _drugRouteMstVO, UserVO _UserVO);

	public Map getModifyDetail(DeskTypeMenuMappingVO deskTypeMenuMappingVO, UserVO _userVO);

	public void saveModifyDetail(DeskTypeMenuMappingVO[] _deskTypeMenuMappingVO, UserVO _userVO);

	// Desk Wise Default Profile Master

	public List getDeskType(UserVO _userVO);

	public List getDeskName(String deskTypeId, UserVO _userVO);

	public List getMenuName(String deskId, UserVO _userVO);

	public List getAllMenuName(String deskId, UserVO _userVO);

	public List getNonDefaultMenuName(String deskId, UserVO _userVO);

	public List getDefaultMenuName(String deskId, UserVO _userVO);

	public void saveDefaultProfileDetails(DeskWiseDefaultProfileMstVO _DeskWiseDefaultProfileMstVO, UserVO _UserVO);

	// Image Pointer Master
	public void saveImagePointerDetail(ImagePointerMasterVO imagePointerMasterVO, UserVO _userVO);

	public ImagePointerMasterVO getModifyDetail(ImagePointerMasterVO imagePointerMasterVO, UserVO _userVO);

	public void saveModifyDetail(ImagePointerMasterVO imagePointerMasterVO, UserVO _userVO);

	public ParameterRangeMasterVO getParaName(String paraId, UserVO _UserVO);

	public boolean saveParaRangeInfo(ParameterRangeMasterVO parameterRangeMasterVO, UserVO _userVO);

	public ParameterRangeMasterVO fetchParaRangeInfo(ParameterRangeMasterVO parameterRangeMasterVO, UserVO _UserVO);

	public boolean saveModParaRangeInfo(ParameterRangeMasterVO parameterRangeMasterVO, UserVO _UserVO);

	// Profile Restricted Category
	public void saveProfileRestrictedCategory(ProfileRestrictedCategoryMasterVO profileRestrictedCategoryMasterVO, UserVO _userVO);

	public ProfileRestrictedCategoryMasterVO fetchPatientCatModify(ProfileRestrictedCategoryMasterVO profileRestrictedCategoryMasterVO, UserVO _UserVO);

	public void modifySaveProfileRestrictedCategory(ProfileRestrictedCategoryMasterVO profileRestrictedCategoryMasterVO, UserVO _userVO);

	// Profile Group Master
	public Map fetchProfileGroupEssentials(UserVO _userVO);

	public void saveProfileGroupDetail(ProfileGroupMasterVO profileGroupMasterVO, UserVO _userVO);

	public ProfileGroupMasterVO fetchProfileGroupDetailModify(ProfileGroupMasterVO profileGroupMasterVO, UserVO _UserVO);

	public void modifySaveProfileGroupMaster(ProfileGroupMasterVO profileGroupMasterVO, UserVO _userVO);

	// Profile Group Detail

	public Map fetchProfileGroupDetailEssentials(UserVO _userVO);

	public void saveProfileGroupAccessPrivDetail(List<ProfileGroupDtlVO> lstProfileAccesses, UserVO _userVO);

	public List<ProfileGroupDtlVO> fetchProfileGroupDetailAccessModify(ProfileGroupDtlVO profileGroupDetailVO, UserVO _UserVO);

	public void modifySaveProfileGroupDetail(List<ProfileGroupDtlVO> lstProfileAccesses, ProfileGroupDtlVO profileGroupDtlVO, UserVO _userVO);

	// Profile Access Policy

	public Map fetchProfileAccessPolicyEssentials(UserVO _userVO);

	public void saveProfileAccessPolicy(List<ProfileAccessPolicyVO> profileAccessPolicyVOList, UserVO _userVO);

	public ProfileAccessPolicyVO fetchProfileAccessPolicyModify(ProfileAccessPolicyVO profileAccessPolicyVO, UserVO _UserVO);

	public void modifySaveProfileAccessPolicy(ProfileAccessPolicyVO profileAccessPolicyVO, UserVO _userVO);

	public void saveUnitExtTreatDetail(List unitExtTreatLst, UserVO userVO);
	public void saveUnitDrugDetail(List unitDrugVOLst, UserVO userVO);

	public void modifySaveUnitExtTreat(List unitExtTreatVOLst, UserVO userVO);
	public void modifySaveUnitDrugDetail(List unitDrugVOLst, UserVO userVO);
	public void saveUnitMacroDetail(List unitMacroTreatLst, UserVO userVO);

	public List getProfileType(UserVO userVO);

	public Map getDeskMenuForProfileMapping(String profileType, UserVO userVO);

	public void saveProfileTypeTabMapping(ProfileTypeTabMappingVO[] insertProfileTypeTabMappingVO,
			ProfileTypeTabMappingVO[] updateProfileTypeTabMappingVO, UserVO userVO);

	public void modifySaveUnitMacro(List unitMacroVOLst, UserVO userVO);

	/**
	 * Save the profile type
	 * 
	 * @param profileTypeMasterVO
	 * @param userVO
	 */
	public void saveProfileType(ProfileTypeMstVO profileTypeMasterVO, UserVO userVO);

	public void saveProfileRestrictedCategory(ProfileRestrictedCategoryMasterVO[] insertProfileRestrictedCatMstVO,
			ProfileRestrictedCategoryMasterVO[] updateProfileRestrictedCatMstVO, UserVO userVO);

	public List<Entry> getDeptUnitMappedWithProfileAccess(ProfileAccessPolicyVO profileAccessPolicyVO, UserVO userVO);

	public boolean saveKeywordMstDetail(EpisodeKeywordsMasterVO keywordMstVO, UserVO userVO);

	public Map getDataForModifyKeywordMst(EpisodeKeywordsMasterVO keywordMstVO, UserVO userVO);

	public boolean saveModKeywordMaster(EpisodeKeywordsMasterVO keywordMstVO, UserVO _UserVO);

	public boolean saveDrugListMstDetail(DrugListMasterVO drugListMasterVO, UserVO userVO);

	public Map getDataForModifyDrugListMst(DrugListMasterVO drugListMasterVO, UserVO userVO);

	public boolean saveModDrugListMstDetail(DrugListMasterVO drugListMasterVO, UserVO userVO);
// Added By: Chetan Sharma 
// Date : 11-12-2015	
	public Map getDrugListItemMasterEssential(PatientDetailVO _patDetailVO ,UserVO userVO);

	public boolean saveDrugListItemMstDetail(List drugListItemMstVOList, UserVO userVO);

	public Map getDataForModifyDrugListItemMst(DrugListItemMstVO drugListItemMstVO, UserVO userVO);

	public void saveModifyDrugListItemMstDetail(List selectedDrugList, UserVO userVO);

	public void saveUnitDrugList(List unitDrugListVOLst, UserVO userVO);

	public void modifySaveDrugList(List unitDrugListVOLst, UserVO userVO);

	// Saving Addendant Reason Record
	public void saveAttendantReason(AttendantReasonMasterVO vo, UserVO _userVO);

	// Getting Addendant Reason Record
	public AttendantReasonMasterVO getAttendantReason(AttendantReasonMasterVO _attReasonVO, UserVO _userVO);

	// Modifying Addendant Reason Record
	public void updateAttendantReason(AttendantReasonMasterVO _attReasonVO, UserVO _userVO);

	// Saving Keyword Unit Record Unit-Wise
	public void saveUnitEpisodeKeywords(List<UnitEpisodeKeywordVO> _lstUnitKeywords, UserVO _UserVO);

	// Modifying Unit Episode Keywords
	public void modifyUnitEpisodeKeywords(List<UnitEpisodeKeywordVO> _lstUnitKeywords, String _deptUnitCode, UserVO _userVO);

	// Modifying ProfileType detail
	public ProfileTypeMstVO getModifyDetail(ProfileTypeMstVO profileTypeMstVO, UserVO userVO);

	public void modifySaveProfileType(ProfileTypeMstVO profileTypeMstVO, UserVO userVO);

	// Saving ICD Include Exclude Record
	public void saveICDIncludeExclude(IcdDiseaseMasterVO _icdDiseaseVO, UserVO _userVO);

	// Getting ICD Include Exclude Record
	public IcdDiseaseMasterVO getICDIncludeExcludeRecord(IcdDiseaseMasterVO _icdDiseaseVO, UserVO _userVO);

	// Modifying ICD Include Exclude Record
	public void updateICDIncludeExcludeRecord(IcdDiseaseMasterVO _icdDiseaseVO, UserVO _userVO);

	// Getting Disease for Patient Alert
	public Map getPatientAlertEssentials(UserVO _userVO);

	// Saving Patient Alert Detail

	public void savePatientAlert(PatientAlertMasterVO _patientAlertMasterVO, UserVO _userVO);

	// Fetchning Patient Alert Detail

	public PatientAlertMasterVO fetchPatientAlertModify(PatientAlertMasterVO _patientAlertMasterVO, UserVO _userVO);

	// Saving modified Patient Alert detail
	public void modifySavePatientAlert(PatientAlertMasterVO _patientAlertMasterVO, UserVO _userVO);

	// Getting Chart Parameter Essentials
	public Map<String, Object> getChartParameterEssentials(UserVO _userVO);

	// Getting parameter for Chart Parameter Mapping
	public Map getParameterForChart(String _chartId, UserVO _userVO);

	// Saving Chart Parameter Mapping
	public void saveChartParameterMapping(String _chartId, List<ChartParameterMappingVO> _insertList, UserVO _userVO);

	// Saving Disease Site Detail
	public void saveDiseaseSite(DiseaseSiteMasterVO _diseaseSiteVO, UserVO _userVO);

	// Fetching Disease Site Detail
	public DiseaseSiteMasterVO fetchDiseaseSiteDtl(DiseaseSiteMasterVO _diseaseSiteVO, UserVO _userVO);

	// Modifying Disease Site Detail
	public void modifyDiseaseSite(DiseaseSiteMasterVO _diseaseSiteVO, UserVO _userVO);

	// save chart master
	public void saveChart(ChartMasterVO _chartMasterVO, UserVO _userVO);

	public ChartMasterVO getModifyDetail(ChartMasterVO _chartMasterVO, UserVO _userVO);

	public void modifySaveChart(ChartMasterVO _chartMasterVO, UserVO _userVO);

	/*
	 * Chart Unit Mapping
	 * 
	 */

	public void saveChartUnitList(List _chartUnitMapppingVO, UserVO _userVO);

	public void modifySaveChartList(List _chartUnitMapppingVO, UserVO _userVO);

	// Save Dept Icd Mapping

	public void saveDeptUnitIcdMapping(List _departmentIcdMasterVOLst, UserVO _userVO);

	// Fetching Department Unit Icd Detail
	public Map getDeptUnitIcdForModify(DepartmentIcdMasterVO _vo, UserVO _userVO);

	// Save Modified Department Unit Icd Details
	public void modifySaveDeptIcdMapping(List _departmentIcdMasterVOLst, UserVO _userVO);

	public Map getModDisease(String _deptCode, String _icdSubgroupCode, UserVO _userVO);

	public void saveDeptUnitHosDisease(List _departmentHosDisVOLst, UserVO _userVO);

	public Map getUnitWiseMappedHosDiseasForModify(DepartmentHosDiseaseMstVO _vo, UserVO _userVO);

	public void modifySaveDeptUnitHosDiseaseMapping(List _departmentHosDiseaseMstVO, UserVO _userVO);

	/**
	 * Save ICD Mapping
	 */
	public void saveIcdMapping(List listIcdMasterVO_p, UserVO userVO_p);

	// to get modified detail of ICD mapping
	public Map getIcdMappingForModify(IcdMappingMasterVO _vo, UserVO userVO_p);

	// to get modified ICD disease
	public Map getModIcdDisease(String strMappingType_p, String strMappingId_p, String strIcdSubGroupCode_p, UserVO userVO_p);

	public void modifySaveIcdMapping(List listIcdMappingMasterVO_p, UserVO userVO_p);

	/*
	 * To Get Initial Add Page
	 */
	public Map<String, Object> getInitializeAdd(UserVO _userVO);

	/*
	 * Populating the Parent Modifier Combo
	 */
	public List<Entry> getParentModifier(IcdIndexLevelMasterVO icdIndexLevelMasterVO, UserVO _userVO);

	/*
	 * Populating the Icd Sub Group Combo
	 */
	public List<IcdSubgroupMasterVO> getIcdSubGroupByGroupCode(String _icdGroupCode, UserVO _userVO);

	// Get Disease SubGroup Wise
	public List<IcdDiseaseMasterVO> getDiseaseBySubGroup(String _icdSubgroupCode, UserVO _userVO);

	// To save Data on Add Page of IcdIndexLevelMaster
	public boolean saveIcdIndexLevelMaster(IcdIndexLevelMasterVO vo, UserVO userVO);

	// To get modify details for IcdIndexLevelMaster
	public void modifyRecordIcdIndexLevelMaster(IcdIndexLevelMasterVO vo, UserVO userVO);

	// To ModifySave a Record for IcdIndexLevelMaster
	public boolean modifySaveIcdIndexLevelMaster(IcdIndexLevelMasterVO vo, UserVO userVO);

	// To View Page for IcdIndexLevelMaster
	public void getViewRecordIcdIndexLevelMaster(IcdIndexLevelMasterVO vo, UserVO userVO);

	/*
	 * To get Group Values
	 */
	public Map<String, Object> getInitializeGroup(UserVO strUserVO_p);

	// for saving Icd Index master
	public boolean saveIcdIndexMaster(IcdIndexMasterVO vo, UserVO userVO);

	// To get modify details for IcdIndexLevelMaster
	public void modifyRecordIcdIndexMaster(IcdIndexMasterVO vo, UserVO userVO);

	// To ModifySave a Record for IcdIndexMaster
	public boolean modifySaveIcdIndexMaster(IcdIndexMasterVO vo, UserVO userVO);

	// To Initialize Index Term

	public Map<String, Object> getInitializeIndexTerm(UserVO _userVO);

	// To initialize Modifier Term

	public List<Entry> getModifier(String strIndex, UserVO _userVO);

	public List<IcdIndexLevelMasterVO> getSeeTerms(String strIndex, UserVO _userVO);

	public List<Entry> getInitializeSubModifierNext(String strModifierID, String level, UserVO _userVO);

	public List<IcdIndexLevelMasterVO> getSeeTermsForModi(String strModId, UserVO _userVO);

	public void saveIcdCrossReferenceMaster(IcdCrossRefMasterVO vo, UserVO userVO);

	// Getting Disease for Hospital Disease Master
		public Map getHospitalDiseaseEssentials(UserVO _userVO);

		// Saving Hospital Disease Detail

		public void saveHospitalDisease(HospitalDiseaseMasterVO _hospitalDiseaseMasterVO, UserVO _userVO);

		// Fetching Hospital Disease Detail

		public HospitalDiseaseMasterVO fetchHospitalDiseaseModify(HospitalDiseaseMasterVO _hospitalDiseaseMasterVO, UserVO _userVO);

		// Saving modified Hospital Disease detail
		public void modifySaveHospitalDisease(HospitalDiseaseMasterVO _hospitalDiseaseMasterVO, UserVO _userVO);

		public Map getHospitalDiseaseMappings(HospitalDiseaseMasterVO _hospitalDiseaseMasterVO);
		
		// ICD Snomed Mapping Master
		
				//Getting ICD And Snomed for ICD Snomed Mapping Master
				public Map getIcdSnomedEssentials(UserVO _userVO);

				// Saving ICD Snomed Mapping Detail

				public void saveIcdSnomedMapping(IcdSnomedMappingMasterVO _icdSnomedMappingMasterVO, UserVO _userVO);

				//Fetching ICD Snomed Already Mapped

				public IcdSnomedMappingMasterVO fetchIcdSnomedModify(IcdSnomedMappingMasterVO _icdSnomedMappingMasterVO, UserVO _userVO);

				public Map fetchIcdSnomedMapping(IcdSnomedMappingMasterVO _icdSnomedMappingMasterVO);
//added by sandip naik on 20/06/2017 for template list
				public Map getTemplateListForSymptom(UserVO _UserVO);
//to save template list
				public void addSymptomWiseTemplateList(SymptomWiseTemplateMappingMasterVO[] voSymptomWiseTemplate,UserVO _UserVO);

				public Map getDetails(SymptomWiseTemplateMappingMasterVO symptomWiseTemplateMappingMasterVO,UserVO _userVO);

				public void saveModifyDetail(SymptomWiseTemplateMappingMasterVO[] _symptomWiseTemplateMappingMasterVO,UserVO _userVO);//end by sandip naik
	
				//Save the new single page Interface Master(Added By Shweta on 12-03-2019)
				public void saveSinglePageInterface(SinglePageInterfaceMasterVO singlePageInterfaceMasterVO, UserVO userVO);
				public SinglePageInterfaceMasterVO getSinglePageModifyDetail(SinglePageInterfaceMasterVO singlePageInterfaceMasterVO, UserVO userVO);
				public void modifySaveSinglePage(SinglePageInterfaceMasterVO singlePageInterfaceMasterVO, UserVO _userVO);
				
				public byte[] fetchimageFromPostgres(String imageCode);
				
}
