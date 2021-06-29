package enquiry.bo;

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

import enquiry.vo.BloodDonorEnquriyVO;
import enquiry.vo.HospitalConsultantEnquiryVO;
import enquiry.vo.HospitalDepartmentEnquiryVO;
import enquiry.vo.HospitalFacilityMasterVO;
import enquiry.vo.StaffEnquiryVO;

public interface EnquiryBOi {
	
	public  Map getPatientEnquiryEssentials(UserVO _userVO);
	
	public CommonEnquiryVO[] searchPatientDetails(PatientEnquiryVO _patEnquiryVO,AddressEnqiryVO _addEnquiryVO,EpisodeEnquiryVO _episodeEnquiryVO,UserVO _userVO);

	public  OpdEnquiryVO[] getOpdEnquirySearchDetail(OpdEnquiryVO _opdEnquiryVO,UserVO _userVO);

	public Map getDepartmentLocationEnquiryEssentials(UserVO _userVO);
	
	public DepartmentLocationEnquiryVO searchDepatmentLocation(String _departmentCode,UserVO _userVO);
	
	public OpdEnquiryVO[] getAllConsulatantDetailsForUnit(String _deptUnitCode,UserVO _userVO);

	public ConsultantDetailVO[] searchConsultantDetail(ConsultantDetailVO _consultantDetailVO,UserVO _userVO);

	public Map getConsultantDetailEnquiryEssentials(UserVO _userVO);

	public  BloodStockEnquiryVO[] getBloodStockEnquiry(UserVO _userVO,String _choice);
	
	//public CommonEnquiryVO[] searchBloodStock(PatientEnquiryVO _patEnquiryVO,AddressEnqiryVO _addEnquiryVO,EpisodeEnquiryVO _episodeEnquiryVO,UserVO _userVO);
    
	public  List getBloodDonorGroups(UserVO _userVO);
	
	public  BloodDonorEnquriyVO[] getBloodDonorEnquiryDetails(UserVO _userVO,BloodDonorEnquriyVO _bloodDonorVO);
	
	public CommonEnquiryVO[] searchInPatientDetails(PatientEnquiryVO _patEnquiryVO,AddressEnqiryVO _addEnquiryVO,EpisodeEnquiryVO _episodeEnquiryVO,UserVO _userVO);

	public Map getInPatientDetail(String patientCRNo,UserVO _userVO);
	
	public Map getStaffEnquiryEssentials(UserVO _userVO);
	
	public StaffEnquiryVO[] searchStaffDetail(StaffEnquiryVO staffEnquiryVO,UserVO _userVO);
	
	public List getFreePatCatList(UserVO userVO);
	
	public Map getDepartmentEnquiryEssential(UserVO _userVO);
	

	public HospitalDepartmentEnquiryVO[] getDepartmentEnquiryDetail(String _deptCode,String _deptTypeCode,UserVO _userVO);

	
	//Consultant Enquiry
	
	public Map getConsultantEnquiryEssential(String processType, UserVO _uservo);

	public Map getConsultantEnquiryDetailByEmpNo(String empNo, UserVO _uservo);

	public Map getConsultantEnquiryDetailByName(HospitalConsultantEnquiryVO consultantVO,UserVO _uservo);
	
	public Map getConsultantEnquiryUnitDetail(String deptUnit,UserVO userVO);


	
	public Map getDepartmentUnitEnquiryDetail(String _deptUnitCode,UserVO _userVO);

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
	
	public List getTemplateListForGuidlines(UserVO _uservo);
	
	public List<DeceasedDetailVO> getAllDeceasedListInMortuary(UserVO userVO);
	
	public Map getDeceasedDtlByDeceaseNo(String deceasedNo,UserVO userVO);
	
	public Map getEssentialForDeceasedEnquiry(UserVO userVO);
	
	public DeceasedDetailVO[] searchDeceased(String fName,String mName,String lName,String genderCode,String fromDate,String toDate,String chkUnknown,String chkUnclaimed,UserVO userVO);
	
	public Map getSearchDeceasedDtlByDeceaseNo(String deceasedNo,UserVO userVO);
	
}

