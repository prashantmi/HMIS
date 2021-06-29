package enquiry.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import enquiry.bo.delegate.EnquiryDelegate;
import enquiry.vo.HospitalDepartmentEnquiryVO;

public class HospitalLabEnquiryDATA extends ControllerDATA {

	public static List getLabEnquiryEssential(UserVO _userVO)
	{
		EnquiryDelegate delegate=new EnquiryDelegate();
		return delegate.getLabEnquiryEssential(_userVO);
	}

	public static List getLabTestList(String labCode, UserVO userVO) {
		EnquiryDelegate delegate=new EnquiryDelegate();
		return delegate.getLabTestList(labCode,userVO);
	}


}
