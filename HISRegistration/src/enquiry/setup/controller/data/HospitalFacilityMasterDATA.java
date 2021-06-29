package enquiry.setup.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;

import java.util.List;

import enquiry.bo.delegate.EnquiryDelegate;
import enquiry.vo.HospitalFacilityMasterVO;

public class HospitalFacilityMasterDATA extends ControllerDATA {
	
	public static List getAllHospitalFacilityList(String isValid, UserVO _userVO){
		EnquiryDelegate enqDelegate=new EnquiryDelegate();
		return enqDelegate.getAllHospitalFacilityList(isValid,_userVO);
	}

	public static void saveHospitalFacility(HospitalFacilityMasterVO hospitalFacilityMasterVO, UserVO userVO) {
		EnquiryDelegate enqDelegate=new EnquiryDelegate();
		enqDelegate.saveHospitalFacility(hospitalFacilityMasterVO,userVO);
		
	}

	public static HospitalFacilityMasterVO getHospitalFacility(HospitalFacilityMasterVO hospitalFacilityMasterVO, UserVO userVO) {
		EnquiryDelegate enqDelegate=new EnquiryDelegate();
		return enqDelegate.getHospitalFacility(hospitalFacilityMasterVO,userVO);
	}

	public static void modifyHospitalFacility(
			HospitalFacilityMasterVO hospitalFacilityMasterVO, UserVO userVO) {
		EnquiryDelegate enqDelegate=new EnquiryDelegate();
		enqDelegate.modifyHospitalFacility(hospitalFacilityMasterVO,userVO);
	}

	public static void deleteHospitalFacility(
			HospitalFacilityMasterVO[] hospitalFacilityMasterVO, UserVO userVO) {
		EnquiryDelegate enqDelegate=new EnquiryDelegate();
		enqDelegate.deleteHospitalFacility(hospitalFacilityMasterVO,userVO);
		
	}

	public static void changeDisplayOrder(HospitalFacilityMasterVO[] hospitalFacilityMasterVO, UserVO userVO) {
		EnquiryDelegate enqDelegate=new EnquiryDelegate();
		enqDelegate.changeDisplayOrder(hospitalFacilityMasterVO,userVO);
	}
	
}
