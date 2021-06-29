package enquiry.bo.delegate;

import hisglobal.business.Delegate;
import hisglobal.vo.AddressEnqiryVO;
import hisglobal.vo.BloodStockEnquiryVO;
import hisglobal.vo.CommonEnquiryVO;
import hisglobal.vo.ConsultantDetailVO;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.DepartmentLocationEnquiryVO;
import hisglobal.vo.EpisodeEnquiryVO;
import hisglobal.vo.OpdEnquiryVO;

import hisglobal.vo.PatientEnquiryVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import enquiry.bo.EnquiryBO;
import enquiry.bo.EnquiryBOi;
import enquiry.vo.BloodDonorEnquriyVO;
import enquiry.vo.HospitalConsultantEnquiryVO;
import enquiry.vo.HospitalDepartmentEnquiryVO;
import enquiry.vo.HospitalFacilityMasterVO;
import enquiry.vo.StaffEnquiryVO;

public class EnquiryDelegate extends Delegate {

	/**
	 * Constructor for Setting Service Provider
	 */
	public EnquiryDelegate(){
		super(new EnquiryBO()); ///<<Setting the service provider
	}
	
	public Map getPatientEnquiryEssentials(UserVO _userVO){
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getPatientEnquiryEssentials(_userVO);
	}
	
	public Map getDepartmentLocationEnquiryEssentials(UserVO _userVO){
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getDepartmentLocationEnquiryEssentials(_userVO);
	}
	
	public CommonEnquiryVO[] searchPatientDetails(PatientEnquiryVO _patEnquiryVO,AddressEnqiryVO _addEnquiryVO,EpisodeEnquiryVO _episodeEnquiryVO,UserVO _userVO){
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.searchPatientDetails(_patEnquiryVO,_addEnquiryVO,_episodeEnquiryVO,_userVO);
	}
	
	public CommonEnquiryVO[] searchInPatientDetails(PatientEnquiryVO _patEnquiryVO,AddressEnqiryVO _addEnquiryVO,EpisodeEnquiryVO _episodeEnquiryVO,UserVO _userVO){
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.searchInPatientDetails(_patEnquiryVO,_addEnquiryVO,_episodeEnquiryVO,_userVO);
	}
	
	public StaffEnquiryVO[] searchStaffDetail(StaffEnquiryVO staffEnquiryVO,UserVO _userVO){
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.searchStaffDetail(staffEnquiryVO,_userVO);
	}

	
	public Map getPatientDetail(String patientCRNo,UserVO userVO){
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getInPatientDetail(patientCRNo, userVO);
	}
	
	public  OpdEnquiryVO[] getOpdEnquirySearchDetail(OpdEnquiryVO _opdEnquiryVO,UserVO _userVO){
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getOpdEnquirySearchDetail(_opdEnquiryVO,_userVO);
	}

	
	public DepartmentLocationEnquiryVO searchDepartmentLocation(String _departmentCode,UserVO _userVO){
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.searchDepatmentLocation(_departmentCode,_userVO);
	}
	
	public OpdEnquiryVO[] getAllConsulatantDetailsForUnit(String _deptUnitCode,UserVO _userVO){
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getAllConsulatantDetailsForUnit(_deptUnitCode,_userVO);
	
	}
	public Map getStaffEnquiryEssentials(UserVO _userVO){
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getStaffEnquiryEssentials(_userVO);
		
	}

	public ConsultantDetailVO[] searchConsultantDetail(ConsultantDetailVO _consultantDetailVO,UserVO _userVO){
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.searchConsultantDetail(_consultantDetailVO,_userVO);
	}

	public Map getConsultantDetailEssentials(UserVO _userVO){
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getConsultantDetailEnquiryEssentials(_userVO);
	}
	
	public BloodStockEnquiryVO[] getBloodStockEnquiry(UserVO _userVO,String _choice){
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getBloodStockEnquiry(_userVO,_choice);
	}

	public List getBloodDonorGroups(UserVO _userVO){
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getBloodDonorGroups(_userVO);
	}
	
	public BloodDonorEnquriyVO[] getBloodDonorEnquiryDetails(UserVO _userVO,BloodDonorEnquriyVO _bloodDonorVO){
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getBloodDonorEnquiryDetails(_userVO,_bloodDonorVO);
	}
	
	public List  getFreePatCatList(UserVO userVO){
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getFreePatCatList( userVO);
	}
	
	public Map  getDepartmentEnquiryEssential(UserVO userVO){
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getDepartmentEnquiryEssential( userVO);
	}
	
	public HospitalDepartmentEnquiryVO[] getDepartmentEnquiryDetail(String _deptCode,String _deptTypeCode,UserVO _userVO){
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getDepartmentEnquiryDetail( _deptCode,_deptTypeCode,_userVO);
	}

	
	public Map getDepartmentUnitEnquiryDetail(String _deptUnitCode,UserVO _userVO){
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getDepartmentUnitEnquiryDetail( _deptUnitCode,_userVO);
	}

	/* ******************** Hospital Consultant Enquiry *******************************/
	
	public Map getConsultantEnquiryEssential(String processType, UserVO _uservo) {
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getConsultantEnquiryEssential(processType, _uservo);
	}

	public Map getConsultantEnquiryDetailByEmpNo(String empNo, UserVO _uservo) {
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getConsultantEnquiryDetailByEmpNo(empNo,_uservo);
	}

	public Map getConsultantEnquiryDetailByName(HospitalConsultantEnquiryVO consultantVO,
			UserVO _uservo) {
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getConsultantEnquiryDetailByName(consultantVO,_uservo);
	}

