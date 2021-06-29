package enquiry.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import enquiry.bo.delegate.EnquiryDelegate;
import enquiry.vo.HospitalDepartmentEnquiryVO;

public class OperationTheaterEnquiryDATA extends ControllerDATA {
	
	public static Map getOperationTheaterEnquiryEssential(UserVO _userVO)
	{
		EnquiryDelegate delegate=new EnquiryDelegate();
		return delegate.getOperationTheaterEnquiryEssential(_userVO);
	}

}
