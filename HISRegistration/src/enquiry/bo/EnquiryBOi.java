package enquiry.bo;

import hisglobal.vo.AddressEnqiryVO;
import hisglobal.vo.CommonEnquiryVO;
import hisglobal.vo.EpisodeEnquiryVO;
import hisglobal.vo.PatientEnquiryVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import enquiry.vo.BloodDonorEnquriyVO;
import enquiry.vo.HospitalConsultantEnquiryVO;
import enquiry.vo.HospitalDepartmentEnquiryVO;
import enquiry.vo.HospitalFacilityMasterVO;
import enquiry.vo.StaffEnquiryVO;

public interface EnquiryBOi {
	
	public  Map getPatientEnquiryEssentials(UserVO _userVO);
	
	public CommonEnquiryVO[] searchPatientDetails(PatientEnquiryVO _patEnquiryVO,AddressEnqiryVO _addEnquiryVO,EpisodeEnquiryVO _episodeEnquiryVO,UserVO _userVO);

	//public  OpdEnquiryVO[] getOpdEnquirySearchDetail(OpdEnquiryVO _opdEnquiryVO,UserVO _userVO);

		
	//public DepartmentLocationEnquiryVO searchDepatmentLocation(String _departmentCode,UserVO _userVO);
	
	//public OpdEnquiryVO[] getAllConsulatantDetailsForUnit(String _deptUnitCode,UserVO _userVO);

	//public ConsultantDetailVO[] searchConsultantDetail(ConsultantDetailVO _consultantDetailVO,UserVO _userVO);

	public Map getConsultantDetailEnquiryEssentials(UserVO _userVO);

	//public  BloodStockEnquiryVO[] getBloodStockEnquiry(UserVO _userVO,String _choice);
	
	//public CommonEnquiryVO[] searchBloodStock(PatientEnquiryVO _patEnquiryVO,AddressEnqiryVO _addEnquiryVO,EpisodeEnquiryVO _episodeEnquiryVO,UserVO _userVO);
    
	
	
	public CommonEnquiryVO[] searchInPatientDetails(PatientEnquiryVO _patEnquiryVO,AddressEnqiryVO _addEnquiryVO,EpisodeEnquiryVO _episodeEnquiryVO,UserVO _userVO);

	public Map getInPatientDetail(String patientCRNo,UserVO _userVO);
	
	public Map getStaffEnquiryEssentials(UserVO _userVO);
	
	
	
	
	
	public Map getDepartmentEnquiryEssential(UserVO _userVO);
	

	
	
	//Consultant Enquiry
	
	public Map getConsultantEnquiryEssential(String processType, UserVO _uservo);

	
	public Map getConsultantEnquiryDetailByName(HospitalConsultantEnquiryVO consultantVO,UserVO _uservo);
	
	public Map getConsultantEnquiryUnitDetail(String deptUnit,UserVO userVO);


	
	

	// Ward Enquiry
	public Map getWardEnquiryEssential(UserVO _uservo);

	public List getWardEnquiryDetail(String wardCode, UserVO _uservo);
	
	
	///For Hospital Enquiry
	
	public Map getHospitalEssentials(UserVO _userVO);

	// Schedule Enquiry
	public Map getOpdScheduleEnquiryEssential(UserVO _uservo);
	
	public Map getUnitWorkingDetail(String departmentCode,UserVO _uservo);

	public Map getSpecialClinicWorkingDetail(String departmentUnitCode,UserVO _uservo);

	public Map getTelephoneEnquiryEssentials(UserVO _uservo);

	public Map getOperationTheaterEnquiryEssential(UserVO _uservo);

	public List getAllHospitalFacilityList(String isValid, UserVO _uservo);

	public void saveHospitalFacility(HospitalFacilityMasterVO hospitalFacilityMasterVO, UserVO userVO);

	public HospitalFacilityMasterVO getHospitalFacility(HospitalFacilityMasterVO hospitalFacilityMasterVO, UserVO userVO);

	public void modifyHospitalFacility(HospitalFacilityMasterVO hospitalFacilityMasterVO, UserVO userVO);

	public void deleteHospitalFacility(HospitalFacilityMasterVO[] hospitalFacilityMasterVO, UserVO userVO);

	public void changeDisplayOrder(HospitalFacilityMasterVO[] hospitalFacilityMasterVO, UserVO userVO);

	public Map getAllHospitalTarrifList(String groupId, UserVO _uservo);

	public Map getChargeDetailByTariff(String tariffId, UserVO userVO);

	public List getLabEnquiryEssential(UserVO _uservo);

	public List getLabTestList(String labCode, UserVO userVO);

	public Map getOTConsultantEnquiryEssential(UserVO _uservo);

	public List getOTConsultantDetail(String empNo,
			UserVO _uservo);

	public Map getHolidayList(String year, UserVO userVO);

	public List getGuestHouseList(UserVO userVO);

	public List getGuestHouseBedDetail(String guestHouse, UserVO userVO);
	
	
	
	//public List<DeceasedDetailVO> getAllDeceasedListInMortuary(UserVO userVO);
	
	public List getWardBasedOnDept(String _deptCode,UserVO _userVO);
	public List getUnitBasedOnDept(String _deptCode,UserVO _userVO);
	
}