	public Map getConsultantEnquiryUnitDetail(String departmentUnitCode,UserVO _uservo) {
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getConsultantEnquiryUnitDetail(departmentUnitCode,_uservo);
	}
		
	
	/* *********************Ward Enquiry **************************************/
	
	public Map getWardEnquiryEssential(UserVO _uservo) {
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getWardEnquiryEssential(_uservo);
	}

	public List getWardEnquiryDetail(String wardCode, UserVO _uservo) {
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getWardEnquiryDetail(wardCode,_uservo);
	}


	//*********************Hospital Enquiry************************/
	public Map  getHospitalEssentials(UserVO userVO){
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getHospitalEssentials( userVO);
	}
	
	

	
	/* *******************OPD Schedule Enquiry **************************/
	public Map getOpdScheduleEnquiryEssentials(UserVO _uservo) {
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getOpdScheduleEnquiryEssential(_uservo);
	}

	public Map getUnitWorkingDetail(String departmentCode, UserVO _uservo) {
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getUnitWorkingDetail(departmentCode, _uservo);
	}

	public Map getSpecialClinicWorkingDetail(String departmentUnitCode,
			UserVO _uservo) {
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getSpecialClinicWorkingDetail(departmentUnitCode, _uservo);
	}

	//telephone Enquiry
	public Map getTelephoneEnquiryEssentials(UserVO _uservo) {
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getTelephoneEnquiryEssentials( _uservo);
	}

	//Operation Theater Enquiry
	public Map getOperationTheaterEnquiryEssential(UserVO _uservo) {
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getOperationTheaterEnquiryEssential( _uservo);
	}

	/* ************************* Hospital Facility Master****************************/
	
	public List getAllHospitalFacilityList(String isValid, UserVO _uservo) {
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getAllHospitalFacilityList(isValid, _uservo);
	}

	public void saveHospitalFacility(
			HospitalFacilityMasterVO hospitalFacilityMasterVO, UserVO userVO) {
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		 serviceBO.saveHospitalFacility(hospitalFacilityMasterVO,userVO);
	}

	public HospitalFacilityMasterVO getHospitalFacility(
			HospitalFacilityMasterVO hospitalFacilityMasterVO, UserVO userVO) {
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getHospitalFacility(hospitalFacilityMasterVO,userVO);
	}

	public void modifyHospitalFacility(
			HospitalFacilityMasterVO hospitalFacilityMasterVO, UserVO userVO) {
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		 serviceBO.modifyHospitalFacility(hospitalFacilityMasterVO,userVO);
	}

	public void deleteHospitalFacility(	HospitalFacilityMasterVO[] hospitalFacilityMasterVO, UserVO userVO) {
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		 serviceBO.deleteHospitalFacility(hospitalFacilityMasterVO,userVO);
	}

	public void changeDisplayOrder(HospitalFacilityMasterVO[] hospitalFacilityMasterVO, UserVO userVO) {
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		 serviceBO.changeDisplayOrder(hospitalFacilityMasterVO,userVO);
		
	}


	public Map getAllHospitalTarrifList(String groupId, UserVO _uservo) {
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getAllHospitalTarrifList(groupId,_uservo);
	}

	public Map getChargeDetailByTariff(String tariffId, UserVO userVO) {
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getChargeDetailByTariff(tariffId,userVO);
	}

	public List getLabEnquiryEssential(UserVO _uservo) {
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getLabEnquiryEssential(_uservo);
	}

	public List getLabTestList(String labCode, UserVO userVO) {
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getLabTestList(labCode,userVO);
	}

	public Map getOTConsultantEnquiryEssential(UserVO _uservo) {
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getOTConsultantEnquiryEssential(_uservo);
	}

	public List getOTConsultantDetail(String empNo,
			UserVO _uservo) {
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getOTConsultantDetail(empNo,_uservo);
	}

	public Map getHolidayList(String year, UserVO userVO) {
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getHolidayList(year,userVO);
	}

	public List getGuestHouseList(UserVO userVO) {
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getGuestHouseList(userVO);
	}

	public List getGuestHouseBedDetail(String guestHouse, UserVO userVO) {
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getGuestHouseBedDetail(guestHouse,userVO);
	}
	
	public List getTemplateListForGuidlines(UserVO userVO) {
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getTemplateListForGuidlines(userVO);
	}
	
	public List<DeceasedDetailVO> getAllDeceasedListInMortuary(UserVO userVO)
	{
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getAllDeceasedListInMortuary(userVO);
	}
	
	public Map getDeceasedDtlByDeceaseNo(String deceasedNo,UserVO userVO)
	{
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getDeceasedDtlByDeceaseNo(deceasedNo,userVO);
	}
	
	public Map getEssentialForDeceasedEnquiry(UserVO userVO)
	{
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getEssentialForDeceasedEnquiry(userVO);
	}
	
	public DeceasedDetailVO[] searchDeceased(String fName,String mName,String lName,String genderCode,String fromDate,String toDate,String chkUnknown,String chkUnclaimed,UserVO userVO)
	{
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.searchDeceased(fName,mName,lName,genderCode,fromDate,toDate,chkUnknown,chkUnclaimed,userVO);
	}
	
	public Map getSearchDeceasedDtlByDeceaseNo(String deceasedNo,UserVO userVO)
	{
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getSearchDeceasedDtlByDeceaseNo(deceasedNo,userVO);
	}
}
