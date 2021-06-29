package enquiry.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;

import java.util.Map;

import enquiry.bo.delegate.EnquiryDelegate;

public class ConsultantDetailEnquiryDATA extends ControllerDATA {
	public static Map getConsultantEssential(UserVO _userVO){
		EnquiryDelegate enquiryDelegate=new EnquiryDelegate();
		return enquiryDelegate.getConsultantDetailEssentials(_userVO);
	}
	
	/*public static ConsultantDetailVO[] searchConsultantDetails(ConsultantDetailVO _consultantDetailVO,UserVO _userVO){
		EnquiryDelegate enquiryDelegate=new EnquiryDelegate();
		return enquiryDelegate.searchConsultantDetail(_consultantDetailVO,_userVO);
	}*/
}

