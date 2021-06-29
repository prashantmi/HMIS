package enquiry.transaction.controller.data;

import enquiry.bo.delegate.EnquiryDelegate;
import enquiry.vo.HospitalConsultantEnquiryVO;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;

import java.util.*;

public class HospitalConsultantEnquiryDATA extends ControllerDATA {
	
	public static Map getConsultantEnquiryEssential(String processType, UserVO _uservo) {
		EnquiryDelegate enquiryDelegate=new EnquiryDelegate();
		return enquiryDelegate.getConsultantEnquiryEssential(processType, _uservo);
	}

	/*public static Map getConsultantEnquiryDetailByEmpNo(String empNo, UserVO _uservo) {
		EnquiryDelegate enquiryDelegate=new EnquiryDelegate();
		return enquiryDelegate.getConsultantEnquiryDetailByEmpNo(empNo,_uservo);
	}*/

	public static Map getConsultantEnquiryDetailByName(HospitalConsultantEnquiryVO consultantVO,
			UserVO _uservo) {
		EnquiryDelegate enquiryDelegate=new EnquiryDelegate();
		return enquiryDelegate.getConsultantEnquiryDetailByName(consultantVO,_uservo);
	}

	public static Map getConsultantEnquiryUnitDetail(String departmentUnitCode,UserVO _uservo) {
		EnquiryDelegate enquiryDelegate=new EnquiryDelegate();
		return enquiryDelegate.getConsultantEnquiryUnitDetail(departmentUnitCode,_uservo);
	}
}

