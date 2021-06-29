package enquiry.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import enquiry.bo.delegate.EnquiryDelegate;
import enquiry.vo.HospitalFacilityMasterVO;

public class GuestHouseEnquiryDATA extends ControllerDATA {
	
	public static List getGuestHouseList(UserVO userVO) {
		EnquiryDelegate enqDelegate=new EnquiryDelegate();
		return enqDelegate.getGuestHouseList(userVO);
	}

	public static List getGuestHouseBedDetail(String guestHouse, UserVO userVO) {
		EnquiryDelegate enqDelegate=new EnquiryDelegate();
		return enqDelegate.getGuestHouseBedDetail(guestHouse,userVO);
	}
		
}
