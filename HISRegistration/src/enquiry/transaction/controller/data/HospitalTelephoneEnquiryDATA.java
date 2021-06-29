package enquiry.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;

import java.util.Map;

import enquiry.bo.delegate.EnquiryDelegate;

public class HospitalTelephoneEnquiryDATA extends ControllerDATA {
	
	public static Map getTelephoneEnquiryEssentials(UserVO _userVO){
		EnquiryDelegate enqDelegate=new EnquiryDelegate();
		return enqDelegate.getTelephoneEnquiryEssentials(_userVO);
	}

	
}
