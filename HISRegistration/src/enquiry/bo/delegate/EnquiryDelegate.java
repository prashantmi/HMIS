package enquiry.bo.delegate;

import hisglobal.business.Delegate;
import hisglobal.vo.AddressEnqiryVO;
//import hisglobal.vo.BloodStockEnquiryVO;
import hisglobal.vo.CommonEnquiryVO;
//import hisglobal.vo.ConsultantDetailVO;
//import hisglobal.vo.DeceasedDetailVO;
//import hisglobal.vo.DepartmentLocationEnquiryVO;
import hisglobal.vo.EpisodeEnquiryVO;
//import hisglobal.vo.OpdEnquiryVO;
import hisglobal.vo.PatientEnquiryVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

//import registration.bo.EssentialBOi;
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
	
	
	
	public CommonEnquiryVO[] searchPatientDetails(PatientEnquiryVO _patEnquiryVO,AddressEnqiryVO _addEnquiryVO,EpisodeEnquiryVO _episodeEnquiryVO,UserVO _userVO){
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.searchPatientDetails(_patEnquiryVO,_addEnquiryVO,_episodeEnquiryVO,_userVO);
	}
	
	public CommonEnquiryVO[] searchInPatientDetails(PatientEnquiryVO _patEnquiryVO,AddressEnqiryVO _addEnquiryVO,EpisodeEnquiryVO _episodeEnquiryVO,UserVO _userVO){
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.searchInPatientDetails(_patEnquiryVO,_addEnquiryVO,_episodeEnquiryVO,_userVO);
	}
	
	

	
	public Map getPatientDetail(String patientCRNo,UserVO userVO){
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getInPatientDetail(patientCRNo, userVO);
	}
	
	
	public Map getStaffEnquiryEssentials(UserVO _userVO){
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getStaffEnquiryEssentials(_userVO);
		
	}

	

	public Map getConsultantDetailEssentials(UserVO _userVO){
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getConsultantDetailEnquiryEssentials(_userVO);
	}
	
	
	/* ******************** Hospital Consultant Enquiry *******************************/
	
	public Map getConsultantEnquiryEssential(String processType, UserVO _uservo) {
		EnquiryBOi serviceBO=(EnquiryBOi)super.getServiceProvider();
		return serviceBO.getConsultantEnquiryEssential(processType, _uservo);
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
	
	
	public List getUnitBasedOnDept(String _deptCode, UserVO _userVO) {
		EnquiryBOi serviceBO = (EnquiryBOi) super.getServiceProvider();
		return serviceBO.getUnitBasedOnDept(_deptCode, _userVO);
	}
	
	public List getWardBasedOndept(String _deptCode, UserVO _userVO) {
		EnquiryBOi serviceBO = (EnquiryBOi) super.getServiceProvider();
		return (serviceBO.getWardBasedOnDept(_deptCode, _userVO));
	}
}
