
package enquiry.transaction.controller.data;

import java.util.Map;

import enquiry.bo.delegate.EnquiryDelegate;
import enquiry.vo.HospitalDepartmentEnquiryVO;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;

public class HospitalEnquiryDATA extends ControllerDATA {
	
	public static Map getHospitalEssentials(UserVO _userVO)
	{
		EnquiryDelegate delegate=new EnquiryDelegate();
		return delegate.getHospitalEssentials(_userVO);
	}
	

}
