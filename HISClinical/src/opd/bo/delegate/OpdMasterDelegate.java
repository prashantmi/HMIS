package opd.bo.delegate;

import hisglobal.business.Delegate;
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
import hisglobal.vo.IcdCrossRefMasterVO;
import hisglobal.vo.IcdDiseaseMasterVO;
import hisglobal.vo.IcdHospitalMasterVO;
import hisglobal.vo.IcdIndexLevelMasterVO;
import hisglobal.vo.IcdIndexMasterVO;
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

import opd.bo.OpdMasterBO;
import opd.bo.OpdMasterBOi;

public class OpdMasterDelegate extends Delegate{
	
	public OpdMasterDelegate(){
		super(new OpdMasterBO()); ///<<Setting the service provider
	}

	
	public void saveDeptIcdCode(DepartmentIcdMasterVO[] _deptIcdVO,String _icdCodeType,UserVO _userVO){
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveDeptIcdCode(_deptIcdVO,_icdCodeType,  _userVO);
		
	}
	
	public  DepartmentIcdMasterVO[] getDeptIcdDetail(DepartmentIcdMasterVO _deptIcdVO,UserVO _userVO){
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return serviceBO.getDeptIcdDetail(_deptIcdVO,  _userVO);
	}
	
	/**
	 * @param _deptHosDisVO
	 * @param _userVO
	 * @return
	 */
	public  DepartmentHosDisMasterVO[] getDeptHosDisDetail(DepartmentHosDisMasterVO _deptHosDisVO,UserVO _userVO){
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return serviceBO.getDeptHosDisDetail(_deptHosDisVO,  _userVO);
	}
	/**
	 * @param _deptHosDisVO
	 * @param _hosDisCodeType
	 * @param _userVO
	 */
	public void saveDeptHosDisCode(DepartmentHosDisMasterVO[] _deptHosDisVO,String _hosDisCodeType,UserVO _userVO){
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveDeptHosDisCode(_deptHosDisVO,_hosDisCodeType,_userVO);
		
	}
	
	public void deleteDeptIcdCode(DepartmentIcdMasterVO _deptIcdVO,String[] _selectedRecord,String _choice,UserVO _userVO){
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.deleteDeptIcdCode(_deptIcdVO,  _selectedRecord,_choice,_userVO);
		
	}
	
	/**
	 * @param _deptHosDisVO
	 * @param _selectedRecord
	 * @param _choice
	 * @param _userVO
	 */
	public void deleteDeptHosDisCode(DepartmentHosDisMasterVO _deptHosDisVO,String[] _selectedRecord,String _choice,UserVO _userVO){
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.deleteDeptHosDisCode(_deptHosDisVO,  _selectedRecord,_choice,_userVO);
		
	}



	//* Saving Data to Desk Master
	public String saveDesk(DeskMasterVO _DskMstVO, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return ( serviceBO.saveDesk(_DskMstVO,_userVO));
	}

	//* Saving Data to Desk Detail 
	public void saveDeskDetail(DeskDetailVO _DskDtlVO, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveDeskDetail(_DskDtlVO,_userVO);
	}
	
	// Saving Desk Detail 
	public void saveCompleteDeskDetail(DeskMasterVO _deskVO,List<DeskDetailVO> _lstDeskDtl, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveCompleteDeskDetail(_deskVO, _lstDeskDtl, _userVO);
	}
	
	// Saving Global Desk Detail 
	public void saveCompleteGlobalDeskDetail(DeskMasterVO _deskVO,List<DeskDetailVO> _lstDeskDtl, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveCompleteGlobalDeskDetail(_deskVO, _lstDeskDtl, _userVO);
	}

	//* Getting Desk VO By Desk Id
	public DeskMasterVO fetchDeskVOByDeskId(String _DeskId, UserVO _UserVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return serviceBO.fetchDeskVOByDeskId(_DeskId,_UserVO);
	}
	
	//* Getting Desk VO By Desk Id
		public DeskMasterVO fetchDeskVOByGlobalDeskId(String _DeskId, UserVO _UserVO)
		{
			OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
			return serviceBO.fetchDeskVOByGlobalDeskId(_DeskId,_UserVO);
		}
	
	
	
