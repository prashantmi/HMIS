package enquiry.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;

import java.util.List;

import enquiry.bo.delegate.EnquiryDelegate;
import enquiry.vo.HospitalFacilityMasterVO;

public class HospitalFacilityEnquiryDATA extends ControllerDATA {
	
	public static List getAllHospitalFacilityList(String isValid, UserVO _userVO){
		EnquiryDelegate enqDelegate=new EnquiryDelegate();
		return enqDelegate.getAllHospitalFacilityList(isValid,_userVO);
	}
		
}
