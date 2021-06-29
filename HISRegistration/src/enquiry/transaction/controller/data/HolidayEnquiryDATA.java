package enquiry.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import enquiry.bo.delegate.EnquiryDelegate;
import enquiry.vo.HospitalFacilityMasterVO;

public class HolidayEnquiryDATA extends ControllerDATA {
	
	public static Map getHolidayList(String year, UserVO userVO) {
		EnquiryDelegate enqDelegate=new EnquiryDelegate();
		return enqDelegate.getHolidayList(year,userVO);
	}
		
}