	//* Getting Desk Detail VO Array By Desk 
	public DeskDetailVO[] getMenuListByDeskId(DeskMasterVO _voDeskMst, UserVO _UserVo)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return serviceBO.getMenuListByDeskId(_voDeskMst, _UserVo);
	}

	
	//* Getting Desk Detail VO Array By Desk 
		public DeskDetailVO[] getMenuListByGlobalDeskId(DeskMasterVO _voDeskMst, UserVO _UserVo)
		{
			OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
			return serviceBO.getMenuListByGlobalDeskId(_voDeskMst, _UserVo);
		}

		
	//* Updating Data to Desk Master 
	public void updateDesk(DeskMasterVO _DskMstVO, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.updateDesk(_DskMstVO,_userVO);
	}

	// Updating Complete Desk Detail
	public void updateCompleteDesk(DeskMasterVO _deskVO, List<DeskDetailVO> _lstDeskDtl, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.updateCompleteDesk(_deskVO, _lstDeskDtl, _userVO);
	}
	
	// Updating Complete Desk Detail
		public void updateCompleteGlobalDesk(DeskMasterVO _deskVO, List<DeskDetailVO> _lstDeskDtl, UserVO _userVO)
		{
			OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
			serviceBO.updateCompleteGlobalDesk(_deskVO, _lstDeskDtl, _userVO);
		}

	//* Deleting All Menus From Desk By Desk Id 
	public void deleteDeskMenus(String _DeskId, UserVO _UserVo)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.deleteDeskMenus(_DeskId,_UserVo);
	}

	//* Save User Desk Menu Record 
	public void saveUserDeskMenu(UserDeskMenuMasterVO _UserDeskMenuMstVO, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveUserDeskMenu(_UserDeskMenuMstVO,_userVO);
	}
	
	//* Save User Desk Menu Record 
	public void saveUserDeskMenu1(UserDeskMenuMasterVO[] _UserDeskMenuMstVO, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveUserDeskMenu1(_UserDeskMenuMstVO,_userVO);
	}
	
	
	
	//* Updating User Desk Menu Record 
	public void updateUserDeskMenu(UserDeskMenuMasterVO _UserDeskMenuMstVO, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.updateUserDeskMenu(_UserDeskMenuMstVO,_userVO);
	}

	
	//Inserting Allergy Type DynamicMode
	public boolean insertAllergyTypeDynamicMode(AllergyTypeVO _allergyTypeVO,UserVO _userVO)
	{
		OpdMasterBOi serviceBO=(OpdMasterBOi)super.getServiceProvider();
		return serviceBO.insertAllergyTypeDynamicMode(_allergyTypeVO,_userVO);
	}
	
	//Getting Allergy Type
	public AllergyTypeVO getAllergyType(String _allergyTypeCode,UserVO _userVO)
	{
		OpdMasterBOi serviceBO=(OpdMasterBOi)super.getServiceProvider();
		return (serviceBO.getAllergyType(_allergyTypeCode,_userVO));
	}

	public boolean updateAllergyType(AllergyTypeVO _allergyTypeVO,UserVO _userVO)
	{
		OpdMasterBOi serviceBO=(OpdMasterBOi)super.getServiceProvider();
		return serviceBO.updateAllergyType(_allergyTypeVO,_userVO);
	}

	
	public String getAllergySensitivitydesc(String sensitivity,UserVO _userVO)
	{
		OpdMasterBOi serviceBO=(OpdMasterBOi)super.getServiceProvider();
		return serviceBO.getAllergySensitivitydesc(sensitivity,_userVO);
	}
	//* Saving Template Record
	public void saveTemplate(OpdTemplateVO _tmpltVO, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveTemplate(_tmpltVO,_userVO);
	}

	//* Updating Template Record  
	/*public void updateTemplate(OpdTemplateVO _tmpltVO, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.updateTemplate(_tmpltVO,_userVO);
	}
	
	//* Adding Template with New Serial No 
	public void saveNewTemp(OpdTemplateVO _tmpltVO, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveNewTemp(_tmpltVO,_userVO);
	}*/

	//* Saving Parameter Record
	public void saveOPDParameter(OpdParameterVO _paraVO, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveOPDParameter(_paraVO,_userVO);
	}
	
	//* Saving Template Parameter Detail to TemplateParameterMaster
	/*public void saveParameterToTemplate(OpdTemplateParameterVO _tempParaVO, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveParameterToTemplate(_tempParaVO,_userVO);
	}*/
	
	//* Getting Template Data
	public void getTemplateDataById(OpdTemplateVO _voTemp, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.getTemplateDataById(_voTemp,_userVO);
	}

	// Saving Patient Parameter Detail
	/*public void savePatientParameters(OpdPatientParameterVO _patParaVO, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.savePatientParameters(_patParaVO,_userVO);
	}*/

	//* Delete the old Template Parameter Data By Template Id
	/*public void deleteTempParaById(String _TempId, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.deleteTempParaById(_TempId,_userVO);
	}*/

	//* Deleting UserDeskMenuTemplate Master By Given Conditions 
	public void deleteTemplateToDeskMenuTemplateMasterDeskWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.deleteTemplateToDeskMenuTemplateMasterDeskWise(_voUDMT,_UserVO);
	}
	
	//* Deleting UserDeskMenuTemplate Master By Given Conditions (Unit Wise)
	public void deleteTemplateToDeskMenuTemplateMasterUnitWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.deleteTemplateToDeskMenuTemplateMasterUnitWise(_voUDMT,_UserVO);
	}
	
	public void deleteTemplateToDeskMenuTemplateMasterUnitWardWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.deleteTemplateToDeskMenuTemplateMasterUnitWardWise(_voUDMT,_UserVO);
	}
	
	
	//* Deleting UserDeskMenuTemplate Master By Given Conditions (Unit Wise)
	public void deleteTemplateToDeskMenuTemplateMasterUnitSeatWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.deleteTemplateToDeskMenuTemplateMasterUnitSeatWise(_voUDMT,_UserVO);
	}

	//* Saving User Desk Menu Template Record 
	public void saveTemplateToDeskMenu(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveTemplateToDeskMenu(_voUDMT,_UserVO);
	}
	

	//* Saving User Desk Menu Template Record 
	public void saveTemplateToDeskMenuSeatWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveTemplateToDeskMenuSeatWise(_voUDMT,_UserVO);
	}
	
	public void saveTemplateToDeskMenuWardSeatWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveTemplateToDeskMenuWardSeatWise(_voUDMT,_UserVO);
	}
	
	public void saveTemplateToDeskMenuWardWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveTemplateToDeskMenuWardWise(_voUDMT,_UserVO);
	}
	
	
	//* Saving User Desk Menu Template Record Unit Wise
	public void saveTemplateToDeskMenuUnitWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveTemplateToDeskMenuUnitWise(_voUDMT,_UserVO);
	}
	
	public void saveTemplateToDeskMenuDeskWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveTemplateToDeskMenuDeskWise(_voUDMT,_UserVO);
	}
	
	/*public void saveUserDeskMenuTemplate(UserDeskMenuTemplateMasterVO _UserDeskMenuMstVO, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveUserDeskMenuTemplate(_UserDeskMenuMstVO,_userVO);
	}*/
	
	// Getting Unit Detaial 
	public UnitMasterVO getUnitDetails(String _deptUnitCode,String _sNo,UserVO _UserVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return (serviceBO.getUnitDetails(_deptUnitCode,_sNo,_UserVO));
	}
	
	// Saving OPD Macro Head
	public void saveMacroHead(DeskMenuMacroMstVO _deskMenuMacroVO,UserVO _UserVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveMacroHead(_deskMenuMacroVO,_UserVO);
	}
	
	// Getting OPD Macro Head for Modify
	public DeskMenuMacroMstVO getMacroHeadForModify(String _macroID,UserVO _UserVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return (serviceBO.getMacroHeadForModify(_macroID,_UserVO));
	}
	
	// Saving Modified OPD Macro Head
	public void modifySaveMacroHead(DeskMenuMacroMstVO _deskMenuMacroVO,UserVO userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.modifySaveMacroHead(_deskMenuMacroVO,userVO);
	}


	// * Creating Initial Deleted Entry for Image in Patient Image Detail  
	/**
	 * Creating Entry for Exam Image 
	 * @param _vo Patient Image Detail VO
	 * @param _UserVO User VO
	 * @return Patient Image Detail VO
	 */
	public OpdPatientImageDtlVO createEntryForImage(OpdPatientImageDtlVO _vo,UserVO _UserVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return serviceBO.createEntryForImage(_vo,_UserVO);
	}

	// * Removing Initial Deleted Entry for Image in Patient Image Detail  
	public void removeEntryForImage(OpdPatientImageDtlVO _vo,UserVO _UserVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.removeEntryForImage(_vo,_UserVO);
	}

	// * Saving Image to Patient in Patient Image Detail
	public void saveOpdPatientImage(OpdPatientImageDtlVO _vo,UserVO _UserVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveOpdPatientImage(_vo,_UserVO);
	}

	// * Getting Images List of Patient in given Episode Visit
	public List<OpdPatientImageDtlVO> getOPDPatOldImagesList(OpdPatientImageDtlVO _PatImgVo,UserVO _UserVO)
	{
		OpdMasterBOi serviceBO= (OpdMasterBOi)super.getServiceProvider();
		return serviceBO.getOPDPatOldImagesList(_PatImgVo,_UserVO);
	}
	
	// Saving Image Record
	public void saveImage(ImageMasterVO imageMasterVO,UserVO userVO)
	{
		OpdMasterBOi serviceBO= (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveImage(imageMasterVO,userVO);
	}
	
	// Getting Image for Modify
	public ImageMasterVO getImageForModify(String imageCode,String imageSlno,UserVO userVO)
	{
		OpdMasterBOi serviceBO= (OpdMasterBOi)super.getServiceProvider();
		return (serviceBO.getImageForModify(imageCode,imageSlno,userVO));
	}
	
	// Modifying Image Record 
	public void saveModifyImage(ImageMasterVO imageMasterVO,UserVO userVO)
	{
		OpdMasterBOi serviceBO= (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveModifyImage(imageMasterVO,userVO);
	}
	
	// Saving Unit Wise Image Description Assigned to Color 
	public void saveUnitImageDesc(List unitImageDescVOLst,UserVO userVO)
	{
		OpdMasterBOi serviceBO= (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveUnitImageDesc(unitImageDescVOLst,userVO);
	}
	
	public void saveModUnitImageDesc(List unitImageDescVoLst,UserVO userVO)
	{
		OpdMasterBOi serviceBO= (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveModUnitImageDesc(unitImageDescVoLst,userVO);
	}
	
	// Getting All Image Description assigned to Color for a particular Unit
	public List getAllColorImageDescForUnit(String selectedUnit,String imageId,String slNo,UserVO userVO)
	{
		OpdMasterBOi serviceBO= (OpdMasterBOi)super.getServiceProvider();
		return (serviceBO.getAllColorImageDescForUnit(selectedUnit,imageId,slNo,userVO));
	}
	
	// Deleting All image Description Assigned to Color for a particular Unit
	public void deleteUnitImageDesc(String unitCode,UserVO userVO)
	{
		OpdMasterBOi serviceBO= (OpdMasterBOi)super.getServiceProvider();
		serviceBO.deleteUnitImageDesc(unitCode,userVO);
	}
	
	// Saving Audio/Video File 
	public void saveAudioVideoFile(AudioVideoMasterVO audioVideoMasterVO,UserVO userVO)
	{
		OpdMasterBOi serviceBO= (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveAudioVideoFile(audioVideoMasterVO,userVO);
	}
	
	// Getting Audio/Video File for Modify
	public AudioVideoMasterVO getAudioVideoForModify(String fileCode,String slNo,UserVO userVO)
	{
		OpdMasterBOi serviceBO= (OpdMasterBOi)super.getServiceProvider();
		return (serviceBO.getAudioVideoForModify(fileCode,slNo,userVO));
	}
	
	// Saving Modified Audio/Video File
	public void saveModifyAudioVideo(AudioVideoMasterVO audioVideoVO,UserVO userVO)
	{
		OpdMasterBOi serviceBO= (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveModifyAudioVideo(audioVideoVO,userVO);
	}
	
	//* Saving Image Unit Record Unit-Wise
	public void saveImageUnit(List<OPDUnitImageMasterVO> _lstUnitImages, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveImageUnit(_lstUnitImages,_userVO);
	}
	
	public void ModifysaveImageUnit(List<OPDUnitImageMasterVO> _lstUnitImages, String _deptUnitCode, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.ModifysaveImageUnit(_lstUnitImages, _deptUnitCode, _userVO);
	}
	
	public String getUnitName(String _deptUnitCode,UserVO _UserVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return serviceBO.getUnitName(_deptUnitCode,_UserVO);
	}

	
	//* Getting Records
	public UserDeskMenuTemplateMasterVO getRecords(UserDeskMenuTemplateMasterVO _voUDMT,UserVO _UserVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return serviceBO.getRecords(_voUDMT,_UserVO);
	}
	
	public UserDeskMenuTemplateMasterVO getRecordsForWard(UserDeskMenuTemplateMasterVO _voUDMT,UserVO _UserVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return serviceBO.getRecordsForWard(_voUDMT,_UserVO);
	}

	//* Getting Image for View
	public OPDUnitImageMasterVO[] getImageForView(String deptUnitCode,UserVO userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return serviceBO.getImageForView(deptUnitCode,userVO);
	}
	
	public void saveUnitAudioVideo(UnitAudioVideoMasterVO unitAVMasterVO,UserVO userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveUnitAudioVideo(unitAVMasterVO,userVO);
	}
	public void deleteUnitAudioVideo(String unitCode,UserVO userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.deleteUnitAudioVideo(unitCode,userVO);
	}
	
	//saving HOspital ICD Master Data
	public void save(IcdHospitalMasterVO[] _VOs,UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.save(_VOs,_userVO);
	}
	
	//* Save User Desk Menu Record Unit wise 
	public void saveUserDeskUnitWardMappingMaster(UserDeskUnitWardMappingMasterVO _UserDeskUnitWardMappingVO, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveUserDeskUnitWardMappingMaster(_UserDeskUnitWardMappingVO,_userVO);
	}
	//* Save User Desk Menu Record both type
	public void saveUserDeskUnitWardMappingMasterUnitWardWise(UserDeskUnitWardMappingMasterVO _UserDeskUnitWardMappingVO, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveUserDeskUnitWardMappingMasterUnitWardWise(_UserDeskUnitWardMappingVO,_userVO);
	}
	//* Updating User Desk Menu Record 
	public void updateUserDeskUnitWardMappingMasterRecord(UserDeskUnitWardMappingMasterVO _UserDeskUnitWardMappingVO, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.updateUserDeskUnitWardMappingMasterRecord(_UserDeskUnitWardMappingVO,_userVO);
	}
	//*Getting the values for combo in allergy type master
	 public Map getTheValues(UserVO _userVO)
		{
		 OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
			return (serviceBO.getTheValues(_userVO));
		}	
	 
	 
	 public List getTableData(UserVO _userVO,String TableId)
		{
		 OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
			return (serviceBO.getTableData(_userVO,TableId));
		}
	 public List getPrimaryKey(String tableName)
	 {
		 OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
		 return (serviceBO.getPrimaryKey( tableName));
	 }
	 
	 public List getAllergySensistivity(UserVO _UserVO)
	 {
		 OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
		 return (serviceBO.getAllergySensistivity( _UserVO));
	 }
	 
	 
	//*Allergy Wise Symptom Master
		public Map getAllergyTypeNotInAllergyWiseSymptom(UserVO _userVO)
		{
			OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
			return (serviceBO.getAllergyTypeNotInAllergyWiseSymptom(_userVO));
		}
		
		
		public void addAllergyTypeAgainstSymptom(AllergyWiseSymptomMasterVO[] _voAllergyWiseSymptom, UserVO _UserVO)
		{
			OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
			serviceBO.addAllergyTypeAgainstSymptom(_voAllergyWiseSymptom,_UserVO);
		}
		
		
		public Map getDetails(AllergyWiseSymptomMasterVO allergyWiseSymptomMasterVO,UserVO _userVO)
		{
			OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
			return (serviceBO.getDetails(allergyWiseSymptomMasterVO,_userVO));
		}
		
		
		public List getDetail(AllergyWiseSymptomMasterVO allergyWiseSymptomMasterVO,UserVO _userVO)
		{
			OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
			return (serviceBO.getDetail(allergyWiseSymptomMasterVO,_userVO));
		}
		
		
	
	//Getting Service List By Service Type ID
	public Map getDetail(ConsentMappingMasterVO consentMappingMasterVO,UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return serviceBO.getDetail(consentMappingMasterVO,_userVO);
	}
	//saving consent mapping master detail
	
	public void saveDetail(ConsentMappingMasterVO[] consentMappingMasterVO,String[] serviceId,List lstServices,UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveDetail(consentMappingMasterVO,serviceId,lstServices,_userVO);
		
	}
	//getting consent mapping master MOdify detail
	public Map getModifyDetail(ConsentMappingMasterVO consentMappingMasterVO,UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return serviceBO.getModifyDetail(consentMappingMasterVO,_userVO);
		
	}
//saving modify consent mapping master detail
	
	public void saveModifyDetail(ConsentMappingMasterVO[] consentMappingMasterVO,UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveModifyDetail(consentMappingMasterVO,_userVO);
		
	}
	///saving modify detail of 
	public  void saveAlergySymMasterModifyDetail(AllergyWiseSymptomMasterVO[] allergyWiseSymptomMasterVO,UserVO _UserVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveAlergySymMasterModifyDetail(allergyWiseSymptomMasterVO,_UserVO);
				
	}
	
	public UserDeskMenuMasterVO gettingUnitName(String _deptUnitCode,UserVO _UserVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return serviceBO.gettingUnitName(_deptUnitCode,_UserVO);
	}
	

	public UserDeskMenuTemplateMasterVO[] getTemplateByDeskType(String deskId,UserVO userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return serviceBO.getTemplateByDeskType(deskId,userVO);
	}
	
	public void saveForDeskId(UserDeskMenuTemplateMasterVO[] userDeskMenuDeskVO,UserVO userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveForDeskId(userDeskMenuDeskVO,userVO);
	}
	
	public void deleteForDeskId(String deskId,UserVO userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.deleteForDeskId(deskId,userVO);
	}
	
	public void saveForUnit(UserDeskMenuTemplateMasterVO[] userDeskMenuDeskVO,UserVO userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveForUnit(userDeskMenuDeskVO,userVO);
	}
	
	public UserDeskMenuTemplateMasterVO[] getTemplateByDeskNUnit(String unitCode,String deskId,UserVO userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return serviceBO.getTemplateByDeskNUnit(unitCode, deskId,userVO);
	}
	
	public void deleteForDeskNUnit(String unitCode,String deskId,UserVO userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.deleteForDeskNUnit(unitCode,deskId,userVO);
	}
	
	public UserDeskMenuTemplateMasterVO[] getTemplateByDeskUnitNSeat(String seatId,String unitCode,String deskId,UserVO userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return serviceBO.getTemplateByDeskUnitNSeat(seatId, unitCode, deskId,userVO);
	}
	
	public void deleteForDeskUnitNSeat(String seatId,String unitCode,String deskId,UserVO userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.deleteForDeskUnitNSeat(seatId,unitCode,deskId,userVO);
	}
	
	public UserDeskMenuTemplateMasterVO[] getTemplateByDeskUnitNWard(String wardCode,String unitCode,String deskId,UserVO userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return serviceBO.getTemplateByDeskUnitNWard(wardCode, unitCode, deskId,userVO);
	}
	
	public void deleteForDeskUnitNWard(String wardCode,String unitCode,String deskId,UserVO userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.deleteForDeskUnitNWard(wardCode,unitCode,deskId,userVO);
	}
	
	public UserDeskMenuTemplateMasterVO[] getTemplateByDeskUnitNWardNSeat(String seatId,String wardCode,String unitCode,String deskId,UserVO userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return serviceBO.getTemplateByDeskUnitNWardNSeat(seatId,wardCode, unitCode, deskId,userVO);
	}
	
	public void deleteForDeskUnitNWardNSeat(String seatId,String wardCode,String unitCode,String deskId,UserVO userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.deleteForDeskUnitNWardNSeat(seatId,wardCode,unitCode,deskId,userVO);
	}

	//// saving desk menu master detail
	
	public void saveDetail(DeskMenuMasterVO deskMenuMasterVO,UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveDetail(deskMenuMasterVO,_userVO);
	}
	public  DeskMenuMasterVO getModifyDetail(DeskMenuMasterVO deskMenuMasterVO,UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return serviceBO.getModifyDetail(deskMenuMasterVO,_userVO);
	}
	
	public void saveModifyDetail(DeskMenuMasterVO deskMenuMasterVO,UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveModifyDetail(deskMenuMasterVO,_userVO);
	}

	
	/* ************************ function for Template Mapping Master *****************************/

	public List getUnitNotAdded(TemplateMappingMstVO templateMappingVO,	UserVO userVO) {
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return serviceBO.getUnitNotAdded(templateMappingVO, userVO);
	}
	
	public void saveTemplateMapping(TemplateMappingMstVO[] templateMappingVOs,UserVO userVO) {
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveTemplateMapping(templateMappingVOs,userVO);
	}


	public Map getTemplateMapping(TemplateMappingMstVO templateMappingVO,UserVO userVO) {
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return serviceBO.getTemplateMapping(templateMappingVO,userVO);
	
	}

	public void modifyTemplateMapping(TemplateMappingMstVO[] updateTemplateMappingVOs,
			TemplateMappingMstVO[] insertTemplateMappingVOs, UserVO userVO) {
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.modifyTemplateMapping(updateTemplateMappingVOs,insertTemplateMappingVOs,userVO);
		
	}

	public Map getEssentialsForTemplateMapping(String templateCategory,UserVO userVO) {
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return serviceBO.getEssentialForTemplateMapping(templateCategory, userVO);
	}


	public List getAllDepartments(UserVO userVO) {
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return serviceBO.getAllDepartments(userVO);
	}


	public List getWardNotAdded(TemplateMappingMstVO templateMappingVO,
			UserVO userVO) {
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return serviceBO.getWardNotAdded(templateMappingVO,userVO);
	}


	public List getAllUnits(String deptCode, UserVO userVO) {
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return serviceBO.getAllUnits(deptCode, userVO);
	}
	////function for drug dose master///////////////
	public void saveDetail(DrugDoseMstVO drugDoseMstVO,UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveDrugDoseDetail(drugDoseMstVO,_userVO);
	}
	
	public DrugDoseMstVO getModifyDetail(DrugDoseMstVO drugDoseMstVO,UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return serviceBO.getDrugDoseModifyDetail(drugDoseMstVO,_userVO);
		
	}
	
	public void saveModifyDetail(DrugDoseMstVO drugDoseMstVO,UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveDrugDoseModifyDetail(drugDoseMstVO,_userVO);
	}
	
	public void saveDetail(DeskTypeMenuMappingVO[] _deskTypeMenuMappingVO,UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveDetail(_deskTypeMenuMappingVO,_userVO);
	}
	public  Map getModifyDetail(DeskTypeMenuMappingVO deskTypeMenuMappingVO,UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return serviceBO.getModifyDetail(deskTypeMenuMappingVO,_userVO);
	}
	public void saveModifyDetail(DeskTypeMenuMappingVO[] _deskTypeMenuMappingVO,UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveModifyDetail(_deskTypeMenuMappingVO,_userVO);
	}

	
	public MacroMasterVO getProcessName(String processID,UserVO _UserVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
		return (serviceBO.getProcessName(processID,_UserVO));
	}
	
	public boolean saveMacroInfo(MacroMasterVO macroMstVO,UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		boolean hasFlag=serviceBO.saveMacroInfo(macroMstVO,_userVO);
		return hasFlag;
	}
	
	public MacroMasterVO fetchMacroInfo(MacroMasterVO _macroMstVO, UserVO _UserVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
		return (serviceBO.fetchMacroInfo(_macroMstVO, _UserVO));
	}
	

	public void saveModMacroInfo(MacroMasterVO _macroMstVO, UserVO _UserVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
		serviceBO.saveModMacroInfo(_macroMstVO, _UserVO);
	}
	
	public DrugRouteMstVO getItemName(String processID,UserVO _UserVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
		return (serviceBO.getItemName(processID,_UserVO));
	}
	public DrugDoseMstVO getDrugItemName(String processID,UserVO _UserVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
		return (serviceBO.getDrugItemName(processID,_UserVO));
	}
	
	public boolean saveDrugRouteInfo(DrugRouteMstVO drugRouteMstVO,UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		boolean hasFlag=serviceBO.saveDrugRouteInfo(drugRouteMstVO,_userVO);
		return hasFlag;
	}
	
	public DrugRouteMstVO fetchDrugRouteInfo(DrugRouteMstVO drugRouteMstVO, UserVO _UserVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
		return (serviceBO.fetchDrugRouteInfo(drugRouteMstVO, _UserVO));
	}
	
	//Added By : Chetan Sharma 
	// Date: 07_12_2015
	public DrugRouteMstVO getDrugRouteInfo(DrugRouteMstVO drugRouteMstVO, UserVO _UserVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
		return (serviceBO.getDrugRouteInfo(drugRouteMstVO, _UserVO));
	}
	
	public void saveModDrugRouteInfo(DrugRouteMstVO drugRouteMstVO, UserVO _UserVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
		serviceBO.saveModDrugRouteInfo(drugRouteMstVO, _UserVO);
	}
	// Desk Wise Default Profile Master
	
	public List getDeskType(UserVO _userVO)
	{
	 OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
		return (serviceBO.getDeskType(_userVO));
	}
	
	public List getDeskName(String deskTypeId,UserVO _userVO)
	{
	 OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
		return (serviceBO.getDeskName(deskTypeId,_userVO));
	}
	
	public List getMenuName(String deskId,UserVO _userVO)
	{
	 OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
		return (serviceBO.getMenuName(deskId,_userVO));
	}
	
	public List getAllMenuName(String deskId,UserVO _userVO)
	{
	 OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
		return (serviceBO.getAllMenuName(deskId,_userVO));
	}
	
	public List getNonDefaultMenuName(String deskId,UserVO _userVO)
	{
	 OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
		return (serviceBO.getNonDefaultMenuName(deskId,_userVO));
	}
	
	public List getDefaultMenuName(String deskId,UserVO _userVO)
	{
	 OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
		return (serviceBO.getDefaultMenuName(deskId,_userVO));
	}
	
	public boolean saveDefaultProfileDetails(DeskWiseDefaultProfileMstVO _DeskWiseDefaultProfileMstVO, UserVO _UserVO)
	{
		boolean hasFlag=true;
		OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
		serviceBO.saveDefaultProfileDetails(_DeskWiseDefaultProfileMstVO, _UserVO);
		return hasFlag;
	}
	//Image Pointer Master
	public void saveImagePointerDetail(ImagePointerMasterVO imagePointerMasterVO,UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveImagePointerDetail(imagePointerMasterVO,_userVO);
	}
	
	public ImagePointerMasterVO getModifyDetail(ImagePointerMasterVO imagePointerMasterVO,UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return serviceBO.getModifyDetail(imagePointerMasterVO,_userVO);
		
	}
	
	public void saveModifyDetail(ImagePointerMasterVO imagePointerMasterVO,UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveModifyDetail(imagePointerMasterVO,_userVO);
	}
	
	
	public ParameterRangeMasterVO getParaName(String paraID,UserVO _UserVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
		return (serviceBO.getParaName(paraID,_UserVO));
	}
	
	
	
	public boolean saveParaRangeInfo(ParameterRangeMasterVO parameterRangeMasterVO,UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		boolean hasFlag=serviceBO.saveParaRangeInfo(parameterRangeMasterVO,_userVO);
		return hasFlag;
	}
	
	public ParameterRangeMasterVO fetchParaRangeInfo(ParameterRangeMasterVO parameterRangeMasterVO, UserVO _UserVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
		return (serviceBO.fetchParaRangeInfo(parameterRangeMasterVO, _UserVO));
	}
	
	public boolean saveModParaRangeInfo(ParameterRangeMasterVO parameterRangeMasterVO, UserVO _UserVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
		boolean flag=serviceBO.saveModParaRangeInfo(parameterRangeMasterVO, _UserVO);
		return flag;
	}
	
	// Profile Restricted Category
	public void saveProfileRestrictedCategory(ProfileRestrictedCategoryMasterVO profileRestrictedCategoryMasterVO,UserVO _userVO){
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveProfileRestrictedCategory(profileRestrictedCategoryMasterVO,_userVO);
		
	}
	
	public ProfileRestrictedCategoryMasterVO fetchPatientCatModify(ProfileRestrictedCategoryMasterVO profileRestrictedCategoryMasterVO, UserVO _UserVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return serviceBO.fetchPatientCatModify(profileRestrictedCategoryMasterVO,_UserVO);
	}
	
	public void modifySave(ProfileRestrictedCategoryMasterVO profileRestrictedCategoryMasterVO,UserVO _userVO){
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.modifySaveProfileRestrictedCategory(profileRestrictedCategoryMasterVO,_userVO);
		
	}
	
	//Profile Group Master
	public Map fetchProfileGroupEssentials(UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return (serviceBO.fetchProfileGroupEssentials(_userVO));
	}
	
	public void saveProfileGroupDetail(ProfileGroupMasterVO profileGroupMasterVO,UserVO _userVO){
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveProfileGroupDetail(profileGroupMasterVO,_userVO);
		
	}
	
	public ProfileGroupMasterVO fetchProfileGroupDetailModify(ProfileGroupMasterVO profileGroupMasterVO, UserVO _UserVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return serviceBO.fetchProfileGroupDetailModify(profileGroupMasterVO,_UserVO);
	}
	
	public void modifySaveProfileGroupMaster(ProfileGroupMasterVO profileGroupMasterVO,UserVO _userVO){
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.modifySaveProfileGroupMaster(profileGroupMasterVO,_userVO);
		
	}
	
	//Profile Group Detail
	
	public Map fetchProfileGroupDetailEssentials(UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return (serviceBO.fetchProfileGroupDetailEssentials(_userVO));
	}
	
	public void saveProfileGroupAccessPrivDetail(List<ProfileGroupDtlVO> lstProfileAccesses,UserVO _userVO){
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveProfileGroupAccessPrivDetail(lstProfileAccesses,_userVO);
	}
	
	public List<ProfileGroupDtlVO> fetchProfileGroupDetailAccessModify(ProfileGroupDtlVO profileGroupDetailVO, UserVO _UserVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return serviceBO.fetchProfileGroupDetailAccessModify(profileGroupDetailVO,_UserVO);
	}
	
	public void modifySaveProfileGroupDetail(List<ProfileGroupDtlVO> lstProfileAccesses,ProfileGroupDtlVO profileGroupDtlVO,UserVO _userVO){
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.modifySaveProfileGroupDetail(lstProfileAccesses,profileGroupDtlVO,_userVO);
		
	}
	
	// Profile Access Policy
	
	public Map fetchProfileAccessPolicyEssentials(UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return (serviceBO.fetchProfileAccessPolicyEssentials(_userVO));
	}
	
	public void saveProfileAccessPolicy(List<ProfileAccessPolicyVO> profileAccessPolicyVOList,UserVO _userVO){
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveProfileAccessPolicy(profileAccessPolicyVOList,_userVO);
	}
	
	public ProfileAccessPolicyVO fetchProfileAccessPolicyModify(ProfileAccessPolicyVO profileAccessPolicyVO, UserVO _UserVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return serviceBO.fetchProfileAccessPolicyModify(profileAccessPolicyVO,_UserVO);
	}
	
	public void modifySaveProfileAccessPolicy(ProfileAccessPolicyVO profileAccessPolicyVO,UserVO _userVO){
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.modifySaveProfileAccessPolicy(profileAccessPolicyVO,_userVO);
		
	}


	public void saveUnitExtTreatDetail(List unitExtTreatLst, UserVO userVO) {
	
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveUnitExtTreatDetail(unitExtTreatLst,userVO);
	}
	
	public void saveUnitDrugDetail(List unitDrugVOLst, UserVO userVO) {
		
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveUnitDrugDetail(unitDrugVOLst,userVO);
	}


	public void modifySaveUnitExtTreat(List unitExtTreatVOLst, UserVO userVO) {
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.modifySaveUnitExtTreat(unitExtTreatVOLst,userVO);
	}
	
	public void modifySaveUnitDrugDetail(List unitDrugVOLst, UserVO userVO) {
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.modifySaveUnitDrugDetail(unitDrugVOLst,userVO);
	}


	public void saveUnitMacroDetail(List unitMacroTreatLst, UserVO userVO) {
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveUnitMacroDetail(unitMacroTreatLst,userVO);
	}


	public List getProfileType(UserVO userVO) {
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return serviceBO.getProfileType(userVO);
	}


	public Map getDeskMenuForProfileMapping(String profileType, UserVO userVO) {
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return serviceBO.getDeskMenuForProfileMapping(profileType,userVO);
	}


	public void saveProfileTypeTabMapping(
			ProfileTypeTabMappingVO[] insertProfileTypeTabMappingVO,
			ProfileTypeTabMappingVO[] updateProfileTypeTabMappingVO,
			UserVO userVO) {
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveProfileTypeTabMapping(insertProfileTypeTabMappingVO,updateProfileTypeTabMappingVO,userVO);
	}

	public void modifySaveUnitMacro(List unitMacroVOLst, UserVO userVO) {
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.modifySaveUnitMacro(unitMacroVOLst,userVO);
	}

	public void saveProfileType(ProfileTypeMstVO profileTypeMasterVO,
			UserVO userVO) {
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveProfileType(profileTypeMasterVO,userVO);
		
	}
	
	
	
	


	public void saveProfileRestrictedCategory(
			ProfileRestrictedCategoryMasterVO[] insertProfileRestrictedCatMstVO,
			ProfileRestrictedCategoryMasterVO[] updateProfileRestrictedCatMstVO,
			UserVO userVO) {
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveProfileRestrictedCategory(insertProfileRestrictedCatMstVO,updateProfileRestrictedCatMstVO,userVO);
		
	}


	public List<Entry> getDeptUnitMappedWithProfileAccess(
			ProfileAccessPolicyVO profileAccessPolicyVO, UserVO userVO) {
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return serviceBO.getDeptUnitMappedWithProfileAccess(profileAccessPolicyVO,userVO);
	}


	public boolean saveKeywordMstDetail(EpisodeKeywordsMasterVO keywordMstVO,
			UserVO userVO) {
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		boolean hasFlag=serviceBO.saveKeywordMstDetail(keywordMstVO,userVO);
		return hasFlag;
	}


	public Map getDataForModifyKeywordMst(EpisodeKeywordsMasterVO keywordMstVO,
			UserVO userVO) {
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return serviceBO.getDataForModifyKeywordMst(keywordMstVO,userVO);
	}
	
	public boolean saveModKeywordMstDetail(EpisodeKeywordsMasterVO keywordMstVO,
			UserVO userVO) {
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		boolean hasFlag=serviceBO.saveModKeywordMaster(keywordMstVO,userVO);
		return hasFlag;
	}


	public boolean saveDrugListMstDetail(DrugListMasterVO drugListMasterVO,
			UserVO userVO) {
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		boolean hasFlag=serviceBO.saveDrugListMstDetail(drugListMasterVO,userVO);
		return hasFlag;
	}


	public Map getDataForModifyDrugListMst(DrugListMasterVO drugListMasterVO,
			UserVO userVO) {
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return serviceBO.getDataForModifyDrugListMst(drugListMasterVO,userVO);
	}


	public boolean saveModDrugListMstDetail(DrugListMasterVO drugListMasterVO,
			UserVO userVO) {
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		boolean hasFlag=serviceBO.saveModDrugListMstDetail(drugListMasterVO,userVO);
		return hasFlag;
	}


	public Map getDrugListItemMasterEssential(PatientDetailVO _patDetailVO,UserVO userVO) {
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return serviceBO.getDrugListItemMasterEssential( _patDetailVO,userVO);
	}


	public boolean saveDrugListItemMstDetail(List drugListItemMstVOList,
			UserVO userVO) {
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		boolean hasFlag=serviceBO.saveDrugListItemMstDetail(drugListItemMstVOList,userVO);
		return hasFlag;
	}


	public Map getDataForModifyDrugListItemMst(
			DrugListItemMstVO drugListItemMstVO, UserVO userVO) {
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return serviceBO.getDataForModifyDrugListItemMst(drugListItemMstVO,userVO);
	}


	public void saveModifyDrugListItemMstDetail(List selectedDrugList,
			UserVO userVO) {
		
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveModifyDrugListItemMstDetail(selectedDrugList,userVO);
	}


	public void saveUnitDrugList(List unitDrugListVOLst, UserVO userVO) {
		
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveUnitDrugList(unitDrugListVOLst,userVO);
	}


	public void modifySaveDrugList(List unitDrugListVOLst, UserVO userVO) {
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.modifySaveDrugList(unitDrugListVOLst,userVO);
		
	}

	// Saving Addendant Reason Record
	public void saveAttendantReason(AttendantReasonMasterVO _attReasonVO, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveAttendantReason(_attReasonVO, _userVO);
	}
	
	// Getting Addendant Reason Record
	public AttendantReasonMasterVO getAttendantReason(AttendantReasonMasterVO _attReasonVO, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return serviceBO.getAttendantReason(_attReasonVO, _userVO);
	}
	
	// Modifying Addendant Reason Record
	public void updateAttendantReason(AttendantReasonMasterVO _attReasonVO, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.updateAttendantReason(_attReasonVO,_userVO);
	}
	
	// Saving Keyword Unit Record Unit-Wise
	public void saveUnitWiseKeyword(List<UnitEpisodeKeywordVO> _lstUnitKeywords, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveUnitEpisodeKeywords(_lstUnitKeywords ,_userVO);
	}

	// Modifying Unit Episode Keywords
	public void modifyUnitEpisodeKeywords(List<UnitEpisodeKeywordVO> _lstUnitKeywords, String _deptUnitCode, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.modifyUnitEpisodeKeywords(_lstUnitKeywords, _deptUnitCode, _userVO);
	}
	
	//Profile Type Master detail
	public ProfileTypeMstVO getModifyDetail(ProfileTypeMstVO profileTypeMstVO, UserVO userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return serviceBO.getModifyDetail(profileTypeMstVO,userVO);
	}
	public void modifySave(ProfileTypeMstVO profileTypeMstVO,UserVO userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.modifySaveProfileType(profileTypeMstVO,userVO);
	}

	// Saving ICD Include Exclude Record
	public void saveICDIncludeExclude(IcdDiseaseMasterVO _icdDiseaseVO, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveICDIncludeExclude(_icdDiseaseVO, _userVO);
	}

	// Getting ICD Include Exclude Record
	public IcdDiseaseMasterVO getICDIncludeExcludeRecord(IcdDiseaseMasterVO _icdDiseaseVO, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return serviceBO.getICDIncludeExcludeRecord(_icdDiseaseVO, _userVO);
	}

	// Modifying ICD Include Exclude Record
	public void updateICDIncludeExcludeRecord(IcdDiseaseMasterVO _icdDiseaseVO, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.updateICDIncludeExcludeRecord(_icdDiseaseVO,_userVO);
	}
	
	
	// Getting Chart Parameter Essentials 
	public Map<String,Object> getChartParameterEssentials(UserVO _userVO)
	{
		OpdMasterBOi serviceBO= (OpdMasterBOi) super.getServiceProvider();
		return serviceBO.getChartParameterEssentials(_userVO);
	}
	
	// Getting Parameter Name for chart Mapping
	public Map getParameterForChart(String _chartId, UserVO _userVO) {
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return serviceBO.getParameterForChart(_chartId,_userVO);
	}
	
	// Saving Chart Parameter Mapping 
	public void saveChartParameterMapping(String _chartId, List<ChartParameterMappingVO> _insertList,UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveChartParameterMapping(_chartId, _insertList,_userVO);
	}
	
	// Saving Disease Site Detail
	public void saveDiseaseSite(DiseaseSiteMasterVO _diseaseSiteVO, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveDiseaseSite(_diseaseSiteVO, _userVO);
	}

	//Fetching Patient Alert essential Detail
	public Map fetch(UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return (serviceBO.getPatientAlertEssentials(_userVO));
	}

	// Fetching Disease Site Detail
	public DiseaseSiteMasterVO fetchDiseaseSiteDtl(DiseaseSiteMasterVO _diseaseSiteVO, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return serviceBO.fetchDiseaseSiteDtl(_diseaseSiteVO, _userVO);
	}

	// Modifying Disease Site Detail
	public void modifyDiseaseSite(DiseaseSiteMasterVO _diseaseSiteVO, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.modifyDiseaseSite(_diseaseSiteVO, _userVO);
	}
	// saving chart master
	public void saveChart(ChartMasterVO _chartMasterVO,UserVO _userVO) {
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveChart(_chartMasterVO,_userVO);
		
	}
	// Fetching Chart Detail
	public ChartMasterVO getModifyDetail(ChartMasterVO _chartMasterVO,UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		return serviceBO.getModifyDetail(_chartMasterVO,_userVO);
	}
	// Saving modified chart details
	public void modifySave(ChartMasterVO _chartMasterVO,UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.modifySaveChart(_chartMasterVO,_userVO);
	}
	
	// Saving Chart Unit Mapping Detail
	public void saveChartUnitList(List _chartUnitMapppingVO, UserVO _userVO) 
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveChartUnitList(_chartUnitMapppingVO,_userVO);
	}

	// Saving Modified Chart Unit Mapping Detail
	public void modifySaveChartList(List _chartUnitMapppingVO, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.modifySaveChartList(_chartUnitMapppingVO,_userVO);
	}
	
	// Saving Department Unit Icd Mapping
	public void saveDeptUnitIcdMapping(List _departmentIcdMasterVOLst,UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveDeptUnitIcdMapping(_departmentIcdMasterVOLst,_userVO);
	}
	// Fetching Department Unit Icd Detail
	public Map getDeptUnitIcdForModify(DepartmentIcdMasterVO _vo, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
		return serviceBO.getDeptUnitIcdForModify(_vo,_userVO);
	}
	// Saving Modified Department Unit Icd Detail
	public void modifySaveDeptIcdMapping(List _departmentIcdMasterVOLst,UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.modifySaveDeptIcdMapping(_departmentIcdMasterVOLst,_userVO);
	}
	public Map getModDisease(String _deptCode,String _icdSubgroupCode, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
		return serviceBO.getModDisease(_deptCode,_icdSubgroupCode, _userVO);
	}
	
	public void saveDeptUnitHosDisease(List _departmentHosDisVOLst, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveDeptUnitHosDisease(_departmentHosDisVOLst,_userVO);
	}
	
	public Map getUnitWiseMappedHosDiseasForModify(DepartmentHosDiseaseMstVO _vo, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
		return serviceBO.getUnitWiseMappedHosDiseasForModify(_vo,_userVO);
	}
	public void modifySaveDeptUnitHosDiseaseMapping(List _departmentHosDiseaseMstVO, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.modifySaveDeptUnitHosDiseaseMapping(_departmentHosDiseaseMstVO,_userVO);
	}
	
	// to save ICD Mapping
	public void saveIcdMapping(List listIcdMasterVO_p,UserVO userVO_p)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.saveIcdMapping(listIcdMasterVO_p,userVO_p);
	}
	// to get modify detail of icd mapping
	public Map getIcdMappingForModify(IcdMappingMasterVO vo_p, UserVO userVO_p)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
		return serviceBO.getIcdMappingForModify(vo_p,userVO_p);
	}
	//to get not mapped disease 
	public Map getModIcdDisease(String strMappingType_p,String strMappingId_p, String strIcdSubgroupCode_p, UserVO userVO_p)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
		return serviceBO.getModIcdDisease(strMappingType_p,strMappingId_p,strIcdSubgroupCode_p, userVO_p);
	}
	// to save modified detail
	public void modifySaveIcdMapping(List listIcdMappingMasterVO_p, UserVO userVO_p)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
		serviceBO.modifySaveIcdMapping(listIcdMappingMasterVO_p,userVO_p);
	}
	
	/*
	 *  Initial Add Page for Icd Index Level Master
	 */
	public Map<String,Object> getInitializeAdd(UserVO _userVO)
	{
	OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
	return (serviceBO.getInitializeAdd(_userVO));
	}
	
	/*
	 *  Populating the Parent Modifier Combo
	 */
	public List<Entry> getParentModifier(IcdIndexLevelMasterVO icdIndexLevelMasterVO, UserVO _userVO)
	{
	OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
	return (serviceBO.getParentModifier(icdIndexLevelMasterVO,_userVO));
	}
	
	/*
	 * Populating the Icd Sub Group Combo
	 */
	public List<IcdSubgroupMasterVO> getIcdSubGroupByGroupCode(String _icdGroupCode, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
		return (serviceBO.getIcdSubGroupByGroupCode(_icdGroupCode, _userVO));
	}
	
	/*
	 * Populating the Icd Disease Combo
	 */
	public List<IcdDiseaseMasterVO> getDiseaseBySubGroup(String icdSubgroupCode, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
		return serviceBO.getDiseaseBySubGroup(icdSubgroupCode, _userVO);
	}
	
	/*
	 * To Save Record on the Add Page
	 */
	public boolean saveIcdIndexLevelMaster(IcdIndexLevelMasterVO vo, UserVO userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
		return serviceBO.saveIcdIndexLevelMaster(vo, userVO);
		
	}
	
	/* 
	 * To get modify record for IcdIndexLevelMaster
	 */
	public void modifyRecordIcdIndexLevelMaster(IcdIndexLevelMasterVO vo, UserVO userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
		serviceBO.modifyRecordIcdIndexLevelMaster(vo, userVO);		
	}
	
	/*
	 * To ModifySave a Record for IcdIndexLevelMaster
	 */
	public boolean modifySaveIcdIndexLevelMaster(IcdIndexLevelMasterVO vo, UserVO userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
		return serviceBO.modifySaveIcdIndexLevelMaster(vo, userVO);
	}
	
	/*
	 * To View Page
	 */
	public void getViewRecordIcdIndexLevelMaster(IcdIndexLevelMasterVO vo, UserVO userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
		serviceBO.getViewRecordIcdIndexLevelMaster(vo, userVO);		
	}
		
	
	/*
	 * Initialize Group for Icd Index Master
	 * 
	 */
	public Map<String,Object> getInitializeGroup(UserVO _userVO)
	{
	OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
	return (serviceBO.getInitializeGroup(_userVO));
	}
	//for saving icd index master
	
	public boolean saveIcdIndexMaster(IcdIndexMasterVO vo, UserVO userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
		return serviceBO.saveIcdIndexMaster(vo, userVO);
		
	}
	
	/* 
	 * To get modify record for IcdIndexMaster
	 */
	public void modifyRecordIcdIndexMaster(IcdIndexMasterVO vo, UserVO userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
		serviceBO.modifyRecordIcdIndexMaster(vo, userVO);		
	}
	
	
	/*
	 * To ModifySave a Record for IcdIndexLevelMaster
	 */
	public boolean modifySaveIcdIndexMaster(IcdIndexMasterVO vo, UserVO userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
		return serviceBO.modifySaveIcdIndexMaster(vo, userVO);
	}
	
	
	/*
	 * To Initialize Index Term For Icd Cross Ref master
	 */
	
	public Map<String,Object> getInitializeIndexTerm(UserVO _userVO)
	{
	OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
	return (serviceBO.getInitializeIndexTerm(_userVO));
	}
	
	/*
	 * To Initialize Modifier Term For Icd Cross Ref master
	 */
	
	public List<Entry> getModifier(String strIndex, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
		return serviceBO.getModifier(strIndex, _userVO);
	}
	
	
	public List<IcdIndexLevelMasterVO> getSeeTerms(String strIndex, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
		return serviceBO.getSeeTerms(strIndex, _userVO);
	}
	
	public List<Entry> getInitializeSubModifierNext(String strModifierID, String level, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
		return serviceBO.getInitializeSubModifierNext(strModifierID, level, _userVO);
	}
	
	//initializing see terms for modifier
	public List<IcdIndexLevelMasterVO> getSeeTermsForModi(String strModId, UserVO _userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
		return serviceBO.getSeeTermsForModi(strModId, _userVO);
	}
	
	public void saveIcdCrossReferenceMaster(IcdCrossRefMasterVO vo, UserVO userVO)
	{
		OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
		serviceBO.saveIcdCrossReferenceMaster(vo, userVO);
	}
	
	//Getting Disease for Patient Alert
		public Map getPatientAlertEssentials(UserVO _userVO)
		{
			OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
			return (serviceBO.getPatientAlertEssentials(_userVO));
		}
		
		// Saving Patient Alert Detail
		public void savePatientAlert(PatientAlertMasterVO _patientAlertMasterVO,UserVO _userVO)
		{
			OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
			serviceBO.savePatientAlert(_patientAlertMasterVO, _userVO);
		}
		
		//Fetching Patient Alert essential Detail
		public Map fetchPatientAlertEssentials(UserVO _userVO)
		{
			OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
			return (serviceBO.getPatientAlertEssentials(_userVO));
		}
		// Saving  Modified Patient Alert Detail
		public void modifySavePatientAlert(PatientAlertMasterVO _patientAlertMasterVO,UserVO _userVO)
		{
			OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
			serviceBO.modifySavePatientAlert(_patientAlertMasterVO, _userVO);
		}
		//Fetching Patient Alert Detail
		public PatientAlertMasterVO  fetchPatientAlertModify(PatientAlertMasterVO _patientAlertMasterVO,UserVO _userVO)
		{
			OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
			return serviceBO.fetchPatientAlertModify(_patientAlertMasterVO, _userVO);
		}

		//Getting Disease for Hopsital Disease Master
		public Map getHospitalDiseaseEssentials(UserVO _userVO)
		{
			OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
			return (serviceBO.getHospitalDiseaseEssentials(_userVO));
		}
		
		// Saving Hopsital Disease Detail
		public void saveHospitalDisease(HospitalDiseaseMasterVO _hospitalDiseaseMasterVO,UserVO _userVO)
		{
			OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
			serviceBO.saveHospitalDisease(_hospitalDiseaseMasterVO, _userVO);
		}
		
		//Fetching Hopsital Disease essential Detail
		public Map fetchHospitalDiseaseEssentials(UserVO _userVO)
		{
			OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
			return (serviceBO.getHospitalDiseaseEssentials(_userVO));
		}
		// Saving  Modified Hopsital Disease Detail
		public void modifySaveHospitalDisease(HospitalDiseaseMasterVO _hospitalDiseaseMasterVO,UserVO _userVO)
		{
			OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
			serviceBO.modifySaveHospitalDisease(_hospitalDiseaseMasterVO, _userVO);
		}
		//Fetching Hopsital Disease Detail
		public HospitalDiseaseMasterVO  fetchHospitalDiseaseModify(HospitalDiseaseMasterVO _hospitalDiseaseMasterVO,UserVO _userVO)
		{
			OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
			return serviceBO.fetchHospitalDiseaseModify(_hospitalDiseaseMasterVO, _userVO);
		}
		
		public Map fetchHospitalDiseaseMappings(HospitalDiseaseMasterVO _hospitalDiseaseMasterVO)
		{
			OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
			return (serviceBO.getHospitalDiseaseMappings(_hospitalDiseaseMasterVO));
		}
		
		
		
		
		// ICD Snomed Mapping Master
		
		//Getting ICD And Snomed for ICD Snomed Mapping Master
				public Map getIcdSnomedEssentials(UserVO _userVO)
				{
					OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
					return (serviceBO.getIcdSnomedEssentials(_userVO));
				}
				
				// Saving ICD Snomed Mapping Detail
				public void saveIcdSnomedMapping(IcdSnomedMappingMasterVO _icdSnomedMappingMasterVO,UserVO _userVO)
				{
					OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
					serviceBO.saveIcdSnomedMapping(_icdSnomedMappingMasterVO, _userVO);
				}
				
				//Fetching ICD Snomed Already Mapped
				public IcdSnomedMappingMasterVO  fetchIcdSnomedModify(IcdSnomedMappingMasterVO _icdSnomedMappingMasterVO,UserVO _userVO)
				{
					OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
					return serviceBO.fetchIcdSnomedModify(_icdSnomedMappingMasterVO, _userVO);
				}
				
				public Map fetchIcdSnomedMapping(IcdSnomedMappingMasterVO _icdSnomedMappingMasterVO)
				{
					OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
					return (serviceBO.fetchIcdSnomedMapping(_icdSnomedMappingMasterVO));
				}

//added by sandip naik on 20/06/2017 for template list
				public Map getTemplateListForSymptom(UserVO _UserVO)
				{
					OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
					return (serviceBO.getTemplateListForSymptom(_UserVO));
			
					
					
				}


				public void addSymptomWiseTemplateList(SymptomWiseTemplateMappingMasterVO[] voSymptomWiseTemplate,UserVO _UserVO)
				{
				
					OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
					serviceBO.addSymptomWiseTemplateList(voSymptomWiseTemplate,_UserVO);
				
				}



				public Map getDetails(SymptomWiseTemplateMappingMasterVO symptomWiseTemplateMappingMasterVO,UserVO _userVO)
				{
					OpdMasterBOi serviceBO = (OpdMasterBOi) super.getServiceProvider();
					return (serviceBO.getDetails(symptomWiseTemplateMappingMasterVO,_userVO));
				}
				


				public void saveModifyDetail(SymptomWiseTemplateMappingMasterVO[] _symptomWiseTemplateMappingMasterVO,UserVO _userVO) 
				{
					OpdMasterBOi serviceBO = (OpdMasterBOi)super.getServiceProvider();
					serviceBO.saveModifyDetail(_symptomWiseTemplateMappingMasterVO,_userVO);
				}//end by sandip naik
				
				//Added by Shweta on 12-03-2019
				public void saveSinglePageInterface(SinglePageInterfaceMasterVO singlePageInterfaceMasterVO,
						UserVO userVO) {
					OpdMasterBO serviceBO = (OpdMasterBO)super.getServiceProvider();
					serviceBO.saveSinglePageInterface(singlePageInterfaceMasterVO,userVO);
					
				}
				
				public SinglePageInterfaceMasterVO getSinglePageModifyDetail(SinglePageInterfaceMasterVO singlePageInterfaceMasterVO, UserVO userVO)
				{
					OpdMasterBO serviceBO = (OpdMasterBO)super.getServiceProvider();
					return serviceBO.getSinglePageModifyDetail(singlePageInterfaceMasterVO,userVO);
				}
				
				public void singlePageModifySave(SinglePageInterfaceMasterVO singlePageInterfaceMasterVO,UserVO _userVO){
					OpdMasterBO serviceBO = (OpdMasterBO)super.getServiceProvider();
					serviceBO.modifySaveSinglePage(singlePageInterfaceMasterVO,_userVO);
					
				}
				
				public byte[] fetchimageFromPostgres(String imageCode)
				{
					OpdMasterBOi serviceBO= (OpdMasterBOi)super.getServiceProvider();
					byte[] getImage=serviceBO.fetchimageFromPostgres(imageCode);
					return getImage;
				}
				
				
}
