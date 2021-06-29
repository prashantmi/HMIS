package enquiry.transaction.controller.data;

import enquiry.bo.delegate.EnquiryDelegate;
import enquiry.vo.HospitalConsultantEnquiryVO;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;

import java.util.*;

public class OTConsultantEnquiryDATA extends ControllerDATA {
	
	public static Map getOTConsultantEnquiryEssential(UserVO _uservo) {
		EnquiryDelegate enquiryDelegate=new EnquiryDelegate();
		return enquiryDelegate.getOTConsultantEnquiryEssential( _uservo);
	}

	public static List getOTConsultantDetail(
			String empNo, UserVO _uservo) {
		EnquiryDelegate enquiryDelegate=new EnquiryDelegate();
		return enquiryDelegate.getOTConsultantDetail(empNo, _uservo);
	}
	
}

